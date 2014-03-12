package com.gadgetomobile.splashbubble;

import java.util.Random;
import android.graphics.Canvas;

public class GameLoopThread extends Thread {

	static final long FPS = 20;

	private GameView view;

	private boolean running = false;

	public GameLoopThread(GameView view) {
		this.view = view;
	}

	public void setRunning(boolean run) {
		running = run;
	}

	@Override
	public void run() {
		long ticksPS = 1000 / FPS;
		long startTime;
		long sleepTime;

		Random rnd = new Random();

		long nextSpawnTime = System.currentTimeMillis() + rnd.nextLong() % 4000;

		while (running) {

			Canvas c = null;
			startTime = System.currentTimeMillis();

			if(startTime > nextSpawnTime) {
				int bubble_color = ( rnd.nextInt() % 4 );
				switch(bubble_color) {
				case 0:
					view.addSprite(R.drawable.bubble_blue);
					break;
				case 1:
					view.addSprite(R.drawable.bubble_green);
					break;
				case 2:
					view.addSprite(R.drawable.bubble_red);
					break;
				case 3:
					view.addSprite(R.drawable.bubble_yellow);
					break;
				/*default:
                	view.addSprite(R.drawable.ic_launcher);
                	break;*/
				}

				rnd = new Random();
				nextSpawnTime = System.currentTimeMillis()+ 500 + rnd.nextLong() % 1500;
			}

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

			sleepTime = ticksPS - (System.currentTimeMillis() - startTime);

			try {
				if (sleepTime > 0)
					sleep(sleepTime);
				else
					sleep(10);
			} catch (Exception e) {}
		}
	}
}