package com.edu.infnet.CyberParts.model.domain;

public class Produto {
    public int codigo;
    public String nomeProduto;
    public String categoria;
    public double preco;
    public int estoque;
    
    @Override
    public String toString() {
        return String.format("Código: %d - Produto: %s - Categoria: %s - Preço: %f - Estoque: %d", codigo, nomeProduto, categoria, preco, estoque);
    }
}