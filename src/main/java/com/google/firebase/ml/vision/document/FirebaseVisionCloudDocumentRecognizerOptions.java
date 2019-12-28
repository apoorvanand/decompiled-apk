package com.google.firebase.ml.vision.document;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FirebaseVisionCloudDocumentRecognizerOptions {
    private final boolean zzauc;
    private final List<String> zzavk;

    public static class Builder {
        private boolean zzauc = false;
        private List<String> zzavk = new ArrayList();

        public FirebaseVisionCloudDocumentRecognizerOptions build() {
            return new FirebaseVisionCloudDocumentRecognizerOptions(this.zzavk, this.zzauc);
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
    }

    private FirebaseVisionCloudDocumentRecognizerOptions(@NonNull List<String> list, boolean z) {
        Preconditions.checkNotNull(list, "Provided hinted languages can not be null");
        this.zzavk = list;
        this.zzauc = z;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FirebaseVisionCloudDocumentRecognizerOptions)) {
            return false;
        }
        FirebaseVisionCloudDocumentRecognizerOptions firebaseVisionCloudDocumentRecognizerOptions = (FirebaseVisionCloudDocumentRecognizerOptions) obj;
        return this.zzavk.equals(firebaseVisionCloudDocumentRecognizerOptions.getHintedLanguages()) && this.zzauc == firebaseVisionCloudDocumentRecognizerOptions.zzauc;
    }

    public List<String> getHintedLanguages() {
        return this.zzavk;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzavk, Boolean.valueOf(this.zzauc));
    }

    public final boolean isEnforceCertFingerprintMatch() {
        return this.zzauc;
    }
}
