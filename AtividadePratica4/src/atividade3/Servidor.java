package atividade3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Servidor {
	
	public static void main(String[] args) {
		
		ServerSocket server;
		Socket socketServer;
		BufferedReader in;
		PrintWriter out;
		
		System.out.println("------ SERVIDOR DE HORAS -------\n");
		try {
			server = new ServerSocket(6543);
				
			System.out.println("Aguardando requisição do cliente...\n");
			socketServer = server.accept();
				
			System.out.println("Conexão estabelecida com: " + socketServer.getInetAddress().getHostName());
				
			in = new BufferedReader(new InputStreamReader(socketServer.getInputStream()));
			out = new PrintWriter(socketServer.getOutputStream());
				
			String requisicao = in.readLine();
				
			if(requisicao.equals("horas")) {
				Date dataAtual = new Date();
				DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
				String dataFormatada = dateFormat.format(dataAtual);
					
				System.out.println("\nRequisição aceita!\n");
				System.out.println("Enviando dados...\n");
					
				out.println(dataFormatada);
				out.flush();
				
				System.out.println("Resposta enviada ao cliente!\n");
			} else {
				System.out.println("\nEncerrando...");
				in.close();
				out.close();
				socketServer.close();
			}
				
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
