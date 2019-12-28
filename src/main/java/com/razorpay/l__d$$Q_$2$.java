package com.razorpay;

import android.webkit.WebView;

interface l__d$$Q_$2$ {
    void clearWebViewHistory(int i);

    void destroy(int i, String str);

    WebView getWebView(int i);

    void hideProgressBar();

    boolean isWebViewVisible(int i);

    void loadDataWithBaseURL(int i, String str, String str2, String str3, String str4, String str5);

    void loadUrl(int i, String str);

    void makeWebViewVisible(int i);

    void requestSmsPermission();

    void showProgressBar(int i);

    void showToast(String str, int i);
}
