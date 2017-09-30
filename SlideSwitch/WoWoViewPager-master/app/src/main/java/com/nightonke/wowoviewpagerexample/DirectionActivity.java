package com.nightonke.wowoviewpagerexample;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.nightonke.wowoviewpager.WoWoViewPager;

public class DirectionActivity extends GuidePageActivity1 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout base = (RelativeLayout) findViewById(R.id.base);

        Button directionButton = new Button(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, 0);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        directionButton.setLayoutParams(layoutParams);
        directionButton.setText("Change Direction");
        directionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wowo.getDirection() == WoWoViewPager.Horizontal) {
                    wowo.setDirection(WoWoViewPager.Vertical);
                } else {
                    wowo.setDirection(WoWoViewPager.Horizontal);
                }
            }
        });
        base.addView(directionButton);
    }
}
