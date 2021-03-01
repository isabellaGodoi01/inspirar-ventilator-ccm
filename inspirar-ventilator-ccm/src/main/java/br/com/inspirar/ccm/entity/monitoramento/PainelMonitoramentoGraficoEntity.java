package br.com.inspirar.ccm.entity.monitoramento;


import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.inspirar.ccm.model.PainelMonitoramento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PAINEL_MONITORAMENTO_GRAFICO", schema = "ccm")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PainelMonitoramentoGraficoEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String imei;
    private Float volumeValor;
    private Float fluxoValor;
    private Float pressaoValor;
	
	@Column(name = "dataInclusao",  columnDefinition = "timestamp default CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private LocalDateTime dataInclusao;
    
	@ManyToOne
	PainelMonitoramentoEntity monitoramentoEntity;

	public PainelMonitoramentoGraficoEntity(PainelMonitoramento monitoramento) {
    	this.id = null;
    	this.imei = monitoramento.getChave();
        this.volumeValor = monitoramento.getVolumeValor();
        this.fluxoValor = monitoramento.getFluxoValor();
        this.pressaoValor = monitoramento.getPressaoValor();
        this.dataInclusao = null;
    }
}
