package com.google.android.gms.internal.firebase_ml;

import java.io.FilterInputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class zzhw extends FilterInputStream {
    private final zzhv zzaag;

    public zzhw(InputStream inputStream, Logger logger, Level level, int i) {
        super(inputStream);
        this.zzaag = new zzhv(logger, level, i);
    }

    public final void close() {
        this.zzaag.close();
        super.close();
    }

    public final int read() {
        int read = super.read();
        this.zzaag.write(read);
        return read;
    }

    public final int read(byte[] bArr, int i, int i2) {
        int read = super.read(bArr, i, i2);
        if (read > 0) {
            this.zzaag.write(bArr, i, read);
        }
        return read;
    }
}
