package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.internal.firebase_ml.zztc;

public final class zzpq {

    public static final class zza extends zztc<zza, C0013zza> implements zzuo {
        private static final zztk<Integer, zzri> zzayv = new zzps();
        /* access modifiers changed from: private */
        public static final zza zzayw = new zza();
        private static volatile zzux<zza> zzcj;
        private zztj zzayu = e();

        /* renamed from: com.google.android.gms.internal.firebase_ml.zzpq$zza$zza  reason: collision with other inner class name */
        public static final class C0013zza extends zztc.zza<zza, C0013zza> implements zzuo {
            private C0013zza() {
                super(zza.zzayw);
            }

            /* synthetic */ C0013zza(zzpr zzpr) {
                this();
            }

            public final C0013zza zzp(Iterable<? extends zzri> iterable) {
                a();
                ((zza) this.a).zzo(iterable);
                return this;
            }
        }

        static {
            zztc.a(zza.class, zzayw);
        }

        private zza() {
        }

        public static C0013zza zzmt() {
            return (C0013zza) zzayw.d();
        }

        /* access modifiers changed from: private */
        public final void zzo(Iterable<? extends zzri> iterable) {
            if (!this.zzayu.zzog()) {
                this.zzayu = zztc.a(this.zzayu);
            }
            for (zzri zzh : iterable) {
                this.zzayu.zzcy(zzh.zzh());
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzpr.a[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0013zza((zzpr) null);
                case 3:
                    return a((zzum) zzayw, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001e", new Object[]{"zzayu", zzri.zzi()});
                case 4:
                    return zzayw;
                case 5:
                    zzux<zza> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zza.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzayw);
                                zzcj = zzux;
                            }
                        }
                    }
                    return zzux;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    public static final class zzb extends zztc<zzb, zza> implements zzuo {
        /* access modifiers changed from: private */
        public static final zzb zzazb = new zzb();
        private static volatile zzux<zzb> zzcj;
        private float zzaio;
        private int zzayx;
        private int zzayy;
        private boolean zzayz;
        private boolean zzaza;
        private int zzcd;
        private int zzgt;

        public static final class zza extends zztc.zza<zzb, zza> implements zzuo {
            private zza() {
                super(zzb.zzazb);
            }

            /* synthetic */ zza(zzpr zzpr) {
                this();
            }
        }

        /* renamed from: com.google.android.gms.internal.firebase_ml.zzpq$zzb$zzb  reason: collision with other inner class name */
        public enum C0014zzb implements zztf {
            CLASSIFICATION_UNKNOWN(0),
            CLASSIFICATION_NONE(1),
            CLASSIFICATION_ALL(2);
            
            private static final zztg<C0014zzb> zzbv = null;
            private final int value;

            static {
                zzbv = new zzpt();
            }

            private C0014zzb(int i) {
                this.value = i;
            }

            public static C0014zzb zzbm(int i) {
                switch (i) {
                    case 0:
                        return CLASSIFICATION_UNKNOWN;
                    case 1:
                        return CLASSIFICATION_NONE;
                    case 2:
                        return CLASSIFICATION_ALL;
                    default:
                        return null;
                }
            }

            public static zzth zzi() {
                return zzpu.a;
            }

            public final int zzh() {
                return this.value;
            }
        }

        public enum zzc implements zztf {
            LANDMARK_UNKNOWN(0),
            LANDMARK_NONE(1),
            LANDMARK_ALL(2),
            LANDMARK_CONTOUR(3);
            
            private static final zztg<zzc> zzbv = null;
            private final int value;

            static {
                zzbv = new zzpv();
            }

            private zzc(int i) {
                this.value = i;
            }

            public static zzc zzbn(int i) {
                switch (i) {
                    case 0:
                        return LANDMARK_UNKNOWN;
                    case 1:
                        return LANDMARK_NONE;
                    case 2:
                        return LANDMARK_ALL;
                    case 3:
                        return LANDMARK_CONTOUR;
                    default:
                        return null;
                }
            }

            public static zzth zzi() {
                return zzpw.a;
            }

            public final int zzh() {
                return this.value;
            }
        }

        public enum zzd implements zztf {
            MODE_UNKNOWN(0),
            MODE_ACCURATE(1),
            MODE_FAST(2),
            MODE_SELFIE(3);
            
            private static final zztg<zzd> zzbv = null;
            private final int value;

            static {
                zzbv = new zzpx();
            }

            private zzd(int i) {
                this.value = i;
            }

            public static zzd zzbo(int i) {
                switch (i) {
                    case 0:
                        return MODE_UNKNOWN;
                    case 1:
                        return MODE_ACCURATE;
                    case 2:
                        return MODE_FAST;
                    case 3:
                        return MODE_SELFIE;
                    default:
                        return null;
                }
            }

            public static zzth zzi() {
                return zzpy.a;
            }

            public final int zzh() {
                return this.value;
            }
        }

        static {
            zztc.a(zzb.class, zzazb);
        }

        private zzb() {
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzpr.a[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza((zzpr) null);
                case 3:
                    return a((zzum) zzazb, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001\f\u0000\u0002\f\u0001\u0003\f\u0002\u0004\u0007\u0003\u0005\u0007\u0004\u0006\u0001\u0005", new Object[]{"zzcd", "zzgt", zzd.zzi(), "zzayx", zzc.zzi(), "zzayy", C0014zzb.zzi(), "zzayz", "zzaza", "zzaio"});
                case 4:
                    return zzazb;
                case 5:
                    zzux<zzb> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zzb.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzazb);
                                zzcj = zzux;
                            }
                        }
                    }
                    return zzux;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }
}
