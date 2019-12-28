package com.razorpay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.SSLContext;
import kotlin.text.Typography;
import org.json.JSONArray;
import org.json.JSONObject;
import proguard.annotation.Keep;
import proguard.annotation.KeepClassMembers;

@Keep
@KeepClassMembers
public class BaseUtils {
    private static int Q_$2$ = 14;
    private static int a_$P$ = 1;
    private static int d__1_;
    private static boolean sWebViewDebuggingEnabled = B_$q$.Q_$2$.booleanValue();

    static {
        int i = d__1_ + 9;
        a_$P$ = i % 128;
        if (i % 2 == 0) {
            Object obj = null;
            super.hashCode();
        }
    }

    BaseUtils() {
    }

    static void checkForLatestVersion(Context context, int i) {
        if (!(!J$$A_.f$_G$().r$_Y_()) && isMerchantAppDebuggable(context) && i < J$$A_.f$_G$().D$_X_()) {
            int i2 = a_$P$ + 89;
            d__1_ = i2 % 128;
            int i3 = i2 % 2;
            Toast.makeText(context, J$$A_.f$_G$().b__J_(), 1).show();
        }
        int i4 = d__1_ + 27;
        a_$P$ = i4 % 128;
        if (i4 % 2 == 0) {
            int i5 = 1 / 0;
        }
    }

