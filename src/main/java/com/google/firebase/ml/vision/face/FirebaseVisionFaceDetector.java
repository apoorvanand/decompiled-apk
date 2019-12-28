package com.google.firebase.ml.vision.face;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzlo;
import com.google.android.gms.internal.firebase_ml.zzly;
import com.google.android.gms.internal.firebase_ml.zzne;
import com.google.android.gms.internal.firebase_ml.zznf;
import com.google.android.gms.internal.firebase_ml.zzov;
import com.google.android.gms.internal.firebase_ml.zzoz;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import java.io.Closeable;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class FirebaseVisionFaceDetector extends zzov<List<FirebaseVisionFace>> implements Closeable {
    private static final Map<zzne<FirebaseVisionFaceDetectorOptions>, FirebaseVisionFaceDetector> zzaj = new HashMap();
    private final FirebaseVisionFaceDetectorOptions zzavx;

    private FirebaseVisionFaceDetector(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseVisionFaceDetectorOptions firebaseVisionFaceDetectorOptions) {
        super(firebaseApp, new zzoz(firebaseApp, firebaseVisionFaceDetectorOptions));
        this.zzavx = firebaseVisionFaceDetectorOptions;
        zznf.zza(firebaseApp, 1).zza(zzlo.zzq.zziu().zzb(zzlo.zzw.zzjh().zzb(firebaseVisionFaceDetectorOptions.zzmb())), zzly.ON_DEVICE_FACE_CREATE);
    }

    public static synchronized FirebaseVisionFaceDetector zza(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseVisionFaceDetectorOptions firebaseVisionFaceDetectorOptions) {
        FirebaseVisionFaceDetector firebaseVisionFaceDetector;
        synchronized (FirebaseVisionFaceDetector.class) {
            Preconditions.checkNotNull(firebaseApp, "You must provide a valid FirebaseApp.");
            Preconditions.checkNotNull(firebaseApp.getPersistenceKey(), "Firebase app name must not be null");
            Preconditions.checkNotNull(firebaseApp.getApplicationContext(), "You must provide a valid Context.");
            Preconditions.checkNotNull(firebaseVisionFaceDetectorOptions, "You must provide a valid FirebaseVisionFaceDetectorOptions.");
            zzne zzj = zzne.zzj(firebaseApp.getPersistenceKey(), firebaseVisionFaceDetectorOptions);
            firebaseVisionFaceDetector = zzaj.get(zzj);
            if (firebaseVisionFaceDetector == null) {
                firebaseVisionFaceDetector = new FirebaseVisionFaceDetector(firebaseApp, firebaseVisionFaceDetectorOptions);
                zzaj.put(zzj, firebaseVisionFaceDetector);
            }
        }
        return firebaseVisionFaceDetector;
    }

    public void close() {
        super.close();
    }

    public Task<List<FirebaseVisionFace>> detectInImage(@NonNull FirebaseVisionImage firebaseVisionImage) {
        Preconditions.checkArgument(this.zzavx.getContourMode() != 2 || firebaseVisionImage.zza(false, false).getMetadata().getWidth() >= 32, String.format(Locale.getDefault(), "The width of input image cannot be less than %s when using contour mode ALL_CONTOURS!", new Object[]{32}));
        return super.zza(firebaseVisionImage, false, true);
    }
}
