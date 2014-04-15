package com.blueweird.splashbubble;

import java.util.List;

public abstract class GameLoopThread extends Thread {

	static final long FPS = 20;

	protected GameView view;
	protected List<Sprite> sprites;
	protected boolean running;
	
	public GameLoopThread(GameView v) {
		view = v;
		running = false;
		sprites = view.getSprites();
	}
	
	@Override
	public synchronized void start() {
		super.start();
		running = true;
	}
	
	public void stopRequest() {
		running = false;
	}
	
	public abstract boolean touchEvent(float x, float y);
}
