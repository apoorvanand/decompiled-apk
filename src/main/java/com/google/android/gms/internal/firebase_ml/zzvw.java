package com.google.android.gms.internal.firebase_ml;

final class zzvw extends zzvu<zzvv, zzvv> {
    zzvw() {
    }

    private static void zza(Object obj, zzvv zzvv) {
        ((zztc) obj).zzbmc = zzvv;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object a() {
        return zzvv.a();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object a(Object obj) {
        return ((zztc) obj).zzbmc;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void a(Object obj, int i, long j) {
        ((zzvv) obj).a(i << 3, (Object) Long.valueOf(j));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void a(Object obj, int i, zzru zzru) {
        ((zzvv) obj).a((i << 3) | 2, (Object) zzru);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void a(Object obj, zzwp zzwp) {
        ((zzvv) obj).zzb(zzwp);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void a(Object obj, Object obj2) {
        zza(obj, (zzvv) obj2);
    }

    /* access modifiers changed from: package-private */
    public final void b(Object obj) {
        ((zztc) obj).zzbmc.zzoh();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void b(Object obj, zzwp zzwp) {
        ((zzvv) obj).a(zzwp);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void b(Object obj, Object obj2) {
        zza(obj, (zzvv) obj2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ int c(Object obj) {
        return ((zzvv) obj).zzrw();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object c(Object obj, Object obj2) {
        zzvv zzvv = (zzvv) obj;
        zzvv zzvv2 = (zzvv) obj2;
        return zzvv2.equals(zzvv.zzru()) ? zzvv : zzvv.a(zzvv, zzvv2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ int d(Object obj) {
        return ((zzvv) obj).zzpe();
    }
}
