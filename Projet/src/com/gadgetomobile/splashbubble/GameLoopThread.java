package com.gadgetomobile.splashbubble;

import java.util.List;
import java.util.Random;
import android.graphics.Canvas;

public class GameLoopThread extends Thread {

	static final long FPS = 20;

	private GameView view;

	private boolean running = false;
	
	List<Sprite> sprites;
	public GameLoopThread(GameView view) {
		this.view = view;
		sprites = view.getSprites();
	}

	public void setRunning(boolean run) {
		running = run;
	}
	
	public boolean isRunning() {
		return running;
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
			
			// Création des nouvelles bulles
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
			
			// Dessin
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
			
			// Attente pour bon fps
			sleepTime = ticksPS - (System.currentTimeMillis() - startTime);

			try {
				if (sleepTime > 0)
					sleep(sleepTime);
				else
					sleep(10);
			} catch (Exception e) {}
		}
	}

	public boolean touchEvent(float x, float y) {
		synchronized (view.getHolder()) {
			for (int i = sprites.size() - 1; i >= 0; i--) {
				Sprite sprite = sprites.get(i);
				if (sprite.isCollision(x, y)) {
					sprites.remove(sprite);
					//temps.add(new TempSprite(temps, this, x, y, bmpBlood));
					view.updateScore(1);
					break;
				}
			}
		}
		return true;
	}
}