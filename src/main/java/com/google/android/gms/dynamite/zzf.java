package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

final class zzf implements DynamiteModule.VersionPolicy {
    zzf() {
    }

    public final DynamiteModule.VersionPolicy.zzb zza(Context context, String str, DynamiteModule.VersionPolicy.zza zza) {
        int i;
        DynamiteModule.VersionPolicy.zzb zzb = new DynamiteModule.VersionPolicy.zzb();
        zzb.zzir = zza.getLocalVersion(context, str);
        zzb.zzis = zza.zza(context, str, true);
        if (zzb.zzir == 0 && zzb.zzis == 0) {
            i = 0;
        } else if (zzb.zzis >= zzb.zzir) {
            zzb.zzit = 1;
            return zzb;
        } else {
            i = -1;
        }
        zzb.zzit = i;
        return zzb;
    }
}