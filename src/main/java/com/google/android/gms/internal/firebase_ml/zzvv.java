package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.internal.firebase_ml.zztc;
import java.util.Arrays;

public final class zzvv {
    private static final zzvv zzbqh = new zzvv(0, new int[0], new Object[0], false);
    private int count;
    private boolean zzbhx;
    private int zzbmd;
    private Object[] zzbot;
    private int[] zzbqi;

    private zzvv() {
        this(0, new int[8], new Object[8], true);
    }

    private zzvv(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zzbmd = -1;
        this.count = i;
        this.zzbqi = iArr;
        this.zzbot = objArr;
        this.zzbhx = z;
    }

    static zzvv a() {
        return new zzvv();
    }

    static zzvv a(zzvv zzvv, zzvv zzvv2) {
        int i = zzvv.count + zzvv2.count;
        int[] copyOf = Arrays.copyOf(zzvv.zzbqi, i);
        System.arraycopy(zzvv2.zzbqi, 0, copyOf, zzvv.count, zzvv2.count);
        Object[] copyOf2 = Arrays.copyOf(zzvv.zzbot, i);
        System.arraycopy(zzvv2.zzbot, 0, copyOf2, zzvv.count, zzvv2.count);
        return new zzvv(i, copyOf, copyOf2, true);
    }

    private static void zzb(int i, Object obj, zzwp zzwp) {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 != 5) {
            switch (i3) {
                case 0:
                    zzwp.zzi(i2, ((Long) obj).longValue());
                    return;
                case 1:
                    zzwp.zzc(i2, ((Long) obj).longValue());
                    return;
                case 2:
                    zzwp.zza(i2, (zzru) obj);
                    return;
                case 3:
                    if (zzwp.zzov() == zztc.zzf.zzbmu) {
                        zzwp.zzcv(i2);
                        ((zzvv) obj).zzb(zzwp);
                        zzwp.zzcw(i2);
                        return;
                    }
                    zzwp.zzcw(i2);
                    ((zzvv) obj).zzb(zzwp);
                    zzwp.zzcv(i2);
                    return;
                default:
                    throw new RuntimeException(zztm.d());
            }
        } else {
            zzwp.zzh(i2, ((Integer) obj).intValue());
        }
    }

    public static zzvv zzru() {
        return zzbqh;
    }

    /* access modifiers changed from: package-private */
    public final void a(int i, Object obj) {
        if (this.zzbhx) {
            int i2 = this.count;
            if (i2 == this.zzbqi.length) {
                int i3 = this.count + (i2 < 4 ? 8 : i2 >> 1);
                this.zzbqi = Arrays.copyOf(this.zzbqi, i3);
                this.zzbot = Arrays.copyOf(this.zzbot, i3);
            }
            int[] iArr = this.zzbqi;
            int i4 = this.count;
            iArr[i4] = i;
            this.zzbot[i4] = obj;
            this.count = i4 + 1;
            return;
        }
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public final void a(zzwp zzwp) {
        if (zzwp.zzov() == zztc.zzf.zzbmv) {
            for (int i = this.count - 1; i >= 0; i--) {
                zzwp.zza(this.zzbqi[i] >>> 3, this.zzbot[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.count; i2++) {
            zzwp.zza(this.zzbqi[i2] >>> 3, this.zzbot[i2]);
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.count; i2++) {
            zzup.a(sb, i, String.valueOf(this.zzbqi[i2] >>> 3), this.zzbot[i2]);
        }
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzvv)) {
            return false;
        }
        zzvv zzvv = (zzvv) obj;
        int i = this.count;
        if (i == zzvv.count) {
            int[] iArr = this.zzbqi;
            int[] iArr2 = zzvv.zzbqi;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    z = true;
                    break;
                } else if (iArr[i2] != iArr2[i2]) {
                    z = false;
                    break;
                } else {
                    i2++;
                }
            }
            if (z) {
                Object[] objArr = this.zzbot;
                Object[] objArr2 = zzvv.zzbot;
                int i3 = this.count;
                int i4 = 0;
                while (true) {
                    if (i4 >= i3) {
                        z2 = true;
                        break;
                    } else if (!objArr[i4].equals(objArr2[i4])) {
                        z2 = false;
                        break;
                    } else {
                        i4++;
                    }
                }
                return z2;
            }
        }
    }

    public final int hashCode() {
        int i = this.count;
        int i2 = (i + 527) * 31;
        int[] iArr = this.zzbqi;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 + i4) * 31;
        Object[] objArr = this.zzbot;
        int i7 = this.count;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
    }

    public final void zzb(zzwp zzwp) {
        if (this.count != 0) {
            if (zzwp.zzov() == zztc.zzf.zzbmu) {
                for (int i = 0; i < this.count; i++) {
                    zzb(this.zzbqi[i], this.zzbot[i], zzwp);
                }
                return;
            }
            for (int i2 = this.count - 1; i2 >= 0; i2--) {
                zzb(this.zzbqi[i2], this.zzbot[i2], zzwp);
            }
        }
    }

    public final void zzoh() {
        this.zzbhx = false;
    }

    public final int zzpe() {
        int zzl;
        int i = this.zzbmd;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.count; i3++) {
            int i4 = this.zzbqi[i3];
            int i5 = i4 >>> 3;
            int i6 = i4 & 7;
            if (i6 != 5) {
                switch (i6) {
                    case 0:
                        zzl = zzsj.zze(i5, ((Long) this.zzbot[i3]).longValue());
                        break;
                    case 1:
                        zzl = zzsj.zzg(i5, ((Long) this.zzbot[i3]).longValue());
                        break;
                    case 2:
                        zzl = zzsj.zzc(i5, (zzru) this.zzbot[i3]);
                        break;
                    case 3:
                        zzl = (zzsj.zzcl(i5) << 1) + ((zzvv) this.zzbot[i3]).zzpe();
                        break;
                    default:
                        throw new IllegalStateException(zztm.d());
                }
            } else {
                zzl = zzsj.zzl(i5, ((Integer) this.zzbot[i3]).intValue());
            }
            i2 += zzl;
        }
        this.zzbmd = i2;
        return i2;
    }

    public final int zzrw() {
        int i = this.zzbmd;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.count; i3++) {
            i2 += zzsj.zzd(this.zzbqi[i3] >>> 3, (zzru) this.zzbot[i3]);
        }
        this.zzbmd = i2;
        return i2;
    }
}
