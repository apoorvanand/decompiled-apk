package com.razorpay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.facebook.share.internal.ShareConstants;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class Checkout extends Fragment {
    public static final int INCOMPATIBLE_PLUGIN = 7;
    public static final int INVALID_OPTIONS = 3;
    public static final int NETWORK_ERROR = 2;
    public static final int PAYMENT_CANCELED = 0;
    /* access modifiers changed from: private */
    public static boolean Q_$2$ = false;
    /* access modifiers changed from: private */
    public static long R$$r_ = 0;
    public static final int RZP_REQUEST_CODE = 62442;
    public static final int TLS_ERROR = 6;
    private static PaymentData Y$_o$;
    /* access modifiers changed from: private */
    @SuppressLint({"StaticFieldLeak"})
    public static WebView a_$P$;
    private static long d__1_;
    private Activity B$$W$;
    private JSONObject D$_X_;
    private String E$_j$;
    private String G__G_;
    private boolean O_$B_;
    private int b__J_;
    private boolean r$_Y_;

    public static class Builder {
        private int R$$r_;
        private boolean a_$P$;
        private String d__1_;

        public Checkout build() {
            Checkout checkout = new Checkout();
            checkout.setFullScreenDisable(this.a_$P$);
            checkout.setImage(this.R$$r_);
            String str = this.d__1_;
            if (str != null) {
                checkout.setKeyID(str);
            }
            return checkout;
        }

        public Builder disableFullscreen(boolean z) {
            this.a_$P$ = z;
            return this;
        }

        public Builder setImage(int i) {
            this.R$$r_ = i;
            return this;
        }

        public Builder setKeyId(String str) {
            this.d__1_ = str;
            return this;
        }
    }

    private static void G__G_(int i, int i2) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("integration_type", Integer.toString(i2));
            AnalyticsUtil.trackEvent(i == 1 ? AnalyticsEvent.MERCHANT_ON_SUCCESS_CALLED : AnalyticsEvent.MERCHANT_ON_ERROR_CALLED, (Map<String, Object>) hashMap);
            AnalyticsUtil.postData();
        } catch (Exception e) {
            AnalyticsUtil.reportError(e, "warning", e.getMessage());
        }
    }

    private static void G__G_(int i, String str) {
        try {
            AnalyticsUtil.addProperty("onActivityResult result", new AnalyticsProperty(str, AnalyticsProperty$R$$r_.ORDER));
            AnalyticsUtil.addProperty("onActivityResult resultCode", new AnalyticsProperty(String.valueOf(i), AnalyticsProperty$R$$r_.ORDER));
            AnalyticsUtil.trackEvent(i == 1 ? AnalyticsEvent.CALLING_ON_SUCCESS : i == 4 ? AnalyticsEvent.CALLING_EXTERNAL_WALLET_SELECTED : AnalyticsEvent.CALLING_ON_ERROR);
            AnalyticsUtil.postData();
        } catch (Exception e) {
            AnalyticsUtil.reportError(e, "warning", e.getMessage());
        }
    }

    private static void G__G_(Activity activity, int i, String str, Exception exc) {
        String str2;
        String str3;
        String str4;
        String str5;
        StringBuilder sb;
        if (i == 1) {
            str3 = "onPaymentSuccess";
            str2 = "success";
        } else if (i == 4) {
            str3 = "onExternalWalletSelected";
            str2 = "redirected";
        } else {
            str3 = "onPaymentError";
            str2 = "error";
        }
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("event_details", exc.getMessage());
            hashMap.put("event_type", exc.getMessage());
            hashMap.put("payment_status", str2);
            AnalyticsUtil.trackEvent(AnalyticsEvent.HANDOVER_ERROR, (Map<String, Object>) hashMap);
            AnalyticsUtil.postData();
        } catch (Exception e) {
            AnalyticsUtil.reportError(e, "error", e.getMessage());
        }
        if (!str.equals("dne")) {
            if (str.equals("threw_error")) {
                sb = new StringBuilder("Your ");
                sb.append(str3);
                str5 = " method is throwing an error. Wrap the entire code of the method inside a try catch.";
            }
            AnalyticsUtil.reportError(exc, "error", exc.getMessage());
        } else if (i == 4) {
            str4 = "Error: ExternalWalletListener probably not implemented in your activity";
            Toast.makeText(activity, str4, 0).show();
            AnalyticsUtil.reportError(exc, "error", exc.getMessage());
        } else {
            sb = new StringBuilder("Error: ");
            sb.append(str3);
            str5 = " probably not implemented in your activity";
        }
        sb.append(str5);
        str4 = sb.toString();
        Toast.makeText(activity, str4, 0).show();
        AnalyticsUtil.reportError(exc, "error", exc.getMessage());
    }

    private static void G__G_(Activity activity, String str) {
        PaymentData paymentData = new PaymentData();
        Y$_o$ = paymentData;
        paymentData.G__G_(Q__v$.G__G_(activity).getString("rzp_user_contact", (String) null));
        Y$_o$.Q_$2$(Q__v$.G__G_(activity).getString("rzp_user_email", (String) null));
        try {
            JSONObject jSONObject = new JSONObject(str);
            Y$_o$.Q_$2$(jSONObject);
            if (jSONObject.has("razorpay_payment_id")) {
                Y$_o$.R$$r_(jSONObject.getString("razorpay_payment_id"));
            }
            if (jSONObject.has("razorpay_order_id")) {
                Y$_o$.a_$P$(jSONObject.getString("razorpay_order_id"));
            }
            if (jSONObject.has("razorpay_signature")) {
                Y$_o$.d__1_(jSONObject.getString("razorpay_signature"));
            }
            if (jSONObject.has("external_wallet")) {
                Y$_o$.E$_j$(jSONObject.getString("external_wallet"));
            }
        } catch (JSONException e) {
            AnalyticsUtil.reportError(e, "error", e.getMessage());
        }
    }

    static /* synthetic */ void R$$r_() {
        try {
            a_$P$.stopLoading();
        } catch (Exception unused) {
        }
        a_$P$ = null;
    }

    private boolean R$$r_(int i, String str) {
        if (getActivity() instanceof PaymentResultListener) {
            try {
                ((PaymentResultListener) getActivity()).onPaymentError(i, str);
                G__G_(i, 3);
            } catch (Exception e) {
                G__G_(this.B$$W$, i, "threw_error", e);
            }
            return true;
        } else if (!(getActivity() instanceof PaymentResultWithDataListener)) {
            return false;
        } else {
            try {
                ((PaymentResultWithDataListener) getActivity()).onPaymentError(i, str, Y$_o$);
                G__G_(i, 3);
            } catch (Exception e2) {
                G__G_(this.B$$W$, i, "threw_error", e2);
            }
            return true;
        }
    }

    private boolean a_$P$(String str) {
        if (getActivity() instanceof PaymentResultListener) {
            try {
                ((PaymentResultListener) getActivity()).onPaymentSuccess(str);
                G__G_(1, 3);
            } catch (Exception e) {
                G__G_(this.B$$W$, 1, "threw_error", e);
            }
            return true;
        } else if (!(getActivity() instanceof PaymentResultWithDataListener)) {
            return false;
        } else {
            try {
                ((PaymentResultWithDataListener) getActivity()).onPaymentSuccess(str, Y$_o$);
                G__G_(1, 3);
            } catch (Exception e2) {
                G__G_(this.B$$W$, 1, "threw_error", e2);
            }
            return true;
        }
    }

    public static void clearUserData(Context context) {
        SharedPreferences.Editor Q_$2$2 = Q__v$.Q_$2$(context);
        Q_$2$2.putString("rzp_user_contact", (String) null);
        Q_$2$2.commit();
        SharedPreferences.Editor Q_$2$3 = Q__v$.Q_$2$(context);
        Q_$2$3.putString("rzp_user_email", (String) null);
        Q_$2$3.commit();
        Q__v$.d__1_(context).putString("rzp_device_token", (String) null).apply();
        CookieManager.getInstance().setCookie("https://api.razorpay.com", "razorpay_api_session=");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0008, code lost:
        r2 = r4.getExtras();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void handleActivityResult(android.app.Activity r1, int r2, int r3, android.content.Intent r4, com.razorpay.PaymentResultWithDataListener r5, com.razorpay.ExternalWalletListener r6) {
        /*
            r0 = 62442(0xf3ea, float:8.75E-41)
            if (r2 == r0) goto L_0x0006
            return
        L_0x0006:
            if (r4 == 0) goto L_0x0015
            android.os.Bundle r2 = r4.getExtras()
            if (r2 == 0) goto L_0x0015
            java.lang.String r4 = "RESULT"
            java.lang.String r2 = r2.getString(r4)
            goto L_0x0016
        L_0x0015:
            r2 = 0
        L_0x0016:
            if (r3 != 0) goto L_0x001a
            java.lang.String r2 = "Payment Cancelled"
        L_0x001a:
            G__G_((android.app.Activity) r1, (java.lang.String) r2)
            G__G_((int) r3, (java.lang.String) r2)
            r4 = 1
            if (r3 != r4) goto L_0x0036
            com.razorpay.PaymentData r2 = Y$_o$     // Catch:{ Exception -> 0x002f }
            java.lang.String r2 = r2.getPaymentId()     // Catch:{ Exception -> 0x002f }
            com.razorpay.PaymentData r4 = Y$_o$     // Catch:{ Exception -> 0x002f }
            r5.onPaymentSuccess(r2, r4)     // Catch:{ Exception -> 0x002f }
            return
        L_0x002f:
            r2 = move-exception
            java.lang.String r4 = "threw_error"
            G__G_(r1, r3, r4, r2)
            return
        L_0x0036:
            r4 = 4
            if (r3 != r4) goto L_0x0047
            if (r6 == 0) goto L_0x0053
            com.razorpay.PaymentData r2 = Y$_o$     // Catch:{ Exception -> 0x002f }
            java.lang.String r2 = r2.getExternalWallet()     // Catch:{ Exception -> 0x002f }
            com.razorpay.PaymentData r4 = Y$_o$     // Catch:{ Exception -> 0x002f }
            r6.onExternalWalletSelected(r2, r4)     // Catch:{ Exception -> 0x002f }
            return
        L_0x0047:
            com.razorpay.PaymentData r4 = Y$_o$     // Catch:{ Exception -> 0x004d }
            r5.onPaymentError(r3, r2, r4)     // Catch:{ Exception -> 0x004d }
            return
        L_0x004d:
            r2 = move-exception
            java.lang.String r4 = "threw_error"
            G__G_(r1, r3, r4, r2)
        L_0x0053:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.razorpay.Checkout.handleActivityResult(android.app.Activity, int, int, android.content.Intent, com.razorpay.PaymentResultWithDataListener, com.razorpay.ExternalWalletListener):void");
    }

    public static void preload(Context context) {
        R$$r_ = 0;
        d__1_ = 0;
        Q_$2$ = false;
        Context applicationContext = context.getApplicationContext();
        a_$P$ = new WebView(applicationContext);
        BaseUtils.setWebViewSettings(applicationContext, a_$P$, false);
        a_$P$.setWebViewClient(new WebViewClient() {
            private long R$$r_;

            public final void onPageFinished(WebView webView, String str) {
                long nanoTime = System.nanoTime();
                if (!Checkout.Q_$2$) {
                    long unused = Checkout.R$$r_ = nanoTime - this.R$$r_;
                    BaseUtils.nanoTimeToSecondsString(Checkout.R$$r_, 2);
                }
                Checkout.R$$r_();
            }

            public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                this.R$$r_ = System.nanoTime();
                if (Checkout.a_$P$ == null) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("error_location", "Checkout->Preload()->onPageStarted");
                    AnalyticsUtil.trackEvent(AnalyticsEvent.WEB_VIEW_UNEXPECTED_NULL, (Map<String, Object>) hashMap);
                    return;
                }
                Checkout.a_$P$.setTag(Long.valueOf(this.R$$r_));
            }

            public final void onReceivedError(WebView webView, int i, String str, String str2) {
                Checkout.Q_$2$ = true;
            }

            public final void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
                Checkout.Q_$2$ = true;
            }
        });
        a_$P$.setWebChromeClient(new WebChromeClient() {
            public final void onProgressChanged(WebView webView, int i) {
            }
        });
        a_$P$.loadUrl("https://api.razorpay.com/v1/checkout/public");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001a, code lost:
        r2 = r4.getExtras();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onActivityResult(int r2, int r3, android.content.Intent r4) {
        /*
            r1 = this;
            r0 = 62442(0xf3ea, float:8.75E-41)
            if (r2 == r0) goto L_0x0006
            return
        L_0x0006:
            android.app.Activity r2 = r1.getActivity()
            r1.B$$W$ = r2
            android.app.Activity r2 = r1.B$$W$
            java.lang.Class r2 = r2.getClass()
            java.lang.String r2 = r2.getName()
            r1.E$_j$ = r2
            if (r4 == 0) goto L_0x0027
            android.os.Bundle r2 = r4.getExtras()
            if (r2 == 0) goto L_0x0027
            java.lang.String r4 = "RESULT"
            java.lang.String r2 = r2.getString(r4)
            goto L_0x0028
        L_0x0027:
            r2 = 0
        L_0x0028:
            if (r3 != 0) goto L_0x002c
            java.lang.String r2 = "Payment Cancelled"
        L_0x002c:
            android.app.Activity r4 = r1.B$$W$
            G__G_((android.app.Activity) r4, (java.lang.String) r2)
            G__G_((int) r3, (java.lang.String) r2)
            r4 = 1
            if (r3 != r4) goto L_0x004c
            com.razorpay.PaymentData r2 = Y$_o$
            java.lang.String r2 = r2.getPaymentId()
            if (r2 == 0) goto L_0x0044
            r1.O_$B_ = r4
            r1.onSuccess(r2)
        L_0x0044:
            boolean r2 = r1.O_$B_
            if (r2 == 0) goto L_0x0099
            G__G_((int) r4, (int) r4)
            goto L_0x0099
        L_0x004c:
            r0 = 4
            if (r3 != r0) goto L_0x008d
            android.app.Activity r2 = r1.getActivity()
            boolean r2 = r2 instanceof com.razorpay.ExternalWalletListener
            if (r2 == 0) goto L_0x0080
            android.app.Activity r2 = r1.getActivity()     // Catch:{ Exception -> 0x0077 }
            com.razorpay.ExternalWalletListener r2 = (com.razorpay.ExternalWalletListener) r2     // Catch:{ Exception -> 0x0077 }
            com.razorpay.PaymentData r3 = Y$_o$     // Catch:{ Exception -> 0x0077 }
            java.lang.String r3 = r3.getExternalWallet()     // Catch:{ Exception -> 0x0077 }
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Exception -> 0x0077 }
            if (r4 != 0) goto L_0x0099
            com.razorpay.PaymentData r4 = Y$_o$     // Catch:{ Exception -> 0x0077 }
            r2.onExternalWalletSelected(r3, r4)     // Catch:{ Exception -> 0x0077 }
            com.razorpay.AnalyticsEvent r2 = com.razorpay.AnalyticsEvent.MERCHANT_EXTERNAL_WALLET_SELECTED_CALLED     // Catch:{ Exception -> 0x0077 }
            com.razorpay.AnalyticsUtil.trackEvent(r2)     // Catch:{ Exception -> 0x0077 }
            com.razorpay.AnalyticsUtil.postData()     // Catch:{ Exception -> 0x0077 }
            goto L_0x0099
        L_0x0077:
            r2 = move-exception
            android.app.Activity r3 = r1.B$$W$
            java.lang.String r4 = "threw_error"
            G__G_(r3, r0, r4, r2)
            goto L_0x0099
        L_0x0080:
            android.app.Activity r2 = r1.B$$W$
            java.lang.String r3 = "dne"
            java.lang.Exception r4 = new java.lang.Exception
            r4.<init>()
            G__G_(r2, r0, r3, r4)
            goto L_0x0099
        L_0x008d:
            r1.O_$B_ = r4
            r1.onError(r3, r2)
            boolean r2 = r1.O_$B_
            if (r2 == 0) goto L_0x0099
            G__G_((int) r3, (int) r4)
        L_0x0099:
            android.app.Activity r2 = r1.getActivity()     // Catch:{ Exception -> 0x00ad }
            android.app.FragmentManager r2 = r2.getFragmentManager()     // Catch:{ Exception -> 0x00ad }
            android.app.FragmentTransaction r2 = r2.beginTransaction()     // Catch:{ Exception -> 0x00ad }
            android.app.FragmentTransaction r2 = r2.remove(r1)     // Catch:{ Exception -> 0x00ad }
            r2.commit()     // Catch:{ Exception -> 0x00ad }
            return
        L_0x00ad:
            r2 = move-exception
            java.lang.String r3 = "error"
            java.lang.String r4 = r2.getMessage()
            com.razorpay.AnalyticsUtil.reportError(r2, r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.razorpay.Checkout.onActivityResult(int, int, android.content.Intent):void");
    }

    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        if (this.D$_X_ != null) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(getActivity().getPackageName(), "com.razorpay.CheckoutActivity"));
            long j = R$$r_;
            if (j > 0) {
                str = "PRELOAD_COMPLETE_DURATION";
            } else {
                j = d__1_;
                if (j > 0) {
                    str = "PRELOAD_ABORT_DURATION";
                }
                intent.putExtra("OPTIONS", this.D$_X_.toString());
                intent.putExtra(ShareConstants.IMAGE_URL, this.b__J_);
                intent.putExtra("DISABLE_FULL_SCREEN", this.r$_Y_);
                this.D$_X_ = null;
                startActivityForResult(intent, RZP_REQUEST_CODE);
            }
            intent.putExtra(str, j);
            intent.putExtra("OPTIONS", this.D$_X_.toString());
            intent.putExtra(ShareConstants.IMAGE_URL, this.b__J_);
            intent.putExtra("DISABLE_FULL_SCREEN", this.r$_Y_);
            this.D$_X_ = null;
            startActivityForResult(intent, RZP_REQUEST_CODE);
        }
    }

    public void onError(int i, String str) {
        this.O_$B_ = false;
        if (!R$$r_(i, str)) {
            Method method = null;
            try {
                method = Class.forName(this.E$_j$).getMethod("onPaymentError", new Class[]{Integer.TYPE, String.class});
            } catch (Exception e) {
                G__G_(this.B$$W$, i, "dne", e);
            }
            try {
                Object[] objArr = {Integer.valueOf(i), str};
                if (method != null) {
                    method.invoke(this.B$$W$, objArr);
                }
                G__G_(i, 2);
            } catch (Exception e2) {
                G__G_(this.B$$W$, i, "threw_error", e2);
            }
        }
    }

    public void onSuccess(String str) {
        this.O_$B_ = false;
        if (!a_$P$(str)) {
            Method method = null;
            try {
                method = Class.forName(this.E$_j$).getMethod("onPaymentSuccess", new Class[]{String.class});
            } catch (Exception e) {
                G__G_(this.B$$W$, 1, "dne", e);
            }
            try {
                Object[] objArr = {str};
                if (method != null) {
                    method.invoke(this.B$$W$, objArr);
                }
                G__G_(1, 2);
            } catch (Exception e2) {
                G__G_(this.B$$W$, 1, "threw_error", e2);
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(10:8|(2:9|10)|13|14|15|(1:17)|18|19|20|22) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x004b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void open(android.app.Activity r5, org.json.JSONObject r6) {
        /*
            r4 = this;
            java.lang.String r0 = r4.G__G_
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x000e
            java.lang.String r0 = com.razorpay.BaseUtils.getKeyId(r5)
            r4.G__G_ = r0
        L_0x000e:
            java.lang.String r0 = r4.G__G_
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x0079
            if (r6 == 0) goto L_0x0071
            int r0 = r6.length()
            if (r0 == 0) goto L_0x0071
            java.lang.String r0 = "key"
            java.lang.String r1 = r4.G__G_     // Catch:{ JSONException -> 0x0026 }
            r6.put(r0, r1)     // Catch:{ JSONException -> 0x0026 }
            goto L_0x0030
        L_0x0026:
            r0 = move-exception
            java.lang.String r1 = "warning"
            java.lang.String r2 = r0.getMessage()
            com.razorpay.AnalyticsUtil.reportError(r0, r1, r2)
        L_0x0030:
            long r0 = java.lang.System.nanoTime()
            android.webkit.WebView r2 = a_$P$     // Catch:{ Exception -> 0x004b }
            if (r2 == 0) goto L_0x004b
            android.webkit.WebView r2 = a_$P$     // Catch:{ Exception -> 0x004b }
            java.lang.Object r2 = r2.getTag()     // Catch:{ Exception -> 0x004b }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ Exception -> 0x004b }
            long r2 = r2.longValue()     // Catch:{ Exception -> 0x004b }
            long r0 = r0 - r2
            d__1_ = r0     // Catch:{ Exception -> 0x004b }
            r2 = 2
            com.razorpay.BaseUtils.nanoTimeToSecondsString(r0, r2)     // Catch:{ Exception -> 0x004b }
        L_0x004b:
            android.webkit.WebView r0 = a_$P$     // Catch:{ Exception -> 0x0050 }
            r0.stopLoading()     // Catch:{ Exception -> 0x0050 }
        L_0x0050:
            r0 = 0
            a_$P$ = r0
            r4.D$_X_ = r6
            java.lang.Class r6 = r5.getClass()
            java.lang.String r6 = r6.getName()
            r4.E$_j$ = r6
            r4.B$$W$ = r5
            android.app.FragmentManager r5 = r5.getFragmentManager()
            android.app.FragmentTransaction r5 = r5.beginTransaction()
            android.app.FragmentTransaction r5 = r5.add(r4, r0)
            r5.commit()
            return
        L_0x0071:
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            java.lang.String r6 = "Checkout options cannot be null or empty"
            r5.<init>(r6)
            throw r5
        L_0x0079:
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            java.lang.String r6 = "Please set your Razorpay API key in AndroidManifest.xml"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.razorpay.Checkout.open(android.app.Activity, org.json.JSONObject):void");
    }

    public final void setFullScreenDisable(boolean z) {
        this.r$_Y_ = z;
    }

    public final void setImage(int i) {
        this.b__J_ = i;
    }

    public final void setKeyID(String str) {
        setPublicKey(str);
    }

    @Deprecated
    public final void setPublicKey(String str) {
        this.G__G_ = str;
    }
}
