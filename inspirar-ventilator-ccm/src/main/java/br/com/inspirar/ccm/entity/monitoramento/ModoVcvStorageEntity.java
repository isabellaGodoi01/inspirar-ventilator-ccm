package br.com.inspirar.ccm.entity.monitoramento;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.inspirar.ccm.model.ModoVcvStorage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MODO_VCV_STORAGE", schema = "ccm")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModoVcvStorageEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String veMinimo;
    private String veMaximo;
    private String picoMinimo;
    private String picoMaximo;
    private String platoMinimo;
    private String platoMaximo;
	
	@Column(name = "dataInclusao",  columnDefinition = "timestamp default CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private LocalDateTime dataInclusao;

    public ModoVcvStorageEntity(ModoVcvStorage modoVcvStorage, Integer id) {
        this.id = id;
        this.veMinimo = modoVcvStorage.getVeMinimo();
        this.veMaximo = modoVcvStorage.getVeMaximo();
        this.picoMinimo = modoVcvStorage.getPicoMinimo();
        this.picoMaximo = modoVcvStorage.getPicoMaximo();
        this.platoMinimo = modoVcvStorage.getPlatoMinimo();
        this.platoMaximo = modoVcvStorage.getPlatoMaximo();
    }
}
