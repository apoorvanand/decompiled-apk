package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.util.VisibleForTesting;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

final class zzeo extends zzge {
    @VisibleForTesting
    static final Pair<String, Long> a = new Pair<>("", 0L);
    private SharedPreferences zzlh;
    public zzes zzli;
    public final zzet zzlj = new zzet(this, "last_upload", 0);
    public final zzet zzlk = new zzet(this, "last_upload_attempt", 0);
    public final zzet zzll = new zzet(this, "backoff", 0);
    public final zzet zzlm = new zzet(this, "last_delete_stale", 0);
    public final zzet zzln = new zzet(this, "midnight_offset", 0);
    public final zzet zzlo = new zzet(this, "first_open_time", 0);
    public final zzet zzlp = new zzet(this, "app_install_time", 0);
    public final zzev zzlq = new zzev(this, "app_instance_id", (String) null);
    private String zzlr;
    private boolean zzls;
    private long zzlt;
    public final zzet zzlu = new zzet(this, "time_before_start", 10000);
    public final zzet zzlv = new zzet(this, "session_timeout", 1800000);
    public final zzeq zzlw = new zzeq(this, "start_new_session", true);
    public final zzev zzlx = new zzev(this, "non_personalized_ads", (String) null);
    public final zzeq zzly = new zzeq(this, "use_dynamite_api", false);
    public final zzeq zzlz = new zzeq(this, "allow_remote_dynamite", false);
    public final zzet zzma = new zzet(this, "last_pause_time", 0);
    public final zzet zzmb = new zzet(this, "time_active", 0);
    public boolean zzmc;
    public zzeq zzmd = new zzeq(this, "app_backgrounded", false);
    public zzet zzme = new zzet(this, "deep_link_last_retrieved", -1);

    zzeo(zzfj zzfj) {
        super(zzfj);
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final SharedPreferences zzhb() {
        zzo();
        l();
        return this.zzlh;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    @NonNull
    public final Pair<String, Boolean> a(String str) {
        zzo();
        long elapsedRealtime = zzx().elapsedRealtime();
        String str2 = this.zzlr;
        if (str2 != null && elapsedRealtime < this.zzlt) {
            return new Pair<>(str2, Boolean.valueOf(this.zzls));
        }
        this.zzlt = elapsedRealtime + zzad().zza(str, zzak.zzgg);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(getContext());
            if (advertisingIdInfo != null) {
                this.zzlr = advertisingIdInfo.getId();
                this.zzls = advertisingIdInfo.isLimitAdTrackingEnabled();
            }
            if (this.zzlr == null) {
                this.zzlr = "";
            }
        } catch (Exception e) {
            zzab().zzgr().zza("Unable to get advertising id", e);
            this.zzlr = "";
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair<>(this.zzlr, Boolean.valueOf(this.zzls));
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void a(boolean z) {
        zzo();
        zzab().zzgs().zza("Setting useService", Boolean.valueOf(z));
        SharedPreferences.Editor edit = zzhb().edit();
        edit.putBoolean("use_service", z);
        edit.apply();
    }

    /* access modifiers changed from: protected */
    public final boolean a() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean a(long j) {
        return j - this.zzlv.get() > this.zzma.get();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final String b(String str) {
        zzo();
        String str2 = (String) a(str).first;
        MessageDigest d = zzjs.d();
        if (d == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new Object[]{new BigInteger(1, d.digest(str2.getBytes()))});
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void b() {
        this.zzlh = getContext().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.zzmc = this.zzlh.getBoolean("has_been_opened", false);
        if (!this.zzmc) {
            SharedPreferences.Editor edit = this.zzlh.edit();
            edit.putBoolean("has_been_opened", true);
            edit.apply();
        }
        this.zzli = new zzes(this, "health_monitor", Math.max(0, zzak.zzgh.get(null).longValue()));
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void b(boolean z) {
        zzo();
        zzab().zzgs().zza("Setting measurementEnabled", Boolean.valueOf(z));
        SharedPreferences.Editor edit = zzhb().edit();
        edit.putBoolean("measurement_enabled", z);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final String c() {
        zzo();
        return zzhb().getString("gmp_app_id", (String) null);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void c(String str) {
        zzo();
        SharedPreferences.Editor edit = zzhb().edit();
        edit.putString("gmp_app_id", str);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean c(boolean z) {
        zzo();
        return zzhb().getBoolean("measurement_enabled", z);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final String d() {
        zzo();
        return zzhb().getString("admob_app_id", (String) null);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void d(String str) {
        zzo();
        SharedPreferences.Editor edit = zzhb().edit();
        edit.putString("admob_app_id", str);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void d(boolean z) {
        zzo();
        zzab().zzgs().zza("Updating deferred analytics collection", Boolean.valueOf(z));
        SharedPreferences.Editor edit = zzhb().edit();
        edit.putBoolean("deferred_analytics_collection", z);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final Boolean e() {
        zzo();
        if (!zzhb().contains("use_service")) {
            return null;
        }
        return Boolean.valueOf(zzhb().getBoolean("use_service", false));
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void f() {
        zzo();
        zzab().zzgs().zzao("Clearing collection preferences.");
        if (zzad().zza(zzak.zzil)) {
            Boolean g = g();
            SharedPreferences.Editor edit = zzhb().edit();
            edit.clear();
            edit.apply();
            if (g != null) {
                b(g.booleanValue());
                return;
            }
            return;
        }
        boolean contains = zzhb().contains("measurement_enabled");
        boolean z = true;
        if (contains) {
            z = c(true);
        }
        SharedPreferences.Editor edit2 = zzhb().edit();
        edit2.clear();
        edit2.apply();
        if (contains) {
            b(z);
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final Boolean g() {
        zzo();
        if (zzhb().contains("measurement_enabled")) {
            return Boolean.valueOf(zzhb().getBoolean("measurement_enabled", true));
        }
        return null;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final String h() {
        zzo();
        String string = zzhb().getString("previous_os_version", (String) null);
        zzw().l();
        String str = Build.VERSION.RELEASE;
        if (!TextUtils.isEmpty(str) && !str.equals(string)) {
            SharedPreferences.Editor edit = zzhb().edit();
            edit.putString("previous_os_version", str);
            edit.apply();
        }
        return string;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean i() {
        zzo();
        return zzhb().getBoolean("deferred_analytics_collection", false);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean j() {
        return this.zzlh.contains("deferred_analytics_collection");
    }
}
