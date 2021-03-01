package br.com.inspirar.ccm.model;

import lombok.Data;

@Data
public class CabecalhoCount {
	
	public CabecalhoCount(String label, Long quantidade) {
		super();
		this.label = label;
		this.quantidade = quantidade == null ? 0 : quantidade;
	}

	private String label;
	private Long quantidade;


}