package atividade3;

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
		
		System.out.println("------ CLIENTE HORAS ------\n");
		try {
			socketCliente = new Socket("localhost", 6543);
			
			in = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
			out = new PrintWriter(socketCliente.getOutputStream());
			scan = new Scanner(System.in);
			
			System.out.println("Digite 'horas' para receber o horário exato do servidor: ");
			String requisicao = scan.nextLine();
			
			if(requisicao.equals("horas")) {
				System.out.println("\nEnviando requisição para o servidor...");
				out.println(requisicao);
				out.flush();
				
				System.out.println("Recebendo resposta do servidor...\n");
				System.out.println("Hora exata: " + in.readLine());
			} else {
				out.println("sair");
				out.flush();
				System.out.println("Operação inválida!");
			}
			
			in.close();
			out.close();
			scan.close();
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
