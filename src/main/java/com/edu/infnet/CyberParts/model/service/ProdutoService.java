package com.edu.infnet.CyberParts.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.infnet.CyberParts.model.domain.Produto;
import com.edu.infnet.CyberParts.model.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Transactional
    public Produto incluirProduto(Produto p){
        return produtoRepository.save(p);
    }

	@Transactional
    public Iterable<Produto> obterProdutos(){
        return produtoRepository.findAll();
    }
    
	@Transactional
    public Produto obterProdutoPorId(Integer id) {
    	
    	return produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado com o id: " + id));
    }
    
	@Transactional
    public void removerProduto(Integer id) {
    	
    	if (!produtoRepository.existsById(id)) {
    		throw new RuntimeException("produto não encontrado com o id: " + id);
    	}
    	
    	produtoRepository.deleteById(id);
    }
	
	@Transactional
    public Produto alterarProduto(Integer id, Produto produto) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado com o id: " + id);
        }
        Produto produtoExistente = produtoRepository.findById(id)
                                                     .orElseThrow(() -> new RuntimeException("Produto para alteração não encontrado com o id: " + id));

        produtoExistente.setNomeProduto(produto.getNomeProduto());
        produtoExistente.setCategoria(produto.getCategoria());
        produtoExistente.setPreco(produto.getPreco());
        produtoExistente.setEstoque(produto.getEstoque());

        return produtoRepository.save(produtoExistente);
    }
}