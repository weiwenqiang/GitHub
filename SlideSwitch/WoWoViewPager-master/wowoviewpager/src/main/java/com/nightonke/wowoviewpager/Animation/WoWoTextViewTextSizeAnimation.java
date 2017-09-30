package com.nightonke.wowoviewpager.Animation;

import android.animation.TimeInterpolator;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import static com.nightonke.wowoviewpager.WoWoViewPager.TAG;

/**
 * Created by Weiping Huang at 13:06 on 2017/3/30
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 * Animation to change text-size for any views those extend {@link TextView}
 */

public class WoWoTextViewTextSizeAnimation extends PageAnimation {

    private float fromSize = UNINITIALIZED_VALUE;
    private float toSize = UNINITIALIZED_VALUE;
    private int unit = TypedValue.COMPLEX_UNIT_SP;

    private WoWoTextViewTextSizeAnimation(int page, float startOffset, float endOffset, int ease, TimeInterpolator interpolator, boolean useSameEaseEnumBack, float fromSize, float toSize, int unit) {
        super(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack);
        this.fromSize = fromSize;
        this.toSize = toSize;
        this.unit = unit;
    }

    @Override
    protected void toStartState(View view) {
        setTextSize(view, fromSize);
    }

    @Override
    protected void toMiddleState(View view, float offset) {
        setTextSize(view, fromSize + (toSize - fromSize) * offset);
    }

    @Override
    protected void toEndState(View view) {
        setTextSize(view, toSize);
    }

    private void setTextSize(View view, float textSize) {
        if (view instanceof TextView) ((TextView) view).setTextSize(unit, textSize);
        else try {
            TextView.class.cast(view).setTextSize(unit, textSize);
        } catch (ClassCastException c) {
            Log.w(TAG, "View must be an instance of TextView in WoWoTextViewTextSizeAnimation");
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends PageAnimation.Builder<WoWoTextViewTextSizeAnimation.Builder> {

        private float fromSize = UNINITIALIZED_VALUE;
        private float toSize = UNINITIALIZED_VALUE;
        private int unit = TypedValue.COMPLEX_UNIT_SP;

        public Builder from(float fromSize) { this.fromSize = fromSize; return this; }

        public Builder fromPx(float fromSize) { unit = TypedValue.COMPLEX_UNIT_PX; return from(fromSize); }

        public Builder fromSp(float fromSize) { unit = TypedValue.COMPLEX_UNIT_SP; return from(fromSize); }

        public Builder fromDp(float fromSize) { unit = TypedValue.COMPLEX_UNIT_DIP; return from(fromSize); }

        public Builder to(float toSize) { this.toSize = toSize; return this; }

        public Builder toPx(float toSize) { unit = TypedValue.COMPLEX_UNIT_PX; return to(toSize); }

        public Builder toSp(float toSize) { unit = TypedValue.COMPLEX_UNIT_SP; return to(toSize); }

        public Builder toDp(float toSize) { unit = TypedValue.COMPLEX_UNIT_DIP; return to(toSize); }

        public Builder from(double fromSize) { return from((float) fromSize); }

        public Builder fromPx(double fromSize) { return fromPx((float) fromSize); }

        public Builder fromSp(double fromSize) { return fromSp((float) fromSize); }

        public Builder fromDp(double fromSize) { return fromDp((float) fromSize); }

        public Builder to(double toSize) { return to((float) toSize); }

        public Builder toPx(double toSize) { return toPx((float) toSize); }

        public Builder toSp(double toSize) { return toSp((float) toSize); }

        public Builder toDp(double toSize) { return toDp((float) toSize); }

        public Builder unit(int unit) { this.unit = unit; return this; }

        public WoWoTextViewTextSizeAnimation build() {
            checkUninitializedAttributes();
            return new WoWoTextViewTextSizeAnimation(page, startOffset, endOffset, ease, interpolator, useSameEaseEnumBack, fromSize, toSize, unit);
        }

        @Override
        protected void checkUninitializedAttributes() {
            if (fromSize == UNINITIALIZED_VALUE) uninitializedAttributeException("fromSize");
            if (toSize == UNINITIALIZED_VALUE) uninitializedAttributeException("toSize");
        }
    }
}
