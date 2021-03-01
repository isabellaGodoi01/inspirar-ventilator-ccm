package br.com.inspirar.ccm.builder;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.inspirar.ccm.entity.monitoramento.DataStorageEntity;
import br.com.inspirar.ccm.entity.monitoramento.ModoPcvStorageEntity;
import br.com.inspirar.ccm.entity.monitoramento.ModoVcvStorageEntity;
import br.com.inspirar.ccm.entity.monitoramento.MonitorDinamicoEntity;
import br.com.inspirar.ccm.entity.monitoramento.PainelMonitoramentoEntity;
import br.com.inspirar.ccm.entity.monitoramento.PainelMonitoramentoGraficoEntity;
import br.com.inspirar.ccm.entity.monitoramento.PcvStorageEntity;
import br.com.inspirar.ccm.entity.monitoramento.VcvStorageEntity;
import br.com.inspirar.ccm.model.DataStorage;
import br.com.inspirar.ccm.model.MonitorDinamico;
import br.com.inspirar.ccm.model.PainelMonitoramento;
import br.com.inspirar.ccm.repository.PainelMonitoramentoRepository;

@Component
public class PainelMonitoramentoBuilder {
	
    @Autowired
    PainelMonitoramentoRepository painelMonitoramentoRepository;

	public  PainelMonitoramentoEntity createPainelMonitoramento(PainelMonitoramento monitoramento) {
		
    	PainelMonitoramentoEntity entity  = painelMonitoramentoRepository.findByImei(monitoramento.getChave()).orElse(new PainelMonitoramentoEntity());

    	Integer id = entity == null ? null : entity.getId();
    	
    	entity = new PainelMonitoramentoEntity(monitoramento.getChave(), id, entity.getDataStorage(), entity.getMonitoramentoDinamico());
    	
    	DataStorageEntity dataStorageEntity = createDataStorageEntity(entity.getDataStorage(), monitoramento.getDataStorage());
    	entity.setDataStorage(dataStorageEntity);
    	
    	MonitorDinamicoEntity monitorDinamicoEntity = createMonitorDinamicoEntity(entity.getMonitoramentoDinamico(), monitoramento.getMonitoramento());
    	entity.setMonitoramentoDinamico(monitorDinamicoEntity);
    	
    	createPainelMonitoramentoGraficoEntity(entity, monitoramento);

		return entity;
	}

	private void createPainelMonitoramentoGraficoEntity(PainelMonitoramentoEntity entity, PainelMonitoramento monitoramento) {
		
		PainelMonitoramentoGraficoEntity grafico = new PainelMonitoramentoGraficoEntity(monitoramento);
		
		if(entity.getGraficos() == null) {
			entity.setGraficos(new ArrayList<>());
		}
		
		grafico.setMonitoramentoEntity(entity);
		
		entity.getGraficos().add(grafico);
	}

	private MonitorDinamicoEntity createMonitorDinamicoEntity(MonitorDinamicoEntity monitorDinamicoEntity, MonitorDinamico monitorDinamico) {
		Integer id = monitorDinamicoEntity == null ? null : monitorDinamicoEntity.getId();
        return monitorDinamicoEntity = new MonitorDinamicoEntity(monitorDinamico, id);
	}
	
	private DataStorageEntity createDataStorageEntity(DataStorageEntity dataStorageEntity, DataStorage dataStorage) {
		
		Integer vcvStorageId = dataStorageEntity == null ? null : dataStorageEntity.getVcvStorage().getId();
		Integer modoVcvStorageId = dataStorageEntity == null ? null : dataStorageEntity.getModoVcvStorage().getId();
		Integer modoPcvStorageId = dataStorageEntity == null ? null : dataStorageEntity.getModoPcvStorage().getId();
		Integer pcvStorageId = dataStorageEntity  == null ? null : dataStorageEntity.getPcvStorage().getId();
		Integer id = dataStorageEntity == null ? null : dataStorageEntity.getId();
		
		VcvStorageEntity vcvStorage = new VcvStorageEntity(dataStorage.getVcvStorage(), vcvStorageId);
		PcvStorageEntity pcvStorage = new PcvStorageEntity(dataStorage.getPcvStorage(), pcvStorageId);
		ModoVcvStorageEntity modoVcvStorage = new ModoVcvStorageEntity(dataStorage.getModoVcvStorage(), modoVcvStorageId);
		ModoPcvStorageEntity modoPcvStorage = new ModoPcvStorageEntity(dataStorage.getModoPcvStorage(), modoPcvStorageId);
			
        return dataStorageEntity = new DataStorageEntity(dataStorage, id, vcvStorage, pcvStorage, modoVcvStorage, modoPcvStorage);
	}
}
