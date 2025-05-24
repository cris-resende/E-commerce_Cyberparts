package com.edu.infnet.CyberParts.model.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.edu.infnet.CyberParts.model.domain.Carrinho;

@Service
public class CarrinhoService {

    private Map<String, Carrinho> mapaCarrinhos = new HashMap<String, Carrinho>();

    public void incluirCarrinho(Carrinho c){
        mapaCarrinhos.put(c.id, c);
    }

    public Collection<Carrinho> obterCarrinhos(){
        return mapaCarrinhos.values();
    }

    public Carrinho obterCarrinhoPorId(String id) {
        return mapaCarrinhos.get(id);
    }
}