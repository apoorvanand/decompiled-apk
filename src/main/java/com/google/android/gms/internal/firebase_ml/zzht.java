package com.google.android.gms.internal.firebase_ml;

public final class zzht {
    /* JADX INFO: finally extract failed */
    public static long zzb(zzie zzie) {
        zzhh zzhh = new zzhh();
        try {
            zzie.writeTo(zzhh);
            zzhh.close();
            return zzhh.a;
        } catch (Throwable th) {
            zzhh.close();
            throw th;
        }
    }
}
