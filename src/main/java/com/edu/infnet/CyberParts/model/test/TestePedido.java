package com.edu.infnet.CyberParts.model.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.edu.infnet.CyberParts.model.domain.Pedido;
import com.edu.infnet.CyberParts.model.service.PedidoService;


@Component
@Order(5)
public class TestePedido implements ApplicationRunner {
	
	@Autowired
	private PedidoService service;

	@Override
	public void run(ApplicationArguments args) throws Exception {
        System.out.println("\n--- TESTE DE PEDIDOS ---");
        try{
            FileReader arquivoPedidos = new FileReader("pedidos.csv");
            BufferedReader leituraPedidos = new BufferedReader(arquivoPedidos);

            String linha = leituraPedidos.readLine();
            String[] campos = null;

            while(linha != null){
                campos = linha.split(",");

                Pedido p = new Pedido();
                p.cliente = campos[0];
                p.produto = campos[1];

                DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                p.data = LocalDate.parse(campos[2], formato);
                p.status = campos[3];

                service.incluirPedido(p);
                linha = leituraPedidos.readLine();
            }
            for (Pedido p : service.obterPedidos()){
                System.out.println(p);
                System.out.println("---------------------------------------------------------------------------------------------");
            }
            leituraPedidos.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de pedidos não encontrado");
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("Imporssível abrir/fechar o arquivo");
            e.printStackTrace();
        }
        System.out.println("\n--- FIM DO TESTE DE PEDIDOS ---");
	}
}