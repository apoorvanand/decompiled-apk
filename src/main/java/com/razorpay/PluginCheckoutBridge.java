package com.razorpay;

import android.webkit.JavascriptInterface;
import java.util.HashMap;
import java.util.Map;

public class PluginCheckoutBridge extends c__C_ {
    /* access modifiers changed from: private */
    public final PluginCheckoutInteractor a_$P$;

    PluginCheckoutBridge(PluginCheckoutInteractor pluginCheckoutInteractor) {
        super(pluginCheckoutInteractor, 1);
        this.a_$P$ = pluginCheckoutInteractor;
    }

    @JavascriptInterface
    public /* bridge */ /* synthetic */ void invokePopup(String str) {
        super.invokePopup(str);
    }

    @JavascriptInterface
    public /* bridge */ /* synthetic */ void onCheckoutBackPress() {
        super.onCheckoutBackPress();
    }

    @JavascriptInterface
    public void processPayment(final String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("data", str);
        AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_AMAZON_PROCESS_PAYMENT_CALLED, (Map<String, Object>) hashMap);
        super.d__1_(new c__C_$Q_$2$() {
            public final void G__G_() {
                PluginCheckoutBridge.this.a_$P$.processPayment(str);
            }
        });
    }
}
