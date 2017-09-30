package com.hpw.myapp.ui.main;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.hpw.mvpframe.base.CoreBaseActivity;
import com.hpw.mvpframe.utils.StatusBarUtil;
import com.hpw.myapp.R;
import com.hpw.myapp.ui.zhihu.activity.WechatDetailsActivity;

import butterknife.BindView;

/**
 * Created by hpw on 16/11/23.
 */

public class AboutActivity extends CoreBaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_content1)
    TextView content;

    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        StatusBarUtil.setTransparent(this);
        setToolBar(toolbar, "关于");
        content.setOnClickListener(onClick -> {
            WechatDetailsActivity.start(mContext, "MVP", "https://github.com/SuperMan42/MVP/blob/master/README.md");
        });
    }
}
