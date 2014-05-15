package com.blueweird.splashbubble.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.blueweird.splashbubble.R;

public class GamePlayActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		switch(getGameplay()) {
		case 0:
			setContentView(R.layout.activity_infinite_menu);
			break;
		case 1:
			setContentView(R.layout.activity_classic_menu);
			break;
		default:
			// TODO: Afficher un dialog d'erreur
			finish();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.high_scores_game_play, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void infiniteGame(View v) {
		Intent intent = new Intent(GamePlayActivity.this, GameInfiniteActivity.class);
		startActivity(intent);
	}
	
	public void infiniteHighScores(View v) {
		Intent intent = new Intent(GamePlayActivity.this, HighScoresActivity.class);
		startActivity(intent);
	}
	
	public void classicGame(View v) {
		Intent intent = new Intent(GamePlayActivity.this, GameClassicActivity.class);
		startActivity(intent);
	}
	
	public void classicHighScores(View v) {
		Intent intent = new Intent(GamePlayActivity.this, HighScoresActivity.class);
		startActivity(intent);
	}
}
