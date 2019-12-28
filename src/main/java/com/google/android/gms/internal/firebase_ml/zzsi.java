package com.google.android.gms.internal.firebase_ml;

final class zzsi extends zzsg {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzbiq;
    private int zzbir;
    private int zzbis;
    private int zzbit;

    private zzsi(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzbit = Integer.MAX_VALUE;
        this.buffer = bArr;
        this.limit = i2 + i;
        this.pos = i;
        this.zzbis = this.pos;
        this.zzbiq = z;
    }

    public final int zzcf(int i) {
        if (i >= 0) {
            int zzor = i + zzor();
            int i2 = this.zzbit;
            if (zzor <= i2) {
                this.zzbit = zzor;
                this.limit += this.zzbir;
                int i3 = this.limit;
                int i4 = i3 - this.zzbis;
                int i5 = this.zzbit;
                if (i4 > i5) {
                    this.zzbir = i4 - i5;
                    this.limit = i3 - this.zzbir;
                } else {
                    this.zzbir = 0;
                }
                return i2;
            }
            throw zztm.a();
        }
        throw zztm.b();
    }

    public final int zzor() {
        return this.pos - this.zzbis;
    }
}
