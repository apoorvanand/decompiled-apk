package com.google.android.gms.internal.firebase_ml;

import android.content.Context;
import android.os.SystemClock;
import android.util.SparseArray;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.firebase_ml.zzlo;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public final class zzoz implements zzmx<List<FirebaseVisionFace>, zzoy>, zznh {
    @VisibleForTesting
    private static AtomicBoolean zzasd = new AtomicBoolean(true);
    private static volatile Boolean zzawe;
    private final zznf zzapl;
    private zzot zzatv = new zzot();
    private final FirebaseVisionFaceDetectorOptions zzavx;
    @GuardedBy("this")
    private FaceDetector zzawf;
    @GuardedBy("this")
    private FaceDetector zzawg;
    private final Context zzv;

    public zzoz(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseVisionFaceDetectorOptions firebaseVisionFaceDetectorOptions) {
        Preconditions.checkNotNull(firebaseApp, "FirebaseApp can not be null");
        Preconditions.checkNotNull(firebaseVisionFaceDetectorOptions, "FirebaseVisionFaceDetectorOptions can not be null");
        this.zzv = firebaseApp.getApplicationContext();
        this.zzavx = firebaseVisionFaceDetectorOptions;
        this.zzapl = zznf.zza(firebaseApp, 1);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0138  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x013a  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0141  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0143  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x010f A[SYNTHETIC] */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized java.util.List<com.google.firebase.ml.vision.face.FirebaseVisionFace> zza(@androidx.annotation.NonNull com.google.android.gms.internal.firebase_ml.zzoy r22) {
        /*
            r21 = this;
            r8 = r21
            r0 = r22
            monitor-enter(r21)
            long r3 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x015a }
            com.google.android.gms.vision.face.FaceDetector r1 = r8.zzawf     // Catch:{ all -> 0x015a }
            r9 = 13
            if (r1 != 0) goto L_0x0027
            com.google.android.gms.vision.face.FaceDetector r1 = r8.zzawg     // Catch:{ all -> 0x015a }
            if (r1 == 0) goto L_0x0014
            goto L_0x0027
        L_0x0014:
            com.google.android.gms.internal.firebase_ml.zzlv r2 = com.google.android.gms.internal.firebase_ml.zzlv.UNKNOWN_ERROR     // Catch:{ all -> 0x015a }
            r6 = 0
            r7 = 0
            r1 = r21
            r5 = r22
            r1.zza(r2, r3, r5, r6, r7)     // Catch:{ all -> 0x015a }
            com.google.firebase.ml.common.FirebaseMLException r0 = new com.google.firebase.ml.common.FirebaseMLException     // Catch:{ all -> 0x015a }
            java.lang.String r1 = "Face detector wasn't initialized correctly. This is most likely caused by invalid face detector options."
            r0.<init>(r1, r9)     // Catch:{ all -> 0x015a }
            throw r0     // Catch:{ all -> 0x015a }
        L_0x0027:
            com.google.android.gms.internal.firebase_ml.zzot r1 = r8.zzatv     // Catch:{ all -> 0x015a }
            r1.zzb(r0)     // Catch:{ all -> 0x015a }
            com.google.android.gms.vision.face.FaceDetector r1 = r8.zzawf     // Catch:{ all -> 0x015a }
            r2 = 0
            if (r1 == 0) goto L_0x0043
            com.google.android.gms.vision.face.FaceDetector r1 = r8.zzawf     // Catch:{ all -> 0x015a }
            java.util.List r1 = r8.zza(r1, r0, r3)     // Catch:{ all -> 0x015a }
            com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions r5 = r8.zzavx     // Catch:{ all -> 0x015a }
            boolean r5 = r5.isTrackingEnabled()     // Catch:{ all -> 0x015a }
            if (r5 != 0) goto L_0x0044
            zzg(r1)     // Catch:{ all -> 0x015a }
            goto L_0x0044
        L_0x0043:
            r1 = r2
        L_0x0044:
            com.google.android.gms.vision.face.FaceDetector r5 = r8.zzawg     // Catch:{ all -> 0x015a }
            if (r5 == 0) goto L_0x0051
            com.google.android.gms.vision.face.FaceDetector r2 = r8.zzawg     // Catch:{ all -> 0x015a }
            java.util.List r2 = r8.zza(r2, r0, r3)     // Catch:{ all -> 0x015a }
            zzg(r2)     // Catch:{ all -> 0x015a }
        L_0x0051:
            if (r1 != 0) goto L_0x005e
            if (r2 == 0) goto L_0x0056
            goto L_0x005e
        L_0x0056:
            com.google.firebase.ml.common.FirebaseMLException r0 = new com.google.firebase.ml.common.FirebaseMLException     // Catch:{ all -> 0x015a }
            java.lang.String r1 = "No detector is enabled"
            r0.<init>(r1, r9)     // Catch:{ all -> 0x015a }
            throw r0     // Catch:{ all -> 0x015a }
        L_0x005e:
            if (r1 != 0) goto L_0x0065
            r0 = r2
        L_0x0061:
            r19 = r3
            goto L_0x0134
        L_0x0065:
            if (r2 != 0) goto L_0x0069
            r0 = r1
            goto L_0x0061
        L_0x0069:
            java.util.HashSet r5 = new java.util.HashSet     // Catch:{ all -> 0x015a }
            r5.<init>()     // Catch:{ all -> 0x015a }
            java.util.Iterator r6 = r2.iterator()     // Catch:{ all -> 0x015a }
        L_0x0072:
            boolean r7 = r6.hasNext()     // Catch:{ all -> 0x015a }
            if (r7 == 0) goto L_0x012d
            java.lang.Object r7 = r6.next()     // Catch:{ all -> 0x015a }
            com.google.firebase.ml.vision.face.FirebaseVisionFace r7 = (com.google.firebase.ml.vision.face.FirebaseVisionFace) r7     // Catch:{ all -> 0x015a }
            java.util.Iterator r10 = r1.iterator()     // Catch:{ all -> 0x015a }
            r11 = 0
        L_0x0083:
            boolean r12 = r10.hasNext()     // Catch:{ all -> 0x015a }
            if (r12 == 0) goto L_0x011c
            java.lang.Object r12 = r10.next()     // Catch:{ all -> 0x015a }
            com.google.firebase.ml.vision.face.FirebaseVisionFace r12 = (com.google.firebase.ml.vision.face.FirebaseVisionFace) r12     // Catch:{ all -> 0x015a }
            android.graphics.Rect r13 = r7.getBoundingBox()     // Catch:{ all -> 0x015a }
            if (r13 == 0) goto L_0x00fe
            android.graphics.Rect r13 = r12.getBoundingBox()     // Catch:{ all -> 0x015a }
            if (r13 != 0) goto L_0x009c
            goto L_0x00fe
        L_0x009c:
            android.graphics.Rect r13 = r7.getBoundingBox()     // Catch:{ all -> 0x015a }
            android.graphics.Rect r15 = r12.getBoundingBox()     // Catch:{ all -> 0x015a }
            boolean r16 = r13.intersect(r15)     // Catch:{ all -> 0x015a }
            if (r16 == 0) goto L_0x00fe
            int r14 = r13.right     // Catch:{ all -> 0x015a }
            int r9 = r15.right     // Catch:{ all -> 0x015a }
            int r9 = java.lang.Math.min(r14, r9)     // Catch:{ all -> 0x015a }
            int r14 = r13.left     // Catch:{ all -> 0x015a }
            int r0 = r15.left     // Catch:{ all -> 0x015a }
            int r0 = java.lang.Math.max(r14, r0)     // Catch:{ all -> 0x015a }
            int r9 = r9 - r0
            int r0 = r13.bottom     // Catch:{ all -> 0x015a }
            int r14 = r15.bottom     // Catch:{ all -> 0x015a }
            int r0 = java.lang.Math.min(r0, r14)     // Catch:{ all -> 0x015a }
            int r14 = r13.top     // Catch:{ all -> 0x015a }
            r17 = r6
            int r6 = r15.top     // Catch:{ all -> 0x015a }
            int r6 = java.lang.Math.max(r14, r6)     // Catch:{ all -> 0x015a }
            int r0 = r0 - r6
            int r9 = r9 * r0
            r0 = r10
            double r9 = (double) r9     // Catch:{ all -> 0x015a }
            int r6 = r13.right     // Catch:{ all -> 0x015a }
            int r14 = r13.left     // Catch:{ all -> 0x015a }
            int r6 = r6 - r14
            int r14 = r13.bottom     // Catch:{ all -> 0x015a }
            int r13 = r13.top     // Catch:{ all -> 0x015a }
            int r14 = r14 - r13
            int r6 = r6 * r14
            double r13 = (double) r6     // Catch:{ all -> 0x015a }
            int r6 = r15.right     // Catch:{ all -> 0x015a }
            r18 = r0
            int r0 = r15.left     // Catch:{ all -> 0x015a }
            int r6 = r6 - r0
            int r0 = r15.bottom     // Catch:{ all -> 0x015a }
            int r15 = r15.top     // Catch:{ all -> 0x015a }
            int r0 = r0 - r15
            int r6 = r6 * r0
            r19 = r3
            double r3 = (double) r6     // Catch:{ all -> 0x015a }
            double r13 = r13 + r3
            double r13 = r13 - r9
            double r9 = r9 / r13
            r3 = 4603579539098121011(0x3fe3333333333333, double:0.6)
            int r0 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r0 <= 0) goto L_0x0104
            r0 = 1
            goto L_0x0105
        L_0x00fe:
            r19 = r3
            r17 = r6
            r18 = r10
        L_0x0104:
            r0 = 0
        L_0x0105:
            if (r0 == 0) goto L_0x010f
            android.util.SparseArray r0 = r7.zzma()     // Catch:{ all -> 0x015a }
            r12.zza(r0)     // Catch:{ all -> 0x015a }
            r11 = 1
        L_0x010f:
            r5.add(r12)     // Catch:{ all -> 0x015a }
            r6 = r17
            r10 = r18
            r3 = r19
            r0 = r22
            goto L_0x0083
        L_0x011c:
            r19 = r3
            r17 = r6
            if (r11 != 0) goto L_0x0125
            r5.add(r7)     // Catch:{ all -> 0x015a }
        L_0x0125:
            r6 = r17
            r3 = r19
            r0 = r22
            goto L_0x0072
        L_0x012d:
            r19 = r3
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x015a }
            r0.<init>(r5)     // Catch:{ all -> 0x015a }
        L_0x0134:
            com.google.android.gms.internal.firebase_ml.zzlv r3 = com.google.android.gms.internal.firebase_ml.zzlv.NO_ERROR     // Catch:{ all -> 0x015a }
            if (r2 != 0) goto L_0x013a
            r6 = 0
            goto L_0x013f
        L_0x013a:
            int r2 = r2.size()     // Catch:{ all -> 0x015a }
            r6 = r2
        L_0x013f:
            if (r1 != 0) goto L_0x0143
            r7 = 0
            goto L_0x0148
        L_0x0143:
            int r1 = r1.size()     // Catch:{ all -> 0x015a }
            r7 = r1
        L_0x0148:
            r1 = r21
            r2 = r3
            r3 = r19
            r5 = r22
            r1.zza(r2, r3, r5, r6, r7)     // Catch:{ all -> 0x015a }
            java.util.concurrent.atomic.AtomicBoolean r1 = zzasd     // Catch:{ all -> 0x015a }
            r2 = 0
            r1.set(r2)     // Catch:{ all -> 0x015a }
            monitor-exit(r21)
            return r0
        L_0x015a:
            r0 = move-exception
            monitor-exit(r21)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzoz.zza(com.google.android.gms.internal.firebase_ml.zzoy):java.util.List");
    }

    @VisibleForTesting
    @WorkerThread
    private final synchronized List<FirebaseVisionFace> zza(@NonNull FaceDetector faceDetector, @NonNull zzoy zzoy, long j) {
        ArrayList arrayList;
        if (this.zzawg != null) {
            if (zzawe == null) {
                zzawe = Boolean.valueOf(DynamiteModule.getLocalVersion(this.zzv, "com.google.android.gms.vision.dynamite.face") > 0);
            }
            if (!zzawe.booleanValue()) {
                throw new FirebaseMLException("No Face Contour model is bundled. Please check your app setup to include firebase-ml-vision-face-model dependency.", 14);
            }
        }
        if (faceDetector.isOperational()) {
            SparseArray<Face> detect = faceDetector.detect(zzoy.zzaux);
            arrayList = new ArrayList();
            for (int i = 0; i < detect.size(); i++) {
                arrayList.add(new FirebaseVisionFace(detect.get(detect.keyAt(i))));
            }
        } else {
            zza(zzlv.MODEL_NOT_DOWNLOADED, j, zzoy, 0, 0);
            throw new FirebaseMLException("Waiting for the face detection model to be downloaded. Please wait.", 14);
        }
        return arrayList;
    }

    private final synchronized void zza(zzlv zzlv, long j, zzoy zzoy, int i, int i2) {
        this.zzapl.zza((zzng) new zzpa(this, j, zzlv, i, i2, zzoy), zzly.ON_DEVICE_FACE_DETECT);
    }

    private static void zzg(@NonNull List<FirebaseVisionFace> list) {
        for (FirebaseVisionFace zzbi : list) {
            zzbi.zzbi(-1);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzlo.zzq.zza a(long j, zzlv zzlv, int i, int i2, zzoy zzoy) {
        return zzlo.zzq.zziu().zzb(zzlo.zzw.zzjh().zzd(zzlo.zzs.zziy().zzk(SystemClock.elapsedRealtime() - j).zzc(zzlv).zzz(zzasd.get()).zzaa(true).zzab(true)).zzb(this.zzavx.zzmb()).zzaz(i).zzba(i2).zzc(zzou.zzc(zzoy)));
    }

    @WorkerThread
    public final synchronized void release() {
        if (this.zzawf != null) {
            this.zzawf.release();
            this.zzawf = null;
        }
        if (this.zzawg != null) {
            this.zzawg.release();
            this.zzawg = null;
        }
        zzasd.set(true);
    }

    public final zznh zzkh() {
        return this;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00e6, code lost:
        return;
     */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zzkl() {
        /*
            r3 = this;
            monitor-enter(r3)
            com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions r0 = r3.zzavx     // Catch:{ all -> 0x00e7 }
            int r0 = r0.getContourMode()     // Catch:{ all -> 0x00e7 }
            r1 = 2
            if (r0 != r1) goto L_0x0096
            com.google.android.gms.vision.face.FaceDetector r0 = r3.zzawg     // Catch:{ all -> 0x00e7 }
            if (r0 != 0) goto L_0x002d
            com.google.android.gms.vision.face.FaceDetector$Builder r0 = new com.google.android.gms.vision.face.FaceDetector$Builder     // Catch:{ all -> 0x00e7 }
            android.content.Context r2 = r3.zzv     // Catch:{ all -> 0x00e7 }
            r0.<init>(r2)     // Catch:{ all -> 0x00e7 }
            com.google.android.gms.vision.face.FaceDetector$Builder r0 = r0.setLandmarkType(r1)     // Catch:{ all -> 0x00e7 }
            com.google.android.gms.vision.face.FaceDetector$Builder r0 = r0.setMode(r1)     // Catch:{ all -> 0x00e7 }
            r2 = 0
            com.google.android.gms.vision.face.FaceDetector$Builder r0 = r0.setTrackingEnabled(r2)     // Catch:{ all -> 0x00e7 }
            r2 = 1
            com.google.android.gms.vision.face.FaceDetector$Builder r0 = r0.setProminentFaceOnly(r2)     // Catch:{ all -> 0x00e7 }
            com.google.android.gms.vision.face.FaceDetector r0 = r0.build()     // Catch:{ all -> 0x00e7 }
            r3.zzawg = r0     // Catch:{ all -> 0x00e7 }
        L_0x002d:
            com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions r0 = r3.zzavx     // Catch:{ all -> 0x00e7 }
            int r0 = r0.getLandmarkMode()     // Catch:{ all -> 0x00e7 }
            if (r0 == r1) goto L_0x0045
            com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions r0 = r3.zzavx     // Catch:{ all -> 0x00e7 }
            int r0 = r0.getClassificationMode()     // Catch:{ all -> 0x00e7 }
            if (r0 == r1) goto L_0x0045
            com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions r0 = r3.zzavx     // Catch:{ all -> 0x00e7 }
            int r0 = r0.getPerformanceMode()     // Catch:{ all -> 0x00e7 }
            if (r0 != r1) goto L_0x00e5
        L_0x0045:
            com.google.android.gms.vision.face.FaceDetector r0 = r3.zzawf     // Catch:{ all -> 0x00e7 }
            if (r0 != 0) goto L_0x00e5
            com.google.android.gms.vision.face.FaceDetector$Builder r0 = new com.google.android.gms.vision.face.FaceDetector$Builder     // Catch:{ all -> 0x00e7 }
            android.content.Context r1 = r3.zzv     // Catch:{ all -> 0x00e7 }
            r0.<init>(r1)     // Catch:{ all -> 0x00e7 }
            com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions r1 = r3.zzavx     // Catch:{ all -> 0x00e7 }
            int r1 = r1.getLandmarkMode()     // Catch:{ all -> 0x00e7 }
            int r1 = com.google.android.gms.internal.firebase_ml.zzou.zzbe(r1)     // Catch:{ all -> 0x00e7 }
            com.google.android.gms.vision.face.FaceDetector$Builder r0 = r0.setLandmarkType(r1)     // Catch:{ all -> 0x00e7 }
            com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions r1 = r3.zzavx     // Catch:{ all -> 0x00e7 }
            int r1 = r1.getClassificationMode()     // Catch:{ all -> 0x00e7 }
            int r1 = com.google.android.gms.internal.firebase_ml.zzou.zzbg(r1)     // Catch:{ all -> 0x00e7 }
            com.google.android.gms.vision.face.FaceDetector$Builder r0 = r0.setClassificationType(r1)     // Catch:{ all -> 0x00e7 }
            com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions r1 = r3.zzavx     // Catch:{ all -> 0x00e7 }
            int r1 = r1.getPerformanceMode()     // Catch:{ all -> 0x00e7 }
            int r1 = com.google.android.gms.internal.firebase_ml.zzou.zzbf(r1)     // Catch:{ all -> 0x00e7 }
            com.google.android.gms.vision.face.FaceDetector$Builder r0 = r0.setMode(r1)     // Catch:{ all -> 0x00e7 }
            com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions r1 = r3.zzavx     // Catch:{ all -> 0x00e7 }
            float r1 = r1.getMinFaceSize()     // Catch:{ all -> 0x00e7 }
            com.google.android.gms.vision.face.FaceDetector$Builder r0 = r0.setMinFaceSize(r1)     // Catch:{ all -> 0x00e7 }
            com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions r1 = r3.zzavx     // Catch:{ all -> 0x00e7 }
            boolean r1 = r1.isTrackingEnabled()     // Catch:{ all -> 0x00e7 }
            com.google.android.gms.vision.face.FaceDetector$Builder r0 = r0.setTrackingEnabled(r1)     // Catch:{ all -> 0x00e7 }
            com.google.android.gms.vision.face.FaceDetector r0 = r0.build()     // Catch:{ all -> 0x00e7 }
            r3.zzawf = r0     // Catch:{ all -> 0x00e7 }
            monitor-exit(r3)
            return
        L_0x0096:
            com.google.android.gms.vision.face.FaceDetector r0 = r3.zzawf     // Catch:{ all -> 0x00e7 }
            if (r0 != 0) goto L_0x00e5
            com.google.android.gms.vision.face.FaceDetector$Builder r0 = new com.google.android.gms.vision.face.FaceDetector$Builder     // Catch:{ all -> 0x00e7 }
            android.content.Context r1 = r3.zzv     // Catch:{ all -> 0x00e7 }
            r0.<init>(r1)     // Catch:{ all -> 0x00e7 }
            com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions r1 = r3.zzavx     // Catch:{ all -> 0x00e7 }
            int r1 = r1.getLandmarkMode()     // Catch:{ all -> 0x00e7 }
            int r1 = com.google.android.gms.internal.firebase_ml.zzou.zzbe(r1)     // Catch:{ all -> 0x00e7 }
            com.google.android.gms.vision.face.FaceDetector$Builder r0 = r0.setLandmarkType(r1)     // Catch:{ all -> 0x00e7 }
            com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions r1 = r3.zzavx     // Catch:{ all -> 0x00e7 }
            int r1 = r1.getClassificationMode()     // Catch:{ all -> 0x00e7 }
            int r1 = com.google.android.gms.internal.firebase_ml.zzou.zzbg(r1)     // Catch:{ all -> 0x00e7 }
            com.google.android.gms.vision.face.FaceDetector$Builder r0 = r0.setClassificationType(r1)     // Catch:{ all -> 0x00e7 }
            com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions r1 = r3.zzavx     // Catch:{ all -> 0x00e7 }
            int r1 = r1.getPerformanceMode()     // Catch:{ all -> 0x00e7 }
            int r1 = com.google.android.gms.internal.firebase_ml.zzou.zzbf(r1)     // Catch:{ all -> 0x00e7 }
            com.google.android.gms.vision.face.FaceDetector$Builder r0 = r0.setMode(r1)     // Catch:{ all -> 0x00e7 }
            com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions r1 = r3.zzavx     // Catch:{ all -> 0x00e7 }
            float r1 = r1.getMinFaceSize()     // Catch:{ all -> 0x00e7 }
            com.google.android.gms.vision.face.FaceDetector$Builder r0 = r0.setMinFaceSize(r1)     // Catch:{ all -> 0x00e7 }
            com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions r1 = r3.zzavx     // Catch:{ all -> 0x00e7 }
            boolean r1 = r1.isTrackingEnabled()     // Catch:{ all -> 0x00e7 }
            com.google.android.gms.vision.face.FaceDetector$Builder r0 = r0.setTrackingEnabled(r1)     // Catch:{ all -> 0x00e7 }
            com.google.android.gms.vision.face.FaceDetector r0 = r0.build()     // Catch:{ all -> 0x00e7 }
            r3.zzawf = r0     // Catch:{ all -> 0x00e7 }
        L_0x00e5:
            monitor-exit(r3)
            return
        L_0x00e7:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzoz.zzkl():void");
    }
}
