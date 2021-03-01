package br.com.inspirar.ccm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.inspirar.ccm.entity.monitoramento.ModoPcvStorageEntity;
import br.com.inspirar.ccm.repository.ModoPcvStorageRepository;

@Service
public class ModoPcvStorageService {

    @Autowired
    ModoPcvStorageRepository modoPcvStorageRepository;

    public ModoPcvStorageEntity save(ModoPcvStorageEntity etitty){

        return modoPcvStorageRepository.save(etitty);

    }

}
