package com.example.splashbubblev2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

public class MenuView extends View {

	GameController controller;

	Bitmap background;
	RectF buttonPlay;
	RectF buttonQuit;

	boolean init = false;

	Paint paint = new Paint();

	public MenuView(Context context, GameController controller) {
		super(context);
		this.controller = controller;
		background = BitmapFactory.decodeResource(getResources(),
				R.drawable.menu_background);
	}

	private void initialization() {
		buttonPlay = new RectF(getWidth() / 2 - 100, getHeight() / 2 + 50,
				getWidth() / 2 + 100, getHeight() / 2 + 100);
		buttonQuit = new RectF(getWidth() / 2 - 100, getHeight() / 2 + 150,
				getWidth() / 2 + 100, getHeight() / 2 + 200);
		init = true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		if (!init)
			initialization();

		canvas.drawColor(Color.BLACK);
		canvas.drawBitmap(background, 0, 0, paint);

		paint.setARGB(255, 18, 40, 127);
		paint.setTextAlign(Align.CENTER);
		paint.setTextSize(55);
		canvas.drawText("Splash Bubble", getWidth() / 2, getHeight() / 2, paint);

		canvas.drawRoundRect(buttonPlay, 25, 25, paint);
		canvas.drawRoundRect(buttonQuit, 25, 25, paint);

		paint.setARGB(255, 252, 143, 18);
		paint.setTextAlign(Align.CENTER);
		paint.setTextSize(40);
		canvas.drawText("Jouer", getWidth() / 2, getHeight() / 2 + 90, paint);
		canvas.drawText("Quitter", getWidth() / 2, getHeight() / 2 + 190, paint);

		paint.setARGB(255, 18, 40, 127);
		paint.setTextAlign(Align.LEFT);
		paint.setTextSize(20);
		canvas.drawText("Design and developped by CGTeam", 5,
				canvas.getHeight() - 20, paint);
	}

//	@Override
//	public boolean onTouchEvent(MotionEvent e) {
//		if (buttonPlay.contains(e.getX(), e.getY())) {
//			controller.buttonPlayAction();
//		} else if (buttonQuit.contains(e.getX(), e.getY())) {
//			controller.buttonQuitAction();
//		}
//		return super.onTouchEvent(e);
//	}

}
