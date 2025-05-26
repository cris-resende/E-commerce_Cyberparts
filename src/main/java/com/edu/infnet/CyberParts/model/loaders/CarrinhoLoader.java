package com.edu.infnet.CyberParts.model.loaders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;

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
public class CarrinhoLoader implements ApplicationRunner {

    @Autowired
    private CarrinhoService carrinhoService;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("\n--- INICIANDO CARREGAMENTO DE CARRINHOS ---");
        try {
            FileReader arquivoCarrinhos = new FileReader("carrinhos.csv");
            BufferedReader leituraCarrinhos = new BufferedReader(arquivoCarrinhos);

            String linha = leituraCarrinhos.readLine();
            String[] campos = null;

            while (linha != null) {
                campos = linha.split(",");

                Integer idCarrinho = Integer.parseInt(campos[0]);
                String emailUsuario = campos[1];
                int codigoProduto = Integer.parseInt(campos[2]);

                Optional<Carrinho> carrinhoOptional = carrinhoService.obterCarrinhoPorId(idCarrinho);
                Carrinho carrinho = null;

                if (carrinhoOptional.isPresent()) {
                    carrinho = carrinhoOptional.get();
                } else {
                    carrinho = new Carrinho();
                    carrinho.id = idCarrinho;
                    Usuario usuario = StreamSupport.stream(usuarioService.obterUsers().spliterator(), false)
                                                    .filter(u -> Objects.equals(u.email, emailUsuario))
                                                    .findFirst()
                                                    .orElse(null);

                    carrinho.usuario = usuario;

                }

                Produto produtoAdicionar = StreamSupport.stream(produtoService.obterProdutos().spliterator(), false)
                                                        .filter(p -> p.codigo == codigoProduto)
                                                        .findFirst()
                                                        .orElse(null);

                if (produtoAdicionar != null) {
                    carrinho.adicionarProduto(produtoAdicionar);
                } else {
                    System.out.println("AVISO: Produto com código " + codigoProduto + " não encontrado para o carrinho " + idCarrinho + ". Verifique o CSV de produtos.");
                }
                
                carrinho.calcularTotal();

                carrinhoService.incluirCarrinho(carrinho);
                linha = leituraCarrinhos.readLine();
            }

            System.out.println("\n--- TESTE DE CARRINHOS (Carregados do H2) ---");
            Iterable<Carrinho> carrinhosCarregados = carrinhoService.obterCarrinhos();
            
            long totalCarrinhos = StreamSupport.stream(carrinhosCarregados.spliterator(), false).count();
            
            for (Carrinho c : carrinhosCarregados) {
                System.out.println(c);

                if (c.produtos != null && !c.produtos.isEmpty()) {
                    System.out.println("  Produtos no carrinho (via transient):");
                    for (Produto p : c.produtos) {
                        System.out.println("    - " + p.nomeProduto + " (R$ " + p.preco + ")");
                    }
                } else {
                    System.out.println("  Nenhum produto neste carrinho (ou não carregado via transient).");
                }
                System.out.println("---------------------------------------------------------------------------------------------");
            }
            System.out.println("Total de carrinhos carregados: " + totalCarrinhos);

            leituraCarrinhos.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de carrinhos (carrinhos.csv) não encontrado!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Impossível abrir/fechar o arquivo de carrinhos.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Erro na conversão de número no arquivo CSV de carrinhos. Verifique o formato do ID e código do produto.");
            e.printStackTrace();
        }
        System.out.println("\n--- FIM DO CARREGAMENTO DE CARRINHOS ---");
    }
}