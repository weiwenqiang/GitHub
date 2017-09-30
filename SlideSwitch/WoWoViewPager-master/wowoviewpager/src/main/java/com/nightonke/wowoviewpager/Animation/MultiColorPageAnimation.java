package com.nightonke.wowoviewpager.Animation;

import android.animation.TimeInterpolator;
import android.graphics.Color;

import com.nightonke.wowoviewpager.Enum.Chameleon;

import java.util.List;

/**
 * Created by Weiping Huang at 12:07 on 2017/3/30
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 * Use as an abstract class of multi-color transformation animations.
 * This methods are able to override outside package.
 * Check {@link WoWoLayerListColorAnimation} and {@link WoWoStateListColorAnimation} as examples.
 */

public abstract class MultiColorPageAnimation extends PageAnimation {

    protected int[] fromColors = null;
    protected int[] toColors = null;
    protected Chameleon chameleon = Chameleon.RGB;

    private int[][] fromARGBArrays = null;
    private int[][] toARGBArrays = null;
    private float[][] fromHSVArrays = null;
    private float[][] toHSVArrays = null;

    /**
     * Construct a page animation for multi-colors translation.
     *
     * @param page The animation will be played when the (page + 1) page is starting to show.
     * @param startOffset The animation only plays when the offset of page is large than startOffset.
     * @param endOffset The animation only plays when the offset of page is less than endOffset.
     * @param ease The ease type of the animation.
     * @param interpolator Custom time interpolator.
     * @param useSameEaseEnumBack Whether use the same ease type of animation when swiping back the view-pager.
     * @param fromColors The starting-colors.
     * @param toColors The ending-colors.
     * @param chameleon The color-changing-type. Check {@link Chameleon}
     */
    public MultiColorPageAnimation(int page, float startOffset, float endOffset, int ease, TimeInterpolator interpolator, boolean useSameEaseEnumBack, int[] fromColors, int[] toColors, Chameleon chameleon) {
        super(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack);
        this.fromColors = fromColors;
        this.toColors = toColors;
        this.chameleon = chameleon;

        prepareColors();
    }

    protected int[] middleColors(Chameleon chameleon, float offset) {
        if (chameleon == Chameleon.RGB) return middleRGBColors(offset);
        else if (chameleon == Chameleon.HSV) return middleHSVColors(offset);
        throw new RuntimeException("Unknown Chameleon!");
    }

    private int[] middleRGBColors(float offset) {
        return Chameleon.getARGBColors(fromARGBArrays, toARGBArrays, offset);
    }

    private int[] middleHSVColors(float offset) {
        return Chameleon.getHSVColors(fromHSVArrays, toHSVArrays, offset);
    }

    private void prepareColors() {
        if (chameleon == Chameleon.RGB) {
            fromARGBArrays = Chameleon.toARGBArrays(fromColors);
            toARGBArrays = Chameleon.toARGBArrays(toColors);
        } else if (chameleon == Chameleon.HSV) {
            fromHSVArrays = Chameleon.toHSVArrays(fromColors);
            toHSVArrays = Chameleon.toHSVArrays(toColors);
        }
    }

    @SuppressWarnings("unchecked")
    public static class Builder<T> extends PageAnimation.Builder<T> {

        protected int[] fromColors = null;
        protected int[] toColors = null;
        protected Chameleon chameleon = Chameleon.RGB;

        public T from(int... fromColors) { this.fromColors = fromColors; return (T) this; }

        public T from(String... fromColors) {
            this.fromColors = new int[fromColors.length];
            for (int i = 0; i < fromColors.length; i++) this.fromColors[i] = Color.parseColor(fromColors[i]);
            return (T) this;
        }

        public T from(List<Object> fromColors) {
            this.fromColors = new int[fromColors.size()];
            for (int i = 0; i < fromColors.size(); i++) {
                Object color = fromColors.get(i);
                if (color instanceof Integer) this.fromColors[i] = (int) color;
                else if (color instanceof String) this.fromColors[i] = Color.parseColor((String) color);
            }
            return (T) this;
        }

        public T to(int... toColors) { this.toColors = toColors; return (T) this; }

        public T to(String... toColors) {
            this.toColors = new int[toColors.length];
            for (int i = 0; i < toColors.length; i++) this.toColors[i] = Color.parseColor(toColors[i]);
            return (T) this;
        }

        public T to(List<Object> toColors) {
            this.toColors = new int[toColors.size()];
            for (int i = 0; i < toColors.size(); i++) {
                Object color = toColors.get(i);
                if (color instanceof Integer) this.toColors[i] = (int) color;
                else if (color instanceof String) this.toColors[i] = Color.parseColor((String) color);
            }
            return (T) this;
        }

        public T chameleon(Chameleon chameleon) { this.chameleon = chameleon; return (T) this; }

        @Override
        protected void checkUninitializedAttributes() {
            if (fromColors == null) uninitializedAttributeException("fromColors");
            if (toColors == null) uninitializedAttributeException("toColors");
        }
    }
}
