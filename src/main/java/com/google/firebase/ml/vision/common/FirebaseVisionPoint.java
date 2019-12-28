package com.google.firebase.ml.vision.common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.firebase_ml.zzkp;

public final class FirebaseVisionPoint {
    private final Float zzavc;
    private final Float zzavd;
    private final Float zzave = null;

    public FirebaseVisionPoint(@NonNull Float f, @NonNull Float f2, @Nullable Float f3) {
        this.zzavc = f;
        this.zzavd = f2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FirebaseVisionPoint)) {
            return false;
        }
        FirebaseVisionPoint firebaseVisionPoint = (FirebaseVisionPoint) obj;
        return Objects.equal(this.zzavc, firebaseVisionPoint.zzavc) && Objects.equal(this.zzavd, firebaseVisionPoint.zzavd) && Objects.equal((Object) null, (Object) null);
    }

    public final Float getX() {
        return this.zzavc;
    }

    public final Float getY() {
        return this.zzavd;
    }

    @Nullable
    public final Float getZ() {
        return null;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzavc, this.zzavd, null);
    }

    public final String toString() {
        return zzkp.zzaw("FirebaseVisionPoint").zzh("x", this.zzavc).zzh("y", this.zzavd).zzh("z", (Object) null).toString();
    }
}
