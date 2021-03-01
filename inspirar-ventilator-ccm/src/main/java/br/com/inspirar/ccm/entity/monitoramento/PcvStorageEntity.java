package br.com.inspirar.ccm.entity.monitoramento;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.inspirar.ccm.model.PcvStorage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PCV_STORAGE", schema = "ccm")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PcvStorageEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String deltaP;
    private String peep;
    private String tinsp;
    private String fr;
    private String sensib;
    private String fio2;
	
	@Column(name = "dataInclusao",  columnDefinition = "timestamp default CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private LocalDateTime dataInclusao;

    public PcvStorageEntity(PcvStorage pcvStorage, Integer id) {
    	this.id = id;
        this.deltaP = pcvStorage.getDeltaP();
        this.peep = pcvStorage.getPeep();
        this.tinsp = pcvStorage.getTinsp();
        this.fr = pcvStorage.getFr();
        this.sensib = pcvStorage.getSensib();
        this.fio2 = pcvStorage.getFio2();
    }
}
