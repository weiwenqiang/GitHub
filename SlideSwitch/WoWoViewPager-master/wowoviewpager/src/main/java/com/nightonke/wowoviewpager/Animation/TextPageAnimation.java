package com.nightonke.wowoviewpager.Animation;

import android.animation.TimeInterpolator;

import com.nightonke.wowoviewpager.Enum.Typewriter;
import com.nightonke.wowoviewpager.Enum.WoWoTypewriter;

/**
 * Created by Weiping Huang at 01:58 on 2017/4/6
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 * Use as an abstract class of text transformation animations.
 * This methods are able to override outside package.
 * Check {@link WoWoTextViewTextAnimation} as an example.
 */

public abstract class TextPageAnimation extends PageAnimation {

    protected String fromText;
    protected String toText;
    protected Typewriter typewriter;

    public TextPageAnimation(int page, float startOffset, float endOffset, int ease, TimeInterpolator interpolator, boolean useSameEaseEnumBack, String fromText, String toText, Typewriter typewriter) {
        super(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack);
        this.fromText = fromText;
        this.toText = toText;
        this.typewriter = typewriter;
    }

    @SuppressWarnings("unchecked")
    public static class Builder<T> extends PageAnimation.Builder<T> {

        protected String fromText;
        protected String toText;
        protected Typewriter typewriter = WoWoTypewriter.DeleteThenType;

        public T from(String fromText) { this.fromText = fromText; return (T) this; }

        public T to(String toText) { this.toText = toText; return (T) this; }

        public T typewriter(Typewriter typewriter) { this.typewriter = typewriter; return (T) this; }

        @Override
        protected void checkUninitializedAttributes() {
            if (fromText == null) uninitializedAttributeException("fromText");
            if (toText == null) uninitializedAttributeException("toText");
            if (typewriter == null) uninitializedAttributeException("typewriter");
        }
    }
}
