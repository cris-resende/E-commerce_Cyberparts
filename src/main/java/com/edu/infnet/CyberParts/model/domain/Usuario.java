package com.edu.infnet.CyberParts.model.domain;

public class Usuario {
    public String nome;
    public String email;
    //private String senha;
    private String tipo;
    
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