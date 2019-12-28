package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

final class zzd implements DynamiteModule.VersionPolicy {
    zzd() {
    }

    public final DynamiteModule.VersionPolicy.zzb zza(Context context, String str, DynamiteModule.VersionPolicy.zza zza) {
        int i;
        DynamiteModule.VersionPolicy.zzb zzb = new DynamiteModule.VersionPolicy.zzb();
        zzb.zzir = zza.getLocalVersion(context, str);
        zzb.zzis = zza.zza(context, str, true);
        if (zzb.zzir == 0 && zzb.zzis == 0) {
            i = 0;
        } else if (zzb.zzir >= zzb.zzis) {
            i = -1;
        } else {
            zzb.zzit = 1;
            return zzb;
        }
        zzb.zzit = i;
        return zzb;
    }
}
