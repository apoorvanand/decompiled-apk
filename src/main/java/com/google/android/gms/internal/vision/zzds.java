package com.google.android.gms.internal.vision;

public final class zzds extends zzjn<zzds> {
    private static volatile zzds[] zzpu;
    public Integer zzpv = null;
    public Integer zzpw = null;

    public zzds() {
        this.b = -1;
    }

    public static zzds[] zzcc() {
        if (zzpu == null) {
            synchronized (zzjr.zzado) {
                if (zzpu == null) {
                    zzpu = new zzds[0];
                }
            }
        }
        return zzpu;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a = super.a();
        Integer num = this.zzpv;
        if (num != null) {
            a += zzjl.zzi(1, num.intValue());
        }
        Integer num2 = this.zzpw;
        return num2 != null ? a + zzjl.zzi(2, num2.intValue()) : a;
    }

    public final /* synthetic */ zzjt zza(zzjk zzjk) {
        while (true) {
            int zzdq = zzjk.zzdq();
            if (zzdq == 0) {
                return this;
            }
            if (zzdq == 8) {
                this.zzpv = Integer.valueOf(zzjk.zzdt());
            } else if (zzdq == 16) {
                this.zzpw = Integer.valueOf(zzjk.zzdt());
            } else if (!super.a(zzjk, zzdq)) {
                return this;
            }
        }
    }

    public final void zza(zzjl zzjl) {
        Integer num = this.zzpv;
        if (num != null) {
            zzjl.zze(1, num.intValue());
        }
        Integer num2 = this.zzpw;
        if (num2 != null) {
            zzjl.zze(2, num2.intValue());
        }
        super.zza(zzjl);
    }
}
