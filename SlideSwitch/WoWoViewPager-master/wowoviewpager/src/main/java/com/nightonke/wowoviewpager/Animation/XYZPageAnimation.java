package com.nightonke.wowoviewpager.Animation;

import android.animation.TimeInterpolator;

/**
 * Created by Weiping Huang at 14:03 on 2017/4/4
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 * Use as an abstract class of x-y transformation animations.
 * This methods are able to override outside package.
 * Check {@link WoWoPosition3DAnimation} and {@link WoWoTranslation3DAnimation} as examples.
 */

public abstract class XYZPageAnimation extends XYPageAnimation {

    protected float fromZ = UNINITIALIZED_VALUE;
    protected float toZ = UNINITIALIZED_VALUE;

    /**
     * Construct a page animation for x-y-z changing animation.
     * 
     * @param page The animation will be played when the (page + 1) page is starting to show.
     * @param startOffset The animation only plays when the offset of page is large than startOffset.
     * @param endOffset The animation only plays when the offset of page is less than endOffset.
     * @param ease The ease type of the animation.
     * @param interpolator Custom time interpolator.
     * @param useSameEaseEnumBack Whether use the same ease type of animation when swiping back the view-pager.
     * @param fromX From x value.
     * @param fromY From y value.
     * @param fromZ From z value.
     * @param toX To x value.
     * @param toY To y value.
     * @param toZ To z value.
     */
    public XYZPageAnimation(int page, float startOffset, float endOffset, int ease, TimeInterpolator interpolator, boolean useSameEaseEnumBack, float fromX, float fromY, float fromZ, float toX, float toY, float toZ) {
        super(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack, fromX, fromY, toX, toY);
        this.fromZ = fromZ;
        this.toZ = toZ;
    }

    @SuppressWarnings("unchecked")
    public static class Builder<T> extends XYPageAnimation.Builder<T> {

        protected float fromZ = UNINITIALIZED_VALUE;
        protected float toZ = UNINITIALIZED_VALUE;

        public T fromZ(float z) { this.fromZ = z; return (T) this; }

        public T fromZ(double z) { return fromZ((float) z); }

        public T toZ(float z) { this.toZ = z; return (T) this; }

        public T toZ(double z) { return toZ((float) z); }

        public T keepZ(float z) { fromZ(z); return toZ(z); }

        public T keepZ(double z) { return keepZ((float) z); }
        
        @Override
        protected void checkUninitializedAttributes() {
            super.checkUninitializedAttributes();
            if (fromZ == UNINITIALIZED_VALUE) uninitializedAttributeException("fromZ");
            if (toZ == UNINITIALIZED_VALUE) uninitializedAttributeException("toZ");
        }
    }
}
