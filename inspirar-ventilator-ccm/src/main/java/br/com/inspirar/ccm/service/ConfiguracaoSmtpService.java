package br.com.inspirar.ccm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.inspirar.ccm.model.ConfiguracaoSmtp;
import br.com.inspirar.ccm.repository.ConfiguracaoSmtpRepository;

@Service
public class ConfiguracaoSmtpService {

    @Autowired
    ConfiguracaoSmtpRepository configuracaoSmtpRepository;
    
	public void adicionarSmtp(ConfiguracaoSmtp configuracaoSmtp) {
		
		br.com.inspirar.ccm.entity.ConfiguracaoSmtpEntity entity = new br.com.inspirar.ccm.entity.ConfiguracaoSmtpEntity(configuracaoSmtp);
		
		configuracaoSmtpRepository.save(entity);
		
	}
}