package com.nightonke.wowoviewpager.Animation;

import android.animation.TimeInterpolator;
import android.os.Build;
import android.view.View;

/**
 * Created by Weiping Huang at 12:39 on 2017/4/3
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 * Animation to change the elevation of a view.
 * Please notice that it's available from Lollipop.
 */

public class WoWoElevationAnimation extends PageAnimation {

    private float fromElevation;
    private float toElevation;

    private WoWoElevationAnimation(int page, float startOffset, float endOffset, int ease, TimeInterpolator interpolator, boolean useSameEaseEnumBack, float fromElevation, float toElevation) {
        super(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack);
        this.fromElevation = fromElevation;
        this.toElevation = toElevation;
    }

    @Override
    protected void toStartState(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setElevation(fromElevation);
        }
    }

    @Override
    protected void toMiddleState(View view, float offset) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setElevation(fromElevation + (toElevation - fromElevation) * offset);
        }
    }

    @Override
    protected void toEndState(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setElevation(toElevation);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends PageAnimation.Builder<WoWoElevationAnimation.Builder> {

        private float fromElevation = UNINITIALIZED_VALUE;
        private float toElevation = UNINITIALIZED_VALUE;

        public Builder from(float fromElevation) { this.fromElevation = fromElevation; return this; }

        public Builder to(float toElevation) { this.toElevation = toElevation; return this; }

        public Builder from(double fromElevation) { return from((float) fromElevation); }

        public Builder to(double toElevation) { return to((float) toElevation); }

        public WoWoElevationAnimation build() {
            checkUninitializedAttributes();
            return new WoWoElevationAnimation(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack, fromElevation, toElevation);
        }

        @Override
        protected void checkUninitializedAttributes() {
            if (fromElevation == UNINITIALIZED_VALUE) uninitializedAttributeException("fromElevation");
            if (toElevation == UNINITIALIZED_VALUE) uninitializedAttributeException("toElevation");
        }
    }

}
