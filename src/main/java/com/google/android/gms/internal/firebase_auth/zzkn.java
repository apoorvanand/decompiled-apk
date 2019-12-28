package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzhs;
import java.util.Arrays;

public final class zzkn {
    private static final zzkn zzael = new zzkn(0, new int[0], new Object[0], false);
    private int count;
    private int zzaak;
    private Object[] zzacy;
    private int[] zzaem;
    private boolean zzvo;

    private zzkn() {
        this(0, new int[8], new Object[8], true);
    }

    private zzkn(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zzaak = -1;
        this.count = i;
        this.zzaem = iArr;
        this.zzacy = objArr;
        this.zzvo = z;
    }

    static zzkn a() {
        return new zzkn();
    }

    static zzkn a(zzkn zzkn, zzkn zzkn2) {
        int i = zzkn.count + zzkn2.count;
        int[] copyOf = Arrays.copyOf(zzkn.zzaem, i);
        System.arraycopy(zzkn2.zzaem, 0, copyOf, zzkn.count, zzkn2.count);
        Object[] copyOf2 = Arrays.copyOf(zzkn.zzacy, i);
        System.arraycopy(zzkn2.zzacy, 0, copyOf2, zzkn.count, zzkn2.count);
        return new zzkn(i, copyOf, copyOf2, true);
    }

    private static void zzb(int i, Object obj, zzlh zzlh) {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 != 5) {
            switch (i3) {
                case 0:
                    zzlh.zzi(i2, ((Long) obj).longValue());
                    return;
                case 1:
                    zzlh.zzc(i2, ((Long) obj).longValue());
                    return;
                case 2:
                    zzlh.zza(i2, (zzgf) obj);
                    return;
                case 3:
                    if (zzlh.zzhl() == zzhs.zzd.zzaay) {
                        zzlh.zzat(i2);
                        ((zzkn) obj).zzb(zzlh);
                        zzlh.zzau(i2);
                        return;
                    }
                    zzlh.zzau(i2);
                    ((zzkn) obj).zzb(zzlh);
                    zzlh.zzat(i2);
                    return;
                default:
                    throw new RuntimeException(zzic.f());
            }
        } else {
            zzlh.zzi(i2, ((Integer) obj).intValue());
        }
    }

    public static zzkn zzko() {
        return zzael;
    }

    /* access modifiers changed from: package-private */
    public final void a(int i, Object obj) {
        if (this.zzvo) {
            int i2 = this.count;
            if (i2 == this.zzaem.length) {
                int i3 = this.count + (i2 < 4 ? 8 : i2 >> 1);
                this.zzaem = Arrays.copyOf(this.zzaem, i3);
                this.zzacy = Arrays.copyOf(this.zzacy, i3);
            }
            int[] iArr = this.zzaem;
            int i4 = this.count;
            iArr[i4] = i;
            this.zzacy[i4] = obj;
            this.count = i4 + 1;
            return;
        }
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public final void a(zzlh zzlh) {
        if (zzlh.zzhl() == zzhs.zzd.zzaaz) {
            for (int i = this.count - 1; i >= 0; i--) {
                zzlh.zza(this.zzaem[i] >>> 3, this.zzacy[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.count; i2++) {
            zzlh.zza(this.zzaem[i2] >>> 3, this.zzacy[i2]);
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.count; i2++) {
            zzjd.a(sb, i, String.valueOf(this.zzaem[i2] >>> 3), this.zzacy[i2]);
        }
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzkn)) {
            return false;
        }
        zzkn zzkn = (zzkn) obj;
        int i = this.count;
        if (i == zzkn.count) {
            int[] iArr = this.zzaem;
            int[] iArr2 = zzkn.zzaem;
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
                Object[] objArr = this.zzacy;
                Object[] objArr2 = zzkn.zzacy;
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
        int[] iArr = this.zzaem;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 + i4) * 31;
        Object[] objArr = this.zzacy;
        int i7 = this.count;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
    }

    public final void zzb(zzlh zzlh) {
        if (this.count != 0) {
            if (zzlh.zzhl() == zzhs.zzd.zzaay) {
                for (int i = 0; i < this.count; i++) {
                    zzb(this.zzaem[i], this.zzacy[i], zzlh);
                }
                return;
            }
            for (int i2 = this.count - 1; i2 >= 0; i2--) {
                zzb(this.zzaem[i2], this.zzacy[i2], zzlh);
            }
        }
    }

    public final void zzfy() {
        this.zzvo = false;
    }

    public final int zzik() {
        int zzm;
        int i = this.zzaak;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.count; i3++) {
            int i4 = this.zzaem[i3];
            int i5 = i4 >>> 3;
            int i6 = i4 & 7;
            if (i6 != 5) {
                switch (i6) {
                    case 0:
                        zzm = zzha.zze(i5, ((Long) this.zzacy[i3]).longValue());
                        break;
                    case 1:
                        zzm = zzha.zzg(i5, ((Long) this.zzacy[i3]).longValue());
                        break;
                    case 2:
                        zzm = zzha.zzc(i5, (zzgf) this.zzacy[i3]);
                        break;
                    case 3:
                        zzm = (zzha.zzak(i5) << 1) + ((zzkn) this.zzacy[i3]).zzik();
                        break;
                    default:
                        throw new IllegalStateException(zzic.f());
                }
            } else {
                zzm = zzha.zzm(i5, ((Integer) this.zzacy[i3]).intValue());
            }
            i2 += zzm;
        }
        this.zzaak = i2;
        return i2;
    }

    public final int zzkq() {
        int i = this.zzaak;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.count; i3++) {
            i2 += zzha.zzd(this.zzaem[i3] >>> 3, (zzgf) this.zzacy[i3]);
        }
        this.zzaak = i2;
        return i2;
    }
}
