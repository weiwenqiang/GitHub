package com.nightonke.wowoviewpager.Animation;

import android.animation.TimeInterpolator;
import android.os.Build;
import android.view.View;

/**
 * Created by Weiping Huang at 14:08 on 2017/4/4
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 * Animation to change 3D-position of a view.
 */

public class WoWoTranslation3DAnimation extends XYZPageAnimation {

    private WoWoTranslation3DAnimation(int page, float startOffset, float endOffset, int ease, TimeInterpolator interpolator, boolean useSameEaseEnumBack, float fromX, float fromY, float fromZ, float toX, float toY, float toZ) {
        super(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack, fromX, fromY, fromZ, toX, toY, toZ);
    }

    @Override
    protected void toStartState(View view) {
        view.setTranslationX(fromX);
        view.setTranslationY(fromY);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) view.setTranslationZ(fromZ);
    }

    @Override
    protected void toMiddleState(View view, float offset) {
        view.setTranslationX(fromX + (toX - fromX) * offset);
        view.setTranslationY(fromY + (toY - fromY) * offset);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) view.setTranslationZ(fromZ + (toZ - fromZ) * offset);
    }

    @Override
    protected void toEndState(View view) {
        view.setTranslationX(toX);
        view.setTranslationY(toY);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) view.setTranslationZ(toZ);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends XYZPageAnimation.Builder<WoWoTranslation3DAnimation.Builder> {

        public WoWoTranslation3DAnimation build() {
            checkUninitializedAttributes();
            return new WoWoTranslation3DAnimation(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack, fromX, fromY, fromZ, toX, toY, toZ);
        }
    }
}