package com.google.android.gms.internal.firebase_ml;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.common.modeldownload.FirebaseCloudModelSource;
import com.google.firebase.ml.common.modeldownload.FirebaseLocalModelSource;
import java.nio.MappedByteBuffer;
import java.util.HashMap;
import java.util.Map;

public final class zzns {
    private static final GmsLogger zzaoe = new GmsLogger("CloudModelLoader", "");
    private static Map<String, zzns> zzapv = new HashMap();
    private final FirebaseApp zzaot;
    private final zznn zzapo;
    private final zzno zzapw;
    private final zznq zzapx;
    private boolean zzapy = true;

    private zzns(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseCloudModelSource firebaseCloudModelSource, @NonNull zznw zznw) {
        this.zzaot = firebaseApp;
        this.zzapx = new zznq(firebaseApp, firebaseCloudModelSource.zzkz(), new zznt(this), zznw);
        this.zzapo = new zznn(firebaseApp, firebaseCloudModelSource);
        this.zzapw = new zzno(firebaseApp, this.zzapx, firebaseCloudModelSource, this.zzapo);
    }

    public static synchronized zzns zza(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseCloudModelSource firebaseCloudModelSource, @NonNull zznw zznw) {
        zzns zzns;
        synchronized (zzns.class) {
            String zzkz = firebaseCloudModelSource.zzkz();
            if (!zzapv.containsKey(zzkz)) {
                zzapv.put(zzkz, new zzns(firebaseApp, firebaseCloudModelSource, zznw));
            }
            zzns = zzapv.get(zzkz);
        }
        return zzns;
    }

