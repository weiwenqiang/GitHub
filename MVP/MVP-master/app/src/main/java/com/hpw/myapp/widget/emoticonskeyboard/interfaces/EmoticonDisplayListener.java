package com.hpw.myapp.widget.emoticonskeyboard.interfaces;

import android.view.ViewGroup;

import com.hpw.myapp.widget.emoticonskeyboard.adpater.EmoticonsAdapter;

public interface EmoticonDisplayListener<T> {

    void onBindView(int position, ViewGroup parent, EmoticonsAdapter.ViewHolder viewHolder, T t, boolean isDelBtn);
}
