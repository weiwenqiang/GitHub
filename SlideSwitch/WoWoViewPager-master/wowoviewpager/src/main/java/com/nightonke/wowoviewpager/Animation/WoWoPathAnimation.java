package com.nightonke.wowoviewpager.Animation;

import android.animation.TimeInterpolator;
import android.view.View;

import com.nightonke.wowoviewpager.WoWoPathView;

/**
 * Created by Weiping Huang at 19:56 on 2016/3/3
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 * Animation to perform a moving path with a image-head.
 */

public class WoWoPathAnimation extends PageAnimation {

    private WoWoPathView pathView;
    private float fromProcess = UNINITIALIZED_VALUE;
    private float toProcess = UNINITIALIZED_VALUE;

    private WoWoPathAnimation(int page, float startOffset, float endOffset, int ease, TimeInterpolator interpolator, boolean useSameEaseEnumBack, WoWoPathView pathView, float fromProcess, float toProcess) {
        super(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack);
        this.pathView = pathView;
        this.fromProcess = fromProcess;
        this.toProcess = toProcess;
    }

    @Override
    protected void toStartState(View view) {
        pathView.setProcess(fromProcess);
    }

    @Override
    protected void toMiddleState(View view, float offset) {
        pathView.setProcess(fromProcess + (toProcess - fromProcess) * offset);
    }

    @Override
    protected void toEndState(View view) {
        pathView.setProcess(toProcess);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends PageAnimation.Builder<WoWoPathAnimation.Builder> {

        private float fromProcess = UNINITIALIZED_VALUE;
        private float toProcess = UNINITIALIZED_VALUE;
        private WoWoPathView pathView = null;

        public Builder from(float fromProcess) { this.fromProcess = fromProcess; return this; }

        public Builder from(double fromProcess) { return from((float) fromProcess); }

        public Builder to(float toProcess) { this.toProcess = toProcess; return this; }

        public Builder to(double toProcess) { return to((float) toProcess); }

        public Builder path(WoWoPathView pathView) {
            this.pathView = pathView;
            return this;
        }

        public WoWoPathAnimation build() {
            checkUninitializedAttributes();
            return new WoWoPathAnimation(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack, pathView, fromProcess, toProcess);
        }

        @Override
        protected void checkUninitializedAttributes() {
            if (pathView == null) uninitializedAttributeException("pathView");
            if (fromProcess == UNINITIALIZED_VALUE) uninitializedAttributeException("fromProcess");
            if (toProcess == UNINITIALIZED_VALUE) uninitializedAttributeException("toProcess");
        }
    }
}
