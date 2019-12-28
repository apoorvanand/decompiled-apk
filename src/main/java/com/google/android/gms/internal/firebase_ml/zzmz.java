package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.Callable;

final class zzmz implements Runnable {
    private final /* synthetic */ Callable val$callable;
    private final /* synthetic */ TaskCompletionSource zzaoi;

    zzmz(zzmy zzmy, Callable callable, TaskCompletionSource taskCompletionSource) {
        this.val$callable = callable;
        this.zzaoi = taskCompletionSource;
    }

    public final void run() {
        zzmy.zza(this.val$callable, this.zzaoi);
    }
}
