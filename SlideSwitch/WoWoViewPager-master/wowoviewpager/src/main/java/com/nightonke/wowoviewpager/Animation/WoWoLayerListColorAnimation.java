package com.nightonke.wowoviewpager.Animation;

import android.animation.TimeInterpolator;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.View;

import com.nightonke.wowoviewpager.Enum.Chameleon;

import static com.nightonke.wowoviewpager.WoWoViewPager.TAG;

/**
 * Created by Weiping Huang at 16:05 on 2017/3/30
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 * Animation to change multi-colors for state-list {@link LayerDrawable}
 */

public class WoWoLayerListColorAnimation extends MultiColorPageAnimation {

    /**
     * Construct a page animation for multi-colors translation.
     *
     * @param page                The animation will be played when the (page + 1) page is starting to show.
     * @param startOffset         The animation only plays when the offset of page is large than startOffset.
     * @param endOffset           The animation only plays when the offset of page is less than endOffset.
     * @param ease                The ease type of the animation.
     * @param interpolator        Custom time interpolator.
     * @param useSameEaseEnumBack Whether use the same ease type of animation when swiping back the view-pager.
     * @param fromColors          The starting-colors.
     * @param toColors            The ending-colors.
     * @param chameleon           The color-changing-type. Check {@link Chameleon}
     */
    private WoWoLayerListColorAnimation(int page, float startOffset, float endOffset, int ease, TimeInterpolator interpolator, boolean useSameEaseEnumBack, int[] fromColors, int[] toColors, Chameleon chameleon) {
        super(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack, fromColors, toColors, chameleon);
    }

    @Override
    protected void toStartState(View view) {
        setColors(view, fromColors);
    }

    @Override
    protected void toMiddleState(View view, float offset) {
        setColors(view, middleColors(chameleon, offset));
    }

    @Override
    protected void toEndState(View view) {
        setColors(view, toColors);
    }

    private void setColors(View view, int[] colors) {
        Drawable drawable = view.getBackground();
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            for (int i = 0; i < colors.length; i++) if (layerDrawable.getDrawable(i) instanceof GradientDrawable) ((GradientDrawable) layerDrawable.getDrawable(i)).setColor(colors[i]);
        } else Log.w(TAG, "Drawable of view must be LayerDrawable in WoWoLayerListColorAnimation");
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends MultiColorPageAnimation.Builder<WoWoLayerListColorAnimation.Builder> {

        public WoWoLayerListColorAnimation build() {
            checkUninitializedAttributes();
            return new WoWoLayerListColorAnimation(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack, fromColors, toColors, chameleon);
        }
    }
}
