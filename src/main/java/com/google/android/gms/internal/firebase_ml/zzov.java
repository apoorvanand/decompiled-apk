package com.google.android.gms.internal.firebase_ml;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import java.io.Closeable;

public class zzov<TDetectionResult> implements Closeable {
    private final zznb zzara;
    private final zzmx<TDetectionResult, zzoy> zzavh;

    protected zzov(@NonNull FirebaseApp firebaseApp, zzmx<TDetectionResult, zzoy> zzmx) {
        Preconditions.checkNotNull(firebaseApp, "FirebaseApp must not be null");
        Preconditions.checkNotNull(firebaseApp.getPersistenceKey(), "Firebase app name must not be null");
        this.zzavh = zzmx;
        this.zzara = zznb.zza(firebaseApp);
        this.zzara.zza(zzmx);
    }

    public void close() {
        this.zzara.zzb(this.zzavh);
    }

    /* access modifiers changed from: protected */
    public final Task<TDetectionResult> zza(@NonNull FirebaseVisionImage firebaseVisionImage, boolean z, boolean z2) {
        Preconditions.checkNotNull(firebaseVisionImage, "FirebaseVisionImage can not be null");
        return this.zzara.zza(this.zzavh, new zzoy(firebaseVisionImage.zza(z, z2)));
    }
}
