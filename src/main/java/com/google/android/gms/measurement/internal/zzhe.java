package com.google.android.gms.measurement.internal;

final class zzhe implements Runnable {
    private final /* synthetic */ boolean zzaz;
    private final /* synthetic */ zzgp zzpt;

    zzhe(zzgp zzgp, boolean z) {
        this.zzpt = zzgp;
        this.zzaz = z;
    }

    public final void run() {
        boolean isEnabled = this.zzpt.b.isEnabled();
        boolean zzib = this.zzpt.b.zzib();
        this.zzpt.b.a(this.zzaz);
        if (zzib == this.zzaz) {
            this.zzpt.b.zzab().zzgs().zza("Default data collection state already set to", Boolean.valueOf(this.zzaz));
        }
        if (this.zzpt.b.isEnabled() == isEnabled || this.zzpt.b.isEnabled() != this.zzpt.b.zzib()) {
            this.zzpt.b.zzab().zzgp().zza("Default data collection is different than actual status", Boolean.valueOf(this.zzaz), Boolean.valueOf(isEnabled));
        }
        this.zzpt.zzil();
    }
}
