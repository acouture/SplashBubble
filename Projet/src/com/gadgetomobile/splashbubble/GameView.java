package com.gadgetomobile.splashbubble;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView {
	private GameLoopThread gameLoopThread;
	private List<Sprite> sprites = new ArrayList<Sprite>();
	private List<TempSprite> temps = new ArrayList<TempSprite>();
	private Bitmap background;
	private Rect bg;
	private int score;
	private Paint score_paint;

	public GameView(Context context) {
		super(context);
		gameLoopThread = new GameLoopThread(this);
		score = 0;
		score_paint = new Paint();
		score_paint.setTextSize(50);
		getHolder().addCallback(new SurfaceHolder.Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				boolean retry = true;
				gameLoopThread.setRunning(false);
				while (retry) {
					try {
						gameLoopThread.join();
						retry = false;
					} catch (InterruptedException e) {}
				}
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				gameLoopThread.setRunning(true);
				gameLoopThread.start();
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
			}
		});

		background = BitmapFactory.decodeResource(getResources(), R.drawable.background);

		int screen_width = context.getResources().getDisplayMetrics().widthPixels;
		int screen_height = context.getResources().getDisplayMetrics().heightPixels;

		bg = new Rect(0, 0, screen_width, screen_height);
	}

	public void start() {
		gameLoopThread.setRunning(true);
		gameLoopThread.start();
	}

	public void stop() {
		gameLoopThread.setRunning(false);
		gameLoopThread.stop();
	}


	private Sprite createSprite(int resource) {
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
		return new Sprite(this, bmp);
	}

	public void addSprite(int resource) {
		sprites.add(createSprite(resource));
	}

	public void draw(Canvas canvas) {
		canvas.drawBitmap(background, null, bg, null);

		for (int i = temps.size() - 1; i >= 0; i--) {
			temps.get(i).draw(canvas);
		}

		for (Sprite sprite : sprites) {
			sprite.draw(canvas);
		}

		canvas.drawText("Score : " + score, 10, 50, score_paint);
	}

	public boolean onTouchEvent(MotionEvent event) {

		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			System.currentTimeMillis();
			float x = event.getX();
			float y = event.getY();

			synchronized (getHolder()) {
				for (int i = sprites.size() - 1; i >= 0; i--) {
					Sprite sprite = sprites.get(i);
					if (sprite.isCollision(x, y)) {
						sprites.remove(sprite);
						//temps.add(new TempSprite(temps, this, x, y, bmpBlood));
						score++;
						break;
					}
				}
			}
		}
		return true;
	}
	
	public int getScore() {
		return score;
	}
}