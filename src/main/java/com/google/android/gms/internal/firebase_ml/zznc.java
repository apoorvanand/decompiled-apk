package com.google.android.gms.internal.firebase_ml;

import java.util.concurrent.Callable;

final class zznc implements Callable<TResult> {
    private final /* synthetic */ Callable val$callable;
    private final /* synthetic */ zznh zzaom;
    private final /* synthetic */ zznb zzaon;

    zznc(zznb zznb, zznh zznh, Callable callable) {
        this.zzaon = zznb;
        this.zzaom = zznh;
        this.val$callable = callable;
    }

    public final TResult call() {
        this.zzaon.zzaol.a(this.zzaom);
        return this.val$callable.call();
    }
}
