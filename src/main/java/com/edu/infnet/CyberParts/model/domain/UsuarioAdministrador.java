package com.edu.infnet.CyberParts.model.domain;

public class UsuarioAdministrador extends Usuario {

    public UsuarioAdministrador() {
        super();
        this.setTipo("administrador");
    }

    public void cadastrarProduto(Produto produto) {
        System.out.println("ADMINISTRADOR: Produto '" + produto.nomeProduto + "' cadastrado.");
    }

    public void editarProduto(Produto produto) {
        System.out.println("ADMINISTRADOR: Produto '" + produto.nomeProduto + "' editado.");
    }

    public void removerProduto(Produto produto) {
        System.out.println("ADMINISTRADOR: Produto '" + produto.nomeProduto + "' removido.");
    }

    public String gerarRelatorioVendas() {
        String relatorio = "ADMINISTRADOR: Gerando relatório de vendas...";
        System.out.println(relatorio);
        return relatorio;
    }

    @Override
    public String toString() {
        return String.format("ADMINISTRADOR - %s", super.toString());
    }
}