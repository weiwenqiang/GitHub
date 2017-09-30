package com.nightonke.wowoviewpagerexample;

import android.os.Bundle;
import android.widget.TextView;

import com.nightonke.wowoviewpager.Animation.WoWoElevationAnimation;

public class WoWoElevationAnimationActivity extends WoWoActivity {

    @Override
    protected int contentViewRes() {
        return R.layout.activity_wowo_elevation_animation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView text = (TextView) findViewById(R.id.test);
        wowo.addAnimation(text)
                .add(WoWoElevationAnimation.builder().page(0).from(0).to(30).build())
                .add(WoWoElevationAnimation.builder().page(1).from(30).to(60).build())
                .add(WoWoElevationAnimation.builder().page(2).from(60).to(0).build())
                .add(WoWoElevationAnimation.builder().page(3).from(0).to(60).build());

        wowo.setEase(ease);
        wowo.setUseSameEaseBack(useSameEaseTypeBack);
        wowo.ready();
    }
}
