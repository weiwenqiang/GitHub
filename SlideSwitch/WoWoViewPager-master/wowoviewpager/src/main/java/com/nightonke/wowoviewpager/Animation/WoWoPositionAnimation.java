package com.nightonke.wowoviewpager.Animation;

import android.animation.TimeInterpolator;
import android.view.View;

/**
 * Created by Weiping Huang at 18:13 on 2017/3/29
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 * Animation to change 2D-position of a view.
 */

public class WoWoPositionAnimation extends XYPageAnimation {

    private WoWoPositionAnimation(int page, float startOffset, float endOffset, int ease, TimeInterpolator interpolator, boolean useSameEaseEnumBack, float fromX, float fromY, float toX, float toY) {
        super(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack, fromX, fromY, toX, toY);
    }

    @Override
    protected void toStartState(View view) {
        view.setX(fromX);
        view.setY(fromY);
    }

    @Override
    protected void toMiddleState(View view, float offset) {
        view.setX(fromX + (toX - fromX) * offset);
        view.setY(fromY + (toY - fromY) * offset);
    }

    @Override
    protected void toEndState(View view) {
        view.setX(toX);
        view.setY(toY);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends XYPageAnimation.Builder<WoWoPositionAnimation.Builder> {

        public WoWoPositionAnimation build() {
            checkUninitializedAttributes();
            return new WoWoPositionAnimation(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack, fromX, fromY, toX, toY);
        }
    }
}
