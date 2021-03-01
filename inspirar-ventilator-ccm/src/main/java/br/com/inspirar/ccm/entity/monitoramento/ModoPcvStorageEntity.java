package br.com.inspirar.ccm.entity.monitoramento;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.inspirar.ccm.model.ModoPcvStorage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MODO_PCV_STORAGE", schema = "ccm")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModoPcvStorageEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String picoMinimo;
    private String picoMaximo;
    private String veMinimo;
    private String veMaximo;
    private String vtMinimo;
    private String vtMaximo;
    private String peepMinimo;
    private String peepMaximo;
	
	@Column(name = "dataInclusao",  columnDefinition = "timestamp default CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private LocalDateTime dataInclusao;
	
    public ModoPcvStorageEntity(ModoPcvStorage mps, Integer id) {
        this.id = id;
       this.picoMinimo = mps.getPicoMinimo();
       this.picoMaximo = mps.getPicoMaximo();
       this.veMinimo = mps.getVeMinimo();
       this.veMaximo = mps.getVeMaximo();
       this.vtMaximo = mps.getVtMaximo();
       this.peepMinimo = mps.getPeepMinimo();
       this.peepMaximo = mps.getPeepMaximo();
    }
}
