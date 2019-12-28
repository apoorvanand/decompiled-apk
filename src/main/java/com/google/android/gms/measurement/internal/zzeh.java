package com.google.android.gms.measurement.internal;

public final class zzeh {
    private final int priority;
    private final /* synthetic */ zzef zzkf;
    private final boolean zzks;
    private final boolean zzkt;

    zzeh(zzef zzef, int i, boolean z, boolean z2) {
        this.zzkf = zzef;
        this.priority = i;
        this.zzks = z;
        this.zzkt = z2;
    }

    public final void zza(String str, Object obj) {
        this.zzkf.a(this.priority, this.zzks, this.zzkt, str, obj, (Object) null, (Object) null);
    }

    public final void zza(String str, Object obj, Object obj2) {
        this.zzkf.a(this.priority, this.zzks, this.zzkt, str, obj, obj2, (Object) null);
    }

    public final void zza(String str, Object obj, Object obj2, Object obj3) {
        this.zzkf.a(this.priority, this.zzks, this.zzkt, str, obj, obj2, obj3);
    }

    public final void zzao(String str) {
        this.zzkf.a(this.priority, this.zzks, this.zzkt, str, (Object) null, (Object) null, (Object) null);
    }
}
