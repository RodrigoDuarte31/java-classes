package atividade4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	
	public static void main(String[] args) {
		
		Socket socketCliente;
		BufferedReader in;
		PrintWriter out;
		Scanner scan;
		
		System.out.println("------ CLIENTE ------\n");
		try {
			socketCliente = new Socket("localhost", 7080);
			
			in = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
			out = new PrintWriter(socketCliente.getOutputStream());
			scan = new Scanner(System.in);
			
			System.out.println("Digite 'contar' para contar as portas abertas de 100 a 200: ");
			String operacao = scan.nextLine();
			
			if(operacao.equals("contar")) {
			
				System.out.println("\nEnviando requisição ao servidor...");
				
				out.println(operacao);
				out.flush();
				
				String portasAbertas = in.readLine();
				System.out.println("\nQuantidade total de portas abertas no intervalo de 100 a 200: " + portasAbertas);
			
			} else {
				System.out.println("\nOperação inválida! Encerrando...");
			}
			
			scan.close();
			in.close();
			out.close();
			socketCliente.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
