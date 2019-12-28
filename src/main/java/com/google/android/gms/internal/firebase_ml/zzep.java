package com.google.android.gms.internal.firebase_ml;

public final class zzep implements zzfk, zzfq {
    private final boolean zzsn;

    public zzep() {
        this(false);
    }

    private zzep(boolean z) {
        this.zzsn = false;
    }

    public final void zza(zzfo zzfo) {
        zzfo.zza((zzfk) this);
    }

    public final void zzb(zzfo zzfo) {
        String requestMethod = zzfo.getRequestMethod();
        boolean z = true;
        if (requestMethod.equals("POST") || ((!requestMethod.equals("GET") || zzfo.zzek().zzeg().length() <= 2048) && zzfo.zzej().zzag(requestMethod))) {
            z = false;
        }
        if (z) {
            String requestMethod2 = zzfo.getRequestMethod();
            zzfo.zzad("POST");
            zzfo.zzeo().zzb("X-HTTP-Method-Override", requestMethod2);
            if (requestMethod2.equals("GET")) {
                zzfo.zza((zzfh) new zzga((zzfg) zzfo.zzek().clone()));
                zzfo.zzek().clear();
            } else if (zzfo.zzel() == null) {
                zzfo.zza((zzfh) new zzfd());
            }
        }
    }
}
