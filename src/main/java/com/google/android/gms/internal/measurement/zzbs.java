package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzey;
import com.google.common.primitives.Ints;
import java.util.Collections;
import java.util.List;

public final class zzbs {

    public static final class zza extends zzey<zza, C0017zza> implements zzgk {
        private static volatile zzgr<zza> zzuo;
        /* access modifiers changed from: private */
        public static final zza zzwf = new zza();
        private int zzue;
        private int zzwb;
        private zzi zzwc;
        private zzi zzwd;
        private boolean zzwe;

        /* renamed from: com.google.android.gms.internal.measurement.zzbs$zza$zza  reason: collision with other inner class name */
        public static final class C0017zza extends zzey.zza<zza, C0017zza> implements zzgk {
            private C0017zza() {
                super(zza.zzwf);
            }

            /* synthetic */ C0017zza(zzbr zzbr) {
                this();
            }

            public final C0017zza zza(zzi.zza zza) {
                a();
                ((zza) this.a).zzb(zza);
                return this;
            }

            public final C0017zza zza(zzi zzi) {
                a();
                ((zza) this.a).zzb(zzi);
                return this;
            }

            public final C0017zza zzi(int i) {
                a();
                ((zza) this.a).zzj(i);
                return this;
            }

            public final C0017zza zzk(boolean z) {
                a();
                ((zza) this.a).zzl(z);
                return this;
            }

            public final zzi zzlv() {
                return ((zza) this.a).zzlv();
            }

            public final boolean zzlw() {
                return ((zza) this.a).zzlw();
            }

            public final zzi zzlx() {
                return ((zza) this.a).zzlx();
            }
        }

        static {
            zzey.a(zza.class, zzwf);
        }

        private zza() {
        }

        /* access modifiers changed from: private */
        public final void zzb(zzi.zza zza) {
            this.zzwc = (zzi) ((zzey) zza.zzug());
            this.zzue |= 2;
        }

