package com.nightonke.wowoviewpager.Animation;

import android.animation.TimeInterpolator;

/**
 * Created by Weiping Huang at 13:31 on 2017/4/4
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 * Use as an abstract class of x-y transformation animations.
 * This methods are able to override outside package.
 * Check {@link WoWoPositionAnimation} and {@link WoWoTranslationAnimation} as examples.
 */

public abstract class XYPageAnimation extends PageAnimation {

    protected float fromX = UNINITIALIZED_VALUE;
    protected float fromY = UNINITIALIZED_VALUE;
    protected float toX = UNINITIALIZED_VALUE;
    protected float toY = UNINITIALIZED_VALUE;

    /**
     * Construct a page animation for x-y changing animation.
     *
     * @param page The animation will be played when the (page + 1) page is starting to show.
     * @param startOffset The animation only plays when the offset of page is large than startOffset.
     * @param endOffset The animation only plays when the offset of page is less than endOffset.
     * @param ease The ease type of the animation.
     * @param interpolator Custom time interpolator.
     * @param useSameEaseEnumBack Whether use the same ease type of animation when swiping back the view-pager.
     * @param fromX From x value.
     * @param fromY From y value.
     * @param toX To x value.
     * @param toY To y value.
     */
    public XYPageAnimation(int page, float startOffset, float endOffset, int ease, TimeInterpolator interpolator, boolean useSameEaseEnumBack, float fromX, float fromY, float toX, float toY) {
        super(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack);
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
    }

    @SuppressWarnings("unchecked")
    public static class Builder<T> extends PageAnimation.Builder<T> {

        protected float fromX = UNINITIALIZED_VALUE;
        protected float fromY = UNINITIALIZED_VALUE;
        protected float toX = UNINITIALIZED_VALUE;
        protected float toY = UNINITIALIZED_VALUE;

        public T fromX(float x) { this.fromX = x; return (T) this; }

        public T fromX(double x) { return fromX((float) x); }

        public T toX(float x) { this.toX = x; return (T) this; }

        public T toX(double x) { return toX((float) x); }

        public T keepX(float x) { fromX(x); return toX(x); }

        public T keepX(double x) { return keepX((float) x); }

        public T fromY(float y) { this.fromY = y; return (T) this; }

        public T fromY(double y) { return fromY((float) y); }

        public T toY(float y) { this.toY = y; return (T) this; }

        public T toY(double y) { return toY((float) y); }

        public T keepY(float y) { fromY(y); return toY(y); }

        public T keepY(double y) { return keepY((float) y); }

        public T fromXY(float xy) { fromX(xy); return fromY(xy); }

        public T fromXY(double xy) { return fromXY((float) xy); }

        public T toXY(float xy) { toX(xy); return toY(xy); }

        public T toXY(double xy) { return toXY((float) xy); }

        public T keepXY(float xy) { keepX(xy); return keepY(xy); }

        public T keepXY(double xy) { return keepXY((float) xy); }

        @Override
        protected void checkUninitializedAttributes() {
            if (fromX == UNINITIALIZED_VALUE) uninitializedAttributeException("fromX");
            if (fromY == UNINITIALIZED_VALUE) uninitializedAttributeException("fromY");
            if (toX == UNINITIALIZED_VALUE) uninitializedAttributeException("toX");
            if (toY == UNINITIALIZED_VALUE) uninitializedAttributeException("toY");
        }
    }
}
