package com.norbsoft.typefacehelper.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import static com.norbsoft.typefacehelper.TypefaceHelper.typeface;

public class SpinnerExampleActivity extends Activity {

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spinner);

		Spinner spinner = (Spinner) findViewById(R.id.spinner);
		spinner.setAdapter(new BaseAdapter() {

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
					convertView = LayoutInflater.from(SpinnerExampleActivity.this)
							.inflate(android.R.layout.simple_list_item_1, parent, false);
					typeface(convertView);
				}

				((TextView) convertView).setText(items[position]);
				return convertView;
			}
			@Override public View getDropDownView(int position, View convertView, ViewGroup parent) {

				if (convertView == null) {
					convertView = LayoutInflater.from(SpinnerExampleActivity.this)
							.inflate(android.R.layout.simple_list_item_2, parent, false);
					typeface(convertView);
				}

				((TextView) convertView.findViewById(android.R.id.text1)).setText(items[position]);
				((TextView) convertView.findViewById(android.R.id.text2)).setText(R.string.lorem_ipsum_title);
				return convertView;
			}
		});
	}
}
