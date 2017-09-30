package com.nightonke.wowoviewpager.Animation;

import android.animation.TimeInterpolator;
import android.view.View;

/**
 * Created by Weiping Huang at 00:20 on 2017/3/30
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 * Animation to change rotate degree of view.
 *          _ _ _ _ _ _ _
 *        /|    x
 *       / |
 *      /  |y
 *     /   |
 *    /z   |
 *   /     |
 *
 */

public class WoWoRotationAnimation extends XYZPageAnimation {

    private WoWoRotationAnimation(int page, float startOffset, float endOffset, int ease, TimeInterpolator interpolator, boolean useSameEaseEnumBack, float fromX, float fromY, float fromZ, float toX, float toY, float toZ) {
        super(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack, fromX, fromY, fromZ, toX, toY, toZ);
    }

    @Override
    protected void toStartState(View view) {
        view.setRotationX(fromX);
        view.setRotationY(fromY);
        view.setRotation(fromZ);
    }

    @Override
    protected void toMiddleState(View view, float offset) {
        view.setRotationX(fromX + (toX - fromX) * offset);
        view.setRotationY(fromY + (toY - fromY) * offset);
        view.setRotation(fromZ + (toZ - fromZ) * offset);
    }

    @Override
    protected void toEndState(View view) {
        view.setRotationX(toX);
        view.setRotationY(toY);
        view.setRotation(toZ);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends XYZPageAnimation.Builder<WoWoRotationAnimation.Builder> {

        public WoWoRotationAnimation build() {
            checkUninitializedAttributes();
            return new WoWoRotationAnimation(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack, fromX, fromY, fromZ, toX, toY, toZ);
        }
    }
}
