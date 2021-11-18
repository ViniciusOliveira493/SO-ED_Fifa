package view;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import controller.FifaController;

public interface Principal {
	public static void main(String[] args) {
		FifaController fifaCtrl = new FifaController();
		Stack<String> jogadoresBrasileiros = new Stack<String>();
		List<String> jogadoresJovens = new LinkedList<String>();
		Scanner in = new Scanner(System.in);
		while(true) {
			System.out.println("Escolha uma opção\n\r"
								+ "1 - Empilhar Brasileiros\r"
								+ "2 - Desempilhar bons brasileiros\r"
								+ "3 - Listar revelações\r"
								+ "4 - Buscar bons jovens\r");
			int x = in.nextInt();
			switch (x) {
			case 1:
				try {
					jogadoresBrasileiros = fifaCtrl.empilhaBrasileiros("C:\\TEMP", "data.csv");
					System.out.println("Os jogadores brasileiros foram empilhados");
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				if(jogadoresBrasileiros.size() != 0) {
					try {
						fifaCtrl.desempilhaBonsBrasileiros(jogadoresBrasileiros);
					} catch (IOException e) {
						e.printStackTrace();
					}	
					System.out.println();
				}else {
					System.out.println("É necessário criar uma lista para usar essa função\n\r");
				}
				break;
			case 3:
				try {
					jogadoresJovens = fifaCtrl.listaRevelacoes("C:\\TEMP", "data.csv");
					System.out.println("Os jogadores jovens foram empilhados");
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 4:
				if(jogadoresJovens.size()!=0) {
					try {
						fifaCtrl.buscaListaBonsJovens(jogadoresJovens);
						System.out.println();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else{
					System.out.println("É necessário criar uma lista para usar essa função\n\r");
				}
				break;
			default:
				break;
			}
		}		
		
	}
}
