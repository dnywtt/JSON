package com.example.json;

import android.app.Activity;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;

public class AnimatedGIFActivity extends Activity {
	private int gifId;
	GIFView gifView = new GIFView(this);
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gifview);
		
		gifView.setGIFResource(R.drawable.twt);
		//setContentView(gifView);
	}

	@SuppressWarnings("unused")
	private void setAttrs(AttributeSet attrs) {
		if (attrs != null) {
			TypedArray a = getBaseContext().obtainStyledAttributes(attrs,
					R.styleable.GIFView, 0, 0);
			String gifSource = a.getString(R.styleable.GIFView_src);
			// little workaround here. Who knows better approach on how to
			// easily get resource id - please share
			String sourceName = Uri.parse(gifSource).getLastPathSegment()
					.replace(".gif", "");gifView.setGIFResource(getResources().getIdentifier(sourceName,
					"drawable", getBaseContext().getPackageName()));
			a.recycle();
		}
	}



}