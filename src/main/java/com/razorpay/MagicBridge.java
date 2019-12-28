package com.razorpay;

import android.webkit.JavascriptInterface;

public class MagicBridge {
    private Y$_o$ d__1_;

    MagicBridge(Y$_o$ y$_o$) {
        this.d__1_ = y$_o$;
    }

    @JavascriptInterface
    public final void relay(String str) {
        this.d__1_.sendDataToWebView(1, str);
    }
}
