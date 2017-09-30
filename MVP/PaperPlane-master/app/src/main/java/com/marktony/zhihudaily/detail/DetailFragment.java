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

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.marktony.zhihudaily.R;

/**
 * Created by lizhaotailang on 2016/12/27.
 */

public class DetailFragment extends Fragment
        implements DetailContract.View {

    private ImageView imageView;
    private WebView webView;
    private NestedScrollView scrollView;
    private CollapsingToolbarLayout toolbarLayout;
    private SwipeRefreshLayout refreshLayout;

    private Context context;

    private DetailContract.Presenter presenter;

    public DetailFragment() {}


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.universal_read_layout, container, false);

        initViews(view);

        setHasOptionsMenu(true);

        presenter.requestData();

        view.findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.smoothScrollTo(0, 0);
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.requestData();
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_more, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            getActivity().onBackPressed();
        } else if (id == R.id.action_more) {

            final BottomSheetDialog dialog = new BottomSheetDialog(getActivity());

            View view = getActivity().getLayoutInflater().inflate(R.layout.reading_actions_sheet, null);

            if (presenter.queryIfIsBookmarked()) {
                ((TextView) view.findViewById(R.id.textView)).setText(R.string.action_delete_from_bookmarks);
                ((ImageView) view.findViewById(R.id.imageView))
                        .setColorFilter(getContext().getResources().getColor(R.color.colorPrimary));
            }

            // add to bookmarks or delete from bookmarks
            view.findViewById(R.id.layout_bookmark).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    presenter.addToOrDeleteFromBookmarks();
                }
            });

            // copy the article's link to clipboard
            view.findViewById(R.id.layout_copy_link).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    presenter.copyLink();
                }
            });

            // open the link in browser
            view.findViewById(R.id.layout_open_in_browser).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    presenter.openInBrowser();
                }
            });

            // copy the text content to clipboard
            view.findViewById(R.id.layout_copy_text).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    presenter.copyText();
                }
            });

            // shareAsText the content as text
            view.findViewById(R.id.layout_share_text).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    presenter.shareAsText();
                }
            });

            dialog.setContentView(view);
            dialog.show();
        }
        return true;
    }

    @Override
    public void showLoading() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void stopLoading() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void showLoadingError() {
        Snackbar.make(imageView,R.string.loaded_failed,Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.requestData();
                    }
                })
                .show();
    }

    @Override
    public void showSharingError() {
        Snackbar.make(imageView,R.string.share_error,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showResult(String result) {
        webView.loadDataWithBaseURL("x-data://base",result,"text/html","utf-8",null);
    }

    @Override
    public void showResultWithoutBody(String url) {
        webView.loadUrl(url);
    }

    @Override
    public void showCover(String url) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .error(R.drawable.placeholder)
                .into(imageView);
    }

    @Override
    public void setTitle(String title) {
        setCollapsingToolbarLayoutTitle(title);
    }

    @Override
    public void setImageMode(boolean showImage) {
        webView.getSettings().setBlockNetworkImage(showImage);
    }

    @Override
    public void showBrowserNotFoundError() {
        Snackbar.make(imageView, R.string.no_browser_found,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showTextCopied() {
        Snackbar.make(imageView, R.string.copied_to_clipboard, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showCopyTextError() {
        Snackbar.make(imageView, R.string.copied_to_clipboard_failed, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showAddedToBookmarks() {
        Snackbar.make(imageView, R.string.added_to_bookmarks, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showDeletedFromBookmarks() {
        Snackbar.make(imageView, R.string.deleted_from_bookmarks, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
        if (presenter != null) {
            this.presenter = presenter;
        }
    }

    @Override
    public void initViews(View view) {

        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);
        //设置下拉刷新的按钮的颜色
        refreshLayout.setColorSchemeResources(R.color.colorPrimary);

        webView = (WebView) view.findViewById(R.id.web_view);
        webView.setScrollbarFadingEnabled(true);

        DetailActivity activity = (DetailActivity) getActivity();
        activity.setSupportActionBar((Toolbar) view.findViewById(R.id.toolbar));
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = (ImageView) view.findViewById(R.id.image_view);
        scrollView = (NestedScrollView) view.findViewById(R.id.scrollView);
        toolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.toolbar_layout);

        //能够和js交互
        webView.getSettings().setJavaScriptEnabled(true);
        //缩放,设置为不能缩放可以防止页面上出现放大和缩小的图标
        webView.getSettings().setBuiltInZoomControls(false);
        //缓存
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        //开启DOM storage API功能
        webView.getSettings().setDomStorageEnabled(true);
        //开启application Cache功能
        webView.getSettings().setAppCacheEnabled(false);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                presenter.openUrl(view, url);
                return true;
            }

        });

    }

    // to change the title's font size of toolbar layout
    private void setCollapsingToolbarLayoutTitle(String title) {
        toolbarLayout.setTitle(title);
        toolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        toolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        toolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBarPlus1);
        toolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBarPlus1);
    }

}
