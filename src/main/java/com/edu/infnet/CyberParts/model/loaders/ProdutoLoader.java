package com.edu.infnet.CyberParts.model.loaders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.edu.infnet.CyberParts.model.domain.Produto;
import com.edu.infnet.CyberParts.model.service.ProdutoService;


@Component
@Order(1)
public class ProdutoLoader implements ApplicationRunner {
	
	@Autowired
	private ProdutoService service;

	@Override
	public void run(ApplicationArguments args) throws Exception {
        System.out.println("\n--- TESTE DE PRODUTOS ---");
        try {
            FileReader arquivoProduto = new FileReader("produtos.csv");
            BufferedReader leituraProduto = new BufferedReader(arquivoProduto);

            String linhaProduto = leituraProduto.readLine();
            String[] Produtos = null;

            while (linhaProduto != null) {
                Produtos = linhaProduto.split(",");
                
                Produto p = new Produto();
                p.codigo = Integer.parseInt(Produtos[0]);
                p.nomeProduto = Produtos[1];
                p.categoria = Produtos[2];
                p.preco = Float.valueOf(Produtos[3]);
                p.estoque = Integer.parseInt(Produtos[4]);
                
                service.incluirProduto(p);
                linhaProduto = leituraProduto.readLine();
            }

            for(Produto p : service.obterProdutos()){
                System.out.println(p);
                System.out.println("---------------------------------------------------------------------------------------------");
            }
            leituraProduto.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de produtos não encontrado");
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("Imporssível abrir/fechar o arquivo");
            e.printStackTrace();
        }
        System.out.println("\n--- FIM DO TESTE DE PRODUTOS ---");
	}
}