package me.zrosfjord.bs;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Battleship implements Runnable {
	
	// initializing objects and array lists
	public static int BOARD_SIZE = 5;
	private List<Entity> entities;
	
	private Thread thread;
	public static boolean running = false;
	
	
	public Battleship() {
		// places entities object on the map
		entities = new ArrayList<Entity>();
		
		entities.add(new Human(4, 4, false));
		entities.add(new Shark(0, 3));
	}
	
	public void start() { // starts program
		// exits out of start() if running = true
		if(running)
			return;
		
		// starts a new thread meaning that the game will start
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		printBoard();
		
		// loop will continue to update the board until the program ends
		while(running) {
			update();
			printBoard();
		}
	}
	
	// updates position of each object in entities
	private void update() {
		for(int x = 0; x < entities.size(); x++) {
			Entity e = entities.get(x);
			e.update(entities);
		}
	}
	
	
	public void printBoard() {
		Map<Point, Entity> points = new HashMap<Point, Entity>();
		
		// loop associates each entity has a meaningful value on the map
		// according to it's position
		for(Entity e : entities) {
			points.put(new Point(e.getX(), e.getY()), e);
		}
		
		// prints out each board
		for(int x = 0; x < BOARD_SIZE; x++) {
			for(int y = 0; y < BOARD_SIZE; y++) {
				Point p = new Point(y, x);
				
				// for each entity the loop encounters, it will replace the entitie's
				// value with an icon (h or s) at the object's current position
				if(points.containsKey(p)) {
					Entity ent = points.get(p);
					System.out.print(ent.getIcon() + "\t");
				} else {
					System.out.print("o\t");
				}
			}
			
			System.out.println();
		}
		// separates after each printed board update
		System.out.println("-----------------------------------");
	}
	
	// starts the game
	public static void main(String[] args) {
		new Battleship().start();
	}

}
