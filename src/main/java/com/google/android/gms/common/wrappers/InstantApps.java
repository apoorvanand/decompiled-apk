package com.google.android.gms.common.wrappers;

import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.PlatformVersion;

@KeepForSdk
public class InstantApps {
    private static Context zzhv;
    private static Boolean zzhw;

    @KeepForSdk
    public static synchronized boolean isInstantApp(Context context) {
        boolean z;
        synchronized (InstantApps.class) {
            Context applicationContext = context.getApplicationContext();
            if (zzhv == null || zzhw == null || zzhv != applicationContext) {
                zzhw = null;
                if (PlatformVersion.isAtLeastO()) {
                    z = Boolean.valueOf(applicationContext.getPackageManager().isInstantApp());
                } else {
                    try {
                        context.getClassLoader().loadClass("com.google.android.instantapps.supervisor.InstantAppsRuntime");
                        zzhw = true;
                    } catch (ClassNotFoundException unused) {
                        z = false;
                    }
                    zzhv = applicationContext;
                    boolean booleanValue = zzhw.booleanValue();
                    return booleanValue;
                }
                zzhw = z;
                zzhv = applicationContext;
                boolean booleanValue2 = zzhw.booleanValue();
                return booleanValue2;
            }
            boolean booleanValue3 = zzhw.booleanValue();
            return booleanValue3;
        }
    }
}
