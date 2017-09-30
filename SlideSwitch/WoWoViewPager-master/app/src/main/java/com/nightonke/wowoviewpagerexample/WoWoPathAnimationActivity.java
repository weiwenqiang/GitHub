package com.nightonke.wowoviewpagerexample;

import android.os.Bundle;

import com.nightonke.wowoviewpager.Animation.ViewAnimation;
import com.nightonke.wowoviewpager.Animation.WoWoPathAnimation;
import com.nightonke.wowoviewpager.WoWoPathView;

public class WoWoPathAnimationActivity extends WoWoActivity {

    @Override
    protected int contentViewRes() {
        return R.layout.activity_wowo_path_animation;
    }

    @Override
    protected int fragmentNumber() {
        return 2;
    }

    @Override
    protected Integer[] fragmentColorsRes() {
        return new Integer[]{R.color.red, R.color.blue_5};
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WoWoPathView pathView = (WoWoPathView) findViewById(R.id.path_view);

        // For different screen size, try to adjust the scale values to see the airplane.
        float xScale = screenW / 720f;
        float yScale = screenH / 1280f;

        pathView.newPath()
                .pathMoveTo(screenW / 2, screenH + yScale * 100)
                .pathCubicTo(xScale * 313, screenH - yScale * 531,
                        xScale * (-234), screenH - yScale * 644,
                        xScale * 141, screenH - yScale * 772)
                .pathCubicTo(xScale * 266, screenH - yScale * 817,
                        xScale * 444, screenH - yScale * 825,
                        xScale * 596, screenH - yScale * 788)
                .pathCubicTo(xScale * 825, screenH - yScale * 727,
                        xScale * 755, screenH - yScale * 592,
                        xScale * (-100), screenH - yScale * 609);

        ViewAnimation animation = new ViewAnimation(pathView);
        animation.add(WoWoPathAnimation.builder().page(0).from(0).to(1).path(pathView).build());
        wowo.addAnimation(animation);

        wowo.setEase(ease);
        wowo.setUseSameEaseBack(useSameEaseTypeBack);
        wowo.ready();
    }
}
