package com.norbsoft.typefacehelper.sample;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.norbsoft.typefacehelper.ActionBarHelper;
import com.norbsoft.typefacehelper.TypefaceCollection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.norbsoft.typefacehelper.TypefaceHelper.typeface;

public class DynamicExampleActivity extends ActionBarActivity implements View.OnClickListener{

	private static final String STATE_SELECTED_FONT = "STATE_SELECTED_FONT";

	private static final String TYPEFACE_DEFAULT = "System default";
	private static final String TYPEFACE_ACTIONMAN = "Action man";
	private static final String TYPEFACE_ARCHRIVAL = "Arch Rival";
	private static final String TYPEFACE_JUICE = "Juice";
	private static final String TYPEFACE_UBUNTU = "Ubuntu";

	private Map<String, TypefaceCollection> mTypefaceMap;
	private EditText mEditText;

	private Spinner mTypefaceSpinner;
	private ToggleButton mBtnItalic;
	private ToggleButton mBtnBold;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actvity_dynamic_typefaces);

		mEditText = (EditText) findViewById(R.id.edit_text);
		mBtnItalic = (ToggleButton) findViewById(R.id.btn_italic);
		mBtnBold = (ToggleButton) findViewById(R.id.btn_bold);
		mTypefaceSpinner = (Spinner) findViewById(R.id.spinner);

		mBtnItalic.setOnClickListener(this);
		mBtnBold.setOnClickListener(this);

		// Retrieve custom typefaces from Application subclass
		MyApplication myApp = (MyApplication) getApplication();
		mTypefaceMap = new HashMap<String, TypefaceCollection>(5);
		mTypefaceMap.put(TYPEFACE_DEFAULT, myApp.getSystemDefaultTypeface());
		mTypefaceMap.put(TYPEFACE_ACTIONMAN, myApp.getActionManTypeface());
		mTypefaceMap.put(TYPEFACE_ARCHRIVAL, myApp.getArchRivalTypeface());
		mTypefaceMap.put(TYPEFACE_JUICE, myApp.getJuiceTypeface());
		mTypefaceMap.put(TYPEFACE_UBUNTU, myApp.getUbuntuTypeface());

		final List<String> fontList = new ArrayList<String>(mTypefaceMap.keySet().size());
		fontList.addAll(mTypefaceMap.keySet());

		mTypefaceSpinner.setAdapter(new BaseAdapter() {

			@Override public int getCount() {
				return fontList.size();
			}
			@Override public Object getItem(int position) {
				return fontList.get(position);
			}
			@Override public long getItemId(int position) {
				return fontList.get(position).hashCode();
			}
			@Override public View getView(int position, View convertView, ViewGroup parent) {
				if (convertView == null) {
					convertView = LayoutInflater.from(DynamicExampleActivity.this)
							.inflate(android.R.layout.simple_list_item_1, parent, false);
				}
				typeface(convertView, mTypefaceMap.get(fontList.get(position)));
				((TextView) convertView).setText(fontList.get(position));
				return convertView;
			}
		});
		mTypefaceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				applyDynamicTypeface(fontList.get(position), mBtnBold.isChecked(), mBtnItalic.isChecked());
			}
			@Override public void onNothingSelected(AdapterView<?> parent) {}
		});

		if (savedInstanceState != null) {
			mTypefaceSpinner.setSelection(savedInstanceState.getInt(STATE_SELECTED_FONT));
		}
	}

	@Override protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(STATE_SELECTED_FONT, mTypefaceSpinner.getSelectedItemPosition());
	}
	@Override public void onClick(View v) {
		applyDynamicTypeface(
				(String) mTypefaceSpinner.getSelectedItem(),
				mBtnBold.isChecked(),
				mBtnItalic.isChecked());
	}

	private void applyDynamicTypeface(String selectedFont, boolean flgBold, boolean flgItalic) {
		typeface(this, mTypefaceMap.get(selectedFont));
		ActionBarHelper.setTitle(getSupportActionBar(),typeface(
				getString(R.string.app_name),
				mTypefaceMap.get(selectedFont),
				getTypefaceStyle(flgBold, flgItalic)));

		// Std typeface style set for ordinary textview
		mEditText.setTypeface(null, getTypefaceStyle(flgBold, flgItalic));

		// Apply custom typeface
		typeface(mEditText, mTypefaceMap.get(selectedFont));
	}

	private int getTypefaceStyle(boolean flgBold, boolean flgItalic) {
		if (flgBold && flgItalic) {
			return Typeface.BOLD_ITALIC;
		} else if (flgBold) {
			return Typeface.BOLD;
		} else if (flgItalic) {
			return Typeface.ITALIC;
		} else {
			return Typeface.NORMAL;
		}
	}
}
