package com.google.android.gms.internal.firebase_ml;

import java.net.URL;
import java.net.URLConnection;

public final class zzny {
    private final URL url;

    zzny(String str) {
        this.url = new URL(str);
    }

    public final URLConnection openConnection() {
        return this.url.openConnection();
    }
}
