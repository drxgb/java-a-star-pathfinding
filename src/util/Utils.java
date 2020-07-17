package util;

/**
 * Classe utilit�ria para calcular as posi��es dos n�s.
 * 
 * @author Dr.XGB
 * @version 1.0
 */
public abstract class Utils {

	/**
	 * <p>Calcula a posi��o da grade em rela��o �s posi��es x e y
	 * do mapa.</p>
	 * 
	 * <p>Calcula-se:<br>
	 * {@code g = y * width + x}</p>
	 * 
	 * @param x Posi��o X
	 * @param y Posi��o Y
	 * @param width Comprimento do mapa
	 * @return O valor da grade, repesentado por {@code g} na f�rmula.
	 */
	public static int getGridPosition(int x, int y, int width) {
		return y * width + x;
	}
	
	/**
	 * Extrai a posi��o X da grade.
	 * @param grid A grade. Em outras palavras, a posi��o do n�.
	 * @param width Comprimento do mapa.
	 * @return A posi��o X.
	 */
	public static int getColumnFromGrid(int grid, int width) {
		return grid % width;
	}
	
	/**
	 * Extrai a posi��o Y da grade.
	 * @param grid A grade. Em outras palavras, a posi��o do n�.
	 * @param width Comprimento do mapa.
	 * @return A posi��o Y.
	 */
	public static int getLineFromGrid(int grid, int width) {
		return grid / width;
	}
	
}
