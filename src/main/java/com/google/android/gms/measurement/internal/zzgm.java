package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzx;

@VisibleForTesting
public final class zzgm {
    final Context a;
    String b;
    String c;
    String d;
    Boolean e;
    long f;
    zzx g;
    boolean h = true;

    @VisibleForTesting
    public zzgm(Context context, zzx zzx) {
        Preconditions.checkNotNull(context);
        Context applicationContext = context.getApplicationContext();
        Preconditions.checkNotNull(applicationContext);
        this.a = applicationContext;
        if (zzx != null) {
            this.g = zzx;
            this.b = zzx.zzv;
            this.c = zzx.origin;
            this.d = zzx.zzu;
            this.h = zzx.zzt;
            this.f = zzx.zzs;
            if (zzx.zzw != null) {
                this.e = Boolean.valueOf(zzx.zzw.getBoolean("dataCollectionDefaultEnabled", true));
            }
        }
    }
}
