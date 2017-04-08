package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;

public class ResourceManager {
	private static ArrayList<Texture> textureList = new ArrayList<Texture>();
	
	public Texture back;
	public Texture backWalk1;
	public Texture backWalk2;
	public Texture front;
	public Texture frontWalk1;
	public Texture frontWalk2;
	public Texture left;
	public Texture leftWalk1;
	public Texture leftWalk2;
	public Texture right;
	public Texture rightWalk1;
	public Texture rightWalk2;
	
	public ResourceManager() {
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
	}
	
	public ArrayList<Texture> getTextureList() {
		return textureList;
	}
}
