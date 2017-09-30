package com.nightonke.wowoviewpager.Animation;

import android.animation.TimeInterpolator;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.View;

import com.nightonke.wowoviewpager.Enum.Chameleon;

import static com.nightonke.wowoviewpager.WoWoViewPager.TAG;

/**
 * Created by Weiping Huang at 10:49 on 2017/3/30
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 * Animation to change color for {@link GradientDrawable#setColor(int)}
 */

public class WoWoShapeColorAnimation extends SingleColorPageAnimation {

    private WoWoShapeColorAnimation(int page, float startOffset, float endOffset, int ease, TimeInterpolator interpolator, boolean useSameEaseEnumBack, Integer fromColor, Integer toColor, Chameleon chameleon) {
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
        Drawable drawable = view.getBackground();
        if (drawable instanceof GradientDrawable) ((GradientDrawable) drawable).setColor(color);
        else Log.w(TAG, "Background must be an instance of GradientDrawable in WoWoShapeColorAnimation");
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends SingleColorPageAnimation.Builder<WoWoShapeColorAnimation.Builder> {

        public WoWoShapeColorAnimation build() {
            checkUninitializedAttributes();
            return new WoWoShapeColorAnimation(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack, fromColor, toColor, chameleon);
        }
    }
}
