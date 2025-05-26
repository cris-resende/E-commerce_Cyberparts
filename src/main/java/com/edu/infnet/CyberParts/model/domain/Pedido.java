package com.edu.infnet.CyberParts.model.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="TPedidos")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
    public LocalDate data;
    public String status;
    
    @Transient
    public Usuario cliente;
    
    @Transient
    public Pagamento pagamento;

    @Transient
    public List<Produto> produtos;
    
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