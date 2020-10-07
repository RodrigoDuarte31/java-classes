package atividade2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	
	public static void main(String[] args) {
		
		Socket socketCliente;
		ObjectOutputStream out;
		ObjectInputStream in;
		Scanner scan;
		
		System.out.println("----------- Cliente Calculadora -------------\n");
		try {
			socketCliente = new Socket("localhost", 7080);
			
			out = new ObjectOutputStream(socketCliente.getOutputStream());
			in = new ObjectInputStream(socketCliente.getInputStream());
			scan = new Scanner(System.in);
			
			int[] valores = new int[2];
			String operador = "";
				
			System.out.println("Informe a operação a ser realizada (+, -, *, /): ");
			operador = scan.nextLine();
			
			out.writeUTF(operador);
			out.flush();
			
			if(operador.equals("+") || operador.equals("-") || operador.equals("*") || operador.equals("/")) {
				
				System.out.println("Informe o primeiro valor inteiro: ");
				valores[0] = scan.nextInt();
				System.out.println("Informe o segundo valor inteiro: ");
				valores[1] = scan.nextInt();
		
				out.writeObject(valores);
				out.flush();
					
				if(operador.equals("+")) {
					System.out.println("\nSoma: " + in.readInt());
				} else if(operador.equals("-")) {
					System.out.println("\nDiferença: " + in.readInt());
				} else if(operador.equals("*")) {
					System.out.println("\nProduto: " + in.readInt());
				} else if(operador.equals("/")) {
					System.out.println("\nRazão: " + in.readInt());
				}
			} else {
				System.out.println("Operador inválido!");
			}
				
			System.out.println("\nConexão encerrada com o servidor!");
			
			scan.close();
			out.close();
			in.close();
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
