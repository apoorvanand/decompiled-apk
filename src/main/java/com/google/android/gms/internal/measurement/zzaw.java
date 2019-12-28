package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzz;

final class zzaw extends zzz.zzb {
    private final /* synthetic */ zzz zzaa;
    private final /* synthetic */ boolean zzaz;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaw(zzz zzz, boolean z) {
        super(zzz);
        this.zzaa = zzz;
        this.zzaz = z;
    }

    /* access modifiers changed from: package-private */
    public final void zzf() {
        this.zzaa.zzar.setDataCollectionEnabled(this.zzaz);
    }
}
