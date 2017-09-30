package com.nightonke.wowoviewpagerexample;

import android.os.Bundle;

import com.nightonke.wowoviewpager.Animation.ViewAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoTextViewTextSizeAnimation;

public class WoWoTextViewTextSizeAnimationActivity extends WoWoActivity {

    @Override
    protected int contentViewRes() {
        return R.layout.activity_wowo_textview_textsize_animation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewAnimation viewAnimation = new ViewAnimation(findViewById(R.id.test));
        viewAnimation.add(WoWoTextViewTextSizeAnimation.builder().page(0)
                .fromSp(50).toSp(20).build());
        viewAnimation.add(WoWoTextViewTextSizeAnimation.builder().page(1)
                .fromSp(20).toSp(60).build());
        viewAnimation.add(WoWoTextViewTextSizeAnimation.builder().page(2)
                .fromSp(60).toSp(5).build());
        viewAnimation.add(WoWoTextViewTextSizeAnimation.builder().page(3).start(0).end(0.5)
                .fromSp(5).toSp(50).build());
        viewAnimation.add(WoWoTextViewTextSizeAnimation.builder().page(3).start(0.5).end(1)
                .fromSp(50).toSp(30).build());
        wowo.addAnimation(viewAnimation);

        wowo.setEase(ease);
        wowo.setUseSameEaseBack(useSameEaseTypeBack);
        wowo.ready();
    }
}
