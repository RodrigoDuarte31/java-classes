package atividade1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) {
		
		ServerSocket server;
		Socket socketServer;
		DataInputStream in;
		DataOutputStream out;
		
		System.out.println("\n---------- Calculadora de Área da Circunferência ----------\n");
		try {
			server = new ServerSocket(7080);
			
			socketServer = server.accept();
			
			System.out.println("Conexão recebida de: " + socketServer.getInetAddress().getHostName());
			
			in = new DataInputStream(socketServer.getInputStream());
			out = new DataOutputStream(socketServer.getOutputStream());
			
			double raio = in.readDouble();
			System.out.println("Raio recebido do cliente: " + raio);
			
			double areaCirculo = Math.PI * Math.pow(raio, 2);
			
			System.out.println("\nEnviando resposta ao cliente...\n");
			
			out.writeDouble(areaCirculo);
			
			System.out.println("------------------------------------------------");
			
			socketServer.close();
			server.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
