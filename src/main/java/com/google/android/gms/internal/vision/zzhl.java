package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Map;

final class zzhl<T> implements zzhw<T> {
    private final zzhf zzzh;
    private final boolean zzzi;
    private final zzio<?, ?> zzzr;
    private final zzfl<?> zzzs;

    private zzhl(zzio<?, ?> zzio, zzfl<?> zzfl, zzhf zzhf) {
        this.zzzr = zzio;
        this.zzzi = zzfl.a(zzhf);
        this.zzzs = zzfl;
        this.zzzh = zzhf;
    }

    static <T> zzhl<T> a(zzio<?, ?> zzio, zzfl<?> zzfl, zzhf zzhf) {
        return new zzhl<>(zzio, zzfl, zzhf);
    }

    public final boolean equals(T t, T t2) {
        if (!this.zzzr.b(t).equals(this.zzzr.b(t2))) {
            return false;
        }
        if (this.zzzi) {
            return this.zzzs.a((Object) t).equals(this.zzzs.a((Object) t2));
        }
        return true;
    }

    public final int hashCode(T t) {
        int hashCode = this.zzzr.b(t).hashCode();
        return this.zzzi ? (hashCode * 53) + this.zzzs.a((Object) t).hashCode() : hashCode;
    }

    public final T newInstance() {
        return this.zzzh.zzfa().zzff();
    }

    public final void zza(T t, zzhv zzhv, zzfk zzfk) {
        boolean z;
        zzio<?, ?> zzio = this.zzzr;
        zzfl<?> zzfl = this.zzzs;
        Object c = zzio.c(t);
        zzfp<?> b = zzfl.b(t);
        do {
            try {
                if (zzhv.zzcn() == Integer.MAX_VALUE) {
                    zzio.b((Object) t, c);
                    return;
                }
                int tag = zzhv.getTag();
                if (tag == 11) {
                    int i = 0;
                    Object obj = null;
                    zzeo zzeo = null;
                    while (zzhv.zzcn() != Integer.MAX_VALUE) {
                        int tag2 = zzhv.getTag();
                        if (tag2 == 16) {
                            i = zzhv.zzcx();
                            obj = zzfl.a(zzfk, this.zzzh, i);
                        } else if (tag2 == 26) {
                            if (obj != null) {
                                zzfl.a(zzhv, obj, zzfk, b);
                            } else {
                                zzeo = zzhv.zzcw();
                            }
                        } else if (!zzhv.zzco()) {
                            break;
                        }
                    }
                    if (zzhv.getTag() != 12) {
                        throw zzgf.e();
                    } else if (zzeo != null) {
                        if (obj != null) {
                            zzfl.a(zzeo, obj, zzfk, b);
                        } else {
                            zzio.a(c, i, zzeo);
                        }
                    }
                } else if ((tag & 7) == 2) {
                    Object a = zzfl.a(zzfk, this.zzzh, tag >>> 3);
                    if (a != null) {
                        zzfl.a(zzhv, a, zzfk, b);
                    } else {
                        z = zzio.a(c, zzhv);
                        continue;
                    }
                } else {
                    z = zzhv.zzco();
                    continue;
                }
                z = true;
                continue;
            } finally {
                zzio.b((Object) t, c);
            }
        } while (z);
    }

