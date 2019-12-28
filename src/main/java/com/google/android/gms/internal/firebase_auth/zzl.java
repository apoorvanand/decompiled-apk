package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzhs;

public final class zzl extends zzhs<zzl, zza> implements zzje {
    /* access modifiers changed from: private */
    public static final zzl zzr = new zzl();
    private static volatile zzjm<zzl> zzs;
    private int zzo;
    private String zzp = "";
    private String zzq = "";

    public static final class zza extends zzhs.zza<zzl, zza> implements zzje {
        private zza() {
            super(zzl.zzr);
        }

        /* synthetic */ zza(zzn zzn) {
            this();
        }
    }

    static {
        zzhs.a(zzl.class, zzr);
    }

    private zzl() {
    }

    /* access modifiers changed from: protected */
    public final Object a(int i, Object obj, Object obj2) {
        switch (zzn.a[i - 1]) {
            case 1:
                return new zzl();
            case 2:
                return new zza((zzn) null);
            case 3:
                return a((zzjc) zzr, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001", new Object[]{"zzo", "zzp", "zzq"});
            case 4:
                return zzr;
            case 5:
                zzjm<zzl> zzjm = zzs;
                if (zzjm == null) {
                    synchronized (zzl.class) {
                        zzjm = zzs;
                        if (zzjm == null) {
                            zzjm = new zzhs.zzc<>(zzr);
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
