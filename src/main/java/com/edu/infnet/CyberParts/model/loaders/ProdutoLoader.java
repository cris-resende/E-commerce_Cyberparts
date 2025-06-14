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
            FileReader arquivoProduto = new FileReader("files/produtos.csv");
            BufferedReader leituraProduto = new BufferedReader(arquivoProduto);

            String linhaProduto = leituraProduto.readLine();
            String[] campos = null;

            while (linhaProduto != null) {
                campos = linhaProduto.split(",");
                
                Produto p = new Produto();
                p.setId(Integer.parseInt(campos[0]));
                p.setNomeProduto(campos[1]);
                p.setCategoria(campos[2]);
                p.setPreco(Double.parseDouble(campos[3]));
                p.setEstoque(Integer.parseInt(campos[4])); 
                
                service.incluirProduto(p);
                linhaProduto = leituraProduto.readLine();
            }

            for(Produto p : service.obterProdutos()){
                System.out.println(String.format("ID: %d - Produto: %s - Categoria: %s - Preço: %.2f - Estoque: %d",
                        p.getId(), p.getNomeProduto(), p.getCategoria(), p.getPreco(), p.getEstoque()));
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