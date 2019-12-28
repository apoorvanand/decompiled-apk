package com.google.firebase.ml.common.modeldownload;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzlo;
import com.google.android.gms.internal.firebase_ml.zztc;
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions;
import java.util.HashMap;
import java.util.Map;

public class FirebaseCloudModelSource {
    private static final Map<zza, String> zzaqi = new HashMap();
    @VisibleForTesting
    private static final Map<zza, String> zzaqj;
    @Nullable
    private final String zzapq;
    private String zzaps;
    @Nullable
    private final zza zzaqh;
    private final boolean zzaqk;
    private final FirebaseModelDownloadConditions zzaql;
    private final FirebaseModelDownloadConditions zzaqm;

    public static class Builder {
        @Nullable
        private final String zzapq;
        @Nullable
        private final zza zzaqh;
        private boolean zzaqk = true;
        private FirebaseModelDownloadConditions zzaql = new FirebaseModelDownloadConditions.Builder().build();
        private FirebaseModelDownloadConditions zzaqm = new FirebaseModelDownloadConditions.Builder().build();

        public Builder(@NonNull String str) {
            this.zzapq = str;
            this.zzaqh = null;
        }

        public FirebaseCloudModelSource build() {
            Preconditions.checkArgument(!TextUtils.isEmpty(this.zzapq), "One of cloud model name and base model cannot be empty");
            Preconditions.checkNotNull(this.zzaql, "Initial download condition cannot be null");
            Preconditions.checkNotNull(this.zzaqm, "Update download condition cannot be null");
            return new FirebaseCloudModelSource(this.zzapq, (zza) null, this.zzaqk, this.zzaql, this.zzaqm, (zzb) null);
        }

        public Builder enableModelUpdates(boolean z) {
            this.zzaqk = z;
            return this;
        }

        public Builder setInitialDownloadConditions(@NonNull FirebaseModelDownloadConditions firebaseModelDownloadConditions) {
            this.zzaql = firebaseModelDownloadConditions;
            return this;
        }

        public Builder setUpdatesDownloadConditions(@NonNull FirebaseModelDownloadConditions firebaseModelDownloadConditions) {
            this.zzaqm = firebaseModelDownloadConditions;
            return this;
        }
    }

    static {
        HashMap hashMap = new HashMap();
        zzaqj = hashMap;
        hashMap.put(zza.FACE_DETECTION, "face_detector_model_m41");
        zzaqj.put(zza.SMART_REPLY, "smart_reply_model_m41");
        zzaqj.put(zza.TRANSLATE, "translate_model_m41");
        zzaqi.put(zza.FACE_DETECTION, "modelHash");
        zzaqi.put(zza.SMART_REPLY, "smart_reply_model_hash");
        zzaqi.put(zza.TRANSLATE, "modelHash");
    }

    private FirebaseCloudModelSource(@Nullable String str, @Nullable zza zza, boolean z, @NonNull FirebaseModelDownloadConditions firebaseModelDownloadConditions, @NonNull FirebaseModelDownloadConditions firebaseModelDownloadConditions2) {
        this.zzapq = str;
        this.zzaqh = zza;
        this.zzaqk = z;
        this.zzaql = firebaseModelDownloadConditions;
        this.zzaqm = firebaseModelDownloadConditions2;
    }

    /* synthetic */ FirebaseCloudModelSource(String str, zza zza, boolean z, FirebaseModelDownloadConditions firebaseModelDownloadConditions, FirebaseModelDownloadConditions firebaseModelDownloadConditions2, zzb zzb) {
        this(str, (zza) null, z, firebaseModelDownloadConditions, firebaseModelDownloadConditions2);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FirebaseCloudModelSource)) {
            return false;
        }
        FirebaseCloudModelSource firebaseCloudModelSource = (FirebaseCloudModelSource) obj;
        return Objects.equal(this.zzapq, firebaseCloudModelSource.zzapq) && Objects.equal(this.zzaqh, firebaseCloudModelSource.zzaqh) && this.zzaqk == firebaseCloudModelSource.zzaqk && this.zzaql.equals(firebaseCloudModelSource.zzaql) && this.zzaqm.equals(firebaseCloudModelSource.zzaqm);
    }

    public FirebaseModelDownloadConditions getInitialDownloadConditions() {
        return this.zzaql;
    }

    @Nullable
    public String getModelName() {
        return this.zzapq;
    }

    public FirebaseModelDownloadConditions getUpdatesDownloadConditions() {
        return this.zzaqm;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzapq, this.zzaqh, Boolean.valueOf(this.zzaqk), Integer.valueOf(Objects.hashCode(this.zzaql)), Integer.valueOf(Objects.hashCode(this.zzaqm)));
    }

    public boolean isModelUpdatesEnabled() {
        return this.zzaqk;
    }

    public final boolean zzby(@NonNull String str) {
        zza zza = this.zzaqh;
        if (zza == null) {
            return false;
        }
        return str.equals(zzaqi.get(zza));
    }

    public final void zzbz(@NonNull String str) {
        this.zzaps = str;
    }

    @NonNull
    public final String zzkz() {
        String str = this.zzapq;
        return str != null ? str : zzaqj.get(this.zzaqh);
    }

    public final zzlo.zzo zzla() {
        zzlo.zzo.zza zzd = zzlo.zzo.zzin().zzc(this.zzaql.zzlb()).zzd(this.zzaqm.zzlb());
        zzlo.zzu.zza zzb = zzlo.zzu.zzjd().zzbb(zzkz()).zzb(zzlo.zzu.zzb.CLOUD);
        String str = this.zzaps;
        if (str == null) {
            str = "";
        }
        return (zzlo.zzo) ((zztc) zzd.zzb(zzb.zzbd(str)).zzn(this.zzaqk).zzpx());
    }
}
