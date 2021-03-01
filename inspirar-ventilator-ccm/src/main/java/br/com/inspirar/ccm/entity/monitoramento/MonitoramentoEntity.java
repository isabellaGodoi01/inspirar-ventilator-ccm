package br.com.inspirar.ccm.entity.monitoramento;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MONITORAMENTO", schema = "ccm")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonitoramentoEntity implements Serializable{

    private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String leito;
	private String paciente;
	private String pressaoPico;
	private String fio2;
	private String peep;
	private String fr;
	private String vt;
	private String ve;
	private String imei;
    private String mensagemAlarme;
    private String modo;
	private Boolean vcv = false;
	private Boolean alarmePPico = false;
	private Boolean alarmeFio2 = false;
	private Boolean alarmePeep = false;
	private Boolean alarmeFr = false;
	private Boolean alarmeVt = false;
	private Boolean alarmeVe = false;
	private Boolean bloqueado = false;
    private Boolean countAlarme = false;
    private Boolean onOff = false;

	@Column(name = "dataInclusao",  columnDefinition = "timestamp default CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private LocalDateTime dataInclusao;
	
	private Boolean excluido = false;

	public MonitoramentoEntity(br.com.inspirar.ccm.model.Monitoramento monitoramento, Integer id) {
		this.id = id;
		this.leito = monitoramento.getLeito();
		this.paciente = monitoramento.getPaciente();
		this.pressaoPico = monitoramento.getPressaoPico();
		this.fio2 = monitoramento.getFio2();
		this.peep = monitoramento.getPeep();
		this.fr = monitoramento.getFr();
		this.vt = monitoramento.getVt();
        this.ve = monitoramento.getVe();
		this.imei = monitoramento.getNumeroImei();
		this.mensagemAlarme = monitoramento.getMensagemAlarme();
        this.modo = monitoramento.getModo();
		this.vcv = monitoramento.isVcv() == null ? false : monitoramento.isVcv();
		this.alarmePPico = monitoramento.isAlarmePPico() == null ? false : monitoramento.isAlarmePPico();
		this.alarmeFio2 = monitoramento.isAlarmeFio2() == null ? false : monitoramento.isAlarmeFio2();
		this.alarmePeep = monitoramento.isAlarmePeep() == null ? false : monitoramento.isAlarmePeep();
		this.alarmeFr = monitoramento.isAlarmeFr() == null ? false : monitoramento.isAlarmeFr();
		this.alarmeVt = monitoramento.isAlarmeVt() == null ? false : monitoramento.isAlarmeVt();
		this.alarmeVe = monitoramento.isAlarmeVe() == null ? false : monitoramento.isAlarmeVe();
		this.bloqueado = monitoramento.isBloqueado() == null ? false : monitoramento.isBloqueado();
        this.countAlarme = monitoramento.isCountAlarme() == null ? false : monitoramento.isCountAlarme();
        this.onOff = monitoramento.isOnOff() == null ? false : monitoramento.isOnOff();
		this.dataInclusao = null;
	}

}
