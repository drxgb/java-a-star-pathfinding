/**
 * ======================================
 * 		A* Pathfinding 
 * 
 * Autor: Dr.XGB	Version: 1.0
 * Data: 17/07/2020
 * 
 * IDE: Eclipse 2020-06 (4.16.0)
 * JDK: Java 11 SE		JRE: 1.8
 * =====================================
 */

package app;

import java.util.List;
import java.util.Scanner;

import gui.GUI;
import map.Node;
import pathfinder.PathFinder;

/**
 * Classe que fará o entrypoint da aplicação.
 * @author Dr.XGB
 * @version 1.0
 */
public class Main {

	/**
	 * Método chamado pela JVM para iniciar a aplicação.
	 * @param args Este argumento não será utilizado nesta aplicação.
	 */
	public static void main(String[] args) {
		GUI.clearScreen();
		PathFinder finder = PathFinder.getInstance();		
		finder.buildMap(5, 5);
		
		List<Node> nodes = finder.getMap().getNodes();
		nodes.get(7).setBlocked(true);
		nodes.get(12).setBlocked(true);
		nodes.get(17).setBlocked(true);
		
		try (Scanner sc = new Scanner(System.in)) {
			System.out.print("Source");
			finder.setSource(GUI.choosePosition(sc));
			
			System.out.print("Destination");
			finder.setDestination(GUI.choosePosition(sc));
			
			long millis = System.currentTimeMillis();
			
			finder.seek();
			GUI.drawMap(finder.getMap());
			
			millis = System.currentTimeMillis() - millis;
			System.out.println("Caminho foi projetado em " + millis + "ms.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
