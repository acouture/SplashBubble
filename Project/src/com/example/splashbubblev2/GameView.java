package com.example.splashbubblev2;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class GameView extends SurfaceView {


	private Bitmap background;
	private Rect backgroundSize;
	
	private RectF backButton;
	private Paint paintBackButton;
	
	private List<Bubble> bubbles;

	private Paint paintText;

	public GameView(Context context) {
		super(context);


		background = BitmapFactory.decodeResource(getResources(),
				R.drawable.game_background);
		backgroundSize = new Rect(0, 0, context.getResources()
				.getDisplayMetrics().widthPixels, context.getResources()
				.getDisplayMetrics().heightPixels);
		
		backButton = new RectF(10, 10, 100, 40);
		paintBackButton = new Paint();
		paintBackButton.setARGB(255, 18, 40, 127);
		paintBackButton.setTextAlign(Align.CENTER);
		paintBackButton.setTextSize(40);
		
		paintText = new Paint();
		paintText.setARGB(255, 252, 143, 18);
		paintText.setTextAlign(Align.CENTER);
		paintText.setTextSize(20);
		
		bubbles = new ArrayList<Bubble>();

	}

	public void addBubble(EBubbleType bubbleType) {
		bubbles.add(new Bubble(this, bubbleType));
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		canvas.drawBitmap(background, null, backgroundSize, null);
		
		for (Bubble bubble : bubbles) {
			bubble.draw(canvas);
		}
		
		// bouton retour
		canvas.drawRoundRect(backButton, 25, 25, paintBackButton);
		canvas.drawText("Retour", 55, 30, paintText);
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		return super.onTouchEvent(e);
	}
}
