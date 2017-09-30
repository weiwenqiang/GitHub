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

import com.marktony.zhihudaily.bean.DoubanMomentNews;
import com.marktony.zhihudaily.BasePresenter;
import com.marktony.zhihudaily.BaseView;

import java.util.ArrayList;

/**
 * Created by Lizhaotailang on 2016/9/10.
 */

public interface DoubanMomentContract {

    interface View extends BaseView<Presenter> {

        void startLoading();

        void stopLoading();

        void showLoadingError();

        void showResults(ArrayList<DoubanMomentNews.posts> list);

    }

    interface Presenter extends BasePresenter {

        void startReading(int position);

        void loadPosts(long date, boolean clearing);

        void refresh();

        void loadMore(long date);

        void feelLucky();

    }

}
