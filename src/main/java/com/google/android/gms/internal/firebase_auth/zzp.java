package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzhs;
import java.util.List;

public final class zzp {

    public static final class zza extends zzhs<zza, C0008zza> implements zzje {
        /* access modifiers changed from: private */
        public static final zza zzak = new zza();
        private static volatile zzjm<zza> zzs;
        private String zzaa = "";
        private String zzab = "";
        private String zzac = "";
        private String zzad = "";
        private String zzae = "";
        private String zzaf = "";
        private String zzag = "";
        private zzhz<zzl> zzah = e();
        private String zzai = "";
        private long zzaj;
        private int zzo;
        private String zzu = "";
        private String zzv = "";
        private String zzw = "";
        private String zzx = "";
        private String zzy = "";
        private String zzz = "";

        /* renamed from: com.google.android.gms.internal.firebase_auth.zzp$zza$zza  reason: collision with other inner class name */
        public static final class C0008zza extends zzhs.zza<zza, C0008zza> implements zzje {
            private C0008zza() {
                super(zza.zzak);
            }

            /* synthetic */ C0008zza(zzo zzo) {
                this();
            }

            public final C0008zza zza(String str) {
                a();
                ((zza) this.a).zzd(str);
                return this;
            }

            public final C0008zza zzb(String str) {
                a();
                ((zza) this.a).zze(str);
                return this;
            }

            public final C0008zza zzc(String str) {
                a();
                ((zza) this.a).zzf(str);
                return this;
            }
        }

        static {
            zzhs.a(zza.class, zzak);
        }

        private zza() {
        }

        public static C0008zza zzd() {
            return (C0008zza) zzak.c();
        }

