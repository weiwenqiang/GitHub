package com.chen.mvp.module.register.setPass;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.chen.mvp.R;
import com.chen.mvp.bean.BaseInfo;
import com.chen.mvp.injector.components.DaggerSetPassComponent;
import com.chen.mvp.injector.modules.SetPassModule;
import com.chen.mvp.module.base.BaseActivity;
import com.chen.mvp.module.base.IBasePresenter;
import com.chen.mvp.module.login.LoginActivity;
import com.chen.mvp.module.register.inputCode.InputCodeActivity;
import com.chen.mvp.utils.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetPassActivity extends BaseActivity<SetPassPresenterImpl> implements ISetPassView {

    private static final String INPUT = "input";
    private static final String PHONE = "phone";
    private static final String REGISTER_TEXT = "设置密码后,你就可以使用手机号来登录了";
    private static final String FORGET_TEXT = "重置密码后,你就可以使用新密码来登录了";
    private static final String MODIFY_TEXT = "修改密码后,你就可以使用新密码来登录了";
    private static final String MODIFY_PHONE_TEXT = "输入密码后,你就可以使用新手机号来登录了";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.pass)
    ClearEditText pass;
    @BindView(R.id.pass2)
    ClearEditText pass2;
    @BindView(R.id.finish)
    Button finish;

    String input;
    String phone;

    public static void launch(Context context, String input, String phone) {
        Intent intent = new Intent(context, SetPassActivity.class);
        intent.putExtra(INPUT, input);
        intent.putExtra(PHONE, phone);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.fade_entry, R.anim.hold);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_set_pass;
    }

    @Override
    protected void initInjector() {
        Intent intent = getIntent();
        input = intent.getStringExtra("input");
        phone = intent.getStringExtra("phone");
        DaggerSetPassComponent
                .builder()
                .appComponent(getAppComponent())
                .setPassModule(new SetPassModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        initToolBar(toolbar, true, "注册账号");

    }

    @Override
    protected void onResume() {
        super.onResume();
        //根据input判断对应的操作,并显示相应的文字
        if (input.equals("register")) {
            text.setText(REGISTER_TEXT);
        } else if (input.equals("forget")) {
            text.setText(FORGET_TEXT);
        } else if (input.equals("modify")) {
            text.setText(MODIFY_TEXT);
        } else if (input.equals("newPhone")) {
            text.setText(MODIFY_PHONE_TEXT);
        }
    }

    @Override
    public void loadInfo(BaseInfo info) {
        if(info.getCode()==0){
            showToast("注册成功");
            LoginActivity.launch(this);
        }
    }

    @Override
    public void showAnimation(String msg) {
        if (msg.equals("edt_pass")) {
            text.setText(REGISTER_TEXT);
            text.setTextColor(ContextCompat.getColor(SetPassActivity.this, android.R.color.black));
            Animation shake = AnimationUtils.loadAnimation(SetPassActivity.this, R.anim.shake);
            this.pass.startAnimation(shake);
        } else if (msg.equals("edt_pass2")) {
            text.setText(REGISTER_TEXT);
            text.setTextColor(ContextCompat.getColor(SetPassActivity.this, android.R.color.black));
            Animation shake = AnimationUtils.loadAnimation(SetPassActivity.this, R.anim.shake);
            this.pass2.startAnimation(shake);
        } else if (msg.equals("pass<6")) {
            text.setText("密码不能小于6个字符");
            text.setTextColor(ContextCompat.getColor(SetPassActivity.this, android.R.color.holo_red_light));
            Animation shake = AnimationUtils.loadAnimation(SetPassActivity.this, R.anim.shake);
            this.pass.startAnimation(shake);
        } else if (msg.equals("pass>=6")) {
            if (!pass.getText().toString().equals(pass2.getText().toString())) {
                text.setText("两次密码不一致");
                text.setTextColor(ContextCompat.getColor(SetPassActivity.this, android.R.color.holo_red_light));
                Animation shake = AnimationUtils.loadAnimation(SetPassActivity.this, R.anim.shake);
                this.pass2.startAnimation(shake);
            }else {
                mPresenter.getData(phone,pass.getText().toString());
            }
        }
    }

    @OnClick(R.id.finish)
    public void onViewClicked() {
        mPresenter.getData(phone,pass.getText().toString(),pass2.getText().toString());
    }
}
