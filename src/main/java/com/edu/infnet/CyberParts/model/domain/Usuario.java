package com.edu.infnet.CyberParts.model.domain;

import java.util.List;

public class Usuario {
    public String nome;
    public String email;
    //private String senha;
    public String tipo;
    public List<Pedido> pedidos;
    public Pagamento pagamento;
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return String.format("Usuário: %s - e-mail: %s - Tipo: %s", nome, email, tipo);
    }
}