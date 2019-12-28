package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;

public final class zzft {
    int a;
    String b;
    zzfl c;
    String d;
    String e;

    public zzft(int i, String str, zzfl zzfl) {
        zzky.checkArgument(i >= 0);
        this.a = i;
        this.b = str;
        this.c = (zzfl) zzky.checkNotNull(zzfl);
    }

    public zzft(zzfr zzfr) {
        this(zzfr.getStatusCode(), zzfr.getStatusMessage(), zzfr.zzeo());
        try {
            this.d = zzfr.zzev();
            if (this.d.length() == 0) {
                this.d = null;
            }
        } catch (IOException e2) {
            zzli.zzb(e2);
        }
        StringBuilder zzc = zzfs.zzc(zzfr);
        if (this.d != null) {
            zzc.append(zzif.zzaai);
            zzc.append(this.d);
        }
        this.e = zzc.toString();
    }

    public final zzft zzae(String str) {
        this.e = str;
        return this;
    }

    public final zzft zzaf(String str) {
        this.d = str;
        return this;
    }
}
