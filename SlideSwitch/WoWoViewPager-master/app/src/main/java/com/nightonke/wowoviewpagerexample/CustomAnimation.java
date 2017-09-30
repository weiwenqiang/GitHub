package com.nightonke.wowoviewpagerexample;

import android.animation.TimeInterpolator;
import android.os.Build;
import android.view.View;

import com.nightonke.wowoviewpager.Animation.PageAnimation;


/**
 * Created by Weiping Huang at 13:13 on 2017/4/2
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

public class CustomAnimation extends PageAnimation {

    private float fromElevation;
    private float toElevation;

    private CustomAnimation(int page, float startOffset, float endOffset, int ease, TimeInterpolator interpolator, boolean useSameEaseEnumBack, float fromElevation, float toElevation) {
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

    public static class Builder extends PageAnimation.Builder<CustomAnimation.Builder> {

        private float fromElevation = UNINITIALIZED_VALUE;
        private float toElevation = UNINITIALIZED_VALUE;

        public Builder from(float fromElevation) { this.fromElevation = fromElevation; return this; }

        public Builder to(float toElevation) { this.toElevation = toElevation; return this; }

        public Builder from(double fromElevation) { return from((float) fromElevation); }

        public Builder to(double toElevation) { return to((float) toElevation); }

        public CustomAnimation build() {
            checkUninitializedAttributes();
            return new CustomAnimation(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack, fromElevation, toElevation);
        }

        @Override
        protected void checkUninitializedAttributes() {
            // Check the uninitialized attributes here if needed.
            if (fromElevation == UNINITIALIZED_VALUE) uninitializedAttributeException("fromElevation");
            if (toElevation == UNINITIALIZED_VALUE) uninitializedAttributeException("toElevation");
        }
    }
}
