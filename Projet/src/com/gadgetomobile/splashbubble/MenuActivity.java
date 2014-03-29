package com.gadgetomobile.splashbubble;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		Button quit = (Button) findViewById(R.id.buttonQuit);
		quit.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		
		Button play = (Button) findViewById(R.id.buttonPlay);
		play.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity.this, GameActivity.class);
				startActivity(intent);
			}
		});
		
		Button scores = (Button) findViewById(R.id.buttonScores);
		scores.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity.this, HighScoresActivity.class);
				startActivity(intent);
			}
		});
		
		try {
			FileInputStream file = openFileInput(getResources().getString(R.string.highScoresFile));
			file.close();
		} catch (FileNotFoundException e) {
			FileOutputStream file;
			try {
				file = openFileOutput(getResources().getString(R.string.highScoresFile), MODE_PRIVATE);
				file.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (NotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

}
