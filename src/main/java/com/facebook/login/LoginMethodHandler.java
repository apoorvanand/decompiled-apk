package com.facebook.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookException;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.Utility;
import com.facebook.login.LoginClient;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

abstract class LoginMethodHandler implements Parcelable {
    Map<String, String> a;
    protected LoginClient b;

    LoginMethodHandler(Parcel parcel) {
        this.a = Utility.readStringMapFromParcel(parcel);
    }

    LoginMethodHandler(LoginClient loginClient) {
        this.b = loginClient;
    }

    static AccessToken a(Bundle bundle, AccessTokenSource accessTokenSource, String str) {
        Bundle bundle2 = bundle;
        Date bundleLongAsDate = Utility.getBundleLongAsDate(bundle, NativeProtocol.EXTRA_EXPIRES_SECONDS_SINCE_EPOCH, new Date(0));
        ArrayList<String> stringArrayList = bundle.getStringArrayList(NativeProtocol.EXTRA_PERMISSIONS);
        String string = bundle.getString(NativeProtocol.EXTRA_ACCESS_TOKEN);
        Date bundleLongAsDate2 = Utility.getBundleLongAsDate(bundle, NativeProtocol.EXTRA_DATA_ACCESS_EXPIRATION_TIME, new Date(0));
        if (Utility.isNullOrEmpty(string)) {
            return null;
        }
        return new AccessToken(string, str, bundle.getString(NativeProtocol.EXTRA_USER_ID), stringArrayList, (Collection<String>) null, accessTokenSource, bundleLongAsDate, new Date(), bundleLongAsDate2);
    }

    static String c(String str) {
        if (str == null || str.isEmpty()) {
            throw new FacebookException("Authorization response does not contain the signed_request");
        }
        try {
            String[] split = str.split("\\.");
            if (split.length == 2) {
                return new JSONObject(new String(Base64.decode(split[1], 0), "UTF-8")).getString(AccessToken.USER_ID_KEY);
            }
        } catch (UnsupportedEncodingException | JSONException unused) {
        }
        throw new FacebookException("Failed to retrieve user_id from signed_request");
    }

    public static AccessToken createAccessTokenFromWebBundle(Collection<String> collection, Bundle bundle, AccessTokenSource accessTokenSource, String str) {
        Date bundleLongAsDate = Utility.getBundleLongAsDate(bundle, AccessToken.EXPIRES_IN_KEY, new Date());
        String string = bundle.getString("access_token");
        Date bundleLongAsDate2 = Utility.getBundleLongAsDate(bundle, AccessToken.DATA_ACCESS_EXPIRATION_TIME, new Date(0));
        String string2 = bundle.getString("granted_scopes");
        if (!Utility.isNullOrEmpty(string2)) {
            collection = new ArrayList<>(Arrays.asList(string2.split(",")));
        }
        Collection<String> collection2 = collection;
        String string3 = bundle.getString("denied_scopes");
        ArrayList arrayList = !Utility.isNullOrEmpty(string3) ? new ArrayList(Arrays.asList(string3.split(","))) : null;
        if (Utility.isNullOrEmpty(string)) {
            return null;
        }
        return new AccessToken(string, str, c(bundle.getString("signed_request")), collection2, arrayList, accessTokenSource, bundleLongAsDate, new Date(), bundleLongAsDate2);
    }

    /* access modifiers changed from: package-private */
    public abstract String a();

    /* access modifiers changed from: protected */
    public String a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("0_auth_logger_id", str);
            jSONObject.put("3_method", a());
            a(jSONObject);
        } catch (JSONException e) {
            Log.w("LoginMethodHandler", "Error creating client state json: " + e.getMessage());
        }
        return jSONObject.toString();
    }

    /* access modifiers changed from: package-private */
    public void a(LoginClient loginClient) {
        if (this.b == null) {
            this.b = loginClient;
            return;
        }
        throw new FacebookException("Can't set LoginClient if it is already set.");
    }

    /* access modifiers changed from: protected */
    public void a(String str, Object obj) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(str, obj == null ? null : obj.toString());
    }

    /* access modifiers changed from: package-private */
    public void a(JSONObject jSONObject) {
    }

    /* access modifiers changed from: package-private */
    public boolean a(int i, int i2, Intent intent) {
        return false;
    }

    /* access modifiers changed from: package-private */
    public abstract boolean a(LoginClient.Request request);

    /* access modifiers changed from: package-private */
    public void b() {
    }

    /* access modifiers changed from: protected */
    public void b(String str) {
        String d = this.b.getPendingRequest().d();
        AppEventsLogger newLogger = AppEventsLogger.newLogger((Context) this.b.a(), d);
        Bundle bundle = new Bundle();
        bundle.putString(AnalyticsEvents.PARAMETER_WEB_LOGIN_E2E, str);
        bundle.putLong(AnalyticsEvents.PARAMETER_WEB_LOGIN_SWITCHBACK_TIME, System.currentTimeMillis());
        bundle.putString("app_id", d);
        newLogger.logSdkEvent(AnalyticsEvents.EVENT_WEB_LOGIN_COMPLETE, (Double) null, bundle);
    }

    /* access modifiers changed from: package-private */
    public boolean d() {
        return false;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Utility.writeStringMapToParcel(parcel, this.a);
    }
}
