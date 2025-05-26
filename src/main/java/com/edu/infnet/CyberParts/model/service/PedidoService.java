package com.edu.infnet.CyberParts.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.infnet.CyberParts.model.domain.Pedido;
import com.edu.infnet.CyberParts.model.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
    public void incluirPedido(Pedido p){
    	pedidoRepository.save(p);
    }

    public Iterable<Pedido> obterPedidos(){
        return pedidoRepository.findAll();
    }
    public Optional<Pedido> obterPedidoPorId(Integer id) {
    	return pedidoRepository.findById(id);
    }
    
    public void removerPedido(int id) {
    	pedidoRepository.deleteById(id);
    }

}