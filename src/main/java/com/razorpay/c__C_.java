package com.razorpay;

import android.webkit.JavascriptInterface;

class c__C_ implements c__C_$Q_$2$ {
    Y$_o$ G__G_;
    private int Q_$2$;

    c__C_(Y$_o$ y$_o$, int i) {
        this.G__G_ = y$_o$;
        this.Q_$2$ = i;
    }

    public final void G__G_() {
        this.G__G_.onLoad();
    }

    @JavascriptInterface
    public final void callNativeIntent(final String str) {
        this.G__G_.isWebViewSafe(this.Q_$2$, new c__C_$Q_$2$() {
            public final void G__G_() {
                c__C_.this.G__G_.callNativeIntent(str, (String) null);
            }
        });
    }

    @JavascriptInterface
    public final void callNativeIntent(final String str, final String str2) {
        this.G__G_.isWebViewSafe(this.Q_$2$, new c__C_$Q_$2$() {
            public final void G__G_() {
                c__C_.this.G__G_.callNativeIntent(str, str2);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public final void d__1_(c__C_$Q_$2$ c__c__q__2_) {
        this.G__G_.isWebViewSafe(this.Q_$2$, c__c__q__2_);
    }

    @JavascriptInterface
    public void invokePopup(final String str) {
        this.G__G_.invokePopup(str);
        this.G__G_.isWebViewSafe(this.Q_$2$, new c__C_$Q_$2$() {
            public final void G__G_() {
                c__C_.this.G__G_.invokePopup(str);
            }
        });
    }

    @JavascriptInterface
    public void onCheckoutBackPress() {
        this.G__G_.onCheckoutBackPress();
        this.G__G_.isWebViewSafe(this.Q_$2$, new c__C_$Q_$2$() {
            public final void G__G_() {
                c__C_.this.G__G_.onCheckoutBackPress();
            }
        });
    }

    @JavascriptInterface
    public final void oncomplete(final String str) {
        this.G__G_.isWebViewSafe(this.Q_$2$, new c__C_$Q_$2$() {
            public final void G__G_() {
                c__C_.this.G__G_.onComplete(str);
            }
        });
    }

    @JavascriptInterface
    public final void ondismiss() {
        this.G__G_.isWebViewSafe(this.Q_$2$, new c__C_$Q_$2$() {
            public final void G__G_() {
                c__C_.this.G__G_.onDismiss();
            }
        });
    }

    @JavascriptInterface
    public final void onerror(final String str) {
        this.G__G_.isWebViewSafe(this.Q_$2$, new c__C_$Q_$2$() {
            public final void G__G_() {
                c__C_.this.G__G_.onError(str);
            }
        });
    }

    @JavascriptInterface
    public final void onfault(final String str) {
        this.G__G_.isWebViewSafe(this.Q_$2$, new c__C_$Q_$2$() {
            public final void G__G_() {
                c__C_.this.G__G_.onFault(str);
            }
        });
    }

    @JavascriptInterface
    public final void onload() {
        this.G__G_.isWebViewSafe(this.Q_$2$, this);
    }

    @JavascriptInterface
    public final void onsubmit(final String str) {
        this.G__G_.isWebViewSafe(this.Q_$2$, new c__C_$Q_$2$() {
            public final void G__G_() {
                c__C_.this.G__G_.onSubmit(str);
            }
        });
    }

    @JavascriptInterface
    public final void relay(final String str) {
        this.G__G_.isWebViewSafe(this.Q_$2$, new c__C_$Q_$2$() {
            public final void G__G_() {
                c__C_.this.G__G_.sendDataToWebView(2, str);
            }
        });
    }

    @JavascriptInterface
    public final void requestExtraAnalyticsData() {
        this.G__G_.isWebViewSafe(this.Q_$2$, new c__C_$Q_$2$() {
            public final void G__G_() {
                c__C_.this.G__G_.requestExtraAnalyticsData();
            }
        });
    }

    @JavascriptInterface
    public final void setAppToken(final String str) {
        this.G__G_.setAppToken(str);
        this.G__G_.isWebViewSafe(this.Q_$2$, new c__C_$Q_$2$() {
            public final void G__G_() {
                c__C_.this.G__G_.setAppToken(str);
            }
        });
    }

    @JavascriptInterface
    public final void setCheckoutBody(final String str) {
        this.G__G_.isWebViewSafe(this.Q_$2$, new c__C_$Q_$2$() {
            public final void G__G_() {
                c__C_.this.G__G_.setCheckoutBody(str);
            }
        });
    }

    @JavascriptInterface
    public final void setDeviceToken(final String str) {
        this.G__G_.isWebViewSafe(this.Q_$2$, new c__C_$Q_$2$() {
            public final void G__G_() {
                c__C_.this.G__G_.setDeviceToken(str);
            }
        });
    }

    @JavascriptInterface
    public final void setDimensions(final int i, final int i2) {
        this.G__G_.isWebViewSafe(this.Q_$2$, new c__C_$Q_$2$() {
            public final void G__G_() {
                c__C_.this.G__G_.setDimensions(i, i2);
            }
        });
    }

    @JavascriptInterface
    public final void setMerchantOptions(final String str) {
        this.G__G_.isWebViewSafe(this.Q_$2$, new c__C_$Q_$2$() {
            public final void G__G_() {
                c__C_.this.G__G_.setMerchantOptions(str);
            }
        });
    }

    @JavascriptInterface
    public final void setPaymentID(final String str) {
        this.G__G_.isWebViewSafe(this.Q_$2$, new c__C_$Q_$2$() {
            public final void G__G_() {
                c__C_.this.G__G_.setPaymentID(str);
            }
        });
    }

    @JavascriptInterface
    public final void showAlertDialog(final String str, final String str2, final String str3) {
        this.G__G_.isWebViewSafe(this.Q_$2$, new c__C_$Q_$2$() {
            public final void G__G_() {
                c__C_.this.G__G_.showAlertDialog(str, str2, str3);
            }
        });
    }

    @JavascriptInterface
    public final void toast(final String str, final int i) {
        this.G__G_.isWebViewSafe(this.Q_$2$, new c__C_$Q_$2$() {
            public final void G__G_() {
                c__C_.this.G__G_.toast(str, i);
            }
        });
    }
}
