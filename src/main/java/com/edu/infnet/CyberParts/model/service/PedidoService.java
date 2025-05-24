package com.edu.infnet.CyberParts.model.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.edu.infnet.CyberParts.model.domain.Pedido;

@Service
public class PedidoService {
	
    private Map<Integer, Pedido> mapaPedidos = new HashMap<Integer, Pedido>();

    public void incluirPedido(Pedido p){
    	if (p.id == 0) {
    		System.err.println("Erro: Pedido sem ID não pode ser incluído no mapa de pedidos.");
    		return;
    	}
        mapaPedidos.put(p.id, p);
    }

    public Collection<Pedido> obterPedidos(){
        return mapaPedidos.values();
    }
    public Pedido obterPedidoPorId(int id) {
    	return mapaPedidos.get(id);
    }
    public void removerPedido(int id) {
    	mapaPedidos.remove(id);
    }

}