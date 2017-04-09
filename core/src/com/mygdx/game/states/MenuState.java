package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ResourceManager;

public class MenuState extends State {

	public MenuState(GameStateManager gsm) {
		super(gsm);
		cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
	}

	@Override
	protected void handleInput() {
		if (Gdx.input.justTouched()) {
			gsm.set(new PlayState(gsm));
		}
	}

	@Override
	public void update(float dt) {
		handleInput();
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(ResourceManager.mainMenu, 0, 0);
        sb.end();
	}

	@Override
	public void resize(int width, int height) {
		cam.setToOrtho(false, width, height);
	}

}
