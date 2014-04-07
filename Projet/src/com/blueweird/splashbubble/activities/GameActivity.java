package com.blueweird.splashbubble.activities;

import java.io.FileNotFoundException;

import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blueweird.splashbubble.GameView;
import com.blueweird.splashbubble.GoodFuncs;
import com.blueweird.splashbubble.R;

public class GameActivity extends BaseActivity {

    /** Called when the activity is first created. */
	GameView view;
	ImageButton buttonPlayPause;
	TextView textScore;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ((RelativeLayout) findViewById(R.id.game_view_layout)).addView(view = new GameView(this, GameActivity.this));
        
        textScore = (TextView) findViewById(R.id.text_score);
        
        buttonPlayPause = (ImageButton) findViewById(R.id.action_play_pause);
        buttonPlayPause.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ImageButton me = (ImageButton) v;
				if (view.isRunning()) {
					me.setImageResource(android.R.drawable.ic_media_play);
					view.stop();
				}
				else {
					me.setImageResource(android.R.drawable.ic_media_pause);
					view.start();
				}
			}
		});
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
		buttonPlayPause.setImageResource(android.R.drawable.ic_media_play);
		view.stop();
		return true;
	}
	
	@Override
	public void onOptionsMenuClosed(Menu menu) {
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
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
    
    public void updateScore() {
    	textScore.setText("Score : " + view.getScore());
	}
}