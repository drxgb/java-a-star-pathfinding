package util;

/**
 * Objeto para enumerar as direções do mapa.
 * 
 * @author Dr.XGB
 * @version 1.0
 */
public enum Direction {

	UP(1),
	RIGHT(2),
	DOWN(3),
	LEFT(4);
	
	private int code;
	
	private Direction(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
}
