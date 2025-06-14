package com.edu.infnet.CyberParts.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.infnet.CyberParts.model.domain.Usuario;
import com.edu.infnet.CyberParts.model.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
    
	@Transactional
    public void incluirUsuario(Usuario u){
        usuarioRepository.save(u);
    }
    
	@Transactional
    public Iterable<Usuario> obterUsers(){
        return usuarioRepository.findAll();
    }

	@Transactional
    public Usuario obterUsuarioPorId(Integer id) {
    	
    	return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado com o id: " + id));
    }
    
	@Transactional
    public void removerUsuario(Integer id) {
    	
    	if (!usuarioRepository.existsById(id)) {
    		throw new RuntimeException("Usuário não encontrado com o id: " + id);
    	}
    	
    	usuarioRepository.deleteById(id);
    }
    
	@Transactional
    public Usuario alterarUsuario(Integer id, Usuario usuario) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado com o id: " + id);
        }
        Usuario usuarioExistente = usuarioRepository.findById(id)
                                                      .orElseThrow(() -> new RuntimeException("Usuário para alteração não encontrado com o id: " + id));

        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setTipo(usuario.getTipo());

        return usuarioRepository.save(usuarioExistente);
    }
}

