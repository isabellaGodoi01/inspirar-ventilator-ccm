package br.com.inspirar.ccm.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "CONFIGURACAO_SMTP", schema = "ccm")
@Data
public class ConfiguracaoSmtpEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String hostSmtp;

    @Column
    private Integer potaSmtp;

    @Column
    private Boolean autenticacaoSmtp;

    @Column
    private String usuario;

    @Column
    private String senha;

    @Column
    private String remetente;

    @Column
    private Boolean sslTsl;

	
	@Column(name = "dataInclusao",  columnDefinition = "timestamp default CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private LocalDateTime dataInclusao;
	
	public ConfiguracaoSmtpEntity(br.com.inspirar.ccm.model.ConfiguracaoSmtp configuracaoSmtp ) {
		super();
		this.hostSmtp = configuracaoSmtp.getHost();
		this.potaSmtp = configuracaoSmtp.getPorta();
		this.autenticacaoSmtp = configuracaoSmtp.isAutenticacao();
		this.usuario = configuracaoSmtp.getUsuario();
		this.senha = configuracaoSmtp.getSenha();
		this.remetente = configuracaoSmtp.getRemetente();
		this.sslTsl = configuracaoSmtp.isSslTls();
	}
    
    
}