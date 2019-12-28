package com.google.android.gms.internal.firebase_ml;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

public final class zzga extends zzfc {
    private Object data;

    public zzga(Object obj) {
        super(zzgb.MEDIA_TYPE);
        this.data = zzky.checkNotNull(obj);
    }

    private static boolean zza(boolean z, Writer writer, String str, Object obj) {
        if (obj != null && !zzhl.isNull(obj)) {
            if (z) {
                z = false;
            } else {
                writer.write("&");
            }
            writer.write(str);
            String zzan = zzik.zzan(obj instanceof Enum ? zzhr.zza((Enum<?>) (Enum) obj).getName() : obj.toString());
            if (zzan.length() != 0) {
                writer.write("=");
                writer.write(zzan);
            }
        }
        return z;
    }

    public final void writeTo(OutputStream outputStream) {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, a()));
        boolean z = true;
        for (Map.Entry next : zzhl.zzf(this.data).entrySet()) {
            Object value = next.getValue();
            if (value != null) {
                String zzan = zzik.zzan((String) next.getKey());
                Class<?> cls = value.getClass();
                if ((value instanceof Iterable) || cls.isArray()) {
                    for (Object zza : zzig.zzi(value)) {
                        z = zza(z, bufferedWriter, zzan, zza);
                    }
                } else {
                    z = zza(z, bufferedWriter, zzan, value);
                }
            }
        }
        bufferedWriter.flush();
    }
}
