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

import com.edu.infnet.CyberParts.model.domain.Carrinho;
import com.edu.infnet.CyberParts.model.service.CarrinhoService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

	@Autowired
	private CarrinhoService carrinhoService;
	
	@GetMapping("/lista")
	@Transactional
	public Iterable<Carrinho> obterCarrinhos(){
		return carrinhoService.obterCarrinhos();
	}
	
	@GetMapping("/{id}")
	@Transactional
    public Carrinho obterCarrinhoPorId(@PathVariable Integer id) {
    	return carrinhoService.obterCarrinhoPorId(id);
    }
    
	@PostMapping("/incluir")
    public void incluirCarrinho(@RequestBody Carrinho c){
		carrinhoService.incluirCarrinho(c);
    }
    
	@PutMapping("/{id}")
    public Carrinho alterarCarrinho(@PathVariable Integer id, @RequestBody Carrinho carrinho) {
    	return carrinhoService.alterarCarrinho(id, carrinho);
    }
    
	@DeleteMapping("/{id}")
    public void removerCarrinho(@PathVariable Integer id) {
		carrinhoService.removerCarrinho(id);
    }

}