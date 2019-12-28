package com.google.android.gms.measurement.internal;

abstract class zzjh extends zzje {
    private boolean zzdh;

    zzjh(zzjg zzjg) {
        super(zzjg);
        this.a.a(this);
    }

    /* access modifiers changed from: protected */
    public abstract boolean a();

    /* access modifiers changed from: package-private */
    public final boolean b() {
        return this.zzdh;
    }

    /* access modifiers changed from: protected */
    public final void c() {
        if (!b()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void initialize() {
        if (!this.zzdh) {
            a();
            this.a.e();
            this.zzdh = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }
}
