package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;

public class ResourceManager {
	private static ArrayList<Texture> textureList = new ArrayList<Texture>();
	
	public static Texture back;
	public static Texture backWalk1;
	public static Texture backWalk2;
	public static Texture front;
	public static Texture frontWalk1;
	public static Texture frontWalk2;
	public static Texture left;
	public static Texture leftWalk1;
	public static Texture leftWalk2;
	public static Texture right;
	public static Texture rightWalk1;
	public static Texture rightWalk2;
	public static Texture testIsland;
	public static Texture testIslandNight;
	public static Texture water1;
	public static Texture water2;
	public static Texture water3;
	public static Texture water1Night;
	public static Texture water2Night;
	public static Texture water3Night;
	public static Texture mainMenu;
	public static Texture backFall;
	public static Texture frontFall;
	public static Texture leftFall;
	public static Texture rightFall;
	public static Texture waterSplash1;
	public static Texture waterSplash2;
	public static Texture waterSplash3;
	public static Texture waterSplash4;
	public static Texture waterSplash5;
	public static Texture waterSplash6;
	public static Texture sword1;
	public static Texture sword2;
	
	public static void initializeTextures() {
		textureList.add(back = new Texture("back.png"));
		textureList.add(backWalk1 = new Texture("backWalk1.png"));
		textureList.add(backWalk2 = new Texture("backWalk2.png"));
		textureList.add(front = new Texture("front.png"));
		textureList.add(frontWalk1 = new Texture("frontWalk1.png"));
		textureList.add(frontWalk2 = new Texture("frontWalk2.png"));
		textureList.add(left = new Texture("left.png"));
		textureList.add(leftWalk1 = new Texture("leftWalk1.png"));
		textureList.add(leftWalk2 = new Texture("leftWalk2.png"));
		textureList.add(right = new Texture("right.png"));
		textureList.add(rightWalk1 = new Texture("rightWalk1.png"));
		textureList.add(rightWalk2 = new Texture("rightWalk2.png"));
		textureList.add(testIsland = new Texture("testIsland.png"));
		textureList.add(testIslandNight = new Texture("testIslandNight.png"));
		textureList.add(water1 = new Texture("water1.png"));
		textureList.add(water2 = new Texture("water2.png"));
		textureList.add(water3 = new Texture("water3.png"));
		textureList.add(water1Night = new Texture("water1Night.png"));
		textureList.add(water2Night = new Texture("water2Night.png"));
		textureList.add(water3Night = new Texture("water3Night.png"));
		textureList.add(mainMenu = new Texture("mainMenu.png"));
		textureList.add(backFall = new Texture("backFall.png"));
		textureList.add(frontFall = new Texture("frontFall.png"));
		textureList.add(leftFall = new Texture("leftFall.png"));
		textureList.add(rightFall = new Texture("rightFall.png"));
		textureList.add(waterSplash1 = new Texture("waterSplash1.png"));
		textureList.add(waterSplash2 = new Texture("waterSplash2.png"));
		textureList.add(waterSplash3 = new Texture("waterSplash3.png"));
		textureList.add(waterSplash4 = new Texture("waterSplash4.png"));
		textureList.add(waterSplash5 = new Texture("waterSplash5.png"));
		textureList.add(waterSplash6 = new Texture("waterSplash6.png"));
		textureList.add(sword1 = new Texture("sword1.png"));
		textureList.add(sword2 = new Texture("sword2.png"));
	}
	
	public static ArrayList<Texture> getTextureList() {
		return textureList;
	}
}
