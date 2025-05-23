package com.edu.infnet.CyberParts.model.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.edu.infnet.CyberParts.model.domain.Pedido;

@Service
public class PedidoService {
	
    private Map<String, Pedido> mapaPedidos = new HashMap<String, Pedido>();

    public void incluirPedido(Pedido p){
        mapaPedidos.put(p.cliente,p);
    }

    public Collection<Pedido> obterPedidos(){
        return mapaPedidos.values();
    }

}
