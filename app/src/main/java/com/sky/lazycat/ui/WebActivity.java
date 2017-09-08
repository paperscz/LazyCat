package com.sky.lazycat.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextSwitcher;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.sky.lazycat.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yuetu-develop on 2017/8/21.
 */

public class WebActivity extends AppCompatActivity{

    private static final String EXTRA_URL = "extra_url";
    private static final String EXTRA_TITLE = "extra_title";
    private String mUrl, mTitle;
    @BindView(R.id.progressbar) NumberProgressBar mProgressbar;
    @BindView(R.id.webView) WebView mWebView;
    private Unbinder unbinder;

    public static Intent newIntent(Context context, String extraURL, String extraTitle) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(EXTRA_URL, extraURL);
        intent.putExtra(EXTRA_TITLE, extraTitle);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        unbinder = ButterKnife.bind(this);

        mUrl = getIntent().getStringExtra(EXTRA_URL);
        mTitle = getIntent().getStringExtra(EXTRA_TITLE);

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setSupportZoom(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.setWebChromeClient(new ChromeClient());
        mWebView.setWebViewClient(new LoveClient());

        mWebView.loadUrl(mUrl);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private class ChromeClient extends WebChromeClient {

        @Override public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if(null == mProgressbar){
                return;
            }
            mProgressbar.setProgress(newProgress);
            if (newProgress == 100) {
                mProgressbar.setVisibility(View.GONE);
            } else {
                mProgressbar.setVisibility(View.VISIBLE);
            }
        }
        @Override public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            setTitle(title);
        }
    }
    private class LoveClient extends WebViewClient {

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url != null) view.loadUrl(url);
            return true;
        }
    }
}
