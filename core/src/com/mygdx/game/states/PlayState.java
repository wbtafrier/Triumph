package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.AnimationManager;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ResourceManager;
import com.mygdx.game.avatar.Avatar;
import com.mygdx.game.entity.SplashMonitor;
import com.mygdx.game.entity.WaterSplash;
import com.mygdx.game.util.Direction;

public class PlayState extends State {
	Avatar avatar;
	SplashMonitor splashMonitor;
	Texture currentWater;
	float avaX, avaY, avaRotation, dt, time = 0;
	final float avaStartX, avaStartY, avaFallSpeed = 1.25f;
	final float waterWidth = ResourceManager.water1.getWidth() * 1.15f,
			waterHeight = ResourceManager.water1.getHeight() * 1.25f,
			waterX = -((waterWidth - ResourceManager.testIsland.getWidth()) / 2),
			waterY = -((waterHeight - ResourceManager.testIsland.getHeight()) / 2),
			avaFallWidth = ResourceManager.frontFall.getWidth(),
			avaFallHeight = ResourceManager.frontFall.getHeight();

	public PlayState(GameStateManager gsm) {
		super(gsm);
		cam.setToOrtho(false, MyGdxGame.WIDTH / 3, MyGdxGame.HEIGHT / 3);
		avatar = new Avatar(ResourceManager.front);
		avaStartX = ResourceManager.testIsland.getWidth() / 2;
		avatar.setX(avaStartX);
		avaStartY = ResourceManager.testIsland.getHeight() / 2;
		avatar.setY(avaStartY);
		splashMonitor = new SplashMonitor();
		currentWater = ResourceManager.water1;
	}

	@Override
	protected void handleInput() {
		boolean moving = false;
		boolean jumping = false;
		
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			gsm.set(new MenuState(gsm));
			return;
		}
		
		if (!avatar.isFalling()) {
			if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT) || Gdx.input.isKeyPressed(Keys.A)) {
				avatar.setDirection(Direction.LEFT);
				avaX -= dt * avatar.getSpeed();
				moving = true;
			}
			if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {
				avatar.setDirection(Direction.RIGHT);
				avaX += dt * avatar.getSpeed();
				moving = true;
			}
			if (Gdx.input.isKeyPressed(Keys.DPAD_UP) || Gdx.input.isKeyPressed(Keys.W)) {
				avatar.setDirection(Direction.UP);
				avaY += dt * avatar.getSpeed();
				moving = true;
			}
			if (Gdx.input.isKeyPressed(Keys.DPAD_DOWN) || Gdx.input.isKeyPressed(Keys.S)) {
				avatar.setDirection(Direction.DOWN);
				avaY -= dt * avatar.getSpeed();
				moving = true;
			}
			if (Gdx.input.isKeyPressed(Keys.SPACE)) {
				//please finish this... coords need implementation and graphics are bugged.
				jumping = true;
			}
		}
		else {
			moving = true;
		}
		avatar.setMoving(moving);
	}

	@Override
	public void update(float dt) {
		boolean falling = false;
		
		this.dt = dt;
		this.time += dt;
		
		if (avatar != null) {
			this.avaX = this.avatar.getX();
			this.avaY = this.avatar.getY();
			this.avaRotation = this.avatar.getRotation();
			handleInput();
			if (avaX < 22) {
				this.avatar.setDirection(Direction.LEFT);
				avaX -= avaFallSpeed;
				avaY -= avaFallSpeed;
				avaRotation += 0.5;
				
				if (avaX < -18 && avaX > -38) {
					/* splash testing code */
					WaterSplash s = new WaterSplash(avaX, avaY - 5);
					splashMonitor.add(s, SplashMonitor.BACKGROUND);
				}
				
				if (avaX < -38) {
					avaX = avaStartX;
					avaY = avaStartY;
					avaRotation = 0;
				}
				else if (!falling) {
					falling = true;
				}
			}
			if (avaY < 40) {
				avaY -= avaFallSpeed;
				if (avaY < -15) {
					avaX = avaStartX;
					avaY = avaStartY;
				}
				else if (!falling) {
					falling = true;
				}
			}
			if (avaX > 450) {
				this.avatar.setDirection(Direction.RIGHT);
				avaX += avaFallSpeed;
				avaY -= avaFallSpeed;
				avaRotation -= 0.5;
				
				if (avaX > 490 && avaX < 510) {
					/* splash testing code */
					WaterSplash s = new WaterSplash(avaX, avaY - 5);
					splashMonitor.add(s, SplashMonitor.BACKGROUND);
				}
				
				if (avaX > 510) {
					avaX = avaStartX;
					avaY = avaStartY;
					avaRotation = 0;
				}
				else if (!falling) {
					falling = true;
				}
			}
			if (avaY > 370) {
				avaY += avaFallSpeed;
				if (avaY > 405) {
					avaX = avaStartX;
					avaY = avaStartY;
				}
				else if (!falling) {
					falling = true;
				}
			}
			avatar.setCoords(avaX, avaY);
			avatar.setRotation(avaRotation);
			this.avatar.setFalling(falling);
			avatar.update(dt);
			splashMonitor.update(dt);
			cam.translate(avaX - cam.position.x, avaY - cam.position.y);
		}

		this.currentWater = AnimationManager.waterFlow.getKeyFrame(time, true);
		cam.update();
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		sb.draw(this.currentWater, waterX, waterY, waterWidth, waterHeight);
		sb.draw(ResourceManager.testIsland, 0, 0);

		splashMonitor.render(sb, SplashMonitor.BACKGROUND);
		
		if (avatar.isFalling() && (avatar.getDirection() == Direction.LEFT || avatar.getDirection() == Direction.RIGHT)) {
			sb.draw(avatar.getTexture(), avatar.getX(), avatar.getY(), avaFallWidth / 2, avaFallHeight / 2,
					avaFallWidth, avaFallHeight, 1, 1, avatar.getRotation(), 0, 0, avatar.getTexture().getWidth(),
					avatar.getTexture().getHeight(), false, false);
		}
		else {
			sb.draw(avatar.getTexture(), avatar.getX(), avatar.getY());
		}
		
		splashMonitor.render(sb, SplashMonitor.FOREGROUND);
		
		BitmapFont font = new BitmapFont();
		String x = Integer.toString((int)avatar.getX());
		String y = Integer.toString((int)avatar.getY());
		font.draw(sb, x + ", " + y, cam.position.x - (cam.viewportWidth / 2) + 10,
				cam.position.y - (cam.viewportHeight / 2) + 30);
		
		sb.end();
	}
	
	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}
}
