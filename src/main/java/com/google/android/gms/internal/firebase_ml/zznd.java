package com.google.android.gms.internal.firebase_ml;

import java.util.concurrent.Callable;

final class zznd implements Callable<T> {
    private final /* synthetic */ zznh zzaom;
    private final /* synthetic */ zznb zzaon;
    private final /* synthetic */ zzmx zzaoo;
    private final /* synthetic */ zzna zzaop;

    zznd(zznb zznb, zznh zznh, zzmx zzmx, zzna zzna) {
        this.zzaon = zznb;
        this.zzaom = zznh;
        this.zzaoo = zzmx;
        this.zzaop = zzna;
    }

    public final T call() {
        if (this.zzaom != null) {
            this.zzaon.zzaol.a(this.zzaom);
        }
        return this.zzaoo.zza(this.zzaop);
    }
}
