package com.norbsoft.typefacehelper.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import com.norbsoft.typefacehelper.ActionBarHelper;

import static com.norbsoft.typefacehelper.TypefaceHelper.typeface;

public class ActionbarExampleActivity extends ActionBarActivity {

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// For use with appcompat library:
		ActionBarHelper.setTitle(getSupportActionBar(), typeface(this, R.string.app_name));

		// Without appcompat library:
		// ActionBarHelper.setTitle(getActionBar(), typeface(this, R.string.app_name));
	}
}