    @WorkerThread
    private final MappedByteBuffer zzbx(@NonNull String str) {
        return new zznv(this.zzaot.getApplicationContext(), new FirebaseLocalModelSource.Builder(ImagesContract.LOCAL).setFilePath(str).build()).load();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void a() {
        this.zzapw.d();
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0116  */
    @androidx.annotation.WorkerThread
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized java.nio.MappedByteBuffer load() {
        /*
            r9 = this;
            monitor-enter(r9)
            com.google.android.gms.common.internal.GmsLogger r0 = zzaoe     // Catch:{ all -> 0x0129 }
            java.lang.String r1 = "CloudModelLoader"
            java.lang.String r2 = "Try to load newly downloaded model file."
            r0.d(r1, r2)     // Catch:{ all -> 0x0129 }
            boolean r0 = r9.zzapy     // Catch:{ all -> 0x0129 }
            com.google.android.gms.internal.firebase_ml.zzno r1 = r9.zzapw     // Catch:{ all -> 0x0129 }
            java.lang.Long r1 = r1.b()     // Catch:{ all -> 0x0129 }
            com.google.android.gms.internal.firebase_ml.zzno r2 = r9.zzapw     // Catch:{ all -> 0x0129 }
            java.lang.String r2 = r2.c()     // Catch:{ all -> 0x0129 }
            r3 = 0
            if (r1 == 0) goto L_0x00e2
            if (r2 != 0) goto L_0x001f
            goto L_0x00e2
        L_0x001f:
            com.google.android.gms.internal.firebase_ml.zzno r1 = r9.zzapw     // Catch:{ all -> 0x0129 }
            java.lang.Integer r1 = r1.e()     // Catch:{ all -> 0x0129 }
            if (r1 != 0) goto L_0x002e
            com.google.android.gms.internal.firebase_ml.zzno r0 = r9.zzapw     // Catch:{ all -> 0x0129 }
        L_0x0029:
            r0.d()     // Catch:{ all -> 0x0129 }
            goto L_0x00ef
        L_0x002e:
            com.google.android.gms.common.internal.GmsLogger r4 = zzaoe     // Catch:{ all -> 0x0129 }
            java.lang.String r5 = "CloudModelLoader"
            java.lang.String r6 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x0129 }
            java.lang.String r7 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x0129 }
            int r7 = r7.length()     // Catch:{ all -> 0x0129 }
            int r7 = r7 + 22
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0129 }
            r8.<init>(r7)     // Catch:{ all -> 0x0129 }
            java.lang.String r7 = "Download Status code: "
            r8.append(r7)     // Catch:{ all -> 0x0129 }
            r8.append(r6)     // Catch:{ all -> 0x0129 }
            java.lang.String r6 = r8.toString()     // Catch:{ all -> 0x0129 }
            r4.d(r5, r6)     // Catch:{ all -> 0x0129 }
            int r4 = r1.intValue()     // Catch:{ all -> 0x0129 }
            r5 = 8
            r6 = 1
            if (r4 != r5) goto L_0x00cf
            com.google.android.gms.common.internal.GmsLogger r1 = zzaoe     // Catch:{ all -> 0x0129 }
            java.lang.String r4 = "CloudModelLoader"
            java.lang.String r5 = "Model downloaded successfully"
            r1.d(r4, r5)     // Catch:{ all -> 0x0129 }
            com.google.android.gms.internal.firebase_ml.zznn r1 = r9.zzapo     // Catch:{ all -> 0x0129 }
            com.google.android.gms.internal.firebase_ml.zzlv r4 = com.google.android.gms.internal.firebase_ml.zzlv.NO_ERROR     // Catch:{ all -> 0x0129 }
            r1.a(r4, r6)     // Catch:{ all -> 0x0129 }
            com.google.android.gms.internal.firebase_ml.zzno r1 = r9.zzapw     // Catch:{ all -> 0x0129 }
            android.os.ParcelFileDescriptor r1 = r1.f()     // Catch:{ all -> 0x0129 }
            if (r1 != 0) goto L_0x0078
            com.google.android.gms.internal.firebase_ml.zzno r0 = r9.zzapw     // Catch:{ all -> 0x0129 }
            goto L_0x0029
        L_0x0078:
            com.google.android.gms.common.internal.GmsLogger r4 = zzaoe     // Catch:{ all -> 0x0129 }
            java.lang.String r5 = "CloudModelLoader"
            java.lang.String r6 = "moving downloaded model from external storage to private folder."
            r4.d(r5, r6)     // Catch:{ all -> 0x0129 }
            com.google.android.gms.internal.firebase_ml.zznq r4 = r9.zzapx     // Catch:{ all -> 0x0129 }
            com.google.android.gms.internal.firebase_ml.zznn r5 = r9.zzapo     // Catch:{ all -> 0x0129 }
            java.lang.String r1 = r4.a(r1, r2, r5)     // Catch:{ all -> 0x0129 }
            if (r1 != 0) goto L_0x008e
            com.google.android.gms.internal.firebase_ml.zzno r0 = r9.zzapw     // Catch:{ all -> 0x0129 }
            goto L_0x0029
        L_0x008e:
            com.google.android.gms.common.internal.GmsLogger r4 = zzaoe     // Catch:{ all -> 0x0129 }
            java.lang.String r5 = "CloudModelLoader"
            java.lang.String r6 = "Moved the downloaded model to private folder successfully: "
            java.lang.String r7 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x0129 }
            int r8 = r7.length()     // Catch:{ all -> 0x0129 }
            if (r8 == 0) goto L_0x00a3
            java.lang.String r6 = r6.concat(r7)     // Catch:{ all -> 0x0129 }
            goto L_0x00a9
        L_0x00a3:
            java.lang.String r7 = new java.lang.String     // Catch:{ all -> 0x0129 }
            r7.<init>(r6)     // Catch:{ all -> 0x0129 }
            r6 = r7
        L_0x00a9:
            r4.d(r5, r6)     // Catch:{ all -> 0x0129 }
            com.google.android.gms.internal.firebase_ml.zzno r4 = r9.zzapw     // Catch:{ all -> 0x0129 }
            r4.a(r2)     // Catch:{ all -> 0x0129 }
            if (r0 == 0) goto L_0x00ca
            com.google.android.gms.internal.firebase_ml.zznq r0 = r9.zzapx     // Catch:{ all -> 0x0129 }
            boolean r0 = r0.a(r1)     // Catch:{ all -> 0x0129 }
            if (r0 == 0) goto L_0x00ca
            com.google.android.gms.common.internal.GmsLogger r0 = zzaoe     // Catch:{ all -> 0x0129 }
            java.lang.String r2 = "CloudModelLoader"
            java.lang.String r4 = "All old models are deleted."
            r0.d(r2, r4)     // Catch:{ all -> 0x0129 }
            com.google.android.gms.internal.firebase_ml.zznq r0 = r9.zzapx     // Catch:{ all -> 0x0129 }
            java.lang.String r1 = r0.b(r1)     // Catch:{ all -> 0x0129 }
        L_0x00ca:
            java.nio.MappedByteBuffer r0 = r9.zzbx(r1)     // Catch:{ all -> 0x0129 }
            goto L_0x00f0
        L_0x00cf:
            int r0 = r1.intValue()     // Catch:{ all -> 0x0129 }
            r1 = 16
            if (r0 != r1) goto L_0x00ef
            com.google.android.gms.internal.firebase_ml.zznn r0 = r9.zzapo     // Catch:{ all -> 0x0129 }
            com.google.android.gms.internal.firebase_ml.zzlv r1 = com.google.android.gms.internal.firebase_ml.zzlv.DOWNLOAD_FAILED     // Catch:{ all -> 0x0129 }
            r0.a(r1, r6)     // Catch:{ all -> 0x0129 }
            com.google.android.gms.internal.firebase_ml.zzno r0 = r9.zzapw     // Catch:{ all -> 0x0129 }
            goto L_0x0029
        L_0x00e2:
            com.google.android.gms.common.internal.GmsLogger r0 = zzaoe     // Catch:{ all -> 0x0129 }
            java.lang.String r1 = "CloudModelLoader"
            java.lang.String r2 = "No new model is downloading."
            r0.d(r1, r2)     // Catch:{ all -> 0x0129 }
            com.google.android.gms.internal.firebase_ml.zzno r0 = r9.zzapw     // Catch:{ all -> 0x0129 }
            goto L_0x0029
        L_0x00ef:
            r0 = r3
        L_0x00f0:
            if (r0 != 0) goto L_0x0112
            com.google.android.gms.common.internal.GmsLogger r0 = zzaoe     // Catch:{ all -> 0x0129 }
            java.lang.String r1 = "CloudModelLoader"
            java.lang.String r2 = "Loading existing model file."
            r0.d(r1, r2)     // Catch:{ all -> 0x0129 }
            com.google.android.gms.internal.firebase_ml.zznq r0 = r9.zzapx     // Catch:{ all -> 0x0129 }
            java.lang.String r0 = r0.a()     // Catch:{ all -> 0x0129 }
            if (r0 != 0) goto L_0x010d
            com.google.android.gms.common.internal.GmsLogger r0 = zzaoe     // Catch:{ all -> 0x0129 }
            java.lang.String r1 = "CloudModelLoader"
            java.lang.String r2 = "No existing model file"
            r0.d(r1, r2)     // Catch:{ all -> 0x0129 }
            goto L_0x0111
        L_0x010d:
            java.nio.MappedByteBuffer r3 = r9.zzbx(r0)     // Catch:{ all -> 0x0129 }
        L_0x0111:
            r0 = r3
        L_0x0112:
            boolean r1 = r9.zzapy     // Catch:{ all -> 0x0129 }
            if (r1 == 0) goto L_0x0127
            r1 = 0
            r9.zzapy = r1     // Catch:{ all -> 0x0129 }
            com.google.android.gms.common.internal.GmsLogger r1 = zzaoe     // Catch:{ all -> 0x0129 }
            java.lang.String r2 = "CloudModelLoader"
            java.lang.String r3 = "Initial loading, check for model updates."
            r1.d(r2, r3)     // Catch:{ all -> 0x0129 }
            com.google.android.gms.internal.firebase_ml.zzno r1 = r9.zzapw     // Catch:{ all -> 0x0129 }
            r1.a()     // Catch:{ all -> 0x0129 }
        L_0x0127:
            monitor-exit(r9)
            return r0
        L_0x0129:
            r0 = move-exception
            monitor-exit(r9)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzns.load():java.nio.MappedByteBuffer");
    }
}
