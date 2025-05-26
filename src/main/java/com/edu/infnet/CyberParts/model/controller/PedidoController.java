package com.edu.infnet.CyberParts.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.infnet.CyberParts.model.domain.Pedido;
import com.edu.infnet.CyberParts.model.service.PedidoService;

@RestController
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping("/pedidos")
    public Iterable<Pedido> obterPedidos(){
        return pedidoService.obterPedidos();
    }
}
