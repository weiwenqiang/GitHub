package com.chen.mvp.module.login;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.chen.mvp.R;
import com.chen.mvp.bean.BaseInfo;
import com.chen.mvp.injector.components.DaggerLoginComponent;
import com.chen.mvp.injector.modules.LoginModule;
import com.chen.mvp.module.base.BaseActivity;
import com.chen.mvp.module.register.getCode.RegGetCodeActivity;
import com.chen.mvp.utils.ClearEditText;
import com.chen.mvp.utils.EditTextListener;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenterImpl> implements ILoginView, View.OnFocusChangeListener {

    @BindView(R.id.user)
    ClearEditText user;
    @BindView(R.id.pass)
    ClearEditText pass;
    @BindView(R.id.register)
    Button register;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.forget)
    TextView forget;

    //用来判断登录按钮是否可点击
    boolean user_size = false;
    boolean pass_size = false;

    public static void launch(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
        ((Activity)context).overridePendingTransition(R.anim.slide_right_entry, R.anim.hold);
        ((Activity) context).finish();
    }


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void initInjector() {
        DaggerLoginComponent
                .builder()
                .appComponent(getAppComponent())
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        user.setOnFocusChangeListener(this);
        user.addTextChangedListener(userTextWatcher);
        pass.setOnFocusChangeListener(this);
        pass.addTextChangedListener(passTextWatcher);

        //动画
        ObjectAnimator animator = ObjectAnimator.ofFloat(forget, "rotation", 0, 360f);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(forget, "alpha", 1f, 0f, 1f);
        AnimatorSet set = new AnimatorSet();
        set.play(animator).with(animator1);
        set.setDuration(5000);
        set.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //进入到这个页面,如果账号输入框为空,则账号输入框自动获得焦点,并弹出键盘
        //如果两个输入框都不为空,则登录按钮可点击
        if (!user.getText().toString().equals("") && !pass.getText().toString().equals("")) {
            user_size = true;
            pass_size = true;
            login.setEnabled(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                login.setBackground(ContextCompat.getDrawable(LoginActivity.this, R.drawable.style_btn_login));
            }
        } else if (user.getText().toString().equals("")) {
            EditTextListener.showSoftInputFromWindow(LoginActivity.this, user);
        }
    }


    @Override
    public void loadInfo(BaseInfo info) {
        Logger.e(info.getMsg());
        showToast(info.getMsg());
        // Toast.makeText(this, info.getMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAnimation(String msg) {
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        user.startAnimation(shake);
    }


    @OnClick({R.id.register, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register:
                RegGetCodeActivity.launch(LoginActivity.this);
                break;
            case R.id.login:
                mPresenter.getData(user.getText().toString(), pass.getText().toString());
                break;
        }
    }

    /**
     * 焦点监听
     *
     * @param v
     * @param hasFocus
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.user:
                if (hasFocus) {
                    if (user.getText().toString().equals("")) {
                        user.setDrawableVisible(false);
                        EditTextListener.noTextAndHasFocus(user, LoginActivity.this, R.mipmap.ic_account_blue);
                    } else {
                        EditTextListener.hasTextAndFocus(user, LoginActivity.this, R.mipmap.ic_account_blue, R.mipmap.qingchu3);
                    }

                } else {
                    user.setDrawableVisible(false);
                    EditTextListener.noFocus(user, LoginActivity.this, R.mipmap.ic_account);
                }
                break;
            case R.id.pass:
                if (hasFocus) {
                    if (pass.getText().toString().equals("")) {
                        pass.setDrawableVisible(false);
                        EditTextListener.noTextAndHasFocus(pass, LoginActivity.this, R.mipmap.ic_password_blue);
                    } else {
                        EditTextListener.hasTextAndFocus(pass, LoginActivity.this, R.mipmap.ic_password_blue, R.mipmap.qingchu3);
                    }
                } else {
                    pass.setDrawableVisible(false);
                    EditTextListener.noFocus(pass, LoginActivity.this, R.mipmap.ic_password);
                }
                break;
        }
    }


    /**
     * 账号输入框的文字改变监听
     */
    TextWatcher userTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() > 0) {
                user_size = true;
                //有文字，有焦点(显示蓝色,和清除)
                if (user.hasFocus()) {
                    EditTextListener.hasTextAndFocus(user, LoginActivity.this, R.mipmap.ic_account_blue, R.mipmap.qingchu3);
                } else {
                    //没有焦点（显示灰色,不显示清除）
                    user.setDrawableVisible(false);
                    EditTextListener.noTextAndHasFocus(user, LoginActivity.this, R.mipmap.ic_account);
                }
            } else {
                user_size = false;
                //没有文字,有焦点(显示蓝色,不显示清除)
                if (user.hasFocus()) {
                    user.setDrawableVisible(false);
                    EditTextListener.noTextAndHasFocus(user, LoginActivity.this, R.mipmap.ic_account_blue);
                } else {
                    //没有焦点（显示灰色,不显示清除）
                    user.setDrawableVisible(false);
                    EditTextListener.noFocus(user, LoginActivity.this, R.mipmap.ic_account);
                }
            }
            if (user_size && pass_size) {
                login.setEnabled(true);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    login.setBackground(ContextCompat.getDrawable(LoginActivity.this, R.drawable.style_btn_login));
                }
            } else {
                login.setEnabled(false);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    login.setBackground(ContextCompat.getDrawable(LoginActivity.this, R.drawable.style_btn_enable));
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    /**
     * 密码输入框的文字改变监听
     */
    private TextWatcher passTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() > 0) {
                pass_size = true;
                //有文字，有焦点(显示蓝色,和清除)
                if (pass.hasFocus()) {
                    EditTextListener.hasTextAndFocus(pass, LoginActivity.this, R.mipmap.ic_password_blue, R.mipmap.qingchu3);
                } else {
                    //没有焦点（显示灰色,不显示清除）
                    pass.setDrawableVisible(false);
                    EditTextListener.noTextAndHasFocus(pass, LoginActivity.this, R.mipmap.ic_password);
                }
            } else {
                pass_size = false;
                //没有文字,有焦点(显示蓝色,不显示清除)
                if (pass.hasFocus()) {
                    pass.setDrawableVisible(false);
                    EditTextListener.noTextAndHasFocus(pass, LoginActivity.this, R.mipmap.ic_password_blue);
                } else {
                    //没有焦点（显示灰色,不显示清除）
                    pass.setDrawableVisible(false);
                    EditTextListener.noFocus(pass, LoginActivity.this, R.mipmap.ic_password);
                }
            }
            if (user_size && pass_size) {
                login.setEnabled(true);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    login.setBackground(ContextCompat.getDrawable(LoginActivity.this, R.drawable.style_btn_login));
                }
            } else {
                login.setEnabled(false);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    login.setBackground(ContextCompat.getDrawable(LoginActivity.this, R.drawable.style_btn_enable));
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


}
