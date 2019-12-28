package com.google.android.gms.internal.clearcut;

import java.util.Iterator;
import java.util.Map;

final class zzdu<T> implements zzef<T> {
    private final zzdo zzmn;
    private final boolean zzmo;
    private final zzex<?, ?> zzmx;
    private final zzbu<?> zzmy;

    private zzdu(zzex<?, ?> zzex, zzbu<?> zzbu, zzdo zzdo) {
        this.zzmx = zzex;
        this.zzmo = zzbu.a(zzdo);
        this.zzmy = zzbu;
        this.zzmn = zzdo;
    }

    static <T> zzdu<T> a(zzex<?, ?> zzex, zzbu<?> zzbu, zzdo zzdo) {
        return new zzdu<>(zzex, zzbu, zzdo);
    }

    public final boolean equals(T t, T t2) {
        if (!this.zzmx.a(t).equals(this.zzmx.a(t2))) {
            return false;
        }
        if (this.zzmo) {
            return this.zzmy.a((Object) t).equals(this.zzmy.a((Object) t2));
        }
        return true;
    }

    public final int hashCode(T t) {
        int hashCode = this.zzmx.a(t).hashCode();
        return this.zzmo ? (hashCode * 53) + this.zzmy.a((Object) t).hashCode() : hashCode;
    }

    public final T newInstance() {
        return this.zzmn.zzbd().zzbi();
    }

    public final void zza(T t, zzfr zzfr) {
        int i;
        Object obj;
        Iterator<Map.Entry<?, Object>> it = this.zzmy.a((Object) t).iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            zzca zzca = (zzca) next.getKey();
            if (zzca.zzav() != zzfq.MESSAGE || zzca.zzaw() || zzca.zzax()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if (next instanceof zzct) {
                i = zzca.zzc();
                obj = ((zzct) next).zzbs().zzr();
            } else {
                i = zzca.zzc();
                obj = next.getValue();
            }
            zzfr.zza(i, obj);
        }
        zzex<?, ?> zzex = this.zzmx;
        zzex.b(zzex.a(t), zzfr);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r7, byte[] r8, int r9, int r10, com.google.android.gms.internal.clearcut.zzay r11) {
        /*
            r6 = this;
            com.google.android.gms.internal.clearcut.zzcg r7 = (com.google.android.gms.internal.clearcut.zzcg) r7
            com.google.android.gms.internal.clearcut.zzey r0 = r7.zzjp
            com.google.android.gms.internal.clearcut.zzey r1 = com.google.android.gms.internal.clearcut.zzey.zzea()
            if (r0 != r1) goto L_0x0010
            com.google.android.gms.internal.clearcut.zzey r0 = com.google.android.gms.internal.clearcut.zzey.a()
            r7.zzjp = r0
        L_0x0010:
            r7 = r0
        L_0x0011:
            if (r9 >= r10) goto L_0x0069
            int r2 = com.google.android.gms.internal.clearcut.zzax.a(r8, r9, r11)
            int r0 = r11.zzfd
            r9 = 11
            r1 = 2
            if (r0 == r9) goto L_0x0030
            r9 = r0 & 7
            if (r9 != r1) goto L_0x002b
            r1 = r8
            r3 = r10
            r4 = r7
            r5 = r11
            int r9 = com.google.android.gms.internal.clearcut.zzax.a((int) r0, (byte[]) r1, (int) r2, (int) r3, (com.google.android.gms.internal.clearcut.zzey) r4, (com.google.android.gms.internal.clearcut.zzay) r5)
            goto L_0x0011
        L_0x002b:
            int r9 = com.google.android.gms.internal.clearcut.zzax.a(r0, r8, r2, r10, r11)
            goto L_0x0011
        L_0x0030:
            r9 = 0
            r0 = 0
        L_0x0032:
            if (r2 >= r10) goto L_0x005f
            int r2 = com.google.android.gms.internal.clearcut.zzax.a(r8, r2, r11)
            int r3 = r11.zzfd
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
            int r2 = com.google.android.gms.internal.clearcut.zzax.e(r8, r2, r11)
            java.lang.Object r0 = r11.zzff
            com.google.android.gms.internal.clearcut.zzbb r0 = (com.google.android.gms.internal.clearcut.zzbb) r0
            goto L_0x0032
        L_0x004d:
            if (r5 != 0) goto L_0x0056
            int r2 = com.google.android.gms.internal.clearcut.zzax.a(r8, r2, r11)
            int r9 = r11.zzfd
            goto L_0x0032
        L_0x0056:
            r4 = 12
            if (r3 == r4) goto L_0x005f
            int r2 = com.google.android.gms.internal.clearcut.zzax.a(r3, r8, r2, r10, r11)
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
            com.google.android.gms.internal.clearcut.zzco r7 = com.google.android.gms.internal.clearcut.zzco.d()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzdu.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.clearcut.zzay):void");
    }

    public final void zzc(T t) {
        this.zzmx.b(t);
        this.zzmy.c(t);
    }

    public final void zzc(T t, T t2) {
        zzeh.a(this.zzmx, t, t2);
        if (this.zzmo) {
            zzeh.a(this.zzmy, t, t2);
        }
    }

    public final int zzm(T t) {
        zzex<?, ?> zzex = this.zzmx;
        int c = zzex.c(zzex.a(t)) + 0;
        return this.zzmo ? c + this.zzmy.a((Object) t).zzat() : c;
    }

    public final boolean zzo(T t) {
        return this.zzmy.a((Object) t).isInitialized();
    }
}
