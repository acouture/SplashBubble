

package com.gadgetomobile.splashbubble;

import android.app.Activity;
import android.os.Bundle;

 

public class GameActivity extends Activity {

    /** Called when the activity is first created. */
	
	GameView view;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(view = new GameView(this));
    }
    

}