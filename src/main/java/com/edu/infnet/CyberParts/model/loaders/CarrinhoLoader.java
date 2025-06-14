package com.edu.infnet.CyberParts.model.loaders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("\n--- INICIANDO CARREGAMENTO DE CARRINHOS ---");
        try {
            FileReader arquivoCarrinhos = new FileReader("files/carrinhos.csv");
            BufferedReader leituraCarrinhos = new BufferedReader(arquivoCarrinhos);

            String linha = leituraCarrinhos.readLine();
            String[] campos = null;

            Map<Integer, Carrinho> carrinhosEmConstrucao = new HashMap<>();

            while (linha != null) {
                campos = linha.split(",");

                Integer idCarrinho = Integer.parseInt(campos[0]);
                String emailUsuario = campos[1];
                int codigoProduto = Integer.parseInt(campos[2]);

                Carrinho carrinhoAtual = carrinhosEmConstrucao.get(idCarrinho);

                if (carrinhoAtual == null) {
                    carrinhoAtual = new Carrinho();
                    carrinhoAtual.setId(idCarrinho);
                    Usuario usuario = StreamSupport.stream(usuarioService.obterUsers().spliterator(), false)
                                                    .filter(u -> Objects.equals(u.getEmail(), emailUsuario))
                                                    .findFirst()
                                                    .orElse(null);
                    carrinhoAtual.setUsuario(usuario);
                    carrinhosEmConstrucao.put(idCarrinho, carrinhoAtual);
                }

                Produto produtoAdicionar = StreamSupport.stream(produtoService.obterProdutos().spliterator(), false)
                                                        .filter(p -> p.getId() == codigoProduto)
                                                        .findFirst()
                                                        .orElse(null);

                if (produtoAdicionar != null) {
                    carrinhoAtual.adicionarProduto(produtoAdicionar);
                } else {
                    System.out.println("AVISO: Produto com código " + codigoProduto + " não encontrado para o carrinho " + idCarrinho + ". Verifique o CSV de produtos.");
                }
                
                carrinhoAtual.calcularTotal();

                linha = leituraCarrinhos.readLine();
            }
            leituraCarrinhos.close();

            for (Carrinho c : carrinhosEmConstrucao.values()) {
                carrinhoService.incluirCarrinho(c);
            }

            System.out.println("\n--- TESTE DE CARRINHOS (Carregados do H2) ---");
            Iterable<Carrinho> carrinhosCarregados = carrinhoService.obterCarrinhos();
            
            long totalCarrinhos = StreamSupport.stream(carrinhosCarregados.spliterator(), false).count();
            
            for (Carrinho c : carrinhosCarregados) {
                System.out.println(c);
                if (c.getProdutos() != null && !c.getProdutos().isEmpty()) {
                    System.out.println("  Produtos no carrinho (via lazy loading):");
                    for (Produto p : c.getProdutos()) {
                        System.out.println("    - " + p.getNomeProduto() + " (R$ " + p.getPreco() + ")");
                    }
                } else {
                    System.out.println("  Nenhum produto neste carrinho (ou lista vazia).");
                }
                System.out.println("---------------------------------------------------------------------------------------------");
            }
            System.out.println("Total de carrinhos carregados: " + totalCarrinhos);

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