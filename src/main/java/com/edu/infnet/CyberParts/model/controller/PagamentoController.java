package com.edu.infnet.CyberParts.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.infnet.CyberParts.model.domain.Pagamento;
import com.edu.infnet.CyberParts.model.service.PagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
	
	@Autowired
	private PagamentoService pagamentoService;
	
	@GetMapping("/lista")
    public Iterable<Pagamento> obterPagamentos(){
        return pagamentoService.obterPagamentos();
    }

	@GetMapping("/{id}")
    public Pagamento obterPagamentoPorId(@PathVariable Integer id) {
    	return pagamentoService.obterPagamentoPorId(id);
    }
    
	@PostMapping("/incluir")
    public void registrarPagamento(@RequestBody Pagamento p, @RequestParam Integer idPedidoAssociado){
        pagamentoService.registrarPagamento(p, idPedidoAssociado);
    }
    
	@PutMapping("/{id}")
    public Pagamento alterarPagamento(@PathVariable Integer id, @RequestBody Pagamento pagamento, @RequestParam(required = false) Integer idPedidoAssociado) {
        return pagamentoService.alterarPagamento(id, pagamento, idPedidoAssociado);
    }
    
	@DeleteMapping("/{id}")
    public void removerPagamento(@PathVariable Integer id) {
		pagamentoService.removerPagamento(id);
    }

}