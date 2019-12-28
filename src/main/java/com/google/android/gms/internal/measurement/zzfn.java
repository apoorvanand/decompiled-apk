package com.google.android.gms.internal.measurement;

public class zzfn {
    private static final zzel zzacw = zzel.zztp();
    private zzdp zzajm;
    private volatile zzgi zzajn;
    private volatile zzdp zzajo;

    /* JADX WARNING: Can't wrap try/catch for region: R(4:7|8|9|10) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0012 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.measurement.zzgi zzh(com.google.android.gms.internal.measurement.zzgi r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.measurement.zzgi r0 = r1.zzajn
            if (r0 != 0) goto L_0x001c
            monitor-enter(r1)
            com.google.android.gms.internal.measurement.zzgi r0 = r1.zzajn     // Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x000b
        L_0x0009:
            monitor-exit(r1)     // Catch:{ all -> 0x0019 }
            goto L_0x001c
        L_0x000b:
            r1.zzajn = r2     // Catch:{ zzfi -> 0x0012 }
            com.google.android.gms.internal.measurement.zzdp r0 = com.google.android.gms.internal.measurement.zzdp.zzadh     // Catch:{ zzfi -> 0x0012 }
            r1.zzajo = r0     // Catch:{ zzfi -> 0x0012 }
            goto L_0x0009
        L_0x0012:
            r1.zzajn = r2     // Catch:{ all -> 0x0019 }
            com.google.android.gms.internal.measurement.zzdp r2 = com.google.android.gms.internal.measurement.zzdp.zzadh     // Catch:{ all -> 0x0019 }
            r1.zzajo = r2     // Catch:{ all -> 0x0019 }
            goto L_0x0009
        L_0x0019:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0019 }
            throw r2
        L_0x001c:
            com.google.android.gms.internal.measurement.zzgi r2 = r1.zzajn
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzfn.zzh(com.google.android.gms.internal.measurement.zzgi):com.google.android.gms.internal.measurement.zzgi");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzfn)) {
            return false;
        }
        zzfn zzfn = (zzfn) obj;
        zzgi zzgi = this.zzajn;
        zzgi zzgi2 = zzfn.zzajn;
        return (zzgi == null && zzgi2 == null) ? zzrs().equals(zzfn.zzrs()) : (zzgi == null || zzgi2 == null) ? zzgi != null ? zzgi.equals(zzfn.zzh(zzgi.zzuh())) : zzh(zzgi2.zzuh()).equals(zzgi2) : zzgi.equals(zzgi2);
    }

    public int hashCode() {
        return 1;
    }

    public final zzgi zzi(zzgi zzgi) {
        zzgi zzgi2 = this.zzajn;
        this.zzajm = null;
        this.zzajo = null;
        this.zzajn = zzgi;
        return zzgi2;
    }

    public final zzdp zzrs() {
        if (this.zzajo != null) {
            return this.zzajo;
        }
        synchronized (this) {
            if (this.zzajo != null) {
                zzdp zzdp = this.zzajo;
                return zzdp;
            }
            this.zzajo = this.zzajn == null ? zzdp.zzadh : this.zzajn.zzrs();
            zzdp zzdp2 = this.zzajo;
            return zzdp2;
        }
    }

    public final int zzuk() {
        if (this.zzajo != null) {
            return this.zzajo.size();
        }
        if (this.zzajn != null) {
            return this.zzajn.zzuk();
        }
        return 0;
    }
}
