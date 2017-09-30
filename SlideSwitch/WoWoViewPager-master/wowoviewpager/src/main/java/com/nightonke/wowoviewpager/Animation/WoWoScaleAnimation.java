package com.nightonke.wowoviewpager.Animation;

import android.animation.TimeInterpolator;
import android.view.View;

/**
 * Created by Weiping Huang at 20:39 on 2017/3/29
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 * Animation to scale a view.
 */

public class WoWoScaleAnimation extends XYPageAnimation {

    private WoWoScaleAnimation(int page, float startOffset, float endOffset, int ease, TimeInterpolator interpolator, boolean useSameEaseEnumBack, float fromX, float fromY, float toX, float toY) {
        super(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack, fromX, fromY, toX, toY);
    }

    @Override
    protected void toStartState(View view) {
        view.setScaleX(fromX);
        view.setScaleY(fromY);
    }

    @Override
    protected void toMiddleState(View view, float offset) {
        view.setScaleX(fromX + (toX - fromX) * offset);
        view.setScaleY(fromY + (toY - fromY) * offset);
    }

    @Override
    protected void toEndState(View view) {
        view.setScaleX(toX);
        view.setScaleY(toY);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends XYPageAnimation.Builder<WoWoScaleAnimation.Builder> {

        public WoWoScaleAnimation build() {
            checkUninitializedAttributes();
            return new WoWoScaleAnimation(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack, fromX, fromY, toX, toY);
        }
    }
}
