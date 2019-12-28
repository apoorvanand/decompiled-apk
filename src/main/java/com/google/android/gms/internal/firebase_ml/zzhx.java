package com.google.android.gms.internal.firebase_ml;

import java.io.FilterOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class zzhx extends FilterOutputStream {
    private final zzhv zzaag;

    public zzhx(OutputStream outputStream, Logger logger, Level level, int i) {
        super(outputStream);
        this.zzaag = new zzhv(logger, level, i);
    }

    public final void close() {
        this.zzaag.close();
        super.close();
    }

    public final void write(int i) {
        this.out.write(i);
        this.zzaag.write(i);
    }

    public final void write(byte[] bArr, int i, int i2) {
        this.out.write(bArr, i, i2);
        this.zzaag.write(bArr, i, i2);
    }

    public final zzhv zzgt() {
        return this.zzaag;
    }
}
