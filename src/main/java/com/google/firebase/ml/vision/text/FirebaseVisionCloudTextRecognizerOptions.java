package com.google.firebase.ml.vision.text;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FirebaseVisionCloudTextRecognizerOptions {
    public static final int DENSE_MODEL = 2;
    public static final int SPARSE_MODEL = 1;
    private final boolean zzauc;
    private final List<String> zzavk;
    private final int zzaxe;

    public static class Builder {
        private boolean zzauc = false;
        private List<String> zzavk = new ArrayList();
        private int zzaxe = 1;

        public FirebaseVisionCloudTextRecognizerOptions build() {
            return new FirebaseVisionCloudTextRecognizerOptions(this.zzavk, this.zzaxe, this.zzauc);
        }

        public Builder enforceCertFingerprintMatch() {
            this.zzauc = true;
            return this;
        }

        public Builder setLanguageHints(@NonNull List<String> list) {
            Preconditions.checkNotNull(list, "Provided hinted languages can not be null");
            this.zzavk = list;
            Collections.sort(this.zzavk);
            return this;
        }

        public Builder setModelType(int i) {
            boolean z = true;
            if (!(i == 1 || i == 2)) {
                z = false;
            }
            Preconditions.checkArgument(z, "modelType should be either SPARSE_MODEL or DENSE_MODEL");
            this.zzaxe = i;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface CloudTextModelType {
    }

    private FirebaseVisionCloudTextRecognizerOptions(@NonNull List<String> list, int i, boolean z) {
        Preconditions.checkNotNull(list, "Provided hinted languages can not be null");
        this.zzavk = list;
        this.zzaxe = i;
        this.zzauc = z;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FirebaseVisionCloudTextRecognizerOptions)) {
            return false;
        }
        FirebaseVisionCloudTextRecognizerOptions firebaseVisionCloudTextRecognizerOptions = (FirebaseVisionCloudTextRecognizerOptions) obj;
        return this.zzavk.equals(firebaseVisionCloudTextRecognizerOptions.getHintedLanguages()) && this.zzaxe == firebaseVisionCloudTextRecognizerOptions.zzaxe && this.zzauc == firebaseVisionCloudTextRecognizerOptions.zzauc;
    }

    public List<String> getHintedLanguages() {
        return this.zzavk;
    }

    public int getModelType() {
        return this.zzaxe;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzavk, Integer.valueOf(this.zzaxe), Boolean.valueOf(this.zzauc));
    }

    public final boolean isEnforceCertFingerprintMatch() {
        return this.zzauc;
    }
}
