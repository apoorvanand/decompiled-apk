package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzz;

final class zzan extends zzz.zzb {
    private final /* synthetic */ zzz zzaa;
    private final /* synthetic */ zzl zzat;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzan(zzz zzz, zzl zzl) {
        super(zzz);
        this.zzaa = zzz;
        this.zzat = zzl;
    }

    /* access modifiers changed from: protected */
    public final void a() {
        this.zzat.zzb((Bundle) null);
    }

    /* access modifiers changed from: package-private */
    public final void zzf() {
        this.zzaa.zzar.getCachedAppInstanceId(this.zzat);
    }
}