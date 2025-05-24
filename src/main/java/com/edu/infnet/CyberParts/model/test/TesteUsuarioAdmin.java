package com.edu.infnet.CyberParts.model.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.edu.infnet.CyberParts.model.domain.Produto;
import com.edu.infnet.CyberParts.model.domain.UsuarioAdministrador;
import com.edu.infnet.CyberParts.model.service.ProdutoService;

@Component
@Order(6)
public class TesteUsuarioAdmin implements ApplicationRunner {

    @Autowired
    private ProdutoService pService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("\n--- TESTE DE ADMINISTRADOR ---");
        try {
            FileReader arquivoUsuarios = new FileReader("usuarios.csv");
            BufferedReader leituraUsuarios = new BufferedReader(arquivoUsuarios);

            String linha = leituraUsuarios.readLine();
            String[] campos = null;

            while (linha != null) {
                campos = linha.split(",");

                String tipoUsuario = campos[3];

                if (Objects.equals(tipoUsuario, "administrador")) {
                    UsuarioAdministrador admin = new UsuarioAdministrador();
                    admin.nome = campos[0];
                    admin.email = campos[1];
                    // admin.senha = campos[2];
                    admin.tipo = tipoUsuario;

                    System.out.println("Ações do administrador: " + admin.nome + " (" + admin.email + ")");

                    Produto novoProduto = new Produto();
                    novoProduto.codigo = 999;
                    novoProduto.nomeProduto = "Novo SSD NVMe Teste";
                    novoProduto.categoria = "Armazenamento";
                    novoProduto.preco = 250.00;
                    novoProduto.estoque = 100;
                    admin.cadastrarProduto(novoProduto);
                    pService.incluirProduto(novoProduto);

                    Produto produtoExistenteParaEditar = null;
                    for(Produto p : pService.obterProdutos()) {
                        if (p.codigo == 999) {
                            produtoExistenteParaEditar = p;
                            break;
                        }
                    }
                    if (produtoExistenteParaEditar != null) {
                        produtoExistenteParaEditar.preco = 350.00;
                        admin.editarProduto(produtoExistenteParaEditar);
                    } else {
                        System.out.println("  Produto com código 999 não encontrado para edição.");
                    }

                    Produto produtoExistenteParaRemover = null;
                    for(Produto p : pService.obterProdutos()) {
                        if (p.codigo == 999) {
                            produtoExistenteParaRemover = p;
                            break;
                        }
                    }
                    if (produtoExistenteParaRemover != null) {
                        admin.removerProduto(produtoExistenteParaRemover);
                    } else {
                        System.out.println("  Produto com código 999 não encontrado para remoção.");
                    }

                    admin.gerarRelatorioVendas();
                    System.out.println("---------------------------------------------------------------------------------------------");
                }
                linha = leituraUsuarios.readLine();
            }
            leituraUsuarios.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de usuários (usuarios.csv) não encontrado!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Impossível abrir/fechar o arquivo de usuários.");
            e.printStackTrace();
        }
        System.out.println("--- FIM DO TESTE DE ADMINISTRADOR ---");
    }
}