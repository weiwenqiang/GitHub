package com.nightonke.wowoviewpager.Animation;

import android.animation.TimeInterpolator;
import android.view.View;

/**
 * Created by Weiping Huang at 20:12 on 2017/3/29
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 * Animation to change 2D-translation of a view.
 */

public class WoWoTranslationAnimation extends XYPageAnimation {

    private WoWoTranslationAnimation(int page, float startOffset, float endOffset, int ease, TimeInterpolator interpolator, boolean useSameEaseEnumBack, float fromX, float fromY, float toX, float toY) {
        super(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack, fromX, fromY, toX, toY);
    }

    @Override
    protected void toStartState(View view) {
        view.setTranslationX(fromX);
        view.setTranslationY(fromY);
    }

    @Override
    protected void toMiddleState(View view, float offset) {
        view.setTranslationX(fromX + (toX - fromX) * offset);
        view.setTranslationY(fromY + (toY - fromY) * offset);
    }

    @Override
    protected void toEndState(View view) {
        view.setTranslationX(toX);
        view.setTranslationY(toY);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends XYPageAnimation.Builder<WoWoTranslationAnimation.Builder> {

        public WoWoTranslationAnimation build() {
            checkUninitializedAttributes();
            return new WoWoTranslationAnimation(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack, fromX, fromY, toX, toY);
        }
    }
}
