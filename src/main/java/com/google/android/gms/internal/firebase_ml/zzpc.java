package com.google.android.gms.internal.firebase_ml;

import androidx.annotation.NonNull;
import com.google.android.gms.internal.firebase_ml.zzlo;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel;
import com.google.firebase.ml.vision.label.FirebaseVisionOnDeviceImageLabelerOptions;
import java.io.Closeable;
import java.util.List;

public final class zzpc extends zzov<List<FirebaseVisionImageLabel>> implements Closeable {
    public zzpc(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseVisionOnDeviceImageLabelerOptions firebaseVisionOnDeviceImageLabelerOptions) {
        super(firebaseApp, new zzpd(firebaseApp, firebaseVisionOnDeviceImageLabelerOptions));
        zznf.zza(firebaseApp, 1).zza(zzlo.zzq.zziu(), zzly.ON_DEVICE_IMAGE_LABEL_CREATE);
    }

    public final Task<List<FirebaseVisionImageLabel>> detectInImage(@NonNull FirebaseVisionImage firebaseVisionImage) {
        return zza(firebaseVisionImage, true, false);
    }
}
