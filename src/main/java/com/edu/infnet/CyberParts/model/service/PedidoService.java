package com.edu.infnet.CyberParts.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.infnet.CyberParts.model.domain.Pagamento;
import com.edu.infnet.CyberParts.model.domain.Pedido;
import com.edu.infnet.CyberParts.model.domain.Produto;
import com.edu.infnet.CyberParts.model.domain.Usuario;
import com.edu.infnet.CyberParts.model.repository.PagamentoRepository;
import com.edu.infnet.CyberParts.model.repository.PedidoRepository;
import com.edu.infnet.CyberParts.model.repository.ProdutoRepository;
import com.edu.infnet.CyberParts.model.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class PedidoService {
	
    @Autowired private PedidoRepository pedidoRepository;
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private ProdutoRepository produtoRepository;
    @Autowired private PagamentoRepository pagamentoRepository; 
	
    @Transactional
    public void incluirPedido(Pedido p){
        if (p.getCliente() != null && p.getCliente().getId() != null) {
            Usuario clienteExistente = usuarioRepository.findById(p.getCliente().getId())
                                                        .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o id: " + p.getCliente().getId()));
            p.setCliente(clienteExistente);
        }
        if (p.getProdutos() != null && !p.getProdutos().isEmpty()) {
            List<Produto> produtosExistentes = new ArrayList<>();
            for (Produto produtoEnviado : p.getProdutos()) {
                if (produtoEnviado.getId() != null) {
                    Produto prod = produtoRepository.findById(produtoEnviado.getId())
                                                    .orElseThrow(() -> new RuntimeException("Produto não encontrado com o id: " + produtoEnviado.getId()));
                    produtosExistentes.add(prod);
                }
            }
            p.setProdutos(produtosExistentes);
        }

        pedidoRepository.save(p);
    }

    @Transactional
    public Iterable<Pedido> obterPedidos(){
        return pedidoRepository.findAll();
    }
    
    @Transactional
    public Pedido obterPedidoPorId(Integer id) {
    	return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado com o id: " + id));
    }
    
    @Transactional
    public void removerPedido(Integer id) {
            Pedido pedidoParaRemover = pedidoRepository.findById(id)
                                                        .orElseThrow(() -> new RuntimeException("Pedido não encontrado com o id: " + id));

            Pagamento pagamentoAssociado = pedidoParaRemover.getPagamento();
            if (pagamentoAssociado != null) {
                pagamentoRepository.delete(pagamentoAssociado);
            }
            pedidoRepository.deleteById(id);
    }
    
    @Transactional
    public Pedido alterarPedido(Integer id, Pedido pedido) {
        Pedido pedidoExistente = pedidoRepository.findById(id)
                                                  .orElseThrow(() -> new RuntimeException("Pedido não encontrado com o id: " + id));
        pedidoExistente.setData(pedido.getData());
        pedidoExistente.setStatus(pedido.getStatus());

        if (pedido.getCliente() != null && pedido.getCliente().getId() != null) {
            Usuario clienteExistente = usuarioRepository.findById(pedido.getCliente().getId())
                                                        .orElseThrow(() -> new RuntimeException("Cliente para alteração não encontrado com o id: " + pedido.getCliente().getId()));
            pedidoExistente.setCliente(clienteExistente);
        }

        if (pedido.getProdutos() != null) {
            pedidoExistente.getProdutos().clear();
            for (Produto produtoEnviado : pedido.getProdutos()) {
                if (produtoEnviado.getId() != null) {
                    Produto prod = produtoRepository.findById(produtoEnviado.getId())
                                                    .orElseThrow(() -> new RuntimeException("Produto para alteração não encontrado com o id: " + produtoEnviado.getId()));
                    pedidoExistente.getProdutos().add(prod);
                }
            }
        }
        return pedidoRepository.save(pedidoExistente);
    }

}