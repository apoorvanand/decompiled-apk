package com.google.android.gms.internal.firebase_ml;

import android.app.Application;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.common.FirebaseMLException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public final class zzni {
    /* access modifiers changed from: private */
    public static final GmsLogger zzaoj = new GmsLogger("ModelResourceManager", "");
    @GuardedBy("ModelResourceManager.class")
    private static zzni zzaph;
    private final zzmy zzapc = zzmy.zzki();
    /* access modifiers changed from: private */
    public final AtomicLong zzapd = new AtomicLong(300000);
    @GuardedBy("this")
    private final Set<zznh> zzape = new HashSet();
    /* access modifiers changed from: private */
    public final Set<zznh> zzapf = new HashSet();
    private final ConcurrentHashMap<zznh, zznk> zzapg = new ConcurrentHashMap<>();

    private zzni(FirebaseApp firebaseApp) {
        if (firebaseApp.getApplicationContext() instanceof Application) {
            BackgroundDetector.initialize((Application) firebaseApp.getApplicationContext());
        } else {
            zzaoj.e("ModelResourceManager", "No valid Application available and auto-manage cannot work");
        }
        BackgroundDetector.getInstance().addListener(new zznj(this));
        if (BackgroundDetector.getInstance().readCurrentStateIfPossible(true)) {
            this.zzapd.set(2000);
        }
    }

    public static synchronized zzni zzb(FirebaseApp firebaseApp) {
        zzni zzni;
        synchronized (zzni.class) {
            if (zzaph == null) {
                zzaph = new zzni(firebaseApp);
            }
            zzni = zzaph;
        }
        return zzni;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized void zzb(@androidx.annotation.Nullable com.google.android.gms.internal.firebase_ml.zznh r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            if (r4 != 0) goto L_0x0005
            monitor-exit(r3)
            return
        L_0x0005:
            com.google.android.gms.internal.firebase_ml.zzmy r0 = r3.zzapc     // Catch:{ all -> 0x001e }
            com.google.android.gms.internal.firebase_ml.zznk r1 = new com.google.android.gms.internal.firebase_ml.zznk     // Catch:{ all -> 0x001e }
            java.lang.String r2 = "OPERATION_LOAD"
            r1.<init>(r3, r4, r2)     // Catch:{ all -> 0x001e }
            r0.zza(r1)     // Catch:{ all -> 0x001e }
            java.util.Set<com.google.android.gms.internal.firebase_ml.zznh> r0 = r3.zzape     // Catch:{ all -> 0x001e }
            boolean r0 = r0.contains(r4)     // Catch:{ all -> 0x001e }
            if (r0 == 0) goto L_0x001c
            r3.zzc(r4)     // Catch:{ all -> 0x001e }
        L_0x001c:
            monitor-exit(r3)
            return
        L_0x001e:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzni.zzb(com.google.android.gms.internal.firebase_ml.zznh):void");
    }

    private final synchronized void zzc(zznh zznh) {
        zznk zze = zze(zznh);
        this.zzapc.zzb(zze);
        long j = this.zzapd.get();
        GmsLogger gmsLogger = zzaoj;
        StringBuilder sb = new StringBuilder(62);
        sb.append("Rescheduling modelResource release after: ");
        sb.append(j);
        gmsLogger.v("ModelResourceManager", sb.toString());
        this.zzapc.zza(zze, j);
    }

    private final zznk zze(zznh zznh) {
        this.zzapg.putIfAbsent(zznh, new zznk(this, zznh, "OPERATION_RELEASE"));
        return this.zzapg.get(zznh);
    }

    /* access modifiers changed from: private */
    public final synchronized void zzkm() {
        for (zznh zzc : this.zzape) {
            zzc(zzc);
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void a(zznh zznh) {
        if (!this.zzapf.contains(zznh)) {
            try {
                zznh.zzkl();
                this.zzapf.add(zznh);
            } catch (RuntimeException e) {
                throw new FirebaseMLException("The load task failed", 13, e);
            }
        }
    }

    public final synchronized void zza(@NonNull zznh zznh) {
        Preconditions.checkNotNull(zznh, "Model source can not be null");
        zzaoj.d("ModelResourceManager", "Add auto-managed model resource");
        if (this.zzape.contains(zznh)) {
            zzaoj.i("ModelResourceManager", "The model resource is already registered.");
            return;
        }
        this.zzape.add(zznh);
        zzb(zznh);
    }

    public final synchronized void zzd(@Nullable zznh zznh) {
        if (zznh != null) {
            zznk zze = zze(zznh);
            this.zzapc.zzb(zze);
            this.zzapc.zza(zze, 0);
        }
    }
}
