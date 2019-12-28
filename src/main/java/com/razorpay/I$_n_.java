package com.razorpay;

import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

final class I$_n_ extends WebChromeClient {
    private L__R$ Q_$2$;

    public I$_n_(L__R$ l__r$) {
        this.Q_$2$ = l__r$;
    }

    public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        return false;
    }

    public final void onProgressChanged(WebView webView, int i) {
        this.Q_$2$.onProgressChanges(2, i);
    }
}
