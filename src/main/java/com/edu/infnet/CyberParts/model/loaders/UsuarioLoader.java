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

import com.edu.infnet.CyberParts.model.domain.Usuario;
import com.edu.infnet.CyberParts.model.service.UsuarioService;


@Component
@Order(2)
public class UsuarioLoader implements ApplicationRunner{
	
	@Autowired
	private UsuarioService service;

	@Override
	public void run(ApplicationArguments args) throws Exception {
        System.out.println("\n--- INICIANDO CARREGAMENTO DE USUARIOS ---");
        try{
            FileReader arquivo = new FileReader("files/usuarios.csv");
            BufferedReader leitura = new BufferedReader(arquivo);

            String linha = leitura.readLine();
            String[] campos = null;

            while(linha != null){
                campos = linha.split(",");

                Usuario u = new Usuario();
                u.setNome(campos[0]);
                u.setEmail(campos[1]);
                u.setTipo(campos[3]);
                
                service.incluirUsuario(u);
                linha = leitura.readLine();
            }
            Iterable<Usuario> usuariosCarregados = service.obterUsers();
            long totalUsuarios = 0;
            
            for(Usuario u : usuariosCarregados){
                System.out.println(u);
                System.out.println("-----------------------------------------------------------------");
                totalUsuarios++;
            }
            System.out.println("Total de usuários carregados: " + totalUsuarios);
            leitura.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de usuários (usuarios.csv) não encontrado!");
            e.printStackTrace();
        }catch (IOException e){
            System.out.println("Impossível abrir/fechar o arquivo de usuários.");
            e.printStackTrace();
        }
        System.out.println("\n--- FIM DO CARREGAMENTO DE USUARIOS ---");
	}
}