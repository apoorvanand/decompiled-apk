package com.google.android.gms.internal.vision;

public final class zzdj extends zzjn<zzdj> {
    private String value = null;
    private Integer zzof;
    private Integer zzog;

    public zzdj() {
        this.b = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzc */
    public final zzdj zza(zzjk zzjk) {
        int i;
        int zzdt;
        while (true) {
            int zzdq = zzjk.zzdq();
            if (zzdq == 0) {
                return this;
            }
            if (zzdq == 8) {
                i = zzjk.getPosition();
                this.zzof = Integer.valueOf(zzeb.zzx(zzjk.zzdt()));
            } else if (zzdq == 16) {
                i = zzjk.getPosition();
                try {
                    zzdt = zzjk.zzdt();
                    if (zzdt <= 0 || zzdt > 12) {
                        StringBuilder sb = new StringBuilder(50);
                        sb.append(zzdt);
                        sb.append(" is not a valid enum BarcodeValueFormat");
                    } else {
                        this.zzog = Integer.valueOf(zzdt);
                    }
                } catch (IllegalArgumentException unused) {
                    zzjk.zzbt(i);
                    a(zzjk, zzdq);
                }
            } else if (zzdq == 26) {
                this.value = zzjk.readString();
            } else if (!super.a(zzjk, zzdq)) {
                return this;
            }
        }
        StringBuilder sb2 = new StringBuilder(50);
        sb2.append(zzdt);
        sb2.append(" is not a valid enum BarcodeValueFormat");
        throw new IllegalArgumentException(sb2.toString());
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a = super.a();
        Integer num = this.zzof;
        if (num != null) {
            a += zzjl.zzi(1, num.intValue());
        }
        Integer num2 = this.zzog;
        if (num2 != null) {
            a += zzjl.zzi(2, num2.intValue());
        }
        String str = this.value;
        return str != null ? a + zzjl.zzb(3, str) : a;
    }

    public final void zza(zzjl zzjl) {
        Integer num = this.zzof;
        if (num != null) {
            zzjl.zze(1, num.intValue());
        }
        Integer num2 = this.zzog;
        if (num2 != null) {
            zzjl.zze(2, num2.intValue());
        }
        String str = this.value;
        if (str != null) {
            zzjl.zza(3, str);
        }
        super.zza(zzjl);
    }
}
