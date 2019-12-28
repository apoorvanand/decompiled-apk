package com.google.android.gms.internal.vision;

import java.util.List;

final class zzfc implements zzhv {
    private int tag;
    private int zzru;
    private final zzez zzsp;
    private int zzsq = 0;

    private zzfc(zzez zzez) {
        this.zzsp = (zzez) zzga.a(zzez, "input");
        this.zzsp.c = this;
    }

    public static zzfc zza(zzez zzez) {
        return zzez.c != null ? zzez.c : new zzfc(zzez);
    }

    private final Object zza(zzjd zzjd, Class<?> cls, zzfk zzfk) {
        switch (zzfd.a[zzjd.ordinal()]) {
            case 1:
                return Boolean.valueOf(zzcu());
            case 2:
                return zzcw();
            case 3:
                return Double.valueOf(readDouble());
            case 4:
                return Integer.valueOf(zzcy());
            case 5:
                return Integer.valueOf(zzct());
            case 6:
                return Long.valueOf(zzcs());
            case 7:
                return Float.valueOf(readFloat());
            case 8:
                return Integer.valueOf(zzcr());
            case 9:
                return Long.valueOf(zzcq());
            case 10:
                return zza(cls, zzfk);
            case 11:
                return Integer.valueOf(zzcz());
            case 12:
                return Long.valueOf(zzda());
            case 13:
                return Integer.valueOf(zzdb());
            case 14:
                return Long.valueOf(zzdc());
            case 15:
                return zzcv();
            case 16:
                return Integer.valueOf(zzcx());
            case 17:
                return Long.valueOf(zzcp());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private final void zza(List<String> list, boolean z) {
        int zzdq;
        int zzdq2;
        if ((this.tag & 7) != 2) {
            throw zzgf.f();
        } else if (!(list instanceof zzgo) || z) {
            do {
                list.add(z ? zzcv() : readString());
                if (!this.zzsp.zzcm()) {
                    zzdq = this.zzsp.zzdq();
                } else {
                    return;
                }
            } while (zzdq == this.tag);
            this.zzsq = zzdq;
        } else {
            zzgo zzgo = (zzgo) list;
            do {
                zzgo.zzc(zzcw());
                if (!this.zzsp.zzcm()) {
                    zzdq2 = this.zzsp.zzdq();
                } else {
                    return;
                }
            } while (zzdq2 == this.tag);
            this.zzsq = zzdq2;
        }
    }

    private final void zzab(int i) {
        if ((this.tag & 7) != i) {
            throw zzgf.f();
        }
    }

    private static void zzac(int i) {
        if ((i & 7) != 0) {
            throw zzgf.h();
        }
    }

    private static void zzad(int i) {
        if ((i & 3) != 0) {
            throw zzgf.h();
        }
    }

    private final void zzae(int i) {
        if (this.zzsp.zzds() != i) {
            throw zzgf.a();
        }
    }

    private final <T> T zzb(zzhw<T> zzhw, zzfk zzfk) {
        int zzcx = this.zzsp.zzcx();
        if (this.zzsp.a < this.zzsp.b) {
            int zzan = this.zzsp.zzan(zzcx);
            T newInstance = zzhw.newInstance();
            this.zzsp.a++;
            zzhw.zza(newInstance, this, zzfk);
            zzhw.zze(newInstance);
            this.zzsp.zzak(0);
            zzez zzez = this.zzsp;
            zzez.a--;
            this.zzsp.zzao(zzan);
            return newInstance;
        }
        throw zzgf.g();
    }

    private final <T> T zzd(zzhw<T> zzhw, zzfk zzfk) {
        int i = this.zzru;
        this.zzru = ((this.tag >>> 3) << 3) | 4;
        try {
            T newInstance = zzhw.newInstance();
            zzhw.zza(newInstance, this, zzfk);
            zzhw.zze(newInstance);
            if (this.tag == this.zzru) {
                return newInstance;
            }
            throw zzgf.h();
        } finally {
            this.zzru = i;
        }
    }

    public final int getTag() {
        return this.tag;
    }

    public final double readDouble() {
        zzab(1);
        return this.zzsp.readDouble();
    }

    public final float readFloat() {
        zzab(5);
        return this.zzsp.readFloat();
    }

    public final String readString() {
        zzab(2);
        return this.zzsp.readString();
    }

    public final void readStringList(List<String> list) {
        zza(list, false);
    }

    public final <T> T zza(zzhw<T> zzhw, zzfk zzfk) {
        zzab(2);
        return zzb(zzhw, zzfk);
    }

    public final <T> T zza(Class<T> cls, zzfk zzfk) {
        zzab(2);
        return zzb(zzhs.zzgl().zzf(cls), zzfk);
    }

    public final void zza(List<Double> list) {
        int zzdq;
        int zzdq2;
        if (list instanceof zzfh) {
            zzfh zzfh = (zzfh) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int zzcx = this.zzsp.zzcx();
                    zzac(zzcx);
                    int zzds = this.zzsp.zzds() + zzcx;
                    do {
                        zzfh.zzc(this.zzsp.readDouble());
                    } while (this.zzsp.zzds() < zzds);
                    return;
                default:
                    throw zzgf.f();
            }
            do {
                zzfh.zzc(this.zzsp.readDouble());
                if (!this.zzsp.zzcm()) {
                    zzdq2 = this.zzsp.zzdq();
                } else {
                    return;
                }
            } while (zzdq2 == this.tag);
            this.zzsq = zzdq2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int zzcx2 = this.zzsp.zzcx();
                zzac(zzcx2);
                int zzds2 = this.zzsp.zzds() + zzcx2;
                do {
                    list.add(Double.valueOf(this.zzsp.readDouble()));
                } while (this.zzsp.zzds() < zzds2);
                return;
            default:
                throw zzgf.f();
        }
        do {
            list.add(Double.valueOf(this.zzsp.readDouble()));
            if (!this.zzsp.zzcm()) {
                zzdq = this.zzsp.zzdq();
            } else {
                return;
            }
        } while (zzdq == this.tag);
        this.zzsq = zzdq;
    }

    public final <T> void zza(List<T> list, zzhw<T> zzhw, zzfk zzfk) {
        int zzdq;
        int i = this.tag;
        if ((i & 7) == 2) {
            do {
                list.add(zzb(zzhw, zzfk));
                if (!this.zzsp.zzcm() && this.zzsq == 0) {
                    zzdq = this.zzsp.zzdq();
                } else {
                    return;
                }
            } while (zzdq == i);
            this.zzsq = zzdq;
            return;
        }
        throw zzgf.f();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0051, code lost:
        if (zzco() != false) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005b, code lost:
        throw new com.google.android.gms.internal.vision.zzgf("Unable to parse map entry.");
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x004d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <K, V> void zza(java.util.Map<K, V> r6, com.google.android.gms.internal.vision.zzgy<K, V> r7, com.google.android.gms.internal.vision.zzfk r8) {
        /*
            r5 = this;
            r0 = 2
            r5.zzab(r0)
            com.google.android.gms.internal.vision.zzez r0 = r5.zzsp
            int r0 = r0.zzcx()
            com.google.android.gms.internal.vision.zzez r1 = r5.zzsp
            int r0 = r1.zzan(r0)
            K r1 = r7.zzyw
            V r2 = r7.zzgq
        L_0x0014:
            int r3 = r5.zzcn()     // Catch:{ all -> 0x0065 }
            r4 = 2147483647(0x7fffffff, float:NaN)
            if (r3 == r4) goto L_0x005c
            com.google.android.gms.internal.vision.zzez r4 = r5.zzsp     // Catch:{ all -> 0x0065 }
            boolean r4 = r4.zzcm()     // Catch:{ all -> 0x0065 }
            if (r4 != 0) goto L_0x005c
            switch(r3) {
                case 1: goto L_0x003a;
                case 2: goto L_0x002d;
                default: goto L_0x0028;
            }
        L_0x0028:
            boolean r3 = r5.zzco()     // Catch:{ zzgg -> 0x004d }
            goto L_0x0042
        L_0x002d:
            com.google.android.gms.internal.vision.zzjd r3 = r7.zzyx     // Catch:{ zzgg -> 0x004d }
            V r4 = r7.zzgq     // Catch:{ zzgg -> 0x004d }
            java.lang.Class r4 = r4.getClass()     // Catch:{ zzgg -> 0x004d }
            java.lang.Object r2 = r5.zza((com.google.android.gms.internal.vision.zzjd) r3, (java.lang.Class<?>) r4, (com.google.android.gms.internal.vision.zzfk) r8)     // Catch:{ zzgg -> 0x004d }
            goto L_0x0014
        L_0x003a:
            com.google.android.gms.internal.vision.zzjd r3 = r7.zzyv     // Catch:{ zzgg -> 0x004d }
            r4 = 0
            java.lang.Object r1 = r5.zza((com.google.android.gms.internal.vision.zzjd) r3, (java.lang.Class<?>) r4, (com.google.android.gms.internal.vision.zzfk) r4)     // Catch:{ zzgg -> 0x004d }
            goto L_0x0014
        L_0x0042:
            if (r3 == 0) goto L_0x0045
            goto L_0x0014
        L_0x0045:
            com.google.android.gms.internal.vision.zzgf r3 = new com.google.android.gms.internal.vision.zzgf     // Catch:{ zzgg -> 0x004d }
            java.lang.String r4 = "Unable to parse map entry."
            r3.<init>(r4)     // Catch:{ zzgg -> 0x004d }
            throw r3     // Catch:{ zzgg -> 0x004d }
        L_0x004d:
            boolean r3 = r5.zzco()     // Catch:{ all -> 0x0065 }
            if (r3 == 0) goto L_0x0054
            goto L_0x0014
        L_0x0054:
            com.google.android.gms.internal.vision.zzgf r6 = new com.google.android.gms.internal.vision.zzgf     // Catch:{ all -> 0x0065 }
            java.lang.String r7 = "Unable to parse map entry."
            r6.<init>(r7)     // Catch:{ all -> 0x0065 }
            throw r6     // Catch:{ all -> 0x0065 }
        L_0x005c:
            r6.put(r1, r2)     // Catch:{ all -> 0x0065 }
            com.google.android.gms.internal.vision.zzez r6 = r5.zzsp
            r6.zzao(r0)
            return
        L_0x0065:
            r6 = move-exception
            com.google.android.gms.internal.vision.zzez r7 = r5.zzsp
            r7.zzao(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzfc.zza(java.util.Map, com.google.android.gms.internal.vision.zzgy, com.google.android.gms.internal.vision.zzfk):void");
    }

    public final <T> T zzb(Class<T> cls, zzfk zzfk) {
        zzab(3);
        return zzd(zzhs.zzgl().zzf(cls), zzfk);
    }

    public final void zzb(List<Float> list) {
        int zzdq;
        int zzdq2;
        if (list instanceof zzfv) {
            zzfv zzfv = (zzfv) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzcx = this.zzsp.zzcx();
                zzad(zzcx);
                int zzds = this.zzsp.zzds() + zzcx;
                do {
                    zzfv.zzh(this.zzsp.readFloat());
                } while (this.zzsp.zzds() < zzds);
            } else if (i == 5) {
                do {
                    zzfv.zzh(this.zzsp.readFloat());
                    if (!this.zzsp.zzcm()) {
                        zzdq2 = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (zzdq2 == this.tag);
                this.zzsq = zzdq2;
            } else {
                throw zzgf.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzcx2 = this.zzsp.zzcx();
                zzad(zzcx2);
                int zzds2 = this.zzsp.zzds() + zzcx2;
                do {
                    list.add(Float.valueOf(this.zzsp.readFloat()));
                } while (this.zzsp.zzds() < zzds2);
            } else if (i2 == 5) {
                do {
                    list.add(Float.valueOf(this.zzsp.readFloat()));
                    if (!this.zzsp.zzcm()) {
                        zzdq = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (zzdq == this.tag);
                this.zzsq = zzdq;
            } else {
                throw zzgf.f();
            }
        }
    }

    public final <T> void zzb(List<T> list, zzhw<T> zzhw, zzfk zzfk) {
        int zzdq;
        int i = this.tag;
        if ((i & 7) == 3) {
            do {
                list.add(zzd(zzhw, zzfk));
                if (!this.zzsp.zzcm() && this.zzsq == 0) {
                    zzdq = this.zzsp.zzdq();
                } else {
                    return;
                }
            } while (zzdq == i);
            this.zzsq = zzdq;
            return;
        }
        throw zzgf.f();
    }

    public final <T> T zzc(zzhw<T> zzhw, zzfk zzfk) {
        zzab(3);
        return zzd(zzhw, zzfk);
    }

    public final void zzc(List<Long> list) {
        int zzdq;
        int zzdq2;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzgt.zzp(this.zzsp.zzcp());
                    if (!this.zzsp.zzcm()) {
                        zzdq2 = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (zzdq2 == this.tag);
                this.zzsq = zzdq2;
            } else if (i == 2) {
                int zzds = this.zzsp.zzds() + this.zzsp.zzcx();
                do {
                    zzgt.zzp(this.zzsp.zzcp());
                } while (this.zzsp.zzds() < zzds);
                zzae(zzds);
            } else {
                throw zzgf.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzsp.zzcp()));
                    if (!this.zzsp.zzcm()) {
                        zzdq = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (zzdq == this.tag);
                this.zzsq = zzdq;
            } else if (i2 == 2) {
                int zzds2 = this.zzsp.zzds() + this.zzsp.zzcx();
                do {
                    list.add(Long.valueOf(this.zzsp.zzcp()));
                } while (this.zzsp.zzds() < zzds2);
                zzae(zzds2);
            } else {
                throw zzgf.f();
            }
        }
    }

    public final int zzcn() {
        int i = this.zzsq;
        if (i != 0) {
            this.tag = i;
            this.zzsq = 0;
        } else {
            this.tag = this.zzsp.zzdq();
        }
        int i2 = this.tag;
        if (i2 == 0 || i2 == this.zzru) {
            return Integer.MAX_VALUE;
        }
        return i2 >>> 3;
    }

    public final boolean zzco() {
        int i;
        if (this.zzsp.zzcm() || (i = this.tag) == this.zzru) {
            return false;
        }
        return this.zzsp.zzal(i);
    }

    public final long zzcp() {
        zzab(0);
        return this.zzsp.zzcp();
    }

    public final long zzcq() {
        zzab(0);
        return this.zzsp.zzcq();
    }

    public final int zzcr() {
        zzab(0);
        return this.zzsp.zzcr();
    }

    public final long zzcs() {
        zzab(1);
        return this.zzsp.zzcs();
    }

    public final int zzct() {
        zzab(5);
        return this.zzsp.zzct();
    }

    public final boolean zzcu() {
        zzab(0);
        return this.zzsp.zzcu();
    }

    public final String zzcv() {
        zzab(2);
        return this.zzsp.zzcv();
    }

    public final zzeo zzcw() {
        zzab(2);
        return this.zzsp.zzcw();
    }

    public final int zzcx() {
        zzab(0);
        return this.zzsp.zzcx();
    }

    public final int zzcy() {
        zzab(0);
        return this.zzsp.zzcy();
    }

    public final int zzcz() {
        zzab(5);
        return this.zzsp.zzcz();
    }

    public final void zzd(List<Long> list) {
        int zzdq;
        int zzdq2;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzgt.zzp(this.zzsp.zzcq());
                    if (!this.zzsp.zzcm()) {
                        zzdq2 = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (zzdq2 == this.tag);
                this.zzsq = zzdq2;
            } else if (i == 2) {
                int zzds = this.zzsp.zzds() + this.zzsp.zzcx();
                do {
                    zzgt.zzp(this.zzsp.zzcq());
                } while (this.zzsp.zzds() < zzds);
                zzae(zzds);
            } else {
                throw zzgf.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzsp.zzcq()));
                    if (!this.zzsp.zzcm()) {
                        zzdq = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (zzdq == this.tag);
                this.zzsq = zzdq;
            } else if (i2 == 2) {
                int zzds2 = this.zzsp.zzds() + this.zzsp.zzcx();
                do {
                    list.add(Long.valueOf(this.zzsp.zzcq()));
                } while (this.zzsp.zzds() < zzds2);
                zzae(zzds2);
            } else {
                throw zzgf.f();
            }
        }
    }

    public final long zzda() {
        zzab(1);
        return this.zzsp.zzda();
    }

    public final int zzdb() {
        zzab(0);
        return this.zzsp.zzdb();
    }

    public final long zzdc() {
        zzab(0);
        return this.zzsp.zzdc();
    }

    public final void zze(List<Integer> list) {
        int zzdq;
        int zzdq2;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzfz.zzbg(this.zzsp.zzcr());
                    if (!this.zzsp.zzcm()) {
                        zzdq2 = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (zzdq2 == this.tag);
                this.zzsq = zzdq2;
            } else if (i == 2) {
                int zzds = this.zzsp.zzds() + this.zzsp.zzcx();
                do {
                    zzfz.zzbg(this.zzsp.zzcr());
                } while (this.zzsp.zzds() < zzds);
                zzae(zzds);
            } else {
                throw zzgf.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzsp.zzcr()));
                    if (!this.zzsp.zzcm()) {
                        zzdq = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (zzdq == this.tag);
                this.zzsq = zzdq;
            } else if (i2 == 2) {
                int zzds2 = this.zzsp.zzds() + this.zzsp.zzcx();
                do {
                    list.add(Integer.valueOf(this.zzsp.zzcr()));
                } while (this.zzsp.zzds() < zzds2);
                zzae(zzds2);
            } else {
                throw zzgf.f();
            }
        }
    }

    public final void zzf(List<Long> list) {
        int zzdq;
        int zzdq2;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int zzcx = this.zzsp.zzcx();
                    zzac(zzcx);
                    int zzds = this.zzsp.zzds() + zzcx;
                    do {
                        zzgt.zzp(this.zzsp.zzcs());
                    } while (this.zzsp.zzds() < zzds);
                    return;
                default:
                    throw zzgf.f();
            }
            do {
                zzgt.zzp(this.zzsp.zzcs());
                if (!this.zzsp.zzcm()) {
                    zzdq2 = this.zzsp.zzdq();
                } else {
                    return;
                }
            } while (zzdq2 == this.tag);
            this.zzsq = zzdq2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int zzcx2 = this.zzsp.zzcx();
                zzac(zzcx2);
                int zzds2 = this.zzsp.zzds() + zzcx2;
                do {
                    list.add(Long.valueOf(this.zzsp.zzcs()));
                } while (this.zzsp.zzds() < zzds2);
                return;
            default:
                throw zzgf.f();
        }
        do {
            list.add(Long.valueOf(this.zzsp.zzcs()));
            if (!this.zzsp.zzcm()) {
                zzdq = this.zzsp.zzdq();
            } else {
                return;
            }
        } while (zzdq == this.tag);
        this.zzsq = zzdq;
    }

    public final void zzg(List<Integer> list) {
        int zzdq;
        int zzdq2;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzcx = this.zzsp.zzcx();
                zzad(zzcx);
                int zzds = this.zzsp.zzds() + zzcx;
                do {
                    zzfz.zzbg(this.zzsp.zzct());
                } while (this.zzsp.zzds() < zzds);
            } else if (i == 5) {
                do {
                    zzfz.zzbg(this.zzsp.zzct());
                    if (!this.zzsp.zzcm()) {
                        zzdq2 = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (zzdq2 == this.tag);
                this.zzsq = zzdq2;
            } else {
                throw zzgf.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzcx2 = this.zzsp.zzcx();
                zzad(zzcx2);
                int zzds2 = this.zzsp.zzds() + zzcx2;
                do {
                    list.add(Integer.valueOf(this.zzsp.zzct()));
                } while (this.zzsp.zzds() < zzds2);
            } else if (i2 == 5) {
                do {
                    list.add(Integer.valueOf(this.zzsp.zzct()));
                    if (!this.zzsp.zzcm()) {
                        zzdq = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (zzdq == this.tag);
                this.zzsq = zzdq;
            } else {
                throw zzgf.f();
            }
        }
    }

    public final void zzh(List<Boolean> list) {
        int zzdq;
        int zzdq2;
        if (list instanceof zzem) {
            zzem zzem = (zzem) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzem.addBoolean(this.zzsp.zzcu());
                    if (!this.zzsp.zzcm()) {
                        zzdq2 = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (zzdq2 == this.tag);
                this.zzsq = zzdq2;
            } else if (i == 2) {
                int zzds = this.zzsp.zzds() + this.zzsp.zzcx();
                do {
                    zzem.addBoolean(this.zzsp.zzcu());
                } while (this.zzsp.zzds() < zzds);
                zzae(zzds);
            } else {
                throw zzgf.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Boolean.valueOf(this.zzsp.zzcu()));
                    if (!this.zzsp.zzcm()) {
                        zzdq = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (zzdq == this.tag);
                this.zzsq = zzdq;
            } else if (i2 == 2) {
                int zzds2 = this.zzsp.zzds() + this.zzsp.zzcx();
                do {
                    list.add(Boolean.valueOf(this.zzsp.zzcu()));
                } while (this.zzsp.zzds() < zzds2);
                zzae(zzds2);
            } else {
                throw zzgf.f();
            }
        }
    }

    public final void zzi(List<String> list) {
        zza(list, true);
    }

    public final void zzj(List<zzeo> list) {
        int zzdq;
        if ((this.tag & 7) == 2) {
            do {
                list.add(zzcw());
                if (!this.zzsp.zzcm()) {
                    zzdq = this.zzsp.zzdq();
                } else {
                    return;
                }
            } while (zzdq == this.tag);
            this.zzsq = zzdq;
            return;
        }
        throw zzgf.f();
    }

    public final void zzk(List<Integer> list) {
        int zzdq;
        int zzdq2;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzfz.zzbg(this.zzsp.zzcx());
                    if (!this.zzsp.zzcm()) {
                        zzdq2 = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (zzdq2 == this.tag);
                this.zzsq = zzdq2;
            } else if (i == 2) {
                int zzds = this.zzsp.zzds() + this.zzsp.zzcx();
                do {
                    zzfz.zzbg(this.zzsp.zzcx());
                } while (this.zzsp.zzds() < zzds);
                zzae(zzds);
            } else {
                throw zzgf.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzsp.zzcx()));
                    if (!this.zzsp.zzcm()) {
                        zzdq = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (zzdq == this.tag);
                this.zzsq = zzdq;
            } else if (i2 == 2) {
                int zzds2 = this.zzsp.zzds() + this.zzsp.zzcx();
                do {
                    list.add(Integer.valueOf(this.zzsp.zzcx()));
                } while (this.zzsp.zzds() < zzds2);
                zzae(zzds2);
            } else {
                throw zzgf.f();
            }
        }
    }

    public final void zzl(List<Integer> list) {
        int zzdq;
        int zzdq2;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzfz.zzbg(this.zzsp.zzcy());
                    if (!this.zzsp.zzcm()) {
                        zzdq2 = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (zzdq2 == this.tag);
                this.zzsq = zzdq2;
            } else if (i == 2) {
                int zzds = this.zzsp.zzds() + this.zzsp.zzcx();
                do {
                    zzfz.zzbg(this.zzsp.zzcy());
                } while (this.zzsp.zzds() < zzds);
                zzae(zzds);
            } else {
                throw zzgf.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzsp.zzcy()));
                    if (!this.zzsp.zzcm()) {
                        zzdq = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (zzdq == this.tag);
                this.zzsq = zzdq;
            } else if (i2 == 2) {
                int zzds2 = this.zzsp.zzds() + this.zzsp.zzcx();
                do {
                    list.add(Integer.valueOf(this.zzsp.zzcy()));
                } while (this.zzsp.zzds() < zzds2);
                zzae(zzds2);
            } else {
                throw zzgf.f();
            }
        }
    }

    public final void zzm(List<Integer> list) {
        int zzdq;
        int zzdq2;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzcx = this.zzsp.zzcx();
                zzad(zzcx);
                int zzds = this.zzsp.zzds() + zzcx;
                do {
                    zzfz.zzbg(this.zzsp.zzcz());
                } while (this.zzsp.zzds() < zzds);
            } else if (i == 5) {
                do {
                    zzfz.zzbg(this.zzsp.zzcz());
                    if (!this.zzsp.zzcm()) {
                        zzdq2 = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (zzdq2 == this.tag);
                this.zzsq = zzdq2;
            } else {
                throw zzgf.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzcx2 = this.zzsp.zzcx();
                zzad(zzcx2);
                int zzds2 = this.zzsp.zzds() + zzcx2;
                do {
                    list.add(Integer.valueOf(this.zzsp.zzcz()));
                } while (this.zzsp.zzds() < zzds2);
            } else if (i2 == 5) {
                do {
                    list.add(Integer.valueOf(this.zzsp.zzcz()));
                    if (!this.zzsp.zzcm()) {
                        zzdq = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (zzdq == this.tag);
                this.zzsq = zzdq;
            } else {
                throw zzgf.f();
            }
        }
    }

    public final void zzn(List<Long> list) {
        int zzdq;
        int zzdq2;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int zzcx = this.zzsp.zzcx();
                    zzac(zzcx);
                    int zzds = this.zzsp.zzds() + zzcx;
                    do {
                        zzgt.zzp(this.zzsp.zzda());
                    } while (this.zzsp.zzds() < zzds);
                    return;
                default:
                    throw zzgf.f();
            }
            do {
                zzgt.zzp(this.zzsp.zzda());
                if (!this.zzsp.zzcm()) {
                    zzdq2 = this.zzsp.zzdq();
                } else {
                    return;
                }
            } while (zzdq2 == this.tag);
            this.zzsq = zzdq2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int zzcx2 = this.zzsp.zzcx();
                zzac(zzcx2);
                int zzds2 = this.zzsp.zzds() + zzcx2;
                do {
                    list.add(Long.valueOf(this.zzsp.zzda()));
                } while (this.zzsp.zzds() < zzds2);
                return;
            default:
                throw zzgf.f();
        }
        do {
            list.add(Long.valueOf(this.zzsp.zzda()));
            if (!this.zzsp.zzcm()) {
                zzdq = this.zzsp.zzdq();
            } else {
                return;
            }
        } while (zzdq == this.tag);
        this.zzsq = zzdq;
    }

    public final void zzo(List<Integer> list) {
        int zzdq;
        int zzdq2;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzfz.zzbg(this.zzsp.zzdb());
                    if (!this.zzsp.zzcm()) {
                        zzdq2 = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (zzdq2 == this.tag);
                this.zzsq = zzdq2;
            } else if (i == 2) {
                int zzds = this.zzsp.zzds() + this.zzsp.zzcx();
                do {
                    zzfz.zzbg(this.zzsp.zzdb());
                } while (this.zzsp.zzds() < zzds);
                zzae(zzds);
            } else {
                throw zzgf.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzsp.zzdb()));
                    if (!this.zzsp.zzcm()) {
                        zzdq = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (zzdq == this.tag);
                this.zzsq = zzdq;
            } else if (i2 == 2) {
                int zzds2 = this.zzsp.zzds() + this.zzsp.zzcx();
                do {
                    list.add(Integer.valueOf(this.zzsp.zzdb()));
                } while (this.zzsp.zzds() < zzds2);
                zzae(zzds2);
            } else {
                throw zzgf.f();
            }
        }
    }

    public final void zzp(List<Long> list) {
        int zzdq;
        int zzdq2;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzgt.zzp(this.zzsp.zzdc());
                    if (!this.zzsp.zzcm()) {
                        zzdq2 = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (zzdq2 == this.tag);
                this.zzsq = zzdq2;
            } else if (i == 2) {
                int zzds = this.zzsp.zzds() + this.zzsp.zzcx();
                do {
                    zzgt.zzp(this.zzsp.zzdc());
                } while (this.zzsp.zzds() < zzds);
                zzae(zzds);
            } else {
                throw zzgf.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzsp.zzdc()));
                    if (!this.zzsp.zzcm()) {
                        zzdq = this.zzsp.zzdq();
                    } else {
                        return;
                    }
                } while (zzdq == this.tag);
                this.zzsq = zzdq;
            } else if (i2 == 2) {
                int zzds2 = this.zzsp.zzds() + this.zzsp.zzcx();
                do {
                    list.add(Long.valueOf(this.zzsp.zzdc()));
                } while (this.zzsp.zzds() < zzds2);
                zzae(zzds2);
            } else {
                throw zzgf.f();
            }
        }
    }
}
