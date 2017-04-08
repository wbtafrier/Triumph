package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.avatar.Avatar;
import com.mygdx.game.util.Direction;

public class MyGdxGame extends ApplicationAdapter {
	private ResourceManager rm;
	SpriteBatch batch;
	Avatar avatar;
	float avaX = 0, avaY = 0, avaSpeed = 50.0f;

	@Override
	public void create() {
		rm = new ResourceManager();
		batch = new SpriteBatch();
		avatar = new Avatar(rm.front);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0.5f, 0.5f, 1);
		
		if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT) || Gdx.input.isKeyPressed(Keys.A)) {
			avatar.setSprite(rm.left);
			avatar.setDirection(Direction.LEFT);
			avaX -= Gdx.graphics.getDeltaTime() * avaSpeed;
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {
			avatar.setSprite(rm.right);
			avatar.setDirection(Direction.RIGHT);
			avaX += Gdx.graphics.getDeltaTime() * avaSpeed;
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_UP) || Gdx.input.isKeyPressed(Keys.W)) {
			avatar.setSprite(rm.back);
			avatar.setDirection(Direction.UP);
			avaY += Gdx.graphics.getDeltaTime() * avaSpeed;
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_DOWN) || Gdx.input.isKeyPressed(Keys.S)) {
			avatar.setSprite(rm.front);
			avatar.setDirection(Direction.DOWN);
			avaY -= Gdx.graphics.getDeltaTime() * avaSpeed;
		}
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(avatar.getSprite(), avaX, avaY);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		for (int i = 0; i < rm.getTextureList().size(); i++) {
			rm.getTextureList().get(i).dispose();
		}
	}
}
