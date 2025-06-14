package com.edu.infnet.CyberParts.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="TPagamentos")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedidoAssociado;
    
    private String forma;
    private Double valorTotal;
    private String status;

    public Pagamento() {}

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Pedido getPedidoAssociado() {
        return pedidoAssociado;
    }
    public void setPedidoAssociado(Pedido pedidoAssociado) {
        this.pedidoAssociado = pedidoAssociado;
    }

    public String getForma() {
        return forma;
    }
    public void setForma(String forma) {
        this.forma = forma;
    }

    public Double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Pagamento ID: %d - Pedido: %s - Forma: %s - Valor: %.2f - Status: %s",
                             id,
                             pedidoAssociado != null ? "ID: " + pedidoAssociado.getId() + " | Cliente: " + (pedidoAssociado.getCliente() != null ? pedidoAssociado.getCliente().getNome() : "N/A") + " | Total Produtos: " + (pedidoAssociado.getProdutos() != null ? pedidoAssociado.getProdutos().size() : 0) : "N/A",
                             forma,
                             valorTotal,
                             status);
    }
}