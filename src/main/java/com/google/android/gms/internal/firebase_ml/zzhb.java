package com.google.android.gms.internal.firebase_ml;

public abstract class zzhb {
    protected final byte a;
    protected final int b;
    @Deprecated
    private final byte zzxu;
    private final int zzxw;
    private final int zzxx;
    private final int zzxz;

    protected zzhb(int i, int i2, int i3, int i4) {
        this(3, 4, i3, i4, (byte) 61);
    }

    private zzhb(int i, int i2, int i3, int i4, byte b2) {
        this.zzxu = 61;
        this.zzxw = 3;
        this.zzxx = 4;
        this.b = i3 > 0 && i4 > 0 ? (i3 / 4) << 2 : 0;
        this.zzxz = i4;
        this.a = 61;
    }

    /* access modifiers changed from: package-private */
    public abstract void a(byte[] bArr, int i, int i2, zzhc zzhc);

    /* access modifiers changed from: protected */
    public abstract boolean a(byte b2);

    /* access modifiers changed from: protected */
    public final byte[] a(int i, zzhc zzhc) {
        if (zzhc.b != null && zzhc.b.length >= zzhc.c + i) {
            return zzhc.b;
        }
        if (zzhc.b == null) {
            zzhc.b = new byte[8192];
            zzhc.c = 0;
            zzhc.d = 0;
        } else {
            byte[] bArr = new byte[(zzhc.b.length << 1)];
            System.arraycopy(zzhc.b, 0, bArr, 0, zzhc.b.length);
            zzhc.b = bArr;
        }
        return zzhc.b;
    }

    public final long zzd(byte[] bArr) {
        int length = bArr.length;
        int i = this.zzxw;
        long j = ((long) (((length + i) - 1) / i)) * ((long) this.zzxx);
        int i2 = this.b;
        return i2 > 0 ? j + ((((((long) i2) + j) - 1) / ((long) i2)) * ((long) this.zzxz)) : j;
    }
}
