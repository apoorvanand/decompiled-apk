package com.google.firebase.ml.vision.label;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import javax.annotation.concurrent.Immutable;

@Immutable
public class FirebaseVisionCloudImageLabelerOptions {
    private final float zzath;
    private final boolean zzauc;

    public static class Builder {
        private float zzath = 0.5f;
        private boolean zzauc = false;

        public FirebaseVisionCloudImageLabelerOptions build() {
            return new FirebaseVisionCloudImageLabelerOptions(this.zzath, this.zzauc);
        }

        public Builder enforceCertFingerprintMatch() {
            this.zzauc = true;
            return this;
        }

        public Builder setConfidenceThreshold(float f) {
            Preconditions.checkArgument(Float.compare(f, 0.0f) >= 0 && Float.compare(f, 1.0f) <= 0, "Confidence Threshold should be in range [0.0f, 1.0f].");
            this.zzath = f;
            return this;
        }
    }

    private FirebaseVisionCloudImageLabelerOptions(float f, boolean z) {
        this.zzath = f;
        this.zzauc = z;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FirebaseVisionCloudImageLabelerOptions)) {
            return false;
        }
        FirebaseVisionCloudImageLabelerOptions firebaseVisionCloudImageLabelerOptions = (FirebaseVisionCloudImageLabelerOptions) obj;
        return Float.compare(this.zzath, firebaseVisionCloudImageLabelerOptions.zzath) == 0 && this.zzauc == firebaseVisionCloudImageLabelerOptions.zzauc;
    }

    public float getConfidenceThreshold() {
        return this.zzath;
    }

    public int hashCode() {
        return Objects.hashCode(Float.valueOf(this.zzath), Boolean.valueOf(this.zzauc));
    }

    public boolean isEnforceCertFingerprintMatch() {
        return this.zzauc;
    }
}
