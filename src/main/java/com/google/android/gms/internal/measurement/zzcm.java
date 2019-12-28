package com.google.android.gms.internal.measurement;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;

public abstract class zzcm<T> {
    private static final Object zzaax = new Object();
    private static boolean zzaay = false;
    private static final AtomicInteger zzabb = new AtomicInteger();
    @SuppressLint({"StaticFieldLeak"})
    private static Context zzob = null;
    private final String name;
    private final zzct zzaaz;
    private final T zzaba;
    private volatile int zzabc;
    private volatile T zzjq;

    private zzcm(zzct zzct, String str, T t) {
        this.zzabc = -1;
        if (zzct.zzabh != null) {
            this.zzaaz = zzct;
            this.name = str;
            this.zzaba = t;
            return;
        }
        throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
    }

    /* synthetic */ zzcm(zzct zzct, String str, Object obj, zzcp zzcp) {
        this(zzct, str, obj);
    }

    static void a() {
        zzabb.incrementAndGet();
    }

    /* access modifiers changed from: private */
    public static zzcm<Double> zza(zzct zzct, String str, double d) {
        return new zzcr(zzct, str, Double.valueOf(d));
    }

    /* access modifiers changed from: private */
    public static zzcm<Long> zza(zzct zzct, String str, long j) {
        return new zzcp(zzct, str, Long.valueOf(j));
    }

    /* access modifiers changed from: private */
    public static zzcm<String> zza(zzct zzct, String str, String str2) {
        return new zzcq(zzct, str, str2);
    }

    /* access modifiers changed from: private */
    public static zzcm<Boolean> zza(zzct zzct, String str, boolean z) {
        return new zzco(zzct, str, Boolean.valueOf(z));
    }

    private final String zzdg(String str) {
        if (str != null && str.isEmpty()) {
            return this.name;
        }
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf(this.name);
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    public static void zzr(Context context) {
        synchronized (zzaax) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
            if (zzob != context) {
                synchronized (zzca.class) {
                    zzca.a.clear();
                }
                synchronized (zzcs.class) {
                    zzcs.a.clear();
                }
                synchronized (zzcj.class) {
                    zzcj.a = null;
                }
                zzabb.incrementAndGet();
                zzob = context;
            }
        }
    }

    @Nullable
    private final T zzrn() {
        zzce zzce;
        Object zzdd;
        zzct zzct = this.zzaaz;
        String str = (String) zzcj.a(zzob).zzdd("gms:phenotype:phenotype_flag:debug_bypass_phenotype");
        if (!(str != null && zzbz.zzzw.matcher(str).matches())) {
            if (this.zzaaz.zzabh == null) {
                Context context = zzob;
                zzct zzct2 = this.zzaaz;
                zzce = zzcs.a(context, (String) null);
            } else if (zzck.zza(zzob, this.zzaaz.zzabh)) {
                zzct zzct3 = this.zzaaz;
                zzce = zzca.zza(zzob.getContentResolver(), this.zzaaz.zzabh);
            } else {
                zzce = null;
            }
            if (!(zzce == null || (zzdd = zzce.zzdd(zzrm())) == null)) {
                return a(zzdd);
            }
        } else if (Log.isLoggable("PhenotypeFlag", 3)) {
            String valueOf = String.valueOf(zzrm());
            Log.d("PhenotypeFlag", valueOf.length() != 0 ? "Bypass reading Phenotype values for flag: ".concat(valueOf) : new String("Bypass reading Phenotype values for flag: "));
        }
        return null;
    }

    @Nullable
    private final T zzro() {
        zzct zzct = this.zzaaz;
        Object zzdd = zzcj.a(zzob).zzdd(zzdg(this.zzaaz.zzabi));
        if (zzdd != null) {
            return a(zzdd);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public abstract T a(Object obj);

    public final T get() {
        int i = zzabb.get();
        if (this.zzabc < i) {
            synchronized (this) {
                if (this.zzabc < i) {
                    if (zzob != null) {
                        zzct zzct = this.zzaaz;
                        T zzrn = zzrn();
                        if (zzrn == null) {
                            zzrn = zzro();
                            if (zzrn == null) {
                                zzrn = this.zzaba;
                            }
                        }
                        this.zzjq = zzrn;
                        this.zzabc = i;
                    } else {
                        throw new IllegalStateException("Must call PhenotypeFlag.init() first");
                    }
                }
            }
        }
        return this.zzjq;
    }

    public final String zzrm() {
        return zzdg(this.zzaaz.zzabj);
    }
}
