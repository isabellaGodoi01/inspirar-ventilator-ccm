package br.com.inspirar.ccm.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.inspirar.ccm.entity.monitoramento.MonitoramentoEntity;
import br.com.inspirar.ccm.model.CabecalhoCount;

@Repository
public interface MonitoramentoRepository extends JpaRepository<MonitoramentoEntity, Integer> {

	MonitoramentoEntity findByImei(String numeroImei);

	@Modifying
	@Transactional
	@Query("update MonitoramentoEntity m set m.excluido = true where m.id = :id ")
	void bloquearMonitoramento(@Param("id") Integer id);

    @Query("select new br.com.inspirar.ccm.model.CabecalhoCount('Paciente', count(id)) from MonitoramentoEntity where excluido = false ")
    CabecalhoCount totalPaciente();
   
    @Query("select new br.com.inspirar.ccm.model.CabecalhoCount('Alarmes', SUM(CASE WHEN countAlarme = 'true' THEN 1 END) ) from MonitoramentoEntity where excluido = false ")
    CabecalhoCount totalAlarmes();

    // @Query("select new br.com.inspirar.ccm.model.CabecalhoCount('Desconectados', SUM(CASE WHEN onOff = 'true' THEN 1 END) ) from MonitoramentoEntity where excluido = false ")
    // CabecalhoCount totalDesconectados();

    @Query("select new br.com.inspirar.ccm.model.CabecalhoCount('Desconectados', SUM(CASE WHEN onOff = 'true' THEN 1 END) ) from MonitoramentoEntity where excluido = false ")
    CabecalhoCount totalDesconectados();
    
    @Query("select new br.com.inspirar.ccm.model.CabecalhoCount('VCV', SUM(CASE WHEN vcv = 'true' THEN 1 END) ) from MonitoramentoEntity where excluido = false ")
    CabecalhoCount totalVcv();
    
    @Query("select new br.com.inspirar.ccm.model.CabecalhoCount('PCV', SUM(CASE WHEN vcv = 'false' THEN 1 END) ) from MonitoramentoEntity where excluido = false")
    CabecalhoCount totalPcv();

	List<MonitoramentoEntity> findAllByExcluidoFalseOrExcluidoIsNullAndImeiIsNotNull();
}
