package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzbk;
import com.google.android.gms.internal.measurement.zzbq;
import com.google.android.gms.internal.measurement.zzbv;
import com.google.android.gms.internal.measurement.zzbw;
import com.google.android.gms.internal.measurement.zzbx;
import com.google.android.gms.internal.measurement.zzey;
import com.google.android.gms.internal.measurement.zzil;
import com.google.android.gms.internal.measurement.zzio;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.util.Map;

public final class zzfd extends zzjh implements zzu {
    @VisibleForTesting
    private static int zznk = 65535;
    @VisibleForTesting
    private static int zznl = 2;
    private final Map<String, Map<String, String>> zznm = new ArrayMap();
    private final Map<String, Map<String, Boolean>> zznn = new ArrayMap();
    private final Map<String, Map<String, Boolean>> zzno = new ArrayMap();
    private final Map<String, zzbw> zznp = new ArrayMap();
    private final Map<String, Map<String, Integer>> zznq = new ArrayMap();
    private final Map<String, String> zznr = new ArrayMap();

    zzfd(zzjg zzjg) {
        super(zzjg);
    }

    @WorkerThread
    private final zzbw zza(String str, byte[] bArr) {
        if (bArr == null) {
            return new zzbw();
        }
        zzil zzj = zzil.zzj(bArr, 0, bArr.length);
        zzbw zzbw = new zzbw();
        try {
            zzbw.zza(zzj);
            zzab().zzgs().zza("Parsed config. version, gmp_app_id", zzbw.zzzk, zzbw.zzcg);
            return zzbw;
        } catch (IOException e) {
            zzab().zzgn().zza("Unable to merge remote config. appId", zzef.a(str), e);
            return new zzbw();
        }
    }

    private static Map<String, String> zza(zzbw zzbw) {
        ArrayMap arrayMap = new ArrayMap();
        if (!(zzbw == null || zzbw.zzzm == null)) {
            for (zzbq.zza zza : zzbw.zzzm) {
                if (zza != null) {
                    arrayMap.put(zza.getKey(), zza.getValue());
                }
            }
        }
        return arrayMap;
    }

    private final void zza(String str, zzbw zzbw) {
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        ArrayMap arrayMap3 = new ArrayMap();
        if (!(zzbw == null || zzbw.zzzn == null)) {
            for (zzbx zzbx : zzbw.zzzn) {
                if (TextUtils.isEmpty(zzbx.name)) {
                    zzab().zzgn().zzao("EventConfig contained null event name");
                } else {
                    String zzbe = zzgj.zzbe(zzbx.name);
                    if (!TextUtils.isEmpty(zzbe)) {
                        zzbx.name = zzbe;
                    }
                    arrayMap.put(zzbx.name, zzbx.zzzs);
                    arrayMap2.put(zzbx.name, zzbx.zzzt);
                    if (zzbx.zzzu != null) {
                        if (zzbx.zzzu.intValue() < zznl || zzbx.zzzu.intValue() > zznk) {
                            zzab().zzgn().zza("Invalid sampling rate. Event name, sample rate", zzbx.name, zzbx.zzzu);
                        } else {
                            arrayMap3.put(zzbx.name, zzbx.zzzu);
                        }
                    }
                }
            }
        }
        this.zznn.put(str, arrayMap);
        this.zzno.put(str, arrayMap2);
        this.zznq.put(str, arrayMap3);
    }

