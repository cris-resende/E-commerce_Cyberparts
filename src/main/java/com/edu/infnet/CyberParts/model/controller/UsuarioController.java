package com.edu.infnet.CyberParts.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.infnet.CyberParts.model.domain.Usuario;
import com.edu.infnet.CyberParts.model.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/lista")
    public Iterable<Usuario> obterUsers(){
        return usuarioService.obterUsers();
    }

	@GetMapping("/{id}")
    public Usuario obterUsuarioPorId(@PathVariable Integer id) {
    	return usuarioService.obterUsuarioPorId(id);
    }
    
	@PostMapping("/incluir")
    public void incluirUsuario(@RequestBody Usuario u){
		usuarioService.incluirUsuario(u);
    }
    
	@PutMapping("/{id}")
    public Usuario alterarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
    	return usuarioService.alterarUsuario(id, usuario);
    }
    
	@DeleteMapping("/{id}")
    public void removerUsuario(@PathVariable Integer id) {
		usuarioService.removerUsuario(id);
    }

}
