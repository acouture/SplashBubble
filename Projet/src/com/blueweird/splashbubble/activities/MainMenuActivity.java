package com.blueweird.splashbubble.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.blueweird.splashbubble.R;

public class MainMenuActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		
		Button infiniteMode = (Button) findViewById(R.id.buttonInfinite);
		infiniteMode.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				setGameplay(0);
				Intent intent = new Intent(MainMenuActivity.this, GamePlayActivity.class);
				startActivity(intent);
			}
		});
		
		Button classicMode = (Button) findViewById(R.id.buttonClassic);
		classicMode.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				setGameplay(1);
				Intent intent = new Intent(MainMenuActivity.this, GamePlayActivity.class);
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
