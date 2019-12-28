package com.razorpay;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

final class U$_z$ extends WebViewClient {
    private L__R$ R$$r_;

    public U$_z$(L__R$ l__r$) {
        this.R$$r_ = l__r$;
    }

    public final void onPageFinished(WebView webView, String str) {
        this.R$$r_.onPageFinished(2, webView, str);
    }

    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        webView.setTag(str);
        this.R$$r_.onPageStarted(2, webView, str);
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        this.R$$r_.destroyActivity(2, str);
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return false;
    }
}
