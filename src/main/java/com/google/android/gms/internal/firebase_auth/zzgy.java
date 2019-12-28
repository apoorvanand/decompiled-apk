package com.google.android.gms.internal.firebase_auth;

import java.util.List;

final class zzgy implements zzjp {
    private int tag;
    private final zzgr zzwt;
    private int zzwu;
    private int zzwv = 0;

    private zzgy(zzgr zzgr) {
        this.zzwt = (zzgr) zzht.a(zzgr, "input");
        this.zzwt.d = this;
    }

    public static zzgy zza(zzgr zzgr) {
        return zzgr.d != null ? zzgr.d : new zzgy(zzgr);
    }

    private final Object zza(zzlb zzlb, Class<?> cls, zzhf zzhf) {
        switch (zzgx.a[zzlb.ordinal()]) {
            case 1:
                return Boolean.valueOf(zzgo());
            case 2:
                return zzgq();
            case 3:
                return Double.valueOf(readDouble());
            case 4:
                return Integer.valueOf(zzgs());
            case 5:
                return Integer.valueOf(zzgn());
            case 6:
                return Long.valueOf(zzgm());
            case 7:
                return Float.valueOf(readFloat());
            case 8:
                return Integer.valueOf(zzgl());
            case 9:
                return Long.valueOf(zzgk());
            case 10:
                zzac(2);
                return zzc(zzjo.zzjv().zzf(cls), zzhf);
            case 11:
                return Integer.valueOf(zzgt());
            case 12:
                return Long.valueOf(zzgu());
            case 13:
                return Integer.valueOf(zzgv());
            case 14:
                return Long.valueOf(zzgw());
            case 15:
                return zzgp();
            case 16:
                return Integer.valueOf(zzgr());
            case 17:
                return Long.valueOf(zzgj());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private final void zza(List<String> list, boolean z) {
        int zzgi;
        int zzgi2;
        if ((this.tag & 7) != 2) {
            throw zzic.f();
        } else if (!(list instanceof zzij) || z) {
            do {
                list.add(z ? zzgp() : readString());
                if (!this.zzwt.zzgy()) {
                    zzgi = this.zzwt.zzgi();
                } else {
                    return;
                }
            } while (zzgi == this.tag);
            this.zzwv = zzgi;
        } else {
            zzij zzij = (zzij) list;
            do {
                zzij.zzc(zzgq());
                if (!this.zzwt.zzgy()) {
                    zzgi2 = this.zzwt.zzgi();
                } else {
                    return;
                }
            } while (zzgi2 == this.tag);
            this.zzwv = zzgi2;
        }
    }

    private final void zzac(int i) {
        if ((this.tag & 7) != i) {
            throw zzic.f();
        }
    }

    private static void zzad(int i) {
        if ((i & 7) != 0) {
            throw zzic.h();
        }
    }

    private static void zzae(int i) {
        if ((i & 3) != 0) {
            throw zzic.h();
        }
    }

    private final void zzaf(int i) {
        if (this.zzwt.zzgz() != i) {
            throw zzic.a();
        }
    }

    private final <T> T zzc(zzjs<T> zzjs, zzhf zzhf) {
        int zzgr = this.zzwt.zzgr();
        if (this.zzwt.a < this.zzwt.b) {
            int zzu = this.zzwt.zzu(zzgr);
            T newInstance = zzjs.newInstance();
            this.zzwt.a++;
            zzjs.zza(newInstance, this, zzhf);
            zzjs.zzf(newInstance);
            this.zzwt.zzs(0);
            zzgr zzgr2 = this.zzwt;
            zzgr2.a--;
            this.zzwt.zzv(zzu);
            return newInstance;
        }
        throw new zzic("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    private final <T> T zzd(zzjs<T> zzjs, zzhf zzhf) {
        int i = this.zzwu;
        this.zzwu = ((this.tag >>> 3) << 3) | 4;
        try {
            T newInstance = zzjs.newInstance();
            zzjs.zza(newInstance, this, zzhf);
            zzjs.zzf(newInstance);
            if (this.tag == this.zzwu) {
                return newInstance;
            }
            throw zzic.h();
        } finally {
            this.zzwu = i;
        }
    }

    public final int getTag() {
        return this.tag;
    }

    public final double readDouble() {
        zzac(1);
        return this.zzwt.readDouble();
    }

    public final float readFloat() {
        zzac(5);
        return this.zzwt.readFloat();
    }

    public final String readString() {
        zzac(2);
        return this.zzwt.readString();
    }

    public final void readStringList(List<String> list) {
        zza(list, false);
    }

    public final <T> T zza(zzjs<T> zzjs, zzhf zzhf) {
        zzac(2);
        return zzc(zzjs, zzhf);
    }

    public final <T> void zza(List<T> list, zzjs<T> zzjs, zzhf zzhf) {
        int zzgi;
        int i = this.tag;
        if ((i & 7) == 2) {
            do {
                list.add(zzc(zzjs, zzhf));
                if (!this.zzwt.zzgy() && this.zzwv == 0) {
                    zzgi = this.zzwt.zzgi();
                } else {
                    return;
                }
            } while (zzgi == i);
            this.zzwv = zzgi;
            return;
        }
        throw zzic.f();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0051, code lost:
        if (zzhh() != false) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005b, code lost:
        throw new com.google.android.gms.internal.firebase_auth.zzic("Unable to parse map entry.");
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x004d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <K, V> void zza(java.util.Map<K, V> r6, com.google.android.gms.internal.firebase_auth.zzit<K, V> r7, com.google.android.gms.internal.firebase_auth.zzhf r8) {
        /*
            r5 = this;
            r0 = 2
            r5.zzac(r0)
            com.google.android.gms.internal.firebase_auth.zzgr r0 = r5.zzwt
            int r0 = r0.zzgr()
            com.google.android.gms.internal.firebase_auth.zzgr r1 = r5.zzwt
            int r0 = r1.zzu(r0)
            K r1 = r7.zzacl
            V r2 = r7.zzacn
        L_0x0014:
            int r3 = r5.zzhg()     // Catch:{ all -> 0x0065 }
            r4 = 2147483647(0x7fffffff, float:NaN)
            if (r3 == r4) goto L_0x005c
            com.google.android.gms.internal.firebase_auth.zzgr r4 = r5.zzwt     // Catch:{ all -> 0x0065 }
            boolean r4 = r4.zzgy()     // Catch:{ all -> 0x0065 }
            if (r4 != 0) goto L_0x005c
            switch(r3) {
                case 1: goto L_0x003a;
                case 2: goto L_0x002d;
                default: goto L_0x0028;
            }
        L_0x0028:
            boolean r3 = r5.zzhh()     // Catch:{ zzib -> 0x004d }
            goto L_0x0042
        L_0x002d:
            com.google.android.gms.internal.firebase_auth.zzlb r3 = r7.zzacm     // Catch:{ zzib -> 0x004d }
            V r4 = r7.zzacn     // Catch:{ zzib -> 0x004d }
            java.lang.Class r4 = r4.getClass()     // Catch:{ zzib -> 0x004d }
            java.lang.Object r2 = r5.zza((com.google.android.gms.internal.firebase_auth.zzlb) r3, (java.lang.Class<?>) r4, (com.google.android.gms.internal.firebase_auth.zzhf) r8)     // Catch:{ zzib -> 0x004d }
            goto L_0x0014
        L_0x003a:
            com.google.android.gms.internal.firebase_auth.zzlb r3 = r7.zzack     // Catch:{ zzib -> 0x004d }
            r4 = 0
            java.lang.Object r1 = r5.zza((com.google.android.gms.internal.firebase_auth.zzlb) r3, (java.lang.Class<?>) r4, (com.google.android.gms.internal.firebase_auth.zzhf) r4)     // Catch:{ zzib -> 0x004d }
            goto L_0x0014
        L_0x0042:
            if (r3 == 0) goto L_0x0045
            goto L_0x0014
        L_0x0045:
            com.google.android.gms.internal.firebase_auth.zzic r3 = new com.google.android.gms.internal.firebase_auth.zzic     // Catch:{ zzib -> 0x004d }
            java.lang.String r4 = "Unable to parse map entry."
            r3.<init>(r4)     // Catch:{ zzib -> 0x004d }
            throw r3     // Catch:{ zzib -> 0x004d }
        L_0x004d:
            boolean r3 = r5.zzhh()     // Catch:{ all -> 0x0065 }
            if (r3 == 0) goto L_0x0054
            goto L_0x0014
        L_0x0054:
            com.google.android.gms.internal.firebase_auth.zzic r6 = new com.google.android.gms.internal.firebase_auth.zzic     // Catch:{ all -> 0x0065 }
            java.lang.String r7 = "Unable to parse map entry."
            r6.<init>(r7)     // Catch:{ all -> 0x0065 }
            throw r6     // Catch:{ all -> 0x0065 }
        L_0x005c:
            r6.put(r1, r2)     // Catch:{ all -> 0x0065 }
            com.google.android.gms.internal.firebase_auth.zzgr r6 = r5.zzwt
            r6.zzv(r0)
            return
        L_0x0065:
            r6 = move-exception
            com.google.android.gms.internal.firebase_auth.zzgr r7 = r5.zzwt
            r7.zzv(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzgy.zza(java.util.Map, com.google.android.gms.internal.firebase_auth.zzit, com.google.android.gms.internal.firebase_auth.zzhf):void");
    }

    public final <T> T zzb(zzjs<T> zzjs, zzhf zzhf) {
        zzac(3);
        return zzd(zzjs, zzhf);
    }

    public final <T> void zzb(List<T> list, zzjs<T> zzjs, zzhf zzhf) {
        int zzgi;
        int i = this.tag;
        if ((i & 7) == 3) {
            do {
                list.add(zzd(zzjs, zzhf));
                if (!this.zzwt.zzgy() && this.zzwv == 0) {
                    zzgi = this.zzwt.zzgi();
                } else {
                    return;
                }
            } while (zzgi == i);
            this.zzwv = zzgi;
            return;
        }
        throw zzic.f();
    }

    public final long zzgj() {
        zzac(0);
        return this.zzwt.zzgj();
    }

    public final long zzgk() {
        zzac(0);
        return this.zzwt.zzgk();
    }

    public final int zzgl() {
        zzac(0);
        return this.zzwt.zzgl();
    }

    public final long zzgm() {
        zzac(1);
        return this.zzwt.zzgm();
    }

    public final int zzgn() {
        zzac(5);
        return this.zzwt.zzgn();
    }

    public final boolean zzgo() {
        zzac(0);
        return this.zzwt.zzgo();
    }

    public final String zzgp() {
        zzac(2);
        return this.zzwt.zzgp();
    }

    public final zzgf zzgq() {
        zzac(2);
        return this.zzwt.zzgq();
    }

    public final int zzgr() {
        zzac(0);
        return this.zzwt.zzgr();
    }

    public final int zzgs() {
        zzac(0);
        return this.zzwt.zzgs();
    }

    public final int zzgt() {
        zzac(5);
        return this.zzwt.zzgt();
    }

    public final long zzgu() {
        zzac(1);
        return this.zzwt.zzgu();
    }

    public final int zzgv() {
        zzac(0);
        return this.zzwt.zzgv();
    }

    public final long zzgw() {
        zzac(0);
        return this.zzwt.zzgw();
    }

    public final void zzh(List<Double> list) {
        int zzgi;
        int zzgi2;
        if (list instanceof zzhb) {
            zzhb zzhb = (zzhb) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int zzgr = this.zzwt.zzgr();
                    zzad(zzgr);
                    int zzgz = this.zzwt.zzgz() + zzgr;
                    do {
                        zzhb.zzc(this.zzwt.readDouble());
                    } while (this.zzwt.zzgz() < zzgz);
                    return;
                default:
                    throw zzic.f();
            }
            do {
                zzhb.zzc(this.zzwt.readDouble());
                if (!this.zzwt.zzgy()) {
                    zzgi2 = this.zzwt.zzgi();
                } else {
                    return;
                }
            } while (zzgi2 == this.tag);
            this.zzwv = zzgi2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int zzgr2 = this.zzwt.zzgr();
                zzad(zzgr2);
                int zzgz2 = this.zzwt.zzgz() + zzgr2;
                do {
                    list.add(Double.valueOf(this.zzwt.readDouble()));
                } while (this.zzwt.zzgz() < zzgz2);
                return;
            default:
                throw zzic.f();
        }
        do {
            list.add(Double.valueOf(this.zzwt.readDouble()));
            if (!this.zzwt.zzgy()) {
                zzgi = this.zzwt.zzgi();
            } else {
                return;
            }
        } while (zzgi == this.tag);
        this.zzwv = zzgi;
    }

    public final int zzhg() {
        int i = this.zzwv;
        if (i != 0) {
            this.tag = i;
            this.zzwv = 0;
        } else {
            this.tag = this.zzwt.zzgi();
        }
        int i2 = this.tag;
        if (i2 == 0 || i2 == this.zzwu) {
            return Integer.MAX_VALUE;
        }
        return i2 >>> 3;
    }

    public final boolean zzhh() {
        int i;
        if (this.zzwt.zzgy() || (i = this.tag) == this.zzwu) {
            return false;
        }
        return this.zzwt.zzt(i);
    }

    public final void zzi(List<Float> list) {
        int zzgi;
        int zzgi2;
        if (list instanceof zzho) {
            zzho zzho = (zzho) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzgr = this.zzwt.zzgr();
                zzae(zzgr);
                int zzgz = this.zzwt.zzgz() + zzgr;
                do {
                    zzho.zzc(this.zzwt.readFloat());
                } while (this.zzwt.zzgz() < zzgz);
            } else if (i == 5) {
                do {
                    zzho.zzc(this.zzwt.readFloat());
                    if (!this.zzwt.zzgy()) {
                        zzgi2 = this.zzwt.zzgi();
                    } else {
                        return;
                    }
                } while (zzgi2 == this.tag);
                this.zzwv = zzgi2;
            } else {
                throw zzic.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzgr2 = this.zzwt.zzgr();
                zzae(zzgr2);
                int zzgz2 = this.zzwt.zzgz() + zzgr2;
                do {
                    list.add(Float.valueOf(this.zzwt.readFloat()));
                } while (this.zzwt.zzgz() < zzgz2);
            } else if (i2 == 5) {
                do {
                    list.add(Float.valueOf(this.zzwt.readFloat()));
                    if (!this.zzwt.zzgy()) {
                        zzgi = this.zzwt.zzgi();
                    } else {
                        return;
                    }
                } while (zzgi == this.tag);
                this.zzwv = zzgi;
            } else {
                throw zzic.f();
            }
        }
    }

    public final void zzj(List<Long> list) {
        int zzgi;
        int zzgi2;
        if (list instanceof zziq) {
            zziq zziq = (zziq) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zziq.zzl(this.zzwt.zzgj());
                    if (!this.zzwt.zzgy()) {
                        zzgi2 = this.zzwt.zzgi();
                    } else {
                        return;
                    }
                } while (zzgi2 == this.tag);
                this.zzwv = zzgi2;
            } else if (i == 2) {
                int zzgz = this.zzwt.zzgz() + this.zzwt.zzgr();
                do {
                    zziq.zzl(this.zzwt.zzgj());
                } while (this.zzwt.zzgz() < zzgz);
                zzaf(zzgz);
            } else {
                throw zzic.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzwt.zzgj()));
                    if (!this.zzwt.zzgy()) {
                        zzgi = this.zzwt.zzgi();
                    } else {
                        return;
                    }
                } while (zzgi == this.tag);
                this.zzwv = zzgi;
            } else if (i2 == 2) {
                int zzgz2 = this.zzwt.zzgz() + this.zzwt.zzgr();
                do {
                    list.add(Long.valueOf(this.zzwt.zzgj()));
                } while (this.zzwt.zzgz() < zzgz2);
                zzaf(zzgz2);
            } else {
                throw zzic.f();
            }
        }
    }

    public final void zzk(List<Long> list) {
        int zzgi;
        int zzgi2;
        if (list instanceof zziq) {
            zziq zziq = (zziq) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zziq.zzl(this.zzwt.zzgk());
                    if (!this.zzwt.zzgy()) {
                        zzgi2 = this.zzwt.zzgi();
                    } else {
                        return;
                    }
                } while (zzgi2 == this.tag);
                this.zzwv = zzgi2;
            } else if (i == 2) {
                int zzgz = this.zzwt.zzgz() + this.zzwt.zzgr();
                do {
                    zziq.zzl(this.zzwt.zzgk());
                } while (this.zzwt.zzgz() < zzgz);
                zzaf(zzgz);
            } else {
                throw zzic.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzwt.zzgk()));
                    if (!this.zzwt.zzgy()) {
                        zzgi = this.zzwt.zzgi();
                    } else {
                        return;
                    }
                } while (zzgi == this.tag);
                this.zzwv = zzgi;
            } else if (i2 == 2) {
                int zzgz2 = this.zzwt.zzgz() + this.zzwt.zzgr();
                do {
                    list.add(Long.valueOf(this.zzwt.zzgk()));
                } while (this.zzwt.zzgz() < zzgz2);
                zzaf(zzgz2);
            } else {
                throw zzic.f();
            }
        }
    }

    public final void zzl(List<Integer> list) {
        int zzgi;
        int zzgi2;
        if (list instanceof zzhu) {
            zzhu zzhu = (zzhu) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzhu.zzaw(this.zzwt.zzgl());
                    if (!this.zzwt.zzgy()) {
                        zzgi2 = this.zzwt.zzgi();
                    } else {
                        return;
                    }
                } while (zzgi2 == this.tag);
                this.zzwv = zzgi2;
            } else if (i == 2) {
                int zzgz = this.zzwt.zzgz() + this.zzwt.zzgr();
                do {
                    zzhu.zzaw(this.zzwt.zzgl());
                } while (this.zzwt.zzgz() < zzgz);
                zzaf(zzgz);
            } else {
                throw zzic.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzwt.zzgl()));
                    if (!this.zzwt.zzgy()) {
                        zzgi = this.zzwt.zzgi();
                    } else {
                        return;
                    }
                } while (zzgi == this.tag);
                this.zzwv = zzgi;
            } else if (i2 == 2) {
                int zzgz2 = this.zzwt.zzgz() + this.zzwt.zzgr();
                do {
                    list.add(Integer.valueOf(this.zzwt.zzgl()));
                } while (this.zzwt.zzgz() < zzgz2);
                zzaf(zzgz2);
            } else {
                throw zzic.f();
            }
        }
    }

    public final void zzm(List<Long> list) {
        int zzgi;
        int zzgi2;
        if (list instanceof zziq) {
            zziq zziq = (zziq) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int zzgr = this.zzwt.zzgr();
                    zzad(zzgr);
                    int zzgz = this.zzwt.zzgz() + zzgr;
                    do {
                        zziq.zzl(this.zzwt.zzgm());
                    } while (this.zzwt.zzgz() < zzgz);
                    return;
                default:
                    throw zzic.f();
            }
            do {
                zziq.zzl(this.zzwt.zzgm());
                if (!this.zzwt.zzgy()) {
                    zzgi2 = this.zzwt.zzgi();
                } else {
                    return;
                }
            } while (zzgi2 == this.tag);
            this.zzwv = zzgi2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int zzgr2 = this.zzwt.zzgr();
                zzad(zzgr2);
                int zzgz2 = this.zzwt.zzgz() + zzgr2;
                do {
                    list.add(Long.valueOf(this.zzwt.zzgm()));
                } while (this.zzwt.zzgz() < zzgz2);
                return;
            default:
                throw zzic.f();
        }
        do {
            list.add(Long.valueOf(this.zzwt.zzgm()));
            if (!this.zzwt.zzgy()) {
                zzgi = this.zzwt.zzgi();
            } else {
                return;
            }
        } while (zzgi == this.tag);
        this.zzwv = zzgi;
    }

    public final void zzn(List<Integer> list) {
        int zzgi;
        int zzgi2;
        if (list instanceof zzhu) {
            zzhu zzhu = (zzhu) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzgr = this.zzwt.zzgr();
                zzae(zzgr);
                int zzgz = this.zzwt.zzgz() + zzgr;
                do {
                    zzhu.zzaw(this.zzwt.zzgn());
                } while (this.zzwt.zzgz() < zzgz);
            } else if (i == 5) {
                do {
                    zzhu.zzaw(this.zzwt.zzgn());
                    if (!this.zzwt.zzgy()) {
                        zzgi2 = this.zzwt.zzgi();
                    } else {
                        return;
                    }
                } while (zzgi2 == this.tag);
                this.zzwv = zzgi2;
            } else {
                throw zzic.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzgr2 = this.zzwt.zzgr();
                zzae(zzgr2);
                int zzgz2 = this.zzwt.zzgz() + zzgr2;
                do {
                    list.add(Integer.valueOf(this.zzwt.zzgn()));
                } while (this.zzwt.zzgz() < zzgz2);
            } else if (i2 == 5) {
                do {
                    list.add(Integer.valueOf(this.zzwt.zzgn()));
                    if (!this.zzwt.zzgy()) {
                        zzgi = this.zzwt.zzgi();
                    } else {
                        return;
                    }
                } while (zzgi == this.tag);
                this.zzwv = zzgi;
            } else {
                throw zzic.f();
            }
        }
    }

    public final void zzo(List<Boolean> list) {
        int zzgi;
        int zzgi2;
        if (list instanceof zzgd) {
            zzgd zzgd = (zzgd) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzgd.addBoolean(this.zzwt.zzgo());
                    if (!this.zzwt.zzgy()) {
                        zzgi2 = this.zzwt.zzgi();
                    } else {
                        return;
                    }
                } while (zzgi2 == this.tag);
                this.zzwv = zzgi2;
            } else if (i == 2) {
                int zzgz = this.zzwt.zzgz() + this.zzwt.zzgr();
                do {
                    zzgd.addBoolean(this.zzwt.zzgo());
                } while (this.zzwt.zzgz() < zzgz);
                zzaf(zzgz);
            } else {
                throw zzic.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Boolean.valueOf(this.zzwt.zzgo()));
                    if (!this.zzwt.zzgy()) {
                        zzgi = this.zzwt.zzgi();
                    } else {
                        return;
                    }
                } while (zzgi == this.tag);
                this.zzwv = zzgi;
            } else if (i2 == 2) {
                int zzgz2 = this.zzwt.zzgz() + this.zzwt.zzgr();
                do {
                    list.add(Boolean.valueOf(this.zzwt.zzgo()));
                } while (this.zzwt.zzgz() < zzgz2);
                zzaf(zzgz2);
            } else {
                throw zzic.f();
            }
        }
    }

    public final void zzp(List<String> list) {
        zza(list, true);
    }

    public final void zzq(List<zzgf> list) {
        int zzgi;
        if ((this.tag & 7) == 2) {
            do {
                list.add(zzgq());
                if (!this.zzwt.zzgy()) {
                    zzgi = this.zzwt.zzgi();
                } else {
                    return;
                }
            } while (zzgi == this.tag);
            this.zzwv = zzgi;
            return;
        }
        throw zzic.f();
    }

    public final void zzr(List<Integer> list) {
        int zzgi;
        int zzgi2;
        if (list instanceof zzhu) {
            zzhu zzhu = (zzhu) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzhu.zzaw(this.zzwt.zzgr());
                    if (!this.zzwt.zzgy()) {
                        zzgi2 = this.zzwt.zzgi();
                    } else {
                        return;
                    }
                } while (zzgi2 == this.tag);
                this.zzwv = zzgi2;
            } else if (i == 2) {
                int zzgz = this.zzwt.zzgz() + this.zzwt.zzgr();
                do {
                    zzhu.zzaw(this.zzwt.zzgr());
                } while (this.zzwt.zzgz() < zzgz);
                zzaf(zzgz);
            } else {
                throw zzic.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzwt.zzgr()));
                    if (!this.zzwt.zzgy()) {
                        zzgi = this.zzwt.zzgi();
                    } else {
                        return;
                    }
                } while (zzgi == this.tag);
                this.zzwv = zzgi;
            } else if (i2 == 2) {
                int zzgz2 = this.zzwt.zzgz() + this.zzwt.zzgr();
                do {
                    list.add(Integer.valueOf(this.zzwt.zzgr()));
                } while (this.zzwt.zzgz() < zzgz2);
                zzaf(zzgz2);
            } else {
                throw zzic.f();
            }
        }
    }

    public final void zzs(List<Integer> list) {
        int zzgi;
        int zzgi2;
        if (list instanceof zzhu) {
            zzhu zzhu = (zzhu) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzhu.zzaw(this.zzwt.zzgs());
                    if (!this.zzwt.zzgy()) {
                        zzgi2 = this.zzwt.zzgi();
                    } else {
                        return;
                    }
                } while (zzgi2 == this.tag);
                this.zzwv = zzgi2;
            } else if (i == 2) {
                int zzgz = this.zzwt.zzgz() + this.zzwt.zzgr();
                do {
                    zzhu.zzaw(this.zzwt.zzgs());
                } while (this.zzwt.zzgz() < zzgz);
                zzaf(zzgz);
            } else {
                throw zzic.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzwt.zzgs()));
                    if (!this.zzwt.zzgy()) {
                        zzgi = this.zzwt.zzgi();
                    } else {
                        return;
                    }
                } while (zzgi == this.tag);
                this.zzwv = zzgi;
            } else if (i2 == 2) {
                int zzgz2 = this.zzwt.zzgz() + this.zzwt.zzgr();
                do {
                    list.add(Integer.valueOf(this.zzwt.zzgs()));
                } while (this.zzwt.zzgz() < zzgz2);
                zzaf(zzgz2);
            } else {
                throw zzic.f();
            }
        }
    }

    public final void zzt(List<Integer> list) {
        int zzgi;
        int zzgi2;
        if (list instanceof zzhu) {
            zzhu zzhu = (zzhu) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzgr = this.zzwt.zzgr();
                zzae(zzgr);
                int zzgz = this.zzwt.zzgz() + zzgr;
                do {
                    zzhu.zzaw(this.zzwt.zzgt());
                } while (this.zzwt.zzgz() < zzgz);
            } else if (i == 5) {
                do {
                    zzhu.zzaw(this.zzwt.zzgt());
                    if (!this.zzwt.zzgy()) {
                        zzgi2 = this.zzwt.zzgi();
                    } else {
                        return;
                    }
                } while (zzgi2 == this.tag);
                this.zzwv = zzgi2;
            } else {
                throw zzic.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzgr2 = this.zzwt.zzgr();
                zzae(zzgr2);
                int zzgz2 = this.zzwt.zzgz() + zzgr2;
                do {
                    list.add(Integer.valueOf(this.zzwt.zzgt()));
                } while (this.zzwt.zzgz() < zzgz2);
            } else if (i2 == 5) {
                do {
                    list.add(Integer.valueOf(this.zzwt.zzgt()));
                    if (!this.zzwt.zzgy()) {
                        zzgi = this.zzwt.zzgi();
                    } else {
                        return;
                    }
                } while (zzgi == this.tag);
                this.zzwv = zzgi;
            } else {
                throw zzic.f();
            }
        }
    }

    public final void zzu(List<Long> list) {
        int zzgi;
        int zzgi2;
        if (list instanceof zziq) {
            zziq zziq = (zziq) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int zzgr = this.zzwt.zzgr();
                    zzad(zzgr);
                    int zzgz = this.zzwt.zzgz() + zzgr;
                    do {
                        zziq.zzl(this.zzwt.zzgu());
                    } while (this.zzwt.zzgz() < zzgz);
                    return;
                default:
                    throw zzic.f();
            }
            do {
                zziq.zzl(this.zzwt.zzgu());
                if (!this.zzwt.zzgy()) {
                    zzgi2 = this.zzwt.zzgi();
                } else {
                    return;
                }
            } while (zzgi2 == this.tag);
            this.zzwv = zzgi2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int zzgr2 = this.zzwt.zzgr();
                zzad(zzgr2);
                int zzgz2 = this.zzwt.zzgz() + zzgr2;
                do {
                    list.add(Long.valueOf(this.zzwt.zzgu()));
                } while (this.zzwt.zzgz() < zzgz2);
                return;
            default:
                throw zzic.f();
        }
        do {
            list.add(Long.valueOf(this.zzwt.zzgu()));
            if (!this.zzwt.zzgy()) {
                zzgi = this.zzwt.zzgi();
            } else {
                return;
            }
        } while (zzgi == this.tag);
        this.zzwv = zzgi;
    }

    public final void zzv(List<Integer> list) {
        int zzgi;
        int zzgi2;
        if (list instanceof zzhu) {
            zzhu zzhu = (zzhu) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzhu.zzaw(this.zzwt.zzgv());
                    if (!this.zzwt.zzgy()) {
                        zzgi2 = this.zzwt.zzgi();
                    } else {
                        return;
                    }
                } while (zzgi2 == this.tag);
                this.zzwv = zzgi2;
            } else if (i == 2) {
                int zzgz = this.zzwt.zzgz() + this.zzwt.zzgr();
                do {
                    zzhu.zzaw(this.zzwt.zzgv());
                } while (this.zzwt.zzgz() < zzgz);
                zzaf(zzgz);
            } else {
                throw zzic.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzwt.zzgv()));
                    if (!this.zzwt.zzgy()) {
                        zzgi = this.zzwt.zzgi();
                    } else {
                        return;
                    }
                } while (zzgi == this.tag);
                this.zzwv = zzgi;
            } else if (i2 == 2) {
                int zzgz2 = this.zzwt.zzgz() + this.zzwt.zzgr();
                do {
                    list.add(Integer.valueOf(this.zzwt.zzgv()));
                } while (this.zzwt.zzgz() < zzgz2);
                zzaf(zzgz2);
            } else {
                throw zzic.f();
            }
        }
    }

    public final void zzw(List<Long> list) {
        int zzgi;
        int zzgi2;
        if (list instanceof zziq) {
            zziq zziq = (zziq) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zziq.zzl(this.zzwt.zzgw());
                    if (!this.zzwt.zzgy()) {
                        zzgi2 = this.zzwt.zzgi();
                    } else {
                        return;
                    }
                } while (zzgi2 == this.tag);
                this.zzwv = zzgi2;
            } else if (i == 2) {
                int zzgz = this.zzwt.zzgz() + this.zzwt.zzgr();
                do {
                    zziq.zzl(this.zzwt.zzgw());
                } while (this.zzwt.zzgz() < zzgz);
                zzaf(zzgz);
            } else {
                throw zzic.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzwt.zzgw()));
                    if (!this.zzwt.zzgy()) {
                        zzgi = this.zzwt.zzgi();
                    } else {
                        return;
                    }
                } while (zzgi == this.tag);
                this.zzwv = zzgi;
            } else if (i2 == 2) {
                int zzgz2 = this.zzwt.zzgz() + this.zzwt.zzgr();
                do {
                    list.add(Long.valueOf(this.zzwt.zzgw()));
                } while (this.zzwt.zzgz() < zzgz2);
                zzaf(zzgz2);
            } else {
                throw zzic.f();
            }
        }
    }
}
