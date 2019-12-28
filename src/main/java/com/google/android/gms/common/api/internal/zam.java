package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.Preconditions;

final class zam {
    private final int zadh;
    private final ConnectionResult zadi;

    zam(ConnectionResult connectionResult, int i) {
        Preconditions.checkNotNull(connectionResult);
        this.zadi = connectionResult;
        this.zadh = i;
    }

    /* access modifiers changed from: package-private */
    public final int a() {
        return this.zadh;
    }

    /* access modifiers changed from: package-private */
    public final ConnectionResult b() {
        return this.zadi;
    }
}
