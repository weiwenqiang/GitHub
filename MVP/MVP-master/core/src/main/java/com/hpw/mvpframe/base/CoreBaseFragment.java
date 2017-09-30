package com.hpw.mvpframe.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hpw.mvpframe.R;
import com.hpw.mvpframe.utils.LogUtil;
import com.hpw.mvpframe.utils.StatusBarUtil;
import com.hpw.mvpframe.utils.TUtil;
import com.hpw.mvpframe.utils.TitleBuilder;
import com.hpw.mvpframe.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by hpw on 16/10/27.
 */

public abstract class CoreBaseFragment<T extends CoreBasePresenter, E extends CoreBaseModel> extends SupportFragment {
    protected String TAG;
    protected OnBackToFirstListener _mBackToFirstListener;

    public T mPresenter;
    public E mModel;
    protected Context mContext;
    protected Activity mActivity;
    Unbinder binder;

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
        if (context instanceof OnBackToFirstListener) {
            _mBackToFirstListener = (OnBackToFirstListener) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutView() != null) {
            return getLayoutView();
        } else {
            return inflater.inflate(getLayoutId(), null);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //设置状态栏透明
        setStatusBarColor();
        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TAG = getClass().getSimpleName();
        binder = ButterKnife.bind(this, view);
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        initUI(view, savedInstanceState);
        if (this instanceof CoreBaseView) mPresenter.attachVM(this, mModel);
        getBundle(getArguments());
        initData();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (binder != null) binder.unbind();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        _mBackToFirstListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.detachVM();
    }

    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        FragmentAnimator fragmentAnimator = _mActivity.getFragmentAnimator();
        fragmentAnimator.setEnter(0);
        fragmentAnimator.setExit(0);
        return fragmentAnimator;
    }

    public abstract int getLayoutId();

    public View getLayoutView() {
        return null;
    }

    /**
     * 得到Activity传进来的值
     */
    public void getBundle(Bundle bundle) {

    }

    /**
     * 初始化控件
     */
    public abstract void initUI(View view, @Nullable Bundle savedInstanceState);

    /**
     * 在监听器之前把数据准备好
     */
    public void initData() {

    }

    public void setStatusBarColor() {
        StatusBarUtil.setTranslucentForImageViewInFragment(getActivity(), null);
    }

    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
    }

    /**
     * 左侧有返回键的标题栏
     * <p>如果在此基础上还要加其他内容,比如右侧有文字按钮,可以获取该方法返回值继续设置其他内容
     *
     * @param title 标题
     */
    protected TitleBuilder initTitleBar(String title) {
        return new TitleBuilder(mActivity)
                .setTitleText(title)
                .setLeftImage(R.mipmap.ic_back)
                .setLeftOnClickListener(v -> {
                    _mActivity.onBackPressed();
                });
    }

    /**
     * 处理回退事件
     * 如果是孩子fragment需要重写onBackPressedSupport(){_mBackToFirstListener.onBackToFirstFragment();return true;}
     *
     * @return
     */
    @Override
    public boolean onBackPressedSupport() {
        if (getChildFragmentManager().getBackStackEntryCount() > 1) {
            popChild();
        } else {
            _mBackToFirstListener.onBackToFirstFragment();
            _mActivity.finish();
        }
        return true;
    }

    public interface OnBackToFirstListener {
        void onBackToFirstFragment();
    }

    public void showToast(String msg) {
        ToastUtils.showToast(mContext, msg, Toast.LENGTH_SHORT);
    }

    public void showLog(String msg) {
        LogUtil.i(TAG, msg);// TODO: 16/10/12 Log需要自己从新搞一下
    }
}
