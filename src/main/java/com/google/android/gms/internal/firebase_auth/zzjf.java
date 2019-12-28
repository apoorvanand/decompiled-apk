package com.google.android.gms.internal.firebase_auth;

import java.util.Iterator;
import java.util.Map;

final class zzjf<T> implements zzjs<T> {
    private final zzjc zzacr;
    private final zzkk<?, ?> zzacs;
    private final boolean zzact;
    private final zzhh<?> zzacu;

    private zzjf(zzkk<?, ?> zzkk, zzhh<?> zzhh, zzjc zzjc) {
        this.zzacs = zzkk;
        this.zzact = zzhh.a(zzjc);
        this.zzacu = zzhh;
        this.zzacr = zzjc;
    }

    static <T> zzjf<T> a(zzkk<?, ?> zzkk, zzhh<?> zzhh, zzjc zzjc) {
        return new zzjf<>(zzkk, zzhh, zzjc);
    }

    public final boolean equals(T t, T t2) {
        if (!this.zzacs.b(t).equals(this.zzacs.b(t2))) {
            return false;
        }
        if (this.zzact) {
            return this.zzacu.a((Object) t).equals(this.zzacu.a((Object) t2));
        }
        return true;
    }

    public final int hashCode(T t) {
        int hashCode = this.zzacs.b(t).hashCode();
        return this.zzact ? (hashCode * 53) + this.zzacu.a((Object) t).hashCode() : hashCode;
    }

    public final T newInstance() {
        return this.zzacr.zzio().zzig();
    }

    public final void zza(T t, zzjp zzjp, zzhf zzhf) {
        boolean z;
        zzkk<?, ?> zzkk = this.zzacs;
        zzhh<?> zzhh = this.zzacu;
        Object c = zzkk.c(t);
        zzhi<?> b = zzhh.b(t);
        do {
            try {
                if (zzjp.zzhg() == Integer.MAX_VALUE) {
                    zzkk.b((Object) t, c);
                    return;
                }
                int tag = zzjp.getTag();
                if (tag == 11) {
                    int i = 0;
                    Object obj = null;
                    zzgf zzgf = null;
                    while (zzjp.zzhg() != Integer.MAX_VALUE) {
                        int tag2 = zzjp.getTag();
                        if (tag2 == 16) {
                            i = zzjp.zzgr();
                            obj = zzhh.a(zzhf, this.zzacr, i);
                        } else if (tag2 == 26) {
                            if (obj != null) {
                                zzhh.a(zzjp, obj, zzhf, b);
                            } else {
                                zzgf = zzjp.zzgq();
                            }
                        } else if (!zzjp.zzhh()) {
                            break;
                        }
                    }
                    if (zzjp.getTag() != 12) {
                        throw zzic.e();
                    } else if (zzgf != null) {
                        if (obj != null) {
                            zzhh.a(zzgf, obj, zzhf, b);
                        } else {
                            zzkk.a(c, i, zzgf);
                        }
                    }
                } else if ((tag & 7) == 2) {
                    Object a = zzhh.a(zzhf, this.zzacr, tag >>> 3);
                    if (a != null) {
                        zzhh.a(zzjp, a, zzhf, b);
                    } else {
                        z = zzkk.a(c, zzjp);
                        continue;
                    }
                } else {
                    z = zzjp.zzhh();
                    continue;
                }
                z = true;
                continue;
            } finally {
                zzkk.b((Object) t, c);
            }
        } while (z);
    }

    public final void zza(T t, zzlh zzlh) {
        int i;
        Object obj;
        Iterator<Map.Entry<?, Object>> it = this.zzacu.a((Object) t).iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            zzhk zzhk = (zzhk) next.getKey();
            if (zzhk.zzhy() != zzle.MESSAGE || zzhk.zzhz() || zzhk.zzia()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if (next instanceof zzif) {
                i = zzhk.zzbq();
                obj = ((zzif) next).zzjc().zzft();
            } else {
                i = zzhk.zzbq();
                obj = next.getValue();
            }
            zzlh.zza(i, obj);
        }
        zzkk<?, ?> zzkk = this.zzacs;
        zzkk.b(zzkk.b(t), zzlh);
    }

    public final void zzd(T t, T t2) {
        zzju.a(this.zzacs, t, t2);
        if (this.zzact) {
            zzju.a(this.zzacu, t, t2);
        }
    }

    public final void zzf(T t) {
        this.zzacs.d(t);
        this.zzacu.c(t);
    }

    public final boolean zzp(T t) {
        return this.zzacu.a((Object) t).isInitialized();
    }

    public final int zzq(T t) {
        zzkk<?, ?> zzkk = this.zzacs;
        int e = zzkk.e(zzkk.b(t)) + 0;
        return this.zzact ? e + this.zzacu.a((Object) t).zzht() : e;
    }
}
