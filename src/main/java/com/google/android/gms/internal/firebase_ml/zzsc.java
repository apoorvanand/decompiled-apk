package com.google.android.gms.internal.firebase_ml;

final class zzsc {
    private final byte[] buffer;
    private final zzsj zzbim;

    private zzsc(int i) {
        this.buffer = new byte[i];
        this.zzbim = zzsj.zzh(this.buffer);
    }

    /* synthetic */ zzsc(int i, zzrv zzrv) {
        this(i);
    }

    public final zzru zzop() {
        this.zzbim.zzot();
        return new zzse(this.buffer);
    }

    public final zzsj zzoq() {
        return this.zzbim;
    }
}
