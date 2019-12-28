package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.internal.firebase_ml.zztc;

final class zzvb implements zzuk {
    private final int flags;
    private final String info;
    private final Object[] zzbot;
    private final zzum zzbow;

    zzvb(zzum zzum, String str, Object[] objArr) {
        this.zzbow = zzum;
        this.info = str;
        this.zzbot = objArr;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.flags = charAt;
            return;
        }
        char c = charAt & 8191;
        int i = 13;
        int i2 = 1;
        while (true) {
            int i3 = i2 + 1;
            char charAt2 = str.charAt(i2);
            if (charAt2 >= 55296) {
                c |= (charAt2 & 8191) << i;
                i += 13;
                i2 = i3;
            } else {
                this.flags = c | (charAt2 << i);
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final String a() {
        return this.info;
    }

    /* access modifiers changed from: package-private */
    public final Object[] b() {
        return this.zzbot;
    }

    public final int zzqv() {
        return (this.flags & 1) == 1 ? zztc.zzf.zzbmr : zztc.zzf.zzbms;
    }

    public final boolean zzqw() {
        return (this.flags & 2) == 2;
    }

    public final zzum zzqx() {
        return this.zzbow;
    }
}
