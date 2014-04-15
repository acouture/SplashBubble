package com.blueweird.splashbubble;

import java.util.List;

import android.graphics.Canvas;

public abstract class GameLoopThread extends Thread {

	static final long FPS = 20;

	protected GameView view;
	protected List<Sprite> sprites;
	protected boolean running;

	protected long ticksPS = 1000 / FPS;
	protected long startTime;
	protected long sleepTime;
	protected Canvas c = null;
	
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
	public abstract void loop();
	
	protected void draw() {
		try {
			c = view.getHolder().lockCanvas();
			synchronized (view.getHolder()) {
				view.draw(c);
			}
		} finally {
			if (c != null) {
				view.getHolder().unlockCanvasAndPost(c);
			}
		}
	}
	
	protected void loopStart() {
		startTime = System.currentTimeMillis();
	}
	
	protected void loopEnd() {
		// Attente pour avoir le bon nombre de FPS
		sleepTime = ticksPS - (System.currentTimeMillis() - startTime);

		try {
			if (sleepTime > 0)
				sleep(sleepTime);
			else
				sleep(10);
		} catch (Exception e) {}
	}

	@Override
	public void run() {
		while(running) {
			loopStart();
			loop();
			draw();
			loopEnd();
		}
		
	}
}
