package atividade4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	public static void main(String[] args) {
		
		ServerSocket server;
		Socket socketServer;
		BufferedReader in;
		PrintWriter out;
		
		System.out.println("------ SERVIDOR ------\n");
		try {
			server = new ServerSocket(7080);
			
			System.out.println("Aguardando conexão...\n");
			socketServer = server.accept();
			System.out.println("Conexão estabelecida com: " + socketServer.getInetAddress().getHostName());
				
			in = new BufferedReader(new InputStreamReader(socketServer.getInputStream()));
			out = new PrintWriter(socketServer.getOutputStream());
				
			String requisicao = in.readLine();
				
			if(requisicao != null && requisicao.equals("contar")) {
				int porta;
				int portasAbertas = 0;
				int tempo = 200;
				String ip = socketServer.getInetAddress().getHostName();
					
				System.out.println("\nVerificando as portas abertas...");
					
				for(porta = 100; porta <= 200; porta++) {
					if(verificaPortaAberta(porta, tempo, ip)) {
						portasAbertas++;
						System.out.println("Porta " + porta + " está aberta");
					}
				}
					
				String resultado = String.valueOf(portasAbertas);
					
				System.out.println("\nEnviando resposta ao cliente...");
				out.println(resultado);
				out.flush();
					
				System.out.println("\nEncerrando...");
			} else {
				System.out.println("\nCliente enviou requisição não suportada. Encerrando...");
			}
				
			in.close();
			out.close();
			socketServer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean verificaPortaAberta(int porta, int tempo, String ip) {
		try {
			Socket socketTeste = new Socket();
			socketTeste.connect(new InetSocketAddress(ip, porta), tempo);
			socketTeste.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

}
