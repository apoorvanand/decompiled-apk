package com.google.android.gms.internal.firebase_ml;

public class zzew implements zzex {
    private final String key;
    private final String zzto;

    public zzew() {
        this((String) null);
    }

    public zzew(String str) {
        this(str, (String) null);
    }

    private zzew(String str, String str2) {
        this.key = str;
        this.zzto = null;
    }

    public void zza(zzet<?> zzet) {
        String str = this.key;
        if (str != null) {
            zzet.put("key", str);
        }
    }
}
