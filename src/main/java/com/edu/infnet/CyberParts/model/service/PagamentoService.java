package com.edu.infnet.CyberParts.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.infnet.CyberParts.model.domain.Pagamento;
import com.edu.infnet.CyberParts.model.repository.PagamentoRepository;

@Service
public class PagamentoService {
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
    public void registrarPagamento(Pagamento p){
        pagamentoRepository.save(p);
    }

    public Iterable<Pagamento> obterPagamentos(){
        return pagamentoRepository.findAll();
    }

}
