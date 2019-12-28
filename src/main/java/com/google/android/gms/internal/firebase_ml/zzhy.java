package com.google.android.gms.internal.firebase_ml;

import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class zzhy implements zzie {
    private final Logger logger;
    private final Level zzaaf;
    private final zzie zztx;
    private final int zzuo;

    public zzhy(zzie zzie, Logger logger2, Level level, int i) {
        this.zztx = zzie;
        this.logger = logger2;
        this.zzaaf = level;
        this.zzuo = i;
    }

    /* JADX INFO: finally extract failed */
    public final void writeTo(OutputStream outputStream) {
        zzhx zzhx = new zzhx(outputStream, this.logger, this.zzaaf, this.zzuo);
        try {
            this.zztx.writeTo(zzhx);
            zzhx.zzgt().close();
            outputStream.flush();
        } catch (Throwable th) {
            zzhx.zzgt().close();
            throw th;
        }
    }
}
