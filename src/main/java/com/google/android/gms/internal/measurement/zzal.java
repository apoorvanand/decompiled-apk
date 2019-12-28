package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzz;
import com.google.android.gms.measurement.internal.zzgk;

final class zzal extends zzz.zzb {
    private final /* synthetic */ zzz zzaa;
    private final /* synthetic */ zzgk zzbc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzal(zzz zzz, zzgk zzgk) {
        super(zzz);
        this.zzaa = zzz;
        this.zzbc = zzgk;
    }

    /* access modifiers changed from: package-private */
    public final void zzf() {
        this.zzaa.zzar.setEventInterceptor(new zzz.zza(this.zzbc));
    }
}
