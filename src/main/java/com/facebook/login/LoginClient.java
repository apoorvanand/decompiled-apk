package com.facebook.login;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.common.R;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.tekartik.sqflite.Constant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

class LoginClient implements Parcelable {
    public static final Parcelable.Creator<LoginClient> CREATOR = new Parcelable.Creator<LoginClient>() {
        public LoginClient createFromParcel(Parcel parcel) {
            return new LoginClient(parcel);
        }

        public LoginClient[] newArray(int i) {
            return new LoginClient[i];
        }
    };
    LoginMethodHandler[] a;
    int b = -1;
    Fragment c;
    OnCompletedListener d;
    BackgroundProcessingListener e;
    boolean f;
    Request g;
    Map<String, String> h;
    Map<String, String> i;
    private LoginLogger loginLogger;

    interface BackgroundProcessingListener {
        void onBackgroundProcessingStarted();

        void onBackgroundProcessingStopped();
    }

    public interface OnCompletedListener {
        void onCompleted(Result result);
    }

    public static class Request implements Parcelable {
        public static final Parcelable.Creator<Request> CREATOR = new Parcelable.Creator<Request>() {
            public Request createFromParcel(Parcel parcel) {
                return new Request(parcel);
            }

            public Request[] newArray(int i) {
                return new Request[i];
            }
        };
        private final String applicationId;
        private final String authId;
        private String authType;
        private final DefaultAudience defaultAudience;
        private String deviceAuthTargetUserId;
        private String deviceRedirectUriString;
        private boolean isRerequest;
        private final LoginBehavior loginBehavior;
        private Set<String> permissions;

        private Request(Parcel parcel) {
            boolean z = false;
            this.isRerequest = false;
            String readString = parcel.readString();
            DefaultAudience defaultAudience2 = null;
            this.loginBehavior = readString != null ? LoginBehavior.valueOf(readString) : null;
            ArrayList arrayList = new ArrayList();
            parcel.readStringList(arrayList);
            this.permissions = new HashSet(arrayList);
            String readString2 = parcel.readString();
            this.defaultAudience = readString2 != null ? DefaultAudience.valueOf(readString2) : defaultAudience2;
            this.applicationId = parcel.readString();
            this.authId = parcel.readString();
            this.isRerequest = parcel.readByte() != 0 ? true : z;
            this.deviceRedirectUriString = parcel.readString();
            this.authType = parcel.readString();
            this.deviceAuthTargetUserId = parcel.readString();
        }

        Request(LoginBehavior loginBehavior2, Set<String> set, DefaultAudience defaultAudience2, String str, String str2, String str3) {
            this.isRerequest = false;
            this.loginBehavior = loginBehavior2;
            this.permissions = set == null ? new HashSet<>() : set;
            this.defaultAudience = defaultAudience2;
            this.authType = str;
            this.applicationId = str2;
            this.authId = str3;
        }

        /* access modifiers changed from: package-private */
        public Set<String> a() {
            return this.permissions;
        }

        /* access modifiers changed from: package-private */
        public void a(String str) {
            this.deviceRedirectUriString = str;
        }

        /* access modifiers changed from: package-private */
        public void a(Set<String> set) {
            Validate.notNull(set, NativeProtocol.RESULT_ARGS_PERMISSIONS);
            this.permissions = set;
        }

        /* access modifiers changed from: package-private */
        public void a(boolean z) {
            this.isRerequest = z;
        }

        /* access modifiers changed from: package-private */
        public LoginBehavior b() {
            return this.loginBehavior;
        }

        /* access modifiers changed from: package-private */
        public void b(String str) {
            this.deviceAuthTargetUserId = str;
        }

        /* access modifiers changed from: package-private */
        public DefaultAudience c() {
            return this.defaultAudience;
        }

        /* access modifiers changed from: package-private */
        public String d() {
            return this.applicationId;
        }

        public int describeContents() {
            return 0;
        }

        /* access modifiers changed from: package-private */
        public String e() {
            return this.authId;
        }

        /* access modifiers changed from: package-private */
        public boolean f() {
            return this.isRerequest;
        }

        /* access modifiers changed from: package-private */
        public String g() {
            return this.deviceRedirectUriString;
        }

