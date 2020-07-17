package map;

import pathfinder.PathFinder;
import util.Utils;

/**
 * <p>Representa uma única célula de um mapa.<br>
 * Um {@code Node} (ou Nó) pode ser utilizado para
 * calcular a distância entre o ponto inicial (fonte)
 * e o ponto final (destino).</p>
 * 
 * <p>Temos as seguintes propriedades principais:</p>
 * 
 * <ul>
 * 	<li>{@code grid}: representa a distância entre sua posição
 * em relação ao nó referente ao ponto fonte.</li>
 * 	<li>{@code heuristic}: distância entre sua posição em relação
 * ao nó referente ao ponto destino.</li>
 * </ul>
 * 
 * <p>Por padrão, todos os nós iniciam suas propriedades principais como
 * {@code null}, porém quando configuradas, cada nó terá seu peso, que é deifinido
 * no método {@code f()}. Para se calcular {@code f}, basta usar a seguinte fórmula:</p>
 * 
 * <p><code>f = g + h</code></p>
 * 
 * <p>Sendo:<br>
 * f -> peso da célula<br>
 * g -> valor de {@code grid}<br>
 * h -> valor de {@code heuristic}</p>
 * 
 * <p>O nó que possui o menor peso é considerao como um nó promissor.
 * Ao encontrar o ponto destino, o sistema deve traçar todos os nós
 * promissores até ao ponto origem, formando então o caminho
 * mais curto possível.</p>
 * 
 * <p>Todos os nós que devem ser computados pertencerão a uma fila
 * prioritária ({@code PriorityQueue}), onde será organizada por peso
 * de forma crescente. Portanto, essa classe deve implementar a interface
 * {@code Comparable} para que a classe {@code PriorityQueue} possa
 * entender qual será o critério de organização dessa fila, onde ela chamará
 * o método {@code compareTo(Node node)} implementado por esta classe.</p>
 * 
 * @author Dr.XGB
 * @version 1.0
 * @see Scene
 * @see PriorityQueue
 * @see Comparable
 */
public class Node implements Comparable<Node> {
	
	/*
	 * ===========================================================
	 * 				*** ATRIBUTOS ***
	 * ===========================================================
	 */

	private Integer id;
	private Integer grid 		= null;
	private Integer heuristic 	= null;
	private boolean visited;
	private boolean blocked;
	private boolean promising;

	/*
	 * ===========================================================
	 * 				*** ASSOCIAÇÕES ***
	 * ===========================================================
	 */
	
	private Node parent = null;
	
	/*
	 * ===========================================================
	 * 				*** CONSTRUTORES ***
	 * ===========================================================
	 */	
	
	public Node(int id) {
		this.id = id;
	}
	
	/*
	 * ===========================================================
	 * 				*** MÉTODOS PRIVADOS ***
	 * ===========================================================
	 */
	
	private Integer f() {
		return grid + heuristic;
	}
	
	/*
	 * ===========================================================
	 * 				*** MÉTODOS IMPLEMENTADOS ***
	 * ===========================================================
	 */

	@Override
	public int compareTo(Node o) {
		return this.f() - o.f();
	}
	
	/*
	 * ===========================================================
	 * 				*** GETTERS E SETTERS ***
	 * ===========================================================
	 */	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	
	
	public Integer getGrid() {
		return grid;
	}

	public void setGrid(Integer grid) {
		this.grid = grid;
	}

	public Integer getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(Integer heuristic) {
		this.heuristic = heuristic;
	}	

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}	

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Node getParent() {
		return parent;
	}	

	public boolean isPromising() {
		return promising;
	}

	public void setPromising(boolean promising) {
		this.promising = promising;
	}

	@Override
	public String toString() {
		Scene map = PathFinder.getInstance().getMap();
		StringBuilder sb = new StringBuilder();
		char col = (char) (Utils.getColumnFromGrid(id, map.getWidth()) + 'A');
		sb.append(col);
		sb.append(Utils.getLineFromGrid(id, map.getWidth()));
		sb.append(" - ");
		sb.append(grid + ", " + heuristic);
		return sb.toString();
	}
	
}
