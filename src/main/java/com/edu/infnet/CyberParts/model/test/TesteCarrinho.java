package com.edu.infnet.CyberParts.model.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects; // Importar Objects para usar Objects.equals

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.edu.infnet.CyberParts.model.domain.Carrinho;
import com.edu.infnet.CyberParts.model.domain.Produto;
import com.edu.infnet.CyberParts.model.domain.Usuario;
import com.edu.infnet.CyberParts.model.service.CarrinhoService;
import com.edu.infnet.CyberParts.model.service.ProdutoService;
import com.edu.infnet.CyberParts.model.service.UsuarioService;

@Component
@Order(3)
public class TesteCarrinho implements ApplicationRunner {

    @Autowired
    private CarrinhoService carrinhoService;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            FileReader arquivoCarrinhos = new FileReader("carrinho.csv");
            BufferedReader leituraCarrinhos = new BufferedReader(arquivoCarrinhos);

            String linha = leituraCarrinhos.readLine();
            String[] campos = null;

            while (linha != null) {
                campos = linha.split(",");

                String idCarrinho = campos[0];
                String emailUsuario = campos[1];
                int codigoProduto = Integer.parseInt(campos[2]);
                // int quantidadeProduto = Integer.parseInt(campos[3]);

                Carrinho carrinho = carrinhoService.obterCarrinhoPorId(idCarrinho);
                if (carrinho == null) {
                    carrinho = new Carrinho();
                    carrinho.id = idCarrinho;
                    Usuario usuario = usuarioService.obterUsers().stream()
                                                    .filter(u -> Objects.equals(u.email, emailUsuario))
                                                    .findFirst()
                                                    .orElse(null);
                    carrinho.usuario = usuario;
                    carrinhoService.incluirCarrinho(carrinho);
                }

                Produto produtoAdicionar = null;
                for (Produto p : produtoService.obterProdutos()) {
                    if (p.codigo == codigoProduto) {
                        produtoAdicionar = p;
                        break;
                    }
                }

                if (produtoAdicionar != null) {
                    carrinho.adicionarProduto(produtoAdicionar);
                } else {
                    System.out.println("Produto com código " + codigoProduto + " não encontrado para o carrinho " + idCarrinho);
                }
                
                carrinho.calcularTotal();

                linha = leituraCarrinhos.readLine();
            }

            System.out.println("\n--- TESTE DE CARRINHOS ---");
            for (Carrinho c : carrinhoService.obterCarrinhos()) {
                System.out.println(c);
                if (c.produtos != null && !c.produtos.isEmpty()) {
                    System.out.println("  Produtos no carrinho:");
                    for (Produto p : c.produtos) {
                        System.out.println("    - " + p.nomeProduto + " (R$ " + p.preco + ")");
                    }
                } else {
                    System.out.println("  Nenhum produto neste carrinho.");
                }
                System.out.println("---------------------------------------------------------------------------------------------");
            }
            System.out.println("Total de carrinhos carregados: " + carrinhoService.obterCarrinhos().size());

            leituraCarrinhos.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de carrinhos (carrinhos.csv) não encontrado!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Impossível abrir/fechar o arquivo de carrinhos.");
            e.printStackTrace();
        }
        System.out.println("\n--- FIM DO TESTE DE CARRINHOS ---");
    }
}