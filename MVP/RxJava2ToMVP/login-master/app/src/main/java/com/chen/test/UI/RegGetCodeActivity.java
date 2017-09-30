package com.chen.test.UI;

import com.chen.test.Entity.BaseEntity;
import com.chen.test.Utils.MobilePhone;

import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;


import com.chen.test.R;
import com.chen.test.Utils.ClearEditText;
import com.chen.test.common.RetrofitService;
import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RegGetCodeActivity extends AppCompatActivity implements View.OnClickListener {

    TextView text;
    ClearEditText phone;
    Button getCode;

    BaseEntity entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_get_code);
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        text = (TextView) findViewById(R.id.text);
        phone = (ClearEditText) findViewById(R.id.phone);
        phone.addTextChangedListener(listener);
        getCode = (Button) findViewById(R.id.getCode);
        getCode.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        text.setTextColor(ContextCompat.getColor(RegGetCodeActivity.this, android.R.color.black));
        text.setText("请输入您的手机号");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.getCode:
                getCode();
                break;
        }
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

    /**
     * 获取验证码
     */
    private void getCode() {
        if (!MobilePhone.isMobileNO(phone.getText().toString())) {
            Animation shake = AnimationUtils.loadAnimation(RegGetCodeActivity.this, R.anim.shake);
            phone.startAnimation(shake);
            text.setText("请输入正确的手机号!");
            text.setTextColor(ContextCompat.getColor(RegGetCodeActivity.this, android.R.color.holo_red_light));
            phone.setTextColor(ContextCompat.getColor(RegGetCodeActivity.this, android.R.color.holo_red_light));
            return;
        }
        Log.e("phone", phone.getText().toString());
        RetrofitService
                .getCheckCode(phone.getText().toString())
                .subscribe(new Observer<BaseEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseEntity baseEntity) {
                        entity = baseEntity;
                    }

                    @Override
                    public void onError(Throwable e) {
                        text.setText(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Logger.e(entity.getMsg());
                        if (entity.getCode() == 0) {
                            Intent intent = new Intent(RegGetCodeActivity.this, InputCodeActivity.class);
                            intent.putExtra("input", "register");
                            intent.putExtra("phone", phone.getText().toString());
                            startActivity(intent);
                        } else {
                            Animation shake = AnimationUtils.loadAnimation(RegGetCodeActivity.this, R.anim.shake);
                            text.startAnimation(shake);
                            text.setTextColor(ContextCompat.getColor(RegGetCodeActivity.this, android.R.color.holo_red_light));
                            text.setText(entity.getMsg());
                        }
                        // Toast.makeText(RegGetCodeActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
