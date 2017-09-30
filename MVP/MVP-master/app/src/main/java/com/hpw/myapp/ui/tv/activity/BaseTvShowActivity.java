package com.hpw.myapp.ui.tv.activity;

import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.hpw.mvpframe.base.CoreBaseActivity;
import com.hpw.mvpframe.base.CoreBaseModel;
import com.hpw.mvpframe.base.CoreBasePresenter;
import com.hpw.mvpframe.utils.NetUtils;
import com.hpw.myapp.ui.tv.contract.TvContract;

/**
 * Created by hpw on 16/12/3.
 */

public abstract class BaseTvShowActivity<T extends CoreBasePresenter, E extends CoreBaseModel> extends CoreBaseActivity<T, E> implements TvContract.TvShowView {
    protected boolean mIsActivityPaused = true;

    @Override
    protected void onResume() {
        super.onResume();
        mIsActivityPaused = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mIsActivityPaused = true;
    }

    @Override
    public void onBackPressedSupport() {
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            return;
        }
        super.onBackPressedSupport();
    }

    protected void showToastTips(final String tips) {
        if (mIsActivityPaused) {
            return;
        }
        showToast(tips);
    }

    private static final int MESSAGE_ID_RECONNECTING = 0x01;

    private void sendReconnectMessage() {
        showToastTips("正在重连...");
        onReConnecting();
        mHandler.removeCallbacksAndMessages(null);
        mHandler.sendMessageDelayed(mHandler.obtainMessage(MESSAGE_ID_RECONNECTING), 500);
    }

    protected Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what != MESSAGE_ID_RECONNECTING) {
                return;
            }
            if (mIsActivityPaused) {
                finish();
                return;
            }
            if (!NetUtils.isConnected(mContext)) {
                sendReconnectMessage();
                return;
            }
            toPrepare();
        }
    };

    protected abstract void toPrepare();
}
