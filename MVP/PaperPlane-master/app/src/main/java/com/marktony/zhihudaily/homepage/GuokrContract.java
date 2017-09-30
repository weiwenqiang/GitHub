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

import com.marktony.zhihudaily.bean.GuokrHandpickNews;
import com.marktony.zhihudaily.BasePresenter;
import com.marktony.zhihudaily.BaseView;

import java.util.ArrayList;

/**
 * Created by Lizhaotailang on 2016/9/15.
 */

public interface GuokrContract {

    interface View extends BaseView<Presenter> {

        void showError();

        void showResults(ArrayList<GuokrHandpickNews.result> list);

        void showLoading();

        void stopLoading();

    }

    interface Presenter extends BasePresenter{

        void loadPosts();

        void refresh();

        void startReading(int position);

        void feelLucky();

    }

}
