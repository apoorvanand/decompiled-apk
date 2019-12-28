package com.razorpay;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import com.facebook.appevents.codeless.internal.Constants;
import com.facebook.internal.AnalyticsEvents;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import proguard.annotation.Keep;
import proguard.annotation.KeepClassMembers;

@Keep
@KeepClassMembers
public class AnalyticsUtil {
    static String BUILD_TYPE = null;
    static String KEY_TYPE = null;
    public static int MERCHANT_APP_BUILD = 0;
    public static CharSequence MERCHANT_APP_NAME = null;
    static CharSequence MERCHANT_APP_NAMESPACE = null;
    public static CharSequence MERCHANT_APP_VERSION = null;
    private static boolean isAnalyticsInitialized = false;
    private static String localOrderId = null;
    private static String localPaymentId = null;
    private static String sdkType = "standealone";
    private static String sdkVersion;
    private static int sdkVersionCode;

    AnalyticsUtil() {
    }

    static void addFilteredPropertiesFromPayload(JSONObject jSONObject) {
        try {
            ((Class) K$$z$.G__G_(48401, 18, 0)).getDeclaredMethod("R$$r_", new Class[]{JSONObject.class}).invoke((Object) null, new Object[]{jSONObject});
        } catch (Throwable th) {
            Throwable cause = th.getCause();
            if (cause != null) {
                throw cause;
            }
            throw th;
        }
    }

    static void addProperty(String str, AnalyticsProperty analyticsProperty) {
        if (analyticsProperty.scope == AnalyticsProperty$R$$r_.PAYMENT) {
            try {
                Object[] objArr = new Object[2];
                objArr[1] = analyticsProperty.value;
                objArr[0] = str;
                ((Class) K$$z$.G__G_(48401, 18, 0)).getDeclaredMethod("R$$r_", new Class[]{String.class, Object.class}).invoke((Object) null, objArr);
            } catch (Throwable th) {
                Throwable cause = th.getCause();
                if (cause != null) {
                    throw cause;
                }
                throw th;
            }
        } else if (analyticsProperty.scope == AnalyticsProperty$R$$r_.ORDER) {
            try {
                Object[] objArr2 = new Object[2];
                objArr2[1] = analyticsProperty.value;
                objArr2[0] = str;
                ((Class) K$$z$.G__G_(48401, 18, 0)).getMethod("Q_$2$", new Class[]{String.class, Object.class}).invoke((Object) null, objArr2);
            } catch (Throwable th2) {
                Throwable cause2 = th2.getCause();
                if (cause2 != null) {
                    throw cause2;
                }
                throw th2;
            }
        }
    }

