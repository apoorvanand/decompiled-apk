package com.google.android.gms.internal.firebase_ml;

public class zztt {
    private static final zzsp zzbhw = zzsp.zzoz();
    private zzru zzbnt;
    private volatile zzum zzbnu;
    private volatile zzru zzbnv;

    /* JADX WARNING: Can't wrap try/catch for region: R(4:7|8|9|10) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0012 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.firebase_ml.zzum zzh(com.google.android.gms.internal.firebase_ml.zzum r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.firebase_ml.zzum r0 = r1.zzbnu
            if (r0 != 0) goto L_0x001c
            monitor-enter(r1)
            com.google.android.gms.internal.firebase_ml.zzum r0 = r1.zzbnu     // Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x000b
        L_0x0009:
            monitor-exit(r1)     // Catch:{ all -> 0x0019 }
            goto L_0x001c
        L_0x000b:
            r1.zzbnu = r2     // Catch:{ zztm -> 0x0012 }
            com.google.android.gms.internal.firebase_ml.zzru r0 = com.google.android.gms.internal.firebase_ml.zzru.zzbig     // Catch:{ zztm -> 0x0012 }
            r1.zzbnv = r0     // Catch:{ zztm -> 0x0012 }
            goto L_0x0009
        L_0x0012:
            r1.zzbnu = r2     // Catch:{ all -> 0x0019 }
            com.google.android.gms.internal.firebase_ml.zzru r2 = com.google.android.gms.internal.firebase_ml.zzru.zzbig     // Catch:{ all -> 0x0019 }
            r1.zzbnv = r2     // Catch:{ all -> 0x0019 }
            goto L_0x0009
        L_0x0019:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0019 }
            throw r2
        L_0x001c:
            com.google.android.gms.internal.firebase_ml.zzum r2 = r1.zzbnu
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zztt.zzh(com.google.android.gms.internal.firebase_ml.zzum):com.google.android.gms.internal.firebase_ml.zzum");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zztt)) {
            return false;
        }
        zztt zztt = (zztt) obj;
        zzum zzum = this.zzbnu;
        zzum zzum2 = zztt.zzbnu;
        return (zzum == null && zzum2 == null) ? zzod().equals(zztt.zzod()) : (zzum == null || zzum2 == null) ? zzum != null ? zzum.equals(zztt.zzh(zzum.zzps())) : zzh(zzum2.zzps()).equals(zzum2) : zzum.equals(zzum2);
    }

    public int hashCode() {
        return 1;
    }

    public final zzum zzi(zzum zzum) {
        zzum zzum2 = this.zzbnu;
        this.zzbnt = null;
        this.zzbnv = null;
        this.zzbnu = zzum;
        return zzum2;
    }

    public final zzru zzod() {
        if (this.zzbnv != null) {
            return this.zzbnv;
        }
        synchronized (this) {
            if (this.zzbnv != null) {
                zzru zzru = this.zzbnv;
                return zzru;
            }
            this.zzbnv = this.zzbnu == null ? zzru.zzbig : this.zzbnu.zzod();
            zzru zzru2 = this.zzbnv;
            return zzru2;
        }
    }

    public final int zzpe() {
        if (this.zzbnv != null) {
            return this.zzbnv.size();
        }
        if (this.zzbnu != null) {
            return this.zzbnu.zzpe();
        }
        return 0;
    }
}
