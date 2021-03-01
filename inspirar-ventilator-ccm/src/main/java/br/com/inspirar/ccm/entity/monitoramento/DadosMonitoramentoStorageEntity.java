package br.com.inspirar.ccm.entity.monitoramento;



import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "DADO_MONITORAMENTO_STORAGE", schema = "ccm")
@Data
public class DadosMonitoramentoStorageEntity   {
	
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String url;
	private String senha;
	private String imei;
	
	@Column(name = "dataInclusao",  columnDefinition = "timestamp default CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private LocalDateTime dataInclusao;
	
	public DadosMonitoramentoStorageEntity(br.com.inspirar.ccm.model.DadosMonitoramentoStorage dadosMonitoramentoStorage) {
		super();
		this.url = dadosMonitoramentoStorage.getUrl();
		this.senha = dadosMonitoramentoStorage.getSenha();
		this.imei = dadosMonitoramentoStorage.getImei();
	}



	public DadosMonitoramentoStorageEntity() {
		super();
	}
}