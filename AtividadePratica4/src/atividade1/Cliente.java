package atividade1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	
	public static void main(String[] args) {
		
		Socket socketCliente;
		Scanner scan;
		DataOutputStream out;
		DataInputStream in;
		
		System.out.println("\n-------- CLIENTE --------\n");
		try {
			socketCliente = new Socket("localhost", 7080);
			
			out = new DataOutputStream(socketCliente.getOutputStream());
			in = new DataInputStream(socketCliente.getInputStream());
			
			System.out.println("Envie o valor do raio da circunferência para o servidor: ");
			scan = new Scanner(System.in);
			double raio = scan.nextDouble();
			
			out.writeDouble(raio);
			
			double areaCirculo = in.readDouble();
			
			System.out.println("Área do círculo calculada pelo servidor: " + areaCirculo);
			
			System.out.println("\n--------------------------------------");
			
			socketCliente.close();
			scan.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
