package br.com.inspirar.ccm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.inspirar.ccm.entity.UsuarioEntity;
import br.com.inspirar.ccm.enuns.PerfilEnum;
import br.com.inspirar.ccm.model.DadosMonitoramentoStorage;
import br.com.inspirar.ccm.repository.DadosMonitoramentoStorageRepository;

@Service
public class DadosMonitoramentoStorageService {

	
    @Autowired
    DadosMonitoramentoStorageRepository dadosMonitoramentoStorageRepository;
    
    @Autowired
    UsuarioService usuarioService;
    
	public void adicionarConfig(DadosMonitoramentoStorage dadosMonitoramentoStorage) {
		
		UsuarioEntity usuario =  usuarioService.findByUsernameAndEnabledTrue(dadosMonitoramentoStorage.getImei());
		
		if(usuario == null ) {
			usuario = new UsuarioEntity();
			usuario.setEnabled(true);
			usuario.setEmail("inspirarvic19@gmail.com");
			usuario.setRole(PerfilEnum.SYSTEM);
			usuario.setUsername(dadosMonitoramentoStorage.getImei());
			usuario.setPassword(dadosMonitoramentoStorage.getImei());
			usuarioService.salvarUsuario(usuario);
		}
		
		br.com.inspirar.ccm.entity.monitoramento.DadosMonitoramentoStorageEntity entity = dadosMonitoramentoStorageRepository.findByImei(dadosMonitoramentoStorage.getImei());
		if(entity == null) {
			entity = new br.com.inspirar.ccm.entity.monitoramento.DadosMonitoramentoStorageEntity(dadosMonitoramentoStorage);
		}
		dadosMonitoramentoStorageRepository.save(entity);
		
	}
}

