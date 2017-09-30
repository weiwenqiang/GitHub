package com.hpw.myapp.ui.tv.activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hpw.mvpframe.utils.LogUtil;
import com.hpw.myapp.R;
import com.hpw.myapp.ui.tv.model.FirstBean;
import com.hpw.myapp.ui.tv.model.OtherBean;
import com.hpw.myapp.ui.tv.model.TvShowBean;
import com.hpw.myapp.ui.tv.model.TvShowModel;
import com.hpw.myapp.ui.tv.presenter.TvShowPresenter;
import com.hpw.myapp.widget.media.HorMediaControllView;
import com.hpw.myapp.widget.media.LivePlayerHolder;
import com.hpw.myapp.widget.media.OnHorControllListener;
import com.hpw.myapp.widget.media.OnVerticalControllListener;
import com.hpw.myapp.widget.media.VerticalMediaControllView;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by hpw on 16/12/3.
 */

public class TvShowActivity extends BaseTvShowActivity<TvShowPresenter, TvShowModel> implements VerticalMediaControllView.OnFullScreenListener, View.OnTouchListener {
    private SurfaceView mSurfaceView;
    private VerticalMediaControllView verticalControll;
    private View mLoadingView;

    private int mPortWidth;
    private int mPortHeight;
    private boolean isLandscape = false;
    private LivePlayerHolder playerHolder;
    private HorMediaControllView horizontalControll;
    private FirstBean.RoomBean.ListBean mPlayBean;
    private OtherBean.DataBean mPlayBean1;
    private boolean isVertical = true;

    private int mCodec;
    private String mPlayerPath;
    private ImageView bgImage;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tv_show;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        initPlayer();
        initVerControll();
        initHorContrll();
        initData();
    }

    private void initPlayer() {
        mLoadingView = findViewById(R.id.LoadingView);
        bgImage = (ImageView) findViewById(R.id.bgImg);
        mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        mSurfaceView.setOnTouchListener(this);
        try {
            mPlayBean = (FirstBean.RoomBean.ListBean) getIntent().getSerializableExtra("playBean");
        } catch (ClassCastException e) {
            mPlayBean1 = (OtherBean.DataBean) getIntent().getSerializableExtra("playBean");
        }

        mCodec = getIntent().getIntExtra("mediaCodec", 0);
    }

    private void initVerControll() {
        verticalControll = (VerticalMediaControllView) findViewById(R.id.verticalControll);
        verticalControll.setOnVerticalControllListener(new OnVerticalControllListener(this, playerHolder));
        verticalControll.setOnFullScreenListener(this);
    }

    private void initHorContrll() {
        horizontalControll = (HorMediaControllView) findViewById(R.id.horizontalControll);
        horizontalControll.setOnHorControllListener(new OnHorControllListener(this, playerHolder));
    }

    private void initData() {
        if (mPlayBean != null) {
            mPresenter.onTvShow(mPlayBean.getUid());
            Glide.with(this).load(mPlayBean.getThumb()).fitCenter().into(bgImage);
            verticalControll.setData(mPlayBean.getView(), mPlayBean.getAvatar(), mPlayBean.getNick(), mPlayBean.getTitle(), mPlayBean.getFollow());
        } else if (mPlayBean1 != null) {
            mPresenter.onTvShow(mPlayBean1.getUid());
            Glide.with(this).load(mPlayBean1.getThumb()).fitCenter().into(bgImage);
            verticalControll.setData(mPlayBean1.getView(), mPlayBean1.getAvatar(), mPlayBean1.getNick(), mPlayBean1.getTitle(), mPlayBean1.getFollow());
        }
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
    protected void onDestroy() {
        if (playerHolder != null) {
            playerHolder.release();
            playerHolder = null;
        }
        verticalControll.onDestroy();
        horizontalControll.onDestroy();
        super.onDestroy();
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
        mLoadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onReConnecting() {
        showToastTips("正在重连...");
    }

    @Override
    public void onConnectSucces() {
        mLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void onConnectFailed() {
        showToastTips("连接失败");
    }

    @Override
    public void onPlayComleted() {
        showToastTips("主播离开了");
    }

    @Override
    public void onPlayerStart() {
        bgImage.animate().alpha(0).setDuration(1000).start();
    }

    @Override
    public void onPlayePause() {

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            //portrait
        } else {
            isLandscape = true;
            //landscape
        }
    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        super.setRequestedOrientation(requestedOrientation);
        if (requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            if (verticalControll != null) {
                isVertical = true;
                verticalControll.onCreate();
                horizontalControll.onDestroy();
            }

        } else {
            if (horizontalControll != null) {
                isVertical = false;
                horizontalControll.onCreate();
                verticalControll.onDestroy();
            }
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        LogUtil.i("TOUCH  " + isVertical);
        verticalControll.onTouchEvent(isVertical, event);
        horizontalControll.onTouchEvent(isVertical, event);
        return false;
    }

    @Override
    public void onVerticalClickFullScreen() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Display display = getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int heightPixels = metrics.heightPixels;
        int widthPixels = metrics.widthPixels;
        ViewGroup.LayoutParams params = mSurfaceView.getLayoutParams();
        int height = params.height;
        int width = params.width;
        getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);
        mPortWidth = width;
        mPortHeight = height;
        params.width = widthPixels;
        params.height = heightPixels;
        mSurfaceView.setLayoutParams(params);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onBackPressedSupport() {
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            ViewGroup.LayoutParams params = mSurfaceView.getLayoutParams();
            params.width = mPortWidth;
            params.height = mPortHeight;
            mSurfaceView.setLayoutParams(params);
            getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);
            return;
        }
        if (playerHolder != null)
            playerHolder.release();
        if (isLandscape == true)
            if (mPlayBean != null) {
                TvMainActivity.startActivity(mContext, mPlayBean.getCategory_slug());
            } else if (mPlayBean1 != null) {
                TvMainActivity.startActivity(mContext, mPlayBean1.getCategory_slug());
            }
        super.onBackPressedSupport();
    }
}
