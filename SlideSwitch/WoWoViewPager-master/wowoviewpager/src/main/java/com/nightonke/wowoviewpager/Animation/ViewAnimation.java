package com.nightonke.wowoviewpager.Animation;

import android.animation.TimeInterpolator;
import android.view.View;

import com.nightonke.wowoviewpager.Enum.Ease;

import java.util.ArrayList;

/**
 * Created by Weiping Huang at 14:28 on 2016/3/3
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 * View animation contains page animations for the same view.
 */

public class ViewAnimation {

    private View view;
    private ArrayList<PageAnimationList> pageAnimations;

    /**
     * Construct a view animation for a certain view.
     *
     * @param view the view
     * @throws NullPointerException Exception when the view is null
     */
    public ViewAnimation(View view) throws NullPointerException {
        if (view == null) throw new NullPointerException("The view for view-animation must not bee null!");
        this.view = view;
    }

    /**
     * Add a pageAnimation to the view.
     * The pageAnimation start playing from pageAnimation.getPage().
     *
     * @param pageAnimation The new pageAnimation
     * @return ViewAnimation itself
     */
    public ViewAnimation add(PageAnimation pageAnimation) {
        int page = pageAnimation.page;

        if (pageAnimations == null) pageAnimations = new ArrayList<>();
        while (page + 1 > pageAnimations.size()) pageAnimations.add(new PageAnimationList());

        ArrayList<PageAnimation> pageAnimationsInPage = pageAnimations.get(page);
        if (pageAnimationsInPage == null) pageAnimationsInPage = new ArrayList<>();
        pageAnimationsInPage.add(pageAnimation);
        return this;
    }

    /**
     * Play all pageAnimations which should start playing from page.
     *
     * @param page The starting page, corresponding animations will end at page + 1
     * @param offset The offset of the animation
     */
    public void play(int page, float offset, boolean easeReverse) {
        if (pageAnimations == null || page >= pageAnimations.size() || page < 0) return;
        ArrayList<PageAnimation> pageAnimationsInSamePage = pageAnimations.get(page);
        if (pageAnimationsInSamePage != null) {
            for (PageAnimation pageAnimation : pageAnimationsInSamePage) {
                if (pageAnimation.play(view, offset, easeReverse)) return;
            }
// Keep this comment as an old implement.
//            if (upstreamFirst) {
//                for (PageAnimation pageAnimation : pageAnimationsInSamePage) {
//                    if (pageAnimation.play(view, offset, upstreamFirst, easeReverse)) return;
//                }
//            } else {
//                for (int i = pageAnimationsInSamePage.size() - 1; i >= 0; i--) {
//                    if (pageAnimationsInSamePage.get(i).play(view, offset, upstreamFirst, easeReverse)) return;
//                }
//            }
        }
    }

    /**
     * Force animations to the end-states.
     *
     * @param page The position of the animations forced
     */
    public void forceAnimationToEndStateInPreviousPage(int page) {
        if (pageAnimations == null || page >= pageAnimations.size() || page < 0) return;
        ArrayList<PageAnimation> pageAnimationsInSamePage = pageAnimations.get(page);
        for (PageAnimation pageAnimation : pageAnimationsInSamePage) {
            pageAnimation.toEndState(view);
            pageAnimation.setToEndStateFlag();
        }
    }

    /**
     * Force animations to the start-states.
     *
     * @param page The position of the animations forced
     */
    public void forceAnimationToStartStateInNextPage(int page) {
        if (pageAnimations == null || page >= pageAnimations.size() || page < 0) return;
        ArrayList<PageAnimation> pageAnimationsInSamePage = pageAnimations.get(page);
        for (PageAnimation pageAnimation : pageAnimationsInSamePage) {
            pageAnimation.toStartState(view);
            pageAnimation.setToStartStateFlag();
        }
    }

    /**
     * Set ease type for all page animations.
     *
     * @param ease Ease
     */
    public void setEase(int ease) { setTimeInterpolator(Ease.getInstance(ease)); }

    /**
     * Set TimeInterpolator for all page animations.
     *
     * @param interpolator TimeInterpolator
     */
    public void setTimeInterpolator(TimeInterpolator interpolator) {
        if (pageAnimations == null) return;
        for (ArrayList<PageAnimation> pageAnimationsInSamePage : pageAnimations) {
            if (pageAnimationsInSamePage == null) continue;
            for (PageAnimation pageAnimation : pageAnimationsInSamePage) pageAnimation.setTimeInterpolator(interpolator);
        }
    }

    /**
     * Set ease type for all page animations at a certain page.
     *
     * @param ease Ease
     * @param page Certain page
     */
    public void setEase(int ease, int page) { setTimeInterpolator(Ease.getInstance(ease), page); }

    /**
     * Set TimeInterpolator for all page animations at a certain page.
     *
     * @param interpolator TimeInterpolator
     * @param page Certain page
     */
    public void setTimeInterpolator(TimeInterpolator interpolator, int page) {
        if (pageAnimations == null || page >= pageAnimations.size() || page < 0) return;
        ArrayList<PageAnimation> pageAnimationsInSamePage = pageAnimations.get(page);
        if (pageAnimationsInSamePage == null) return;
        for (PageAnimation pageAnimation : pageAnimationsInSamePage) pageAnimation.setTimeInterpolator(interpolator);
    }

    /**
     * Whether use same ease enum when swiping the view-pager back for all page animations.
     *
     * @param useSameEaseEnumBack Use same ease enum when swiping the view-pager back
     */
    public void setUseSameEaseBack(boolean useSameEaseEnumBack) {
        if (pageAnimations == null) return;
        for (ArrayList<PageAnimation> pageAnimationsInSamePage : pageAnimations) {
            if (pageAnimationsInSamePage == null) continue;
            for (PageAnimation pageAnimation : pageAnimationsInSamePage) pageAnimation.useSameEaseEnumBack = useSameEaseEnumBack;
        }
    }

    /**
     * Whether use same ease enum when swiping the view-pager back at a certain page.
     *
     * @param useSameEaseEnumBack Use same ease enum when swiping the view-pager back
     * @param page Certain page
     */
    public void setUseSameEaseBack(boolean useSameEaseEnumBack, int page) {
        if (pageAnimations == null || page >= pageAnimations.size() || page < 0) return;
        ArrayList<PageAnimation> pageAnimationsInSamePage = pageAnimations.get(page);
        if (pageAnimationsInSamePage == null) return;
        for (PageAnimation pageAnimation : pageAnimationsInSamePage) pageAnimation.useSameEaseEnumBack = useSameEaseEnumBack;
    }

    private class PageAnimationList extends ArrayList<PageAnimation> {
        @Override
        public boolean add(PageAnimation pageAnimation) {
            if (pageAnimation == null) return false;
            boolean added = false;
            for (int i = 0; i < this.size(); i++) {
                PageAnimation p = this.get(i);
                if (pageAnimation.startOffset <= p.startOffset && pageAnimation.endOffset <= p.endOffset) {
                    super.add(i, pageAnimation);
                    added = true;
                    break;
                }
            }
            if (!added) super.add(pageAnimation);
            return true;
        }
    }

}
