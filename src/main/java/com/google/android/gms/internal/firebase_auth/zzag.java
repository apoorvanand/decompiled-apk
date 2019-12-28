package com.google.android.gms.internal.firebase_auth;

final class zzag extends zzad {
    private final char zzgj;

    zzag(char c) {
        this.zzgj = c;
    }

    public final String toString() {
        String a = zzae.zzb(this.zzgj);
        StringBuilder sb = new StringBuilder(String.valueOf(a).length() + 18);
        sb.append("CharMatcher.is('");
        sb.append(a);
        sb.append("')");
        return sb.toString();
    }

    public final boolean zza(char c) {
        return c == this.zzgj;
    }
}
