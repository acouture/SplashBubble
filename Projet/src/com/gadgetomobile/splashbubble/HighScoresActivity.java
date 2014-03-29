package com.gadgetomobile.splashbubble;

import java.io.FileNotFoundException;

import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HighScoresActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_high_scores);

		Integer[] scores = null;
		try {
			scores = GoodFuncs.getHighScores(openFileInput(getResources().getString(R.string.highScoresFile)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
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
		Button back = (Button) findViewById(R.id.buttonBack);
		back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.high_scores, menu);
		return true;
	}
}
