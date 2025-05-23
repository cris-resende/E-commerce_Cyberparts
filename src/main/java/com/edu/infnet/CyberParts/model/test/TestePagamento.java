package com.edu.infnet.CyberParts.model.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.edu.infnet.CyberParts.model.domain.Pagamento;
import com.edu.infnet.CyberParts.model.service.PagamentoService;


@Component
public class TestePagamento implements ApplicationRunner {

	@Autowired
	private PagamentoService service;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
        try {
            FileReader arquivoPagamentos = new FileReader("pagamento.csv");
            BufferedReader leituraPagamentos = new BufferedReader(arquivoPagamentos);

            String linha = leituraPagamentos.readLine();
            String[] campos = null;

            while(linha != null){
                campos = linha.split(",");
                
                Pagamento p = new Pagamento();
                p.pedido = campos[0];
                p.forma = campos[1];
                p.valor = Float.parseFloat(campos[2]);
                p.status = campos[3];
                
                service.registrarPagamento(p);
                linha = leituraPagamentos.readLine();
            }
            for(Pagamento p : service.obterPagamentos()){
                System.out.println(p);
                System.out.println("---------------------------------------------------------------------------------------------");
            }
            leituraPagamentos.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de pagamentos não encontrado");
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("Imporssível abrir/fechar o arquivo");
            e.printStackTrace();
        }
	}
}