package br.com.inspirar.ccm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.inspirar.ccm.entity.monitoramento.DataStorageEntity;
import br.com.inspirar.ccm.repository.DataStorageRepository;

@Service
public class DataStorageService {

    @Autowired
	DataStorageRepository dataStorageRepository;

	@Autowired
	ModoVcvStorageService modoVcvStorageService;

	@Autowired
	VcvStorageService vcvStorageService;

	@Autowired
	PcvStorageService pcvStorageService;

	@Autowired
	ModoPcvStorageService modoPcvStorageService;
	
    public DataStorageEntity save(DataStorageEntity entity){

        modoVcvStorageService.save(entity.getModoVcvStorage());
        modoPcvStorageService.save(entity.getModoPcvStorage());
        vcvStorageService.save(entity.getVcvStorage());
        pcvStorageService.save(entity.getPcvStorage());

        return dataStorageRepository.save(entity);

    }

}
