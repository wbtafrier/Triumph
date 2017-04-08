package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture character;

	@Override
	public void create() {
		batch = new SpriteBatch();
		character = new Texture("frontWalk1.png");
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(character, 0, 0);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		character.dispose();
	}
}