    static String constructBasicAuth(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(":");
        String encodeToString = Base64.encodeToString(sb.toString().getBytes("UTF-8"), 2);
        int i = d__1_ + 55;
        a_$P$ = i % 128;
        int i2 = i % 2;
        return encodeToString;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.security.PublicKey] */
    static PublicKey constructPublicKey(String str) {
        ? r0 = 0;
        try {
            boolean z = false;
            PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str.getBytes(), 0)));
            int i = a_$P$ + 125;
            d__1_ = i % 128;
            if (i % 2 != 0) {
                z = true;
            }
            if (!z) {
                return generatePublic;
            }
            int length = r0.length;
            return generatePublic;
        } catch (Exception unused) {
            return r0;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: char[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v7, resolved type: java.lang.String} */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String d__1_(boolean r5, java.lang.String r6, int r7, int r8, int r9) {
        /*
            if (r6 == 0) goto L_0x0010
            int r0 = d__1_
            int r0 = r0 + 43
            int r1 = r0 % 128
            a_$P$ = r1
            int r0 = r0 % 2
            char[] r6 = r6.toCharArray()
        L_0x0010:
            char[] r6 = (char[]) r6
            char[] r0 = new char[r9]
            r1 = 0
            r2 = 0
        L_0x0016:
            r3 = 97
            if (r2 >= r9) goto L_0x001d
            r4 = 42
            goto L_0x001f
        L_0x001d:
            r4 = 97
        L_0x001f:
            if (r4 == r3) goto L_0x003c
            char r3 = r6[r2]
            int r3 = r3 + r7
            char r3 = (char) r3
            r0[r2] = r3
            char r3 = r0[r2]
            int r4 = Q_$2$
            int r3 = r3 - r4
            char r3 = (char) r3
            r0[r2] = r3
            int r2 = r2 + 1
            int r3 = a_$P$
            int r3 = r3 + 45
            int r4 = r3 % 128
            d__1_ = r4
            int r3 = r3 % 2
            goto L_0x0016
        L_0x003c:
            if (r8 <= 0) goto L_0x004b
            char[] r6 = new char[r9]
            java.lang.System.arraycopy(r0, r1, r6, r1, r9)
            int r7 = r9 - r8
            java.lang.System.arraycopy(r6, r1, r0, r7, r8)
            java.lang.System.arraycopy(r6, r8, r0, r1, r7)
        L_0x004b:
            r6 = 1
            if (r5 == 0) goto L_0x0050
            r5 = 1
            goto L_0x0051
        L_0x0050:
            r5 = 0
        L_0x0051:
            if (r5 == 0) goto L_0x0074
            char[] r5 = new char[r9]
        L_0x0055:
            r7 = 31
            if (r1 >= r9) goto L_0x005c
            r8 = 31
            goto L_0x005d
        L_0x005c:
            r8 = 7
        L_0x005d:
            if (r8 == r7) goto L_0x0060
            goto L_0x0075
        L_0x0060:
            int r7 = r9 - r1
            int r7 = r7 - r6
            char r7 = r0[r7]
            r5[r1] = r7
            int r1 = r1 + 1
            int r7 = a_$P$
            int r7 = r7 + 17
            int r8 = r7 % 128
            d__1_ = r8
            int r7 = r7 % 2
            goto L_0x0055
        L_0x0074:
            r5 = r0
        L_0x0075:
            java.lang.String r6 = new java.lang.String
            r6.<init>(r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.razorpay.BaseUtils.d__1_(boolean, java.lang.String, int, int, int):java.lang.String");
    }

    static String decryptFile(String str) {
        String str2;
        try {
            O__Y_ o__y_ = new O__Y_();
            String intern = d__1_(false, "￼\u0007￸￷\u0005￸￷\u0005\r\u0003￼\u0006\b\u0001", 123, 7, 14).intern();
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(intern.getBytes("UTF-8"));
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            int length = digest.length;
            int i = 0;
            while (i < length) {
                int i2 = d__1_ + 69;
                a_$P$ = i2 % 128;
                if (i2 % 2 == 0) {
                    Object[] objArr = new Object[0];
                    objArr[0] = Byte.valueOf(digest[i]);
                    stringBuffer.append(String.format("%02x", objArr));
                    i += 105;
                } else {
                    stringBuffer.append(String.format("%02x", new Object[]{Byte.valueOf(digest[i])}));
                    i++;
                }
            }
            if ((32 > stringBuffer.toString().length() ? 'E' : 'O') != 'E') {
                str2 = stringBuffer.toString().substring(0, 32);
                int i3 = d__1_ + 27;
                a_$P$ = i3 % 128;
                if (i3 % 2 == 0) {
                }
            } else {
                str2 = stringBuffer.toString();
            }
            return o__y_.d__1_(str, str2, d__1_(true, "\u0004\u0001￾￹\u000b\u0004\u0001￾￹￶￷\u0006￻\u0000\u0007\u000b", 124, 9, 16).intern());
        } catch (Exception e) {
            AnalyticsUtil.reportError(e, "error", "Unable to decrypt file");
            e.getMessage();
            return null;
        }
    }

    static int dpToPixels(Context context, int i) {
        int i2 = d__1_ + 119;
        a_$P$ = i2 % 128;
        if (i2 % 2 == 0) {
        }
        return (int) TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics());
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private static void enableJavaScriptInWebView(WebView webView) {
        int i = d__1_ + 123;
        a_$P$ = i % 128;
        int i2 = i % 2;
        webView.getSettings().setJavaScriptEnabled(true);
        int i3 = d__1_ + 55;
        a_$P$ = i3 % 128;
        if ((i3 % 2 == 0 ? '%' : Typography.less) == '%') {
            Object obj = null;
            super.hashCode();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003a, code lost:
        if (r7.metaData == null) goto L_0x003c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.util.HashMap<java.lang.String, java.lang.String> getAllPluginsFromManifest(android.content.Context r7) {
        /*
            int r0 = d__1_
            int r0 = r0 + 59
            int r1 = r0 % 128
            a_$P$ = r1
            int r0 = r0 % 2
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0010
            r0 = 0
            goto L_0x0011
        L_0x0010:
            r0 = 1
        L_0x0011:
            r3 = 0
            if (r0 == r2) goto L_0x002a
            android.content.pm.PackageManager r0 = r7.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0027 }
            java.lang.String r7 = r7.getPackageName()     // Catch:{ NameNotFoundException -> 0x0027 }
            r4 = 22872(0x5958, float:3.205E-41)
            android.content.pm.ApplicationInfo r7 = r0.getApplicationInfo(r7, r4)     // Catch:{ NameNotFoundException -> 0x0027 }
            android.os.Bundle r0 = r7.metaData     // Catch:{ NameNotFoundException -> 0x0027 }
            if (r0 != 0) goto L_0x0056
            goto L_0x003c
        L_0x0027:
            r7 = move-exception
            goto L_0x009c
        L_0x002a:
            android.content.pm.PackageManager r0 = r7.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0027 }
            java.lang.String r7 = r7.getPackageName()     // Catch:{ NameNotFoundException -> 0x0027 }
            r4 = 128(0x80, float:1.794E-43)
            android.content.pm.ApplicationInfo r7 = r0.getApplicationInfo(r7, r4)     // Catch:{ NameNotFoundException -> 0x0027 }
            android.os.Bundle r0 = r7.metaData     // Catch:{ NameNotFoundException -> 0x0027 }
            if (r0 != 0) goto L_0x0056
        L_0x003c:
            int r7 = d__1_
            int r7 = r7 + 5
            int r0 = r7 % 128
            a_$P$ = r0
            int r7 = r7 % 2
            r0 = 91
            if (r7 != 0) goto L_0x004d
            r7 = 91
            goto L_0x004f
        L_0x004d:
            r7 = 31
        L_0x004f:
            if (r7 == r0) goto L_0x0052
            return r3
        L_0x0052:
            r7 = 30
            int r7 = r7 / r1
            return r3
        L_0x0056:
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ NameNotFoundException -> 0x0027 }
            r0.<init>()     // Catch:{ NameNotFoundException -> 0x0027 }
            android.os.Bundle r4 = r7.metaData     // Catch:{ NameNotFoundException -> 0x0027 }
            java.util.Set r4 = r4.keySet()     // Catch:{ NameNotFoundException -> 0x0027 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ NameNotFoundException -> 0x0027 }
        L_0x0065:
            boolean r5 = r4.hasNext()     // Catch:{ NameNotFoundException -> 0x0027 }
            if (r5 == 0) goto L_0x009b
            int r5 = d__1_
            int r5 = r5 + 33
            int r6 = r5 % 128
            a_$P$ = r6
            int r5 = r5 % 2
            java.lang.Object r5 = r4.next()     // Catch:{ NameNotFoundException -> 0x0027 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ NameNotFoundException -> 0x0027 }
            java.lang.String r6 = "com.razorpay.plugin."
            boolean r6 = r5.contains(r6)     // Catch:{ NameNotFoundException -> 0x0027 }
            if (r6 == 0) goto L_0x0085
            r6 = 1
            goto L_0x0086
        L_0x0085:
            r6 = 0
        L_0x0086:
            if (r6 == r2) goto L_0x0089
            goto L_0x0065
        L_0x0089:
            android.os.Bundle r6 = r7.metaData     // Catch:{ NameNotFoundException -> 0x0027 }
            java.lang.String r6 = r6.getString(r5)     // Catch:{ NameNotFoundException -> 0x0027 }
            if (r6 == 0) goto L_0x0065
            android.os.Bundle r6 = r7.metaData     // Catch:{ NameNotFoundException -> 0x0027 }
            java.lang.String r6 = r6.getString(r5)     // Catch:{ NameNotFoundException -> 0x0027 }
            r0.put(r5, r6)     // Catch:{ NameNotFoundException -> 0x0027 }
            goto L_0x0065
        L_0x009b:
            return r0
        L_0x009c:
            java.lang.String r0 = "critical"
            java.lang.String r1 = r7.getMessage()
            com.razorpay.AnalyticsUtil.reportError(r7, r0, r1)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.razorpay.BaseUtils.getAllPluginsFromManifest(android.content.Context):java.util.HashMap");
    }

    static String getAppBuildType(Context context) {
        if (((context.getApplicationInfo().flags & 2) != 0 ? '-' : 7) == 7) {
            return "production";
        }
        int i = a_$P$ + 49;
        d__1_ = i % 128;
        int i2 = i % 2;
        int i3 = a_$P$ + 53;
        d__1_ = i3 % 128;
        if (i3 % 2 != 0) {
            Object[] objArr = null;
            int length = objArr.length;
        }
        return "development";
    }

    static String getAppNameOfPackageName(String str, Context context) {
        int i = a_$P$ + 113;
        d__1_ = i % 128;
        int i2 = i % 2;
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 128);
            int i3 = applicationInfo.labelRes;
            Resources resourcesForApplication = packageManager.getResourcesForApplication(applicationInfo);
            if ((i3 == 0 ? (char) 13 : 14) == 14) {
                return resourcesForApplication.getString(i3);
            }
            int i4 = a_$P$ + 117;
            d__1_ = i4 % 128;
            if (!(i4 % 2 != 0)) {
                return applicationInfo.nonLocalizedLabel.toString();
            }
            int i5 = 64 / 0;
            return applicationInfo.nonLocalizedLabel.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    static String getAppNameOfResolveInfo(ResolveInfo resolveInfo, Context context) {
        String str;
        int i = d__1_ + 21;
        a_$P$ = i % 128;
        if ((i % 2 == 0 ? '(' : '!') != '(') {
            str = getAppNameOfPackageName(resolveInfo.activityInfo.packageName, context);
        } else {
            str = getAppNameOfPackageName(resolveInfo.activityInfo.packageName, context);
            Object[] objArr = null;
            int length = objArr.length;
        }
        int i2 = a_$P$ + 67;
        d__1_ = i2 % 128;
        int i3 = i2 % 2;
        return str;
    }

    static String getBase64FromOtherAppsResource(Context context, String str) {
        int i = d__1_ + 81;
        a_$P$ = i % 128;
        int i2 = i % 2;
        PackageManager packageManager = context.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 128);
            String base64FromResource = getBase64FromResource(packageManager.getResourcesForApplication(applicationInfo), applicationInfo.icon);
            int i3 = a_$P$ + 107;
            d__1_ = i3 % 128;
            int i4 = i3 % 2;
            return base64FromResource;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [java.lang.Object, java.lang.String] */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0024, code lost:
        if ((r0 == null ? ' ' : 'D') != ' ') goto L_0x009a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002f, code lost:
        if (r0 == null) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004c, code lost:
        if (r7 != null) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0057, code lost:
        if (r1 != 19) goto L_0x009a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x006b, code lost:
        if (r0 != false) goto L_0x0079;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0070, code lost:
        if ((r7 instanceof android.graphics.drawable.BitmapDrawable) != false) goto L_0x0072;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0079, code lost:
        r0 = android.graphics.Bitmap.createBitmap(r7.getIntrinsicWidth(), r7.getIntrinsicHeight(), android.graphics.Bitmap.Config.ARGB_8888);
        r8 = new android.graphics.Canvas(r0);
        r7.setBounds(0, 0, r8.getWidth(), r8.getHeight());
        r7.draw(r8);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.String getBase64FromResource(android.content.res.Resources r7, int r8) {
        /*
            int r0 = d__1_
            r1 = 69
            int r0 = r0 + r1
            int r2 = r0 % 128
            a_$P$ = r2
            r2 = 2
            int r0 = r0 % r2
            r3 = 55
            if (r0 != 0) goto L_0x0012
            r0 = 55
            goto L_0x0013
        L_0x0012:
            r0 = 4
        L_0x0013:
            r4 = 0
            r5 = 0
            if (r0 == r3) goto L_0x0028
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeResource(r7, r8)
            r3 = 32
            if (r0 != 0) goto L_0x0022
            r6 = 32
            goto L_0x0024
        L_0x0022:
            r6 = 68
        L_0x0024:
            if (r6 == r3) goto L_0x0031
            goto L_0x009a
        L_0x0028:
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeResource(r7, r8)
            super.hashCode()
            if (r0 != 0) goto L_0x009a
        L_0x0031:
            int r3 = a_$P$
            int r3 = r3 + 17
            int r6 = r3 % 128
            d__1_ = r6
            int r3 = r3 % r2
            r6 = 19
            if (r3 == 0) goto L_0x0041
            r3 = 48
            goto L_0x0043
        L_0x0041:
            r3 = 19
        L_0x0043:
            if (r3 == r6) goto L_0x004f
            android.graphics.drawable.Drawable r7 = r7.getDrawable(r8)
            super.hashCode()
            if (r7 == 0) goto L_0x009a
            goto L_0x005a
        L_0x004f:
            android.graphics.drawable.Drawable r7 = r7.getDrawable(r8)
            if (r7 == 0) goto L_0x0057
            r1 = 19
        L_0x0057:
            if (r1 == r6) goto L_0x005a
            goto L_0x009a
        L_0x005a:
            int r8 = d__1_
            r0 = 1
            int r8 = r8 + r0
            int r1 = r8 % 128
            a_$P$ = r1
            int r8 = r8 % r2
            if (r8 != 0) goto L_0x006e
            boolean r8 = r7 instanceof android.graphics.drawable.BitmapDrawable
            int r1 = r5.length
            if (r8 == 0) goto L_0x006b
            r0 = 0
        L_0x006b:
            if (r0 == 0) goto L_0x0072
            goto L_0x0079
        L_0x006e:
            boolean r8 = r7 instanceof android.graphics.drawable.BitmapDrawable
            if (r8 == 0) goto L_0x0079
        L_0x0072:
            android.graphics.drawable.BitmapDrawable r7 = (android.graphics.drawable.BitmapDrawable) r7
            android.graphics.Bitmap r0 = r7.getBitmap()
            goto L_0x009a
        L_0x0079:
            int r8 = r7.getIntrinsicWidth()
            int r0 = r7.getIntrinsicHeight()
            android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.ARGB_8888
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createBitmap(r8, r0, r1)
            android.graphics.Canvas r8 = new android.graphics.Canvas
            r8.<init>(r0)
            int r1 = r8.getWidth()
            int r3 = r8.getHeight()
            r7.setBounds(r4, r4, r1, r3)
            r7.draw(r8)
        L_0x009a:
            if (r0 == 0) goto L_0x00d9
            java.lang.String r7 = "data:image/png;base64,"
            java.io.ByteArrayOutputStream r8 = new java.io.ByteArrayOutputStream
            r8.<init>()
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.PNG
            r3 = 100
            r0.compress(r1, r3, r8)
            byte[] r8 = r8.toByteArray()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r7)
            java.lang.String r7 = android.util.Base64.encodeToString(r8, r2)
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            int r8 = a_$P$
            int r8 = r8 + 35
            int r0 = r8 % 128
            d__1_ = r0
            int r8 = r8 % r2
            r0 = 23
            if (r8 == 0) goto L_0x00d1
            r8 = 91
            goto L_0x00d3
        L_0x00d1:
            r8 = 23
        L_0x00d3:
            if (r8 == r0) goto L_0x00d8
            r8 = 79
            int r8 = r8 / r4
        L_0x00d8:
            return r7
        L_0x00d9:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.razorpay.BaseUtils.getBase64FromResource(android.content.res.Resources, int):java.lang.String");
    }

    public static String getCellularNetworkProviderName(Context context) {
        TelephonyManager telephonyManager;
        int i = a_$P$ + 85;
        d__1_ = i % 128;
        if (i % 2 != 0) {
            telephonyManager = (TelephonyManager) getSystemService(context, "phone");
            Object obj = null;
            super.hashCode();
            if (!(telephonyManager != null)) {
                return "unknown";
            }
        } else {
            telephonyManager = (TelephonyManager) getSystemService(context, "phone");
            if ((telephonyManager != null ? 'R' : 'a') == 'a') {
                return "unknown";
            }
        }
        int i2 = a_$P$ + 9;
        d__1_ = i2 % 128;
        int i3 = i2 % 2;
        return telephonyManager.getNetworkOperatorName();
    }

    public static String getCellularNetworkType(Context context) {
        int i = a_$P$ + 59;
        d__1_ = i % 128;
        Object[] objArr = null;
        if (i % 2 != 0) {
            try {
                int networkType = ((TelephonyManager) context.getSystemService("phone")).getNetworkType();
                int length = objArr.length;
                switch (networkType) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        return "2G";
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        break;
                    case 13:
                        return "4G";
                    default:
                        return "NA";
                }
            } catch (Exception unused) {
                return "NA";
            }
        } else {
            switch (((TelephonyManager) context.getSystemService("phone")).getNetworkType()) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                    return "2G";
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                    break;
                case 13:
                    return "4G";
                default:
                    return "NA";
            }
        }
        int i2 = d__1_ + 119;
        a_$P$ = i2 % 128;
        if ((i2 % 2 == 0 ? '3' : 13) != '3') {
            return "3G";
        }
        super.hashCode();
        return "3G";
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003e, code lost:
        if ((r0 ? '9' : 'D') != '9') goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004e, code lost:
        if ((r2.isConnected() ? 'U' : 'Y') != 'U') goto L_0x0050;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.razorpay.Q$$U_ getDataNetworkType(android.content.Context r5) {
        /*
            java.lang.String r0 = "android.permission.ACCESS_NETWORK_STATE"
            boolean r0 = hasPermission(r5, r0)
            if (r0 == 0) goto L_0x0093
            java.lang.String r0 = "connectivity"
            java.lang.Object r5 = getSystemService(r5, r0)
            android.net.ConnectivityManager r5 = (android.net.ConnectivityManager) r5
            r0 = 1
            r1 = 0
            if (r5 == 0) goto L_0x0016
            r2 = 1
            goto L_0x0017
        L_0x0016:
            r2 = 0
        L_0x0017:
            if (r2 == 0) goto L_0x0093
            android.net.NetworkInfo r2 = r5.getNetworkInfo(r0)
            if (r2 == 0) goto L_0x0020
            r0 = 0
        L_0x0020:
            r3 = 0
            if (r0 == 0) goto L_0x0024
            goto L_0x0050
        L_0x0024:
            int r0 = d__1_
            int r0 = r0 + 113
            int r4 = r0 % 128
            a_$P$ = r4
            int r0 = r0 % 2
            if (r0 != 0) goto L_0x0041
            boolean r0 = r2.isConnected()
            int r2 = r3.length
            r2 = 57
            if (r0 == 0) goto L_0x003c
            r0 = 57
            goto L_0x003e
        L_0x003c:
            r0 = 68
        L_0x003e:
            if (r0 == r2) goto L_0x0086
            goto L_0x0050
        L_0x0041:
            boolean r0 = r2.isConnected()
            r2 = 85
            if (r0 == 0) goto L_0x004c
            r0 = 85
            goto L_0x004e
        L_0x004c:
            r0 = 89
        L_0x004e:
            if (r0 == r2) goto L_0x0086
        L_0x0050:
            r0 = 7
            android.net.NetworkInfo r0 = r5.getNetworkInfo(r0)
            if (r0 == 0) goto L_0x006a
            boolean r0 = r0.isConnected()
            if (r0 == 0) goto L_0x006a
            com.razorpay.Q$$U_ r5 = com.razorpay.Q$$U_.BLUETOOTH
            int r0 = d__1_
            int r0 = r0 + 11
            int r1 = r0 % 128
            a_$P$ = r1
            int r0 = r0 % 2
            return r5
        L_0x006a:
            android.net.NetworkInfo r5 = r5.getNetworkInfo(r1)
            if (r5 == 0) goto L_0x0093
            boolean r5 = r5.isConnected()
            if (r5 == 0) goto L_0x0093
            com.razorpay.Q$$U_ r5 = com.razorpay.Q$$U_.CELLULAR
            int r0 = a_$P$
            int r0 = r0 + 81
            int r1 = r0 % 128
            d__1_ = r1
            int r0 = r0 % 2
            if (r0 == 0) goto L_0x0085
            int r0 = r3.length
        L_0x0085:
            return r5
        L_0x0086:
            com.razorpay.Q$$U_ r5 = com.razorpay.Q$$U_.WIFI
            int r0 = d__1_
            int r0 = r0 + 33
            int r1 = r0 % 128
            a_$P$ = r1
            int r0 = r0 % 2
            return r5
        L_0x0093:
            com.razorpay.Q$$U_ r5 = com.razorpay.Q$$U_.UNKNOWN
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.razorpay.BaseUtils.getDataNetworkType(android.content.Context):com.razorpay.Q$$U_");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0036, code lost:
        if ("9774d56d682e549c".equals(r0) == false) goto L_0x0038;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002d, code lost:
        if ((!r1 ? '4' : '9') != '9') goto L_0x0038;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getDeviceId(android.content.Context r4) {
        /*
            android.content.ContentResolver r0 = r4.getContentResolver()
            java.lang.String r1 = "android_id"
            java.lang.String r0 = android.provider.Settings.Secure.getString(r0, r1)
            boolean r1 = com.razorpay.AnalyticsUtil.isNullOrEmpty(r0)
            r2 = 0
            if (r1 != 0) goto L_0x0053
            int r1 = a_$P$
            int r1 = r1 + 99
            int r3 = r1 % 128
            d__1_ = r3
            int r1 = r1 % 2
            if (r1 == 0) goto L_0x0030
            java.lang.String r1 = "9774d56d682e549c"
            boolean r1 = r1.equals(r0)
            int r3 = r2.length
            r3 = 57
            if (r1 != 0) goto L_0x002b
            r1 = 52
            goto L_0x002d
        L_0x002b:
            r1 = 57
        L_0x002d:
            if (r1 == r3) goto L_0x0053
            goto L_0x0038
        L_0x0030:
            java.lang.String r1 = "9774d56d682e549c"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0053
        L_0x0038:
            java.lang.String r1 = "unknown"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0053
            java.lang.String r1 = "000000000000000"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0053
            int r4 = a_$P$
            int r4 = r4 + 107
            int r1 = r4 % 128
            d__1_ = r1
            int r4 = r4 % 2
            return r0
        L_0x0053:
            java.lang.String r0 = android.os.Build.SERIAL
            boolean r0 = com.razorpay.AnalyticsUtil.isNullOrEmpty(r0)
            if (r0 != 0) goto L_0x0070
            int r4 = a_$P$
            int r4 = r4 + 1
            int r0 = r4 % 128
            d__1_ = r0
            int r4 = r4 % 2
            if (r4 == 0) goto L_0x006d
            java.lang.String r4 = android.os.Build.SERIAL
            super.hashCode()
            return r4
        L_0x006d:
            java.lang.String r4 = android.os.Build.SERIAL
            return r4
        L_0x0070:
            java.lang.String r0 = "android.permission.READ_PHONE_STATE"
            boolean r0 = hasPermission(r4, r0)
            r1 = 38
            if (r0 == 0) goto L_0x007d
            r0 = 49
            goto L_0x007f
        L_0x007d:
            r0 = 38
        L_0x007f:
            if (r0 == r1) goto L_0x00b2
            java.lang.String r0 = "android.hardware.telephony"
            boolean r0 = hasFeature(r4, r0)
            r1 = 55
            if (r0 == 0) goto L_0x008e
            r0 = 55
            goto L_0x008f
        L_0x008e:
            r0 = 5
        L_0x008f:
            if (r0 == r1) goto L_0x0092
            goto L_0x00b2
        L_0x0092:
            java.lang.String r0 = "phone"
            java.lang.Object r4 = getSystemService(r4, r0)
            android.telephony.TelephonyManager r4 = (android.telephony.TelephonyManager) r4
            java.lang.String r4 = r4.getDeviceId()
            boolean r0 = com.razorpay.AnalyticsUtil.isNullOrEmpty(r4)
            if (r0 != 0) goto L_0x00b2
            int r0 = d__1_
            int r0 = r0 + 31
            int r1 = r0 % 128
            a_$P$ = r1
            int r0 = r0 % 2
            if (r0 != 0) goto L_0x00b1
            int r0 = r2.length
        L_0x00b1:
            return r4
        L_0x00b2:
            java.util.UUID r4 = java.util.UUID.randomUUID()
            java.lang.String r4 = r4.toString()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.razorpay.BaseUtils.getDeviceId(android.content.Context):java.lang.String");
    }

    static int getDisplayHeight(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.heightPixels;
        int i2 = a_$P$ + 111;
        d__1_ = i2 % 128;
        int i3 = i2 % 2;
        return i;
    }

    static int getDisplayWidth(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = a_$P$ + 67;
        d__1_ = i2 % 128;
        int i3 = i2 % 2;
        return i;
    }

    public static String getFileFromInternal(Activity activity, String str, String str2) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(activity.openFileInput(getVersionedAssetName(getLocalVersion(activity, str2).toString(), str)), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if ((readLine != null ? 2 : '^') != 2) {
                bufferedReader.close();
                String decryptFile = decryptFile(sb.toString());
                int i = a_$P$ + 111;
                d__1_ = i % 128;
                int i2 = i % 2;
                return decryptFile;
            }
            int i3 = d__1_ + 99;
            a_$P$ = i3 % 128;
            if (!(i3 % 2 != 0)) {
                sb.append(readLine);
                int i4 = 47 / 0;
            } else {
                sb.append(readLine);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0023, code lost:
        if (r5 != null) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        if (r5 != null) goto L_0x002f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static org.json.JSONObject getJSONFromIntentData(android.content.Intent r5) {
        /*
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            r1 = 1
            r2 = 0
            if (r5 == 0) goto L_0x000b
            r3 = 1
            goto L_0x000c
        L_0x000b:
            r3 = 0
        L_0x000c:
            if (r3 == 0) goto L_0x0057
            int r3 = d__1_
            int r3 = r3 + 47
            int r4 = r3 % 128
            a_$P$ = r4
            int r3 = r3 % 2
            if (r3 != 0) goto L_0x001c
            r3 = 1
            goto L_0x001d
        L_0x001c:
            r3 = 0
        L_0x001d:
            if (r3 == r1) goto L_0x0026
            android.os.Bundle r5 = r5.getExtras()
            if (r5 == 0) goto L_0x0057
            goto L_0x002f
        L_0x0026:
            android.os.Bundle r5 = r5.getExtras()
            r1 = 45
            int r1 = r1 / r2
            if (r5 == 0) goto L_0x0057
        L_0x002f:
            java.util.Set r1 = r5.keySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x0037:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0057
            int r2 = a_$P$
            int r2 = r2 + 79
            int r3 = r2 % 128
            d__1_ = r3
            int r2 = r2 % 2
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r3 = r5.get(r2)     // Catch:{ JSONException -> 0x0055 }
            r0.put(r2, r3)     // Catch:{ JSONException -> 0x0055 }
            goto L_0x0037
        L_0x0055:
            goto L_0x0037
        L_0x0057:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.razorpay.BaseUtils.getJSONFromIntentData(android.content.Intent):org.json.JSONObject");
    }

    static Object getJsonValue(String str, JSONObject jSONObject, Object obj) {
        boolean z = false;
        Object jsonValue = getJsonValue(str.split("\\."), (Object) jSONObject, 0);
        if ((jsonValue != null ? 'R' : 4) != 'R') {
            int i = a_$P$ + 45;
            d__1_ = i % 128;
            if (i % 2 == 0) {
                z = true;
            }
            if (!z) {
                Object obj2 = null;
                super.hashCode();
            }
            return obj;
        }
        int i2 = a_$P$ + 37;
        d__1_ = i2 % 128;
        int i3 = i2 % 2;
        return jsonValue;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v12, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v13, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v15, resolved type: java.lang.Object} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Object getJsonValue(java.lang.String[] r6, java.lang.Object r7, int r8) {
        /*
        L_0x0000:
            int r0 = r6.length
            r1 = 0
            r2 = 1
            if (r8 != r0) goto L_0x0007
            r0 = 0
            goto L_0x0008
        L_0x0007:
            r0 = 1
        L_0x0008:
            if (r0 == 0) goto L_0x006e
            r0 = r6[r8]
            boolean r3 = r7 instanceof org.json.JSONObject
            if (r3 == 0) goto L_0x0019
            org.json.JSONObject r7 = (org.json.JSONObject) r7
            java.lang.Object r7 = r7.opt(r0)
        L_0x0016:
            int r8 = r8 + 1
            goto L_0x0000
        L_0x0019:
            boolean r3 = r7 instanceof org.json.JSONArray
            if (r3 == 0) goto L_0x001f
            r3 = 1
            goto L_0x0020
        L_0x001f:
            r3 = 0
        L_0x0020:
            r4 = 2
            if (r3 == 0) goto L_0x0061
            int r3 = d__1_
            int r3 = r3 + 7
            int r5 = r3 % 128
            a_$P$ = r5
            int r3 = r3 % r4
            r5 = 99
            if (r3 != 0) goto L_0x0032
            r3 = 2
            goto L_0x0034
        L_0x0032:
            r3 = 99
        L_0x0034:
            if (r3 == r5) goto L_0x004b
            org.json.JSONArray r7 = (org.json.JSONArray) r7
            boolean r2 = android.text.TextUtils.isDigitsOnly(r0)
            r3 = 56
            int r3 = r3 / r1
            r1 = 66
            if (r2 == 0) goto L_0x0046
            r2 = 57
            goto L_0x0048
        L_0x0046:
            r2 = 66
        L_0x0048:
            if (r2 == r1) goto L_0x0061
            goto L_0x0058
        L_0x004b:
            org.json.JSONArray r7 = (org.json.JSONArray) r7
            boolean r3 = android.text.TextUtils.isDigitsOnly(r0)
            if (r3 == 0) goto L_0x0054
            goto L_0x0055
        L_0x0054:
            r1 = 1
        L_0x0055:
            if (r1 == 0) goto L_0x0058
            goto L_0x0061
        L_0x0058:
            int r0 = java.lang.Integer.parseInt(r0)
            java.lang.Object r7 = r7.opt(r0)
            goto L_0x0016
        L_0x0061:
            int r6 = d__1_
            int r6 = r6 + 3
            int r7 = r6 % 128
            a_$P$ = r7
            int r6 = r6 % r4
            r7 = 0
            if (r6 != 0) goto L_0x006e
            int r6 = r7.length
        L_0x006e:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.razorpay.BaseUtils.getJsonValue(java.lang.String[], java.lang.Object, int):java.lang.Object");
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.String] */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0040, code lost:
        if (r3.metaData == null) goto L_0x0042;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.String getKeyId(android.content.Context r3) {
        /*
            int r0 = a_$P$
            int r0 = r0 + 17
            int r1 = r0 % 128
            d__1_ = r1
            int r0 = r0 % 2
            if (r0 == 0) goto L_0x000e
            r0 = 1
            goto L_0x000f
        L_0x000e:
            r0 = 0
        L_0x000f:
            r1 = 0
            if (r0 == 0) goto L_0x0030
            android.content.pm.PackageManager r0 = r3.getPackageManager()     // Catch:{ NameNotFoundException -> 0x002e }
            java.lang.String r3 = r3.getPackageName()     // Catch:{ NameNotFoundException -> 0x002e }
            r2 = 14731(0x398b, float:2.0643E-41)
            android.content.pm.ApplicationInfo r3 = r0.getApplicationInfo(r3, r2)     // Catch:{ NameNotFoundException -> 0x002e }
            android.os.Bundle r0 = r3.metaData     // Catch:{ NameNotFoundException -> 0x002e }
            r2 = 56
            if (r0 != 0) goto L_0x0029
            r0 = 56
            goto L_0x002b
        L_0x0029:
            r0 = 96
        L_0x002b:
            if (r0 == r2) goto L_0x0042
            goto L_0x0050
        L_0x002e:
            r3 = move-exception
            goto L_0x0059
        L_0x0030:
            android.content.pm.PackageManager r0 = r3.getPackageManager()     // Catch:{ NameNotFoundException -> 0x002e }
            java.lang.String r3 = r3.getPackageName()     // Catch:{ NameNotFoundException -> 0x002e }
            r2 = 128(0x80, float:1.794E-43)
            android.content.pm.ApplicationInfo r3 = r0.getApplicationInfo(r3, r2)     // Catch:{ NameNotFoundException -> 0x002e }
            android.os.Bundle r0 = r3.metaData     // Catch:{ NameNotFoundException -> 0x002e }
            if (r0 != 0) goto L_0x0050
        L_0x0042:
            int r3 = d__1_
            int r3 = r3 + 21
            int r0 = r3 % 128
            a_$P$ = r0
            int r3 = r3 % 2
            if (r3 != 0) goto L_0x004f
            int r3 = r1.length
        L_0x004f:
            return r1
        L_0x0050:
            android.os.Bundle r3 = r3.metaData     // Catch:{ NameNotFoundException -> 0x002e }
            java.lang.String r0 = "com.razorpay.ApiKey"
            java.lang.String r3 = r3.getString(r0)     // Catch:{ NameNotFoundException -> 0x002e }
            return r3
        L_0x0059:
            java.lang.String r0 = "critical"
            java.lang.String r2 = r3.getMessage()
            com.razorpay.AnalyticsUtil.reportError(r3, r0, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.razorpay.BaseUtils.getKeyId(android.content.Context):java.lang.String");
    }

    static List<ResolveInfo> getListOfAppsWhichHandleDeepLink(Context context, String str) {
        Intent intent = new Intent();
        intent.setData(Uri.parse(str));
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 131072);
        int i = a_$P$ + 3;
        d__1_ = i % 128;
        int i2 = i % 2;
        return queryIntentActivities;
    }

    public static String getLocalVersion(Activity activity, String str) {
        int i = d__1_ + 109;
        a_$P$ = i % 128;
        int i2 = i % 2;
        String R$$r_ = Q__v$.R$$r_(activity, str);
        if (!(R$$r_ == null)) {
            return R$$r_;
        }
        int i3 = d__1_ + 57;
        a_$P$ = i3 % 128;
        int i4 = i3 % 2;
        return getVersionFromJsonString("{\n  \"hash\" : \"\",\n  \"magic_hash\": \"\"\n}\n", str);
    }

    public static String getLocale() {
        StringBuilder sb = new StringBuilder();
        sb.append(Locale.getDefault().getLanguage());
        sb.append("-");
        sb.append(Locale.getDefault().getCountry());
        String obj = sb.toString();
        int i = d__1_ + 71;
        a_$P$ = i % 128;
        if (!(i % 2 != 0)) {
            Object[] objArr = null;
            int length = objArr.length;
        }
        return obj;
    }

    static HashMap<String, String> getMapFromJSONObject(JSONObject jSONObject) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            Iterator<String> keys = jSONObject.keys();
            while (true) {
                if (!(keys.hasNext())) {
                    break;
                }
                int i = d__1_ + 7;
                a_$P$ = i % 128;
                int i2 = i % 2;
                String next = keys.next();
                hashMap.put(next, jSONObject.getString(next));
                int i3 = a_$P$ + 59;
                d__1_ = i3 % 128;
                if (i3 % 2 != 0) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    static int getNetworkType(Context context) {
        Q$$U_ dataNetworkType = getDataNetworkType(context);
        boolean z = false;
        if (dataNetworkType == Q$$U_.WIFI) {
            int i = a_$P$ + 71;
            d__1_ = i % 128;
            int i2 = i % 2;
            return 0;
        }
        if (!(dataNetworkType != Q$$U_.BLUETOOTH)) {
            return 1;
        }
        if ((dataNetworkType == Q$$U_.CELLULAR ? 'B' : '2') == '2') {
            return -1;
        }
        int i3 = d__1_ + 81;
        a_$P$ = i3 % 128;
        int i4 = i3 % 2;
        String cellularNetworkType = getCellularNetworkType(context);
        if (cellularNetworkType.equalsIgnoreCase("2G")) {
            return 2;
        }
        if (!(!cellularNetworkType.equalsIgnoreCase("3G"))) {
            return 3;
        }
        if (cellularNetworkType.equalsIgnoreCase("4G")) {
            z = true;
        }
        if (!z) {
            return -1;
        }
        int i5 = a_$P$ + 31;
        d__1_ = i5 % 128;
        if ((i5 % 2 != 0 ? 'V' : 'T') != 'V') {
        }
        return 4;
    }

    static String getRandomString() {
        String bigInteger = new BigInteger(130, new SecureRandom()).toString(32);
        int i = a_$P$ + 89;
        d__1_ = i % 128;
        int i2 = i % 2;
        return bigInteger;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002f, code lost:
        if (r5.size() > 0) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003e, code lost:
        if ((r5.size() > 0) != false) goto L_0x0040;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.util.HashSet<java.lang.String> getSetOfPackageNamesSupportingUpi(android.content.Context r5) {
        /*
            java.lang.String r0 = "upi://pay"
            java.util.List r5 = getListOfAppsWhichHandleDeepLink(r5, r0)
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            r1 = 17
            if (r5 == 0) goto L_0x0012
            r2 = 17
            goto L_0x0014
        L_0x0012:
            r2 = 9
        L_0x0014:
            if (r2 == r1) goto L_0x0018
            goto L_0x0092
        L_0x0018:
            int r1 = a_$P$
            int r1 = r1 + 67
            int r2 = r1 % 128
            d__1_ = r2
            int r1 = r1 % 2
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x0028
            r1 = 0
            goto L_0x0029
        L_0x0028:
            r1 = 1
        L_0x0029:
            if (r1 == 0) goto L_0x0032
            int r1 = r5.size()
            if (r1 <= 0) goto L_0x0092
            goto L_0x0040
        L_0x0032:
            int r1 = r5.size()
            r4 = 96
            int r4 = r4 / r2
            if (r1 <= 0) goto L_0x003d
            r1 = 1
            goto L_0x003e
        L_0x003d:
            r1 = 0
        L_0x003e:
            if (r1 == 0) goto L_0x0092
        L_0x0040:
            java.util.Iterator r5 = r5.iterator()
            int r1 = d__1_
            int r1 = r1 + 65
            int r4 = r1 % 128
            a_$P$ = r4
            int r1 = r1 % 2
            if (r1 != 0) goto L_0x0052
            r1 = 0
            goto L_0x0053
        L_0x0052:
            r1 = 1
        L_0x0053:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L_0x005b
            r1 = 1
            goto L_0x005c
        L_0x005b:
            r1 = 0
        L_0x005c:
            if (r1 == r3) goto L_0x005f
            goto L_0x0092
        L_0x005f:
            int r1 = a_$P$
            int r1 = r1 + 111
            int r4 = r1 % 128
            d__1_ = r4
            int r1 = r1 % 2
            if (r1 == 0) goto L_0x006d
            r1 = 0
            goto L_0x006e
        L_0x006d:
            r1 = 1
        L_0x006e:
            if (r1 == 0) goto L_0x0080
            java.lang.Object r1 = r5.next()
            android.content.pm.ResolveInfo r1 = (android.content.pm.ResolveInfo) r1
            android.content.pm.ActivityInfo r1 = r1.activityInfo     // Catch:{ Exception -> 0x007e }
            java.lang.String r1 = r1.packageName     // Catch:{ Exception -> 0x007e }
            r0.add(r1)     // Catch:{ Exception -> 0x007e }
            goto L_0x0053
        L_0x007e:
            goto L_0x0053
        L_0x0080:
            java.lang.Object r1 = r5.next()
            android.content.pm.ResolveInfo r1 = (android.content.pm.ResolveInfo) r1
            android.content.pm.ActivityInfo r1 = r1.activityInfo     // Catch:{ Exception -> 0x007e }
            java.lang.String r1 = r1.packageName     // Catch:{ Exception -> 0x007e }
            r0.add(r1)     // Catch:{ Exception -> 0x007e }
            r1 = 0
            super.hashCode()     // Catch:{ Exception -> 0x007e }
            goto L_0x0053
        L_0x0092:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.razorpay.BaseUtils.getSetOfPackageNamesSupportingUpi(android.content.Context):java.util.HashSet");
    }

    public static <T> T getSystemService(Context context, String str) {
        int i = d__1_ + 65;
        a_$P$ = i % 128;
        int i2 = i % 2;
        T systemService = context.getApplicationContext().getSystemService(str);
        int i3 = a_$P$ + 55;
        d__1_ = i3 % 128;
        if ((i3 % 2 != 0 ? Typography.less : '^') != '<') {
            return systemService;
        }
        Object[] objArr = null;
        int length = objArr.length;
        return systemService;
    }

    public static String getVersionFromJsonString(String str, String str2) {
        boolean z = true;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (str2.equalsIgnoreCase("otpelf_version")) {
                int i = d__1_ + 43;
                a_$P$ = i % 128;
                int i2 = i % 2;
                return jSONObject.getString("hash");
            }
            if (str2.equalsIgnoreCase("magic_version")) {
                return jSONObject.getString("magic_hash");
            }
            int i3 = a_$P$ + 95;
            d__1_ = i3 % 128;
            if (i3 % 2 == 0) {
                z = false;
            }
            if (z) {
                int i4 = 87 / 0;
            }
            return null;
        } catch (Exception unused) {
        }
    }

    static String getVersionedAssetName(String str, String str2) {
        String replaceAll = str.replaceAll("\\.", "-");
        StringBuilder sb = new StringBuilder();
        sb.append(replaceAll);
        sb.append("-");
        sb.append(str2);
        String obj = sb.toString();
        int i = d__1_ + 11;
        a_$P$ = i % 128;
        if (i % 2 != 0) {
            return obj;
        }
        Object obj2 = null;
        super.hashCode();
        return obj;
    }

    public static CharSequence getWebViewUserAgent(Context context) {
        CharSequence returnUndefinedIfNull = AnalyticsUtil.returnUndefinedIfNull(new WebView(context).getSettings().getUserAgentString());
        int i = a_$P$ + 77;
        d__1_ = i % 128;
        if (i % 2 != 0) {
            int i2 = 23 / 0;
        }
        return returnUndefinedIfNull;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [java.security.cert.Certificate, java.lang.Object] */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0032, code lost:
        if (r4 == null) goto L_0x0034;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r4 = java.security.cert.CertificateFactory.getInstance("X.509").generateCertificate(new java.io.ByteArrayInputStream(r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004e, code lost:
        r0 = a_$P$ + 91;
        d__1_ = r0 % 128;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0058, code lost:
        if ((r0 % 2) == 0) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005a, code lost:
        r0 = r3.length;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005b, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005c, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0025, code lost:
        if (r1 != true) goto L_0x0034;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.security.cert.Certificate getX509Certificate(android.net.http.SslCertificate r4) {
        /*
            int r0 = d__1_
            int r0 = r0 + 49
            int r1 = r0 % 128
            a_$P$ = r1
            int r0 = r0 % 2
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0010
            r0 = 0
            goto L_0x0011
        L_0x0010:
            r0 = 1
        L_0x0011:
            r3 = 0
            if (r0 == r2) goto L_0x0028
            android.os.Bundle r4 = android.net.http.SslCertificate.saveState(r4)
            java.lang.String r0 = "x509-certificate"
            byte[] r4 = r4.getByteArray(r0)
            super.hashCode()
            if (r4 != 0) goto L_0x0024
            goto L_0x0025
        L_0x0024:
            r1 = 1
        L_0x0025:
            if (r1 == r2) goto L_0x003f
            goto L_0x0034
        L_0x0028:
            android.os.Bundle r4 = android.net.http.SslCertificate.saveState(r4)
            java.lang.String r0 = "x509-certificate"
            byte[] r4 = r4.getByteArray(r0)
            if (r4 != 0) goto L_0x003f
        L_0x0034:
            int r4 = d__1_
            int r4 = r4 + 87
            int r0 = r4 % 128
            a_$P$ = r0
            int r4 = r4 % 2
            return r3
        L_0x003f:
            java.lang.String r0 = "X.509"
            java.security.cert.CertificateFactory r0 = java.security.cert.CertificateFactory.getInstance(r0)     // Catch:{ CertificateException -> 0x005c }
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch:{ CertificateException -> 0x005c }
            r1.<init>(r4)     // Catch:{ CertificateException -> 0x005c }
            java.security.cert.Certificate r4 = r0.generateCertificate(r1)     // Catch:{ CertificateException -> 0x005c }
            int r0 = a_$P$
            int r0 = r0 + 91
            int r1 = r0 % 128
            d__1_ = r1
            int r0 = r0 % 2
            if (r0 == 0) goto L_0x005b
            int r0 = r3.length
        L_0x005b:
            return r4
        L_0x005c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.razorpay.BaseUtils.getX509Certificate(android.net.http.SslCertificate):java.security.cert.Certificate");
    }

    static boolean hasFeature(Context context, String str) {
        int i = d__1_ + 119;
        a_$P$ = i % 128;
        if ((i % 2 == 0 ? 'W' : 22) == 22) {
            return context.getPackageManager().hasSystemFeature(str);
        }
        boolean hasSystemFeature = context.getPackageManager().hasSystemFeature(str);
        Object[] objArr = null;
        int length = objArr.length;
        return hasSystemFeature;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002a, code lost:
        if ((r3.checkCallingOrSelfPermission(r4) != 0) != false) goto L_0x002c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean hasPermission(android.content.Context r3, java.lang.String r4) {
        /*
            int r0 = d__1_
            int r0 = r0 + 69
            int r1 = r0 % 128
            a_$P$ = r1
            int r0 = r0 % 2
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0010
            r0 = 0
            goto L_0x0011
        L_0x0010:
            r0 = 1
        L_0x0011:
            if (r0 == r2) goto L_0x0021
            int r3 = r3.checkCallingOrSelfPermission(r4)     // Catch:{ Exception -> 0x001f }
            if (r2 != r3) goto L_0x001b
            r3 = 0
            goto L_0x001c
        L_0x001b:
            r3 = 1
        L_0x001c:
            if (r3 == r2) goto L_0x002c
            goto L_0x002d
        L_0x001f:
            r3 = move-exception
            goto L_0x003c
        L_0x0021:
            int r3 = r3.checkCallingOrSelfPermission(r4)     // Catch:{ Exception -> 0x001f }
            if (r3 != 0) goto L_0x0029
            r3 = 0
            goto L_0x002a
        L_0x0029:
            r3 = 1
        L_0x002a:
            if (r3 == 0) goto L_0x002d
        L_0x002c:
            return r1
        L_0x002d:
            int r3 = a_$P$
            int r3 = r3 + 119
            int r4 = r3 % 128
            d__1_ = r4
            int r3 = r3 % 2
            if (r3 == 0) goto L_0x003a
            goto L_0x003b
        L_0x003a:
            r1 = 1
        L_0x003b:
            return r1
        L_0x003c:
            java.lang.String r4 = "critical"
            java.lang.String r0 = r3.getMessage()
            com.razorpay.AnalyticsUtil.reportError(r3, r4, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.razorpay.BaseUtils.hasPermission(android.content.Context, java.lang.String):boolean");
    }

    static boolean isDeviceHaveCorrectTlsVersion() {
        try {
            String[] protocols = SSLContext.getDefault().getDefaultSSLParameters().getProtocols();
            if (protocols == null) {
                int i = d__1_ + 87;
                a_$P$ = i % 128;
                return i % 2 == 0;
            }
            int length = protocols.length;
            int i2 = 0;
            while (true) {
                if ((i2 < length ? (char) 22 : 27) != 27) {
                    String str = protocols[i2];
                    if (str.startsWith("TLS")) {
                        int i3 = d__1_ + 9;
                        a_$P$ = i3 % 128;
                        int i4 = i3 % 2;
                        if (!(str.equalsIgnoreCase("TLSv1"))) {
                            return true;
                        }
                    }
                    i2++;
                } else {
                    int i5 = a_$P$ + 117;
                    d__1_ = i5 % 128;
                    if (i5 % 2 != 0) {
                    }
                }
            }
            return false;
        } catch (NoSuchAlgorithmException unused) {
        }
    }

    static boolean isMerchantAppDebuggable(Context context) {
        int i = d__1_ + 101;
        a_$P$ = i % 128;
        int i2 = i % 2;
        if (((context.getApplicationInfo().flags & 2) != 0 ? '7' : '+') == '+') {
            return false;
        }
        int i3 = a_$P$ + 11;
        d__1_ = i3 % 128;
        if ((i3 % 2 != 0 ? 'F' : '^') != 'F') {
            return true;
        }
        Object[] objArr = null;
        int length = objArr.length;
        return true;
    }

    static ArrayList<String> jsonStringArrayToArrayList(JSONArray jSONArray) {
        ArrayList<String> arrayList = new ArrayList<>();
        int i = 0;
        while (true) {
            if (!(i < jSONArray.length())) {
                return arrayList;
            }
            int i2 = a_$P$ + 57;
            d__1_ = i2 % 128;
            int i3 = i2 % 2;
            arrayList.add(jSONArray.getString(i));
            i++;
            int i4 = d__1_ + 43;
            a_$P$ = i4 % 128;
            if (i4 % 2 == 0) {
            }
        }
    }

    public static String nanoTimeToSecondsString(long j, int i) {
        int i2 = d__1_ + 87;
        a_$P$ = i2 % 128;
        String valueOf = String.valueOf(round(!(i2 % 2 != 0) ? ((double) j) + 1.0E9d : ((double) j) / 1.0E9d, i));
        int i3 = a_$P$ + 45;
        d__1_ = i3 % 128;
        if ((i3 % 2 != 0 ? 'C' : 'Z') != 'C') {
            return valueOf;
        }
        Object obj = null;
        super.hashCode();
        return valueOf;
    }

    public static double round(double d, int i) {
        int i2 = d__1_ + 17;
        a_$P$ = i2 % 128;
        int i3 = i2 % 2;
        if (i >= 0) {
            double doubleValue = new BigDecimal(d).setScale(i, RoundingMode.HALF_UP).doubleValue();
            int i4 = a_$P$ + 89;
            d__1_ = i4 % 128;
            int i5 = i4 % 2;
            return doubleValue;
        }
        throw new IllegalArgumentException();
    }

    private static void setBaseWebViewSettings() {
        int i = d__1_ + 47;
        a_$P$ = i % 128;
        int i2 = i % 2;
        if ((Build.VERSION.SDK_INT >= 19 ? 'V' : 'O') != 'O') {
            int i3 = a_$P$ + 113;
            d__1_ = i3 % 128;
            int i4 = i3 % 2;
            WebView.setWebContentsDebuggingEnabled(sWebViewDebuggingEnabled);
            int i5 = d__1_ + 19;
            a_$P$ = i5 % 128;
            int i6 = i5 % 2;
        }
    }

    static void setWebViewSettings(Context context, WebView webView, boolean z) {
        setBaseWebViewSettings();
        enableJavaScriptInWebView(webView);
        CookieManager.getInstance().setAcceptCookie(true);
        WebSettings settings = webView.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        String path = context.getApplicationContext().getDir("database", 0).getPath();
        if (Build.VERSION.SDK_INT < 19) {
            int i = a_$P$ + 17;
            d__1_ = i % 128;
            if (i % 2 != 0) {
                settings.setDatabasePath(path);
                Object[] objArr = null;
                int length = objArr.length;
            } else {
                settings.setDatabasePath(path);
            }
        }
        if (!(Build.VERSION.SDK_INT >= 24)) {
            int i2 = d__1_ + 21;
            a_$P$ = i2 % 128;
            int i3 = i2 % 2;
            settings.setGeolocationDatabasePath(path);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
            int i4 = d__1_ + 69;
            a_$P$ = i4 % 128;
            int i5 = i4 % 2;
        }
        if (z) {
            int i6 = a_$P$ + 5;
            d__1_ = i6 % 128;
            int i7 = i6 % 2;
            settings.setCacheMode(2);
            int i8 = d__1_ + 29;
            a_$P$ = i8 % 128;
            int i9 = i8 % 2;
        }
        settings.setSaveFormData(false);
        webView.addJavascriptInterface(new N$$J$(context), "StorageBridge");
        int i10 = d__1_ + 75;
        a_$P$ = i10 % 128;
        int i11 = i10 % 2;
    }

    static void setup() {
        int i = a_$P$ + 85;
        d__1_ = i % 128;
        boolean z = false;
        if ((i % 2 != 0 ? '5' : 'X') != '5') {
            AnalyticsUtil.reset();
        } else {
            AnalyticsUtil.reset();
            int i2 = 72 / 0;
        }
        int i3 = a_$P$ + 7;
        d__1_ = i3 % 128;
        if (i3 % 2 != 0) {
            z = true;
        }
        if (z) {
            Object[] objArr = null;
            int length = objArr.length;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0035, code lost:
        if (r5 > 0) goto L_0x003e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003c, code lost:
        if (r6.length() > 0) goto L_0x003e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void startActivityForResult(java.lang.String r5, java.lang.String r6, android.app.Activity r7) {
        /*
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "android.intent.action.VIEW"
            r0.<init>(r1)
            android.net.Uri r5 = android.net.Uri.parse(r5)
            r0.setData(r5)
            r5 = 48
            if (r6 == 0) goto L_0x0015
            r1 = 48
            goto L_0x0017
        L_0x0015:
            r1 = 47
        L_0x0017:
            r2 = 0
            if (r1 == r5) goto L_0x001b
            goto L_0x0058
        L_0x001b:
            int r5 = d__1_
            int r5 = r5 + 3
            int r1 = r5 % 128
            a_$P$ = r1
            int r5 = r5 % 2
            r1 = 0
            r3 = 1
            if (r5 != 0) goto L_0x002b
            r5 = 0
            goto L_0x002c
        L_0x002b:
            r5 = 1
        L_0x002c:
            if (r5 == r3) goto L_0x0038
            int r5 = r6.length()
            super.hashCode()
            if (r5 <= 0) goto L_0x0058
            goto L_0x003e
        L_0x0038:
            int r5 = r6.length()
            if (r5 <= 0) goto L_0x0058
        L_0x003e:
            int r5 = d__1_
            int r5 = r5 + 75
            int r4 = r5 % 128
            a_$P$ = r4
            int r5 = r5 % 2
            if (r5 != 0) goto L_0x004b
            goto L_0x004c
        L_0x004b:
            r1 = 1
        L_0x004c:
            if (r1 == r3) goto L_0x0055
            r0.setPackage(r6)
            super.hashCode()
            goto L_0x0058
        L_0x0055:
            r0.setPackage(r6)
        L_0x0058:
            r5 = 99
            r7.startActivityForResult(r0, r5)
            int r5 = a_$P$
            int r5 = r5 + 57
            int r6 = r5 % 128
            d__1_ = r6
            int r5 = r5 % 2
            r6 = 71
            if (r5 == 0) goto L_0x006e
            r5 = 70
            goto L_0x0070
        L_0x006e:
            r5 = 71
        L_0x0070:
            if (r5 == r6) goto L_0x0073
            int r5 = r2.length
        L_0x0073:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.razorpay.BaseUtils.startActivityForResult(java.lang.String, java.lang.String, android.app.Activity):void");
    }

    static boolean storeFileInInternal(Activity activity, String str, String str2) {
        int i = d__1_ + 11;
        a_$P$ = i % 128;
        int i2 = i % 2;
        try {
            FileOutputStream openFileOutput = activity.openFileOutput(str, 0);
            openFileOutput.write(str2.getBytes());
            openFileOutput.close();
            int i3 = a_$P$ + 115;
            d__1_ = i3 % 128;
            if ((i3 % 2 != 0 ? Typography.dollar : '_') != '_') {
                int i4 = 45 / 0;
            }
            return true;
        } catch (Exception e) {
            AnalyticsUtil.reportError(e, "error", "Error in saving file: ".concat(String.valueOf(str)));
            return false;
        }
    }

    static void updateLocalVersion(Activity activity, String str, String str2) {
        int i = d__1_ + 101;
        a_$P$ = i % 128;
        int i2 = i % 2;
        Q__v$.R$$r_(activity, str, str2);
        int i3 = a_$P$ + 71;
        d__1_ = i3 % 128;
        int i4 = i3 % 2;
    }
}
