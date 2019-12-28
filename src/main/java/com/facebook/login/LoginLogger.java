package com.facebook.login;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.login.LoginClient;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

class LoginLogger {
    private final AppEventsLogger appEventsLogger;
    private String applicationId;
    private String facebookVersion;

    LoginLogger(Context context, String str) {
        PackageInfo packageInfo;
        this.applicationId = str;
        this.appEventsLogger = AppEventsLogger.newLogger(context, str);
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null && (packageInfo = packageManager.getPackageInfo("com.facebook.katana", 0)) != null) {
                this.facebookVersion = packageInfo.versionName;
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    static Bundle a(String str) {
        Bundle bundle = new Bundle();
        bundle.putLong("1_timestamp_ms", System.currentTimeMillis());
        bundle.putString("0_auth_logger_id", str);
        bundle.putString("3_method", "");
        bundle.putString("2_result", "");
        bundle.putString("5_error_message", "");
        bundle.putString("4_error_code", "");
        bundle.putString("6_extras", "");
        return bundle;
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public void logAuthorizationMethodComplete(String str, String str2, String str3, String str4, String str5, Map<String, String> map) {
        Bundle a = a(str);
        if (str3 != null) {
            a.putString("2_result", str3);
        }
        if (str4 != null) {
            a.putString("5_error_message", str4);
        }
        if (str5 != null) {
            a.putString("4_error_code", str5);
        }
        if (map != null && !map.isEmpty()) {
            a.putString("6_extras", new JSONObject(map).toString());
        }
        a.putString("3_method", str2);
        this.appEventsLogger.logSdkEvent("fb_mobile_login_method_complete", (Double) null, a);
    }

    public void logAuthorizationMethodNotTried(String str, String str2) {
        Bundle a = a(str);
        a.putString("3_method", str2);
        this.appEventsLogger.logSdkEvent("fb_mobile_login_method_not_tried", (Double) null, a);
    }

    public void logAuthorizationMethodStart(String str, String str2) {
        Bundle a = a(str);
        a.putString("3_method", str2);
        this.appEventsLogger.logSdkEvent("fb_mobile_login_method_start", (Double) null, a);
    }

    public void logCompleteLogin(String str, Map<String, String> map, LoginClient.Result.Code code, Map<String, String> map2, Exception exc) {
        Bundle a = a(str);
        if (code != null) {
            a.putString("2_result", code.a());
        }
        if (!(exc == null || exc.getMessage() == null)) {
            a.putString("5_error_message", exc.getMessage());
        }
        JSONObject jSONObject = !map.isEmpty() ? new JSONObject(map) : null;
        if (map2 != null) {
            if (jSONObject == null) {
                jSONObject = new JSONObject();
            }
            try {
                for (Map.Entry next : map2.entrySet()) {
                    jSONObject.put((String) next.getKey(), next.getValue());
                }
            } catch (JSONException unused) {
            }
        }
        if (jSONObject != null) {
            a.putString("6_extras", jSONObject.toString());
        }
        this.appEventsLogger.logSdkEvent("fb_mobile_login_complete", (Double) null, a);
    }

    public void logLoginStatusError(String str, Exception exc) {
        Bundle a = a(str);
        a.putString("2_result", LoginClient.Result.Code.ERROR.a());
        a.putString("5_error_message", exc.toString());
        this.appEventsLogger.logSdkEvent("fb_mobile_login_status_complete", (Double) null, a);
    }

    public void logLoginStatusFailure(String str) {
        Bundle a = a(str);
        a.putString("2_result", "failure");
        this.appEventsLogger.logSdkEvent("fb_mobile_login_status_complete", (Double) null, a);
    }

    public void logLoginStatusStart(String str) {
        this.appEventsLogger.logSdkEvent("fb_mobile_login_status_start", (Double) null, a(str));
    }

    public void logLoginStatusSuccess(String str) {
        Bundle a = a(str);
        a.putString("2_result", LoginClient.Result.Code.SUCCESS.a());
        this.appEventsLogger.logSdkEvent("fb_mobile_login_status_complete", (Double) null, a);
    }

    public void logStartLogin(LoginClient.Request request) {
        Bundle a = a(request.e());
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("login_behavior", request.b().toString());
            jSONObject.put("request_code", LoginClient.getLoginRequestCode());
            jSONObject.put(NativeProtocol.RESULT_ARGS_PERMISSIONS, TextUtils.join(",", request.a()));
            jSONObject.put(ServerProtocol.DIALOG_PARAM_DEFAULT_AUDIENCE, request.c().toString());
            jSONObject.put("isReauthorize", request.f());
            if (this.facebookVersion != null) {
                jSONObject.put("facebookVersion", this.facebookVersion);
            }
            a.putString("6_extras", jSONObject.toString());
        } catch (JSONException unused) {
        }
        this.appEventsLogger.logSdkEvent("fb_mobile_login_start", (Double) null, a);
    }

    public void logUnexpectedError(String str, String str2) {
        logUnexpectedError(str, str2, "");
    }

    public void logUnexpectedError(String str, String str2, String str3) {
        Bundle a = a("");
        a.putString("2_result", LoginClient.Result.Code.ERROR.a());
        a.putString("5_error_message", str2);
        a.putString("3_method", str3);
        this.appEventsLogger.logSdkEvent(str, (Double) null, a);
    }
}
