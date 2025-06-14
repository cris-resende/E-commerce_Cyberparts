package com.edu.infnet.CyberParts.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.infnet.CyberParts.model.domain.Pagamento;
import com.edu.infnet.CyberParts.model.domain.Pedido;
import com.edu.infnet.CyberParts.model.repository.PagamentoRepository;
import com.edu.infnet.CyberParts.model.repository.PedidoRepository;

import jakarta.transaction.Transactional;

@Service
public class PagamentoService {
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Transactional
    public void registrarPagamento(Pagamento p, Integer idPedidoAssociado){
        if (idPedidoAssociado != null) {
            Pedido pedidoExistente = pedidoRepository.findById(idPedidoAssociado)
                                                    .orElseThrow(() -> new RuntimeException("Pedido associado não encontrado com o id: " + idPedidoAssociado));
            p.setPedidoAssociado(pedidoExistente);
        } else {

        }
        pagamentoRepository.save(p);
    }

    public Iterable<Pagamento> obterPagamentos(){
        return pagamentoRepository.findAll();
    }

    public Pagamento obterPagamentoPorId(Integer id) {
    	
    	return pagamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pagamento não encontrado com o id: " + id));
    }
    
    @Transactional
    public void removerPagamento(Integer id) {
    	if (!pagamentoRepository.existsById(id)) {
    		throw new RuntimeException("Pagamento não encontrado com o id: " + id);
    	}
    	
    	pagamentoRepository.deleteById(id);
    }
    
    @Transactional
    public Pagamento alterarPagamento(Integer id, Pagamento pagamento, Integer idPedidoAssociado) {
        if (!pagamentoRepository.existsById(id)) {
            throw new RuntimeException("Pagamento não encontrado com o id: " + id);
        }
        pagamento.setId(id);
        if (idPedidoAssociado != null) {
            Pedido pedidoExistente = pedidoRepository.findById(idPedidoAssociado)
                                                    .orElseThrow(() -> new RuntimeException("Pedido associado para alteração não encontrado com o id: " + idPedidoAssociado));
            pagamento.setPedidoAssociado(pedidoExistente);
        }
        return pagamentoRepository.save(pagamento);
    }
}
