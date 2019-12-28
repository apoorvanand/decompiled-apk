package com.razorpay;

import android.app.Activity;
import android.text.TextUtils;
import com.facebook.share.internal.MessengerShareContentUtility;
import org.json.JSONException;
import org.json.JSONObject;

final class J$_0_ {
    private JSONObject a_$P$;

    J$_0_(String str) {
        try {
            this.a_$P$ = new JSONObject(str);
        } catch (JSONException e) {
            AnalyticsUtil.reportError(e, "critical", e.getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    public final String D$_X_() {
        if (this.a_$P$.optJSONObject("prefill") == null) {
            return null;
        }
        return this.a_$P$.optJSONObject("prefill").optString("contact");
    }

    /* access modifiers changed from: package-private */
    public final void G__G_() {
        try {
            JSONObject jSONObject = new JSONObject(this.a_$P$.toString());
            if (jSONObject.has("prefill")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("prefill");
                jSONObject2.remove("card");
                jSONObject2.remove("card[number]");
                jSONObject2.remove("card[expiry]");
                jSONObject2.remove("card[cvv]");
                jSONObject.put("prefill", jSONObject2);
            }
            jSONObject.remove(MessengerShareContentUtility.MEDIA_IMAGE);
            AnalyticsUtil.addProperty("merchant options", new AnalyticsProperty(jSONObject, AnalyticsProperty$R$$r_.ORDER));
        } catch (Exception e) {
            AnalyticsUtil.reportError(e, "warning", e.getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    public final JSONObject Q_$2$() {
        return this.a_$P$;
    }

    /* access modifiers changed from: package-private */
    public final void Q_$2$(String str, Object obj) {
        JSONObject jSONObject = new JSONObject();
        if (this.a_$P$.optJSONObject("prefill") != null) {
            jSONObject = this.a_$P$.optJSONObject("prefill");
        }
        try {
            jSONObject.put(str, obj);
        } catch (JSONException e) {
            AnalyticsUtil.reportError(e, "error", e.getMessage());
        }
        try {
            this.a_$P$.put("prefill", jSONObject);
        } catch (JSONException e2) {
            AnalyticsUtil.reportError(e2, "error", e2.getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean Q_$2$(String str) {
        return this.a_$P$.has(str);
    }

    /* access modifiers changed from: package-private */
    public final <T> T R$$r_(String str) {
        Object opt = this.a_$P$.opt(str);
        if (opt == null) {
            return null;
        }
        return opt.getClass().cast(opt);
    }

    /* access modifiers changed from: package-private */
    public final String R$$r_() {
        try {
            return this.a_$P$.getString("key");
        } catch (JSONException e) {
            AnalyticsUtil.reportError(e, "critical", e.getMessage());
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final void R$$r_(Activity activity, int i) {
        String base64FromResource;
        try {
            this.a_$P$.put("redirect", Boolean.TRUE);
        } catch (JSONException e) {
            AnalyticsUtil.reportError(e, "error", e.getMessage());
        }
        if (!(i == 0 || (base64FromResource = BaseUtils.getBase64FromResource(activity.getResources(), i)) == null)) {
            try {
                this.a_$P$.put(MessengerShareContentUtility.MEDIA_IMAGE, base64FromResource);
            } catch (JSONException e2) {
                AnalyticsUtil.reportError(e2, "error", e2.getMessage());
            }
        }
        String string = Q__v$.G__G_(activity).getString("rzp_user_email", (String) null);
        if (!TextUtils.isEmpty(string) && (this.a_$P$.optJSONObject("prefill") == null || !this.a_$P$.optJSONObject("prefill").has("email"))) {
            Q_$2$("email", string);
        }
        String string2 = Q__v$.G__G_(activity).getString("rzp_user_contact", (String) null);
        if (TextUtils.isEmpty(string2)) {
            return;
        }
        if (this.a_$P$.optJSONObject("prefill") == null || !this.a_$P$.optJSONObject("prefill").has("contact")) {
            Q_$2$("contact", string2);
        }
    }

    /* access modifiers changed from: package-private */
    public final JSONObject a_$P$() {
        return this.a_$P$.optJSONObject("otpelf_preferences");
    }

    /* access modifiers changed from: package-private */
    public final String b__J_() {
        try {
            this.a_$P$.put(MessengerShareContentUtility.MEDIA_IMAGE, (Object) null);
        } catch (JSONException e) {
            AnalyticsUtil.reportError(e, "error", e.getMessage());
        }
        return this.a_$P$.toString();
    }

    /* access modifiers changed from: package-private */
    public final String d__1_() {
        return this.a_$P$.toString();
    }

    public final boolean d__1_(String str) {
        try {
            return this.a_$P$.has("external") && this.a_$P$.getJSONObject("external").getJSONArray("wallets").toString().contains(str);
        } catch (Exception e) {
            AnalyticsUtil.reportError(e, "warning", e.getMessage());
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public final String r$_Y_() {
        if (this.a_$P$.optJSONObject("prefill") == null) {
            return null;
        }
        return this.a_$P$.optJSONObject("prefill").optString("contact");
    }
}
