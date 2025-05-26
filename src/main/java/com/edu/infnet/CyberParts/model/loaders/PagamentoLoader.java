package com.edu.infnet.CyberParts.model.loaders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.edu.infnet.CyberParts.model.domain.Pagamento;
import com.edu.infnet.CyberParts.model.domain.Pedido;
import com.edu.infnet.CyberParts.model.service.PagamentoService;
import com.edu.infnet.CyberParts.model.service.PedidoService;

@Component
@Order(6)
public class PagamentoLoader implements ApplicationRunner {

	@Autowired
	private PagamentoService service;
	@Autowired
	private PedidoService pedidoService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
        System.out.println("\n--- TESTE DE PAGAMENTOS ---");
        try {
            FileReader arquivoPagamentos = new FileReader("pagamento.csv");
            BufferedReader leituraPagamentos = new BufferedReader(arquivoPagamentos);

            String linha = leituraPagamentos.readLine();
            String[] campos = null;

            while(linha != null){
                campos = linha.split(",");
                
                Pagamento p = new Pagamento();
                
                int idPagamento = Integer.parseInt(campos[0]);
                int idPedidoAssociado = Integer.parseInt(campos[0]);

                p.id = idPagamento;
                p.forma = campos[1];
                p.valorTotal = Double.parseDouble(campos[2]);
                p.status = campos[3];
                
                Optional<Pedido> pedidoOptional = pedidoService.obterPedidoPorId(idPedidoAssociado);
                Pedido pedidoAssociado = pedidoOptional.orElse(null);

                if (pedidoAssociado != null) {
                    p.pedidoAssociado = pedidoAssociado;
                } else {
                    System.out.println("AVISO: Pedido com ID " + idPedidoAssociado + " não encontrado para o pagamento ID " + idPagamento);
                }
                
                service.registrarPagamento(p);
                linha = leituraPagamentos.readLine();
            }
            for(Pagamento p : service.obterPagamentos()){
                System.out.println(p);
                System.out.println("---------------------------------------------------------------------------------------------");
            }
            System.out.println("Total de pagamentos carregados: " + service.obterPagamentos());
            leituraPagamentos.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de pagamentos (pagamento.csv) não encontrado!");
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("Impossível abrir/fechar o arquivo de pagamentos.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Erro na conversão de número no arquivo CSV de pagamentos. Verifique o formato dos valores.");
            e.printStackTrace();
        }
        System.out.println("\n--- FIM DO TESTE DE PAGAMENTOS ---");
	}
}