        /* access modifiers changed from: private */
        public final void zzb(zzi zzi) {
            if (zzi != null) {
                this.zzwd = zzi;
                this.zzue |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzj(int i) {
            this.zzue |= 1;
            this.zzwb = i;
        }

        /* access modifiers changed from: private */
        public final void zzl(boolean z) {
            this.zzue |= 8;
            this.zzwe = z;
        }

        public static C0017zza zzmc() {
            return (C0017zza) zzwf.d();
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzbr.a[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0017zza((zzbr) null);
                case 3:
                    return a((zzgi) zzwf, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u0004\u0000\u0002\t\u0001\u0003\t\u0002\u0004\u0007\u0003", new Object[]{"zzue", "zzwb", "zzwc", "zzwd", "zzwe"});
                case 4:
                    return zzwf;
                case 5:
                    zzgr<zza> zzgr = zzuo;
                    if (zzgr == null) {
                        synchronized (zza.class) {
                            zzgr = zzuo;
                            if (zzgr == null) {
                                zzgr = new zzey.zzc<>(zzwf);
                                zzuo = zzgr;
                            }
                        }
                    }
                    return zzgr;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final zzi zzlv() {
            zzi zzi = this.zzwc;
            return zzi == null ? zzi.zzqi() : zzi;
        }

        public final boolean zzlw() {
            return (this.zzue & 4) != 0;
        }

        public final zzi zzlx() {
            zzi zzi = this.zzwd;
            return zzi == null ? zzi.zzqi() : zzi;
        }

        public final boolean zzly() {
            return (this.zzue & 1) != 0;
        }

        public final int zzlz() {
            return this.zzwb;
        }

        public final boolean zzma() {
            return (this.zzue & 8) != 0;
        }

        public final boolean zzmb() {
            return this.zzwe;
        }
    }

    public static final class zzb extends zzey<zzb, zza> implements zzgk {
        private static volatile zzgr<zzb> zzuo;
        /* access modifiers changed from: private */
        public static final zzb zzwi = new zzb();
        private int zzue;
        private int zzwg;
        private long zzwh;

        public static final class zza extends zzey.zza<zzb, zza> implements zzgk {
            private zza() {
                super(zzb.zzwi);
            }

            /* synthetic */ zza(zzbr zzbr) {
                this();
            }

            public final zza zzae(long j) {
                a();
                ((zzb) this.a).zzaf(j);
                return this;
            }

            public final zza zzk(int i) {
                a();
                ((zzb) this.a).setIndex(i);
                return this;
            }
        }

        static {
            zzey.a(zzb.class, zzwi);
        }

        private zzb() {
        }

        /* access modifiers changed from: private */
        public final void setIndex(int i) {
            this.zzue |= 1;
            this.zzwg = i;
        }

        /* access modifiers changed from: private */
        public final void zzaf(long j) {
            this.zzue |= 2;
            this.zzwh = j;
        }

        public static zza zzmh() {
            return (zza) zzwi.d();
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzbr.a[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza((zzbr) null);
                case 3:
                    return a((zzgi) zzwi, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0004\u0000\u0002\u0002\u0001", new Object[]{"zzue", "zzwg", "zzwh"});
                case 4:
                    return zzwi;
                case 5:
                    zzgr<zzb> zzgr = zzuo;
                    if (zzgr == null) {
                        synchronized (zzb.class) {
                            zzgr = zzuo;
                            if (zzgr == null) {
                                zzgr = new zzey.zzc<>(zzwi);
                                zzuo = zzgr;
                            }
                        }
                    }
                    return zzgr;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final int getIndex() {
            return this.zzwg;
        }

        public final boolean zzme() {
            return (this.zzue & 1) != 0;
        }

        public final boolean zzmf() {
            return (this.zzue & 2) != 0;
        }

        public final long zzmg() {
            return this.zzwh;
        }
    }

    public static final class zzc extends zzey<zzc, zza> implements zzgk {
        private static volatile zzgr<zzc> zzuo;
        /* access modifiers changed from: private */
        public static final zzc zzwo = new zzc();
        private int zzue;
        private zzff<zze> zzwj = g();
        private String zzwk = "";
        private long zzwl;
        private long zzwm;
        private int zzwn;

        public static final class zza extends zzey.zza<zzc, zza> implements zzgk {
            private zza() {
                super(zzc.zzwo);
            }

            /* synthetic */ zza(zzbr zzbr) {
                this();
            }

            public final String getName() {
                return ((zzc) this.a).getName();
            }

            public final long getTimestampMillis() {
                return ((zzc) this.a).getTimestampMillis();
            }

            public final zza zza(int i, zze.zza zza) {
                a();
                ((zzc) this.a).zzb(i, zza);
                return this;
            }

            public final zza zza(int i, zze zze) {
                a();
                ((zzc) this.a).zzb(i, zze);
                return this;
            }

            public final zza zza(zze.zza zza) {
                a();
                ((zzc) this.a).zzb(zza);
                return this;
            }

            public final zza zza(zze zze) {
                a();
                ((zzc) this.a).zzb(zze);
                return this;
            }

            public final zza zzag(long j) {
                a();
                ((zzc) this.a).zzai(j);
                return this;
            }

            public final zza zzah(long j) {
                a();
                ((zzc) this.a).zzaj(j);
                return this;
            }

            public final zza zzbx(String str) {
                a();
                ((zzc) this.a).setName(str);
                return this;
            }

            public final zze zzl(int i) {
                return ((zzc) this.a).zzl(i);
            }

            public final zza zzm(int i) {
                a();
                ((zzc) this.a).zzn(i);
                return this;
            }

            public final List<zze> zzmj() {
                return Collections.unmodifiableList(((zzc) this.a).zzmj());
            }

            public final int zzmk() {
                return ((zzc) this.a).zzmk();
            }

            public final boolean zzml() {
                return ((zzc) this.a).zzml();
            }

            public final long zzmm() {
                return ((zzc) this.a).zzmm();
            }
        }

        static {
            zzey.a(zzc.class, zzwo);
        }

        private zzc() {
        }

        /* access modifiers changed from: private */
        public final void setName(String str) {
            if (str != null) {
                this.zzue |= 1;
                this.zzwk = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzai(long j) {
            this.zzue |= 2;
            this.zzwl = j;
        }

        /* access modifiers changed from: private */
        public final void zzaj(long j) {
            this.zzue |= 4;
            this.zzwm = j;
        }

        /* access modifiers changed from: private */
        public final void zzb(int i, zze.zza zza2) {
            zzmn();
            this.zzwj.set(i, (zze) ((zzey) zza2.zzug()));
        }

        /* access modifiers changed from: private */
        public final void zzb(int i, zze zze) {
            if (zze != null) {
                zzmn();
                this.zzwj.set(i, zze);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzb(zze.zza zza2) {
            zzmn();
            this.zzwj.add((zze) ((zzey) zza2.zzug()));
        }

        /* access modifiers changed from: private */
        public final void zzb(zze zze) {
            if (zze != null) {
                zzmn();
                this.zzwj.add(zze);
                return;
            }
            throw new NullPointerException();
        }

        public static zzc zzc(byte[] bArr, zzel zzel) {
            return (zzc) zzey.a(zzwo, bArr, zzel);
        }

        private final void zzmn() {
            if (!this.zzwj.zzrx()) {
                this.zzwj = zzey.a(this.zzwj);
            }
        }

        public static zza zzmq() {
            return (zza) zzwo.d();
        }

        /* access modifiers changed from: private */
        public final void zzn(int i) {
            zzmn();
            this.zzwj.remove(i);
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzbr.a[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza((zzbr) null);
                case 3:
                    return a((zzgi) zzwo, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001\u001b\u0002\b\u0000\u0003\u0002\u0001\u0004\u0002\u0002\u0005\u0004\u0003", new Object[]{"zzue", "zzwj", zze.class, "zzwk", "zzwl", "zzwm", "zzwn"});
                case 4:
                    return zzwo;
                case 5:
                    zzgr<zzc> zzgr = zzuo;
                    if (zzgr == null) {
                        synchronized (zzc.class) {
                            zzgr = zzuo;
                            if (zzgr == null) {
                                zzgr = new zzey.zzc<>(zzwo);
                                zzuo = zzgr;
                            }
                        }
                    }
                    return zzgr;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final int getCount() {
            return this.zzwn;
        }

        public final String getName() {
            return this.zzwk;
        }

        public final long getTimestampMillis() {
            return this.zzwl;
        }

        public final zze zzl(int i) {
            return (zze) this.zzwj.get(i);
        }

        public final List<zze> zzmj() {
            return this.zzwj;
        }

        public final int zzmk() {
            return this.zzwj.size();
        }

        public final boolean zzml() {
            return (this.zzue & 2) != 0;
        }

        public final long zzmm() {
            return this.zzwm;
        }

        public final boolean zzmo() {
            return (this.zzue & 4) != 0;
        }

        public final boolean zzmp() {
            return (this.zzue & 8) != 0;
        }
    }

    public static final class zzd extends zzey<zzd, zza> implements zzgk {
        private static volatile zzgr<zzd> zzuo;
        /* access modifiers changed from: private */
        public static final zzd zzwq = new zzd();
        private int zzue;
        private String zzwk = "";
        private long zzwp;

        public static final class zza extends zzey.zza<zzd, zza> implements zzgk {
            private zza() {
                super(zzd.zzwq);
            }

            /* synthetic */ zza(zzbr zzbr) {
                this();
            }

            public final zza zzak(long j) {
                a();
                ((zzd) this.a).zzal(j);
                return this;
            }

            public final zza zzby(String str) {
                a();
                ((zzd) this.a).setName(str);
                return this;
            }
        }

        static {
            zzey.a(zzd.class, zzwq);
        }

        private zzd() {
        }

        /* access modifiers changed from: private */
        public final void setName(String str) {
            if (str != null) {
                this.zzue |= 1;
                this.zzwk = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzal(long j) {
            this.zzue |= 2;
            this.zzwp = j;
        }

        public static zza zzms() {
            return (zza) zzwq.d();
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzbr.a[i - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza((zzbr) null);
                case 3:
                    return a((zzgi) zzwq, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\b\u0000\u0002\u0002\u0001", new Object[]{"zzue", "zzwk", "zzwp"});
                case 4:
                    return zzwq;
                case 5:
                    zzgr<zzd> zzgr = zzuo;
                    if (zzgr == null) {
                        synchronized (zzd.class) {
                            zzgr = zzuo;
                            if (zzgr == null) {
                                zzgr = new zzey.zzc<>(zzwq);
                                zzuo = zzgr;
                            }
                        }
                    }
                    return zzgr;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    public static final class zze extends zzey<zze, zza> implements zzgk {
        private static volatile zzgr<zze> zzuo;
        /* access modifiers changed from: private */
        public static final zze zzwu = new zze();
        private int zzue;
        private String zzwk = "";
        private long zzwp;
        private String zzwr = "";
        private float zzws;
        private double zzwt;

        public static final class zza extends zzey.zza<zze, zza> implements zzgk {
            private zza() {
                super(zze.zzwu);
            }

            /* synthetic */ zza(zzbr zzbr) {
                this();
            }

            public final zza zza(double d) {
                a();
                ((zze) this.a).zzb(d);
                return this;
            }

            public final zza zzam(long j) {
                a();
                ((zze) this.a).zzal(j);
                return this;
            }

            public final zza zzbz(String str) {
                a();
                ((zze) this.a).setName(str);
                return this;
            }

            public final zza zzca(String str) {
                a();
                ((zze) this.a).zzcb(str);
                return this;
            }

            public final zza zzmu() {
                a();
                ((zze) this.a).zzmz();
                return this;
            }

            public final zza zzmv() {
                a();
                ((zze) this.a).zznc();
                return this;
            }

            public final zza zzmw() {
                a();
                ((zze) this.a).zznf();
                return this;
            }
        }

        static {
            zzey.a(zze.class, zzwu);
        }

        private zze() {
        }

        /* access modifiers changed from: private */
        public final void setName(String str) {
            if (str != null) {
                this.zzue |= 1;
                this.zzwk = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzal(long j) {
            this.zzue |= 4;
            this.zzwp = j;
        }

        /* access modifiers changed from: private */
        public final void zzb(double d) {
            this.zzue |= 16;
            this.zzwt = d;
        }

        /* access modifiers changed from: private */
        public final void zzcb(String str) {
            if (str != null) {
                this.zzue |= 2;
                this.zzwr = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzmz() {
            this.zzue &= -3;
            this.zzwr = zzwu.zzwr;
        }

        /* access modifiers changed from: private */
        public final void zznc() {
            this.zzue &= -5;
            this.zzwp = 0;
        }

        /* access modifiers changed from: private */
        public final void zznf() {
            this.zzue &= -17;
            this.zzwt = 0.0d;
        }

        public static zza zzng() {
            return (zza) zzwu.d();
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzbr.a[i - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zza((zzbr) null);
                case 3:
                    return a((zzgi) zzwu, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\u0002\u0002\u0004\u0001\u0003\u0005\u0000\u0004", new Object[]{"zzue", "zzwk", "zzwr", "zzwp", "zzws", "zzwt"});
                case 4:
                    return zzwu;
                case 5:
                    zzgr<zze> zzgr = zzuo;
                    if (zzgr == null) {
                        synchronized (zze.class) {
                            zzgr = zzuo;
                            if (zzgr == null) {
                                zzgr = new zzey.zzc<>(zzwu);
                                zzuo = zzgr;
                            }
                        }
                    }
                    return zzgr;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final String getName() {
            return this.zzwk;
        }

        public final boolean zzmx() {
            return (this.zzue & 2) != 0;
        }

        public final String zzmy() {
            return this.zzwr;
        }

        public final boolean zzna() {
            return (this.zzue & 4) != 0;
        }

        public final long zznb() {
            return this.zzwp;
        }

        public final boolean zznd() {
            return (this.zzue & 16) != 0;
        }

        public final double zzne() {
            return this.zzwt;
        }
    }

    public static final class zzf extends zzey<zzf, zza> implements zzgk {
        private static volatile zzgr<zzf> zzuo;
        /* access modifiers changed from: private */
        public static final zzf zzww = new zzf();
        private zzff<zzg> zzwv = g();

        public static final class zza extends zzey.zza<zzf, zza> implements zzgk {
            private zza() {
                super(zzf.zzww);
            }

            /* synthetic */ zza(zzbr zzbr) {
                this();
            }

            public final zza zza(zzg.zza zza) {
                a();
                ((zzf) this.a).zzb(zza);
                return this;
            }

            public final zzg zzo(int i) {
                return ((zzf) this.a).zzo(0);
            }
        }

        static {
            zzey.a(zzf.class, zzww);
        }

        private zzf() {
        }

        /* access modifiers changed from: private */
        public final void zzb(zzg.zza zza2) {
            if (!this.zzwv.zzrx()) {
                this.zzwv = zzey.a(this.zzwv);
            }
            this.zzwv.add((zzg) ((zzey) zza2.zzug()));
        }

        public static zza zznj() {
            return (zza) zzww.d();
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzbr.a[i - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zza((zzbr) null);
                case 3:
                    return a((zzgi) zzww, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzwv", zzg.class});
                case 4:
                    return zzww;
                case 5:
                    zzgr<zzf> zzgr = zzuo;
                    if (zzgr == null) {
                        synchronized (zzf.class) {
                            zzgr = zzuo;
                            if (zzgr == null) {
                                zzgr = new zzey.zzc<>(zzww);
                                zzuo = zzgr;
                            }
                        }
                    }
                    return zzgr;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final List<zzg> zzni() {
            return this.zzwv;
        }

        public final zzg zzo(int i) {
            return (zzg) this.zzwv.get(0);
        }
    }

    public static final class zzg extends zzey<zzg, zza> implements zzgk {
        private static volatile zzgr<zzg> zzuo;
        /* access modifiers changed from: private */
        public static final zzg zzyo = new zzg();
        private int zzue;
        private int zzwx;
        private int zzwy;
        private zzff<zzc> zzwz = g();
        private zzff<zzk> zzxa = g();
        private long zzxb;
        private long zzxc;
        private long zzxd;
        private long zzxe;
        private long zzxf;
        private String zzxg = "";
        private String zzxh = "";
        private String zzxi = "";
        private String zzxj = "";
        private int zzxk;
        private String zzxl = "";
        private String zzxm = "";
        private String zzxn = "";
        private long zzxo;
        private long zzxp;
        private String zzxq = "";
        private boolean zzxr;
        private String zzxs = "";
        private long zzxt;
        private int zzxu;
        private String zzxv = "";
        private String zzxw = "";
        private boolean zzxx;
        private zzff<zza> zzxy = g();
        private String zzxz = "";
        private int zzya;
        private int zzyb;
        private int zzyc;
        private String zzyd = "";
        private long zzye;
        private long zzyf;
        private String zzyg = "";
        private String zzyh = "";
        private int zzyi;
        private String zzyj = "";
        private zzh zzyk;
        private zzfd zzyl = e();
        private long zzym;
        private long zzyn;

        public static final class zza extends zzey.zza<zzg, zza> implements zzgk {
            private zza() {
                super(zzg.zzyo);
            }

            /* synthetic */ zza(zzbr zzbr) {
                this();
            }

            public final String getGmpAppId() {
                return ((zzg) this.a).getGmpAppId();
            }

            public final zza zza(int i, zzc.zza zza) {
                a();
                ((zzg) this.a).zzb(i, zza);
                return this;
            }

            public final zza zza(int i, zzk zzk) {
                a();
                ((zzg) this.a).zzb(i, zzk);
                return this;
            }

            public final zza zza(zzc.zza zza) {
                a();
                ((zzg) this.a).zzb(zza);
                return this;
            }

            public final zza zza(zzh.zza zza) {
                a();
                ((zzg) this.a).zzb(zza);
                return this;
            }

            public final zza zza(zzk.zza zza) {
                a();
                ((zzg) this.a).zzb(zza);
                return this;
            }

            public final zza zza(zzk zzk) {
                a();
                ((zzg) this.a).zzb(zzk);
                return this;
            }

            public final zza zza(Iterable<? extends zzc> iterable) {
                a();
                ((zzg) this.a).zze(iterable);
                return this;
            }

            public final String zzag() {
                return ((zzg) this.a).zzag();
            }

            public final zza zzan(long j) {
                a();
                ((zzg) this.a).zzaz(j);
                return this;
            }

            public final zza zzao(long j) {
                a();
                ((zzg) this.a).zzba(j);
                return this;
            }

            public final zza zzap(long j) {
                a();
                ((zzg) this.a).zzbb(j);
                return this;
            }

            public final zza zzaq(long j) {
                a();
                ((zzg) this.a).zzbc(j);
                return this;
            }

            public final zza zzar(long j) {
                a();
                ((zzg) this.a).zzbd(j);
                return this;
            }

            public final zza zzas(long j) {
                a();
                ((zzg) this.a).zzh(j);
                return this;
            }

            public final zza zzat(long j) {
                a();
                ((zzg) this.a).zzbe(j);
                return this;
            }

            public final zza zzau(long j) {
                a();
                ((zzg) this.a).zzi(j);
                return this;
            }

            public final zza zzav(long j) {
                a();
                ((zzg) this.a).zzbf(j);
                return this;
            }

            public final zza zzaw(long j) {
                a();
                ((zzg) this.a).zzt(j);
                return this;
            }

            public final zza zzax(long j) {
                a();
                ((zzg) this.a).zzj(j);
                return this;
            }

            public final zza zzay(long j) {
                a();
                ((zzg) this.a).zzbg(j);
                return this;
            }

            public final zza zzb(Iterable<? extends zzk> iterable) {
                a();
                ((zzg) this.a).zzf(iterable);
                return this;
            }

            public final zza zzc(Iterable<? extends zza> iterable) {
                a();
                ((zzg) this.a).zzg(iterable);
                return this;
            }

            public final zza zzcc(String str) {
                a();
                ((zzg) this.a).zzcr(str);
                return this;
            }

            public final zza zzcd(String str) {
                a();
                ((zzg) this.a).zzcs(str);
                return this;
            }

            public final zza zzce(String str) {
                a();
                ((zzg) this.a).zzct(str);
                return this;
            }

            public final zza zzcf(String str) {
                a();
                ((zzg) this.a).zzcu(str);
                return this;
            }

            public final zza zzcg(String str) {
                a();
                ((zzg) this.a).zzg(str);
                return this;
            }

            public final zza zzch(String str) {
                a();
                ((zzg) this.a).zzcv(str);
                return this;
            }

            public final zza zzci(String str) {
                a();
                ((zzg) this.a).zzf(str);
                return this;
            }

            public final zza zzcj(String str) {
                a();
                ((zzg) this.a).zzcw(str);
                return this;
            }

            public final zza zzck(String str) {
                a();
                ((zzg) this.a).zza(str);
                return this;
            }

            public final zza zzcl(String str) {
                a();
                ((zzg) this.a).zzcx(str);
                return this;
            }

            public final zza zzcm(String str) {
                a();
                ((zzg) this.a).zzb(str);
                return this;
            }

            public final zza zzcn(String str) {
                a();
                ((zzg) this.a).zze(str);
                return this;
            }

            public final zza zzco(String str) {
                a();
                ((zzg) this.a).zzcy(str);
                return this;
            }

            public final zza zzcp(String str) {
                a();
                ((zzg) this.a).zzcz((String) null);
                return this;
            }

            public final zza zzcq(String str) {
                a();
                ((zzg) this.a).zzda(str);
                return this;
            }

            public final zza zzd(Iterable<? extends Integer> iterable) {
                a();
                ((zzg) this.a).zzh(iterable);
                return this;
            }

            public final zza zzm(boolean z) {
                a();
                ((zzg) this.a).zzo(z);
                return this;
            }

            public final zza zzn(boolean z) {
                a();
                ((zzg) this.a).zzp(z);
                return this;
            }

            public final List<zzc> zznl() {
                return Collections.unmodifiableList(((zzg) this.a).zznl());
            }

            public final int zznm() {
                return ((zzg) this.a).zznm();
            }

            public final zza zznn() {
                a();
                ((zzg) this.a).zzoa();
                return this;
            }

            public final List<zzk> zzno() {
                return Collections.unmodifiableList(((zzg) this.a).zzno());
            }

            public final int zznp() {
                return ((zzg) this.a).zznp();
            }

            public final long zznq() {
                return ((zzg) this.a).zznq();
            }

            public final long zznr() {
                return ((zzg) this.a).zznr();
            }

            public final zza zzns() {
                a();
                ((zzg) this.a).zzoi();
                return this;
            }

            public final zza zznt() {
                a();
                ((zzg) this.a).zzol();
                return this;
            }

            public final zza zznu() {
                a();
                ((zzg) this.a).zzpa();
                return this;
            }

            public final zza zznv() {
                a();
                ((zzg) this.a).zzpe();
                return this;
            }

            public final zza zznw() {
                a();
                ((zzg) this.a).zzpm();
                return this;
            }

            public final zza zzp(int i) {
                a();
                ((zzg) this.a).zzx(1);
                return this;
            }

            public final zzc zzq(int i) {
                return ((zzg) this.a).zzq(i);
            }

            public final zza zzr(int i) {
                a();
                ((zzg) this.a).zzy(i);
                return this;
            }

            public final zzk zzs(int i) {
                return ((zzg) this.a).zzs(i);
            }

            public final zza zzt(int i) {
                a();
                ((zzg) this.a).zzz(i);
                return this;
            }

            public final zza zzu(int i) {
                a();
                ((zzg) this.a).zzaa(i);
                return this;
            }

            public final zza zzv(int i) {
                a();
                ((zzg) this.a).zzab(i);
                return this;
            }

            public final zza zzw(int i) {
                a();
                ((zzg) this.a).zzac(i);
                return this;
            }
        }

        static {
            zzey.a(zzg.class, zzyo);
        }

        private zzg() {
        }

        /* access modifiers changed from: private */
        public final void zza(String str) {
            if (str != null) {
                this.zzue |= 262144;
                this.zzxs = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzaa(int i) {
            this.zzue |= 1048576;
            this.zzxu = i;
        }

        /* access modifiers changed from: private */
        public final void zzab(int i) {
            this.zzue |= 33554432;
            this.zzya = i;
        }

        /* access modifiers changed from: private */
        public final void zzac(int i) {
            this.zzwx |= 2;
            this.zzyi = i;
        }

        /* access modifiers changed from: private */
        public final void zzaz(long j) {
            this.zzue |= 2;
            this.zzxb = j;
        }

        /* access modifiers changed from: private */
        public final void zzb(int i, zzc.zza zza2) {
            zznz();
            this.zzwz.set(i, (zzc) ((zzey) zza2.zzug()));
        }

        /* access modifiers changed from: private */
        public final void zzb(int i, zzk zzk) {
            if (zzk != null) {
                zzob();
                this.zzxa.set(i, zzk);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzb(zzc.zza zza2) {
            zznz();
            this.zzwz.add((zzc) ((zzey) zza2.zzug()));
        }

        /* access modifiers changed from: private */
        public final void zzb(zzh.zza zza2) {
            this.zzyk = (zzh) ((zzey) zza2.zzug());
            this.zzwx |= 8;
        }

        /* access modifiers changed from: private */
        public final void zzb(zzk.zza zza2) {
            zzob();
            this.zzxa.add((zzk) ((zzey) zza2.zzug()));
        }

        /* access modifiers changed from: private */
        public final void zzb(zzk zzk) {
            if (zzk != null) {
                zzob();
                this.zzxa.add(zzk);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzb(String str) {
            if (str != null) {
                this.zzue |= 4194304;
                this.zzxw = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzba(long j) {
            this.zzue |= 4;
            this.zzxc = j;
        }

        /* access modifiers changed from: private */
        public final void zzbb(long j) {
            this.zzue |= 8;
            this.zzxd = j;
        }

        /* access modifiers changed from: private */
        public final void zzbc(long j) {
            this.zzue |= 16;
            this.zzxe = j;
        }

        /* access modifiers changed from: private */
        public final void zzbd(long j) {
            this.zzue |= 32;
            this.zzxf = j;
        }

        /* access modifiers changed from: private */
        public final void zzbe(long j) {
            this.zzue |= 32768;
            this.zzxp = j;
        }

        /* access modifiers changed from: private */
        public final void zzbf(long j) {
            this.zzue |= 536870912;
            this.zzye = j;
        }

        /* access modifiers changed from: private */
        public final void zzbg(long j) {
            this.zzwx |= 32;
            this.zzyn = j;
        }

        /* access modifiers changed from: private */
        public final void zzcr(String str) {
            if (str != null) {
                this.zzue |= 64;
                this.zzxg = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzcs(String str) {
            if (str != null) {
                this.zzue |= 128;
                this.zzxh = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzct(String str) {
            if (str != null) {
                this.zzue |= 256;
                this.zzxi = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzcu(String str) {
            if (str != null) {
                this.zzue |= 512;
                this.zzxj = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzcv(String str) {
            if (str != null) {
                this.zzue |= 4096;
                this.zzxm = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzcw(String str) {
            if (str != null) {
                this.zzue |= 65536;
                this.zzxq = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzcx(String str) {
            if (str != null) {
                this.zzue |= 2097152;
                this.zzxv = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzcy(String str) {
            if (str != null) {
                this.zzue |= 268435456;
                this.zzyd = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzcz(String str) {
            if (str != null) {
                this.zzue |= Integer.MIN_VALUE;
                this.zzyg = str;
                return;
            }
            throw new NullPointerException();
        }

        public static zzg zzd(byte[] bArr, zzel zzel) {
            return (zzg) zzey.a(zzyo, bArr, zzel);
        }

        /* access modifiers changed from: private */
        public final void zzda(String str) {
            if (str != null) {
                this.zzwx |= 4;
                this.zzyj = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zze(Iterable<? extends zzc> iterable) {
            zznz();
            zzdf.a(iterable, this.zzwz);
        }

        /* access modifiers changed from: private */
        public final void zze(String str) {
            if (str != null) {
                this.zzue |= 16777216;
                this.zzxz = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzf(Iterable<? extends zzk> iterable) {
            zzob();
            zzdf.a(iterable, this.zzxa);
        }

        /* access modifiers changed from: private */
        public final void zzf(String str) {
            if (str != null) {
                this.zzue |= 8192;
                this.zzxn = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzg(Iterable<? extends zza> iterable) {
            if (!this.zzxy.zzrx()) {
                this.zzxy = zzey.a(this.zzxy);
            }
            zzdf.a(iterable, this.zzxy);
        }

        /* access modifiers changed from: private */
        public final void zzg(String str) {
            if (str != null) {
                this.zzue |= 2048;
                this.zzxl = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzh(long j) {
            this.zzue |= 16384;
            this.zzxo = j;
        }

        /* access modifiers changed from: private */
        public final void zzh(Iterable<? extends Integer> iterable) {
            if (!this.zzyl.zzrx()) {
                zzfd zzfd = this.zzyl;
                int size = zzfd.size();
                this.zzyl = zzfd.zzbt(size == 0 ? 10 : size << 1);
            }
            zzdf.a(iterable, this.zzyl);
        }

        /* access modifiers changed from: private */
        public final void zzi(long j) {
            this.zzue |= 524288;
            this.zzxt = j;
        }

        /* access modifiers changed from: private */
        public final void zzj(long j) {
            this.zzwx |= 16;
            this.zzym = j;
        }

        private final void zznz() {
            if (!this.zzwz.zzrx()) {
                this.zzwz = zzey.a(this.zzwz);
            }
        }

        /* access modifiers changed from: private */
        public final void zzo(boolean z) {
            this.zzue |= 131072;
            this.zzxr = z;
        }

        /* access modifiers changed from: private */
        public final void zzoa() {
            this.zzwz = g();
        }

        private final void zzob() {
            if (!this.zzxa.zzrx()) {
                this.zzxa = zzey.a(this.zzxa);
            }
        }

        /* access modifiers changed from: private */
        public final void zzoi() {
            this.zzue &= -17;
            this.zzxe = 0;
        }

        /* access modifiers changed from: private */
        public final void zzol() {
            this.zzue &= -33;
            this.zzxf = 0;
        }

        /* access modifiers changed from: private */
        public final void zzp(boolean z) {
            this.zzue |= 8388608;
            this.zzxx = z;
        }

        /* access modifiers changed from: private */
        public final void zzpa() {
            this.zzue &= -2097153;
            this.zzxv = zzyo.zzxv;
        }

        /* access modifiers changed from: private */
        public final void zzpe() {
            this.zzxy = g();
        }

        /* access modifiers changed from: private */
        public final void zzpm() {
            this.zzue &= Integer.MAX_VALUE;
            this.zzyg = zzyo.zzyg;
        }

        public static zza zzpr() {
            return (zza) zzyo.d();
        }

        /* access modifiers changed from: private */
        public final void zzt(long j) {
            this.zzue |= Ints.MAX_POWER_OF_TWO;
            this.zzyf = j;
        }

        /* access modifiers changed from: private */
        public final void zzx(int i) {
            this.zzue |= 1;
            this.zzwy = i;
        }

        /* access modifiers changed from: private */
        public final void zzy(int i) {
            zznz();
            this.zzwz.remove(i);
        }

        /* access modifiers changed from: private */
        public final void zzz(int i) {
            this.zzue |= 1024;
            this.zzxk = i;
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzbr.a[i - 1]) {
                case 1:
                    return new zzg();
                case 2:
                    return new zza((zzbr) null);
                case 3:
                    return a((zzgi) zzyo, "\u0001*\u0000\u0002\u0001/*\u0000\u0004\u0000\u0001\u0004\u0000\u0002\u001b\u0003\u001b\u0004\u0002\u0001\u0005\u0002\u0002\u0006\u0002\u0003\u0007\u0002\u0005\b\b\u0006\t\b\u0007\n\b\b\u000b\b\t\f\u0004\n\r\b\u000b\u000e\b\f\u0010\b\r\u0011\u0002\u000e\u0012\u0002\u000f\u0013\b\u0010\u0014\u0007\u0011\u0015\b\u0012\u0016\u0002\u0013\u0017\u0004\u0014\u0018\b\u0015\u0019\b\u0016\u001a\u0002\u0004\u001c\u0007\u0017\u001d\u001b\u001e\b\u0018\u001f\u0004\u0019 \u0004\u001a!\u0004\u001b\"\b\u001c#\u0002\u001d$\u0002\u001e%\b\u001f&\b '\u0004!)\b\",\t#-\u001d.\u0002$/\u0002%", new Object[]{"zzue", "zzwx", "zzwy", "zzwz", zzc.class, "zzxa", zzk.class, "zzxb", "zzxc", "zzxd", "zzxf", "zzxg", "zzxh", "zzxi", "zzxj", "zzxk", "zzxl", "zzxm", "zzxn", "zzxo", "zzxp", "zzxq", "zzxr", "zzxs", "zzxt", "zzxu", "zzxv", "zzxw", "zzxe", "zzxx", "zzxy", zza.class, "zzxz", "zzya", "zzyb", "zzyc", "zzyd", "zzye", "zzyf", "zzyg", "zzyh", "zzyi", "zzyj", "zzyk", "zzyl", "zzym", "zzyn"});
                case 4:
                    return zzyo;
                case 5:
                    zzgr<zzg> zzgr = zzuo;
                    if (zzgr == null) {
                        synchronized (zzg.class) {
                            zzgr = zzuo;
                            if (zzgr == null) {
                                zzgr = new zzey.zzc<>(zzyo);
                                zzuo = zzgr;
                            }
                        }
                    }
                    return zzgr;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final String getAppInstanceId() {
            return this.zzxs;
        }

        public final String getFirebaseInstanceId() {
            return this.zzxz;
        }

        public final String getGmpAppId() {
            return this.zzxw;
        }

        public final String getOsVersion() {
            return this.zzxh;
        }

        public final String zzag() {
            return this.zzxm;
        }

        public final String zzal() {
            return this.zzxn;
        }

        public final String zzan() {
            return this.zzxl;
        }

        public final long zzao() {
            return this.zzxo;
        }

        public final long zzap() {
            return this.zzxt;
        }

        public final long zzaq() {
            return this.zzym;
        }

        public final long zzbd() {
            return this.zzyf;
        }

        public final String zzcr() {
            return this.zzxj;
        }

        public final List<zzc> zznl() {
            return this.zzwz;
        }

        public final int zznm() {
            return this.zzwz.size();
        }

        public final List<zzk> zzno() {
            return this.zzxa;
        }

        public final int zznp() {
            return this.zzxa.size();
        }

        public final long zznq() {
            return this.zzxc;
        }

        public final long zznr() {
            return this.zzxd;
        }

        public final boolean zznx() {
            return (this.zzue & 1) != 0;
        }

        public final int zzny() {
            return this.zzwy;
        }

        public final boolean zzoc() {
            return (this.zzue & 2) != 0;
        }

        public final long zzod() {
            return this.zzxb;
        }

        public final boolean zzoe() {
            return (this.zzue & 4) != 0;
        }

        public final boolean zzof() {
            return (this.zzue & 8) != 0;
        }

        public final boolean zzog() {
            return (this.zzue & 16) != 0;
        }

        public final long zzoh() {
            return this.zzxe;
        }

        public final boolean zzoj() {
            return (this.zzue & 32) != 0;
        }

        public final long zzok() {
            return this.zzxf;
        }

        public final String zzom() {
            return this.zzxg;
        }

        public final String zzon() {
            return this.zzxi;
        }

        public final boolean zzoo() {
            return (this.zzue & 1024) != 0;
        }

        public final int zzop() {
            return this.zzxk;
        }

        public final boolean zzoq() {
            return (this.zzue & 16384) != 0;
        }

        public final boolean zzor() {
            return (this.zzue & 32768) != 0;
        }

        public final long zzos() {
            return this.zzxp;
        }

        public final String zzot() {
            return this.zzxq;
        }

        public final boolean zzou() {
            return (this.zzue & 131072) != 0;
        }

        public final boolean zzov() {
            return this.zzxr;
        }

        public final boolean zzow() {
            return (this.zzue & 524288) != 0;
        }

        public final boolean zzox() {
            return (this.zzue & 1048576) != 0;
        }

        public final int zzoy() {
            return this.zzxu;
        }

        public final String zzoz() {
            return this.zzxv;
        }

        public final boolean zzpb() {
            return (this.zzue & 8388608) != 0;
        }

        public final boolean zzpc() {
            return this.zzxx;
        }

        public final List<zza> zzpd() {
            return this.zzxy;
        }

        public final boolean zzpf() {
            return (this.zzue & 33554432) != 0;
        }

        public final int zzpg() {
            return this.zzya;
        }

        public final String zzph() {
            return this.zzyd;
        }

        public final boolean zzpi() {
            return (this.zzue & 536870912) != 0;
        }

        public final long zzpj() {
            return this.zzye;
        }

        public final boolean zzpk() {
            return (this.zzue & Ints.MAX_POWER_OF_TWO) != 0;
        }

        public final String zzpl() {
            return this.zzyg;
        }

        public final boolean zzpn() {
            return (this.zzwx & 2) != 0;
        }

        public final int zzpo() {
            return this.zzyi;
        }

        public final String zzpp() {
            return this.zzyj;
        }

        public final boolean zzpq() {
            return (this.zzwx & 16) != 0;
        }

        public final zzc zzq(int i) {
            return (zzc) this.zzwz.get(i);
        }

        public final zzk zzs(int i) {
            return (zzk) this.zzxa.get(i);
        }
    }

    public static final class zzh extends zzey<zzh, zza> implements zzgk {
        private static volatile zzgr<zzh> zzuo;
        /* access modifiers changed from: private */
        public static final zzh zzyr = new zzh();
        private int zzue;
        private int zzyp = 1;
        private zzff<zzd> zzyq = g();

        public static final class zza extends zzey.zza<zzh, zza> implements zzgk {
            private zza() {
                super(zzh.zzyr);
            }

            /* synthetic */ zza(zzbr zzbr) {
                this();
            }

            public final zza zza(zzd.zza zza) {
                a();
                ((zzh) this.a).zzb(zza);
                return this;
            }
        }

        public enum zzb implements zzfc {
            RADS(1),
            PROVISIONING(2);
            
            private static final zzfb<zzb> zzvf = null;
            private final int value;

            static {
                zzvf = new zzbt();
            }

            private zzb(int i) {
                this.value = i;
            }

            public static zzb zzad(int i) {
                switch (i) {
                    case 1:
                        return RADS;
                    case 2:
                        return PROVISIONING;
                    default:
                        return null;
                }
            }

            public static zzfe zzlh() {
                return zzbu.a;
            }

            public final int zzlg() {
                return this.value;
            }
        }

        static {
            zzey.a(zzh.class, zzyr);
        }

        private zzh() {
        }

        /* access modifiers changed from: private */
        public final void zzb(zzd.zza zza2) {
            if (!this.zzyq.zzrx()) {
                this.zzyq = zzey.a(this.zzyq);
            }
            this.zzyq.add((zzd) ((zzey) zza2.zzug()));
        }

        public static zza zzpt() {
            return (zza) zzyr.d();
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzbr.a[i - 1]) {
                case 1:
                    return new zzh();
                case 2:
                    return new zza((zzbr) null);
                case 3:
                    return a((zzgi) zzyr, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\f\u0000\u0002\u001b", new Object[]{"zzue", "zzyp", zzb.zzlh(), "zzyq", zzd.class});
                case 4:
                    return zzyr;
                case 5:
                    zzgr<zzh> zzgr = zzuo;
                    if (zzgr == null) {
                        synchronized (zzh.class) {
                            zzgr = zzuo;
                            if (zzgr == null) {
                                zzgr = new zzey.zzc<>(zzyr);
                                zzuo = zzgr;
                            }
                        }
                    }
                    return zzgr;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    public static final class zzi extends zzey<zzi, zza> implements zzgk {
        private static volatile zzgr<zzi> zzuo;
        /* access modifiers changed from: private */
        public static final zzi zzyz = new zzi();
        private zzfg zzyv = f();
        private zzfg zzyw = f();
        private zzff<zzb> zzyx = g();
        private zzff<zzj> zzyy = g();

        public static final class zza extends zzey.zza<zzi, zza> implements zzgk {
            private zza() {
                super(zzi.zzyz);
            }

            /* synthetic */ zza(zzbr zzbr) {
                this();
            }

            public final zza zzaj(int i) {
                a();
                ((zzi) this.a).zzaf(i);
                return this;
            }

            public final zza zzak(int i) {
                a();
                ((zzi) this.a).zzah(i);
                return this;
            }

            public final zza zzn(Iterable<? extends Long> iterable) {
                a();
                ((zzi) this.a).zzi(iterable);
                return this;
            }

            public final zza zzo(Iterable<? extends Long> iterable) {
                a();
                ((zzi) this.a).zzj(iterable);
                return this;
            }

            public final zza zzp(Iterable<? extends zzb> iterable) {
                a();
                ((zzi) this.a).zzk(iterable);
                return this;
            }

            public final zza zzq(Iterable<? extends zzj> iterable) {
                a();
                ((zzi) this.a).zzl(iterable);
                return this;
            }

            public final zza zzqq() {
                a();
                ((zzi) this.a).zzpx();
                return this;
            }

            public final zza zzqr() {
                a();
                ((zzi) this.a).zzqa();
                return this;
            }
        }

        static {
            zzey.a(zzi.class, zzyz);
        }

        private zzi() {
        }

        /* access modifiers changed from: private */
        public final void zzaf(int i) {
            zzqd();
            this.zzyx.remove(i);
        }

        /* access modifiers changed from: private */
        public final void zzah(int i) {
            zzqg();
            this.zzyy.remove(i);
        }

        public static zzi zze(byte[] bArr, zzel zzel) {
            return (zzi) zzey.a(zzyz, bArr, zzel);
        }

        /* access modifiers changed from: private */
        public final void zzi(Iterable<? extends Long> iterable) {
            if (!this.zzyv.zzrx()) {
                this.zzyv = zzey.a(this.zzyv);
            }
            zzdf.a(iterable, this.zzyv);
        }

        /* access modifiers changed from: private */
        public final void zzj(Iterable<? extends Long> iterable) {
            if (!this.zzyw.zzrx()) {
                this.zzyw = zzey.a(this.zzyw);
            }
            zzdf.a(iterable, this.zzyw);
        }

        /* access modifiers changed from: private */
        public final void zzk(Iterable<? extends zzb> iterable) {
            zzqd();
            zzdf.a(iterable, this.zzyx);
        }

        /* access modifiers changed from: private */
        public final void zzl(Iterable<? extends zzj> iterable) {
            zzqg();
            zzdf.a(iterable, this.zzyy);
        }

        /* access modifiers changed from: private */
        public final void zzpx() {
            this.zzyv = f();
        }

        /* access modifiers changed from: private */
        public final void zzqa() {
            this.zzyw = f();
        }

        private final void zzqd() {
            if (!this.zzyx.zzrx()) {
                this.zzyx = zzey.a(this.zzyx);
            }
        }

        private final void zzqg() {
            if (!this.zzyy.zzrx()) {
                this.zzyy = zzey.a(this.zzyy);
            }
        }

        public static zza zzqh() {
            return (zza) zzyz.d();
        }

        public static zzi zzqi() {
            return zzyz;
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzbr.a[i - 1]) {
                case 1:
                    return new zzi();
                case 2:
                    return new zza((zzbr) null);
                case 3:
                    return a((zzgi) zzyz, "\u0001\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0004\u0000\u0001\u0015\u0002\u0015\u0003\u001b\u0004\u001b", new Object[]{"zzyv", "zzyw", "zzyx", zzb.class, "zzyy", zzj.class});
                case 4:
                    return zzyz;
                case 5:
                    zzgr<zzi> zzgr = zzuo;
                    if (zzgr == null) {
                        synchronized (zzi.class) {
                            zzgr = zzuo;
                            if (zzgr == null) {
                                zzgr = new zzey.zzc<>(zzyz);
                                zzuo = zzgr;
                            }
                        }
                    }
                    return zzgr;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final zzb zzae(int i) {
            return (zzb) this.zzyx.get(i);
        }

        public final zzj zzag(int i) {
            return (zzj) this.zzyy.get(i);
        }

        public final List<Long> zzpv() {
            return this.zzyv;
        }

        public final int zzpw() {
            return this.zzyv.size();
        }

        public final List<Long> zzpy() {
            return this.zzyw;
        }

        public final int zzpz() {
            return this.zzyw.size();
        }

        public final List<zzb> zzqb() {
            return this.zzyx;
        }

        public final int zzqc() {
            return this.zzyx.size();
        }

        public final List<zzj> zzqe() {
            return this.zzyy;
        }

        public final int zzqf() {
            return this.zzyy.size();
        }
    }

    public static final class zzj extends zzey<zzj, zza> implements zzgk {
        private static volatile zzgr<zzj> zzuo;
        /* access modifiers changed from: private */
        public static final zzj zzzb = new zzj();
        private int zzue;
        private int zzwg;
        private zzfg zzza = f();

        public static final class zza extends zzey.zza<zzj, zza> implements zzgk {
            private zza() {
                super(zzj.zzzb);
            }

            /* synthetic */ zza(zzbr zzbr) {
                this();
            }

            public final zza zzal(int i) {
                a();
                ((zzj) this.a).setIndex(i);
                return this;
            }

            public final zza zzbj(long j) {
                a();
                ((zzj) this.a).zzbh(j);
                return this;
            }

            public final zza zzqw() {
                a();
                ((zzj) this.a).zzqn();
                return this;
            }

            public final zza zzr(Iterable<? extends Long> iterable) {
                a();
                ((zzj) this.a).zzm(iterable);
                return this;
            }
        }

        static {
            zzey.a(zzj.class, zzzb);
        }

        private zzj() {
        }

        /* access modifiers changed from: private */
        public final void setIndex(int i) {
            this.zzue |= 1;
            this.zzwg = i;
        }

        /* access modifiers changed from: private */
        public final void zzbh(long j) {
            zzqm();
            this.zzza.zzby(j);
        }

        /* access modifiers changed from: private */
        public final void zzm(Iterable<? extends Long> iterable) {
            zzqm();
            zzdf.a(iterable, this.zzza);
        }

        private final void zzqm() {
            if (!this.zzza.zzrx()) {
                this.zzza = zzey.a(this.zzza);
            }
        }

        /* access modifiers changed from: private */
        public final void zzqn() {
            this.zzza = f();
        }

        public static zza zzqo() {
            return (zza) zzzb.d();
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzbr.a[i - 1]) {
                case 1:
                    return new zzj();
                case 2:
                    return new zza((zzbr) null);
                case 3:
                    return a((zzgi) zzzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u0004\u0000\u0002\u0014", new Object[]{"zzue", "zzwg", "zzza"});
                case 4:
                    return zzzb;
                case 5:
                    zzgr<zzj> zzgr = zzuo;
                    if (zzgr == null) {
                        synchronized (zzj.class) {
                            zzgr = zzuo;
                            if (zzgr == null) {
                                zzgr = new zzey.zzc<>(zzzb);
                                zzuo = zzgr;
                            }
                        }
                    }
                    return zzgr;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final int getIndex() {
            return this.zzwg;
        }

        public final long zzai(int i) {
            return this.zzza.getLong(i);
        }

        public final boolean zzme() {
            return (this.zzue & 1) != 0;
        }

        public final List<Long> zzqk() {
            return this.zzza;
        }

        public final int zzql() {
            return this.zzza.size();
        }
    }

    public static final class zzk extends zzey<zzk, zza> implements zzgk {
        private static volatile zzgr<zzk> zzuo;
        /* access modifiers changed from: private */
        public static final zzk zzzd = new zzk();
        private int zzue;
        private String zzwk = "";
        private long zzwp;
        private String zzwr = "";
        private float zzws;
        private double zzwt;
        private long zzzc;

        public static final class zza extends zzey.zza<zzk, zza> implements zzgk {
            private zza() {
                super(zzk.zzzd);
            }

            /* synthetic */ zza(zzbr zzbr) {
                this();
            }

            public final zza zzbk(long j) {
                a();
                ((zzk) this.a).zzbi(j);
                return this;
            }

            public final zza zzbl(long j) {
                a();
                ((zzk) this.a).zzal(j);
                return this;
            }

            public final zza zzc(double d) {
                a();
                ((zzk) this.a).zzb(d);
                return this;
            }

            public final zza zzdb(String str) {
                a();
                ((zzk) this.a).setName(str);
                return this;
            }

            public final zza zzdc(String str) {
                a();
                ((zzk) this.a).zzcb(str);
                return this;
            }

            public final zza zzqz() {
                a();
                ((zzk) this.a).zzmz();
                return this;
            }

            public final zza zzra() {
                a();
                ((zzk) this.a).zznc();
                return this;
            }

            public final zza zzrb() {
                a();
                ((zzk) this.a).zznf();
                return this;
            }
        }

        static {
            zzey.a(zzk.class, zzzd);
        }

        private zzk() {
        }

        /* access modifiers changed from: private */
        public final void setName(String str) {
            if (str != null) {
                this.zzue |= 2;
                this.zzwk = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzal(long j) {
            this.zzue |= 8;
            this.zzwp = j;
        }

        /* access modifiers changed from: private */
        public final void zzb(double d) {
            this.zzue |= 32;
            this.zzwt = d;
        }

        /* access modifiers changed from: private */
        public final void zzbi(long j) {
            this.zzue |= 1;
            this.zzzc = j;
        }

        /* access modifiers changed from: private */
        public final void zzcb(String str) {
            if (str != null) {
                this.zzue |= 4;
                this.zzwr = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzmz() {
            this.zzue &= -5;
            this.zzwr = zzzd.zzwr;
        }

        /* access modifiers changed from: private */
        public final void zznc() {
            this.zzue &= -9;
            this.zzwp = 0;
        }

        /* access modifiers changed from: private */
        public final void zznf() {
            this.zzue &= -33;
            this.zzwt = 0.0d;
        }

        public static zza zzqu() {
            return (zza) zzzd.d();
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzbr.a[i - 1]) {
                case 1:
                    return new zzk();
                case 2:
                    return new zza((zzbr) null);
                case 3:
                    return a((zzgi) zzzd, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001\u0002\u0000\u0002\b\u0001\u0003\b\u0002\u0004\u0002\u0003\u0005\u0001\u0004\u0006\u0000\u0005", new Object[]{"zzue", "zzzc", "zzwk", "zzwr", "zzwp", "zzws", "zzwt"});
                case 4:
                    return zzzd;
                case 5:
                    zzgr<zzk> zzgr = zzuo;
                    if (zzgr == null) {
                        synchronized (zzk.class) {
                            zzgr = zzuo;
                            if (zzgr == null) {
                                zzgr = new zzey.zzc<>(zzzd);
                                zzuo = zzgr;
                            }
                        }
                    }
                    return zzgr;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final String getName() {
            return this.zzwk;
        }

        public final boolean zzmx() {
            return (this.zzue & 4) != 0;
        }

        public final String zzmy() {
            return this.zzwr;
        }

        public final boolean zzna() {
            return (this.zzue & 8) != 0;
        }

        public final long zznb() {
            return this.zzwp;
        }

        public final boolean zznd() {
            return (this.zzue & 32) != 0;
        }

        public final double zzne() {
            return this.zzwt;
        }

        public final boolean zzqs() {
            return (this.zzue & 1) != 0;
        }

        public final long zzqt() {
            return this.zzzc;
        }
    }
}
