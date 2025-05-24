package com.edu.infnet.CyberParts.model.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.edu.infnet.CyberParts.model.domain.Usuario;
import com.edu.infnet.CyberParts.model.service.UsuarioService;


@Component
@Order(2)
public class TesteUsuario implements ApplicationRunner{
	
	@Autowired
	private UsuarioService service;

	@Override
	public void run(ApplicationArguments args) throws Exception {
        System.out.println("\n--- TESTE DE USUARIOS ---");
        try{
            FileReader arquivo = new FileReader("usuarios.csv");
            BufferedReader leitura = new BufferedReader(arquivo);

            String linha = leitura.readLine();
            String[] campos = null;

            while(linha != null){
                campos = linha.split(",");

                Usuario u = new Usuario();
                u.nome = campos[0];
                u.email = campos[1];
                u.setTipo(campos[3]);
                
                service.incluirUser(u);
                linha = leitura.readLine();

            }
            for(Usuario u : service.obterUsers()){
                System.out.println(u);
                System.out.println("-----------------------------------------------------------------");
            }
            leitura.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");
            e.printStackTrace();
        }catch (IOException e){
            System.out.println("Imporssível abrir/fechar o arquivo");
            e.printStackTrace();
        }
        System.out.println("\n--- FIM DO TESTE DE USUARIOS ---");
	}
}