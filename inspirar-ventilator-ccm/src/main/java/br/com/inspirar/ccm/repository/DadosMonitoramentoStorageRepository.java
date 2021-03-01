package br.com.inspirar.ccm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.inspirar.ccm.entity.monitoramento.DadosMonitoramentoStorageEntity;
 
public interface DadosMonitoramentoStorageRepository extends JpaRepository<DadosMonitoramentoStorageEntity, Integer> {

	DadosMonitoramentoStorageEntity findByImei(String imei);
 
}