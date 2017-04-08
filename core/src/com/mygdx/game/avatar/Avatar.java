package com.mygdx.game.avatar;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.util.Direction;

public class Avatar {
	
	private Sprite sprite;
	private Direction direction;
	
	public Avatar(Sprite s) {
		
	}
	
	public void setDirection(Direction dir) {
		this.direction = dir;
	}
}
