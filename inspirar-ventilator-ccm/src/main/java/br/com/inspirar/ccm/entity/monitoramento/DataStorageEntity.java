package br.com.inspirar.ccm.entity.monitoramento;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.inspirar.ccm.model.DataStorage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DATA_STORAGE", schema = "ccm")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataStorageEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;
	private String modo;
	private String sexo;
	private String altura;
	private String peso;
	private String nomePaciente;
	private String leito;
	private String isVcv;
	private String isLigado;
	
	@Column(name = "dataInclusao",  columnDefinition = "timestamp default CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private LocalDateTime dataInclusao;

	@ManyToOne
	private VcvStorageEntity vcvStorage;

	@ManyToOne
	private PcvStorageEntity pcvStorage;

	@ManyToOne
	private ModoVcvStorageEntity modoVcvStorage;

	@ManyToOne
	private ModoPcvStorageEntity modoPcvStorage;

	public DataStorageEntity(DataStorage dataStorage, Integer id, VcvStorageEntity vcvStorage, PcvStorageEntity pcvStorage, ModoVcvStorageEntity modoVcvStorage, ModoPcvStorageEntity modoPcvStorage) {
		this.id = id;
		this.modo = dataStorage.getModo();
		this.sexo = dataStorage.getSexo();
		this.altura = dataStorage.getAltura();
		this.peso = dataStorage.getPeso();
		this.nomePaciente = dataStorage.getNomePaciente();
		this.leito = dataStorage.getLeito();
		this.isVcv = dataStorage.getIsVcv();
		this.isLigado = dataStorage.getIsLigado();
		this.vcvStorage = vcvStorage;
		this.pcvStorage = pcvStorage;
		this.modoVcvStorage = modoVcvStorage;
		this.modoPcvStorage = modoPcvStorage;

	}
}
