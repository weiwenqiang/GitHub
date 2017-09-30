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

import com.marktony.zhihudaily.BasePresenter;
import com.marktony.zhihudaily.BaseView;
import com.marktony.zhihudaily.bean.ZhihuDailyNews;

import java.util.ArrayList;

/**
 * Created by Lizhaotailang on 2016/9/16.
 */

public interface ZhihuDailyContract {

    interface View extends BaseView<Presenter> {

        void showError();

        void showLoading();

        void stopLoading();

        void showResults(ArrayList<ZhihuDailyNews.Question> list);

    }

    interface Presenter extends BasePresenter {

        void loadPosts(long date, boolean clearing);

        void refresh();

        void loadMore(long date);

        void startReading(int position);

        void feelLucky();

    }

}
