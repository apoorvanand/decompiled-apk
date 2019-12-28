package com.google.android.gms.internal.firebase_ml;

final class zzkk extends zzkj {
    private final char zzabf;

    zzkk(char c) {
        this.zzabf = c;
    }

    public final String toString() {
        String a = zzki.zzc(this.zzabf);
        StringBuilder sb = new StringBuilder(String.valueOf(a).length() + 18);
        sb.append("CharMatcher.is('");
        sb.append(a);
        sb.append("')");
        return sb.toString();
    }

    public final boolean zzb(char c) {
        return c == this.zzabf;
    }
}
