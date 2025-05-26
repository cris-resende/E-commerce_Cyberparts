package com.edu.infnet.CyberParts.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.infnet.CyberParts.model.domain.Usuario;
import com.edu.infnet.CyberParts.model.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
    
    public void incluirUser(Usuario u){
        usuarioRepository.save(u);
    }
    
    public Iterable<Usuario> obterUsers(){
        return usuarioRepository.findAll();
    }

}
