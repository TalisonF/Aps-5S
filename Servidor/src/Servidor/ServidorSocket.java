package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import View.View;


public class ServidorSocket extends Thread {

	public ServidorSocket() {
		start();
	}
	
	@Override
	public void run() {
		ServerSocket servidor = null;
		try {
			servidor = new ServerSocket(8000);
			View.AddLog("Servidor iniciado com sucesso!!\n");
			
			while (true) {	
				Socket cliente = servidor.accept();
				new GerenciadordeClientes(cliente);
			}

		} catch (IOException e) {
			try {
				if (servidor != null) {
					servidor.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			System.err.println("Falha ao iniciar o servidor, a porta esta oculpada!");
			View.AddLog("Falha ao iniciar o servidor, a porta esta oculpada!\n");
			e.printStackTrace();
		}

	}

}
