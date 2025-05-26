package com.edu.infnet.CyberParts.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.infnet.CyberParts.model.domain.Produto;
import com.edu.infnet.CyberParts.model.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
    public void incluirProduto(Produto p){
        produtoRepository.save(p);
    }

    public Iterable<Produto> obterProdutos(){
        return produtoRepository.findAll();
    }
}