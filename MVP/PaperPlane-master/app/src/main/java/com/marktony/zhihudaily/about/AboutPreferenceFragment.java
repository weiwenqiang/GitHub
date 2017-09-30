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

package com.marktony.zhihudaily.about;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.marktony.zhihudaily.R;

/**
 * Created by lizhaotailang on 2016/7/26.
 */

public class AboutPreferenceFragment extends PreferenceFragmentCompat
        implements AboutContract.View {

    private Toolbar toolbar;
    private AboutContract.Presenter presenter;

    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        addPreferencesFromResource(R.xml.about_preference_fragment);

        initViews(getView());

        findPreference("rate").setOnPreferenceClickListener(new android.support.v7.preference.Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(android.support.v7.preference.Preference preference) {
                presenter.rate();
                return false;
            }
        });

        findPreference("open_source_license").setOnPreferenceClickListener(new android.support.v7.preference.Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(android.support.v7.preference.Preference preference) {
                presenter.openLicense();
                return false;
            }
        });

        findPreference("follow_me_on_github").setOnPreferenceClickListener(new android.support.v7.preference.Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(android.support.v7.preference.Preference preference) {
                presenter.followOnGithub();
                return false;
            }
        });

        findPreference("follow_me_on_zhihu").setOnPreferenceClickListener(new android.support.v7.preference.Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(android.support.v7.preference.Preference preference) {
                presenter.followOnZhihu();
                return false;
            }
        });

        findPreference("feedback").setOnPreferenceClickListener(new android.support.v7.preference.Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(android.support.v7.preference.Preference preference) {
                presenter.feedback();
                return false;
            }
        });

        findPreference("coffee").setOnPreferenceClickListener(new android.support.v7.preference.Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(android.support.v7.preference.Preference preference) {
                presenter.donate();
                return false;
            }
        });

        findPreference("author").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                presenter.showEasterEgg();
                return false;
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void setPresenter(AboutContract.Presenter presenter) {
        if (presenter != null){
            this.presenter = presenter;
        }
    }

    // some problems occur when set support action bar
    // with PreferenceFragmentCompat
    // setting display home as up enable can not work either
    // so work it in activity directly
    @Override
    public void initViews(View view) {
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
    }

    @Override
    public void showRateError() {
        Snackbar.make(toolbar, R.string.no_app_store_found,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showFeedbackError() {
        Snackbar.make(toolbar, R.string.no_mail_app,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showBrowserNotFoundError() {
        Snackbar.make(toolbar, R.string.no_browser_found,Snackbar.LENGTH_SHORT).show();
    }

}
