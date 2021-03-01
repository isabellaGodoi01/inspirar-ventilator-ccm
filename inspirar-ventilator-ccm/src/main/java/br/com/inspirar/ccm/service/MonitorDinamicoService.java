package br.com.inspirar.ccm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.inspirar.ccm.entity.monitoramento.MonitorDinamicoEntity;
import br.com.inspirar.ccm.repository.MonitorDinamicoRepository;

@Service
public class MonitorDinamicoService {

    @Autowired
    MonitorDinamicoRepository monitorDinamicoRepository;

    public MonitorDinamicoEntity save(MonitorDinamicoEntity entity){
        return monitorDinamicoRepository.save(entity);

    }

}
