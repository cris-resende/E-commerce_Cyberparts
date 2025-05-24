package com.edu.infnet.CyberParts.model.domain;

import java.util.List;
import java.util.ArrayList; // Importar ArrayList para inicializar a lista de produtos

public class Carrinho {
    public String id;
    public Usuario usuario;
    public List<Produto> produtos;
    public double valorTotal;

    public Carrinho() {
        this.produtos = new ArrayList<>();
    }
    
    public void adicionarProduto(Produto produto) {
        if (this.produtos == null) {
            this.produtos = new ArrayList<>();
        }
        this.produtos.add(produto);
    }

    public void removerProduto(Produto produto) {
        if (this.produtos != null) {
            this.produtos.remove(produto);
        }
    }

    public double calcularTotal() {
        this.valorTotal = 0.0;
        if (this.produtos != null) {
            for (Produto p : this.produtos) {
                this.valorTotal += p.preco;
            }
        }
        return this.valorTotal;
    }

    @Override
    public String toString() {
        return String.format("Carrinho ID: %s - Usuário: %s - Total de Produtos: %d - Valor Total: %.2f", 
                             id, 
                             usuario != null ? usuario.nome : "N/A", 
                             produtos != null ? produtos.size() : 0, 
                             valorTotal);
    }
}