package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;
import com.mygdx.game.states.State;

public class MyGdxGame extends ApplicationAdapter {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	public GameStateManager gsm;
	public State currentState;
	public SpriteBatch batch;
	

	@Override
	public void create() {
		ResourceManager.initializeTextures();
		AnimationManager.initializeAnimations();
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
	}
	
	@Override
	public void resize(int width, int height) {
		//TODO
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
		for (int i = 0; i < ResourceManager.getTextureList().size(); i++) {
			ResourceManager.getTextureList().get(i).dispose();
		}
	}
}
