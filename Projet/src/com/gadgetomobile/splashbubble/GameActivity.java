package com.gadgetomobile.splashbubble;

import java.io.FileNotFoundException;

import android.content.res.Resources.NotFoundException;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class GameActivity extends BaseActivity {

    /** Called when the activity is first created. */
	GameView view;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(view = new GameView(this));
    }
    
    @Override
    public void onPause() {
    	super.onPause();
    	view.stop();
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
		if(!view.isRunning())
			view.start();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_play_pause:
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
		        if (view.isRunning()) {
		        	item.setIcon(android.R.drawable.ic_media_play);
		        	view.stop();
		        }
		        else {
		        	item.setIcon(android.R.drawable.ic_media_pause);
		        	view.start();
		        }
		    }
			else {
				view.start();
			}
			return true;
		case R.id.action_settings:
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
    @Override
    public void onBackPressed() {
    	view.stop();
    	// Sauvegarde des scores
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