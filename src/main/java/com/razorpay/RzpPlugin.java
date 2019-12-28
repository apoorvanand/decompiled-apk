package com.razorpay;

import android.app.Activity;
import android.content.Intent;
import org.json.JSONObject;
import proguard.annotation.Keep;
import proguard.annotation.KeepClassMembers;

@Keep
@KeepClassMembers
interface RzpPlugin {
    boolean doesHandlePayload(String str, JSONObject jSONObject, Activity activity);

    RzpPluginCompatibilityResponse isCompatible(String str, int i, String str2);

    void onActivityResult(String str, int i, int i2, Intent intent);

    void processPayment(String str, JSONObject jSONObject, Activity activity, RzpInternalCallback rzpInternalCallback);
}
