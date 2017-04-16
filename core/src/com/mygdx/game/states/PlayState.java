package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.AnimationManager;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ResourceManager;
import com.mygdx.game.avatar.Avatar;
import com.mygdx.game.util.Direction;

public class PlayState extends State {
	Avatar avatar;
	Texture currentWater;
	float avaX, avaY, dt, time = 0;
	final float avaStartX, avaStartY, avaFallSpeed = 1.25f;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		cam.setToOrtho(false, MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT / 2);
		avatar = new Avatar(ResourceManager.front);
		avaStartX = cam.position.x * 2;
		avatar.setX(avaStartX);
		avaStartY = cam.position.y * 2;
		avatar.setY(avaStartY);
		currentWater = ResourceManager.water1;
	}

	@Override
	protected void handleInput() {
		boolean moving = false;
		
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			gsm.set(new MenuState(gsm));
			return;
		}
		
		if (!avatar.isFalling()) {
			if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT) || Gdx.input.isKeyPressed(Keys.A)) {
				avatar.setDirection(Direction.LEFT);
				avaX -= dt * avatar.getSpeed();
				moving = true;
			}
			if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {
				avatar.setDirection(Direction.RIGHT);
				avaX += dt * avatar.getSpeed();
				moving = true;
			}
			if (Gdx.input.isKeyPressed(Keys.DPAD_UP) || Gdx.input.isKeyPressed(Keys.W)) {
				avatar.setDirection(Direction.UP);
				avaY += dt * avatar.getSpeed();
				moving = true;
			}
			if (Gdx.input.isKeyPressed(Keys.DPAD_DOWN) || Gdx.input.isKeyPressed(Keys.S)) {
				avatar.setDirection(Direction.DOWN);
				avaY -= dt * avatar.getSpeed();
				moving = true;
			}
		}
		else {
			moving = true;
		}
		avatar.setMoving(moving);
	}

	@Override
	public void update(float dt) {
		boolean falling = false;
		
		this.dt = dt;
		this.time += dt;
		
		if (avatar != null) {
			this.avaX = this.avatar.getX();
			this.avaY = this.avatar.getY();
			handleInput();
			if (avaX < 170) {
				avaX -= avaFallSpeed;
				if (avaX < 120) {
					avaX = 380;
					avaY = 290;
				}
				else {
					falling = true;
				}
			}
			if (avaY < 110) {
				avaY -= avaFallSpeed;
				if (avaY < 80) {
					avaX = 380;
					avaY = 290;
				}
				else {
					falling = true;
				}
			}
			if (avaX > 590) {
				avaX += avaFallSpeed;
				if (avaX > 630) {
					avaX = 380;
					avaY = 290;
				}
				else {
					falling = true;
				}
			}
			if (avaY > 440) {
				avaY += avaFallSpeed;
				if (avaY > 470) {
					avaX = 380;
					avaY = 290;
				}
				else {
					falling = true;
				}
			}
			avatar.setCoords(avaX, avaY);
			this.avatar.setFalling(falling);
			avatar.update(dt);
			cam.translate(avaX - cam.position.x, avaY - cam.position.y);
		}

		this.currentWater = AnimationManager.waterFlow.getKeyFrame(time, true);
		cam.update();
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		sb.draw(this.currentWater , -100, -100, 1000, 1000);
		sb.draw(ResourceManager.testIsland, (ResourceManager.water1.getWidth() / 2) - (ResourceManager.testIsland.getWidth() / 2),
				(ResourceManager.water1.getHeight() / 2) - (ResourceManager.testIsland.getHeight() / 2));
		sb.draw(avatar.getTexture(), avatar.getX(), avatar.getY());
		sb.end();
	}
	
	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}
}
