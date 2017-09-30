package com.nightonke.wowoviewpager.Animation;

import android.animation.TimeInterpolator;
import android.view.View;

import com.nightonke.wowoviewpager.Enum.Ease;

/**
 * Created by Weiping Huang at 02:29 on 2016/3/3
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 * PageAnimation play a kind of animation for a view started from a page in viewpager
 */

public abstract class PageAnimation {

    /**
     * Uninitialized value.
     */
    protected static final float UNINITIALIZED_VALUE = Float.MAX_VALUE - 2584541288f;

    /**
     * The animation will be played when the (page + 1) page is starting to show.
     */
    protected int page;

    /**
     * The animation only plays when the offset of page is large than startOffset.
     */
    protected float startOffset;

    /**
     * The animation only plays when the offset of page is less than endOffset.
     */
    protected float endOffset;

    /**
     * The ease type of the animation.
     */
    protected TimeInterpolator easer = Ease.getInstance(Ease.Linear);

    /**
     * Whether use the same ease type of animation when swiping back the view-pager.
     */
    protected boolean useSameEaseEnumBack = true;

    /**
     * Offset relative to the animation last time.
     */
    private float lastOffset = -1;

    /**
     * The offset is less than the start-offset last time.
     */
    private boolean wasLessThanStartOffset = false;

    /**
     * The offset is exceed the end-offset last time.
     */
    private boolean wasExceedEndOffset = false;

    /**
     * Construct a page animation.
     *
     * @param page The animation will be played when the (page + 1) page is starting to show.
     * @param startOffset The animation only plays when the offset of page is large than startOffset.
     * @param endOffset The animation only plays when the offset of page is less than endOffset.
     * @param ease The ease type of the animation.
     * @param interpolator Custom time interpolator.
     * @param useSameEaseEnumBack Whether use the same ease type of animation when swiping back the view-pager.
     */
    protected PageAnimation(int page, float startOffset, float endOffset, int ease, TimeInterpolator interpolator, boolean useSameEaseEnumBack) {
        this.page = page;
        this.startOffset = startOffset;
        this.endOffset = endOffset;
        if (interpolator != null) this.easer = interpolator;
        else this.easer = Ease.getInstance(ease);
        this.useSameEaseEnumBack = useSameEaseEnumBack;
    }

//    /**
//     *
//     * @param view Animation-playing-view
//     * @param offset Offset of the view-pager
//     * @param upstreamFirst Upstream has the priority
//     * @param easeReverse Only this value is true, we calculate the ease-offset reversely
//     * @return True to stop the next page-animation to play.
//     */
//    protected boolean play(View view, float offset, boolean upstreamFirst, boolean easeReverse) {
//        // if the offset is less than the start offset,
//        // we should set the view to start-state.
//        if (offset <= startOffset) {
//            if (wasLessThanStartOffset) return upstreamFirst;  // We have do this before
//            toStartState(view);
//            wasLessThanStartOffset = true;
//            return upstreamFirst;
//        }
//        wasLessThanStartOffset = false;
//
//        // if the offset exceeds the endOffset,
//        // we should set the view to end-state.
//        if (offset >= endOffset) {
//            if (wasExceedEndOffset) return !upstreamFirst;  // We have do this before
//            toEndState(view);
//            wasExceedEndOffset = true;
//            return !upstreamFirst;
//        }
//        wasExceedEndOffset = false;
//
//        // else, set the view to middle-state
//        float relativeOffset = relativeOffset(offset);
//        float easeOffset;
//
//        if (lastOffset == -1) easeOffset = ease.getInterpolation(relativeOffset);  // first playing
//        else {
//            if (easeReverse) {  // swiping view-pager reversely from a zero offset
//                if (useSameEaseEnumBack) easeOffset = 1 - ease.getInterpolation(1 - relativeOffset);
//                else easeOffset = ease.getInterpolation(relativeOffset);
//            } else easeOffset = ease.getInterpolation(relativeOffset);  // forward
//        }
//        lastOffset = relativeOffset;
//
//        toMiddleState(view, easeOffset);
//
//        return true;
//    }

