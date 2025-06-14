package com.edu.infnet.CyberParts.model.loaders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.edu.infnet.CyberParts.model.domain.Pedido;
import com.edu.infnet.CyberParts.model.domain.Produto;
import com.edu.infnet.CyberParts.model.domain.Usuario;
import com.edu.infnet.CyberParts.model.service.PedidoService;
import com.edu.infnet.CyberParts.model.service.ProdutoService;
import com.edu.infnet.CyberParts.model.service.UsuarioService;

@Component
@Order(4)
public class PedidoLoader implements ApplicationRunner {
	
	@Autowired
	private PedidoService service;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ProdutoService produtoService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
        System.out.println("\n--- INICIANDO CARREGAMENTO DE PEDIDOS ---");
        try{
            FileReader arquivoPedidos = new FileReader("files/pedidos.csv");
            BufferedReader leituraPedidos = new BufferedReader(arquivoPedidos);

            String linha = leituraPedidos.readLine();
            String[] campos = null;

            while(linha != null){
                campos = linha.split(",");

                Pedido p = new Pedido();
                p.setId(Integer.parseInt(campos[0]));

                String emailCliente = campos[1];
                Usuario cliente =StreamSupport.stream(usuarioService.obterUsers().spliterator(), false)
                                                    .filter(u -> Objects.equals(u.getEmail(), emailCliente))
                                                    .findFirst()
                                                    .orElse(null);
                if (cliente != null) {
                    p.setCliente(cliente);
                } else {
                    System.out.println("AVISO: Usuário '" + emailCliente + "' não encontrado para o pedido ID " + p.getId() + ". Verifique o CSV de usuários.");
                }

                String nomeProdutoDoCsv = campos[2];
                Produto produtoEncontrado = StreamSupport.stream(produtoService.obterProdutos().spliterator(), false)
                                                    .filter(prod -> Objects.equals(prod.getNomeProduto(), nomeProdutoDoCsv))
                                                    .findFirst()
                                                    .orElse(null);

                if (produtoEncontrado != null) {
                    p.getProdutos().add(produtoEncontrado);
                } else {
                    System.out.println("AVISO: Produto '" + nomeProdutoDoCsv + "' não encontrado para o pedido ID " + p.getId() + ". Verifique o CSV de produtos.");
                }

                DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                p.setData(LocalDate.parse(campos[3], formato));
                p.setStatus(campos[4]);

                service.incluirPedido(p);
                linha = leituraPedidos.readLine();
            }
            for (Pedido p : service.obterPedidos()){
                System.out.println(p);
                System.out.println("---------------------------------------------------------------------------------------------");
            }
            System.out.println("Total de pedidos carregados: " + StreamSupport.stream(service.obterPedidos().spliterator(), false).count());
            leituraPedidos.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de pedidos (pedidos.csv) não encontrado!");
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("Impossível abrir/fechar o arquivo de pedidos.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Erro na conversão de número no arquivo CSV de pedidos. Verifique o formato do ID.");
            e.printStackTrace();
        }
        System.out.println("--- FIM DO CARREGAMENTO DE PEDIDOS ---");
	}
}