package com.google.android.gms.measurement.internal;

abstract class zzge extends zzgf {
    private boolean zzdh;

    zzge(zzfj zzfj) {
        super(zzfj);
        this.b.a(this);
    }

    /* access modifiers changed from: protected */
    public abstract boolean a();

    /* access modifiers changed from: protected */
    public void b() {
    }

    public final void initialize() {
        if (this.zzdh) {
            throw new IllegalStateException("Can't initialize twice");
        } else if (!a()) {
            this.b.f();
            this.zzdh = true;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean k() {
        return this.zzdh;
    }

    /* access modifiers changed from: protected */
    public final void l() {
        if (!k()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzbj() {
        if (!this.zzdh) {
            b();
            this.b.f();
            this.zzdh = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }
}