    public final void zza(T t, zzjj zzjj) {
        int i;
        Object obj;
        Iterator<Map.Entry<?, Object>> it = this.zzzs.a((Object) t).iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            zzfr zzfr = (zzfr) next.getKey();
            if (zzfr.zzet() != zzji.MESSAGE || zzfr.zzeu() || zzfr.zzev()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if (next instanceof zzgk) {
                i = zzfr.zzr();
                obj = ((zzgk) next).zzfs().zzce();
            } else {
                i = zzfr.zzr();
                obj = next.getValue();
            }
            zzjj.zza(i, obj);
        }
        zzio<?, ?> zzio = this.zzzr;
        zzio.b(zzio.b(t), zzjj);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r7, byte[] r8, int r9, int r10, com.google.android.gms.internal.vision.zzei r11) {
        /*
            r6 = this;
            com.google.android.gms.internal.vision.zzfy r7 = (com.google.android.gms.internal.vision.zzfy) r7
            com.google.android.gms.internal.vision.zzip r0 = r7.zzwj
            com.google.android.gms.internal.vision.zzip r1 = com.google.android.gms.internal.vision.zzip.zzhe()
            if (r0 != r1) goto L_0x0010
            com.google.android.gms.internal.vision.zzip r0 = com.google.android.gms.internal.vision.zzip.a()
            r7.zzwj = r0
        L_0x0010:
            r7 = r0
        L_0x0011:
            if (r9 >= r10) goto L_0x0069
            int r2 = com.google.android.gms.internal.vision.zzeh.a(r8, r9, r11)
            int r0 = r11.zzro
            r9 = 11
            r1 = 2
            if (r0 == r9) goto L_0x0030
            r9 = r0 & 7
            if (r9 != r1) goto L_0x002b
            r1 = r8
            r3 = r10
            r4 = r7
            r5 = r11
            int r9 = com.google.android.gms.internal.vision.zzeh.a((int) r0, (byte[]) r1, (int) r2, (int) r3, (com.google.android.gms.internal.vision.zzip) r4, (com.google.android.gms.internal.vision.zzei) r5)
            goto L_0x0011
        L_0x002b:
            int r9 = com.google.android.gms.internal.vision.zzeh.a(r0, r8, r2, r10, r11)
            goto L_0x0011
        L_0x0030:
            r9 = 0
            r0 = 0
        L_0x0032:
            if (r2 >= r10) goto L_0x005f
            int r2 = com.google.android.gms.internal.vision.zzeh.a(r8, r2, r11)
            int r3 = r11.zzro
            int r4 = r3 >>> 3
            r5 = r3 & 7
            switch(r4) {
                case 2: goto L_0x004d;
                case 3: goto L_0x0042;
                default: goto L_0x0041;
            }
        L_0x0041:
            goto L_0x0056
        L_0x0042:
            if (r5 != r1) goto L_0x0056
            int r2 = com.google.android.gms.internal.vision.zzeh.e(r8, r2, r11)
            java.lang.Object r0 = r11.zzrq
            com.google.android.gms.internal.vision.zzeo r0 = (com.google.android.gms.internal.vision.zzeo) r0
            goto L_0x0032
        L_0x004d:
            if (r5 != 0) goto L_0x0056
            int r2 = com.google.android.gms.internal.vision.zzeh.a(r8, r2, r11)
            int r9 = r11.zzro
            goto L_0x0032
        L_0x0056:
            r4 = 12
            if (r3 == r4) goto L_0x005f
            int r2 = com.google.android.gms.internal.vision.zzeh.a(r3, r8, r2, r10, r11)
            goto L_0x0032
        L_0x005f:
            if (r0 == 0) goto L_0x0067
            int r9 = r9 << 3
            r9 = r9 | r1
            r7.a((int) r9, (java.lang.Object) r0)
        L_0x0067:
            r9 = r2
            goto L_0x0011
        L_0x0069:
            if (r9 != r10) goto L_0x006c
            return
        L_0x006c:
            com.google.android.gms.internal.vision.zzgf r7 = com.google.android.gms.internal.vision.zzgf.h()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhl.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.vision.zzei):void");
    }

    public final void zzc(T t, T t2) {
        zzhy.a(this.zzzr, t, t2);
        if (this.zzzi) {
            zzhy.a(this.zzzs, t, t2);
        }
    }

    public final void zze(T t) {
        this.zzzr.d(t);
        this.zzzs.c(t);
    }

    public final int zzp(T t) {
        zzio<?, ?> zzio = this.zzzr;
        int e = zzio.e(zzio.b(t)) + 0;
        return this.zzzi ? e + this.zzzs.a((Object) t).zzer() : e;
    }

    public final boolean zzr(T t) {
        return this.zzzs.a((Object) t).isInitialized();
    }
}
