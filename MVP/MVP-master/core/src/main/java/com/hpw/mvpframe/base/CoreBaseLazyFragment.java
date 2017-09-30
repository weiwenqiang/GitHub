package com.hpw.mvpframe.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by hpw on 16/10/31.
 */

public abstract class CoreBaseLazyFragment<T extends CoreBasePresenter, E extends CoreBaseModel> extends CoreBaseFragment<T, E> {
    private boolean mInited = false;
    private Bundle mSavedInstanceState;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSavedInstanceState = savedInstanceState;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState == null) {
            if (!isHidden()) {
                mInited = true;
                initLazyView(null);
            }
        } else {
            // isSupportHidden()仅在saveIns tanceState!=null时有意义,是库帮助记录Fragment状态的方法
            if (!isSupportHidden()) {
                mInited = true;
                initLazyView(savedInstanceState);
            }
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!mInited && !hidden) {
            mInited = true;
            initLazyView(mSavedInstanceState);
        }
    }

    /**
     * 懒加载
     */
    protected abstract void initLazyView(@Nullable Bundle savedInstanceState);
}
