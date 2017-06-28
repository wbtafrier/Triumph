package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;

public abstract class Splash {

	public static final int WATER = 0;
	
	private float x = 0, y = 0, rotation = 0;
	private boolean alive = true;
	
	protected Texture texture;
	protected float time = 0f;
	
	public Splash(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Splash(float x, float y, float rotation) {
		this(x, y);
		this.rotation = rotation;
	}
	
	public void update(float dt) {
		this.time += dt;
	}
	
	public float getX() {
		return this.x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return this.y;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getRotation() {
		return this.rotation;
	}
	
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	
	public boolean isAlive() {
		return this.alive;
	}
	
	public void kill() {
		this.alive = false;
	}
	
	public Texture getTexture() {
		return this.texture;
	}
	
	public abstract int getType();
	
}
