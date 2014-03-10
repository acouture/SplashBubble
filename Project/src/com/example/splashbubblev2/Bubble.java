package com.example.splashbubblev2;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Bubble {

	private GameView gameView;

	private static final int MAX_SPEED = 8;

	private EBubbleType bubbleType;
	private Bitmap sprite;

	private int x;
	private int y;
	private int width;
	private int height;
	private int xSpeed;
	private int ySpeed;

	public Bubble(GameView gameView, EBubbleType bubbleType) {

		this.gameView = gameView;
		setBubbleType(bubbleType);

		// position aléatoire
		Random rnd = new Random();

		// gameView.getController();
		x = rnd.nextInt(GameController.SCREEN_WIDTH - width);
		y = rnd.nextInt(GameController.SCREEN_HEIGHT - height);

		// deplacement aléatoire
		xSpeed = rnd.nextInt(MAX_SPEED * 2) - MAX_SPEED;
		ySpeed = rnd.nextInt(MAX_SPEED * 2) - MAX_SPEED;
	}

	private void setBubbleType(EBubbleType type) {

		bubbleType = type;

		switch (bubbleType) {
		case BLUE:
			setSprite(BitmapFactory.decodeResource(gameView.getResources(),
					R.drawable.bubble_blue));
			break;
		case GREEN:
			setSprite(BitmapFactory.decodeResource(gameView.getResources(),
					R.drawable.bubble_green));
			break;
		case RED:
			setSprite(BitmapFactory.decodeResource(gameView.getResources(),
					R.drawable.bubble_red));
			break;
		case YELLOW:
			setSprite(BitmapFactory.decodeResource(gameView.getResources(),
					R.drawable.bubble_yellow));
			break;
		default:
			break;
		}

	}

	private void setSprite(Bitmap bmp) {
		sprite = bmp;
		width = bmp.getWidth();
		height = bmp.getHeight();
	}

	private void move() {

		if (x >= gameView.getWidth() - width - xSpeed || x + xSpeed <= 0) {
			xSpeed = -xSpeed;
		}

		x = x + xSpeed;

		if (y >= gameView.getHeight() - height - ySpeed || y + ySpeed <= 0) {
			ySpeed = -ySpeed;
		}

		y = y + ySpeed;

	}
	
	public void draw(Canvas canvas) {
		
		move();
        canvas.drawBitmap(sprite, x, y, null);
        
	}
}
