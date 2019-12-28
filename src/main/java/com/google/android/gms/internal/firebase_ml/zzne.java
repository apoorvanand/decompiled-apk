package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.common.internal.Objects;

public final class zzne<T> {
    private final String zzaoq;
    private final T zzaor;

    private zzne(String str, T t) {
        if (str != null) {
            this.zzaoq = str;
            if (t != null) {
                this.zzaor = t;
                return;
            }
            throw new NullPointerException("Null options");
        }
        throw new NullPointerException("Null firebasePersistentKey");
    }

    public static <T> zzne<T> zzj(String str, T t) {
        return new zzne<>(str, t);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzne) {
            zzne zzne = (zzne) obj;
            return this.zzaoq.equals(zzne.zzaoq) && this.zzaor.equals(zzne.zzaor);
        }
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzaoq, this.zzaor);
    }

    public final String toString() {
        String str = this.zzaoq;
        String valueOf = String.valueOf(this.zzaor);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 58 + String.valueOf(valueOf).length());
        sb.append("MlModelDriverInstanceKey{firebasePersistentKey=");
        sb.append(str);
        sb.append(", options=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
