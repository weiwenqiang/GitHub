package com.hpw.myapp.widget.emoticonskeyboard.interfaces;

import android.view.View;
import android.view.ViewGroup;

import com.hpw.myapp.widget.emoticonskeyboard.data.PageEntity;

public interface PageViewInstantiateListener<T extends PageEntity> {

    View instantiateItem(ViewGroup container, int position, T pageEntity);
}
