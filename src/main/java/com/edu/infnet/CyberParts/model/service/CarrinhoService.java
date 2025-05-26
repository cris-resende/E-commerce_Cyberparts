package com.edu.infnet.CyberParts.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.infnet.CyberParts.model.domain.Carrinho;
import com.edu.infnet.CyberParts.model.repository.CarrinhoRepository;

@Service
public class CarrinhoService {
	
	@Autowired
	private CarrinhoRepository carrinhoRepository;

    public Carrinho incluirCarrinho(Carrinho c){
        return carrinhoRepository.save(c);
    }

    public Iterable<Carrinho> obterCarrinhos(){
        return carrinhoRepository.findAll();
    }

    public Optional<Carrinho> obterCarrinhoPorId(Integer id) {
        return carrinhoRepository.findById(id);
    }
}