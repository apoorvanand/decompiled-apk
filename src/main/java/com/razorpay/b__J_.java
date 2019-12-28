package com.razorpay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.util.HashMap;

class b__J_ extends Activity implements O_$v$, l__d$$Q_$2$ {
    private WebChromeClient B$$W$;
    private WebChromeClient E$_j$;
    private WebViewClient G__G_;
    private WebView Q_$2$;
    private ViewGroup R$$r_;
    private RelativeLayout a_$P$;
    private WebViewClient b__J_;
    protected Object checkoutBridgeObject;
    private WebView d__1_;
    protected L__R$ presenter;
    private B$$J$ r$_Y_;

    b__J_() {
    }

    private void G__G_(int i, WebViewClient webViewClient) {
        switch (i) {
            case 1:
                this.G__G_ = webViewClient;
                return;
            case 2:
                this.b__J_ = webViewClient;
                return;
            default:
                return;
        }
    }

    private void a_$P$(int i, WebChromeClient webChromeClient) {
        switch (i) {
            case 1:
                this.B$$W$ = webChromeClient;
                return;
            case 2:
                this.E$_j$ = webChromeClient;
                return;
            default:
                return;
        }
    }

    @SuppressLint({"JavascriptInterface"})
    public void addJavascriptInterfaceToPrimaryWebview(Object obj, String str) {
        this.d__1_.addJavascriptInterface(obj, str);
    }

    public void clearWebViewHistory(int i) {
        switch (i) {
            case 1:
                this.d__1_.clearHistory();
                return;
            case 2:
                this.Q_$2$.clearHistory();
                return;
            default:
                return;
        }
    }

    public void destroy(int i, String str) {
        Intent intent = new Intent();
        intent.putExtra("RESULT", str);
        setResult(i, intent);
        finish();
    }

    public WebView getWebView(int i) {
        switch (i) {
            case 1:
                return this.d__1_;
            case 2:
                return this.Q_$2$;
            default:
                return null;
        }
    }

