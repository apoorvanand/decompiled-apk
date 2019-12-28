package com.google.android.gms.internal.firebase_ml;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

final class zzkz<T> extends zzku<T> {
    private final T zzabm;

    zzkz(T t) {
        this.zzabm = t;
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (obj instanceof zzkz) {
            return this.zzabm.equals(((zzkz) obj).zzabm);
        }
        return false;
    }

    public final T get() {
        return this.zzabm;
    }

    public final int hashCode() {
        return this.zzabm.hashCode() + 1502476572;
    }

    public final boolean isPresent() {
        return true;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzabm);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 13);
        sb.append("Optional.of(");
        sb.append(valueOf);
        sb.append(")");
        return sb.toString();
    }
}
