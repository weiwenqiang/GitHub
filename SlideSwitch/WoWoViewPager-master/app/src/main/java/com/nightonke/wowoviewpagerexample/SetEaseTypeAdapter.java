package com.nightonke.wowoviewpagerexample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Weiping Huang at 15:37 on 2016/3/3
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 */

class SetEaseTypeAdapter extends BaseAdapter {

    private Context mContext;

    SetEaseTypeAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mContext.getResources().getStringArray(R.array.ease_types).length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_ease_type, parent, false);

        ((TextView)convertView.findViewById(R.id.textview)).setText(
                mContext.getResources().getStringArray(R.array.ease_types)[position]);

        return convertView;
    }
}