    public void hideProgressBar() {
        B$$J$ b$$j$ = this.r$_Y_;
        if (b$$j$ != null) {
            b$$j$.Q_$2$();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001b A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isWebViewVisible(int r2) {
        /*
            r1 = this;
            r0 = 1
            switch(r2) {
                case 1: goto L_0x0010;
                case 2: goto L_0x0005;
                default: goto L_0x0004;
            }
        L_0x0004:
            goto L_0x001b
        L_0x0005:
            android.webkit.WebView r2 = r1.Q_$2$
            if (r2 == 0) goto L_0x001b
            int r2 = r2.getVisibility()
            if (r2 != 0) goto L_0x001b
            goto L_0x001c
        L_0x0010:
            android.webkit.WebView r2 = r1.d__1_
            if (r2 == 0) goto L_0x001b
            int r2 = r2.getVisibility()
            if (r2 != 0) goto L_0x001b
            goto L_0x001c
        L_0x001b:
            r0 = 0
        L_0x001c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.razorpay.b__J_.isWebViewVisible(int):boolean");
    }

    public void loadData(int i, String str, String str2, String str3) {
        switch (i) {
            case 1:
                this.d__1_.loadData(str, str2, str3);
                return;
            case 2:
                this.Q_$2$.loadData(str, str2, str3);
                return;
            default:
                return;
        }
    }

    public void loadDataWithBaseURL(int i, String str, String str2, String str3, String str4, String str5) {
        switch (i) {
            case 1:
                this.d__1_.loadDataWithBaseURL(str, str2, str3, str4, str5);
                return;
            case 2:
                this.Q_$2$.loadDataWithBaseURL(str, str2, str3, str4, str5);
                return;
            default:
                return;
        }
    }

    public void loadUrl(int i, String str) {
        switch (i) {
            case 1:
                this.d__1_.loadUrl(str);
                return;
            case 2:
                this.Q_$2$.loadUrl(str);
                return;
            default:
                return;
        }
    }

    public void makeWebViewVisible(int i) {
        switch (i) {
            case 1:
                if (this.d__1_.getVisibility() == 8) {
                    this.d__1_.setVisibility(0);
                    this.Q_$2$.setVisibility(8);
                    g__v_.d__1_();
                    AnalyticsUtil.trackEvent(AnalyticsEvent.WEB_VIEW_SECONDARY_TO_PRIMARY_SWITCH);
                    return;
                }
                return;
            case 2:
                if (this.Q_$2$.getVisibility() == 8) {
                    this.d__1_.setVisibility(8);
                    this.Q_$2$.setVisibility(0);
                    g__v_.d__1_();
                    AnalyticsUtil.trackEvent(AnalyticsEvent.WEB_VIEW_PRIMARY_TO_SECONDARY_SWITCH);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.presenter.onActivityResultReceived(i, i2, intent);
    }

    public void onBackPressed() {
        this.presenter.backPressed(new HashMap());
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public void onCreate(Bundle bundle) {
        boolean z;
        Q__v$.Q_$2$(this, f$_G$.a_$P$);
        f$_G$.f$_G$().G__G_(O_$B_.Q_$2$(this, R.raw.rzp_config));
        BaseUtils.checkForLatestVersion(this, f$_G$.Q_$2$);
        this.presenter.setCheckoutLoadStartAt();
        G__G_(1, new L$$C_(this.presenter));
        G__G_(2, new U$_z$(this.presenter));
        a_$P$(1, new H$_a_(this.presenter));
        a_$P$(2, new I$_n_(this.presenter));
        BaseUtils.setup();
        AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_INIT);
        super.onCreate(bundle);
        requestWindowFeature(1);
        if (bundle == null) {
            bundle = getIntent().getExtras();
            z = false;
        } else {
            z = true;
        }
        if (this.presenter.setOptions(bundle, z)) {
            this.R$$r_ = (ViewGroup) findViewById(16908290);
            Object obj = this.checkoutBridgeObject;
            this.d__1_ = new WebView(this);
            BaseUtils.setWebViewSettings(this, this.d__1_, false);
            this.d__1_.clearFormData();
            this.d__1_.addJavascriptInterface(obj, "CheckoutBridge");
            this.d__1_.setWebChromeClient(this.B$$W$);
            this.d__1_.setWebViewClient(this.G__G_);
            this.Q_$2$ = new WebView(this);
            BaseUtils.setWebViewSettings(this, this.Q_$2$, false);
            this.Q_$2$.clearFormData();
            this.Q_$2$.addJavascriptInterface(new MagicBridge((Y$_o$) this.presenter), "MagicBridge");
            this.Q_$2$.addJavascriptInterface(new c__C_((Y$_o$) this.presenter, 2), "CheckoutBridge");
            this.Q_$2$.setVisibility(8);
            this.Q_$2$.setWebChromeClient(this.E$_j$);
            this.Q_$2$.setWebViewClient(this.b__J_);
            this.a_$P$ = new RelativeLayout(this);
            this.a_$P$.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            this.a_$P$.setBackgroundColor(-1);
            this.R$$r_.addView(this.a_$P$);
            this.d__1_.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            this.Q_$2$.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            this.a_$P$.addView(this.d__1_);
            this.a_$P$.addView(this.Q_$2$);
            String progressBarColor = this.presenter.getProgressBarColor();
            if (progressBarColor != null) {
                this.r$_Y_ = new B$$J$(this, this.a_$P$, progressBarColor);
            } else {
                this.r$_Y_ = new B$$J$(this, this.a_$P$);
            }
            this.presenter.setUpAddOn();
            this.presenter.loadForm("");
            this.presenter.passPrefillToSegment();
            if ((getWindow().getAttributes().flags & 1024) != 0) {
                r$_Y_.a_$P$(this);
            }
            if (getResources().getBoolean(R.bool.isTablet)) {
                setFinishOnTouchOutside(false);
                WindowManager.LayoutParams attributes = getWindow().getAttributes();
                attributes.height = (int) TypedValue.applyDimension(1, 600.0f, getResources().getDisplayMetrics());
                attributes.width = (int) TypedValue.applyDimension(1, 375.0f, getResources().getDisplayMetrics());
                getWindow().setAttributes(attributes);
            } else {
                setRequestedOrientation(1);
            }
            this.presenter.fetchCondfig();
            this.presenter.handleCardSaving();
            if (!BaseUtils.isDeviceHaveCorrectTlsVersion()) {
                AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_TLS_ERROR);
                destroy(6, "TLSv1  is not supported for security reasons");
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        AnalyticsUtil.trackEvent(AnalyticsEvent.ACTIVITY_ONDESTROY_CALLED);
        this.presenter.cleanUpOnDestroy();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.presenter.onRequestPermissionsResult(i, strArr, iArr);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.presenter.saveInstanceState(bundle);
    }

    public void postSms(String str, String str2) {
    }

    public void requestSmsPermission() {
    }

    public void setSmsPermission(boolean z) {
        this.presenter.sendOtpPermissionCallback(z);
    }

    public void showProgressBar(int i) {
        B$$J$ b$$j$ = this.r$_Y_;
        if (b$$j$ != null) {
            b$$j$.G__G_(i);
        }
    }

    public void showToast(String str, int i) {
        Toast.makeText(this, str, i).show();
    }
}
