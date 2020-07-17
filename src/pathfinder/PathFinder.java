package pathfinder;

import java.util.PriorityQueue;
import java.util.Queue;

import map.Node;
import map.Scene;
import util.Direction;
import util.Utils;

/**
 * Classe respons�vel por realizar o Algor�tmo de
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
	 * 				*** ASSOCIA��ES ***
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
	 * 				*** M�TODOS P�BLICOS ***
	 * ===========================================================
	 */
	
	/**
	 * Recebe a inst�ncia �nica desta classe.
	 * @return Uma inst�ncia de {@code PathFinder}.
	 */
	public static PathFinder getInstance() {
		return self;
	}

	/**
	 * Constr�i o mapa que deve ser trabalhado o algoritmo.
	 * @param w Comprimento do mapa.
	 * @param h Altura do mapa.
	 */
	public void buildMap(int w, int h) {
		map = new Scene(w, h);
	}
	
	/**
	 * Far� a busca do caminho mais curto poss�vel para se fazer do
	 * ponto de origem at� o ponto destino, detectando obst�culos e
	 * criando desvios at� o caminho desejado.
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
			
			// Quebrar la�o se o destino foi encontrado
			if (node != null && node.equals(destination)) {
				this.tracePath();
				break;
			}
		} while (node != null);
	}
	
	/**
	 * <p>Far� a rota de caminho caso tenha conseguido
	 * encontrar o destino.</p>
	 * <p>Dessa forma, o sistema deve recuperar todos os n�s
	 * pai e determinar que aqueles n�s s�o promissores, ou seja,
	 * � o caminho mais curto poss�vel para chegar at� o destino.
	 * Este processo � feito at� que chegue ao ponto de origem,
	 * tentdo ent�o, a rota projetada com sucesso.</p>
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
	 * 				*** M�TODOS PRIVADOS ***
	 * ===========================================================
	 */
	
	/**
	 * <p>Calcula a dist�ncia de Manhattan, cuja f�rmula �:</p>
	 * <p>{@code d = abs(dx - cx) + abs(dy - cy)}</p>
	 * <p>Sendo:<br>
	 * d -> dist�ncia<br>
	 * dx -> posi��o x do destino<br>
	 * dy -> posi��o y do destino<br>
	 * cx -> posi��o x do n� atual<br>
	 * cy -> posi��o y do n� atual<br>
	 * abs -> valor absoluto</p>
	 * @param current O n� atual
	 * @param target O n� destino
	 * @return A dist�ncia em tiles entre o n� atual e o destino,
	 * juntando a dist�ncia horizontal e vertical.
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
	 * Adiciona todos os n�s filhos para a fila priorit�ria.
	 * @param node O n� pai.
	 */
	private void pushChildren(Node node) {
		pushNeighbor(node, Direction.UP);
		pushNeighbor(node, Direction.RIGHT);
		pushNeighbor(node, Direction.DOWN);
		pushNeighbor(node, Direction.LEFT);
	}
	
	/**
	 * Busca por poss�veis n�s vizinhos para serem inseridos
	 * na fila priorit�ria.
	 * @param node O n� pai
	 * @param dir A dire��o vizinha do n� a ser buscado.
	 */
	private void pushNeighbor(Node node, Direction dir) {
		int w = map.getWidth();
		int h = map.getHeight();
		int x = Utils.getColumnFromGrid(node.getId(), w);
		int y = Utils.getLineFromGrid(node.getId(), w);
		int index = -1;
		
		// Buscar n� vizinho de acordo com a dire��o solicitada
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
		
		// Adicionar n� � lista caso seja uma posi��o poss�vel
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
