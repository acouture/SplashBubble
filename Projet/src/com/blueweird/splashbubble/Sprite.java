package com.blueweird.splashbubble;

import java.util.Random;

import android.graphics.Bitmap;

import android.graphics.Canvas;

public class Sprite {

	private static final int MAX_SPEED = 8;

	private GameView gameView;
	private Bitmap bmp;

	private int x = 0;
	private int y = 0;
	private int xSpeed;
	private int ySpeed;
	private int width;
	private int height;

	public Sprite(GameView gameView, Bitmap bmp) {
		this.width = bmp.getWidth();
		this.height = bmp.getHeight();
		this.gameView = gameView;
		this.bmp = bmp;

		Random rnd = new Random();
		// Les bulles vont poper hors de l'�cran puis rentrer
		// Choix du cot� (gauche / bas / droite / haut)
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

	private void update() {
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

	public void draw(Canvas canvas) {
		update();
		canvas.drawBitmap(bmp, x, y, null);
	}

	public boolean isCollision(float x2, float y2) {
		return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
	}
}