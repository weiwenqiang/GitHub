package com.chen.test.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.test.Entity.BaseEntity;
import com.chen.test.R;
import com.chen.test.common.RetrofitService;
import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SetPassActivity extends AppCompatActivity {

    TextView tv;
    EditText pass;
    EditText pass2;
    Button finish;

    String input;
    String phone;
    String code;

    String registerText = "设置密码后,你就可以使用手机号来登录了";

    String forgetText = "重置密码后,你就可以使用新密码来登录了";

    String modifyText = "修改密码后,你就可以使用新密码来登录了";

    String modifyPhone = "输入密码后,你就可以使用新手机号来登录了";
    private BaseEntity entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_set_pass);
        initData();
        initView();
    }

    /**
     * 初始化数据（获取上个页面传过来的数据）
     */
    private void initData() {
        Intent intent = getIntent();
        input = intent.getStringExtra("input");
        phone = intent.getStringExtra("phone");
        code = intent.getStringExtra("code");
        Log.e("input", input);
    }

    /**
     * 初始化视图
     */
    private void initView() {
        tv = (TextView) findViewById(R.id.text);
        pass = (EditText) findViewById(R.id.pass);
        pass2 = (EditText) findViewById(R.id.pass2);
        finish = (Button) findViewById(R.id.finish);
        finish.setOnClickListener(listener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //根据input判断对应的操作,并显示相应的文字
        if (input.equals("register")) {
            tv.setText(registerText);
        } else if (input.equals("forget")) {
            tv.setText(forgetText);
        } else if (input.equals("modify")) {
            tv.setText(modifyText);
        } else if (input.equals("newPhone")) {
            tv.setText(modifyPhone);
        }
    }

    /**
     * 完成按钮的点击事件监听
     */
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            complete();
        }
    };

    /**
     * 点击完成按钮调用该方法
     */
    private void complete() {
        String pass = this.pass.getText().toString();
        String pass2 = this.pass2.getText().toString();
        if (input.equals("register")) {
            verify(pass, pass2, registerText);
        } else if (input.equals("forget")) {
            verify(pass, pass2, forgetText);
        } else if (input.equals("modify")) {
            verify(pass, pass2, modifyText);
        } else if (input.equals("newPhone")) {
            verify(pass, pass2, modifyPhone);
        }
    }

    /**
     * 对密码进行认证
     *
     * @param pass
     * @param pass2
     */
    public void verify(String pass, String pass2, String text) {
        //为空就抖动
        if (pass.equals("")) {
            tv.setText(text);
            tv.setTextColor(ContextCompat.getColor(SetPassActivity.this, android.R.color.black));
            Animation shake = AnimationUtils.loadAnimation(SetPassActivity.this, R.anim.shake);
            this.pass.startAnimation(shake);
        } else if (pass2.equals("")) {
            tv.setText(text);
            tv.setTextColor(ContextCompat.getColor(SetPassActivity.this, android.R.color.black));
            Animation shake = AnimationUtils.loadAnimation(SetPassActivity.this, R.anim.shake);
            this.pass2.startAnimation(shake);
        } else if (pass.length() > 0 && pass.length() < 6) {
            tv.setText("密码不能小于6个字符");
            tv.setTextColor(ContextCompat.getColor(SetPassActivity.this, android.R.color.holo_red_light));
            Animation shake = AnimationUtils.loadAnimation(SetPassActivity.this, R.anim.shake);
            this.pass.startAnimation(shake);
        } else if (pass.length() >= 6) {
            if (!pass.equals(pass2)) {
                tv.setText("两次密码不一致");
                tv.setTextColor(ContextCompat.getColor(SetPassActivity.this, android.R.color.holo_red_light));
                Animation shake = AnimationUtils.loadAnimation(SetPassActivity.this, R.anim.shake);
                this.pass2.startAnimation(shake);
            } else {
                if (input.equals("register")) {
                    register(pass);
                } else if (input.equals("forget")) {

                } else if (input.equals("modify")) {

                } else if (input.equals("newPhone")) {

                }
            }
        }
    }

    /**
     * 注册
     *
     * @param pass
     */
    private void register(String pass) {
        RetrofitService
                .Register(phone, pass)
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
                        Toast.makeText(SetPassActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(SetPassActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        Logger.e(entity.getMsg());
                        startActivity(new Intent(SetPassActivity.this, MainActivity.class));
                        finish();
                    }
                });
    }
}
