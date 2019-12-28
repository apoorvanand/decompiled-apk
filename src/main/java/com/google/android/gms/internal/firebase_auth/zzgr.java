package com.google.android.gms.internal.firebase_auth;

public abstract class zzgr {
    int a;
    int b;
    int c;
    zzgy d;
    private boolean zzwi;

    private zzgr() {
        this.b = 100;
        this.c = Integer.MAX_VALUE;
        this.zzwi = false;
    }

    static zzgr a(byte[] bArr, int i, int i2, boolean z) {
        zzgt zzgt = new zzgt(bArr, 0, i2, false);
        try {
            zzgt.zzu(i2);
            return zzgt;
        } catch (zzic e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static long zza(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    public static int zzw(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    /* access modifiers changed from: package-private */
    public abstract long a();

    public abstract double readDouble();

    public abstract float readFloat();

    public abstract String readString();

    public abstract int zzgi();

    public abstract long zzgj();

    public abstract long zzgk();

    public abstract int zzgl();

    public abstract long zzgm();

    public abstract int zzgn();

    public abstract boolean zzgo();

    public abstract String zzgp();

    public abstract zzgf zzgq();

    public abstract int zzgr();

    public abstract int zzgs();

    public abstract int zzgt();

    public abstract long zzgu();

    public abstract int zzgv();

    public abstract long zzgw();

    public abstract boolean zzgy();

    public abstract int zzgz();

    public abstract void zzs(int i);

    public abstract boolean zzt(int i);

    public abstract int zzu(int i);

    public abstract void zzv(int i);
}
