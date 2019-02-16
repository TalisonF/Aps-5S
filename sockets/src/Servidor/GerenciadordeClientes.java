package Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import Model.UsuarioDao;

public class GerenciadordeClientes extends Thread {

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
	public void run() {
		try {
			leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			escritor = new PrintWriter(cliente.getOutputStream(), true);

			boolean logado = false;

			boolean UsuarioInseridoNoArray = true;

			while (true) {
				String msg = leitor.readLine();
				JSONObject recebi = new JSONObject(msg);

				if (recebi.getString("função").equals("logar")) {
					if (!logado) {

						String login = recebi.get("usuario").toString();
						String senha = recebi.get("senha").toString();

						UsuarioDao newUser = new UsuarioDao();
						newUser.setLogin(login);
						newUser.setSenha(senha);

						if (newUser.validar()) {
							if (UsuarioInseridoNoArray) {
								this.nomeCliente = login;
								clientes.put(this.nomeCliente, this);
								UsuarioInseridoNoArray = false;
								escritor.println("Bem vindo " + this.nomeCliente);
								escritor.println("Para enviar mensagem basta digitar: \nEnviar");
							}
							logado = true;
						} else {
							escritor.println("Senha ou usuario incorretos !!");
						}
					} else {
						escritor.println("Usuario já esta logado");
					}
					continue;
				}
				if (recebi.getString("função").equals("Sair")) {
					System.out.println("O cliente " + this.nomeCliente + " saiu!");
					this.cliente.close();
				} else if (recebi.getString("função").equals("cadastrar")) {

					String nome = recebi.getString("nome");
					String login = recebi.getString("login");
					String senha = recebi.getString("senha");

					UsuarioDao newUser = new UsuarioDao();
					newUser.setNome(nome);
					newUser.setLogin(login);
					newUser.setSenha(senha);

					newUser.salvar();

				} else if (logado) {
					if (recebi.get("função").toString().equals("MensagemFor")) {
						String nomeDestinatario = recebi.getString("destinatario");
						GerenciadordeClientes destinatario = clientes.get(nomeDestinatario);
						if (destinatario == null) {
							escritor.println("O cliente informado não existe");
						} else {
							escritor.println("Digite a mensagem a ser enviada para " + nomeDestinatario + ": ");
							msg = recebi.getString("mensagem");
							destinatario.getEscritor().println(this.nomeCliente + " disse: " + msg);
						}
						continue;
					} else {
						escritor.println(this.nomeCliente + " Você disse: " + recebi.getString("mensagem"));
						continue;
					}
				} else {
					escritor.println("Usuario não esta logado");
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
