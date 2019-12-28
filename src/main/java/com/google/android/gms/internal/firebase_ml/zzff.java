package com.google.android.gms.internal.firebase_ml;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

final class zzff extends BufferedOutputStream {
    zzff(zzfe zzfe, OutputStream outputStream) {
        super(outputStream);
    }

    public final void close() {
        try {
            flush();
        } catch (IOException unused) {
        }
    }
}
