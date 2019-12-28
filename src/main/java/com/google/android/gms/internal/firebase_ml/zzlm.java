package com.google.android.gms.internal.firebase_ml;

import java.util.List;

final class zzlm extends zzlj {
    private final zzlk zzadf = new zzlk();

    zzlm() {
    }

    public final void zza(Throwable th, Throwable th2) {
        if (th2 == th) {
            throw new IllegalArgumentException("Self suppression is not allowed.", th2);
        } else if (th2 != null) {
            this.zzadf.zza(th, true).add(th2);
        } else {
            throw new NullPointerException("The suppressed exception cannot be null.");
        }
    }

    public final void zzb(Throwable th) {
        th.printStackTrace();
        List<Throwable> zza = this.zzadf.zza(th, false);
        if (zza != null) {
            synchronized (zza) {
                for (Throwable printStackTrace : zza) {
                    System.err.print("Suppressed: ");
                    printStackTrace.printStackTrace();
                }
            }
        }
    }
}
