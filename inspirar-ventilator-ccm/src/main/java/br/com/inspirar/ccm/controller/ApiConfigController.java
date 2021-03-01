package br.com.inspirar.ccm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.inspirar.ccm.api.ConfigApi;
import br.com.inspirar.ccm.model.ConfiguracaoSmtp;
import br.com.inspirar.ccm.model.DadosMonitoramentoStorage;
import br.com.inspirar.ccm.service.ConfiguracaoSmtpService;
import br.com.inspirar.ccm.service.DadosMonitoramentoStorageService;
import io.swagger.annotations.Api;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@ApiIgnore
@Api(hidden = true)
public class ApiConfigController implements ConfigApi {

	@Autowired
	ConfiguracaoSmtpService configuracaoSmtpService;
	
	@Autowired
	DadosMonitoramentoStorageService dadosMonitoramentoStorageService;
	
	@Override
	public ResponseEntity<Void> adicionarConfig(@Valid DadosMonitoramentoStorage monitoramento) {
		
		dadosMonitoramentoStorageService.adicionarConfig(monitoramento);
		
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> adicionarSmtp(@Valid ConfiguracaoSmtp smtp) {
		
		configuracaoSmtpService.adicionarSmtp(smtp);
		
		return ResponseEntity.noContent().build();
	}

	
}
