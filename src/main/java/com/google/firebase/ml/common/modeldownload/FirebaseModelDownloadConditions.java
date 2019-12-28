package com.google.firebase.ml.common.modeldownload;

import android.annotation.TargetApi;
import androidx.annotation.RequiresApi;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.firebase_ml.zzlo;
import com.google.android.gms.internal.firebase_ml.zztc;

public class FirebaseModelDownloadConditions {
    private final boolean zzaqq;
    private final boolean zzaqr;
    private final boolean zzaqs;

    public static class Builder {
        private boolean zzaqq = false;
        private boolean zzaqr = false;
        private boolean zzaqs = false;

        public FirebaseModelDownloadConditions build() {
            return new FirebaseModelDownloadConditions(this.zzaqq, this.zzaqr, this.zzaqs);
        }

        @RequiresApi(24)
        @TargetApi(24)
        public Builder requireCharging() {
            this.zzaqq = true;
            return this;
        }

        @RequiresApi(24)
        @TargetApi(24)
        public Builder requireDeviceIdle() {
            this.zzaqs = true;
            return this;
        }

        public Builder requireWifi() {
            this.zzaqr = true;
            return this;
        }
    }

    private FirebaseModelDownloadConditions(boolean z, boolean z2, boolean z3) {
        this.zzaqq = z;
        this.zzaqr = z2;
        this.zzaqs = z3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FirebaseModelDownloadConditions)) {
            return false;
        }
        FirebaseModelDownloadConditions firebaseModelDownloadConditions = (FirebaseModelDownloadConditions) obj;
        return this.zzaqq == firebaseModelDownloadConditions.zzaqq && this.zzaqs == firebaseModelDownloadConditions.zzaqs && this.zzaqr == firebaseModelDownloadConditions.zzaqr;
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.zzaqq), Boolean.valueOf(this.zzaqr), Boolean.valueOf(this.zzaqs));
    }

    public boolean isChargingRequired() {
        return this.zzaqq;
    }

    public boolean isDeviceIdleRequired() {
        return this.zzaqs;
    }

    public boolean isWifiRequired() {
        return this.zzaqr;
    }

    public final zzlo.zzo.zzb zzlb() {
        return (zzlo.zzo.zzb) ((zztc) zzlo.zzo.zzb.zzip().zzr(this.zzaqq).zzt(this.zzaqs).zzs(this.zzaqr).zzpx());
    }
}
