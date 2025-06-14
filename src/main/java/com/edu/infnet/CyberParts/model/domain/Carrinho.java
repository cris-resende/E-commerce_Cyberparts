package com.edu.infnet.CyberParts.model.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="TCarrinho")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Carrinho {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double valorTotal;
    
    @JsonBackReference("usuario-carrinhos")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    	    name = "tcarrinho_produtos",
    	    joinColumns = @JoinColumn(name = "carrinho_id"),
    	    inverseJoinColumns = @JoinColumn(name = "produto_id")
    	)
    private List<Produto> produtos;


    public Carrinho() {
        this.produtos = new ArrayList<>();
    }
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
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
                this.valorTotal += p.getPreco();
            }
        }
        return this.valorTotal;
    }

    @Override
    public String toString() {
        return String.format("Carrinho ID: %d - Usuário: %s - Valor Total: %.2f",
                getId(),
                usuario != null ? usuario.getNome() : "N/A",
                getValorTotal());
    }
}