package com.google.android.gms.auth.api.credentials;

import com.google.android.gms.auth.api.Auth;

public final class CredentialsOptions extends Auth.AuthCredentialsOptions {
    public static final CredentialsOptions DEFAULT = ((CredentialsOptions) new Builder().zzc());

    public static final class Builder extends Auth.AuthCredentialsOptions.Builder {
        /* renamed from: build */
        public final CredentialsOptions zzc() {
            return new CredentialsOptions(this);
        }

        public final Builder forceEnableSaveDialog() {
            this.a = true;
            return this;
        }
    }

    private CredentialsOptions(Builder builder) {
        super(builder);
    }
}
