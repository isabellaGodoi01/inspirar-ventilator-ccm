package br.com.inspirar.ccm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.inspirar.ccm.model.ListaMonitoramento;

@Component
public class MonitorService {

	@Autowired
	SimpMessagingTemplate template;

	@Autowired
	MonitoramentoService monitoramentoService;

	@Scheduled(fixedDelay = 10000, initialDelay = 0)
	@Primary
	public void graficosRead() throws InterruptedException {

		ListaMonitoramento listaMonitoramento =  monitoramentoService.recuperarMonitoramento();

		publicarGraficos(listaMonitoramento);
				
	}

	public void publicarGraficos(ListaMonitoramento listaMonitoramento) throws InterruptedException {

		template.convertAndSend("/topic/monitor", listaMonitoramento);

	}
}

