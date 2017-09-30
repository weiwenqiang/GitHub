package com.norbsoft.typefacehelper.sample;

import android.app.Activity;
import android.os.Bundle;
import static com.norbsoft.typefacehelper.TypefaceHelper.typeface;

public class SimpleExampleActivity extends Activity {

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sample);
		typeface(this);
	}
}
