package br.com.inspirar.ccm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.inspirar.ccm.entity.ConfiguracaoSmtpEntity;
 
public interface ConfiguracaoSmtpRepository extends JpaRepository<ConfiguracaoSmtpEntity, Integer> {
 
}