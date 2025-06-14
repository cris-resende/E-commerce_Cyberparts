package com.edu.infnet.CyberParts.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.infnet.CyberParts.model.domain.Carrinho;
import com.edu.infnet.CyberParts.model.domain.Produto;
import com.edu.infnet.CyberParts.model.domain.Usuario;
import com.edu.infnet.CyberParts.model.repository.CarrinhoRepository;
import com.edu.infnet.CyberParts.model.repository.ProdutoRepository;
import com.edu.infnet.CyberParts.model.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class CarrinhoService {
	
	@Autowired
	private CarrinhoRepository carrinhoRepository;
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private ProdutoRepository produtoRepository;

    @Transactional
    public Carrinho incluirCarrinho(Carrinho c){
        if (c.getUsuario() != null && c.getUsuario().getId() != null) {
            Usuario usuarioExistente = usuarioRepository.findById(c.getUsuario().getId())
                                                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o id: " + c.getUsuario().getId()));
            c.setUsuario(usuarioExistente);
        }

        if (c.getProdutos() != null && !c.getProdutos().isEmpty()) {
            List<Produto> produtosExistentes = new ArrayList<>();
            for (Produto produtoEnviado : c.getProdutos()) {
                if (produtoEnviado.getId() != null) {
                    Produto prod = produtoRepository.findById(produtoEnviado.getId())
                                                    .orElseThrow(() -> new RuntimeException("Produto não encontrado com o id: " + produtoEnviado.getId()));
                    produtosExistentes.add(prod);
                }
            }
            c.setProdutos(produtosExistentes);
        }
        return carrinhoRepository.save(c);
    }

    @Transactional
    public Iterable<Carrinho> obterCarrinhos(){
        return carrinhoRepository.findAll();
    }

    @Transactional
    public Carrinho obterCarrinhoPorId(Integer id) {
        return carrinhoRepository.findById(id).orElseThrow(() -> new RuntimeException("Carrinho não encontrado com o id: " + id));
    }
    
    @Transactional
    public void removerCarrinho(Integer id) {
    	
    	if (!carrinhoRepository.existsById(id)) {
    		throw new RuntimeException("Carrinho não encontrado com o id: " + id);
    	}
    	
    	carrinhoRepository.deleteById(id);
    }
    
    @Transactional
    public Carrinho alterarCarrinho(Integer id, Carrinho carrinho) {
    	if (!carrinhoRepository.existsById(id)) {
    		throw new RuntimeException("Carrinho não encontrado com o id: " + id);
    	}
    	Carrinho carrinhoExistente = carrinhoRepository.findById(id)
    	                                                .orElseThrow(() -> new RuntimeException("Carrinho para alteração não encontrado com o id: " + id));

    	carrinhoExistente.setValorTotal(carrinho.getValorTotal());

    	if (carrinho.getUsuario() != null && carrinho.getUsuario().getId() != null) {
    	    Usuario usuarioExistente = usuarioRepository.findById(carrinho.getUsuario().getId())
    	                                                .orElseThrow(() -> new RuntimeException("Usuário para alteração não encontrado com o id: " + carrinho.getUsuario().getId()));
    	    carrinhoExistente.setUsuario(usuarioExistente);
    	}

    	if (carrinho.getProdutos() != null) {
    	    carrinhoExistente.getProdutos().clear();
    	    for (Produto produtoEnviado : carrinho.getProdutos()) {
    	        if (produtoEnviado.getId() != null) {
    	            Produto prod = produtoRepository.findById(produtoEnviado.getId())
    	                                            .orElseThrow(() -> new RuntimeException("Produto para alteração não encontrado com o id: " + produtoEnviado.getId()));
    	            carrinhoExistente.getProdutos().add(prod);
    	        }
    	    }
    	}
    	return carrinhoRepository.save(carrinhoExistente);
    }
}