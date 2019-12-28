package com.razorpay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import com.tekartik.sqflite.Constant;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class PluginOtpElfCheckoutPresenterImpl extends r_$Z$ implements PluginCheckoutInteractor {
    private final RzpInternalCallback E$_j$ = new RzpInternalCallback() {
        public final void onPaymentError(int i, String str) {
            HashMap hashMap = new HashMap();
            hashMap.put("response", str);
            hashMap.put(Constant.PARAM_ERROR_CODE, Integer.valueOf(i));
            AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_AMAZON_INTERNAL_CALLBACK_ERROR, (Map<String, Object>) hashMap);
            try {
                PluginOtpElfCheckoutPresenterImpl.this.onComplete(new JSONObject(str).toString());
            } catch (Exception unused) {
                AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_AMAZON_INTERNAL_CALLBACK_ERROR_EXCEPTION);
                PluginOtpElfCheckoutPresenterImpl.this.onComplete(str);
            }
        }

        public final void onPaymentSuccess(String str) {
            HashMap hashMap = new HashMap();
            hashMap.put("response", str);
            AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_AMAZON_INTERNAL_CALLBACK_SUCCESS, (Map<String, Object>) hashMap);
            PluginOtpElfCheckoutPresenterImpl.this.onComplete(str);
        }
    };
    private HashMap<String, String> G__G_;
    private boolean a_$P$ = false;
    private RzpPlugin d__1_;

    public PluginOtpElfCheckoutPresenterImpl(Activity activity, l__d$$Q_$2$ l__d__q__2_, HashMap<String, String> hashMap) {
        super(activity, l__d__q__2_);
        this.G__G_ = hashMap;
    }

    public /* bridge */ /* synthetic */ void backPressed(Map map) {
        super.backPressed(map);
    }

    public /* bridge */ /* synthetic */ void callNativeIntent(String str, String str2) {
        super.callNativeIntent(str, str2);
    }

    public /* bridge */ /* synthetic */ void cleanUpOnDestroy() {
        super.cleanUpOnDestroy();
    }

    public /* bridge */ /* synthetic */ void destroyActivity(int i, String str) {
        super.destroyActivity(i, str);
    }

    public /* bridge */ /* synthetic */ void fetchCondfig() {
        super.fetchCondfig();
    }

    public /* bridge */ /* synthetic */ J$_0_ getCheckoutOptions() {
        return super.getCheckoutOptions();
    }

    /* access modifiers changed from: protected */
    public JSONObject getOptionsForHandleMessage() {
        JSONObject optionsForHandleMessage = super.getOptionsForHandleMessage();
        JSONObject jSONObject = new JSONObject();
        try {
            for (String next : this.G__G_.keySet()) {
                jSONObject.put(next.substring(20, next.length()), true);
            }
            optionsForHandleMessage.put("external_sdks", jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return optionsForHandleMessage;
    }

    public /* bridge */ /* synthetic */ String getProgressBarColor() {
        return super.getProgressBarColor();
    }

    public /* bridge */ /* synthetic */ void handleCardSaving() {
        super.handleCardSaving();
    }

    public /* bridge */ /* synthetic */ void invokePopup(String str) {
        super.invokePopup(str);
    }

    public /* bridge */ /* synthetic */ boolean isMagicPresent() {
        return super.isMagicPresent();
    }

    public /* bridge */ /* synthetic */ void isWebViewSafe(int i, c__C_$Q_$2$ c__c__q__2_) {
        super.isWebViewSafe(i, c__c__q__2_);
    }

    public /* bridge */ /* synthetic */ void loadForm(String str) {
        super.loadForm(str);
    }

    public void onActivityResultReceived(int i, int i2, Intent intent) {
        if (this.a_$P$) {
            this.d__1_.onActivityResult(this.R$$r_, i, i2, intent);
        } else {
            super.onActivityResultReceived(i, i2, intent);
        }
    }

    public /* bridge */ /* synthetic */ void onCheckoutBackPress() {
        super.onCheckoutBackPress();
    }

    public /* bridge */ /* synthetic */ void onComplete(String str) {
        super.onComplete(str);
    }

    public /* bridge */ /* synthetic */ void onDismiss() {
        super.onDismiss();
    }

    public /* bridge */ /* synthetic */ void onError(String str) {
        super.onError(str);
    }

    /* access modifiers changed from: protected */
    public void onError(JSONObject jSONObject) {
        AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_AMAZON_ON_ERROR_CALLED, jSONObject);
        if (this.a_$P$) {
            this.view.loadUrl(1, String.format("javascript: window.onComplete(%s)", new Object[]{jSONObject.toString()}));
            this.a_$P$ = false;
            return;
        }
        super.onError(jSONObject);
    }

    public /* bridge */ /* synthetic */ void onFault(String str) {
        super.onFault(str);
    }

    public /* bridge */ /* synthetic */ void onLoad() {
        super.onLoad();
    }

    public /* bridge */ /* synthetic */ void onPageFinished(int i, WebView webView, String str) {
        super.onPageFinished(i, webView, str);
    }

    public /* bridge */ /* synthetic */ void onPageStarted(int i, WebView webView, String str) {
        super.onPageStarted(i, webView, str);
    }

    public /* bridge */ /* synthetic */ void onProgressChanges(int i, int i2) {
        super.onProgressChanges(i, i2);
    }

    public /* bridge */ /* synthetic */ void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    public /* bridge */ /* synthetic */ void onSubmit(String str) {
        super.onSubmit(str);
    }

    public /* bridge */ /* synthetic */ void passPrefillToSegment() {
        super.passPrefillToSegment();
    }

    public void processPayment(String str) {
        HashMap<String, String> hashMap = this.G__G_;
        if (hashMap != null && hashMap.size() != 0) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                HashMap hashMap2 = new HashMap();
                hashMap2.put("data", str);
                AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_AMAZON_RAZORPAY_AMAZON_CALLING_PROCESS_PAYMENT, (Map<String, Object>) hashMap2);
                for (String loadClass : this.G__G_.values()) {
                    try {
                        RzpPlugin rzpPlugin = (RzpPlugin) RzpPlugin.class.getClassLoader().loadClass(loadClass).newInstance();
                        if (rzpPlugin.doesHandlePayload(this.R$$r_, jSONObject, this.activity)) {
                            this.a_$P$ = true;
                            this.d__1_ = rzpPlugin;
                            rzpPlugin.processPayment(this.R$$r_, jSONObject, this.activity, this.E$_j$);
                            return;
                        }
                    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
                HashMap hashMap3 = new HashMap();
                hashMap3.put("data", str);
                AnalyticsUtil.trackEvent(AnalyticsEvent.CHECKOUT_AMAZON_RAZORPAY_AMAZON_CALLING_PROCESS_PAYMENT_EXCEPTION, (Map<String, Object>) hashMap3);
                e2.printStackTrace();
            }
        }
    }

    public /* bridge */ /* synthetic */ void relay(String str) {
        super.relay(str);
    }

    public /* bridge */ /* synthetic */ void requestExtraAnalyticsData() {
        super.requestExtraAnalyticsData();
    }

    public /* bridge */ /* synthetic */ void requestOtpPermission() {
        super.requestOtpPermission();
    }

    public /* bridge */ /* synthetic */ void requestSmsPermission() {
        super.requestSmsPermission();
    }

    public /* bridge */ /* synthetic */ void saveInstanceState(Bundle bundle) {
        super.saveInstanceState(bundle);
    }

    public /* bridge */ /* synthetic */ void sendDataToWebView(int i, String str) {
        super.sendDataToWebView(i, str);
    }

    public /* bridge */ /* synthetic */ void sendOtpPermissionCallback(boolean z) {
        super.sendOtpPermissionCallback(z);
    }

    public /* bridge */ /* synthetic */ void setAppToken(String str) {
        super.setAppToken(str);
    }

    public /* bridge */ /* synthetic */ void setCheckoutBody(String str) {
        super.setCheckoutBody(str);
    }

    public /* bridge */ /* synthetic */ void setCheckoutLoadStartAt() {
        super.setCheckoutLoadStartAt();
    }

    public /* bridge */ /* synthetic */ void setDeviceToken(String str) {
        super.setDeviceToken(str);
    }

    public /* bridge */ /* synthetic */ void setDimensions(int i, int i2) {
        super.setDimensions(i, i2);
    }

    public /* bridge */ /* synthetic */ void setMerchantOptions(String str) {
        super.setMerchantOptions(str);
    }

    public /* bridge */ /* synthetic */ boolean setOptions(Bundle bundle, boolean z) {
        return super.setOptions(bundle, z);
    }

    public /* bridge */ /* synthetic */ void setPaymentID(String str) {
        super.setPaymentID(str);
    }

    public /* bridge */ /* synthetic */ void setUpAddOn() {
        super.setUpAddOn();
    }

    public /* bridge */ /* synthetic */ void showAlertDialog(String str, String str2, String str3) {
        super.showAlertDialog(str, str2, str3);
    }

    public /* bridge */ /* synthetic */ void toast(String str, int i) {
        super.toast(str, i);
    }
}
