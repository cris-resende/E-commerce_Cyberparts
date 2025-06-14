package com.edu.infnet.CyberParts.model.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="TPedidos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
    private LocalDate data;
    private String status;
    
    @JsonBackReference("usuario-pedidos")
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Usuario cliente;
    
    @JsonManagedReference
    @OneToOne(mappedBy = "pedidoAssociado")
    private Pagamento pagamento;

    @ManyToMany
    @JoinTable(
        name = "tpedido_produtos",
        joinColumns = @JoinColumn(name = "id_pedido"),
        inverseJoinColumns = @JoinColumn(name = "id_produto")
    )
    private List<Produto> produtos;
    
    public Pedido() {
    	this.produtos = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Usuario getCliente() {
        return cliente;
    }
    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }
    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public String toString() {
        return String.format("Pedido ID: %d - Cliente: %s - Data: %s - Status: %s",
                id,
                cliente != null ? cliente.getNome() : "N/A",
                data,
                status
                );
    }
}