package br.com.inspirar.ccm.model;

import lombok.Data;

@Data
public class CabecalhoTotal {
	
	public CabecalhoTotal(CabecalhoCount pacienteTotal, CabecalhoCount vcvTotal, CabecalhoCount pcvTotal,
			CabecalhoCount alarmesTotal, CabecalhoCount desconectadosTotal) {
		super();
		this.pacienteTotal = pacienteTotal;
		this.vcvTotal = vcvTotal;
		this.pcvTotal = pcvTotal;
        this.alarmesTotal = alarmesTotal;
        this.desconectadosTotal = desconectadosTotal;
	}
	
	CabecalhoCount pacienteTotal;
	CabecalhoCount vcvTotal;
	CabecalhoCount pcvTotal;
    CabecalhoCount alarmesTotal;
    CabecalhoCount desconectadosTotal;
	
}
