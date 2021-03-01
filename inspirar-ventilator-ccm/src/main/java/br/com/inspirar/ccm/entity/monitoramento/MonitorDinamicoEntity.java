package br.com.inspirar.ccm.entity.monitoramento;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.inspirar.ccm.model.MonitorDinamico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MONITOR_DINAMICO", schema = "ccm")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonitorDinamicoEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String vt;
    private String fluxo;
	private String fr;
	private String peep;
    private String pausaInsp;
    private String sensib;
    private String fio2;
	
	@Column(name = "dataInclusao",  columnDefinition = "timestamp default CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private LocalDateTime dataInclusao;

    public MonitorDinamicoEntity(MonitorDinamico monitorDinamico, Integer id) {
        this.id = id;
        this.vt = monitorDinamico.getVt();
        this.fluxo = monitorDinamico.getFluxo();
        this.fr = monitorDinamico.getFr();
        this.peep = monitorDinamico.getPeep();
        this.pausaInsp = monitorDinamico.getPausaInsp();
        this.sensib = monitorDinamico.getSensib();
        this.fio2 = monitorDinamico.getFio2();
    }
}
