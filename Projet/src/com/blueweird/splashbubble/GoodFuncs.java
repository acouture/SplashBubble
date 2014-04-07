package com.blueweird.splashbubble;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class GoodFuncs {

	public static int NB_SCORES = 5;
	
	public static Integer[] getHighScores(FileInputStream inputStream) {
		Integer[] scores = new Integer[NB_SCORES];
		for(int i = 0 ; i < NB_SCORES ; i++)
			scores[i] = 0;
		
		int i = 0;
    	try {
    		if(inputStream != null) {
    			DataInputStream buf = new DataInputStream(inputStream);
    			for(i = 0 ; i < NB_SCORES ; i++) {
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
	
	public static void setHighScores(Integer[] scores, FileOutputStream outputStream, int newScore) {
		int i = 0;
		while(i < NB_SCORES) {
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
				for(int j = i ; j < NB_SCORES - 1 ; j++)
					dataOutputStream.writeInt(scores[j]);
				outputStream.close();
			}
    	} catch (FileNotFoundException e) {
    		e.printStackTrace();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
	}
}
