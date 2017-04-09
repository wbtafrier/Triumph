package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ResourceManager;
import com.mygdx.game.avatar.Avatar;
import com.mygdx.game.util.Direction;

public class PlayState extends State {
	// private Bird bird;
	Avatar avatar;

	float avaX, avaY;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		avatar = new Avatar(ResourceManager.front);
		avaX = cam.position.x - (cam.viewportWidth / 2);
		avaY = avatar.getY();
		cam.setToOrtho(false);
	}

	@Override
	protected void handleInput() {
		//TODO
	}

	@Override
	public void update(float dt) {
		handleInput();
		if (avatar != null) {
			if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT) || Gdx.input.isKeyPressed(Keys.A)) {
				avatar.setTexture(ResourceManager.left);
				avatar.setDirection(Direction.LEFT);
				avaX -= dt * avatar.getSpeed();
			}
			if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {
				avatar.setTexture(ResourceManager.right);
				avatar.setDirection(Direction.RIGHT);
				avaX += dt * avatar.getSpeed();
			}
			if (Gdx.input.isKeyPressed(Keys.DPAD_UP) || Gdx.input.isKeyPressed(Keys.W)) {
				avatar.setTexture(ResourceManager.back);
				avatar.setDirection(Direction.UP);
				avaY += dt * avatar.getSpeed();
			}
			if (Gdx.input.isKeyPressed(Keys.DPAD_DOWN) || Gdx.input.isKeyPressed(Keys.S)) {
				avatar.setTexture(ResourceManager.front);
				avatar.setDirection(Direction.DOWN);
				avaY -= dt * avatar.getSpeed();
			}
			avatar.setCoords(avaX, avaY);
		}
		cam.update();
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		sb.draw(avatar.getTexture(), avatar.getX(), avatar.getY());
		sb.end();
	}
}
