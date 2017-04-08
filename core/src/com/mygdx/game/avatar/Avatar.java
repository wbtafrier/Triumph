package com.mygdx.game.avatar;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.util.Direction;

public class Avatar {
	
	private Sprite sprite;
	private Direction direction;
	
	public Avatar(Texture t) {
		this(new Sprite(t));
	}
	
	public Avatar(Sprite s) {
		this.setSprite(s);
	}
	
	public void setDirection(Direction dir) {
		this.direction = dir;
	}
	
	public void setSprite(Texture text) {
		this.sprite = new Sprite(text);
	}
	
	public void setSprite(Sprite spr) {
		this.sprite = spr;
	}
	
	public Direction getDirection() {
		return this.direction;
	}
	
	public Sprite getSprite() {
		return this.sprite;
	}
}
