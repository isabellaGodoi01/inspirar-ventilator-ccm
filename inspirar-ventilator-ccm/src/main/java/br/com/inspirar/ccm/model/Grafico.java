package br.com.inspirar.ccm.model;

import lombok.Data;

@Data
public class Grafico  {
	
	private String imei;
	private Float volumeValor;
	private Float fluxoValor;
	private Float pressaoValor;
	private String sexo;
	private String altura;
	private String peso;
	
	public Grafico(String sexo, String altura, String peso, String imei, Float volumeValor, Float fluxoValor, Float pressaoValor) {
		super();
		this.imei = imei;
		this.volumeValor = volumeValor;
		this.fluxoValor = fluxoValor;
		this.pressaoValor = pressaoValor;
		this.altura = altura;
		this.peso = peso;
		this.sexo = sexo;
	}
	
}

