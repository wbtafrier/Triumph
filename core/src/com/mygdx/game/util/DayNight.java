package com.mygdx.game.util;

public class DayNight {
	public static final int DAY = 0, NIGHT = 1;
	public int phase = DAY, lastSwitchTime = 0;
	
	public DayNight(int phase) {
		this.phase = phase;
	}
	
	public void update(float time) {
		int timeInt = (int)time;
		if (timeInt % 24 == 0 && this.lastSwitchTime != timeInt) {
			this.lastSwitchTime = timeInt;
			this.phase = !isNight() ? NIGHT : DAY;
		}
	}
	
	public int getPhase() {
		return this.phase;
	}
	
	public boolean isNight() {
		return this.phase == NIGHT;
	}
}
