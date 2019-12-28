package com.google.android.gms.internal.firebase_ml;

public final class zziq extends zzey {
    static {
        boolean z = zzeo.zzsk.intValue() == 1 && zzeo.zzsl.intValue() >= 15;
        Object[] objArr = {zzeo.VERSION};
        if (!z) {
            throw new IllegalStateException(zzlg.zzb("You are currently running with version %s of google-api-client. You need at least version 1.15 of google-api-client to run version 1.25.0-SNAPSHOT of the Cloud Vision API library.", objArr));
        }
    }

    zziq(zzir zzir) {
        super(zzir);
    }

    /* access modifiers changed from: protected */
    public final void a(zzet<?> zzet) {
        super.a(zzet);
    }
}
