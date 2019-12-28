package com.google.android.gms.internal.firebase_ml;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

final class zzke<T> extends zzku<T> {
    static final zzke<Object> a = new zzke<>();

    private zzke() {
    }

    public final boolean equals(@NullableDecl Object obj) {
        return obj == this;
    }

    public final T get() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    public final int hashCode() {
        return 2040732332;
    }

    public final boolean isPresent() {
        return false;
    }

    public final String toString() {
        return "Optional.absent()";
    }
}
