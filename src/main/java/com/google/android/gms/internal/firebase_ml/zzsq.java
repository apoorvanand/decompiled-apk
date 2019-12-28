package com.google.android.gms.internal.firebase_ml;

final class zzsq {
    private final int number;
    private final Object object;

    zzsq(Object obj, int i) {
        this.object = obj;
        this.number = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzsq)) {
            return false;
        }
        zzsq zzsq = (zzsq) obj;
        return this.object == zzsq.object && this.number == zzsq.number;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.object) * 65535) + this.number;
    }
}
