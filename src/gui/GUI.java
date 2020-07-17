package gui;

import java.io.IOException;
import java.util.Scanner;

import map.Node;
import map.Scene;
import pathfinder.PathFinder;
import util.Utils;

/**
 * Classe que tratar� da interface "gr�fica" do programa em console.
 * 
 * @author Dr.XGB
 * @version 1.0
 */
public abstract class GUI {

	/**
	 * Desenha o mapa na tela.
	 * @param map A inst�ncia de {@code Scene} representando o mapa.
	 */
	public static void drawMap(Scene map) {		
		// Cabe�alho da tabela
		System.out.println();
		fill(" ", (String.valueOf(map.getHeight()).length() + 1));
		System.out.print("| ");
		drawColumnHeader(map.getWidth());
		
		// Desenhar o mapa dentro da tabela
		drawLines(map);
		System.out.println();
	}
	
	/**
	 * Desenha o cabe�alho da coluna.
	 * @param width Comprimento do mapa.
	 */
	private static void drawColumnHeader(int width) {
		// Nome das colunas
		for (int i = 0; i < width; i++) {
			char col = (char) ((char) i + 65);
			System.out.print(col + " ");
		}		
		breakRow(width);
	}
	
	/**
	 * Desenha cada linha do mapa de acordo com sua altura.
	 * @param map A inst�ncia de {@code Scene} representando o mapa.
	 */
	private static void drawLines(Scene map) {
		String[] lines = map.draw().split("\n");
		for (int i = 0; i < map.getHeight(); i++) {
			System.out.printf("%2d", i);
			System.out.print("| ");
			System.out.print(lines[i]);					
			System.out.println();
		}
	}
	
	/**
	 * Faz a quebra de linha, desenhando uma linha horizontal
	 * de acordo com o comprimento dado por argumento.
	 * @param width O comprimento da linha.
	 */
	private static void breakRow(int width) {
		System.out.println();
		for (int i = 0; i < (width * 2 + 4); i++) {
			System.out.print('-');
		}
		System.out.println();
	}
	
	/**
	 * Preenche um conjunto de caracteres em uma determinada
	 * quantidade de vezes dadas por argumento.
	 * @param str A string a ser repetida.
	 * @param amount Quantidade de vezes na qual a string se repetir�.
	 */
	private static void fill(String str, int amount) {
		do System.out.print(str); while (--amount > 0);
	}
	
	/**
	 * <p>Mostra uma solicita��o de entrada de dados para
	 * escolher um determinado n�.</p>
	 * <p>O usu�rio deve digitar a posi��o do mapa, sendo que
	 * as colunas s�o de A at� Z e as linhas um valor num�rico.</p>
	 * <p>Exemplo: A0, B1, E4, C6, H13...</p>
	 * <p>E ent�o o sistema detectar� essas posi��es e retornar�
	 * um n� caso essa posi��o exista.</p>
	 * @param sc Inst�ncia de {@code Scanner}
	 * @return O n� encontrado na posi�ao solicitada.
	 */
	public static Node choosePosition(Scanner sc) {
		System.out.print(" => ");
		
		String input = sc.nextLine();
		if (!input.matches("[A-Z][0-9]*")) {
			throw new IllegalArgumentException("Posi��o inv�lida!");
		}
		
		int col = input.charAt(0) - 'A';
		int lin = Integer.parseInt(input.substring(1));
		
		Scene map = PathFinder.getInstance().getMap();
		if (col < 0 || col >= map.getWidth() || lin < 0 || lin >= map.getHeight()) {
			throw new IllegalArgumentException("Posi��o fora do mapa!");
		}		
		
		int index = Utils.getGridPosition(col, lin, map.getWidth());
		Node target = map.getNodes().get(index);
		if (target.isBlocked())
			throw new IllegalArgumentException("N�o pode posicionar um ponto em um bloco.");
		return target;
	}
	
	/**
	 * Apaga todo o conte�do da tela. Normalmente utilizado para
	 * atualizar a tela do jogo.
	 */
	public static void clearScreen() {
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}
	
}
