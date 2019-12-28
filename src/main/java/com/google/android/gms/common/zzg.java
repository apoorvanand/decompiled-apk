package com.google.android.gms.common;

import java.lang.ref.WeakReference;

abstract class zzg extends zze {
    private static final WeakReference<byte[]> zzw = new WeakReference<>((Object) null);
    private WeakReference<byte[]> zzv = zzw;

    zzg(byte[] bArr) {
        super(bArr);
    }

    /* access modifiers changed from: package-private */
    public final byte[] a() {
        byte[] bArr;
        synchronized (this) {
            bArr = (byte[]) this.zzv.get();
            if (bArr == null) {
                bArr = b();
                this.zzv = new WeakReference<>(bArr);
            }
        }
        return bArr;
    }

    /* access modifiers changed from: protected */
    public abstract byte[] b();
}
