package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

final class zzgo<T> implements zzgx<T> {
    private final zzgi zzakn;
    private final boolean zzako;
    private final zzhp<?, ?> zzakx;
    private final zzen<?> zzaky;

    private zzgo(zzhp<?, ?> zzhp, zzen<?> zzen, zzgi zzgi) {
        this.zzakx = zzhp;
        this.zzako = zzen.a(zzgi);
        this.zzaky = zzen;
        this.zzakn = zzgi;
    }

    static <T> zzgo<T> a(zzhp<?, ?> zzhp, zzen<?> zzen, zzgi zzgi) {
        return new zzgo<>(zzhp, zzen, zzgi);
    }

    public final boolean equals(T t, T t2) {
        if (!this.zzakx.b(t).equals(this.zzakx.b(t2))) {
            return false;
        }
        if (this.zzako) {
            return this.zzaky.a((Object) t).equals(this.zzaky.a((Object) t2));
        }
        return true;
    }

    public final int hashCode(T t) {
        int hashCode = this.zzakx.b(t).hashCode();
        return this.zzako ? (hashCode * 53) + this.zzaky.a((Object) t).hashCode() : hashCode;
    }

    public final T newInstance() {
        return this.zzakn.zzup().zzuf();
    }

    public final void zza(T t, zzgy zzgy, zzel zzel) {
        boolean z;
        zzhp<?, ?> zzhp = this.zzakx;
        zzen<?> zzen = this.zzaky;
        Object c = zzhp.c(t);
        zzeo<?> b = zzen.b(t);
        do {
            try {
                if (zzgy.zzsy() == Integer.MAX_VALUE) {
                    zzhp.b((Object) t, c);
                    return;
                }
                int tag = zzgy.getTag();
                if (tag == 11) {
                    int i = 0;
                    Object obj = null;
                    zzdp zzdp = null;
                    while (zzgy.zzsy() != Integer.MAX_VALUE) {
                        int tag2 = zzgy.getTag();
                        if (tag2 == 16) {
                            i = zzgy.zzsp();
                            obj = zzen.a(zzel, this.zzakn, i);
                        } else if (tag2 == 26) {
                            if (obj != null) {
                                zzen.a(zzgy, obj, zzel, b);
                            } else {
                                zzdp = zzgy.zzso();
                            }
                        } else if (!zzgy.zzsz()) {
                            break;
                        }
                    }
                    if (zzgy.getTag() != 12) {
                        throw zzfi.e();
                    } else if (zzdp != null) {
                        if (obj != null) {
                            zzen.a(zzdp, obj, zzel, b);
                        } else {
                            zzhp.a(c, i, zzdp);
                        }
                    }
                } else if ((tag & 7) == 2) {
                    Object a = zzen.a(zzel, this.zzakn, tag >>> 3);
                    if (a != null) {
                        zzen.a(zzgy, a, zzel, b);
                    } else {
                        z = zzhp.a(c, zzgy);
                        continue;
                    }
                } else {
                    z = zzgy.zzsz();
                    continue;
                }
                z = true;
                continue;
            } finally {
                zzhp.b((Object) t, c);
            }
        } while (z);
    }

