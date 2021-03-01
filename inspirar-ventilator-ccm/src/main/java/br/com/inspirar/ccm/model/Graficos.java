package br.com.inspirar.ccm.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Graficos  {
	
	private String imei;
	private String sexo;
	private String altura;
	private String peso;
	private List<Float> volumeValor;
	private List<Float> fluxoValor;
	private List<Float> pressaoValor;
	
	public Graficos(){
		this.volumeValor = new ArrayList<>();
		this.fluxoValor = new ArrayList<>();
		this.pressaoValor = new ArrayList<>();
	}
	
}
