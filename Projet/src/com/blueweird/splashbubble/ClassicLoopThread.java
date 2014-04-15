package com.blueweird.splashbubble;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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
			Bitmap bmp = null;
			int life = 5 * FPS;
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
				System.out.println("no bulle");
            	break;
			}
			if(bmp != null)
				sprites.add(new TempSprite(view, bmp, life));
		}
		
		// Suppression des bulles mortent
		for(int i = 0; i < sprites.size(); i++) {
			TempSprite sprite = (TempSprite) sprites.get(i);
			if(sprite.isDead())
				sprites.remove(sprite);
		}
	}
}