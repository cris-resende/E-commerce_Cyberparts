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

import com.edu.infnet.CyberParts.model.domain.Pedido;
import com.edu.infnet.CyberParts.model.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping("/lista")
    public Iterable<Pedido> obterPedidos(){
        return pedidoService.obterPedidos();
    }
	
	@GetMapping("/{id}")
    public Pedido obterPedidoPorId(@PathVariable Integer id) {
    	return pedidoService.obterPedidoPorId(id);
    }
    
	@PostMapping("/incluir")
    public void incluirPedido(@RequestBody Pedido p){
    	pedidoService.incluirPedido(p);
    }
    
	@PutMapping("/{id}")
    public Pedido alterarPedido(@PathVariable Integer id, @RequestBody Pedido pedido) {
    	return pedidoService.alterarPedido(id, pedido);
    }
    
	@DeleteMapping("/{id}")
    public void removerPedido(@PathVariable Integer id) {
    	pedidoService.removerPedido(id);
    }
}