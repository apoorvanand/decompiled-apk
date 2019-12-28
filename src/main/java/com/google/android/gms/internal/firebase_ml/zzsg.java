package com.google.android.gms.internal.firebase_ml;

public abstract class zzsg {
    private int zzbin;
    private int zzbio;
    private boolean zzbip;

    private zzsg() {
        this.zzbin = 100;
        this.zzbio = Integer.MAX_VALUE;
        this.zzbip = false;
    }

    static zzsg a(byte[] bArr, int i, int i2, boolean z) {
        zzsi zzsi = new zzsi(bArr, 0, i2, false);
        try {
            zzsi.zzcf(i2);
            return zzsi;
        } catch (zztm e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static int zzcg(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public static long zzm(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    public abstract int zzcf(int i);

    public abstract int zzor();
}
