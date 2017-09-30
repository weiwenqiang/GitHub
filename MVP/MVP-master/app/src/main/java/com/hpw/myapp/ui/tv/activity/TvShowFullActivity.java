package com.hpw.myapp.ui.tv.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.hpw.myapp.R;
import com.hpw.myapp.ui.tv.model.FirstBean;
import com.hpw.myapp.ui.tv.model.OtherBean;
import com.hpw.myapp.ui.tv.model.TvShowBean;
import com.hpw.myapp.ui.tv.model.TvShowModel;
import com.hpw.myapp.ui.tv.presenter.TvShowPresenter;
import com.hpw.myapp.widget.media.Blurry;
import com.hpw.myapp.widget.media.LivePlayerHolder;
import com.hpw.myapp.widget.media.LoadingImageView;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by hpw on 16/12/4.
 */

public class TvShowFullActivity extends BaseTvShowActivity<TvShowPresenter, TvShowModel> {
    private SurfaceView mSurfaceView;
    private LivePlayerHolder playerHolder;
    private FirstBean.RoomBean.ListBean mPlayBean;
    private OtherBean.DataBean mPlayBean1;

    private int mCodec;
    private String mPlayerPath;
    private ImageView bgImg;
    private ImageView imgBack;
    private LoadingImageView loadingView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tv_show_full;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        initPlayer();
        initData();
    }

    private void initPlayer() {
        try {
            mPlayBean = (FirstBean.RoomBean.ListBean) getIntent().getSerializableExtra("playBean");
        } catch (ClassCastException e) {
            mPlayBean1 = (OtherBean.DataBean) getIntent().getSerializableExtra("playBean");
        }
        mSurfaceView = (SurfaceView) findViewById(R.id.mSurfaceView);
        bgImg = (ImageView) findViewById(R.id.bgImg);
        loadingView = (LoadingImageView) findViewById(R.id.loadingView);
        imgBack = (ImageView) findViewById(R.id.imgBack);
        imgBack.setOnClickListener(v -> onBackPressed());
        mCodec = getIntent().getIntExtra("mediaCodec", 0);
    }

    private void initData() {
        if (mPlayBean != null) {
            mPresenter.onTvShow(mPlayBean.getUid());
            Glide.with(this).load(mPlayBean.getLove_cover()).into(new ImageViewTarget<GlideDrawable>(bgImg) {
                @Override
                protected void setResource(GlideDrawable resource) {
                    bgImg.setImageDrawable(resource);
                    Blurry.with(TvShowFullActivity.this).animate().radius(10).sampling(8).capture(bgImg).into(bgImg);
                }
            });
        } else if (mPlayBean1 != null) {
            mPresenter.onTvShow(mPlayBean1.getUid());
            Glide.with(this).load(mPlayBean1.getLove_cover()).into(new ImageViewTarget<GlideDrawable>(bgImg) {
                @Override
                protected void setResource(GlideDrawable resource) {
                    bgImg.setImageDrawable(resource);
                    Blurry.with(TvShowFullActivity.this).animate().radius(10).sampling(8).capture(bgImg).into(bgImg);
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        if (playerHolder != null) {
            playerHolder.release();
            playerHolder = null;
        }
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (playerHolder != null)
            playerHolder.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (playerHolder != null)
            playerHolder.onPause();
    }


    @Override
    protected void toPrepare() {
        if (playerHolder != null)
            playerHolder.prepare();
    }

    @Override
    public void showContent(TvShowBean info) {
        JSONObject jsonObject = new JSONObject((Map) info.getLive().getWs().getHls());
        JSONObject object = jsonObject.optJSONObject("0");
        if (object != null) {
            mPlayerPath = object.optString("src");
        } else {
            mPlayerPath = jsonObject.optJSONObject("4").optString("src");
        }
        playerHolder = new LivePlayerHolder(this, mSurfaceView, mCodec, mPlayerPath);
        playerHolder.startPlayer();
    }

    @Override
    public void onConnecting() {
        bgImg.setAlpha(1);
        bgImg.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onReConnecting() {
        bgImg.setAlpha(1);
        bgImg.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onConnectSucces() {
        loadingView.setVisibility(View.GONE);
    }

    @Override
    public void onConnectFailed() {

    }

    @Override
    public void onPlayComleted() {

    }

    @Override
    public void onPlayerStart() {
        loadingView.setVisibility(View.GONE);
        bgImg.animate().alpha(0).setDuration(1000).start();
    }

    @Override
    public void onPlayePause() {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showError(String msg) {

    }
}
