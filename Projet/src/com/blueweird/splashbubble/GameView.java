package com.blueweird.splashbubble;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.blueweird.splashbubble.activities.GameActivity;

public class GameView extends SurfaceView {
	private GameActivity gameActivity;
	private GameLoopThread gameLoopThread;
	private List<Bubble> sprites;
	private Bitmap background;
	private Rect bg;
	private int score;

	public GameView(Context context) {
		super(context);
	}
	
	public GameView(Context context, GameActivity parentActivity) {
		super(context);
		this.gameActivity = parentActivity;
		sprites = new ArrayList<Bubble>();
		score = 0;
		
		getHolder().addCallback(new SurfaceHolder.Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				stop();
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				start();
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
		switch(GameActivity.getGameplay()) {
		case 0:
			gameLoopThread = new InfiniteLoopThread(this);
			break;
		case 1:
			gameLoopThread = new ClassicLoopThread(this);
			break;
		}
		gameLoopThread.start();
	}

	public void stop() {
		gameLoopThread.stopRequest();
		boolean retry = true;
		while (retry) {
			try {
				gameLoopThread.join();
				retry = false;
			} catch (InterruptedException e) {}
		}
	}

	public boolean isRunning() {
		return gameLoopThread.isAlive();
	}

	public void draw(Canvas canvas) {
		canvas.drawBitmap(background, null, bg, null);

		for (Bubble sprite : sprites) {
			sprite.draw(canvas);
		}
	}

	public boolean onTouchEvent(MotionEvent event) {

		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			System.currentTimeMillis();
			gameLoopThread.touchEvent(event.getX(), event.getY());
			gameActivity.updateUI();
		}
		return true;
	}
	
	public GameLoopThread getGameLoopThread() {
		return gameLoopThread;
	}
	
	public Integer getScore() {
		return score;
	}
	
	public void updateScore(int toAdd) {
		score += toAdd;
	}
	
	public List<Bubble> getSprites() {
		return sprites;
	}
}