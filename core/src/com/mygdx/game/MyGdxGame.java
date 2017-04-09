package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.avatar.Avatar;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.PlayState;
import com.mygdx.game.states.State;

public class MyGdxGame extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	
	public static ResourceManager rm;
	public static State state;
	private static Avatar avatar;
	
	SpriteBatch batch;
	

	@Override
	public void create() {
		rm = new ResourceManager();
		avatar = new Avatar(rm.front);
		batch = new SpriteBatch();
		state = new PlayState(new GameStateManager(), avatar);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0.5f, 0.5f, 1);
		
		state.update(0);
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(avatar.getSprite(), avatar.getX(), avatar.getY());
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
