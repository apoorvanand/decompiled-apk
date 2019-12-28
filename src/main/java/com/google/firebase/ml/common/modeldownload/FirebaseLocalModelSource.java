package com.google.firebase.ml.common.modeldownload;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzlo;
import com.google.android.gms.internal.firebase_ml.zztc;

public class FirebaseLocalModelSource {
    private final String zzapq;
    private final String zzaqn;
    private final String zzaqo;

    public static class Builder {
        private final String zzapq;
        private String zzaqn = null;
        private String zzaqp = null;

        public Builder(@NonNull String str) {
            Preconditions.checkNotEmpty(str, "Model name can not be empty");
            this.zzapq = str;
        }

        public FirebaseLocalModelSource build() {
            Preconditions.checkArgument((this.zzaqn != null && this.zzaqp == null) || (this.zzaqn == null && this.zzaqp != null), "Please set either filePath or assetFilePath.");
            return new FirebaseLocalModelSource(this.zzapq, this.zzaqn, this.zzaqp);
        }

        public Builder setAssetFilePath(@NonNull String str) {
            Preconditions.checkNotEmpty(str, "Model Source file path can not be empty");
            Preconditions.checkArgument(this.zzaqn == null, "A local model source is either from local file or for asset, you can not set both.");
            this.zzaqp = str;
            return this;
        }

        public Builder setFilePath(@NonNull String str) {
            Preconditions.checkNotEmpty(str, "Model Source file path can not be empty");
            Preconditions.checkArgument(this.zzaqp == null, "A local model source is either from local file or for asset, you can not set both.");
            this.zzaqn = str;
            return this;
        }
    }

    private FirebaseLocalModelSource(@NonNull String str, @Nullable String str2, @Nullable String str3) {
        this.zzapq = str;
        this.zzaqn = str2;
        this.zzaqo = str3;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FirebaseLocalModelSource)) {
            return false;
        }
        FirebaseLocalModelSource firebaseLocalModelSource = (FirebaseLocalModelSource) obj;
        return Objects.equal(this.zzapq, firebaseLocalModelSource.zzapq) && Objects.equal(this.zzaqn, firebaseLocalModelSource.zzaqn) && Objects.equal(this.zzaqo, firebaseLocalModelSource.zzaqo);
    }

    @Nullable
    public String getAssetFilePath() {
        return this.zzaqo;
    }

    @Nullable
    public String getFilePath() {
        return this.zzaqn;
    }

    public String getModelName() {
        return this.zzapq;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzapq, this.zzaqn, this.zzaqo);
    }

    public final zzlo.zzo zzla() {
        zzlo.zzo.zza zzin = zzlo.zzo.zzin();
        zzlo.zzu.zza zzjd = zzlo.zzu.zzjd();
        String str = this.zzaqn;
        if (str == null) {
            str = this.zzaqo;
        }
        return (zzlo.zzo) ((zztc) zzin.zzb(zzjd.zzbc(str).zzb(this.zzaqn != null ? zzlo.zzu.zzb.LOCAL : this.zzaqo != null ? zzlo.zzu.zzb.APP_ASSET : zzlo.zzu.zzb.SOURCE_UNKNOWN)).zzpx());
    }
}