    @WorkerThread
    private final void zzav(String str) {
        c();
        zzo();
        Preconditions.checkNotEmpty(str);
        if (this.zznp.get(str) == null) {
            byte[] zzad = zzgy().zzad(str);
            if (zzad == null) {
                this.zznm.put(str, (Object) null);
                this.zznn.put(str, (Object) null);
                this.zzno.put(str, (Object) null);
                this.zznp.put(str, (Object) null);
                this.zznr.put(str, (Object) null);
                this.zznq.put(str, (Object) null);
                return;
            }
            zzbw zza = zza(str, zzad);
            this.zznm.put(str, zza(zza));
            zza(str, zza);
            this.zznp.put(str, zza);
            this.zznr.put(str, (Object) null);
        }
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final zzbw a(String str) {
        c();
        zzo();
        Preconditions.checkNotEmpty(str);
        zzav(str);
        return this.zznp.get(str);
    }

    /* access modifiers changed from: protected */
    public final boolean a() {
        return false;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean a(String str, String str2) {
        Boolean bool;
        zzo();
        zzav(str);
        if (g(str) && zzjs.e(str2)) {
            return true;
        }
        if (h(str) && zzjs.a(str2)) {
            return true;
        }
        Map map = this.zznn.get(str);
        if (map == null || (bool = (Boolean) map.get(str2)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final boolean a(String str, byte[] bArr, String str2) {
        byte[] bArr2;
        boolean z;
        String str3 = str;
        c();
        zzo();
        Preconditions.checkNotEmpty(str);
        zzbw zza = zza(str, bArr);
        if (zza == null) {
            return false;
        }
        zza(str3, zza);
        this.zznp.put(str3, zza);
        this.zznr.put(str3, str2);
        this.zznm.put(str3, zza(zza));
        zzp zzgx = zzgx();
        zzbv[] zzbvArr = zza.zzzo;
        Preconditions.checkNotNull(zzbvArr);
        for (zzbv zzbv : zzbvArr) {
            if (zzbv.zzzh != null) {
                for (int i = 0; i < zzbv.zzzh.length; i++) {
                    zzbk.zza.C0015zza zza2 = (zzbk.zza.C0015zza) zzbv.zzzh[i].zzuj();
                    zzbk.zza.C0015zza zza3 = (zzbk.zza.C0015zza) ((zzey.zza) zza2.clone());
                    String zzbe = zzgj.zzbe(zza2.zzjz());
                    if (zzbe != null) {
                        zza3.zzbs(zzbe);
                        z = true;
                    } else {
                        z = false;
                    }
                    boolean z2 = z;
                    for (int i2 = 0; i2 < zza2.zzka(); i2++) {
                        zzbk.zzb zze = zza2.zze(i2);
                        String zzbe2 = zzgi.zzbe(zze.zzkr());
                        if (zzbe2 != null) {
                            zza3.zza(i2, (zzbk.zzb) ((zzey) ((zzbk.zzb.zza) zze.zzuj()).zzbu(zzbe2).zzug()));
                            z2 = true;
                        }
                    }
                    if (z2) {
                        zzbv.zzzh[i] = (zzbk.zza) ((zzey) zza3.zzug());
                    }
                }
            }
            if (zzbv.zzzg != null) {
                for (int i3 = 0; i3 < zzbv.zzzg.length; i3++) {
                    zzbk.zzd zzd = zzbv.zzzg[i3];
                    String zzbe3 = zzgl.zzbe(zzd.getPropertyName());
                    if (zzbe3 != null) {
                        zzbv.zzzg[i3] = (zzbk.zzd) ((zzey) ((zzbk.zzd.zza) zzd.zzuj()).zzbw(zzbe3).zzug());
                    }
                }
            }
        }
        zzgx.zzgy().a(str3, zzbvArr);
        try {
            zza.zzzo = null;
            bArr2 = new byte[zza.zzuk()];
            zza.zza(zzio.zzk(bArr2, 0, bArr2.length));
        } catch (IOException e) {
            zzab().zzgn().zza("Unable to serialize reduced-size config. Storing full config instead. appId", zzef.a(str), e);
            bArr2 = bArr;
        }
        zzx zzgy = zzgy();
        Preconditions.checkNotEmpty(str);
        zzgy.zzo();
        zzgy.c();
        ContentValues contentValues = new ContentValues();
        contentValues.put("remote_config", bArr2);
        try {
            if (((long) zzgy.d().update("apps", contentValues, "app_id = ?", new String[]{str3})) == 0) {
                zzgy.zzab().zzgk().zza("Failed to update remote config (got 0). appId", zzef.a(str));
            }
        } catch (SQLiteException e2) {
            zzgy.zzab().zzgk().zza("Error storing remote config. appId", zzef.a(str), e2);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final String b(String str) {
        zzo();
        return this.zznr.get(str);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean b(String str, String str2) {
        Boolean bool;
        zzo();
        zzav(str);
        if (FirebaseAnalytics.Event.ECOMMERCE_PURCHASE.equals(str2)) {
            return true;
        }
        Map map = this.zzno.get(str);
        if (map == null || (bool = (Boolean) map.get(str2)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final int c(String str, String str2) {
        Integer num;
        zzo();
        zzav(str);
        Map map = this.zznq.get(str);
        if (map == null || (num = (Integer) map.get(str2)) == null) {
            return 1;
        }
        return num.intValue();
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void c(String str) {
        zzo();
        this.zznr.put(str, (Object) null);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void d(String str) {
        zzo();
        this.zznp.remove(str);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean e(String str) {
        Boolean bool;
        zzo();
        zzbw a = a(str);
        if (a == null || (bool = a.zzzq) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final long f(String str) {
        String zzb = zzb(str, "measurement.account.time_zone_offset_minutes");
        if (TextUtils.isEmpty(zzb)) {
            return 0;
        }
        try {
            return Long.parseLong(zzb);
        } catch (NumberFormatException e) {
            zzab().zzgn().zza("Unable to parse timezone offset. appId", zzef.a(str), e);
            return 0;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean g(String str) {
        return AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(zzb(str, "measurement.upload.blacklist_internal"));
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    /* access modifiers changed from: package-private */
    public final boolean h(String str) {
        return AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(zzb(str, "measurement.upload.blacklist_public"));
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

    @WorkerThread
    public final String zzb(String str, String str2) {
        zzo();
        zzav(str);
        Map map = this.zznm.get(str);
        if (map != null) {
            return (String) map.get(str2);
        }
        return null;
    }

    public final /* bridge */ /* synthetic */ zzjo zzgw() {
        return super.zzgw();
    }

    public final /* bridge */ /* synthetic */ zzp zzgx() {
        return super.zzgx();
    }

    public final /* bridge */ /* synthetic */ zzx zzgy() {
        return super.zzgy();
    }

    public final /* bridge */ /* synthetic */ zzfd zzgz() {
        return super.zzgz();
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
