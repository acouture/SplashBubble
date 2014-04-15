package com.blueweird.splashbubble;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class InfiniteLoopThread extends GameLoopThread {

	private double spawnPerSecond = 1;
	private double spawnRate = 100 * spawnPerSecond / FPS;

	private Random rnd;
	private int toss;
	
	public InfiniteLoopThread(GameView v) {
		super(v);
		rnd = new Random();
	}

	public boolean touchEvent(float x, float y) {
		if(running) {
			synchronized (view.getHolder()) {
				for (int i = bubbles.size() - 1; i >= 0; i--) {
					Bubble bubble = bubbles.get(i);
					if (bubble.isCollision(x, y)) {
						bubbles.remove(bubble);
						view.updateScore(1);
						break;
					}
				}
			}
		}
		return true;
	}

	@Override
	public void loop() {
		toss = rnd.nextInt(100);
		
		// Création des nouvelles bulles
		if(toss < spawnRate) {
			int bubble_color = rnd.nextInt(4);
			Bitmap bmp = null;
			switch(bubble_color) {
			case 0:
				bmp = BitmapFactory.decodeResource(view.getResources(), R.drawable.bubble_blue);
				break;
			case 1:
				bmp = BitmapFactory.decodeResource(view.getResources(), R.drawable.bubble_green);
				break;
			case 2:
				bmp = BitmapFactory.decodeResource(view.getResources(), R.drawable.bubble_red);
				break;
			case 3:
				bmp = BitmapFactory.decodeResource(view.getResources(), R.drawable.bubble_yellow);
				break;
			default:
            	break;
			}
			if(bmp != null)
				bubbles.add(new Bubble(view, bmp, bubble_color));
		}
	}
}