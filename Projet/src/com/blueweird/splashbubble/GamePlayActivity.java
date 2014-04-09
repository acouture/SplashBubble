package com.blueweird.splashbubble;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.blueweird.splashbubble.activities.BaseActivity;
import com.blueweird.splashbubble.activities.GameActivity;

public class GamePlayActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_play);
		
		Button back = (Button) findViewById(R.id.buttonBack);
		back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		
		Button infinite = (Button) findViewById(R.id.buttonInfinite);
		infinite.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(GamePlayActivity.this, GameActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_play, menu);
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
	
	@Override
	public void onStop() {
		super.onStop();
		finish();
	}
}
