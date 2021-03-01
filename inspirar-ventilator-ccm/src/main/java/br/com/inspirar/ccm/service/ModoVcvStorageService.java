package br.com.inspirar.ccm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.inspirar.ccm.entity.monitoramento.ModoVcvStorageEntity;
import br.com.inspirar.ccm.repository.ModoVcvStorageRepository;

@Service
public class ModoVcvStorageService {

    @Autowired
    ModoVcvStorageRepository modoVcvStorageRepository;

    public ModoVcvStorageEntity save(ModoVcvStorageEntity etitty){

        return modoVcvStorageRepository.save(etitty);

    }

}
