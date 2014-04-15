package com.blueweird.splashbubble;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class TempSprite extends Sprite {

	private int life;

	List<Sprite> sprites;
	
	public TempSprite(GameView v, Bitmap bmp, int life) {
		super(v, bmp);
		this.life = life;
	}

	public void draw(Canvas canvas) {
		update();
		canvas.drawBitmap(bmp, x, y, null);
	}

	protected void update() {
		super.updateSprite();
		life--;
	}
	
	public boolean isDead() {
		if(life < 0)
			return true;
		return false;
	}
}