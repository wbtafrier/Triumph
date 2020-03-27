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
import com.mygdx.game.map.TestIsland;
import com.mygdx.game.util.DayNight;
import com.mygdx.game.util.Direction;

public class PlayState extends State {
	DayNight dayNight;
	Avatar player;
	Knight knight;
	TestIsland island;
	boolean stats = false;
	float dt, time = 0;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		cam.setToOrtho(false, MyGdxGame.WIDTH / 3, MyGdxGame.HEIGHT / 3);
		dayNight = new DayNight(DayNight.DAY);
		player = new Avatar(ResourceManager.front);
		island = new TestIsland(dayNight.isNight());
		
		knight = new Knight(ResourceManager.front);
		knight.setCoords(TestIsland.randomX(), TestIsland.randomY());
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
		
		if (!player.isFalling()) {
			if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT) || Gdx.input.isKeyPressed(Keys.A)) {
				player.setDirection(Direction.LEFT);
				player.subX(dt * player.getSpeed());
				moving = true;
			}
			if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {
				player.setDirection(Direction.RIGHT);
				player.addX(dt * player.getSpeed());
				moving = true;
			}
			if (Gdx.input.isKeyPressed(Keys.DPAD_UP) || Gdx.input.isKeyPressed(Keys.W)) {
				player.setDirection(Direction.UP);
				player.addY(dt * player.getSpeed());
				moving = true;
			}
			if (Gdx.input.isKeyPressed(Keys.DPAD_DOWN) || Gdx.input.isKeyPressed(Keys.S)) {
				player.setDirection(Direction.DOWN);
				player.subY(dt * player.getSpeed());
				moving = true;
			}
			if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
				player.triggerStrike();
			}
		}
		else {
			moving = true;
		}
		player.setMoving(moving);
	}
	
	public boolean checkAndHandleFalls(Avatar avatar, float dt) {
		boolean falling = false;
		
		if (avatar.getX() < TestIsland.WEST_BEACH) {
			avatar.setDirection(Direction.LEFT);
			avatar.subX(avatar.getFallSpeed());
			avatar.subY(avatar.getFallSpeed());
			avatar.addRotation(0.5f);
			
			if (avatar.getX() < TestIsland.WEST_SHORE && avatar.getX() > TestIsland.WEST_EDGE) {
				/* splash testing code */
				avatar.addSplashToMonitor(avatar.getX(), avatar.getY() - 5);
			}
			
			if (avatar.getX() < TestIsland.WEST_EDGE) {
				avatar.reset();
			}
			else if (!falling) {
				falling = true;
			}
		}
		if (avatar.getY() < TestIsland.SOUTH_BEACH) {
			avatar.subY(avatar.getFallSpeed());
			if (avatar.getY() < TestIsland.SOUTH_SHORE) {
				avatar.reset();
			}
			else if (!falling) {
				falling = true;
			}
		}
		if (avatar.getX() > TestIsland.EAST_BEACH) {
			avatar.setDirection(Direction.RIGHT);
			avatar.addX(avatar.getFallSpeed());
			avatar.subY(avatar.getFallSpeed());
			avatar.subRotation(0.5f);
			
			if (avatar.getX() > TestIsland.EAST_SHORE && avatar.getX() < TestIsland.EAST_EDGE) {
				/* splash testing code */
				avatar.addSplashToMonitor(avatar.getX(), avatar.getY() - 5);
			}
			
			if (avatar.getX() > TestIsland.EAST_EDGE) {
				avatar.reset();
			}
			else if (!falling) {
				falling = true;
			}
		}
		if (avatar.getY() > TestIsland.NORTH_BEACH) {
			avatar.addY(avatar.getFallSpeed());
			if (avatar.getY() > TestIsland.NORTH_SHORE) {
				avatar.reset();
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
		
		if (player != null) {
			handleInput();
			if (player.isStriking() && Math.abs(player.getX() - knight.getX()) < 25 && Math.abs(player.getY() - knight.getY()) < 25) {
				knight.changeHealth(-1);
				knight.setUnderAttack(true);
			}
			else if (knight.isUnderAttack()) {
				knight.setUnderAttack(false);
			}
			
			falling = checkAndHandleFalls(player, dt);
			this.player.setFalling(falling);
			player.update(dt);
			cam.translate(player.getX() - cam.position.x, player.getY() - cam.position.y);
		}
		
		if (knight != null) {
			if (Math.abs(player.getX() - knight.getX()) < 25 && Math.abs(player.getY() - knight.getY()) < 25) {
				knight.triggerStrike();
				player.changeHealth(-1);
				player.setUnderAttack(true);
			}
			else if (knight.isStriking()) {
				knight.stopStriking();
				player.setUnderAttack(false);
			}
			
			knight.updateAI(dt);
			falling = checkAndHandleFalls(knight, dt);
			knight.setFalling(falling);
			
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

		player.getSplashMonitor().render(sb, SplashMonitor.BACKGROUND);
		
		if (player.isFalling() && (player.getDirection() == Direction.LEFT || player.getDirection() == Direction.RIGHT)) {
			sb.draw(player.getTexture(), player.getX(), player.getY(), Avatar.fallWidth / 2, Avatar.fallHeight / 2,
					Avatar.fallWidth, Avatar.fallHeight, 1, 1, player.getRotation(), 0, 0, player.getTexture().getWidth(),
					player.getTexture().getHeight(), false, false);
		}
		else {
			sb.draw(player.getTexture(), player.getX(), player.getY());
		}
		
		player.getSplashMonitor().render(sb, SplashMonitor.FOREGROUND);
		
		knight.getSplashMonitor().render(sb, SplashMonitor.BACKGROUND);
		
		if (knight.isFalling() && (knight.getDirection() == Direction.LEFT || knight.getDirection() == Direction.RIGHT)) {
			sb.draw(knight.getTexture(), knight.getX(), knight.getY(), Avatar.fallWidth / 2, Avatar.fallHeight / 2,
					Avatar.fallWidth, Avatar.fallHeight, 1, 1, knight.getRotation(), 0, 0, knight.getTexture().getWidth(),
					knight.getTexture().getHeight(), false, false);
		}
		else {
			sb.draw(knight.getTexture(), knight.getX(), knight.getY());
		}
		
		knight.getSplashMonitor().render(sb, SplashMonitor.FOREGROUND);
		
		BitmapFont font = new BitmapFont();
		if (this.stats) {
			String x = Integer.toString((int)player.getX());
			String y = Integer.toString((int)player.getY());
			String t = Float.toString((float)this.time);
			font.draw(sb, x + ", " + y, cam.position.x - (cam.viewportWidth / 2) + 10,
					cam.position.y + (cam.viewportHeight / 2) - 10);
			font.draw(sb, t, cam.position.x - (cam.viewportWidth / 2) + 10,
					cam.position.y + (cam.viewportHeight / 2) - 30);
		}
		
		String h = Integer.toString((int)player.getHealth());
		font.draw(sb, h, player.getX(), player.getY() + Avatar.fallHeight + 20);
		
		h = Integer.toString((int)knight.getHealth());
		font.draw(sb, h, knight.getX(), knight.getY() + Avatar.fallHeight + 20);
		
		sb.end();
	}
	
	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}
}
