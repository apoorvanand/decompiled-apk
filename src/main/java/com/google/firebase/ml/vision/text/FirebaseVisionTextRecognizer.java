package com.google.firebase.ml.vision.text;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzpf;
import com.google.android.gms.internal.firebase_ml.zzpg;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

public class FirebaseVisionTextRecognizer implements Closeable {
    public static final int CLOUD = 2;
    public static final int ON_DEVICE = 1;
    @GuardedBy("FirebaseVisionTextRecognizer.class")
    private static final Map<zzpg, FirebaseVisionTextRecognizer> zzawp = new HashMap();
    @GuardedBy("FirebaseVisionTextRecognizer.class")
    private static final Map<zzpf, FirebaseVisionTextRecognizer> zzawq = new HashMap();
    private final zzpg zzaxi;
    private final zzpf zzaxj;
    @RecognizerType
    private final int zzaxk;

    public @interface RecognizerType {
    }

    private FirebaseVisionTextRecognizer(@Nullable zzpg zzpg, @Nullable zzpf zzpf, @RecognizerType int i) {
        this.zzaxk = i;
        this.zzaxi = zzpg;
        this.zzaxj = zzpf;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0035, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0050, code lost:
        return r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer zza(@androidx.annotation.NonNull com.google.firebase.FirebaseApp r3, @androidx.annotation.Nullable com.google.firebase.ml.vision.text.FirebaseVisionCloudTextRecognizerOptions r4, boolean r5) {
        /*
            java.lang.Class<com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer> r0 = com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer.class
            monitor-enter(r0)
            java.lang.String r1 = "FirebaseApp must not be null"
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r3, r1)     // Catch:{ all -> 0x0051 }
            java.lang.String r1 = r3.getPersistenceKey()     // Catch:{ all -> 0x0051 }
            java.lang.String r2 = "Firebase app name must not be null"
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r1, r2)     // Catch:{ all -> 0x0051 }
            if (r5 != 0) goto L_0x0018
            java.lang.String r1 = "Options must not be null"
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r4, r1)     // Catch:{ all -> 0x0051 }
        L_0x0018:
            r1 = 0
            if (r5 == 0) goto L_0x0036
            com.google.android.gms.internal.firebase_ml.zzpg r3 = com.google.android.gms.internal.firebase_ml.zzpg.zzi(r3)     // Catch:{ all -> 0x0051 }
            java.util.Map<com.google.android.gms.internal.firebase_ml.zzpg, com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer> r4 = zzawp     // Catch:{ all -> 0x0051 }
            java.lang.Object r4 = r4.get(r3)     // Catch:{ all -> 0x0051 }
            com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer r4 = (com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer) r4     // Catch:{ all -> 0x0051 }
            if (r4 != 0) goto L_0x0034
            com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer r4 = new com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer     // Catch:{ all -> 0x0051 }
            r5 = 1
            r4.<init>(r3, r1, r5)     // Catch:{ all -> 0x0051 }
            java.util.Map<com.google.android.gms.internal.firebase_ml.zzpg, com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer> r5 = zzawp     // Catch:{ all -> 0x0051 }
            r5.put(r3, r4)     // Catch:{ all -> 0x0051 }
        L_0x0034:
            monitor-exit(r0)
            return r4
        L_0x0036:
            com.google.android.gms.internal.firebase_ml.zzpf r3 = com.google.android.gms.internal.firebase_ml.zzpf.zza((com.google.firebase.FirebaseApp) r3, (com.google.firebase.ml.vision.text.FirebaseVisionCloudTextRecognizerOptions) r4)     // Catch:{ all -> 0x0051 }
            java.util.Map<com.google.android.gms.internal.firebase_ml.zzpf, com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer> r4 = zzawq     // Catch:{ all -> 0x0051 }
            java.lang.Object r4 = r4.get(r3)     // Catch:{ all -> 0x0051 }
            com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer r4 = (com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer) r4     // Catch:{ all -> 0x0051 }
            if (r4 != 0) goto L_0x004f
            com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer r4 = new com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer     // Catch:{ all -> 0x0051 }
            r5 = 2
            r4.<init>(r1, r3, r5)     // Catch:{ all -> 0x0051 }
            java.util.Map<com.google.android.gms.internal.firebase_ml.zzpf, com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer> r5 = zzawq     // Catch:{ all -> 0x0051 }
            r5.put(r3, r4)     // Catch:{ all -> 0x0051 }
        L_0x004f:
            monitor-exit(r0)
            return r4
        L_0x0051:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer.zza(com.google.firebase.FirebaseApp, com.google.firebase.ml.vision.text.FirebaseVisionCloudTextRecognizerOptions, boolean):com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer");
    }

    public void close() {
        zzpg zzpg = this.zzaxi;
        if (zzpg != null) {
            zzpg.close();
        }
        zzpf zzpf = this.zzaxj;
        if (zzpf != null) {
            zzpf.close();
        }
    }

    @RecognizerType
    public int getRecognizerType() {
        return this.zzaxk;
    }

    public Task<FirebaseVisionText> processImage(@NonNull FirebaseVisionImage firebaseVisionImage) {
        Preconditions.checkArgument((this.zzaxi == null && this.zzaxj == null) ? false : true, "Either on-device or cloud text recognizer should be enabled.");
        zzpg zzpg = this.zzaxi;
        return zzpg != null ? zzpg.processImage(firebaseVisionImage) : this.zzaxj.processImage(firebaseVisionImage);
    }
}