        /* access modifiers changed from: package-private */
        public String h() {
            return this.deviceAuthTargetUserId;
        }

        /* access modifiers changed from: package-private */
        public String i() {
            return this.authType;
        }

        /* access modifiers changed from: package-private */
        public boolean j() {
            for (String a : this.permissions) {
                if (LoginManager.a(a)) {
                    return true;
                }
            }
            return false;
        }

        public void writeToParcel(Parcel parcel, int i) {
            LoginBehavior loginBehavior2 = this.loginBehavior;
            String str = null;
            parcel.writeString(loginBehavior2 != null ? loginBehavior2.name() : null);
            parcel.writeStringList(new ArrayList(this.permissions));
            DefaultAudience defaultAudience2 = this.defaultAudience;
            if (defaultAudience2 != null) {
                str = defaultAudience2.name();
            }
            parcel.writeString(str);
            parcel.writeString(this.applicationId);
            parcel.writeString(this.authId);
            parcel.writeByte(this.isRerequest ? (byte) 1 : 0);
            parcel.writeString(this.deviceRedirectUriString);
            parcel.writeString(this.authType);
            parcel.writeString(this.deviceAuthTargetUserId);
        }
    }

    public static class Result implements Parcelable {
        public static final Parcelable.Creator<Result> CREATOR = new Parcelable.Creator<Result>() {
            public Result createFromParcel(Parcel parcel) {
                return new Result(parcel);
            }

            public Result[] newArray(int i) {
                return new Result[i];
            }
        };
        final Code a;
        final AccessToken b;
        final String c;
        final String d;
        final Request e;
        public Map<String, String> extraData;
        public Map<String, String> loggingExtras;

        enum Code {
            SUCCESS("success"),
            CANCEL("cancel"),
            ERROR("error");
            
            private final String loggingValue;

            private Code(String str) {
                this.loggingValue = str;
            }

            /* access modifiers changed from: package-private */
            public String a() {
                return this.loggingValue;
            }
        }

        private Result(Parcel parcel) {
            this.a = Code.valueOf(parcel.readString());
            this.b = (AccessToken) parcel.readParcelable(AccessToken.class.getClassLoader());
            this.c = parcel.readString();
            this.d = parcel.readString();
            this.e = (Request) parcel.readParcelable(Request.class.getClassLoader());
            this.loggingExtras = Utility.readStringMapFromParcel(parcel);
            this.extraData = Utility.readStringMapFromParcel(parcel);
        }

        Result(Request request, Code code, AccessToken accessToken, String str, String str2) {
            Validate.notNull(code, Constant.PARAM_ERROR_CODE);
            this.e = request;
            this.b = accessToken;
            this.c = str;
            this.a = code;
            this.d = str2;
        }

        static Result a(Request request, AccessToken accessToken) {
            return new Result(request, Code.SUCCESS, accessToken, (String) null, (String) null);
        }

        static Result a(Request request, String str) {
            return new Result(request, Code.CANCEL, (AccessToken) null, str, (String) null);
        }

        static Result a(Request request, String str, String str2) {
            return a(request, str, str2, (String) null);
        }

