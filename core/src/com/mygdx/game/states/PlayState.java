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

	float avaX, avaY, dt;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		cam.setToOrtho(false, MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT / 2);
		avatar = new Avatar(ResourceManager.front);
		avatar.setX(cam.position.x * 2);
		avatar.setY(cam.position.y * 2);
	}

	@Override
	protected void handleInput() {
		if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT) || Gdx.input.isKeyPressed(Keys.A)) {
			avatar.setDirection(Direction.LEFT);
			avatar.setTexture(ResourceManager.left);
			avaX -= dt * avatar.getSpeed();
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {
			avatar.setDirection(Direction.RIGHT);
			avatar.setTexture(ResourceManager.right);
			avaX += dt * avatar.getSpeed();
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_UP) || Gdx.input.isKeyPressed(Keys.W)) {
			avatar.setDirection(Direction.UP);
			avatar.setTexture(ResourceManager.back);
			avaY += dt * avatar.getSpeed();
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_DOWN) || Gdx.input.isKeyPressed(Keys.S)) {
			avatar.setDirection(Direction.DOWN);
			avatar.setTexture(ResourceManager.front);
			avaY -= dt * avatar.getSpeed();
		}
	}

	@Override
	public void update(float dt) {
		this.dt = dt;
		
		if (avatar != null) {
			this.avaX = this.avatar.getX();
			this.avaY = this.avatar.getY();
			handleInput();
			avatar.setCoords(avaX, avaY);
			cam.translate(avaX - cam.position.x, avaY - cam.position.y);
		}
		
		cam.update();
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		sb.draw(ResourceManager.water1, 0, 0);
		sb.draw(ResourceManager.testIsland, (ResourceManager.water1.getWidth() / 2) - (ResourceManager.testIsland.getWidth() / 2),
				(ResourceManager.water1.getHeight() / 2) - (ResourceManager.testIsland.getHeight() / 2));
		sb.draw(avatar.getTexture(), avatar.getX(), avatar.getY());
		sb.end();
	}
	
	@Override
	public void resize(int width, int height) {
		cam.setToOrtho(false, width / 2, height / 2);
	}
}
