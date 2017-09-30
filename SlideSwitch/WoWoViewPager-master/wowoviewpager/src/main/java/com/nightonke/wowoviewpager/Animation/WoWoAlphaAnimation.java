package com.nightonke.wowoviewpager.Animation;

import android.animation.TimeInterpolator;
import android.view.View;

/**
 * Created by Weiping Huang at 15:40 on 2016/3/3
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 * Animation to change the opacity of view.
 */

public class WoWoAlphaAnimation extends PageAnimation {

    private float fromAlpha = UNINITIALIZED_VALUE;
    private float toAlpha = UNINITIALIZED_VALUE;

    /**
     *
     * @param page The animation will be played when the (page + 1) page is starting to show.
     * @param startOffset The animation only plays when the offset of page is large than startOffset.
     * @param endOffset The animation only plays when the offset of page is less than endOffset.
     * @param ease The ease type of the animation.
     * @param interpolator Custom time interpolator.
     * @param useSameEaseEnumBack Whether use the same ease type of animation when swiping back the view-pager.
     * @param fromAlpha The start-opacity.
     * @param toAlpha The end-opacity.
     */
    private WoWoAlphaAnimation(int page, float startOffset, float endOffset, int ease, TimeInterpolator interpolator, boolean useSameEaseEnumBack, float fromAlpha, float toAlpha) {
        super(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack);
        this.fromAlpha = fromAlpha;
        this.toAlpha = toAlpha;
    }

    @SuppressWarnings("Range")
    @Override
    protected void toStartState(View view) {
        view.setAlpha(fromAlpha);
    }

    @Override
    protected void toMiddleState(View view, float offset) {
        view.setAlpha(fromAlpha + (toAlpha - fromAlpha) * offset);
    }

    @SuppressWarnings("Range")
    @Override
    protected void toEndState(View view) {
        view.setAlpha(toAlpha);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends PageAnimation.Builder<WoWoAlphaAnimation.Builder> {

        private float fromAlpha = UNINITIALIZED_VALUE;
        private float toAlpha = UNINITIALIZED_VALUE;

        public Builder from(float fromAlpha) { this.fromAlpha = fromAlpha; return this; }

        public Builder from(double fromAlpha) { return from((float) fromAlpha); }

        public Builder to(float toAlpha) { this.toAlpha = toAlpha; return this; }

        public Builder to(double toAlpha) { return to((float) toAlpha); }

        public WoWoAlphaAnimation build() {
            checkUninitializedAttributes();
            return new WoWoAlphaAnimation(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack, fromAlpha, toAlpha);
        }

        @Override
        protected void checkUninitializedAttributes() {
            if (fromAlpha == UNINITIALIZED_VALUE) uninitializedAttributeException("fromAlpha");
            if (toAlpha == UNINITIALIZED_VALUE) uninitializedAttributeException("toAlpha");
        }
    }
}
