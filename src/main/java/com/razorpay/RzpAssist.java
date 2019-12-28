package com.razorpay;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;
import com.facebook.internal.ServerProtocol;
import com.google.common.net.HttpHeaders;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public final class RzpAssist implements O_$v$ {
    private String B$$W$;
    /* access modifiers changed from: private */
    public Activity D$_X_;
    private JSONObject E$_6$ = new JSONObject();
    /* access modifiers changed from: private */
    public boolean E$_j$ = false;
    private boolean G__G_ = false;
    private int J$_0_;
    private String L__R$ = "standalone";
    private JSONObject O_$B_ = new JSONObject();
    private String Q_$2$ = "";
    private long R$$r_;
    private String Y$_o$;
    private String a_$P$ = "";
    private boolean b__J_ = false;
    /* access modifiers changed from: private */
    public boolean c__C_ = false;
    /* access modifiers changed from: private */
    public WebView d__1_;
    private String f$_G$;
    private boolean l_$w$ = false;
    private boolean l__d$ = false;
    private Object r$_Y_$7229b5a8;

    public RzpAssist(String str, Activity activity, WebView webView, String str2, int i, String str3) {
        Throwable cause;
        if (J$$A_.f$_G$().B$$W$().booleanValue()) {
            if (str == null || str.isEmpty()) {
                throw new RuntimeException("merchantKey cannot be null or empty");
            }
            this.L__R$ = str2;
            this.J$_0_ = i;
            if (str2.equals("standalone")) {
                AnalyticsUtil.setup(activity, str, str2, i, str3);
            }
            this.d__1_ = webView;
            this.B$$W$ = str;
            this.D$_X_ = activity;
            try {
                this.r$_Y_$7229b5a8 = ((Class) K$$z$.G__G_(15392, 18, 18)).getDeclaredConstructor(new Class[]{Activity.class}).newInstance(new Object[]{activity});
                try {
                    ((Class) K$$z$.G__G_(15392, 18, 18)).getDeclaredMethod("G__G_", (Class[]) null).invoke(this.r$_Y_$7229b5a8, (Object[]) null);
                    this.d__1_.addJavascriptInterface(this, "OTPElfBridge");
                    this.d__1_.getSettings().setUseWideViewPort(true);
                    AnalyticsUtil.addProperty("OTPElf Version", new AnalyticsProperty(BaseUtils.getLocalVersion(activity, (String) ((Class) K$$z$.G__G_(15392, 18, 18)).getField("R$$r_").get((Object) null)), AnalyticsProperty$R$$r_.ORDER));
                } catch (Throwable th) {
                    if (cause != null) {
                        throw cause;
                    }
                    throw th;
                }
            } finally {
                cause = th.getCause();
                if (cause != null) {
                    throw cause;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean G__G_() {
        return this.E$_j$;
    }

    /* access modifiers changed from: package-private */
    public final void Q_$2$(String str) {
        this.Y$_o$ = str;
    }

    /* access modifiers changed from: package-private */
    public final void Q_$2$(boolean z) {
        this.l_$w$ = z;
    }

    /* access modifiers changed from: package-private */
    public final String a_$P$() {
        return this.Q_$2$;
    }

    /* access modifiers changed from: package-private */
    public final void a_$P$(JSONObject jSONObject) {
        this.E$_6$ = jSONObject;
    }

    @JavascriptInterface
    public final void copyToClipboard(String str) {
        ((ClipboardManager) this.D$_X_.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("rzp_clip_data", str));
    }

    /* access modifiers changed from: package-private */
    public final String d__1_() {
        return this.a_$P$;
    }

    @JavascriptInterface
    public final void onOtpParsed(final String str) {
        this.D$_X_.runOnUiThread(new Runnable() {
            public final void run() {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    OTP otp = new OTP(jSONObject.getString("otp"), jSONObject.getString("sender"), jSONObject.getString("bank"));
                    HashMap hashMap = new HashMap();
                    hashMap.put("sender", otp.R$$r_);
                    if (otp.R$$r_.contains("RZRPAY")) {
                        boolean unused = RzpAssist.this.E$_j$ = true;
                        hashMap.put("razorpay_otp", Boolean.TRUE);
                    } else {
                        hashMap.put("razorpay_otp", Boolean.FALSE);
                        boolean unused2 = RzpAssist.this.c__C_ = true;
                        AnalyticsUtil.addProperty("payment_otp_received", new AnalyticsProperty(true, AnalyticsProperty$R$$r_.PAYMENT));
                    }
                    AnalyticsUtil.trackEvent(AnalyticsEvent.OTP_RECEIVED, (Map<String, Object>) hashMap);
                } catch (Exception unused3) {
                }
            }
        });
    }

    public final void onPageFinished(WebView webView, String str) {
        AnalyticsUtil.trackPageLoadEnd(str, System.nanoTime() - this.R$$r_);
        this.a_$P$ = str;
        this.Q_$2$ = "";
        if (J$$A_.f$_G$().B$$W$().booleanValue() && !this.l__d$) {
            try {
                JSONObject E$_j$2 = J$$A_.f$_G$().E$_j$();
                E$_j$2.put("merchant_key", this.B$$W$);
                E$_j$2.put("otp_permission", this.G__G_);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("type", this.L__R$);
                jSONObject.put("version_code", this.J$_0_);
                E$_j$2.put(ServerProtocol.DIALOG_PARAM_SDK_VERSION, jSONObject);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("type", "rzpassist");
                jSONObject2.put("version_code", B_$q$.R$$r_.intValue());
                E$_j$2.put("plugin", jSONObject2);
                E$_j$2.put("payment_data", this.E$_6$);
                E$_j$2.put("preferences", this.O_$B_);
                StringBuilder sb = new StringBuilder("window.__rzp_options = ");
                sb.append(E$_j$2.toString());
                String obj = sb.toString();
                this.d__1_.loadUrl(String.format("javascript: %s", new Object[]{obj}));
            } catch (Exception unused) {
            }
            try {
                Object invoke = ((Class) K$$z$.G__G_(15392, 18, 18)).getDeclaredMethod("R$$r_", (Class[]) null).invoke(this.r$_Y_$7229b5a8, (Object[]) null);
                this.d__1_.loadUrl(String.format("javascript: %s", new Object[]{invoke}));
                this.l__d$ = true;
            } catch (Throwable th) {
                Throwable cause = th.getCause();
                if (cause != null) {
                    throw cause;
                }
                throw th;
            }
        }
    }

    public final void onPageStarted(WebView webView, String str) {
        AnalyticsUtil.trackPageLoadStart(str);
        this.R$$r_ = System.nanoTime();
        this.Q_$2$ = str;
        this.l__d$ = false;
    }

    public final void onProgressChanged(int i) {
        if (!J$$A_.f$_G$().B$$W$().booleanValue()) {
        }
    }

    @JavascriptInterface
    public final void openKeyboard() {
        this.D$_X_.runOnUiThread(new Runnable() {
            public final void run() {
                ((InputMethodManager) RzpAssist.this.D$_X_.getSystemService("input_method")).showSoftInput(RzpAssist.this.d__1_, 0);
            }
        });
    }

    public final void paymentFlowEnd() {
        if (this.L__R$.equals("standalone")) {
            AnalyticsUtil.postData();
        }
        if (!J$$A_.f$_G$().B$$W$().booleanValue()) {
        }
    }

    public final void postSms(String str, String str2) {
        if (this.l_$w$) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("sender", str);
                jSONObject.put("message", str2);
                this.f$_G$ = jSONObject.toString();
                String format = String.format("OTPElf.elfBridge.setSms(%s)", new Object[]{this.f$_G$});
                this.d__1_.loadUrl(String.format("javascript: %s", new Object[]{format}));
            } catch (Exception unused) {
            }
        }
    }

    public final void reset() {
        try {
            String constructBasicAuth = BaseUtils.constructBasicAuth(this.B$$W$);
            HashMap hashMap = new HashMap();
            hashMap.put(HttpHeaders.AUTHORIZATION, "Basic ".concat(String.valueOf(constructBasicAuth)));
            hashMap.put(HttpHeaders.CONTENT_TYPE, "application/json");
            if (this.Y$_o$ != null) {
                StringBuilder sb = new StringBuilder("https://api.razorpay.com/v1/payments/");
                sb.append(this.Y$_o$);
                sb.append("/metadata");
                b_$A$.R$$r_(sb.toString(), B$$W$.G__G_(this.c__C_).toString(), hashMap, new Callback() {
                    public final void run(t_$J_ t__j_) {
                    }
                });
            }
        } catch (Exception e) {
            AnalyticsUtil.reportError(e, "critical", e.getMessage());
        }
        this.a_$P$ = "";
        this.Q_$2$ = "";
        this.c__C_ = false;
    }

    public final void setOtpElfPreferences(JSONObject jSONObject) {
        this.O_$B_ = jSONObject;
    }

    public final void setSmsPermission(boolean z) {
        this.G__G_ = z;
        AnalyticsUtil.addProperty("otp_autoreading_access", new AnalyticsProperty(z, AnalyticsProperty$R$$r_.ORDER));
    }

    @JavascriptInterface
    public final void setUseWideViewPort(final boolean z) {
        this.D$_X_.runOnUiThread(new Runnable() {
            public final void run() {
                RzpAssist.this.d__1_.getSettings().setUseWideViewPort(z);
            }
        });
    }

    @JavascriptInterface
    public final void toast(final String str) {
        this.D$_X_.runOnUiThread(new Runnable() {
            public final void run() {
                Toast.makeText(RzpAssist.this.D$_X_, str, 1).show();
            }
        });
    }

    @JavascriptInterface
    public final void trackEvent(String str) {
        AnalyticsEvent analyticsEvent = AnalyticsEvent.JS_EVENT;
        analyticsEvent.setEventName(str);
        AnalyticsUtil.trackEvent(analyticsEvent);
    }

    @JavascriptInterface
    public final void trackEvent(String str, String str2) {
        try {
            AnalyticsEvent analyticsEvent = AnalyticsEvent.JS_EVENT;
            analyticsEvent.setEventName(str);
            AnalyticsUtil.trackEvent(analyticsEvent, new JSONObject(str2));
        } catch (Exception unused) {
        }
    }
}
