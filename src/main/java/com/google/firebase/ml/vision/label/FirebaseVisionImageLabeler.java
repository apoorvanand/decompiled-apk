package com.google.firebase.ml.vision.label;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzne;
import com.google.android.gms.internal.firebase_ml.zzpb;
import com.google.android.gms.internal.firebase_ml.zzpc;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import java.io.Closeable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

public class FirebaseVisionImageLabeler implements Closeable {
    public static final int CLOUD = 2;
    public static final int ON_DEVICE = 1;
    @GuardedBy("FirebaseVisionImageLabeler.class")
    private static final Map<zzne<FirebaseVisionOnDeviceImageLabelerOptions>, FirebaseVisionImageLabeler> zzawp = new HashMap();
    @GuardedBy("FirebaseVisionImageLabeler.class")
    private static final Map<zzne<FirebaseVisionCloudImageLabelerOptions>, FirebaseVisionImageLabeler> zzawq = new HashMap();
    private final zzpb zzawl;
    private final zzpc zzawm;
    /* access modifiers changed from: private */
    public final FirebaseVisionCloudImageLabelerOptions zzawn;
    @ImageLabelerType
    private final int zzawo;

    public @interface ImageLabelerType {
    }

    private FirebaseVisionImageLabeler(@Nullable zzpc zzpc, @Nullable zzpb zzpb, @Nullable FirebaseVisionCloudImageLabelerOptions firebaseVisionCloudImageLabelerOptions) {
        int i = 1;
        Preconditions.checkArgument((zzpc == null && zzpb == null) ? false : true, "Either on-device or cloud image labeler should be enabled.");
        this.zzawo = zzpb != null ? 2 : i;
        this.zzawm = zzpc;
        this.zzawl = zzpb;
        this.zzawn = firebaseVisionCloudImageLabelerOptions;
    }

    public static synchronized FirebaseVisionImageLabeler zza(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseVisionCloudImageLabelerOptions firebaseVisionCloudImageLabelerOptions) {
        FirebaseVisionImageLabeler firebaseVisionImageLabeler;
        synchronized (FirebaseVisionImageLabeler.class) {
            Preconditions.checkNotNull(firebaseApp, "FirebaseApp must not be null");
            Preconditions.checkNotNull(firebaseApp.getPersistenceKey(), "Firebase app name must not be null");
            zzne zzj = zzne.zzj(firebaseApp.getPersistenceKey(), firebaseVisionCloudImageLabelerOptions);
            firebaseVisionImageLabeler = zzawq.get(zzj);
            if (firebaseVisionImageLabeler == null) {
                FirebaseVisionCloudDetectorOptions.Builder maxResults = new FirebaseVisionCloudDetectorOptions.Builder().setMaxResults(20);
                if (firebaseVisionCloudImageLabelerOptions.isEnforceCertFingerprintMatch()) {
                    maxResults.enforceCertFingerprintMatch();
                }
                firebaseVisionImageLabeler = new FirebaseVisionImageLabeler((zzpc) null, new zzpb(firebaseApp, maxResults.build()), firebaseVisionCloudImageLabelerOptions);
                zzawq.put(zzj, firebaseVisionImageLabeler);
            }
        }
        return firebaseVisionImageLabeler;
    }

    public static synchronized FirebaseVisionImageLabeler zza(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseVisionOnDeviceImageLabelerOptions firebaseVisionOnDeviceImageLabelerOptions) {
        FirebaseVisionImageLabeler firebaseVisionImageLabeler;
        synchronized (FirebaseVisionImageLabeler.class) {
            Preconditions.checkNotNull(firebaseApp, "FirebaseApp must not be null");
            Preconditions.checkNotNull(firebaseApp.getPersistenceKey(), "Firebase app name must not be null");
            zzne zzj = zzne.zzj(firebaseApp.getPersistenceKey(), firebaseVisionOnDeviceImageLabelerOptions);
            FirebaseVisionImageLabeler firebaseVisionImageLabeler2 = zzawp.get(zzj);
            if (firebaseVisionImageLabeler2 == null) {
                firebaseVisionImageLabeler = new FirebaseVisionImageLabeler(new zzpc(firebaseApp, firebaseVisionOnDeviceImageLabelerOptions), (zzpb) null, (FirebaseVisionCloudImageLabelerOptions) null);
                zzawp.put(zzj, firebaseVisionImageLabeler);
            } else {
                firebaseVisionImageLabeler = firebaseVisionImageLabeler2;
            }
        }
        return firebaseVisionImageLabeler;
    }

    public void close() {
        zzpc zzpc = this.zzawm;
        if (zzpc != null) {
            zzpc.close();
        }
        zzpb zzpb = this.zzawl;
        if (zzpb != null) {
            zzpb.close();
        }
    }

    @ImageLabelerType
    public int getImageLabelerType() {
        return this.zzawo;
    }

    public Task<List<FirebaseVisionImageLabel>> processImage(@NonNull FirebaseVisionImage firebaseVisionImage) {
        Preconditions.checkState((this.zzawm == null && this.zzawl == null) ? false : true, "Either on-device or cloud image labeler should be enabled.");
        zzpc zzpc = this.zzawm;
        return zzpc != null ? zzpc.detectInImage(firebaseVisionImage) : this.zzawl.detectInImage(firebaseVisionImage).continueWith(new zzb(this));
    }
}
