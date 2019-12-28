package com.razorpay;

import android.app.Activity;
import android.webkit.WebView;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

class r_$Z$ extends l__d$ {
    private RzpAssist G__G_;
    private RzpAssist a_$P$;
    private boolean d__1_ = true;

    public r_$Z$(Activity activity, l__d$$Q_$2$ l__d__q__2_) {
        super(activity, l__d__q__2_);
    }

    /* access modifiers changed from: protected */
    public void addAnalyticsData(JSONObject jSONObject) {
        try {
            if (this.a_$P$ != null) {
                this.a_$P$.a_$P$(jSONObject);
                jSONObject.put("razorpay_otp", this.a_$P$.G__G_());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        super.addAnalyticsData(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void addOnFlowEnd() {
        RzpAssist rzpAssist = this.a_$P$;
        if (rzpAssist != null) {
            rzpAssist.paymentFlowEnd();
        }
        RzpAssist rzpAssist2 = this.G__G_;
        if (rzpAssist2 != null) {
            rzpAssist2.paymentFlowEnd();
        }
        super.addOnFlowEnd();
    }

    public void backPressed(Map<String, Object> map) {
        RzpAssist rzpAssist = this.a_$P$;
        if (rzpAssist != null) {
            map.put("current_loading_url_primary_webview", rzpAssist.a_$P$());
            map.put("last_loaded_url_primary_webview", this.a_$P$.d__1_());
        }
        RzpAssist rzpAssist2 = this.G__G_;
        if (rzpAssist2 != null) {
            map.put("current_loading_url_secondary_webview", rzpAssist2.a_$P$());
            map.put("last_loaded_url_secondary_webview", this.G__G_.d__1_());
        }
        super.backPressed(map);
    }

    /* access modifiers changed from: protected */
    public void enableAddon(JSONObject jSONObject) {
        super.enableAddon(jSONObject);
        try {
            if (jSONObject.has("otpelf")) {
                this.d__1_ = jSONObject.getBoolean("otpelf");
                if (this.G__G_ != null) {
                    this.G__G_.Q_$2$(this.d__1_);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void helpersReset() {
        super.helpersReset();
        RzpAssist rzpAssist = this.a_$P$;
        if (rzpAssist != null) {
            rzpAssist.reset();
        }
        RzpAssist rzpAssist2 = this.G__G_;
        if (rzpAssist2 != null && this.d__1_) {
            rzpAssist2.reset();
        }
    }

    public void onPageFinished(int i, WebView webView, String str) {
        super.onPageFinished(i, webView, str);
        if (i == 2) {
            RzpAssist rzpAssist = this.G__G_;
            if (rzpAssist != null && this.d__1_) {
                rzpAssist.onPageFinished(webView, str);
            }
            if (this.view.isWebViewVisible(2)) {
                g__v_.d__1_();
            }
        }
    }

    public void onPageStarted(int i, WebView webView, String str) {
        super.onPageStarted(i, webView, str);
        switch (i) {
            case 1:
                RzpAssist rzpAssist = this.a_$P$;
                if (rzpAssist != null) {
                    rzpAssist.onPageStarted(webView, str);
                    return;
                }
                return;
            case 2:
                RzpAssist rzpAssist2 = this.G__G_;
                if (rzpAssist2 != null && this.d__1_) {
                    rzpAssist2.onPageStarted(webView, str);
                }
                if (this.view.isWebViewVisible(2)) {
                    g__v_.R$$r_(this.activity);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000a, code lost:
        if (r2.d__1_ != false) goto L_0x0011;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000f, code lost:
        if (r0 != null) goto L_0x0011;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0011, code lost:
        r0.onProgressChanged(r4);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onProgressChanges(int r3, int r4) {
        /*
            r2 = this;
            switch(r3) {
                case 1: goto L_0x000d;
                case 2: goto L_0x0004;
                default: goto L_0x0003;
            }
        L_0x0003:
            goto L_0x0014
        L_0x0004:
            com.razorpay.RzpAssist r0 = r2.G__G_
            if (r0 == 0) goto L_0x0014
            boolean r1 = r2.d__1_
            if (r1 == 0) goto L_0x0014
            goto L_0x0011
        L_0x000d:
            com.razorpay.RzpAssist r0 = r2.a_$P$
            if (r0 == 0) goto L_0x0014
        L_0x0011:
            r0.onProgressChanged(r4)
        L_0x0014:
            super.onProgressChanges(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.razorpay.r_$Z$.onProgressChanges(int, int):void");
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    /* access modifiers changed from: protected */
    public void primaryWebviewPageFinished(String str, WebView webView) {
        super.primaryWebviewPageFinished(str, webView);
        RzpAssist rzpAssist = this.a_$P$;
        if (rzpAssist != null) {
            rzpAssist.onPageFinished(webView, str);
        }
    }

    public void setPaymentID(String str) {
        RzpAssist rzpAssist = this.a_$P$;
        if (rzpAssist != null) {
            rzpAssist.Q_$2$(str);
        }
        super.setPaymentID(str);
    }

    public void setUpAddOn() {
        RzpAssist rzpAssist;
        this.a_$P$ = new RzpAssist(this.R$$r_, this.activity, this.view.getWebView(1), f$_G$.R$$r_, f$_G$.Q_$2$, f$_G$.a_$P$);
        this.a_$P$.Q_$2$(true);
        this.G__G_ = new RzpAssist(this.R$$r_, this.activity, this.view.getWebView(2), f$_G$.R$$r_, f$_G$.Q_$2$, f$_G$.a_$P$);
        this.G__G_.Q_$2$(true);
        if (!(this.Q_$2$.a_$P$() == null || (rzpAssist = this.a_$P$) == null)) {
            rzpAssist.setOtpElfPreferences(this.Q_$2$.a_$P$());
        }
        super.setUpAddOn();
    }
}
