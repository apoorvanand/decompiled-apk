package com.google.firebase.iid;

import android.util.Base64;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.VisibleForTesting;
import java.security.KeyPair;

final class zzaa {
    private final KeyPair zzby;
    /* access modifiers changed from: private */
    public final long zzbz;

    @VisibleForTesting
    zzaa(KeyPair keyPair, long j) {
        this.zzby = keyPair;
        this.zzbz = j;
    }

    /* access modifiers changed from: private */
    public final String zzv() {
        return Base64.encodeToString(this.zzby.getPublic().getEncoded(), 11);
    }

    /* access modifiers changed from: private */
    public final String zzw() {
        return Base64.encodeToString(this.zzby.getPrivate().getEncoded(), 11);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzaa)) {
            return false;
        }
        zzaa zzaa = (zzaa) obj;
        return this.zzbz == zzaa.zzbz && this.zzby.getPublic().equals(zzaa.zzby.getPublic()) && this.zzby.getPrivate().equals(zzaa.zzby.getPrivate());
    }

    /* access modifiers changed from: package-private */
    public final long getCreationTime() {
        return this.zzbz;
    }

    /* access modifiers changed from: package-private */
    public final KeyPair getKeyPair() {
        return this.zzby;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzby.getPublic(), this.zzby.getPrivate(), Long.valueOf(this.zzbz));
    }
}
