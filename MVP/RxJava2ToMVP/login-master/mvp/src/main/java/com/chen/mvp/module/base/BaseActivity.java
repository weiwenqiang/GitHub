package com.chen.mvp.module.base;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.chen.mvp.App.App;
import com.chen.mvp.R;
import com.chen.mvp.injector.components.AppComponent;
import com.chen.mvp.utils.MyDialog;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import butterknife.ButterKnife;

public abstract class BaseActivity<T extends IBasePresenter> extends AppCompatActivity implements IBaseView {

    @Inject
    protected T mPresenter;

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @LayoutRes
    protected abstract int attachLayoutRes();

    /**
     * Dagger 注入
     */
    protected abstract void initInjector();

    /**
     * 初始化视图控件
     */
    protected abstract void initViews();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(attachLayoutRes());
        ButterKnife.bind(this);
        Logger.e(getClass().getSimpleName());
        initInjector();
        initViews();
    }

    @Override
    public void showLoading() {
        MyDialog.showProgressDialog(this,"正在加载...");
    }

    @Override
    public void hideLoading() {
        MyDialog.closeProgressDialog();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取 AppComponent
     *
     * @return AppComponent
     */
    protected AppComponent getAppComponent() {
        return App.getAppComponent();
    }

    /**
     * 初始化 Toolbar
     *
     * @param toolbar
     * @param homeAsUpEnabled
     * @param title
     */
    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
