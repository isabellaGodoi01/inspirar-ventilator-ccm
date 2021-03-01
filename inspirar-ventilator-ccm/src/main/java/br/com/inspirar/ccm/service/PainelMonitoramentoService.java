package br.com.inspirar.ccm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.inspirar.ccm.builder.PainelMonitoramentoBuilder;
import br.com.inspirar.ccm.entity.monitoramento.PainelMonitoramentoEntity;
import br.com.inspirar.ccm.model.PainelMonitoramento;
import br.com.inspirar.ccm.repository.PainelMonitoramentoRepository;

@Service
public class PainelMonitoramentoService {

	private static final Logger LOG = LoggerFactory.getLogger(PainelMonitoramentoService.class);
	
    @Autowired
    PainelMonitoramentoRepository painelMonitoramentoRepository;
   
    @Autowired
    PainelMonitoramentoGraficoService graficoService;
    
    @Autowired 
    MonitorDinamicoService monitorDinamicoService;

    @Autowired 
    DataStorageService dataStorageService;

    @Autowired 
    PainelMonitoramentoBuilder painelMonitoramentoBuilder;
    
    public void save(PainelMonitoramento painelMonitoramento){
    	
    	Gson g =  new Gson();
    	LOG.warn(g.toJson(painelMonitoramento));
    	
    	PainelMonitoramentoEntity entity = painelMonitoramentoBuilder.createPainelMonitoramento(painelMonitoramento);
		
    	dataStorageService.save(entity.getDataStorage());
    	
    	monitorDinamicoService.save(entity.getMonitoramentoDinamico());

    	painelMonitoramentoRepository.save(entity);

    	graficoService.save(entity.getGraficos());

    }

}
