package com.mygdx.game.map;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.AnimationManager;
import com.mygdx.game.ResourceManager;

public class TestIsland {

	public Texture currentIsland, currentWater;
	
	public static final int WEST_BEACH = 22, EAST_BEACH = 450,
			NORTH_BEACH = 370, SOUTH_BEACH = 40;
	
	public static final int WEST_SHORE = -18, EAST_SHORE = 490,
			NORTH_SHORE = 405, SOUTH_SHORE = -15;
	
	public static final int WEST_EDGE = -38, EAST_EDGE = 510;
			//NORTH_EDGE = ?, SOUTH_EDGE = ?;
	
	final float waterWidth = ResourceManager.water1.getWidth() * 1.15f,
			waterHeight = ResourceManager.water1.getHeight() * 1.25f,
			waterX = -((waterWidth - ResourceManager.testIsland.getWidth()) / 2),
			waterY = -((waterHeight - ResourceManager.testIsland.getHeight()) / 2);
	
	static Random rand = new Random();
	
	public TestIsland(boolean night) {
		currentIsland = !night ? ResourceManager.testIsland : ResourceManager.testIslandNight;
		currentWater = !night ? ResourceManager.water1 : ResourceManager.water1Night;
	}
	
	public void update(float time, boolean night) {
		this.currentIsland = !night ? ResourceManager.testIsland : ResourceManager.testIslandNight;
		this.currentWater = !night ? AnimationManager.waterFlow.getKeyFrame(time, true) : 
			AnimationManager.waterNightFlow.getKeyFrame(time, true);
	}
	
	public void render(SpriteBatch sb) {
		sb.draw(this.currentWater, waterX, waterY, waterWidth, waterHeight);
		sb.draw(this.currentIsland, 0, 0);
	}
	
	public static int randomX() {
		int r = rand.nextInt(EAST_BEACH - WEST_BEACH);
		
		return r + WEST_BEACH;
	}
	
	public static int randomY() {
		int r = rand.nextInt(NORTH_BEACH - SOUTH_BEACH);
		
		return r + SOUTH_BEACH;
	}
	
}
