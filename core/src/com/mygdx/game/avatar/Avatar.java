package com.mygdx.game.avatar;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.util.Direction;

public class Avatar {
	
	private Texture texture;
	private Direction direction;
	private float x = 0, y = 0, speed = 50f;
	
	public Avatar(Texture t) {
		this.texture = t;
	}
	
	public void setDirection(Direction dir) {
		this.direction = dir;
	}
	
	public void setTexture(Texture text) {
		this.texture = text;
	}
	
	public Direction getDirection() {
		return this.direction;
	}
	
	public Texture getTexture() {
		return this.texture;
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
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public float getSpeed() {
		return this.speed;
	}
}
