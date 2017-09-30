package com.chen.mvp.module.register.inputCode;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chen.mvp.R;
import com.chen.mvp.bean.BaseInfo;
import com.chen.mvp.injector.components.DaggerInputCodeComponent;
import com.chen.mvp.injector.modules.InputCodeModule;
import com.chen.mvp.module.base.BaseActivity;
import com.chen.mvp.module.register.setPass.SetPassActivity;
import com.chen.mvp.utils.TimeUtils;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

public class InputCodeActivity extends BaseActivity<InputCodePresenterImpl> implements IInputCodeView {

    private static final String TAG = "InputCodeActivity";
    private static final String INPUT = "input";
    private static final String PHONE = "phone";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.phoneNumb)
    TextView phoneNumb;
    @BindView(R.id.code)
    EditText code;
    @BindView(R.id.send)
    TextView send;
    @BindView(R.id.next)
    Button next;

    String input;
    String phone;

    TimeUtils time;

    public static void launch(Context context, String input, String phone) {
        Intent intent = new Intent(context, InputCodeActivity.class);
        intent.putExtra(INPUT, input);
        intent.putExtra(PHONE, phone);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.fade_entry, R.anim.hold);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_input_code;
    }

    @Override
    protected void initInjector() {
        Intent intent = getIntent();
        input = intent.getStringExtra("input");
        phone = intent.getStringExtra("phone");
        DaggerInputCodeComponent
                .builder()
                .appComponent(getAppComponent())
                .inputCodeModule(new InputCodeModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        initToolBar(toolbar, true, "注册账号");
        code.addTextChangedListener(listener);
        time = new TimeUtils(60000, 1000, send, InputCodeActivity.this);
        if (!input.equals("modify")) {
            time.start();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
        //判断是注册还是忘记密码还是修改密码,还是修改手机号
        if (input.equals("register")) {
            text.setText("验证码已发送到以下手机,请在5分钟内完成注册");
            text.setTextColor(ContextCompat.getColor(InputCodeActivity.this, android.R.color.black));
        } else if (input.equals("forget")) {
            text.setText("验证码已发送到以下手机,请在5分钟内完成重置");
            text.setTextColor(ContextCompat.getColor(InputCodeActivity.this, android.R.color.black));
        } else if (input.equals("newPhone")) {
            text.setText("验证码已发送到以下手机,请在5分钟内完成更换");
            text.setTextColor(ContextCompat.getColor(InputCodeActivity.this, android.R.color.black));
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
    public void showAnimation(String msg) {

    }

    @OnClick({R.id.send, R.id.next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.send:
                time.start();
                mPresenter.getData(phone);
                break;
            case R.id.next:
                mPresenter.checkCode(code.getText().toString());
                break;
        }
    }

    @Override
    public void loadInfo(BaseInfo info) {
        Logger.e(info.getMsg());
        if (info.getMsg().equals("验证码正确")) {
            SetPassActivity.launch(InputCodeActivity.this, "register",phone);
        } else {
            Animation shake = AnimationUtils.loadAnimation(InputCodeActivity.this, R.anim.shake);
            code.startAnimation(shake);
            text.setTextColor(ContextCompat.getColor(InputCodeActivity.this, android.R.color.holo_red_light));
            text.setText(info.getMsg());
        }
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
        if (time != null) {
            time = null;
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.hold, R.anim.fade_exit);
    }
}
