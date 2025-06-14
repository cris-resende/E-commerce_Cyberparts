package com.edu.infnet.CyberParts.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="TProdutos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
    private String nomeProduto;
    private String categoria;
    private double preco;
    private int estoque;
    
    public Produto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
    
    @Override
    public String toString() {
        return String.format("ID: %d - Produto: %s - Categoria: %s - Preço: %.2f - Estoque: %d", id, nomeProduto, categoria, preco, estoque);
    }
}