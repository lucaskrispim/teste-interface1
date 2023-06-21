/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webtask.aula.controller.service;

import br.com.webtask.aula.domain.model.UserClient;
import br.com.webtask.aula.domain.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author daves
 */
@Service
public class UserService {

    @Autowired
    private UserRepo users;
    
    public UserClient buscarUsuario(long id) {
        return users.getOne(id);    
    
    }

    public UserClient salvar(UserClient usuario) {
        
           return  users.save(usuario);
    }
    
}
