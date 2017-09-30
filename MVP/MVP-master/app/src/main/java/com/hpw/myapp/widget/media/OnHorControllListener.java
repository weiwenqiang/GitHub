package com.hpw.myapp.widget.media;

import com.hpw.myapp.ui.tv.activity.TvShowActivity;

/**
 * Created by hpw on 16/12/3.
 */

public class OnHorControllListener implements HorMediaControllView.OnHorControllListener {

    private TvShowActivity activity;
    private LivePlayerHolder playerHolder;

    public OnHorControllListener(TvShowActivity activity, LivePlayerHolder playerHolder) {
        this.activity = activity;
        this.playerHolder = playerHolder;

    }

    @Override
    public void onHorClickPause() {
        activity.onPlayePause();
    }

    @Override
    public void onHorClickStart() {
        activity.onPlayerStart();
    }

    @Override
    public void onHorClickBack() {
        activity.onBackPressedSupport();
    }
}