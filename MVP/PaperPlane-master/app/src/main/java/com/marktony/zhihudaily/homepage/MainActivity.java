/*
 * Copyright 2017 lizhaotailang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.marktony.zhihudaily.homepage;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.marktony.zhihudaily.R;
import com.marktony.zhihudaily.about.AboutPreferenceActivity;
import com.marktony.zhihudaily.bookmarks.BookmarksFragment;
import com.marktony.zhihudaily.bookmarks.BookmarksPresenter;
import com.marktony.zhihudaily.service.CacheService;
import com.marktony.zhihudaily.settings.SettingsPreferenceActivity;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private MainFragment mainFragment;
    private BookmarksFragment bookmarksFragment;

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Toolbar toolbar;

    public static final String ACTION_BOOKMARKS = "com.marktony.zhihudaily.bookmarks";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        if (savedInstanceState != null) {
            mainFragment = (MainFragment) getSupportFragmentManager().getFragment(savedInstanceState, "MainFragment");
            bookmarksFragment = (BookmarksFragment) getSupportFragmentManager().getFragment(savedInstanceState, "BookmarksFragment");
        } else {
            mainFragment = MainFragment.newInstance();
            bookmarksFragment = BookmarksFragment.newInstance();
        }

        if (!mainFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.layout_fragment, mainFragment, "MainFragment")
                    .commit();
        }

        if (!bookmarksFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.layout_fragment, bookmarksFragment, "BookmarksFragment")
                    .commit();
        }

        new BookmarksPresenter(MainActivity.this, bookmarksFragment);

        String action = getIntent().getAction();

        if (action.equals(ACTION_BOOKMARKS)) {
            showBookmarksFragment();
            navigationView.setCheckedItem(R.id.nav_bookmarks);
        } else {
            showMainFragment();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        startService(new Intent(this, CacheService.class));

    }

    private void initViews() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void showMainFragment() {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.show(mainFragment);
        fragmentTransaction.hide(bookmarksFragment);
        fragmentTransaction.commit();

        toolbar.setTitle(getResources().getString(R.string.app_name));

    }

    private void showBookmarksFragment() {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.show(bookmarksFragment);
        fragmentTransaction.hide(mainFragment);
        fragmentTransaction.commit();

        toolbar.setTitle(getResources().getString(R.string.nav_bookmarks));

        if (bookmarksFragment.isAdded()) {
            bookmarksFragment.notifyDataChanged();
        }

    }

    @Override
    protected void onDestroy() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (CacheService.class.getName().equals(service.service.getClassName())) {
                stopService(new Intent(this, CacheService.class));
            }
        }
        super.onDestroy();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        drawer.closeDrawer(GravityCompat.START);

        int id = item.getItemId();
        if (id == R.id.nav_home) {
            showMainFragment();
        } else if (id == R.id.nav_bookmarks) {
            showBookmarksFragment();
        } else if (id == R.id.nav_change_theme) {

            // change the day/night mode after the drawer closed
            drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
                @Override
                public void onDrawerSlide(View drawerView, float slideOffset) {

                }

                @Override
                public void onDrawerOpened(View drawerView) {

                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    SharedPreferences sp =  getSharedPreferences("user_settings",MODE_PRIVATE);
                    if ((getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK)
                            == Configuration.UI_MODE_NIGHT_YES) {
                        sp.edit().putInt("theme", 0).apply();
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    } else {
                        sp.edit().putInt("theme", 1).apply();
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    }
                    getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
                    recreate();
                }

                @Override
                public void onDrawerStateChanged(int newState) {

                }
            });

        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(this,SettingsPreferenceActivity.class));
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(this,AboutPreferenceActivity.class));
        }

        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mainFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, "MainFragment", mainFragment);
        }

        if (bookmarksFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, "BookmarksFragment", bookmarksFragment);
        }
    }

}
