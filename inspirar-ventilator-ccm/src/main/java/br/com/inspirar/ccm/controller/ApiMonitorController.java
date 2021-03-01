package br.com.inspirar.ccm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.inspirar.ccm.api.GraficoApi;
import br.com.inspirar.ccm.api.MonitorApi;
import br.com.inspirar.ccm.model.ListaMonitoramento;
import br.com.inspirar.ccm.model.Monitoramento;
import br.com.inspirar.ccm.model.PainelMonitoramento;
import br.com.inspirar.ccm.service.MonitoramentoService;
import br.com.inspirar.ccm.service.PainelMonitoramentoService;
import br.com.inspirar.ccm.service.email.EmailService;


@RestController
public class ApiMonitorController implements MonitorApi, GraficoApi{

	private static final Logger LOG = LoggerFactory.getLogger(ApiMonitorController.class);
	
	@Autowired
	MonitoramentoService monitoramentoService;

	@Autowired
	PainelMonitoramentoService painelMonitoramentoService;

	@Autowired
	EmailService emailService;

	@Override
	public ResponseEntity<Void> adicionarPainelMonitoramento(@RequestBody PainelMonitoramento painelMonitoramento) {
		
		painelMonitoramentoService.save(painelMonitoramento);
		
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> adicionarMonitoramento(@RequestBody Monitoramento monitoramento) {
		
		monitoramentoService.save(monitoramento);
		
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> removerMonitoramento(@PathVariable("key") Integer key) {
		try {
			monitoramentoService.bloquearMonitoramento(key);
		} catch (InterruptedException e) {
			LOG.error(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}


	@Override
	public ResponseEntity<Object> recuperarMonitoramento() {
		ListaMonitoramento listaMonitoramento = monitoramentoService.recuperarMonitoramento();
		return ResponseEntity.ok(listaMonitoramento);
	}
}
