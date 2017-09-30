package com.chen.test.UI;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.test.Entity.BaseEntity;
import com.chen.test.R;
import com.chen.test.Utils.ClearEditText;
import com.chen.test.Utils.EditTextListener;
import com.chen.test.Utils.MyDialog;
import com.chen.test.common.RetrofitService;
import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity implements View.OnFocusChangeListener, View.OnClickListener {

    private ClearEditText user, pass;
    private Button login, register;
    private TextView forget;

    //用来判断登录按钮是否可点击
    boolean user_size = false;
    boolean pass_size = false;

    BaseEntity entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = (ClearEditText) findViewById(R.id.user);
        user.addTextChangedListener(userTextWatcher);
        user.setOnFocusChangeListener(this);

        pass = (ClearEditText) findViewById(R.id.pass);
        pass.setOnFocusChangeListener(this);
        pass.addTextChangedListener(passTextWatcher);

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(this);
        forget = (TextView) findViewById(R.id.forget);

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
                login.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.style_btn_login));
            }
        } else if (user.getText().toString().equals("")) {
            EditTextListener.showSoftInputFromWindow(MainActivity.this, user);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //登录
            case R.id.login:
                MyDialog.showProgressDialog(MainActivity.this);
                String username = user.getText().toString();
                String password = pass.getText().toString();

                RetrofitService
                        .Login(username, password)
                        .subscribe(new Observer<BaseEntity>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }

                            @Override
                            public void onNext(BaseEntity baseEntity) {
                                entity = baseEntity;
                                // text.setText(baseEntity.getMsg());
                            }

                            @Override
                            public void onError(Throwable e) {
                                // Log.e("infoo",e.getMessage());
                            }

                            @Override
                            public void onComplete() {
                                // Logger.e(baseEntity.toString());
                                Logger.e(entity.getMsg());
                                MyDialog.closeProgressDialog();
                                Toast.makeText(MainActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        });


                break;
            //注册
            case R.id.register:
                startActivity(new Intent(MainActivity.this, RegGetCodeActivity.class));
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
                        EditTextListener.noTextAndHasFocus(user, MainActivity.this, R.mipmap.ic_account_blue);
                    } else {
                        EditTextListener.hasTextAndFocus(user, MainActivity.this, R.mipmap.ic_account_blue, R.mipmap.qingchu3);
                    }

                } else {
                    user.setDrawableVisible(false);
                    EditTextListener.noFocus(user, MainActivity.this, R.mipmap.ic_account);
                }
                break;
            case R.id.pass:
                if (hasFocus) {
                    if (pass.getText().toString().equals("")) {
                        pass.setDrawableVisible(false);
                        EditTextListener.noTextAndHasFocus(pass, MainActivity.this, R.mipmap.ic_password_blue);
                    } else {
                        EditTextListener.hasTextAndFocus(pass, MainActivity.this, R.mipmap.ic_password_blue, R.mipmap.qingchu3);
                    }
                } else {
                    pass.setDrawableVisible(false);
                    EditTextListener.noFocus(pass, MainActivity.this, R.mipmap.ic_password);
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
                    EditTextListener.hasTextAndFocus(user, MainActivity.this, R.mipmap.ic_account_blue, R.mipmap.qingchu3);
                } else {
                    //没有焦点（显示灰色,不显示清除）
                    user.setDrawableVisible(false);
                    EditTextListener.noTextAndHasFocus(user, MainActivity.this, R.mipmap.ic_account);
                }
            } else {
                user_size = false;
                //没有文字,有焦点(显示蓝色,不显示清除)
                if (user.hasFocus()) {
                    user.setDrawableVisible(false);
                    EditTextListener.noTextAndHasFocus(user, MainActivity.this, R.mipmap.ic_account_blue);
                } else {
                    //没有焦点（显示灰色,不显示清除）
                    user.setDrawableVisible(false);
                    EditTextListener.noFocus(user, MainActivity.this, R.mipmap.ic_account);
                }
            }
            if (user_size && pass_size) {
                login.setEnabled(true);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    login.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.style_btn_login));
                }
            } else {
                login.setEnabled(false);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    login.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.style_btn_enable));
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
                    EditTextListener.hasTextAndFocus(pass, MainActivity.this, R.mipmap.ic_password_blue, R.mipmap.qingchu3);
                } else {
                    //没有焦点（显示灰色,不显示清除）
                    pass.setDrawableVisible(false);
                    EditTextListener.noTextAndHasFocus(pass, MainActivity.this, R.mipmap.ic_password);
                }
            } else {
                pass_size = false;
                //没有文字,有焦点(显示蓝色,不显示清除)
                if (pass.hasFocus()) {
                    pass.setDrawableVisible(false);
                    EditTextListener.noTextAndHasFocus(pass, MainActivity.this, R.mipmap.ic_password_blue);
                } else {
                    //没有焦点（显示灰色,不显示清除）
                    pass.setDrawableVisible(false);
                    EditTextListener.noFocus(pass, MainActivity.this, R.mipmap.ic_password);
                }
            }
            if (user_size && pass_size) {
                login.setEnabled(true);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    login.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.style_btn_login));
                }
            } else {
                login.setEnabled(false);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    login.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.style_btn_enable));
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };



}
