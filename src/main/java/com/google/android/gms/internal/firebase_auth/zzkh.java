package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzhs;

public final class zzkh extends zzhs<zzkh, zza> implements zzje {
    /* access modifiers changed from: private */
    public static final zzkh zzaej = new zzkh();
    private static volatile zzjm<zzkh> zzs;
    private long zzaeh;
    private int zzaei;

    public static final class zza extends zzhs.zza<zzkh, zza> implements zzje {
        private zza() {
            super(zzkh.zzaej);
        }

        /* synthetic */ zza(zzkj zzkj) {
            this();
        }
    }

    static {
        zzhs.a(zzkh.class, zzaej);
    }

    private zzkh() {
    }

    public static zzkh zzkl() {
        return zzaej;
    }

    /* access modifiers changed from: protected */
    public final Object a(int i, Object obj, Object obj2) {
        switch (zzkj.a[i - 1]) {
            case 1:
                return new zzkh();
            case 2:
                return new zza((zzkj) null);
            case 3:
                return a((zzjc) zzaej, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0002\u0002\u0004", new Object[]{"zzaeh", "zzaei"});
            case 4:
                return zzaej;
            case 5:
                zzjm<zzkh> zzjm = zzs;
                if (zzjm == null) {
                    synchronized (zzkh.class) {
                        zzjm = zzs;
                        if (zzjm == null) {
                            zzjm = new zzhs.zzc<>(zzaej);
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

    public final long getSeconds() {
        return this.zzaeh;
    }
}
