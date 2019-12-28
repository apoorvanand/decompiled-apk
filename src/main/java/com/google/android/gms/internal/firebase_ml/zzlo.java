package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.internal.firebase_ml.zzpq;
import com.google.android.gms.internal.firebase_ml.zztc;

public final class zzlo {

    public static final class zza extends zztc<zza, C0010zza> implements zzuo {
        /* access modifiers changed from: private */
        public static final zza zzadj = new zza();
        private static volatile zzux<zza> zzcj;
        private zzs zzadg;
        private zzb zzadh;
        private zzr zzadi;
        private int zzcd;

        /* renamed from: com.google.android.gms.internal.firebase_ml.zzlo$zza$zza  reason: collision with other inner class name */
        public static final class C0010zza extends zztc.zza<zza, C0010zza> implements zzuo {
            private C0010zza() {
                super(zza.zzadj);
            }

            /* synthetic */ C0010zza(zzlp zzlp) {
                this();
            }
        }

        static {
            zztc.a(zza.class, zzadj);
        }

        private zza() {
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzlp.a[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0010zza((zzlp) null);
                case 3:
                    return a((zzum) zzadj, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0000\u0002\t\u0001\u0003\t\u0002", new Object[]{"zzcd", "zzadg", "zzadh", "zzadi"});
                case 4:
                    return zzadj;
                case 5:
                    zzux<zza> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zza.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzadj);
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

    public static final class zzaa extends zztc<zzaa, zza> implements zzuo {
        /* access modifiers changed from: private */
        public static final zzaa zzann = new zzaa();
        private static volatile zzux<zzaa> zzcj;
        private zzs zzadg;
        private zztl<zzc> zzanl = f();
        private int zzanm;
        private int zzcd;

        public static final class zza extends zztc.zza<zzaa, zza> implements zzuo {
            private zza() {
                super(zzaa.zzann);
            }

            /* synthetic */ zza(zzlp zzlp) {
                this();
            }
        }

        public enum zzb implements zztf {
            NO_ERROR(0),
            STATUS_SENSITIVE_TOPIC(1),
            STATUS_QUALITY_THRESHOLDED(2),
            STATUS_INTERNAL_ERROR(3);
            
            private static final zztg<zzb> zzbv = null;
            private final int value;

            static {
                zzbv = new zzmt();
            }

            private zzb(int i) {
                this.value = i;
            }

            public static zzb zzbb(int i) {
                switch (i) {
                    case 0:
                        return NO_ERROR;
                    case 1:
                        return STATUS_SENSITIVE_TOPIC;
                    case 2:
                        return STATUS_QUALITY_THRESHOLDED;
                    case 3:
                        return STATUS_INTERNAL_ERROR;
                    default:
                        return null;
                }
            }

            public static zzth zzi() {
                return zzmu.a;
            }

            public final int zzh() {
                return this.value;
            }
        }

        public static final class zzc extends zztc<zzc, zza> implements zzuo {
            /* access modifiers changed from: private */
            public static final zzc zzant = new zzc();
            private static volatile zzux<zzc> zzcj;
            private float zzane;
            private int zzcd;

            public static final class zza extends zztc.zza<zzc, zza> implements zzuo {
                private zza() {
                    super(zzc.zzant);
                }

                /* synthetic */ zza(zzlp zzlp) {
                    this();
                }
            }

            static {
                zztc.a(zzc.class, zzant);
            }

            private zzc() {
            }

            /* access modifiers changed from: protected */
            public final Object a(int i, Object obj, Object obj2) {
                switch (zzlp.a[i - 1]) {
                    case 1:
                        return new zzc();
                    case 2:
                        return new zza((zzlp) null);
                    case 3:
                        return a((zzum) zzant, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0001\u0000", new Object[]{"zzcd", "zzane"});
                    case 4:
                        return zzant;
                    case 5:
                        zzux<zzc> zzux = zzcj;
                        if (zzux == null) {
                            synchronized (zzc.class) {
                                zzux = zzcj;
                                if (zzux == null) {
                                    zzux = new zztc.zzb<>(zzant);
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

        static {
            zztc.a(zzaa.class, zzann);
        }

        private zzaa() {
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzlp.a[i - 1]) {
                case 1:
                    return new zzaa();
                case 2:
                    return new zza((zzlp) null);
                case 3:
                    return a((zzum) zzann, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001\t\u0000\u0002\u001b\u0003\f\u0001", new Object[]{"zzcd", "zzadg", "zzanl", zzc.class, "zzanm", zzb.zzi()});
                case 4:
                    return zzann;
                case 5:
                    zzux<zzaa> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zzaa.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzann);
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

    public static final class zzab extends zztc<zzab, zza> implements zzuo {
        /* access modifiers changed from: private */
        public static final zzab zzanu = new zzab();
        private static volatile zzux<zzab> zzcj;
        private zzs zzadg;
        private zzr zzadi;
        private int zzcd;

        public static final class zza extends zztc.zza<zzab, zza> implements zzuo {
            private zza() {
                super(zzab.zzanu);
            }

            /* synthetic */ zza(zzlp zzlp) {
                this();
            }

            public final zza zze(zzr zzr) {
                a();
                ((zzab) this.a).zza(zzr);
                return this;
            }

            public final zza zzg(zzs.zza zza) {
                a();
                ((zzab) this.a).zza(zza);
                return this;
            }
        }

        static {
            zztc.a(zzab.class, zzanu);
        }

        private zzab() {
        }

        /* access modifiers changed from: private */
        public final void zza(zzr zzr) {
            if (zzr != null) {
                this.zzadi = zzr;
                this.zzcd |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zza(zzs.zza zza2) {
            this.zzadg = (zzs) ((zztc) zza2.zzpx());
            this.zzcd |= 1;
        }

        public static zza zzjz() {
            return (zza) zzanu.d();
        }

        public static zzab zzka() {
            return zzanu;
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzlp.a[i - 1]) {
                case 1:
                    return new zzab();
                case 2:
                    return new zza((zzlp) null);
                case 3:
                    return a((zzum) zzanu, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0000\u0002\t\u0001", new Object[]{"zzcd", "zzadg", "zzadi"});
                case 4:
                    return zzanu;
                case 5:
                    zzux<zzab> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zzab.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzanu);
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

    public static final class zzac extends zztc<zzac, zza> implements zzuo {
        /* access modifiers changed from: private */
        public static final zzac zzaod = new zzac();
        private static volatile zzux<zzac> zzcj;
        private String zzanv = "";
        private String zzanw = "";
        private String zzanx = "";
        private String zzany = "";
        private String zzanz = "";
        private String zzaoa = "";
        private String zzaob = "";
        private zztl<String> zzaoc = zztc.f();
        private int zzcd;

        public static final class zza extends zztc.zza<zzac, zza> implements zzuo {
            private zza() {
                super(zzac.zzaod);
            }

            /* synthetic */ zza(zzlp zzlp) {
                this();
            }

            public final zza zzbn(String str) {
                a();
                ((zzac) this.a).zzbg(str);
                return this;
            }

            public final zza zzbo(String str) {
                a();
                ((zzac) this.a).zzbh(str);
                return this;
            }

            public final zza zzbp(String str) {
                a();
                ((zzac) this.a).zzbi(str);
                return this;
            }

            public final zza zzbq(String str) {
                a();
                ((zzac) this.a).zzbj(str);
                return this;
            }

            public final zza zzbr(String str) {
                a();
                ((zzac) this.a).zzbk(str);
                return this;
            }

            public final zza zzbs(String str) {
                a();
                ((zzac) this.a).zzbl(str);
                return this;
            }

            public final zza zzbt(String str) {
                a();
                ((zzac) this.a).zzbm(str);
                return this;
            }

            public final zza zzn(Iterable<String> iterable) {
                a();
                ((zzac) this.a).zzm(iterable);
                return this;
            }
        }

        static {
            zztc.a(zzac.class, zzaod);
        }

        private zzac() {
        }

        /* access modifiers changed from: private */
        public final void zzbg(String str) {
            if (str != null) {
                this.zzcd |= 1;
                this.zzanv = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzbh(String str) {
            if (str != null) {
                this.zzcd |= 2;
                this.zzanw = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzbi(String str) {
            if (str != null) {
                this.zzcd |= 4;
                this.zzanx = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzbj(String str) {
            if (str != null) {
                this.zzcd |= 8;
                this.zzany = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzbk(String str) {
            if (str != null) {
                this.zzcd |= 16;
                this.zzanz = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzbl(String str) {
            if (str != null) {
                this.zzcd |= 32;
                this.zzaoa = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzbm(String str) {
            if (str != null) {
                this.zzcd |= 64;
                this.zzaob = str;
                return;
            }
            throw new NullPointerException();
        }

        public static zza zzkd() {
            return (zza) zzaod.d();
        }

        public static zzac zzke() {
            return zzaod;
        }

        /* access modifiers changed from: private */
        public final void zzm(Iterable<String> iterable) {
            if (!this.zzaoc.zzog()) {
                this.zzaoc = zztc.a(this.zzaoc);
            }
            zzrl.a(iterable, this.zzaoc);
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzlp.a[i - 1]) {
                case 1:
                    return new zzac();
                case 2:
                    return new zza((zzlp) null);
                case 3:
                    return a((zzum) zzaod, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0001\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\b\u0003\u0005\b\u0004\u0006\b\u0005\u0007\b\u0006\b\u001a", new Object[]{"zzcd", "zzanv", "zzanw", "zzanx", "zzany", "zzanz", "zzaoa", "zzaob", "zzaoc"});
                case 4:
                    return zzaod;
                case 5:
                    zzux<zzac> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zzac.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzaod);
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

        public final String zzkc() {
            return this.zzanz;
        }
    }

    public static final class zzb extends zztc<zzb, zza> implements zzuo {
        /* access modifiers changed from: private */
        public static final zzb zzadl = new zzb();
        private static volatile zzux<zzb> zzcj;
        private int zzadk;
        private int zzcd;
        private int zzqt;

        public static final class zza extends zztc.zza<zzb, zza> implements zzuo {
            private zza() {
                super(zzb.zzadl);
            }

            /* synthetic */ zza(zzlp zzlp) {
                this();
            }
        }

        /* renamed from: com.google.android.gms.internal.firebase_ml.zzlo$zzb$zzb  reason: collision with other inner class name */
        public enum C0011zzb implements zztf {
            UNKNOWN_MODEL_TYPE(0),
            STABLE_MODEL(1),
            LATEST_MODEL(2);
            
            private static final zztg<C0011zzb> zzbv = null;
            private final int value;

            static {
                zzbv = new zzlq();
            }

            private C0011zzb(int i) {
                this.value = i;
            }

            public static C0011zzb zzaj(int i) {
                switch (i) {
                    case 0:
                        return UNKNOWN_MODEL_TYPE;
                    case 1:
                        return STABLE_MODEL;
                    case 2:
                        return LATEST_MODEL;
                    default:
                        return null;
                }
            }

            public static zzth zzi() {
                return zzlr.a;
            }

            public final int zzh() {
                return this.value;
            }
        }

        static {
            zztc.a(zzb.class, zzadl);
        }

        private zzb() {
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzlp.a[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza((zzlp) null);
                case 3:
                    return a((zzum) zzadl, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0004\u0000\u0002\f\u0001", new Object[]{"zzcd", "zzqt", "zzadk", C0011zzb.zzi()});
                case 4:
                    return zzadl;
                case 5:
                    zzux<zzb> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zzb.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzadl);
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

    public static final class zzc extends zztc<zzc, zza> implements zzuo {
        /* access modifiers changed from: private */
        public static final zzc zzadq = new zzc();
        private static volatile zzux<zzc> zzcj;
        private zzs zzadg;
        private zzb zzadh;
        private zzr zzadi;
        private int zzcd;

        public static final class zza extends zztc.zza<zzc, zza> implements zzuo {
            private zza() {
                super(zzc.zzadq);
            }

            /* synthetic */ zza(zzlp zzlp) {
                this();
            }
        }

        static {
            zztc.a(zzc.class, zzadq);
        }

        private zzc() {
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzlp.a[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza((zzlp) null);
                case 3:
                    return a((zzum) zzadq, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0000\u0002\t\u0001\u0003\t\u0002", new Object[]{"zzcd", "zzadg", "zzadh", "zzadi"});
                case 4:
                    return zzadq;
                case 5:
                    zzux<zzc> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zzc.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzadq);
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

    public static final class zzd extends zztc<zzd, zza> implements zzuo {
        /* access modifiers changed from: private */
        public static final zzd zzadr = new zzd();
        private static volatile zzux<zzd> zzcj;
        private zzs zzadg;
        private zzb zzadh;
        private zzr zzadi;
        private int zzcd;

        public static final class zza extends zztc.zza<zzd, zza> implements zzuo {
            private zza() {
                super(zzd.zzadr);
            }

            /* synthetic */ zza(zzlp zzlp) {
                this();
            }
        }

        static {
            zztc.a(zzd.class, zzadr);
        }

        private zzd() {
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzlp.a[i - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza((zzlp) null);
                case 3:
                    return a((zzum) zzadr, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0000\u0002\t\u0001\u0003\t\u0002", new Object[]{"zzcd", "zzadg", "zzadh", "zzadi"});
                case 4:
                    return zzadr;
                case 5:
                    zzux<zzd> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zzd.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzadr);
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

    public static final class zze extends zztc<zze, zza> implements zzuo {
        /* access modifiers changed from: private */
        public static final zze zzads = new zze();
        private static volatile zzux<zze> zzcj;
        private zzs zzadg;
        private zzb zzadh;
        private zzr zzadi;
        private int zzcd;

        public static final class zza extends zztc.zza<zze, zza> implements zzuo {
            private zza() {
                super(zze.zzads);
            }

            /* synthetic */ zza(zzlp zzlp) {
                this();
            }
        }

        static {
            zztc.a(zze.class, zzads);
        }

        private zze() {
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzlp.a[i - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zza((zzlp) null);
                case 3:
                    return a((zzum) zzads, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0000\u0002\t\u0001\u0003\t\u0002", new Object[]{"zzcd", "zzadg", "zzadh", "zzadi"});
                case 4:
                    return zzads;
                case 5:
                    zzux<zze> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zze.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzads);
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

    public static final class zzf extends zztc<zzf, zza> implements zzuo {
        /* access modifiers changed from: private */
        public static final zzf zzadt = new zzf();
        private static volatile zzux<zzf> zzcj;
        private zzs zzadg;
        private zzb zzadh;
        private zzr zzadi;
        private int zzcd;

        public static final class zza extends zztc.zza<zzf, zza> implements zzuo {
            private zza() {
                super(zzf.zzadt);
            }

            /* synthetic */ zza(zzlp zzlp) {
                this();
            }
        }

        static {
            zztc.a(zzf.class, zzadt);
        }

        private zzf() {
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzlp.a[i - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zza((zzlp) null);
                case 3:
                    return a((zzum) zzadt, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0000\u0002\t\u0001\u0003\t\u0002", new Object[]{"zzcd", "zzadg", "zzadh", "zzadi"});
                case 4:
                    return zzadt;
                case 5:
                    zzux<zzf> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zzf.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzadt);
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

    public static final class zzg extends zztc<zzg, zza> implements zzuo {
        /* access modifiers changed from: private */
        public static final zzg zzadu = new zzg();
        private static volatile zzux<zzg> zzcj;
        private zzs zzadg;
        private zzb zzadh;
        private zzr zzadi;
        private int zzcd;

        public static final class zza extends zztc.zza<zzg, zza> implements zzuo {
            private zza() {
                super(zzg.zzadu);
            }

            /* synthetic */ zza(zzlp zzlp) {
                this();
            }
        }

        static {
            zztc.a(zzg.class, zzadu);
        }

        private zzg() {
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzlp.a[i - 1]) {
                case 1:
                    return new zzg();
                case 2:
                    return new zza((zzlp) null);
                case 3:
                    return a((zzum) zzadu, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0000\u0002\t\u0001\u0003\t\u0002", new Object[]{"zzcd", "zzadg", "zzadh", "zzadi"});
                case 4:
                    return zzadu;
                case 5:
                    zzux<zzg> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zzg.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzadu);
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

    public static final class zzh extends zztc<zzh, zza> implements zzuo {
        /* access modifiers changed from: private */
        public static final zzh zzadv = new zzh();
        private static volatile zzux<zzh> zzcj;
        private zzs zzadg;
        private zzb zzadh;
        private zzr zzadi;
        private int zzcd;

        public static final class zza extends zztc.zza<zzh, zza> implements zzuo {
            private zza() {
                super(zzh.zzadv);
            }

            /* synthetic */ zza(zzlp zzlp) {
                this();
            }
        }

        static {
            zztc.a(zzh.class, zzadv);
        }

        private zzh() {
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzlp.a[i - 1]) {
                case 1:
                    return new zzh();
                case 2:
                    return new zza((zzlp) null);
                case 3:
                    return a((zzum) zzadv, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0000\u0002\t\u0001\u0003\t\u0002", new Object[]{"zzcd", "zzadg", "zzadh", "zzadi"});
                case 4:
                    return zzadv;
                case 5:
                    zzux<zzh> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zzh.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzadv);
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

    public static final class zzi extends zztc<zzi, zza> implements zzuo {
        /* access modifiers changed from: private */
        public static final zzi zzadw = new zzi();
        private static volatile zzux<zzi> zzcj;
        private zzs zzadg;
        private zzb zzadh;
        private zzr zzadi;
        private int zzcd;

        public static final class zza extends zztc.zza<zzi, zza> implements zzuo {
            private zza() {
                super(zzi.zzadw);
            }

            /* synthetic */ zza(zzlp zzlp) {
                this();
            }
        }

        static {
            zztc.a(zzi.class, zzadw);
        }

        private zzi() {
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzlp.a[i - 1]) {
                case 1:
                    return new zzi();
                case 2:
                    return new zza((zzlp) null);
                case 3:
                    return a((zzum) zzadw, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0000\u0002\t\u0001\u0003\t\u0002", new Object[]{"zzcd", "zzadg", "zzadh", "zzadi"});
                case 4:
                    return zzadw;
                case 5:
                    zzux<zzi> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zzi.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzadw);
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

    public static final class zzj extends zztc<zzj, zza> implements zzuo {
        /* access modifiers changed from: private */
        public static final zzj zzadx = new zzj();
        private static volatile zzux<zzj> zzcj;
        private zzs zzadg;
        private zzb zzadh;
        private zzr zzadi;
        private int zzcd;

        public static final class zza extends zztc.zza<zzj, zza> implements zzuo {
            private zza() {
                super(zzj.zzadx);
            }

            /* synthetic */ zza(zzlp zzlp) {
                this();
            }
        }

        static {
            zztc.a(zzj.class, zzadx);
        }

        private zzj() {
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzlp.a[i - 1]) {
                case 1:
                    return new zzj();
                case 2:
                    return new zza((zzlp) null);
                case 3:
                    return a((zzum) zzadx, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0000\u0002\t\u0001\u0003\t\u0002", new Object[]{"zzcd", "zzadg", "zzadh", "zzadi"});
                case 4:
                    return zzadx;
                case 5:
                    zzux<zzj> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zzj.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzadx);
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

    public static final class zzk extends zztc<zzk, zza> implements zzuo {
        /* access modifiers changed from: private */
        public static final zzk zzady = new zzk();
        private static volatile zzux<zzk> zzcj;
        private zzs zzadg;
        private zzb zzadh;
        private zzr zzadi;
        private int zzcd;

        public static final class zza extends zztc.zza<zzk, zza> implements zzuo {
            private zza() {
                super(zzk.zzady);
            }

            /* synthetic */ zza(zzlp zzlp) {
                this();
            }
        }

        static {
            zztc.a(zzk.class, zzady);
        }

        private zzk() {
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzlp.a[i - 1]) {
                case 1:
                    return new zzk();
                case 2:
                    return new zza((zzlp) null);
                case 3:
                    return a((zzum) zzady, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0000\u0002\t\u0001\u0003\t\u0002", new Object[]{"zzcd", "zzadg", "zzadh", "zzadi"});
                case 4:
                    return zzady;
                case 5:
                    zzux<zzk> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zzk.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzady);
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

    public static final class zzl extends zztc<zzl, zza> implements zzuo {
        /* access modifiers changed from: private */
        public static final zzl zzaec = new zzl();
        private static volatile zzux<zzl> zzcj;
        private zzo zzadz;
        private long zzaea;
        private int zzaeb;
        private int zzcd;

        public static final class zza extends zztc.zza<zzl, zza> implements zzuo {
            private zza() {
                super(zzl.zzaec);
            }

            /* synthetic */ zza(zzlp zzlp) {
                this();
            }

            public final zza zzb(zzo zzo) {
                a();
                ((zzl) this.a).zza(zzo);
                return this;
            }

            public final zza zzb(zzlv zzlv) {
                a();
                ((zzl) this.a).zza(zzlv);
                return this;
            }

            public final zza zzf(long j) {
                a();
                ((zzl) this.a).zze(j);
                return this;
            }
        }

        static {
            zztc.a(zzl.class, zzaec);
        }

        private zzl() {
        }

        /* access modifiers changed from: private */
        public final void zza(zzo zzo) {
            if (zzo != null) {
                this.zzadz = zzo;
                this.zzcd |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zza(zzlv zzlv) {
            if (zzlv != null) {
                this.zzcd |= 4;
                this.zzaeb = zzlv.zzh();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zze(long j) {
            this.zzcd |= 2;
            this.zzaea = j;
        }

        public static zza zzif() {
            return (zza) zzaec.d();
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzlp.a[i - 1]) {
                case 1:
                    return new zzl();
                case 2:
                    return new zza((zzlp) null);
                case 3:
                    return a((zzum) zzaec, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0000\u0002\u0003\u0001\u0003\f\u0002", new Object[]{"zzcd", "zzadz", "zzaea", "zzaeb", zzlv.zzi()});
                case 4:
                    return zzaec;
                case 5:
                    zzux<zzl> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zzl.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzaec);
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

    public static final class zzm extends zztc<zzm, zza> implements zzuo {
        /* access modifiers changed from: private */
        public static final zzm zzaeg = new zzm();
        private static volatile zzux<zzm> zzcj;
        private zzs zzadg;
        private zzo zzadz;
        private zztl<zzb> zzaed = f();
        private zztl<zzb> zzaee = f();
        private long zzaef;
        private int zzcd;

        public static final class zza extends zztc.zza<zzm, zza> implements zzuo {
            private zza() {
                super(zzm.zzaeg);
            }

            /* synthetic */ zza(zzlp zzlp) {
                this();
            }

            public final zza zzb(zzs.zza zza) {
                a();
                ((zzm) this.a).zza(zza);
                return this;
            }

            public final zza zzc(zzo zzo) {
                a();
                ((zzm) this.a).zza(zzo);
                return this;
            }

            public final zza zzc(Iterable<? extends zzb> iterable) {
                a();
                ((zzm) this.a).zza(iterable);
                return this;
            }

            public final zza zzd(Iterable<? extends zzb> iterable) {
                a();
                ((zzm) this.a).zzb(iterable);
                return this;
            }

            public final zza zzh(long j) {
                a();
                ((zzm) this.a).zzg(j);
                return this;
            }
        }

        public static final class zzb extends zztc<zzb, zza> implements zzuo {
            /* access modifiers changed from: private */
            public static final zzb zzaej = new zzb();
            private static volatile zzux<zzb> zzcj;
            private int zzaeh;
            private zztj zzaei = e();
            private int zzcd;

            public static final class zza extends zztc.zza<zzb, zza> implements zzuo {
                private zza() {
                    super(zzb.zzaej);
                }

                /* synthetic */ zza(zzlp zzlp) {
                    this();
                }

                public final zza zzb(C0012zzb zzb) {
                    a();
                    ((zzb) this.a).zza(zzb);
                    return this;
                }

                public final zza zzf(Iterable<? extends Integer> iterable) {
                    a();
                    ((zzb) this.a).zze(iterable);
                    return this;
                }
            }

            /* renamed from: com.google.android.gms.internal.firebase_ml.zzlo$zzm$zzb$zzb  reason: collision with other inner class name */
            public enum C0012zzb implements zztf {
                UNKNOWN_DATA_TYPE(0),
                TYPE_FLOAT32(1),
                TYPE_INT32(2),
                TYPE_BYTE(3),
                TYPE_LONG(4);
                
                private static final zztg<C0012zzb> zzbv = null;
                private final int value;

                static {
                    zzbv = new zzls();
                }

                private C0012zzb(int i) {
                    this.value = i;
                }

                public static C0012zzb zzak(int i) {
                    switch (i) {
                        case 0:
                            return UNKNOWN_DATA_TYPE;
                        case 1:
                            return TYPE_FLOAT32;
                        case 2:
                            return TYPE_INT32;
                        case 3:
                            return TYPE_BYTE;
                        case 4:
                            return TYPE_LONG;
                        default:
                            return null;
                    }
                }

                public static zzth zzi() {
                    return zzlt.a;
                }

                public final int zzh() {
                    return this.value;
                }
            }

            static {
                zztc.a(zzb.class, zzaej);
            }

            private zzb() {
            }

            /* access modifiers changed from: private */
            public final void zza(C0012zzb zzb) {
                if (zzb != null) {
                    this.zzcd |= 1;
                    this.zzaeh = zzb.zzh();
                    return;
                }
                throw new NullPointerException();
            }

            /* access modifiers changed from: private */
            public final void zze(Iterable<? extends Integer> iterable) {
                if (!this.zzaei.zzog()) {
                    this.zzaei = zztc.a(this.zzaei);
                }
                zzrl.a(iterable, this.zzaei);
            }

            public static zza zzij() {
                return (zza) zzaej.d();
            }

            /* access modifiers changed from: protected */
            public final Object a(int i, Object obj, Object obj2) {
                switch (zzlp.a[i - 1]) {
                    case 1:
                        return new zzb();
                    case 2:
                        return new zza((zzlp) null);
                    case 3:
                        return a((zzum) zzaej, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\f\u0000\u0002\u0016", new Object[]{"zzcd", "zzaeh", C0012zzb.zzi(), "zzaei"});
                    case 4:
                        return zzaej;
                    case 5:
                        zzux<zzb> zzux = zzcj;
                        if (zzux == null) {
                            synchronized (zzb.class) {
                                zzux = zzcj;
                                if (zzux == null) {
                                    zzux = new zztc.zzb<>(zzaej);
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

        static {
            zztc.a(zzm.class, zzaeg);
        }

        private zzm() {
        }

        /* access modifiers changed from: private */
        public final void zza(zzo zzo) {
            if (zzo != null) {
                this.zzadz = zzo;
                this.zzcd |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zza(zzs.zza zza2) {
            this.zzadg = (zzs) ((zztc) zza2.zzpx());
            this.zzcd |= 1;
        }

        /* access modifiers changed from: private */
        public final void zza(Iterable<? extends zzb> iterable) {
            if (!this.zzaed.zzog()) {
                this.zzaed = zztc.a(this.zzaed);
            }
            zzrl.a(iterable, this.zzaed);
        }

        /* access modifiers changed from: private */
        public final void zzb(Iterable<? extends zzb> iterable) {
            if (!this.zzaee.zzog()) {
                this.zzaee = zztc.a(this.zzaee);
            }
            zzrl.a(iterable, this.zzaee);
        }

        /* access modifiers changed from: private */
        public final void zzg(long j) {
            this.zzcd |= 4;
            this.zzaef = j;
        }

        public static zza zzih() {
            return (zza) zzaeg.d();
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzlp.a[i - 1]) {
                case 1:
                    return new zzm();
                case 2:
                    return new zza((zzlp) null);
                case 3:
                    return a((zzum) zzaeg, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0002\u0000\u0001\t\u0000\u0002\t\u0001\u0003\u001b\u0004\u001b\u0005\u0003\u0002", new Object[]{"zzcd", "zzadg", "zzadz", "zzaed", zzb.class, "zzaee", zzb.class, "zzaef"});
                case 4:
                    return zzaeg;
                case 5:
                    zzux<zzm> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zzm.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzaeg);
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

    public static final class zzn extends zztc<zzn, zza> implements zzuo {
        private static final zztk<Integer, zzlv> zzaet = new zzlu();
        /* access modifiers changed from: private */
        public static final zzn zzaev = new zzn();
        private static volatile zzux<zzn> zzcj;
        private long zzaef;
        private zzo zzaeq;
        private zzo zzaer;
        private zztj zzaes = e();
        private boolean zzaeu;
        private int zzcd;

        public static final class zza extends zztc.zza<zzn, zza> implements zzuo {
            private zza() {
                super(zzn.zzaev);
            }

            /* synthetic */ zza(zzlp zzlp) {
                this();
            }

            public final zza zzf(zzo zzo) {
                a();
                ((zzn) this.a).zzd(zzo);
                return this;
            }

            public final zza zzg(zzo zzo) {
                a();
                ((zzn) this.a).zze(zzo);
                return this;
            }

            public final zza zzh(Iterable<? extends zzlv> iterable) {
                a();
                ((zzn) this.a).zzg(iterable);
                return this;
            }

            public final zza zzi(long j) {
                a();
                ((zzn) this.a).zzg(j);
                return this;
            }

            public final zza zzl(boolean z) {
                a();
                ((zzn) this.a).zzk(z);
                return this;
            }
        }

        static {
            zztc.a(zzn.class, zzaev);
        }

        private zzn() {
        }

        /* access modifiers changed from: private */
        public final void zzd(zzo zzo) {
            if (zzo != null) {
                this.zzaeq = zzo;
                this.zzcd |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zze(zzo zzo) {
            if (zzo != null) {
                this.zzaer = zzo;
                this.zzcd |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzg(long j) {
            this.zzcd |= 4;
            this.zzaef = j;
        }

        /* access modifiers changed from: private */
        public final void zzg(Iterable<? extends zzlv> iterable) {
            if (!this.zzaes.zzog()) {
                this.zzaes = zztc.a(this.zzaes);
            }
            for (zzlv zzh : iterable) {
                this.zzaes.zzcy(zzh.zzh());
            }
        }

        public static zza zzil() {
            return (zza) zzaev.d();
        }

        /* access modifiers changed from: private */
        public final void zzk(boolean z) {
            this.zzcd |= 8;
            this.zzaeu = z;
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzlp.a[i - 1]) {
                case 1:
                    return new zzn();
                case 2:
                    return new zza((zzlp) null);
                case 3:
                    return a((zzum) zzaev, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001\t\u0000\u0002\t\u0001\u0003\u001e\u0004\u0003\u0002\u0005\u0007\u0003", new Object[]{"zzcd", "zzaeq", "zzaer", "zzaes", zzlv.zzi(), "zzaef", "zzaeu"});
                case 4:
                    return zzaev;
                case 5:
                    zzux<zzn> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zzn.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzaev);
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

    public static final class zzo extends zztc<zzo, zza> implements zzuo {
        /* access modifiers changed from: private */
        public static final zzo zzafa = new zzo();
        private static volatile zzux<zzo> zzcj;
        private zzu zzaew;
        private zzb zzaex;
        private zzb zzaey;
        private boolean zzaez;
        private int zzcd;

        public static final class zza extends zztc.zza<zzo, zza> implements zzuo {
            private zza() {
                super(zzo.zzafa);
            }

            /* synthetic */ zza(zzlp zzlp) {
                this();
            }

            public final zza zzb(zzu.zza zza) {
                a();
                ((zzo) this.a).zza(zza);
                return this;
            }

            public final zza zzc(zzb zzb) {
                a();
                ((zzo) this.a).zza(zzb);
                return this;
            }

            public final zza zzd(zzb zzb) {
                a();
                ((zzo) this.a).zzb(zzb);
                return this;
            }

            public final zza zzn(boolean z) {
                a();
                ((zzo) this.a).zzm(z);
                return this;
            }
        }

        public static final class zzb extends zztc<zzb, zza> implements zzuo {
            /* access modifiers changed from: private */
            public static final zzb zzaff = new zzb();
            private static volatile zzux<zzb> zzcj;
            private boolean zzafb;
            private boolean zzafc;
            private boolean zzafd;
            private boolean zzafe;
            private int zzcd;

            public static final class zza extends zztc.zza<zzb, zza> implements zzuo {
                private zza() {
                    super(zzb.zzaff);
                }

                /* synthetic */ zza(zzlp zzlp) {
                    this();
                }

                public final zza zzr(boolean z) {
                    a();
                    ((zzb) this.a).zzo(z);
                    return this;
                }

                public final zza zzs(boolean z) {
                    a();
                    ((zzb) this.a).zzp(z);
                    return this;
                }

                public final zza zzt(boolean z) {
                    a();
                    ((zzb) this.a).zzq(z);
                    return this;
                }
            }

            static {
                zztc.a(zzb.class, zzaff);
            }

            private zzb() {
            }

            public static zza zzip() {
                return (zza) zzaff.d();
            }

            /* access modifiers changed from: private */
            public final void zzo(boolean z) {
                this.zzcd |= 1;
                this.zzafb = z;
            }

            /* access modifiers changed from: private */
            public final void zzp(boolean z) {
                this.zzcd |= 2;
                this.zzafc = z;
            }

            /* access modifiers changed from: private */
            public final void zzq(boolean z) {
                this.zzcd |= 4;
                this.zzafd = z;
            }

            /* access modifiers changed from: protected */
            public final Object a(int i, Object obj, Object obj2) {
                switch (zzlp.a[i - 1]) {
                    case 1:
                        return new zzb();
                    case 2:
                        return new zza((zzlp) null);
                    case 3:
                        return a((zzum) zzaff, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u0007\u0000\u0002\u0007\u0001\u0003\u0007\u0002\u0004\u0007\u0003", new Object[]{"zzcd", "zzafb", "zzafc", "zzafd", "zzafe"});
                    case 4:
                        return zzaff;
                    case 5:
                        zzux<zzb> zzux = zzcj;
                        if (zzux == null) {
                            synchronized (zzb.class) {
                                zzux = zzcj;
                                if (zzux == null) {
                                    zzux = new zztc.zzb<>(zzaff);
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

        static {
            zztc.a(zzo.class, zzafa);
        }

        private zzo() {
        }

        /* access modifiers changed from: private */
        public final void zza(zzb zzb2) {
            if (zzb2 != null) {
                this.zzaex = zzb2;
                this.zzcd |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zza(zzu.zza zza2) {
            this.zzaew = (zzu) ((zztc) zza2.zzpx());
            this.zzcd |= 1;
        }

        /* access modifiers changed from: private */
        public final void zzb(zzb zzb2) {
            if (zzb2 != null) {
                this.zzaey = zzb2;
                this.zzcd |= 4;
                return;
            }
            throw new NullPointerException();
        }

        public static zza zzin() {
            return (zza) zzafa.d();
        }

        /* access modifiers changed from: private */
        public final void zzm(boolean z) {
            this.zzcd |= 8;
            this.zzaez = z;
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzlp.a[i - 1]) {
                case 1:
                    return new zzo();
                case 2:
                    return new zza((zzlp) null);
                case 3:
                    return a((zzum) zzafa, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\t\u0000\u0002\t\u0001\u0003\t\u0002\u0004\u0007\u0003", new Object[]{"zzcd", "zzaew", "zzaex", "zzaey", "zzaez"});
                case 4:
                    return zzafa;
                case 5:
                    zzux<zzo> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zzo.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzafa);
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

    public static final class zzp extends zztc<zzp, zza> implements zzuo {
        /* access modifiers changed from: private */
        public static final zzp zzaip = new zzp();
        private static volatile zzux<zzp> zzcj;
        private int zzaij;
        private int zzaik;
        private int zzail;
        private int zzaim;
        private boolean zzain;
        private float zzaio;
        private int zzcd;

        public static final class zza extends zztc.zza<zzp, zza> implements zzuo {
            private zza() {
                super(zzp.zzaip);
            }

            /* synthetic */ zza(zzlp zzlp) {
                this();
            }

            public final zza zzb(zzb zzb) {
                a();
                ((zzp) this.a).zza(zzb);
                return this;
            }

            public final zza zzb(zzc zzc) {
                a();
                ((zzp) this.a).zza(zzc);
                return this;
            }

            public final zza zzb(zzd zzd) {
                a();
                ((zzp) this.a).zza(zzd);
                return this;
            }

            public final zza zzb(zze zze) {
                a();
                ((zzp) this.a).zza(zze);
                return this;
            }

            public final zza zzl(float f) {
                a();
                ((zzp) this.a).zzk(f);
                return this;
            }

            public final zza zzv(boolean z) {
                a();
                ((zzp) this.a).zzu(z);
                return this;
            }
        }

        public enum zzb implements zztf {
            UNKNOWN_CLASSIFICATIONS(0),
            NO_CLASSIFICATIONS(1),
            ALL_CLASSIFICATIONS(2);
            
            private static final zztg<zzb> zzbv = null;
            private final int value;

            static {
                zzbv = new zzmb();
            }

            private zzb(int i) {
                this.value = i;
            }

            public static zzb zzan(int i) {
                switch (i) {
                    case 0:
                        return UNKNOWN_CLASSIFICATIONS;
                    case 1:
                        return NO_CLASSIFICATIONS;
                    case 2:
                        return ALL_CLASSIFICATIONS;
                    default:
                        return null;
                }
            }

            public static zzth zzi() {
                return zzmc.a;
            }

            public final int zzh() {
                return this.value;
            }
        }

        public enum zzc implements zztf {
            UNKNOWN_CONTOURS(0),
            NO_CONTOURS(1),
            ALL_CONTOURS(2);
            
            private static final zztg<zzc> zzbv = null;
            private final int value;

            static {
                zzbv = new zzmd();
            }

            private zzc(int i) {
                this.value = i;
            }

            public static zzc zzao(int i) {
                switch (i) {
                    case 0:
                        return UNKNOWN_CONTOURS;
                    case 1:
                        return NO_CONTOURS;
                    case 2:
                        return ALL_CONTOURS;
                    default:
                        return null;
                }
            }

            public static zzth zzi() {
                return zzme.a;
            }

            public final int zzh() {
                return this.value;
            }
        }

        public enum zzd implements zztf {
            UNKNOWN_LANDMARKS(0),
            NO_LANDMARKS(1),
            ALL_LANDMARKS(2);
            
            private static final zztg<zzd> zzbv = null;
            private final int value;

            static {
                zzbv = new zzmf();
            }

            private zzd(int i) {
                this.value = i;
            }

            public static zzd zzap(int i) {
                switch (i) {
                    case 0:
                        return UNKNOWN_LANDMARKS;
                    case 1:
                        return NO_LANDMARKS;
                    case 2:
                        return ALL_LANDMARKS;
                    default:
                        return null;
                }
            }

            public static zzth zzi() {
                return zzmg.a;
            }

            public final int zzh() {
                return this.value;
            }
        }

        public enum zze implements zztf {
            UNKNOWN_PERFORMANCE(0),
            FAST(1),
            ACCURATE(2);
            
            private static final zztg<zze> zzbv = null;
            private final int value;

            static {
                zzbv = new zzmh();
            }

            private zze(int i) {
                this.value = i;
            }

            public static zze zzaq(int i) {
                switch (i) {
                    case 0:
                        return UNKNOWN_PERFORMANCE;
                    case 1:
                        return FAST;
                    case 2:
                        return ACCURATE;
                    default:
                        return null;
                }
            }

            public static zzth zzi() {
                return zzmi.a;
            }

            public final int zzh() {
                return this.value;
            }
        }

        static {
            zztc.a(zzp.class, zzaip);
        }

        private zzp() {
        }

        /* access modifiers changed from: private */
        public final void zza(zzb zzb2) {
            if (zzb2 != null) {
                this.zzcd |= 2;
                this.zzaik = zzb2.zzh();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zza(zzc zzc2) {
            if (zzc2 != null) {
                this.zzcd |= 8;
                this.zzaim = zzc2.zzh();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zza(zzd zzd2) {
            if (zzd2 != null) {
                this.zzcd |= 1;
                this.zzaij = zzd2.zzh();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zza(zze zze2) {
            if (zze2 != null) {
                this.zzcd |= 4;
                this.zzail = zze2.zzh();
                return;
            }
            throw new NullPointerException();
        }

        public static zza zzir() {
            return (zza) zzaip.d();
        }

        /* access modifiers changed from: private */
        public final void zzk(float f) {
            this.zzcd |= 32;
            this.zzaio = f;
        }

        /* access modifiers changed from: private */
        public final void zzu(boolean z) {
            this.zzcd |= 16;
            this.zzain = z;
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzlp.a[i - 1]) {
                case 1:
                    return new zzp();
                case 2:
                    return new zza((zzlp) null);
                case 3:
                    return a((zzum) zzaip, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001\f\u0000\u0002\f\u0001\u0003\f\u0002\u0004\f\u0003\u0005\u0007\u0004\u0006\u0001\u0005", new Object[]{"zzcd", "zzaij", zzd.zzi(), "zzaik", zzb.zzi(), "zzail", zze.zzi(), "zzaim", zzc.zzi(), "zzain", "zzaio"});
                case 4:
                    return zzaip;
                case 5:
                    zzux<zzp> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zzp.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzaip);
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

    public static final class zzq extends zztc<zzq, zza> implements zzuo {
        /* access modifiers changed from: private */
        public static final zzq zzakb = new zzq();
        private static volatile zzux<zzq> zzcj;
        private zzac zzajg;
        private int zzajh;
        private zzl zzaji;
        private zzm zzajj;
        private zzn zzajk;
        private zzw zzajl;
        private zzab zzajm;
        private zzv zzajn;
        private zzx zzajo;
        private zzaa zzajp;
        private zzz zzajq;
        private zzd zzajr;
        private zza zzajs;
        private zzc zzajt;
        private zzf zzaju;
        private zze zzajv;
        private zzg zzajw;
        private zzh zzajx;
        private zzi zzajy;
        private zzj zzajz;
        private zzk zzaka;
        private int zzcd;

        public static final class zza extends zztc.zza<zzq, zza> implements zzuo {
            private zza() {
                super(zzq.zzakb);
            }

            /* synthetic */ zza(zzlp zzlp) {
                this();
            }

            public final zza zzb(zzab.zza zza) {
                a();
                ((zzq) this.a).zza(zza);
                return this;
            }

            public final zza zzb(zzab zzab) {
                a();
                ((zzq) this.a).zza(zzab);
                return this;
            }

            public final zza zzb(zzac.zza zza) {
                a();
                ((zzq) this.a).zza(zza);
                return this;
            }

            public final zza zzb(zzl.zza zza) {
                a();
                ((zzq) this.a).zza(zza);
                return this;
            }

            public final zza zzb(zzm.zza zza) {
                a();
                ((zzq) this.a).zza(zza);
                return this;
            }

            public final zza zzb(zzm zzm) {
                a();
                ((zzq) this.a).zza(zzm);
                return this;
            }

            public final zza zzb(zzn.zza zza) {
                a();
                ((zzq) this.a).zza(zza);
                return this;
            }

            public final zza zzb(zzv.zzc zzc) {
                a();
                ((zzq) this.a).zza(zzc);
                return this;
            }

            public final zza zzb(zzw.zza zza) {
                a();
                ((zzq) this.a).zza(zza);
                return this;
            }

            public final zza zzb(zzx.zza zza) {
                a();
                ((zzq) this.a).zza(zza);
                return this;
            }

            public final zza zzb(zzz.zza zza) {
                a();
                ((zzq) this.a).zza(zza);
                return this;
            }

            public final zza zzb(zzly zzly) {
                a();
                ((zzq) this.a).zza(zzly);
                return this;
            }

            public final zzac zzit() {
                return ((zzq) this.a).zzit();
            }
        }

        static {
            zztc.a(zzq.class, zzakb);
        }

        private zzq() {
        }

        /* access modifiers changed from: private */
        public final void zza(zzab.zza zza2) {
            this.zzajm = (zzab) ((zztc) zza2.zzpx());
            this.zzcd |= 64;
        }

        /* access modifiers changed from: private */
        public final void zza(zzab zzab) {
            if (zzab != null) {
                this.zzajm = zzab;
                this.zzcd |= 64;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zza(zzac.zza zza2) {
            this.zzajg = (zzac) ((zztc) zza2.zzpx());
            this.zzcd |= 1;
        }

        /* access modifiers changed from: private */
        public final void zza(zzl.zza zza2) {
            this.zzaji = (zzl) ((zztc) zza2.zzpx());
            this.zzcd |= 4;
        }

        /* access modifiers changed from: private */
        public final void zza(zzm.zza zza2) {
            this.zzajj = (zzm) ((zztc) zza2.zzpx());
            this.zzcd |= 8;
        }

        /* access modifiers changed from: private */
        public final void zza(zzm zzm) {
            if (zzm != null) {
                this.zzajj = zzm;
                this.zzcd |= 8;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zza(zzn.zza zza2) {
            this.zzajk = (zzn) ((zztc) zza2.zzpx());
            this.zzcd |= 16;
        }

        /* access modifiers changed from: private */
        public final void zza(zzv.zzc zzc) {
            this.zzajn = (zzv) ((zztc) zzc.zzpx());
            this.zzcd |= 128;
        }

        /* access modifiers changed from: private */
        public final void zza(zzw.zza zza2) {
            this.zzajl = (zzw) ((zztc) zza2.zzpx());
            this.zzcd |= 32;
        }

        /* access modifiers changed from: private */
        public final void zza(zzx.zza zza2) {
            this.zzajo = (zzx) ((zztc) zza2.zzpx());
            this.zzcd |= 256;
        }

        /* access modifiers changed from: private */
        public final void zza(zzz.zza zza2) {
            this.zzajq = (zzz) ((zztc) zza2.zzpx());
            this.zzcd |= 1024;
        }

        /* access modifiers changed from: private */
        public final void zza(zzly zzly) {
            if (zzly != null) {
                this.zzcd |= 2;
                this.zzajh = zzly.zzh();
                return;
            }
            throw new NullPointerException();
        }

        public static zza zziu() {
            return (zza) zzakb.d();
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzlp.a[i - 1]) {
                case 1:
                    return new zzq();
                case 2:
                    return new zza((zzlp) null);
                case 3:
                    return a((zzum) zzakb, "\u0001\u0015\u0000\u0001\u0001\u0015\u0015\u0000\u0000\u0000\u0001\t\u0000\u0002\f\u0001\u0003\t\u0002\u0004\t\u0003\u0005\t\u0005\u0006\t\u0006\u0007\t\u0007\b\t\u000b\t\t\f\n\t\r\u000b\t\u000e\f\t\u000f\r\t\u0010\u000e\t\u0011\u000f\t\u0012\u0010\t\u0013\u0011\t\u0014\u0012\t\b\u0013\t\t\u0014\t\u0004\u0015\t\n", new Object[]{"zzcd", "zzajg", "zzajh", zzly.zzi(), "zzaji", "zzajj", "zzajl", "zzajm", "zzajn", "zzajr", "zzajs", "zzajt", "zzaju", "zzajv", "zzajw", "zzajx", "zzajy", "zzajz", "zzaka", "zzajo", "zzajp", "zzajk", "zzajq"});
                case 4:
                    return zzakb;
                case 5:
                    zzux<zzq> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zzq.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzakb);
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

        public final zzac zzit() {
            zzac zzac = this.zzajg;
            return zzac == null ? zzac.zzke() : zzac;
        }
    }

    public static final class zzr extends zztc<zzr, zza> implements zzuo {
        /* access modifiers changed from: private */
        public static final zzr zzakf = new zzr();
        private static volatile zzux<zzr> zzcj;
        private int zzakc;
        private int zzakd;
        private int zzake;
        private int zzcd;

        public static final class zza extends zztc.zza<zzr, zza> implements zzuo {
            private zza() {
                super(zzr.zzakf);
            }

            /* synthetic */ zza(zzlp zzlp) {
                this();
            }

            public final zza zzas(int i) {
                a();
                ((zzr) this.a).zzar(i);
                return this;
            }

            public final zza zzb(zzb zzb) {
                a();
                ((zzr) this.a).zza(zzb);
                return this;
            }
        }

        public enum zzb implements zztf {
            UNKNOWN_FORMAT(0),
            NV16(1),
            NV21(2),
            YV12(3),
            BITMAP(4),
            CM_SAMPLE_BUFFER_REF(5),
            UI_IMAGE(6);
            
            private static final zztg<zzb> zzbv = null;
            private final int value;

            static {
                zzbv = new zzmj();
            }

            private zzb(int i) {
                this.value = i;
            }

            public static zzb zzat(int i) {
                switch (i) {
                    case 0:
                        return UNKNOWN_FORMAT;
                    case 1:
                        return NV16;
                    case 2:
                        return NV21;
                    case 3:
                        return YV12;
                    case 4:
                        return BITMAP;
                    case 5:
                        return CM_SAMPLE_BUFFER_REF;
                    case 6:
                        return UI_IMAGE;
                    default:
                        return null;
                }
            }

            public static zzth zzi() {
                return zzmk.a;
            }

            public final int zzh() {
                return this.value;
            }
        }

        static {
            zztc.a(zzr.class, zzakf);
        }

        private zzr() {
        }

        /* access modifiers changed from: private */
        public final void zza(zzb zzb2) {
            if (zzb2 != null) {
                this.zzcd |= 1;
                this.zzakc = zzb2.zzh();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzar(int i) {
            this.zzcd |= 2;
            this.zzakd = i;
        }

        public static zza zziw() {
            return (zza) zzakf.d();
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzlp.a[i - 1]) {
                case 1:
                    return new zzr();
                case 2:
                    return new zza((zzlp) null);
                case 3:
                    return a((zzum) zzakf, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\f\u0000\u0002\u000b\u0001\u0003\u000b\u0002", new Object[]{"zzcd", "zzakc", zzb.zzi(), "zzakd", "zzake"});
                case 4:
                    return zzakf;
                case 5:
                    zzux<zzr> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zzr.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzakf);
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

    public static final class zzs extends zztc<zzs, zza> implements zzuo {
        /* access modifiers changed from: private */
        public static final zzs zzaks = new zzs();
        private static volatile zzux<zzs> zzcj;
        private int zzaeb;
        private boolean zzaeu;
        private long zzako;
        private boolean zzakp;
        private boolean zzakq;
        private boolean zzakr;
        private int zzcd;

        public static final class zza extends zztc.zza<zzs, zza> implements zzuo {
            private zza() {
                super(zzs.zzaks);
            }

            /* synthetic */ zza(zzlp zzlp) {
                this();
            }

            public final zza zzaa(boolean z) {
                a();
                ((zzs) this.a).zzx(true);
                return this;
            }

            public final zza zzab(boolean z) {
                a();
                ((zzs) this.a).zzy(true);
                return this;
            }

            public final zza zzac(boolean z) {
                a();
                ((zzs) this.a).zzk(z);
                return this;
            }

            public final zza zzc(zzlv zzlv) {
                a();
                ((zzs) this.a).zza(zzlv);
                return this;
            }

            public final zza zzk(long j) {
                a();
                ((zzs) this.a).zzj(j);
                return this;
            }

            public final zza zzz(boolean z) {
                a();
                ((zzs) this.a).zzw(z);
                return this;
            }
        }

        static {
            zztc.a(zzs.class, zzaks);
        }

        private zzs() {
        }

        /* access modifiers changed from: private */
        public final void zza(zzlv zzlv) {
            if (zzlv != null) {
                this.zzcd |= 2;
                this.zzaeb = zzlv.zzh();
                return;
            }
            throw new NullPointerException();
        }

        public static zza zziy() {
            return (zza) zzaks.d();
        }

        /* access modifiers changed from: private */
        public final void zzj(long j) {
            this.zzcd |= 1;
            this.zzako = j;
        }

        /* access modifiers changed from: private */
        public final void zzk(boolean z) {
            this.zzcd |= 32;
            this.zzaeu = z;
        }

        /* access modifiers changed from: private */
        public final void zzw(boolean z) {
            this.zzcd |= 4;
            this.zzakp = z;
        }

        /* access modifiers changed from: private */
        public final void zzx(boolean z) {
            this.zzcd |= 8;
            this.zzakq = z;
        }

        /* access modifiers changed from: private */
        public final void zzy(boolean z) {
            this.zzcd |= 16;
            this.zzakr = z;
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzlp.a[i - 1]) {
                case 1:
                    return new zzs();
                case 2:
                    return new zza((zzlp) null);
                case 3:
                    return a((zzum) zzaks, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001\u0003\u0000\u0002\f\u0001\u0003\u0007\u0002\u0004\u0007\u0003\u0005\u0007\u0004\u0006\u0007\u0005", new Object[]{"zzcd", "zzako", "zzaeb", zzlv.zzi(), "zzakp", "zzakq", "zzakr", "zzaeu"});
                case 4:
                    return zzaks;
                case 5:
                    zzux<zzs> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zzs.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzaks);
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

    public static final class zzt extends zztc<zzt, zza> implements zzuo {
        /* access modifiers changed from: private */
        public static final zzt zzakv = new zzt();
        private static volatile zzux<zzt> zzcj;
        private float zzakt;
        private float zzaku;
        private int zzcd;
        private float zzgm;

        public static final class zza extends zztc.zza<zzt, zza> implements zzuo {
            private zza() {
                super(zzt.zzakv);
            }

            /* synthetic */ zza(zzlp zzlp) {
                this();
            }

            public final zza zzn(float f) {
                a();
                ((zzt) this.a).zzm(f);
                return this;
            }
        }

        static {
            zztc.a(zzt.class, zzakv);
        }

        private zzt() {
        }

        public static zza zzja() {
            return (zza) zzakv.d();
        }

        public static zzt zzjb() {
            return zzakv;
        }

        /* access modifiers changed from: private */
        public final void zzm(float f) {
            this.zzcd |= 4;
            this.zzgm = f;
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzlp.a[i - 1]) {
                case 1:
                    return new zzt();
                case 2:
                    return new zza((zzlp) null);
                case 3:
                    return a((zzum) zzakv, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0001\u0000\u0002\u0001\u0001\u0003\u0001\u0002", new Object[]{"zzcd", "zzakt", "zzaku", "zzgm"});
                case 4:
                    return zzakv;
                case 5:
                    zzux<zzt> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zzt.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzakv);
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

    public static final class zzu extends zztc<zzu, zza> implements zzuo {
        /* access modifiers changed from: private */
        public static final zzu zzalb = new zzu();
        private static volatile zzux<zzu> zzcj;
        private String zzakw = "";
        private String zzakx = "";
        private int zzaky;
        private String zzakz = "";
        private String zzala = "";
        private int zzcd;

        public static final class zza extends zztc.zza<zzu, zza> implements zzuo {
            private zza() {
                super(zzu.zzalb);
            }

            /* synthetic */ zza(zzlp zzlp) {
                this();
            }

            public final zza zzb(zzb zzb) {
                a();
                ((zzu) this.a).zza(zzb);
                return this;
            }

            public final zza zzbb(String str) {
                a();
                ((zzu) this.a).setName(str);
                return this;
            }

            public final zza zzbc(String str) {
                a();
                ((zzu) this.a).zzaz(str);
                return this;
            }

            public final zza zzbd(String str) {
                a();
                ((zzu) this.a).zzba(str);
                return this;
            }
        }

        public enum zzb implements zztf {
            SOURCE_UNKNOWN(0),
            APP_ASSET(1),
            LOCAL(2),
            CLOUD(3),
            SDK_BUILT_IN(4);
            
            private static final zztg<zzb> zzbv = null;
            private final int value;

            static {
                zzbv = new zzml();
            }

            private zzb(int i) {
                this.value = i;
            }

            public static zzb zzau(int i) {
                switch (i) {
                    case 0:
                        return SOURCE_UNKNOWN;
                    case 1:
                        return APP_ASSET;
                    case 2:
                        return LOCAL;
                    case 3:
                        return CLOUD;
                    case 4:
                        return SDK_BUILT_IN;
                    default:
                        return null;
                }
            }

            public static zzth zzi() {
                return zzmm.a;
            }

            public final int zzh() {
                return this.value;
            }
        }

        static {
            zztc.a(zzu.class, zzalb);
        }

        private zzu() {
        }

        /* access modifiers changed from: private */
        public final void setName(String str) {
            if (str != null) {
                this.zzcd |= 1;
                this.zzakw = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zza(zzb zzb2) {
            if (zzb2 != null) {
                this.zzcd |= 4;
                this.zzaky = zzb2.zzh();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzaz(String str) {
            if (str != null) {
                this.zzcd |= 8;
                this.zzakz = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzba(String str) {
            if (str != null) {
                this.zzcd |= 16;
                this.zzala = str;
                return;
            }
            throw new NullPointerException();
        }

        public static zza zzjd() {
            return (zza) zzalb.d();
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzlp.a[i - 1]) {
                case 1:
                    return new zzu();
                case 2:
                    return new zza((zzlp) null);
                case 3:
                    return a((zzum) zzalb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\f\u0002\u0004\b\u0003\u0005\b\u0004", new Object[]{"zzcd", "zzakw", "zzakx", "zzaky", zzb.zzi(), "zzakz", "zzala"});
                case 4:
                    return zzalb;
                case 5:
                    zzux<zzu> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zzu.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzalb);
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

    public static final class zzv extends zztc<zzv, zzc> implements zzuo {
        private static final zztk<Integer, zza> zzalk = new zzmn();
        private static final zztk<Integer, zzb> zzalm = new zzmo();
        /* access modifiers changed from: private */
        public static final zzv zzaln = new zzv();
        private static volatile zzux<zzv> zzcj;
        private zzs zzadg;
        private zzr zzadi;
        private zzpq.zza zzali;
        private zztj zzalj = e();
        private zztj zzall = e();
        private int zzcd;

        public enum zza implements zztf {
            FORMAT_UNKNOWN(0),
            FORMAT_CODE_128(1),
            FORMAT_CODE_39(2),
            FORMAT_CODE_93(4),
            FORMAT_CODABAR(8),
            FORMAT_DATA_MATRIX(16),
            FORMAT_EAN_13(32),
            FORMAT_EAN_8(64),
            FORMAT_ITF(128),
            FORMAT_QR_CODE(256),
            FORMAT_UPC_A(512),
            FORMAT_UPC_E(1024),
            FORMAT_PDF417(2048),
            FORMAT_AZTEC(4096);
            
            private static final zztg<zza> zzbv = null;
            private final int value;

            static {
                zzbv = new zzmp();
            }

            private zza(int i) {
                this.value = i;
            }

            public static zza zzav(int i) {
                switch (i) {
                    case 0:
                        return FORMAT_UNKNOWN;
                    case 1:
                        return FORMAT_CODE_128;
                    case 2:
                        return FORMAT_CODE_39;
                    case 4:
                        return FORMAT_CODE_93;
                    case 8:
                        return FORMAT_CODABAR;
                    case 16:
                        return FORMAT_DATA_MATRIX;
                    case 32:
                        return FORMAT_EAN_13;
                    case 64:
                        return FORMAT_EAN_8;
                    case 128:
                        return FORMAT_ITF;
                    case 256:
                        return FORMAT_QR_CODE;
                    case 512:
                        return FORMAT_UPC_A;
                    case 1024:
                        return FORMAT_UPC_E;
                    case 2048:
                        return FORMAT_PDF417;
                    case 4096:
                        return FORMAT_AZTEC;
                    default:
                        return null;
                }
            }

            public static zzth zzi() {
                return zzmq.a;
            }

            public final int zzh() {
                return this.value;
            }
        }

        public enum zzb implements zztf {
            TYPE_UNKNOWN(0),
            TYPE_CONTACT_INFO(1),
            TYPE_EMAIL(2),
            TYPE_ISBN(3),
            TYPE_PHONE(4),
            TYPE_PRODUCT(5),
            TYPE_SMS(6),
            TYPE_TEXT(7),
            TYPE_URL(8),
            TYPE_WIFI(9),
            TYPE_GEO(10),
            TYPE_CALENDAR_EVENT(11),
            TYPE_DRIVER_LICENSE(12);
            
            private static final zztg<zzb> zzbv = null;
            private final int value;

            static {
                zzbv = new zzmr();
            }

            private zzb(int i) {
                this.value = i;
            }

            public static zzb zzaw(int i) {
                switch (i) {
                    case 0:
                        return TYPE_UNKNOWN;
                    case 1:
                        return TYPE_CONTACT_INFO;
                    case 2:
                        return TYPE_EMAIL;
                    case 3:
                        return TYPE_ISBN;
                    case 4:
                        return TYPE_PHONE;
                    case 5:
                        return TYPE_PRODUCT;
                    case 6:
                        return TYPE_SMS;
                    case 7:
                        return TYPE_TEXT;
                    case 8:
                        return TYPE_URL;
                    case 9:
                        return TYPE_WIFI;
                    case 10:
                        return TYPE_GEO;
                    case 11:
                        return TYPE_CALENDAR_EVENT;
                    case 12:
                        return TYPE_DRIVER_LICENSE;
                    default:
                        return null;
                }
            }

            public static zzth zzi() {
                return zzms.a;
            }

            public final int zzh() {
                return this.value;
            }
        }

        public static final class zzc extends zztc.zza<zzv, zzc> implements zzuo {
            private zzc() {
                super(zzv.zzaln);
            }

            /* synthetic */ zzc(zzlp zzlp) {
                this();
            }

            public final zzc zzb(zzr zzr) {
                a();
                ((zzv) this.a).zza(zzr);
                return this;
            }

            public final zzc zzb(zzpq.zza zza) {
                a();
                ((zzv) this.a).zza(zza);
                return this;
            }

            public final zzc zzc(zzs.zza zza) {
                a();
                ((zzv) this.a).zza(zza);
                return this;
            }

            public final zzc zzk(Iterable<? extends zza> iterable) {
                a();
                ((zzv) this.a).zzi(iterable);
                return this;
            }

            public final zzc zzl(Iterable<? extends zzb> iterable) {
                a();
                ((zzv) this.a).zzj(iterable);
                return this;
            }
        }

        static {
            zztc.a(zzv.class, zzaln);
        }

        private zzv() {
        }

        /* access modifiers changed from: private */
        public final void zza(zzr zzr) {
            if (zzr != null) {
                this.zzadi = zzr;
                this.zzcd |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zza(zzs.zza zza2) {
            this.zzadg = (zzs) ((zztc) zza2.zzpx());
            this.zzcd |= 1;
        }

        /* access modifiers changed from: private */
        public final void zza(zzpq.zza zza2) {
            if (zza2 != null) {
                this.zzali = zza2;
                this.zzcd |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzi(Iterable<? extends zza> iterable) {
            if (!this.zzalj.zzog()) {
                this.zzalj = zztc.a(this.zzalj);
            }
            for (zza zzh : iterable) {
                this.zzalj.zzcy(zzh.zzh());
            }
        }

        /* access modifiers changed from: private */
        public final void zzj(Iterable<? extends zzb> iterable) {
            if (!this.zzall.zzog()) {
                this.zzall = zztc.a(this.zzall);
            }
            for (zzb zzh : iterable) {
                this.zzall.zzcy(zzh.zzh());
            }
        }

        public static zzc zzjf() {
            return (zzc) zzaln.d();
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzlp.a[i - 1]) {
                case 1:
                    return new zzv();
                case 2:
                    return new zzc((zzlp) null);
                case 3:
                    return a((zzum) zzaln, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0002\u0000\u0001\t\u0000\u0002\t\u0001\u0003\u001e\u0004\u001e\u0005\t\u0002", new Object[]{"zzcd", "zzadg", "zzali", "zzalj", zza.zzi(), "zzall", zzb.zzi(), "zzadi"});
                case 4:
                    return zzaln;
                case 5:
                    zzux<zzv> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zzv.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzaln);
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

    public static final class zzw extends zztc<zzw, zza> implements zzuo {
        /* access modifiers changed from: private */
        public static final zzw zzamv = new zzw();
        private static volatile zzux<zzw> zzcj;
        private zzs zzadg;
        private zzr zzadi;
        private zzpq.zzb zzamr;
        private zzp zzams;
        private int zzamt;
        private int zzamu;
        private int zzcd;

        public static final class zza extends zztc.zza<zzw, zza> implements zzuo {
            private zza() {
                super(zzw.zzamv);
            }

            /* synthetic */ zza(zzlp zzlp) {
                this();
            }

            public final zza zzaz(int i) {
                a();
                ((zzw) this.a).zzax(i);
                return this;
            }

            public final zza zzb(zzp zzp) {
                a();
                ((zzw) this.a).zza(zzp);
                return this;
            }

            public final zza zzba(int i) {
                a();
                ((zzw) this.a).zzay(i);
                return this;
            }

            public final zza zzc(zzr zzr) {
                a();
                ((zzw) this.a).zza(zzr);
                return this;
            }

            public final zza zzd(zzs.zza zza) {
                a();
                ((zzw) this.a).zza(zza);
                return this;
            }
        }

        static {
            zztc.a(zzw.class, zzamv);
        }

        private zzw() {
        }

        /* access modifiers changed from: private */
        public final void zza(zzp zzp) {
            if (zzp != null) {
                this.zzams = zzp;
                this.zzcd |= 8;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zza(zzr zzr) {
            if (zzr != null) {
                this.zzadi = zzr;
                this.zzcd |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zza(zzs.zza zza2) {
            this.zzadg = (zzs) ((zztc) zza2.zzpx());
            this.zzcd |= 1;
        }

        /* access modifiers changed from: private */
        public final void zzax(int i) {
            this.zzcd |= 16;
            this.zzamt = i;
        }

        /* access modifiers changed from: private */
        public final void zzay(int i) {
            this.zzcd |= 32;
            this.zzamu = i;
        }

        public static zza zzjh() {
            return (zza) zzamv.d();
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzlp.a[i - 1]) {
                case 1:
                    return new zzw();
                case 2:
                    return new zza((zzlp) null);
                case 3:
                    return a((zzum) zzamv, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001\t\u0000\u0002\t\u0001\u0003\t\u0002\u0004\t\u0003\u0005\u000b\u0004\u0006\u000b\u0005", new Object[]{"zzcd", "zzadg", "zzamr", "zzadi", "zzams", "zzamt", "zzamu"});
                case 4:
                    return zzamv;
                case 5:
                    zzux<zzw> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zzw.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzamv);
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

    public static final class zzx extends zztc<zzx, zza> implements zzuo {
        /* access modifiers changed from: private */
        public static final zzx zzamx = new zzx();
        private static volatile zzux<zzx> zzcj;
        private zzs zzadg;
        private zzr zzadi;
        private zzy zzamw;
        private int zzcd;

        public static final class zza extends zztc.zza<zzx, zza> implements zzuo {
            private zza() {
                super(zzx.zzamx);
            }

            /* synthetic */ zza(zzlp zzlp) {
                this();
            }

            public final zza zzb(zzy zzy) {
                a();
                ((zzx) this.a).zza(zzy);
                return this;
            }

            public final zza zzd(zzr zzr) {
                a();
                ((zzx) this.a).zza(zzr);
                return this;
            }

            public final zza zze(zzs.zza zza) {
                a();
                ((zzx) this.a).zza(zza);
                return this;
            }
        }

        static {
            zztc.a(zzx.class, zzamx);
        }

        private zzx() {
        }

        /* access modifiers changed from: private */
        public final void zza(zzr zzr) {
            if (zzr != null) {
                this.zzadi = zzr;
                this.zzcd |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zza(zzs.zza zza2) {
            this.zzadg = (zzs) ((zztc) zza2.zzpx());
            this.zzcd |= 1;
        }

        /* access modifiers changed from: private */
        public final void zza(zzy zzy) {
            if (zzy != null) {
                this.zzamw = zzy;
                this.zzcd |= 2;
                return;
            }
            throw new NullPointerException();
        }

        public static zza zzjj() {
            return (zza) zzamx.d();
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzlp.a[i - 1]) {
                case 1:
                    return new zzx();
                case 2:
                    return new zza((zzlp) null);
                case 3:
                    return a((zzum) zzamx, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0000\u0002\t\u0001\u0003\t\u0002", new Object[]{"zzcd", "zzadg", "zzamw", "zzadi"});
                case 4:
                    return zzamx;
                case 5:
                    zzux<zzx> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zzx.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzamx);
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

    public static final class zzy extends zztc<zzy, zza> implements zzuo {
        /* access modifiers changed from: private */
        public static final zzy zzamz = new zzy();
        private static volatile zzux<zzy> zzcj;
        private int zzamy;
        private int zzcd;
        private float zzgm;

        public static final class zza extends zztc.zza<zzy, zza> implements zzuo {
            private zza() {
                super(zzy.zzamz);
            }

            /* synthetic */ zza(zzlp zzlp) {
                this();
            }

            public final zza zzo(float f) {
                a();
                ((zzy) this.a).zzm(f);
                return this;
            }
        }

        static {
            zztc.a(zzy.class, zzamz);
        }

        private zzy() {
        }

        public static zza zzjl() {
            return (zza) zzamz.d();
        }

        /* access modifiers changed from: private */
        public final void zzm(float f) {
            this.zzcd |= 2;
            this.zzgm = f;
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzlp.a[i - 1]) {
                case 1:
                    return new zzy();
                case 2:
                    return new zza((zzlp) null);
                case 3:
                    return a((zzum) zzamz, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0000\u0002\u0001\u0001", new Object[]{"zzcd", "zzamy", "zzgm"});
                case 4:
                    return zzamz;
                case 5:
                    zzux<zzy> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zzy.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzamz);
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

    public static final class zzz extends zztc<zzz, zza> implements zzuo {
        /* access modifiers changed from: private */
        public static final zzz zzand = new zzz();
        private static volatile zzux<zzz> zzcj;
        private zzs zzadg;
        private zzt zzana;
        private zzc zzanb;
        private zzd zzanc;
        private int zzcd;

        public static final class zza extends zztc.zza<zzz, zza> implements zzuo {
            private zza() {
                super(zzz.zzand);
            }

            /* synthetic */ zza(zzlp zzlp) {
                this();
            }

            public final zza zzb(zzt zzt) {
                a();
                ((zzz) this.a).zza(zzt);
                return this;
            }

            public final zza zzb(zzc zzc) {
                a();
                ((zzz) this.a).zza(zzc);
                return this;
            }

            public final zza zzb(zzd zzd) {
                a();
                ((zzz) this.a).zza(zzd);
                return this;
            }

            public final zza zzf(zzs.zza zza) {
                a();
                ((zzz) this.a).zza(zza);
                return this;
            }
        }

        public static final class zzb extends zztc<zzb, zza> implements zzuo {
            /* access modifiers changed from: private */
            public static final zzb zzang = new zzb();
            private static volatile zzux<zzb> zzcj;
            private float zzane;
            private String zzanf = "";
            private int zzcd;

            public static final class zza extends zztc.zza<zzb, zza> implements zzuo {
                private zza() {
                    super(zzb.zzang);
                }

                /* synthetic */ zza(zzlp zzlp) {
                    this();
                }

                public final zza zzbf(String str) {
                    a();
                    ((zzb) this.a).zzbe(str);
                    return this;
                }

                public final zza zzq(float f) {
                    a();
                    ((zzb) this.a).zzp(f);
                    return this;
                }
            }

            static {
                zztc.a(zzb.class, zzang);
            }

            private zzb() {
            }

            /* access modifiers changed from: private */
            public final void zzbe(String str) {
                if (str != null) {
                    this.zzcd |= 2;
                    this.zzanf = str;
                    return;
                }
                throw new NullPointerException();
            }

            public static zza zzjp() {
                return (zza) zzang.d();
            }

            /* access modifiers changed from: private */
            public final void zzp(float f) {
                this.zzcd |= 1;
                this.zzane = f;
            }

            /* access modifiers changed from: protected */
            public final Object a(int i, Object obj, Object obj2) {
                switch (zzlp.a[i - 1]) {
                    case 1:
                        return new zzb();
                    case 2:
                        return new zza((zzlp) null);
                    case 3:
                        return a((zzum) zzang, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0001\u0000\u0002\b\u0001", new Object[]{"zzcd", "zzane", "zzanf"});
                    case 4:
                        return zzang;
                    case 5:
                        zzux<zzb> zzux = zzcj;
                        if (zzux == null) {
                            synchronized (zzb.class) {
                                zzux = zzcj;
                                if (zzux == null) {
                                    zzux = new zztc.zzb<>(zzang);
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

        public static final class zzc extends zztc<zzc, zza> implements zzuo {
            /* access modifiers changed from: private */
            public static final zzc zzani = new zzc();
            private static volatile zzux<zzc> zzcj;
            private zzb zzanh;
            private int zzcd;

            public static final class zza extends zztc.zza<zzc, zza> implements zzuo {
                private zza() {
                    super(zzc.zzani);
                }

                /* synthetic */ zza(zzlp zzlp) {
                    this();
                }

                public final zza zzb(zzb.zza zza) {
                    a();
                    ((zzc) this.a).zza(zza);
                    return this;
                }
            }

            static {
                zztc.a(zzc.class, zzani);
            }

            private zzc() {
            }

            /* access modifiers changed from: private */
            public final void zza(zzb.zza zza2) {
                this.zzanh = (zzb) ((zztc) zza2.zzpx());
                this.zzcd |= 1;
            }

            public static zza zzjr() {
                return (zza) zzani.d();
            }

            public static zzc zzjs() {
                return zzani;
            }

            /* access modifiers changed from: protected */
            public final Object a(int i, Object obj, Object obj2) {
                switch (zzlp.a[i - 1]) {
                    case 1:
                        return new zzc();
                    case 2:
                        return new zza((zzlp) null);
                    case 3:
                        return a((zzum) zzani, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t\u0000", new Object[]{"zzcd", "zzanh"});
                    case 4:
                        return zzani;
                    case 5:
                        zzux<zzc> zzux = zzcj;
                        if (zzux == null) {
                            synchronized (zzc.class) {
                                zzux = zzcj;
                                if (zzux == null) {
                                    zzux = new zztc.zzb<>(zzani);
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

        public static final class zzd extends zztc<zzd, zza> implements zzuo {
            /* access modifiers changed from: private */
            public static final zzd zzank = new zzd();
            private static volatile zzux<zzd> zzcj;
            private zztl<zzb> zzanj = f();

            public static final class zza extends zztc.zza<zzd, zza> implements zzuo {
                private zza() {
                    super(zzd.zzank);
                }

                /* synthetic */ zza(zzlp zzlp) {
                    this();
                }

                public final zza zzd(zzb.zza zza) {
                    a();
                    ((zzd) this.a).zzc(zza);
                    return this;
                }
            }

            static {
                zztc.a(zzd.class, zzank);
            }

            private zzd() {
            }

            /* access modifiers changed from: private */
            public final void zzc(zzb.zza zza2) {
                if (!this.zzanj.zzog()) {
                    this.zzanj = zztc.a(this.zzanj);
                }
                this.zzanj.add((zzb) ((zztc) zza2.zzpx()));
            }

            public static zza zzju() {
                return (zza) zzank.d();
            }

            public static zzd zzjv() {
                return zzank;
            }

            /* access modifiers changed from: protected */
            public final Object a(int i, Object obj, Object obj2) {
                switch (zzlp.a[i - 1]) {
                    case 1:
                        return new zzd();
                    case 2:
                        return new zza((zzlp) null);
                    case 3:
                        return a((zzum) zzank, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzanj", zzb.class});
                    case 4:
                        return zzank;
                    case 5:
                        zzux<zzd> zzux = zzcj;
                        if (zzux == null) {
                            synchronized (zzd.class) {
                                zzux = zzcj;
                                if (zzux == null) {
                                    zzux = new zztc.zzb<>(zzank);
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

        static {
            zztc.a(zzz.class, zzand);
        }

        private zzz() {
        }

        /* access modifiers changed from: private */
        public final void zza(zzs.zza zza2) {
            this.zzadg = (zzs) ((zztc) zza2.zzpx());
            this.zzcd |= 1;
        }

        /* access modifiers changed from: private */
        public final void zza(zzt zzt) {
            if (zzt != null) {
                this.zzana = zzt;
                this.zzcd |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zza(zzc zzc2) {
            if (zzc2 != null) {
                this.zzanb = zzc2;
                this.zzcd |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zza(zzd zzd2) {
            if (zzd2 != null) {
                this.zzanc = zzd2;
                this.zzcd |= 8;
                return;
            }
            throw new NullPointerException();
        }

        public static zza zzjn() {
            return (zza) zzand.d();
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzlp.a[i - 1]) {
                case 1:
                    return new zzz();
                case 2:
                    return new zza((zzlp) null);
                case 3:
                    return a((zzum) zzand, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\t\u0000\u0002\t\u0001\u0003\t\u0002\u0004\t\u0003", new Object[]{"zzcd", "zzadg", "zzana", "zzanb", "zzanc"});
                case 4:
                    return zzand;
                case 5:
                    zzux<zzz> zzux = zzcj;
                    if (zzux == null) {
                        synchronized (zzz.class) {
                            zzux = zzcj;
                            if (zzux == null) {
                                zzux = new zztc.zzb<>(zzand);
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
