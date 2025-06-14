package com.edu.infnet.CyberParts.model.loaders;

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
import org.springframework.transaction.annotation.Transactional;

import com.edu.infnet.CyberParts.model.domain.Produto;
import com.edu.infnet.CyberParts.model.domain.UsuarioAdministrador;
import com.edu.infnet.CyberParts.model.service.ProdutoService;
import com.edu.infnet.CyberParts.model.service.UsuarioService;

@Component
@Order(6)
public class AdminLoader implements ApplicationRunner {

    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("\n--- TESTE DE ADMINISTRADOR ---");
        try {
            FileReader arquivoUsuarios = new FileReader("files/usuarios.csv");
            BufferedReader leituraUsuarios = new BufferedReader(arquivoUsuarios);

            String linha = leituraUsuarios.readLine();
            String[] campos = null;

            while (linha != null) {
                campos = linha.split(",");

                String tipoUsuario = campos[3];

                if (Objects.equals(tipoUsuario, "administrador")) {
                    UsuarioAdministrador admin = new UsuarioAdministrador();
                    admin.setNome(campos[0]);
                    admin.setEmail(campos[1]);
                    admin.setTipo(tipoUsuario);

                    System.out.println("Ações do administrador: " + admin.getNome() + " (" + admin.getEmail() + ")");

                    Produto novoProduto = new Produto();
                    novoProduto.setId(999);
                    novoProduto.setNomeProduto("SSD NVMe Teste Admin");
                    novoProduto.setCategoria("Armazenamento");
                    novoProduto.setPreco(250.00);
                    novoProduto.setEstoque(100);
                    
                    admin.cadastrarProduto(novoProduto);
                    Produto produtoCadastrado = produtoService.incluirProduto(novoProduto);

                    if (produtoCadastrado != null) {
                        System.out.println("  Editando produto cadastrado (ID: " + produtoCadastrado.getId() + ")...");
                        produtoCadastrado.setPreco(350.00);
                        admin.editarProduto(produtoCadastrado);
                        produtoService.alterarProduto(produtoCadastrado.getId(), produtoCadastrado);
                        System.out.println("  Produto " + produtoCadastrado.getNomeProduto() + " (ID: " + produtoCadastrado.getId() + ") editado com sucesso.");
                    } else {
                        System.out.println("  ERRO: Produto com ID 999 NÃO FOI CADASTRADO para edição.");
                    }


                    Produto produtoParaRemocao = new Produto();
                    produtoParaRemocao.setId(998);
                    produtoParaRemocao.setNomeProduto("Produto para Remover Teste");
                    produtoParaRemocao.setCategoria("Teste");
                    produtoParaRemocao.setPreco(10.00);
                    produtoParaRemocao.setEstoque(1);

                    produtoService.incluirProduto(produtoParaRemocao);
                    System.out.println("  Produto temporário para remoção (ID: " + produtoParaRemocao.getId() + ") cadastrado.");

                    System.out.println("  Testando remoção de produto com ID " + produtoParaRemocao.getId() + "...");
                    try {
                        produtoService.removerProduto(produtoParaRemocao.getId());
                        System.out.println("  Produto com ID " + produtoParaRemocao.getId() + " removido com sucesso.");
                    } catch (RuntimeException e) {
                        System.out.println("  AVISO: Falha ao remover produto com ID " + produtoParaRemocao.getId() + ": " + e.getMessage());
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
        } catch (RuntimeException e) {
            System.err.println("Erro durante o teste de administrador: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("--- FIM DO TESTE DE ADMINISTRADOR ---");
    }
}