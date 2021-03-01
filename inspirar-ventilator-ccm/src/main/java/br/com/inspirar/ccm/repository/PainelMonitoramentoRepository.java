package br.com.inspirar.ccm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.inspirar.ccm.entity.monitoramento.PainelMonitoramentoEntity;

@Repository
public interface PainelMonitoramentoRepository extends JpaRepository<PainelMonitoramentoEntity, Integer> {

	Optional<PainelMonitoramentoEntity> findByImei(String chave);
 

}
