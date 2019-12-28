package com.google.android.gms.internal.vision;

public abstract class zzez {
    int a;
    int b;
    zzfc c;
    private int zzsh;
    private boolean zzsj;

    private zzez() {
        this.b = 100;
        this.zzsh = Integer.MAX_VALUE;
        this.zzsj = false;
    }

    private static zzez zza(byte[] bArr, int i, int i2, boolean z) {
        zzfb zzfb = new zzfb(bArr, i, i2, false);
        try {
            zzfb.zzan(i2);
            return zzfb;
        } catch (zzgf e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static int zzaq(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public static long zzd(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    public static zzez zze(byte[] bArr, int i, int i2) {
        return zza(bArr, i, i2, false);
    }

    public static zzez zzf(byte[] bArr) {
        return zza(bArr, 0, bArr.length, false);
    }

    /* access modifiers changed from: package-private */
    public abstract long a();

    public abstract double readDouble();

    public abstract float readFloat();

    public abstract String readString();

    public abstract <T extends zzhf> T zza(zzhq<T> zzhq, zzfk zzfk);

    public abstract void zzak(int i);

    public abstract boolean zzal(int i);

    public final int zzam(int i) {
        if (i >= 0) {
            int i2 = this.b;
            this.b = i;
            return i2;
        }
        StringBuilder sb = new StringBuilder(47);
        sb.append("Recursion limit cannot be negative: ");
        sb.append(i);
        throw new IllegalArgumentException(sb.toString());
    }

    public abstract int zzan(int i);

    public abstract void zzao(int i);

    public abstract void zzap(int i);

    public abstract boolean zzcm();

    public abstract long zzcp();

    public abstract long zzcq();

    public abstract int zzcr();

    public abstract long zzcs();

    public abstract int zzct();

    public abstract boolean zzcu();

    public abstract String zzcv();

    public abstract zzeo zzcw();

    public abstract int zzcx();

    public abstract int zzcy();

    public abstract int zzcz();

    public abstract long zzda();

    public abstract int zzdb();

    public abstract long zzdc();

    public abstract int zzdq();

    public abstract int zzds();
}
