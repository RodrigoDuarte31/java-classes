package atividade2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Calculadora {

	public static void main(String[] args) {
		
		ServerSocket server;
		Socket socketServer;
		ObjectOutputStream out;
		ObjectInputStream in;
		
		System.out.println("\n------------ Servidor Calculadora -----------\n");
		try {
			server = new ServerSocket(7080);
			
			String operador = "";
				
			System.out.println("Aguardando requisi��o do cliente...\n");
			
			socketServer = server.accept();
						
			System.out.println("Conex�o estabelecida com: " + socketServer.getInetAddress().getHostName());
			
			out = new ObjectOutputStream(socketServer.getOutputStream());
			in = new ObjectInputStream(socketServer.getInputStream());
			
			operador = in.readUTF();
					
			if(operador.equals("+") || operador.equals("-") || operador.equals("*") || operador.equals("/")) {
				
				try {
					int[] valores = (int[]) in.readObject();
								
					if(operador.equals("+")) {
						int soma = valores[0] + valores[1];
						out.writeInt(soma);
						out.flush();
					} else if(operador.equals("-")) {
						int diferenca = valores[0] - valores[1];
						out.writeInt(diferenca);
						out.flush();
					} else if(operador.equals("*")) {
						int produto = valores[0] * valores[1];
						out.writeInt(produto);
						out.flush();
					} else if(operador.equals("/")) {
						int razao = valores[0] / valores[1];
						out.writeInt(razao);
						out.flush();
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			} else {
				System.out.println("\nN�o foi poss�vel identificar a opera��o");
			}
			
			System.out.println("\nConex�o encerrada com o cliente!");
			
			out.close();
			in.close();
			socketServer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