        static Result a(Request request, String str, String str2, String str3) {
            return new Result(request, Code.ERROR, (AccessToken) null, TextUtils.join(": ", Utility.asListNoNulls(str, str2)), str3);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.a.name());
            parcel.writeParcelable(this.b, i);
            parcel.writeString(this.c);
            parcel.writeString(this.d);
            parcel.writeParcelable(this.e, i);
            Utility.writeStringMapToParcel(parcel, this.loggingExtras);
            Utility.writeStringMapToParcel(parcel, this.extraData);
        }
    }

    public LoginClient(Parcel parcel) {
        Parcelable[] readParcelableArray = parcel.readParcelableArray(LoginMethodHandler.class.getClassLoader());
        this.a = new LoginMethodHandler[readParcelableArray.length];
        for (int i2 = 0; i2 < readParcelableArray.length; i2++) {
            LoginMethodHandler[] loginMethodHandlerArr = this.a;
            loginMethodHandlerArr[i2] = (LoginMethodHandler) readParcelableArray[i2];
            loginMethodHandlerArr[i2].a(this);
        }
        this.b = parcel.readInt();
        this.g = (Request) parcel.readParcelable(Request.class.getClassLoader());
        this.h = Utility.readStringMapFromParcel(parcel);
        this.i = Utility.readStringMapFromParcel(parcel);
    }

    public LoginClient(Fragment fragment) {
        this.c = fragment;
    }

    private void addLoggingExtra(String str, String str2, boolean z) {
        if (this.h == null) {
            this.h = new HashMap();
        }
        if (this.h.containsKey(str) && z) {
            str2 = this.h.get(str) + "," + str2;
        }
        this.h.put(str, str2);
    }

    private void completeWithFailure() {
        b(Result.a(this.g, "Login attempt failed.", (String) null));
    }

    private LoginLogger getLogger() {
        LoginLogger loginLogger2 = this.loginLogger;
        if (loginLogger2 == null || !loginLogger2.getApplicationId().equals(this.g.d())) {
            this.loginLogger = new LoginLogger(a(), this.g.d());
        }
        return this.loginLogger;
    }

    public static int getLoginRequestCode() {
        return CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode();
    }

    static String j() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("init", System.currentTimeMillis());
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }

    private void logAuthorizationMethodComplete(String str, Result result, Map<String, String> map) {
        logAuthorizationMethodComplete(str, result.a.a(), result.c, result.d, map);
    }

    private void logAuthorizationMethodComplete(String str, String str2, String str3, String str4, Map<String, String> map) {
        if (this.g == null) {
            getLogger().logUnexpectedError("fb_mobile_login_method_complete", "Unexpected call to logCompleteLogin with null pendingAuthorizationRequest.", str);
        } else {
            getLogger().logAuthorizationMethodComplete(this.g.e(), str, str2, str3, str4, map);
        }
    }

    private void notifyOnCompleteListener(Result result) {
        OnCompletedListener onCompletedListener = this.d;
        if (onCompletedListener != null) {
            onCompletedListener.onCompleted(result);
        }
    }

    /* access modifiers changed from: package-private */
    public int a(String str) {
        return a().checkCallingOrSelfPermission(str);
    }

    /* access modifiers changed from: package-private */
    public FragmentActivity a() {
        return this.c.getActivity();
    }

    /* access modifiers changed from: package-private */
    public void a(Fragment fragment) {
        if (this.c == null) {
            this.c = fragment;
            return;
        }
        throw new FacebookException("Can't set fragment once it is already set.");
    }

    /* access modifiers changed from: package-private */
    public void a(BackgroundProcessingListener backgroundProcessingListener) {
        this.e = backgroundProcessingListener;
    }

    /* access modifiers changed from: package-private */
    public void a(OnCompletedListener onCompletedListener) {
        this.d = onCompletedListener;
    }

    /* access modifiers changed from: package-private */
    public void a(Request request) {
        if (!b()) {
            b(request);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Result result) {
        if (result.b == null || !AccessToken.isCurrentAccessTokenActive()) {
            b(result);
        } else {
            c(result);
        }
    }

    /* access modifiers changed from: package-private */
    public void b(Request request) {
        if (request != null) {
            if (this.g != null) {
                throw new FacebookException("Attempted to authorize while a request is pending.");
            } else if (!AccessToken.isCurrentAccessTokenActive() || e()) {
                this.g = request;
                this.a = c(request);
                f();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void b(Result result) {
        LoginMethodHandler d2 = d();
        if (d2 != null) {
            logAuthorizationMethodComplete(d2.a(), result, d2.a);
        }
        Map<String, String> map = this.h;
        if (map != null) {
            result.loggingExtras = map;
        }
        Map<String, String> map2 = this.i;
        if (map2 != null) {
            result.extraData = map2;
        }
        this.a = null;
        this.b = -1;
        this.g = null;
        this.h = null;
        notifyOnCompleteListener(result);
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.g != null && this.b >= 0;
    }

    /* access modifiers changed from: package-private */
    public void c() {
        if (this.b >= 0) {
            d().b();
        }
    }

    /* access modifiers changed from: package-private */
    public void c(Result result) {
        Result result2;
        if (result.b != null) {
            AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
            AccessToken accessToken = result.b;
            if (!(currentAccessToken == null || accessToken == null)) {
                try {
                    if (currentAccessToken.getUserId().equals(accessToken.getUserId())) {
                        result2 = Result.a(this.g, result.b);
                        b(result2);
                        return;
                    }
                } catch (Exception e2) {
                    b(Result.a(this.g, "Caught exception", e2.getMessage()));
                    return;
                }
            }
            result2 = Result.a(this.g, "User logged in as different Facebook user.", (String) null);
            b(result2);
            return;
        }
        throw new FacebookException("Can't validate without a token");
    }

    /* access modifiers changed from: protected */
    public LoginMethodHandler[] c(Request request) {
        ArrayList arrayList = new ArrayList();
        LoginBehavior b2 = request.b();
        if (b2.a()) {
            arrayList.add(new GetTokenLoginMethodHandler(this));
        }
        if (b2.b()) {
            arrayList.add(new KatanaProxyLoginMethodHandler(this));
        }
        if (b2.f()) {
            arrayList.add(new FacebookLiteLoginMethodHandler(this));
        }
        if (b2.e()) {
            arrayList.add(new CustomTabLoginMethodHandler(this));
        }
        if (b2.c()) {
            arrayList.add(new WebViewLoginMethodHandler(this));
        }
        if (b2.d()) {
            arrayList.add(new DeviceAuthMethodHandler(this));
        }
        LoginMethodHandler[] loginMethodHandlerArr = new LoginMethodHandler[arrayList.size()];
        arrayList.toArray(loginMethodHandlerArr);
        return loginMethodHandlerArr;
    }

    /* access modifiers changed from: package-private */
    public LoginMethodHandler d() {
        int i2 = this.b;
        if (i2 >= 0) {
            return this.a[i2];
        }
        return null;
    }

    public int describeContents() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public boolean e() {
        if (this.f) {
            return true;
        }
        if (a("android.permission.INTERNET") != 0) {
            FragmentActivity a2 = a();
            b(Result.a(this.g, a2.getString(R.string.com_facebook_internet_permission_error_title), a2.getString(R.string.com_facebook_internet_permission_error_message)));
            return false;
        }
        this.f = true;
        return true;
    }

    /* access modifiers changed from: package-private */
    public void f() {
        int i2;
        if (this.b >= 0) {
            logAuthorizationMethodComplete(d().a(), "skipped", (String) null, (String) null, d().a);
        }
        do {
            LoginMethodHandler[] loginMethodHandlerArr = this.a;
            if (loginMethodHandlerArr != null && (i2 = this.b) < loginMethodHandlerArr.length - 1) {
                this.b = i2 + 1;
            } else if (this.g != null) {
                completeWithFailure();
                return;
            } else {
                return;
            }
        } while (!g());
    }

    /* access modifiers changed from: package-private */
    public boolean g() {
        LoginMethodHandler d2 = d();
        if (!d2.d() || e()) {
            boolean a2 = d2.a(this.g);
            if (a2) {
                getLogger().logAuthorizationMethodStart(this.g.e(), d2.a());
            } else {
                getLogger().logAuthorizationMethodNotTried(this.g.e(), d2.a());
                addLoggingExtra("not_tried", d2.a(), true);
            }
            return a2;
        }
        addLoggingExtra("no_internet_permission", AppEventsConstants.EVENT_PARAM_VALUE_YES, false);
        return false;
    }

    public Fragment getFragment() {
        return this.c;
    }

    public Request getPendingRequest() {
        return this.g;
    }

    /* access modifiers changed from: package-private */
    public void h() {
        BackgroundProcessingListener backgroundProcessingListener = this.e;
        if (backgroundProcessingListener != null) {
            backgroundProcessingListener.onBackgroundProcessingStarted();
        }
    }

    /* access modifiers changed from: package-private */
    public void i() {
        BackgroundProcessingListener backgroundProcessingListener = this.e;
        if (backgroundProcessingListener != null) {
            backgroundProcessingListener.onBackgroundProcessingStopped();
        }
    }

    public boolean onActivityResult(int i2, int i3, Intent intent) {
        if (this.g != null) {
            return d().a(i2, i3, intent);
        }
        return false;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelableArray(this.a, i2);
        parcel.writeInt(this.b);
        parcel.writeParcelable(this.g, i2);
        Utility.writeStringMapToParcel(parcel, this.h);
        Utility.writeStringMapToParcel(parcel, this.i);
    }
}
