package com.edu.infnet.CyberParts.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="TProdutos")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer codigo;
	
    public String nomeProduto;
    public String categoria;
    public double preco;
    public int estoque;
    
    @Override
    public String toString() {
        return String.format("Código: %d - Produto: %s - Categoria: %s - Preço: %f - Estoque: %d", codigo, nomeProduto, categoria, preco, estoque);
    }
}