package com.google.android.gms.internal.firebase_auth;

final class zzbb<E> extends zzay<E> {
    static final zzay<Object> a = new zzbb(new Object[0], 0);
    private final transient int size;
    private final transient Object[] zzhc;

    zzbb(Object[] objArr, int i) {
        this.zzhc = objArr;
        this.size = i;
    }

    /* access modifiers changed from: package-private */
    public final int a(Object[] objArr, int i) {
        System.arraycopy(this.zzhc, 0, objArr, i, this.size);
        return i + this.size;
    }

    /* access modifiers changed from: package-private */
    public final Object[] a() {
        return this.zzhc;
    }

    /* access modifiers changed from: package-private */
    public final int b() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public final int c() {
        return this.size;
    }

    public final E get(int i) {
        zzaj.zza(i, this.size);
        return this.zzhc[i];
    }

    public final int size() {
        return this.size;
    }
}
