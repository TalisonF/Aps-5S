package Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;




public class GerenciadordeClientes extends Thread{
	
	private Socket cliente;
	private String nomeCliente;
	private static final Map<String, GerenciadordeClientes> clientes = new HashMap<String, GerenciadordeClientes>(); 
	private BufferedReader leitor;
	private PrintWriter escritor;
	
	public GerenciadordeClientes(Socket cliente) {
		this.cliente = cliente;
		start();
		
	}
	
	@Override
	public void run(){
		try {
			leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			escritor= new PrintWriter(cliente.getOutputStream(), true);
			escritor.println( "O servidor disse: " +"Insira seu nome: ");
			String msg = leitor.readLine();
			escritor.println("O servidor disse: " +"Olá " + msg);
			this.nomeCliente = msg;
			
			clientes.put(this.nomeCliente, this);
			
			while(true) {
				msg = leitor.readLine();
				if(msg.equalsIgnoreCase("Sair")) {
					System.out.println("O cliente " + this.nomeCliente + " saiu!");
					this.cliente.close();
				}else if(msg.startsWith("Msg:")){
					String nomeDestinatario = msg.substring(4, msg.length());
					GerenciadordeClientes destinatario =  clientes.get(nomeDestinatario);
					if(destinatario == null) {
						escritor.println("O cliente informado não existe");
					}else {
						escritor.println("Digite a mensagem a ser enviada para " + nomeDestinatario + ": ");
						msg = leitor.readLine();
						destinatario.getEscritor().println(this.nomeCliente + " disse: " + msg);
					}
					
				} else {
					escritor.println(this.nomeCliente  + " Você disse: " + msg);
				}
			}
			
			
		} catch (IOException e) {
			System.out.println("O cliente fechou a conexão");
		}
	}
	
	public PrintWriter getEscritor() {
		return escritor;
	}
	
	
}
