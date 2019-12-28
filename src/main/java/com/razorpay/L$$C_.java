package com.razorpay;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

final class L$$C_ extends WebViewClient {
    private L__R$ G__G_;

    public L$$C_(L__R$ l__r$) {
        this.G__G_ = l__r$;
    }

    public final void onPageFinished(WebView webView, String str) {
        this.G__G_.onPageFinished(1, webView, str);
    }

    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        webView.setTag(str);
        this.G__G_.onPageStarted(1, webView, str);
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        this.G__G_.destroyActivity(2, str);
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return false;
    }
}
