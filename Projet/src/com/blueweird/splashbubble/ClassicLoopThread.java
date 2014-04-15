package com.blueweird.splashbubble;

import java.util.Random;

public class ClassicLoopThread extends GameLoopThread {

	private double spawnPerSecond = 1;
	private double spawnRate = 100 * spawnPerSecond / FPS;

	private Random rnd;
	private int toss;
	
	public ClassicLoopThread(GameView v) {
		super(v);
		rnd = new Random();
	}

	public boolean touchEvent(float x, float y) {
		if(running) {
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
		}
		return true;
	}

	@Override
	public void loop() {
		toss = rnd.nextInt(100);
		
		// Création des nouvelles bulles
		if(toss < spawnRate) {
			int bubble_color = rnd.nextInt(4);
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
			default:
//            	view.addSprite(R.drawable.ic_launcher);
            	break;
			}
		}
	}
}