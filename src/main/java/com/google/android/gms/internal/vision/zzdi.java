package com.google.android.gms.internal.vision;

public final class zzdi extends zzjn<zzdi> {
    private int[] zzoe = zzjw.zzzb;

    public zzdi() {
        this.b = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public final zzdi zza(zzjk zzjk) {
        while (true) {
            int zzdq = zzjk.zzdq();
            if (zzdq == 0) {
                return this;
            }
            if (zzdq == 8) {
                int zzb = zzjw.zzb(zzjk, 8);
                int[] iArr = new int[zzb];
                int i = 0;
                for (int i2 = 0; i2 < zzb; i2++) {
                    if (i2 != 0) {
                        zzjk.zzdq();
                    }
                    int position = zzjk.getPosition();
                    try {
                        iArr[i] = zzeb.zzx(zzjk.zzdt());
                        i++;
                    } catch (IllegalArgumentException unused) {
                        zzjk.zzbt(position);
                        a(zzjk, zzdq);
                    }
                }
                if (i != 0) {
                    int[] iArr2 = this.zzoe;
                    int length = iArr2 == null ? 0 : iArr2.length;
                    if (length == 0 && i == iArr.length) {
                        this.zzoe = iArr;
                    } else {
                        int[] iArr3 = new int[(length + i)];
                        if (length != 0) {
                            System.arraycopy(this.zzoe, 0, iArr3, 0, length);
                        }
                        System.arraycopy(iArr, 0, iArr3, length, i);
                        this.zzoe = iArr3;
                    }
                }
            } else if (zzdq == 10) {
                int zzan = zzjk.zzan(zzjk.zzdt());
                int position2 = zzjk.getPosition();
                int i3 = 0;
                while (zzjk.zzhq() > 0) {
                    try {
                        zzeb.zzx(zzjk.zzdt());
                        i3++;
                    } catch (IllegalArgumentException unused2) {
                    }
                }
                if (i3 != 0) {
                    zzjk.zzbt(position2);
                    int[] iArr4 = this.zzoe;
                    int length2 = iArr4 == null ? 0 : iArr4.length;
                    int[] iArr5 = new int[(i3 + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.zzoe, 0, iArr5, 0, length2);
                    }
                    while (zzjk.zzhq() > 0) {
                        int position3 = zzjk.getPosition();
                        try {
                            iArr5[length2] = zzeb.zzx(zzjk.zzdt());
                            length2++;
                        } catch (IllegalArgumentException unused3) {
                            zzjk.zzbt(position3);
                            a(zzjk, 8);
                        }
                    }
                    this.zzoe = iArr5;
                }
                zzjk.zzao(zzan);
            } else if (!super.a(zzjk, zzdq)) {
                return this;
            }
        }
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a = super.a();
        int[] iArr = this.zzoe;
        if (iArr == null || iArr.length <= 0) {
            return a;
        }
        int i = 0;
        int i2 = 0;
        while (true) {
            int[] iArr2 = this.zzoe;
            if (i >= iArr2.length) {
                return a + i2 + (iArr2.length * 1);
            }
            i2 += zzjl.zzaw(iArr2[i]);
            i++;
        }
    }

    public final void zza(zzjl zzjl) {
        int[] iArr = this.zzoe;
        if (iArr != null && iArr.length > 0) {
            int i = 0;
            while (true) {
                int[] iArr2 = this.zzoe;
                if (i >= iArr2.length) {
                    break;
                }
                zzjl.zze(1, iArr2[i]);
                i++;
            }
        }
        super.zza(zzjl);
    }
}
