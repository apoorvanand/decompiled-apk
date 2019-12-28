package com.google.android.gms.internal.measurement;

import java.util.List;

final class zzft extends zzfs {
    private zzft() {
        super();
    }

    private static <E> zzff<E> zzc(Object obj, long j) {
        return (zzff) zzhv.f(obj, j);
    }

    /* access modifiers changed from: package-private */
    public final <L> List<L> a(Object obj, long j) {
        zzff zzc = zzc(obj, j);
        if (zzc.zzrx()) {
            return zzc;
        }
        int size = zzc.size();
        zzff zzap = zzc.zzap(size == 0 ? 10 : size << 1);
        zzhv.a(obj, j, (Object) zzap);
        return zzap;
    }

    /* access modifiers changed from: package-private */
    public final <E> void a(Object obj, Object obj2, long j) {
        zzff zzc = zzc(obj, j);
        zzff zzc2 = zzc(obj2, j);
        int size = zzc.size();
        int size2 = zzc2.size();
        if (size > 0 && size2 > 0) {
            if (!zzc.zzrx()) {
                zzc = zzc.zzap(size2 + size);
            }
            zzc.addAll(zzc2);
        }
        if (size > 0) {
            zzc2 = zzc;
        }
        zzhv.a(obj, j, (Object) zzc2);
    }

    /* access modifiers changed from: package-private */
    public final void b(Object obj, long j) {
        zzc(obj, j).zzry();
    }
}
