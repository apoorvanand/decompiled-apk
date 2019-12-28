package com.google.android.gms.internal.firebase_ml;

import java.io.OutputStream;

final class zzhh extends OutputStream {
    long a;

    zzhh() {
    }

    public final void write(int i) {
        this.a++;
    }

    public final void write(byte[] bArr, int i, int i2) {
        this.a += (long) i2;
    }
}
