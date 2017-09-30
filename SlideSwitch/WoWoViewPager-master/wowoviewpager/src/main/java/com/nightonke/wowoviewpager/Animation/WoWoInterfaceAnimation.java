package com.nightonke.wowoviewpager.Animation;

import android.animation.TimeInterpolator;
import android.view.View;

/**
 * Created by Weiping Huang at 19:51 on 2017/3/30
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 * Animation to change the process of a {@link WoWoAnimationInterface}.
 */

public class WoWoInterfaceAnimation extends PageAnimation {

    private WoWoAnimationInterface animationInterface;

    private WoWoInterfaceAnimation(int page, float startOffset, float endOffset, int ease, TimeInterpolator interpolator, boolean useSameEaseEnumBack, WoWoAnimationInterface animationInterface) {
        super(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack);
        this.animationInterface = animationInterface;
    }

    @Override
    protected void toStartState(View view) {
        if (animationInterface != null) animationInterface.toStartState();
    }

    @Override
    protected void toMiddleState(View view, float offset) {
        if (animationInterface != null) animationInterface.toMiddleState(offset);
    }

    @Override
    protected void toEndState(View view) {
        if (animationInterface != null) animationInterface.toEndState();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends PageAnimation.Builder<WoWoInterfaceAnimation.Builder> {

        private WoWoAnimationInterface animationInterface = null;

        public Builder implementedBy(WoWoAnimationInterface animationInterface) {
            this.animationInterface = animationInterface;
            return this;
        }

        public WoWoInterfaceAnimation build() {
            checkUninitializedAttributes();
            return new WoWoInterfaceAnimation(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack, animationInterface);
        }

        @Override
        protected void checkUninitializedAttributes() {
            if (animationInterface == null) uninitializedAttributeException("animationInterface");
        }
    }
}
