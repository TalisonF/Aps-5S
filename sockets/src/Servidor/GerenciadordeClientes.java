package Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import org.json.*;

import com.mysql.cj.xdevapi.JsonArray;




public class GerenciadordeClientes extends Thread{
	
	private Socket cliente;
	private String nomeCliente;
	private static final Map<String, GerenciadordeClientes> clientes = new HashMap<String, GerenciadordeClientes>(); 
	private BufferedReader leitor;
	private PrintWriter escritor;
	private JSONObject recebi;
	
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
			recebi = new JSONObject(msg);
			escritor.println("O servidor disse: " +"Olá " + recebi.getString("mensagem"));
			this.nomeCliente = recebi.getString("mensagem");
			
			clientes.put(this.nomeCliente, this);
			
			while(true) {
				msg = leitor.readLine();
				
				JSONObject recebi = new JSONObject(msg);
				
				if(recebi.getString("função").equals("logar")) {
					
					String nome = recebi.get("usuario").toString(); 
					String senha = recebi.get("senha").toString(); 
					
					System.out.println("Usuario : " + nome + " ; senha: " + senha);
					
					continue;
				
				}
				if(recebi.getString("função").equals("Sair")) {
					System.out.println("O cliente " + this.nomeCliente + " saiu!");
					this.cliente.close();
				}else if( recebi.get("função").toString().equals("MensagemFor")){
					String nomeDestinatario = recebi.getString("destinatario");
					GerenciadordeClientes destinatario =  clientes.get(nomeDestinatario);
					if(destinatario == null) {
						escritor.println("O cliente informado não existe");
					}else {
						escritor.println("Digite a mensagem a ser enviada para " + nomeDestinatario + ": ");
						msg = recebi.getString("mensagem");
						destinatario.getEscritor().println(this.nomeCliente + " disse: " + msg);
					}
					
					continue;
				} else {
					escritor.println(this.nomeCliente  + " Você disse: " + recebi.getString("mensagem"));
					continue;
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
