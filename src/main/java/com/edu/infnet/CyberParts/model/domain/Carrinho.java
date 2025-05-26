package com.edu.infnet.CyberParts.model.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="TCarrinho")
public class Carrinho {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public double valorTotal;
    
    @Transient
    public Usuario usuario;
    @Transient
    public List<Produto> produtos;


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