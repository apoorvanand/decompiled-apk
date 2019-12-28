package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzcm;
import com.google.android.gms.internal.measurement.zzp;
import com.google.android.gms.internal.measurement.zzx;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONException;
import org.json.JSONObject;

public class zzfj implements zzgh {
    private static volatile zzfj zzoa;
    private final Clock zzac;
    private boolean zzdh = false;
    private final long zzdr;
    private final zzr zzfv;
    private final Context zzob;
    private final String zzoc;
    private final String zzod;
    private final zzs zzoe;
    private final zzeo zzof;
    private final zzef zzog;
    private final zzfc zzoh;
    private final zziw zzoi;
    private final zzjs zzoj;
    private final zzed zzok;
    private final zzhq zzol;
    private final zzgp zzom;
    private final zza zzon;
    private final zzhl zzoo;
    private zzeb zzop;
    private zzhv zzoq;
    private zzac zzor;
    private zzdy zzos;
    private zzeu zzot;
    private Boolean zzou;
    private long zzov;
    private volatile Boolean zzow;
    @VisibleForTesting
    private Boolean zzox;
    @VisibleForTesting
    private Boolean zzoy;
    private int zzoz;
    private AtomicInteger zzpa = new AtomicInteger(0);
    private final boolean zzt;
    private final String zzv;

    private zzfj(zzgm zzgm) {
        String str;
        zzeh zzeh;
        boolean z = false;
        Preconditions.checkNotNull(zzgm);
        this.zzfv = new zzr(zzgm.a);
        zzak.a(this.zzfv);
        this.zzob = zzgm.a;
        this.zzv = zzgm.b;
        this.zzoc = zzgm.c;
        this.zzod = zzgm.d;
        this.zzt = zzgm.h;
        this.zzow = zzgm.e;
        zzx zzx = zzgm.g;
        if (!(zzx == null || zzx.zzw == null)) {
            Object obj = zzx.zzw.get("measurementEnabled");
            if (obj instanceof Boolean) {
                this.zzox = (Boolean) obj;
            }
            Object obj2 = zzx.zzw.get("measurementDeactivated");
            if (obj2 instanceof Boolean) {
                this.zzoy = (Boolean) obj2;
            }
        }
        zzcm.zzr(this.zzob);
        this.zzac = DefaultClock.getInstance();
        this.zzdr = this.zzac.currentTimeMillis();
        this.zzoe = new zzs(this);
        zzeo zzeo = new zzeo(this);
        zzeo.initialize();
        this.zzof = zzeo;
        zzef zzef = new zzef(this);
        zzef.initialize();
        this.zzog = zzef;
        zzjs zzjs = new zzjs(this);
        zzjs.initialize();
        this.zzoj = zzjs;
        zzed zzed = new zzed(this);
        zzed.initialize();
        this.zzok = zzed;
        this.zzon = new zza(this);
        zzhq zzhq = new zzhq(this);
        zzhq.initialize();
        this.zzol = zzhq;
        zzgp zzgp = new zzgp(this);
        zzgp.initialize();
        this.zzom = zzgp;
        zziw zziw = new zziw(this);
        zziw.initialize();
        this.zzoi = zziw;
        zzhl zzhl = new zzhl(this);
        zzhl.initialize();
        this.zzoo = zzhl;
        zzfc zzfc = new zzfc(this);
        zzfc.initialize();
        this.zzoh = zzfc;
        if (!(zzgm.g == null || zzgm.g.zzs == 0)) {
            z = true;
        }
        boolean z2 = !z;
        zzr zzr = this.zzfv;
        if (this.zzob.getApplicationContext() instanceof Application) {
            zzgp zzq = zzq();
            if (zzq.getContext().getApplicationContext() instanceof Application) {
                Application application = (Application) zzq.getContext().getApplicationContext();
                if (zzq.a == null) {
                    zzq.a = new zzhj(zzq, (zzgo) null);
                }
                if (z2) {
                    application.unregisterActivityLifecycleCallbacks(zzq.a);
                    application.registerActivityLifecycleCallbacks(zzq.a);
                    zzeh = zzq.zzab().zzgs();
                    str = "Registered activity lifecycle callback";
                }
            }
            this.zzoh.zza((Runnable) new zzfl(this, zzgm));
        }
        zzeh = zzab().zzgn();
        str = "Application context is not an Application";
        zzeh.zzao(str);
        this.zzoh.zza((Runnable) new zzfl(this, zzgm));
    }

