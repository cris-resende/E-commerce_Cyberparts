package com.edu.infnet.CyberParts.model.domain;

public class UsuarioAdministrador extends Usuario {

    // Construtor padrão, pode chamar o construtor da classe pai se houver
    public UsuarioAdministrador() {
        super(); // Chama o construtor padrão da classe Usuario
        this.setTipo("administrador"); // Garante que o tipo seja sempre "administrador"
    }

    // Métodos específicos do administrador, conforme o diagrama de classes
    public void cadastrarProduto(Produto produto) {
        System.out.println("ADMINISTRADOR: Produto '" + produto.nomeProduto + "' cadastrado.");
        // Em uma implementação real, isso chamaria um ProdutoService para persistir o produto
    }

    public void editarProduto(Produto produto) {
        System.out.println("ADMINISTRADOR: Produto '" + produto.nomeProduto + "' editado.");
        // Em uma implementação real, isso chamaria um ProdutoService para atualizar o produto
    }

    public void removerProduto(Produto produto) {
        System.out.println("ADMINISTRADOR: Produto '" + produto.nomeProduto + "' removido.");
        // Em uma implementação real, isso chamaria um ProdutoService para remover o produto
    }

    public String gerarRelatorioVendas() {
        String relatorio = "ADMINISTRADOR: Gerando relatório de vendas...";
        System.out.println(relatorio);
        // Em uma implementação real, isso envolveria acesso a dados de pedidos para gerar um relatório complexo
        return relatorio;
    }

    @Override
    public String toString() {
        // Usa o toString da classe pai e adiciona uma marcação de administrador
        return String.format("ADMINISTRADOR - %s", super.toString());
    }
}