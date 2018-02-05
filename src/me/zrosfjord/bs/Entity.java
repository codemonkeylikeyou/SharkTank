package me.zrosfjord.bs;

import java.util.List;

public abstract class Entity {
	
	protected int x, y;
	protected char icon;
	protected boolean alive = true;
	
	public Entity(int x, int y, char icon){
		this.x = x;
		this.y = y;
		this.icon = icon;
	}
	
	public abstract void update(List<Entity> entities);
	
	
	public boolean move(int moveX, int moveY) {
		if(x + moveX < 0 || x + moveX >= Battleship.BOARD_SIZE) {
			return false;
		} else if(y + moveY < 0 || y + moveY >= Battleship.BOARD_SIZE) {
			return false;
		} else {
			x += moveX;
			y += moveY;
			return true;
		}
	}
	
	public Entity collision(List<Entity> entities) {
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if(e == this)
				continue;
			
			if(e.getX() == x && e.getY() == y)
				return e;
		}
		
		return null;
	}
	

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public char getIcon() {
		return icon;
	}

	public boolean isAlive() {
		return alive;
	}

}
