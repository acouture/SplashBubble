package com.example.splashbubblev2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class GameActivity extends Activity {

	private static final int ACTIVITY_CODE = 1;
	
	GameController gameController;
	GameView gameView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		gameView = new GameView(this);
		gameController = new GameController(this, gameView);
		setContentView(gameView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public GameController getController() {
		return gameController;
	}
	
	@Override
	protected void onPause() {
		
	}
}
