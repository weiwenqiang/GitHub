package com.nightonke.wowoviewpager.Animation;

import android.animation.TimeInterpolator;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.nightonke.wowoviewpager.Enum.Typewriter;

import static com.nightonke.wowoviewpager.WoWoViewPager.TAG;

/**
 * Created by Weiping Huang at 14:14 on 2017/3/30
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 * Animation to change text in an instance of TextView.
 */

public class WoWoTextViewTextAnimation extends TextPageAnimation {

    public WoWoTextViewTextAnimation(int page, float startOffset, float endOffset, int ease, TimeInterpolator interpolator, boolean useSameEaseEnumBack, String fromText, String toText, Typewriter typewriter) {
        super(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack, fromText, toText, typewriter);
    }

    @Override
    protected void toStartState(View view) {
        setText(view, fromText);
    }

    @Override
    protected void toMiddleState(View view, float offset) {
        setText(view, typewriter.type(fromText, toText, offset));
    }

    @Override
    protected void toEndState(View view) {
        setText(view, toText);
    }

    private void setText(View view, String text) {
        if (view instanceof TextView) ((TextView) view).setText(text);
        else try {
            TextView.class.cast(view).setText(text);
        } catch (ClassCastException c) {
            Log.w(TAG, "View must be an instance of TextView in WoWoTextViewTextAnimation");
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends TextPageAnimation.Builder<WoWoTextViewTextAnimation.Builder> {

        public WoWoTextViewTextAnimation build() {
            checkUninitializedAttributes();
            return new WoWoTextViewTextAnimation(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack, fromText, toText, typewriter);
        }
    }
}
