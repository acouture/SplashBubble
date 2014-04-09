package com.blueweird.splashbubble.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.blueweird.splashbubble.GamePlayActivity;
import com.blueweird.splashbubble.R;

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
				Intent intent = new Intent(MenuActivity.this, GamePlayActivity.class);
				startActivity(intent);
			}
		});
		
		Button scores = (Button) findViewById(R.id.buttonScores);
		scores.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity.this, HighScoresGamePlayActivity.class);
				startActivity(intent);
			}
		});
		
		// Vérification de l'existence des fichiers
//		try {
//			FileInputStream fileInput = openFileInput(getResources().getString(R.string.highScoresInfiniteFile));
//			fileInput.close();
//		} catch (FileNotFoundException e) {
//			try {
//				FileOutputStream fileOutput = openFileOutput(getResources().getString(R.string.highScoresInfiniteFile), MODE_PRIVATE);
//				fileOutput.close();
//			} catch (FileNotFoundException e1) {
//				e1.printStackTrace();
//			} catch (NotFoundException e1) {
//				e1.printStackTrace();
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//		} catch (NotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

}
