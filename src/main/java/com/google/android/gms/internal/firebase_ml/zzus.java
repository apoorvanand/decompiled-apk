package com.google.android.gms.internal.firebase_ml;

import java.util.Iterator;
import java.util.Map;

final class zzus<T> implements zzvc<T> {
    private final zzum zzbow;
    private final boolean zzbox;
    private final zzvu<?, ?> zzbpg;
    private final zzsr<?> zzbph;

    private zzus(zzvu<?, ?> zzvu, zzsr<?> zzsr, zzum zzum) {
        this.zzbpg = zzvu;
        this.zzbox = zzsr.a(zzum);
        this.zzbph = zzsr;
        this.zzbow = zzum;
    }

    static <T> zzus<T> a(zzvu<?, ?> zzvu, zzsr<?> zzsr, zzum zzum) {
        return new zzus<>(zzvu, zzsr, zzum);
    }

    public final boolean equals(T t, T t2) {
        if (!this.zzbpg.a(t).equals(this.zzbpg.a(t2))) {
            return false;
        }
        if (this.zzbox) {
            return this.zzbph.a((Object) t).equals(this.zzbph.a((Object) t2));
        }
        return true;
    }

    public final int hashCode(T t) {
        int hashCode = this.zzbpg.a(t).hashCode();
        return this.zzbox ? (hashCode * 53) + this.zzbph.a((Object) t).hashCode() : hashCode;
    }

    public final T newInstance() {
        return this.zzbow.zzpr().zzpw();
    }

