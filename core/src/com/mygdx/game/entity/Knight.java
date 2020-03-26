package com.mygdx.game.entity;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.avatar.Avatar;
import com.mygdx.game.util.Direction;

public class Knight extends Avatar {

	static Random rand = new Random();
	float timeLeft = 0;
	
	public Knight(Texture t) {
		super(t);
	}

	public void updateAI(float dt) {
		if (!this.isFalling() && !this.isMoving()) {
			int r = rand.nextInt(!this.isStriking() ? 150 : 200);
			if (r == 0) {
				this.setMoving(true);
				timeLeft = rand.nextFloat() * 3f;
 				r = rand.nextInt(4);
				if (r == 0) {
					this.setDirection(Direction.LEFT);
					this.subX(dt * this.getSpeed());
				}
				else if (r == 1) {
					this.setDirection(Direction.RIGHT);
					this.addX(dt * this.getSpeed());
				}
				else if (r == 2) {
					this.setDirection(Direction.UP);
					this.addY(dt * this.getSpeed());
				}
				else {
					this.setDirection(Direction.DOWN);
					this.subY(dt * this.getSpeed());
				}
			}
		}
		else if (!this.isFalling() && timeLeft > 0) {
			timeLeft -= dt;
			Direction dir = this.getDirection();
			if (dir == Direction.LEFT) {
				this.subX(dt * this.getSpeed());
			}
			else if (dir == Direction.RIGHT) {
				this.addX(dt * this.getSpeed());
			}
			else if (dir == Direction.UP) {
				this.addY(dt * this.getSpeed());
			}
			else {
				this.subY(dt * this.getSpeed());
			}
		}
		else if (!this.isFalling() && timeLeft <= 0) {
			timeLeft = 0;
			this.setMoving(false);
		}
		else if (this.isFalling()) {
			timeLeft = 0;
			this.setMoving(true);
		}
	}
	
}
