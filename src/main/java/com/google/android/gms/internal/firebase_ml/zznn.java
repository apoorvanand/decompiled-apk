package com.google.android.gms.internal.firebase_ml;

import android.os.SystemClock;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.internal.firebase_ml.zzlo;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.common.modeldownload.FirebaseCloudModelSource;

public final class zznn {
    private static final GmsLogger zzaoe = new GmsLogger("ModelDownloadLogger", "");
    private final FirebaseApp zzaot;
    private final zznf zzapl;
    private final FirebaseCloudModelSource zzapm;

    public zznn(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseCloudModelSource firebaseCloudModelSource) {
        this.zzaot = firebaseApp;
        this.zzapl = zznf.zza(firebaseApp, 4);
        this.zzapm = firebaseCloudModelSource;
    }

    /* access modifiers changed from: package-private */
    public final void a(zzlv zzlv, boolean z) {
        zza(zzlv, "NA", z);
    }

    public final void zza(zzlv zzlv, String str, boolean z) {
        zzlo.zzo zzla = this.zzapm.zzla();
        if (!z) {
            this.zzapl.zza(zzlo.zzq.zziu().zzb(zzlo.zzac.zzkd().zzbr(str)).zzb(zzlo.zzl.zzif().zzb(zzlv).zzb(zzla)), zzly.CUSTOM_MODEL_DOWNLOAD);
            return;
        }
        long zze = zznl.zze(this.zzaot, this.zzapm.zzkz());
        if (zze == 0) {
            zzaoe.w("ModelDownloadLogger", "Model downloaded without its beginning time recorded.");
            this.zzapl.zza(zzlo.zzq.zziu().zzb(zzlo.zzac.zzkd().zzbr(str)).zzb(zzlo.zzl.zzif().zzb(zzlv.UNKNOWN_ERROR).zzb(zzla)), zzly.CUSTOM_MODEL_DOWNLOAD);
            return;
        }
        long zzf = zznl.zzf(this.zzaot, this.zzapm.zzkz());
        if (zzf == 0) {
            zzf = SystemClock.elapsedRealtime();
            zznl.zza(this.zzaot, this.zzapm.zzkz(), zzf);
        }
        this.zzapl.zza(zzlo.zzq.zziu().zzb(zzlo.zzac.zzkd().zzbr(str)).zzb(zzlo.zzl.zzif().zzf(zzf - zze).zzb(zzlv).zzb(zzla)), zzly.CUSTOM_MODEL_DOWNLOAD);
    }
}
