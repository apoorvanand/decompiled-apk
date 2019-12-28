package com.google.android.gms.measurement.internal;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.List;

public final class zzdy extends zzg {
    private String zzce;
    private String zzcg;
    private String zzcm;
    private String zzco;
    private long zzcr;
    private String zzcu;
    private List<String> zzcw;
    private int zzds;
    private int zzjr;
    private String zzjs;
    private long zzjt;
    private long zzs;

    zzdy(zzfj zzfj, long j) {
        super(zzfj);
        this.zzs = j;
    }

    @WorkerThread
    @VisibleForTesting
    private final String zzge() {
        zzeh zzgo;
        String str;
        try {
            Class<?> loadClass = getContext().getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
            if (loadClass == null) {
                return null;
            }
            try {
                Object invoke = loadClass.getDeclaredMethod("getInstance", new Class[]{Context.class}).invoke((Object) null, new Object[]{getContext()});
                if (invoke == null) {
                    return null;
                }
                try {
                    return (String) loadClass.getDeclaredMethod("getFirebaseInstanceId", new Class[0]).invoke(invoke, new Object[0]);
                } catch (Exception unused) {
                    zzgo = zzab().zzgp();
                    str = "Failed to retrieve Firebase Instance Id";
                    zzgo.zzao(str);
                    return null;
                }
            } catch (Exception unused2) {
                zzgo = zzab().zzgo();
                str = "Failed to obtain Firebase Analytics instance";
                zzgo.zzao(str);
                return null;
            }
        } catch (ClassNotFoundException unused3) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final zzn a(String str) {
        Boolean a;
        zzo();
        zzm();
        String c = c();
        String d = d();
        j();
        String str2 = this.zzcm;
        long f = (long) f();
        j();
        String str3 = this.zzco;
        long zzao = zzad().zzao();
        j();
        zzo();
        if (this.zzjt == 0) {
            this.zzjt = this.b.zzz().a(getContext(), getContext().getPackageName());
        }
        long j = this.zzjt;
        boolean isEnabled = this.b.isEnabled();
        boolean z = !zzac().zzmc;
        zzo();
        zzm();
        String zzge = !this.b.isEnabled() ? null : zzge();
        j();
        boolean z2 = isEnabled;
        long j2 = this.zzcr;
        long c2 = this.b.c();
        int g = g();
        boolean booleanValue = zzad().zzbr().booleanValue();
        zzs zzad = zzad();
        zzad.zzm();
        Boolean a2 = zzad.a("google_analytics_ssaid_collection_enabled");
        return new zzn(c, d, str2, f, str3, zzao, j, str, z2, z, zzge, j2, c2, g, booleanValue, Boolean.valueOf(a2 == null || a2.booleanValue()).booleanValue(), zzac().i(), e(), (!zzad().zze(c(), zzak.zzij) || (a = zzad().a("google_analytics_default_allow_ad_personalization_signals")) == null) ? null : Boolean.valueOf(!a.booleanValue()), this.zzs, zzad().zze(c(), zzak.zzix) ? this.zzcw : null);
    }

    /* access modifiers changed from: protected */
    public final boolean a() {
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0195 A[Catch:{ IllegalStateException -> 0x01c6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0198 A[Catch:{ IllegalStateException -> 0x01c6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x01a1 A[Catch:{ IllegalStateException -> 0x01c6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x01b4 A[Catch:{ IllegalStateException -> 0x01c6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x01e9  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0234  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x0244  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b() {
        /*
            r11 = this;
            java.lang.String r0 = "unknown"
            java.lang.String r1 = "Unknown"
            java.lang.String r2 = "Unknown"
            android.content.Context r3 = r11.getContext()
            java.lang.String r3 = r3.getPackageName()
            android.content.Context r4 = r11.getContext()
            android.content.pm.PackageManager r4 = r4.getPackageManager()
            r5 = 0
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r4 != 0) goto L_0x002d
            com.google.android.gms.measurement.internal.zzef r7 = r11.zzab()
            com.google.android.gms.measurement.internal.zzeh r7 = r7.zzgk()
            java.lang.String r8 = "PackageManager is null, app identity information might be inaccurate. appId"
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r3)
            r7.zza(r8, r9)
            goto L_0x0086
        L_0x002d:
            java.lang.String r0 = r4.getInstallerPackageName(r3)     // Catch:{ IllegalArgumentException -> 0x0032 }
            goto L_0x0043
        L_0x0032:
            com.google.android.gms.measurement.internal.zzef r7 = r11.zzab()
            com.google.android.gms.measurement.internal.zzeh r7 = r7.zzgk()
            java.lang.String r8 = "Error retrieving app installer package name. appId"
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r3)
            r7.zza(r8, r9)
        L_0x0043:
            if (r0 != 0) goto L_0x0048
            java.lang.String r0 = "manual_install"
            goto L_0x0052
        L_0x0048:
            java.lang.String r7 = "com.android.vending"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x0052
            java.lang.String r0 = ""
        L_0x0052:
            android.content.Context r7 = r11.getContext()     // Catch:{ NameNotFoundException -> 0x0075 }
            java.lang.String r7 = r7.getPackageName()     // Catch:{ NameNotFoundException -> 0x0075 }
            android.content.pm.PackageInfo r7 = r4.getPackageInfo(r7, r5)     // Catch:{ NameNotFoundException -> 0x0075 }
            if (r7 == 0) goto L_0x0086
            android.content.pm.ApplicationInfo r8 = r7.applicationInfo     // Catch:{ NameNotFoundException -> 0x0075 }
            java.lang.CharSequence r8 = r4.getApplicationLabel(r8)     // Catch:{ NameNotFoundException -> 0x0075 }
            boolean r9 = android.text.TextUtils.isEmpty(r8)     // Catch:{ NameNotFoundException -> 0x0075 }
            if (r9 != 0) goto L_0x0070
            java.lang.String r2 = r8.toString()     // Catch:{ NameNotFoundException -> 0x0075 }
        L_0x0070:
            java.lang.String r1 = r7.versionName     // Catch:{ NameNotFoundException -> 0x0075 }
            int r6 = r7.versionCode     // Catch:{ NameNotFoundException -> 0x0075 }
            goto L_0x0086
        L_0x0075:
            com.google.android.gms.measurement.internal.zzef r7 = r11.zzab()
            com.google.android.gms.measurement.internal.zzeh r7 = r7.zzgk()
            java.lang.String r8 = "Error retrieving package info. appId, appName"
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r3)
            r7.zza(r8, r9, r2)
        L_0x0086:
            r11.zzce = r3
            r11.zzco = r0
            r11.zzcm = r1
            r11.zzjr = r6
            r11.zzjs = r2
            r0 = 0
            r11.zzjt = r0
            r11.zzae()
            android.content.Context r2 = r11.getContext()
            com.google.android.gms.common.api.Status r2 = com.google.android.gms.common.api.internal.GoogleServices.initialize(r2)
            r6 = 1
            if (r2 == 0) goto L_0x00aa
            boolean r7 = r2.isSuccess()
            if (r7 == 0) goto L_0x00aa
            r7 = 1
            goto L_0x00ab
        L_0x00aa:
            r7 = 0
        L_0x00ab:
            com.google.android.gms.measurement.internal.zzfj r8 = r11.b
            java.lang.String r8 = r8.zzhx()
            boolean r8 = android.text.TextUtils.isEmpty(r8)
            if (r8 != 0) goto L_0x00c7
            java.lang.String r8 = "am"
            com.google.android.gms.measurement.internal.zzfj r9 = r11.b
            java.lang.String r9 = r9.zzhy()
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x00c7
            r8 = 1
            goto L_0x00c8
        L_0x00c7:
            r8 = 0
        L_0x00c8:
            r7 = r7 | r8
            if (r7 != 0) goto L_0x00f4
            if (r2 != 0) goto L_0x00db
            com.google.android.gms.measurement.internal.zzef r2 = r11.zzab()
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzgk()
            java.lang.String r8 = "GoogleService failed to initialize (no status)"
            r2.zzao(r8)
            goto L_0x00f4
        L_0x00db:
            com.google.android.gms.measurement.internal.zzef r8 = r11.zzab()
            com.google.android.gms.measurement.internal.zzeh r8 = r8.zzgk()
            java.lang.String r9 = "GoogleService failed to initialize, status"
            int r10 = r2.getStatusCode()
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            java.lang.String r2 = r2.getStatusMessage()
            r8.zza(r9, r10, r2)
        L_0x00f4:
            if (r7 == 0) goto L_0x015b
            com.google.android.gms.measurement.internal.zzs r2 = r11.zzad()
            java.lang.Boolean r2 = r2.zzbq()
            com.google.android.gms.measurement.internal.zzs r7 = r11.zzad()
            boolean r7 = r7.zzbp()
            if (r7 == 0) goto L_0x011e
            com.google.android.gms.measurement.internal.zzfj r2 = r11.b
            boolean r2 = r2.zzhw()
            if (r2 == 0) goto L_0x015b
            com.google.android.gms.measurement.internal.zzef r2 = r11.zzab()
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzgq()
            java.lang.String r7 = "Collection disabled with firebase_analytics_collection_deactivated=1"
        L_0x011a:
            r2.zzao(r7)
            goto L_0x015b
        L_0x011e:
            if (r2 == 0) goto L_0x0139
            boolean r7 = r2.booleanValue()
            if (r7 != 0) goto L_0x0139
            com.google.android.gms.measurement.internal.zzfj r2 = r11.b
            boolean r2 = r2.zzhw()
            if (r2 == 0) goto L_0x015b
            com.google.android.gms.measurement.internal.zzef r2 = r11.zzab()
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzgq()
            java.lang.String r7 = "Collection disabled with firebase_analytics_collection_enabled=0"
            goto L_0x011a
        L_0x0139:
            if (r2 != 0) goto L_0x014c
            boolean r2 = com.google.android.gms.common.api.internal.GoogleServices.isMeasurementExplicitlyDisabled()
            if (r2 == 0) goto L_0x014c
            com.google.android.gms.measurement.internal.zzef r2 = r11.zzab()
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzgq()
            java.lang.String r7 = "Collection disabled with google_app_measurement_enable=0"
            goto L_0x011a
        L_0x014c:
            com.google.android.gms.measurement.internal.zzef r2 = r11.zzab()
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzgs()
            java.lang.String r7 = "Collection enabled"
            r2.zzao(r7)
            r2 = 1
            goto L_0x015c
        L_0x015b:
            r2 = 0
        L_0x015c:
            java.lang.String r7 = ""
            r11.zzcg = r7
            java.lang.String r7 = ""
            r11.zzcu = r7
            r11.zzcr = r0
            r11.zzae()
            com.google.android.gms.measurement.internal.zzfj r0 = r11.b
            java.lang.String r0 = r0.zzhx()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x018b
            java.lang.String r0 = "am"
            com.google.android.gms.measurement.internal.zzfj r1 = r11.b
            java.lang.String r1 = r1.zzhy()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x018b
            com.google.android.gms.measurement.internal.zzfj r0 = r11.b
            java.lang.String r0 = r0.zzhx()
            r11.zzcu = r0
        L_0x018b:
            java.lang.String r0 = com.google.android.gms.common.api.internal.GoogleServices.getGoogleAppId()     // Catch:{ IllegalStateException -> 0x01c6 }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ IllegalStateException -> 0x01c6 }
            if (r1 == 0) goto L_0x0198
            java.lang.String r1 = ""
            goto L_0x0199
        L_0x0198:
            r1 = r0
        L_0x0199:
            r11.zzcg = r1     // Catch:{ IllegalStateException -> 0x01c6 }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ IllegalStateException -> 0x01c6 }
            if (r0 != 0) goto L_0x01b2
            com.google.android.gms.common.internal.StringResourceValueReader r0 = new com.google.android.gms.common.internal.StringResourceValueReader     // Catch:{ IllegalStateException -> 0x01c6 }
            android.content.Context r1 = r11.getContext()     // Catch:{ IllegalStateException -> 0x01c6 }
            r0.<init>(r1)     // Catch:{ IllegalStateException -> 0x01c6 }
            java.lang.String r1 = "admob_app_id"
            java.lang.String r0 = r0.getString(r1)     // Catch:{ IllegalStateException -> 0x01c6 }
            r11.zzcu = r0     // Catch:{ IllegalStateException -> 0x01c6 }
        L_0x01b2:
            if (r2 == 0) goto L_0x01d8
            com.google.android.gms.measurement.internal.zzef r0 = r11.zzab()     // Catch:{ IllegalStateException -> 0x01c6 }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzgs()     // Catch:{ IllegalStateException -> 0x01c6 }
            java.lang.String r1 = "App package, google app id"
            java.lang.String r2 = r11.zzce     // Catch:{ IllegalStateException -> 0x01c6 }
            java.lang.String r7 = r11.zzcg     // Catch:{ IllegalStateException -> 0x01c6 }
            r0.zza(r1, r2, r7)     // Catch:{ IllegalStateException -> 0x01c6 }
            goto L_0x01d8
        L_0x01c6:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzef r1 = r11.zzab()
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzgk()
            java.lang.String r2 = "getGoogleAppId or isMeasurementEnabled failed with exception. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r3)
            r1.zza(r2, r3, r0)
        L_0x01d8:
            r0 = 0
            r11.zzcw = r0
            com.google.android.gms.measurement.internal.zzs r0 = r11.zzad()
            java.lang.String r1 = r11.zzce
            com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzak.zzix
            boolean r0 = r0.zze(r1, r2)
            if (r0 == 0) goto L_0x022e
            r11.zzae()
            com.google.android.gms.measurement.internal.zzs r0 = r11.zzad()
            java.lang.String r1 = "analytics.safelisted_events"
            java.util.List r0 = r0.b(r1)
            if (r0 == 0) goto L_0x022a
            int r1 = r0.size()
            if (r1 != 0) goto L_0x020d
            com.google.android.gms.measurement.internal.zzef r1 = r11.zzab()
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzgn()
            java.lang.String r2 = "Safelisted event list cannot be empty. Ignoring"
            r1.zzao(r2)
        L_0x020b:
            r6 = 0
            goto L_0x022a
        L_0x020d:
            java.util.Iterator r1 = r0.iterator()
        L_0x0211:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x022a
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            com.google.android.gms.measurement.internal.zzjs r3 = r11.zzz()
            java.lang.String r7 = "safelisted event"
            boolean r2 = r3.b((java.lang.String) r7, (java.lang.String) r2)
            if (r2 != 0) goto L_0x0211
            goto L_0x020b
        L_0x022a:
            if (r6 == 0) goto L_0x022e
            r11.zzcw = r0
        L_0x022e:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 16
            if (r0 < r1) goto L_0x0244
            if (r4 == 0) goto L_0x0241
            android.content.Context r0 = r11.getContext()
            boolean r0 = com.google.android.gms.common.wrappers.InstantApps.isInstantApp(r0)
            r11.zzds = r0
            return
        L_0x0241:
            r11.zzds = r5
            return
        L_0x0244:
            r11.zzds = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzdy.b():void");
    }

    /* access modifiers changed from: package-private */
    public final String c() {
        j();
        return this.zzce;
    }

    /* access modifiers changed from: package-private */
    public final String d() {
        j();
        return this.zzcg;
    }

    /* access modifiers changed from: package-private */
    public final String e() {
        j();
        return this.zzcu;
    }

    /* access modifiers changed from: package-private */
    public final int f() {
        j();
        return this.zzjr;
    }

    /* access modifiers changed from: package-private */
    public final int g() {
        j();
        return this.zzds;
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public final List<String> h() {
        return this.zzcw;
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

    public final /* bridge */ /* synthetic */ void zzl() {
        super.zzl();
    }

    public final /* bridge */ /* synthetic */ void zzm() {
        super.zzm();
    }

    public final /* bridge */ /* synthetic */ void zzn() {
        super.zzn();
    }

    public final /* bridge */ /* synthetic */ void zzo() {
        super.zzo();
    }

    public final /* bridge */ /* synthetic */ zza zzp() {
        return super.zzp();
    }

    public final /* bridge */ /* synthetic */ zzgp zzq() {
        return super.zzq();
    }

    public final /* bridge */ /* synthetic */ zzdy zzr() {
        return super.zzr();
    }

    public final /* bridge */ /* synthetic */ zzhv zzs() {
        return super.zzs();
    }

    public final /* bridge */ /* synthetic */ zzhq zzt() {
        return super.zzt();
    }

    public final /* bridge */ /* synthetic */ zzeb zzu() {
        return super.zzu();
    }

    public final /* bridge */ /* synthetic */ zziw zzv() {
        return super.zzv();
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
