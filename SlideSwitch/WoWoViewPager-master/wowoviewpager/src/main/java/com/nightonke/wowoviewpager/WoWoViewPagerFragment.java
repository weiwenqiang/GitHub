package com.nightonke.wowoviewpager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Weiping Huang at 15:16 on 2017/4/1
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

public class WoWoViewPagerFragment extends Fragment {

    private Integer colorRes = null;
    private Integer color = null;

    public WoWoViewPagerFragment() {
        this.colorRes = android.R.color.transparent;
    }

    public Integer getColorRes() {
        return colorRes;
    }

    public void setColorRes(Integer colorRes) {
        this.colorRes = colorRes;
        color = null;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
        colorRes = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = new View(getContext());
        view.setLayoutParams(new ViewGroup.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));

        if (colorRes != null) {
            // the resource of color has been set
            view.setBackgroundColor(ContextCompat.getColor(getActivity(), colorRes));
        } else {
            if (color != null) {
                // the color has been set
                view.setBackgroundColor(color);
            } else {
                // set transparent
                view.setBackgroundColor(Color.TRANSPARENT);
            }
        }

        return view;
    }
}
