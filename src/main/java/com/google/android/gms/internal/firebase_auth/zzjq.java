package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzhs;

final class zzjq implements zzja {
    private final int flags;
    private final String info;
    private final zzjc zzacr;
    private final Object[] zzacy;

    zzjq(zzjc zzjc, String str, Object[] objArr) {
        this.zzacr = zzjc;
        this.info = str;
        this.zzacy = objArr;
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
        return this.zzacy;
    }

    public final int zzjo() {
        return (this.flags & 1) == 1 ? zzhs.zzd.zzaav : zzhs.zzd.zzaaw;
    }

    public final boolean zzjp() {
        return (this.flags & 2) == 2;
    }

    public final zzjc zzjq() {
        return this.zzacr;
    }
}
