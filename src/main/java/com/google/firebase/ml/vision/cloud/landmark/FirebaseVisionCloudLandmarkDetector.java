package com.google.firebase.ml.vision.cloud.landmark;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzix;
import com.google.android.gms.internal.firebase_ml.zzjh;
import com.google.android.gms.internal.firebase_ml.zzlo;
import com.google.android.gms.internal.firebase_ml.zzly;
import com.google.android.gms.internal.firebase_ml.zzne;
import com.google.android.gms.internal.firebase_ml.zznf;
import com.google.android.gms.internal.firebase_ml.zzon;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseVisionCloudLandmarkDetector extends zzon<List<FirebaseVisionCloudLandmark>> {
    private static final Map<zzne<FirebaseVisionCloudDetectorOptions>, FirebaseVisionCloudLandmarkDetector> zzaj = new HashMap();

    private FirebaseVisionCloudLandmarkDetector(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseVisionCloudDetectorOptions firebaseVisionCloudDetectorOptions) {
        super(firebaseApp, "LANDMARK_DETECTION", firebaseVisionCloudDetectorOptions);
        zznf.zza(firebaseApp, 1).zza(zzlo.zzq.zziu(), zzly.CLOUD_LANDMARK_CREATE);
    }

    public static synchronized FirebaseVisionCloudLandmarkDetector zza(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseVisionCloudDetectorOptions firebaseVisionCloudDetectorOptions) {
        FirebaseVisionCloudLandmarkDetector firebaseVisionCloudLandmarkDetector;
        synchronized (FirebaseVisionCloudLandmarkDetector.class) {
            Preconditions.checkNotNull(firebaseApp, "FirebaseApp must not be null");
            Preconditions.checkNotNull(firebaseApp.getPersistenceKey(), "Firebase app name must not be null");
            Preconditions.checkNotNull(firebaseVisionCloudDetectorOptions, "Options must not be null");
            zzne zzj = zzne.zzj(firebaseApp.getPersistenceKey(), firebaseVisionCloudDetectorOptions);
            firebaseVisionCloudLandmarkDetector = zzaj.get(zzj);
            if (firebaseVisionCloudLandmarkDetector == null) {
                firebaseVisionCloudLandmarkDetector = new FirebaseVisionCloudLandmarkDetector(firebaseApp, firebaseVisionCloudDetectorOptions);
                zzaj.put(zzj, firebaseVisionCloudLandmarkDetector);
            }
        }
        return firebaseVisionCloudLandmarkDetector;
    }

    public Task<List<FirebaseVisionCloudLandmark>> detectInImage(@NonNull FirebaseVisionImage firebaseVisionImage) {
        zznf.zza(this.zzaot, 1).zza(zzlo.zzq.zziu(), zzly.CLOUD_LANDMARK_DETECT);
        return super.zza(firebaseVisionImage);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object zza(@NonNull zzix zzix, float f) {
        if (zzix.zzgy() == null) {
            return new ArrayList();
        }
        float f2 = 1.0f / f;
        List<zzjh> zzgy = zzix.zzgy();
        ArrayList arrayList = new ArrayList();
        for (zzjh zza : zzgy) {
            FirebaseVisionCloudLandmark zza2 = FirebaseVisionCloudLandmark.zza(zza, f2);
            if (zza2 != null) {
                arrayList.add(zza2);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public final int zzlv() {
        return 640;
    }

    /* access modifiers changed from: protected */
    public final int zzlw() {
        return 480;
    }
}
