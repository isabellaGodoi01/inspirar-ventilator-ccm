package br.com.inspirar.ccm.entity.monitoramento;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.inspirar.ccm.model.VcvStorage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "VCV_STORAGE", schema = "ccm")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VcvStorageEntity {

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

    public VcvStorageEntity(VcvStorage vcvStorage, Integer id) {
        this.id = id;
        this.vt = vcvStorage.getVt();
        this.fluxo = vcvStorage.getFluxo();
        this.fr = vcvStorage.getFr();
        this.peep = vcvStorage.getPeep();
        this.pausaInsp = vcvStorage.getPausaInsp();
        this.sensib = vcvStorage.getSensib();
        this.fio2 = vcvStorage.getFio2();
    }
}
