package com.chen.mvp.module.base;

import android.view.animation.Animation;

/**
 * Created by long on 17-4-5.
 */

public interface IBaseView {

    /**
     * 显示加载动画
     */
    void showLoading();

    /**
     * 隐藏加载动画
     */
    void hideLoading();

    /**
     * 显示toast
     * @param msg
     */
    void showToast(String msg);

    /**
     * 显示动画
     */
    void showAnimation(String msg);
}
