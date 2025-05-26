package com.edu.infnet.CyberParts.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.infnet.CyberParts.model.domain.Pagamento;
import com.edu.infnet.CyberParts.model.service.PagamentoService;

@RestController
public class PagamentoController {
	
	@Autowired
	private PagamentoService pagamentoService;
	
	@GetMapping("/Pagamentos")
    public Iterable<Pagamento> obterPagamentos(){
        return pagamentoService.obterPagamentos();
    }

}