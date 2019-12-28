package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzhs;
import java.util.List;

public final class zzz extends zzhs<zzz, zza> implements zzje {
    /* access modifiers changed from: private */
    public static final zzz zzga = new zzz();
    private static volatile zzjm<zzz> zzs;
    private String zzai = "";
    private String zzau = "";
    private String zzaz = "";
    private String zzce = "";
    private String zzcj = "";
    private zzhz<String> zzcl = zzhs.e();
    private boolean zzcm;
    private String zzct = "";
    private long zzcy;
    private long zzcz;
    private zzhz<zzu> zzdb = e();
    private boolean zzde;
    private String zzdv = "";
    private String zzdw = "";
    private String zzdx = "";
    private String zzeo = "";
    private zzhz<zzr> zzes = e();
    private zzgf zzft = zzgf.zzvv;
    private zzgf zzfu = zzgf.zzvv;
    private int zzfv;
    private long zzfw;
    private long zzfx;
    private boolean zzfy;
    private String zzfz = "";
    private int zzo;

    public static final class zza extends zzhs.zza<zzz, zza> implements zzje {
        private zza() {
            super(zzz.zzga);
        }

        /* synthetic */ zza(zzy zzy) {
            this();
        }
    }

    static {
        zzhs.a(zzz.class, zzga);
    }

    private zzz() {
    }

    /* access modifiers changed from: protected */
    public final Object a(int i, Object obj, Object obj2) {
        switch (zzy.a[i - 1]) {
            case 1:
                return new zzz();
            case 2:
                return new zza((zzy) null);
            case 3:
                return a((zzjc) zzga, "\u0001\u0018\u0000\u0001\u0001\u001a\u0018\u0000\u0003\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\u001a\u0005\b\u0003\u0006\b\u0004\u0007\b\u0005\b\b\u0006\t\n\u0007\n\n\b\u000b\u0004\t\f\u0007\n\r\u0002\u000b\u000e\u001b\u000f\u0002\f\u0010\u0007\r\u0011\u0002\u000e\u0012\u0002\u000f\u0013\b\u0010\u0014\u0007\u0011\u0015\b\u0012\u0016\b\u0013\u0019\b\u0014\u001a\u001b", new Object[]{"zzo", "zzau", "zzaz", "zzcj", "zzcl", "zzdv", "zzct", "zzdw", "zzdx", "zzft", "zzfu", "zzfv", "zzcm", "zzfw", "zzdb", zzu.class, "zzfx", "zzde", "zzcy", "zzcz", "zzeo", "zzfy", "zzfz", "zzce", "zzai", "zzes", zzr.class});
            case 4:
                return zzga;
            case 5:
                zzjm<zzz> zzjm = zzs;
                if (zzjm == null) {
                    synchronized (zzz.class) {
                        zzjm = zzs;
                        if (zzjm == null) {
                            zzjm = new zzhs.zzc<>(zzga);
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

    public final String getLocalId() {
        return this.zzau;
    }

    public final String getPhoneNumber() {
        return this.zzce;
    }

    public final List<zzu> zzal() {
        return this.zzdb;
    }

    public final String zzam() {
        return this.zzct;
    }

    public final boolean zzao() {
        return this.zzcm;
    }

    public final List<zzr> zzbc() {
        return this.zzes;
    }

    public final long zzbs() {
        return this.zzcy;
    }

    public final long zzbt() {
        return this.zzcz;
    }

    public final String zzbu() {
        return this.zzfz;
    }
}
