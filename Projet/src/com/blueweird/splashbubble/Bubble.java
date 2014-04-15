package com.blueweird.splashbubble;

import java.util.Random;

import android.graphics.Bitmap;

import android.graphics.Canvas;

public class Bubble {

	protected static final int MAX_SPEED = 8;

	protected GameView gameView;
	protected Bitmap bmp;

	protected int x = 0;
	protected int y = 0;
	protected int xSpeed;
	protected int ySpeed;
	protected int width;
	protected int height;
	protected int type;

	public Bubble(GameView gameView, Bitmap bmp, int type) {
		this.width = bmp.getWidth();
		this.height = bmp.getHeight();
		this.gameView = gameView;
		this.bmp = bmp;
		this.type = type;

		Random rnd = new Random();
		// Les bulles vont poper hors de l'écran puis rentrer
		// Choix du coté (gauche / bas / droite / haut)
		int side = rnd.nextInt(4);
		
		if((side % 2) == 0) {
			y = rnd.nextInt(gameView.getHeight() - height);
			ySpeed = rnd.nextInt(MAX_SPEED * 2) - MAX_SPEED;
			if(side == 0) {
				x = -width;
				xSpeed = rnd.nextInt(MAX_SPEED);
			}
			else {
				x = gameView.getWidth();
				xSpeed = -rnd.nextInt(MAX_SPEED);
			}
		}
		else {
			x = rnd.nextInt(gameView.getWidth() - width);
			xSpeed = rnd.nextInt(MAX_SPEED * 2) - MAX_SPEED;
			if(side == 1) {
				y = gameView.getHeight();
				ySpeed = -rnd.nextInt(MAX_SPEED);
			}
			else {
				y = -height;
				ySpeed = rnd.nextInt(MAX_SPEED);
			}
		}
	}

	protected void updateSprite() {
		if(x + xSpeed <= 0 && xSpeed < 0)
			xSpeed = -xSpeed;
		if (x + xSpeed >= gameView.getWidth() - width && xSpeed > 0)
			xSpeed = -xSpeed;

		x = x + xSpeed;

		if(y + ySpeed <= 0 && ySpeed < 0)
			ySpeed = -ySpeed;
		if (y + ySpeed >= gameView.getHeight() - height && ySpeed > 0)
			ySpeed = -ySpeed;

		y = y + ySpeed;
	}

	protected void update() {
		updateSprite();
	}
	
	public void draw(Canvas canvas) {
		update();
		canvas.drawBitmap(bmp, x, y, null);
	}

	public boolean isCollision(float x2, float y2) {
		return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
	}
	
	public int getType() {
		return type;
	}
}