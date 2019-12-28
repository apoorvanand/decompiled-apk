package bolts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import java.util.Map;

public final class AppLinks {
    public static Bundle getAppLinkData(Intent intent) {
        return intent.getBundleExtra("al_applink_data");
    }

    public static Bundle getAppLinkExtras(Intent intent) {
        Bundle appLinkData = getAppLinkData(intent);
        if (appLinkData == null) {
            return null;
        }
        return appLinkData.getBundle("extras");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = r0.getString("target_url");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.net.Uri getTargetUrl(android.content.Intent r2) {
        /*
            android.os.Bundle r0 = getAppLinkData(r2)
            if (r0 == 0) goto L_0x0013
            java.lang.String r1 = "target_url"
            java.lang.String r0 = r0.getString(r1)
            if (r0 == 0) goto L_0x0013
            android.net.Uri r2 = android.net.Uri.parse(r0)
            return r2
        L_0x0013:
            android.net.Uri r2 = r2.getData()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: bolts.AppLinks.getTargetUrl(android.content.Intent):android.net.Uri");
    }

    public static Uri getTargetUrlFromInboundIntent(Context context, Intent intent) {
        String string;
        Bundle appLinkData = getAppLinkData(intent);
        if (appLinkData == null || (string = appLinkData.getString("target_url")) == null) {
            return null;
        }
        MeasurementEvent.a(context, MeasurementEvent.APP_LINK_NAVIGATE_IN_EVENT_NAME, intent, (Map<String, String>) null);
        return Uri.parse(string);
    }
}
