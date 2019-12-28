package com.google.android.gms.internal.measurement;

final class zzhr extends zzhp<zzhs, zzhs> {
    zzhr() {
    }

    private static void zza(Object obj, zzhs zzhs) {
        ((zzey) obj).zzahz = zzhs;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object a() {
        return zzhs.a();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object a(Object obj) {
        zzhs zzhs = (zzhs) obj;
        zzhs.zzry();
        return zzhs;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void a(Object obj, int i, int i2) {
        ((zzhs) obj).a((i << 3) | 5, (Object) Integer.valueOf(i2));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void a(Object obj, int i, long j) {
        ((zzhs) obj).a(i << 3, (Object) Long.valueOf(j));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void a(Object obj, int i, zzdp zzdp) {
        ((zzhs) obj).a((i << 3) | 2, (Object) zzdp);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void a(Object obj, int i, Object obj2) {
        ((zzhs) obj).a((i << 3) | 3, (Object) (zzhs) obj2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void a(Object obj, zzim zzim) {
        ((zzhs) obj).zzb(zzim);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void a(Object obj, Object obj2) {
        zza(obj, (zzhs) obj2);
    }

    /* access modifiers changed from: package-private */
    public final boolean a(zzgy zzgy) {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object b(Object obj) {
        return ((zzey) obj).zzahz;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void b(Object obj, int i, long j) {
        ((zzhs) obj).a((i << 3) | 1, (Object) Long.valueOf(j));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void b(Object obj, zzim zzim) {
        ((zzhs) obj).a(zzim);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void b(Object obj, Object obj2) {
        zza(obj, (zzhs) obj2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object c(Object obj) {
        zzhs zzhs = ((zzey) obj).zzahz;
        if (zzhs != zzhs.zzwq()) {
            return zzhs;
        }
        zzhs a = zzhs.a();
        zza(obj, a);
        return a;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object c(Object obj, Object obj2) {
        zzhs zzhs = (zzhs) obj;
        zzhs zzhs2 = (zzhs) obj2;
        return zzhs2.equals(zzhs.zzwq()) ? zzhs : zzhs.a(zzhs, zzhs2);
    }

    /* access modifiers changed from: package-private */
    public final void d(Object obj) {
        ((zzey) obj).zzahz.zzry();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ int e(Object obj) {
        return ((zzhs) obj).zzws();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ int f(Object obj) {
        return ((zzhs) obj).zzuk();
    }
}
