package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.avatar.Avatar;
import com.mygdx.game.util.Direction;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Avatar avatar;
	Sprite back;
	Sprite backWalk1;
	Sprite backWalk2;
	Sprite front;
	Sprite frontWalk1;
	Sprite frontWalk2;
	Sprite left;
	Sprite leftWalk1;
	Sprite leftWalk2;
	Sprite right;
	Sprite rightWalk1;
	Sprite rightWalk2;
	float avaX = 0, avaY = 0, avaSpeed = 50.0f;

	@Override
	public void create() {
		batch = new SpriteBatch();
		back = new Sprite(new Texture("back.png"));
		backWalk1 = new Sprite(new Texture("backWalk1.png"));
		backWalk2 = new Sprite(new Texture("backWalk2.png"));
		front = new Sprite(new Texture("front.png"));
		frontWalk1 = new Sprite(new Texture("frontWalk1.png"));
		frontWalk2 = new Sprite(new Texture("frontWalk2.png"));
		left = new Sprite(new Texture("left.png"));
		leftWalk1 = new Sprite(new Texture("leftWalk1.png"));
		leftWalk2 = new Sprite(new Texture("leftWalk2.png"));
		right = new Sprite(new Texture("right.png"));
		rightWalk1 = new Sprite(new Texture("rightWalk1.png"));
		rightWalk2 = new Sprite(new Texture("rightWalk2.png"));
		
		avatar = new Avatar(front);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0.5f, 0.5f, 1);
		
		if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT) || Gdx.input.isKeyPressed(Keys.A)) {
			avatar.setSprite(left);
			avatar.setDirection(Direction.LEFT);
			avaX -= Gdx.graphics.getDeltaTime() * avaSpeed;
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {
			avatar.setSprite(right);
			avatar.setDirection(Direction.RIGHT);
			avaX += Gdx.graphics.getDeltaTime() * avaSpeed;
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_UP) || Gdx.input.isKeyPressed(Keys.W)) {
			avatar.setSprite(back);
			avatar.setDirection(Direction.UP);
			avaY += Gdx.graphics.getDeltaTime() * avaSpeed;
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_DOWN) || Gdx.input.isKeyPressed(Keys.S)) {
			avatar.setSprite(front);
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
		back.getTexture().dispose();
		backWalk1.getTexture().dispose();
		backWalk2.getTexture().dispose();
		front.getTexture().dispose();
		frontWalk1.getTexture().dispose();
		frontWalk2.getTexture().dispose();
		left.getTexture().dispose();
		leftWalk1.getTexture().dispose();
		leftWalk2.getTexture().dispose();
		right.getTexture().dispose();
		rightWalk1.getTexture().dispose();
		rightWalk2.getTexture().dispose();
	}
}
