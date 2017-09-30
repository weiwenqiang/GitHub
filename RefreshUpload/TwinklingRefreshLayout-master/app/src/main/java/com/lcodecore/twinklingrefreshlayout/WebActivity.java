package com.lcodecore.twinklingrefreshlayout;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

public class WebActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        TwinklingRefreshLayout refreshLayout = (TwinklingRefreshLayout) findViewById(R.id.refreshLayout);
        ProgressLayout header = new ProgressLayout(this);
        refreshLayout.setHeaderView(header);
        refreshLayout.setFloatRefresh(true);
        refreshLayout.setOverScrollRefreshShow(false);
        refreshLayout.setHeaderHeight(140);
        refreshLayout.setWaveHeight(240);
        refreshLayout.setOverScrollHeight(200);
        refreshLayout.setEnableLoadmore(false);
        header.setColorSchemeResources(R.color.Blue,R.color.Orange,R.color.Yellow,R.color.Green);
//        header.setColorSchemeColors(0xff4674e7,0xff0ba62c);

        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.loadUrl("https://dribbble.com/shots");

        refreshLayout.startRefresh();
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefreshing();
                    }
                }, 4000);
            }
        });
    }

    @Override
    protected void onPause() {
        mWebView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mWebView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }
}
