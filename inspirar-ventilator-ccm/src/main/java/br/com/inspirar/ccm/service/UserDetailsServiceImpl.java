package br.com.inspirar.ccm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.inspirar.ccm.entity.UsuarioEntity;
import br.com.inspirar.ccm.model.UserDetails;
import br.com.inspirar.ccm.repository.UsuarioRepository;
 
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private UsuarioRepository usuarioRepository;
     
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UsuarioEntity usuario = usuarioRepository.findByUsernameAndEnabledTrue(username);
         
        if (usuario == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new UserDetails(usuario);
    }
 
}