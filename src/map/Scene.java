package map;

import java.util.ArrayList;
import java.util.List;

import pathfinder.PathFinder;

/**
 * <p>Representa o mapa a ser simulado o algorítmo de busca.</p>
 * 
 * @author Dr.XGB
 * @version 1.0
 */
public class Scene {

	/*
	 * ===========================================================
	 * 				*** ATRIBUTOS ***
	 * ===========================================================
	 */
	
	private int width;
	private int height;
	
	/*
	 * ===========================================================
	 * 				*** ASSOCIAÇÕES ***
	 * ===========================================================
	 */
	
	private List<Node> nodes = new ArrayList<>();
	
	/*
	 * ===========================================================
	 * 				*** CONSTRUTORES ***
	 * ===========================================================
	 */
	
	public Scene(int width, int height) {
		this.width = width;
		this.height = height;
		this.createNodes();
	}
	
	/*
	 * ===========================================================
	 * 				*** MÉTODOS PÚBLICOS ***
	 * ===========================================================
	 */
	
	/**
	 * <p>Organiza todos os nós do mapa, atribuindo suas
	 * posições de grade para cada um deles de forma ordenada
	 * numericamente.</p>
	 */
	private void createNodes() {
		for (int i = 0; i < (width * height); i++) {
			nodes.add(new Node(i));
		}
	}
	
	/**
	 * Desenha o mapa incluindo o ponto de origem, destino, obstáculos
	 * e a rota projetada (caso tenha sucedido).
	 * @return Um texto representando o mapa para ser desenhado no console.
	 */
	public String draw() {
		PathFinder finder = PathFinder.getInstance();
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < nodes.size(); i++) {
			Node n = nodes.get(i);
			char c = ' ';										// Tile passível
			
			if (n.isBlocked()) 						c = 'X'; 	// Tile não passível
			if (n.equals(finder.getSource())) 		c = 'S';	// Origem
			if (n.equals(finder.getDestination())) 	c = 'D';	// Destino	
			if (n.isPromising())					c = '.';	// Tile promissor
			
			// Escreve a célula na string
			sb.append(c + " ");
			
			// Quebra linha quando chegar no comprimento do mapa
			if ((i % width) == (width - 1)) {
				sb.append('\n');
			}			
		}
		
		return sb.toString();
	}
	
	/**
	 * <p>Conta a quantidade de blocos existentes no mapa.</p>
	 * <p>Os blocos não são considerados pontos passíveis do mapa,
	 * portanto ele deve ser subtraído no momento que calcular a
	 * rota em relação ao tamanho total da grade.</p>
	 * @return A quantidade de blocos no mapa.
	 */
	public int countBlocks() {
		int count = 0;
		for (Node n : nodes) {
			if (n.isBlocked()) count++;
		}
		return count;
	}
	
	/*
	 * ===========================================================
	 * 				*** GETTERS ***
	 * ===========================================================
	 */

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public List<Node> getNodes() {
		return nodes;
	}	
	
}
