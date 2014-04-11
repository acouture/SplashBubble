package com.blueweird.splashbubble.activities;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import com.blueweird.splashbubble.R;

public class BaseActivity extends Activity {

	public static int NB_HIGH_SCORES_MAX = 5;
	private static int gameplay = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.base);
	}

	public Integer[] getHighScores(String filename) {
		Integer[] scores = new Integer[NB_HIGH_SCORES_MAX];
		FileInputStream inputStream = null;

		for(int i = 0 ; i < NB_HIGH_SCORES_MAX ; i++)
			scores[i] = 0;
		
		try {
			inputStream = openFileInput(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int i = 0;
		try {
			if(inputStream != null) {
				DataInputStream buf = new DataInputStream(inputStream);
				for(i = 0 ; i < NB_HIGH_SCORES_MAX ; i++) {
					scores[i] = buf.readInt();
				}
				inputStream.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {
			e.printStackTrace();
			try {
				inputStream.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		return scores;
	}

	public void setHighScores(Integer[] scores, String filename, int newScore) {
		FileOutputStream outputStream = null;
		
		try {
			outputStream = openFileOutput(filename, MODE_PRIVATE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int i = 0;
		while(i < NB_HIGH_SCORES_MAX) {
			if(newScore < scores[i])
				i++;
			else
				break;
		}

		try {
			if(outputStream != null) {
				DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
				for(int j = 0 ; j < i ; j++)
					dataOutputStream.writeInt(scores[j]);
				dataOutputStream.writeInt(newScore);
				for(int j = i ; j < NB_HIGH_SCORES_MAX - 1 ; j++)
					dataOutputStream.writeInt(scores[j]);
				outputStream.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int getGameplay() {
		return gameplay;
	}
	
	protected static void setGameplay(int g) {
		gameplay = g;
	}
	
	public void quitActivity(View v) {
		finish();
	}
}
