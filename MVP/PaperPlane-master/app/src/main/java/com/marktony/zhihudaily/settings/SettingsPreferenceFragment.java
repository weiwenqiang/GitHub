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

package com.marktony.zhihudaily.settings;

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

public class SettingsPreferenceFragment extends PreferenceFragmentCompat
        implements SettingsContract.View{

    private SettingsContract.Presenter presenter;
    private Toolbar toolbar;

    private Preference timePreference;

    public SettingsPreferenceFragment() {

    }

    public static SettingsPreferenceFragment newInstance() {
        return new SettingsPreferenceFragment();
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        addPreferencesFromResource(R.xml.settings_preference_fragment);

        initViews(getView());

        findPreference("no_picture_mode").setOnPreferenceClickListener(new android.support.v7.preference.Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(android.support.v7.preference.Preference preference) {
                presenter.setNoPictureMode(preference);
                return false;
            }
        });

        findPreference("in_app_browser").setOnPreferenceClickListener(new android.support.v7.preference.Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(android.support.v7.preference.Preference preference) {
                presenter.setInAppBrowser(preference);
                return false;
            }
        });

        findPreference("clear_glide_cache").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                presenter.cleanGlideCache();
                return false;
            }
        });


        timePreference = findPreference("time_of_saving_articles");

        timePreference.setSummary(presenter.getTimeSummary());

        timePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                presenter.setTimeOfSavingArticles(preference, newValue);
                timePreference.setSummary(presenter.getTimeSummary());
                return true;
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void showCleanGlideCacheDone() {
        Snackbar.make(toolbar, R.string.clear_image_cache_successfully, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(SettingsContract.Presenter presenter) {
        if (presenter != null){
            this.presenter = presenter;
        }
    }

    @Override
    public void initViews(View view) {
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
    }

}
