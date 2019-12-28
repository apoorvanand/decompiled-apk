package com.google.android.gms.internal.vision;

import java.util.List;

final class zzgs extends zzgp {
    private zzgs() {
        super();
    }

    private static <E> zzge<E> zzd(Object obj, long j) {
        return (zzge) zziu.f(obj, j);
    }

    /* access modifiers changed from: package-private */
    public final <L> List<L> a(Object obj, long j) {
        zzge zzd = zzd(obj, j);
        if (zzd.zzch()) {
            return zzd;
        }
        int size = zzd.size();
        zzge zzah = zzd.zzah(size == 0 ? 10 : size << 1);
        zziu.a(obj, j, (Object) zzah);
        return zzah;
    }

    /* access modifiers changed from: package-private */
    public final <E> void a(Object obj, Object obj2, long j) {
        zzge zzd = zzd(obj, j);
        zzge zzd2 = zzd(obj2, j);
        int size = zzd.size();
        int size2 = zzd2.size();
        if (size > 0 && size2 > 0) {
            if (!zzd.zzch()) {
                zzd = zzd.zzah(size2 + size);
            }
            zzd.addAll(zzd2);
        }
        if (size > 0) {
            zzd2 = zzd;
        }
        zziu.a(obj, j, (Object) zzd2);
    }

    /* access modifiers changed from: package-private */
    public final void b(Object obj, long j) {
        zzd(obj, j).zzci();
    }
}
