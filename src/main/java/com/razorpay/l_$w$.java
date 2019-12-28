package com.razorpay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import org.json.JSONArray;
import org.json.JSONObject;

final class l_$w$ {
    l_$w$() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.String G__G_(android.content.Context r11, org.json.JSONArray r12) {
        /*
            r0 = 0
            if (r12 == 0) goto L_0x00da
            int r1 = r12.length()
            if (r1 != 0) goto L_0x000b
            goto L_0x00da
        L_0x000b:
            int r1 = r12.length()
            r2 = 0
            r3 = 1
            if (r1 != r3) goto L_0x0019
        L_0x0013:
            org.json.JSONObject r12 = r12.getJSONObject(r2)     // Catch:{ Exception -> 0x00ae }
            goto L_0x00af
        L_0x0019:
            int r1 = r12.length()
            if (r1 == r3) goto L_0x00ae
            java.lang.String r1 = "{"
            r7 = r0
            r4 = r1
            r1 = 0
            r5 = 0
            r6 = 1
        L_0x0026:
            int r8 = r12.length()
            if (r1 >= r8) goto L_0x008b
            org.json.JSONObject r8 = r12.getJSONObject(r1)     // Catch:{ Throwable -> 0x0088 }
            if (r6 != 0) goto L_0x0044
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0088 }
            r9.<init>()     // Catch:{ Throwable -> 0x0088 }
            r9.append(r4)     // Catch:{ Throwable -> 0x0088 }
            java.lang.String r10 = ","
            r9.append(r10)     // Catch:{ Throwable -> 0x0088 }
            java.lang.String r4 = r9.toString()     // Catch:{ Throwable -> 0x0088 }
            goto L_0x0045
        L_0x0044:
            r6 = 0
        L_0x0045:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0088 }
            r9.<init>()     // Catch:{ Throwable -> 0x0088 }
            r9.append(r4)     // Catch:{ Throwable -> 0x0088 }
            java.lang.String r10 = "'"
            r9.append(r10)     // Catch:{ Throwable -> 0x0088 }
            java.lang.String r10 = "card_saving_token_source"
            java.lang.String r10 = r8.getString(r10)     // Catch:{ Throwable -> 0x0088 }
            r9.append(r10)     // Catch:{ Throwable -> 0x0088 }
            java.lang.String r10 = "': '"
            r9.append(r10)     // Catch:{ Throwable -> 0x0088 }
            java.lang.String r10 = "rzp_device_token"
            java.lang.String r10 = r8.getString(r10)     // Catch:{ Throwable -> 0x0088 }
            r9.append(r10)     // Catch:{ Throwable -> 0x0088 }
            java.lang.String r10 = "'"
            r9.append(r10)     // Catch:{ Throwable -> 0x0088 }
            java.lang.String r4 = r9.toString()     // Catch:{ Throwable -> 0x0088 }
            if (r7 != 0) goto L_0x007b
            java.lang.String r9 = "rzp_device_token"
            java.lang.String r7 = r8.getString(r9)     // Catch:{ Throwable -> 0x0088 }
            goto L_0x0088
        L_0x007b:
            java.lang.String r9 = "rzp_device_token"
            java.lang.String r8 = r8.getString(r9)     // Catch:{ Throwable -> 0x0088 }
            boolean r8 = r7.equals(r8)     // Catch:{ Throwable -> 0x0088 }
            if (r8 != 0) goto L_0x0088
            r5 = 1
        L_0x0088:
            int r1 = r1 + 1
            goto L_0x0026
        L_0x008b:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r4)
            java.lang.String r4 = "}"
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            if (r5 != r3) goto L_0x0013
            java.util.HashMap r11 = new java.util.HashMap
            r11.<init>()
            java.lang.String r12 = "packages"
            r11.put(r12, r1)
            com.razorpay.AnalyticsEvent r12 = com.razorpay.AnalyticsEvent.MULTIPLE_TOKEN_EVENT
            com.razorpay.AnalyticsUtil.trackEvent((com.razorpay.AnalyticsEvent) r12, (java.util.Map<java.lang.String, java.lang.Object>) r11)
            return r0
        L_0x00ae:
            r12 = r0
        L_0x00af:
            if (r12 == 0) goto L_0x00da
            java.lang.String r0 = ""
            java.lang.String r1 = ""
            java.lang.String r2 = "rzp_device_token"
            java.lang.String r0 = r12.getString(r2)     // Catch:{ Exception -> 0x00c1 }
            java.lang.String r2 = "card_saving_token_source"
            java.lang.String r1 = r12.getString(r2)     // Catch:{ Exception -> 0x00c1 }
        L_0x00c1:
            android.content.SharedPreferences$Editor r11 = com.razorpay.Q__v$.d__1_(r11)
            java.lang.String r12 = "rzp_device_token"
            android.content.SharedPreferences$Editor r11 = r11.putString(r12, r0)
            r11.apply()
            java.lang.String r11 = "device_token_source_single"
            com.razorpay.AnalyticsProperty r12 = new com.razorpay.AnalyticsProperty
            com.razorpay.AnalyticsProperty$R$$r_ r2 = com.razorpay.AnalyticsProperty$R$$r_.ORDER
            r12.<init>((java.lang.String) r1, (com.razorpay.AnalyticsProperty$R$$r_) r2)
            com.razorpay.AnalyticsUtil.addProperty(r11, r12)
        L_0x00da:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.razorpay.l_$w$.G__G_(android.content.Context, org.json.JSONArray):java.lang.String");
    }

    private static JSONArray R$$r_(Context context) {
        JSONArray jSONArray = new JSONArray();
        int i = 0;
        for (ResolveInfo resolveInfo : BaseUtils.getListOfAppsWhichHandleDeepLink(context, "io.rzp://rzp.io")) {
            String str = resolveInfo.activityInfo.taskAffinity;
            i++;
            try {
                String string = Q__v$.a_$P$(context.createPackageContext(str, 2)).getString("rzp_device_token", (String) null);
                if (string != null) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("rzp_device_token", string);
                    jSONObject.put("card_saving_token_source", str);
                    jSONArray.put(jSONObject);
                }
            } catch (Exception e) {
                if (!(e instanceof SecurityException) || Build.VERSION.SDK_INT < 24) {
                    AnalyticsUtil.reportError(e, "critical", e.getMessage());
                } else {
                    AnalyticsUtil.trackEvent(AnalyticsEvent.SHARE_PREFERENCES_SECURITY_EXCEPTION);
                }
            }
        }
        AnalyticsUtil.addProperty("sdk_count", new AnalyticsProperty(i, AnalyticsProperty$R$$r_.ORDER));
        AnalyticsUtil.addProperty("sdk_count_with_token", new AnalyticsProperty(jSONArray.length(), AnalyticsProperty$R$$r_.ORDER));
        return jSONArray;
    }

    static void a_$P$(Context context) {
        if (f$_G$.f$_G$().O__Y_() && Q__v$.a_$P$(context).getString("rzp_device_token", (String) null) != null) {
            AnalyticsUtil.addProperty("device_token_source_single", new AnalyticsProperty(context.getPackageName(), AnalyticsProperty$R$$r_.ORDER));
        } else if (Build.VERSION.SDK_INT >= 24 && f$_G$.f$_G$().H$_a_()) {
            Intent intent = new Intent();
            intent.setAction("rzp.device_token.share");
            context.sendOrderedBroadcast(intent, (String) null, new BroadcastReceiver() {
                public final void onReceive(Context context, Intent intent) {
                    String string;
                    Bundle resultExtras = getResultExtras(true);
                    if (resultExtras != null && (string = resultExtras.getString("device_token_info_list")) != null) {
                        try {
                            l_$w$.G__G_(context, new JSONArray(string));
                        } catch (Exception unused) {
                        }
                    }
                }
            }, (Handler) null, -1, (String) null, (Bundle) null);
        } else if (f$_G$.f$_G$().L$$C_()) {
            G__G_(context, R$$r_(context));
        }
    }
}
