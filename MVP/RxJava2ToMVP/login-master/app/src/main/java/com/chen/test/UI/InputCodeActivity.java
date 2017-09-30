package com.chen.test.UI;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chen.test.Entity.BaseEntity;
import com.chen.test.R;
import com.chen.test.Utils.TimeUtils;
import com.chen.test.common.RetrofitService;
import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * 本页面供4个页面使用  分别有注册,忘记密码,修改密码.最后还有更换手机号都可以使用
 * 只需要根据相应的页面显示相应的文字就可以了
 */
public class InputCodeActivity extends AppCompatActivity implements View.OnClickListener {

    TextView text;
    TextView phoneNumb;
    EditText code;
    TextView send;
    Button next;

    String input;
    String phone;

    TimeUtils time;
    private BaseEntity entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_input_code);
        initData();
        initView();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        Intent intent = getIntent();
        input = intent.getStringExtra("input");
        phone = intent.getStringExtra("phone");
        Log.e("input", input);
    }

    /**
     * 初始化视图
     */
    private void initView() {
        text = (TextView) findViewById(R.id.text);
        phoneNumb = (TextView) findViewById(R.id.phoneNumb);
        code = (EditText) findViewById(R.id.code);
        code.addTextChangedListener(listener);
        send = (TextView) findViewById(R.id.send);
        send.setOnClickListener(this);
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(this);
        time = new TimeUtils(60000, 1000, send, InputCodeActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //判断是注册还是忘记密码还是修改密码,还是修改手机号
        if (input.equals("register")) {
            time.start();
            text.setText("验证码已发送到以下手机,请在5分钟内完成注册");
        } else if (input.equals("forget")) {
            time.start();
            text.setText("验证码已发送到以下手机,请在5分钟内完成重置");
        } else if (input.equals("newPhone")) {
            time.start();
            text.setText("验证码已发送到以下手机,请在5分钟内完成更换");
        } else if (input.equals("modify")) {
            send.setEnabled(true);
            text.setText("点击发送验证码按钮发送验证码,请在5分钟内完成修改");
            send.setTextColor(ContextCompat.getColor(InputCodeActivity.this, R.color.pressed));
        }
        try {
            phoneNumb.setText(phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send:
                send();
                break;
            case R.id.next:
                next();
                break;
        }
    }

    /**
     * 计时完成后,点击发送验证码
     */
    private void send() {
        time.start();
        getCode();
    }

    /**
     * 下一步按钮点击调用
     */
    private void next() {
        String code = this.code.getText().toString();
        Intent intent = new Intent(InputCodeActivity.this, SetPassActivity.class);
        intent.putExtra("input", input);
        intent.putExtra("phone", phone);
        intent.putExtra("code", code);
        startActivity(intent);
    }

    /**
     * 输入框文字改变监听
     */
    TextWatcher listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() > 0) {
                next.setEnabled(true);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    next.setBackground(ContextCompat.getDrawable(InputCodeActivity.this, R.drawable.style_btn_login));
                }
            } else {
                next.setEnabled(false);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    next.setBackground(ContextCompat.getDrawable(InputCodeActivity.this, R.drawable.style_btn_enable));
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
        RetrofitService
                .getCheckCode(phone)
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

                    }

                    @Override
                    public void onComplete() {
                        Logger.e(entity.getMsg());
                    }
                });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (time != null) {
            time = null;
        }
    }
}
