package util;

/**
 * Classe utilitária para calcular as posições dos nós.
 * 
 * @author Dr.XGB
 * @version 1.0
 */
public abstract class Utils {

	/**
	 * <p>Calcula a posição da grade em relação às posições x e y
	 * do mapa.</p>
	 * 
	 * <p>Calcula-se:<br>
	 * {@code g = y * width + x}</p>
	 * 
	 * @param x Posição X
	 * @param y Posição Y
	 * @param width Comprimento do mapa
	 * @return O valor da grade, repesentado por {@code g} na fórmula.
	 */
	public static int getGridPosition(int x, int y, int width) {
		return y * width + x;
	}
	
	/**
	 * Extrai a posição X da grade.
	 * @param grid A grade. Em outras palavras, a posição do nó.
	 * @param width Comprimento do mapa.
	 * @return A posição X.
	 */
	public static int getColumnFromGrid(int grid, int width) {
		return grid % width;
	}
	
	/**
	 * Extrai a posição Y da grade.
	 * @param grid A grade. Em outras palavras, a posição do nó.
	 * @param width Comprimento do mapa.
	 * @return A posição Y.
	 */
	public static int getLineFromGrid(int grid, int width) {
		return grid / width;
	}
	
}
