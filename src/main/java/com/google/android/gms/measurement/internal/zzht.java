package com.google.android.gms.measurement.internal;

import android.os.Bundle;

final class zzht implements Runnable {
    private final /* synthetic */ zzhq zzqz;
    private final /* synthetic */ boolean zzra;
    private final /* synthetic */ zzhr zzrb;
    private final /* synthetic */ zzhr zzrc;

    zzht(zzhq zzhq, boolean z, zzhr zzhr, zzhr zzhr2) {
        this.zzqz = zzhq;
        this.zzra = z;
        this.zzrb = zzhr;
        this.zzrc = zzhr2;
    }

    public final void run() {
        boolean z;
        boolean z2 = false;
        if (this.zzqz.zzad().o(this.zzqz.zzr().c())) {
            z = this.zzra && this.zzqz.a != null;
            if (z) {
                zzhq zzhq = this.zzqz;
                zzhq.zza(zzhq.a, true);
            }
        } else {
            if (this.zzra && this.zzqz.a != null) {
                zzhq zzhq2 = this.zzqz;
                zzhq2.zza(zzhq2.a, true);
            }
            z = false;
        }
        zzhr zzhr = this.zzrb;
        if (zzhr == null || zzhr.zzqw != this.zzrc.zzqw || !zzjs.d(this.zzrb.zzqv, this.zzrc.zzqv) || !zzjs.d(this.zzrb.zzqu, this.zzrc.zzqu)) {
            z2 = true;
        }
        if (z2) {
            Bundle bundle = new Bundle();
            zzhq.zza(this.zzrc, bundle, true);
            zzhr zzhr2 = this.zzrb;
            if (zzhr2 != null) {
                if (zzhr2.zzqu != null) {
                    bundle.putString("_pn", this.zzrb.zzqu);
                }
                bundle.putString("_pc", this.zzrb.zzqv);
                bundle.putLong("_pi", this.zzrb.zzqw);
            }
            if (this.zzqz.zzad().o(this.zzqz.zzr().c()) && z) {
                long e = this.zzqz.zzv().e();
                if (e > 0) {
                    this.zzqz.zzz().a(bundle, e);
                }
            }
            this.zzqz.zzq().a("auto", "_vs", bundle);
        }
        zzhq zzhq3 = this.zzqz;
        zzhq3.a = this.zzrc;
        zzhq3.zzs().a(this.zzrc);
    }
}
