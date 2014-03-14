package com.gadgetomobile.splashbubble;

import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.view.Menu;

public class GameActivity extends Activity {

    /** Called when the activity is first created. */
	GameView view;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(view = new GameView(this));
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}
	
	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		view.stop();
		return true;
	}
	
	@Override
	public void onOptionsMenuClosed(Menu menu) {
		view.start();
	}
	
    @Override
    public void onBackPressed() {
    	view.stop();
    	try {
			GoodFuncs.setHighScores(GoodFuncs.getHighScores(openFileInput(getResources().getString(R.string.highScoresFile))),
					openFileOutput(getResources().getString(R.string.highScoresFile), MODE_PRIVATE), view.getScore());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
    	finish();
    }
}