package com.edu.infnet.CyberParts.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.infnet.CyberParts.model.domain.Carrinho;
import com.edu.infnet.CyberParts.model.service.CarrinhoService;

@RestController
public class CarrinhoController {

	@Autowired
	private CarrinhoService carrinhoService;
	
	@GetMapping("/carrinho")
	public Iterable<Carrinho> obterCarrinhos(){
		return carrinhoService.obterCarrinhos();
	}
}