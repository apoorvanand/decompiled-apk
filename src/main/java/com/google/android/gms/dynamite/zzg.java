package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

final class zzg implements DynamiteModule.VersionPolicy {
    zzg() {
    }

    public final DynamiteModule.VersionPolicy.zzb zza(Context context, String str, DynamiteModule.VersionPolicy.zza zza) {
        DynamiteModule.VersionPolicy.zzb zzb = new DynamiteModule.VersionPolicy.zzb();
        zzb.zzir = zza.getLocalVersion(context, str);
        zzb.zzis = zzb.zzir != 0 ? zza.zza(context, str, false) : zza.zza(context, str, true);
        if (zzb.zzir == 0 && zzb.zzis == 0) {
            zzb.zzit = 0;
        } else if (zzb.zzis >= zzb.zzir) {
            zzb.zzit = 1;
        } else {
            zzb.zzit = -1;
        }
        return zzb;
    }
}