    /**
     *
     * @param view Animation-playing-view
     * @param offset Offset of the view-pager
     * @param easeReverse Only this value is true, we calculate the ease-offset reversely
     * @return True to stop the next page-animation to play.
     */
    boolean play(View view, float offset, boolean easeReverse) {
        // if the offset is less than the start offset,
        // we should set the view to start-state.
        if (offset <= startOffset) {
            if (wasLessThanStartOffset) return false;  // We have do this before
            toStartState(view);
            wasLessThanStartOffset = true;
            return false;
        }
        wasLessThanStartOffset = false;

        // if the offset exceeds the endOffset,
        // we should set the view to end-state.
        if (offset >= endOffset) {
            if (wasExceedEndOffset) return false;  // We have do this before
            toEndState(view);
            wasExceedEndOffset = true;
            return false;
        }
        wasExceedEndOffset = false;

        // else, set the view to middle-state
        float relativeOffset = relativeOffset(offset);
        float easeOffset;

        if (lastOffset == -1) easeOffset = easer.getInterpolation(relativeOffset);  // first playing
        else {
            if (easeReverse) {  // swiping view-pager reversely from a zero offset
                if (useSameEaseEnumBack) easeOffset = 1 - easer.getInterpolation(1 - relativeOffset);
                else easeOffset = easer.getInterpolation(relativeOffset);
            } else easeOffset = easer.getInterpolation(relativeOffset);  // forward
        }
        lastOffset = relativeOffset;

        toMiddleState(view, easeOffset);

        return false;
    }

    /**
     * Set the view to start-state.
     *
     * @param view Animation-playing-view
     */
    protected abstract void toStartState(View view);

    /**
     * Set the view to middle-state.
     *
     * @param view Animation-playing-view
     * @param offset Offset
     */
    protected abstract void toMiddleState(View view, float offset);

    /**
     * Set the view to end-state.
     *
     * @param view Animation-playing-view
     */
    protected abstract void toEndState(View view);

    void setToStartStateFlag() {
        wasLessThanStartOffset = true;
        wasExceedEndOffset = false;
    }

    void setToEndStateFlag() {
        wasLessThanStartOffset = false;
        wasExceedEndOffset = true;
    }

    /**
     * Get offset in animation from offset in view-pager.
     *
     * @param offset Offset in view-pager
     * @return Offset in animation
     */
    private float relativeOffset(float offset) {
        return (offset - startOffset) / (endOffset - startOffset);
    }

    /**
     * Set ease type for animation.
     *
     * @param ease Ease enum
     */
    void setEaseEnum(int ease) { setTimeInterpolator(Ease.getInstance(ease)); }

    /**
     * Set TimeInterpolator for animation.
     *
     * @param easer TimeInterpolator
     */
    void setTimeInterpolator(TimeInterpolator easer) { this.easer = easer; }

    @SuppressWarnings("unchecked")
    public static abstract class Builder<T> {

        protected int page = 0;
        protected float startOffset = 0;
        protected float endOffset = 1;
        protected int ease = Ease.Linear;
        protected TimeInterpolator interpolator = null;
        protected boolean useSameEaseEnumBack = true;

        public T page(int page) { this.page = page; return (T)this; }

        public T start(float startOffset) { this.startOffset = startOffset; return (T)this; }

        public T start(double startOffset) { return start((float) startOffset); }

        public T end(float endOffset) { this.endOffset = endOffset; return (T)this; }

        public T end(double endOffset) { return end((float) endOffset); }

        public T ease(int ease) { this.ease = ease; return (T)this; }

        public T interpolator(TimeInterpolator interpolator) { this.interpolator = interpolator; return (T) this; }

        public T sameEaseBack(boolean useSameEaseEnumBack) { this.useSameEaseEnumBack = useSameEaseEnumBack; return (T)this; }

        /**
         * Check for some attributes which must be initialized.
         */
        protected abstract void checkUninitializedAttributes();

        /**
         * Throw an exception for method checkUninitializedAttributes.
         *
         * @param attributeName Name of attribute
         */
        protected void uninitializedAttributeException(String attributeName) {
            throw new RuntimeException("Attribute '" + attributeName + "' is not initialized!");
        }
    }
}