    public static zzfj zza(Context context, zzx zzx) {
        if (zzx != null && (zzx.origin == null || zzx.zzv == null)) {
            zzx = new zzx(zzx.zzr, zzx.zzs, zzx.zzt, zzx.zzu, (String) null, (String) null, zzx.zzw);
        }
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzoa == null) {
            synchronized (zzfj.class) {
                if (zzoa == null) {
                    zzoa = new zzfj(new zzgm(context, zzx));
                }
            }
        } else if (!(zzx == null || zzx.zzw == null || !zzx.zzw.containsKey("dataCollectionDefaultEnabled"))) {
            zzoa.a(zzx.zzw.getBoolean("dataCollectionDefaultEnabled"));
        }
        return zzoa;
    }

    @VisibleForTesting
    public static zzfj zza(Context context, String str, String str2, Bundle bundle) {
        return zza(context, new zzx(0, 0, true, (String) null, (String) null, (String) null, bundle));
    }

    private static void zza(zzg zzg) {
        if (zzg == null) {
            throw new IllegalStateException("Component not created");
        } else if (!zzg.i()) {
            String valueOf = String.valueOf(zzg.getClass());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
            sb.append("Component not initialized: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
    }

    private static void zza(zzge zzge) {
        if (zzge == null) {
            throw new IllegalStateException("Component not created");
        } else if (!zzge.k()) {
            String valueOf = String.valueOf(zzge.getClass());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
            sb.append("Component not initialized: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
    }

    private static void zza(zzgf zzgf) {
        if (zzgf == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zza(zzgm zzgm) {
        String str;
        zzeh zzeh;
        zzaa().zzo();
        zzs.a();
        zzac zzac2 = new zzac(this);
        zzac2.initialize();
        this.zzor = zzac2;
        zzdy zzdy = new zzdy(this, zzgm.f);
        zzdy.initialize();
        this.zzos = zzdy;
        zzeb zzeb = new zzeb(this);
        zzeb.initialize();
        this.zzop = zzeb;
        zzhv zzhv = new zzhv(this);
        zzhv.initialize();
        this.zzoq = zzhv;
        this.zzoj.zzbj();
        this.zzof.zzbj();
        this.zzot = new zzeu(this);
        this.zzos.zzbj();
        zzab().zzgq().zza("App measurement is starting up, version", Long.valueOf(this.zzoe.zzao()));
        zzr zzr = this.zzfv;
        zzab().zzgq().zzao("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        zzr zzr2 = this.zzfv;
        String c = zzdy.c();
        if (TextUtils.isEmpty(this.zzv)) {
            if (zzz().f(c)) {
                zzeh = zzab().zzgq();
                str = "Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.";
            } else {
                zzeh = zzab().zzgq();
                String valueOf = String.valueOf(c);
                str = valueOf.length() != 0 ? "To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ".concat(valueOf) : new String("To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ");
            }
            zzeh.zzao(str);
        }
        zzab().zzgr().zzao("Debug-level message logging enabled");
        if (this.zzoz != this.zzpa.get()) {
            zzab().zzgk().zza("Not all components initialized", Integer.valueOf(this.zzoz), Integer.valueOf(this.zzpa.get()));
        }
        this.zzdh = true;
    }

    private final void zzbi() {
        if (!this.zzdh) {
            throw new IllegalStateException("AppMeasurement is not initialized");
        }
    }

    private final zzhl zzhv() {
        zza((zzge) this.zzoo);
        return this.zzoo;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void a() {
        zzaa().zzo();
        if (zzac().zzlj.get() == 0) {
            zzac().zzlj.set(this.zzac.currentTimeMillis());
        }
        if (Long.valueOf(zzac().zzlo.get()).longValue() == 0) {
            zzab().zzgs().zza("Persisting first open", Long.valueOf(this.zzdr));
            zzac().zzlo.set(this.zzdr);
        }
        if (g()) {
            zzr zzr = this.zzfv;
            if (!TextUtils.isEmpty(zzr().d()) || !TextUtils.isEmpty(zzr().e())) {
                zzz();
                if (zzjs.a(zzr().d(), zzac().c(), zzr().e(), zzac().d())) {
                    zzab().zzgq().zzao("Rechecking which service to use due to a GMP App Id change");
                    zzac().f();
                    zzu().resetAnalyticsData();
                    this.zzoq.disconnect();
                    this.zzoq.f();
                    zzac().zzlo.set(this.zzdr);
                    zzac().zzlq.zzau((String) null);
                }
                zzac().c(zzr().d());
                zzac().d(zzr().e());
            }
            zzq().a(zzac().zzlq.zzho());
            zzr zzr2 = this.zzfv;
            if (!TextUtils.isEmpty(zzr().d()) || !TextUtils.isEmpty(zzr().e())) {
                boolean isEnabled = isEnabled();
                if (!zzac().j() && !this.zzoe.zzbp()) {
                    zzac().d(!isEnabled);
                }
                if (isEnabled) {
                    zzq().zzim();
                }
                zzs().zza(new AtomicReference());
            }
        } else if (isEnabled()) {
            if (!zzz().d("android.permission.INTERNET")) {
                zzab().zzgk().zzao("App is missing INTERNET permission");
            }
            if (!zzz().d("android.permission.ACCESS_NETWORK_STATE")) {
                zzab().zzgk().zzao("App is missing ACCESS_NETWORK_STATE permission");
            }
            zzr zzr3 = this.zzfv;
            if (!Wrappers.packageManager(this.zzob).isCallerInstantApp() && !this.zzoe.b()) {
                if (!zzez.zzl(this.zzob)) {
                    zzab().zzgk().zzao("AppMeasurementReceiver not registered/enabled");
                }
                if (!zzjs.a(this.zzob, false)) {
                    zzab().zzgk().zzao("AppMeasurementService not registered/enabled");
                }
            }
            zzab().zzgk().zzao("Uploading is not possible. App measurement disabled");
        }
        zzac().zzly.set(this.zzoe.zza(zzak.zziu));
        zzac().zzlz.set(this.zzoe.zza(zzak.zziv));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void a(zzp zzp, String str, int i, Throwable th, byte[] bArr, Map map) {
        List<ResolveInfo> queryIntentActivities;
        boolean z = true;
        if (!((i == 200 || i == 204 || i == 304) && th == null)) {
            zzab().zzgn().zza("Network Request for Deferred Deep Link failed. response, exception", Integer.valueOf(i), th);
        } else if (bArr.length != 0) {
            try {
                JSONObject jSONObject = new JSONObject(new String(bArr));
                String optString = jSONObject.optString("deeplink", "");
                String optString2 = jSONObject.optString("gclid", "");
                zzjs zzz = zzz();
                zzz.zzm();
                if (TextUtils.isEmpty(optString) || (queryIntentActivities = zzz.getContext().getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(optString)), 0)) == null || queryIntentActivities.isEmpty()) {
                    z = false;
                }
                if (!z) {
                    zzab().zzgn().zza("Deferred Deep Link validation failed. gclid, deep link", optString2, optString);
                    zzz().zzb(zzp, "");
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("deeplink", optString);
                bundle.putString("gclid", optString2);
                this.zzom.logEvent("auto", "_cmp", bundle);
                zzz().zzb(zzp, optString);
                return;
            } catch (JSONException e) {
                zzab().zzgk().zza("Failed to parse the Deferred Deep Link response. exception", e);
            }
        }
        zzz().zzb(zzp, "");
    }

    /* access modifiers changed from: package-private */
    public final void a(zzg zzg) {
        this.zzoz++;
    }

    /* access modifiers changed from: package-private */
    public final void a(zzge zzge) {
        this.zzoz++;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void a(boolean z) {
        this.zzow = Boolean.valueOf(z);
    }

    /* access modifiers changed from: package-private */
    public final zzfc b() {
        return this.zzoh;
    }

    /* access modifiers changed from: package-private */
    public final long c() {
        Long valueOf = Long.valueOf(zzac().zzlo.get());
        return valueOf.longValue() == 0 ? this.zzdr : Math.min(this.zzdr, valueOf.longValue());
    }

    /* access modifiers changed from: package-private */
    public final void d() {
        zzr zzr = this.zzfv;
    }

    /* access modifiers changed from: package-private */
    public final void e() {
        zzr zzr = this.zzfv;
        throw new IllegalStateException("Unexpected call on client side");
    }

    /* access modifiers changed from: package-private */
    public final void f() {
        this.zzpa.incrementAndGet();
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final boolean g() {
        zzbi();
        zzaa().zzo();
        Boolean bool = this.zzou;
        if (bool == null || this.zzov == 0 || (bool != null && !bool.booleanValue() && Math.abs(this.zzac.elapsedRealtime() - this.zzov) > 1000)) {
            this.zzov = this.zzac.elapsedRealtime();
            zzr zzr = this.zzfv;
            boolean z = true;
            this.zzou = Boolean.valueOf(zzz().d("android.permission.INTERNET") && zzz().d("android.permission.ACCESS_NETWORK_STATE") && (Wrappers.packageManager(this.zzob).isCallerInstantApp() || this.zzoe.b() || (zzez.zzl(this.zzob) && zzjs.a(this.zzob, false))));
            if (this.zzou.booleanValue()) {
                if (!zzz().c(zzr().d(), zzr().e()) && TextUtils.isEmpty(zzr().e())) {
                    z = false;
                }
                this.zzou = Boolean.valueOf(z);
            }
        }
        return this.zzou.booleanValue();
    }

    public final Context getContext() {
        return this.zzob;
    }

    @WorkerThread
    public final boolean isEnabled() {
        boolean z;
        zzaa().zzo();
        zzbi();
        if (this.zzoe.zza(zzak.zzil)) {
            if (this.zzoe.zzbp()) {
                return false;
            }
            Boolean bool = this.zzoy;
            if (bool != null && bool.booleanValue()) {
                return false;
            }
            Boolean g = zzac().g();
            if (g != null) {
                return g.booleanValue();
            }
            Boolean zzbq = this.zzoe.zzbq();
            if (zzbq != null) {
                return zzbq.booleanValue();
            }
            Boolean bool2 = this.zzox;
            if (bool2 != null) {
                return bool2.booleanValue();
            }
            if (GoogleServices.isMeasurementExplicitlyDisabled()) {
                return false;
            }
            if (!this.zzoe.zza(zzak.zzig) || this.zzow == null) {
                return true;
            }
            return this.zzow.booleanValue();
        } else if (this.zzoe.zzbp()) {
            return false;
        } else {
            Boolean zzbq2 = this.zzoe.zzbq();
            if (zzbq2 == null) {
                z = !GoogleServices.isMeasurementExplicitlyDisabled();
                if (z && this.zzow != null && zzak.zzig.get(null).booleanValue()) {
                    zzbq2 = this.zzow;
                }
                return zzac().c(z);
            }
            z = zzbq2.booleanValue();
            return zzac().c(z);
        }
    }

    @WorkerThread
    public final void zza(@NonNull zzp zzp) {
        zzaa().zzo();
        zza((zzge) zzhv());
        String c = zzr().c();
        Pair<String, Boolean> a = zzac().a(c);
        if (!this.zzoe.zzbr().booleanValue() || ((Boolean) a.second).booleanValue()) {
            zzab().zzgr().zzao("ADID unavailable to retrieve Deferred Deep Link. Skipping");
            zzz().zzb(zzp, "");
        } else if (!zzhv().zzgv()) {
            zzab().zzgn().zzao("Network is not available for Deferred Deep Link request. Skipping");
            zzz().zzb(zzp, "");
        } else {
            URL zza = zzz().zza(zzr().zzad().zzao(), c, (String) a.first);
            zzhl zzhv = zzhv();
            zzfi zzfi = new zzfi(this, zzp);
            zzhv.zzo();
            zzhv.l();
            Preconditions.checkNotNull(zza);
            Preconditions.checkNotNull(zzfi);
            zzhv.zzaa().zzb((Runnable) new zzhn(zzhv, c, zza, (byte[]) null, (Map<String, String>) null, zzfi));
        }
    }

    public final zzfc zzaa() {
        zza((zzge) this.zzoh);
        return this.zzoh;
    }

    public final zzef zzab() {
        zza((zzge) this.zzog);
        return this.zzog;
    }

    public final zzeo zzac() {
        zza((zzgf) this.zzof);
        return this.zzof;
    }

    public final zzs zzad() {
        return this.zzoe;
    }

    public final zzr zzae() {
        return this.zzfv;
    }

    public final zzef zzhs() {
        zzef zzef = this.zzog;
        if (zzef == null || !zzef.k()) {
            return null;
        }
        return this.zzog;
    }

    public final zzeu zzht() {
        return this.zzot;
    }

    public final boolean zzhw() {
        return TextUtils.isEmpty(this.zzv);
    }

    public final String zzhx() {
        return this.zzv;
    }

    public final String zzhy() {
        return this.zzoc;
    }

    public final String zzhz() {
        return this.zzod;
    }

    public final boolean zzia() {
        return this.zzt;
    }

    @WorkerThread
    public final boolean zzib() {
        return this.zzow != null && this.zzow.booleanValue();
    }

    public final zza zzp() {
        zza zza = this.zzon;
        if (zza != null) {
            return zza;
        }
        throw new IllegalStateException("Component not created");
    }

    public final zzgp zzq() {
        zza((zzg) this.zzom);
        return this.zzom;
    }

    public final zzdy zzr() {
        zza((zzg) this.zzos);
        return this.zzos;
    }

    public final zzhv zzs() {
        zza((zzg) this.zzoq);
        return this.zzoq;
    }

    public final zzhq zzt() {
        zza((zzg) this.zzol);
        return this.zzol;
    }

    public final zzeb zzu() {
        zza((zzg) this.zzop);
        return this.zzop;
    }

    public final zziw zzv() {
        zza((zzg) this.zzoi);
        return this.zzoi;
    }

    public final zzac zzw() {
        zza((zzge) this.zzor);
        return this.zzor;
    }

    public final Clock zzx() {
        return this.zzac;
    }

    public final zzed zzy() {
        zza((zzgf) this.zzok);
        return this.zzok;
    }

    public final zzjs zzz() {
        zza((zzgf) this.zzoj);
        return this.zzoj;
    }
}
