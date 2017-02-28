package com.shahinjo.dev.chartsitunes.activities;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.shahinjo.dev.chartsitunes.R;

/**
 * Created by shahinjo on 2/27/17.
 */

public class WebControlActivity extends BaseDrawerActivity implements SwipeRefreshLayout.OnRefreshListener {

    WebView webView;
    SwipeRefreshLayout swipeRefreshLayout;
    Bundle extras;
    private String itunesURL;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_web_control, contentLayout);

        webView = (WebView) findViewById(R.id.web_view_browser);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                swipeRefreshLayout.setRefreshing(true);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);//
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);

        extras = getIntent().getExtras();

        itunesURL = extras.getString(ItemDetailsActivity.KEY_APP_URI);

        navigationView.setCheckedItem(sharedPrefs.getNavSelectedItem());

        reloadWebView();

    }

    private void reloadWebView() {
        webView.loadUrl(itunesURL);
    }

    @Override
    public void onRefresh() {
        reloadWebView();
    }
}
