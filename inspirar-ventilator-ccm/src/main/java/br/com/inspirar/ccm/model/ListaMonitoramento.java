package br.com.inspirar.ccm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ListaMonitoramento  {
	
  private List<Monitoramento> monitoramentos = new ArrayList<>();
  private CabecalhoTotal cabecalhoTotal = null;
  private Map<String, Graficos>  graficos;
  
}