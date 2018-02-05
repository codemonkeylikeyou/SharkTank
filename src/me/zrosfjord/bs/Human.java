package me.zrosfjord.bs;

import java.util.List;
import java.util.Scanner;

public class Human extends Entity {
	
	private Scanner scanner;
	
	public Human(int x, int y) {
		super(x, y, 'h');
		
		scanner = new Scanner(System.in);
	}

	@Override
	public void update(List<Entity> entities) {
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
	}

}
