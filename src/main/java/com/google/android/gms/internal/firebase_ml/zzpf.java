package com.google.android.gms.internal.firebase_ml;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzlo;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionCloudTextRecognizerOptions;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import java.util.HashMap;
import java.util.Map;

public final class zzpf extends zzon<FirebaseVisionText> {
    @GuardedBy("CloudTextRecognizer.class")
    private static final Map<zzne<FirebaseVisionCloudTextRecognizerOptions>, zzpf> zzaj = new HashMap();
    private final FirebaseVisionCloudTextRecognizerOptions zzaxl;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private zzpf(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseVisionCloudTextRecognizerOptions firebaseVisionCloudTextRecognizerOptions) {
        super(firebaseApp, firebaseVisionCloudTextRecognizerOptions.getModelType() == 1 ? "TEXT_DETECTION" : "DOCUMENT_TEXT_DETECTION", new zzjl(), firebaseVisionCloudTextRecognizerOptions.isEnforceCertFingerprintMatch());
        this.zzaxl = firebaseVisionCloudTextRecognizerOptions;
        zznf.zza(firebaseApp, 1).zza(zzlo.zzq.zziu(), firebaseVisionCloudTextRecognizerOptions.getModelType() == 2 ? zzly.CLOUD_DOCUMENT_TEXT_CREATE : zzly.CLOUD_TEXT_CREATE);
    }

    public static synchronized zzpf zza(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseVisionCloudTextRecognizerOptions firebaseVisionCloudTextRecognizerOptions) {
        zzpf zzpf;
        synchronized (zzpf.class) {
            Preconditions.checkNotNull(firebaseApp, "FirebaseApp must not be null");
            Preconditions.checkNotNull(firebaseApp.getPersistenceKey(), "Firebase app name must not be null");
            Preconditions.checkNotNull(firebaseVisionCloudTextRecognizerOptions, "Options must not be null");
            zzne zzj = zzne.zzj(firebaseApp.getPersistenceKey(), firebaseVisionCloudTextRecognizerOptions);
            zzpf = zzaj.get(zzj);
            if (zzpf == null) {
                zzpf = new zzpf(firebaseApp, firebaseVisionCloudTextRecognizerOptions);
                zzaj.put(zzj, zzpf);
            }
        }
        return zzpf;
    }

    public final Task<FirebaseVisionText> processImage(@NonNull FirebaseVisionImage firebaseVisionImage) {
        zzly zzly = zzly.CLOUD_TEXT_DETECT;
        if (this.zzaxl.getModelType() == 2) {
            zzly = zzly.CLOUD_DOCUMENT_TEXT_DETECT;
        }
        zznf.zza(this.zzaot, 1).zza(zzlo.zzq.zziu(), zzly);
        return super.zza(firebaseVisionImage);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final /* synthetic */ Object zza(@NonNull zzix zzix, float f) {
        return zzpj.a(zzix.zzgw(), 1.0f / f);
    }

    /* access modifiers changed from: protected */
    public final int zzlv() {
        return 1024;
    }

    /* access modifiers changed from: protected */
    public final int zzlw() {
        return 768;
    }
}
