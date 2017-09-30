package com.hpw.myapp.ui.zhihu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.hpw.mvpframe.base.CoreBaseActivity;
import com.hpw.myapp.R;
import com.hpw.myapp.ui.main.AboutActivity;
import com.hpw.myapp.ui.map.PoiAroundSearchActivity;
import com.hpw.myapp.ui.publish.PublishActivity;
import com.hpw.myapp.ui.tv.activity.TvMainActivity;
import com.hpw.myapp.ui.zhihu.fragment.ZhihuMainFragment;

import butterknife.BindView;

public class ZhihuMainActivity extends CoreBaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        ZhihuMainFragment.OnFragmentOpenDrawerListener, ZhihuMainFragment.OnBackToFirstListener {

    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @Override
    protected void onResume() {
        super.onResume();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, (Toolbar) findViewById(R.id.toolbar), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public boolean isOpen() {
        return true;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_container, ZhihuMainFragment.newInstance(0));
        }
    }

    @Override
    public void onBackPressedSupport() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressedSupport();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_publish) {
            startActivity(PublishActivity.class);
        } else if (id == R.id.menu_tv) {
            startActivity(TvMainActivity.class);
        } else if (id == R.id.menu_map) {
            Intent intent = new Intent(mContext, PoiAroundSearchActivity.class);
            startActivityForResult(intent, PoiAroundSearchActivity.REQUESTCODE);
        } else if (id == R.id.about) {
            startActivity(AboutActivity.class);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onOpenDrawer() {
        if (!drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onBackToFirstFragment() {
        loadRootFragment(R.id.fl_container, ZhihuMainFragment.newInstance(1));
    }
}
