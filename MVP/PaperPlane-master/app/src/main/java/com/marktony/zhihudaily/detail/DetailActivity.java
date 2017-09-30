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

package com.marktony.zhihudaily.detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.marktony.zhihudaily.R;
import com.marktony.zhihudaily.bean.BeanType;

public class DetailActivity extends AppCompatActivity {

    private DetailFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame);

        if (savedInstanceState != null) {
            fragment = (DetailFragment) getSupportFragmentManager().getFragment(savedInstanceState,"detailFragment");
        } else {
            fragment = new DetailFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }

        Intent intent = getIntent();

        DetailPresenter presenter = new DetailPresenter(DetailActivity.this, fragment);

        presenter.setType((BeanType) intent.getSerializableExtra("type"));
        presenter.setId(intent.getIntExtra("id", 0));
        presenter.setTitle(intent.getStringExtra("title"));
        presenter.setCoverUrl(intent.getStringExtra("coverUrl"));

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (fragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, "detailFragment", fragment);
        }
    }
}
