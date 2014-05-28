package com.example.json;

import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

public class GIFView extends View {

	private Movie movie;
	long now;
	private int gifId;
	int movieStart = 0;

	public GIFView(Context context) {
		super(context);
		initializeView();
	}

	public GIFView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initializeView();
	}

	public GIFView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initializeView();
	}

	private void initializeView() {
		// R.drawable.loader - our animated GIF
		if (gifId != 0) {
			InputStream is = getContext().getResources().openRawResource(gifId);
			movie = Movie.decodeStream(is);

			this.invalidate();
		}

	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.TRANSPARENT);
		super.onDraw(canvas);
		now = android.os.SystemClock.uptimeMillis();
		// int movieStart;
		if (movieStart == 0) {
			movieStart = (int) now;
		}
		if (movie != null) {
			int relTime = (int) ((now - movieStart) % movie.duration());
			movie.setTime(relTime);
			movie.draw(canvas, getWidth() - movie.width(),
					getHeight() - movie.height());
			this.invalidate();
		}
	}

	public void setGIFResource(int resId) {
		this.gifId = resId;
		//initializeView();
	}

	public int getGIFResource() {
		return this.gifId;
	}
	

}