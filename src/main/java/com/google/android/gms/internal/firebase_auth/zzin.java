package com.google.android.gms.internal.firebase_auth;

import java.util.List;

final class zzin extends zzim {
    private zzin() {
        super();
    }

    private static <E> zzhz<E> zzc(Object obj, long j) {
        return (zzhz) zzkq.f(obj, j);
    }

    /* access modifiers changed from: package-private */
    public final <L> List<L> a(Object obj, long j) {
        zzhz zzc = zzc(obj, j);
        if (zzc.zzfx()) {
            return zzc;
        }
        int size = zzc.size();
        zzhz zzo = zzc.zzo(size == 0 ? 10 : size << 1);
        zzkq.a(obj, j, (Object) zzo);
        return zzo;
    }

    /* access modifiers changed from: package-private */
    public final <E> void a(Object obj, Object obj2, long j) {
        zzhz zzc = zzc(obj, j);
        zzhz zzc2 = zzc(obj2, j);
        int size = zzc.size();
        int size2 = zzc2.size();
        if (size > 0 && size2 > 0) {
            if (!zzc.zzfx()) {
                zzc = zzc.zzo(size2 + size);
            }
            zzc.addAll(zzc2);
        }
        if (size > 0) {
            zzc2 = zzc;
        }
        zzkq.a(obj, j, (Object) zzc2);
    }

    /* access modifiers changed from: package-private */
    public final void b(Object obj, long j) {
        zzc(obj, j).zzfy();
    }
}
