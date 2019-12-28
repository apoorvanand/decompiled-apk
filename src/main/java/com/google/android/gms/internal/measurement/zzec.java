package com.google.android.gms.internal.measurement;

import java.util.List;

final class zzec implements zzgy {
    private int tag;
    private final zzeb zzadu;
    private int zzadv;
    private int zzadw = 0;

    private zzec(zzeb zzeb) {
        this.zzadu = (zzeb) zzez.a(zzeb, "input");
        this.zzadu.c = this;
    }

    public static zzec zza(zzeb zzeb) {
        return zzeb.c != null ? zzeb.c : new zzec(zzeb);
    }

    private final Object zza(zzig zzig, Class<?> cls, zzel zzel) {
        switch (zzef.a[zzig.ordinal()]) {
            case 1:
                return Boolean.valueOf(zzsm());
            case 2:
                return zzso();
            case 3:
                return Double.valueOf(readDouble());
            case 4:
                return Integer.valueOf(zzsq());
            case 5:
                return Integer.valueOf(zzsl());
            case 6:
                return Long.valueOf(zzsk());
            case 7:
                return Float.valueOf(readFloat());
            case 8:
                return Integer.valueOf(zzsj());
            case 9:
                return Long.valueOf(zzsi());
            case 10:
                zzba(2);
                return zzc(zzgt.zzvy().zzf(cls), zzel);
            case 11:
                return Integer.valueOf(zzsr());
            case 12:
                return Long.valueOf(zzss());
            case 13:
                return Integer.valueOf(zzst());
            case 14:
                return Long.valueOf(zzsu());
            case 15:
                return zzsn();
            case 16:
                return Integer.valueOf(zzsp());
            case 17:
                return Long.valueOf(zzsh());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private final void zza(List<String> list, boolean z) {
        int zzsg;
        int zzsg2;
        if ((this.tag & 7) != 2) {
            throw zzfi.f();
        } else if (!(list instanceof zzfp) || z) {
            do {
                list.add(z ? zzsn() : readString());
                if (!this.zzadu.zzsw()) {
                    zzsg = this.zzadu.zzsg();
                } else {
                    return;
                }
            } while (zzsg == this.tag);
            this.zzadw = zzsg;
        } else {
            zzfp zzfp = (zzfp) list;
            do {
                zzfp.zzc(zzso());
                if (!this.zzadu.zzsw()) {
                    zzsg2 = this.zzadu.zzsg();
                } else {
                    return;
                }
            } while (zzsg2 == this.tag);
            this.zzadw = zzsg2;
        }
    }

    private final void zzba(int i) {
        if ((this.tag & 7) != i) {
            throw zzfi.f();
        }
    }

    private static void zzbb(int i) {
        if ((i & 7) != 0) {
            throw zzfi.h();
        }
    }

    private static void zzbc(int i) {
        if ((i & 3) != 0) {
            throw zzfi.h();
        }
    }

    private final void zzbd(int i) {
        if (this.zzadu.zzsx() != i) {
            throw zzfi.a();
        }
    }

    private final <T> T zzc(zzgx<T> zzgx, zzel zzel) {
        int zzsp = this.zzadu.zzsp();
        if (this.zzadu.a < this.zzadu.b) {
            int zzaw = this.zzadu.zzaw(zzsp);
            T newInstance = zzgx.newInstance();
            this.zzadu.a++;
            zzgx.zza(newInstance, this, zzel);
            zzgx.zzj(newInstance);
            this.zzadu.zzat(0);
            zzeb zzeb = this.zzadu;
            zzeb.a--;
            this.zzadu.zzax(zzaw);
            return newInstance;
        }
        throw zzfi.g();
    }

    private final <T> T zzd(zzgx<T> zzgx, zzel zzel) {
        int i = this.zzadv;
        this.zzadv = ((this.tag >>> 3) << 3) | 4;
        try {
            T newInstance = zzgx.newInstance();
            zzgx.zza(newInstance, this, zzel);
            zzgx.zzj(newInstance);
            if (this.tag == this.zzadv) {
                return newInstance;
            }
            throw zzfi.h();
        } finally {
            this.zzadv = i;
        }
    }

    public final int getTag() {
        return this.tag;
    }

    public final double readDouble() {
        zzba(1);
        return this.zzadu.readDouble();
    }

    public final float readFloat() {
        zzba(5);
        return this.zzadu.readFloat();
    }

    public final String readString() {
        zzba(2);
        return this.zzadu.readString();
    }

    public final void readStringList(List<String> list) {
        zza(list, false);
    }

    public final <T> T zza(zzgx<T> zzgx, zzel zzel) {
        zzba(2);
        return zzc(zzgx, zzel);
    }

    public final <T> void zza(List<T> list, zzgx<T> zzgx, zzel zzel) {
        int zzsg;
        int i = this.tag;
        if ((i & 7) == 2) {
            do {
                list.add(zzc(zzgx, zzel));
                if (!this.zzadu.zzsw() && this.zzadw == 0) {
                    zzsg = this.zzadu.zzsg();
                } else {
                    return;
                }
            } while (zzsg == i);
            this.zzadw = zzsg;
            return;
        }
        throw zzfi.f();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0051, code lost:
        if (zzsz() != false) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005b, code lost:
        throw new com.google.android.gms.internal.measurement.zzfi("Unable to parse map entry.");
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x004d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <K, V> void zza(java.util.Map<K, V> r6, com.google.android.gms.internal.measurement.zzfz<K, V> r7, com.google.android.gms.internal.measurement.zzel r8) {
        /*
            r5 = this;
            r0 = 2
            r5.zzba(r0)
            com.google.android.gms.internal.measurement.zzeb r0 = r5.zzadu
            int r0 = r0.zzsp()
            com.google.android.gms.internal.measurement.zzeb r1 = r5.zzadu
            int r0 = r1.zzaw(r0)
            K r1 = r7.zzakc
            V r2 = r7.zzaba
        L_0x0014:
            int r3 = r5.zzsy()     // Catch:{ all -> 0x0065 }
            r4 = 2147483647(0x7fffffff, float:NaN)
            if (r3 == r4) goto L_0x005c
            com.google.android.gms.internal.measurement.zzeb r4 = r5.zzadu     // Catch:{ all -> 0x0065 }
            boolean r4 = r4.zzsw()     // Catch:{ all -> 0x0065 }
            if (r4 != 0) goto L_0x005c
            switch(r3) {
                case 1: goto L_0x003a;
                case 2: goto L_0x002d;
                default: goto L_0x0028;
            }
        L_0x0028:
            boolean r3 = r5.zzsz()     // Catch:{ zzfh -> 0x004d }
            goto L_0x0042
        L_0x002d:
            com.google.android.gms.internal.measurement.zzig r3 = r7.zzakd     // Catch:{ zzfh -> 0x004d }
            V r4 = r7.zzaba     // Catch:{ zzfh -> 0x004d }
            java.lang.Class r4 = r4.getClass()     // Catch:{ zzfh -> 0x004d }
            java.lang.Object r2 = r5.zza((com.google.android.gms.internal.measurement.zzig) r3, (java.lang.Class<?>) r4, (com.google.android.gms.internal.measurement.zzel) r8)     // Catch:{ zzfh -> 0x004d }
            goto L_0x0014
        L_0x003a:
            com.google.android.gms.internal.measurement.zzig r3 = r7.zzakb     // Catch:{ zzfh -> 0x004d }
            r4 = 0
            java.lang.Object r1 = r5.zza((com.google.android.gms.internal.measurement.zzig) r3, (java.lang.Class<?>) r4, (com.google.android.gms.internal.measurement.zzel) r4)     // Catch:{ zzfh -> 0x004d }
            goto L_0x0014
        L_0x0042:
            if (r3 == 0) goto L_0x0045
            goto L_0x0014
        L_0x0045:
            com.google.android.gms.internal.measurement.zzfi r3 = new com.google.android.gms.internal.measurement.zzfi     // Catch:{ zzfh -> 0x004d }
            java.lang.String r4 = "Unable to parse map entry."
            r3.<init>(r4)     // Catch:{ zzfh -> 0x004d }
            throw r3     // Catch:{ zzfh -> 0x004d }
        L_0x004d:
            boolean r3 = r5.zzsz()     // Catch:{ all -> 0x0065 }
            if (r3 == 0) goto L_0x0054
            goto L_0x0014
        L_0x0054:
            com.google.android.gms.internal.measurement.zzfi r6 = new com.google.android.gms.internal.measurement.zzfi     // Catch:{ all -> 0x0065 }
            java.lang.String r7 = "Unable to parse map entry."
            r6.<init>(r7)     // Catch:{ all -> 0x0065 }
            throw r6     // Catch:{ all -> 0x0065 }
        L_0x005c:
            r6.put(r1, r2)     // Catch:{ all -> 0x0065 }
            com.google.android.gms.internal.measurement.zzeb r6 = r5.zzadu
            r6.zzax(r0)
            return
        L_0x0065:
            r6 = move-exception
            com.google.android.gms.internal.measurement.zzeb r7 = r5.zzadu
            r7.zzax(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzec.zza(java.util.Map, com.google.android.gms.internal.measurement.zzfz, com.google.android.gms.internal.measurement.zzel):void");
    }

    public final <T> T zzb(zzgx<T> zzgx, zzel zzel) {
        zzba(3);
        return zzd(zzgx, zzel);
    }

    public final <T> void zzb(List<T> list, zzgx<T> zzgx, zzel zzel) {
        int zzsg;
        int i = this.tag;
        if ((i & 7) == 3) {
            do {
                list.add(zzd(zzgx, zzel));
                if (!this.zzadu.zzsw() && this.zzadw == 0) {
                    zzsg = this.zzadu.zzsg();
                } else {
                    return;
                }
            } while (zzsg == i);
            this.zzadw = zzsg;
            return;
        }
        throw zzfi.f();
    }

    public final void zze(List<Double> list) {
        int zzsg;
        int zzsg2;
        if (list instanceof zzeh) {
            zzeh zzeh = (zzeh) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int zzsp = this.zzadu.zzsp();
                    zzbb(zzsp);
                    int zzsx = this.zzadu.zzsx() + zzsp;
                    do {
                        zzeh.zzf(this.zzadu.readDouble());
                    } while (this.zzadu.zzsx() < zzsx);
                    return;
                default:
                    throw zzfi.f();
            }
            do {
                zzeh.zzf(this.zzadu.readDouble());
                if (!this.zzadu.zzsw()) {
                    zzsg2 = this.zzadu.zzsg();
                } else {
                    return;
                }
            } while (zzsg2 == this.tag);
            this.zzadw = zzsg2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int zzsp2 = this.zzadu.zzsp();
                zzbb(zzsp2);
                int zzsx2 = this.zzadu.zzsx() + zzsp2;
                do {
                    list.add(Double.valueOf(this.zzadu.readDouble()));
                } while (this.zzadu.zzsx() < zzsx2);
                return;
            default:
                throw zzfi.f();
        }
        do {
            list.add(Double.valueOf(this.zzadu.readDouble()));
            if (!this.zzadu.zzsw()) {
                zzsg = this.zzadu.zzsg();
            } else {
                return;
            }
        } while (zzsg == this.tag);
        this.zzadw = zzsg;
    }

    public final void zzf(List<Float> list) {
        int zzsg;
        int zzsg2;
        if (list instanceof zzeu) {
            zzeu zzeu = (zzeu) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzsp = this.zzadu.zzsp();
                zzbc(zzsp);
                int zzsx = this.zzadu.zzsx() + zzsp;
                do {
                    zzeu.zzc(this.zzadu.readFloat());
                } while (this.zzadu.zzsx() < zzsx);
            } else if (i == 5) {
                do {
                    zzeu.zzc(this.zzadu.readFloat());
                    if (!this.zzadu.zzsw()) {
                        zzsg2 = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg2 == this.tag);
                this.zzadw = zzsg2;
            } else {
                throw zzfi.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzsp2 = this.zzadu.zzsp();
                zzbc(zzsp2);
                int zzsx2 = this.zzadu.zzsx() + zzsp2;
                do {
                    list.add(Float.valueOf(this.zzadu.readFloat()));
                } while (this.zzadu.zzsx() < zzsx2);
            } else if (i2 == 5) {
                do {
                    list.add(Float.valueOf(this.zzadu.readFloat()));
                    if (!this.zzadu.zzsw()) {
                        zzsg = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg == this.tag);
                this.zzadw = zzsg;
            } else {
                throw zzfi.f();
            }
        }
    }

    public final void zzg(List<Long> list) {
        int zzsg;
        int zzsg2;
        if (list instanceof zzfw) {
            zzfw zzfw = (zzfw) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzfw.zzby(this.zzadu.zzsh());
                    if (!this.zzadu.zzsw()) {
                        zzsg2 = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg2 == this.tag);
                this.zzadw = zzsg2;
            } else if (i == 2) {
                int zzsx = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    zzfw.zzby(this.zzadu.zzsh());
                } while (this.zzadu.zzsx() < zzsx);
                zzbd(zzsx);
            } else {
                throw zzfi.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzadu.zzsh()));
                    if (!this.zzadu.zzsw()) {
                        zzsg = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg == this.tag);
                this.zzadw = zzsg;
            } else if (i2 == 2) {
                int zzsx2 = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    list.add(Long.valueOf(this.zzadu.zzsh()));
                } while (this.zzadu.zzsx() < zzsx2);
                zzbd(zzsx2);
            } else {
                throw zzfi.f();
            }
        }
    }

    public final void zzh(List<Long> list) {
        int zzsg;
        int zzsg2;
        if (list instanceof zzfw) {
            zzfw zzfw = (zzfw) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzfw.zzby(this.zzadu.zzsi());
                    if (!this.zzadu.zzsw()) {
                        zzsg2 = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg2 == this.tag);
                this.zzadw = zzsg2;
            } else if (i == 2) {
                int zzsx = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    zzfw.zzby(this.zzadu.zzsi());
                } while (this.zzadu.zzsx() < zzsx);
                zzbd(zzsx);
            } else {
                throw zzfi.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzadu.zzsi()));
                    if (!this.zzadu.zzsw()) {
                        zzsg = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg == this.tag);
                this.zzadw = zzsg;
            } else if (i2 == 2) {
                int zzsx2 = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    list.add(Long.valueOf(this.zzadu.zzsi()));
                } while (this.zzadu.zzsx() < zzsx2);
                zzbd(zzsx2);
            } else {
                throw zzfi.f();
            }
        }
    }

    public final void zzi(List<Integer> list) {
        int zzsg;
        int zzsg2;
        if (list instanceof zzfa) {
            zzfa zzfa = (zzfa) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzfa.zzbu(this.zzadu.zzsj());
                    if (!this.zzadu.zzsw()) {
                        zzsg2 = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg2 == this.tag);
                this.zzadw = zzsg2;
            } else if (i == 2) {
                int zzsx = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    zzfa.zzbu(this.zzadu.zzsj());
                } while (this.zzadu.zzsx() < zzsx);
                zzbd(zzsx);
            } else {
                throw zzfi.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzadu.zzsj()));
                    if (!this.zzadu.zzsw()) {
                        zzsg = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg == this.tag);
                this.zzadw = zzsg;
            } else if (i2 == 2) {
                int zzsx2 = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    list.add(Integer.valueOf(this.zzadu.zzsj()));
                } while (this.zzadu.zzsx() < zzsx2);
                zzbd(zzsx2);
            } else {
                throw zzfi.f();
            }
        }
    }

    public final void zzj(List<Long> list) {
        int zzsg;
        int zzsg2;
        if (list instanceof zzfw) {
            zzfw zzfw = (zzfw) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int zzsp = this.zzadu.zzsp();
                    zzbb(zzsp);
                    int zzsx = this.zzadu.zzsx() + zzsp;
                    do {
                        zzfw.zzby(this.zzadu.zzsk());
                    } while (this.zzadu.zzsx() < zzsx);
                    return;
                default:
                    throw zzfi.f();
            }
            do {
                zzfw.zzby(this.zzadu.zzsk());
                if (!this.zzadu.zzsw()) {
                    zzsg2 = this.zzadu.zzsg();
                } else {
                    return;
                }
            } while (zzsg2 == this.tag);
            this.zzadw = zzsg2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int zzsp2 = this.zzadu.zzsp();
                zzbb(zzsp2);
                int zzsx2 = this.zzadu.zzsx() + zzsp2;
                do {
                    list.add(Long.valueOf(this.zzadu.zzsk()));
                } while (this.zzadu.zzsx() < zzsx2);
                return;
            default:
                throw zzfi.f();
        }
        do {
            list.add(Long.valueOf(this.zzadu.zzsk()));
            if (!this.zzadu.zzsw()) {
                zzsg = this.zzadu.zzsg();
            } else {
                return;
            }
        } while (zzsg == this.tag);
        this.zzadw = zzsg;
    }

    public final void zzk(List<Integer> list) {
        int zzsg;
        int zzsg2;
        if (list instanceof zzfa) {
            zzfa zzfa = (zzfa) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzsp = this.zzadu.zzsp();
                zzbc(zzsp);
                int zzsx = this.zzadu.zzsx() + zzsp;
                do {
                    zzfa.zzbu(this.zzadu.zzsl());
                } while (this.zzadu.zzsx() < zzsx);
            } else if (i == 5) {
                do {
                    zzfa.zzbu(this.zzadu.zzsl());
                    if (!this.zzadu.zzsw()) {
                        zzsg2 = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg2 == this.tag);
                this.zzadw = zzsg2;
            } else {
                throw zzfi.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzsp2 = this.zzadu.zzsp();
                zzbc(zzsp2);
                int zzsx2 = this.zzadu.zzsx() + zzsp2;
                do {
                    list.add(Integer.valueOf(this.zzadu.zzsl()));
                } while (this.zzadu.zzsx() < zzsx2);
            } else if (i2 == 5) {
                do {
                    list.add(Integer.valueOf(this.zzadu.zzsl()));
                    if (!this.zzadu.zzsw()) {
                        zzsg = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg == this.tag);
                this.zzadw = zzsg;
            } else {
                throw zzfi.f();
            }
        }
    }

    public final void zzl(List<Boolean> list) {
        int zzsg;
        int zzsg2;
        if (list instanceof zzdn) {
            zzdn zzdn = (zzdn) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzdn.addBoolean(this.zzadu.zzsm());
                    if (!this.zzadu.zzsw()) {
                        zzsg2 = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg2 == this.tag);
                this.zzadw = zzsg2;
            } else if (i == 2) {
                int zzsx = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    zzdn.addBoolean(this.zzadu.zzsm());
                } while (this.zzadu.zzsx() < zzsx);
                zzbd(zzsx);
            } else {
                throw zzfi.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Boolean.valueOf(this.zzadu.zzsm()));
                    if (!this.zzadu.zzsw()) {
                        zzsg = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg == this.tag);
                this.zzadw = zzsg;
            } else if (i2 == 2) {
                int zzsx2 = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    list.add(Boolean.valueOf(this.zzadu.zzsm()));
                } while (this.zzadu.zzsx() < zzsx2);
                zzbd(zzsx2);
            } else {
                throw zzfi.f();
            }
        }
    }

    public final void zzm(List<String> list) {
        zza(list, true);
    }

    public final void zzn(List<zzdp> list) {
        int zzsg;
        if ((this.tag & 7) == 2) {
            do {
                list.add(zzso());
                if (!this.zzadu.zzsw()) {
                    zzsg = this.zzadu.zzsg();
                } else {
                    return;
                }
            } while (zzsg == this.tag);
            this.zzadw = zzsg;
            return;
        }
        throw zzfi.f();
    }

    public final void zzo(List<Integer> list) {
        int zzsg;
        int zzsg2;
        if (list instanceof zzfa) {
            zzfa zzfa = (zzfa) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzfa.zzbu(this.zzadu.zzsp());
                    if (!this.zzadu.zzsw()) {
                        zzsg2 = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg2 == this.tag);
                this.zzadw = zzsg2;
            } else if (i == 2) {
                int zzsx = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    zzfa.zzbu(this.zzadu.zzsp());
                } while (this.zzadu.zzsx() < zzsx);
                zzbd(zzsx);
            } else {
                throw zzfi.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzadu.zzsp()));
                    if (!this.zzadu.zzsw()) {
                        zzsg = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg == this.tag);
                this.zzadw = zzsg;
            } else if (i2 == 2) {
                int zzsx2 = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    list.add(Integer.valueOf(this.zzadu.zzsp()));
                } while (this.zzadu.zzsx() < zzsx2);
                zzbd(zzsx2);
            } else {
                throw zzfi.f();
            }
        }
    }

    public final void zzp(List<Integer> list) {
        int zzsg;
        int zzsg2;
        if (list instanceof zzfa) {
            zzfa zzfa = (zzfa) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzfa.zzbu(this.zzadu.zzsq());
                    if (!this.zzadu.zzsw()) {
                        zzsg2 = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg2 == this.tag);
                this.zzadw = zzsg2;
            } else if (i == 2) {
                int zzsx = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    zzfa.zzbu(this.zzadu.zzsq());
                } while (this.zzadu.zzsx() < zzsx);
                zzbd(zzsx);
            } else {
                throw zzfi.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzadu.zzsq()));
                    if (!this.zzadu.zzsw()) {
                        zzsg = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg == this.tag);
                this.zzadw = zzsg;
            } else if (i2 == 2) {
                int zzsx2 = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    list.add(Integer.valueOf(this.zzadu.zzsq()));
                } while (this.zzadu.zzsx() < zzsx2);
                zzbd(zzsx2);
            } else {
                throw zzfi.f();
            }
        }
    }

    public final void zzq(List<Integer> list) {
        int zzsg;
        int zzsg2;
        if (list instanceof zzfa) {
            zzfa zzfa = (zzfa) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzsp = this.zzadu.zzsp();
                zzbc(zzsp);
                int zzsx = this.zzadu.zzsx() + zzsp;
                do {
                    zzfa.zzbu(this.zzadu.zzsr());
                } while (this.zzadu.zzsx() < zzsx);
            } else if (i == 5) {
                do {
                    zzfa.zzbu(this.zzadu.zzsr());
                    if (!this.zzadu.zzsw()) {
                        zzsg2 = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg2 == this.tag);
                this.zzadw = zzsg2;
            } else {
                throw zzfi.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzsp2 = this.zzadu.zzsp();
                zzbc(zzsp2);
                int zzsx2 = this.zzadu.zzsx() + zzsp2;
                do {
                    list.add(Integer.valueOf(this.zzadu.zzsr()));
                } while (this.zzadu.zzsx() < zzsx2);
            } else if (i2 == 5) {
                do {
                    list.add(Integer.valueOf(this.zzadu.zzsr()));
                    if (!this.zzadu.zzsw()) {
                        zzsg = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg == this.tag);
                this.zzadw = zzsg;
            } else {
                throw zzfi.f();
            }
        }
    }

    public final void zzr(List<Long> list) {
        int zzsg;
        int zzsg2;
        if (list instanceof zzfw) {
            zzfw zzfw = (zzfw) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int zzsp = this.zzadu.zzsp();
                    zzbb(zzsp);
                    int zzsx = this.zzadu.zzsx() + zzsp;
                    do {
                        zzfw.zzby(this.zzadu.zzss());
                    } while (this.zzadu.zzsx() < zzsx);
                    return;
                default:
                    throw zzfi.f();
            }
            do {
                zzfw.zzby(this.zzadu.zzss());
                if (!this.zzadu.zzsw()) {
                    zzsg2 = this.zzadu.zzsg();
                } else {
                    return;
                }
            } while (zzsg2 == this.tag);
            this.zzadw = zzsg2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int zzsp2 = this.zzadu.zzsp();
                zzbb(zzsp2);
                int zzsx2 = this.zzadu.zzsx() + zzsp2;
                do {
                    list.add(Long.valueOf(this.zzadu.zzss()));
                } while (this.zzadu.zzsx() < zzsx2);
                return;
            default:
                throw zzfi.f();
        }
        do {
            list.add(Long.valueOf(this.zzadu.zzss()));
            if (!this.zzadu.zzsw()) {
                zzsg = this.zzadu.zzsg();
            } else {
                return;
            }
        } while (zzsg == this.tag);
        this.zzadw = zzsg;
    }

    public final void zzs(List<Integer> list) {
        int zzsg;
        int zzsg2;
        if (list instanceof zzfa) {
            zzfa zzfa = (zzfa) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzfa.zzbu(this.zzadu.zzst());
                    if (!this.zzadu.zzsw()) {
                        zzsg2 = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg2 == this.tag);
                this.zzadw = zzsg2;
            } else if (i == 2) {
                int zzsx = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    zzfa.zzbu(this.zzadu.zzst());
                } while (this.zzadu.zzsx() < zzsx);
                zzbd(zzsx);
            } else {
                throw zzfi.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzadu.zzst()));
                    if (!this.zzadu.zzsw()) {
                        zzsg = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg == this.tag);
                this.zzadw = zzsg;
            } else if (i2 == 2) {
                int zzsx2 = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    list.add(Integer.valueOf(this.zzadu.zzst()));
                } while (this.zzadu.zzsx() < zzsx2);
                zzbd(zzsx2);
            } else {
                throw zzfi.f();
            }
        }
    }

    public final long zzsh() {
        zzba(0);
        return this.zzadu.zzsh();
    }

    public final long zzsi() {
        zzba(0);
        return this.zzadu.zzsi();
    }

    public final int zzsj() {
        zzba(0);
        return this.zzadu.zzsj();
    }

    public final long zzsk() {
        zzba(1);
        return this.zzadu.zzsk();
    }

    public final int zzsl() {
        zzba(5);
        return this.zzadu.zzsl();
    }

    public final boolean zzsm() {
        zzba(0);
        return this.zzadu.zzsm();
    }

    public final String zzsn() {
        zzba(2);
        return this.zzadu.zzsn();
    }

    public final zzdp zzso() {
        zzba(2);
        return this.zzadu.zzso();
    }

    public final int zzsp() {
        zzba(0);
        return this.zzadu.zzsp();
    }

    public final int zzsq() {
        zzba(0);
        return this.zzadu.zzsq();
    }

    public final int zzsr() {
        zzba(5);
        return this.zzadu.zzsr();
    }

    public final long zzss() {
        zzba(1);
        return this.zzadu.zzss();
    }

    public final int zzst() {
        zzba(0);
        return this.zzadu.zzst();
    }

    public final long zzsu() {
        zzba(0);
        return this.zzadu.zzsu();
    }

    public final int zzsy() {
        int i = this.zzadw;
        if (i != 0) {
            this.tag = i;
            this.zzadw = 0;
        } else {
            this.tag = this.zzadu.zzsg();
        }
        int i2 = this.tag;
        if (i2 == 0 || i2 == this.zzadv) {
            return Integer.MAX_VALUE;
        }
        return i2 >>> 3;
    }

    public final boolean zzsz() {
        int i;
        if (this.zzadu.zzsw() || (i = this.tag) == this.zzadv) {
            return false;
        }
        return this.zzadu.zzau(i);
    }

    public final void zzt(List<Long> list) {
        int zzsg;
        int zzsg2;
        if (list instanceof zzfw) {
            zzfw zzfw = (zzfw) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzfw.zzby(this.zzadu.zzsu());
                    if (!this.zzadu.zzsw()) {
                        zzsg2 = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg2 == this.tag);
                this.zzadw = zzsg2;
            } else if (i == 2) {
                int zzsx = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    zzfw.zzby(this.zzadu.zzsu());
                } while (this.zzadu.zzsx() < zzsx);
                zzbd(zzsx);
            } else {
                throw zzfi.f();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzadu.zzsu()));
                    if (!this.zzadu.zzsw()) {
                        zzsg = this.zzadu.zzsg();
                    } else {
                        return;
                    }
                } while (zzsg == this.tag);
                this.zzadw = zzsg;
            } else if (i2 == 2) {
                int zzsx2 = this.zzadu.zzsx() + this.zzadu.zzsp();
                do {
                    list.add(Long.valueOf(this.zzadu.zzsu()));
                } while (this.zzadu.zzsx() < zzsx2);
                zzbd(zzsx2);
            } else {
                throw zzfi.f();
            }
        }
    }
}
