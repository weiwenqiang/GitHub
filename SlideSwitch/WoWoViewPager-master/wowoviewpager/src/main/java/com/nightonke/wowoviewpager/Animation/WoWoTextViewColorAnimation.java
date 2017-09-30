package com.nightonke.wowoviewpager.Animation;

import android.animation.TimeInterpolator;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.nightonke.wowoviewpager.Enum.Chameleon;

import static com.nightonke.wowoviewpager.WoWoViewPager.TAG;

/**
 * Created by Weiping Huang at 09:44 on 2017/3/30
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 * Animation to change text-color for any views those extend {@link TextView}
 */

public class WoWoTextViewColorAnimation extends SingleColorPageAnimation {

    /**
     * Construct a page animation for a single color translation.
     *
     * @param page                The animation will be played when the (page + 1) page is starting to show.
     * @param startOffset         The animation only plays when the offset of page is large than startOffset.
     * @param endOffset           The animation only plays when the offset of page is less than endOffset.
     * @param ease                The ease type of the animation.
     * @param interpolator        Custom time interpolator.
     * @param useSameEaseEnumBack Whether use the same ease type of animation when swiping back the view-pager.
     * @param fromColor           The starting-color.
     * @param toColor             The ending-color.
     * @param chameleon           The color-changing-type. Check {@link Chameleon}
     */
    private WoWoTextViewColorAnimation(int page, float startOffset, float endOffset, int ease, TimeInterpolator interpolator, boolean useSameEaseEnumBack, Integer fromColor, Integer toColor, Chameleon chameleon) {
        super(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack, fromColor, toColor, chameleon);
    }

    @Override
    protected void toStartState(View view) {
        setColor(view, fromColor);
    }

    @Override
    protected void toMiddleState(View view, float offset) {
        setColor(view, middleColor(chameleon, offset));
    }

    @Override
    protected void toEndState(View view) {
        setColor(view, toColor);
    }

    private void setColor(View view, int color) {
        if (view instanceof TextView) ((TextView) view).setTextColor(color);
        else try {
            TextView.class.cast(view).setTextColor(color);
        } catch (ClassCastException c) {
            Log.w(TAG, "View must be an instance of TextView in WoWoTextViewColorAnimation");
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends SingleColorPageAnimation.Builder<WoWoTextViewColorAnimation.Builder> {

        public WoWoTextViewColorAnimation build() {
            checkUninitializedAttributes();
            return new WoWoTextViewColorAnimation(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack, fromColor, toColor, chameleon);
        }
    }
}
