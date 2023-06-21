/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webtask.aula.util;

import br.com.webtask.aula.config.security.user.UserLogado;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

/**
 *
 * @author daves
 */
@Service
public class LogadoUtil {
     
    public long getIdUserLogado(Authentication authentication){
     
        return ((UserLogado)authentication.getPrincipal()).getId();
    }
     
    public String getNomeUserLogado(Authentication authentication){
     
        return ((UserLogado)authentication.getPrincipal()).getNome();
    }
     
    public String getEmailUserLogado(Authentication authentication){
     
        return ((UserLogado)authentication.getPrincipal()).getEmail();
    }
     
    public boolean eHAdministrador(Authentication authentication){     
        return authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
    
}
