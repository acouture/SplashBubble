package com.example.splashbubblev2;

import android.content.Context;

public class GameController {
	
	GameActivity activity;
	GameView gameView;

	private RunningThread thread;
	
	public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;
	
	public GameController(Context context, GameView gameView) {
		activity = (GameActivity) context;
		this.gameView = gameView;

		thread = new RunningThread(this);
		
		SCREEN_WIDTH = context.getResources().getDisplayMetrics().widthPixels;
		SCREEN_HEIGHT = context.getResources().getDisplayMetrics().heightPixels;
	}

	public GameView getGameView() {
		return gameView;
	}
	
	public boolean isGameRunning() {
		return thread.isRunning();
	}
}
