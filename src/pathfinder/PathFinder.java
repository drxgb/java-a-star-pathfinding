package pathfinder;

import java.util.PriorityQueue;
import java.util.Queue;

import map.Node;
import map.Scene;
import util.Direction;
import util.Utils;

/**
 * Classe responsável por realizar o Algorítmo de
 * Busca.
 * 
 * @author Dr.XGB
 * @version 1.0
 */
public class PathFinder {
	
	/*
	 * ===========================================================
	 * 				*** ATRIBUTOS ***
	 * ===========================================================
	 */
	
	private static PathFinder self = new PathFinder();
	private Node source;
	private Node destination;
	private Queue<Node> openList = new PriorityQueue<>();
	
	/*
	 * ===========================================================
	 * 				*** ASSOCIAÇÕES ***
	 * ===========================================================
	 */
	
	private Scene map;
	
	/*
	 * ===========================================================
	 * 				*** CONSTRUTORES ***
	 * ===========================================================
	 */
	
	private PathFinder() {}
	
	/*
	 * ===========================================================
	 * 				*** MÉTODOS PÚBLICOS ***
	 * ===========================================================
	 */
	
	/**
	 * Recebe a instância única desta classe.
	 * @return Uma instância de {@code PathFinder}.
	 */
	public static PathFinder getInstance() {
		return self;
	}

	/**
	 * Constrói o mapa que deve ser trabalhado o algoritmo.
	 * @param w Comprimento do mapa.
	 * @param h Altura do mapa.
	 */
	public void buildMap(int w, int h) {
		map = new Scene(w, h);
	}
	
	/**
	 * Fará a busca do caminho mais curto possível para se fazer do
	 * ponto de origem até o ponto destino, detectando obstáculos e
	 * criando desvios até o caminho desejado.
	 */
	public void seek() {
		Node node = source;
		node.setGrid(0);
		node.setHeuristic(0);
		openList.offer(node);
		do {
			pushChildren(node);
			node.setVisited(true);
			openList.poll();
			node = openList.peek();
			
			// Quebrar laço se o destino foi encontrado
			if (node != null && node.equals(destination)) {
				this.tracePath();
				break;
			}
		} while (node != null);
	}
	
	/**
	 * <p>Fará a rota de caminho caso tenha conseguido
	 * encontrar o destino.</p>
	 * <p>Dessa forma, o sistema deve recuperar todos os nós
	 * pai e determinar que aqueles nós são promissores, ou seja,
	 * é o caminho mais curto possível para chegar até o destino.
	 * Este processo é feito até que chegue ao ponto de origem,
	 * tentdo então, a rota projetada com sucesso.</p>
	 */
	public void tracePath() {
		Node node = destination.getParent();
		while (!node.equals(source)) {
			node.setPromising(true);
			node = node.getParent();
		}
	}
	
	/*
	 * ===========================================================
	 * 				*** MÉTODOS PRIVADOS ***
	 * ===========================================================
	 */
	
	/**
	 * <p>Calcula a distância de Manhattan, cuja fórmula é:</p>
	 * <p>{@code d = abs(dx - cx) + abs(dy - cy)}</p>
	 * <p>Sendo:<br>
	 * d -> distância<br>
	 * dx -> posição x do destino<br>
	 * dy -> posição y do destino<br>
	 * cx -> posição x do nó atual<br>
	 * cy -> posição y do nó atual<br>
	 * abs -> valor absoluto</p>
	 * @param current O nó atual
	 * @param target O nó destino
	 * @return A distância em tiles entre o nó atual e o destino,
	 * juntando a distância horizontal e vertical.
	 */
	private int getManhattanDistance(Node current, Node target) {
		int width = map.getWidth();
		int xDist = 
				Utils.getColumnFromGrid(map.getNodes().indexOf(target), width) - 
				Utils.getColumnFromGrid(map.getNodes().indexOf(current), width);
		int yDist = 
				Utils.getLineFromGrid(map.getNodes().indexOf(target), width) - 
				Utils.getLineFromGrid(map.getNodes().indexOf(current), width);
		return Math.abs(xDist) + Math.abs(yDist);
	}
	
	/**
	 * Adiciona todos os nós filhos para a fila prioritária.
	 * @param node O nó pai.
	 */
	private void pushChildren(Node node) {
		pushNeighbor(node, Direction.UP);
		pushNeighbor(node, Direction.RIGHT);
		pushNeighbor(node, Direction.DOWN);
		pushNeighbor(node, Direction.LEFT);
	}
	
	/**
	 * Busca por possíveis nós vizinhos para serem inseridos
	 * na fila prioritária.
	 * @param node O nó pai
	 * @param dir A direção vizinha do nó a ser buscado.
	 */
	private void pushNeighbor(Node node, Direction dir) {
		int w = map.getWidth();
		int h = map.getHeight();
		int x = Utils.getColumnFromGrid(node.getId(), w);
		int y = Utils.getLineFromGrid(node.getId(), w);
		int index = -1;
		
		// Buscar nó vizinho de acordo com a direção solicitada
		switch (dir) {
		case UP:
			y--;
			index = (y >= 0) ? Utils.getGridPosition(x, y, w) : -1;
			break;
		case RIGHT:
			x++;
			index = (x < w) ? Utils.getGridPosition(x, y, w) : -1;
			break;
		case DOWN:
			y++;
			index = (y < h) ? Utils.getGridPosition(x, y, w) : -1;
			break;
		case LEFT:
			x--;
			index = (x >= 0) ? Utils.getGridPosition(x, y, w) : -1;
			break;
		}
		
		// Adicionar nó à lista caso seja uma posição possível
		if (index >= 0) {
			Node child = map.getNodes().get(index);
			if (!child.isBlocked() && !child.isVisited()) {
				child.setParent(node);
				child.setGrid(child.getParent().getGrid() + 1);
				child.setHeuristic(this.getManhattanDistance(child, destination));
				openList.offer(child);
			}
		}		
	}
		
	
	/*
	 * ===========================================================
	 * 				*** GETTERS ***
	 * ===========================================================
	 */

	public Scene getMap() {
		return map;
	}
	
	/*
	 * ===========================================================
	 * 				*** GETTERS E SETTERS ***
	 * ===========================================================
	 */

	public Node getSource() {
		return source;
	}

	public void setSource(Node source) {
		this.source = source;
	}

	public Node getDestination() {
		return destination;
	}

	public void setDestination(Node destination) {
		this.destination = destination;
	}	

}
