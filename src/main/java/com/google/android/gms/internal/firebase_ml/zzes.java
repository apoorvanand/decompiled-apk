package com.google.android.gms.internal.firebase_ml;

import java.util.logging.Level;
import java.util.logging.Logger;

public class zzes {
    private static final Logger logger = Logger.getLogger(zzes.class.getName());
    private final zzfp zzsp;
    private final zzex zzsq;
    private final String zzsr;
    private final String zzss;
    private final String zzst;
    private final String zzsu;
    private final zzia zzsv;
    private final boolean zzsw;
    private final boolean zzsx;

    public static abstract class zza {
        final zzfv a;
        zzex b;
        zzfq c;
        final zzia d;
        String e;
        String f;
        String g;
        String h;

        protected zza(zzfv zzfv, String str, String str2, zzia zzia, zzfq zzfq) {
            this.a = (zzfv) zzky.checkNotNull(zzfv);
            this.d = zzia;
            zzh(str);
            zzi(str2);
            this.c = zzfq;
        }

        public zza zza(zzex zzex) {
            this.b = zzex;
            return this;
        }

        public zza zzh(String str) {
            this.e = zzes.a(str);
            return this;
        }

        public zza zzi(String str) {
            this.f = zzes.b(str);
            return this;
        }

        public zza zzj(String str) {
            this.g = str;
            return this;
        }

        public zza zzk(String str) {
            this.h = str;
            return this;
        }
    }

    protected zzes(zza zza2) {
        zzfq zzfq;
        zzfv zzfv;
        this.zzsq = zza2.b;
        this.zzsr = a(zza2.e);
        this.zzss = b(zza2.f);
        this.zzst = zza2.g;
        if (zzlg.zzay(zza2.h)) {
            logger.logp(Level.WARNING, "com.google.api.client.googleapis.services.AbstractGoogleClient", "<init>", "Application name is not set. Call Builder#setApplicationName.");
        }
        this.zzsu = zza2.h;
        if (zza2.c == null) {
            zzfv = zza2.a;
            zzfq = null;
        } else {
            zzfv = zza2.a;
            zzfq = zza2.c;
        }
        this.zzsp = zzfv.zza(zzfq);
        this.zzsv = zza2.d;
        this.zzsw = false;
        this.zzsx = false;
    }

    static String a(String str) {
        zzky.checkNotNull(str, "root URL cannot be null.");
        return !str.endsWith("/") ? String.valueOf(str).concat("/") : str;
    }

    static String b(String str) {
        zzky.checkNotNull(str, "service path cannot be null");
        if (str.length() == 1) {
            zzky.checkArgument("/".equals(str), "service path must equal \"/\" if it is of length 1.");
            return "";
        } else if (str.length() <= 0) {
            return str;
        } else {
            if (!str.endsWith("/")) {
                str = String.valueOf(str).concat("/");
            }
            return str.startsWith("/") ? str.substring(1) : str;
        }
    }

    /* access modifiers changed from: protected */
    public void a(zzet<?> zzet) {
        zzex zzex = this.zzsq;
        if (zzex != null) {
            zzex.zza(zzet);
        }
    }

    public final String zzdt() {
        String valueOf = String.valueOf(this.zzsr);
        String valueOf2 = String.valueOf(this.zzss);
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    public final String zzdu() {
        return this.zzsu;
    }

    public final zzfp zzdv() {
        return this.zzsp;
    }

    public zzia zzdw() {
        return this.zzsv;
    }
}