    static JSONObject getAnalyticsDataForCheckout(Context context) {
        String str;
        String str2;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("platform", "mobile_sdk");
            jSONObject.put("platform_version", sdkVersion);
            jSONObject.put("os", Constants.PLATFORM);
            jSONObject.put("os_version", Build.VERSION.RELEASE);
            if (context.getResources().getBoolean(R.bool.isTablet)) {
                str = "device";
                str2 = "tablet";
            } else {
                str = "device";
                str2 = "mobile";
            }
            jSONObject.put(str, str2);
        } catch (Exception e) {
            reportError(e, "critical", e.getMessage());
        }
        return jSONObject;
    }

    static String getAppDetail() {
        if (!isAnalyticsInitialized) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(MERCHANT_APP_NAME);
        sb.append("-");
        sb.append(MERCHANT_APP_VERSION);
        sb.append("-");
        sb.append(MERCHANT_APP_BUILD);
        return sb.toString();
    }

    static String getBuildType() {
        return BUILD_TYPE;
    }

    static Map<String, Object> getErrorProperties(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("error_level", str);
        hashMap.put(AnalyticsEvents.PARAMETER_SHARE_ERROR_MESSAGE, str2);
        return hashMap;
    }

    public static JSONObject getExtraAnalyticsPayload() {
        try {
            return (JSONObject) ((Class) K$$z$.G__G_(48401, 18, 0)).getDeclaredMethod("d__1_", (Class[]) null).invoke((Object) null, (Object[]) null);
        } catch (Throwable th) {
            Throwable cause = th.getCause();
            if (cause != null) {
                throw cause;
            }
            throw th;
        }
    }

    public static String getKeyType() {
        return KEY_TYPE;
    }

    static String getKeyType(String str) {
        if (isNullOrEmpty(str)) {
            return null;
        }
        String substring = str.substring(0, 8);
        if (substring.equals("rzp_live")) {
            return "live";
        }
        if (substring.equals("rzp_test")) {
            return "test";
        }
        return null;
    }

    public static String getLocalOrderId() {
        if (localOrderId == null) {
            localOrderId = getUniqueId();
        }
        return localOrderId;
    }

    public static String getLocalPaymentId() {
        if (localPaymentId == null) {
            localPaymentId = getUniqueId();
        }
        return localPaymentId;
    }

    static Map<String, Object> getPageLoadEndProperties(String str, long j) {
        HashMap hashMap = new HashMap();
        hashMap.put("url", str);
        hashMap.put("page_load_time", Double.valueOf(((double) j) / 1.0E9d));
        return hashMap;
    }

    static Map<String, Object> getPageLoadStartProperties(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("url", str);
        return hashMap;
    }

    static String getStackTrace(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter, true));
        return stringWriter.getBuffer().toString();
    }

    static String getUniqueId() {
        StringBuilder sb = new StringBuilder();
        sb.append(tobase62((System.currentTimeMillis() - 1388534400000L) * 1000000));
        sb.append(tobase62((long) Math.floor(Math.random() * 1.4776336E7d)));
        return sb.toString();
    }

    private static void init(Context context, String str) {
        Throwable cause;
        if (context == null) {
            throw new RuntimeException("Context not set");
        } else if (str != null) {
            String str2 = sdkType;
            try {
                Object[] objArr = new Object[3];
                objArr[2] = sdkVersion;
                objArr[1] = str2;
                objArr[0] = context;
                ((Class) K$$z$.G__G_(48401, 18, 0)).getDeclaredMethod("d__1_", new Class[]{Context.class, String.class, String.class}).invoke((Object) null, objArr);
                try {
                    Object[] objArr2 = new Object[2];
                    objArr2[1] = str;
                    objArr2[0] = "merchant_key";
                    ((Class) K$$z$.G__G_(48401, 18, 0)).getDeclaredMethod("Q_$2$", new Class[]{String.class, Object.class}).invoke((Object) null, objArr2);
                    try {
                        Object[] objArr3 = new Object[2];
                        objArr3[1] = context.getPackageName();
                        objArr3[0] = "merchant_package";
                        ((Class) K$$z$.G__G_(48401, 18, 0)).getMethod("Q_$2$", new Class[]{String.class, Object.class}).invoke((Object) null, objArr3);
                        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
                        if (!(defaultUncaughtExceptionHandler instanceof C__D$)) {
                            Thread.setDefaultUncaughtExceptionHandler(new C__D$(context, defaultUncaughtExceptionHandler));
                        }
                        isAnalyticsInitialized = true;
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
            } finally {
                Throwable cause2 = th.getCause();
                if (cause2 != null) {
                    throw cause2;
                }
            }
        } else {
            throw new RuntimeException("Merchant key not set");
        }
    }

    static boolean isCheckoutUrl(String str) {
        return str.indexOf(J$$A_.f$_G$().Y$_o$()) == 0;
    }

    public static boolean isNullOrEmpty(CharSequence charSequence) {
        return TextUtils.isEmpty(charSequence) || TextUtils.getTrimmedLength(charSequence) == 0;
    }

    static void postData() {
        if (isAnalyticsInitialized) {
            try {
                ((Class) K$$z$.G__G_(48401, 18, 0)).getDeclaredMethod("a_$P$", (Class[]) null).invoke((Object) null, (Object[]) null);
            } catch (Throwable th) {
                Throwable cause = th.getCause();
                if (cause != null) {
                    throw cause;
                }
                throw th;
            }
        }
    }

    static void refreshOrderSession() {
        Throwable cause;
        localOrderId = getUniqueId();
        localPaymentId = getUniqueId();
        try {
            ((Class) K$$z$.G__G_(48401, 18, 0)).getDeclaredMethod("G__G_", (Class[]) null).invoke((Object) null, (Object[]) null);
            try {
                ((Class) K$$z$.G__G_(48401, 18, 0)).getMethod("R$$r_", (Class[]) null).invoke((Object) null, (Object[]) null);
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

    static void refreshPaymentSession() {
        localPaymentId = getUniqueId();
        try {
            ((Class) K$$z$.G__G_(48401, 18, 0)).getDeclaredMethod("R$$r_", (Class[]) null).invoke((Object) null, (Object[]) null);
        } catch (Throwable th) {
            Throwable cause = th.getCause();
            if (cause != null) {
                throw cause;
            }
            throw th;
        }
    }

    public static void reportError(Exception exc, String str, String str2) {
        trackEvent(AnalyticsEvent.ERROR_LOGGED, getErrorProperties(str, str2));
    }

    static void reportUncaughtException(Throwable th) {
        trackEvent(AnalyticsEvent.ERROR_LOGGED, getErrorProperties("uncaught", getStackTrace(th)));
    }

    static void reset() {
        isAnalyticsInitialized = false;
        localPaymentId = null;
        localOrderId = null;
        try {
            ((Class) K$$z$.G__G_(48401, 18, 0)).getDeclaredMethod("Q_$2$", (Class[]) null).invoke((Object) null, (Object[]) null);
        } catch (Throwable th) {
            Throwable cause = th.getCause();
            if (cause != null) {
                throw cause;
            }
            throw th;
        }
    }

    public static CharSequence returnUndefinedIfNull(CharSequence charSequence) {
        return isNullOrEmpty(charSequence) ? "undefined" : charSequence;
    }

    public static void saveEventsToPreferences(Context context) {
        try {
            ((Class) K$$z$.G__G_(48401, 18, 0)).getDeclaredMethod("G__G_", new Class[]{Context.class}).invoke((Object) null, new Object[]{context});
        } catch (Throwable th) {
            Throwable cause = th.getCause();
            if (cause != null) {
                throw cause;
            }
            throw th;
        }
    }

    static void setAppDetails(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            MERCHANT_APP_NAME = returnUndefinedIfNull(packageInfo.applicationInfo.loadLabel(packageManager));
            MERCHANT_APP_VERSION = returnUndefinedIfNull(packageInfo.versionName);
            MERCHANT_APP_NAMESPACE = returnUndefinedIfNull(packageInfo.packageName);
            MERCHANT_APP_BUILD = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            reportError(e, "critical", e.getMessage());
        }
        BUILD_TYPE = BaseUtils.getAppBuildType(context);
        KEY_TYPE = getKeyType(str);
    }

    static void setLocalOrderId(String str) {
        localOrderId = str;
    }

    static void setup(Context context, String str, String str2, int i, String str3) {
        sdkType = str2;
        sdkVersionCode = i;
        sdkVersion = str3;
        setAppDetails(context, str);
        init(context, str);
    }

    static String tobase62(long j) {
        String[] split = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".split("");
        String str = "";
        while (j > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.valueOf(split[(int) (j % 62)]));
            sb.append(str);
            str = sb.toString();
            j = (long) Math.floor((double) (j / 62));
        }
        return str;
    }

    static void trackEvent(AnalyticsEvent analyticsEvent) {
        try {
            ((Class) K$$z$.G__G_(48401, 18, 0)).getDeclaredMethod("R$$r_", new Class[]{String.class}).invoke((Object) null, new Object[]{analyticsEvent.getEventName()});
        } catch (Throwable th) {
            Throwable cause = th.getCause();
            if (cause != null) {
                throw cause;
            }
            throw th;
        }
    }

    static void trackEvent(AnalyticsEvent analyticsEvent, Map<String, Object> map) {
        String eventName = analyticsEvent.getEventName();
        try {
            Object[] objArr = new Object[2];
            objArr[1] = map;
            objArr[0] = eventName;
            ((Class) K$$z$.G__G_(48401, 18, 0)).getDeclaredMethod("R$$r_", new Class[]{String.class, Map.class}).invoke((Object) null, objArr);
        } catch (Throwable th) {
            Throwable cause = th.getCause();
            if (cause != null) {
                throw cause;
            }
            throw th;
        }
    }

    static void trackEvent(AnalyticsEvent analyticsEvent, JSONObject jSONObject) {
        String eventName = analyticsEvent.getEventName();
        try {
            Object[] objArr = new Object[2];
            objArr[1] = jSONObject;
            objArr[0] = eventName;
            ((Class) K$$z$.G__G_(48401, 18, 0)).getDeclaredMethod("G__G_", new Class[]{String.class, JSONObject.class}).invoke((Object) null, objArr);
        } catch (Throwable th) {
            Throwable cause = th.getCause();
            if (cause != null) {
                throw cause;
            }
            throw th;
        }
    }

    static void trackPage(String str, String str2) {
        try {
            Object[] objArr = new Object[2];
            objArr[1] = str2;
            objArr[0] = str;
            ((Class) K$$z$.G__G_(48401, 18, 0)).getDeclaredMethod("d__1_", new Class[]{String.class, String.class}).invoke((Object) null, objArr);
        } catch (Throwable th) {
            Throwable cause = th.getCause();
            if (cause != null) {
                throw cause;
            }
            throw th;
        }
    }

    static void trackPageLoadEnd(String str, long j) {
        trackEvent(isCheckoutUrl(str) ? AnalyticsEvent.CHECKOUT_PAGE_LOAD_FINISH : AnalyticsEvent.PAGE_LOAD_FINISH, getPageLoadEndProperties(str, j));
    }

    static void trackPageLoadStart(String str) {
        trackEvent(isCheckoutUrl(str) ? AnalyticsEvent.CHECKOUT_PAGE_LOAD_START : AnalyticsEvent.PAGE_LOAD_START, getPageLoadStartProperties(str));
    }
}
