package com.google.firebase.ml.vision.label;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzlo;
import com.google.android.gms.internal.firebase_ml.zztc;
import javax.annotation.concurrent.Immutable;

@Immutable
public class FirebaseVisionOnDeviceImageLabelerOptions {
    private final float zzath;

    public static class Builder {
        private float zzath = 0.5f;

        public FirebaseVisionOnDeviceImageLabelerOptions build() {
            return new FirebaseVisionOnDeviceImageLabelerOptions(this.zzath);
        }

        public Builder setConfidenceThreshold(float f) {
            Preconditions.checkArgument(Float.compare(f, 0.0f) >= 0 && Float.compare(f, 1.0f) <= 0, "Confidence Threshold should be in range [0.0f, 1.0f].");
            this.zzath = f;
            return this;
        }
    }

    private FirebaseVisionOnDeviceImageLabelerOptions(float f) {
        this.zzath = f;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof FirebaseVisionOnDeviceImageLabelerOptions) && this.zzath == ((FirebaseVisionOnDeviceImageLabelerOptions) obj).zzath;
    }

    public float getConfidenceThreshold() {
        return this.zzath;
    }

    public int hashCode() {
        return Objects.hashCode(Float.valueOf(this.zzath));
    }

    public final zzlo.zzy zzmc() {
        return (zzlo.zzy) ((zztc) zzlo.zzy.zzjl().zzo(this.zzath).zzpx());
    }
}
