package com.google.android.gms.internal.clearcut;

import java.io.IOException;

public class zzfz {
    protected volatile int b = -1;

    public static final void zza(zzfz zzfz, byte[] bArr, int i, int i2) {
        try {
            zzfs zzh = zzfs.zzh(bArr, 0, i2);
            zzfz.zza(zzh);
            zzh.zzem();
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    /* access modifiers changed from: protected */
    public int a() {
        return 0;
    }

    public String toString() {
        return zzga.zza(this);
    }

    public void zza(zzfs zzfs) {
    }

    public final int zzas() {
        int a = a();
        this.b = a;
        return a;
    }

    /* renamed from: zzep */
    public zzfz clone() {
        return (zzfz) super.clone();
    }
}
