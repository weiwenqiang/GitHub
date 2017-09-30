package com.nightonke.wowoviewpager.Animation;

import android.animation.TimeInterpolator;
import android.graphics.Color;

import com.nightonke.wowoviewpager.Enum.Chameleon;

/**
 * Created by Weiping Huang at 09:49 on 2017/3/30
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 * Use as an abstract class of single-color transformation animations.
 * This methods are able to override outside package.
 * Check {@link WoWoBackgroundColorAnimation} and {@link WoWoShapeColorAnimation} as examples.
 */

public abstract class SingleColorPageAnimation extends PageAnimation {

    protected Integer fromColor = null;
    protected Integer toColor = null;
    protected Chameleon chameleon = Chameleon.RGB;

    private int[] fromARGBArray = null;
    private int[] toARGBArray = null;
    private float[] fromHSVArray = null;
    private float[] toHSVArray = null;

    /**
     * Construct a page animation for a single color translation.
     *
     * @param page The animation will be played when the (page + 1) page is starting to show.
     * @param startOffset The animation only plays when the offset of page is large than startOffset.
     * @param endOffset The animation only plays when the offset of page is less than endOffset.
     * @param ease The ease type of the animation.
     * @param interpolator Custom time interpolator.
     * @param useSameEaseEnumBack Whether use the same ease type of animation when swiping back the view-pager.
     * @param fromColor The starting-color.
     * @param toColor The ending-color.
     * @param chameleon The color-changing-type. Check {@link Chameleon}
     */
    public SingleColorPageAnimation(int page, float startOffset, float endOffset, int ease, TimeInterpolator interpolator, boolean useSameEaseEnumBack, Integer fromColor, Integer toColor, Chameleon chameleon) {
        super(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack);
        this.fromColor = fromColor;
        this.toColor = toColor;
        this.chameleon = chameleon;

        prepareColor();
    }

    private void prepareColor() {
        if (chameleon == Chameleon.RGB) {
            fromARGBArray = Chameleon.toARGBArray(fromColor);
            toARGBArray = Chameleon.toARGBArray(toColor);
        } else if (chameleon == Chameleon.HSV) {
            fromHSVArray = Chameleon.toHSVArray(fromColor);
            toHSVArray = Chameleon.toHSVArray(toColor);
        }
    }

    protected int middleColor(Chameleon chameleon, float offset) {
        if (chameleon == Chameleon.RGB) return middleRGBColor(offset);
        else if (chameleon == Chameleon.HSV) return middleHSVColor(offset);
        throw new RuntimeException("Unknown Chameleon!");
    }

    private int middleRGBColor(float offset) {
        return Chameleon.getARGBColor(fromARGBArray, toARGBArray, offset);
    }

    private int middleHSVColor(float offset) {
        return Chameleon.getHSVColor(fromHSVArray, toHSVArray, offset);
    }

    @SuppressWarnings("unchecked")
    public static class Builder<T> extends PageAnimation.Builder<T> {

        protected Integer fromColor = null;
        protected Integer toColor = null;
        protected Chameleon chameleon = Chameleon.RGB;

        public T from(Integer fromColor) { this.fromColor = fromColor; return (T) this; }

        public T from(String fromColor) { return from(Color.parseColor(fromColor)); }

        public T to(Integer toColor) { this.toColor = toColor; return (T) this; }

        public T to(String toColor) { return to(Color.parseColor(toColor)); }

        public T chameleon(Chameleon chameleon) { this.chameleon = chameleon; return (T) this; }

        @Override
        protected void checkUninitializedAttributes() {
            if (fromColor == null) uninitializedAttributeException("fromColor");
            if (toColor == null) uninitializedAttributeException("toColor");
        }
    }
}
