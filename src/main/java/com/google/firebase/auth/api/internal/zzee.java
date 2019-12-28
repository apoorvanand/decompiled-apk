package com.google.firebase.auth.api.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;

public final class zzee extends zzai implements Api.ApiOptions.HasOptions {
    private final String zzht;

    private zzee(String str) {
        this.zzht = Preconditions.checkNotEmpty(str, "A valid API key must be provided");
    }

    /* synthetic */ zzee(String str, zzeb zzeb) {
        this(str);
    }

    public final /* synthetic */ Object clone() {
        return new zzed(this.zzht).zzef();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzee)) {
            return false;
        }
        return Objects.equal(this.zzht, ((zzee) obj).zzht);
    }

    public final String getApiKey() {
        return this.zzht;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzht);
    }

    public final /* synthetic */ zzai zzdt() {
        return (zzee) clone();
    }
}
