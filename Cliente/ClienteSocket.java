
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import org.json.*;

public class ClienteSocket {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		BufferedReader leitorTerminal = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Digite o Ip do servidor. Ex: 127.0.0.1");
			String ipServidor = leitorTerminal.readLine();
			
			final Socket cliente =  new Socket(ipServidor, 8000);
			
			System.out.println("Opções: \n logar \n cadastrar  \n sair ");
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
			
			String mensagem = "";
			while(true) {
				JSONObject enviar = null;
				
				mensagem = leitorTerminal.readLine();
				if((mensagem == null || mensagem.length() == 0)) {
					continue;
				}
				
				if(mensagem.equalsIgnoreCase("logar")){
					String nome;
					String senha;
					
					System.out.println("Entre com o usuario");
					nome = leitorTerminal.readLine();
					System.out.println("Entre com a senha");
					senha = leitorTerminal.readLine();
				
					enviar = new JSONObject();
					
					enviar.put("função", "logar");
					enviar.put("usuario", nome);
					enviar.put("senha", senha);
					
					String send = enviar.toString();
					
					escritor.println(send);
					continue;
				}else if((mensagem.equalsIgnoreCase("cadastrar"))) {
					String nome;
					String login;
					String senha;
					
					System.out.println("Entre com o login");
					login = leitorTerminal.readLine();
					System.out.println("Entre com seu nome");
					nome = leitorTerminal.readLine();
					System.out.println("Entre com a senha");
					senha = leitorTerminal.readLine();
				
					enviar = new JSONObject();
					
					enviar.put("função", "cadastrar");
					enviar.put("nome", nome);
					enviar.put("login", login);
					enviar.put("senha", senha);
					
					String send = enviar.toString();
					
					escritor.println(send);
					continue;
					
				}else if((mensagem.equalsIgnoreCase("SAIR"))) {
					enviar = new JSONObject();
					enviar.put("função", "Sair");
					escritor.println(enviar.toString());					
					System.exit(0);
					continue;
				}else if(mensagem.equals("Enviar")) {
					String destinatario;
					String msg;
					
					System.out.println("quem será o destinario: ");
					destinatario = leitorTerminal.readLine();
					System.out.println("Escreva a mensagem");
					msg = leitorTerminal.readLine();
				
					enviar = new JSONObject();
					
					enviar.put("função", "MensagemFor");
					enviar.put("destinatario", destinatario);
					enviar.put("mensagem", msg);
					
					String send = enviar.toString();
					
					escritor.println(send);
					continue;
				}else {
					enviar = new JSONObject();
					enviar.put("função", "mensagem");
					enviar.put("mensagem", mensagem);
					escritor.println(enviar.toString());
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
