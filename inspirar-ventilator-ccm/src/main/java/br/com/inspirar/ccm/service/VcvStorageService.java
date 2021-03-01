package br.com.inspirar.ccm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.inspirar.ccm.entity.monitoramento.VcvStorageEntity;
import br.com.inspirar.ccm.repository.VcvStorageRepository;

@Service
public class VcvStorageService {

    @Autowired
    VcvStorageRepository vcvStorageRepository;

    public VcvStorageEntity save(VcvStorageEntity entity){

        return vcvStorageRepository.save(entity);

    }

}
