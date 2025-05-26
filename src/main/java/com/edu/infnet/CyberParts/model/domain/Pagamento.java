package com.edu.infnet.CyberParts.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="TPagamentos")
public class Pagamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
	
    public String forma;
    public Double valorTotal;
    public String status;
    
    @Transient
    public Pedido pedidoAssociado;



    @Override
    public String toString() {
        return String.format("Pagamento ID: %d - Pedido: %s - Forma: %s - Valor: %.2f - Status: %s",
                             id,
                             pedidoAssociado != null ? "ID: " + pedidoAssociado.id + " | Cliente: " + (pedidoAssociado.cliente != null ? pedidoAssociado.cliente.nome : "N/A") : "N/A",
                             forma,
                             valorTotal,
                             status);
    }
}