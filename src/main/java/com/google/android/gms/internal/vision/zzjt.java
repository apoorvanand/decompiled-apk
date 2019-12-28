package com.google.android.gms.internal.vision;

import java.io.IOException;

public abstract class zzjt {
    protected volatile int b = -1;

    public static final <T extends zzjt> T zza(T t, byte[] bArr) {
        return zzb(t, bArr, 0, bArr.length);
    }

    public static final void zza(zzjt zzjt, byte[] bArr, int i, int i2) {
        try {
            zzjl zzl = zzjl.zzl(bArr, 0, i2);
            zzjt.zza(zzl);
            zzl.zzea();
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    private static final <T extends zzjt> T zzb(T t, byte[] bArr, int i, int i2) {
        try {
            zzjk zzk = zzjk.zzk(bArr, 0, i2);
            t.zza(zzk);
            zzk.zzak(0);
            return t;
        } catch (zzjs e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e2);
        }
    }

    /* access modifiers changed from: protected */
    public int a() {
        return 0;
    }

    public String toString() {
        return zzju.zzb(this);
    }

    public abstract zzjt zza(zzjk zzjk);

    public void zza(zzjl zzjl) {
    }

    public final int zzeq() {
        int a = a();
        this.b = a;
        return a;
    }

    /* renamed from: zzhr */
    public zzjt clone() {
        return (zzjt) super.clone();
    }
}
