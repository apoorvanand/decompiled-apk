package com.google.android.gms.internal.firebase_ml;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import java.util.concurrent.Callable;

public final class zznb {
    private static final GmsLogger zzaoj = new GmsLogger("MLTaskManager", "");
    @GuardedBy("MLTaskManager.class")
    private static zznb zzaok;
    /* access modifiers changed from: private */
    public final zzni zzaol;

    private zznb(FirebaseApp firebaseApp) {
        this.zzaol = zzni.zzb(firebaseApp);
    }

    public static synchronized zznb zza(FirebaseApp firebaseApp) {
        zznb zznb;
        synchronized (zznb.class) {
            if (zzaok == null) {
                zzaok = new zznb(firebaseApp);
            }
            zznb = zzaok;
        }
        return zznb;
    }

    public final synchronized <T, S extends zzna> Task<T> zza(@NonNull zzmx<T, S> zzmx, @NonNull S s) {
        Preconditions.checkNotNull(zzmx, "Operation can not be null");
        Preconditions.checkNotNull(s, "Input can not be null");
        zzaoj.d("MLTaskManager", "Execute task");
        return zzmy.zzki().zza(new zznd(this, zzmx.zzkh(), zzmx, s));
    }

    public final synchronized <TResult> Task<TResult> zza(@NonNull zznh zznh, @NonNull Callable<TResult> callable) {
        Preconditions.checkNotNull(callable, "Operation can not be null");
        Preconditions.checkNotNull(zznh, "Model resource can not be null");
        zzaoj.d("MLTaskManager", "Execute task");
        return zzmy.zzki().zza(new zznc(this, zznh, callable));
    }

    public final <T, S extends zzna> void zza(zzmx<T, S> zzmx) {
        zznh zzkh = zzmx.zzkh();
        if (zzkh != null) {
            this.zzaol.zza(zzkh);
        }
    }

    public final <T, S extends zzna> void zzb(zzmx<T, S> zzmx) {
        zznh zzkh = zzmx.zzkh();
        if (zzkh != null) {
            this.zzaol.zzd(zzkh);
        }
    }
}
