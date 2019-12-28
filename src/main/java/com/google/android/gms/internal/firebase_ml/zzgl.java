package com.google.android.gms.internal.firebase_ml;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

public abstract class zzgl {
    private final String zza(Object obj, boolean z) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        zzgm zza = zza((OutputStream) byteArrayOutputStream, zzhi.UTF_8);
        if (z) {
            zza.zzfn();
        }
        zza.zzd(obj);
        zza.flush();
        return byteArrayOutputStream.toString("UTF-8");
    }

    public final String toString(Object obj) {
        return zza(obj, false);
    }

    public abstract zzgm zza(OutputStream outputStream, Charset charset);

    public abstract zzgp zza(InputStream inputStream);

    public abstract zzgp zza(InputStream inputStream, Charset charset);

    public abstract zzgp zzaj(String str);

    public final String zzc(Object obj) {
        return zza(obj, true);
    }
}
