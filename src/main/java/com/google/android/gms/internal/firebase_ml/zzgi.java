package com.google.android.gms.internal.firebase_ml;

import java.io.OutputStream;

public final class zzgi extends zzfc {
    private final Object data;
    private final zzgl zzwj;
    private String zzwk;

    public zzgi(zzgl zzgl, Object obj) {
        super("application/json; charset=UTF-8");
        this.zzwj = (zzgl) zzky.checkNotNull(zzgl);
        this.data = zzky.checkNotNull(obj);
    }

    public final void writeTo(OutputStream outputStream) {
        zzgm zza = this.zzwj.zza(outputStream, a());
        if (this.zzwk != null) {
            zza.zzfk();
            zza.zzak(this.zzwk);
        }
        zza.zzd(this.data);
        if (this.zzwk != null) {
            zza.zzfl();
        }
        zza.flush();
    }

    public final zzgi zzai(String str) {
        this.zzwk = str;
        return this;
    }
}
