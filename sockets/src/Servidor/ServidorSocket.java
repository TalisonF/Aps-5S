package Servidor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class ServidorSocket {

	public static void main(String[] args) {
		ServerSocket servidor = null;
		
		 
		try {
			System.out.println("Iniciando o servidor: ");
			servidor = new ServerSocket(8000);
			System.out.println("Servidor iniciado com sucesso!");
			
			while(true) {
				
				Socket cliente = servidor.accept();
				
				new GerenciadordeClientes(cliente);
				
			}
			
		} catch (IOException e) {
			try {
				if(servidor != null) {
					servidor.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
			System.err.println("Falha ao iniciar o servidor, a porta esta oculpada!");
			e.printStackTrace();
		}

	}

}
