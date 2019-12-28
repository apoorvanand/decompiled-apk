package com.google.firebase.ml.vision.barcode;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzlo;
import com.google.android.gms.internal.firebase_ml.zzly;
import com.google.android.gms.internal.firebase_ml.zzne;
import com.google.android.gms.internal.firebase_ml.zznf;
import com.google.android.gms.internal.firebase_ml.zzoj;
import com.google.android.gms.internal.firebase_ml.zzov;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import java.io.Closeable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseVisionBarcodeDetector extends zzov<List<FirebaseVisionBarcode>> implements Closeable {
    private static final Map<zzne<FirebaseVisionBarcodeDetectorOptions>, FirebaseVisionBarcodeDetector> zzaj = new HashMap();

    private FirebaseVisionBarcodeDetector(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseVisionBarcodeDetectorOptions firebaseVisionBarcodeDetectorOptions) {
        super(firebaseApp, new zzoj(firebaseApp, firebaseVisionBarcodeDetectorOptions));
        zznf.zza(firebaseApp, 1).zza(zzlo.zzq.zziu().zzb(zzlo.zzv.zzjf().zzb(firebaseVisionBarcodeDetectorOptions.zzlu())), zzly.ON_DEVICE_BARCODE_CREATE);
    }

    public static synchronized FirebaseVisionBarcodeDetector zza(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseVisionBarcodeDetectorOptions firebaseVisionBarcodeDetectorOptions) {
        FirebaseVisionBarcodeDetector firebaseVisionBarcodeDetector;
        synchronized (FirebaseVisionBarcodeDetector.class) {
            Preconditions.checkNotNull(firebaseApp, "You must provide a valid FirebaseApp.");
            Preconditions.checkNotNull(firebaseApp.getPersistenceKey(), "Firebase app name must not be null");
            Preconditions.checkNotNull(firebaseApp.getApplicationContext(), "You must provide a valid Context.");
            Preconditions.checkNotNull(firebaseVisionBarcodeDetectorOptions, "You must provide a valid FirebaseVisionBarcodeDetectorOptions.");
            zzne zzj = zzne.zzj(firebaseApp.getPersistenceKey(), firebaseVisionBarcodeDetectorOptions);
            firebaseVisionBarcodeDetector = zzaj.get(zzj);
            if (firebaseVisionBarcodeDetector == null) {
                firebaseVisionBarcodeDetector = new FirebaseVisionBarcodeDetector(firebaseApp, firebaseVisionBarcodeDetectorOptions);
                zzaj.put(zzj, firebaseVisionBarcodeDetector);
            }
        }
        return firebaseVisionBarcodeDetector;
    }

    public void close() {
        super.close();
    }

    public Task<List<FirebaseVisionBarcode>> detectInImage(@NonNull FirebaseVisionImage firebaseVisionImage) {
        Preconditions.checkNotNull(firebaseVisionImage, "FirebaseVisionImage can not be null");
        return zza(firebaseVisionImage, false, false);
    }
}
