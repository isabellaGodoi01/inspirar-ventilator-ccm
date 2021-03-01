package br.com.inspirar.ccm.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;

import br.com.inspirar.ccm.model.Grafico;

public interface IMonitoramentoRepository {

	List<Grafico> getGraficoPorImeis(@Param("imeiList") List<String> imeiList);
	
	void removerPorImeisGraficosMaior30Ciclos(@Param("imeiList") List<String> imeiList);
	
}
