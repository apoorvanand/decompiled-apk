package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

final class zza implements DynamiteModule.VersionPolicy.zza {
    zza() {
    }

    public final int getLocalVersion(Context context, String str) {
        return DynamiteModule.getLocalVersion(context, str);
    }

    public final int zza(Context context, String str, boolean z) {
        return DynamiteModule.zza(context, str, z);
    }
}
