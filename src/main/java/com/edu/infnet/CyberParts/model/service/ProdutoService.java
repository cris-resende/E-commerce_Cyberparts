package com.edu.infnet.CyberParts.model.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.edu.infnet.CyberParts.model.domain.Produto;

@Service
public class ProdutoService {
    private Map<Integer, Produto> mapaProdutos = new HashMap<Integer, Produto>();

    public void incluirProduto(Produto p){
        mapaProdutos.put(p.codigo, p);
    }

    public Collection<Produto> obterProdutos(){
        return mapaProdutos.values();
    }
}
