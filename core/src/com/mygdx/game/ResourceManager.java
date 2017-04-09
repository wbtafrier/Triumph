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
	public static Texture water1;
	public static Texture water2;
	public static Texture water3;
	public static Texture mainMenu;
	
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
		textureList.add(water1 = new Texture("water1.png"));
		textureList.add(water2 = new Texture("water2.png"));
		textureList.add(water3 = new Texture("water3.png"));
		textureList.add(mainMenu = new Texture("mainMenu.png"));
	}
	
	public static ArrayList<Texture> getTextureList() {
		return textureList;
	}
}
