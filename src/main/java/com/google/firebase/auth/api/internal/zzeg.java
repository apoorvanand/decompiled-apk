package com.google.firebase.auth.api.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.LibraryVersion;
import com.google.android.gms.internal.firebase_auth.zzam;
import java.util.List;

public final class zzeg {
    private final int zzph;
    private final int zzpi = -1;

    private zzeg(String str, int i) {
        this.zzph = zzcd(str);
    }

    private static String zzcc(String str) {
        String version = LibraryVersion.getInstance().getVersion(str);
        return (TextUtils.isEmpty(version) || version.equals("UNKNOWN")) ? "-1" : version;
    }

    private static int zzcd(String str) {
        List<String> zza = zzam.zzbp(".").zza(str);
        if (zza.size() == 1) {
            return Integer.parseInt(str);
        }
        if (zza.size() == 3) {
            return (Integer.parseInt(zza.get(0)) * 1000000) + (Integer.parseInt(zza.get(1)) * 1000) + Integer.parseInt(zza.get(2));
        }
        String str2 = "";
        for (String valueOf : zza) {
            String valueOf2 = String.valueOf(str2);
            String valueOf3 = String.valueOf(valueOf);
            str2 = valueOf3.length() != 0 ? valueOf2.concat(valueOf3) : new String(valueOf2);
        }
        return Integer.parseInt(str2);
    }

    public static String zzek() {
        return zzcc("firebase-auth");
    }

    public static zzeg zzel() {
        return new zzeg(zzcc("firebase-auth-impl"), -1);
    }

    public final boolean zzej() {
        return this.zzph >= zzcd("16.2.1");
    }
}
