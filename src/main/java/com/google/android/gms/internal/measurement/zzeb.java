package com.google.android.gms.internal.measurement;

public abstract class zzeb {
    int a;
    int b;
    zzec c;
    private int zzadr;
    private boolean zzadt;

    private zzeb() {
        this.b = 100;
        this.zzadr = Integer.MAX_VALUE;
        this.zzadt = false;
    }

    static zzeb a(byte[] bArr, int i, int i2, boolean z) {
        zzed zzed = new zzed(bArr, i, i2, false);
        try {
            zzed.zzaw(i2);
            return zzed;
        } catch (zzfi e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static int zzaz(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public static long zzbm(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    public static zzeb zzd(byte[] bArr, int i, int i2) {
        return a(bArr, i, i2, false);
    }

    /* access modifiers changed from: package-private */
    public abstract long a();

    public abstract double readDouble();

    public abstract float readFloat();

    public abstract String readString();

    public abstract <T extends zzgi> T zza(zzgr<T> zzgr, zzel zzel);

    public abstract void zzat(int i);

    public abstract boolean zzau(int i);

    public final int zzav(int i) {
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

    public abstract int zzaw(int i);

    public abstract void zzax(int i);

    public abstract void zzay(int i);

    public abstract int zzsg();

    public abstract long zzsh();

    public abstract long zzsi();

    public abstract int zzsj();

    public abstract long zzsk();

    public abstract int zzsl();

    public abstract boolean zzsm();

    public abstract String zzsn();

    public abstract zzdp zzso();

    public abstract int zzsp();

    public abstract int zzsq();

    public abstract int zzsr();

    public abstract long zzss();

    public abstract int zzst();

    public abstract long zzsu();

    public abstract boolean zzsw();

    public abstract int zzsx();
}
