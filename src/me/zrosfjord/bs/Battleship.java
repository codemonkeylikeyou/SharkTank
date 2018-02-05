package me.zrosfjord.bs;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Battleship implements Runnable {
	
	public static final int BOARD_SIZE = 5;
	private List<Entity> entities;
	
	private Thread thread;
	public static boolean running = false;
	
	
	public Battleship() {
		entities = new ArrayList<Entity>();
		
		entities.add(new Human(1, 1));
		entities.add(new Shark(4, 4));
	}
	
	public void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		printBoard();
		
		while(running) {
			update();
			printBoard();
		}
	}
	
	
	private void update() {
		for(int x = 0; x < entities.size(); x++) {
			Entity e = entities.get(x);
			e.update(entities);
		}
	}
	
	
	public void printBoard() {
		Map<Point, Entity> points = new HashMap<Point, Entity>();
		for(Entity e : entities) {
			points.put(new Point(e.getX(), e.getY()), e);
		}
		
		for(int x = 0; x < BOARD_SIZE; x++) {
			for(int y = 0; y < BOARD_SIZE; y++) {
				Point p = new Point(x, y);
				
				if(points.containsKey(p)) {
					Entity ent = points.get(p);
					System.out.print(ent.getIcon() + "\t");
				} else {
					System.out.print("o\t");
				}
			}
			
			System.out.println();
		}
	}
	
	
	public static void main(String[] args) {
		new Battleship().start();
	}

}
