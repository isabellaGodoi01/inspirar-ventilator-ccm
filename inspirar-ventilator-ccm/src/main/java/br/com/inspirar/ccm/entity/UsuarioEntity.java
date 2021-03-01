package br.com.inspirar.ccm.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.inspirar.ccm.enuns.PerfilEnum;
import lombok.Data;

@Entity
@Table(name = "USUARIO", schema = "public")
@Data
public class UsuarioEntity {

    public UsuarioEntity(){ }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100 )
    private String username;

    @Column(length = 100)
    private String password;

    @Column(length = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private PerfilEnum role;

    @Column
    private boolean enabled;

	
	@Column(name = "dataInclusao",  columnDefinition = "timestamp default CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private LocalDateTime dataInclusao;

    public UsuarioEntity(br.com.inspirar.ccm.model.Usuario usuario) {
        this.id = null;
        this.username = usuario.getUsername();
        this.password = usuario.getPassword();
        this.email = usuario.getEmail();
        this.role = PerfilEnum.valueOf(usuario.getRole().name());
        this.enabled = true;
    }
}
