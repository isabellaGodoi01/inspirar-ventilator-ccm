package br.com.inspirar.ccm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.inspirar.ccm.entity.monitoramento.PainelMonitoramentoGraficoEntity;

@Repository
public interface PainelMonitoramentoGraficoRepository extends JpaRepository<PainelMonitoramentoGraficoEntity, Integer> {

}
	