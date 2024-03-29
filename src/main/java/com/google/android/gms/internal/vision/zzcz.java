package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzfy;

public final class zzcz {

    public static final class zza extends zzfy<zza, C0018zza> implements zzhh {
        private static volatile zzhq<zza> zzbf;
        /* access modifiers changed from: private */
        public static final zza zzma = new zza();
        private int zzbg;
        private int zzlx = 1;
        private int zzly = 1;
        private String zzlz = "";

        /* renamed from: com.google.android.gms.internal.vision.zzcz$zza$zza  reason: collision with other inner class name */
        public static final class C0018zza extends zzfy.zza<zza, C0018zza> implements zzhh {
            private C0018zza() {
                super(zza.zzma);
            }

            /* synthetic */ C0018zza(zzda zzda) {
                this();
            }
        }

        static {
            zzfy.a(zza.class, zzma);
        }

        private zza() {
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzda.a[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0018zza((zzda) null);
                case 3:
                    return a((zzhf) zzma, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\f\u0000\u0002\f\u0001\u0003\b\u0002", new Object[]{"zzbg", "zzlx", zzdv.zzah(), "zzly", zzdy.zzah(), "zzlz"});
                case 4:
                    return zzma;
                case 5:
                    zzhq<zza> zzhq = zzbf;
                    if (zzhq == null) {
                        synchronized (zza.class) {
                            zzhq = zzbf;
                            if (zzhq == null) {
                                zzhq = new zzfy.zzb<>(zzma);
                                zzbf = zzhq;
                            }
                        }
                    }
                    return zzhq;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    public static final class zzb extends zzfy<zzb, zza> implements zzhh {
        private static volatile zzhq<zzb> zzbf;
        /* access modifiers changed from: private */
        public static final zzb zzmc = new zzb();
        private zzge<zzh> zzmb = c();

        public static final class zza extends zzfy.zza<zzb, zza> implements zzhh {
            private zza() {
                super(zzb.zzmc);
            }

            /* synthetic */ zza(zzda zzda) {
                this();
            }
        }

        static {
            zzfy.a(zzb.class, zzmc);
        }

        private zzb() {
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzda.a[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza((zzda) null);
                case 3:
                    return a((zzhf) zzmc, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzmb", zzh.class});
                case 4:
                    return zzmc;
                case 5:
                    zzhq<zzb> zzhq = zzbf;
                    if (zzhq == null) {
                        synchronized (zzb.class) {
                            zzhq = zzbf;
                            if (zzhq == null) {
                                zzhq = new zzfy.zzb<>(zzmc);
                                zzbf = zzhq;
                            }
                        }
                    }
                    return zzhq;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    public static final class zzc extends zzfy<zzc, zza> implements zzhh {
        private static volatile zzhq<zzc> zzbf;
        /* access modifiers changed from: private */
        public static final zzc zzmk = new zzc();
        private int zzbg;
        private String zzmd = "";
        private boolean zzme;
        private int zzmf;
        private long zzmg;
        private long zzmh;
        private long zzmi;
        private String zzmj = "";

        public static final class zza extends zzfy.zza<zzc, zza> implements zzhh {
            private zza() {
                super(zzc.zzmk);
            }

            /* synthetic */ zza(zzda zzda) {
                this();
            }
        }

        public enum zzb implements zzgb {
            REASON_UNKNOWN(0),
            REASON_MISSING(1),
            REASON_UPGRADE(2),
            REASON_INVALID(3);
            
            private static final zzgc<zzb> zzdv = null;
            private final int value;

            static {
                zzdv = new zzdb();
            }

            private zzb(int i) {
                this.value = i;
            }

            public static zzgd zzah() {
                return zzdc.a;
            }

            public static zzb zzs(int i) {
                switch (i) {
                    case 0:
                        return REASON_UNKNOWN;
                    case 1:
                        return REASON_MISSING;
                    case 2:
                        return REASON_UPGRADE;
                    case 3:
                        return REASON_INVALID;
                    default:
                        return null;
                }
            }

            public final int zzr() {
                return this.value;
            }
        }

        static {
            zzfy.a(zzc.class, zzmk);
        }

        private zzc() {
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzda.a[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza((zzda) null);
                case 3:
                    return a((zzhf) zzmk, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001\b\u0000\u0002\u0007\u0001\u0003\f\u0002\u0004\u0002\u0003\u0005\u0002\u0004\u0006\u0002\u0005\u0007\b\u0006", new Object[]{"zzbg", "zzmd", "zzme", "zzmf", zzb.zzah(), "zzmg", "zzmh", "zzmi", "zzmj"});
                case 4:
                    return zzmk;
                case 5:
                    zzhq<zzc> zzhq = zzbf;
                    if (zzhq == null) {
                        synchronized (zzc.class) {
                            zzhq = zzbf;
                            if (zzhq == null) {
                                zzhq = new zzfy.zzb<>(zzmk);
                                zzbf = zzhq;
                            }
                        }
                    }
                    return zzhq;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    public static final class zzd extends zzfy<zzd, zza> implements zzhh {
        private static volatile zzhq<zzd> zzbf;
        /* access modifiers changed from: private */
        public static final zzd zzmy = new zzd();
        private int zzbg;
        private String zzmq = "";
        private String zzmr = "";
        private zzge<String> zzms = zzfy.c();
        private int zzmt;
        private String zzmu = "";
        private long zzmv;
        private long zzmw;
        private zzge<zzi> zzmx = c();

        public static final class zza extends zzfy.zza<zzd, zza> implements zzhh {
            private zza() {
                super(zzd.zzmy);
            }

            /* synthetic */ zza(zzda zzda) {
                this();
            }
        }

        public enum zzb implements zzgb {
            RESULT_UNKNOWN(0),
            RESULT_SUCCESS(1),
            RESULT_FAIL(2),
            RESULT_SKIPPED(3);
            
            private static final zzgc<zzb> zzdv = null;
            private final int value;

            static {
                zzdv = new zzdd();
            }

            private zzb(int i) {
                this.value = i;
            }

            public static zzgd zzah() {
                return zzde.a;
            }

            public static zzb zzt(int i) {
                switch (i) {
                    case 0:
                        return RESULT_UNKNOWN;
                    case 1:
                        return RESULT_SUCCESS;
                    case 2:
                        return RESULT_FAIL;
                    case 3:
                        return RESULT_SKIPPED;
                    default:
                        return null;
                }
            }

            public final int zzr() {
                return this.value;
            }
        }

        static {
            zzfy.a(zzd.class, zzmy);
        }

        private zzd() {
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzda.a[i - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza((zzda) null);
                case 3:
                    return a((zzhf) zzmy, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0002\u0000\u0001\b\u0000\u0002\b\u0001\u0003\u001a\u0004\f\u0002\u0005\b\u0003\u0006\u0002\u0004\u0007\u0002\u0005\b\u001b", new Object[]{"zzbg", "zzmq", "zzmr", "zzms", "zzmt", zzb.zzah(), "zzmu", "zzmv", "zzmw", "zzmx", zzi.class});
                case 4:
                    return zzmy;
                case 5:
                    zzhq<zzd> zzhq = zzbf;
                    if (zzhq == null) {
                        synchronized (zzd.class) {
                            zzhq = zzbf;
                            if (zzhq == null) {
                                zzhq = new zzfy.zzb<>(zzmy);
                                zzbf = zzhq;
                            }
                        }
                    }
                    return zzhq;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    public static final class zze extends zzfy<zze, zza> implements zzhh {
        private static volatile zzhq<zze> zzbf;
        /* access modifiers changed from: private */
        public static final zze zznk = new zze();
        private int zzbg;
        private float zzne;
        private float zznf;
        private float zzng;
        private float zznh;
        private float zzni;
        private float zznj;

        public static final class zza extends zzfy.zza<zze, zza> implements zzhh {
            private zza() {
                super(zze.zznk);
            }

            /* synthetic */ zza(zzda zzda) {
                this();
            }
        }

        static {
            zzfy.a(zze.class, zznk);
        }

        private zze() {
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzda.a[i - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zza((zzda) null);
                case 3:
                    return a((zzhf) zznk, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001\u0001\u0000\u0002\u0001\u0001\u0003\u0001\u0002\u0004\u0001\u0003\u0005\u0001\u0004\u0006\u0001\u0005", new Object[]{"zzbg", "zzne", "zznf", "zzng", "zznh", "zzni", "zznj"});
                case 4:
                    return zznk;
                case 5:
                    zzhq<zze> zzhq = zzbf;
                    if (zzhq == null) {
                        synchronized (zze.class) {
                            zzhq = zzbf;
                            if (zzhq == null) {
                                zzhq = new zzfy.zzb<>(zznk);
                                zzbf = zzhq;
                            }
                        }
                    }
                    return zzhq;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    public static final class zzf extends zzfy<zzf, zza> implements zzhh {
        private static volatile zzhq<zzf> zzbf;
        /* access modifiers changed from: private */
        public static final zzf zzno = new zzf();
        private int zzbg;
        private long zzhr;
        private int zzlx;
        private long zznl;
        private long zznm;
        private long zznn;

        public static final class zza extends zzfy.zza<zzf, zza> implements zzhh {
            private zza() {
                super(zzf.zzno);
            }

            /* synthetic */ zza(zzda zzda) {
                this();
            }
        }

        public enum zzb implements zzgb {
            FORMAT_UNKNOWN(0),
            FORMAT_LUMINANCE(1),
            FORMAT_RGB8(2),
            FORMAT_MONOCHROME(3);
            
            private static final zzgc<zzb> zzdv = null;
            private final int value;

            static {
                zzdv = new zzdf();
            }

            private zzb(int i) {
                this.value = i;
            }

            public static zzgd zzah() {
                return zzdg.a;
            }

            public static zzb zzu(int i) {
                switch (i) {
                    case 0:
                        return FORMAT_UNKNOWN;
                    case 1:
                        return FORMAT_LUMINANCE;
                    case 2:
                        return FORMAT_RGB8;
                    case 3:
                        return FORMAT_MONOCHROME;
                    default:
                        return null;
                }
            }

            public final int zzr() {
                return this.value;
            }
        }

        static {
            zzfy.a(zzf.class, zzno);
        }

        private zzf() {
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzda.a[i - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zza((zzda) null);
                case 3:
                    return a((zzhf) zzno, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001\f\u0000\u0002\u0002\u0001\u0003\u0002\u0002\u0004\u0002\u0004\u0005\u0002\u0003", new Object[]{"zzbg", "zzlx", zzb.zzah(), "zznl", "zznm", "zzhr", "zznn"});
                case 4:
                    return zzno;
                case 5:
                    zzhq<zzf> zzhq = zzbf;
                    if (zzhq == null) {
                        synchronized (zzf.class) {
                            zzhq = zzbf;
                            if (zzhq == null) {
                                zzhq = new zzfy.zzb<>(zzno);
                                zzbf = zzhq;
                            }
                        }
                    }
                    return zzhq;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    public static final class zzg extends zzfy<zzg, zza> implements zzhh {
        private static volatile zzhq<zzg> zzbf;
        /* access modifiers changed from: private */
        public static final zzg zznu = new zzg();
        private int zzbg;
        private long zzmv;
        private long zzmw;

        public static final class zza extends zzfy.zza<zzg, zza> implements zzhh {
            private zza() {
                super(zzg.zznu);
            }

            /* synthetic */ zza(zzda zzda) {
                this();
            }
        }

        static {
            zzfy.a(zzg.class, zznu);
        }

        private zzg() {
        }

        public static zzhq<zzg> zzbx() {
            return (zzhq) zznu.a(zzfy.zzg.zzxd, (Object) null, (Object) null);
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzda.a[i - 1]) {
                case 1:
                    return new zzg();
                case 2:
                    return new zza((zzda) null);
                case 3:
                    return a((zzhf) zznu, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0002\u0000\u0002\u0002\u0001", new Object[]{"zzbg", "zzmv", "zzmw"});
                case 4:
                    return zznu;
                case 5:
                    zzhq<zzg> zzhq = zzbf;
                    if (zzhq == null) {
                        synchronized (zzg.class) {
                            zzhq = zzbf;
                            if (zzhq == null) {
                                zzhq = new zzfy.zzb<>(zznu);
                                zzbf = zzhq;
                            }
                        }
                    }
                    return zzhq;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    public static final class zzh extends zzfy<zzh, zza> implements zzhh {
        private static volatile zzhq<zzh> zzbf;
        /* access modifiers changed from: private */
        public static final zzh zznx = new zzh();
        private int zzbg;
        private int zznv;
        private int zznw;

        public static final class zza extends zzfy.zza<zzh, zza> implements zzhh {
            private zza() {
                super(zzh.zznx);
            }

            /* synthetic */ zza(zzda zzda) {
                this();
            }
        }

        static {
            zzfy.a(zzh.class, zznx);
        }

        private zzh() {
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzda.a[i - 1]) {
                case 1:
                    return new zzh();
                case 2:
                    return new zza((zzda) null);
                case 3:
                    return a((zzhf) zznx, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0004\u0000\u0002\u0004\u0001", new Object[]{"zzbg", "zznv", "zznw"});
                case 4:
                    return zznx;
                case 5:
                    zzhq<zzh> zzhq = zzbf;
                    if (zzhq == null) {
                        synchronized (zzh.class) {
                            zzhq = zzbf;
                            if (zzhq == null) {
                                zzhq = new zzfy.zzb<>(zznx);
                                zzbf = zzhq;
                            }
                        }
                    }
                    return zzhq;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    public static final class zzi extends zzfy<zzi, zza> implements zzhh {
        private static volatile zzhq<zzi> zzbf;
        /* access modifiers changed from: private */
        public static final zzi zzoc = new zzi();
        private int zzbg;
        private zzb zzny;
        private int zznz;
        private zze zzoa;
        private zza zzob;

        public static final class zza extends zzfy.zza<zzi, zza> implements zzhh {
            private zza() {
                super(zzi.zzoc);
            }

            /* synthetic */ zza(zzda zzda) {
                this();
            }
        }

        static {
            zzfy.a(zzi.class, zzoc);
        }

        private zzi() {
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzda.a[i - 1]) {
                case 1:
                    return new zzi();
                case 2:
                    return new zza((zzda) null);
                case 3:
                    return a((zzhf) zzoc, "\u0001\u0004\u0000\u0001\u0001\u0011\u0004\u0000\u0000\u0000\u0001\t\u0000\u0002\u0004\u0001\u0010\t\u0002\u0011\t\u0003", new Object[]{"zzbg", "zzny", "zznz", "zzoa", "zzob"});
                case 4:
                    return zzoc;
                case 5:
                    zzhq<zzi> zzhq = zzbf;
                    if (zzhq == null) {
                        synchronized (zzi.class) {
                            zzhq = zzbf;
                            if (zzhq == null) {
                                zzhq = new zzfy.zzb<>(zzoc);
                                zzbf = zzhq;
                            }
                        }
                    }
                    return zzhq;
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
