package com.edu.infnet.CyberParts.model.domain;

import java.util.List;
import java.time.LocalDate;

public class Pedido {
    public String cliente;
    public String produto;
    public LocalDate data;
    public String status;
    public List<Produto> produtos;
    public Pagamento pagamento;
    public Usuario usuario;

    @Override
    public String toString() {
        return String.format("Pedido: %s - Produto: %s - Data: %s - Status: %s", cliente, produto, data, status);
    }
}