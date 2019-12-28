package com.google.android.gms.internal.firebase_ml;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class zzgn implements zzia {
    private final zzgl zzwj;
    private final Set<String> zzwl;

    protected zzgn(zzgo zzgo) {
        this.zzwj = zzgo.a;
        this.zzwl = new HashSet(zzgo.b);
    }

    public final <T> T zza(InputStream inputStream, Charset charset, Class<T> cls) {
        zzgp zza = this.zzwj.zza(inputStream, charset);
        if (!this.zzwl.isEmpty()) {
            try {
                boolean z = (zza.zza(this.zzwl) == null || zza.zzfs() == zzgt.END_OBJECT) ? false : true;
                Object[] objArr = {this.zzwl};
                if (!z) {
                    throw new IllegalArgumentException(zzlg.zzb("wrapper key(s) not found: %s", objArr));
                }
            } catch (Throwable th) {
                zza.close();
                throw th;
            }
        }
        return zza.zza(cls, true, (zzgj) null);
    }

    public final zzgl zzec() {
        return this.zzwj;
    }

    public final Set<String> zzfo() {
        return Collections.unmodifiableSet(this.zzwl);
    }
}
