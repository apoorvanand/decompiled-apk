package com.google.firebase.ml.vision.face;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.firebase_ml.zzkp;
import com.google.android.gms.internal.firebase_ml.zzlo;
import com.google.android.gms.internal.firebase_ml.zztc;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class FirebaseVisionFaceDetectorOptions {
    public static final int ACCURATE = 2;
    public static final int ALL_CLASSIFICATIONS = 2;
    public static final int ALL_CONTOURS = 2;
    public static final int ALL_LANDMARKS = 2;
    public static final int FAST = 1;
    public static final int NO_CLASSIFICATIONS = 1;
    public static final int NO_CONTOURS = 1;
    public static final int NO_LANDMARKS = 1;
    private final boolean trackingEnabled;
    private final int zzavy;
    private final int zzavz;
    private final int zzawa;
    private final int zzawb;
    private final float zzawc;

    public static class Builder {
        private boolean trackingEnabled = false;
        private int zzavy = 1;
        private int zzavz = 1;
        private int zzawa = 1;
        private int zzawb = 1;
        private float zzawc = 0.1f;

        public FirebaseVisionFaceDetectorOptions build() {
            return new FirebaseVisionFaceDetectorOptions(this.zzavy, this.zzavz, this.zzawa, this.zzawb, this.trackingEnabled, this.zzawc);
        }

        public Builder enableTracking() {
            this.trackingEnabled = true;
            return this;
        }

        public Builder setClassificationMode(int i) {
            this.zzawa = i;
            return this;
        }

        public Builder setContourMode(int i) {
            this.zzavz = i;
            return this;
        }

        public Builder setLandmarkMode(int i) {
            this.zzavy = i;
            return this;
        }

        public Builder setMinFaceSize(float f) {
            this.zzawc = f;
            return this;
        }

        public Builder setPerformanceMode(int i) {
            this.zzawb = i;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ClassificationMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ContourMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LandmarkMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PerformanceMode {
    }

    private FirebaseVisionFaceDetectorOptions(int i, int i2, int i3, int i4, boolean z, float f) {
        this.zzavy = i;
        this.zzavz = i2;
        this.zzawa = i3;
        this.zzawb = i4;
        this.trackingEnabled = z;
        this.zzawc = f;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FirebaseVisionFaceDetectorOptions)) {
            return false;
        }
        FirebaseVisionFaceDetectorOptions firebaseVisionFaceDetectorOptions = (FirebaseVisionFaceDetectorOptions) obj;
        return Float.floatToIntBits(this.zzawc) == Float.floatToIntBits(firebaseVisionFaceDetectorOptions.zzawc) && this.zzavy == firebaseVisionFaceDetectorOptions.zzavy && this.zzavz == firebaseVisionFaceDetectorOptions.zzavz && this.zzawb == firebaseVisionFaceDetectorOptions.zzawb && this.trackingEnabled == firebaseVisionFaceDetectorOptions.trackingEnabled && this.zzawa == firebaseVisionFaceDetectorOptions.zzawa;
    }

    public int getClassificationMode() {
        return this.zzawa;
    }

    public int getContourMode() {
        return this.zzavz;
    }

    public int getLandmarkMode() {
        return this.zzavy;
    }

    public float getMinFaceSize() {
        return this.zzawc;
    }

    public int getPerformanceMode() {
        return this.zzawb;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(Float.floatToIntBits(this.zzawc)), Integer.valueOf(this.zzavy), Integer.valueOf(this.zzavz), Integer.valueOf(this.zzawb), Boolean.valueOf(this.trackingEnabled), Integer.valueOf(this.zzawa));
    }

    public boolean isTrackingEnabled() {
        return this.trackingEnabled;
    }

    public String toString() {
        return zzkp.zzaw("FaceDetectorOptions").zzb("landmarkMode", this.zzavy).zzb("contourMode", this.zzavz).zzb("classificationMode", this.zzawa).zzb("performanceMode", this.zzawb).zzb("trackingEnabled", this.trackingEnabled).zza("minFaceSize", this.zzawc).toString();
    }

    public final zzlo.zzp zzmb() {
        zzlo.zzp.zzd zzd;
        zzlo.zzp.zzb zzb;
        zzlo.zzp.zze zze;
        zzlo.zzp.zzc zzc;
        zzlo.zzp.zza zzir = zzlo.zzp.zzir();
        switch (this.zzavy) {
            case 1:
                zzd = zzlo.zzp.zzd.NO_LANDMARKS;
                break;
            case 2:
                zzd = zzlo.zzp.zzd.ALL_LANDMARKS;
                break;
            default:
                zzd = zzlo.zzp.zzd.UNKNOWN_LANDMARKS;
                break;
        }
        zzlo.zzp.zza zzb2 = zzir.zzb(zzd);
        switch (this.zzawa) {
            case 1:
                zzb = zzlo.zzp.zzb.NO_CLASSIFICATIONS;
                break;
            case 2:
                zzb = zzlo.zzp.zzb.ALL_CLASSIFICATIONS;
                break;
            default:
                zzb = zzlo.zzp.zzb.UNKNOWN_CLASSIFICATIONS;
                break;
        }
        zzlo.zzp.zza zzb3 = zzb2.zzb(zzb);
        switch (this.zzawb) {
            case 1:
                zze = zzlo.zzp.zze.FAST;
                break;
            case 2:
                zze = zzlo.zzp.zze.ACCURATE;
                break;
            default:
                zze = zzlo.zzp.zze.UNKNOWN_PERFORMANCE;
                break;
        }
        zzlo.zzp.zza zzb4 = zzb3.zzb(zze);
        switch (this.zzavz) {
            case 1:
                zzc = zzlo.zzp.zzc.NO_CONTOURS;
                break;
            case 2:
                zzc = zzlo.zzp.zzc.ALL_CONTOURS;
                break;
            default:
                zzc = zzlo.zzp.zzc.UNKNOWN_CONTOURS;
                break;
        }
        return (zzlo.zzp) ((zztc) zzb4.zzb(zzc).zzv(isTrackingEnabled()).zzl(this.zzawc).zzpx());
    }
}
