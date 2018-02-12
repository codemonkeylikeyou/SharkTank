package me.zrosfjord.bs;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Human extends Entity {
	
	private boolean input;
	private Scanner scanner;
	
	public Human(int x, int y, boolean input) { //constructor
		super(x, y, 'h');
		this.input = input;
		
		scanner = new Scanner(System.in);
	}

	@Override
	public void update(List<Entity> entities) { //update human move
		if(input) {
			System.out.print("Move Human (delta x, delta y): ");
			
			String[] splitInput = scanner.nextLine().split(",");
			int x = Integer.parseInt(splitInput[0].trim());
			int y = Integer.parseInt(splitInput[1].trim());
			
			while(!move(x, y)) {
				System.out.print("Move Human (delta x, delta y): ");
				
				splitInput = scanner.nextLine().split(",");
				x = Integer.parseInt(splitInput[0].trim());
				y = Integer.parseInt(splitInput[1].trim());
			}
		} else {
			Shark s = null;
			
			for(int x = 0; x < entities.size(); x++) {
				Entity e = entities.get(x);
				if(e instanceof Shark)
					s = (Shark) e;
			}
			
			if(s == null)
				return;
			
			int sharkX = s.getX();
			int sharkY = s.getY();
			
			Map<Point, Integer> difMap = new HashMap<Point, Integer>();
			
			for(int x = -1; x <= 1; x++) {
				for(int y = -1; y <= 1; y++) {
					int difX = Math.abs((this.x + x) - sharkX);
					int difY = Math.abs((this.y + y) - sharkY);
					
					difMap.put(new Point(x, y), difX + difY);
				}
			}
			
			List<Integer> orderedDifList = new ArrayList<Integer>();
			for(Entry<Point, Integer> e : difMap.entrySet()) {
				orderedDifList.add(e.getValue());
			}
			
			Collections.sort(orderedDifList, Collections.reverseOrder());
			
			Point p = null;
			for(int dif : orderedDifList) {
				for(Entry<Point, Integer> e : difMap.entrySet()) {
					if(e.getValue() == dif)
						p = e.getKey();
				}
				
				if(move((int) p.getX(), (int) p.getY()))
					return;
			}
		}
		
	}

}
