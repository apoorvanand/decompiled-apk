package com.google.android.gms.measurement.internal;

abstract class zzg extends zzd {
    private boolean zzdh;

    zzg(zzfj zzfj) {
        super(zzfj);
        this.b.a(this);
    }

    /* access modifiers changed from: protected */
    public abstract boolean a();

    /* access modifiers changed from: protected */
    public void b() {
    }

    /* access modifiers changed from: package-private */
    public final boolean i() {
        return this.zzdh;
    }

    public final void initialize() {
        if (this.zzdh) {
            throw new IllegalStateException("Can't initialize twice");
        } else if (!a()) {
            this.b.f();
            this.zzdh = true;
        }
    }

    /* access modifiers changed from: protected */
    public final void j() {
        if (!i()) {
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
