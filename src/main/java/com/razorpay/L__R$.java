package com.razorpay;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import java.util.Map;

interface L__R$ {
    void backPressed(Map<String, Object> map);

    void cleanUpOnDestroy();

    void destroyActivity(int i, String str);

    void fetchCondfig();

    String getProgressBarColor();

    void handleCardSaving();

    void loadForm(String str);

    void onActivityResultReceived(int i, int i2, Intent intent);

    void onPageFinished(int i, WebView webView, String str);

    void onPageStarted(int i, WebView webView, String str);

    void onProgressChanges(int i, int i2);

    void onRequestPermissionsResult(int i, String[] strArr, int[] iArr);

    void passPrefillToSegment();

    void saveInstanceState(Bundle bundle);

    void sendOtpPermissionCallback(boolean z);

    void setCheckoutLoadStartAt();

    boolean setOptions(Bundle bundle, boolean z);

    void setUpAddOn();
}
