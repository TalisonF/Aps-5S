package Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteSocket {
	public static void main(String[] args) {
		try {
			final Socket cliente =  new Socket("10.0.0.6", 8000);
			
			new Thread() {
				@Override
				public void run() {
					try {
						BufferedReader leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
						while(true) {
						
							String mensagem =  leitor.readLine();
							System.out.println(mensagem);
						}
						
					} catch (IOException e) {
						System.err.println("Não foi possivel ler o servidor");
						e.printStackTrace();
					}
				}
			}.start();
			//Escrevendo para o servidor;
			PrintWriter escritor= new PrintWriter(cliente.getOutputStream(), true);
			BufferedReader leitorTerminal = new BufferedReader(new InputStreamReader(System.in));
			String mensagem = "";
			while(true) {
				mensagem = leitorTerminal.readLine();
				if((mensagem == null || mensagem.length() == 0)) {
					continue;
				}
				escritor.println(mensagem);
				if(mensagem.equalsIgnoreCase("SAIR")) {
					System.exit(0);
				}
			}
			
		} catch (UnknownHostException e) {
			System.err.println("O endereço passado e invalido");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("O servidor pode estar fora do ar");
			e.printStackTrace();
		}
	}
}
