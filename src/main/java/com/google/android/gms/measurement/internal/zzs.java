package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Size;
import androidx.annotation.WorkerThread;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import java.lang.reflect.InvocationTargetException;

public final class zzs extends zzgf {
    private Boolean zzeb;
    @NonNull
    private zzu zzec = zzv.a;
    private Boolean zzed;

    zzs(zzfj zzfj) {
        super(zzfj);
        zzak.a(zzfj);
    }

    static String a() {
        return zzak.zzgf.get(null);
    }

    @WorkerThread
    static boolean c() {
        return zzak.zzhy.get(null).booleanValue();
    }

    @Nullable
    @VisibleForTesting
    private final Bundle zzbo() {
        try {
            if (getContext().getPackageManager() == null) {
                zzab().zzgk().zzao("Failed to load metadata: PackageManager is null");
                return null;
            }
            ApplicationInfo applicationInfo = Wrappers.packageManager(getContext()).getApplicationInfo(getContext().getPackageName(), 128);
            if (applicationInfo != null) {
                return applicationInfo.metaData;
            }
            zzab().zzgk().zzao("Failed to load metadata: ApplicationInfo is null");
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            zzab().zzgk().zza("Failed to load metadata: Package name not found", e);
            return null;
        }
    }

    public static long zzbs() {
        return zzak.zzhi.get(null).longValue();
    }

    public static long zzbt() {
        return zzak.zzgi.get(null).longValue();
    }

    public static boolean zzbv() {
        return zzak.zzge.get(null).booleanValue();
    }

