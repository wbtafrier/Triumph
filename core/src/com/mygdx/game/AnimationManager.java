package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationManager {

	public static Animation<Texture> leftWalk;
	public static Animation<Texture> rightWalk;
	public static Animation<Texture> backWalk;
	public static Animation<Texture> frontWalk;
	public static Animation<Texture> waterFlow;

	public static void initializeAnimations() {
		Texture[] left = new Texture[] { ResourceManager.left, ResourceManager.leftWalk1, ResourceManager.leftWalk2 };
		leftWalk = new Animation<Texture>(0.1f, left);

		Texture[] right = new Texture[] { ResourceManager.right, ResourceManager.rightWalk1, ResourceManager.rightWalk2 };
		rightWalk = new Animation<Texture>(0.1f, right);
		
		Texture[] back = new Texture[] { ResourceManager.back, ResourceManager.backWalk1, ResourceManager.backWalk2 };
		backWalk = new Animation<Texture>(0.1f, back);
		
		Texture[] front = new Texture[] { ResourceManager.front, ResourceManager.frontWalk1, ResourceManager.frontWalk2 };
		frontWalk = new Animation<Texture>(0.1f, front);
		
		Texture[] water = new Texture[] { ResourceManager.water1, ResourceManager.water2, ResourceManager.water3,
											ResourceManager.water2 };
		waterFlow = new Animation<Texture>(0.5f, water);
	}
}
