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

import com.edu.infnet.CyberParts.model.domain.Pagamento;
import com.edu.infnet.CyberParts.model.domain.Pedido;
import com.edu.infnet.CyberParts.model.service.PagamentoService;
import com.edu.infnet.CyberParts.model.service.PedidoService;

import jakarta.transaction.Transactional;

@Component
@Order(5)
public class PagamentoLoader implements ApplicationRunner {

	@Autowired
	private PagamentoService service;
	@Autowired
	private PedidoService pedidoService;
	
	@Transactional
	@Override
	public void run(ApplicationArguments args) throws Exception {
        System.out.println("\n--- INICIANDO CARREGAMENTO DE PAGAMENTOS ---");
        try {
            FileReader arquivoPagamentos = new FileReader("files/pagamento.csv");
            BufferedReader leituraPagamentos = new BufferedReader(arquivoPagamentos);

            String linha = leituraPagamentos.readLine();
            String[] campos = null;

            while(linha != null){
                campos = linha.split(",");
                
                Pagamento p = new Pagamento();
                
                int idPagamento = Integer.parseInt(campos[0]);
                int idPedidoAssociado = Integer.parseInt(campos[0]);

                p.setId(idPagamento);
                p.setForma(campos[1]);
                p.setValorTotal(Double.parseDouble(campos[2]));
                p.setStatus(campos[3]);
                
                service.registrarPagamento(p, idPedidoAssociado);
                
                linha = leituraPagamentos.readLine();
            }
            
            Iterable<Pagamento> pagamentosCarregados = service.obterPagamentos();
            long totalPagamentos = 0;
            
            for(Pagamento p : pagamentosCarregados){
                System.out.println(p);
                System.out.println("---------------------------------------------------------------------------------------------");
                totalPagamentos++;
            }
            
            System.out.println("Total de pagamentos carregados: " + totalPagamentos);
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
        System.out.println("\n--- FIM DO CARREGAMENTO DE PAGAMENTOS ---");
	}
}