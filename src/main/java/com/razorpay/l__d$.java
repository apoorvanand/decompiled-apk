package com.razorpay;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.WindowManager;
import android.webkit.WebView;
import com.facebook.internal.ServerProtocol;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.facebook.share.internal.ShareConstants;
import com.google.common.net.HttpHeaders;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.razorpay.g__v_;
import com.tekartik.sqflite.Constant;
import java.lang.Thread;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import org.json.JSONException;
import org.json.JSONObject;

class l__d$ implements L__R$, Y$_o$, Runnable {
    private boolean B$$W$;
    private boolean B_$q$ = false;
    private int D$_X_ = 0;
    private boolean E$_6$ = false;
    /* access modifiers changed from: private */
    public String E$_j$;
    private JSONObject G__G_;
    private int J$_0_ = 0;
    private Queue<String> L__R$ = new LinkedList();
    /* access modifiers changed from: private */
    public boolean O_$B_ = false;
    J$_0_ Q_$2$;
    String R$$r_;
    private long Y$_o$;
    private String a_$P$;
    protected Activity activity;
    private String b__J_ = "{}";
    private long c__C_;
    private String d__1_;
    private boolean f$_G$ = false;
    private B$$W$ g__v_ = null;
    private long l_$w$;
    private String l__d$ = null;
    private boolean r$_Y_ = false;
    protected l__d$$Q_$2$ view;

    public l__d$(Activity activity2, l__d$$Q_$2$ l__d__q__2_) {
        this.activity = activity2;
        this.view = l__d__q__2_;
    }

    /* access modifiers changed from: private */
    public void a_$P$() {
        if (this.l__d$ != null && !this.r$_Y_) {
            try {
                String constructBasicAuth = BaseUtils.constructBasicAuth(this.R$$r_);
                HashMap hashMap = new HashMap();
                hashMap.put(HttpHeaders.AUTHORIZATION, "Basic ".concat(String.valueOf(constructBasicAuth)));
                StringBuilder sb = new StringBuilder("https://api.razorpay.com/v1/payments/");
                sb.append(this.l__d$);
                sb.append("/cancel?platform=android_sdk");
                b_$A$.a_$P$(sb.toString(), hashMap, new Callback() {
                    public final void run(t_$J_ t__j_) {
                    }
                });
                this.l__d$ = null;
            } catch (Exception unused) {
            }
        }
    }

    static /* synthetic */ void d__1_(l__d$ l__d_, String str) {
        int i = l__d_.D$_X_;
        int B_$q$2 = f$_G$.f$_G$().B_$q$();
        boolean z = true;
        if (!f$_G$.f$_G$().K$$z$() || (B_$q$2 != -1 && B_$q$2 <= i)) {
            z = false;
        }
        if (z) {
            l__d_.helpersReset();
            l__d_.loadForm(str);
            return;
        }
        l__d_.destroyActivity(0, "BackPressed");
    }

