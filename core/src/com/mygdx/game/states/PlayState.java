package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ResourceManager;
import com.mygdx.game.avatar.Avatar;
import com.mygdx.game.entity.Knight;
import com.mygdx.game.entity.SplashMonitor;
import com.mygdx.game.entity.WaterSplash;
import com.mygdx.game.map.TestIsland;
import com.mygdx.game.util.DayNight;
import com.mygdx.game.util.Direction;

public class PlayState extends State {
	DayNight dayNight;
	Avatar avatar;
	Knight knight;
	SplashMonitor splashMonitor;
	TestIsland island;
	boolean stats = false;
	float avaX, avaY, avaRotation, dt, time = 0;
	float knightX, knightY;
	final float avaStartX, avaStartY, avaFallSpeed = 1.25f;
	final float avaFallWidth = ResourceManager.frontFall.getWidth(),
			avaFallHeight = ResourceManager.frontFall.getHeight();

	public PlayState(GameStateManager gsm) {
		super(gsm);
		cam.setToOrtho(false, MyGdxGame.WIDTH / 3, MyGdxGame.HEIGHT / 3);
		dayNight = new DayNight(DayNight.DAY);
		avatar = new Avatar(ResourceManager.front);
		avaStartX = ResourceManager.testIsland.getWidth() / 2;
		avatar.setX(avaStartX);
		avaStartY = ResourceManager.testIsland.getHeight() / 2;
		avatar.setY(avaStartY);
		splashMonitor = new SplashMonitor();
		island = new TestIsland(dayNight.isNight());
		
		knight = new Knight(ResourceManager.front);
		knight.setCoords(avaStartX, avaStartY);
	}

	@Override
	protected void handleInput() {
		boolean moving = false;
		
		/*
		 * isKeyPressed VERSUS isKeyJustPressed
		 * 
		 * isKeyPressed:
		 * 		detects whether the key is currently pressed.
		 * isKeyJustPressed:
		 * 		detects whether the key was pressed and released the last frame.
		 * 
		 */
		
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			gsm.set(new MenuState(gsm));
			return;
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.F5)) {
			this.stats = !stats;
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
			if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
				avatar.triggerStrike();
			}
		}
		else {
			moving = true;
		}
		avatar.setMoving(moving);
	}
	
	public boolean checkAndHandleFalls(float dt) {
		boolean falling = false;
		
		if (avaX < TestIsland.WEST_BEACH) {
			this.avatar.setDirection(Direction.LEFT);
			avaX -= avaFallSpeed;
			avaY -= avaFallSpeed;
			avaRotation += 0.5;
			
			if (avaX < TestIsland.WEST_SHORE && avaX > TestIsland.WEST_EDGE) {
				/* splash testing code */
				WaterSplash s = new WaterSplash(avaX, avaY - 5);
				splashMonitor.add(s, SplashMonitor.BACKGROUND);
			}
			
			if (avaX < TestIsland.WEST_EDGE) {
				avaX = avaStartX;
				avaY = avaStartY;
				avaRotation = 0;
			}
			else if (!falling) {
				falling = true;
			}
		}
		if (avaY < TestIsland.SOUTH_BEACH) {
			avaY -= avaFallSpeed;
			if (avaY < TestIsland.SOUTH_SHORE) {
				avaX = avaStartX;
				avaY = avaStartY;
			}
			else if (!falling) {
				falling = true;
			}
		}
		if (avaX > TestIsland.EAST_BEACH) {
			this.avatar.setDirection(Direction.RIGHT);
			avaX += avaFallSpeed;
			avaY -= avaFallSpeed;
			avaRotation -= 0.5;
			
			if (avaX > TestIsland.EAST_SHORE && avaX < TestIsland.EAST_EDGE) {
				/* splash testing code */
				WaterSplash s = new WaterSplash(avaX, avaY - 5);
				splashMonitor.add(s, SplashMonitor.BACKGROUND);
			}
			
			if (avaX > TestIsland.EAST_EDGE) {
				avaX = avaStartX;
				avaY = avaStartY;
				avaRotation = 0;
			}
			else if (!falling) {
				falling = true;
			}
		}
		if (avaY > TestIsland.NORTH_BEACH) {
			avaY += avaFallSpeed;
			if (avaY > TestIsland.NORTH_SHORE) {
				avaX = avaStartX;
				avaY = avaStartY;
			}
			else if (!falling) {
				falling = true;
			}
		}
		
		return falling;
	}

	@Override
	public void update(float dt) {
		boolean falling;
		
		this.dt = dt;
		this.time += dt;
		
		if (avatar != null) {
			this.avaX = this.avatar.getX();
			this.avaY = this.avatar.getY();
			this.avaRotation = this.avatar.getRotation();
			handleInput();
			falling = checkAndHandleFalls(dt);
			avatar.setCoords(avaX, avaY);
			avatar.setRotation(avaRotation);
			this.avatar.setFalling(falling);
			avatar.update(dt);
			splashMonitor.update(dt);
			cam.translate(avaX - cam.position.x, avaY - cam.position.y);
		}
		
		if (knight != null) {
			this.knightX = this.knight.getX();
			this.knightY = this.knight.getY();
			knight.update(dt);
		}
		
		dayNight.update(time);
		island.update(time, dayNight.isNight());
		
		cam.update();
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		island.render(sb);

		splashMonitor.render(sb, SplashMonitor.BACKGROUND);
		
		if (avatar.isFalling() && (avatar.getDirection() == Direction.LEFT || avatar.getDirection() == Direction.RIGHT)) {
			sb.draw(avatar.getTexture(), avatar.getX(), avatar.getY(), avaFallWidth / 2, avaFallHeight / 2,
					avaFallWidth, avaFallHeight, 1, 1, avatar.getRotation(), 0, 0, avatar.getTexture().getWidth(),
					avatar.getTexture().getHeight(), false, false);
		}
		else {
			sb.draw(avatar.getTexture(), avatar.getX(), avatar.getY());
		}
		
		sb.draw(knight.getTexture(), knight.getX(), knight.getY());
		
		splashMonitor.render(sb, SplashMonitor.FOREGROUND);
		
		BitmapFont font = new BitmapFont();
		if (this.stats) {
			String x = Integer.toString((int)avatar.getX());
			String y = Integer.toString((int)avatar.getY());
			String t = Float.toString((float)this.time);
			font.draw(sb, x + ", " + y, cam.position.x - (cam.viewportWidth / 2) + 10,
					cam.position.y + (cam.viewportHeight / 2) - 10);
			font.draw(sb, t, cam.position.x - (cam.viewportWidth / 2) + 10,
					cam.position.y + (cam.viewportHeight / 2) - 30);
		}
		
		String w = Integer.toString((int)avatar.getHealth());
		font.draw(sb, w, cam.position.x - (cam.viewportWidth / 2) + 10, cam.position.y - (cam.viewportHeight / 2) + 30);
		
		sb.end();
	}
	
	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}
}
