package com.hpw.myapp.ui.tv.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.hpw.mvpframe.base.CoreBaseActivity;
import com.hpw.mvpframe.base.CoreBaseFragment;
import com.hpw.mvpframe.utils.StatusBarUtil;
import com.hpw.mvpframe.utils.helper.FragmentAdapter;
import com.hpw.myapp.Constants;
import com.hpw.myapp.R;
import com.hpw.myapp.ui.tv.contract.TvContract;
import com.hpw.myapp.ui.tv.fragment.FirstFragment;
import com.hpw.myapp.ui.tv.fragment.OtherFragment;
import com.hpw.myapp.ui.tv.model.TabBean;
import com.hpw.myapp.ui.tv.model.TvMainModel;
import com.hpw.myapp.ui.tv.presenter.TvMainPresenter;
import com.hpw.myapp.ui.zhihu.activity.ZhihuMainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hpw on 16/12/2.
 */

public class TvMainActivity extends CoreBaseActivity<TvMainPresenter, TvMainModel> implements TvContract.TvMainView, CoreBaseFragment.OnBackToFirstListener {
    List<Fragment> fragments = new ArrayList<>();
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tv;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        StatusBarUtil.setTransparent(this);
        toolbar.setTitle("全民TV");
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showTabList(List<TabBean> mTabs) {
        for (int i = 0; i < mTabs.size(); i++) {
            tabs.addTab(tabs.newTab().setText(mTabs.get(i).getName()));
            switch (i) {
                case 0:
                    fragments.add(new FirstFragment());
                    break;
                default:
                    fragments.add(OtherFragment.newInstance("json/categories/" + mTabs.get(i).getSlug() + "/list.json"));
                    break;
            }
        }
        int position = 0;
        for (int i = 0; i < mTabs.size(); i++) {
            if (mTabs.get(i).getSlug().equals(getIntent().getStringExtra(Constants.ARG_POSITION_TV)))
                position = i;
        }
        viewpager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragments));
        viewpager.setCurrentItem(position);//要设置到viewpager.setAdapter后才起作用
        tabs.setupWithViewPager(viewpager);
        tabs.setVerticalScrollbarPosition(position);
        for (int i = 0; i < mTabs.size(); i++) {
            tabs.getTabAt(i).setText(mTabs.get(i).getName());
        }
    }

    public static void startActivity(Context context, String position) {
        Intent starter = new Intent(context, TvMainActivity.class);
        starter.putExtra(Constants.ARG_POSITION_TV, position);
        context.startActivity(starter);
    }

    @Override
    public void onBackToFirstFragment() {
        startActivity(ZhihuMainActivity.class);
        finish();
    }
}