        /* access modifiers changed from: private */
        public final void zzd(String str) {
            if (str != null) {
                this.zzo |= 1;
                this.zzu = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zze(String str) {
            if (str != null) {
                this.zzo |= 2;
                this.zzv = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzf(String str) {
            if (str != null) {
                this.zzo |= 8192;
                this.zzai = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzo.a[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0008zza((zzo) null);
                case 3:
                    return a((zzjc) zzak, "\u0001\u0010\u0000\u0001\u0001\u0010\u0010\u0000\u0001\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\b\u0003\u0005\b\u0004\u0006\b\u0005\u0007\b\u0006\b\b\u0007\t\b\b\n\b\t\u000b\b\n\f\b\u000b\r\b\f\u000e\u001b\u000f\b\r\u0010\u0003\u000e", new Object[]{"zzo", "zzu", "zzv", "zzw", "zzx", "zzy", "zzz", "zzaa", "zzab", "zzac", "zzad", "zzae", "zzaf", "zzag", "zzah", zzl.class, "zzai", "zzaj"});
                case 4:
                    return zzak;
                case 5:
                    zzjm<zza> zzjm = zzs;
                    if (zzjm == null) {
                        synchronized (zza.class) {
                            zzjm = zzs;
                            if (zzjm == null) {
                                zzjm = new zzhs.zzc<>(zzak);
                                zzs = zzjm;
                            }
                        }
                    }
                    return zzjm;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    public static final class zzb extends zzhs<zzb, zza> implements zzje {
        /* access modifiers changed from: private */
        public static final zzb zzat = new zzb();
        private static volatile zzjm<zzb> zzs;
        private String zzaf = "";
        private String zzal = "";
        private String zzam = "";
        private zzhz<String> zzan = zzhs.e();
        private boolean zzao;
        private boolean zzap;
        private boolean zzaq;
        private zzhz<String> zzar = zzhs.e();
        private byte zzas = 2;
        private int zzo;
        private String zzx = "";

        public static final class zza extends zzhs.zza<zzb, zza> implements zzje {
            private zza() {
                super(zzb.zzat);
            }

            /* synthetic */ zza(zzo zzo) {
                this();
            }
        }

        static {
            zzhs.a(zzb.class, zzat);
        }

        private zzb() {
        }

        public static zzjm<zzb> zzm() {
            return (zzjm) zzat.a(zzhs.zzd.zzaat, (Object) null, (Object) null);
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            int i2 = 0;
            switch (zzo.a[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza((zzo) null);
                case 3:
                    return a((zzjc) zzat, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0002\u0001\u0001Ԉ\u0000\u0002\b\u0001\u0003\u001a\u0004\u0007\u0002\u0005\b\u0003\u0006\u0007\u0004\u0007\u0007\u0005\b\b\u0006\t\u001a", new Object[]{"zzo", "zzal", "zzam", "zzan", "zzao", "zzx", "zzap", "zzaq", "zzaf", "zzar"});
                case 4:
                    return zzat;
                case 5:
                    zzjm<zzb> zzjm = zzs;
                    if (zzjm == null) {
                        synchronized (zzb.class) {
                            zzjm = zzs;
                            if (zzjm == null) {
                                zzjm = new zzhs.zzc<>(zzat);
                                zzs = zzjm;
                            }
                        }
                    }
                    return zzjm;
                case 6:
                    return Byte.valueOf(this.zzas);
                case 7:
                    if (obj != null) {
                        i2 = 1;
                    }
                    this.zzas = (byte) i2;
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final String getProviderId() {
            return this.zzx;
        }

        public final String zzf() {
            return this.zzam;
        }

        public final List<String> zzg() {
            return this.zzan;
        }

        public final int zzh() {
            return this.zzan.size();
        }

        public final boolean zzi() {
            return this.zzao;
        }

        public final boolean zzj() {
            return this.zzap;
        }

        public final List<String> zzk() {
            return this.zzar;
        }

        public final int zzl() {
            return this.zzar.size();
        }
    }

    public static final class zzc extends zzhs<zzc, zza> implements zzje {
        /* access modifiers changed from: private */
        public static final zzc zzax = new zzc();
        private static volatile zzjm<zzc> zzs;
        private String zzau = "";
        private long zzav;
        private String zzaw = "";
        private int zzo;

        public static final class zza extends zzhs.zza<zzc, zza> implements zzje {
            private zza() {
                super(zzc.zzax);
            }

            /* synthetic */ zza(zzo zzo) {
                this();
            }

            public final zza zzg(String str) {
                a();
                ((zzc) this.a).zzh(str);
                return this;
            }
        }

        static {
            zzhs.a(zzc.class, zzax);
        }

        private zzc() {
        }

        /* access modifiers changed from: private */
        public final void zzh(String str) {
            if (str != null) {
                this.zzo |= 4;
                this.zzaw = str;
                return;
            }
            throw new NullPointerException();
        }

        public static zza zzo() {
            return (zza) zzax.c();
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzo.a[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza((zzo) null);
                case 3:
                    return a((zzjc) zzax, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\b\u0000\u0002\u0002\u0001\u0003\b\u0002", new Object[]{"zzo", "zzau", "zzav", "zzaw"});
                case 4:
                    return zzax;
                case 5:
                    zzjm<zzc> zzjm = zzs;
                    if (zzjm == null) {
                        synchronized (zzc.class) {
                            zzjm = zzs;
                            if (zzjm == null) {
                                zzjm = new zzhs.zzc<>(zzax);
                                zzs = zzjm;
                            }
                        }
                    }
                    return zzjm;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    public static final class zzd extends zzhs<zzd, zza> implements zzje {
        /* access modifiers changed from: private */
        public static final zzd zzba = new zzd();
        private static volatile zzjm<zzd> zzs;
        private String zzai = "";
        private long zzaj;
        private String zzaw = "";
        private String zzay = "";
        private String zzaz = "";
        private int zzo;

        public static final class zza extends zzhs.zza<zzd, zza> implements zzje {
            private zza() {
                super(zzd.zzba);
            }

            /* synthetic */ zza(zzo zzo) {
                this();
            }

            public final zza zzi(String str) {
                a();
                ((zzd) this.a).zzm(str);
                return this;
            }

            public final zza zzj(String str) {
                a();
                ((zzd) this.a).zzn(str);
                return this;
            }

            public final zza zzk(String str) {
                a();
                ((zzd) this.a).zzh(str);
                return this;
            }

            public final zza zzl(String str) {
                a();
                ((zzd) this.a).zzf(str);
                return this;
            }
        }

        static {
            zzhs.a(zzd.class, zzba);
        }

        private zzd() {
        }

        /* access modifiers changed from: private */
        public final void zzf(String str) {
            if (str != null) {
                this.zzo |= 8;
                this.zzai = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzh(String str) {
            if (str != null) {
                this.zzo |= 4;
                this.zzaw = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzm(String str) {
            if (str != null) {
                this.zzo |= 1;
                this.zzay = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzn(String str) {
            if (str != null) {
                this.zzo |= 2;
                this.zzaz = str;
                return;
            }
            throw new NullPointerException();
        }

        public static zza zzq() {
            return (zza) zzba.c();
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzo.a[i - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza((zzo) null);
                case 3:
                    return a((zzjc) zzba, "\u0001\u0005\u0000\u0001\u0001\u0007\u0005\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0006\b\u0003\u0007\u0003\u0004", new Object[]{"zzo", "zzay", "zzaz", "zzaw", "zzai", "zzaj"});
                case 4:
                    return zzba;
                case 5:
                    zzjm<zzd> zzjm = zzs;
                    if (zzjm == null) {
                        synchronized (zzd.class) {
                            zzjm = zzs;
                            if (zzjm == null) {
                                zzjm = new zzhs.zzc<>(zzba);
                                zzs = zzjm;
                            }
                        }
                    }
                    return zzjm;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    public static final class zze extends zzhs<zze, zza> implements zzje {
        /* access modifiers changed from: private */
        public static final zze zzbe = new zze();
        private static volatile zzjm<zze> zzs;
        private String zzal = "";
        private byte zzas = 2;
        private String zzau = "";
        private String zzaw = "";
        private String zzaz = "";
        private String zzbb = "";
        private long zzbc;
        private boolean zzbd;
        private int zzo;

        public static final class zza extends zzhs.zza<zze, zza> implements zzje {
            private zza() {
                super(zze.zzbe);
            }

            /* synthetic */ zza(zzo zzo) {
                this();
            }
        }

        static {
            zzhs.a(zze.class, zzbe);
        }

        private zze() {
        }

        public static zzjm<zze> zzm() {
            return (zzjm) zzbe.a(zzhs.zzd.zzaat, (Object) null, (Object) null);
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            int i2 = 0;
            switch (zzo.a[i - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zza((zzo) null);
                case 3:
                    return a((zzjc) zzbe, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0001\u0001Ԉ\u0000\u0002\b\u0001\u0003\b\u0002\u0004\b\u0003\u0005\u0002\u0004\u0006\b\u0005\u0007\u0007\u0006", new Object[]{"zzo", "zzal", "zzaw", "zzaz", "zzbb", "zzbc", "zzau", "zzbd"});
                case 4:
                    return zzbe;
                case 5:
                    zzjm<zze> zzjm = zzs;
                    if (zzjm == null) {
                        synchronized (zze.class) {
                            zzjm = zzs;
                            if (zzjm == null) {
                                zzjm = new zzhs.zzc<>(zzbe);
                                zzs = zzjm;
                            }
                        }
                    }
                    return zzjm;
                case 6:
                    return Byte.valueOf(this.zzas);
                case 7:
                    if (obj != null) {
                        i2 = 1;
                    }
                    this.zzas = (byte) i2;
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final String getEmail() {
            return this.zzaz;
        }

        public final String getIdToken() {
            return this.zzaw;
        }

        public final String getLocalId() {
            return this.zzau;
        }

        public final String zzs() {
            return this.zzbb;
        }

        public final long zzt() {
            return this.zzbc;
        }

        public final boolean zzu() {
            return this.zzbd;
        }
    }

    public static final class zzf extends zzhs<zzf, zza> implements zzje {
        /* access modifiers changed from: private */
        public static final zzf zzbh = new zzf();
        private static volatile zzjm<zzf> zzs;
        private long zzav;
        private String zzaw = "";
        private zzhz<String> zzbf = zzhs.e();
        private zzhz<String> zzbg = zzhs.e();
        private int zzo;

        public static final class zza extends zzhs.zza<zzf, zza> implements zzje {
            private zza() {
                super(zzf.zzbh);
            }

            /* synthetic */ zza(zzo zzo) {
                this();
            }

            public final zza zzo(String str) {
                a();
                ((zzf) this.a).zzh(str);
                return this;
            }
        }

        static {
            zzhs.a(zzf.class, zzbh);
        }

        private zzf() {
        }

        /* access modifiers changed from: private */
        public final void zzh(String str) {
            if (str != null) {
                this.zzo |= 1;
                this.zzaw = str;
                return;
            }
            throw new NullPointerException();
        }

        public static zza zzw() {
            return (zza) zzbh.c();
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzo.a[i - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zza((zzo) null);
                case 3:
                    return a((zzjc) zzbh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0002\u0000\u0001\b\u0000\u0002\u001a\u0003\u001a\u0004\u0002\u0001", new Object[]{"zzo", "zzaw", "zzbf", "zzbg", "zzav"});
                case 4:
                    return zzbh;
                case 5:
                    zzjm<zzf> zzjm = zzs;
                    if (zzjm == null) {
                        synchronized (zzf.class) {
                            zzjm = zzs;
                            if (zzjm == null) {
                                zzjm = new zzhs.zzc<>(zzbh);
                                zzs = zzjm;
                            }
                        }
                    }
                    return zzjm;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    public static final class zzg extends zzhs<zzg, zza> implements zzje {
        /* access modifiers changed from: private */
        public static final zzg zzbj = new zzg();
        private static volatile zzjm<zzg> zzs;
        private String zzal = "";
        private byte zzas = 2;
        private zzhz<zzz> zzbi = e();
        private int zzo;

        public static final class zza extends zzhs.zza<zzg, zza> implements zzje {
            private zza() {
                super(zzg.zzbj);
            }

            /* synthetic */ zza(zzo zzo) {
                this();
            }
        }

        static {
            zzhs.a(zzg.class, zzbj);
        }

        private zzg() {
        }

        public static zzjm<zzg> zzm() {
            return (zzjm) zzbj.a(zzhs.zzd.zzaat, (Object) null, (Object) null);
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            int i2 = 0;
            switch (zzo.a[i - 1]) {
                case 1:
                    return new zzg();
                case 2:
                    return new zza((zzo) null);
                case 3:
                    return a((zzjc) zzbj, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0001\u0001Ԉ\u0000\u0002\u001b", new Object[]{"zzo", "zzal", "zzbi", zzz.class});
                case 4:
                    return zzbj;
                case 5:
                    zzjm<zzg> zzjm = zzs;
                    if (zzjm == null) {
                        synchronized (zzg.class) {
                            zzjm = zzs;
                            if (zzjm == null) {
                                zzjm = new zzhs.zzc<>(zzbj);
                                zzs = zzjm;
                            }
                        }
                    }
                    return zzjm;
                case 6:
                    return Byte.valueOf(this.zzas);
                case 7:
                    if (obj != null) {
                        i2 = 1;
                    }
                    this.zzas = (byte) i2;
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final zzz zzb(int i) {
            return (zzz) this.zzbi.get(i);
        }

        public final int zzy() {
            return this.zzbi.size();
        }
    }

    public static final class zzh extends zzhs<zzh, zza> implements zzje {
        /* access modifiers changed from: private */
        public static final zzh zzby = new zzh();
        private static volatile zzjm<zzh> zzs;
        private String zzai = "";
        private long zzaj;
        private String zzaw = "";
        private String zzaz = "";
        private int zzbk;
        private String zzbl = "";
        private String zzbm = "";
        private String zzbn = "";
        private String zzbo = "";
        private String zzbp = "";
        private String zzbq = "";
        private String zzbr = "";
        private String zzbs = "";
        private boolean zzbt;
        private String zzbu = "";
        private boolean zzbv;
        private String zzbw = "";
        private boolean zzbx;
        private int zzo;

        public static final class zza extends zzhs.zza<zzh, zza> implements zzje {
            private zza() {
                super(zzh.zzby);
            }

            /* synthetic */ zza(zzo zzo) {
                this();
            }

            public final zza zza(zzfw zzfw) {
                a();
                ((zzh) this.a).zzb(zzfw);
                return this;
            }

            public final zza zza(boolean z) {
                a();
                ((zzh) this.a).zzc(z);
                return this;
            }

            public final zza zzb(boolean z) {
                a();
                ((zzh) this.a).zzd(z);
                return this;
            }

            public final zza zzp(String str) {
                a();
                ((zzh) this.a).zzn(str);
                return this;
            }

            public final zza zzq(String str) {
                a();
                ((zzh) this.a).zzz(str);
                return this;
            }

            public final zza zzr(String str) {
                a();
                ((zzh) this.a).zzh(str);
                return this;
            }

            public final zza zzs(String str) {
                a();
                ((zzh) this.a).zzaa(str);
                return this;
            }

            public final zza zzt(String str) {
                a();
                ((zzh) this.a).zzab(str);
                return this;
            }

            public final zza zzu(String str) {
                a();
                ((zzh) this.a).zzac(str);
                return this;
            }

            public final zza zzv(String str) {
                a();
                ((zzh) this.a).zzad(str);
                return this;
            }

            public final zza zzw(String str) {
                a();
                ((zzh) this.a).zzae(str);
                return this;
            }

            public final zza zzx(String str) {
                a();
                ((zzh) this.a).zzf(str);
                return this;
            }

            public final zza zzy(String str) {
                a();
                ((zzh) this.a).zzaf(str);
                return this;
            }
        }

        static {
            zzhs.a(zzh.class, zzby);
        }

        private zzh() {
        }

        public static zza zzaa() {
            return (zza) zzby.c();
        }

        /* access modifiers changed from: private */
        public final void zzaa(String str) {
            if (str != null) {
                this.zzo |= 128;
                this.zzbp = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzab(String str) {
            if (str != null) {
                this.zzo |= 256;
                this.zzbq = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzac(String str) {
            if (str != null) {
                this.zzo |= 512;
                this.zzbr = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzad(String str) {
            if (str != null) {
                this.zzo |= 1024;
                this.zzbs = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzae(String str) {
            if (str != null) {
                this.zzo |= 4096;
                this.zzbu = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzaf(String str) {
            if (str != null) {
                this.zzo |= 65536;
                this.zzbw = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzb(zzfw zzfw) {
            if (zzfw != null) {
                this.zzo |= 1;
                this.zzbk = zzfw.zzbq();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzc(boolean z) {
            this.zzo |= 2048;
            this.zzbt = z;
        }

        /* access modifiers changed from: private */
        public final void zzd(boolean z) {
            this.zzo |= 8192;
            this.zzbv = z;
        }

        /* access modifiers changed from: private */
        public final void zzf(String str) {
            if (str != null) {
                this.zzo |= 16384;
                this.zzai = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzh(String str) {
            if (str != null) {
                this.zzo |= 64;
                this.zzaw = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzn(String str) {
            if (str != null) {
                this.zzo |= 2;
                this.zzaz = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzz(String str) {
            if (str != null) {
                this.zzo |= 32;
                this.zzbo = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzo.a[i - 1]) {
                case 1:
                    return new zzh();
                case 2:
                    return new zza((zzo) null);
                case 3:
                    return a((zzjc) zzby, "\u0001\u0012\u0000\u0001\u0001\u0013\u0012\u0000\u0000\u0000\u0001\f\u0000\u0002\b\u0001\u0003\b\u0002\u0004\b\u0003\u0005\b\u0004\u0006\b\u0005\u0007\b\u0006\b\b\u0007\t\b\b\n\b\t\u000b\b\n\f\u0007\u000b\r\b\f\u000e\u0007\r\u000f\b\u000e\u0010\u0003\u000f\u0012\b\u0010\u0013\u0007\u0011", new Object[]{"zzo", "zzbk", zzfw.zzbr(), "zzaz", "zzbl", "zzbm", "zzbn", "zzbo", "zzaw", "zzbp", "zzbq", "zzbr", "zzbs", "zzbt", "zzbu", "zzbv", "zzai", "zzaj", "zzbw", "zzbx"});
                case 4:
                    return zzby;
                case 5:
                    zzjm<zzh> zzjm = zzs;
                    if (zzjm == null) {
                        synchronized (zzh.class) {
                            zzjm = zzs;
                            if (zzjm == null) {
                                zzjm = new zzhs.zzc<>(zzby);
                                zzs = zzjm;
                            }
                        }
                    }
                    return zzjm;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    public static final class zzi extends zzhs<zzi, zza> implements zzje {
        /* access modifiers changed from: private */
        public static final zzi zzcb = new zzi();
        private static volatile zzjm<zzi> zzs;
        private String zzai = "";
        private long zzaj;
        private String zzay = "";
        private String zzaz = "";
        private String zzbz = "";
        private String zzca = "";
        private int zzo;

        public static final class zza extends zzhs.zza<zzi, zza> implements zzje {
            private zza() {
                super(zzi.zzcb);
            }

            /* synthetic */ zza(zzo zzo) {
                this();
            }

            public final zza zzag(String str) {
                a();
                ((zzi) this.a).zzm(str);
                return this;
            }

            public final zza zzah(String str) {
                a();
                ((zzi) this.a).zzaj(str);
                return this;
            }

            public final zza zzai(String str) {
                a();
                ((zzi) this.a).zzf(str);
                return this;
            }
        }

        static {
            zzhs.a(zzi.class, zzcb);
        }

        private zzi() {
        }

        public static zza zzac() {
            return (zza) zzcb.c();
        }

        /* access modifiers changed from: private */
        public final void zzaj(String str) {
            if (str != null) {
                this.zzo |= 2;
                this.zzbz = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzf(String str) {
            if (str != null) {
                this.zzo |= 16;
                this.zzai = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzm(String str) {
            if (str != null) {
                this.zzo |= 1;
                this.zzay = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzo.a[i - 1]) {
                case 1:
                    return new zzi();
                case 2:
                    return new zza((zzo) null);
                case 3:
                    return a((zzjc) zzcb, "\u0001\u0006\u0000\u0001\u0001\u0007\u0006\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\b\u0003\u0006\b\u0004\u0007\u0003\u0005", new Object[]{"zzo", "zzay", "zzbz", "zzca", "zzaz", "zzai", "zzaj"});
                case 4:
                    return zzcb;
                case 5:
                    zzjm<zzi> zzjm = zzs;
                    if (zzjm == null) {
                        synchronized (zzi.class) {
                            zzjm = zzs;
                            if (zzjm == null) {
                                zzjm = new zzhs.zzc<>(zzcb);
                                zzs = zzjm;
                            }
                        }
                    }
                    return zzjm;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    public static final class zzj extends zzhs<zzj, zza> implements zzje {
        /* access modifiers changed from: private */
        public static final zzj zzcd = new zzj();
        private static volatile zzjm<zzj> zzs;
        private String zzal = "";
        private byte zzas = 2;
        private String zzaz = "";
        private String zzbo = "";
        private int zzcc;
        private int zzo;

        public static final class zza extends zzhs.zza<zzj, zza> implements zzje {
            private zza() {
                super(zzj.zzcd);
            }

            /* synthetic */ zza(zzo zzo) {
                this();
            }
        }

        static {
            zzhs.a(zzj.class, zzcd);
        }

        private zzj() {
        }

        public static zzjm<zzj> zzm() {
            return (zzjm) zzcd.a(zzhs.zzd.zzaat, (Object) null, (Object) null);
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            int i2 = 0;
            switch (zzo.a[i - 1]) {
                case 1:
                    return new zzj();
                case 2:
                    return new zza((zzo) null);
                case 3:
                    return a((zzjc) zzcd, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0001\u0001Ԉ\u0000\u0002\b\u0001\u0003\b\u0002\u0004\f\u0003", new Object[]{"zzo", "zzal", "zzaz", "zzbo", "zzcc", zzfw.zzbr()});
                case 4:
                    return zzcd;
                case 5:
                    zzjm<zzj> zzjm = zzs;
                    if (zzjm == null) {
                        synchronized (zzj.class) {
                            zzjm = zzs;
                            if (zzjm == null) {
                                zzjm = new zzhs.zzc<>(zzcd);
                                zzs = zzjm;
                            }
                        }
                    }
                    return zzjm;
                case 6:
                    return Byte.valueOf(this.zzas);
                case 7:
                    if (obj != null) {
                        i2 = 1;
                    }
                    this.zzas = (byte) i2;
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final String getEmail() {
            return this.zzaz;
        }

        public final String zzae() {
            return this.zzbo;
        }

        public final zzfw zzaf() {
            zzfw zzk = zzfw.zzk(this.zzcc);
            return zzk == null ? zzfw.OOB_REQ_TYPE_UNSPECIFIED : zzk;
        }
    }

    public static final class zzk extends zzhs<zzk, zza> implements zzje {
        /* access modifiers changed from: private */
        public static final zzk zzci = new zzk();
        private static volatile zzjm<zzk> zzs;
        private String zzai = "";
        private long zzaj;
        private String zzce = "";
        private String zzcf = "";
        private String zzcg = "";
        private String zzch = "";
        private int zzo;

        public static final class zza extends zzhs.zza<zzk, zza> implements zzje {
            private zza() {
                super(zzk.zzci);
            }

            /* synthetic */ zza(zzo zzo) {
                this();
            }

            public final zza zzak(String str) {
                a();
                ((zzk) this.a).zzam(str);
                return this;
            }

            public final zza zzal(String str) {
                a();
                ((zzk) this.a).zzf(str);
                return this;
            }
        }

        static {
            zzhs.a(zzk.class, zzci);
        }

        private zzk() {
        }

        public static zza zzah() {
            return (zza) zzci.c();
        }

        /* access modifiers changed from: private */
        public final void zzam(String str) {
            if (str != null) {
                this.zzo |= 1;
                this.zzce = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzf(String str) {
            if (str != null) {
                this.zzo |= 16;
                this.zzai = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzo.a[i - 1]) {
                case 1:
                    return new zzk();
                case 2:
                    return new zza((zzo) null);
                case 3:
                    return a((zzjc) zzci, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\b\u0003\u0005\b\u0004\u0006\u0003\u0005", new Object[]{"zzo", "zzce", "zzcf", "zzcg", "zzch", "zzai", "zzaj"});
                case 4:
                    return zzci;
                case 5:
                    zzjm<zzk> zzjm = zzs;
                    if (zzjm == null) {
                        synchronized (zzk.class) {
                            zzjm = zzs;
                            if (zzjm == null) {
                                zzjm = new zzhs.zzc<>(zzci);
                                zzs = zzjm;
                            }
                        }
                    }
                    return zzjm;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    public static final class zzl extends zzhs<zzl, zza> implements zzje {
        private static final zzia<Integer, zzv> zzcv = new zzq();
        /* access modifiers changed from: private */
        public static final zzl zzda = new zzl();
        private static volatile zzjm<zzl> zzs;
        private String zzai = "";
        private long zzaj;
        private String zzau = "";
        private long zzav;
        private String zzaw = "";
        private String zzay = "";
        private String zzaz = "";
        private String zzcj = "";
        private String zzck = "";
        private zzhz<String> zzcl = zzhs.e();
        private boolean zzcm;
        private boolean zzcn;
        private String zzco = "";
        private String zzcp = "";
        private zzkh zzcq;
        private boolean zzcr;
        private String zzcs = "";
        private String zzct = "";
        private zzhx zzcu = d();
        private boolean zzcw;
        private zzhz<String> zzcx = zzhs.e();
        private long zzcy;
        private long zzcz;
        private int zzo;

        public static final class zza extends zzhs.zza<zzl, zza> implements zzje {
            private zza() {
                super(zzl.zzda);
            }

            /* synthetic */ zza(zzo zzo) {
                this();
            }

            public final zza zzap(String str) {
                a();
                ((zzl) this.a).zzh(str);
                return this;
            }

            public final zza zzaq(String str) {
                a();
                ((zzl) this.a).zzan(str);
                return this;
            }

            public final zza zzar(String str) {
                a();
                ((zzl) this.a).zzn(str);
                return this;
            }

            public final zza zzas(String str) {
                a();
                ((zzl) this.a).setPassword(str);
                return this;
            }

            public final zza zzat(String str) {
                a();
                ((zzl) this.a).zzm(str);
                return this;
            }

            public final zza zzau(String str) {
                a();
                ((zzl) this.a).zzao(str);
                return this;
            }

            public final zza zzav(String str) {
                a();
                ((zzl) this.a).zzf(str);
                return this;
            }

            public final zza zzc(Iterable<? extends zzv> iterable) {
                a();
                ((zzl) this.a).zza(iterable);
                return this;
            }

            public final zza zzd(Iterable<String> iterable) {
                a();
                ((zzl) this.a).zzb(iterable);
                return this;
            }

            public final zza zzf(boolean z) {
                a();
                ((zzl) this.a).zze(z);
                return this;
            }
        }

        static {
            zzhs.a(zzl.class, zzda);
        }

        private zzl() {
        }

        /* access modifiers changed from: private */
        public final void setPassword(String str) {
            if (str != null) {
                this.zzo |= 16;
                this.zzck = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zza(Iterable<? extends zzv> iterable) {
            if (!this.zzcu.zzfx()) {
                zzhx zzhx = this.zzcu;
                int size = zzhx.size();
                this.zzcu = zzhx.zzav(size == 0 ? 10 : size << 1);
            }
            for (zzv zzbq : iterable) {
                this.zzcu.zzaw(zzbq.zzbq());
            }
        }

        public static zza zzaj() {
            return (zza) zzda.c();
        }

        /* access modifiers changed from: private */
        public final void zzan(String str) {
            if (str != null) {
                this.zzo |= 4;
                this.zzcj = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzao(String str) {
            if (str != null) {
                this.zzo |= 16384;
                this.zzct = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzb(Iterable<String> iterable) {
            if (!this.zzcx.zzfx()) {
                zzhz<String> zzhz = this.zzcx;
                int size = zzhz.size();
                this.zzcx = zzhz.zzo(size == 0 ? 10 : size << 1);
            }
            zzfx.a(iterable, this.zzcx);
        }

        /* access modifiers changed from: private */
        public final void zze(boolean z) {
            this.zzo |= 32768;
            this.zzcw = z;
        }

        /* access modifiers changed from: private */
        public final void zzf(String str) {
            if (str != null) {
                this.zzo |= 262144;
                this.zzai = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzh(String str) {
            if (str != null) {
                this.zzo |= 1;
                this.zzaw = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzm(String str) {
            if (str != null) {
                this.zzo |= 32;
                this.zzay = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzn(String str) {
            if (str != null) {
                this.zzo |= 8;
                this.zzaz = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzo.a[i - 1]) {
                case 1:
                    return new zzl();
                case 2:
                    return new zza((zzo) null);
                case 3:
                    return a((zzjc) zzda, "\u0001\u0017\u0000\u0001\u0002\u001a\u0017\u0000\u0003\u0000\u0002\b\u0000\u0003\b\u0001\u0004\b\u0002\u0005\b\u0003\u0006\b\u0004\u0007\u001a\b\b\u0005\t\u0007\u0006\n\u0007\u0007\u000b\b\b\f\b\t\r\t\n\u000e\u0007\u000b\u000f\b\f\u0010\u0002\r\u0011\b\u000e\u0012\u001e\u0013\u0007\u000f\u0014\u001a\u0015\u0002\u0010\u0016\u0002\u0011\u0019\b\u0012\u001a\u0003\u0013", new Object[]{"zzo", "zzaw", "zzau", "zzcj", "zzaz", "zzck", "zzcl", "zzay", "zzcm", "zzcn", "zzco", "zzcp", "zzcq", "zzcr", "zzcs", "zzav", "zzct", "zzcu", zzv.zzbr(), "zzcw", "zzcx", "zzcy", "zzcz", "zzai", "zzaj"});
                case 4:
                    return zzda;
                case 5:
                    zzjm<zzl> zzjm = zzs;
                    if (zzjm == null) {
                        synchronized (zzl.class) {
                            zzjm = zzs;
                            if (zzjm == null) {
                                zzjm = new zzhs.zzc<>(zzda);
                                zzs = zzjm;
                            }
                        }
                    }
                    return zzjm;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    public static final class zzm extends zzhs<zzm, zza> implements zzje {
        /* access modifiers changed from: private */
        public static final zzm zzdd = new zzm();
        private static volatile zzjm<zzm> zzs;
        private String zzal = "";
        private byte zzas = 2;
        private String zzau = "";
        private String zzaw = "";
        private String zzaz = "";
        private String zzbb = "";
        private long zzbc;
        private String zzbo = "";
        private String zzcj = "";
        private zzhz<String> zzcl = zzhs.e();
        private boolean zzcm;
        private String zzct = "";
        private zzhz<zzu> zzdb = e();
        private String zzdc = "";
        private int zzo;

        public static final class zza extends zzhs.zza<zzm, zza> implements zzje {
            private zza() {
                super(zzm.zzdd);
            }

            /* synthetic */ zza(zzo zzo) {
                this();
            }
        }

        static {
            zzhs.a(zzm.class, zzdd);
        }

        private zzm() {
        }

        public static zzjm<zzm> zzm() {
            return (zzjm) zzdd.a(zzhs.zzd.zzaat, (Object) null, (Object) null);
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            int i2 = 0;
            switch (zzo.a[i - 1]) {
                case 1:
                    return new zzm();
                case 2:
                    return new zza((zzo) null);
                case 3:
                    return a((zzjc) zzdd, "\u0001\r\u0000\u0001\u0001\r\r\u0000\u0002\u0001\u0001Ԉ\u0000\u0002\b\u0001\u0003\b\u0002\u0004\b\u0003\u0005\u001a\u0006\b\u0004\u0007\u001b\b\b\u0005\t\b\u0006\n\b\u0007\u000b\u0002\b\f\b\t\r\u0007\n", new Object[]{"zzo", "zzal", "zzau", "zzaz", "zzcj", "zzcl", "zzaw", "zzdb", zzu.class, "zzbo", "zzct", "zzbb", "zzbc", "zzdc", "zzcm"});
                case 4:
                    return zzdd;
                case 5:
                    zzjm<zzm> zzjm = zzs;
                    if (zzjm == null) {
                        synchronized (zzm.class) {
                            zzjm = zzs;
                            if (zzjm == null) {
                                zzjm = new zzhs.zzc<>(zzdd);
                                zzs = zzjm;
                            }
                        }
                    }
                    return zzjm;
                case 6:
                    return Byte.valueOf(this.zzas);
                case 7:
                    if (obj != null) {
                        i2 = 1;
                    }
                    this.zzas = (byte) i2;
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Deprecated
        public final String getDisplayName() {
            return this.zzcj;
        }

        @Deprecated
        public final String getEmail() {
            return this.zzaz;
        }

        public final String getIdToken() {
            return this.zzaw;
        }

        @Deprecated
        public final List<zzu> zzal() {
            return this.zzdb;
        }

        @Deprecated
        public final String zzam() {
            return this.zzct;
        }

        public final String zzan() {
            return this.zzdc;
        }

        public final boolean zzao() {
            return this.zzcm;
        }

        public final String zzs() {
            return this.zzbb;
        }

        public final long zzt() {
            return this.zzbc;
        }
    }

    public static final class zzn extends zzhs<zzn, zza> implements zzje {
        /* access modifiers changed from: private */
        public static final zzn zzdf = new zzn();
        private static volatile zzjm<zzn> zzs;
        private String zzai = "";
        private long zzaj;
        private String zzaw = "";
        private String zzaz = "";
        private String zzcj = "";
        private String zzck = "";
        private boolean zzcm;
        private String zzco = "";
        private String zzcp = "";
        private String zzcs = "";
        private String zzct = "";
        private boolean zzde;
        private int zzo;

        public static final class zza extends zzhs.zza<zzn, zza> implements zzje {
            private zza() {
                super(zzn.zzdf);
            }

            /* synthetic */ zza(zzo zzo) {
                this();
            }

            public final zza zzaw(String str) {
                a();
                ((zzn) this.a).zzn(str);
                return this;
            }

            public final zza zzax(String str) {
                a();
                ((zzn) this.a).setPassword(str);
                return this;
            }

            public final zza zzay(String str) {
                a();
                ((zzn) this.a).zzf(str);
                return this;
            }
        }

        static {
            zzhs.a(zzn.class, zzdf);
        }

        private zzn() {
        }

        /* access modifiers changed from: private */
        public final void setPassword(String str) {
            if (str != null) {
                this.zzo |= 2;
                this.zzck = str;
                return;
            }
            throw new NullPointerException();
        }

        public static zza zzaq() {
            return (zza) zzdf.c();
        }

        /* access modifiers changed from: private */
        public final void zzf(String str) {
            if (str != null) {
                this.zzo |= 1024;
                this.zzai = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzn(String str) {
            if (str != null) {
                this.zzo |= 1;
                this.zzaz = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzo.a[i - 1]) {
                case 1:
                    return new zzn();
                case 2:
                    return new zza((zzo) null);
                case 3:
                    return a((zzjc) zzdf, "\u0001\f\u0000\u0001\u0001\u000e\f\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\b\u0003\u0005\b\u0004\u0006\b\u0005\u0007\b\u0006\b\u0007\u0007\t\b\b\n\u0007\t\r\b\n\u000e\u0003\u000b", new Object[]{"zzo", "zzaz", "zzck", "zzcj", "zzco", "zzcp", "zzcs", "zzaw", "zzcm", "zzct", "zzde", "zzai", "zzaj"});
                case 4:
                    return zzdf;
                case 5:
                    zzjm<zzn> zzjm = zzs;
                    if (zzjm == null) {
                        synchronized (zzn.class) {
                            zzjm = zzs;
                            if (zzjm == null) {
                                zzjm = new zzhs.zzc<>(zzdf);
                                zzs = zzjm;
                            }
                        }
                    }
                    return zzjm;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    public static final class zzo extends zzhs<zzo, zza> implements zzje {
        /* access modifiers changed from: private */
        public static final zzo zzdg = new zzo();
        private static volatile zzjm<zzo> zzs;
        private String zzal = "";
        private byte zzas = 2;
        private String zzau = "";
        private String zzaw = "";
        private String zzaz = "";
        private String zzbb = "";
        private long zzbc;
        private String zzcj = "";
        private int zzo;

        public static final class zza extends zzhs.zza<zzo, zza> implements zzje {
            private zza() {
                super(zzo.zzdg);
            }

            /* synthetic */ zza(zzo zzo) {
                this();
            }
        }

        static {
            zzhs.a(zzo.class, zzdg);
        }

        private zzo() {
        }

        public static zzjm<zzo> zzm() {
            return (zzjm) zzdg.a(zzhs.zzd.zzaat, (Object) null, (Object) null);
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            int i2 = 0;
            switch (zzo.a[i - 1]) {
                case 1:
                    return new zzo();
                case 2:
                    return new zza((zzo) null);
                case 3:
                    return a((zzjc) zzdg, "\u0001\u0007\u0000\u0001\u0001\b\u0007\u0000\u0000\u0001\u0001Ԉ\u0000\u0002\b\u0001\u0004\b\u0002\u0005\b\u0003\u0006\b\u0004\u0007\u0002\u0005\b\b\u0006", new Object[]{"zzo", "zzal", "zzaw", "zzcj", "zzaz", "zzbb", "zzbc", "zzau"});
                case 4:
                    return zzdg;
                case 5:
                    zzjm<zzo> zzjm = zzs;
                    if (zzjm == null) {
                        synchronized (zzo.class) {
                            zzjm = zzs;
                            if (zzjm == null) {
                                zzjm = new zzhs.zzc<>(zzdg);
                                zzs = zzjm;
                            }
                        }
                    }
                    return zzjm;
                case 6:
                    return Byte.valueOf(this.zzas);
                case 7:
                    if (obj != null) {
                        i2 = 1;
                    }
                    this.zzas = (byte) i2;
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final String getDisplayName() {
            return this.zzcj;
        }

        public final String getEmail() {
            return this.zzaz;
        }

        public final String getIdToken() {
            return this.zzaw;
        }

        public final String zzs() {
            return this.zzbb;
        }

        public final long zzt() {
            return this.zzbc;
        }
    }

    /* renamed from: com.google.android.gms.internal.firebase_auth.zzp$zzp  reason: collision with other inner class name */
    public static final class C0009zzp extends zzhs<C0009zzp, zza> implements zzje {
        /* access modifiers changed from: private */
        public static final C0009zzp zzdo = new C0009zzp();
        private static volatile zzjm<C0009zzp> zzs;
        private String zzaf = "";
        private String zzai = "";
        private long zzaj;
        private long zzav;
        private String zzaw = "";
        private String zzcs = "";
        private boolean zzcw;
        private String zzdh = "";
        private String zzdi = "";
        private String zzdj = "";
        private boolean zzdk;
        private boolean zzdl;
        private boolean zzdm = true;
        private String zzdn = "";
        private int zzo;

        /* renamed from: com.google.android.gms.internal.firebase_auth.zzp$zzp$zza */
        public static final class zza extends zzhs.zza<C0009zzp, zza> implements zzje {
            private zza() {
                super(C0009zzp.zzdo);
            }

            /* synthetic */ zza(zzo zzo) {
                this();
            }

            public final zza zzbd(String str) {
                a();
                ((C0009zzp) this.a).zzaz(str);
                return this;
            }

            public final zza zzbe(String str) {
                a();
                ((C0009zzp) this.a).zzba(str);
                return this;
            }

            public final zza zzbf(String str) {
                a();
                ((C0009zzp) this.a).zzbb(str);
                return this;
            }

            public final zza zzbg(String str) {
                a();
                ((C0009zzp) this.a).zzh(str);
                return this;
            }

            public final zza zzbh(String str) {
                a();
                ((C0009zzp) this.a).zzf(str);
                return this;
            }

            public final zza zzbi(String str) {
                a();
                ((C0009zzp) this.a).zzbc(str);
                return this;
            }

            public final zza zzi(boolean z) {
                a();
                ((C0009zzp) this.a).zze(z);
                return this;
            }

            public final zza zzj(boolean z) {
                a();
                ((C0009zzp) this.a).zzg(z);
                return this;
            }

            public final zza zzk(boolean z) {
                a();
                ((C0009zzp) this.a).zzh(z);
                return this;
            }
        }

        static {
            zzhs.a(C0009zzp.class, zzdo);
        }

        private C0009zzp() {
        }

        public static zza zzat() {
            return (zza) zzdo.c();
        }

        /* access modifiers changed from: private */
        public final void zzaz(String str) {
            if (str != null) {
                this.zzo |= 1;
                this.zzdh = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzba(String str) {
            if (str != null) {
                this.zzo |= 2;
                this.zzdi = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzbb(String str) {
            if (str != null) {
                this.zzo |= 16;
                this.zzaf = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzbc(String str) {
            if (str != null) {
                this.zzo |= 8192;
                this.zzdn = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zze(boolean z) {
            this.zzo |= 256;
            this.zzcw = z;
        }

        /* access modifiers changed from: private */
        public final void zzf(String str) {
            if (str != null) {
                this.zzo |= 2048;
                this.zzai = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzg(boolean z) {
            this.zzo |= 512;
            this.zzdl = z;
        }

        /* access modifiers changed from: private */
        public final void zzh(String str) {
            if (str != null) {
                this.zzo |= 128;
                this.zzaw = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzh(boolean z) {
            this.zzo |= 1024;
            this.zzdm = z;
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzo.a[i - 1]) {
                case 1:
                    return new C0009zzp();
                case 2:
                    return new zza((zzo) null);
                case 3:
                    return a((zzjc) zzdo, "\u0001\u000e\u0000\u0001\u0001\u000f\u000e\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\u0007\u0003\u0005\b\u0004\u0006\b\u0005\u0007\u0002\u0006\b\b\u0007\t\u0007\b\n\u0007\t\u000b\u0007\n\r\b\u000b\u000e\u0003\f\u000f\b\r", new Object[]{"zzo", "zzdh", "zzdi", "zzdj", "zzdk", "zzaf", "zzcs", "zzav", "zzaw", "zzcw", "zzdl", "zzdm", "zzai", "zzaj", "zzdn"});
                case 4:
                    return zzdo;
                case 5:
                    zzjm<C0009zzp> zzjm = zzs;
                    if (zzjm == null) {
                        synchronized (C0009zzp.class) {
                            zzjm = zzs;
                            if (zzjm == null) {
                                zzjm = new zzhs.zzc<>(zzdo);
                                zzs = zzjm;
                            }
                        }
                    }
                    return zzjm;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    public static final class zzq extends zzhs<zzq, zza> implements zzje {
        /* access modifiers changed from: private */
        public static final zzq zzet = new zzq();
        private static volatile zzjm<zzq> zzs;
        private String zzab = "";
        private String zzai = "";
        private String zzau = "";
        private String zzaw = "";
        private String zzaz = "";
        private String zzbb = "";
        private long zzbc;
        private boolean zzbd;
        private String zzcj = "";
        private boolean zzcm;
        private String zzct = "";
        private String zzdn = "";
        private int zzdp;
        private String zzdq = "";
        private String zzdr = "";
        private String zzds = "";
        private String zzdt = "";
        private String zzdu = "";
        private String zzdv = "";
        private String zzdw = "";
        private String zzdx = "";
        private String zzdy = "";
        private String zzdz = "";
        private String zzea = "";
        private boolean zzeb;
        private String zzec = "";
        private zzhz<String> zzed = zzhs.e();
        private boolean zzee;
        private String zzef = "";
        private String zzeg = "";
        private String zzeh = "";
        private String zzei = "";
        private long zzej;
        private String zzek = "";
        private boolean zzel;
        private String zzem = "";
        private String zzen = "";
        private String zzeo = "";
        private String zzep = "";
        private String zzeq = "";
        private String zzer = "";
        private zzhz<zzr> zzes = e();
        private int zzo;
        private String zzx = "";
        private String zzz = "";

        public static final class zza extends zzhs.zza<zzq, zza> implements zzje {
            private zza() {
                super(zzq.zzet);
            }

            /* synthetic */ zza(zzo zzo) {
                this();
            }
        }

        static {
            zzhs.a(zzq.class, zzet);
        }

        private zzq() {
        }

        public static zzjm<zzq> zzm() {
            return (zzjm) zzet.a(zzhs.zzd.zzaat, (Object) null, (Object) null);
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzo.a[i - 1]) {
                case 1:
                    return new zzq();
                case 2:
                    return new zza((zzo) null);
                case 3:
                    return a((zzjc) zzet, "\u0001+\u0000\u0002\u0001-+\u0000\u0002\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\u0007\u0003\u0005\b\u0004\u0006\b\u0005\u0007\b\u0006\b\b\u0007\t\b\b\n\b\t\u000b\b\n\f\b\u000b\r\b\f\u000e\b\r\u000f\b\u000e\u0010\b\u000f\u0011\b\u0010\u0012\u0007\u0011\u0013\b\u0012\u0014\b\u0013\u0015\b\u0014\u0017\b\u0015\u0018\u001a\u0019\u0007\u0016\u001a\b\u0017\u001b\b\u0018\u001c\b\u0019\u001d\b\u001a\u001e\u0002\u001b\u001f\b\u001c \u0007\u001d!\b\u001e\"\b\u001f#\u0002 $\b!%\b\"&\b#'\b$(\u0007%*\b&+\b',\b(-\u001b", new Object[]{"zzo", "zzdp", "zzdq", "zzx", "zzaz", "zzcm", "zzdr", "zzds", "zzdt", "zzdu", "zzdv", "zzdw", "zzct", "zzdx", "zzdy", "zzz", "zzdz", "zzea", "zzau", "zzeb", "zzcj", "zzaw", "zzec", "zzab", "zzed", "zzee", "zzef", "zzeg", "zzeh", "zzei", "zzej", "zzek", "zzel", "zzem", "zzbb", "zzbc", "zzen", "zzeo", "zzep", "zzeq", "zzbd", "zzdn", "zzai", "zzer", "zzes", zzr.class});
                case 4:
                    return zzet;
                case 5:
                    zzjm<zzq> zzjm = zzs;
                    if (zzjm == null) {
                        synchronized (zzq.class) {
                            zzjm = zzs;
                            if (zzjm == null) {
                                zzjm = new zzhs.zzc<>(zzet);
                                zzs = zzjm;
                            }
                        }
                    }
                    return zzjm;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final String getDisplayName() {
            return this.zzcj;
        }

        public final String getEmail() {
            return this.zzaz;
        }

        public final String getErrorMessage() {
            return this.zzeq;
        }

        public final String getIdToken() {
            return this.zzaw;
        }

        public final String getLocalId() {
            return this.zzau;
        }

        public final String getProviderId() {
            return this.zzx;
        }

        public final String getRawUserInfo() {
            return this.zzep;
        }

        public final String zzam() {
            return this.zzct;
        }

        public final boolean zzav() {
            return this.zzee;
        }

        public final String zzaw() {
            return this.zzeh;
        }

        public final boolean zzax() {
            return this.zzel;
        }

        public final String zzay() {
            return this.zzen;
        }

        public final String zzaz() {
            return this.zzdn;
        }

        public final String zzba() {
            return this.zzai;
        }

        public final String zzbb() {
            return this.zzer;
        }

        public final List<zzr> zzbc() {
            return this.zzes;
        }

        public final String zzs() {
            return this.zzbb;
        }

        public final long zzt() {
            return this.zzbc;
        }

        public final boolean zzu() {
            return this.zzbd;
        }
    }

    public static final class zzr extends zzhs<zzr, zza> implements zzje {
        /* access modifiers changed from: private */
        public static final zzr zzev = new zzr();
        private static volatile zzjm<zzr> zzs;
        private String zzai = "";
        private long zzav;
        private String zzcs = "";
        private boolean zzcw;
        private String zzeu = "";
        private int zzo;

        public static final class zza extends zzhs.zza<zzr, zza> implements zzje {
            private zza() {
                super(zzr.zzev);
            }

            /* synthetic */ zza(zzo zzo) {
                this();
            }

            public final zza zzbk(String str) {
                a();
                ((zzr) this.a).zzbj(str);
                return this;
            }

            public final zza zzbl(String str) {
                a();
                ((zzr) this.a).zzf(str);
                return this;
            }

            public final zza zzl(boolean z) {
                a();
                ((zzr) this.a).zze(true);
                return this;
            }
        }

        static {
            zzhs.a(zzr.class, zzev);
        }

        private zzr() {
        }

        public static zza zzbe() {
            return (zza) zzev.c();
        }

        /* access modifiers changed from: private */
        public final void zzbj(String str) {
            if (str != null) {
                this.zzo |= 1;
                this.zzeu = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zze(boolean z) {
            this.zzo |= 4;
            this.zzcw = z;
        }

        /* access modifiers changed from: private */
        public final void zzf(String str) {
            if (str != null) {
                this.zzo |= 16;
                this.zzai = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzo.a[i - 1]) {
                case 1:
                    return new zzr();
                case 2:
                    return new zza((zzo) null);
                case 3:
                    return a((zzjc) zzev, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\u0007\u0002\u0004\u0002\u0003\u0005\b\u0004", new Object[]{"zzo", "zzeu", "zzcs", "zzcw", "zzav", "zzai"});
                case 4:
                    return zzev;
                case 5:
                    zzjm<zzr> zzjm = zzs;
                    if (zzjm == null) {
                        synchronized (zzr.class) {
                            zzjm = zzs;
                            if (zzjm == null) {
                                zzjm = new zzhs.zzc<>(zzev);
                                zzs = zzjm;
                            }
                        }
                    }
                    return zzjm;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    public static final class zzs extends zzhs<zzs, zza> implements zzje {
        /* access modifiers changed from: private */
        public static final zzs zzew = new zzs();
        private static volatile zzjm<zzs> zzs;
        private String zzal = "";
        private byte zzas = 2;
        private String zzaw = "";
        private String zzbb = "";
        private long zzbc;
        private boolean zzbd;
        private int zzo;

        public static final class zza extends zzhs.zza<zzs, zza> implements zzje {
            private zza() {
                super(zzs.zzew);
            }

            /* synthetic */ zza(zzo zzo) {
                this();
            }
        }

        static {
            zzhs.a(zzs.class, zzew);
        }

        private zzs() {
        }

        public static zzjm<zzs> zzm() {
            return (zzjm) zzew.a(zzhs.zzd.zzaat, (Object) null, (Object) null);
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            int i2 = 0;
            switch (zzo.a[i - 1]) {
                case 1:
                    return new zzs();
                case 2:
                    return new zza((zzo) null);
                case 3:
                    return a((zzjc) zzew, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0001\u0001Ԉ\u0000\u0002\b\u0001\u0003\b\u0002\u0004\u0002\u0003\u0005\u0007\u0004", new Object[]{"zzo", "zzal", "zzaw", "zzbb", "zzbc", "zzbd"});
                case 4:
                    return zzew;
                case 5:
                    zzjm<zzs> zzjm = zzs;
                    if (zzjm == null) {
                        synchronized (zzs.class) {
                            zzjm = zzs;
                            if (zzjm == null) {
                                zzjm = new zzhs.zzc<>(zzew);
                                zzs = zzjm;
                            }
                        }
                    }
                    return zzjm;
                case 6:
                    return Byte.valueOf(this.zzas);
                case 7:
                    if (obj != null) {
                        i2 = 1;
                    }
                    this.zzas = (byte) i2;
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final String getIdToken() {
            return this.zzaw;
        }

        public final String zzs() {
            return this.zzbb;
        }

        public final long zzt() {
            return this.zzbc;
        }

        public final boolean zzu() {
            return this.zzbd;
        }
    }

    public static final class zzt extends zzhs<zzt, zza> implements zzje {
        /* access modifiers changed from: private */
        public static final zzt zzey = new zzt();
        private static volatile zzjm<zzt> zzs;
        private String zzai = "";
        private long zzaj;
        private long zzav;
        private String zzaw = "";
        private String zzaz = "";
        private String zzck = "";
        private String zzco = "";
        private String zzcp = "";
        private String zzcs = "";
        private boolean zzcw;
        private String zzdj = "";
        private String zzex = "";
        private int zzo;

        public static final class zza extends zzhs.zza<zzt, zza> implements zzje {
            private zza() {
                super(zzt.zzey);
            }

            /* synthetic */ zza(zzo zzo) {
                this();
            }

            public final zza zzbm(String str) {
                a();
                ((zzt) this.a).zzn(str);
                return this;
            }

            public final zza zzbn(String str) {
                a();
                ((zzt) this.a).setPassword(str);
                return this;
            }

            public final zza zzbo(String str) {
                a();
                ((zzt) this.a).zzf(str);
                return this;
            }

            public final zza zzm(boolean z) {
                a();
                ((zzt) this.a).zze(z);
                return this;
            }
        }

        static {
            zzhs.a(zzt.class, zzey);
        }

        private zzt() {
        }

        /* access modifiers changed from: private */
        public final void setPassword(String str) {
            if (str != null) {
                this.zzo |= 2;
                this.zzck = str;
                return;
            }
            throw new NullPointerException();
        }

        public static zza zzbh() {
            return (zza) zzey.c();
        }

        /* access modifiers changed from: private */
        public final void zze(boolean z) {
            this.zzo |= 512;
            this.zzcw = z;
        }

        /* access modifiers changed from: private */
        public final void zzf(String str) {
            if (str != null) {
                this.zzo |= 1024;
                this.zzai = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzn(String str) {
            if (str != null) {
                this.zzo |= 1;
                this.zzaz = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzo.a[i - 1]) {
                case 1:
                    return new zzt();
                case 2:
                    return new zza((zzo) null);
                case 3:
                    return a((zzjc) zzey, "\u0001\f\u0000\u0001\u0001\f\f\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\b\u0003\u0005\b\u0004\u0006\b\u0005\u0007\b\u0006\b\u0002\u0007\t\b\b\n\u0007\t\u000b\b\n\f\u0003\u000b", new Object[]{"zzo", "zzaz", "zzck", "zzdj", "zzco", "zzcp", "zzex", "zzcs", "zzav", "zzaw", "zzcw", "zzai", "zzaj"});
                case 4:
                    return zzey;
                case 5:
                    zzjm<zzt> zzjm = zzs;
                    if (zzjm == null) {
                        synchronized (zzt.class) {
                            zzjm = zzs;
                            if (zzjm == null) {
                                zzjm = new zzhs.zzc<>(zzey);
                                zzs = zzjm;
                            }
                        }
                    }
                    return zzjm;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    public static final class zzu extends zzhs<zzu, zza> implements zzje {
        /* access modifiers changed from: private */
        public static final zzu zzez = new zzu();
        private static volatile zzjm<zzu> zzs;
        private String zzal = "";
        private boolean zzao;
        private byte zzas = 2;
        private String zzau = "";
        private String zzaw = "";
        private String zzaz = "";
        private String zzbb = "";
        private long zzbc;
        private String zzcj = "";
        private String zzct = "";
        private String zzeh = "";
        private long zzej;
        private String zzek = "";
        private String zzer = "";
        private zzhz<zzr> zzes = e();
        private int zzo;

        public static final class zza extends zzhs.zza<zzu, zza> implements zzje {
            private zza() {
                super(zzu.zzez);
            }

            /* synthetic */ zza(zzo zzo) {
                this();
            }
        }

        static {
            zzhs.a(zzu.class, zzez);
        }

        private zzu() {
        }

        public static zzjm<zzu> zzm() {
            return (zzjm) zzez.a(zzhs.zzd.zzaat, (Object) null, (Object) null);
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            int i2 = 0;
            switch (zzo.a[i - 1]) {
                case 1:
                    return new zzu();
                case 2:
                    return new zza((zzo) null);
                case 3:
                    return a((zzjc) zzez, "\u0001\u000e\u0000\u0001\u0001\u000f\u000e\u0000\u0001\u0001\u0001Ԉ\u0000\u0002\b\u0001\u0003\b\u0002\u0004\b\u0003\u0005\b\u0004\u0006\u0007\u0005\u0007\b\u0006\b\b\u0007\t\u0002\b\n\b\t\u000b\b\n\f\u0002\u000b\u000e\b\f\u000f\u001b", new Object[]{"zzo", "zzal", "zzau", "zzaz", "zzcj", "zzaw", "zzao", "zzct", "zzeh", "zzej", "zzek", "zzbb", "zzbc", "zzer", "zzes", zzr.class});
                case 4:
                    return zzez;
                case 5:
                    zzjm<zzu> zzjm = zzs;
                    if (zzjm == null) {
                        synchronized (zzu.class) {
                            zzjm = zzs;
                            if (zzjm == null) {
                                zzjm = new zzhs.zzc<>(zzez);
                                zzs = zzjm;
                            }
                        }
                    }
                    return zzjm;
                case 6:
                    return Byte.valueOf(this.zzas);
                case 7:
                    if (obj != null) {
                        i2 = 1;
                    }
                    this.zzas = (byte) i2;
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final String getDisplayName() {
            return this.zzcj;
        }

        public final String getEmail() {
            return this.zzaz;
        }

        public final String getIdToken() {
            return this.zzaw;
        }

        public final String getLocalId() {
            return this.zzau;
        }

        public final String zzam() {
            return this.zzct;
        }

        public final String zzbb() {
            return this.zzer;
        }

        public final List<zzr> zzbc() {
            return this.zzes;
        }

        public final String zzs() {
            return this.zzbb;
        }

        public final long zzt() {
            return this.zzbc;
        }
    }
}
