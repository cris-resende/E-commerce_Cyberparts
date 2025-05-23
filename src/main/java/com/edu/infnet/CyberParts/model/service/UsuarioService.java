package com.edu.infnet.CyberParts.model.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.edu.infnet.CyberParts.model.domain.Usuario;

@Service
public class UsuarioService {
    private Map<String, Usuario> mapaUsers = new HashMap<String, Usuario>();
    
    public void incluirUser(Usuario u){
        mapaUsers.put(u.email, u);
    }
    
    public Collection<Usuario> obterUsers(){
        return mapaUsers.values();
    }

}
