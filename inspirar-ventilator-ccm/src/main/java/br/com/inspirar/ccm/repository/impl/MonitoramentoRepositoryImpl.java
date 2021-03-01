package br.com.inspirar.ccm.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.inspirar.ccm.model.Grafico;
import br.com.inspirar.ccm.repository.IMonitoramentoRepository;

@Repository
class MonitoramentoRepositoryImpl implements IMonitoramentoRepository {

	 
    @PersistenceContext
    private EntityManager entityManager;
 
    
    @SuppressWarnings("unchecked")
	public List<Grafico> getGraficoPorImeis(@Param("imeiList") List<String> imeiList){
        return entityManager.createQuery("select new br.com.inspirar.ccm.model.Grafico( "
        		+ " monitoramentoEntity.dataStorage.sexo, monitoramentoEntity.dataStorage.altura, monitoramentoEntity.dataStorage.peso,"
        		+ " imei, volumeValor, fluxoValor, pressaoValor )   "
        		+ " from PainelMonitoramentoGraficoEntity WHERE imei in (:imeiList) order by dataInclusao desc").setMaxResults(30).
        		setParameter("imeiList", imeiList).getResultList();
    }
    
    @Transactional
    @Modifying
	public void removerPorImeisGraficosMaior30Ciclos(@Param("imeiList") List<String> imeiList){
		
    	for (String imei : imeiList) {
    		
    		@SuppressWarnings("unchecked")
    		List<Integer> ids = entityManager.createQuery(" select id from PainelMonitoramentoGraficoEntity WHERE imei = :imei order by dataInclusao desc")
    		.setMaxResults(30).setParameter("imei", imei).getResultList();
    		
    		entityManager.createQuery(" delete PainelMonitoramentoGraficoEntity where id not in (:ids) ")
    		.setParameter("ids", ids).executeUpdate();
    	}
			
	}
}	