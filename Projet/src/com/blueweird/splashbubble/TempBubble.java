package com.blueweird.splashbubble;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class TempBubble extends Bubble {

	private int life;

	List<Bubble> sprites;
	
	public TempBubble(GameView v, Bitmap bmp, int type, int life) {
		super(v, bmp, type);
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