    /* access modifiers changed from: package-private */
    @Nullable
    @VisibleForTesting
    public final Boolean a(@Size(min = 1) String str) {
        Preconditions.checkNotEmpty(str);
        Bundle zzbo = zzbo();
        if (zzbo == null) {
            zzab().zzgk().zzao("Failed to load metadata: Metadata bundle is null");
            return null;
        } else if (!zzbo.containsKey(str)) {
            return null;
        } else {
            return Boolean.valueOf(zzbo.getBoolean(str));
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(@NonNull zzu zzu) {
        this.zzec = zzu;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x002b A[SYNTHETIC, Splitter:B:9:0x002b] */
    @androidx.annotation.Nullable
    @com.google.android.gms.common.util.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<java.lang.String> b(@androidx.annotation.Size(min = 1) java.lang.String r4) {
        /*
            r3 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r4)
            android.os.Bundle r0 = r3.zzbo()
            r1 = 0
            if (r0 != 0) goto L_0x0019
            com.google.android.gms.measurement.internal.zzef r4 = r3.zzab()
            com.google.android.gms.measurement.internal.zzeh r4 = r4.zzgk()
            java.lang.String r0 = "Failed to load metadata: Metadata bundle is null"
            r4.zzao(r0)
        L_0x0017:
            r4 = r1
            goto L_0x0028
        L_0x0019:
            boolean r2 = r0.containsKey(r4)
            if (r2 != 0) goto L_0x0020
            goto L_0x0017
        L_0x0020:
            int r4 = r0.getInt(r4)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
        L_0x0028:
            if (r4 != 0) goto L_0x002b
            return r1
        L_0x002b:
            android.content.Context r0 = r3.getContext()     // Catch:{ NotFoundException -> 0x0043 }
            android.content.res.Resources r0 = r0.getResources()     // Catch:{ NotFoundException -> 0x0043 }
            int r4 = r4.intValue()     // Catch:{ NotFoundException -> 0x0043 }
            java.lang.String[] r4 = r0.getStringArray(r4)     // Catch:{ NotFoundException -> 0x0043 }
            if (r4 != 0) goto L_0x003e
            return r1
        L_0x003e:
            java.util.List r4 = java.util.Arrays.asList(r4)     // Catch:{ NotFoundException -> 0x0043 }
            return r4
        L_0x0043:
            r4 = move-exception
            com.google.android.gms.measurement.internal.zzef r0 = r3.zzab()
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzgk()
            java.lang.String r2 = "Failed to load string array from metadata: resource not found"
            r0.zza(r2, r4)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzs.b(java.lang.String):java.util.List");
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean b() {
        if (this.zzeb == null) {
            this.zzeb = a("app_measurement_lite");
            if (this.zzeb == null) {
                this.zzeb = false;
            }
        }
        return this.zzeb.booleanValue() || !this.b.zzia();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean c(String str) {
        return zzd(str, zzak.zzhs);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean d(String str) {
        return zzd(str, zzak.zzhm);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final String e(String str) {
        zzdu<String> zzdu = zzak.zzhn;
        return zzdu.get(str == null ? null : this.zzec.zzb(str, zzdu.getKey()));
    }

    /* access modifiers changed from: package-private */
    public final boolean f(String str) {
        return zzd(str, zzak.zzht);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean g(String str) {
        return zzd(str, zzak.zzhu);
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean h(String str) {
        return zzd(str, zzak.zzhv);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean i(String str) {
        return zzd(str, zzak.zzhx);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean j(String str) {
        return zzd(str, zzak.zzhw);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean k(String str) {
        return zzd(str, zzak.zzhz);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean l(String str) {
        return zzd(str, zzak.zzia);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean m(String str) {
        return zzd(str, zzak.zzib);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean n(String str) {
        return zzd(str, zzak.zzic);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean o(String str) {
        return zzd(str, zzak.zzih);
    }

    @WorkerThread
    public final long zza(String str, @NonNull zzdu<Long> zzdu) {
        if (str != null) {
            String zzb = this.zzec.zzb(str, zzdu.getKey());
            if (!TextUtils.isEmpty(zzb)) {
                try {
                    return zzdu.get(Long.valueOf(Long.parseLong(zzb))).longValue();
                } catch (NumberFormatException unused) {
                }
            }
        }
        return zzdu.get(null).longValue();
    }

    public final boolean zza(zzdu<Boolean> zzdu) {
        return zzd((String) null, zzdu);
    }

    public final /* bridge */ /* synthetic */ zzfc zzaa() {
        return super.zzaa();
    }

    public final /* bridge */ /* synthetic */ zzef zzab() {
        return super.zzab();
    }

    public final /* bridge */ /* synthetic */ zzeo zzac() {
        return super.zzac();
    }

    public final /* bridge */ /* synthetic */ zzs zzad() {
        return super.zzad();
    }

    public final /* bridge */ /* synthetic */ zzr zzae() {
        return super.zzae();
    }

    public final long zzao() {
        zzae();
        return 16250;
    }

    @WorkerThread
    public final int zzb(String str, @NonNull zzdu<Integer> zzdu) {
        if (str != null) {
            String zzb = this.zzec.zzb(str, zzdu.getKey());
            if (!TextUtils.isEmpty(zzb)) {
                try {
                    return zzdu.get(Integer.valueOf(Integer.parseInt(zzb))).intValue();
                } catch (NumberFormatException unused) {
                }
            }
        }
        return zzdu.get(null).intValue();
    }

    public final boolean zzbn() {
        if (this.zzed == null) {
            synchronized (this) {
                if (this.zzed == null) {
                    ApplicationInfo applicationInfo = getContext().getApplicationInfo();
                    String myProcessName = ProcessUtils.getMyProcessName();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        this.zzed = Boolean.valueOf(str != null && str.equals(myProcessName));
                    }
                    if (this.zzed == null) {
                        this.zzed = Boolean.TRUE;
                        zzab().zzgk().zzao("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.zzed.booleanValue();
    }

    public final boolean zzbp() {
        zzae();
        Boolean a = a("firebase_analytics_collection_deactivated");
        return a != null && a.booleanValue();
    }

    public final Boolean zzbq() {
        zzae();
        return a("firebase_analytics_collection_enabled");
    }

    public final Boolean zzbr() {
        zzm();
        Boolean a = a("google_analytics_adid_collection_enabled");
        return Boolean.valueOf(a == null || a.booleanValue());
    }

    public final String zzbu() {
        String str;
        zzeh zzeh;
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke((Object) null, new Object[]{"debug.firebase.analytics.app", ""});
        } catch (ClassNotFoundException e) {
            e = e;
            zzeh = zzab().zzgk();
            str = "Could not find SystemProperties class";
            zzeh.zza(str, e);
            return "";
        } catch (NoSuchMethodException e2) {
            e = e2;
            zzeh = zzab().zzgk();
            str = "Could not find SystemProperties.get() method";
            zzeh.zza(str, e);
            return "";
        } catch (IllegalAccessException e3) {
            e = e3;
            zzeh = zzab().zzgk();
            str = "Could not access SystemProperties.get()";
            zzeh.zza(str, e);
            return "";
        } catch (InvocationTargetException e4) {
            e = e4;
            zzeh = zzab().zzgk();
            str = "SystemProperties.get() threw an exception";
            zzeh.zza(str, e);
            return "";
        }
    }

    @WorkerThread
    public final double zzc(String str, @NonNull zzdu<Double> zzdu) {
        if (str != null) {
            String zzb = this.zzec.zzb(str, zzdu.getKey());
            if (!TextUtils.isEmpty(zzb)) {
                try {
                    return zzdu.get(Double.valueOf(Double.parseDouble(zzb))).doubleValue();
                } catch (NumberFormatException unused) {
                }
            }
        }
        return zzdu.get(null).doubleValue();
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean>, com.google.android.gms.measurement.internal.zzdu] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzd(java.lang.String r4, @androidx.annotation.NonNull com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r5) {
        /*
            r3 = this;
            r0 = 0
            if (r4 != 0) goto L_0x000e
        L_0x0003:
            java.lang.Object r4 = r5.get(r0)
        L_0x0007:
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            return r4
        L_0x000e:
            com.google.android.gms.measurement.internal.zzu r1 = r3.zzec
            java.lang.String r2 = r5.getKey()
            java.lang.String r4 = r1.zzb(r4, r2)
            boolean r1 = android.text.TextUtils.isEmpty(r4)
            if (r1 == 0) goto L_0x001f
            goto L_0x0003
        L_0x001f:
            boolean r4 = java.lang.Boolean.parseBoolean(r4)
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            java.lang.Object r4 = r5.get(r4)
            goto L_0x0007
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzs.zzd(java.lang.String, com.google.android.gms.measurement.internal.zzdu):boolean");
    }

    public final boolean zze(String str, zzdu<Boolean> zzdu) {
        return zzd(str, zzdu);
    }

    @WorkerThread
    public final int zzi(@Size(min = 1) String str) {
        return zzb(str, zzak.zzgt);
    }

    public final /* bridge */ /* synthetic */ void zzl() {
        super.zzl();
    }

    public final boolean zzl(String str) {
        return AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(this.zzec.zzb(str, "gaia_collection_enabled"));
    }

    public final /* bridge */ /* synthetic */ void zzm() {
        super.zzm();
    }

    public final boolean zzm(String str) {
        return AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(this.zzec.zzb(str, "measurement.event_sampling_enabled"));
    }

    public final /* bridge */ /* synthetic */ void zzn() {
        super.zzn();
    }

    public final /* bridge */ /* synthetic */ void zzo() {
        super.zzo();
    }

    public final /* bridge */ /* synthetic */ zzac zzw() {
        return super.zzw();
    }

    public final /* bridge */ /* synthetic */ Clock zzx() {
        return super.zzx();
    }

    public final /* bridge */ /* synthetic */ zzed zzy() {
        return super.zzy();
    }

    public final /* bridge */ /* synthetic */ zzjs zzz() {
        return super.zzz();
    }
}
