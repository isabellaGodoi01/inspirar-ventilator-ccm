package br.com.inspirar.ccm.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.inspirar.ccm.model.Usuario;
import br.com.inspirar.ccm.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    
	public void salvarUsuario(Usuario usuario) {
		
		br.com.inspirar.ccm.entity.UsuarioEntity entity = new br.com.inspirar.ccm.entity.UsuarioEntity(usuario);
		entity.setPassword(passwordEncoder.encode(usuario.getPassword()));
		entity.setEnabled(true);
		
		usuarioRepository.save(entity);
		
	}
	
	public void salvarUsuario(br.com.inspirar.ccm.entity.UsuarioEntity entity) {
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		usuarioRepository.save(entity);
		
	}

	public void desabilitar(Integer idUsuario) {
		
		usuarioRepository.desabilitar(idUsuario);
		
	}

	public br.com.inspirar.ccm.entity.UsuarioEntity findByUsernameAndEnabledTrue(String username) {

		return usuarioRepository.findByUsernameAndEnabledTrue(username);
	}

	public String recuperarSenha(String email) {
		
		br.com.inspirar.ccm.entity.UsuarioEntity entity = usuarioRepository.findByEmail(email);
		
		if(entity != null) {
			entity.setPassword(passwordEncoder.encode("inspirar_" + LocalDate.now().getYear()));
			entity.setEnabled(true);
			
			usuarioRepository.save(entity);
			
			return "inspirar_" + LocalDate.now().getYear();
		}
		
		 throw new UsernameNotFoundException("Could not find user");

	}
}