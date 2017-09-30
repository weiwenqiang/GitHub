package com.chen.mvp.module.register.getCode;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.chen.mvp.R;
import com.chen.mvp.bean.BaseInfo;
import com.chen.mvp.injector.components.DaggerRegGetCodeComponent;
import com.chen.mvp.injector.modules.RegGetCodeModule;
import com.chen.mvp.module.base.BaseActivity;
import com.chen.mvp.module.register.inputCode.InputCodeActivity;
import com.chen.mvp.utils.ClearEditText;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

public class RegGetCodeActivity extends BaseActivity<RegGetCodePresenterImpl> implements IRegGetCodeView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.phone)
    ClearEditText phone;
    @BindView(R.id.getCode)
    Button getCode;

    /**
     * 跳转activity
     *
     * @param context 上个页面的context
     */
    public static void launch(Context context) {
        Intent intent = new Intent(context, RegGetCodeActivity.class);
        context.startActivity(intent);
        ((Activity)context).overridePendingTransition(R.anim.slide_right_entry, R.anim.hold);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_reg_get_code;
    }

    @Override
    protected void initInjector() {
        DaggerRegGetCodeComponent
                .builder()
                .appComponent(getAppComponent())
                .regGetCodeModule(new RegGetCodeModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        initToolBar(toolbar, true, "注册账号");
        phone.addTextChangedListener(listener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        text.setTextColor(ContextCompat.getColor(RegGetCodeActivity.this, android.R.color.black));
        text.setText("请输入您的手机号");
    }

    @Override
    public void loadInfo(BaseInfo info) {
        Logger.e(info.getMsg());
        if (info.getCode() == 0) {
            InputCodeActivity.launch(RegGetCodeActivity.this, "register", phone.getText().toString());
        } else {
            Animation shake = AnimationUtils.loadAnimation(RegGetCodeActivity.this, R.anim.shake);
            text.startAnimation(shake);
            text.setTextColor(ContextCompat.getColor(RegGetCodeActivity.this, android.R.color.holo_red_light));
            text.setText(info.getMsg());
        }
    }

    @Override
    public void showAnimation(String msg) {
        Animation shake = AnimationUtils.loadAnimation(RegGetCodeActivity.this, R.anim.shake);
        phone.startAnimation(shake);
        text.setText("请输入正确的手机号!");
        text.setTextColor(ContextCompat.getColor(RegGetCodeActivity.this, android.R.color.holo_red_light));
        phone.setTextColor(ContextCompat.getColor(RegGetCodeActivity.this, android.R.color.holo_red_light));
    }

    @OnClick(R.id.getCode)
    public void onViewClicked() {
        mPresenter.getData(phone.getText().toString());
    }

    /**
     * 输入框的文字改变监听
     */
    private TextWatcher listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            phone.setTextColor(ContextCompat.getColor(RegGetCodeActivity.this, android.R.color.black));
            if (s.length() > 0) {
                getCode.setEnabled(true);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    getCode.setBackground(ContextCompat.getDrawable(RegGetCodeActivity.this, R.drawable.style_btn_login));
                }
            } else {
                getCode.setEnabled(false);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    getCode.setBackground(ContextCompat.getDrawable(RegGetCodeActivity.this, R.drawable.style_btn_enable));
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.hold, R.anim.slide_right_exit);
    }
}
