package com.google.android.gms.internal.firebase_ml;

final class zztz extends zztw {
    private zztz() {
        super();
    }

    private static <E> zztl<E> zzd(Object obj, long j) {
        return (zztl) zzwa.f(obj, j);
    }

    /* access modifiers changed from: package-private */
    public final void a(Object obj, long j) {
        zzd(obj, j).zzoh();
    }

    /* access modifiers changed from: package-private */
    public final <E> void a(Object obj, Object obj2, long j) {
        zztl zzd = zzd(obj, j);
        zztl zzd2 = zzd(obj2, j);
        int size = zzd.size();
        int size2 = zzd2.size();
        if (size > 0 && size2 > 0) {
            if (!zzd.zzog()) {
                zzd = zzd.zzcb(size2 + size);
            }
            zzd.addAll(zzd2);
        }
        if (size > 0) {
            zzd2 = zzd;
        }
        zzwa.a(obj, j, (Object) zzd2);
    }
}
