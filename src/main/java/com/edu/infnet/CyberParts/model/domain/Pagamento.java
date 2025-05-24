package com.edu.infnet.CyberParts.model.domain;



public class Pagamento {
    public int id;
    public Pedido pedidoAssociado;
    public String forma;
    public Double valorTotal;
    public String status;


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