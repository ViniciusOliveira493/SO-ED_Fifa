package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class FifaController implements IFifaController{

	@Override
	public Stack<String> empilhaBrasileiros(String caminho, String nome) throws IOException {
		Stack pilhaBrasileiros = new Stack<String>();
		File diretorio = new File(caminho);		
		if(diretorio.exists() && diretorio.isDirectory()) {
			File arquivo = new File(caminho,nome);
			if(arquivo.exists() && arquivo.isFile()) {
				BufferedReader buffer = createBuffer(arquivo);
				String linha = buffer.readLine();
				while (linha != null) {
					if(linha.contains("Brazil")) {
						String jogador[] = obterJogador(linha);
						if(jogador[5].equals("Brazil")) {
							pilhaBrasileiros.push(linha);
						}
					}
					linha = buffer.readLine();
				}
			}else {
				throw new IOException("O arquivo não existe");
			}
		}else {
			throw new IOException("O diretório não existe");
		}
		return pilhaBrasileiros;
	}

	@Override
	public void desempilhaBonsBrasileiros(Stack<String> pilha) throws IOException {
		String linha = pilha.pop();
		while(linha != null) {
			String jogador[] = linha.split(",");
			int overall = Integer.parseInt(jogador[7]);
			if(overall>80) {
				System.out.println(jogador[2]+" | "+jogador[7]);
			}
			if(!pilha.isEmpty()) {
				linha = pilha.pop();
			}else {
				linha = null;
			}			
		}
	}

	@Override
	public List<String> listaRevelacoes(String caminho, String nome) throws IOException {
		List<String> jogadoresJovens = new LinkedList<String>();
		File diretorio = new File(caminho);
		if(diretorio.exists() && diretorio.isDirectory()) {
			File arquivo = new File(caminho,nome);
			if(arquivo.exists() && arquivo.isFile()) {
				BufferedReader buffer = createBuffer(arquivo);
				String linha = buffer.readLine();
				linha = buffer.readLine();
				while (linha != null) {
					String jogador[] = obterJogador(linha);
					int idade = Integer.parseInt(jogador[3]);
					if(idade < 21) {
						jogadoresJovens.add(linha);
					}
					linha = buffer.readLine();
				}
			}else {
				throw new IOException("O arquivo não existe");
			}
		}else {
			throw new IOException("O diretório não existe");
		}
		return jogadoresJovens;
	}

	public void buscaListaBonsJovens(List<String> lista) throws IOException {
		int tamanhoLista= lista.size();
		for (int i = tamanhoLista-1; i > -1; i--) {
			String[] jogador = obterJogador(lista.get(i));
			
			if(jogadorBom(jogador)){
				System.out.println(jogador[2]+"|"+jogador[3]+"|"+jogador[7]);
			}
		}
	}

	private boolean jogadorBom(String[] jogador) {
		int overall = Integer.parseInt(jogador[7]);
		return (overall > 80);
	}	
	private String[] obterJogador(String linha) {
		return linha.split(",");
	}	
	private BufferedReader createBuffer(File arquivo) throws FileNotFoundException {
		FileInputStream fis = new FileInputStream(arquivo);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		
		return br;
	}
}
