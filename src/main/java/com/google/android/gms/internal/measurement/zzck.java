package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.net.Uri;

public final class zzck {
    private static volatile zzcw<Boolean> zzaav = zzcw.zzrp();
    private static final Object zzaaw = new Object();

    public static boolean zza(Context context, Uri uri) {
        boolean z;
        String authority = uri.getAuthority();
        if ("com.google.android.gms.phenotype".equals(authority)) {
            if (!zzaav.isPresent()) {
                synchronized (zzaaw) {
                    if (zzaav.isPresent()) {
                        boolean booleanValue = zzaav.get().booleanValue();
                        return booleanValue;
                    }
                    boolean z2 = false;
                    if (!"com.google.android.gms".equals(context.getPackageName())) {
                        ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider("com.google.android.gms.phenotype", 0);
                        if (resolveContentProvider == null || !"com.google.android.gms".equals(resolveContentProvider.packageName)) {
                            z = false;
                            if (z && zzq(context)) {
                                z2 = true;
                            }
                            zzaav = zzcw.zzf(Boolean.valueOf(z2));
                        }
                    }
                    z = true;
                    z2 = true;
                    zzaav = zzcw.zzf(Boolean.valueOf(z2));
                }
            }
            return zzaav.get().booleanValue();
        }
        StringBuilder sb = new StringBuilder(String.valueOf(authority).length() + 91);
        sb.append(authority);
        sb.append(" is an unsupported authority. Only com.google.android.gms.phenotype authority is supported.");
        throw new IllegalArgumentException(sb.toString());
    }

    private static boolean zzq(Context context) {
        try {
            return (context.getPackageManager().getApplicationInfo("com.google.android.gms", 0).flags & 129) != 0;
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }
}
