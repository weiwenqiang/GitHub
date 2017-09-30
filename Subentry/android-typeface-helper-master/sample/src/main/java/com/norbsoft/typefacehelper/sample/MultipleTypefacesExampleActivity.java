package com.norbsoft.typefacehelper.sample;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.norbsoft.typefacehelper.ActionBarHelper;
import com.norbsoft.typefacehelper.TypefaceCollection;

import static com.norbsoft.typefacehelper.TypefaceHelper.typeface;

public class MultipleTypefacesExampleActivity extends ActionBarActivity {

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multiple_typefaces);

		// Apply default typeface
		typeface(this);

		// Retrieve custom typefaces from Application subclass
		final TypefaceCollection actionMan = ((MyApplication) getApplication()).getActionManTypeface();
		final TypefaceCollection archRival = ((MyApplication) getApplication()).getArchRivalTypeface();
		final TypefaceCollection juice = ((MyApplication) getApplication()).getJuiceTypeface();

		// Typeface the actionbar!
		ActionBarHelper.setTitle(
				getSupportActionBar(),
				typeface(getString(R.string.app_name), juice, Typeface.BOLD_ITALIC));

		// Apply custom typefaces!
		typeface(findViewById(R.id.label_title), actionMan);
		typeface(findViewById(R.id.label_subtitle), archRival);
		typeface(findViewById(R.id.lorem_ipsum_2), juice);
		typeface(findViewById(R.id.lorem_ipsum_3), actionMan);
		typeface(findViewById(R.id.lorem_ipsum_4), archRival);

		// Apply typefaces inside spinner!
		((Spinner) findViewById(R.id.spinner)).setAdapter(new BaseAdapter() {
			String[] items = getResources().getStringArray(R.array.adapter_values);
			@Override public int getCount() {
				return items.length;
			}
			@Override public Object getItem(int position) {
				return items[position];
			}
			@Override public long getItemId(int position) {
				return items[position].hashCode();
			}
			@Override public View getView(int position, View convertView, ViewGroup parent) {
				if (convertView == null) {
					convertView = LayoutInflater.from(MultipleTypefacesExampleActivity.this)
							.inflate(android.R.layout.simple_list_item_1, parent, false);
				}

				// Custom typefaces
				// Default typeface is Ubuntu
				switch (position % 4) {
				case 0:
					typeface(convertView, actionMan);
					break;
				case 1:
					typeface(convertView);
					break;
				case 2:
					typeface(convertView, archRival);
					break;
				case 3:
					typeface(convertView, juice);
					break;
				}

				((TextView) convertView).setText(items[position]);
				return convertView;
			}
			@Override public View getDropDownView(int position, View convertView, ViewGroup parent) {

				if (convertView == null) {
					convertView = LayoutInflater.from(MultipleTypefacesExampleActivity.this)
							.inflate(android.R.layout.simple_list_item_2, parent, false);
				}

				TextView text1 = (TextView) convertView.findViewById(android.R.id.text1);
				TextView text2 = (TextView) convertView.findViewById(android.R.id.text2);

				// Custom typefaces (different for each line!)
				// Default typeface is Ubuntu
				switch (position % 4) {
				case 0:
					typeface(text1, actionMan);
					typeface(text2);
					break;
				case 1:
					typeface(text1);
					typeface(text2);
					break;
				case 2:
					typeface(text1, archRival);
					typeface(text2);
					break;
				case 3:
					typeface(text1, juice);
					typeface(text2);
					break;
				}

				text1.setText(items[position]);
				text2.setText(R.string.lorem_ipsum_title);
				return convertView;
			}
		});
	}
}
