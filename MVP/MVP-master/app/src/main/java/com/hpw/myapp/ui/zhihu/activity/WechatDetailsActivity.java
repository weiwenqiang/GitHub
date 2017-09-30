package com.hpw.myapp.ui.zhihu.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.hpw.mvpframe.base.CoreBaseActivity;
import com.hpw.mvpframe.utils.NetUtils;
import com.hpw.mvpframe.utils.SpUtil;
import com.hpw.myapp.R;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;

public class WechatDetailsActivity extends CoreBaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolBar;
    @BindView(R.id.wv_tech_content)
    WebView wvTechContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_wechat_details;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        Intent intent = getIntent();
        setToolBar(toolBar, intent.getExtras().getString("title"));
        WebSettings settings = wvTechContent.getSettings();
        if (SpUtil.getNoImageState()) {
            settings.setBlockNetworkImage(true);
        }
        if (SpUtil.getAutoCacheState()) {
            settings.setAppCacheEnabled(true);
            settings.setDomStorageEnabled(true);
            settings.setDatabaseEnabled(true);
            if (NetUtils.isConnected(mContext)) {
                settings.setCacheMode(WebSettings.LOAD_DEFAULT);
            } else {
                settings.setCacheMode(WebSettings.LOAD_CACHE_ONLY);
            }
        }
        wvTechContent.setBackgroundColor(0x00000000);
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        wvTechContent.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        //可以操作progress
        wvTechContent.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {

                } else {

                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                setTitle(title);
            }
        });
        wvTechContent.loadUrl(intent.getExtras().getString("url"));
    }

    public static void start(Context context, String title, String url) {
        Intent intent = new Intent();
        intent.setClass(context, WechatDetailsActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }
}
