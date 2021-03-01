package br.com.inspirar.ccm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.inspirar.ccm.entity.monitoramento.PainelMonitoramentoGraficoEntity;
import br.com.inspirar.ccm.repository.PainelMonitoramentoGraficoRepository;

@Service
public class PainelMonitoramentoGraficoService {

    @Autowired
    PainelMonitoramentoGraficoRepository graficoRepository;
    
    public void save(List<PainelMonitoramentoGraficoEntity> entities ){
    	
    	entities.stream().forEach(mg -> {
    		if(mg.getId() == null)
    			graficoRepository.save(mg);
    	});

    }

}
