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

import android.webkit.WebView;

import com.marktony.zhihudaily.BasePresenter;
import com.marktony.zhihudaily.BaseView;

/**
 * Created by lizhaotailang on 2016/12/27.
 */

public class DetailContract {

    interface View extends BaseView<Presenter> {

        void showLoading();

        void stopLoading();

        void showLoadingError();

        void showSharingError();

        void showResult(String result);

        void showResultWithoutBody(String url);

        void showCover(String url);

        void setTitle(String title);

        void setImageMode(boolean showImage);

        void showBrowserNotFoundError();

        void showTextCopied();

        void showCopyTextError();

        void showAddedToBookmarks();

        void showDeletedFromBookmarks();

    }

    interface Presenter extends BasePresenter{

        void openInBrowser();

        void shareAsText();

        void openUrl(WebView webView, String url);

        void copyText();

        void copyLink();

        void addToOrDeleteFromBookmarks();

        boolean queryIfIsBookmarked();

        void requestData();

    }

}
