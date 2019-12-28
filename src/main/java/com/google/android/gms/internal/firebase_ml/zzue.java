package com.google.android.gms.internal.firebase_ml;

public final class zzue<K, V> {
    private final V value;
    private final zzuf<K, V> zzboh;
    private final K zzboi;

    private zzue(zzwj zzwj, K k, zzwj zzwj2, V v) {
        this.zzboh = new zzuf<>(zzwj, k, zzwj2, v);
        this.zzboi = k;
        this.value = v;
    }

    static <K, V> int a(zzuf<K, V> zzuf, K k, V v) {
        return zzsu.a(zzuf.zzboj, 1, k) + zzsu.a(zzuf.zzbol, 2, v);
    }

    static <K, V> void a(zzsj zzsj, zzuf<K, V> zzuf, K k, V v) {
        zzsu.a(zzsj, zzuf.zzboj, 1, k);
        zzsu.a(zzsj, zzuf.zzbol, 2, v);
    }

    public static <K, V> zzue<K, V> zza(zzwj zzwj, K k, zzwj zzwj2, V v) {
        return new zzue<>(zzwj, k, zzwj2, v);
    }

    /* access modifiers changed from: package-private */
    public final zzuf<K, V> a() {
        return this.zzboh;
    }

    public final int zzc(int i, K k, V v) {
        return zzsj.zzcl(i) + zzsj.a(a(this.zzboh, k, v));
    }
}
