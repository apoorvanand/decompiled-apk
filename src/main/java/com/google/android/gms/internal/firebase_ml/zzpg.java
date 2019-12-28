package com.google.android.gms.internal.firebase_ml;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzlo;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;

public final class zzpg extends zzov<FirebaseVisionText> implements Closeable {
    @GuardedBy("OnDeviceTextRecognizer.class")
    private static final Map<String, zzpg> zzaj = new HashMap();

    private zzpg(@NonNull FirebaseApp firebaseApp) {
        super(firebaseApp, new zzph(firebaseApp));
        zznf.zza(firebaseApp, 1).zza(zzlo.zzq.zziu().zzb(zzlo.zzab.zzka()), zzly.ON_DEVICE_TEXT_CREATE);
    }

    public static synchronized zzpg zzi(@NonNull FirebaseApp firebaseApp) {
        zzpg zzpg;
        synchronized (zzpg.class) {
            Preconditions.checkNotNull(firebaseApp, "FirebaseApp can not be null.");
            Preconditions.checkNotNull(firebaseApp.getPersistenceKey(), "Firebase app name must not be null");
            zzpg = zzaj.get(firebaseApp.getPersistenceKey());
            if (zzpg == null) {
                zzpg = new zzpg(firebaseApp);
                zzaj.put(firebaseApp.getPersistenceKey(), zzpg);
            }
        }
        return zzpg;
    }

    public final Task<FirebaseVisionText> processImage(@NonNull FirebaseVisionImage firebaseVisionImage) {
        return super.zza(firebaseVisionImage, false, true);
    }
}
