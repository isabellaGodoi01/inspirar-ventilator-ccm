package br.com.inspirar.ccm.entity.monitoramento;


import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PAINEL_MONITORAMENTO", schema = "ccm")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PainelMonitoramentoEntity {
	
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String imei;
	
	@Column(name = "dataInclusao",  columnDefinition = "timestamp default CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private LocalDateTime dataInclusao;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "monitoramentoEntity", cascade = CascadeType.ALL)
    private List<PainelMonitoramentoGraficoEntity> graficos;
    
    @ManyToOne
    private DataStorageEntity dataStorage;

    @ManyToOne
    private MonitorDinamicoEntity monitoramentoDinamico;

    public PainelMonitoramentoEntity(String imei, Integer id, DataStorageEntity dataStorage, MonitorDinamicoEntity monitoramentoDinamico) {
    	this.id = id;
        this.imei = imei;
        this.dataStorage = dataStorage;
        this.monitoramentoDinamico = monitoramentoDinamico;
    }
}
