package com.google.android.gms.internal.vision;

public class zzgm {
    private static final zzfk zzrk = zzfk.zzek();
    private zzeo zzyh;
    private volatile zzhf zzyi;
    private volatile zzeo zzyj;

    /* JADX WARNING: Can't wrap try/catch for region: R(4:7|8|9|10) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0012 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.vision.zzhf zzh(com.google.android.gms.internal.vision.zzhf r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.vision.zzhf r0 = r1.zzyi
            if (r0 != 0) goto L_0x001c
            monitor-enter(r1)
            com.google.android.gms.internal.vision.zzhf r0 = r1.zzyi     // Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x000b
        L_0x0009:
            monitor-exit(r1)     // Catch:{ all -> 0x0019 }
            goto L_0x001c
        L_0x000b:
            r1.zzyi = r2     // Catch:{ zzgf -> 0x0012 }
            com.google.android.gms.internal.vision.zzeo r0 = com.google.android.gms.internal.vision.zzeo.zzrx     // Catch:{ zzgf -> 0x0012 }
            r1.zzyj = r0     // Catch:{ zzgf -> 0x0012 }
            goto L_0x0009
        L_0x0012:
            r1.zzyi = r2     // Catch:{ all -> 0x0019 }
            com.google.android.gms.internal.vision.zzeo r2 = com.google.android.gms.internal.vision.zzeo.zzrx     // Catch:{ all -> 0x0019 }
            r1.zzyj = r2     // Catch:{ all -> 0x0019 }
            goto L_0x0009
        L_0x0019:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0019 }
            throw r2
        L_0x001c:
            com.google.android.gms.internal.vision.zzhf r2 = r1.zzyi
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzgm.zzh(com.google.android.gms.internal.vision.zzhf):com.google.android.gms.internal.vision.zzhf");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzgm)) {
            return false;
        }
        zzgm zzgm = (zzgm) obj;
        zzhf zzhf = this.zzyi;
        zzhf zzhf2 = zzgm.zzyi;
        return (zzhf == null && zzhf2 == null) ? zzce().equals(zzgm.zzce()) : (zzhf == null || zzhf2 == null) ? zzhf != null ? zzhf.equals(zzgm.zzh(zzhf.zzfb())) : zzh(zzhf2.zzfb()).equals(zzhf2) : zzhf.equals(zzhf2);
    }

    public int hashCode() {
        return 1;
    }

    public final zzeo zzce() {
        if (this.zzyj != null) {
            return this.zzyj;
        }
        synchronized (this) {
            if (this.zzyj != null) {
                zzeo zzeo = this.zzyj;
                return zzeo;
            }
            this.zzyj = this.zzyi == null ? zzeo.zzrx : this.zzyi.zzce();
            zzeo zzeo2 = this.zzyj;
            return zzeo2;
        }
    }

    public final int zzeq() {
        if (this.zzyj != null) {
            return this.zzyj.size();
        }
        if (this.zzyi != null) {
            return this.zzyi.zzeq();
        }
        return 0;
    }

    public final zzhf zzi(zzhf zzhf) {
        zzhf zzhf2 = this.zzyi;
        this.zzyh = null;
        this.zzyj = null;
        this.zzyi = zzhf;
        return zzhf2;
    }
}
