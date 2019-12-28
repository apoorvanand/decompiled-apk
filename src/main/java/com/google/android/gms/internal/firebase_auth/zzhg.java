package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzhs;
import java.util.Map;

final class zzhg extends zzhh<Object> {
    zzhg() {
    }

    /* access modifiers changed from: package-private */
    public final int a(Map.Entry<?, ?> entry) {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: package-private */
    public final zzhi<Object> a(Object obj) {
        return ((zzhs.zzb) obj).zzaam;
    }

    /* access modifiers changed from: package-private */
    public final Object a(zzhf zzhf, zzjc zzjc, int i) {
        return zzhf.zza(zzjc, i);
    }

    /* access modifiers changed from: package-private */
    public final <UT, UB> UB a(zzjp zzjp, Object obj, zzhf zzhf, zzhi<Object> zzhi, UB ub, zzkk<UT, UB> zzkk) {
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: package-private */
    public final void a(zzgf zzgf, Object obj, zzhf zzhf, zzhi<Object> zzhi) {
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: package-private */
    public final void a(zzjp zzjp, Object obj, zzhf zzhf, zzhi<Object> zzhi) {
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: package-private */
    public final void a(zzlh zzlh, Map.Entry<?, ?> entry) {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: package-private */
    public final boolean a(zzjc zzjc) {
        return zzjc instanceof zzhs.zzb;
    }

    /* access modifiers changed from: package-private */
    public final zzhi<Object> b(Object obj) {
        zzhs.zzb zzb = (zzhs.zzb) obj;
        if (zzb.zzaam.isImmutable()) {
            zzb.zzaam = (zzhi) zzb.zzaam.clone();
        }
        return zzb.zzaam;
    }

    /* access modifiers changed from: package-private */
    public final void c(Object obj) {
        a(obj).zzfy();
    }
}
