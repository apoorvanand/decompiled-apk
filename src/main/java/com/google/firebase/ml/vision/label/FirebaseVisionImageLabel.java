package com.google.firebase.ml.vision.label;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.firebase_ml.zzjh;
import com.google.android.gms.internal.firebase_ml.zzol;
import com.google.android.gms.vision.label.ImageLabel;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public class FirebaseVisionImageLabel {
    private final String text;
    private final float zzasl;
    private final String zzatf;

    public FirebaseVisionImageLabel(@NonNull ImageLabel imageLabel) {
        this(imageLabel.getLabel(), imageLabel.getConfidence(), imageLabel.getMid());
    }

    @VisibleForTesting
    private FirebaseVisionImageLabel(@Nullable String str, float f, @Nullable String str2) {
        this.text = str == null ? "" : str;
        this.zzatf = str2;
        this.zzasl = Float.compare(f, 0.0f) >= 0 ? Float.compare(f, 1.0f) > 0 ? 1.0f : f : 0.0f;
    }

    @Nullable
    public static FirebaseVisionImageLabel zza(@Nullable zzjh zzjh) {
        if (zzjh == null) {
            return null;
        }
        return new FirebaseVisionImageLabel(zzjh.getDescription(), zzol.zza(zzjh.zzhf()), zzjh.getMid());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FirebaseVisionImageLabel)) {
            return false;
        }
        FirebaseVisionImageLabel firebaseVisionImageLabel = (FirebaseVisionImageLabel) obj;
        return Objects.equal(this.zzatf, firebaseVisionImageLabel.getEntityId()) && Objects.equal(this.text, firebaseVisionImageLabel.getText()) && Float.compare(this.zzasl, firebaseVisionImageLabel.getConfidence()) == 0;
    }

    public float getConfidence() {
        return this.zzasl;
    }

    @Nullable
    public String getEntityId() {
        return this.zzatf;
    }

    public String getText() {
        return this.text;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzatf, this.text, Float.valueOf(this.zzasl));
    }
}
