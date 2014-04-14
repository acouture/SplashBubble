package com.blueweird.splashbubble.activities;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.blueweird.splashbubble.R;

public class HighScoresActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_high_scores);
		String filename = null;
		
		switch(getGameplay()) {
		case 0:
			filename = getResources().getString(R.string.highScoresInfinite);
			break;
		case 1:
			filename = getResources().getString(R.string.highScoresClassic);
			break;
		default:
			// TODO: Afficher un dialog d'erreur
			finish();
		}
		
		Integer[] scores = null;
		scores = getHighScores(filename);
		TextView textView;

		if(scores != null) {
			textView = (TextView) findViewById(R.id.score1);
			textView.setText(scores[0].toString());
			textView = (TextView) findViewById(R.id.score2);
			textView.setText(scores[1].toString());
			textView = (TextView) findViewById(R.id.score3);
			textView.setText(scores[2].toString());
			textView = (TextView) findViewById(R.id.score4);
			textView.setText(scores[3].toString());
			textView = (TextView) findViewById(R.id.score5);
			textView.setText(scores[4].toString());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.high_scores, menu);
		return true;
	}
}
