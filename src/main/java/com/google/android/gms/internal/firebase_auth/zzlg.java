package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzhs;

public final class zzlg {

    public static final class zza extends zzhs<zza, C0007zza> implements zzje {
        /* access modifiers changed from: private */
        public static final zza zzagx = new zza();
        private static volatile zzjm<zza> zzs;
        private String zzagt = "";
        private String zzagu = "";
        private String zzagv = "";
        private String zzagw = "";
        private String zzbb = "";

        /* renamed from: com.google.android.gms.internal.firebase_auth.zzlg$zza$zza  reason: collision with other inner class name */
        public static final class C0007zza extends zzhs.zza<zza, C0007zza> implements zzje {
            private C0007zza() {
                super(zza.zzagx);
            }

            /* synthetic */ C0007zza(zzli zzli) {
                this();
            }

            public final C0007zza zzdn(String str) {
                a();
                ((zza) this.a).zzdm(str);
                return this;
            }

            public final C0007zza zzdo(String str) {
                a();
                ((zza) this.a).zzcm(str);
                return this;
            }
        }

        static {
            zzhs.a(zza.class, zzagx);
        }

        private zza() {
        }

        /* access modifiers changed from: private */
        public final void zzcm(String str) {
            if (str != null) {
                this.zzbb = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public final void zzdm(String str) {
            if (str != null) {
                this.zzagt = str;
                return;
            }
            throw new NullPointerException();
        }

        public static C0007zza zzkz() {
            return (C0007zza) zzagx.c();
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzli.a[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0007zza((zzli) null);
                case 3:
                    return a((zzjc) zzagx, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ\u0004Ȉ\u0005Ȉ", new Object[]{"zzagt", "zzagu", "zzbb", "zzagv", "zzagw"});
                case 4:
                    return zzagx;
                case 5:
                    zzjm<zza> zzjm = zzs;
                    if (zzjm == null) {
                        synchronized (zza.class) {
                            zzjm = zzs;
                            if (zzjm == null) {
                                zzjm = new zzhs.zzc<>(zzagx);
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
        public static final zzb zzahc = new zzb();
        private static volatile zzjm<zzb> zzs;
        private String zzagy = "";
        private String zzagz = "";
        private String zzaha = "";
        private long zzahb;
        private String zzaw = "";
        private String zzbb = "";
        private long zzbc;

        public static final class zza extends zzhs.zza<zzb, zza> implements zzje {
            private zza() {
                super(zzb.zzahc);
            }

            /* synthetic */ zza(zzli zzli) {
                this();
            }
        }

        static {
            zzhs.a(zzb.class, zzahc);
        }

        private zzb() {
        }

        public static zzjm<zzb> zzm() {
            return (zzjm) zzahc.a(zzhs.zzd.zzaat, (Object) null, (Object) null);
        }

        /* access modifiers changed from: protected */
        public final Object a(int i, Object obj, Object obj2) {
            switch (zzli.a[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza((zzli) null);
                case 3:
                    return a((zzjc) zzahc, "\u0000\u0007\u0000\u0000\u0001\u0007\u0007\u0000\u0000\u0000\u0001Ȉ\u0002\u0002\u0003Ȉ\u0004Ȉ\u0005Ȉ\u0006Ȉ\u0007\u0002", new Object[]{"zzagy", "zzbc", "zzagz", "zzbb", "zzaw", "zzaha", "zzahb"});
                case 4:
                    return zzahc;
                case 5:
                    zzjm<zzb> zzjm = zzs;
                    if (zzjm == null) {
                        synchronized (zzb.class) {
                            zzjm = zzs;
                            if (zzjm == null) {
                                zzjm = new zzhs.zzc<>(zzahc);
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

        public final String getAccessToken() {
            return this.zzagy;
        }

        public final String zzeu() {
            return this.zzagz;
        }

        public final String zzs() {
            return this.zzbb;
        }

        public final long zzt() {
            return this.zzbc;
        }
    }
}
