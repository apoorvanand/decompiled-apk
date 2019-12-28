package com.google.firebase.ml.vision.document;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzix;
import com.google.android.gms.internal.firebase_ml.zzjl;
import com.google.android.gms.internal.firebase_ml.zzlo;
import com.google.android.gms.internal.firebase_ml.zzly;
import com.google.android.gms.internal.firebase_ml.zzne;
import com.google.android.gms.internal.firebase_ml.zznf;
import com.google.android.gms.internal.firebase_ml.zzon;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import java.util.HashMap;
import java.util.Map;

public class FirebaseVisionDocumentTextRecognizer extends zzon<FirebaseVisionDocumentText> {
    private static final Map<zzne<FirebaseVisionCloudDocumentRecognizerOptions>, FirebaseVisionDocumentTextRecognizer> zzaj = new HashMap();

    private FirebaseVisionDocumentTextRecognizer(@NonNull FirebaseApp firebaseApp, @NonNull zzjl zzjl, boolean z) {
        super(firebaseApp, "DOCUMENT_TEXT_DETECTION", zzjl, z);
        zznf.zza(firebaseApp, 1).zza(zzlo.zzq.zziu(), zzly.CLOUD_DOCUMENT_TEXT_CREATE);
    }

    public static synchronized FirebaseVisionDocumentTextRecognizer zza(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseVisionCloudDocumentRecognizerOptions firebaseVisionCloudDocumentRecognizerOptions) {
        FirebaseVisionDocumentTextRecognizer firebaseVisionDocumentTextRecognizer;
        synchronized (FirebaseVisionDocumentTextRecognizer.class) {
            Preconditions.checkNotNull(firebaseApp, "FirebaseApp must not be null");
            Preconditions.checkNotNull(firebaseApp.getPersistenceKey(), "Firebase app name must not be null");
            Preconditions.checkNotNull(firebaseVisionCloudDocumentRecognizerOptions, "Options must not be null");
            zzne zzj = zzne.zzj(firebaseApp.getPersistenceKey(), firebaseVisionCloudDocumentRecognizerOptions);
            firebaseVisionDocumentTextRecognizer = zzaj.get(zzj);
            if (firebaseVisionDocumentTextRecognizer == null) {
                zzjl zzjl = new zzjl();
                zzjl.zzd(firebaseVisionCloudDocumentRecognizerOptions.getHintedLanguages());
                FirebaseVisionDocumentTextRecognizer firebaseVisionDocumentTextRecognizer2 = new FirebaseVisionDocumentTextRecognizer(firebaseApp, zzjl, firebaseVisionCloudDocumentRecognizerOptions.isEnforceCertFingerprintMatch());
                zzaj.put(zzj, firebaseVisionDocumentTextRecognizer2);
                firebaseVisionDocumentTextRecognizer = firebaseVisionDocumentTextRecognizer2;
            }
        }
        return firebaseVisionDocumentTextRecognizer;
    }

    public Task<FirebaseVisionDocumentText> processImage(@NonNull FirebaseVisionImage firebaseVisionImage) {
        zznf.zza(this.zzaot, 1).zza(zzlo.zzq.zziu(), zzly.CLOUD_DOCUMENT_TEXT_DETECT);
        return super.zza(firebaseVisionImage);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final /* synthetic */ Object zza(@NonNull zzix zzix, float f) {
        return FirebaseVisionDocumentText.zza(zzix.zzgw(), 1.0f / f);
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
