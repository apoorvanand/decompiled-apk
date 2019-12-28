package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import androidx.core.app.NotificationCompat;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReference;

public final class zzgp extends zzg {
    @VisibleForTesting
    protected zzhj a;
    @VisibleForTesting
    protected boolean c = true;
    private zzgk zzpv;
    private final Set<zzgn> zzpw = new CopyOnWriteArraySet();
    private boolean zzpx;
    private final AtomicReference<String> zzpy = new AtomicReference<>();

    protected zzgp(zzfj zzfj) {
        super(zzfj);
    }

    private final void zza(Bundle bundle, long j) {
        Preconditions.checkNotNull(bundle);
        zzgg.zza(bundle, "app_id", String.class, null);
        zzgg.zza(bundle, "origin", String.class, null);
        zzgg.zza(bundle, "name", String.class, null);
        zzgg.zza(bundle, "value", Object.class, null);
        zzgg.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, String.class, null);
        zzgg.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.class, 0L);
        zzgg.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, String.class, null);
        zzgg.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, Bundle.class, null);
        zzgg.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, String.class, null);
        zzgg.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, Bundle.class, null);
        zzgg.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.class, 0L);
        zzgg.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, String.class, null);
        zzgg.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, Bundle.class, null);
        Preconditions.checkNotEmpty(bundle.getString("name"));
        Preconditions.checkNotEmpty(bundle.getString("origin"));
        Preconditions.checkNotNull(bundle.get("value"));
        bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, j);
        String string = bundle.getString("name");
        Object obj = bundle.get("value");
        if (zzz().c(string) != 0) {
            zzab().zzgk().zza("Invalid conditional user property name", zzy().c(string));
        } else if (zzz().b(string, obj) != 0) {
            zzab().zzgk().zza("Invalid conditional user property value", zzy().c(string), obj);
        } else {
            Object c2 = zzz().c(string, obj);
            if (c2 == null) {
                zzab().zzgk().zza("Unable to normalize conditional user property value", zzy().c(string), obj);
                return;
            }
            zzgg.zza(bundle, c2);
            long j2 = bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT);
            if (TextUtils.isEmpty(bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME)) || (j2 <= 15552000000L && j2 >= 1)) {
                long j3 = bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE);
                if (j3 > 15552000000L || j3 < 1) {
                    zzab().zzgk().zza("Invalid conditional user property time to live", zzy().c(string), Long.valueOf(j3));
                } else {
                    zzaa().zza((Runnable) new zzgx(this, bundle));
                }
            } else {
                zzab().zzgk().zza("Invalid conditional user property timeout", zzy().c(string), Long.valueOf(j2));
            }
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zza(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        String[] strArr;
        List list;
        zzhr zzhr;
        int i;
        long j2;
        ArrayList arrayList;
        Bundle bundle2;
        List<String> h;
        String str4 = str;
        String str5 = str2;
        long j3 = j;
        Bundle bundle3 = bundle;
        String str6 = str3;
        Preconditions.checkNotEmpty(str);
        if (!zzad().zze(str6, zzak.zzip)) {
            Preconditions.checkNotEmpty(str2);
        }
        Preconditions.checkNotNull(bundle);
        zzo();
        j();
        if (!this.b.isEnabled()) {
            zzab().zzgr().zzao("Event not sent since app measurement is disabled");
        } else if (!zzad().zze(zzr().c(), zzak.zzix) || (h = zzr().h()) == null || h.contains(str5)) {
            int i2 = 0;
            if (!this.zzpx) {
                this.zzpx = true;
                try {
                    try {
                        (!this.b.zzia() ? Class.forName("com.google.android.gms.tagmanager.TagManagerService", true, getContext().getClassLoader()) : Class.forName("com.google.android.gms.tagmanager.TagManagerService")).getDeclaredMethod("initialize", new Class[]{Context.class}).invoke((Object) null, new Object[]{getContext()});
                    } catch (Exception e) {
                        zzab().zzgn().zza("Failed to invoke Tag Manager's initialize() method", e);
                    }
                } catch (ClassNotFoundException unused) {
                    zzab().zzgq().zzao("Tag Manager is not found and thus will not be used");
                }
            }
            if (zzad().zze(zzr().c(), zzak.zzje) && "_cmp".equals(str5) && bundle3.containsKey("gclid")) {
                a("auto", "_lgclid", (Object) bundle3.getString("gclid"), zzx().currentTimeMillis());
            }
            if (z3) {
                zzae();
                if (!"_iap".equals(str5)) {
                    zzjs zzz = this.b.zzz();
                    int i3 = 2;
                    if (zzz.a(NotificationCompat.CATEGORY_EVENT, str5)) {
                        if (!zzz.a(NotificationCompat.CATEGORY_EVENT, zzgj.zzpn, str5)) {
                            i3 = 13;
                        } else if (zzz.a(NotificationCompat.CATEGORY_EVENT, 40, str5)) {
                            i3 = 0;
                        }
                    }
                    if (i3 != 0) {
                        zzab().zzgm().zza("Invalid public event name. Event will not be logged (FE)", zzy().a(str5));
                        this.b.zzz();
                        this.b.zzz().zza(i3, "_ev", zzjs.zza(str5, 40, true), str5 != null ? str2.length() : 0);
                        return;
                    }
                }
            }
            zzae();
            zzhr zzin = zzt().zzin();
            if (zzin != null && !bundle3.containsKey("_sc")) {
                zzin.a = true;
            }
            zzhq.zza(zzin, bundle3, z && z3);
            boolean equals = "am".equals(str4);
            boolean e2 = zzjs.e(str2);
            if (z && this.zzpv != null && !e2 && !equals) {
                zzab().zzgr().zza("Passing event to registered event handler (FE)", zzy().a(str5), zzy().a(bundle3));
                this.zzpv.interceptEvent(str, str2, bundle, j);
            } else if (this.b.g()) {
                int b = zzz().b(str5);
                if (b != 0) {
                    zzab().zzgm().zza("Invalid event name. Event will not be logged (FE)", zzy().a(str5));
                    zzz();
                    String zza = zzjs.zza(str5, 40, true);
                    if (str5 != null) {
                        i2 = str2.length();
                    }
                    this.b.zzz().a(str3, b, "_ev", zza, i2);
                    return;
                }
                List listOf = CollectionUtils.listOf((T[]) new String[]{"_o", "_sn", "_sc", "_si"});
                String str7 = str6;
                long j4 = j3;
                String str8 = str5;
                Bundle a2 = zzz().a(str3, str2, bundle, listOf, z3, true);
                zzhr zzhr2 = (a2 == null || !a2.containsKey("_sc") || !a2.containsKey("_si")) ? null : new zzhr(a2.getString("_sn"), a2.getString("_sc"), Long.valueOf(a2.getLong("_si")).longValue());
                zzhr zzhr3 = zzhr2 == null ? zzin : zzhr2;
                if (zzad().o(str7)) {
                    zzae();
                    if (zzt().zzin() != null && "_ae".equals(str8)) {
                        long e3 = zzv().e();
                        if (e3 > 0) {
                            zzz().a(a2, e3);
                        }
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(a2);
                long nextLong = zzz().c().nextLong();
                if (zzad().zze(zzr().c(), zzak.zzid) && zzac().zzma.get() > 0 && zzac().a(j4) && zzac().zzmd.get()) {
                    zzab().zzgs().zzao("Current session is expired, remove the session number and Id");
                    if (zzad().zze(zzr().c(), zzak.zzhz)) {
                        a("auto", "_sid", (Object) null, zzx().currentTimeMillis());
                    }
                    if (zzad().zze(zzr().c(), zzak.zzia)) {
                        a("auto", "_sno", (Object) null, zzx().currentTimeMillis());
                    }
                }
                if (!zzad().n(zzr().c()) || a2.getLong(FirebaseAnalytics.Param.EXTEND_SESSION, 0) != 1) {
                    long j5 = j;
                } else {
                    zzab().zzgs().zzao("EXTEND_SESSION param attached: initiate a new session or extend the current active session");
                    this.b.zzv().a(j, true);
                }
                String[] strArr2 = (String[]) a2.keySet().toArray(new String[bundle.size()]);
                Arrays.sort(strArr2);
                int length = strArr2.length;
                int i4 = 0;
                int i5 = 0;
                while (i4 < length) {
                    String str9 = strArr2[i4];
                    Object obj = a2.get(str9);
                    zzz();
                    Bundle[] a3 = zzjs.a(obj);
                    if (a3 != null) {
                        strArr = strArr2;
                        a2.putInt(str9, a3.length);
                        i = length;
                        int i6 = 0;
                        while (i6 < a3.length) {
                            Bundle bundle4 = a3[i6];
                            zzhq.zza(zzhr3, bundle4, true);
                            String str10 = str9;
                            long j6 = nextLong;
                            Bundle bundle5 = bundle4;
                            ArrayList arrayList3 = arrayList2;
                            Bundle a4 = zzz().a(str3, "_ep", bundle5, listOf, z3, false);
                            a4.putString("_en", str8);
                            a4.putLong("_eid", j6);
                            String str11 = str10;
                            a4.putString("_gn", str11);
                            a4.putInt("_ll", a3.length);
                            a4.putInt("_i", i6);
                            arrayList3.add(a4);
                            i6++;
                            a2 = a2;
                            arrayList2 = arrayList3;
                            str9 = str11;
                            zzhr3 = zzhr3;
                            listOf = listOf;
                            i5 = i5;
                            nextLong = j6;
                            long j7 = j;
                        }
                        list = listOf;
                        j2 = nextLong;
                        arrayList = arrayList2;
                        zzhr = zzhr3;
                        bundle2 = a2;
                        i5 += a3.length;
                    } else {
                        list = listOf;
                        strArr = strArr2;
                        i = length;
                        int i7 = i5;
                        j2 = nextLong;
                        arrayList = arrayList2;
                        zzhr = zzhr3;
                        bundle2 = a2;
                    }
                    i4++;
                    strArr2 = strArr;
                    a2 = bundle2;
                    arrayList2 = arrayList;
                    nextLong = j2;
                    length = i;
                    zzhr3 = zzhr;
                    listOf = list;
                    long j8 = j;
                }
                int i8 = i5;
                long j9 = nextLong;
                ArrayList arrayList4 = arrayList2;
                Bundle bundle6 = a2;
                if (i8 != 0) {
                    bundle6.putLong("_eid", j9);
                    bundle6.putInt("_epc", i8);
                }
                int i9 = 0;
                while (i9 < arrayList4.size()) {
                    Bundle bundle7 = (Bundle) arrayList4.get(i9);
                    String str12 = i9 != 0 ? "_ep" : str8;
                    String str13 = str8;
                    bundle7.putString("_o", str);
                    if (z2) {
                        bundle7 = zzz().a(bundle7);
                    }
                    Bundle bundle8 = bundle7;
                    zzab().zzgr().zza("Logging event (FE)", zzy().a(str13), zzy().a(bundle8));
                    ArrayList arrayList5 = arrayList4;
                    zzs().a(new zzai(str12, new zzah(bundle8), str, j), str3);
                    if (!equals) {
                        for (zzgn onEvent : this.zzpw) {
                            onEvent.onEvent(str, str2, new Bundle(bundle8), j);
                        }
                    }
                    i9++;
                    str8 = str13;
                    arrayList4 = arrayList5;
                }
                String str14 = str8;
                zzae();
                if (zzt().zzin() != null && "_ae".equals(str14)) {
                    zzv().zza(true, true);
                }
            }
        } else {
            zzab().zzgr().zza("Dropping non-safelisted event. event name, origin", str5, str4);
        }
    }

    private final void zza(String str, String str2, long j, Object obj) {
        zzaa().zza((Runnable) new zzgq(this, str, str2, obj, j));
    }

    private final void zza(String str, String str2, String str3, Bundle bundle) {
        long currentTimeMillis = zzx().currentTimeMillis();
        Preconditions.checkNotEmpty(str2);
        Bundle bundle2 = new Bundle();
        if (str != null) {
            bundle2.putString("app_id", str);
        }
        bundle2.putString("name", str2);
        bundle2.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, currentTimeMillis);
        if (str3 != null) {
            bundle2.putString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, str3);
            bundle2.putBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, bundle);
        }
        zzaa().zza((Runnable) new zzgw(this, bundle2));
    }

    @VisibleForTesting
    private final Map<String, Object> zzb(String str, String str2, String str3, boolean z) {
        zzeh zzgn;
        String str4;
        if (zzaa().zzhp()) {
            zzgn = zzab().zzgk();
            str4 = "Cannot get user properties from analytics worker thread";
        } else if (zzr.isMainThread()) {
            zzgn = zzab().zzgk();
            str4 = "Cannot get user properties from main thread";
        } else {
            AtomicReference atomicReference = new AtomicReference();
            synchronized (atomicReference) {
                this.b.zzaa().zza((Runnable) new zzhb(this, atomicReference, str, str2, str3, z));
                try {
                    atomicReference.wait(5000);
                } catch (InterruptedException e) {
                    zzab().zzgn().zza("Interrupted waiting for get user properties", e);
                }
            }
            List<zzjn> list = (List) atomicReference.get();
            if (list == null) {
                zzgn = zzab().zzgn();
                str4 = "Timed out waiting for get user properties";
            } else {
                ArrayMap arrayMap = new ArrayMap(list.size());
                for (zzjn zzjn : list) {
                    arrayMap.put(zzjn.name, zzjn.getValue());
                }
                return arrayMap;
            }
        }
        zzgn.zzao(str4);
        return Collections.emptyMap();
    }

    private final void zzb(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        zzaa().zza((Runnable) new zzgr(this, str, str2, j, zzjs.zzh(bundle), z, z2, z3, str3));
    }

    @VisibleForTesting
    private final ArrayList<Bundle> zze(String str, String str2, String str3) {
        if (zzaa().zzhp()) {
            zzab().zzgk().zzao("Cannot get conditional user properties from analytics worker thread");
            return new ArrayList<>(0);
        } else if (zzr.isMainThread()) {
            zzab().zzgk().zzao("Cannot get conditional user properties from main thread");
            return new ArrayList<>(0);
        } else {
            AtomicReference atomicReference = new AtomicReference();
            synchronized (atomicReference) {
                this.b.zzaa().zza((Runnable) new zzgz(this, atomicReference, str, str2, str3));
                try {
                    atomicReference.wait(5000);
                } catch (InterruptedException e) {
                    zzab().zzgn().zza("Interrupted waiting for get conditional user properties", str, e);
                }
            }
            List list = (List) atomicReference.get();
            if (list != null) {
                return zzjs.zzd((List<zzq>) list);
            }
            zzab().zzgn().zza("Timed out waiting for get conditional user properties", str);
            return new ArrayList<>();
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zze(Bundle bundle) {
        Bundle bundle2 = bundle;
        zzo();
        j();
        Preconditions.checkNotNull(bundle);
        Preconditions.checkNotEmpty(bundle2.getString("name"));
        Preconditions.checkNotEmpty(bundle2.getString("origin"));
        Preconditions.checkNotNull(bundle2.get("value"));
        if (!this.b.isEnabled()) {
            zzab().zzgr().zzao("Conditional property not sent since collection is disabled");
            return;
        }
        zzjn zzjn = new zzjn(bundle2.getString("name"), bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP), bundle2.get("value"), bundle2.getString("origin"));
        try {
            zzai a2 = zzz().a(bundle2.getString("app_id"), bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME), bundle2.getBundle(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS), bundle2.getString("origin"), 0, true, false);
            zzai a3 = zzz().a(bundle2.getString("app_id"), bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME), bundle2.getBundle(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS), bundle2.getString("origin"), 0, true, false);
            zzai a4 = zzz().a(bundle2.getString("app_id"), bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME), bundle2.getBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS), bundle2.getString("origin"), 0, true, false);
            String string = bundle2.getString("app_id");
            String string2 = bundle2.getString("origin");
            long j = bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP);
            String string3 = bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME);
            long j2 = bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT);
            long j3 = bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE);
            zzq zzq = r3;
            zzq zzq2 = new zzq(string, string2, zzjn, j, false, string3, a3, j2, a2, j3, a4);
            zzs().a(zzq);
        } catch (IllegalArgumentException unused) {
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzf(Bundle bundle) {
        Bundle bundle2 = bundle;
        zzo();
        j();
        Preconditions.checkNotNull(bundle);
        Preconditions.checkNotEmpty(bundle2.getString("name"));
        if (!this.b.isEnabled()) {
            zzab().zzgr().zzao("Conditional property not cleared since collection is disabled");
            return;
        }
        zzjn zzjn = new zzjn(bundle2.getString("name"), 0, (Object) null, (String) null);
        try {
            zzai a2 = zzz().a(bundle2.getString("app_id"), bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME), bundle2.getBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS), bundle2.getString("origin"), bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), true, false);
            String string = bundle2.getString("app_id");
            String string2 = bundle2.getString("origin");
            long j = bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP);
            boolean z = bundle2.getBoolean(AppMeasurementSdk.ConditionalUserProperty.ACTIVE);
            String string3 = bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME);
            long j2 = bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT);
            long j3 = bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE);
            zzq zzq = r3;
            zzq zzq2 = new zzq(string, string2, zzjn, j, z, string3, (zzai) null, j2, (zzai) null, j3, a2);
            zzs().a(zzq);
        } catch (IllegalArgumentException unused) {
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzg(boolean z) {
        zzo();
        zzm();
        j();
        zzab().zzgr().zza("Setting app measurement enabled (FE)", Boolean.valueOf(z));
        zzac().b(z);
        zzil();
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzil() {
        if (zzad().zze(zzr().c(), zzak.zzik)) {
            zzo();
            String zzho = zzac().zzlx.zzho();
            if (zzho != null) {
                if ("unset".equals(zzho)) {
                    a("app", "_npa", (Object) null, zzx().currentTimeMillis());
                } else {
                    a("app", "_npa", (Object) Long.valueOf(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(zzho) ? 1 : 0), zzx().currentTimeMillis());
                }
            }
        }
        if (!this.b.isEnabled() || !this.c) {
            zzab().zzgr().zzao("Updating Scion state (FE)");
            zzs().c();
            return;
        }
        zzab().zzgr().zzao("Recording app launch after enabling measurement for the first time (FE)");
        zzim();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:10|11|12|13) */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        zzab().zzgn().zzao("Interrupted waiting for app instance id");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002c, code lost:
        return null;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x001d */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String zzz(long r4) {
        /*
            r3 = this;
            java.util.concurrent.atomic.AtomicReference r0 = new java.util.concurrent.atomic.AtomicReference
            r0.<init>()
            monitor-enter(r0)
            com.google.android.gms.measurement.internal.zzfc r1 = r3.zzaa()     // Catch:{ all -> 0x002d }
            com.google.android.gms.measurement.internal.zzgs r2 = new com.google.android.gms.measurement.internal.zzgs     // Catch:{ all -> 0x002d }
            r2.<init>(r3, r0)     // Catch:{ all -> 0x002d }
            r1.zza((java.lang.Runnable) r2)     // Catch:{ all -> 0x002d }
            r0.wait(r4)     // Catch:{ InterruptedException -> 0x001d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            java.lang.Object r4 = r0.get()
            java.lang.String r4 = (java.lang.String) r4
            return r4
        L_0x001d:
            com.google.android.gms.measurement.internal.zzef r4 = r3.zzab()     // Catch:{ all -> 0x002d }
            com.google.android.gms.measurement.internal.zzeh r4 = r4.zzgn()     // Catch:{ all -> 0x002d }
            java.lang.String r5 = "Interrupted waiting for app instance id"
            r4.zzao(r5)     // Catch:{ all -> 0x002d }
            r4 = 0
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return r4
        L_0x002d:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzgp.zzz(long):java.lang.String");
    }

    /* access modifiers changed from: package-private */
    public final void a(@Nullable String str) {
        this.zzpy.set(str);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void a(String str, String str2, long j, Bundle bundle) {
        zzm();
        zzo();
        zza(str, str2, j, bundle, true, this.zzpv == null || zzjs.e(str2), false, (String) null);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void a(String str, String str2, Bundle bundle) {
        zzm();
        zzo();
        a(str, str2, zzx().currentTimeMillis(), bundle);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void a(String str, String str2, Object obj, long j) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzo();
        zzm();
        j();
        if (zzad().zze(zzr().c(), zzak.zzik) && FirebaseAnalytics.UserProperty.ALLOW_AD_PERSONALIZATION_SIGNALS.equals(str2)) {
            if (obj instanceof String) {
                String str3 = (String) obj;
                if (!TextUtils.isEmpty(str3)) {
                    obj = Long.valueOf("false".equals(str3.toLowerCase(Locale.ENGLISH)) ? 1 : 0);
                    str2 = "_npa";
                    zzac().zzlx.zzau(((Long) obj).longValue() == 1 ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false");
                }
            }
            if (obj == null) {
                str2 = "_npa";
                zzac().zzlx.zzau("unset");
            }
        }
        String str4 = str2;
        Object obj2 = obj;
        if (!this.b.isEnabled()) {
            zzab().zzgr().zzao("User property not set since app measurement is disabled");
        } else if (this.b.g()) {
            zzab().zzgr().zza("Setting user property (FE)", zzy().a(str4), obj2);
            zzs().a(new zzjn(str4, j, obj2, str));
        }
    }

    /* access modifiers changed from: protected */
    public final boolean a() {
        return false;
    }

    public final void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        zzm();
        zza((String) null, str, str2, bundle);
    }

    public final void clearConditionalUserPropertyAs(String str, String str2, String str3, Bundle bundle) {
        Preconditions.checkNotEmpty(str);
        zzl();
        zza(str, str2, str3, bundle);
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @Nullable
    public final String getCurrentScreenClass() {
        zzhr zzio = this.b.zzt().zzio();
        if (zzio != null) {
            return zzio.zzqv;
        }
        return null;
    }

    @Nullable
    public final String getCurrentScreenName() {
        zzhr zzio = this.b.zzt().zzio();
        if (zzio != null) {
            return zzio.zzqu;
        }
        return null;
    }

    @Nullable
    public final String getGmpAppId() {
        if (this.b.zzhx() != null) {
            return this.b.zzhx();
        }
        try {
            return GoogleServices.getGoogleAppId();
        } catch (IllegalStateException e) {
            this.b.zzab().zzgk().zza("getGoogleAppId failed with exception", e);
            return null;
        }
    }

    public final Map<String, Object> getUserProperties(String str, String str2, boolean z) {
        zzm();
        return zzb((String) null, str, str2, z);
    }

    public final Map<String, Object> getUserPropertiesAs(String str, String str2, String str3, boolean z) {
        Preconditions.checkNotEmpty(str);
        zzl();
        return zzb(str, str2, str3, z);
    }

    public final void logEvent(String str, String str2, Bundle bundle) {
        logEvent(str, str2, bundle, true, true, zzx().currentTimeMillis());
    }

    public final void logEvent(String str, String str2, Bundle bundle, boolean z, boolean z2, long j) {
        boolean z3;
        zzm();
        String str3 = str == null ? "app" : str;
        Bundle bundle2 = bundle == null ? new Bundle() : bundle;
        if (z2) {
            if (this.zzpv != null && !zzjs.e(str2)) {
                z3 = false;
                zzb(str3, str2, j, bundle2, z2, z3, !z, (String) null);
            }
        }
        z3 = true;
        zzb(str3, str2, j, bundle2, z2, z3, !z, (String) null);
    }

    public final void resetAnalyticsData(long j) {
        a((String) null);
        zzaa().zza((Runnable) new zzgv(this, j));
    }

    public final void setConditionalUserProperty(Bundle bundle) {
        setConditionalUserProperty(bundle, zzx().currentTimeMillis());
    }

    public final void setConditionalUserProperty(Bundle bundle, long j) {
        Preconditions.checkNotNull(bundle);
        zzm();
        Bundle bundle2 = new Bundle(bundle);
        if (!TextUtils.isEmpty(bundle2.getString("app_id"))) {
            zzab().zzgn().zzao("Package name should be null when calling setConditionalUserProperty");
        }
        bundle2.remove("app_id");
        zza(bundle2, j);
    }

    public final void setMeasurementEnabled(boolean z) {
        j();
        zzm();
        zzaa().zza((Runnable) new zzhf(this, z));
    }

    public final void setMinimumSessionDuration(long j) {
        zzm();
        zzaa().zza((Runnable) new zzhh(this, j));
    }

    public final void setSessionTimeoutDuration(long j) {
        zzm();
        zzaa().zza((Runnable) new zzhg(this, j));
    }

    @WorkerThread
    public final void zza(zzgk zzgk) {
        zzgk zzgk2;
        zzo();
        zzm();
        j();
        if (!(zzgk == null || zzgk == (zzgk2 = this.zzpv))) {
            Preconditions.checkState(zzgk2 == null, "EventInterceptor already set.");
        }
        this.zzpv = zzgk;
    }

    public final void zza(zzgn zzgn) {
        zzm();
        j();
        Preconditions.checkNotNull(zzgn);
        if (!this.zzpw.add(zzgn)) {
            zzab().zzgn().zzao("OnEventListener already registered");
        }
    }

    public final void zza(String str, String str2, Bundle bundle, boolean z) {
        logEvent(str, str2, bundle, false, true, zzx().currentTimeMillis());
    }

    public final void zza(String str, String str2, Object obj, boolean z, long j) {
        if (str == null) {
            str = "app";
        }
        String str3 = str;
        int i = 6;
        int i2 = 0;
        if (z) {
            i = zzz().c(str2);
        } else {
            zzjs zzz = zzz();
            if (zzz.a("user property", str2)) {
                if (!zzz.a("user property", zzgl.zzpp, str2)) {
                    i = 15;
                } else if (zzz.a("user property", 24, str2)) {
                    i = 0;
                }
            }
        }
        if (i != 0) {
            zzz();
            String zza = zzjs.zza(str2, 24, true);
            if (str2 != null) {
                i2 = str2.length();
            }
            this.b.zzz().zza(i, "_ev", zza, i2);
        } else if (obj != null) {
            int b = zzz().b(str2, obj);
            if (b != 0) {
                zzz();
                String zza2 = zzjs.zza(str2, 24, true);
                if ((obj instanceof String) || (obj instanceof CharSequence)) {
                    i2 = String.valueOf(obj).length();
                }
                this.b.zzz().zza(b, "_ev", zza2, i2);
                return;
            }
            Object c2 = zzz().c(str2, obj);
            if (c2 != null) {
                zza(str3, str2, j, c2);
            }
        } else {
            zza(str3, str2, j, (Object) null);
        }
    }

    public final void zza(boolean z) {
        j();
        zzm();
        zzaa().zza((Runnable) new zzhe(this, z));
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

    public final void zzb(zzgn zzgn) {
        zzm();
        j();
        Preconditions.checkNotNull(zzgn);
        if (!this.zzpw.remove(zzgn)) {
            zzab().zzgn().zzao("OnEventListener had not been registered");
        }
    }

    public final void zzb(String str, String str2, Object obj, boolean z) {
        zza(str, str2, obj, z, zzx().currentTimeMillis());
    }

    public final ArrayList<Bundle> zzd(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzl();
        return zze(str, str2, str3);
    }

    public final void zzd(Bundle bundle) {
        Preconditions.checkNotNull(bundle);
        Preconditions.checkNotEmpty(bundle.getString("app_id"));
        zzl();
        zza(new Bundle(bundle), zzx().currentTimeMillis());
    }

    public final List<zzjn> zzh(boolean z) {
        zzeh zzgn;
        String str;
        zzm();
        j();
        zzab().zzgr().zzao("Fetching user attributes (FE)");
        if (zzaa().zzhp()) {
            zzgn = zzab().zzgk();
            str = "Cannot get all user properties from analytics worker thread";
        } else if (zzr.isMainThread()) {
            zzgn = zzab().zzgk();
            str = "Cannot get all user properties from main thread";
        } else {
            AtomicReference atomicReference = new AtomicReference();
            synchronized (atomicReference) {
                this.b.zzaa().zza((Runnable) new zzgt(this, atomicReference, z));
                try {
                    atomicReference.wait(5000);
                } catch (InterruptedException e) {
                    zzab().zzgn().zza("Interrupted waiting for get user properties", e);
                }
            }
            List<zzjn> list = (List) atomicReference.get();
            if (list != null) {
                return list;
            }
            zzgn = zzab().zzgn();
            str = "Timed out waiting for get user properties";
        }
        zzgn.zzao(str);
        return Collections.emptyList();
    }

    @Nullable
    public final String zzi() {
        zzm();
        return this.zzpy.get();
    }

    public final void zzif() {
        if (getContext().getApplicationContext() instanceof Application) {
            ((Application) getContext().getApplicationContext()).unregisterActivityLifecycleCallbacks(this.a);
        }
    }

    public final Boolean zzig() {
        AtomicReference atomicReference = new AtomicReference();
        return (Boolean) zzaa().a(atomicReference, 15000, "boolean test flag value", new zzgo(this, atomicReference));
    }

    public final String zzih() {
        AtomicReference atomicReference = new AtomicReference();
        return (String) zzaa().a(atomicReference, 15000, "String test flag value", new zzgy(this, atomicReference));
    }

    public final Long zzii() {
        AtomicReference atomicReference = new AtomicReference();
        return (Long) zzaa().a(atomicReference, 15000, "long test flag value", new zzha(this, atomicReference));
    }

    public final Integer zzij() {
        AtomicReference atomicReference = new AtomicReference();
        return (Integer) zzaa().a(atomicReference, 15000, "int test flag value", new zzhd(this, atomicReference));
    }

    public final Double zzik() {
        AtomicReference atomicReference = new AtomicReference();
        return (Double) zzaa().a(atomicReference, 15000, "double test flag value", new zzhc(this, atomicReference));
    }

    @WorkerThread
    public final void zzim() {
        zzo();
        zzm();
        j();
        if (this.b.g()) {
            zzs().e();
            this.c = false;
            String h = zzac().h();
            if (!TextUtils.isEmpty(h)) {
                zzw().l();
                if (!h.equals(Build.VERSION.RELEASE)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("_po", h);
                    logEvent("auto", "_ou", bundle);
                }
            }
        }
    }

    public final /* bridge */ /* synthetic */ void zzl() {
        super.zzl();
    }

    public final /* bridge */ /* synthetic */ void zzm() {
        super.zzm();
    }

    public final ArrayList<Bundle> zzn(String str, String str2) {
        zzm();
        return zze((String) null, str, str2);
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

    @Nullable
    public final String zzy(long j) {
        zzeh zzgk;
        String str;
        if (zzaa().zzhp()) {
            zzgk = zzab().zzgk();
            str = "Cannot retrieve app instance id from analytics worker thread";
        } else if (zzr.isMainThread()) {
            zzgk = zzab().zzgk();
            str = "Cannot retrieve app instance id from main thread";
        } else {
            long elapsedRealtime = zzx().elapsedRealtime();
            String zzz = zzz(120000);
            long elapsedRealtime2 = zzx().elapsedRealtime() - elapsedRealtime;
            return (zzz != null || elapsedRealtime2 >= 120000) ? zzz : zzz(120000 - elapsedRealtime2);
        }
        zzgk.zzao(str);
        return null;
    }

    public final /* bridge */ /* synthetic */ zzjs zzz() {
        return super.zzz();
    }
}
