package com.google.android.gms.internal.firebase_ml;

import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

public final class zzfe implements zzfi {
    public final String getName() {
        return "gzip";
    }

    public final void zza(zzie zzie, OutputStream outputStream) {
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(new zzff(this, outputStream));
        zzie.writeTo(gZIPOutputStream);
        gZIPOutputStream.close();
    }
}