    public final void zza(T t, zzim zzim) {
        int i;
        Object obj;
        Iterator<Map.Entry<?, Object>> it = this.zzaky.a((Object) t).iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            zzeq zzeq = (zzeq) next.getKey();
            if (zzeq.zztx() != zzij.MESSAGE || zzeq.zzty() || zzeq.zztz()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if (next instanceof zzfl) {
                i = zzeq.zzlg();
                obj = ((zzfl) next).zzve().zzrs();
            } else {
                i = zzeq.zzlg();
                obj = next.getValue();
            }
            zzim.zza(i, obj);
        }
        zzhp<?, ?> zzhp = this.zzakx;
        zzhp.b(zzhp.b(t), zzim);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: com.google.android.gms.internal.measurement.zzey$zze} */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r9, byte[] r10, int r11, int r12, com.google.android.gms.internal.measurement.zzdk r13) {
        /*
            r8 = this;
            r0 = r9
            com.google.android.gms.internal.measurement.zzey r0 = (com.google.android.gms.internal.measurement.zzey) r0
            com.google.android.gms.internal.measurement.zzhs r1 = r0.zzahz
            com.google.android.gms.internal.measurement.zzhs r2 = com.google.android.gms.internal.measurement.zzhs.zzwq()
            if (r1 != r2) goto L_0x0011
            com.google.android.gms.internal.measurement.zzhs r1 = com.google.android.gms.internal.measurement.zzhs.a()
            r0.zzahz = r1
        L_0x0011:
            com.google.android.gms.internal.measurement.zzey$zzb r9 = (com.google.android.gms.internal.measurement.zzey.zzb) r9
            r9.a()
            r9 = 0
            r0 = r9
        L_0x0018:
            if (r11 >= r12) goto L_0x00a2
            int r4 = com.google.android.gms.internal.measurement.zzdl.a(r10, r11, r13)
            int r2 = r13.zzada
            r11 = 11
            r3 = 2
            if (r2 == r11) goto L_0x0051
            r11 = r2 & 7
            if (r11 != r3) goto L_0x004c
            com.google.android.gms.internal.measurement.zzen<?> r11 = r8.zzaky
            com.google.android.gms.internal.measurement.zzel r0 = r13.zzadd
            com.google.android.gms.internal.measurement.zzgi r3 = r8.zzakn
            int r5 = r2 >>> 3
            java.lang.Object r11 = r11.a(r0, r3, r5)
            r0 = r11
            com.google.android.gms.internal.measurement.zzey$zze r0 = (com.google.android.gms.internal.measurement.zzey.zze) r0
            if (r0 != 0) goto L_0x0043
            r3 = r10
            r5 = r12
            r6 = r1
            r7 = r13
            int r11 = com.google.android.gms.internal.measurement.zzdl.a((int) r2, (byte[]) r3, (int) r4, (int) r5, (com.google.android.gms.internal.measurement.zzhs) r6, (com.google.android.gms.internal.measurement.zzdk) r7)
            goto L_0x0018
        L_0x0043:
            com.google.android.gms.internal.measurement.zzgt.zzvy()
            java.lang.NoSuchMethodError r9 = new java.lang.NoSuchMethodError
            r9.<init>()
            throw r9
        L_0x004c:
            int r11 = com.google.android.gms.internal.measurement.zzdl.a((int) r2, (byte[]) r10, (int) r4, (int) r12, (com.google.android.gms.internal.measurement.zzdk) r13)
            goto L_0x0018
        L_0x0051:
            r11 = 0
            r2 = r9
        L_0x0053:
            if (r4 >= r12) goto L_0x0097
            int r4 = com.google.android.gms.internal.measurement.zzdl.a(r10, r4, r13)
            int r5 = r13.zzada
            int r6 = r5 >>> 3
            r7 = r5 & 7
            switch(r6) {
                case 2: goto L_0x0079;
                case 3: goto L_0x0063;
                default: goto L_0x0062;
            }
        L_0x0062:
            goto L_0x008e
        L_0x0063:
            if (r0 != 0) goto L_0x0070
            if (r7 != r3) goto L_0x008e
            int r4 = com.google.android.gms.internal.measurement.zzdl.e(r10, r4, r13)
            java.lang.Object r2 = r13.zzadc
            com.google.android.gms.internal.measurement.zzdp r2 = (com.google.android.gms.internal.measurement.zzdp) r2
            goto L_0x0053
        L_0x0070:
            com.google.android.gms.internal.measurement.zzgt.zzvy()
            java.lang.NoSuchMethodError r9 = new java.lang.NoSuchMethodError
            r9.<init>()
            throw r9
        L_0x0079:
            if (r7 != 0) goto L_0x008e
            int r4 = com.google.android.gms.internal.measurement.zzdl.a(r10, r4, r13)
            int r11 = r13.zzada
            com.google.android.gms.internal.measurement.zzen<?> r0 = r8.zzaky
            com.google.android.gms.internal.measurement.zzel r5 = r13.zzadd
            com.google.android.gms.internal.measurement.zzgi r6 = r8.zzakn
            java.lang.Object r0 = r0.a(r5, r6, r11)
            com.google.android.gms.internal.measurement.zzey$zze r0 = (com.google.android.gms.internal.measurement.zzey.zze) r0
            goto L_0x0053
        L_0x008e:
            r6 = 12
            if (r5 == r6) goto L_0x0097
            int r4 = com.google.android.gms.internal.measurement.zzdl.a((int) r5, (byte[]) r10, (int) r4, (int) r12, (com.google.android.gms.internal.measurement.zzdk) r13)
            goto L_0x0053
        L_0x0097:
            if (r2 == 0) goto L_0x009f
            int r11 = r11 << 3
            r11 = r11 | r3
            r1.a((int) r11, (java.lang.Object) r2)
        L_0x009f:
            r11 = r4
            goto L_0x0018
        L_0x00a2:
            if (r11 != r12) goto L_0x00a5
            return
        L_0x00a5:
            com.google.android.gms.internal.measurement.zzfi r9 = com.google.android.gms.internal.measurement.zzfi.h()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgo.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.measurement.zzdk):void");
    }

    public final void zzc(T t, T t2) {
        zzgz.a(this.zzakx, t, t2);
        if (this.zzako) {
            zzgz.a(this.zzaky, t, t2);
        }
    }

    public final void zzj(T t) {
        this.zzakx.d(t);
        this.zzaky.c(t);
    }

    public final int zzt(T t) {
        zzhp<?, ?> zzhp = this.zzakx;
        int e = zzhp.e(zzhp.b(t)) + 0;
        return this.zzako ? e + this.zzaky.a((Object) t).zzts() : e;
    }

    public final boolean zzv(T t) {
        return this.zzaky.a((Object) t).isInitialized();
    }
}
