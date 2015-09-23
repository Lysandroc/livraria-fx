package io;

import java.util.Scanner;

public class TesteEntrada {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		try {
////		InputStream is = new FileInputStream("teste.txt");
//			InputStream is = System.in;
//			InputStreamReader isr = new InputStreamReader(is);
//			BufferedReader reader = new BufferedReader(isr);
//			String linha = reader.readLine();
//			while(linha!=null) {
//				System.out.println(linha);
//				linha = reader.readLine();
//			}
//		} catch (IOException e) {
//			System.out.println(e);
//		} 
//			reader.close();
//			Scanner sc = new Scanner(new File("Teste.txt"));
//			while(sc.hasNextLine()) {
//				System.out.println(sc.nextLine());
//			}
//					
			Scanner sc = new Scanner(System.in);
			System.out.println("Digite seu nome: ");
			String nome = sc.nextLine();
			System.out.println("Digite sua idade: ");
			int idade = sc.nextInt();
			System.out.println("Nome: "+ nome);
			System.out.println("Idade: "+ idade);
			
	}

}
