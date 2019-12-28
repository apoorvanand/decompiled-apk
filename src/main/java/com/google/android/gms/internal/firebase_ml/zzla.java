package com.google.android.gms.internal.firebase_ml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class zzla {
    /* access modifiers changed from: private */
    public final int limit;
    /* access modifiers changed from: private */
    public final zzki zzabn;
    private final boolean zzabo;
    private final zzle zzabp;

    private zzla(zzle zzle) {
        this(zzle, false, zzkm.a, Integer.MAX_VALUE);
    }

    private zzla(zzle zzle, boolean z, zzki zzki, int i) {
        this.zzabp = zzle;
        this.zzabo = false;
        this.zzabn = zzki;
        this.limit = Integer.MAX_VALUE;
    }

    public static zzla zza(zzki zzki) {
        zzky.checkNotNull(zzki);
        return new zzla(new zzlb(zzki));
    }

    public final List<String> zza(CharSequence charSequence) {
        zzky.checkNotNull(charSequence);
        Iterator<String> zza = this.zzabp.zza(this, charSequence);
        ArrayList arrayList = new ArrayList();
        while (zza.hasNext()) {
            arrayList.add(zza.next());
        }
        return Collections.unmodifiableList(arrayList);
    }
}
