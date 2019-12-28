package com.google.firebase.ml.vision.cloud;

import com.google.android.gms.common.internal.Objects;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.concurrent.Immutable;

@Immutable
public class FirebaseVisionCloudDetectorOptions {
    public static final FirebaseVisionCloudDetectorOptions DEFAULT = new Builder().build();
    public static final int LATEST_MODEL = 2;
    public static final int STABLE_MODEL = 1;
    private final int zzaua;
    private final int zzaub;
    private final boolean zzauc;

    public static class Builder {
        private int zzaua = 10;
        private int zzaub = 1;
        private boolean zzauc = false;

        public FirebaseVisionCloudDetectorOptions build() {
            return new FirebaseVisionCloudDetectorOptions(this.zzaua, this.zzaub, this.zzauc);
        }

        public Builder enforceCertFingerprintMatch() {
            this.zzauc = true;
            return this;
        }

        public Builder setMaxResults(int i) {
            this.zzaua = i;
            return this;
        }

        public Builder setModelType(int i) {
            this.zzaub = i;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ModelType {
    }

    private FirebaseVisionCloudDetectorOptions(int i, int i2, boolean z) {
        this.zzaua = i;
        this.zzaub = i2;
        this.zzauc = z;
    }

    public Builder builder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FirebaseVisionCloudDetectorOptions)) {
            return false;
        }
        FirebaseVisionCloudDetectorOptions firebaseVisionCloudDetectorOptions = (FirebaseVisionCloudDetectorOptions) obj;
        return this.zzaua == firebaseVisionCloudDetectorOptions.zzaua && this.zzaub == firebaseVisionCloudDetectorOptions.zzaub && this.zzauc == firebaseVisionCloudDetectorOptions.zzauc;
    }

    public int getMaxResults() {
        return this.zzaua;
    }

    public int getModelType() {
        return this.zzaub;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzaua), Integer.valueOf(this.zzaub), Boolean.valueOf(this.zzauc));
    }

    public final boolean isEnforceCertFingerprintMatch() {
        return this.zzauc;
    }
}
