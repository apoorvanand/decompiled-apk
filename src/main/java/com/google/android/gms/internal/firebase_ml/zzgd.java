package com.google.android.gms.internal.firebase_ml;

import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;

public final class zzgd implements zzgc {
    private final Proxy zzwb;

    public zzgd() {
        this((Proxy) null);
    }

    public zzgd(Proxy proxy) {
        this.zzwb = proxy;
    }

    public final HttpURLConnection zza(URL url) {
        Proxy proxy = this.zzwb;
        return (HttpURLConnection) (proxy == null ? url.openConnection() : url.openConnection(proxy));
    }
}
