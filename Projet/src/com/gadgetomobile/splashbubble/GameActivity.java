package com.gadgetomobile.splashbubble;

import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;

public class GameActivity extends Activity {

    /** Called when the activity is first created. */
	GameView view;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(view = new GameView(this));
    }
    
    @Override
    public void onBackPressed() {
//    	FileOutputStream output = null;
//    	Integer[] scores = GoodFuncs.getHighScores(getResources().getString(R.string.highScoresFile));
//    	Integer score = view.getScore();
//    	
//    	try {
//    		output = openFileOutput(getResources().getString(R.string.highScoresFile), MODE_PRIVATE);
//        	DataOutputStream dataOutputStream = new DataOutputStream(output);
//    		dataOutputStream.writeInt(score);
//    		if(output != null)
//    			output.close();
//    	} catch (FileNotFoundException e) {
//    		e.printStackTrace();
//    	} catch (IOException e) {
//    		e.printStackTrace();
//    	}
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
}