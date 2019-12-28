package com.google.android.gms.internal.firebase_ml;

import android.content.Context;
import android.os.SystemClock;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzlo;
import com.google.android.gms.vision.label.ImageLabeler;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel;
import com.google.firebase.ml.vision.label.FirebaseVisionOnDeviceImageLabelerOptions;
import java.util.List;

public final class zzpd implements zzmx<List<FirebaseVisionImageLabel>, zzoy>, zznh {
    @VisibleForTesting
    private static boolean zzarn = true;
    private static volatile Boolean zzaws;
    private final zznf zzapl;
    private final FirebaseVisionOnDeviceImageLabelerOptions zzawt;
    @GuardedBy("this")
    private ImageLabeler zzawu;
    private final Context zzv;

    public zzpd(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseVisionOnDeviceImageLabelerOptions firebaseVisionOnDeviceImageLabelerOptions) {
        Preconditions.checkNotNull(firebaseApp, "Context can not be null");
        Preconditions.checkNotNull(firebaseVisionOnDeviceImageLabelerOptions, "FirebaseVisionOnDeviceImageLabelerOptions can not be null");
        this.zzv = firebaseApp.getApplicationContext();
        this.zzawt = firebaseVisionOnDeviceImageLabelerOptions;
        this.zzapl = zznf.zza(firebaseApp, 1);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x001f  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0020  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized java.util.List<com.google.firebase.ml.vision.label.FirebaseVisionImageLabel> zza(com.google.android.gms.internal.firebase_ml.zzoy r9) {
        /*
            r8 = this;
            monitor-enter(r8)
            java.lang.Boolean r0 = zzaws     // Catch:{ all -> 0x0099 }
            r1 = 0
            if (r0 != 0) goto L_0x0027
            android.content.Context r0 = r8.zzv     // Catch:{ all -> 0x0099 }
            java.lang.String r2 = "com.google.android.gms.vision.dynamite.ica"
            int r2 = com.google.android.gms.dynamite.DynamiteModule.getLocalVersion(r0, r2)     // Catch:{ all -> 0x0099 }
            r3 = 1
            if (r2 > 0) goto L_0x001c
            java.lang.String r2 = "com.google.android.gms.vision.dynamite.imagelabel"
            int r0 = com.google.android.gms.dynamite.DynamiteModule.getLocalVersion(r0, r2)     // Catch:{ all -> 0x0099 }
            if (r0 <= 0) goto L_0x001a
            goto L_0x001c
        L_0x001a:
            r0 = 0
            goto L_0x001d
        L_0x001c:
            r0 = 1
        L_0x001d:
            if (r0 != 0) goto L_0x0020
            goto L_0x0021
        L_0x0020:
            r3 = 0
        L_0x0021:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r3)     // Catch:{ all -> 0x0099 }
            zzaws = r0     // Catch:{ all -> 0x0099 }
        L_0x0027:
            java.lang.Boolean r0 = zzaws     // Catch:{ all -> 0x0099 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0099 }
            r2 = 14
            if (r0 != 0) goto L_0x0091
            long r3 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x0099 }
            com.google.android.gms.vision.label.ImageLabeler r0 = r8.zzawu     // Catch:{ all -> 0x0099 }
            if (r0 == 0) goto L_0x0082
            com.google.android.gms.vision.label.ImageLabeler r0 = r8.zzawu     // Catch:{ all -> 0x0099 }
            boolean r0 = r0.isOperational()     // Catch:{ all -> 0x0099 }
            if (r0 == 0) goto L_0x0075
            com.google.android.gms.vision.label.ImageLabeler r0 = r8.zzawu     // Catch:{ all -> 0x0099 }
            com.google.android.gms.vision.Frame r2 = r9.zzaux     // Catch:{ all -> 0x0099 }
            android.util.SparseArray r0 = r0.detect(r2)     // Catch:{ all -> 0x0099 }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x0099 }
            r2.<init>()     // Catch:{ all -> 0x0099 }
            if (r0 == 0) goto L_0x006c
            r5 = 0
        L_0x0051:
            int r6 = r0.size()     // Catch:{ all -> 0x0099 }
            if (r5 >= r6) goto L_0x006c
            int r6 = r0.keyAt(r5)     // Catch:{ all -> 0x0099 }
            com.google.firebase.ml.vision.label.FirebaseVisionImageLabel r7 = new com.google.firebase.ml.vision.label.FirebaseVisionImageLabel     // Catch:{ all -> 0x0099 }
            java.lang.Object r6 = r0.get(r6)     // Catch:{ all -> 0x0099 }
            com.google.android.gms.vision.label.ImageLabel r6 = (com.google.android.gms.vision.label.ImageLabel) r6     // Catch:{ all -> 0x0099 }
            r7.<init>(r6)     // Catch:{ all -> 0x0099 }
            r2.add(r7)     // Catch:{ all -> 0x0099 }
            int r5 = r5 + 1
            goto L_0x0051
        L_0x006c:
            com.google.android.gms.internal.firebase_ml.zzlv r0 = com.google.android.gms.internal.firebase_ml.zzlv.NO_ERROR     // Catch:{ all -> 0x0099 }
            r8.zza(r0, r3, r9)     // Catch:{ all -> 0x0099 }
            zzarn = r1     // Catch:{ all -> 0x0099 }
            monitor-exit(r8)
            return r2
        L_0x0075:
            com.google.android.gms.internal.firebase_ml.zzlv r0 = com.google.android.gms.internal.firebase_ml.zzlv.MODEL_NOT_DOWNLOADED     // Catch:{ all -> 0x0099 }
            r8.zza(r0, r3, r9)     // Catch:{ all -> 0x0099 }
            com.google.firebase.ml.common.FirebaseMLException r9 = new com.google.firebase.ml.common.FirebaseMLException     // Catch:{ all -> 0x0099 }
            java.lang.String r0 = "Waiting for the label detection model to be downloaded. Please wait."
            r9.<init>(r0, r2)     // Catch:{ all -> 0x0099 }
            throw r9     // Catch:{ all -> 0x0099 }
        L_0x0082:
            com.google.android.gms.internal.firebase_ml.zzlv r0 = com.google.android.gms.internal.firebase_ml.zzlv.UNKNOWN_ERROR     // Catch:{ all -> 0x0099 }
            r8.zza(r0, r3, r9)     // Catch:{ all -> 0x0099 }
            com.google.firebase.ml.common.FirebaseMLException r9 = new com.google.firebase.ml.common.FirebaseMLException     // Catch:{ all -> 0x0099 }
            java.lang.String r0 = "Model source is unavailable. Please load the model resource first."
            r1 = 13
            r9.<init>(r0, r1)     // Catch:{ all -> 0x0099 }
            throw r9     // Catch:{ all -> 0x0099 }
        L_0x0091:
            com.google.firebase.ml.common.FirebaseMLException r9 = new com.google.firebase.ml.common.FirebaseMLException     // Catch:{ all -> 0x0099 }
            java.lang.String r0 = "No model is bundled. Please check your app setup to includefirebase-ml-vision-image-label-model dependency."
            r9.<init>(r0, r2)     // Catch:{ all -> 0x0099 }
            throw r9     // Catch:{ all -> 0x0099 }
        L_0x0099:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzpd.zza(com.google.android.gms.internal.firebase_ml.zzoy):java.util.List");
    }

    private final void zza(zzlv zzlv, long j, zzoy zzoy) {
        this.zzapl.zza((zzng) new zzpe(this, j, zzlv, zzoy), zzly.ON_DEVICE_IMAGE_LABEL_DETECT);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzlo.zzq.zza a(long j, zzlv zzlv, zzoy zzoy) {
        return zzlo.zzq.zziu().zzb(zzlo.zzx.zzjj().zze(zzlo.zzs.zziy().zzk(SystemClock.elapsedRealtime() - j).zzc(zzlv).zzz(zzarn).zzaa(true).zzab(true)).zzb(this.zzawt.zzmc()).zzd(zzou.zzc(zzoy)));
    }

    @WorkerThread
    public final synchronized void release() {
        if (this.zzawu != null) {
            this.zzawu.release();
            this.zzawu = null;
        }
        zzarn = true;
    }

    public final zznh zzkh() {
        return this;
    }

    @WorkerThread
    public final synchronized void zzkl() {
        if (this.zzawu == null) {
            this.zzawu = new ImageLabeler.Builder(this.zzv).setScoreThreshold(this.zzawt.getConfidenceThreshold()).build();
        }
    }
}
