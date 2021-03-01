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
@Table(name = "EVENTO", schema = "public")
@Data
public class EventoEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public String descricao;
    public String cor;
	
	@Column(name = "dataInclusao",  columnDefinition = "timestamp default CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private LocalDateTime dataInclusao;

}
