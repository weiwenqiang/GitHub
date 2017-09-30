package com.norbsoft.typefacehelper.sample;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import static com.norbsoft.typefacehelper.TypefaceHelper.typeface;

public class ListExampleActivity extends ListActivity {

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setListAdapter(new BaseAdapter() {

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
					convertView = LayoutInflater.from(ListExampleActivity.this)
							.inflate(android.R.layout.simple_list_item_1, parent, false);
					typeface(convertView);
				}

				((TextView) convertView).setText(items[position]);
				return convertView;
			}
		});
	}
}
