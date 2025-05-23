package com.edu.infnet.CyberParts.model.domain;

import java.util.List;

public class Pagamento {
    public String pedido;
    public String forma;
    public Float valor;
    public String status;
    public Usuario usuario;
    public List<Pedido> pedidos; 
    @Override
    public String toString() {
        return String.format("Pagamento: %s - Forma:%s - Valor: %f - Status: %s", pedido, forma, valor, status);
    }
}