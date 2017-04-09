package com.mygdx.game.avatar;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.util.Direction;

public class Avatar {
	
	private Sprite sprite;
	private Direction direction;
	private float x = 0, y = 0;
	
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
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void setCoords(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
}
