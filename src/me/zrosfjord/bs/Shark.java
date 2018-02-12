package me.zrosfjord.bs;

import java.awt.Point;
import java.util.List;

public class Shark extends Entity {

	public Shark(int x, int y) {
		super(x, y, 's');
	}

	@Override
	public void update(List<Entity> entities) { //update shark moves
		Human h = null;
		
		for(int x = 0; x < entities.size(); x++) {
			Entity e = entities.get(x);
			if(e instanceof Human) {
				h = (Human) e;
			}
		}
		
		if(h == null)
			return;
		
		int humanX = h.getX();
		int humanY = h.getY();
		
		Point p = null;
		int minDif = 100;
		
		for(int x = -1; x <= 1; x++) {
			for(int y = -1; y <= 1; y++) {
				int difX = Math.abs((this.x + x) - humanX);
				int difY = Math.abs((this.y + y) - humanY);
				
				if(difX + difY < minDif) {
					minDif = difX + difY;
					p = new Point(x, y);
				}
			}
		}
		
		move((int) p.getX(), (int) p.getY());
		
		if(h == collision(entities)) {
			Battleship.running = false;
		}
	}

}
