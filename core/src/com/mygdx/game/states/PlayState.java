package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.avatar.Avatar;
import com.mygdx.game.util.Direction;

public class PlayState extends State {
	// private Bird bird;
	Avatar avatar;

	float avaX, avaY, avaSpeed = 50.0f;

	public PlayState(GameStateManager gsm, Avatar avatar) {
		super(gsm);
		this.avatar = avatar;
		avaX = avatar.getX();
		avaY = avatar.getY();
		// bird = new Bird(50,100);
		// cam.setToOrtho();
	}

	@Override
	protected void handleInput() {
		if (avatar != null) {
			if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT) || Gdx.input.isKeyPressed(Keys.A)) {
				avatar.setSprite(MyGdxGame.rm.left);
				avatar.setDirection(Direction.LEFT);
				avaX -= Gdx.graphics.getDeltaTime() * avaSpeed;
			}
			if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {
				avatar.setSprite(MyGdxGame.rm.right);
				avatar.setDirection(Direction.RIGHT);
				avaX += Gdx.graphics.getDeltaTime() * avaSpeed;
			}
			if (Gdx.input.isKeyPressed(Keys.DPAD_UP) || Gdx.input.isKeyPressed(Keys.W)) {
				avatar.setSprite(MyGdxGame.rm.back);
				avatar.setDirection(Direction.UP);
				avaY += Gdx.graphics.getDeltaTime() * avaSpeed;
			}
			if (Gdx.input.isKeyPressed(Keys.DPAD_DOWN) || Gdx.input.isKeyPressed(Keys.S)) {
				avatar.setSprite(MyGdxGame.rm.front);
				avatar.setDirection(Direction.DOWN);
				avaY -= Gdx.graphics.getDeltaTime() * avaSpeed;
			}
			avatar.setCoords(avaX, avaY);

		}
	}

	@Override
	public void update(float dt) {
		handleInput();
		// bird.update(dt);
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		// sb.draw(bird.getTexture(),bird.getPosition().x,bird.getPosition().y);
		sb.end();

	}
}
