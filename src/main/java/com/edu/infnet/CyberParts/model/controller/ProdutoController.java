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

import com.edu.infnet.CyberParts.model.domain.Produto;
import com.edu.infnet.CyberParts.model.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping("/lista")
    public Iterable<Produto> obterProdutos(){
        return produtoService.obterProdutos();
    }
	
	@GetMapping("/{id}")
    public Produto obterProdutoPorId(@PathVariable Integer id) {
    	return produtoService.obterProdutoPorId(id);
    }
    
	@PostMapping("/incluir")
    public void incluirProduto(@RequestBody Produto p){
    	produtoService.incluirProduto(p);
    }
    
	@PutMapping("/{id}")
    public Produto alterarProduto(@PathVariable Integer id, @RequestBody Produto produto) {
    	return produtoService.alterarProduto(id, produto);
    }
    
	@DeleteMapping("/{id}")
    public void removerProduto(@PathVariable Integer id) {
    	produtoService.removerProduto(id);
    }

}
