package com.mygdx.game.avatar;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.AnimationManager;
import com.mygdx.game.ResourceManager;
import com.mygdx.game.entity.SplashMonitor;
import com.mygdx.game.entity.WaterSplash;
import com.mygdx.game.util.Direction;

public class Avatar {
	
	private Texture texture;
	private Direction direction = Direction.DOWN;
	private float x = 0, y = 0, rotation = 0, time = 0f, speed = 60f, strikeStartTime = 0f;
	private int health = 500;
	private boolean moving = false, falling = false, striking = false, underAttack = false;
	SplashMonitor splashMonitor;
	final float startX, startY;
	float fallSpeed = 1.25f;
	public static final float fallWidth = ResourceManager.frontFall.getWidth(),
			fallHeight = ResourceManager.frontFall.getHeight();
	
	public Avatar(Texture t) {
		this.texture = t;
		splashMonitor = new SplashMonitor();
		startX = ResourceManager.testIsland.getWidth() / 2;
		this.setX(startX);
		startY = ResourceManager.testIsland.getHeight() / 2;
		this.setY(startY);
	}
	
	public void update(float dt) {
		this.time += dt;
		
		if (!this.striking) {
			if (this.direction.equals(Direction.LEFT)) {
				if (this.moving && !this.falling) {
					this.texture = AnimationManager.leftWalk.getKeyFrame(this.time, true);
				}
				else if (this.falling) {
					
					this.texture = ResourceManager.leftFall;
				}
				else
					this.texture = ResourceManager.left;
			} else if (this.direction.equals(Direction.RIGHT)) {
				if (this.moving && !this.falling)
					this.texture = AnimationManager.rightWalk.getKeyFrame(this.time, true);
				else if (this.falling)
					this.texture = ResourceManager.rightFall;
				else
					this.texture = ResourceManager.right;
			} else if (this.direction.equals(Direction.DOWN)) {
				if (this.moving && !this.falling)
					this.texture = AnimationManager.frontWalk.getKeyFrame(this.time, true);
				else if (this.falling)
					this.texture = ResourceManager.frontFall;
				else
					this.texture = ResourceManager.front;
			} else if (this.direction.equals(Direction.UP)) {
				if (this.moving && !this.falling)
					this.texture = AnimationManager.backWalk.getKeyFrame(this.time, true);
				else if (this.falling)
					this.texture = ResourceManager.backFall;
				else
					this.texture = ResourceManager.back;
			}
		}
		else {
			if (this.striking && this.strikeStartTime == 0) {
				this.strikeStartTime = this.time;
			}
			else if (AnimationManager.swordHit.isAnimationFinished(time - this.strikeStartTime)) {
				this.striking = false;
				this.strikeStartTime = 0;
			}
			
			this.texture = AnimationManager.swordHit.getKeyFrame(time, true);
		}
		
		if (this.health <= 0) {
			this.reset();
		}
		
		splashMonitor.update(dt);
	}
	
	public void triggerStrike() {
		this.striking = true;
	}
	
	public void stopStriking() {
		this.striking = false;
	}
	
	public void setUnderAttack(boolean a) {
		this.underAttack = a;
	}
	
	public boolean isUnderAttack() {
		return this.underAttack;
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
	
	public void addX(float dx) {
		this.x += dx;
	}
	
	public void subX(float dx) {
		this.x -= dx;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void addY(float dy) {
		this.y += dy;
	}
	
	public void subY(float dy) {
		this.y -= dy;
	}
	
	public void setCoords(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void reset() {
		this.x = startX;
		this.y = startY;
		this.rotation = 0f;
		this.health = 500;
		this.direction = Direction.DOWN;
		this.texture = ResourceManager.front;
		this.moving = false;
		this.falling = false;
		this.stopStriking();
	}
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
	
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	
	public void addRotation(float dr) {
		this.rotation += dr;
	}
	
	public void subRotation(float dr) {
		this.rotation -= dr;
	}
	
	public float getRotation() {
		return this.rotation;
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
	
	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	
	public boolean isFalling() {
		return this.falling;
	}
	
	
	public boolean isStriking() {
		return this.striking;
	}
	
	public void setHealth(int h) {
		this.health = h;
	}
	
	public void changeHealth(int dh) {
		this.health += dh;
	}

	public int getHealth() {
		return this.health;
	}
	
	public float getFallSpeed() {
		return this.fallSpeed;
	}
	
	public void addSplashToMonitor(float x, float y) {
		this.addSplashToMonitor(x, y, SplashMonitor.BACKGROUND);
	}
	
	public void addSplashToMonitor(float x, float y, int plane) {
		WaterSplash s = new WaterSplash(x, y);
		splashMonitor.add(s, plane);
	}
	
	public SplashMonitor getSplashMonitor() {
		return this.splashMonitor;
	}
}
