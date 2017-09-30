package com.hpw.myapp.ui.main;

import android.os.Bundle;

import com.hpw.mvpframe.base.CoreBaseActivity;
import com.hpw.mvpframe.utils.SpUtil;
import com.hpw.myapp.R;

public class MainActivity extends CoreBaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean isOpen() {
        return true;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        findViewById(R.id.btn_1).setOnClickListener(click -> {
            SpUtil.setNightModel(mContext, !SpUtil.getNightModel(mContext));
            reload();
        });
    }
}
