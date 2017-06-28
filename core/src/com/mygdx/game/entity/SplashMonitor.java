package com.mygdx.game.entity;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashMonitor {
	
	public static final int FOREGROUND = 0, BACKGROUND = 1;
	
	protected static Random rand = new Random();
	
	private ArrayList<Splash> backList;
	private ArrayList<Splash> frontList;
	
	public SplashMonitor() {
		backList = new ArrayList<Splash>();
		frontList = new ArrayList<Splash>();
	}
	
	public void add(Splash s, int plane) {
		if (plane == BACKGROUND) {
			backList.add(s);
		} else {
			frontList.add(s);
		}
	}

	public void update(float dt) {
		int i = 0;
		
		for (; i < backList.size(); i++) {
			if (!backList.get(i).isAlive()) {
				backList.remove(i);
				i--;
			} else {
				backList.get(i).update(dt);
			}
		}
		
		for (i = 0; i < frontList.size(); i++) {
			if (!frontList.get(i).isAlive()) {
				frontList.remove(i);
				i--;
			} else {
				frontList.get(i).update(dt);
			}
		}
	}
	
	public void render(SpriteBatch sb, int plane) {
		if (plane == BACKGROUND) {
			for (Splash s : backList) {
				Texture t = s.getTexture();
				int width = t.getWidth();
				int height = t.getHeight();
				
				sb.draw(t, s.getX(), s.getY(), width / 2, height / 2,
						width, height, 1, 1, s.getRotation(), 0, 0, width,
						height, false, false);
			}
		} else {
			for (Splash s : frontList) {
				Texture t = s.getTexture();
				int width = t.getWidth();
				int height = t.getHeight();
				
				sb.draw(t, s.getX(), s.getY(), width / 2, height / 2,
						width, height, 1, 1, s.getRotation(), 0, 0, width,
						height, false, false);
			}
		}
	}
	
	public void clearAll() {
		backList.clear();
		frontList.clear();
	}
	
	public void clearAll(int plane) {
		if (plane == BACKGROUND) {
			backList.clear();
		} else if (plane == FOREGROUND) {
			frontList.clear();
		}
	}
	
	public void clear(int splashType) {
		for (Splash s : backList) {
			if (s.getType() == splashType) {
				s.kill();
			}
		}
		
		for (Splash s : frontList) {
			if (s.getType() == splashType) {
				s.kill();
			}
		}
	}
	
	public void clear(int splashType, int plane) {
		if (plane == BACKGROUND) {
			for (Splash s : backList) {
				if (s.getType() == splashType) {
					s.kill();
				}
			}
		} else {
			for (Splash s : frontList) {
				if (s.getType() == splashType) {
					s.kill();
				}
			}
		}
	}
}