    /* access modifiers changed from: protected */
    public void addAnalyticsData(JSONObject jSONObject) {
        AnalyticsUtil.addFilteredPropertiesFromPayload(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void addOnFlowEnd() {
        B$$W$ b$$w$ = this.g__v_;
        if (b$$w$ != null) {
            b$$w$.G__G_();
        }
    }

    public void backPressed(final Map<String, Object> map) {
        AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_HARD_BACK_PRESSED, map);
        if (this.view.getWebView(1).getTag().toString().contains(f$_G$.f$_G$().Y$_o$()) && !this.view.isWebViewVisible(2)) {
            this.view.loadUrl(1, "javascript: window.backPressed ? window.backPressed('onCheckoutBackPress') : CheckoutBridge.onCheckoutBackPress();");
            map.put("in_checkout", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        } else if (!f$_G$.f$_G$().Q$$U_()) {
            destroyActivity(0, "BackPressed");
        } else {
            g__v_.Q_$2$(this.activity, f$_G$.f$_G$().C__D$(), f$_G$.f$_G$().r_$Z$(), f$_G$.f$_G$().I$_e_(), new g__v_.d__1_() {
                public final void a_$P$() {
                    AnalyticsUtil.trackEvent(AnalyticsEvent.ALERT_PAYMENT_CONTINUE, (Map<String, Object>) map);
                }

                public final void d__1_() {
                    AnalyticsUtil.trackEvent(AnalyticsEvent.ALERT_PAYMENT_CANCELLED, (Map<String, Object>) map);
                    if (l__d$.this.O_$B_) {
                        l__d$.this.view.makeWebViewVisible(1);
                        l__d$.this.view.loadUrl(2, "about:blank");
                        l__d$.this.view.loadUrl(1, "javascript: window.onpaymentcancel()");
                    } else {
                        l__d$.d__1_(l__d$.this, "");
                        l__d$.this.a_$P$();
                    }
                    boolean unused = l__d$.this.O_$B_ = false;
                }
            });
        }
    }

    public void callNativeIntent(String str, String str2) {
        BaseUtils.startActivityForResult(str, str2, this.activity);
        HashMap hashMap = new HashMap();
        if (str == null) {
            str = "null";
        }
        hashMap.put("url", str);
        if (str2 == null) {
            str2 = "null";
        }
        hashMap.put("package_name", str2);
        AnalyticsUtil.trackEvent(AnalyticsEvent.NATIVE_INTENT_CALLED, (Map<String, Object>) hashMap);
    }

    public void cleanUpOnDestroy() {
        a_$P$();
        addOnFlowEnd();
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler instanceof C__D$) {
            Thread.setDefaultUncaughtExceptionHandler(((C__D$) defaultUncaughtExceptionHandler).a_$P$);
        }
    }

    public void destroyActivity(int i, String str) {
        AnalyticsUtil.addProperty("destroy_resultCode", new AnalyticsProperty(String.valueOf(i), AnalyticsProperty$R$$r_.ORDER));
        AnalyticsUtil.addProperty("destroy_result", new AnalyticsProperty(str, AnalyticsProperty$R$$r_.ORDER));
        AnalyticsUtil.trackEvent(AnalyticsEvent.INTERNAL_DESTROY_METHOD_CALLED);
        this.view.destroy(i, str);
    }

    /* access modifiers changed from: protected */
    public void enableAddon(JSONObject jSONObject) {
        try {
            if (jSONObject.has("magic")) {
                this.E$_6$ = jSONObject.getBoolean("magic");
                if (this.g__v_ != null) {
                    this.g__v_.a_$P$(this.E$_6$);
                }
                AnalyticsUtil.addProperty("is_magic", new AnalyticsProperty(this.E$_6$, AnalyticsProperty$R$$r_.PAYMENT));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void fetchCondfig() {
        f$_G$.G__G_ = isMagicPresent();
        f$_G$.a_$P$(this.activity, this.R$$r_);
    }

    public J$_0_ getCheckoutOptions() {
        return this.Q_$2$;
    }

    /* access modifiers changed from: protected */
    public JSONObject getOptionsForHandleMessage() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constant.METHOD_OPTIONS, this.Q_$2$.Q_$2$());
            jSONObject.put("data", this.b__J_);
            jSONObject.put(ShareConstants.WEB_DIALOG_PARAM_ID, AnalyticsUtil.getLocalOrderId());
            jSONObject.put("key_id", this.R$$r_);
            jSONObject.put("upi_intents_data", g__v_.G__G_(this.activity));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("openedAt", System.currentTimeMillis());
            jSONObject.put("metadata", jSONObject2);
            String string = Q__v$.a_$P$(this.activity.getApplicationContext()).getString("rzp_device_token", (String) null);
            if (!TextUtils.isEmpty(string)) {
                jSONObject.put("device_token", string);
            }
            jSONObject.put("sdk_popup", true);
            jSONObject.put("magic", true);
            jSONObject.put("network_type", BaseUtils.getNetworkType(this.activity));
            jSONObject.put("activity_recreated", this.f$_G$);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public String getProgressBarColor() {
        String str = null;
        try {
            if (this.Q_$2$.Q_$2$() != null) {
                String string = this.Q_$2$.Q_$2$().getJSONObject("theme").getString("color");
                Color.parseColor(string);
                return string;
            }
            throw new Exception("No options defined");
        } catch (Exception e) {
            if (this.G__G_ != null) {
                str = this.G__G_.getJSONObject("theme").getString("color");
                Color.parseColor(str);
            } else {
                throw new Exception("No dash options defined");
            }
        } catch (Exception e2) {
            AnalyticsUtil.reportError(e2, "error", e2.getMessage());
        }
        AnalyticsUtil.reportError(e, "error", e.getMessage());
        return str;
    }

    public void handleCardSaving() {
        AnalyticsUtil.trackEvent(AnalyticsEvent.CARD_SAVING_START);
        l_$w$.a_$P$(this.activity.getApplicationContext());
    }

    /* access modifiers changed from: protected */
    public void helpersReset() {
    }

    public void invokePopup(final String str) {
        this.O_$B_ = true;
        try {
            this.activity.runOnUiThread(new Runnable() {
                public final void run() {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        l__d$.this.enableAddon(jSONObject);
                        if (jSONObject.has(FirebaseAnalytics.Param.CONTENT)) {
                            l__d$.this.view.loadDataWithBaseURL(2, "about:blank", jSONObject.getString(FirebaseAnalytics.Param.CONTENT), "text/html", "UTF-8", (String) null);
                        }
                        if (jSONObject.has("url")) {
                            l__d$.this.view.loadUrl(2, jSONObject.getString("url"));
                        }
                        if (!jSONObject.has("focus") || jSONObject.getBoolean("focus")) {
                            l__d$.this.view.makeWebViewVisible(2);
                        } else {
                            l__d$.this.view.makeWebViewVisible(1);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    AnalyticsUtil.addProperty("two_webview_flow", new AnalyticsProperty(true, AnalyticsProperty$R$$r_.PAYMENT));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isMagicPresent() {
        return false;
    }

    public void isWebViewSafe(final int i, final c__C_$Q_$2$ c__c__q__2_) {
        this.activity.runOnUiThread(new Runnable() {
            public final void run() {
                try {
                    String host = new URL((i == 1 ? l__d$.this.view.getWebView(1) : l__d$.this.view.getWebView(2)).getTag().toString()).getHost();
                    if (host != null && host.endsWith("razorpay.com")) {
                        c__c__q__2_.G__G_();
                    }
                } catch (Exception unused) {
                }
            }
        });
    }

    public void loadForm(String str) {
        if (this.D$_X_ != 0) {
            AnalyticsUtil.postData();
        }
        this.D$_X_++;
        AnalyticsUtil.addProperty("payment_attempt", new AnalyticsProperty(this.D$_X_, AnalyticsProperty$R$$r_.ORDER));
        this.B$$W$ = true;
        StringBuilder sb = new StringBuilder();
        sb.append(this.a_$P$);
        sb.append(str);
        String replace = sb.toString().replace(" ", "%20");
        String str2 = this.E$_j$;
        if (str2 == null || str2.isEmpty()) {
            this.view.loadUrl(1, replace);
        } else {
            this.view.loadDataWithBaseURL(1, replace, this.E$_j$, "text/html", "UTF-8", (String) null);
        }
    }

    public void onActivityResultReceived(int i, int i2, Intent intent) {
        if (i == 99) {
            JSONObject jSONFromIntentData = BaseUtils.getJSONFromIntentData(intent);
            String format = String.format("javascript: upiIntentResponse(%s)", new Object[]{jSONFromIntentData.toString()});
            HashMap hashMap = new HashMap();
            hashMap.put(Constant.PARAM_RESULT, jSONFromIntentData);
            AnalyticsUtil.trackEvent(AnalyticsEvent.NATIVE_INTENT_ONACTIVITY_RESULT, (Map<String, Object>) hashMap);
            if (this.B_$q$) {
                this.view.loadUrl(1, format);
                return;
            }
            if (this.L__R$ == null) {
                this.L__R$ = new LinkedList();
            }
            this.L__R$.add(format);
        }
    }

    public void onCheckoutBackPress() {
        AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_SOFT_BACK_PRESSED);
        destroyActivity(0, "Checkout BackPressed");
    }

    public void onComplete(final String str) {
        this.activity.runOnUiThread(new Runnable() {
            public final void run() {
                try {
                    l__d$.this.onComplete(new JSONObject(str));
                } catch (Exception e) {
                    AnalyticsUtil.reportError(e, "critical", e.getMessage());
                    l__d$.this.destroyActivity(0, e.getMessage());
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onComplete(JSONObject jSONObject) {
        try {
            if (jSONObject.has("error")) {
                AnalyticsUtil.addProperty("payment_status", new AnalyticsProperty("fail", AnalyticsProperty$R$$r_.PAYMENT));
                AnalyticsUtil.addProperty(MessengerShareContentUtility.ATTACHMENT_PAYLOAD, new AnalyticsProperty(jSONObject.toString(), AnalyticsProperty$R$$r_.PAYMENT));
                AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_PAYMENT_COMPLETE);
                if (this.O_$B_) {
                    this.view.makeWebViewVisible(1);
                }
                onError(jSONObject);
            } else if (jSONObject.has("razorpay_payment_id")) {
                String string = jSONObject.getString("razorpay_payment_id");
                this.l__d$ = string;
                AnalyticsUtil.addProperty("payment_id", new AnalyticsProperty(string, AnalyticsProperty$R$$r_.PAYMENT));
                AnalyticsUtil.addProperty("payment_status", new AnalyticsProperty("success", AnalyticsProperty$R$$r_.PAYMENT));
                AnalyticsUtil.addProperty(MessengerShareContentUtility.ATTACHMENT_PAYLOAD, new AnalyticsProperty(jSONObject.toString(), AnalyticsProperty$R$$r_.PAYMENT));
                AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_PAYMENT_COMPLETE);
                this.r$_Y_ = true;
                destroyActivity(1, jSONObject.toString());
            } else if (jSONObject.has("external_wallet")) {
                destroyActivity(4, jSONObject.toString());
            } else {
                destroyActivity(0, "Post payment parsing error");
            }
        } catch (Exception e) {
            AnalyticsUtil.reportError(e, "critical", e.getMessage());
            destroyActivity(0, e.getMessage());
        }
        this.O_$B_ = false;
    }

    public void onDismiss() {
        destroyActivity(0, "Payment Cancelled");
    }

    public void onError(String str) {
        try {
            onError(new JSONObject(str));
        } catch (Exception e) {
            AnalyticsUtil.reportError(e, "critical", e.getMessage());
            this.activity.runOnUiThread(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onError(JSONObject jSONObject) {
        StringBuilder sb;
        if (this.O_$B_) {
            this.view.loadUrl(1, String.format("javascript: window.onComplete(%s)", new Object[]{jSONObject.toString()}));
            return;
        }
        final String str = "";
        try {
            if (jSONObject.has("error")) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(this.a_$P$.contains("?") ? "&" : "?");
                str = sb2.toString();
                if (jSONObject.get("error") instanceof JSONObject) {
                    JSONObject jSONObject2 = (JSONObject) jSONObject.get("error");
                    if (jSONObject2.has("description")) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(str);
                        sb3.append("error.description=");
                        sb3.append(jSONObject2.get("description"));
                        str = sb3.toString();
                        if (jSONObject2.has("field")) {
                            sb = new StringBuilder();
                            sb.append(str);
                            sb.append("&error.field=");
                            sb.append(jSONObject2.get("field"));
                        }
                    } else {
                        sb = new StringBuilder();
                        sb.append(str);
                        sb.append("error=");
                        sb.append(jSONObject2.toString());
                    }
                    str = sb.toString();
                }
            }
        } catch (Exception e) {
            AnalyticsUtil.reportError(e, "critical", e.getMessage());
        } finally {
            this.activity.runOnUiThread(new Runnable() {
                public final void run() {
                    l__d$.d__1_(l__d$.this, str);
                }
            });
        }
    }

    public void onFault(String str) {
        destroyActivity(3, str);
    }

    public void onLoad() {
        this.activity.runOnUiThread(new Runnable() {
            public final void run() {
                if (l__d$.this.E$_j$ == null || l__d$.this.E$_j$.isEmpty()) {
                    l__d$.this.view.loadUrl(1, "javascript: CheckoutBridge.setCheckoutBody(document.documentElement.outerHTML)");
                }
                l__d$.this.view.loadUrl(1, String.format("javascript: handleMessage(%s)", new Object[]{l__d$.this.getOptionsForHandleMessage().toString()}));
                l__d$.this.view.loadUrl(1, String.format("javascript: CheckoutBridge.sendAnalyticsData({data: %s})", new Object[]{AnalyticsUtil.getAnalyticsDataForCheckout(l__d$.this.activity).toString()}));
            }
        });
    }

    public void onPageFinished(int i, WebView webView, String str) {
        switch (i) {
            case 1:
                primaryWebviewPageFinished(str, webView);
                return;
            case 2:
                B$$W$ b$$w$ = this.g__v_;
                if (b$$w$ != null && this.E$_6$) {
                    b$$w$.Q_$2$();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onPageStarted(int i, WebView webView, String str) {
        switch (i) {
            case 1:
                g__v_.R$$r_(this.activity);
                return;
            case 2:
                B$$W$ b$$w$ = this.g__v_;
                if (b$$w$ != null && this.E$_6$) {
                    b$$w$.R$$r_();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onProgressChanges(int i, int i2) {
        if (i == 1) {
            this.view.showProgressBar(i2);
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
    }

    public void onSubmit(String str) {
        if (this.D$_X_ > 1) {
            AnalyticsUtil.refreshPaymentSession();
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.b__J_ = str;
            addAnalyticsData(jSONObject);
            try {
                if (jSONObject.has("contact")) {
                    Activity activity2 = this.activity;
                    String string = jSONObject.getString("contact");
                    SharedPreferences.Editor Q_$2$2 = Q__v$.Q_$2$(activity2);
                    Q_$2$2.putString("rzp_user_contact", string);
                    Q_$2$2.commit();
                    this.Q_$2$.Q_$2$("contact", jSONObject.getString("contact"));
                }
                if (jSONObject.has("email")) {
                    Activity activity3 = this.activity;
                    String string2 = jSONObject.getString("email");
                    SharedPreferences.Editor Q_$2$3 = Q__v$.Q_$2$(activity3);
                    Q_$2$3.putString("rzp_user_email", string2);
                    Q_$2$3.commit();
                    this.Q_$2$.Q_$2$("email", jSONObject.getString("email"));
                }
            } catch (JSONException unused) {
            }
            String string3 = jSONObject.getString("method");
            if (!string3.equals("card") && string3.equals("wallet")) {
                String string4 = jSONObject.getString("wallet");
                if (this.Q_$2$.d__1_(string4)) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("external_wallet", string4);
                    AnalyticsUtil.addProperty("external_wallet", new AnalyticsProperty(string4, AnalyticsProperty$R$$r_.ORDER));
                    AnalyticsUtil.trackEvent(AnalyticsEvent.EXTERNAL_WALLET_SELECTED);
                    onComplete(jSONObject2);
                }
            }
            AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_SUBMIT);
            AnalyticsUtil.postData();
        } catch (Exception e) {
            AnalyticsUtil.reportError(e, "critical", e.getMessage());
        }
    }

    public void passPrefillToSegment() {
        String r$_Y_2 = this.Q_$2$.r$_Y_();
        if (!TextUtils.isEmpty(r$_Y_2)) {
            AnalyticsUtil.addProperty("email", new AnalyticsProperty(r$_Y_2, AnalyticsProperty$R$$r_.ORDER));
        }
        String D$_X_2 = this.Q_$2$.D$_X_();
        if (!TextUtils.isEmpty(D$_X_2)) {
            AnalyticsUtil.addProperty("contact", new AnalyticsProperty(D$_X_2, AnalyticsProperty$R$$r_.ORDER));
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0087  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void primaryWebviewPageFinished(java.lang.String r9, android.webkit.WebView r10) {
        /*
            r8 = this;
            long r0 = java.lang.System.nanoTime()
            com.razorpay.g__v_.d__1_()
            com.razorpay.l__d$$Q_$2$ r10 = r8.view
            r10.hideProgressBar()
            java.lang.String r10 = r8.a_$P$
            int r9 = r9.indexOf(r10)
            if (r9 != 0) goto L_0x00a4
            int r9 = r8.D$_X_
            r10 = 1
            if (r9 != r10) goto L_0x0098
            r8.B_$q$ = r10
            java.util.Queue<java.lang.String> r9 = r8.L__R$
            if (r9 == 0) goto L_0x0042
            boolean r9 = r9.isEmpty()
            if (r9 != 0) goto L_0x0042
            java.util.Queue<java.lang.String> r9 = r8.L__R$
            java.util.Iterator r9 = r9.iterator()
        L_0x002b:
            boolean r2 = r9.hasNext()
            if (r2 == 0) goto L_0x003d
            java.lang.Object r2 = r9.next()
            java.lang.String r2 = (java.lang.String) r2
            com.razorpay.l__d$$Q_$2$ r3 = r8.view
            r3.loadUrl(r10, r2)
            goto L_0x002b
        L_0x003d:
            java.util.Queue<java.lang.String> r9 = r8.L__R$
            r9.clear()
        L_0x0042:
            java.util.HashMap r9 = new java.util.HashMap
            r9.<init>()
            long r2 = r8.Y$_o$
            long r0 = r0 - r2
            java.lang.String r2 = "checkout_load_duration"
            java.lang.Long r3 = java.lang.Long.valueOf(r0)
            r9.put(r2, r3)
            r2 = 2
            com.razorpay.BaseUtils.nanoTimeToSecondsString(r0, r2)
            long r3 = r8.l_$w$
            r5 = 0
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x006e
            java.lang.String r7 = "preload_finish_duration"
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r9.put(r7, r3)
            long r3 = r8.l_$w$
        L_0x006a:
            com.razorpay.BaseUtils.nanoTimeToSecondsString(r3, r2)
            goto L_0x0080
        L_0x006e:
            long r3 = r8.c__C_
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x0080
            java.lang.String r7 = "preload_abort_duration"
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r9.put(r7, r3)
            long r3 = r8.c__C_
            goto L_0x006a
        L_0x0080:
            long r3 = r8.l_$w$
            long r3 = r3 - r0
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 <= 0) goto L_0x0093
            java.lang.String r0 = "time_shaved_off"
            java.lang.Long r1 = java.lang.Long.valueOf(r3)
            r9.put(r0, r1)
            com.razorpay.BaseUtils.nanoTimeToSecondsString(r3, r2)
        L_0x0093:
            com.razorpay.AnalyticsEvent r0 = com.razorpay.AnalyticsEvent.CHECKOUT_LOADED
            com.razorpay.AnalyticsUtil.trackEvent((com.razorpay.AnalyticsEvent) r0, (java.util.Map<java.lang.String, java.lang.Object>) r9)
        L_0x0098:
            boolean r9 = r8.B$$W$
            if (r9 != r10) goto L_0x00a4
            com.razorpay.l__d$$Q_$2$ r9 = r8.view
            r9.clearWebViewHistory(r10)
            r9 = 0
            r8.B$$W$ = r9
        L_0x00a4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.razorpay.l__d$.primaryWebviewPageFinished(java.lang.String, android.webkit.WebView):void");
    }

    public void relay(String str) {
    }

    public void requestExtraAnalyticsData() {
        final JSONObject extraAnalyticsPayload = AnalyticsUtil.getExtraAnalyticsPayload();
        this.activity.runOnUiThread(new Runnable() {
            public final void run() {
                try {
                    l__d$.this.view.loadUrl(1, String.format("javascript: CheckoutBridge.sendExtraAnalyticsData(%s)", new Object[]{extraAnalyticsPayload.toString()}));
                } catch (Exception unused) {
                }
            }
        });
    }

    public void requestOtpPermission() {
    }

    public void requestSmsPermission() {
        this.view.requestSmsPermission();
    }

    public void run() {
        helpersReset();
        loadForm("");
    }

    public void saveInstanceState(Bundle bundle) {
        if (this.J$_0_ != 0) {
            bundle.putString("OPTIONS", this.Q_$2$.b__J_());
            bundle.putInt(ShareConstants.IMAGE_URL, this.J$_0_);
        } else {
            bundle.putString("OPTIONS", this.Q_$2$.d__1_());
        }
        bundle.putString("DASH_OPTIONS", this.d__1_);
        bundle.putBoolean("DISABLE_FULL_SCREEN", this.activity.getIntent().getBooleanExtra("DISABLE_FULL_SCREEN", false));
    }

    public void sendDataToWebView(final int i, final String str) {
        this.activity.runOnUiThread(new Runnable() {
            public final void run() {
                switch (i) {
                    case 1:
                        l__d$.this.view.loadUrl(1, String.format("javascript: handleRelay(%s)", new Object[]{str}));
                        return;
                    case 2:
                        l__d$.this.view.loadUrl(2, String.format("javascript: Magic.handleRelay(%s)", new Object[]{str}));
                        return;
                    default:
                        return;
                }
            }
        });
    }

    public void sendOtpPermissionCallback(final boolean z) {
        this.activity.runOnUiThread(new Runnable() {
            public final void run() {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("granted", z);
                    l__d$.this.view.loadUrl(1, String.format("javascript: otpPermissionCallback(%s)", new Object[]{jSONObject.toString()}));
                } catch (Exception unused) {
                }
            }
        });
    }

    public void setAppToken(String str) {
        Q__v$.Q_$2$(this.activity).putString("rzp_app_token", str).apply();
    }

    public void setCheckoutBody(String str) {
        this.E$_j$ = str;
    }

    public void setCheckoutLoadStartAt() {
        this.Y$_o$ = System.nanoTime();
    }

    public void setDeviceToken(String str) {
        Q__v$.d__1_(this.activity).putString("rzp_device_token", str).apply();
    }

    public void setDimensions(final int i, final int i2) {
        if (this.activity.getResources().getBoolean(R.bool.isTablet)) {
            this.activity.runOnUiThread(new Runnable() {
                public final void run() {
                    WindowManager.LayoutParams attributes = l__d$.this.activity.getWindow().getAttributes();
                    Activity activity = l__d$.this.activity;
                    attributes.height = (int) TypedValue.applyDimension(1, (float) i2, activity.getResources().getDisplayMetrics());
                    Activity activity2 = l__d$.this.activity;
                    attributes.width = (int) TypedValue.applyDimension(1, (float) i, activity2.getResources().getDisplayMetrics());
                    l__d$.this.activity.getWindow().setAttributes(attributes);
                }
            });
        }
    }

    public void setMerchantOptions(String str) {
        this.d__1_ = str;
        try {
            this.G__G_ = new JSONObject(str);
        } catch (Exception e) {
            this.G__G_ = null;
            AnalyticsUtil.reportError(e, "critical", e.getMessage());
        }
        if (this.G__G_ == null) {
            g__v_.a_$P$(this.activity, this.R$$r_, (String) null);
        } else {
            g__v_.a_$P$(this.activity, this.R$$r_, str);
        }
    }

    public boolean setOptions(Bundle bundle, boolean z) {
        this.f$_G$ = z;
        if (bundle == null) {
            destroyActivity(0, this.activity.getResources().getString(R.string.activity_result_invalid_parameters));
            return false;
        }
        this.Q_$2$ = new J$_0_(bundle.getString("OPTIONS"));
        this.R$$r_ = this.Q_$2$.R$$r_();
        this.J$_0_ = bundle.getInt(ShareConstants.IMAGE_URL, 0);
        this.Q_$2$.R$$r_(this.activity, this.J$_0_);
        this.E$_j$ = bundle.getString("BODY");
        AnalyticsUtil.setup(this.activity, this.R$$r_, f$_G$.R$$r_, f$_G$.Q_$2$, f$_G$.a_$P$);
        J$_0_ j$_0_ = this.Q_$2$;
        String G__G_2 = g__v_.G__G_("https://api.razorpay.com/v1/checkout/public", ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, f$_G$.a_$P$);
        Map<String, String> Y_$B$ = f$_G$.f$_G$().Y_$B$();
        for (String next : Y_$B$.keySet()) {
            G__G_2 = g__v_.G__G_(G__G_2, next, Y_$B$.get(next));
        }
        Iterator<String> it = f$_G$.f$_G$().T__j$().iterator();
        while (it.hasNext()) {
            String next2 = it.next();
            if (j$_0_.Q_$2$(next2)) {
                G__G_2 = g__v_.G__G_(G__G_2, next2, (String) j$_0_.R$$r_(next2));
            }
        }
        this.a_$P$ = G__G_2;
        if (this.a_$P$ == null) {
            destroyActivity(3, this.activity.getResources().getString(R.string.activity_result_invalid_url));
        }
        try {
            this.G__G_ = new JSONObject(this.d__1_);
        } catch (Exception unused) {
        }
        if (!z) {
            this.Q_$2$.G__G_();
            this.d__1_ = Q__v$.G__G_(this.activity).getString("pref_merchant_options_".concat(String.valueOf(this.R$$r_)), (String) null);
            String string = bundle.getString("FRAMEWORK");
            if (string != null) {
                AnalyticsUtil.addProperty("framework", new AnalyticsProperty(string, AnalyticsProperty$R$$r_.ORDER));
            }
            if (bundle.getBoolean("DISABLE_FULL_SCREEN", false)) {
                Activity activity2 = this.activity;
                activity2.getWindow().addFlags(2048);
                activity2.getWindow().clearFlags(1024);
            }
            if (bundle.containsKey("PRELOAD_COMPLETE_DURATION")) {
                this.l_$w$ = bundle.getLong("PRELOAD_COMPLETE_DURATION");
            }
            if (bundle.containsKey("PRELOAD_ABORT_DURATION")) {
                this.c__C_ = bundle.getLong("PRELOAD_ABORT_DURATION");
            }
        } else {
            this.d__1_ = bundle.getString("DASH_OPTIONS");
            if (bundle.getBoolean("DISABLE_FULL_SCREEN", false)) {
                Activity activity3 = this.activity;
                activity3.getWindow().addFlags(2048);
                activity3.getWindow().clearFlags(1024);
            }
        }
        return true;
    }

    public void setPaymentID(String str) {
        this.l__d$ = str;
        AnalyticsUtil.addProperty("payment_id", new AnalyticsProperty(str, AnalyticsProperty$R$$r_.PAYMENT));
        AnalyticsUtil.trackEvent(AnalyticsEvent.PAYMENT_ID_ATTACHED);
    }

    public void setUpAddOn() {
        this.g__v_ = new B$$W$(this.activity, this.view.getWebView(2));
    }

    public void showAlertDialog(final String str, final String str2, final String str3) {
        this.activity.runOnUiThread(new Object() {
            public final void a_$P$() {
                l__d$.this.view.loadUrl(1, String.format("javascript: CheckoutBridge.isPositiveButtonClicked({isClicked: %s})", new Object[]{Boolean.TRUE}));
            }

            public final void d__1_() {
                l__d$.this.view.loadUrl(1, String.format("javascript: CheckoutBridge.isPositiveButtonClicked({isClicked: %s})", new Object[]{Boolean.FALSE}));
            }

            public final void run() {
                g__v_.Q_$2$(l__d$.this.activity, str, str3, str2, this);
            }
        });
    }

    public void toast(final String str, final int i) {
        this.activity.runOnUiThread(new Runnable() {
            public final void run() {
                l__d$.this.view.showToast(str, i);
            }
        });
    }
}
