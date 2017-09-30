package com.hpw.myapp.widget.media;

import com.hpw.myapp.ui.tv.activity.TvShowActivity;

/**
 * Created by hpw on 16/12/3.
 */

public class OnVerticalControllListener implements VerticalMediaControllView.OnVerticalControllListener {

    private TvShowActivity activity;
    private LivePlayerHolder playerHolder;

    public OnVerticalControllListener(TvShowActivity activity, LivePlayerHolder playerHolder) {
        this.activity = activity;
        this.playerHolder = playerHolder;
    }

    @Override
    public void onVerticalClickPause() {
        if (playerHolder != null)
            playerHolder.pausePlayer();

    }

    @Override
    public void onVerticalClickStart() {
        if (playerHolder != null)
            playerHolder.startPlayer();
    }

    @Override
    public void onVerticalClickBack() {
        activity.onBackPressedSupport();
    }
}