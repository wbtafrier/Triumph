package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Sprite avatar;
	Texture back;
	Texture backWalk1;
	Texture backWalk2;
	Texture front;
	Texture frontWalk1;
	Texture frontWalk2;
	Texture left;
	Texture leftWalk1;
	Texture leftWalk2;
	Texture right;
	Texture rightWalk1;
	Texture rightWalk2;
	float avaX = 0, avaY = 0, avaSpeed = 50.0f;

	@Override
	public void create() {
		batch = new SpriteBatch();
		back = new Texture("back.png");
		backWalk1 = new Texture("backWalk1.png");
		backWalk2 = new Texture("backWalk2.png");
		front = new Texture("front.png");
		frontWalk1 = new Texture("frontWalk1.png");
		frontWalk2 = new Texture("frontWalk2.png");
		left = new Texture("left.png");
		leftWalk1 = new Texture("leftWalk1.png");
		leftWalk2 = new Texture("leftWalk2.png");
		right = new Texture("right.png");
		rightWalk1 = new Texture("rightWalk1.png");
		rightWalk2 = new Texture("rightWalk2.png");
		
		avatar = new Sprite(front, front.getWidth(), front.getHeight());
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0.5f, 0.5f, 1);
		
		if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT) || Gdx.input.isKeyPressed(Keys.A)) {
			avatar.setTexture(left);
			avaX -= Gdx.graphics.getDeltaTime() * avaSpeed;
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {
			avatar.setTexture(right);
			avaX += Gdx.graphics.getDeltaTime() * avaSpeed;
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_UP) || Gdx.input.isKeyPressed(Keys.W)) {
			avatar.setTexture(back);
			avaY += Gdx.graphics.getDeltaTime() * avaSpeed;
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_DOWN) || Gdx.input.isKeyPressed(Keys.S)) {
			avatar.setTexture(front);
			avaY -= Gdx.graphics.getDeltaTime() * avaSpeed;
		}
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(avatar, avaX, avaY);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		back.dispose();
		backWalk1.dispose();
		backWalk2.dispose();
		front.dispose();
		frontWalk1.dispose();
		frontWalk2.dispose();
		left.dispose();
		leftWalk1.dispose();
		leftWalk2.dispose();
		right.dispose();
		rightWalk1.dispose();
		rightWalk2.dispose();
	}
}
