package com.edu.infnet.CyberParts.model.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.edu.infnet.CyberParts.model.domain.Pagamento;

@Service
public class PagamentoService {
	
    private Map<Integer, Pagamento> mapaPagamentos = new HashMap<Integer, Pagamento>();

    public void registrarPagamento(Pagamento p){
        mapaPagamentos.put(p.id, p);
    }

    public Collection<Pagamento> obterPagamentos(){
        return mapaPagamentos.values();
    }

}
