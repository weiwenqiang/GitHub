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

package com.marktony.zhihudaily.license;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.marktony.zhihudaily.R;

/**
 * Created by Lizhaotailang on 2016/9/3.
 */

public class OpenSourceLicenseFragment extends Fragment
        implements OpenSourceListenContract.View{

    private WebView webView;

    public OpenSourceLicenseFragment() {
        // requires an empty constructor
    }

    public static OpenSourceLicenseFragment newInstance() {
        return new OpenSourceLicenseFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_open_source_license, container, false);

        initViews(view);
        setHasOptionsMenu(true);

        loadLicense("file:///android_asset/license.html");

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            getActivity().onBackPressed();
        }
        return true;
    }

    @Override
    public void setPresenter(OpenSourceListenContract.Presenter presenter) {

    }

    @Override
    public void initViews(View view) {
        AppCompatActivity activity = (OpenSourceLicenseActivity) getActivity();
        activity.setSupportActionBar((Toolbar) view.findViewById(R.id.toolbar));
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        webView = (WebView) view.findViewById(R.id.web_view);
    }

    @Override
    public void loadLicense(String path) {
        webView.loadUrl(path);
    }

}
