package com.google.android.gms.internal.firebase_ml;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;

public final class zzgu extends zzgl {
    private final zzgp zza(Reader reader) {
        return new zzgx(this, new zzpl(reader));
    }

    public static zzgu zzgh() {
        return zzgv.a;
    }

    public final zzgm zza(OutputStream outputStream, Charset charset) {
        return new zzgw(this, new zzpo(new OutputStreamWriter(outputStream, charset)));
    }

    public final zzgp zza(InputStream inputStream) {
        return zza((Reader) new InputStreamReader(inputStream, zzhi.UTF_8));
    }

    public final zzgp zza(InputStream inputStream, Charset charset) {
        return charset == null ? zza(inputStream) : zza((Reader) new InputStreamReader(inputStream, charset));
    }

    public final zzgp zzaj(String str) {
        return zza((Reader) new StringReader(str));
    }
}
