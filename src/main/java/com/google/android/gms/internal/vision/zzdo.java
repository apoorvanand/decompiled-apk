package com.google.android.gms.internal.vision;

public final class zzdo extends zzjn<zzdo> {
    public Float zzpc = null;
    public Float zzpd = null;
    public Float zzpe = null;
    public Float zzpf = null;
    public Float zzpg = null;
    public Float zzph = null;

    public zzdo() {
        this.b = -1;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a = super.a();
        Float f = this.zzpc;
        if (f != null) {
            f.floatValue();
            a += zzjl.zzav(1) + 4;
        }
        Float f2 = this.zzpd;
        if (f2 != null) {
            f2.floatValue();
            a += zzjl.zzav(2) + 4;
        }
        Float f3 = this.zzpe;
        if (f3 != null) {
            f3.floatValue();
            a += zzjl.zzav(3) + 4;
        }
        Float f4 = this.zzpf;
        if (f4 != null) {
            f4.floatValue();
            a += zzjl.zzav(4) + 4;
        }
        Float f5 = this.zzpg;
        if (f5 != null) {
            f5.floatValue();
            a += zzjl.zzav(5) + 4;
        }
        Float f6 = this.zzph;
        if (f6 == null) {
            return a;
        }
        f6.floatValue();
        return a + zzjl.zzav(6) + 4;
    }

    public final /* synthetic */ zzjt zza(zzjk zzjk) {
        while (true) {
            int zzdq = zzjk.zzdq();
            if (zzdq == 0) {
                return this;
            }
            if (zzdq == 13) {
                this.zzpc = Float.valueOf(Float.intBitsToFloat(zzjk.zzdv()));
            } else if (zzdq == 21) {
                this.zzpd = Float.valueOf(Float.intBitsToFloat(zzjk.zzdv()));
            } else if (zzdq == 29) {
                this.zzpe = Float.valueOf(Float.intBitsToFloat(zzjk.zzdv()));
            } else if (zzdq == 37) {
                this.zzpf = Float.valueOf(Float.intBitsToFloat(zzjk.zzdv()));
            } else if (zzdq == 45) {
                this.zzpg = Float.valueOf(Float.intBitsToFloat(zzjk.zzdv()));
            } else if (zzdq == 53) {
                this.zzph = Float.valueOf(Float.intBitsToFloat(zzjk.zzdv()));
            } else if (!super.a(zzjk, zzdq)) {
                return this;
            }
        }
    }

    public final void zza(zzjl zzjl) {
        Float f = this.zzpc;
        if (f != null) {
            zzjl.zza(1, f.floatValue());
        }
        Float f2 = this.zzpd;
        if (f2 != null) {
            zzjl.zza(2, f2.floatValue());
        }
        Float f3 = this.zzpe;
        if (f3 != null) {
            zzjl.zza(3, f3.floatValue());
        }
        Float f4 = this.zzpf;
        if (f4 != null) {
            zzjl.zza(4, f4.floatValue());
        }
        Float f5 = this.zzpg;
        if (f5 != null) {
            zzjl.zza(5, f5.floatValue());
        }
        Float f6 = this.zzph;
        if (f6 != null) {
            zzjl.zza(6, f6.floatValue());
        }
        super.zza(zzjl);
    }
}
