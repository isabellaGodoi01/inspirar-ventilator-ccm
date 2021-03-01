package br.com.inspirar.ccm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.inspirar.ccm.entity.monitoramento.PcvStorageEntity;
import br.com.inspirar.ccm.repository.PcvStorageRepository;

@Service
public class PcvStorageService {

    @Autowired
    PcvStorageRepository pcvStorageRepository;

    public PcvStorageEntity save(PcvStorageEntity entity){

        return pcvStorageRepository.save(entity);

    }

}
