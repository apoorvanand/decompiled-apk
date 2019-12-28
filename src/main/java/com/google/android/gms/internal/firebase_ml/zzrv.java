package com.google.android.gms.internal.firebase_ml;

import java.util.NoSuchElementException;

final class zzrv extends zzrx {
    private final int limit = this.zzbij.size();
    private int position = 0;
    private final /* synthetic */ zzru zzbij;

    zzrv(zzru zzru) {
        this.zzbij = zzru;
    }

    public final boolean hasNext() {
        return this.position < this.limit;
    }

    public final byte nextByte() {
        int i = this.position;
        if (i < this.limit) {
            this.position = i + 1;
            return this.zzbij.a(i);
        }
        throw new NoSuchElementException();
    }
}
