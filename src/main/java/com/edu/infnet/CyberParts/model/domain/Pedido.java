package com.edu.infnet.CyberParts.model.domain;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Pedido {
	public int id;
    public Usuario cliente;
    public LocalDate data;
    public String status;
    public List<Produto> produtos;
    public Pagamento pagamento;
    
    public Pedido() {
    	this.produtos = new ArrayList<>();
    }

    @Override
    public String toString() {
        return String.format("Pedido ID: %d - Cliente: %s - Data: %s - Status: %s - Total de Produtos: %d",
                id,
                cliente != null ? cliente.nome : "N/A",
                data,
                status,
                produtos != null? produtos.size() : 0);
    }
}