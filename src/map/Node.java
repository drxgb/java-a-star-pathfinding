package map;

import pathfinder.PathFinder;
import util.Utils;

/**
 * <p>Representa uma �nica c�lula de um mapa.<br>
 * Um {@code Node} (ou N�) pode ser utilizado para
 * calcular a dist�ncia entre o ponto inicial (fonte)
 * e o ponto final (destino).</p>
 * 
 * <p>Temos as seguintes propriedades principais:</p>
 * 
 * <ul>
 * 	<li>{@code grid}: representa a dist�ncia entre sua posi��o
 * em rela��o ao n� referente ao ponto fonte.</li>
 * 	<li>{@code heuristic}: dist�ncia entre sua posi��o em rela��o
 * ao n� referente ao ponto destino.</li>
 * </ul>
 * 
 * <p>Por padr�o, todos os n�s iniciam suas propriedades principais como
 * {@code null}, por�m quando configuradas, cada n� ter� seu peso, que � deifinido
 * no m�todo {@code f()}. Para se calcular {@code f}, basta usar a seguinte f�rmula:</p>
 * 
 * <p><code>f = g + h</code></p>
 * 
 * <p>Sendo:<br>
 * f -> peso da c�lula<br>
 * g -> valor de {@code grid}<br>
 * h -> valor de {@code heuristic}</p>
 * 
 * <p>O n� que possui o menor peso � considerao como um n� promissor.
 * Ao encontrar o ponto destino, o sistema deve tra�ar todos os n�s
 * promissores at� ao ponto origem, formando ent�o o caminho
 * mais curto poss�vel.</p>
 * 
 * <p>Todos os n�s que devem ser computados pertencer�o a uma fila
 * priorit�ria ({@code PriorityQueue}), onde ser� organizada por peso
 * de forma crescente. Portanto, essa classe deve implementar a interface
 * {@code Comparable} para que a classe {@code PriorityQueue} possa
 * entender qual ser� o crit�rio de organiza��o dessa fila, onde ela chamar�
 * o m�todo {@code compareTo(Node node)} implementado por esta classe.</p>
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
	 * 				*** ASSOCIA��ES ***
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
	 * 				*** M�TODOS PRIVADOS ***
	 * ===========================================================
	 */
	
	private Integer f() {
		return grid + heuristic;
	}
	
	/*
	 * ===========================================================
	 * 				*** M�TODOS IMPLEMENTADOS ***
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
