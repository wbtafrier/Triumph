package com.mygdx.game.avatar;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.AnimationManager;
import com.mygdx.game.ResourceManager;
import com.mygdx.game.util.Direction;

public class Avatar {
	
	private Texture texture;
	private Direction direction = Direction.DOWN;
	private float x = 0, y = 0, time = 0f, speed = 60f;
	private boolean moving = false;
	
	public Avatar(Texture t) {
		this.texture = t;
	}
	
	public void update(float dt) {
		this.time += dt;
		if (this.direction.equals(Direction.LEFT)) {
			if (this.moving)
				this.texture = AnimationManager.leftWalk.getKeyFrame(this.time, true);
			else
				this.texture = ResourceManager.left;
		} else if (this.direction.equals(Direction.RIGHT)) {
			if (this.moving)
				this.texture = AnimationManager.rightWalk.getKeyFrame(this.time, true);
			else
				this.texture = ResourceManager.right;
		} else if (this.direction.equals(Direction.DOWN)) {
			if (this.moving)
				this.texture = AnimationManager.frontWalk.getKeyFrame(this.time, true);
			else
				this.texture = ResourceManager.front;
		} else if (this.direction.equals(Direction.UP)) {
			if (this.moving)
				this.texture = AnimationManager.backWalk.getKeyFrame(this.time, true);
			else
				this.texture = ResourceManager.back;
		}
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
	
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	public boolean isMoving() {
		return this.moving;
	}
}
