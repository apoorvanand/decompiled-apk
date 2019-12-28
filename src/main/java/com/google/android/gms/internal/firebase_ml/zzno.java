package com.google.android.gms.internal.firebase_ml;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.common.modeldownload.FirebaseCloudModelSource;
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions;

public final class zzno {
    private static final GmsLogger zzaoe = new GmsLogger("ModelDownloadManager", "");
    private final FirebaseApp zzaot;
    private final FirebaseCloudModelSource zzapm;
    private final DownloadManager zzapn = ((DownloadManager) this.zzv.getSystemService("download"));
    private final zznn zzapo;
    private final zznq zzapp;
    private final Context zzv;

    zzno(@NonNull FirebaseApp firebaseApp, @NonNull zznq zznq, @NonNull FirebaseCloudModelSource firebaseCloudModelSource, @NonNull zznn zznn) {
        this.zzaot = firebaseApp;
        this.zzapp = zznq;
        this.zzv = firebaseApp.getApplicationContext();
        this.zzapm = firebaseCloudModelSource;
        if (this.zzapn == null) {
            zzaoe.d("ModelDownloadManager", "Download manager service is not available in the service.");
        }
        this.zzapo = zznn;
    }

    private final synchronized Long zza(@NonNull DownloadManager.Request request, @NonNull zznp zznp) {
        if (this.zzapn == null) {
            return null;
        }
        Long valueOf = Long.valueOf(this.zzapn.enqueue(request));
        GmsLogger gmsLogger = zzaoe;
        String valueOf2 = String.valueOf(valueOf);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf2).length() + 33);
        sb.append("Schedule a new downloading task: ");
        sb.append(valueOf2);
        gmsLogger.d("ModelDownloadManager", sb.toString());
        zznl.zza(this.zzaot, zznp.a, zznp.c, valueOf.longValue());
        return valueOf;
    }

    @WorkerThread
    @Nullable
    private final synchronized Long zza(@NonNull zznp zznp) {
        String zzb = zznl.zzb(this.zzaot, zznp.a);
        if (zzb == null || !zzb.equals(zznp.c)) {
            zzaoe.d("ModelDownloadManager", "Need to download a new model.");
            d();
            DownloadManager.Request request = new DownloadManager.Request(zznp.b);
            request.setDestinationUri((Uri) null);
            FirebaseModelDownloadConditions initialDownloadConditions = this.zzapm.getInitialDownloadConditions();
            if (this.zzapm.isModelUpdatesEnabled() || this.zzapp.a() == null) {
                if (this.zzapm.isModelUpdatesEnabled() && this.zzapp.a() != null) {
                    zzaoe.d("ModelDownloadManager", "Model update is enabled and have a previous downloaded model, use download condition");
                    initialDownloadConditions = this.zzapm.getUpdatesDownloadConditions();
                }
                zzaoe.d("ModelDownloadManager", "Use initial download conditions.");
                if (Build.VERSION.SDK_INT >= 24) {
                    request.setRequiresCharging(initialDownloadConditions.isChargingRequired());
                    request.setRequiresDeviceIdle(initialDownloadConditions.isDeviceIdleRequired());
                }
                if (initialDownloadConditions.isWifiRequired()) {
                    request.setAllowedNetworkTypes(2);
                }
                return zza(request, zznp);
            }
            zzaoe.d("ModelDownloadManager", "Model update is disabled and have a previous downloaded model, skip downloading");
            return null;
        }
        zzaoe.d("ModelDownloadManager", "New model is already in downloading, do nothing.");
        return null;
    }

    private final synchronized boolean zzkv() {
        return this.zzapp.a() != null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0068, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x006a, code lost:
        return null;
     */
    @androidx.annotation.WorkerThread
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized com.google.android.gms.internal.firebase_ml.zznp zzkw() {
        /*
            r8 = this;
            monitor-enter(r8)
            com.google.firebase.ml.common.modeldownload.FirebaseCloudModelSource r0 = r8.zzapm     // Catch:{ all -> 0x006b }
            java.lang.String r0 = r0.zzkz()     // Catch:{ all -> 0x006b }
            com.google.firebase.FirebaseApp r1 = r8.zzaot     // Catch:{ all -> 0x006b }
            com.google.firebase.ml.common.modeldownload.FirebaseCloudModelSource r2 = r8.zzapm     // Catch:{ all -> 0x006b }
            com.google.android.gms.internal.firebase_ml.zznn r3 = r8.zzapo     // Catch:{ all -> 0x006b }
            com.google.android.gms.internal.firebase_ml.zznp r1 = com.google.android.gms.internal.firebase_ml.zznr.a(r1, r2, r3)     // Catch:{ all -> 0x006b }
            r2 = 0
            if (r1 != 0) goto L_0x0016
            monitor-exit(r8)
            return r2
        L_0x0016:
            com.google.firebase.FirebaseApp r3 = r8.zzaot     // Catch:{ all -> 0x006b }
            java.lang.String r4 = r1.c     // Catch:{ all -> 0x006b }
            java.lang.String r5 = com.google.android.gms.internal.firebase_ml.zznl.zzd(r3, r0)     // Catch:{ all -> 0x006b }
            boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x006b }
            r5 = 0
            r6 = 1
            if (r4 == 0) goto L_0x003a
            android.content.Context r4 = r3.getApplicationContext()     // Catch:{ all -> 0x006b }
            java.lang.String r4 = com.google.android.gms.internal.firebase_ml.zzmv.zza(r4)     // Catch:{ all -> 0x006b }
            java.lang.String r3 = com.google.android.gms.internal.firebase_ml.zznl.zze(r3)     // Catch:{ all -> 0x006b }
            boolean r3 = r4.equals(r3)     // Catch:{ all -> 0x006b }
            if (r3 == 0) goto L_0x003a
            r3 = 1
            goto L_0x003b
        L_0x003a:
            r3 = 0
        L_0x003b:
            if (r3 == 0) goto L_0x0047
            com.google.android.gms.common.internal.GmsLogger r3 = zzaoe     // Catch:{ all -> 0x006b }
            java.lang.String r4 = "ModelDownloadManager"
            java.lang.String r7 = "The new model is incompatible and the app is not upgraded, do not download"
            r3.d(r4, r7)     // Catch:{ all -> 0x006b }
            goto L_0x0048
        L_0x0047:
            r5 = 1
        L_0x0048:
            boolean r3 = r8.zzkv()     // Catch:{ all -> 0x006b }
            r3 = r3 ^ r6
            if (r3 == 0) goto L_0x0054
            com.google.firebase.FirebaseApp r4 = r8.zzaot     // Catch:{ all -> 0x006b }
            com.google.android.gms.internal.firebase_ml.zznl.zzh(r4, r0)     // Catch:{ all -> 0x006b }
        L_0x0054:
            com.google.firebase.FirebaseApp r4 = r8.zzaot     // Catch:{ all -> 0x006b }
            java.lang.String r7 = r1.c     // Catch:{ all -> 0x006b }
            java.lang.String r0 = com.google.android.gms.internal.firebase_ml.zznl.zzc(r4, r0)     // Catch:{ all -> 0x006b }
            boolean r0 = r7.equals(r0)     // Catch:{ all -> 0x006b }
            r0 = r0 ^ r6
            if (r5 == 0) goto L_0x0069
            if (r3 != 0) goto L_0x0067
            if (r0 == 0) goto L_0x0069
        L_0x0067:
            monitor-exit(r8)
            return r1
        L_0x0069:
            monitor-exit(r8)
            return r2
        L_0x006b:
            r0 = move-exception
            monitor-exit(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzno.zzkw():com.google.android.gms.internal.firebase_ml.zznp");
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    @Nullable
    public final synchronized Long a() {
        zznp zzkw = zzkw();
        if (zzkw == null) {
            GmsLogger gmsLogger = zzaoe;
            String valueOf = String.valueOf(this.zzapm.zzkz());
            gmsLogger.d("ModelDownloadManager", valueOf.length() != 0 ? "No model updates for model: ".concat(valueOf) : new String("No model updates for model: "));
            return null;
        }
        return zza(zzkw);
    }

    /* access modifiers changed from: package-private */
    public final synchronized void a(@NonNull String str) {
        zznl.zza(this.zzaot, this.zzapm.zzkz(), str);
        d();
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public final synchronized Long b() {
        return zznl.zza(this.zzaot, this.zzapm.zzkz());
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public final synchronized String c() {
        return zznl.zzb(this.zzaot, this.zzapm.zzkz());
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0056, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0058, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void d() {
        /*
            r6 = this;
            monitor-enter(r6)
            java.lang.Long r0 = r6.b()     // Catch:{ all -> 0x0059 }
            android.app.DownloadManager r1 = r6.zzapn     // Catch:{ all -> 0x0059 }
            if (r1 == 0) goto L_0x0057
            if (r0 != 0) goto L_0x000c
            goto L_0x0057
        L_0x000c:
            com.google.android.gms.common.internal.GmsLogger r1 = zzaoe     // Catch:{ all -> 0x0059 }
            java.lang.String r2 = "ModelDownloadManager"
            java.lang.String r3 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0059 }
            java.lang.String r4 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x0059 }
            int r4 = r4.length()     // Catch:{ all -> 0x0059 }
            int r4 = r4 + 44
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0059 }
            r5.<init>(r4)     // Catch:{ all -> 0x0059 }
            java.lang.String r4 = "Cancel or remove existing downloading task: "
            r5.append(r4)     // Catch:{ all -> 0x0059 }
            r5.append(r3)     // Catch:{ all -> 0x0059 }
            java.lang.String r3 = r5.toString()     // Catch:{ all -> 0x0059 }
            r1.d(r2, r3)     // Catch:{ all -> 0x0059 }
            android.app.DownloadManager r1 = r6.zzapn     // Catch:{ all -> 0x0059 }
            r2 = 1
            long[] r2 = new long[r2]     // Catch:{ all -> 0x0059 }
            r3 = 0
            long r4 = r0.longValue()     // Catch:{ all -> 0x0059 }
            r2[r3] = r4     // Catch:{ all -> 0x0059 }
            int r0 = r1.remove(r2)     // Catch:{ all -> 0x0059 }
            if (r0 > 0) goto L_0x004a
            java.lang.Integer r0 = r6.e()     // Catch:{ all -> 0x0059 }
            if (r0 != 0) goto L_0x0055
        L_0x004a:
            com.google.firebase.FirebaseApp r0 = r6.zzaot     // Catch:{ all -> 0x0059 }
            com.google.firebase.ml.common.modeldownload.FirebaseCloudModelSource r1 = r6.zzapm     // Catch:{ all -> 0x0059 }
            java.lang.String r1 = r1.zzkz()     // Catch:{ all -> 0x0059 }
            com.google.android.gms.internal.firebase_ml.zznl.zzg(r0, r1)     // Catch:{ all -> 0x0059 }
        L_0x0055:
            monitor-exit(r6)
            return
        L_0x0057:
            monitor-exit(r6)
            return
        L_0x0059:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzno.d():void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0068, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x006a, code lost:
        return null;
     */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized java.lang.Integer e() {
        /*
            r9 = this;
            monitor-enter(r9)
            java.lang.Long r0 = r9.b()     // Catch:{ all -> 0x006b }
            android.app.DownloadManager r1 = r9.zzapn     // Catch:{ all -> 0x006b }
            r2 = 0
            if (r1 == 0) goto L_0x0069
            if (r0 != 0) goto L_0x000d
            goto L_0x0069
        L_0x000d:
            android.app.DownloadManager r1 = r9.zzapn     // Catch:{ all -> 0x006b }
            android.app.DownloadManager$Query r3 = new android.app.DownloadManager$Query     // Catch:{ all -> 0x006b }
            r3.<init>()     // Catch:{ all -> 0x006b }
            r4 = 1
            long[] r5 = new long[r4]     // Catch:{ all -> 0x006b }
            r6 = 0
            long r7 = r0.longValue()     // Catch:{ all -> 0x006b }
            r5[r6] = r7     // Catch:{ all -> 0x006b }
            android.app.DownloadManager$Query r0 = r3.setFilterById(r5)     // Catch:{ all -> 0x006b }
            android.database.Cursor r0 = r1.query(r0)     // Catch:{ all -> 0x006b }
            if (r0 == 0) goto L_0x003d
            boolean r1 = r0.moveToFirst()     // Catch:{ all -> 0x006b }
            if (r1 == 0) goto L_0x003d
            java.lang.String r1 = "status"
            int r1 = r0.getColumnIndex(r1)     // Catch:{ all -> 0x006b }
            int r0 = r0.getInt(r1)     // Catch:{ all -> 0x006b }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x006b }
            goto L_0x003e
        L_0x003d:
            r0 = r2
        L_0x003e:
            if (r0 != 0) goto L_0x0042
            monitor-exit(r9)
            return r2
        L_0x0042:
            int r1 = r0.intValue()     // Catch:{ all -> 0x006b }
            r3 = 2
            if (r1 == r3) goto L_0x0067
            int r1 = r0.intValue()     // Catch:{ all -> 0x006b }
            r3 = 4
            if (r1 == r3) goto L_0x0067
            int r1 = r0.intValue()     // Catch:{ all -> 0x006b }
            if (r1 == r4) goto L_0x0067
            int r1 = r0.intValue()     // Catch:{ all -> 0x006b }
            r3 = 8
            if (r1 == r3) goto L_0x0067
            int r1 = r0.intValue()     // Catch:{ all -> 0x006b }
            r3 = 16
            if (r1 == r3) goto L_0x0067
            r0 = r2
        L_0x0067:
            monitor-exit(r9)
            return r0
        L_0x0069:
            monitor-exit(r9)
            return r2
        L_0x006b:
            r0 = move-exception
            monitor-exit(r9)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzno.e():java.lang.Integer");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't wrap try/catch for region: R(2:8|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0024, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        zzaoe.e("ModelDownloadManager", "Downloaded file is not found");
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0018 */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized android.os.ParcelFileDescriptor f() {
        /*
            r5 = this;
            monitor-enter(r5)
            java.lang.Long r0 = r5.b()     // Catch:{ all -> 0x0025 }
            android.app.DownloadManager r1 = r5.zzapn     // Catch:{ all -> 0x0025 }
            r2 = 0
            if (r1 == 0) goto L_0x0023
            if (r0 != 0) goto L_0x000d
            goto L_0x0023
        L_0x000d:
            android.app.DownloadManager r1 = r5.zzapn     // Catch:{ FileNotFoundException -> 0x0018 }
            long r3 = r0.longValue()     // Catch:{ FileNotFoundException -> 0x0018 }
            android.os.ParcelFileDescriptor r2 = r1.openDownloadedFile(r3)     // Catch:{ FileNotFoundException -> 0x0018 }
            goto L_0x0021
        L_0x0018:
            com.google.android.gms.common.internal.GmsLogger r0 = zzaoe     // Catch:{ all -> 0x0025 }
            java.lang.String r1 = "ModelDownloadManager"
            java.lang.String r3 = "Downloaded file is not found"
            r0.e(r1, r3)     // Catch:{ all -> 0x0025 }
        L_0x0021:
            monitor-exit(r5)
            return r2
        L_0x0023:
            monitor-exit(r5)
            return r2
        L_0x0025:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzno.f():android.os.ParcelFileDescriptor");
    }
}
