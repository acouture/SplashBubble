package com.blueweird.splashbubble.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blueweird.splashbubble.ClassicLoopThread;
import com.blueweird.splashbubble.GameView;
import com.blueweird.splashbubble.R;

public class GameClassicActivity extends GameActivity {

    /** Called when the activity is first created. */
	GameView view;
	ImageButton buttonPlayPause;
	TextView scoreValue;
	TextView bonusValue;
	TextView lastColor;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_classic);
        ((RelativeLayout) findViewById(R.id.game_view_layout)).addView(view = new GameView(this, GameClassicActivity.this));
        
        scoreValue = (TextView) findViewById(R.id.score_value);
        bonusValue = (TextView) findViewById(R.id.bonus_value);
        lastColor = (TextView) findViewById(R.id.last_color_value);
        
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
    	saveScore();
    	finish();
    }
    
    private void saveScore() {
    	String filename = null;
    	switch (getGameplay()) {
		case 0:
			filename = getResources().getString(R.string.highScoresInfinite);
			break;
		case 1:
			filename = getResources().getString(R.string.highScoresClassic);
			break;
		default:
			break;
		}
    	if(filename != null)
    		setHighScores(getHighScores(filename), filename, view.getScore());
    }
    
    public void updateScore() {
    	scoreValue.setText(view.getScore().toString());
    	ClassicLoopThread clt = (ClassicLoopThread) view.getGameLoopThread();
    	Integer b = clt.getBonus() + 1;
    	bonusValue.setText(b.toString());
    	String str;
    	switch(clt.getLastColor()) {
    	case 0:
    		str = "Bleu";
    		break;
    	case 1:
    		str = "Vert";
    		break;
    	case 2:
    		str = "Rouge";
    		break;
    	case 3:
    		str = "Jaune";
    		break;
    	default:
    		str = "Aucune";
    	}
    	lastColor.setText(str);
	}
}