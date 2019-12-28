package com.google.firebase.ml.vision.text;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.firebase_ml.zzjf;

public class RecognizedLanguage {
    private final String languageCode;

    private RecognizedLanguage(@Nullable String str) {
        this.languageCode = str;
    }

    @Nullable
    public static RecognizedLanguage zza(@Nullable zzjf zzjf) {
        if (zzjf == null || zzjf.getLanguageCode() == null || zzjf.getLanguageCode().isEmpty()) {
            return null;
        }
        return new RecognizedLanguage(zzjf.getLanguageCode());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RecognizedLanguage)) {
            return false;
        }
        RecognizedLanguage recognizedLanguage = (RecognizedLanguage) obj;
        String str = this.languageCode;
        return str == null ? recognizedLanguage.languageCode == null : str.equals(recognizedLanguage.languageCode);
    }

    @Nullable
    public String getLanguageCode() {
        return this.languageCode;
    }

    public int hashCode() {
        return Objects.hashCode(this.languageCode);
    }
}
