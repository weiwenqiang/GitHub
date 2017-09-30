package com.nightonke.wowoviewpager;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * Created by Weiping Huang at 14:01 on 2016/3/7
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 */

class WoWoScroller extends Scroller {

    private int duration = -1;

    public WoWoScroller(Context context) {
        super(context);
    }

    WoWoScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public WoWoScroller(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, this.duration == -1 ? duration : this.duration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        // Ignore received duration, use fixed one instead
        if (duration == -1) super.startScroll(startX, startY, dx, dy);
        else super.startScroll(startX, startY, dx, dy, duration);
    }

    void setDuration(int duration) {
        this.duration = duration;
    }
}
