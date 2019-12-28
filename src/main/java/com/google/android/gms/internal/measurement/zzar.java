package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzz;

final class zzar extends zzz.zzb {
    private final /* synthetic */ zzz zzaa;
    private final /* synthetic */ String zzas;
    private final /* synthetic */ zzl zzat;
    private final /* synthetic */ boolean zzbi;
    private final /* synthetic */ String zzx;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzar(zzz zzz, String str, String str2, boolean z, zzl zzl) {
        super(zzz);
        this.zzaa = zzz;
        this.zzx = str;
        this.zzas = str2;
        this.zzbi = z;
        this.zzat = zzl;
    }

    /* access modifiers changed from: protected */
    public final void a() {
        this.zzat.zzb((Bundle) null);
    }

    /* access modifiers changed from: package-private */
    public final void zzf() {
        this.zzaa.zzar.getUserProperties(this.zzx, this.zzas, this.zzbi, this.zzat);
    }
}