    public final void zza(T t, zzwp zzwp) {
        int i;
        Object obj;
        Iterator<Map.Entry<?, Object>> it = this.zzbph.a((Object) t).iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            zzsw zzsw = (zzsw) next.getKey();
            if (zzsw.zzph() != zzwo.MESSAGE || zzsw.zzpi() || zzsw.zzpj()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if (next instanceof zztr) {
                i = zzsw.zzh();
                obj = ((zztr) next).zzqi().zzod();
            } else {
                i = zzsw.zzh();
                obj = next.getValue();
            }
            zzwp.zza(i, obj);
        }
        zzvu<?, ?> zzvu = this.zzbpg;
        zzvu.b(zzvu.a(t), zzwp);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: com.google.android.gms.internal.firebase_ml.zztc$zze} */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r9, byte[] r10, int r11, int r12, com.google.android.gms.internal.firebase_ml.zzrr r13) {
        /*
            r8 = this;
            r0 = r9
            com.google.android.gms.internal.firebase_ml.zztc r0 = (com.google.android.gms.internal.firebase_ml.zztc) r0
            com.google.android.gms.internal.firebase_ml.zzvv r1 = r0.zzbmc
            com.google.android.gms.internal.firebase_ml.zzvv r2 = com.google.android.gms.internal.firebase_ml.zzvv.zzru()
            if (r1 != r2) goto L_0x0011
            com.google.android.gms.internal.firebase_ml.zzvv r1 = com.google.android.gms.internal.firebase_ml.zzvv.a()
            r0.zzbmc = r1
        L_0x0011:
            com.google.android.gms.internal.firebase_ml.zztc$zzd r9 = (com.google.android.gms.internal.firebase_ml.zztc.zzd) r9
            r9.a()
            r9 = 0
            r0 = r9
        L_0x0018:
            if (r11 >= r12) goto L_0x00a2
            int r4 = com.google.android.gms.internal.firebase_ml.zzrq.a(r10, r11, r13)
            int r2 = r13.zzbia
            r11 = 11
            r3 = 2
            if (r2 == r11) goto L_0x0051
            r11 = r2 & 7
            if (r11 != r3) goto L_0x004c
            com.google.android.gms.internal.firebase_ml.zzsr<?> r11 = r8.zzbph
            com.google.android.gms.internal.firebase_ml.zzsp r0 = r13.zzbid
            com.google.android.gms.internal.firebase_ml.zzum r3 = r8.zzbow
            int r5 = r2 >>> 3
            java.lang.Object r11 = r11.a(r0, r3, r5)
            r0 = r11
            com.google.android.gms.internal.firebase_ml.zztc$zze r0 = (com.google.android.gms.internal.firebase_ml.zztc.zze) r0
            if (r0 != 0) goto L_0x0043
            r3 = r10
            r5 = r12
            r6 = r1
            r7 = r13
            int r11 = com.google.android.gms.internal.firebase_ml.zzrq.a((int) r2, (byte[]) r3, (int) r4, (int) r5, (com.google.android.gms.internal.firebase_ml.zzvv) r6, (com.google.android.gms.internal.firebase_ml.zzrr) r7)
            goto L_0x0018
        L_0x0043:
            com.google.android.gms.internal.firebase_ml.zzuz.zzrc()
            java.lang.NoSuchMethodError r9 = new java.lang.NoSuchMethodError
            r9.<init>()
            throw r9
        L_0x004c:
            int r11 = com.google.android.gms.internal.firebase_ml.zzrq.a((int) r2, (byte[]) r10, (int) r4, (int) r12, (com.google.android.gms.internal.firebase_ml.zzrr) r13)
            goto L_0x0018
        L_0x0051:
            r11 = 0
            r2 = r9
        L_0x0053:
            if (r4 >= r12) goto L_0x0097
            int r4 = com.google.android.gms.internal.firebase_ml.zzrq.a(r10, r4, r13)
            int r5 = r13.zzbia
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
            int r4 = com.google.android.gms.internal.firebase_ml.zzrq.e(r10, r4, r13)
            java.lang.Object r2 = r13.zzbic
            com.google.android.gms.internal.firebase_ml.zzru r2 = (com.google.android.gms.internal.firebase_ml.zzru) r2
            goto L_0x0053
        L_0x0070:
            com.google.android.gms.internal.firebase_ml.zzuz.zzrc()
            java.lang.NoSuchMethodError r9 = new java.lang.NoSuchMethodError
            r9.<init>()
            throw r9
        L_0x0079:
            if (r7 != 0) goto L_0x008e
            int r4 = com.google.android.gms.internal.firebase_ml.zzrq.a(r10, r4, r13)
            int r11 = r13.zzbia
            com.google.android.gms.internal.firebase_ml.zzsr<?> r0 = r8.zzbph
            com.google.android.gms.internal.firebase_ml.zzsp r5 = r13.zzbid
            com.google.android.gms.internal.firebase_ml.zzum r6 = r8.zzbow
            java.lang.Object r0 = r0.a(r5, r6, r11)
            com.google.android.gms.internal.firebase_ml.zztc$zze r0 = (com.google.android.gms.internal.firebase_ml.zztc.zze) r0
            goto L_0x0053
        L_0x008e:
            r6 = 12
            if (r5 == r6) goto L_0x0097
            int r4 = com.google.android.gms.internal.firebase_ml.zzrq.a((int) r5, (byte[]) r10, (int) r4, (int) r12, (com.google.android.gms.internal.firebase_ml.zzrr) r13)
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
            com.google.android.gms.internal.firebase_ml.zztm r9 = com.google.android.gms.internal.firebase_ml.zztm.e()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzus.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.firebase_ml.zzrr):void");
    }

    public final int zzaa(T t) {
        zzvu<?, ?> zzvu = this.zzbpg;
        int c = zzvu.c(zzvu.a(t)) + 0;
        return this.zzbox ? c + this.zzbph.a((Object) t).zzpf() : c;
    }

    public final boolean zzac(T t) {
        return this.zzbph.a((Object) t).isInitialized();
    }

    public final void zze(T t, T t2) {
        zzve.a(this.zzbpg, t, t2);
        if (this.zzbox) {
            zzve.a(this.zzbph, t, t2);
        }
    }

    public final void zzq(T t) {
        this.zzbpg.b(t);
        this.zzbph.c(t);
    }
}
