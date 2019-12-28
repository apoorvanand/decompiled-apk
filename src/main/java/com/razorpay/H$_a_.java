package com.razorpay;

import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import java.util.HashMap;
import java.util.Map;

final class H$_a_ extends WebChromeClient {
    private L__R$ d__1_;

    public H$_a_(L__R$ l__r$) {
        this.d__1_ = l__r$;
    }

    public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        if (consoleMessage.messageLevel() != ConsoleMessage.MessageLevel.ERROR) {
            return false;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("message", consoleMessage.message());
        hashMap.put("source_id", consoleMessage.sourceId());
        hashMap.put("line_number", String.valueOf(consoleMessage.lineNumber()));
        AnalyticsUtil.trackEvent(AnalyticsEvent.WEB_VIEW_JS_ERROR, (Map<String, Object>) hashMap);
        consoleMessage.message();
        return false;
    }

    public final void onProgressChanged(WebView webView, int i) {
        this.d__1_.onProgressChanges(1, i);
    }
}
