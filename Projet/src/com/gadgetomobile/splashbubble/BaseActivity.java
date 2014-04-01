package com.gadgetomobile.splashbubble;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;

public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (isAPILowerThanHoneycomb()) {
	        setTheme(android.R.style.Theme_NoTitleBar);
		}
		else {
			setTheme(android.R.style.Theme_Holo);
		}
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.base);
	}
	
	public boolean isAPILowerThanHoneycomb() {
		return Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB;
	}
}
