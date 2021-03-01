package br.com.inspirar.ccm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.inspirar.ccm.api.UsuarioApi;
import br.com.inspirar.ccm.model.RecuperarSenha;
import br.com.inspirar.ccm.service.UsuarioService;
import br.com.inspirar.ccm.service.email.EmailService;


@RestController
public class ApiUsuarioController implements UsuarioApi {

	@Autowired
	EmailService emailService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Override
	public ResponseEntity<Void> desabilitarUsuario(Integer idUsuario) {
		
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> recuperarSenha(@Valid RecuperarSenha email ) {
		try {

			emailService.sendEmailTeste("Inspirar", email.getEmail());
			
		}catch (Exception ex){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> salvarUsuario(@Valid br.com.inspirar.ccm.model.Usuario usuario) {
		
		usuarioService.salvarUsuario(usuario);
		
		return ResponseEntity.noContent().build();
	}
	
}
