package br.com.inspirar.ccm.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.inspirar.ccm.entity.UsuarioEntity;
 
@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
 
	UsuarioEntity findByUsernameAndEnabledTrue(String username);
    
	@Modifying
	@Transactional
	@Query("UPDATE FROM UsuarioEntity u SET u.enabled = false WHERE u.id = :id ")
    void desabilitar(@Param("id") Integer id);

	@Modifying
	@Transactional
	@Query("UPDATE FROM UsuarioEntity u SET u.password = :passwword WHERE u.id = :id")
    void alterarSenha(@Param("passwword") String passwword, @Param("id") Integer id);
	
	UsuarioEntity findByEmail(String email);

}