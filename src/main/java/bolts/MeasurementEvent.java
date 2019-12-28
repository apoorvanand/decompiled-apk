package bolts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class MeasurementEvent {
    public static final String APP_LINK_NAVIGATE_IN_EVENT_NAME = "al_nav_in";
    public static final String APP_LINK_NAVIGATE_OUT_EVENT_NAME = "al_nav_out";
    public static final String MEASUREMENT_EVENT_ARGS_KEY = "event_args";
    public static final String MEASUREMENT_EVENT_NAME_KEY = "event_name";
    public static final String MEASUREMENT_EVENT_NOTIFICATION_NAME = "com.parse.bolts.measurement_event";
    private Context appContext;
    private Bundle args;
    private String name;

    private MeasurementEvent(Context context, String str, Bundle bundle) {
        this.appContext = context.getApplicationContext();
        this.name = str;
        this.args = bundle;
    }

    static void a(Context context, String str, Intent intent, Map<String, String> map) {
        Bundle bundle = new Bundle();
        if (intent != null) {
            Bundle appLinkData = AppLinks.getAppLinkData(intent);
            if (appLinkData != null) {
                bundle = getApplinkLogData(context, str, appLinkData, intent);
            } else {
                Uri data = intent.getData();
                if (data != null) {
                    bundle.putString("intentData", data.toString());
                }
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    for (String str2 : extras.keySet()) {
                        bundle.putString(str2, objectToJSONString(extras.get(str2)));
                    }
                }
            }
        }
        if (map != null) {
            for (String next : map.keySet()) {
                bundle.putString(next, map.get(next));
            }
        }
        new MeasurementEvent(context, str, bundle).sendBroadcast();
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x007f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.os.Bundle getApplinkLogData(android.content.Context r6, java.lang.String r7, android.os.Bundle r8, android.content.Intent r9) {
        /*
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            android.content.pm.PackageManager r6 = r6.getPackageManager()
            android.content.ComponentName r6 = r9.resolveActivity(r6)
            if (r6 == 0) goto L_0x0018
            java.lang.String r1 = "class"
            java.lang.String r2 = r6.getShortClassName()
            r0.putString(r1, r2)
        L_0x0018:
            java.lang.String r1 = "al_nav_out"
            boolean r1 = r1.equals(r7)
            if (r1 == 0) goto L_0x0047
            if (r6 == 0) goto L_0x002b
            java.lang.String r7 = "package"
            java.lang.String r6 = r6.getPackageName()
            r0.putString(r7, r6)
        L_0x002b:
            android.net.Uri r6 = r9.getData()
            if (r6 == 0) goto L_0x003e
            java.lang.String r6 = "outputURL"
            android.net.Uri r7 = r9.getData()
            java.lang.String r7 = r7.toString()
            r0.putString(r6, r7)
        L_0x003e:
            java.lang.String r6 = r9.getScheme()
            if (r6 == 0) goto L_0x0071
            java.lang.String r6 = "outputURLScheme"
            goto L_0x006a
        L_0x0047:
            java.lang.String r6 = "al_nav_in"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x0071
            android.net.Uri r6 = r9.getData()
            if (r6 == 0) goto L_0x0062
            java.lang.String r6 = "inputURL"
            android.net.Uri r7 = r9.getData()
            java.lang.String r7 = r7.toString()
            r0.putString(r6, r7)
        L_0x0062:
            java.lang.String r6 = r9.getScheme()
            if (r6 == 0) goto L_0x0071
            java.lang.String r6 = "inputURLScheme"
        L_0x006a:
            java.lang.String r7 = r9.getScheme()
            r0.putString(r6, r7)
        L_0x0071:
            java.util.Set r6 = r8.keySet()
            java.util.Iterator r6 = r6.iterator()
        L_0x0079:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x0115
            java.lang.Object r7 = r6.next()
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r9 = r8.get(r7)
            boolean r1 = r9 instanceof android.os.Bundle
            if (r1 == 0) goto L_0x00ec
            android.os.Bundle r9 = (android.os.Bundle) r9
            java.util.Set r1 = r9.keySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x0097:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0079
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r3 = r9.get(r2)
            java.lang.String r3 = objectToJSONString(r3)
            java.lang.String r4 = "referer_app_link"
            boolean r4 = r7.equals(r4)
            if (r4 == 0) goto L_0x00d4
            java.lang.String r4 = "url"
            boolean r4 = r2.equalsIgnoreCase(r4)
            if (r4 == 0) goto L_0x00be
            java.lang.String r2 = "refererURL"
            goto L_0x00e8
        L_0x00be:
            java.lang.String r4 = "app_name"
            boolean r4 = r2.equalsIgnoreCase(r4)
            if (r4 == 0) goto L_0x00c9
            java.lang.String r2 = "refererAppName"
            goto L_0x00e8
        L_0x00c9:
            java.lang.String r4 = "package"
            boolean r4 = r2.equalsIgnoreCase(r4)
            if (r4 == 0) goto L_0x00d4
            java.lang.String r2 = "sourceApplication"
            goto L_0x00e8
        L_0x00d4:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r7)
            java.lang.String r5 = "/"
            r4.append(r5)
            r4.append(r2)
            java.lang.String r2 = r4.toString()
        L_0x00e8:
            r0.putString(r2, r3)
            goto L_0x0097
        L_0x00ec:
            java.lang.String r9 = objectToJSONString(r9)
            java.lang.String r1 = "target_url"
            boolean r1 = r7.equals(r1)
            if (r1 == 0) goto L_0x0110
            android.net.Uri r7 = android.net.Uri.parse(r9)
            java.lang.String r9 = "targetURL"
            java.lang.String r1 = r7.toString()
            r0.putString(r9, r1)
            java.lang.String r9 = "targetURLHost"
            java.lang.String r7 = r7.getHost()
            r0.putString(r9, r7)
            goto L_0x0079
        L_0x0110:
            r0.putString(r7, r9)
            goto L_0x0079
        L_0x0115:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: bolts.MeasurementEvent.getApplinkLogData(android.content.Context, java.lang.String, android.os.Bundle, android.content.Intent):android.os.Bundle");
    }

    private static String objectToJSONString(Object obj) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof JSONArray) || (obj instanceof JSONObject)) {
            return obj.toString();
        }
        try {
            return obj instanceof Collection ? new JSONArray((Collection) obj).toString() : obj instanceof Map ? new JSONObject((Map) obj).toString() : obj.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    private void sendBroadcast() {
        if (this.name == null) {
            Log.d(getClass().getName(), "Event name is required");
        }
        try {
            Class<?> cls = Class.forName("androidx.localbroadcastmanager.content.LocalBroadcastManager");
            Method method = cls.getMethod("getInstance", new Class[]{Context.class});
            Method method2 = cls.getMethod("sendBroadcast", new Class[]{Intent.class});
            Object invoke = method.invoke((Object) null, new Object[]{this.appContext});
            Intent intent = new Intent(MEASUREMENT_EVENT_NOTIFICATION_NAME);
            intent.putExtra(MEASUREMENT_EVENT_NAME_KEY, this.name);
            intent.putExtra(MEASUREMENT_EVENT_ARGS_KEY, this.args);
            method2.invoke(invoke, new Object[]{intent});
        } catch (Exception unused) {
            Log.d(getClass().getName(), "LocalBroadcastManager in android support library is required to raise bolts event.");
        }
    }
}
