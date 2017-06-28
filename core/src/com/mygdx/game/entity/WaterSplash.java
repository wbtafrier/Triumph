package com.mygdx.game.entity;

import com.mygdx.game.ResourceManager;

public class WaterSplash extends Splash {
	
	public WaterSplash(float x, float y) {
		super(x, y);
	}

	@Override
	public void update(float dt) {
		super.update(dt);
		
		if (this.texture == null) {
			int r = SplashMonitor.rand.nextInt(6);

			if (r == 0)
				this.texture = ResourceManager.waterSplash1;
			else if (r == 1)
				this.texture = ResourceManager.waterSplash2;
			else if (r == 2)
				this.texture = ResourceManager.waterSplash3;
			else if (r == 3)
				this.texture = ResourceManager.waterSplash4;
			else if (r == 4)
				this.texture = ResourceManager.waterSplash5;
			else if (r == 5)
				this.texture = ResourceManager.waterSplash6;
		}
		
		//temporary
		if (time >= 0.5) {
			this.kill();
		}
	}

	@Override
	public int getType() {
		return Splash.WATER;
	}

}
