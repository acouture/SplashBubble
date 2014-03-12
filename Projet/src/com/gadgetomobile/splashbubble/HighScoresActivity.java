package com.gadgetomobile.splashbubble;

import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HighScoresActivity extends Activity {

	//	private static int NB_SCORES = 5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_high_scores);

		//		FileInputStream input = null;
		//		Integer[] scores = new Integer[NB_SCORES];
		//		for(int i = 0 ; i < NB_SCORES ; i++)
		//			scores[i] = 0;
		//		
		//		int nbLus = 0;
		//    	try {
		//    		input = openFileInput(getResources().getString(R.string.highScoresFile));
		//    		DataInputStream buf = new DataInputStream(input);
		//    		if(input != null) {
		//    			while(nbLus < NB_SCORES) {
		//    				scores[nbLus] = buf.readInt();
		//    				nbLus++;
		//    			}
		//    		}
		//    		if(input != null)
		//    			input.close();
		//    	} catch (FileNotFoundException e) {
		//    		e.printStackTrace();
		//    	} catch (EOFException e) {
		//    		
		//    	}catch (IOException e) {
		//    		e.printStackTrace();
		//    	}
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

	//	public static int NB_SCORES = 5;
	//	
	//	static Integer[] getHighScores(String filename) {
	//		FileInputStream input = null;
	//		Integer[] scores = new Integer[NB_SCORES];
	//		for(int i = 0 ; i < NB_SCORES ; i++)
	//			scores[i] = 0;
	//		
	//		int nbLus = 0;
	//    	try {
	//    		input = new FileInputStream(filename);
	//    		DataInputStream buf = new DataInputStream(input);
	//    		if(input != null) {
	//    			while(nbLus < NB_SCORES) {
	//    				scores[nbLus] = buf.readInt();
	//    				nbLus++;
	//    			}
	//    		}
	//    		if(input != null)
	//    			input.close();
	//    	} catch (FileNotFoundException e) {
	//    		e.printStackTrace();
	//    	} catch (EOFException e) {
	//    		
	//    	}catch (IOException e) {
	//    		e.printStackTrace();
	//    	}
	//    	return scores;
	//	}
	//	
	//	public void setHighScores(String filename, int newScore) {
	//		Integer[] scores = getHighScores(filename);
	//		
	//		int i = 0;
	//		while(i < GoodFuncs.NB_SCORES)
	//			if(newScore < scores[i])
	//				i++;
	//		
	//    	FileOutputStream output = null;
	//		try {
	//    		output = openFileOutput(filename, MODE_PRIVATE);
	//        	DataOutputStream dataOutputStream = new DataOutputStream(output);
	//        	for(int j = 0 ; j < i ; j++)
	//        		dataOutputStream.writeInt(scores[j]);
	//    		dataOutputStream.writeInt(newScore);
	//    		if(output != null)
	//    			output.close();
	//    	} catch (FileNotFoundException e) {
	//    		e.printStackTrace();
	//    	} catch (IOException e) {
	//    		e.printStackTrace();
	//    	}
	//	}
}
