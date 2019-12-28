package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import com.facebook.appevents.codeless.internal.Constants;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzbs;
import com.google.android.gms.internal.measurement.zzbw;
import com.google.android.gms.internal.measurement.zzey;
import com.google.android.gms.internal.measurement.zzx;
import com.google.common.net.HttpHeaders;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zzjg implements zzgh {
    private static volatile zzjg zzsn;
    private boolean zzdh;
    private final zzfj zzj;
    private zzfd zzso;
    private zzej zzsp;
    private zzx zzsq;
    private zzem zzsr;
    private zzjc zzss;
    private zzp zzst;
    private final zzjo zzsu;
    private zzhp zzsv;
    private boolean zzsw;
    private boolean zzsx;
    @VisibleForTesting
    private long zzsy;
    private List<Runnable> zzsz;
    private int zzta;
    private int zztb;
    private boolean zztc;
    private boolean zztd;
    private boolean zzte;
    private FileLock zztf;
    private FileChannel zztg;
    private List<Long> zzth;
    private List<Long> zzti;
    private long zztj;

    class zza implements zzz {
        zzbs.zzg a;
        List<Long> b;
        List<zzbs.zzc> c;
        private long zztq;

        private zza() {
        }

        /* synthetic */ zza(zzjg zzjg, zzjj zzjj) {
            this();
        }

        private static long zza(zzbs.zzc zzc) {
            return ((zzc.getTimestampMillis() / 1000) / 60) / 60;
        }

        public final boolean zza(long j, zzbs.zzc zzc) {
            Preconditions.checkNotNull(zzc);
            if (this.c == null) {
                this.c = new ArrayList();
            }
            if (this.b == null) {
                this.b = new ArrayList();
            }
            if (this.c.size() > 0 && zza(this.c.get(0)) != zza(zzc)) {
                return false;
            }
            long zzuk = this.zztq + ((long) zzc.zzuk());
            if (zzuk >= ((long) Math.max(0, zzak.zzgn.get(null).intValue()))) {
                return false;
            }
            this.zztq = zzuk;
            this.c.add(zzc);
            this.b.add(Long.valueOf(j));
            return this.c.size() < Math.max(1, zzak.zzgo.get(null).intValue());
        }

        public final void zzb(zzbs.zzg zzg) {
            Preconditions.checkNotNull(zzg);
            this.a = zzg;
        }
    }

    private zzjg(zzjm zzjm) {
        this(zzjm, (zzfj) null);
    }

    private zzjg(zzjm zzjm, zzfj zzfj) {
        this.zzdh = false;
        Preconditions.checkNotNull(zzjm);
        this.zzj = zzfj.zza(zzjm.a, (zzx) null);
        this.zztj = -1;
        zzjo zzjo = new zzjo(this);
        zzjo.initialize();
        this.zzsu = zzjo;
        zzej zzej = new zzej(this);
        zzej.initialize();
        this.zzsp = zzej;
        zzfd zzfd = new zzfd(this);
        zzfd.initialize();
        this.zzso = zzfd;
        this.zzj.zzaa().zza((Runnable) new zzjj(this, zzjm));
    }

    @WorkerThread
    @VisibleForTesting
    private final int zza(FileChannel fileChannel) {
        zzo();
        if (fileChannel == null || !fileChannel.isOpen()) {
            this.zzj.zzab().zzgk().zzao("Bad channel to read from");
            return 0;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        try {
            fileChannel.position(0);
            int read = fileChannel.read(allocate);
            if (read != 4) {
                if (read != -1) {
                    this.zzj.zzab().zzgn().zza("Unexpected data length. Bytes read", Integer.valueOf(read));
                }
                return 0;
            }
            allocate.flip();
            return allocate.getInt();
        } catch (IOException e) {
            this.zzj.zzab().zzgk().zza("Failed to read from channel", e);
            return 0;
        }
    }

    private final zzn zza(Context context, String str, String str2, boolean z, boolean z2, boolean z3, long j, String str3) {
        String str4;
        int i;
        String str5 = str;
        String str6 = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            this.zzj.zzab().zzgk().zzao("PackageManager is null, can not log app install information");
            return null;
        }
        try {
            str6 = packageManager.getInstallerPackageName(str5);
        } catch (IllegalArgumentException unused) {
            this.zzj.zzab().zzgk().zza("Error retrieving installer package name. appId", zzef.a(str));
        }
        if (str6 == null) {
            str6 = "manual_install";
        } else if ("com.android.vending".equals(str6)) {
            str6 = "";
        }
        String str7 = str6;
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(str5, 0);
            if (packageInfo != null) {
                CharSequence applicationLabel = Wrappers.packageManager(context).getApplicationLabel(str5);
                if (!TextUtils.isEmpty(applicationLabel)) {
                    String charSequence = applicationLabel.toString();
                }
                str4 = packageInfo.versionName;
                i = packageInfo.versionCode;
            } else {
                str4 = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
                i = Integer.MIN_VALUE;
            }
            this.zzj.zzae();
            return new zzn(str, str2, str4, (long) i, str7, this.zzj.zzad().zzao(), this.zzj.zzz().a(context, str5), (String) null, z, false, "", 0, this.zzj.zzad().g(str5) ? j : 0, 0, z2, z3, false, str3, (Boolean) null, 0, (List<String>) null);
        } catch (PackageManager.NameNotFoundException unused2) {
            this.zzj.zzab().zzgk().zza("Error retrieving newly installed package info. appId, appName", zzef.a(str), AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN);
            return null;
        }
    }

    @VisibleForTesting
    private static void zza(zzbs.zzc.zza zza2, int i, String str) {
        List<zzbs.zze> zzmj = zza2.zzmj();
        int i2 = 0;
        while (i2 < zzmj.size()) {
            if (!"_err".equals(zzmj.get(i2).getName())) {
                i2++;
            } else {
                return;
            }
        }
        zza2.zza((zzbs.zze) ((zzey) zzbs.zze.zzng().zzbz("_err").zzam(Long.valueOf((long) i).longValue()).zzug())).zza((zzbs.zze) ((zzey) zzbs.zze.zzng().zzbz("_ev").zzca(str).zzug()));
    }

    @VisibleForTesting
    private static void zza(zzbs.zzc.zza zza2, @NonNull String str) {
        List<zzbs.zze> zzmj = zza2.zzmj();
        for (int i = 0; i < zzmj.size(); i++) {
            if (str.equals(zzmj.get(i).getName())) {
                zza2.zzm(i);
                return;
            }
        }
    }

    @VisibleForTesting
    private final void zza(zzbs.zzg.zza zza2, long j, boolean z) {
        zzjp zzjp;
        String str = "_lte";
        if (z) {
            str = "_se";
        }
        zzjp zze = zzgy().zze(zza2.zzag(), str);
        if (zze == null || zze.e == null) {
            zzjp = new zzjp(zza2.zzag(), "auto", str, this.zzj.zzx().currentTimeMillis(), Long.valueOf(j));
        } else {
            zzjp = new zzjp(zza2.zzag(), "auto", str, this.zzj.zzx().currentTimeMillis(), Long.valueOf(((Long) zze.e).longValue() + j));
        }
        zzbs.zzk zzk = (zzbs.zzk) ((zzey) zzbs.zzk.zzqu().zzdb(str).zzbk(this.zzj.zzx().currentTimeMillis()).zzbl(((Long) zzjp.e).longValue()).zzug());
        boolean z2 = false;
        int i = 0;
        while (true) {
            if (i >= zza2.zznp()) {
                break;
            } else if (str.equals(zza2.zzs(i).getName())) {
                zza2.zza(i, zzk);
                z2 = true;
                break;
            } else {
                i++;
            }
        }
        if (!z2) {
            zza2.zza(zzk);
        }
        if (j > 0) {
            zzgy().zza(zzjp);
            String str2 = "lifetime";
            if (z) {
                str2 = "session-scoped";
            }
            this.zzj.zzab().zzgr().zza("Updated engagement user property. scope, value", str2, zzjp.e);
        }
    }

    private static void zza(zzjh zzjh) {
        if (zzjh == null) {
            throw new IllegalStateException("Upload Component not created");
        } else if (!zzjh.b()) {
            String valueOf = String.valueOf(zzjh.getClass());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
            sb.append("Component not initialized: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zza(zzjm zzjm) {
        this.zzj.zzaa().zzo();
        zzx zzx = new zzx(this);
        zzx.initialize();
        this.zzsq = zzx;
        this.zzj.zzad().a((zzu) this.zzso);
        zzp zzp = new zzp(this);
        zzp.initialize();
        this.zzst = zzp;
        zzhp zzhp = new zzhp(this);
        zzhp.initialize();
        this.zzsv = zzhp;
        zzjc zzjc = new zzjc(this);
        zzjc.initialize();
        this.zzss = zzjc;
        this.zzsr = new zzem(this);
        if (this.zzta != this.zztb) {
            this.zzj.zzab().zzgk().zza("Not all upload components initialized", Integer.valueOf(this.zzta), Integer.valueOf(this.zztb));
        }
        this.zzdh = true;
    }

    @WorkerThread
    @VisibleForTesting
    private final boolean zza(int i, FileChannel fileChannel) {
        zzo();
        if (fileChannel == null || !fileChannel.isOpen()) {
            this.zzj.zzab().zzgk().zzao("Bad channel to read from");
            return false;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(i);
        allocate.flip();
        try {
            fileChannel.truncate(0);
            fileChannel.write(allocate);
            fileChannel.force(true);
            if (fileChannel.size() != 4) {
                this.zzj.zzab().zzgk().zza("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
            }
            return true;
        } catch (IOException e) {
            this.zzj.zzab().zzgk().zza("Failed to write to channel", e);
            return false;
        }
    }

    private final boolean zza(zzbs.zzc.zza zza2, zzbs.zzc.zza zza3) {
        Preconditions.checkArgument("_e".equals(zza2.getName()));
        zzgw();
        zzbs.zze a = zzjo.a((zzbs.zzc) ((zzey) zza2.zzug()), "_sc");
        String str = null;
        String zzmy = a == null ? null : a.zzmy();
        zzgw();
        zzbs.zze a2 = zzjo.a((zzbs.zzc) ((zzey) zza3.zzug()), "_pc");
        if (a2 != null) {
            str = a2.zzmy();
        }
        if (str == null || !str.equals(zzmy)) {
            return false;
        }
        zzgw();
        zzbs.zze a3 = zzjo.a((zzbs.zzc) ((zzey) zza2.zzug()), "_et");
        if (a3.zzna() && a3.zznb() > 0) {
            long zznb = a3.zznb();
            zzgw();
            zzbs.zze a4 = zzjo.a((zzbs.zzc) ((zzey) zza3.zzug()), "_et");
            if (a4 != null && a4.zznb() > 0) {
                zznb += a4.zznb();
            }
            zzgw();
            zzjo.a(zza3, "_et", Long.valueOf(zznb));
            zzgw();
            zzjo.a(zza2, "_fr", 1L);
        }
        return true;
    }

    @WorkerThread
    private final void zzb(zzf zzf) {
        zzo();
        if (!TextUtils.isEmpty(zzf.getGmpAppId()) || (zzs.c() && !TextUtils.isEmpty(zzf.zzah()))) {
            zzs zzad = this.zzj.zzad();
            Uri.Builder builder = new Uri.Builder();
            String gmpAppId = zzf.getGmpAppId();
            if (TextUtils.isEmpty(gmpAppId) && zzs.c()) {
                gmpAppId = zzf.zzah();
            }
            ArrayMap arrayMap = null;
            Uri.Builder encodedAuthority = builder.scheme(zzak.zzgj.get(null)).encodedAuthority(zzak.zzgk.get(null));
            String valueOf = String.valueOf(gmpAppId);
            encodedAuthority.path(valueOf.length() != 0 ? "config/app/".concat(valueOf) : new String("config/app/")).appendQueryParameter("app_instance_id", zzf.getAppInstanceId()).appendQueryParameter("platform", Constants.PLATFORM).appendQueryParameter("gmp_version", String.valueOf(zzad.zzao()));
            String uri = builder.build().toString();
            try {
                URL url = new URL(uri);
                this.zzj.zzab().zzgs().zza("Fetching remote configuration", zzf.zzag());
                zzbw a = zzgz().a(zzf.zzag());
                String b = zzgz().b(zzf.zzag());
                if (a != null && !TextUtils.isEmpty(b)) {
                    arrayMap = new ArrayMap();
                    arrayMap.put(HttpHeaders.IF_MODIFIED_SINCE, b);
                }
                this.zztc = true;
                zzej zzjf = zzjf();
                String zzag = zzf.zzag();
                zzjl zzjl = new zzjl(this);
                zzjf.zzo();
                zzjf.c();
                Preconditions.checkNotNull(url);
                Preconditions.checkNotNull(zzjl);
                zzjf.zzaa().zzb((Runnable) new zzen(zzjf, zzag, url, (byte[]) null, arrayMap, zzjl));
            } catch (MalformedURLException unused) {
                this.zzj.zzab().zzgk().zza("Failed to parse config URL. Not fetching. appId", zzef.a(zzf.zzag()), uri);
            }
        } else {
            a(zzf.zzag(), 204, (Throwable) null, (byte[]) null, (Map<String, List<String>>) null);
        }
    }

    @WorkerThread
    private final zzn zzbi(String str) {
        zzeh zzgr;
        String str2;
        Object obj;
        String str3 = str;
        zzf zzab = zzgy().zzab(str3);
        if (zzab == null || TextUtils.isEmpty(zzab.zzal())) {
            zzgr = this.zzj.zzab().zzgr();
            str2 = "No app data available; dropping";
            obj = str3;
        } else {
            Boolean zzc = zzc(zzab);
            if (zzc == null || zzc.booleanValue()) {
                zzf zzf = zzab;
                return new zzn(str, zzab.getGmpAppId(), zzab.zzal(), zzab.zzam(), zzab.zzan(), zzab.zzao(), zzab.zzap(), (String) null, zzab.isMeasurementEnabled(), false, zzab.getFirebaseInstanceId(), zzf.zzbd(), 0, 0, zzf.zzbe(), zzf.zzbf(), false, zzf.zzah(), zzf.zzbg(), zzf.zzaq(), zzf.zzbh());
            }
            zzgr = this.zzj.zzab().zzgk();
            str2 = "App version does not match; dropping. appId";
            obj = zzef.a(str);
        }
        zzgr.zza(str2, obj);
        return null;
    }

    @WorkerThread
    private final Boolean zzc(zzf zzf) {
        try {
            if (zzf.zzam() != -2147483648L) {
                if (zzf.zzam() == ((long) Wrappers.packageManager(this.zzj.getContext()).getPackageInfo(zzf.zzag(), 0).versionCode)) {
                    return true;
                }
            } else {
                String str = Wrappers.packageManager(this.zzj.getContext()).getPackageInfo(zzf.zzag(), 0).versionName;
                if (zzf.zzal() != null && zzf.zzal().equals(str)) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:181:0x05f4 A[Catch:{ IOException -> 0x0858, all -> 0x08ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:191:0x0633 A[Catch:{ IOException -> 0x0858, all -> 0x08ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:196:0x064e A[Catch:{ IOException -> 0x0858, all -> 0x08ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:213:0x0708 A[Catch:{ IOException -> 0x0858, all -> 0x08ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:216:0x0717 A[Catch:{ IOException -> 0x0858, all -> 0x08ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:219:0x0788 A[Catch:{ IOException -> 0x0858, all -> 0x08ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:222:0x0799 A[Catch:{ IOException -> 0x0858, all -> 0x08ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:226:0x07b1 A[Catch:{ IOException -> 0x0858, all -> 0x08ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:233:0x07fa A[Catch:{ IOException -> 0x0858, all -> 0x08ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:247:0x0855 A[Catch:{ IOException -> 0x0858, all -> 0x08ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:252:0x0885 A[Catch:{ IOException -> 0x0858, all -> 0x08ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x027e A[Catch:{ IOException -> 0x0858, all -> 0x08ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x02b6 A[Catch:{ IOException -> 0x0858, all -> 0x08ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0305 A[Catch:{ IOException -> 0x0858, all -> 0x08ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0332  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzd(com.google.android.gms.measurement.internal.zzai r25, com.google.android.gms.measurement.internal.zzn r26) {
        /*
            r24 = this;
            r1 = r24
            r2 = r25
            r3 = r26
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r26)
            java.lang.String r4 = r3.packageName
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r4)
            long r4 = java.lang.System.nanoTime()
            r24.zzo()
            r24.b()
            java.lang.String r15 = r3.packageName
            com.google.android.gms.measurement.internal.zzjo r6 = r24.zzgw()
            boolean r6 = r6.a((com.google.android.gms.measurement.internal.zzai) r2, (com.google.android.gms.measurement.internal.zzn) r3)
            if (r6 != 0) goto L_0x0025
            return
        L_0x0025:
            boolean r6 = r3.zzcq
            if (r6 != 0) goto L_0x002d
            r1.zzg(r3)
            return
        L_0x002d:
            com.google.android.gms.measurement.internal.zzfd r6 = r24.zzgz()
            java.lang.String r7 = r2.name
            boolean r6 = r6.a(r15, r7)
            r14 = 0
            r13 = 0
            r12 = 1
            if (r6 == 0) goto L_0x00d7
            com.google.android.gms.measurement.internal.zzfj r3 = r1.zzj
            com.google.android.gms.measurement.internal.zzef r3 = r3.zzab()
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzgn()
            java.lang.String r4 = "Dropping blacklisted event. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r15)
            com.google.android.gms.measurement.internal.zzfj r6 = r1.zzj
            com.google.android.gms.measurement.internal.zzed r6 = r6.zzy()
            java.lang.String r7 = r2.name
            java.lang.String r6 = r6.a((java.lang.String) r7)
            r3.zza(r4, r5, r6)
            com.google.android.gms.measurement.internal.zzfd r3 = r24.zzgz()
            boolean r3 = r3.g(r15)
            if (r3 != 0) goto L_0x006f
            com.google.android.gms.measurement.internal.zzfd r3 = r24.zzgz()
            boolean r3 = r3.h(r15)
            if (r3 == 0) goto L_0x0070
        L_0x006f:
            r13 = 1
        L_0x0070:
            if (r13 != 0) goto L_0x008d
            java.lang.String r3 = "_err"
            java.lang.String r4 = r2.name
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x008d
            com.google.android.gms.measurement.internal.zzfj r3 = r1.zzj
            com.google.android.gms.measurement.internal.zzjs r6 = r3.zzz()
            r8 = 11
            java.lang.String r9 = "_ev"
            java.lang.String r10 = r2.name
            r11 = 0
            r7 = r15
            r6.a(r7, r8, r9, r10, r11)
        L_0x008d:
            if (r13 == 0) goto L_0x00d6
            com.google.android.gms.measurement.internal.zzx r2 = r24.zzgy()
            com.google.android.gms.measurement.internal.zzf r2 = r2.zzab(r15)
            if (r2 == 0) goto L_0x00d6
            long r3 = r2.zzat()
            long r5 = r2.zzas()
            long r3 = java.lang.Math.max(r3, r5)
            com.google.android.gms.measurement.internal.zzfj r5 = r1.zzj
            com.google.android.gms.common.util.Clock r5 = r5.zzx()
            long r5 = r5.currentTimeMillis()
            long r5 = r5 - r3
            long r3 = java.lang.Math.abs(r5)
            com.google.android.gms.measurement.internal.zzdu<java.lang.Long> r5 = com.google.android.gms.measurement.internal.zzak.zzhe
            java.lang.Object r5 = r5.get(r14)
            java.lang.Long r5 = (java.lang.Long) r5
            long r5 = r5.longValue()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x00d6
            com.google.android.gms.measurement.internal.zzfj r3 = r1.zzj
            com.google.android.gms.measurement.internal.zzef r3 = r3.zzab()
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzgr()
            java.lang.String r4 = "Fetching config for blacklisted app"
            r3.zzao(r4)
            r1.zzb(r2)
        L_0x00d6:
            return
        L_0x00d7:
            com.google.android.gms.measurement.internal.zzfj r6 = r1.zzj
            com.google.android.gms.measurement.internal.zzef r6 = r6.zzab()
            r10 = 2
            boolean r6 = r6.a((int) r10)
            if (r6 == 0) goto L_0x00fd
            com.google.android.gms.measurement.internal.zzfj r6 = r1.zzj
            com.google.android.gms.measurement.internal.zzef r6 = r6.zzab()
            com.google.android.gms.measurement.internal.zzeh r6 = r6.zzgs()
            java.lang.String r7 = "Logging event"
            com.google.android.gms.measurement.internal.zzfj r8 = r1.zzj
            com.google.android.gms.measurement.internal.zzed r8 = r8.zzy()
            java.lang.String r8 = r8.a((com.google.android.gms.measurement.internal.zzai) r2)
            r6.zza(r7, r8)
        L_0x00fd:
            com.google.android.gms.measurement.internal.zzx r6 = r24.zzgy()
            r6.beginTransaction()
            r1.zzg(r3)     // Catch:{ all -> 0x08ca }
            java.lang.String r6 = "_iap"
            java.lang.String r7 = r2.name     // Catch:{ all -> 0x08ca }
            boolean r6 = r6.equals(r7)     // Catch:{ all -> 0x08ca }
            if (r6 != 0) goto L_0x0122
            java.lang.String r6 = "ecommerce_purchase"
            java.lang.String r7 = r2.name     // Catch:{ all -> 0x08ca }
            boolean r6 = r6.equals(r7)     // Catch:{ all -> 0x08ca }
            if (r6 == 0) goto L_0x011c
            goto L_0x0122
        L_0x011c:
            r16 = 1
            r18 = 2
            goto L_0x02c5
        L_0x0122:
            com.google.android.gms.measurement.internal.zzah r6 = r2.zzfq     // Catch:{ all -> 0x08ca }
            java.lang.String r7 = "currency"
            java.lang.String r6 = r6.d(r7)     // Catch:{ all -> 0x08ca }
            java.lang.String r7 = "ecommerce_purchase"
            java.lang.String r8 = r2.name     // Catch:{ all -> 0x08ca }
            boolean r7 = r7.equals(r8)     // Catch:{ all -> 0x08ca }
            if (r7 == 0) goto L_0x018b
            com.google.android.gms.measurement.internal.zzah r7 = r2.zzfq     // Catch:{ all -> 0x08ca }
            java.lang.String r8 = "value"
            java.lang.Double r7 = r7.c(r8)     // Catch:{ all -> 0x08ca }
            double r7 = r7.doubleValue()     // Catch:{ all -> 0x08ca }
            r16 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            double r7 = r7 * r16
            r18 = 0
            int r9 = (r7 > r18 ? 1 : (r7 == r18 ? 0 : -1))
            if (r9 != 0) goto L_0x015c
            com.google.android.gms.measurement.internal.zzah r7 = r2.zzfq     // Catch:{ all -> 0x08ca }
            java.lang.String r8 = "value"
            java.lang.Long r7 = r7.b(r8)     // Catch:{ all -> 0x08ca }
            long r7 = r7.longValue()     // Catch:{ all -> 0x08ca }
            double r7 = (double) r7     // Catch:{ all -> 0x08ca }
            double r7 = r7 * r16
        L_0x015c:
            r16 = 4890909195324358656(0x43e0000000000000, double:9.223372036854776E18)
            int r9 = (r7 > r16 ? 1 : (r7 == r16 ? 0 : -1))
            if (r9 > 0) goto L_0x016d
            r16 = -4332462841530417152(0xc3e0000000000000, double:-9.223372036854776E18)
            int r9 = (r7 > r16 ? 1 : (r7 == r16 ? 0 : -1))
            if (r9 < 0) goto L_0x016d
            long r7 = java.lang.Math.round(r7)     // Catch:{ all -> 0x08ca }
            goto L_0x0197
        L_0x016d:
            com.google.android.gms.measurement.internal.zzfj r6 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzef r6 = r6.zzab()     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzeh r6 = r6.zzgn()     // Catch:{ all -> 0x08ca }
            java.lang.String r9 = "Data lost. Currency value is too big. appId"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r15)     // Catch:{ all -> 0x08ca }
            java.lang.Double r7 = java.lang.Double.valueOf(r7)     // Catch:{ all -> 0x08ca }
            r6.zza(r9, r11, r7)     // Catch:{ all -> 0x08ca }
            r6 = 0
            r16 = 1
            r18 = 2
            goto L_0x02b4
        L_0x018b:
            com.google.android.gms.measurement.internal.zzah r7 = r2.zzfq     // Catch:{ all -> 0x08ca }
            java.lang.String r8 = "value"
            java.lang.Long r7 = r7.b(r8)     // Catch:{ all -> 0x08ca }
            long r7 = r7.longValue()     // Catch:{ all -> 0x08ca }
        L_0x0197:
            boolean r9 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x08ca }
            if (r9 != 0) goto L_0x02af
            java.util.Locale r9 = java.util.Locale.US     // Catch:{ all -> 0x08ca }
            java.lang.String r6 = r6.toUpperCase(r9)     // Catch:{ all -> 0x08ca }
            java.lang.String r9 = "[A-Z]{3}"
            boolean r9 = r6.matches(r9)     // Catch:{ all -> 0x08ca }
            if (r9 == 0) goto L_0x02af
            java.lang.String r9 = "_ltv_"
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ all -> 0x08ca }
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x08ca }
            int r11 = r6.length()     // Catch:{ all -> 0x08ca }
            if (r11 == 0) goto L_0x01c0
            java.lang.String r6 = r9.concat(r6)     // Catch:{ all -> 0x08ca }
            goto L_0x01c5
        L_0x01c0:
            java.lang.String r6 = new java.lang.String     // Catch:{ all -> 0x08ca }
            r6.<init>(r9)     // Catch:{ all -> 0x08ca }
        L_0x01c5:
            r9 = r6
            com.google.android.gms.measurement.internal.zzx r6 = r24.zzgy()     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzjp r6 = r6.zze(r15, r9)     // Catch:{ all -> 0x08ca }
            if (r6 == 0) goto L_0x0208
            java.lang.Object r11 = r6.e     // Catch:{ all -> 0x08ca }
            boolean r11 = r11 instanceof java.lang.Long     // Catch:{ all -> 0x08ca }
            if (r11 != 0) goto L_0x01d7
            goto L_0x0208
        L_0x01d7:
            java.lang.Object r6 = r6.e     // Catch:{ all -> 0x08ca }
            java.lang.Long r6 = (java.lang.Long) r6     // Catch:{ all -> 0x08ca }
            long r16 = r6.longValue()     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzjp r18 = new com.google.android.gms.measurement.internal.zzjp     // Catch:{ all -> 0x08ca }
            java.lang.String r11 = r2.origin     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzfj r6 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.common.util.Clock r6 = r6.zzx()     // Catch:{ all -> 0x08ca }
            long r19 = r6.currentTimeMillis()     // Catch:{ all -> 0x08ca }
            long r16 = r16 + r7
            java.lang.Long r16 = java.lang.Long.valueOf(r16)     // Catch:{ all -> 0x08ca }
            r6 = r18
            r7 = r15
            r8 = r11
            r14 = 2
            r10 = r19
            r14 = 1
            r12 = r16
            r6.<init>(r7, r8, r9, r10, r12)     // Catch:{ all -> 0x08ca }
            r14 = r18
            r16 = 1
            r18 = 2
            goto L_0x0274
        L_0x0208:
            r14 = 1
            com.google.android.gms.measurement.internal.zzx r6 = r24.zzgy()     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzfj r10 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzs r10 = r10.zzad()     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzdu<java.lang.Integer> r11 = com.google.android.gms.measurement.internal.zzak.zzhj     // Catch:{ all -> 0x08ca }
            int r10 = r10.zzb(r15, r11)     // Catch:{ all -> 0x08ca }
            int r10 = r10 - r14
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r15)     // Catch:{ all -> 0x08ca }
            r6.zzo()     // Catch:{ all -> 0x08ca }
            r6.c()     // Catch:{ all -> 0x08ca }
            android.database.sqlite.SQLiteDatabase r11 = r6.d()     // Catch:{ SQLiteException -> 0x0242 }
            java.lang.String r12 = "delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);"
            r14 = 3
            java.lang.String[] r14 = new java.lang.String[r14]     // Catch:{ SQLiteException -> 0x0242 }
            r14[r13] = r15     // Catch:{ SQLiteException -> 0x0242 }
            r16 = 1
            r14[r16] = r15     // Catch:{ SQLiteException -> 0x0240 }
            java.lang.String r10 = java.lang.String.valueOf(r10)     // Catch:{ SQLiteException -> 0x0240 }
            r18 = 2
            r14[r18] = r10     // Catch:{ SQLiteException -> 0x023e }
            r11.execSQL(r12, r14)     // Catch:{ SQLiteException -> 0x023e }
            goto L_0x0259
        L_0x023e:
            r0 = move-exception
            goto L_0x0247
        L_0x0240:
            r0 = move-exception
            goto L_0x0245
        L_0x0242:
            r0 = move-exception
            r16 = 1
        L_0x0245:
            r18 = 2
        L_0x0247:
            r10 = r0
            com.google.android.gms.measurement.internal.zzef r6 = r6.zzab()     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzeh r6 = r6.zzgk()     // Catch:{ all -> 0x08ca }
            java.lang.String r11 = "Error pruning currencies. appId"
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r15)     // Catch:{ all -> 0x08ca }
            r6.zza(r11, r12, r10)     // Catch:{ all -> 0x08ca }
        L_0x0259:
            com.google.android.gms.measurement.internal.zzjp r14 = new com.google.android.gms.measurement.internal.zzjp     // Catch:{ all -> 0x08ca }
            java.lang.String r10 = r2.origin     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzfj r6 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.common.util.Clock r6 = r6.zzx()     // Catch:{ all -> 0x08ca }
            long r11 = r6.currentTimeMillis()     // Catch:{ all -> 0x08ca }
            java.lang.Long r19 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x08ca }
            r6 = r14
            r7 = r15
            r8 = r10
            r10 = r11
            r12 = r19
            r6.<init>(r7, r8, r9, r10, r12)     // Catch:{ all -> 0x08ca }
        L_0x0274:
            com.google.android.gms.measurement.internal.zzx r6 = r24.zzgy()     // Catch:{ all -> 0x08ca }
            boolean r6 = r6.zza((com.google.android.gms.measurement.internal.zzjp) r14)     // Catch:{ all -> 0x08ca }
            if (r6 != 0) goto L_0x02b3
            com.google.android.gms.measurement.internal.zzfj r6 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzef r6 = r6.zzab()     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzeh r6 = r6.zzgk()     // Catch:{ all -> 0x08ca }
            java.lang.String r7 = "Too many unique user properties are set. Ignoring user property. appId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r15)     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzfj r9 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzed r9 = r9.zzy()     // Catch:{ all -> 0x08ca }
            java.lang.String r10 = r14.c     // Catch:{ all -> 0x08ca }
            java.lang.String r9 = r9.c(r10)     // Catch:{ all -> 0x08ca }
            java.lang.Object r10 = r14.e     // Catch:{ all -> 0x08ca }
            r6.zza(r7, r8, r9, r10)     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzfj r6 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzjs r6 = r6.zzz()     // Catch:{ all -> 0x08ca }
            r8 = 9
            r9 = 0
            r10 = 0
            r11 = 0
            r7 = r15
            r6.a(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x08ca }
            goto L_0x02b3
        L_0x02af:
            r16 = 1
            r18 = 2
        L_0x02b3:
            r6 = 1
        L_0x02b4:
            if (r6 != 0) goto L_0x02c5
            com.google.android.gms.measurement.internal.zzx r2 = r24.zzgy()     // Catch:{ all -> 0x08ca }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzx r2 = r24.zzgy()
            r2.endTransaction()
            return
        L_0x02c5:
            java.lang.String r6 = r2.name     // Catch:{ all -> 0x08ca }
            boolean r19 = com.google.android.gms.measurement.internal.zzjs.a((java.lang.String) r6)     // Catch:{ all -> 0x08ca }
            java.lang.String r6 = "_err"
            java.lang.String r7 = r2.name     // Catch:{ all -> 0x08ca }
            boolean r20 = r6.equals(r7)     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzx r6 = r24.zzgy()     // Catch:{ all -> 0x08ca }
            long r7 = r24.zzjk()     // Catch:{ all -> 0x08ca }
            r10 = 1
            r12 = 0
            r14 = 0
            r9 = r15
            r11 = r19
            r13 = r20
            r22 = r4
            r4 = 1
            r5 = 0
            com.google.android.gms.measurement.internal.zzw r6 = r6.zza(r7, r9, r10, r11, r12, r13, r14)     // Catch:{ all -> 0x08ca }
            long r7 = r6.b     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzdu<java.lang.Integer> r9 = com.google.android.gms.measurement.internal.zzak.zzgp     // Catch:{ all -> 0x08ca }
            java.lang.Object r9 = r9.get(r5)     // Catch:{ all -> 0x08ca }
            java.lang.Integer r9 = (java.lang.Integer) r9     // Catch:{ all -> 0x08ca }
            int r9 = r9.intValue()     // Catch:{ all -> 0x08ca }
            long r9 = (long) r9     // Catch:{ all -> 0x08ca }
            long r7 = r7 - r9
            r9 = 1000(0x3e8, double:4.94E-321)
            r11 = 1
            r13 = 0
            int r16 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r16 <= 0) goto L_0x0332
            long r7 = r7 % r9
            int r2 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r2 != 0) goto L_0x0323
            com.google.android.gms.measurement.internal.zzfj r2 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzef r2 = r2.zzab()     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzgk()     // Catch:{ all -> 0x08ca }
            java.lang.String r3 = "Data loss. Too many events logged. appId, count"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r15)     // Catch:{ all -> 0x08ca }
            long r5 = r6.b     // Catch:{ all -> 0x08ca }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x08ca }
            r2.zza(r3, r4, r5)     // Catch:{ all -> 0x08ca }
        L_0x0323:
            com.google.android.gms.measurement.internal.zzx r2 = r24.zzgy()     // Catch:{ all -> 0x08ca }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzx r2 = r24.zzgy()
            r2.endTransaction()
            return
        L_0x0332:
            if (r19 == 0) goto L_0x038c
            long r7 = r6.a     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzdu<java.lang.Integer> r4 = com.google.android.gms.measurement.internal.zzak.zzgr     // Catch:{ all -> 0x08ca }
            java.lang.Object r4 = r4.get(r5)     // Catch:{ all -> 0x08ca }
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ all -> 0x08ca }
            int r4 = r4.intValue()     // Catch:{ all -> 0x08ca }
            r16 = r6
            long r5 = (long) r4     // Catch:{ all -> 0x08ca }
            long r7 = r7 - r5
            int r4 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r4 <= 0) goto L_0x038a
            long r7 = r7 % r9
            int r3 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r3 != 0) goto L_0x036a
            com.google.android.gms.measurement.internal.zzfj r3 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzef r3 = r3.zzab()     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzgk()     // Catch:{ all -> 0x08ca }
            java.lang.String r4 = "Data loss. Too many public events logged. appId, count"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r15)     // Catch:{ all -> 0x08ca }
            r6 = r16
            long r6 = r6.a     // Catch:{ all -> 0x08ca }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x08ca }
            r3.zza(r4, r5, r6)     // Catch:{ all -> 0x08ca }
        L_0x036a:
            com.google.android.gms.measurement.internal.zzfj r3 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzjs r6 = r3.zzz()     // Catch:{ all -> 0x08ca }
            r8 = 16
            java.lang.String r9 = "_ev"
            java.lang.String r10 = r2.name     // Catch:{ all -> 0x08ca }
            r11 = 0
            r7 = r15
            r6.a(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzx r2 = r24.zzgy()     // Catch:{ all -> 0x08ca }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzx r2 = r24.zzgy()
            r2.endTransaction()
            return
        L_0x038a:
            r6 = r16
        L_0x038c:
            if (r20 == 0) goto L_0x03dc
            long r4 = r6.d     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzfj r7 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzs r7 = r7.zzad()     // Catch:{ all -> 0x08ca }
            java.lang.String r8 = r3.packageName     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzdu<java.lang.Integer> r9 = com.google.android.gms.measurement.internal.zzak.zzgq     // Catch:{ all -> 0x08ca }
            int r7 = r7.zzb(r8, r9)     // Catch:{ all -> 0x08ca }
            r8 = 1000000(0xf4240, float:1.401298E-39)
            int r7 = java.lang.Math.min(r8, r7)     // Catch:{ all -> 0x08ca }
            r10 = 0
            int r7 = java.lang.Math.max(r10, r7)     // Catch:{ all -> 0x08ca }
            long r7 = (long) r7     // Catch:{ all -> 0x08ca }
            long r4 = r4 - r7
            int r7 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
            if (r7 <= 0) goto L_0x03dd
            int r2 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r2 != 0) goto L_0x03cd
            com.google.android.gms.measurement.internal.zzfj r2 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzef r2 = r2.zzab()     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzgk()     // Catch:{ all -> 0x08ca }
            java.lang.String r3 = "Too many error events logged. appId, count"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r15)     // Catch:{ all -> 0x08ca }
            long r5 = r6.d     // Catch:{ all -> 0x08ca }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x08ca }
            r2.zza(r3, r4, r5)     // Catch:{ all -> 0x08ca }
        L_0x03cd:
            com.google.android.gms.measurement.internal.zzx r2 = r24.zzgy()     // Catch:{ all -> 0x08ca }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzx r2 = r24.zzgy()
            r2.endTransaction()
            return
        L_0x03dc:
            r10 = 0
        L_0x03dd:
            com.google.android.gms.measurement.internal.zzah r4 = r2.zzfq     // Catch:{ all -> 0x08ca }
            android.os.Bundle r4 = r4.zzcv()     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzfj r5 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzjs r5 = r5.zzz()     // Catch:{ all -> 0x08ca }
            java.lang.String r6 = "_o"
            java.lang.String r7 = r2.origin     // Catch:{ all -> 0x08ca }
            r5.a((android.os.Bundle) r4, (java.lang.String) r6, (java.lang.Object) r7)     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzfj r5 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzjs r5 = r5.zzz()     // Catch:{ all -> 0x08ca }
            boolean r5 = r5.f(r15)     // Catch:{ all -> 0x08ca }
            if (r5 == 0) goto L_0x041a
            com.google.android.gms.measurement.internal.zzfj r5 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzjs r5 = r5.zzz()     // Catch:{ all -> 0x08ca }
            java.lang.String r6 = "_dbg"
            java.lang.Long r7 = java.lang.Long.valueOf(r11)     // Catch:{ all -> 0x08ca }
            r5.a((android.os.Bundle) r4, (java.lang.String) r6, (java.lang.Object) r7)     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzfj r5 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzjs r5 = r5.zzz()     // Catch:{ all -> 0x08ca }
            java.lang.String r6 = "_r"
            java.lang.Long r7 = java.lang.Long.valueOf(r11)     // Catch:{ all -> 0x08ca }
            r5.a((android.os.Bundle) r4, (java.lang.String) r6, (java.lang.Object) r7)     // Catch:{ all -> 0x08ca }
        L_0x041a:
            java.lang.String r5 = "_s"
            java.lang.String r6 = r2.name     // Catch:{ all -> 0x08ca }
            boolean r5 = r5.equals(r6)     // Catch:{ all -> 0x08ca }
            if (r5 == 0) goto L_0x0453
            com.google.android.gms.measurement.internal.zzfj r5 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzs r5 = r5.zzad()     // Catch:{ all -> 0x08ca }
            java.lang.String r6 = r3.packageName     // Catch:{ all -> 0x08ca }
            boolean r5 = r5.l(r6)     // Catch:{ all -> 0x08ca }
            if (r5 == 0) goto L_0x0453
            com.google.android.gms.measurement.internal.zzx r5 = r24.zzgy()     // Catch:{ all -> 0x08ca }
            java.lang.String r6 = r3.packageName     // Catch:{ all -> 0x08ca }
            java.lang.String r7 = "_sno"
            com.google.android.gms.measurement.internal.zzjp r5 = r5.zze(r6, r7)     // Catch:{ all -> 0x08ca }
            if (r5 == 0) goto L_0x0453
            java.lang.Object r6 = r5.e     // Catch:{ all -> 0x08ca }
            boolean r6 = r6 instanceof java.lang.Long     // Catch:{ all -> 0x08ca }
            if (r6 == 0) goto L_0x0453
            com.google.android.gms.measurement.internal.zzfj r6 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzjs r6 = r6.zzz()     // Catch:{ all -> 0x08ca }
            java.lang.String r7 = "_sno"
            java.lang.Object r5 = r5.e     // Catch:{ all -> 0x08ca }
            r6.a((android.os.Bundle) r4, (java.lang.String) r7, (java.lang.Object) r5)     // Catch:{ all -> 0x08ca }
        L_0x0453:
            java.lang.String r5 = "_s"
            java.lang.String r6 = r2.name     // Catch:{ all -> 0x08ca }
            boolean r5 = r5.equals(r6)     // Catch:{ all -> 0x08ca }
            if (r5 == 0) goto L_0x0486
            com.google.android.gms.measurement.internal.zzfj r5 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzs r5 = r5.zzad()     // Catch:{ all -> 0x08ca }
            java.lang.String r6 = r3.packageName     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzak.zzif     // Catch:{ all -> 0x08ca }
            boolean r5 = r5.zze(r6, r7)     // Catch:{ all -> 0x08ca }
            if (r5 == 0) goto L_0x0486
            com.google.android.gms.measurement.internal.zzfj r5 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzs r5 = r5.zzad()     // Catch:{ all -> 0x08ca }
            java.lang.String r6 = r3.packageName     // Catch:{ all -> 0x08ca }
            boolean r5 = r5.l(r6)     // Catch:{ all -> 0x08ca }
            if (r5 != 0) goto L_0x0486
            com.google.android.gms.measurement.internal.zzjn r5 = new com.google.android.gms.measurement.internal.zzjn     // Catch:{ all -> 0x08ca }
            java.lang.String r6 = "_sno"
            r7 = 0
            r5.<init>(r6, r13, r7)     // Catch:{ all -> 0x08ca }
            r1.b((com.google.android.gms.measurement.internal.zzjn) r5, (com.google.android.gms.measurement.internal.zzn) r3)     // Catch:{ all -> 0x08ca }
        L_0x0486:
            com.google.android.gms.measurement.internal.zzx r5 = r24.zzgy()     // Catch:{ all -> 0x08ca }
            long r5 = r5.zzac(r15)     // Catch:{ all -> 0x08ca }
            int r7 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
            if (r7 <= 0) goto L_0x04a9
            com.google.android.gms.measurement.internal.zzfj r7 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzef r7 = r7.zzab()     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzeh r7 = r7.zzgn()     // Catch:{ all -> 0x08ca }
            java.lang.String r8 = "Data lost. Too many events stored on disk, deleted. appId"
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r15)     // Catch:{ all -> 0x08ca }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x08ca }
            r7.zza(r8, r9, r5)     // Catch:{ all -> 0x08ca }
        L_0x04a9:
            com.google.android.gms.measurement.internal.zzaf r5 = new com.google.android.gms.measurement.internal.zzaf     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzfj r7 = r1.zzj     // Catch:{ all -> 0x08ca }
            java.lang.String r8 = r2.origin     // Catch:{ all -> 0x08ca }
            java.lang.String r11 = r2.name     // Catch:{ all -> 0x08ca }
            long r13 = r2.zzfu     // Catch:{ all -> 0x08ca }
            r20 = 0
            r6 = r5
            r9 = r15
            r2 = 0
            r10 = r11
            r11 = r13
            r13 = r20
            r2 = r15
            r15 = r4
            r6.<init>((com.google.android.gms.measurement.internal.zzfj) r7, (java.lang.String) r8, (java.lang.String) r9, (java.lang.String) r10, (long) r11, (long) r13, (android.os.Bundle) r15)     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzx r4 = r24.zzgy()     // Catch:{ all -> 0x08ca }
            java.lang.String r6 = r5.b     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzae r4 = r4.zzc(r2, r6)     // Catch:{ all -> 0x08ca }
            if (r4 != 0) goto L_0x0533
            com.google.android.gms.measurement.internal.zzx r4 = r24.zzgy()     // Catch:{ all -> 0x08ca }
            long r6 = r4.zzag(r2)     // Catch:{ all -> 0x08ca }
            r8 = 500(0x1f4, double:2.47E-321)
            int r4 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r4 < 0) goto L_0x0519
            if (r19 == 0) goto L_0x0519
            com.google.android.gms.measurement.internal.zzfj r3 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzef r3 = r3.zzab()     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzgk()     // Catch:{ all -> 0x08ca }
            java.lang.String r4 = "Too many event names used, ignoring event. appId, name, supported count"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r2)     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzfj r7 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzed r7 = r7.zzy()     // Catch:{ all -> 0x08ca }
            java.lang.String r5 = r5.b     // Catch:{ all -> 0x08ca }
            java.lang.String r5 = r7.a((java.lang.String) r5)     // Catch:{ all -> 0x08ca }
            r7 = 500(0x1f4, float:7.0E-43)
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x08ca }
            r3.zza(r4, r6, r5, r7)     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzfj r3 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzjs r6 = r3.zzz()     // Catch:{ all -> 0x08ca }
            r8 = 8
            r9 = 0
            r10 = 0
            r11 = 0
            r7 = r2
            r6.a(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzx r2 = r24.zzgy()
            r2.endTransaction()
            return
        L_0x0519:
            com.google.android.gms.measurement.internal.zzae r4 = new com.google.android.gms.measurement.internal.zzae     // Catch:{ all -> 0x08ca }
            java.lang.String r8 = r5.b     // Catch:{ all -> 0x08ca }
            r9 = 0
            r11 = 0
            long r13 = r5.c     // Catch:{ all -> 0x08ca }
            r15 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r6 = r4
            r7 = r2
            r6.<init>(r7, r8, r9, r11, r13, r15, r17, r18, r19, r20)     // Catch:{ all -> 0x08ca }
            goto L_0x0541
        L_0x0533:
            com.google.android.gms.measurement.internal.zzfj r2 = r1.zzj     // Catch:{ all -> 0x08ca }
            long r6 = r4.f     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzaf r5 = r5.a(r2, r6)     // Catch:{ all -> 0x08ca }
            long r6 = r5.c     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzae r4 = r4.a(r6)     // Catch:{ all -> 0x08ca }
        L_0x0541:
            com.google.android.gms.measurement.internal.zzx r2 = r24.zzgy()     // Catch:{ all -> 0x08ca }
            r2.zza((com.google.android.gms.measurement.internal.zzae) r4)     // Catch:{ all -> 0x08ca }
            r24.zzo()     // Catch:{ all -> 0x08ca }
            r24.b()     // Catch:{ all -> 0x08ca }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)     // Catch:{ all -> 0x08ca }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r26)     // Catch:{ all -> 0x08ca }
            java.lang.String r2 = r5.a     // Catch:{ all -> 0x08ca }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r2)     // Catch:{ all -> 0x08ca }
            java.lang.String r2 = r5.a     // Catch:{ all -> 0x08ca }
            java.lang.String r4 = r3.packageName     // Catch:{ all -> 0x08ca }
            boolean r2 = r2.equals(r4)     // Catch:{ all -> 0x08ca }
            com.google.android.gms.common.internal.Preconditions.checkArgument(r2)     // Catch:{ all -> 0x08ca }
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r2 = com.google.android.gms.internal.measurement.zzbs.zzg.zzpr()     // Catch:{ all -> 0x08ca }
            r10 = 1
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r2 = r2.zzp(r10)     // Catch:{ all -> 0x08ca }
            java.lang.String r4 = "android"
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r2 = r2.zzcc(r4)     // Catch:{ all -> 0x08ca }
            java.lang.String r4 = r3.packageName     // Catch:{ all -> 0x08ca }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x08ca }
            if (r4 != 0) goto L_0x0580
            java.lang.String r4 = r3.packageName     // Catch:{ all -> 0x08ca }
            r2.zzch(r4)     // Catch:{ all -> 0x08ca }
        L_0x0580:
            java.lang.String r4 = r3.zzco     // Catch:{ all -> 0x08ca }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x08ca }
            if (r4 != 0) goto L_0x058d
            java.lang.String r4 = r3.zzco     // Catch:{ all -> 0x08ca }
            r2.zzcg(r4)     // Catch:{ all -> 0x08ca }
        L_0x058d:
            java.lang.String r4 = r3.zzcm     // Catch:{ all -> 0x08ca }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x08ca }
            if (r4 != 0) goto L_0x059a
            java.lang.String r4 = r3.zzcm     // Catch:{ all -> 0x08ca }
            r2.zzci(r4)     // Catch:{ all -> 0x08ca }
        L_0x059a:
            long r6 = r3.zzcn     // Catch:{ all -> 0x08ca }
            r8 = -2147483648(0xffffffff80000000, double:NaN)
            int r4 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r4 == 0) goto L_0x05a9
            long r6 = r3.zzcn     // Catch:{ all -> 0x08ca }
            int r4 = (int) r6     // Catch:{ all -> 0x08ca }
            r2.zzv(r4)     // Catch:{ all -> 0x08ca }
        L_0x05a9:
            long r6 = r3.zzr     // Catch:{ all -> 0x08ca }
            r2.zzas(r6)     // Catch:{ all -> 0x08ca }
            java.lang.String r4 = r3.zzcg     // Catch:{ all -> 0x08ca }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x08ca }
            if (r4 != 0) goto L_0x05bb
            java.lang.String r4 = r3.zzcg     // Catch:{ all -> 0x08ca }
            r2.zzcm(r4)     // Catch:{ all -> 0x08ca }
        L_0x05bb:
            com.google.android.gms.measurement.internal.zzfj r4 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzs r4 = r4.zzad()     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r6 = com.google.android.gms.measurement.internal.zzak.zzit     // Catch:{ all -> 0x08ca }
            boolean r4 = r4.zza(r6)     // Catch:{ all -> 0x08ca }
            if (r4 == 0) goto L_0x05e1
            java.lang.String r4 = r2.getGmpAppId()     // Catch:{ all -> 0x08ca }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x08ca }
            if (r4 == 0) goto L_0x05ec
            java.lang.String r4 = r3.zzcu     // Catch:{ all -> 0x08ca }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x08ca }
            if (r4 != 0) goto L_0x05ec
            java.lang.String r4 = r3.zzcu     // Catch:{ all -> 0x08ca }
        L_0x05dd:
            r2.zzcq(r4)     // Catch:{ all -> 0x08ca }
            goto L_0x05ec
        L_0x05e1:
            java.lang.String r4 = r3.zzcu     // Catch:{ all -> 0x08ca }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x08ca }
            if (r4 != 0) goto L_0x05ec
            java.lang.String r4 = r3.zzcu     // Catch:{ all -> 0x08ca }
            goto L_0x05dd
        L_0x05ec:
            long r6 = r3.zzcp     // Catch:{ all -> 0x08ca }
            r8 = 0
            int r4 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r4 == 0) goto L_0x05f9
            long r6 = r3.zzcp     // Catch:{ all -> 0x08ca }
            r2.zzau(r6)     // Catch:{ all -> 0x08ca }
        L_0x05f9:
            long r6 = r3.zzs     // Catch:{ all -> 0x08ca }
            r2.zzax(r6)     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzfj r4 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzs r4 = r4.zzad()     // Catch:{ all -> 0x08ca }
            java.lang.String r6 = r3.packageName     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzak.zzin     // Catch:{ all -> 0x08ca }
            boolean r4 = r4.zze(r6, r7)     // Catch:{ all -> 0x08ca }
            if (r4 == 0) goto L_0x061b
            com.google.android.gms.measurement.internal.zzjo r4 = r24.zzgw()     // Catch:{ all -> 0x08ca }
            java.util.List r4 = r4.d()     // Catch:{ all -> 0x08ca }
            if (r4 == 0) goto L_0x061b
            r2.zzd(r4)     // Catch:{ all -> 0x08ca }
        L_0x061b:
            com.google.android.gms.measurement.internal.zzfj r4 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzeo r4 = r4.zzac()     // Catch:{ all -> 0x08ca }
            java.lang.String r6 = r3.packageName     // Catch:{ all -> 0x08ca }
            android.util.Pair r4 = r4.a((java.lang.String) r6)     // Catch:{ all -> 0x08ca }
            if (r4 == 0) goto L_0x064e
            java.lang.Object r6 = r4.first     // Catch:{ all -> 0x08ca }
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6     // Catch:{ all -> 0x08ca }
            boolean r6 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x08ca }
            if (r6 != 0) goto L_0x064e
            boolean r6 = r3.zzcs     // Catch:{ all -> 0x08ca }
            if (r6 == 0) goto L_0x06b0
            java.lang.Object r6 = r4.first     // Catch:{ all -> 0x08ca }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x08ca }
            r2.zzcj(r6)     // Catch:{ all -> 0x08ca }
            java.lang.Object r6 = r4.second     // Catch:{ all -> 0x08ca }
            if (r6 == 0) goto L_0x06b0
            java.lang.Object r4 = r4.second     // Catch:{ all -> 0x08ca }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x08ca }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x08ca }
            r2.zzm(r4)     // Catch:{ all -> 0x08ca }
            goto L_0x06b0
        L_0x064e:
            com.google.android.gms.measurement.internal.zzfj r4 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzac r4 = r4.zzw()     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzfj r6 = r1.zzj     // Catch:{ all -> 0x08ca }
            android.content.Context r6 = r6.getContext()     // Catch:{ all -> 0x08ca }
            boolean r4 = r4.zzj(r6)     // Catch:{ all -> 0x08ca }
            if (r4 != 0) goto L_0x06b0
            boolean r4 = r3.zzct     // Catch:{ all -> 0x08ca }
            if (r4 == 0) goto L_0x06b0
            com.google.android.gms.measurement.internal.zzfj r4 = r1.zzj     // Catch:{ all -> 0x08ca }
            android.content.Context r4 = r4.getContext()     // Catch:{ all -> 0x08ca }
            android.content.ContentResolver r4 = r4.getContentResolver()     // Catch:{ all -> 0x08ca }
            java.lang.String r6 = "android_id"
            java.lang.String r4 = android.provider.Settings.Secure.getString(r4, r6)     // Catch:{ all -> 0x08ca }
            if (r4 != 0) goto L_0x0690
            com.google.android.gms.measurement.internal.zzfj r4 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzef r4 = r4.zzab()     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzeh r4 = r4.zzgn()     // Catch:{ all -> 0x08ca }
            java.lang.String r6 = "null secure ID. appId"
            java.lang.String r7 = r2.zzag()     // Catch:{ all -> 0x08ca }
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r7)     // Catch:{ all -> 0x08ca }
            r4.zza(r6, r7)     // Catch:{ all -> 0x08ca }
            java.lang.String r4 = "null"
            goto L_0x06ad
        L_0x0690:
            boolean r6 = r4.isEmpty()     // Catch:{ all -> 0x08ca }
            if (r6 == 0) goto L_0x06ad
            com.google.android.gms.measurement.internal.zzfj r6 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzef r6 = r6.zzab()     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzeh r6 = r6.zzgn()     // Catch:{ all -> 0x08ca }
            java.lang.String r7 = "empty secure ID. appId"
            java.lang.String r11 = r2.zzag()     // Catch:{ all -> 0x08ca }
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r11)     // Catch:{ all -> 0x08ca }
            r6.zza(r7, r11)     // Catch:{ all -> 0x08ca }
        L_0x06ad:
            r2.zzco(r4)     // Catch:{ all -> 0x08ca }
        L_0x06b0:
            com.google.android.gms.measurement.internal.zzfj r4 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzac r4 = r4.zzw()     // Catch:{ all -> 0x08ca }
            r4.l()     // Catch:{ all -> 0x08ca }
            java.lang.String r4 = android.os.Build.MODEL     // Catch:{ all -> 0x08ca }
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r4 = r2.zzce(r4)     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzfj r6 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzac r6 = r6.zzw()     // Catch:{ all -> 0x08ca }
            r6.l()     // Catch:{ all -> 0x08ca }
            java.lang.String r6 = android.os.Build.VERSION.RELEASE     // Catch:{ all -> 0x08ca }
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r4 = r4.zzcd(r6)     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzfj r6 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzac r6 = r6.zzw()     // Catch:{ all -> 0x08ca }
            long r6 = r6.zzcq()     // Catch:{ all -> 0x08ca }
            int r7 = (int) r6     // Catch:{ all -> 0x08ca }
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r4 = r4.zzt(r7)     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzfj r6 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzac r6 = r6.zzw()     // Catch:{ all -> 0x08ca }
            java.lang.String r6 = r6.zzcr()     // Catch:{ all -> 0x08ca }
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r4 = r4.zzcf(r6)     // Catch:{ all -> 0x08ca }
            long r6 = r3.zzcr     // Catch:{ all -> 0x08ca }
            r4.zzaw(r6)     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzfj r4 = r1.zzj     // Catch:{ all -> 0x08ca }
            boolean r4 = r4.isEnabled()     // Catch:{ all -> 0x08ca }
            if (r4 == 0) goto L_0x070b
            boolean r4 = com.google.android.gms.measurement.internal.zzs.zzbv()     // Catch:{ all -> 0x08ca }
            if (r4 == 0) goto L_0x070b
            r2.zzag()     // Catch:{ all -> 0x08ca }
            r4 = 0
            boolean r6 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x08ca }
            if (r6 != 0) goto L_0x070b
            r2.zzcp(r4)     // Catch:{ all -> 0x08ca }
        L_0x070b:
            com.google.android.gms.measurement.internal.zzx r4 = r24.zzgy()     // Catch:{ all -> 0x08ca }
            java.lang.String r6 = r3.packageName     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzf r4 = r4.zzab(r6)     // Catch:{ all -> 0x08ca }
            if (r4 != 0) goto L_0x077e
            com.google.android.gms.measurement.internal.zzf r4 = new com.google.android.gms.measurement.internal.zzf     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzfj r6 = r1.zzj     // Catch:{ all -> 0x08ca }
            java.lang.String r7 = r3.packageName     // Catch:{ all -> 0x08ca }
            r4.<init>(r6, r7)     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzfj r6 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzjs r6 = r6.zzz()     // Catch:{ all -> 0x08ca }
            java.lang.String r6 = r6.e()     // Catch:{ all -> 0x08ca }
            r4.zza((java.lang.String) r6)     // Catch:{ all -> 0x08ca }
            java.lang.String r6 = r3.zzci     // Catch:{ all -> 0x08ca }
            r4.zze((java.lang.String) r6)     // Catch:{ all -> 0x08ca }
            java.lang.String r6 = r3.zzcg     // Catch:{ all -> 0x08ca }
            r4.zzb((java.lang.String) r6)     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzfj r6 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzeo r6 = r6.zzac()     // Catch:{ all -> 0x08ca }
            java.lang.String r7 = r3.packageName     // Catch:{ all -> 0x08ca }
            java.lang.String r6 = r6.b((java.lang.String) r7)     // Catch:{ all -> 0x08ca }
            r4.zzd(r6)     // Catch:{ all -> 0x08ca }
            r4.zzk(r8)     // Catch:{ all -> 0x08ca }
            r4.zze((long) r8)     // Catch:{ all -> 0x08ca }
            r4.zzf((long) r8)     // Catch:{ all -> 0x08ca }
            java.lang.String r6 = r3.zzcm     // Catch:{ all -> 0x08ca }
            r4.zzf((java.lang.String) r6)     // Catch:{ all -> 0x08ca }
            long r6 = r3.zzcn     // Catch:{ all -> 0x08ca }
            r4.zzg((long) r6)     // Catch:{ all -> 0x08ca }
            java.lang.String r6 = r3.zzco     // Catch:{ all -> 0x08ca }
            r4.zzg((java.lang.String) r6)     // Catch:{ all -> 0x08ca }
            long r6 = r3.zzr     // Catch:{ all -> 0x08ca }
            r4.zzh((long) r6)     // Catch:{ all -> 0x08ca }
            long r6 = r3.zzcp     // Catch:{ all -> 0x08ca }
            r4.zzi(r6)     // Catch:{ all -> 0x08ca }
            boolean r6 = r3.zzcq     // Catch:{ all -> 0x08ca }
            r4.setMeasurementEnabled(r6)     // Catch:{ all -> 0x08ca }
            long r6 = r3.zzcr     // Catch:{ all -> 0x08ca }
            r4.zzt(r6)     // Catch:{ all -> 0x08ca }
            long r6 = r3.zzs     // Catch:{ all -> 0x08ca }
            r4.zzj(r6)     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzx r6 = r24.zzgy()     // Catch:{ all -> 0x08ca }
            r6.zza((com.google.android.gms.measurement.internal.zzf) r4)     // Catch:{ all -> 0x08ca }
        L_0x077e:
            java.lang.String r6 = r4.getAppInstanceId()     // Catch:{ all -> 0x08ca }
            boolean r6 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x08ca }
            if (r6 != 0) goto L_0x078f
            java.lang.String r6 = r4.getAppInstanceId()     // Catch:{ all -> 0x08ca }
            r2.zzck(r6)     // Catch:{ all -> 0x08ca }
        L_0x078f:
            java.lang.String r6 = r4.getFirebaseInstanceId()     // Catch:{ all -> 0x08ca }
            boolean r6 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x08ca }
            if (r6 != 0) goto L_0x07a0
            java.lang.String r4 = r4.getFirebaseInstanceId()     // Catch:{ all -> 0x08ca }
            r2.zzcn(r4)     // Catch:{ all -> 0x08ca }
        L_0x07a0:
            com.google.android.gms.measurement.internal.zzx r4 = r24.zzgy()     // Catch:{ all -> 0x08ca }
            java.lang.String r3 = r3.packageName     // Catch:{ all -> 0x08ca }
            java.util.List r3 = r4.zzaa(r3)     // Catch:{ all -> 0x08ca }
            r4 = 0
        L_0x07ab:
            int r6 = r3.size()     // Catch:{ all -> 0x08ca }
            if (r4 >= r6) goto L_0x07e2
            com.google.android.gms.internal.measurement.zzbs$zzk$zza r6 = com.google.android.gms.internal.measurement.zzbs.zzk.zzqu()     // Catch:{ all -> 0x08ca }
            java.lang.Object r7 = r3.get(r4)     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzjp r7 = (com.google.android.gms.measurement.internal.zzjp) r7     // Catch:{ all -> 0x08ca }
            java.lang.String r7 = r7.c     // Catch:{ all -> 0x08ca }
            com.google.android.gms.internal.measurement.zzbs$zzk$zza r6 = r6.zzdb(r7)     // Catch:{ all -> 0x08ca }
            java.lang.Object r7 = r3.get(r4)     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzjp r7 = (com.google.android.gms.measurement.internal.zzjp) r7     // Catch:{ all -> 0x08ca }
            long r11 = r7.d     // Catch:{ all -> 0x08ca }
            com.google.android.gms.internal.measurement.zzbs$zzk$zza r6 = r6.zzbk(r11)     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzjo r7 = r24.zzgw()     // Catch:{ all -> 0x08ca }
            java.lang.Object r11 = r3.get(r4)     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzjp r11 = (com.google.android.gms.measurement.internal.zzjp) r11     // Catch:{ all -> 0x08ca }
            java.lang.Object r11 = r11.e     // Catch:{ all -> 0x08ca }
            r7.a((com.google.android.gms.internal.measurement.zzbs.zzk.zza) r6, (java.lang.Object) r11)     // Catch:{ all -> 0x08ca }
            r2.zza((com.google.android.gms.internal.measurement.zzbs.zzk.zza) r6)     // Catch:{ all -> 0x08ca }
            int r4 = r4 + 1
            goto L_0x07ab
        L_0x07e2:
            com.google.android.gms.measurement.internal.zzx r3 = r24.zzgy()     // Catch:{ IOException -> 0x0858 }
            com.google.android.gms.internal.measurement.zzgi r4 = r2.zzug()     // Catch:{ IOException -> 0x0858 }
            com.google.android.gms.internal.measurement.zzey r4 = (com.google.android.gms.internal.measurement.zzey) r4     // Catch:{ IOException -> 0x0858 }
            com.google.android.gms.internal.measurement.zzbs$zzg r4 = (com.google.android.gms.internal.measurement.zzbs.zzg) r4     // Catch:{ IOException -> 0x0858 }
            long r2 = r3.zza((com.google.android.gms.internal.measurement.zzbs.zzg) r4)     // Catch:{ IOException -> 0x0858 }
            com.google.android.gms.measurement.internal.zzx r4 = r24.zzgy()     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzah r6 = r5.e     // Catch:{ all -> 0x08ca }
            if (r6 == 0) goto L_0x084e
            com.google.android.gms.measurement.internal.zzah r6 = r5.e     // Catch:{ all -> 0x08ca }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x08ca }
        L_0x0800:
            boolean r7 = r6.hasNext()     // Catch:{ all -> 0x08ca }
            if (r7 == 0) goto L_0x0815
            java.lang.Object r7 = r6.next()     // Catch:{ all -> 0x08ca }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x08ca }
            java.lang.String r11 = "_r"
            boolean r7 = r11.equals(r7)     // Catch:{ all -> 0x08ca }
            if (r7 == 0) goto L_0x0800
            goto L_0x084f
        L_0x0815:
            com.google.android.gms.measurement.internal.zzfd r6 = r24.zzgz()     // Catch:{ all -> 0x08ca }
            java.lang.String r7 = r5.a     // Catch:{ all -> 0x08ca }
            java.lang.String r11 = r5.b     // Catch:{ all -> 0x08ca }
            boolean r6 = r6.b(r7, r11)     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzx r11 = r24.zzgy()     // Catch:{ all -> 0x08ca }
            long r12 = r24.zzjk()     // Catch:{ all -> 0x08ca }
            java.lang.String r14 = r5.a     // Catch:{ all -> 0x08ca }
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            com.google.android.gms.measurement.internal.zzw r7 = r11.zza(r12, r14, r15, r16, r17, r18, r19)     // Catch:{ all -> 0x08ca }
            if (r6 == 0) goto L_0x084e
            long r6 = r7.e     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzfj r11 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzs r11 = r11.zzad()     // Catch:{ all -> 0x08ca }
            java.lang.String r12 = r5.a     // Catch:{ all -> 0x08ca }
            int r11 = r11.zzi(r12)     // Catch:{ all -> 0x08ca }
            long r11 = (long) r11     // Catch:{ all -> 0x08ca }
            int r13 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
            if (r13 >= 0) goto L_0x084e
            goto L_0x084f
        L_0x084e:
            r10 = 0
        L_0x084f:
            boolean r2 = r4.zza((com.google.android.gms.measurement.internal.zzaf) r5, (long) r2, (boolean) r10)     // Catch:{ all -> 0x08ca }
            if (r2 == 0) goto L_0x0871
            r1.zzsy = r8     // Catch:{ all -> 0x08ca }
            goto L_0x0871
        L_0x0858:
            r0 = move-exception
            r3 = r0
            com.google.android.gms.measurement.internal.zzfj r4 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzef r4 = r4.zzab()     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzeh r4 = r4.zzgk()     // Catch:{ all -> 0x08ca }
            java.lang.String r6 = "Data loss. Failed to insert raw event metadata. appId"
            java.lang.String r2 = r2.zzag()     // Catch:{ all -> 0x08ca }
            java.lang.Object r2 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r2)     // Catch:{ all -> 0x08ca }
            r4.zza(r6, r2, r3)     // Catch:{ all -> 0x08ca }
        L_0x0871:
            com.google.android.gms.measurement.internal.zzx r2 = r24.zzgy()     // Catch:{ all -> 0x08ca }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzfj r2 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzef r2 = r2.zzab()     // Catch:{ all -> 0x08ca }
            r3 = 2
            boolean r2 = r2.a((int) r3)     // Catch:{ all -> 0x08ca }
            if (r2 == 0) goto L_0x089e
            com.google.android.gms.measurement.internal.zzfj r2 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzef r2 = r2.zzab()     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzgs()     // Catch:{ all -> 0x08ca }
            java.lang.String r3 = "Event recorded"
            com.google.android.gms.measurement.internal.zzfj r4 = r1.zzj     // Catch:{ all -> 0x08ca }
            com.google.android.gms.measurement.internal.zzed r4 = r4.zzy()     // Catch:{ all -> 0x08ca }
            java.lang.String r4 = r4.a((com.google.android.gms.measurement.internal.zzaf) r5)     // Catch:{ all -> 0x08ca }
            r2.zza(r3, r4)     // Catch:{ all -> 0x08ca }
        L_0x089e:
            com.google.android.gms.measurement.internal.zzx r2 = r24.zzgy()
            r2.endTransaction()
            r24.zzjn()
            com.google.android.gms.measurement.internal.zzfj r2 = r1.zzj
            com.google.android.gms.measurement.internal.zzef r2 = r2.zzab()
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzgs()
            java.lang.String r3 = "Background event processing time, ms"
            long r4 = java.lang.System.nanoTime()
            long r4 = r4 - r22
            r6 = 500000(0x7a120, double:2.47033E-318)
            long r4 = r4 + r6
            r6 = 1000000(0xf4240, double:4.940656E-318)
            long r4 = r4 / r6
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r2.zza(r3, r4)
            return
        L_0x08ca:
            r0 = move-exception
            r2 = r0
            com.google.android.gms.measurement.internal.zzx r3 = r24.zzgy()
            r3.endTransaction()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzjg.zzd(com.google.android.gms.measurement.internal.zzai, com.google.android.gms.measurement.internal.zzn):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0223, code lost:
        if (r5 != null) goto L_0x01cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0040, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0041, code lost:
        r6 = r1;
        r22 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x0264, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x0265, code lost:
        r5 = r3;
        r7 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0046, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x0285, code lost:
        if (r5 != null) goto L_0x01cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0047, code lost:
        r5 = null;
        r7 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0085, code lost:
        if (r3 != null) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:479:0x0c41, code lost:
        if (r6 != r11) goto L_0x0c43;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01ca, code lost:
        if (r5 != null) goto L_0x01cc;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0040 A[ExcHandler: all (th java.lang.Throwable), PHI: r3 
  PHI: (r3v34 android.database.Cursor) = (r3v28 android.database.Cursor), (r3v37 android.database.Cursor), (r3v37 android.database.Cursor), (r3v37 android.database.Cursor), (r3v37 android.database.Cursor), (r3v0 android.database.Cursor), (r3v0 android.database.Cursor) binds: [B:45:0x00e0, B:23:0x007f, B:29:0x008c, B:31:0x0090, B:32:?, B:9:0x0031, B:10:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:9:0x0031] */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x028d A[Catch:{ IOException -> 0x022f, all -> 0x0efb }] */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x029b A[Catch:{ IOException -> 0x022f, all -> 0x0efb }] */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x03c7 A[Catch:{ IOException -> 0x022f, all -> 0x0efb }] */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x03c9 A[Catch:{ IOException -> 0x022f, all -> 0x0efb }] */
    /* JADX WARNING: Removed duplicated region for block: B:195:0x03cc A[Catch:{ IOException -> 0x022f, all -> 0x0efb }] */
    /* JADX WARNING: Removed duplicated region for block: B:196:0x03cd A[Catch:{ IOException -> 0x022f, all -> 0x0efb }] */
    /* JADX WARNING: Removed duplicated region for block: B:246:0x05ec A[Catch:{ IOException -> 0x022f, all -> 0x0efb }] */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x0619 A[Catch:{ IOException -> 0x022f, all -> 0x0efb }] */
    /* JADX WARNING: Removed duplicated region for block: B:280:0x06b9 A[Catch:{ IOException -> 0x022f, all -> 0x0efb }] */
    /* JADX WARNING: Removed duplicated region for block: B:308:0x0766 A[Catch:{ IOException -> 0x022f, all -> 0x0efb }] */
    /* JADX WARNING: Removed duplicated region for block: B:310:0x0780 A[Catch:{ IOException -> 0x022f, all -> 0x0efb }] */
    /* JADX WARNING: Removed duplicated region for block: B:424:0x0aa9 A[Catch:{ IOException -> 0x022f, all -> 0x0efb }] */
    /* JADX WARNING: Removed duplicated region for block: B:425:0x0abc A[Catch:{ IOException -> 0x022f, all -> 0x0efb }] */
    /* JADX WARNING: Removed duplicated region for block: B:427:0x0abf A[Catch:{ IOException -> 0x022f, all -> 0x0efb }] */
    /* JADX WARNING: Removed duplicated region for block: B:428:0x0ae3 A[SYNTHETIC, Splitter:B:428:0x0ae3] */
    /* JADX WARNING: Removed duplicated region for block: B:486:0x0c5e A[Catch:{ all -> 0x0cfd }] */
    /* JADX WARNING: Removed duplicated region for block: B:490:0x0ca8 A[Catch:{ all -> 0x0cfd }] */
    /* JADX WARNING: Removed duplicated region for block: B:579:0x0edd  */
    /* JADX WARNING: Removed duplicated region for block: B:587:0x0ef5 A[SYNTHETIC, Splitter:B:587:0x0ef5] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0121 A[SYNTHETIC, Splitter:B:58:0x0121] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0144 A[SYNTHETIC, Splitter:B:67:0x0144] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:27:0x0087=Splitter:B:27:0x0087, B:146:0x0289=Splitter:B:146:0x0289, B:96:0x01cc=Splitter:B:96:0x01cc} */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzd(java.lang.String r61, long r62) {
        /*
            r60 = this;
            r1 = r60
            com.google.android.gms.measurement.internal.zzx r2 = r60.zzgy()
            r2.beginTransaction()
            com.google.android.gms.measurement.internal.zzjg$zza r2 = new com.google.android.gms.measurement.internal.zzjg$zza     // Catch:{ all -> 0x0efb }
            r3 = 0
            r2.<init>(r1, r3)     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzx r4 = r60.zzgy()     // Catch:{ all -> 0x0efb }
            long r5 = r1.zztj     // Catch:{ all -> 0x0efb }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r2)     // Catch:{ all -> 0x0efb }
            r4.zzo()     // Catch:{ all -> 0x0efb }
            r4.c()     // Catch:{ all -> 0x0efb }
            r8 = -1
            r10 = 2
            r11 = 0
            r12 = 1
            android.database.sqlite.SQLiteDatabase r15 = r4.d()     // Catch:{ SQLiteException -> 0x0270, all -> 0x0269 }
            boolean r13 = android.text.TextUtils.isEmpty(r3)     // Catch:{ SQLiteException -> 0x0270, all -> 0x0269 }
            if (r13 == 0) goto L_0x009f
            int r13 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r13 == 0) goto L_0x004c
            java.lang.String[] r14 = new java.lang.String[r10]     // Catch:{ SQLiteException -> 0x0046, all -> 0x0040 }
            java.lang.String r16 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x0046, all -> 0x0040 }
            r14[r11] = r16     // Catch:{ SQLiteException -> 0x0046, all -> 0x0040 }
            java.lang.String r16 = java.lang.String.valueOf(r62)     // Catch:{ SQLiteException -> 0x0046, all -> 0x0040 }
            r14[r12] = r16     // Catch:{ SQLiteException -> 0x0046, all -> 0x0040 }
            goto L_0x0054
        L_0x0040:
            r0 = move-exception
            r6 = r1
            r22 = r3
            goto L_0x026d
        L_0x0046:
            r0 = move-exception
            r5 = r3
            r7 = r5
        L_0x0049:
            r3 = r0
            goto L_0x0274
        L_0x004c:
            java.lang.String[] r14 = new java.lang.String[r12]     // Catch:{ SQLiteException -> 0x0270, all -> 0x0269 }
            java.lang.String r16 = java.lang.String.valueOf(r62)     // Catch:{ SQLiteException -> 0x0270, all -> 0x0269 }
            r14[r11] = r16     // Catch:{ SQLiteException -> 0x0270, all -> 0x0269 }
        L_0x0054:
            if (r13 == 0) goto L_0x0059
            java.lang.String r13 = "rowid <= ? and "
            goto L_0x005b
        L_0x0059:
            java.lang.String r13 = ""
        L_0x005b:
            java.lang.String r16 = java.lang.String.valueOf(r13)     // Catch:{ SQLiteException -> 0x0270, all -> 0x0269 }
            int r7 = r16.length()     // Catch:{ SQLiteException -> 0x0270, all -> 0x0269 }
            int r7 = r7 + 148
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0270, all -> 0x0269 }
            r3.<init>(r7)     // Catch:{ SQLiteException -> 0x0270, all -> 0x0269 }
            java.lang.String r7 = "select app_id, metadata_fingerprint from raw_events where "
            r3.append(r7)     // Catch:{ SQLiteException -> 0x0270, all -> 0x0269 }
            r3.append(r13)     // Catch:{ SQLiteException -> 0x0270, all -> 0x0269 }
            java.lang.String r7 = "app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;"
            r3.append(r7)     // Catch:{ SQLiteException -> 0x0270, all -> 0x0269 }
            java.lang.String r3 = r3.toString()     // Catch:{ SQLiteException -> 0x0270, all -> 0x0269 }
            android.database.Cursor r3 = r15.rawQuery(r3, r14)     // Catch:{ SQLiteException -> 0x0270, all -> 0x0269 }
            boolean r7 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x0264, all -> 0x0040 }
            if (r7 != 0) goto L_0x008c
            if (r3 == 0) goto L_0x0289
        L_0x0087:
            r3.close()     // Catch:{ all -> 0x0efb }
            goto L_0x0289
        L_0x008c:
            java.lang.String r7 = r3.getString(r11)     // Catch:{ SQLiteException -> 0x0264, all -> 0x0040 }
            java.lang.String r13 = r3.getString(r12)     // Catch:{ SQLiteException -> 0x009c, all -> 0x0040 }
            r3.close()     // Catch:{ SQLiteException -> 0x009c, all -> 0x0040 }
            r23 = r3
            r3 = r7
            r7 = r13
            goto L_0x00f4
        L_0x009c:
            r0 = move-exception
            r5 = r3
            goto L_0x0049
        L_0x009f:
            int r3 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r3 == 0) goto L_0x00b0
            java.lang.String[] r7 = new java.lang.String[r10]     // Catch:{ SQLiteException -> 0x0270, all -> 0x0269 }
            r13 = 0
            r7[r11] = r13     // Catch:{ SQLiteException -> 0x0270, all -> 0x0269 }
            java.lang.String r13 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x0270, all -> 0x0269 }
            r7[r12] = r13     // Catch:{ SQLiteException -> 0x0270, all -> 0x0269 }
            r13 = r7
            goto L_0x00b5
        L_0x00b0:
            r7 = 0
            java.lang.String[] r13 = new java.lang.String[]{r7}     // Catch:{ SQLiteException -> 0x0270, all -> 0x0269 }
        L_0x00b5:
            if (r3 == 0) goto L_0x00ba
            java.lang.String r3 = " and rowid <= ?"
            goto L_0x00bc
        L_0x00ba:
            java.lang.String r3 = ""
        L_0x00bc:
            java.lang.String r7 = java.lang.String.valueOf(r3)     // Catch:{ SQLiteException -> 0x0270, all -> 0x0269 }
            int r7 = r7.length()     // Catch:{ SQLiteException -> 0x0270, all -> 0x0269 }
            int r7 = r7 + 84
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0270, all -> 0x0269 }
            r14.<init>(r7)     // Catch:{ SQLiteException -> 0x0270, all -> 0x0269 }
            java.lang.String r7 = "select metadata_fingerprint from raw_events where app_id = ?"
            r14.append(r7)     // Catch:{ SQLiteException -> 0x0270, all -> 0x0269 }
            r14.append(r3)     // Catch:{ SQLiteException -> 0x0270, all -> 0x0269 }
            java.lang.String r3 = " order by rowid limit 1;"
            r14.append(r3)     // Catch:{ SQLiteException -> 0x0270, all -> 0x0269 }
            java.lang.String r3 = r14.toString()     // Catch:{ SQLiteException -> 0x0270, all -> 0x0269 }
            android.database.Cursor r3 = r15.rawQuery(r3, r13)     // Catch:{ SQLiteException -> 0x0270, all -> 0x0269 }
            boolean r7 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x0264, all -> 0x0040 }
            if (r7 != 0) goto L_0x00e9
            if (r3 == 0) goto L_0x0289
            goto L_0x0087
        L_0x00e9:
            java.lang.String r13 = r3.getString(r11)     // Catch:{ SQLiteException -> 0x0264, all -> 0x0040 }
            r3.close()     // Catch:{ SQLiteException -> 0x0264, all -> 0x0040 }
            r23 = r3
            r7 = r13
            r3 = 0
        L_0x00f4:
            java.lang.String r14 = "raw_events_metadata"
            java.lang.String r13 = "metadata"
            java.lang.String[] r16 = new java.lang.String[]{r13}     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            java.lang.String r17 = "app_id = ? and metadata_fingerprint = ?"
            java.lang.String[] r13 = new java.lang.String[r10]     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            r13[r11] = r3     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            r13[r12] = r7     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            r18 = 0
            r19 = 0
            java.lang.String r20 = "rowid"
            java.lang.String r21 = "2"
            r24 = r13
            r13 = r15
            r25 = r15
            r15 = r16
            r16 = r17
            r17 = r24
            android.database.Cursor r15 = r13.query(r14, r15, r16, r17, r18, r19, r20, r21)     // Catch:{ SQLiteException -> 0x025e, all -> 0x0259 }
            boolean r13 = r15.moveToFirst()     // Catch:{ SQLiteException -> 0x0253, all -> 0x024d }
            if (r13 != 0) goto L_0x0144
            com.google.android.gms.measurement.internal.zzef r5 = r4.zzab()     // Catch:{ SQLiteException -> 0x013f, all -> 0x0139 }
            com.google.android.gms.measurement.internal.zzeh r5 = r5.zzgk()     // Catch:{ SQLiteException -> 0x013f, all -> 0x0139 }
            java.lang.String r6 = "Raw event metadata record is missing. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r3)     // Catch:{ SQLiteException -> 0x013f, all -> 0x0139 }
            r5.zza(r6, r7)     // Catch:{ SQLiteException -> 0x013f, all -> 0x0139 }
            if (r15 == 0) goto L_0x0289
            r15.close()     // Catch:{ all -> 0x0efb }
            goto L_0x0289
        L_0x0139:
            r0 = move-exception
            r6 = r1
            r22 = r15
            goto L_0x026d
        L_0x013f:
            r0 = move-exception
            r7 = r3
            r5 = r15
            goto L_0x0049
        L_0x0144:
            byte[] r13 = r15.getBlob(r11)     // Catch:{ SQLiteException -> 0x0253, all -> 0x024d }
            com.google.android.gms.internal.measurement.zzel r14 = com.google.android.gms.internal.measurement.zzel.zztq()     // Catch:{ IOException -> 0x022f }
            com.google.android.gms.internal.measurement.zzbs$zzg r13 = com.google.android.gms.internal.measurement.zzbs.zzg.zzd(r13, r14)     // Catch:{ IOException -> 0x022f }
            boolean r14 = r15.moveToNext()     // Catch:{ SQLiteException -> 0x0253, all -> 0x024d }
            if (r14 == 0) goto L_0x0167
            com.google.android.gms.measurement.internal.zzef r14 = r4.zzab()     // Catch:{ SQLiteException -> 0x013f, all -> 0x0139 }
            com.google.android.gms.measurement.internal.zzeh r14 = r14.zzgn()     // Catch:{ SQLiteException -> 0x013f, all -> 0x0139 }
            java.lang.String r10 = "Get multiple raw event metadata records, expected one. appId"
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r3)     // Catch:{ SQLiteException -> 0x013f, all -> 0x0139 }
            r14.zza(r10, r12)     // Catch:{ SQLiteException -> 0x013f, all -> 0x0139 }
        L_0x0167:
            r15.close()     // Catch:{ SQLiteException -> 0x0253, all -> 0x024d }
            r2.zzb(r13)     // Catch:{ SQLiteException -> 0x0253, all -> 0x024d }
            int r10 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r10 == 0) goto L_0x0187
            java.lang.String r10 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?"
            r12 = 3
            java.lang.String[] r13 = new java.lang.String[r12]     // Catch:{ SQLiteException -> 0x013f, all -> 0x0139 }
            r13[r11] = r3     // Catch:{ SQLiteException -> 0x013f, all -> 0x0139 }
            r12 = 1
            r13[r12] = r7     // Catch:{ SQLiteException -> 0x013f, all -> 0x0139 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x013f, all -> 0x0139 }
            r6 = 2
            r13[r6] = r5     // Catch:{ SQLiteException -> 0x013f, all -> 0x0139 }
            r16 = r10
            r17 = r13
            goto L_0x0195
        L_0x0187:
            java.lang.String r5 = "app_id = ? and metadata_fingerprint = ?"
            r6 = 2
            java.lang.String[] r10 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x0253, all -> 0x024d }
            r10[r11] = r3     // Catch:{ SQLiteException -> 0x0253, all -> 0x024d }
            r6 = 1
            r10[r6] = r7     // Catch:{ SQLiteException -> 0x0253, all -> 0x024d }
            r16 = r5
            r17 = r10
        L_0x0195:
            java.lang.String r14 = "raw_events"
            java.lang.String r5 = "rowid"
            java.lang.String r6 = "name"
            java.lang.String r7 = "timestamp"
            java.lang.String r10 = "data"
            java.lang.String[] r5 = new java.lang.String[]{r5, r6, r7, r10}     // Catch:{ SQLiteException -> 0x0253, all -> 0x024d }
            r18 = 0
            r19 = 0
            java.lang.String r20 = "rowid"
            r21 = 0
            r13 = r25
            r6 = r15
            r15 = r5
            android.database.Cursor r5 = r13.query(r14, r15, r16, r17, r18, r19, r20, r21)     // Catch:{ SQLiteException -> 0x024b, all -> 0x0249 }
            boolean r6 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x022b, all -> 0x0226 }
            if (r6 != 0) goto L_0x01d1
            com.google.android.gms.measurement.internal.zzef r6 = r4.zzab()     // Catch:{ SQLiteException -> 0x022b, all -> 0x0226 }
            com.google.android.gms.measurement.internal.zzeh r6 = r6.zzgn()     // Catch:{ SQLiteException -> 0x022b, all -> 0x0226 }
            java.lang.String r7 = "Raw event data disappeared while in transaction. appId"
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r3)     // Catch:{ SQLiteException -> 0x022b, all -> 0x0226 }
            r6.zza(r7, r10)     // Catch:{ SQLiteException -> 0x022b, all -> 0x0226 }
            if (r5 == 0) goto L_0x0289
        L_0x01cc:
            r5.close()     // Catch:{ all -> 0x0efb }
            goto L_0x0289
        L_0x01d1:
            long r6 = r5.getLong(r11)     // Catch:{ SQLiteException -> 0x022b, all -> 0x0226 }
            r10 = 3
            byte[] r12 = r5.getBlob(r10)     // Catch:{ SQLiteException -> 0x022b, all -> 0x0226 }
            com.google.android.gms.internal.measurement.zzbs$zzc$zza r10 = com.google.android.gms.internal.measurement.zzbs.zzc.zzmq()     // Catch:{ IOException -> 0x020a }
            com.google.android.gms.internal.measurement.zzel r13 = com.google.android.gms.internal.measurement.zzel.zztq()     // Catch:{ IOException -> 0x020a }
            com.google.android.gms.internal.measurement.zzdh r10 = r10.zzf(r12, r13)     // Catch:{ IOException -> 0x020a }
            com.google.android.gms.internal.measurement.zzbs$zzc$zza r10 = (com.google.android.gms.internal.measurement.zzbs.zzc.zza) r10     // Catch:{ IOException -> 0x020a }
            r12 = 1
            java.lang.String r13 = r5.getString(r12)     // Catch:{ SQLiteException -> 0x022b, all -> 0x0226 }
            com.google.android.gms.internal.measurement.zzbs$zzc$zza r12 = r10.zzbx(r13)     // Catch:{ SQLiteException -> 0x022b, all -> 0x0226 }
            r13 = 2
            long r14 = r5.getLong(r13)     // Catch:{ SQLiteException -> 0x022b, all -> 0x0226 }
            r12.zzag(r14)     // Catch:{ SQLiteException -> 0x022b, all -> 0x0226 }
            com.google.android.gms.internal.measurement.zzgi r10 = r10.zzug()     // Catch:{ SQLiteException -> 0x022b, all -> 0x0226 }
            com.google.android.gms.internal.measurement.zzey r10 = (com.google.android.gms.internal.measurement.zzey) r10     // Catch:{ SQLiteException -> 0x022b, all -> 0x0226 }
            com.google.android.gms.internal.measurement.zzbs$zzc r10 = (com.google.android.gms.internal.measurement.zzbs.zzc) r10     // Catch:{ SQLiteException -> 0x022b, all -> 0x0226 }
            boolean r6 = r2.zza(r6, r10)     // Catch:{ SQLiteException -> 0x022b, all -> 0x0226 }
            if (r6 != 0) goto L_0x021d
            if (r5 == 0) goto L_0x0289
            goto L_0x01cc
        L_0x020a:
            r0 = move-exception
            r6 = r0
            com.google.android.gms.measurement.internal.zzef r7 = r4.zzab()     // Catch:{ SQLiteException -> 0x022b, all -> 0x0226 }
            com.google.android.gms.measurement.internal.zzeh r7 = r7.zzgk()     // Catch:{ SQLiteException -> 0x022b, all -> 0x0226 }
            java.lang.String r10 = "Data loss. Failed to merge raw event. appId"
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r3)     // Catch:{ SQLiteException -> 0x022b, all -> 0x0226 }
            r7.zza(r10, r12, r6)     // Catch:{ SQLiteException -> 0x022b, all -> 0x0226 }
        L_0x021d:
            boolean r6 = r5.moveToNext()     // Catch:{ SQLiteException -> 0x022b, all -> 0x0226 }
            if (r6 != 0) goto L_0x01d1
            if (r5 == 0) goto L_0x0289
            goto L_0x01cc
        L_0x0226:
            r0 = move-exception
            r6 = r1
            r22 = r5
            goto L_0x026d
        L_0x022b:
            r0 = move-exception
            r7 = r3
            goto L_0x0049
        L_0x022f:
            r0 = move-exception
            r6 = r15
            r5 = r0
            com.google.android.gms.measurement.internal.zzef r7 = r4.zzab()     // Catch:{ SQLiteException -> 0x024b, all -> 0x0249 }
            com.google.android.gms.measurement.internal.zzeh r7 = r7.zzgk()     // Catch:{ SQLiteException -> 0x024b, all -> 0x0249 }
            java.lang.String r10 = "Data loss. Failed to merge raw event metadata. appId"
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r3)     // Catch:{ SQLiteException -> 0x024b, all -> 0x0249 }
            r7.zza(r10, r12, r5)     // Catch:{ SQLiteException -> 0x024b, all -> 0x0249 }
            if (r6 == 0) goto L_0x0289
            r6.close()     // Catch:{ all -> 0x0efb }
            goto L_0x0289
        L_0x0249:
            r0 = move-exception
            goto L_0x024f
        L_0x024b:
            r0 = move-exception
            goto L_0x0255
        L_0x024d:
            r0 = move-exception
            r6 = r15
        L_0x024f:
            r22 = r6
            r6 = r1
            goto L_0x026d
        L_0x0253:
            r0 = move-exception
            r6 = r15
        L_0x0255:
            r7 = r3
            r5 = r6
            goto L_0x0049
        L_0x0259:
            r0 = move-exception
            r6 = r1
            r22 = r23
            goto L_0x026d
        L_0x025e:
            r0 = move-exception
            r7 = r3
            r5 = r23
            goto L_0x0049
        L_0x0264:
            r0 = move-exception
            r5 = r3
            r7 = 0
            goto L_0x0049
        L_0x0269:
            r0 = move-exception
            r6 = r1
            r22 = 0
        L_0x026d:
            r1 = r0
            goto L_0x0ef3
        L_0x0270:
            r0 = move-exception
            r3 = r0
            r5 = 0
            r7 = 0
        L_0x0274:
            com.google.android.gms.measurement.internal.zzef r4 = r4.zzab()     // Catch:{ all -> 0x0eee }
            com.google.android.gms.measurement.internal.zzeh r4 = r4.zzgk()     // Catch:{ all -> 0x0eee }
            java.lang.String r6 = "Data loss. Error selecting raw event. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r7)     // Catch:{ all -> 0x0eee }
            r4.zza(r6, r7, r3)     // Catch:{ all -> 0x0eee }
            if (r5 == 0) goto L_0x0289
            goto L_0x01cc
        L_0x0289:
            java.util.List<com.google.android.gms.internal.measurement.zzbs$zzc> r3 = r2.c     // Catch:{ all -> 0x0efb }
            if (r3 == 0) goto L_0x0298
            java.util.List<com.google.android.gms.internal.measurement.zzbs$zzc> r3 = r2.c     // Catch:{ all -> 0x0efb }
            boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x0efb }
            if (r3 == 0) goto L_0x0296
            goto L_0x0298
        L_0x0296:
            r3 = 0
            goto L_0x0299
        L_0x0298:
            r3 = 1
        L_0x0299:
            if (r3 != 0) goto L_0x0edd
            com.google.android.gms.internal.measurement.zzbs$zzg r3 = r2.a     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzey$zza r3 = r3.zzuj()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzey$zza r3 = (com.google.android.gms.internal.measurement.zzey.zza) r3     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r3 = (com.google.android.gms.internal.measurement.zzbs.zzg.zza) r3     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r3 = r3.zznn()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzfj r4 = r1.zzj     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzs r4 = r4.zzad()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzg r5 = r2.a     // Catch:{ all -> 0x0efb }
            java.lang.String r5 = r5.zzag()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r6 = com.google.android.gms.measurement.internal.zzak.zzii     // Catch:{ all -> 0x0efb }
            boolean r4 = r4.zze(r5, r6)     // Catch:{ all -> 0x0efb }
            r7 = 0
            r8 = -1
            r9 = -1
            r12 = 0
            r13 = 0
            r15 = 0
            r18 = 0
            r19 = 0
        L_0x02c6:
            java.util.List<com.google.android.gms.internal.measurement.zzbs$zzc> r11 = r2.c     // Catch:{ all -> 0x0efb }
            int r11 = r11.size()     // Catch:{ all -> 0x0efb }
            if (r7 >= r11) goto L_0x07cf
            java.util.List<com.google.android.gms.internal.measurement.zzbs$zzc> r11 = r2.c     // Catch:{ all -> 0x0efb }
            java.lang.Object r11 = r11.get(r7)     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzc r11 = (com.google.android.gms.internal.measurement.zzbs.zzc) r11     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzey$zza r11 = r11.zzuj()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzey$zza r11 = (com.google.android.gms.internal.measurement.zzey.zza) r11     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzc$zza r11 = (com.google.android.gms.internal.measurement.zzbs.zzc.zza) r11     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzfd r10 = r60.zzgz()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzg r5 = r2.a     // Catch:{ all -> 0x0efb }
            java.lang.String r5 = r5.zzag()     // Catch:{ all -> 0x0efb }
            java.lang.String r6 = r11.getName()     // Catch:{ all -> 0x0efb }
            boolean r5 = r10.a(r5, r6)     // Catch:{ all -> 0x0efb }
            if (r5 == 0) goto L_0x036f
            com.google.android.gms.measurement.internal.zzfj r5 = r1.zzj     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzef r5 = r5.zzab()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzeh r5 = r5.zzgn()     // Catch:{ all -> 0x0efb }
            java.lang.String r6 = "Dropping blacklisted raw event. appId"
            com.google.android.gms.internal.measurement.zzbs$zzg r10 = r2.a     // Catch:{ all -> 0x0efb }
            java.lang.String r10 = r10.zzag()     // Catch:{ all -> 0x0efb }
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r10)     // Catch:{ all -> 0x0efb }
            r23 = r12
            com.google.android.gms.measurement.internal.zzfj r12 = r1.zzj     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzed r12 = r12.zzy()     // Catch:{ all -> 0x0efb }
            r25 = r15
            java.lang.String r15 = r11.getName()     // Catch:{ all -> 0x0efb }
            java.lang.String r12 = r12.a((java.lang.String) r15)     // Catch:{ all -> 0x0efb }
            r5.zza(r6, r10, r12)     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzfd r5 = r60.zzgz()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzg r6 = r2.a     // Catch:{ all -> 0x0efb }
            java.lang.String r6 = r6.zzag()     // Catch:{ all -> 0x0efb }
            boolean r5 = r5.g(r6)     // Catch:{ all -> 0x0efb }
            if (r5 != 0) goto L_0x0340
            com.google.android.gms.measurement.internal.zzfd r5 = r60.zzgz()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzg r6 = r2.a     // Catch:{ all -> 0x0efb }
            java.lang.String r6 = r6.zzag()     // Catch:{ all -> 0x0efb }
            boolean r5 = r5.h(r6)     // Catch:{ all -> 0x0efb }
            if (r5 == 0) goto L_0x033e
            goto L_0x0340
        L_0x033e:
            r5 = 0
            goto L_0x0341
        L_0x0340:
            r5 = 1
        L_0x0341:
            if (r5 != 0) goto L_0x0368
            java.lang.String r5 = "_err"
            java.lang.String r6 = r11.getName()     // Catch:{ all -> 0x0efb }
            boolean r5 = r5.equals(r6)     // Catch:{ all -> 0x0efb }
            if (r5 != 0) goto L_0x0368
            com.google.android.gms.measurement.internal.zzfj r5 = r1.zzj     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzjs r26 = r5.zzz()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzg r5 = r2.a     // Catch:{ all -> 0x0efb }
            java.lang.String r27 = r5.zzag()     // Catch:{ all -> 0x0efb }
            r28 = 11
            java.lang.String r29 = "_ev"
            java.lang.String r30 = r11.getName()     // Catch:{ all -> 0x0efb }
            r31 = 0
            r26.a(r27, r28, r29, r30, r31)     // Catch:{ all -> 0x0efb }
        L_0x0368:
            r10 = r7
            r15 = r25
            r6 = -1
            r12 = 3
            goto L_0x07c9
        L_0x036f:
            r23 = r12
            r25 = r15
            com.google.android.gms.measurement.internal.zzfd r5 = r60.zzgz()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzg r6 = r2.a     // Catch:{ all -> 0x0efb }
            java.lang.String r6 = r6.zzag()     // Catch:{ all -> 0x0efb }
            java.lang.String r10 = r11.getName()     // Catch:{ all -> 0x0efb }
            boolean r5 = r5.b(r6, r10)     // Catch:{ all -> 0x0efb }
            if (r5 != 0) goto L_0x03d3
            r60.zzgw()     // Catch:{ all -> 0x0efb }
            java.lang.String r6 = r11.getName()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r6)     // Catch:{ all -> 0x0efb }
            int r10 = r6.hashCode()     // Catch:{ all -> 0x0efb }
            r12 = 94660(0x171c4, float:1.32647E-40)
            if (r10 == r12) goto L_0x03b9
            r12 = 95025(0x17331, float:1.33158E-40)
            if (r10 == r12) goto L_0x03af
            r12 = 95027(0x17333, float:1.33161E-40)
            if (r10 == r12) goto L_0x03a5
            goto L_0x03c3
        L_0x03a5:
            java.lang.String r10 = "_ui"
            boolean r6 = r6.equals(r10)     // Catch:{ all -> 0x0efb }
            if (r6 == 0) goto L_0x03c3
            r6 = 1
            goto L_0x03c4
        L_0x03af:
            java.lang.String r10 = "_ug"
            boolean r6 = r6.equals(r10)     // Catch:{ all -> 0x0efb }
            if (r6 == 0) goto L_0x03c3
            r6 = 2
            goto L_0x03c4
        L_0x03b9:
            java.lang.String r10 = "_in"
            boolean r6 = r6.equals(r10)     // Catch:{ all -> 0x0efb }
            if (r6 == 0) goto L_0x03c3
            r6 = 0
            goto L_0x03c4
        L_0x03c3:
            r6 = -1
        L_0x03c4:
            switch(r6) {
                case 0: goto L_0x03c9;
                case 1: goto L_0x03c9;
                case 2: goto L_0x03c9;
                default: goto L_0x03c7;
            }     // Catch:{ all -> 0x0efb }
        L_0x03c7:
            r6 = 0
            goto L_0x03ca
        L_0x03c9:
            r6 = 1
        L_0x03ca:
            if (r6 == 0) goto L_0x03cd
            goto L_0x03d3
        L_0x03cd:
            r29 = r7
            r30 = r13
            goto L_0x05c6
        L_0x03d3:
            r6 = 0
            r10 = 0
            r12 = 0
        L_0x03d6:
            int r15 = r11.zzmk()     // Catch:{ all -> 0x0efb }
            if (r6 >= r15) goto L_0x0446
            java.lang.String r15 = "_c"
            com.google.android.gms.internal.measurement.zzbs$zze r28 = r11.zzl(r6)     // Catch:{ all -> 0x0efb }
            r29 = r7
            java.lang.String r7 = r28.getName()     // Catch:{ all -> 0x0efb }
            boolean r7 = r15.equals(r7)     // Catch:{ all -> 0x0efb }
            if (r7 == 0) goto L_0x040f
            com.google.android.gms.internal.measurement.zzbs$zze r7 = r11.zzl(r6)     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzey$zza r7 = r7.zzuj()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzey$zza r7 = (com.google.android.gms.internal.measurement.zzey.zza) r7     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zze$zza r7 = (com.google.android.gms.internal.measurement.zzbs.zze.zza) r7     // Catch:{ all -> 0x0efb }
            r30 = r13
            r13 = 1
            com.google.android.gms.internal.measurement.zzbs$zze$zza r7 = r7.zzam(r13)     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzgi r7 = r7.zzug()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzey r7 = (com.google.android.gms.internal.measurement.zzey) r7     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zze r7 = (com.google.android.gms.internal.measurement.zzbs.zze) r7     // Catch:{ all -> 0x0efb }
            r11.zza((int) r6, (com.google.android.gms.internal.measurement.zzbs.zze) r7)     // Catch:{ all -> 0x0efb }
            r10 = 1
            goto L_0x043f
        L_0x040f:
            r30 = r13
            java.lang.String r7 = "_r"
            com.google.android.gms.internal.measurement.zzbs$zze r13 = r11.zzl(r6)     // Catch:{ all -> 0x0efb }
            java.lang.String r13 = r13.getName()     // Catch:{ all -> 0x0efb }
            boolean r7 = r7.equals(r13)     // Catch:{ all -> 0x0efb }
            if (r7 == 0) goto L_0x043f
            com.google.android.gms.internal.measurement.zzbs$zze r7 = r11.zzl(r6)     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzey$zza r7 = r7.zzuj()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzey$zza r7 = (com.google.android.gms.internal.measurement.zzey.zza) r7     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zze$zza r7 = (com.google.android.gms.internal.measurement.zzbs.zze.zza) r7     // Catch:{ all -> 0x0efb }
            r12 = 1
            com.google.android.gms.internal.measurement.zzbs$zze$zza r7 = r7.zzam(r12)     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzgi r7 = r7.zzug()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzey r7 = (com.google.android.gms.internal.measurement.zzey) r7     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zze r7 = (com.google.android.gms.internal.measurement.zzbs.zze) r7     // Catch:{ all -> 0x0efb }
            r11.zza((int) r6, (com.google.android.gms.internal.measurement.zzbs.zze) r7)     // Catch:{ all -> 0x0efb }
            r12 = 1
        L_0x043f:
            int r6 = r6 + 1
            r7 = r29
            r13 = r30
            goto L_0x03d6
        L_0x0446:
            r29 = r7
            r30 = r13
            if (r10 != 0) goto L_0x047e
            if (r5 == 0) goto L_0x047e
            com.google.android.gms.measurement.internal.zzfj r6 = r1.zzj     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzef r6 = r6.zzab()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzeh r6 = r6.zzgs()     // Catch:{ all -> 0x0efb }
            java.lang.String r7 = "Marking event as conversion"
            com.google.android.gms.measurement.internal.zzfj r10 = r1.zzj     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzed r10 = r10.zzy()     // Catch:{ all -> 0x0efb }
            java.lang.String r13 = r11.getName()     // Catch:{ all -> 0x0efb }
            java.lang.String r10 = r10.a((java.lang.String) r13)     // Catch:{ all -> 0x0efb }
            r6.zza(r7, r10)     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zze$zza r6 = com.google.android.gms.internal.measurement.zzbs.zze.zzng()     // Catch:{ all -> 0x0efb }
            java.lang.String r7 = "_c"
            com.google.android.gms.internal.measurement.zzbs$zze$zza r6 = r6.zzbz(r7)     // Catch:{ all -> 0x0efb }
            r13 = 1
            com.google.android.gms.internal.measurement.zzbs$zze$zza r6 = r6.zzam(r13)     // Catch:{ all -> 0x0efb }
            r11.zza((com.google.android.gms.internal.measurement.zzbs.zze.zza) r6)     // Catch:{ all -> 0x0efb }
        L_0x047e:
            if (r12 != 0) goto L_0x04b0
            com.google.android.gms.measurement.internal.zzfj r6 = r1.zzj     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzef r6 = r6.zzab()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzeh r6 = r6.zzgs()     // Catch:{ all -> 0x0efb }
            java.lang.String r7 = "Marking event as real-time"
            com.google.android.gms.measurement.internal.zzfj r10 = r1.zzj     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzed r10 = r10.zzy()     // Catch:{ all -> 0x0efb }
            java.lang.String r12 = r11.getName()     // Catch:{ all -> 0x0efb }
            java.lang.String r10 = r10.a((java.lang.String) r12)     // Catch:{ all -> 0x0efb }
            r6.zza(r7, r10)     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zze$zza r6 = com.google.android.gms.internal.measurement.zzbs.zze.zzng()     // Catch:{ all -> 0x0efb }
            java.lang.String r7 = "_r"
            com.google.android.gms.internal.measurement.zzbs$zze$zza r6 = r6.zzbz(r7)     // Catch:{ all -> 0x0efb }
            r12 = 1
            com.google.android.gms.internal.measurement.zzbs$zze$zza r6 = r6.zzam(r12)     // Catch:{ all -> 0x0efb }
            r11.zza((com.google.android.gms.internal.measurement.zzbs.zze.zza) r6)     // Catch:{ all -> 0x0efb }
        L_0x04b0:
            com.google.android.gms.measurement.internal.zzx r32 = r60.zzgy()     // Catch:{ all -> 0x0efb }
            long r33 = r60.zzjk()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzg r6 = r2.a     // Catch:{ all -> 0x0efb }
            java.lang.String r35 = r6.zzag()     // Catch:{ all -> 0x0efb }
            r36 = 0
            r37 = 0
            r38 = 0
            r39 = 0
            r40 = 1
            com.google.android.gms.measurement.internal.zzw r6 = r32.zza(r33, r35, r36, r37, r38, r39, r40)     // Catch:{ all -> 0x0efb }
            long r6 = r6.e     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzfj r10 = r1.zzj     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzs r10 = r10.zzad()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzg r12 = r2.a     // Catch:{ all -> 0x0efb }
            java.lang.String r12 = r12.zzag()     // Catch:{ all -> 0x0efb }
            int r10 = r10.zzi(r12)     // Catch:{ all -> 0x0efb }
            long r12 = (long) r10     // Catch:{ all -> 0x0efb }
            int r10 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r10 <= 0) goto L_0x04eb
            java.lang.String r6 = "_r"
            zza((com.google.android.gms.internal.measurement.zzbs.zzc.zza) r11, (java.lang.String) r6)     // Catch:{ all -> 0x0efb }
            r12 = r23
            goto L_0x04ec
        L_0x04eb:
            r12 = 1
        L_0x04ec:
            java.lang.String r6 = r11.getName()     // Catch:{ all -> 0x0efb }
            boolean r6 = com.google.android.gms.measurement.internal.zzjs.a((java.lang.String) r6)     // Catch:{ all -> 0x0efb }
            if (r6 == 0) goto L_0x05c4
            if (r5 == 0) goto L_0x05c4
            com.google.android.gms.measurement.internal.zzx r32 = r60.zzgy()     // Catch:{ all -> 0x0efb }
            long r33 = r60.zzjk()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzg r6 = r2.a     // Catch:{ all -> 0x0efb }
            java.lang.String r35 = r6.zzag()     // Catch:{ all -> 0x0efb }
            r36 = 0
            r37 = 0
            r38 = 1
            r39 = 0
            r40 = 0
            com.google.android.gms.measurement.internal.zzw r6 = r32.zza(r33, r35, r36, r37, r38, r39, r40)     // Catch:{ all -> 0x0efb }
            long r6 = r6.c     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzfj r10 = r1.zzj     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzs r10 = r10.zzad()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzg r13 = r2.a     // Catch:{ all -> 0x0efb }
            java.lang.String r13 = r13.zzag()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzdu<java.lang.Integer> r14 = com.google.android.gms.measurement.internal.zzak.zzgs     // Catch:{ all -> 0x0efb }
            int r10 = r10.zzb(r13, r14)     // Catch:{ all -> 0x0efb }
            long r13 = (long) r10     // Catch:{ all -> 0x0efb }
            int r10 = (r6 > r13 ? 1 : (r6 == r13 ? 0 : -1))
            if (r10 <= 0) goto L_0x05c4
            com.google.android.gms.measurement.internal.zzfj r6 = r1.zzj     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzef r6 = r6.zzab()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzeh r6 = r6.zzgn()     // Catch:{ all -> 0x0efb }
            java.lang.String r7 = "Too many conversions. Not logging as conversion. appId"
            com.google.android.gms.internal.measurement.zzbs$zzg r10 = r2.a     // Catch:{ all -> 0x0efb }
            java.lang.String r10 = r10.zzag()     // Catch:{ all -> 0x0efb }
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r10)     // Catch:{ all -> 0x0efb }
            r6.zza(r7, r10)     // Catch:{ all -> 0x0efb }
            r6 = 0
            r7 = 0
            r10 = 0
            r13 = -1
        L_0x054a:
            int r14 = r11.zzmk()     // Catch:{ all -> 0x0efb }
            if (r6 >= r14) goto L_0x057e
            com.google.android.gms.internal.measurement.zzbs$zze r14 = r11.zzl(r6)     // Catch:{ all -> 0x0efb }
            java.lang.String r15 = "_c"
            r23 = r12
            java.lang.String r12 = r14.getName()     // Catch:{ all -> 0x0efb }
            boolean r12 = r15.equals(r12)     // Catch:{ all -> 0x0efb }
            if (r12 == 0) goto L_0x056c
            com.google.android.gms.internal.measurement.zzey$zza r10 = r14.zzuj()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzey$zza r10 = (com.google.android.gms.internal.measurement.zzey.zza) r10     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zze$zza r10 = (com.google.android.gms.internal.measurement.zzbs.zze.zza) r10     // Catch:{ all -> 0x0efb }
            r13 = r6
            goto L_0x0579
        L_0x056c:
            java.lang.String r12 = "_err"
            java.lang.String r14 = r14.getName()     // Catch:{ all -> 0x0efb }
            boolean r12 = r12.equals(r14)     // Catch:{ all -> 0x0efb }
            if (r12 == 0) goto L_0x0579
            r7 = 1
        L_0x0579:
            int r6 = r6 + 1
            r12 = r23
            goto L_0x054a
        L_0x057e:
            r23 = r12
            if (r7 == 0) goto L_0x0588
            if (r10 == 0) goto L_0x0588
            r11.zzm(r13)     // Catch:{ all -> 0x0efb }
            goto L_0x05c6
        L_0x0588:
            if (r10 == 0) goto L_0x05aa
            java.lang.Object r6 = r10.clone()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzey$zza r6 = (com.google.android.gms.internal.measurement.zzey.zza) r6     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zze$zza r6 = (com.google.android.gms.internal.measurement.zzbs.zze.zza) r6     // Catch:{ all -> 0x0efb }
            java.lang.String r7 = "_err"
            com.google.android.gms.internal.measurement.zzbs$zze$zza r6 = r6.zzbz(r7)     // Catch:{ all -> 0x0efb }
            r14 = 10
            com.google.android.gms.internal.measurement.zzbs$zze$zza r6 = r6.zzam(r14)     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzgi r6 = r6.zzug()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzey r6 = (com.google.android.gms.internal.measurement.zzey) r6     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zze r6 = (com.google.android.gms.internal.measurement.zzbs.zze) r6     // Catch:{ all -> 0x0efb }
            r11.zza((int) r13, (com.google.android.gms.internal.measurement.zzbs.zze) r6)     // Catch:{ all -> 0x0efb }
            goto L_0x05c6
        L_0x05aa:
            com.google.android.gms.measurement.internal.zzfj r6 = r1.zzj     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzef r6 = r6.zzab()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzeh r6 = r6.zzgk()     // Catch:{ all -> 0x0efb }
            java.lang.String r7 = "Did not find conversion parameter. appId"
            com.google.android.gms.internal.measurement.zzbs$zzg r10 = r2.a     // Catch:{ all -> 0x0efb }
            java.lang.String r10 = r10.zzag()     // Catch:{ all -> 0x0efb }
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r10)     // Catch:{ all -> 0x0efb }
            r6.zza(r7, r10)     // Catch:{ all -> 0x0efb }
            goto L_0x05c6
        L_0x05c4:
            r23 = r12
        L_0x05c6:
            com.google.android.gms.measurement.internal.zzfj r6 = r1.zzj     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzs r6 = r6.zzad()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzg r7 = r2.a     // Catch:{ all -> 0x0efb }
            java.lang.String r7 = r7.zzag()     // Catch:{ all -> 0x0efb }
            boolean r6 = r6.h(r7)     // Catch:{ all -> 0x0efb }
            if (r6 == 0) goto L_0x06a3
            if (r5 == 0) goto L_0x06a3
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x0efb }
            java.util.List r6 = r11.zzmj()     // Catch:{ all -> 0x0efb }
            r5.<init>(r6)     // Catch:{ all -> 0x0efb }
            r6 = 0
            r7 = -1
            r10 = -1
        L_0x05e6:
            int r12 = r5.size()     // Catch:{ all -> 0x0efb }
            if (r6 >= r12) goto L_0x0616
            java.lang.String r12 = "value"
            java.lang.Object r13 = r5.get(r6)     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zze r13 = (com.google.android.gms.internal.measurement.zzbs.zze) r13     // Catch:{ all -> 0x0efb }
            java.lang.String r13 = r13.getName()     // Catch:{ all -> 0x0efb }
            boolean r12 = r12.equals(r13)     // Catch:{ all -> 0x0efb }
            if (r12 == 0) goto L_0x0600
            r7 = r6
            goto L_0x0613
        L_0x0600:
            java.lang.String r12 = "currency"
            java.lang.Object r13 = r5.get(r6)     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zze r13 = (com.google.android.gms.internal.measurement.zzbs.zze) r13     // Catch:{ all -> 0x0efb }
            java.lang.String r13 = r13.getName()     // Catch:{ all -> 0x0efb }
            boolean r12 = r12.equals(r13)     // Catch:{ all -> 0x0efb }
            if (r12 == 0) goto L_0x0613
            r10 = r6
        L_0x0613:
            int r6 = r6 + 1
            goto L_0x05e6
        L_0x0616:
            r6 = -1
            if (r7 == r6) goto L_0x06a4
            java.lang.Object r6 = r5.get(r7)     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zze r6 = (com.google.android.gms.internal.measurement.zzbs.zze) r6     // Catch:{ all -> 0x0efb }
            boolean r6 = r6.zzna()     // Catch:{ all -> 0x0efb }
            if (r6 != 0) goto L_0x0650
            java.lang.Object r6 = r5.get(r7)     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zze r6 = (com.google.android.gms.internal.measurement.zzbs.zze) r6     // Catch:{ all -> 0x0efb }
            boolean r6 = r6.zznd()     // Catch:{ all -> 0x0efb }
            if (r6 != 0) goto L_0x0650
            com.google.android.gms.measurement.internal.zzfj r5 = r1.zzj     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzef r5 = r5.zzab()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzeh r5 = r5.zzgp()     // Catch:{ all -> 0x0efb }
            java.lang.String r6 = "Value must be specified with a numeric type."
            r5.zzao(r6)     // Catch:{ all -> 0x0efb }
            r11.zzm(r7)     // Catch:{ all -> 0x0efb }
            java.lang.String r5 = "_c"
            zza((com.google.android.gms.internal.measurement.zzbs.zzc.zza) r11, (java.lang.String) r5)     // Catch:{ all -> 0x0efb }
            r5 = 18
            java.lang.String r6 = "value"
            zza((com.google.android.gms.internal.measurement.zzbs.zzc.zza) r11, (int) r5, (java.lang.String) r6)     // Catch:{ all -> 0x0efb }
            goto L_0x06a3
        L_0x0650:
            r6 = -1
            if (r10 != r6) goto L_0x0656
            r5 = 1
            r12 = 3
            goto L_0x0682
        L_0x0656:
            java.lang.Object r5 = r5.get(r10)     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zze r5 = (com.google.android.gms.internal.measurement.zzbs.zze) r5     // Catch:{ all -> 0x0efb }
            java.lang.String r5 = r5.zzmy()     // Catch:{ all -> 0x0efb }
            int r10 = r5.length()     // Catch:{ all -> 0x0efb }
            r12 = 3
            if (r10 == r12) goto L_0x0669
        L_0x0667:
            r5 = 1
            goto L_0x0682
        L_0x0669:
            r10 = 0
        L_0x066a:
            int r13 = r5.length()     // Catch:{ all -> 0x0efb }
            if (r10 >= r13) goto L_0x0681
            int r13 = r5.codePointAt(r10)     // Catch:{ all -> 0x0efb }
            boolean r14 = java.lang.Character.isLetter(r13)     // Catch:{ all -> 0x0efb }
            if (r14 != 0) goto L_0x067b
            goto L_0x0667
        L_0x067b:
            int r13 = java.lang.Character.charCount(r13)     // Catch:{ all -> 0x0efb }
            int r10 = r10 + r13
            goto L_0x066a
        L_0x0681:
            r5 = 0
        L_0x0682:
            if (r5 == 0) goto L_0x06a5
            com.google.android.gms.measurement.internal.zzfj r5 = r1.zzj     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzef r5 = r5.zzab()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzeh r5 = r5.zzgp()     // Catch:{ all -> 0x0efb }
            java.lang.String r10 = "Value parameter discarded. You must also supply a 3-letter ISO_4217 currency code in the currency parameter."
            r5.zzao(r10)     // Catch:{ all -> 0x0efb }
            r11.zzm(r7)     // Catch:{ all -> 0x0efb }
            java.lang.String r5 = "_c"
            zza((com.google.android.gms.internal.measurement.zzbs.zzc.zza) r11, (java.lang.String) r5)     // Catch:{ all -> 0x0efb }
            r5 = 19
            java.lang.String r7 = "currency"
            zza((com.google.android.gms.internal.measurement.zzbs.zzc.zza) r11, (int) r5, (java.lang.String) r7)     // Catch:{ all -> 0x0efb }
            goto L_0x06a5
        L_0x06a3:
            r6 = -1
        L_0x06a4:
            r12 = 3
        L_0x06a5:
            com.google.android.gms.measurement.internal.zzfj r5 = r1.zzj     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzs r5 = r5.zzad()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzg r7 = r2.a     // Catch:{ all -> 0x0efb }
            java.lang.String r7 = r7.zzag()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r10 = com.google.android.gms.measurement.internal.zzak.zzih     // Catch:{ all -> 0x0efb }
            boolean r5 = r5.zze(r7, r10)     // Catch:{ all -> 0x0efb }
            if (r5 == 0) goto L_0x0752
            java.lang.String r5 = "_e"
            java.lang.String r7 = r11.getName()     // Catch:{ all -> 0x0efb }
            boolean r5 = r5.equals(r7)     // Catch:{ all -> 0x0efb }
            r13 = 1000(0x3e8, double:4.94E-321)
            if (r5 == 0) goto L_0x0709
            r60.zzgw()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzgi r5 = r11.zzug()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzey r5 = (com.google.android.gms.internal.measurement.zzey) r5     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzc r5 = (com.google.android.gms.internal.measurement.zzbs.zzc) r5     // Catch:{ all -> 0x0efb }
            java.lang.String r7 = "_fr"
            com.google.android.gms.internal.measurement.zzbs$zze r5 = com.google.android.gms.measurement.internal.zzjo.a((com.google.android.gms.internal.measurement.zzbs.zzc) r5, (java.lang.String) r7)     // Catch:{ all -> 0x0efb }
            if (r5 != 0) goto L_0x0752
            if (r19 == 0) goto L_0x0704
            long r26 = r19.getTimestampMillis()     // Catch:{ all -> 0x0efb }
            long r32 = r11.getTimestampMillis()     // Catch:{ all -> 0x0efb }
            long r26 = r26 - r32
            long r26 = java.lang.Math.abs(r26)     // Catch:{ all -> 0x0efb }
            int r5 = (r26 > r13 ? 1 : (r26 == r13 ? 0 : -1))
            if (r5 > 0) goto L_0x0704
            java.lang.Object r5 = r19.clone()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzey$zza r5 = (com.google.android.gms.internal.measurement.zzey.zza) r5     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzc$zza r5 = (com.google.android.gms.internal.measurement.zzbs.zzc.zza) r5     // Catch:{ all -> 0x0efb }
            boolean r7 = r1.zza((com.google.android.gms.internal.measurement.zzbs.zzc.zza) r11, (com.google.android.gms.internal.measurement.zzbs.zzc.zza) r5)     // Catch:{ all -> 0x0efb }
            if (r7 == 0) goto L_0x0704
            r3.zza((int) r9, (com.google.android.gms.internal.measurement.zzbs.zzc.zza) r5)     // Catch:{ all -> 0x0efb }
        L_0x06ff:
            r18 = 0
            r19 = 0
            goto L_0x0752
        L_0x0704:
            r18 = r11
            r8 = r25
            goto L_0x0752
        L_0x0709:
            java.lang.String r5 = "_vs"
            java.lang.String r7 = r11.getName()     // Catch:{ all -> 0x0efb }
            boolean r5 = r5.equals(r7)     // Catch:{ all -> 0x0efb }
            if (r5 == 0) goto L_0x0752
            r60.zzgw()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzgi r5 = r11.zzug()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzey r5 = (com.google.android.gms.internal.measurement.zzey) r5     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzc r5 = (com.google.android.gms.internal.measurement.zzbs.zzc) r5     // Catch:{ all -> 0x0efb }
            java.lang.String r7 = "_et"
            com.google.android.gms.internal.measurement.zzbs$zze r5 = com.google.android.gms.measurement.internal.zzjo.a((com.google.android.gms.internal.measurement.zzbs.zzc) r5, (java.lang.String) r7)     // Catch:{ all -> 0x0efb }
            if (r5 != 0) goto L_0x0752
            if (r18 == 0) goto L_0x074e
            long r26 = r18.getTimestampMillis()     // Catch:{ all -> 0x0efb }
            long r32 = r11.getTimestampMillis()     // Catch:{ all -> 0x0efb }
            long r26 = r26 - r32
            long r26 = java.lang.Math.abs(r26)     // Catch:{ all -> 0x0efb }
            int r5 = (r26 > r13 ? 1 : (r26 == r13 ? 0 : -1))
            if (r5 > 0) goto L_0x074e
            java.lang.Object r5 = r18.clone()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzey$zza r5 = (com.google.android.gms.internal.measurement.zzey.zza) r5     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzc$zza r5 = (com.google.android.gms.internal.measurement.zzbs.zzc.zza) r5     // Catch:{ all -> 0x0efb }
            boolean r7 = r1.zza((com.google.android.gms.internal.measurement.zzbs.zzc.zza) r5, (com.google.android.gms.internal.measurement.zzbs.zzc.zza) r11)     // Catch:{ all -> 0x0efb }
            if (r7 == 0) goto L_0x074e
            r3.zza((int) r8, (com.google.android.gms.internal.measurement.zzbs.zzc.zza) r5)     // Catch:{ all -> 0x0efb }
            goto L_0x06ff
        L_0x074e:
            r19 = r11
            r9 = r25
        L_0x0752:
            if (r4 != 0) goto L_0x07b3
            java.lang.String r5 = "_e"
            java.lang.String r7 = r11.getName()     // Catch:{ all -> 0x0efb }
            boolean r5 = r5.equals(r7)     // Catch:{ all -> 0x0efb }
            if (r5 == 0) goto L_0x07b3
            int r5 = r11.zzmk()     // Catch:{ all -> 0x0efb }
            if (r5 != 0) goto L_0x0780
            com.google.android.gms.measurement.internal.zzfj r5 = r1.zzj     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzef r5 = r5.zzab()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzeh r5 = r5.zzgn()     // Catch:{ all -> 0x0efb }
            java.lang.String r7 = "Engagement event does not contain any parameters. appId"
            com.google.android.gms.internal.measurement.zzbs$zzg r10 = r2.a     // Catch:{ all -> 0x0efb }
            java.lang.String r10 = r10.zzag()     // Catch:{ all -> 0x0efb }
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r10)     // Catch:{ all -> 0x0efb }
        L_0x077c:
            r5.zza(r7, r10)     // Catch:{ all -> 0x0efb }
            goto L_0x07b3
        L_0x0780:
            r60.zzgw()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzgi r5 = r11.zzug()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzey r5 = (com.google.android.gms.internal.measurement.zzey) r5     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzc r5 = (com.google.android.gms.internal.measurement.zzbs.zzc) r5     // Catch:{ all -> 0x0efb }
            java.lang.String r7 = "_et"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzjo.b(r5, r7)     // Catch:{ all -> 0x0efb }
            java.lang.Long r5 = (java.lang.Long) r5     // Catch:{ all -> 0x0efb }
            if (r5 != 0) goto L_0x07ac
            com.google.android.gms.measurement.internal.zzfj r5 = r1.zzj     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzef r5 = r5.zzab()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzeh r5 = r5.zzgn()     // Catch:{ all -> 0x0efb }
            java.lang.String r7 = "Engagement event does not include duration. appId"
            com.google.android.gms.internal.measurement.zzbs$zzg r10 = r2.a     // Catch:{ all -> 0x0efb }
            java.lang.String r10 = r10.zzag()     // Catch:{ all -> 0x0efb }
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r10)     // Catch:{ all -> 0x0efb }
            goto L_0x077c
        L_0x07ac:
            long r13 = r5.longValue()     // Catch:{ all -> 0x0efb }
            long r13 = r30 + r13
            goto L_0x07b5
        L_0x07b3:
            r13 = r30
        L_0x07b5:
            java.util.List<com.google.android.gms.internal.measurement.zzbs$zzc> r5 = r2.c     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzgi r7 = r11.zzug()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzey r7 = (com.google.android.gms.internal.measurement.zzey) r7     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzc r7 = (com.google.android.gms.internal.measurement.zzbs.zzc) r7     // Catch:{ all -> 0x0efb }
            r10 = r29
            r5.set(r10, r7)     // Catch:{ all -> 0x0efb }
            int r15 = r25 + 1
            r3.zza((com.google.android.gms.internal.measurement.zzbs.zzc.zza) r11)     // Catch:{ all -> 0x0efb }
        L_0x07c9:
            int r7 = r10 + 1
            r12 = r23
            goto L_0x02c6
        L_0x07cf:
            r23 = r12
            r30 = r13
            r25 = r15
            if (r4 == 0) goto L_0x0830
            r5 = r25
            r13 = r30
            r4 = 0
        L_0x07dc:
            if (r4 >= r5) goto L_0x0832
            com.google.android.gms.internal.measurement.zzbs$zzc r6 = r3.zzq(r4)     // Catch:{ all -> 0x0efb }
            java.lang.String r7 = "_e"
            java.lang.String r8 = r6.getName()     // Catch:{ all -> 0x0efb }
            boolean r7 = r7.equals(r8)     // Catch:{ all -> 0x0efb }
            if (r7 == 0) goto L_0x0801
            r60.zzgw()     // Catch:{ all -> 0x0efb }
            java.lang.String r7 = "_fr"
            com.google.android.gms.internal.measurement.zzbs$zze r7 = com.google.android.gms.measurement.internal.zzjo.a((com.google.android.gms.internal.measurement.zzbs.zzc) r6, (java.lang.String) r7)     // Catch:{ all -> 0x0efb }
            if (r7 == 0) goto L_0x0801
            r3.zzr(r4)     // Catch:{ all -> 0x0efb }
            int r5 = r5 + -1
            int r4 = r4 + -1
            goto L_0x082d
        L_0x0801:
            r60.zzgw()     // Catch:{ all -> 0x0efb }
            java.lang.String r7 = "_et"
            com.google.android.gms.internal.measurement.zzbs$zze r6 = com.google.android.gms.measurement.internal.zzjo.a((com.google.android.gms.internal.measurement.zzbs.zzc) r6, (java.lang.String) r7)     // Catch:{ all -> 0x0efb }
            if (r6 == 0) goto L_0x082d
            boolean r7 = r6.zzna()     // Catch:{ all -> 0x0efb }
            if (r7 == 0) goto L_0x081b
            long r6 = r6.zznb()     // Catch:{ all -> 0x0efb }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0efb }
            goto L_0x081c
        L_0x081b:
            r6 = 0
        L_0x081c:
            if (r6 == 0) goto L_0x082d
            long r7 = r6.longValue()     // Catch:{ all -> 0x0efb }
            r9 = 0
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 <= 0) goto L_0x082d
            long r6 = r6.longValue()     // Catch:{ all -> 0x0efb }
            long r13 = r13 + r6
        L_0x082d:
            r6 = 1
            int r4 = r4 + r6
            goto L_0x07dc
        L_0x0830:
            r13 = r30
        L_0x0832:
            r4 = 0
            r1.zza((com.google.android.gms.internal.measurement.zzbs.zzg.zza) r3, (long) r13, (boolean) r4)     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzfj r4 = r1.zzj     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzs r4 = r4.zzad()     // Catch:{ all -> 0x0efb }
            java.lang.String r5 = r3.zzag()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r6 = com.google.android.gms.measurement.internal.zzak.zzja     // Catch:{ all -> 0x0efb }
            boolean r4 = r4.zze(r5, r6)     // Catch:{ all -> 0x0efb }
            if (r4 == 0) goto L_0x087f
            java.util.List r4 = r3.zznl()     // Catch:{ all -> 0x0efb }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x0efb }
        L_0x0850:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x0efb }
            if (r5 == 0) goto L_0x086a
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzc r5 = (com.google.android.gms.internal.measurement.zzbs.zzc) r5     // Catch:{ all -> 0x0efb }
            java.lang.String r6 = "_s"
            java.lang.String r5 = r5.getName()     // Catch:{ all -> 0x0efb }
            boolean r5 = r6.equals(r5)     // Catch:{ all -> 0x0efb }
            if (r5 == 0) goto L_0x0850
            r4 = 1
            goto L_0x086b
        L_0x086a:
            r4 = 0
        L_0x086b:
            if (r4 == 0) goto L_0x087a
            com.google.android.gms.measurement.internal.zzx r4 = r60.zzgy()     // Catch:{ all -> 0x0efb }
            java.lang.String r5 = r3.zzag()     // Catch:{ all -> 0x0efb }
            java.lang.String r6 = "_se"
            r4.zzd(r5, r6)     // Catch:{ all -> 0x0efb }
        L_0x087a:
            r4 = 1
            r1.zza((com.google.android.gms.internal.measurement.zzbs.zzg.zza) r3, (long) r13, (boolean) r4)     // Catch:{ all -> 0x0efb }
            goto L_0x089e
        L_0x087f:
            com.google.android.gms.measurement.internal.zzfj r4 = r1.zzj     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzs r4 = r4.zzad()     // Catch:{ all -> 0x0efb }
            java.lang.String r5 = r3.zzag()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r6 = com.google.android.gms.measurement.internal.zzak.zzjb     // Catch:{ all -> 0x0efb }
            boolean r4 = r4.zze(r5, r6)     // Catch:{ all -> 0x0efb }
            if (r4 == 0) goto L_0x089e
            com.google.android.gms.measurement.internal.zzx r4 = r60.zzgy()     // Catch:{ all -> 0x0efb }
            java.lang.String r5 = r3.zzag()     // Catch:{ all -> 0x0efb }
            java.lang.String r6 = "_se"
            r4.zzd(r5, r6)     // Catch:{ all -> 0x0efb }
        L_0x089e:
            com.google.android.gms.measurement.internal.zzfj r4 = r1.zzj     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzs r4 = r4.zzad()     // Catch:{ all -> 0x0efb }
            java.lang.String r5 = r3.zzag()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r6 = com.google.android.gms.measurement.internal.zzak.zzij     // Catch:{ all -> 0x0efb }
            boolean r4 = r4.zze(r5, r6)     // Catch:{ all -> 0x0efb }
            if (r4 == 0) goto L_0x0943
            com.google.android.gms.measurement.internal.zzjo r4 = r60.zzgw()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzef r5 = r4.zzab()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzeh r5 = r5.zzgs()     // Catch:{ all -> 0x0efb }
            java.lang.String r6 = "Checking account type status for ad personalization signals"
            r5.zzao(r6)     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzfd r5 = r4.zzgz()     // Catch:{ all -> 0x0efb }
            java.lang.String r6 = r3.zzag()     // Catch:{ all -> 0x0efb }
            boolean r5 = r5.e(r6)     // Catch:{ all -> 0x0efb }
            if (r5 == 0) goto L_0x0943
            com.google.android.gms.measurement.internal.zzx r5 = r4.zzgy()     // Catch:{ all -> 0x0efb }
            java.lang.String r6 = r3.zzag()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzf r5 = r5.zzab(r6)     // Catch:{ all -> 0x0efb }
            if (r5 == 0) goto L_0x0943
            boolean r5 = r5.zzbe()     // Catch:{ all -> 0x0efb }
            if (r5 == 0) goto L_0x0943
            com.google.android.gms.measurement.internal.zzac r5 = r4.zzw()     // Catch:{ all -> 0x0efb }
            boolean r5 = r5.d()     // Catch:{ all -> 0x0efb }
            if (r5 == 0) goto L_0x0943
            com.google.android.gms.measurement.internal.zzef r5 = r4.zzab()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzeh r5 = r5.zzgr()     // Catch:{ all -> 0x0efb }
            java.lang.String r6 = "Turning off ad personalization due to account type"
            r5.zzao(r6)     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzk$zza r5 = com.google.android.gms.internal.measurement.zzbs.zzk.zzqu()     // Catch:{ all -> 0x0efb }
            java.lang.String r6 = "_npa"
            com.google.android.gms.internal.measurement.zzbs$zzk$zza r5 = r5.zzdb(r6)     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzac r4 = r4.zzw()     // Catch:{ all -> 0x0efb }
            long r6 = r4.c_()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzk$zza r4 = r5.zzbk(r6)     // Catch:{ all -> 0x0efb }
            r5 = 1
            com.google.android.gms.internal.measurement.zzbs$zzk$zza r4 = r4.zzbl(r5)     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzgi r4 = r4.zzug()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzey r4 = (com.google.android.gms.internal.measurement.zzey) r4     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzk r4 = (com.google.android.gms.internal.measurement.zzbs.zzk) r4     // Catch:{ all -> 0x0efb }
            r5 = 0
        L_0x091f:
            int r6 = r3.zznp()     // Catch:{ all -> 0x0efb }
            if (r5 >= r6) goto L_0x093d
            java.lang.String r6 = "_npa"
            com.google.android.gms.internal.measurement.zzbs$zzk r7 = r3.zzs(r5)     // Catch:{ all -> 0x0efb }
            java.lang.String r7 = r7.getName()     // Catch:{ all -> 0x0efb }
            boolean r6 = r6.equals(r7)     // Catch:{ all -> 0x0efb }
            if (r6 == 0) goto L_0x093a
            r3.zza((int) r5, (com.google.android.gms.internal.measurement.zzbs.zzk) r4)     // Catch:{ all -> 0x0efb }
            r5 = 1
            goto L_0x093e
        L_0x093a:
            int r5 = r5 + 1
            goto L_0x091f
        L_0x093d:
            r5 = 0
        L_0x093e:
            if (r5 != 0) goto L_0x0943
            r3.zza((com.google.android.gms.internal.measurement.zzbs.zzk) r4)     // Catch:{ all -> 0x0efb }
        L_0x0943:
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r4 = r3.zznv()     // Catch:{ all -> 0x0efb }
            java.lang.String r5 = r3.zzag()     // Catch:{ all -> 0x0efb }
            java.util.List r6 = r3.zzno()     // Catch:{ all -> 0x0efb }
            java.util.List r7 = r3.zznl()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r5)     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzp r8 = r60.zzgx()     // Catch:{ all -> 0x0efb }
            java.util.List r5 = r8.a(r5, r7, r6)     // Catch:{ all -> 0x0efb }
            r4.zzc(r5)     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzfj r4 = r1.zzj     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzs r4 = r4.zzad()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzg r5 = r2.a     // Catch:{ all -> 0x0efb }
            java.lang.String r5 = r5.zzag()     // Catch:{ all -> 0x0efb }
            boolean r4 = r4.zzm(r5)     // Catch:{ all -> 0x0efb }
            if (r4 == 0) goto L_0x0d03
            java.util.HashMap r4 = new java.util.HashMap     // Catch:{ all -> 0x0cfd }
            r4.<init>()     // Catch:{ all -> 0x0cfd }
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x0cfd }
            r5.<init>()     // Catch:{ all -> 0x0cfd }
            com.google.android.gms.measurement.internal.zzfj r6 = r1.zzj     // Catch:{ all -> 0x0cfd }
            com.google.android.gms.measurement.internal.zzjs r6 = r6.zzz()     // Catch:{ all -> 0x0cfd }
            java.security.SecureRandom r6 = r6.c()     // Catch:{ all -> 0x0cfd }
            r7 = 0
        L_0x0988:
            int r8 = r3.zznm()     // Catch:{ all -> 0x0cfd }
            if (r7 >= r8) goto L_0x0cc8
            com.google.android.gms.internal.measurement.zzbs$zzc r8 = r3.zzq(r7)     // Catch:{ all -> 0x0cfd }
            com.google.android.gms.internal.measurement.zzey$zza r8 = r8.zzuj()     // Catch:{ all -> 0x0cfd }
            com.google.android.gms.internal.measurement.zzey$zza r8 = (com.google.android.gms.internal.measurement.zzey.zza) r8     // Catch:{ all -> 0x0cfd }
            com.google.android.gms.internal.measurement.zzbs$zzc$zza r8 = (com.google.android.gms.internal.measurement.zzbs.zzc.zza) r8     // Catch:{ all -> 0x0cfd }
            java.lang.String r9 = r8.getName()     // Catch:{ all -> 0x0cfd }
            java.lang.String r10 = "_ep"
            boolean r9 = r9.equals(r10)     // Catch:{ all -> 0x0cfd }
            if (r9 == 0) goto L_0x0a1b
            r60.zzgw()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzgi r9 = r8.zzug()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzey r9 = (com.google.android.gms.internal.measurement.zzey) r9     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzc r9 = (com.google.android.gms.internal.measurement.zzbs.zzc) r9     // Catch:{ all -> 0x0efb }
            java.lang.String r10 = "_en"
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzjo.b(r9, r10)     // Catch:{ all -> 0x0efb }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ all -> 0x0efb }
            java.lang.Object r10 = r4.get(r9)     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzae r10 = (com.google.android.gms.measurement.internal.zzae) r10     // Catch:{ all -> 0x0efb }
            if (r10 != 0) goto L_0x09d2
            com.google.android.gms.measurement.internal.zzx r10 = r60.zzgy()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzg r11 = r2.a     // Catch:{ all -> 0x0efb }
            java.lang.String r11 = r11.zzag()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzae r10 = r10.zzc(r11, r9)     // Catch:{ all -> 0x0efb }
            r4.put(r9, r10)     // Catch:{ all -> 0x0efb }
        L_0x09d2:
            java.lang.Long r9 = r10.i     // Catch:{ all -> 0x0efb }
            if (r9 != 0) goto L_0x0a11
            java.lang.Long r9 = r10.j     // Catch:{ all -> 0x0efb }
            long r11 = r9.longValue()     // Catch:{ all -> 0x0efb }
            r13 = 1
            int r9 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r9 <= 0) goto L_0x09ec
            r60.zzgw()     // Catch:{ all -> 0x0efb }
            java.lang.String r9 = "_sr"
            java.lang.Long r11 = r10.j     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzjo.a(r8, r9, r11)     // Catch:{ all -> 0x0efb }
        L_0x09ec:
            java.lang.Boolean r9 = r10.k     // Catch:{ all -> 0x0efb }
            if (r9 == 0) goto L_0x0a06
            java.lang.Boolean r9 = r10.k     // Catch:{ all -> 0x0efb }
            boolean r9 = r9.booleanValue()     // Catch:{ all -> 0x0efb }
            if (r9 == 0) goto L_0x0a06
            r60.zzgw()     // Catch:{ all -> 0x0efb }
            java.lang.String r9 = "_efs"
            r10 = 1
            java.lang.Long r12 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzjo.a(r8, r9, r12)     // Catch:{ all -> 0x0efb }
        L_0x0a06:
            com.google.android.gms.internal.measurement.zzgi r9 = r8.zzug()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzey r9 = (com.google.android.gms.internal.measurement.zzey) r9     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzc r9 = (com.google.android.gms.internal.measurement.zzbs.zzc) r9     // Catch:{ all -> 0x0efb }
            r5.add(r9)     // Catch:{ all -> 0x0efb }
        L_0x0a11:
            r3.zza((int) r7, (com.google.android.gms.internal.measurement.zzbs.zzc.zza) r8)     // Catch:{ all -> 0x0efb }
            r21 = r2
            r61 = r6
            r1 = r7
            goto L_0x0cbe
        L_0x0a1b:
            com.google.android.gms.measurement.internal.zzfd r9 = r60.zzgz()     // Catch:{ all -> 0x0cfd }
            com.google.android.gms.internal.measurement.zzbs$zzg r10 = r2.a     // Catch:{ all -> 0x0cfd }
            java.lang.String r10 = r10.zzag()     // Catch:{ all -> 0x0cfd }
            long r9 = r9.f(r10)     // Catch:{ all -> 0x0cfd }
            com.google.android.gms.measurement.internal.zzfj r11 = r1.zzj     // Catch:{ all -> 0x0cfd }
            r11.zzz()     // Catch:{ all -> 0x0cfd }
            long r11 = r8.getTimestampMillis()     // Catch:{ all -> 0x0cfd }
            long r11 = com.google.android.gms.measurement.internal.zzjs.zzc(r11, r9)     // Catch:{ all -> 0x0cfd }
            com.google.android.gms.internal.measurement.zzgi r13 = r8.zzug()     // Catch:{ all -> 0x0cfd }
            com.google.android.gms.internal.measurement.zzey r13 = (com.google.android.gms.internal.measurement.zzey) r13     // Catch:{ all -> 0x0cfd }
            com.google.android.gms.internal.measurement.zzbs$zzc r13 = (com.google.android.gms.internal.measurement.zzbs.zzc) r13     // Catch:{ all -> 0x0cfd }
            java.lang.String r14 = "_dbg"
            r18 = 1
            java.lang.Long r15 = java.lang.Long.valueOf(r18)     // Catch:{ all -> 0x0cfd }
            boolean r18 = android.text.TextUtils.isEmpty(r14)     // Catch:{ all -> 0x0cfd }
            if (r18 != 0) goto L_0x0aa6
            if (r15 != 0) goto L_0x0a4f
            goto L_0x0aa6
        L_0x0a4f:
            java.util.List r13 = r13.zzmj()     // Catch:{ all -> 0x0efb }
            java.util.Iterator r13 = r13.iterator()     // Catch:{ all -> 0x0efb }
        L_0x0a57:
            boolean r18 = r13.hasNext()     // Catch:{ all -> 0x0efb }
            if (r18 == 0) goto L_0x0aa6
            java.lang.Object r18 = r13.next()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zze r18 = (com.google.android.gms.internal.measurement.zzbs.zze) r18     // Catch:{ all -> 0x0efb }
            r61 = r13
            java.lang.String r13 = r18.getName()     // Catch:{ all -> 0x0efb }
            boolean r13 = r14.equals(r13)     // Catch:{ all -> 0x0efb }
            if (r13 == 0) goto L_0x0aa3
            boolean r13 = r15 instanceof java.lang.Long     // Catch:{ all -> 0x0efb }
            if (r13 == 0) goto L_0x0a81
            long r13 = r18.zznb()     // Catch:{ all -> 0x0efb }
            java.lang.Long r13 = java.lang.Long.valueOf(r13)     // Catch:{ all -> 0x0efb }
            boolean r13 = r15.equals(r13)     // Catch:{ all -> 0x0efb }
            if (r13 != 0) goto L_0x0aa1
        L_0x0a81:
            boolean r13 = r15 instanceof java.lang.String     // Catch:{ all -> 0x0efb }
            if (r13 == 0) goto L_0x0a8f
            java.lang.String r13 = r18.zzmy()     // Catch:{ all -> 0x0efb }
            boolean r13 = r15.equals(r13)     // Catch:{ all -> 0x0efb }
            if (r13 != 0) goto L_0x0aa1
        L_0x0a8f:
            boolean r13 = r15 instanceof java.lang.Double     // Catch:{ all -> 0x0efb }
            if (r13 == 0) goto L_0x0aa6
            double r13 = r18.zzne()     // Catch:{ all -> 0x0efb }
            java.lang.Double r13 = java.lang.Double.valueOf(r13)     // Catch:{ all -> 0x0efb }
            boolean r13 = r15.equals(r13)     // Catch:{ all -> 0x0efb }
            if (r13 == 0) goto L_0x0aa6
        L_0x0aa1:
            r13 = 1
            goto L_0x0aa7
        L_0x0aa3:
            r13 = r61
            goto L_0x0a57
        L_0x0aa6:
            r13 = 0
        L_0x0aa7:
            if (r13 != 0) goto L_0x0abc
            com.google.android.gms.measurement.internal.zzfd r13 = r60.zzgz()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzg r14 = r2.a     // Catch:{ all -> 0x0efb }
            java.lang.String r14 = r14.zzag()     // Catch:{ all -> 0x0efb }
            java.lang.String r15 = r8.getName()     // Catch:{ all -> 0x0efb }
            int r13 = r13.c(r14, r15)     // Catch:{ all -> 0x0efb }
            goto L_0x0abd
        L_0x0abc:
            r13 = 1
        L_0x0abd:
            if (r13 > 0) goto L_0x0ae3
            com.google.android.gms.measurement.internal.zzfj r9 = r1.zzj     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzef r9 = r9.zzab()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzeh r9 = r9.zzgn()     // Catch:{ all -> 0x0efb }
            java.lang.String r10 = "Sample rate must be positive. event, rate"
            java.lang.String r11 = r8.getName()     // Catch:{ all -> 0x0efb }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r13)     // Catch:{ all -> 0x0efb }
            r9.zza(r10, r11, r12)     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzgi r9 = r8.zzug()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzey r9 = (com.google.android.gms.internal.measurement.zzey) r9     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzc r9 = (com.google.android.gms.internal.measurement.zzbs.zzc) r9     // Catch:{ all -> 0x0efb }
            r5.add(r9)     // Catch:{ all -> 0x0efb }
            goto L_0x0a11
        L_0x0ae3:
            java.lang.String r14 = r8.getName()     // Catch:{ all -> 0x0cfd }
            java.lang.Object r14 = r4.get(r14)     // Catch:{ all -> 0x0cfd }
            com.google.android.gms.measurement.internal.zzae r14 = (com.google.android.gms.measurement.internal.zzae) r14     // Catch:{ all -> 0x0cfd }
            if (r14 != 0) goto L_0x0b7d
            com.google.android.gms.measurement.internal.zzx r14 = r60.zzgy()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzg r15 = r2.a     // Catch:{ all -> 0x0efb }
            java.lang.String r15 = r15.zzag()     // Catch:{ all -> 0x0efb }
            r18 = r9
            java.lang.String r9 = r8.getName()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzae r14 = r14.zzc(r15, r9)     // Catch:{ all -> 0x0efb }
            if (r14 != 0) goto L_0x0b7f
            com.google.android.gms.measurement.internal.zzfj r9 = r1.zzj     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzef r9 = r9.zzab()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzeh r9 = r9.zzgn()     // Catch:{ all -> 0x0efb }
            java.lang.String r10 = "Event being bundled has no eventAggregate. appId, eventName"
            com.google.android.gms.internal.measurement.zzbs$zzg r14 = r2.a     // Catch:{ all -> 0x0efb }
            java.lang.String r14 = r14.zzag()     // Catch:{ all -> 0x0efb }
            java.lang.String r15 = r8.getName()     // Catch:{ all -> 0x0efb }
            r9.zza(r10, r14, r15)     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzfj r9 = r1.zzj     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzs r9 = r9.zzad()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzg r10 = r2.a     // Catch:{ all -> 0x0efb }
            java.lang.String r10 = r10.zzag()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r14 = com.google.android.gms.measurement.internal.zzak.zziz     // Catch:{ all -> 0x0efb }
            boolean r9 = r9.zze(r10, r14)     // Catch:{ all -> 0x0efb }
            if (r9 == 0) goto L_0x0b58
            com.google.android.gms.measurement.internal.zzae r9 = new com.google.android.gms.measurement.internal.zzae     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzg r10 = r2.a     // Catch:{ all -> 0x0efb }
            java.lang.String r29 = r10.zzag()     // Catch:{ all -> 0x0efb }
            java.lang.String r30 = r8.getName()     // Catch:{ all -> 0x0efb }
            r31 = 1
            r33 = 1
            r35 = 1
            long r37 = r8.getTimestampMillis()     // Catch:{ all -> 0x0efb }
            r39 = 0
            r41 = 0
            r42 = 0
            r43 = 0
            r44 = 0
            r28 = r9
            r28.<init>(r29, r30, r31, r33, r35, r37, r39, r41, r42, r43, r44)     // Catch:{ all -> 0x0efb }
            goto L_0x0b7b
        L_0x0b58:
            com.google.android.gms.measurement.internal.zzae r9 = new com.google.android.gms.measurement.internal.zzae     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzg r10 = r2.a     // Catch:{ all -> 0x0efb }
            java.lang.String r46 = r10.zzag()     // Catch:{ all -> 0x0efb }
            java.lang.String r47 = r8.getName()     // Catch:{ all -> 0x0efb }
            r48 = 1
            r50 = 1
            long r52 = r8.getTimestampMillis()     // Catch:{ all -> 0x0efb }
            r54 = 0
            r56 = 0
            r57 = 0
            r58 = 0
            r59 = 0
            r45 = r9
            r45.<init>(r46, r47, r48, r50, r52, r54, r56, r57, r58, r59)     // Catch:{ all -> 0x0efb }
        L_0x0b7b:
            r14 = r9
            goto L_0x0b7f
        L_0x0b7d:
            r18 = r9
        L_0x0b7f:
            r60.zzgw()     // Catch:{ all -> 0x0cfd }
            com.google.android.gms.internal.measurement.zzgi r9 = r8.zzug()     // Catch:{ all -> 0x0cfd }
            com.google.android.gms.internal.measurement.zzey r9 = (com.google.android.gms.internal.measurement.zzey) r9     // Catch:{ all -> 0x0cfd }
            com.google.android.gms.internal.measurement.zzbs$zzc r9 = (com.google.android.gms.internal.measurement.zzbs.zzc) r9     // Catch:{ all -> 0x0cfd }
            java.lang.String r10 = "_eid"
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzjo.b(r9, r10)     // Catch:{ all -> 0x0cfd }
            java.lang.Long r9 = (java.lang.Long) r9     // Catch:{ all -> 0x0cfd }
            if (r9 == 0) goto L_0x0b96
            r10 = 1
            goto L_0x0b97
        L_0x0b96:
            r10 = 0
        L_0x0b97:
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)     // Catch:{ all -> 0x0cfd }
            r15 = 1
            if (r13 != r15) goto L_0x0bc9
            com.google.android.gms.internal.measurement.zzgi r9 = r8.zzug()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzey r9 = (com.google.android.gms.internal.measurement.zzey) r9     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzc r9 = (com.google.android.gms.internal.measurement.zzbs.zzc) r9     // Catch:{ all -> 0x0efb }
            r5.add(r9)     // Catch:{ all -> 0x0efb }
            boolean r9 = r10.booleanValue()     // Catch:{ all -> 0x0efb }
            if (r9 == 0) goto L_0x0a11
            java.lang.Long r9 = r14.i     // Catch:{ all -> 0x0efb }
            if (r9 != 0) goto L_0x0bbb
            java.lang.Long r9 = r14.j     // Catch:{ all -> 0x0efb }
            if (r9 != 0) goto L_0x0bbb
            java.lang.Boolean r9 = r14.k     // Catch:{ all -> 0x0efb }
            if (r9 == 0) goto L_0x0a11
        L_0x0bbb:
            r9 = 0
            com.google.android.gms.measurement.internal.zzae r10 = r14.a(r9, r9, r9)     // Catch:{ all -> 0x0efb }
            java.lang.String r9 = r8.getName()     // Catch:{ all -> 0x0efb }
            r4.put(r9, r10)     // Catch:{ all -> 0x0efb }
            goto L_0x0a11
        L_0x0bc9:
            int r15 = r6.nextInt(r13)     // Catch:{ all -> 0x0cfd }
            if (r15 != 0) goto L_0x0c0c
            r60.zzgw()     // Catch:{ all -> 0x0efb }
            java.lang.String r9 = "_sr"
            r61 = r6
            r15 = r7
            long r6 = (long) r13     // Catch:{ all -> 0x0efb }
            java.lang.Long r13 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzjo.a(r8, r9, r13)     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzgi r9 = r8.zzug()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzey r9 = (com.google.android.gms.internal.measurement.zzey) r9     // Catch:{ all -> 0x0efb }
            com.google.android.gms.internal.measurement.zzbs$zzc r9 = (com.google.android.gms.internal.measurement.zzbs.zzc) r9     // Catch:{ all -> 0x0efb }
            r5.add(r9)     // Catch:{ all -> 0x0efb }
            boolean r9 = r10.booleanValue()     // Catch:{ all -> 0x0efb }
            if (r9 == 0) goto L_0x0bf9
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0efb }
            r7 = 0
            com.google.android.gms.measurement.internal.zzae r14 = r14.a(r7, r6, r7)     // Catch:{ all -> 0x0efb }
        L_0x0bf9:
            java.lang.String r6 = r8.getName()     // Catch:{ all -> 0x0efb }
            long r9 = r8.getTimestampMillis()     // Catch:{ all -> 0x0efb }
            com.google.android.gms.measurement.internal.zzae r7 = r14.a(r9, r11)     // Catch:{ all -> 0x0efb }
            r4.put(r6, r7)     // Catch:{ all -> 0x0efb }
            r21 = r2
            goto L_0x0cba
        L_0x0c0c:
            r61 = r6
            r15 = r7
            com.google.android.gms.measurement.internal.zzfj r6 = r1.zzj     // Catch:{ all -> 0x0cfd }
            com.google.android.gms.measurement.internal.zzs r6 = r6.zzad()     // Catch:{ all -> 0x0cfd }
            com.google.android.gms.internal.measurement.zzbs$zzg r7 = r2.a     // Catch:{ all -> 0x0cfd }
            java.lang.String r7 = r7.zzag()     // Catch:{ all -> 0x0cfd }
            boolean r6 = r6.j(r7)     // Catch:{ all -> 0x0cfd }
            if (r6 == 0) goto L_0x0c47
            java.lang.Long r6 = r14.h     // Catch:{ all -> 0x0cfd }
            if (r6 == 0) goto L_0x0c2e
            java.lang.Long r6 = r14.h     // Catch:{ all -> 0x0efb }
            long r6 = r6.longValue()     // Catch:{ all -> 0x0efb }
            r21 = r2
            goto L_0x0c3f
        L_0x0c2e:
            com.google.android.gms.measurement.internal.zzfj r6 = r1.zzj     // Catch:{ all -> 0x0cfd }
            r6.zzz()     // Catch:{ all -> 0x0cfd }
            long r6 = r8.zzmm()     // Catch:{ all -> 0x0cfd }
            r21 = r2
            r1 = r18
            long r6 = com.google.android.gms.measurement.internal.zzjs.zzc(r6, r1)     // Catch:{ all -> 0x0cfd }
        L_0x0c3f:
            int r1 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
            if (r1 == 0) goto L_0x0c45
        L_0x0c43:
            r1 = 1
            goto L_0x0c5c
        L_0x0c45:
            r1 = 0
            goto L_0x0c5c
        L_0x0c47:
            r21 = r2
            long r1 = r14.g     // Catch:{ all -> 0x0cfd }
            long r6 = r8.getTimestampMillis()     // Catch:{ all -> 0x0cfd }
            long r6 = r6 - r1
            long r1 = java.lang.Math.abs(r6)     // Catch:{ all -> 0x0cfd }
            r6 = 86400000(0x5265c00, double:4.2687272E-316)
            int r18 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r18 < 0) goto L_0x0c45
            goto L_0x0c43
        L_0x0c5c:
            if (r1 == 0) goto L_0x0ca8
            r60.zzgw()     // Catch:{ all -> 0x0cfd }
            java.lang.String r1 = "_efs"
            r6 = 1
            java.lang.Long r2 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0cfd }
            com.google.android.gms.measurement.internal.zzjo.a(r8, r1, r2)     // Catch:{ all -> 0x0cfd }
            r60.zzgw()     // Catch:{ all -> 0x0cfd }
            java.lang.String r1 = "_sr"
            long r6 = (long) r13     // Catch:{ all -> 0x0cfd }
            java.lang.Long r2 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0cfd }
            com.google.android.gms.measurement.internal.zzjo.a(r8, r1, r2)     // Catch:{ all -> 0x0cfd }
            com.google.android.gms.internal.measurement.zzgi r1 = r8.zzug()     // Catch:{ all -> 0x0cfd }
            com.google.android.gms.internal.measurement.zzey r1 = (com.google.android.gms.internal.measurement.zzey) r1     // Catch:{ all -> 0x0cfd }
            com.google.android.gms.internal.measurement.zzbs$zzc r1 = (com.google.android.gms.internal.measurement.zzbs.zzc) r1     // Catch:{ all -> 0x0cfd }
            r5.add(r1)     // Catch:{ all -> 0x0cfd }
            boolean r1 = r10.booleanValue()     // Catch:{ all -> 0x0cfd }
            if (r1 == 0) goto L_0x0c98
            java.lang.Long r1 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0cfd }
            r2 = 1
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r2)     // Catch:{ all -> 0x0cfd }
            r2 = 0
            com.google.android.gms.measurement.internal.zzae r14 = r14.a(r2, r1, r6)     // Catch:{ all -> 0x0cfd }
        L_0x0c98:
            java.lang.String r1 = r8.getName()     // Catch:{ all -> 0x0cfd }
            long r6 = r8.getTimestampMillis()     // Catch:{ all -> 0x0cfd }
            com.google.android.gms.measurement.internal.zzae r2 = r14.a(r6, r11)     // Catch:{ all -> 0x0cfd }
            r4.put(r1, r2)     // Catch:{ all -> 0x0cfd }
            goto L_0x0cba
        L_0x0ca8:
            boolean r1 = r10.booleanValue()     // Catch:{ all -> 0x0cfd }
            if (r1 == 0) goto L_0x0cba
            java.lang.String r1 = r8.getName()     // Catch:{ all -> 0x0cfd }
            r2 = 0
            com.google.android.gms.measurement.internal.zzae r6 = r14.a(r9, r2, r2)     // Catch:{ all -> 0x0cfd }
            r4.put(r1, r6)     // Catch:{ all -> 0x0cfd }
        L_0x0cba:
            r1 = r15
            r3.zza((int) r1, (com.google.android.gms.internal.measurement.zzbs.zzc.zza) r8)     // Catch:{ all -> 0x0cfd }
        L_0x0cbe:
            int r7 = r1 + 1
            r6 = r61
            r2 = r21
            r1 = r60
            goto L_0x0988
        L_0x0cc8:
            r21 = r2
            int r1 = r5.size()     // Catch:{ all -> 0x0cfd }
            int r2 = r3.zznm()     // Catch:{ all -> 0x0cfd }
            if (r1 >= r2) goto L_0x0cdb
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r1 = r3.zznn()     // Catch:{ all -> 0x0cfd }
            r1.zza((java.lang.Iterable<? extends com.google.android.gms.internal.measurement.zzbs.zzc>) r5)     // Catch:{ all -> 0x0cfd }
        L_0x0cdb:
            java.util.Set r1 = r4.entrySet()     // Catch:{ all -> 0x0cfd }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0cfd }
        L_0x0ce3:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x0cfd }
            if (r2 == 0) goto L_0x0d05
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x0cfd }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ all -> 0x0cfd }
            com.google.android.gms.measurement.internal.zzx r4 = r60.zzgy()     // Catch:{ all -> 0x0cfd }
            java.lang.Object r2 = r2.getValue()     // Catch:{ all -> 0x0cfd }
            com.google.android.gms.measurement.internal.zzae r2 = (com.google.android.gms.measurement.internal.zzae) r2     // Catch:{ all -> 0x0cfd }
            r4.zza((com.google.android.gms.measurement.internal.zzae) r2)     // Catch:{ all -> 0x0cfd }
            goto L_0x0ce3
        L_0x0cfd:
            r0 = move-exception
            r1 = r0
            r6 = r60
            goto L_0x0efe
        L_0x0d03:
            r21 = r2
        L_0x0d05:
            r1 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r1 = r3.zzao(r1)     // Catch:{ all -> 0x0ed9 }
            r4 = -9223372036854775808
            r1.zzap(r4)     // Catch:{ all -> 0x0ed9 }
            r1 = 0
        L_0x0d14:
            int r2 = r3.zznm()     // Catch:{ all -> 0x0ed9 }
            if (r1 >= r2) goto L_0x0d47
            com.google.android.gms.internal.measurement.zzbs$zzc r2 = r3.zzq(r1)     // Catch:{ all -> 0x0cfd }
            long r4 = r2.getTimestampMillis()     // Catch:{ all -> 0x0cfd }
            long r6 = r3.zznq()     // Catch:{ all -> 0x0cfd }
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 >= 0) goto L_0x0d31
            long r4 = r2.getTimestampMillis()     // Catch:{ all -> 0x0cfd }
            r3.zzao(r4)     // Catch:{ all -> 0x0cfd }
        L_0x0d31:
            long r4 = r2.getTimestampMillis()     // Catch:{ all -> 0x0cfd }
            long r6 = r3.zznr()     // Catch:{ all -> 0x0cfd }
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 <= 0) goto L_0x0d44
            long r4 = r2.getTimestampMillis()     // Catch:{ all -> 0x0cfd }
            r3.zzap(r4)     // Catch:{ all -> 0x0cfd }
        L_0x0d44:
            int r1 = r1 + 1
            goto L_0x0d14
        L_0x0d47:
            r1 = r21
            com.google.android.gms.internal.measurement.zzbs$zzg r2 = r1.a     // Catch:{ all -> 0x0ed9 }
            java.lang.String r2 = r2.zzag()     // Catch:{ all -> 0x0ed9 }
            com.google.android.gms.measurement.internal.zzx r4 = r60.zzgy()     // Catch:{ all -> 0x0ed9 }
            com.google.android.gms.measurement.internal.zzf r4 = r4.zzab(r2)     // Catch:{ all -> 0x0ed9 }
            if (r4 != 0) goto L_0x0d75
            r6 = r60
            com.google.android.gms.measurement.internal.zzfj r4 = r6.zzj     // Catch:{ all -> 0x0ef9 }
            com.google.android.gms.measurement.internal.zzef r4 = r4.zzab()     // Catch:{ all -> 0x0ef9 }
            com.google.android.gms.measurement.internal.zzeh r4 = r4.zzgk()     // Catch:{ all -> 0x0ef9 }
            java.lang.String r5 = "Bundling raw events w/o app info. appId"
            com.google.android.gms.internal.measurement.zzbs$zzg r7 = r1.a     // Catch:{ all -> 0x0ef9 }
            java.lang.String r7 = r7.zzag()     // Catch:{ all -> 0x0ef9 }
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r7)     // Catch:{ all -> 0x0ef9 }
            r4.zza(r5, r7)     // Catch:{ all -> 0x0ef9 }
            goto L_0x0dd2
        L_0x0d75:
            r6 = r60
            int r5 = r3.zznm()     // Catch:{ all -> 0x0ef9 }
            if (r5 <= 0) goto L_0x0dd2
            long r7 = r4.zzak()     // Catch:{ all -> 0x0ef9 }
            r9 = 0
            int r5 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r5 == 0) goto L_0x0d8b
            r3.zzar(r7)     // Catch:{ all -> 0x0ef9 }
            goto L_0x0d8e
        L_0x0d8b:
            r3.zznt()     // Catch:{ all -> 0x0ef9 }
        L_0x0d8e:
            long r9 = r4.zzaj()     // Catch:{ all -> 0x0ef9 }
            r11 = 0
            int r5 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r5 != 0) goto L_0x0d99
            goto L_0x0d9a
        L_0x0d99:
            r7 = r9
        L_0x0d9a:
            int r5 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r5 == 0) goto L_0x0da2
            r3.zzaq(r7)     // Catch:{ all -> 0x0ef9 }
            goto L_0x0da5
        L_0x0da2:
            r3.zzns()     // Catch:{ all -> 0x0ef9 }
        L_0x0da5:
            r4.zzau()     // Catch:{ all -> 0x0ef9 }
            long r7 = r4.zzar()     // Catch:{ all -> 0x0ef9 }
            int r5 = (int) r7     // Catch:{ all -> 0x0ef9 }
            r3.zzu(r5)     // Catch:{ all -> 0x0ef9 }
            long r7 = r3.zznq()     // Catch:{ all -> 0x0ef9 }
            r4.zze((long) r7)     // Catch:{ all -> 0x0ef9 }
            long r7 = r3.zznr()     // Catch:{ all -> 0x0ef9 }
            r4.zzf((long) r7)     // Catch:{ all -> 0x0ef9 }
            java.lang.String r5 = r4.zzbc()     // Catch:{ all -> 0x0ef9 }
            if (r5 == 0) goto L_0x0dc8
            r3.zzcl(r5)     // Catch:{ all -> 0x0ef9 }
            goto L_0x0dcb
        L_0x0dc8:
            r3.zznu()     // Catch:{ all -> 0x0ef9 }
        L_0x0dcb:
            com.google.android.gms.measurement.internal.zzx r5 = r60.zzgy()     // Catch:{ all -> 0x0ef9 }
            r5.zza((com.google.android.gms.measurement.internal.zzf) r4)     // Catch:{ all -> 0x0ef9 }
        L_0x0dd2:
            int r4 = r3.zznm()     // Catch:{ all -> 0x0ef9 }
            if (r4 <= 0) goto L_0x0e38
            com.google.android.gms.measurement.internal.zzfj r4 = r6.zzj     // Catch:{ all -> 0x0ef9 }
            r4.zzae()     // Catch:{ all -> 0x0ef9 }
            com.google.android.gms.measurement.internal.zzfd r4 = r60.zzgz()     // Catch:{ all -> 0x0ef9 }
            com.google.android.gms.internal.measurement.zzbs$zzg r5 = r1.a     // Catch:{ all -> 0x0ef9 }
            java.lang.String r5 = r5.zzag()     // Catch:{ all -> 0x0ef9 }
            com.google.android.gms.internal.measurement.zzbw r4 = r4.a(r5)     // Catch:{ all -> 0x0ef9 }
            if (r4 == 0) goto L_0x0dfc
            java.lang.Long r5 = r4.zzzk     // Catch:{ all -> 0x0ef9 }
            if (r5 != 0) goto L_0x0df2
            goto L_0x0dfc
        L_0x0df2:
            java.lang.Long r4 = r4.zzzk     // Catch:{ all -> 0x0ef9 }
            long r4 = r4.longValue()     // Catch:{ all -> 0x0ef9 }
            r3.zzav(r4)     // Catch:{ all -> 0x0ef9 }
            goto L_0x0e27
        L_0x0dfc:
            com.google.android.gms.internal.measurement.zzbs$zzg r4 = r1.a     // Catch:{ all -> 0x0ef9 }
            java.lang.String r4 = r4.getGmpAppId()     // Catch:{ all -> 0x0ef9 }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x0ef9 }
            if (r4 == 0) goto L_0x0e0e
            r4 = -1
            r3.zzav(r4)     // Catch:{ all -> 0x0ef9 }
            goto L_0x0e27
        L_0x0e0e:
            com.google.android.gms.measurement.internal.zzfj r4 = r6.zzj     // Catch:{ all -> 0x0ef9 }
            com.google.android.gms.measurement.internal.zzef r4 = r4.zzab()     // Catch:{ all -> 0x0ef9 }
            com.google.android.gms.measurement.internal.zzeh r4 = r4.zzgn()     // Catch:{ all -> 0x0ef9 }
            java.lang.String r5 = "Did not find measurement config or missing version info. appId"
            com.google.android.gms.internal.measurement.zzbs$zzg r7 = r1.a     // Catch:{ all -> 0x0ef9 }
            java.lang.String r7 = r7.zzag()     // Catch:{ all -> 0x0ef9 }
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r7)     // Catch:{ all -> 0x0ef9 }
            r4.zza(r5, r7)     // Catch:{ all -> 0x0ef9 }
        L_0x0e27:
            com.google.android.gms.measurement.internal.zzx r4 = r60.zzgy()     // Catch:{ all -> 0x0ef9 }
            com.google.android.gms.internal.measurement.zzgi r3 = r3.zzug()     // Catch:{ all -> 0x0ef9 }
            com.google.android.gms.internal.measurement.zzey r3 = (com.google.android.gms.internal.measurement.zzey) r3     // Catch:{ all -> 0x0ef9 }
            com.google.android.gms.internal.measurement.zzbs$zzg r3 = (com.google.android.gms.internal.measurement.zzbs.zzg) r3     // Catch:{ all -> 0x0ef9 }
            r11 = r23
            r4.zza((com.google.android.gms.internal.measurement.zzbs.zzg) r3, (boolean) r11)     // Catch:{ all -> 0x0ef9 }
        L_0x0e38:
            com.google.android.gms.measurement.internal.zzx r3 = r60.zzgy()     // Catch:{ all -> 0x0ef9 }
            java.util.List<java.lang.Long> r1 = r1.b     // Catch:{ all -> 0x0ef9 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r1)     // Catch:{ all -> 0x0ef9 }
            r3.zzo()     // Catch:{ all -> 0x0ef9 }
            r3.c()     // Catch:{ all -> 0x0ef9 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0ef9 }
            java.lang.String r5 = "rowid in ("
            r4.<init>(r5)     // Catch:{ all -> 0x0ef9 }
            r5 = 0
        L_0x0e4f:
            int r7 = r1.size()     // Catch:{ all -> 0x0ef9 }
            if (r5 >= r7) goto L_0x0e6c
            if (r5 == 0) goto L_0x0e5c
            java.lang.String r7 = ","
            r4.append(r7)     // Catch:{ all -> 0x0ef9 }
        L_0x0e5c:
            java.lang.Object r7 = r1.get(r5)     // Catch:{ all -> 0x0ef9 }
            java.lang.Long r7 = (java.lang.Long) r7     // Catch:{ all -> 0x0ef9 }
            long r7 = r7.longValue()     // Catch:{ all -> 0x0ef9 }
            r4.append(r7)     // Catch:{ all -> 0x0ef9 }
            int r5 = r5 + 1
            goto L_0x0e4f
        L_0x0e6c:
            java.lang.String r5 = ")"
            r4.append(r5)     // Catch:{ all -> 0x0ef9 }
            android.database.sqlite.SQLiteDatabase r5 = r3.d()     // Catch:{ all -> 0x0ef9 }
            java.lang.String r7 = "raw_events"
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0ef9 }
            r8 = 0
            int r4 = r5.delete(r7, r4, r8)     // Catch:{ all -> 0x0ef9 }
            int r5 = r1.size()     // Catch:{ all -> 0x0ef9 }
            if (r4 == r5) goto L_0x0e9f
            com.google.android.gms.measurement.internal.zzef r3 = r3.zzab()     // Catch:{ all -> 0x0ef9 }
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzgk()     // Catch:{ all -> 0x0ef9 }
            java.lang.String r5 = "Deleted fewer rows from raw events table than expected"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0ef9 }
            int r1 = r1.size()     // Catch:{ all -> 0x0ef9 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x0ef9 }
            r3.zza(r5, r4, r1)     // Catch:{ all -> 0x0ef9 }
        L_0x0e9f:
            com.google.android.gms.measurement.internal.zzx r1 = r60.zzgy()     // Catch:{ all -> 0x0ef9 }
            android.database.sqlite.SQLiteDatabase r3 = r1.d()     // Catch:{ all -> 0x0ef9 }
            java.lang.String r4 = "delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)"
            r5 = 2
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x0eb6 }
            r7 = 0
            r5[r7] = r2     // Catch:{ SQLiteException -> 0x0eb6 }
            r7 = 1
            r5[r7] = r2     // Catch:{ SQLiteException -> 0x0eb6 }
            r3.execSQL(r4, r5)     // Catch:{ SQLiteException -> 0x0eb6 }
            goto L_0x0ec9
        L_0x0eb6:
            r0 = move-exception
            r3 = r0
            com.google.android.gms.measurement.internal.zzef r1 = r1.zzab()     // Catch:{ all -> 0x0ef9 }
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzgk()     // Catch:{ all -> 0x0ef9 }
            java.lang.String r4 = "Failed to remove unused event metadata. appId"
            java.lang.Object r2 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r2)     // Catch:{ all -> 0x0ef9 }
            r1.zza(r4, r2, r3)     // Catch:{ all -> 0x0ef9 }
        L_0x0ec9:
            com.google.android.gms.measurement.internal.zzx r1 = r60.zzgy()     // Catch:{ all -> 0x0ef9 }
            r1.setTransactionSuccessful()     // Catch:{ all -> 0x0ef9 }
            com.google.android.gms.measurement.internal.zzx r1 = r60.zzgy()
            r1.endTransaction()
            r1 = 1
            return r1
        L_0x0ed9:
            r0 = move-exception
            r6 = r60
            goto L_0x0efd
        L_0x0edd:
            r6 = r1
            com.google.android.gms.measurement.internal.zzx r1 = r60.zzgy()     // Catch:{ all -> 0x0ef9 }
            r1.setTransactionSuccessful()     // Catch:{ all -> 0x0ef9 }
            com.google.android.gms.measurement.internal.zzx r1 = r60.zzgy()
            r1.endTransaction()
            r1 = 0
            return r1
        L_0x0eee:
            r0 = move-exception
            r6 = r1
            r1 = r0
            r22 = r5
        L_0x0ef3:
            if (r22 == 0) goto L_0x0ef8
            r22.close()     // Catch:{ all -> 0x0ef9 }
        L_0x0ef8:
            throw r1     // Catch:{ all -> 0x0ef9 }
        L_0x0ef9:
            r0 = move-exception
            goto L_0x0efd
        L_0x0efb:
            r0 = move-exception
            r6 = r1
        L_0x0efd:
            r1 = r0
        L_0x0efe:
            com.google.android.gms.measurement.internal.zzx r2 = r60.zzgy()
            r2.endTransaction()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzjg.zzd(java.lang.String, long):boolean");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00d8  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00fe  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x010c  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0136  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0144  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0152  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x018e  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzf zzg(com.google.android.gms.measurement.internal.zzn r11) {
        /*
            r10 = this;
            r10.zzo()
            r10.b()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r11)
            java.lang.String r0 = r11.packageName
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r0)
            com.google.android.gms.measurement.internal.zzx r0 = r10.zzgy()
            java.lang.String r1 = r11.packageName
            com.google.android.gms.measurement.internal.zzf r0 = r0.zzab(r1)
            com.google.android.gms.measurement.internal.zzfj r1 = r10.zzj
            com.google.android.gms.measurement.internal.zzeo r1 = r1.zzac()
            java.lang.String r2 = r11.packageName
            java.lang.String r1 = r1.b((java.lang.String) r2)
            r2 = 1
            if (r0 != 0) goto L_0x0042
            com.google.android.gms.measurement.internal.zzf r0 = new com.google.android.gms.measurement.internal.zzf
            com.google.android.gms.measurement.internal.zzfj r3 = r10.zzj
            java.lang.String r4 = r11.packageName
            r0.<init>(r3, r4)
            com.google.android.gms.measurement.internal.zzfj r3 = r10.zzj
            com.google.android.gms.measurement.internal.zzjs r3 = r3.zzz()
            java.lang.String r3 = r3.e()
            r0.zza((java.lang.String) r3)
            r0.zzd(r1)
        L_0x0040:
            r1 = 1
            goto L_0x005e
        L_0x0042:
            java.lang.String r3 = r0.zzai()
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x005d
            r0.zzd(r1)
            com.google.android.gms.measurement.internal.zzfj r1 = r10.zzj
            com.google.android.gms.measurement.internal.zzjs r1 = r1.zzz()
            java.lang.String r1 = r1.e()
            r0.zza((java.lang.String) r1)
            goto L_0x0040
        L_0x005d:
            r1 = 0
        L_0x005e:
            java.lang.String r3 = r11.zzcg
            java.lang.String r4 = r0.getGmpAppId()
            boolean r3 = android.text.TextUtils.equals(r3, r4)
            if (r3 != 0) goto L_0x0070
            java.lang.String r1 = r11.zzcg
            r0.zzb((java.lang.String) r1)
            r1 = 1
        L_0x0070:
            java.lang.String r3 = r11.zzcu
            java.lang.String r4 = r0.zzah()
            boolean r3 = android.text.TextUtils.equals(r3, r4)
            if (r3 != 0) goto L_0x0082
            java.lang.String r1 = r11.zzcu
            r0.zzc((java.lang.String) r1)
            r1 = 1
        L_0x0082:
            java.lang.String r3 = r11.zzci
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x009c
            java.lang.String r3 = r11.zzci
            java.lang.String r4 = r0.getFirebaseInstanceId()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x009c
            java.lang.String r1 = r11.zzci
            r0.zze((java.lang.String) r1)
            r1 = 1
        L_0x009c:
            long r3 = r11.zzr
            r5 = 0
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x00b4
            long r3 = r11.zzr
            long r7 = r0.zzao()
            int r9 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x00b4
            long r3 = r11.zzr
            r0.zzh((long) r3)
            r1 = 1
        L_0x00b4:
            java.lang.String r3 = r11.zzcm
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x00ce
            java.lang.String r3 = r11.zzcm
            java.lang.String r4 = r0.zzal()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x00ce
            java.lang.String r1 = r11.zzcm
            r0.zzf((java.lang.String) r1)
            r1 = 1
        L_0x00ce:
            long r3 = r11.zzcn
            long r7 = r0.zzam()
            int r9 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x00de
            long r3 = r11.zzcn
            r0.zzg((long) r3)
            r1 = 1
        L_0x00de:
            java.lang.String r3 = r11.zzco
            if (r3 == 0) goto L_0x00f4
            java.lang.String r3 = r11.zzco
            java.lang.String r4 = r0.zzan()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x00f4
            java.lang.String r1 = r11.zzco
            r0.zzg((java.lang.String) r1)
            r1 = 1
        L_0x00f4:
            long r3 = r11.zzcp
            long r7 = r0.zzap()
            int r9 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x0104
            long r3 = r11.zzcp
            r0.zzi(r3)
            r1 = 1
        L_0x0104:
            boolean r3 = r11.zzcq
            boolean r4 = r0.isMeasurementEnabled()
            if (r3 == r4) goto L_0x0112
            boolean r1 = r11.zzcq
            r0.setMeasurementEnabled(r1)
            r1 = 1
        L_0x0112:
            java.lang.String r3 = r11.zzdp
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x012c
            java.lang.String r3 = r11.zzdp
            java.lang.String r4 = r0.zzbb()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x012c
            java.lang.String r1 = r11.zzdp
            r0.zzh((java.lang.String) r1)
            r1 = 1
        L_0x012c:
            long r3 = r11.zzcr
            long r7 = r0.zzbd()
            int r9 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x013c
            long r3 = r11.zzcr
            r0.zzt(r3)
            r1 = 1
        L_0x013c:
            boolean r3 = r11.zzcs
            boolean r4 = r0.zzbe()
            if (r3 == r4) goto L_0x014a
            boolean r1 = r11.zzcs
            r0.zzb((boolean) r1)
            r1 = 1
        L_0x014a:
            boolean r3 = r11.zzct
            boolean r4 = r0.zzbf()
            if (r3 == r4) goto L_0x0158
            boolean r1 = r11.zzct
            r0.zzc((boolean) r1)
            r1 = 1
        L_0x0158:
            com.google.android.gms.measurement.internal.zzfj r3 = r10.zzj
            com.google.android.gms.measurement.internal.zzs r3 = r3.zzad()
            java.lang.String r4 = r11.packageName
            com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzak.zzij
            boolean r3 = r3.zze(r4, r7)
            if (r3 == 0) goto L_0x0176
            java.lang.Boolean r3 = r11.zzcv
            java.lang.Boolean r4 = r0.zzbg()
            if (r3 == r4) goto L_0x0176
            java.lang.Boolean r1 = r11.zzcv
            r0.zza((java.lang.Boolean) r1)
            r1 = 1
        L_0x0176:
            long r3 = r11.zzs
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x018c
            long r3 = r11.zzs
            long r5 = r0.zzaq()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x018c
            long r3 = r11.zzs
            r0.zzj(r3)
            r1 = 1
        L_0x018c:
            if (r1 == 0) goto L_0x0195
            com.google.android.gms.measurement.internal.zzx r11 = r10.zzgy()
            r11.zza((com.google.android.gms.measurement.internal.zzf) r0)
        L_0x0195:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzjg.zzg(com.google.android.gms.measurement.internal.zzn):com.google.android.gms.measurement.internal.zzf");
    }

    private final zzem zzjg() {
        zzem zzem = this.zzsr;
        if (zzem != null) {
            return zzem;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    private final zzjc zzjh() {
        zza((zzjh) this.zzss);
        return this.zzss;
    }

    private final long zzjk() {
        long currentTimeMillis = this.zzj.zzx().currentTimeMillis();
        zzeo zzac = this.zzj.zzac();
        zzac.l();
        zzac.zzo();
        long j = zzac.zzln.get();
        if (j == 0) {
            j = 1 + ((long) zzac.zzz().c().nextInt(86400000));
            zzac.zzln.set(j);
        }
        return ((((currentTimeMillis + j) / 1000) / 60) / 60) / 24;
    }

    private final boolean zzjm() {
        zzo();
        b();
        return zzgy().zzcd() || !TextUtils.isEmpty(zzgy().zzby());
    }

    /* JADX WARNING: Removed duplicated region for block: B:54:0x0186  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x01a4  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzjn() {
        /*
            r21 = this;
            r0 = r21
            r21.zzo()
            r21.b()
            boolean r1 = r21.zzjr()
            if (r1 != 0) goto L_0x001d
            com.google.android.gms.measurement.internal.zzfj r1 = r0.zzj
            com.google.android.gms.measurement.internal.zzs r1 = r1.zzad()
            com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzak.zzim
            boolean r1 = r1.zza(r2)
            if (r1 != 0) goto L_0x001d
            return
        L_0x001d:
            long r1 = r0.zzsy
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x0062
            com.google.android.gms.measurement.internal.zzfj r1 = r0.zzj
            com.google.android.gms.common.util.Clock r1 = r1.zzx()
            long r1 = r1.elapsedRealtime()
            r5 = 3600000(0x36ee80, double:1.7786363E-317)
            long r7 = r0.zzsy
            long r1 = r1 - r7
            long r1 = java.lang.Math.abs(r1)
            long r5 = r5 - r1
            int r1 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r1 <= 0) goto L_0x0060
            com.google.android.gms.measurement.internal.zzfj r1 = r0.zzj
            com.google.android.gms.measurement.internal.zzef r1 = r1.zzab()
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzgs()
            java.lang.String r2 = "Upload has been suspended. Will update scheduling later in approximately ms"
            java.lang.Long r3 = java.lang.Long.valueOf(r5)
            r1.zza(r2, r3)
            com.google.android.gms.measurement.internal.zzem r1 = r21.zzjg()
            r1.unregister()
            com.google.android.gms.measurement.internal.zzjc r1 = r21.zzjh()
            r1.cancel()
            return
        L_0x0060:
            r0.zzsy = r3
        L_0x0062:
            com.google.android.gms.measurement.internal.zzfj r1 = r0.zzj
            boolean r1 = r1.g()
            if (r1 == 0) goto L_0x024f
            boolean r1 = r21.zzjm()
            if (r1 != 0) goto L_0x0072
            goto L_0x024f
        L_0x0072:
            com.google.android.gms.measurement.internal.zzfj r1 = r0.zzj
            com.google.android.gms.common.util.Clock r1 = r1.zzx()
            long r1 = r1.currentTimeMillis()
            com.google.android.gms.measurement.internal.zzdu<java.lang.Long> r5 = com.google.android.gms.measurement.internal.zzak.zzhf
            r6 = 0
            java.lang.Object r5 = r5.get(r6)
            java.lang.Long r5 = (java.lang.Long) r5
            long r7 = r5.longValue()
            long r7 = java.lang.Math.max(r3, r7)
            com.google.android.gms.measurement.internal.zzx r5 = r21.zzgy()
            boolean r5 = r5.zzce()
            if (r5 != 0) goto L_0x00a4
            com.google.android.gms.measurement.internal.zzx r5 = r21.zzgy()
            boolean r5 = r5.zzbz()
            if (r5 == 0) goto L_0x00a2
            goto L_0x00a4
        L_0x00a2:
            r5 = 0
            goto L_0x00a5
        L_0x00a4:
            r5 = 1
        L_0x00a5:
            if (r5 == 0) goto L_0x00c5
            com.google.android.gms.measurement.internal.zzfj r10 = r0.zzj
            com.google.android.gms.measurement.internal.zzs r10 = r10.zzad()
            java.lang.String r10 = r10.zzbu()
            boolean r11 = android.text.TextUtils.isEmpty(r10)
            if (r11 != 0) goto L_0x00c2
            java.lang.String r11 = ".none."
            boolean r10 = r11.equals(r10)
            if (r10 != 0) goto L_0x00c2
            com.google.android.gms.measurement.internal.zzdu<java.lang.Long> r10 = com.google.android.gms.measurement.internal.zzak.zzha
            goto L_0x00c7
        L_0x00c2:
            com.google.android.gms.measurement.internal.zzdu<java.lang.Long> r10 = com.google.android.gms.measurement.internal.zzak.zzgz
            goto L_0x00c7
        L_0x00c5:
            com.google.android.gms.measurement.internal.zzdu<java.lang.Long> r10 = com.google.android.gms.measurement.internal.zzak.zzgy
        L_0x00c7:
            java.lang.Object r10 = r10.get(r6)
            java.lang.Long r10 = (java.lang.Long) r10
            long r10 = r10.longValue()
            long r10 = java.lang.Math.max(r3, r10)
            com.google.android.gms.measurement.internal.zzfj r12 = r0.zzj
            com.google.android.gms.measurement.internal.zzeo r12 = r12.zzac()
            com.google.android.gms.measurement.internal.zzet r12 = r12.zzlj
            long r12 = r12.get()
            com.google.android.gms.measurement.internal.zzfj r14 = r0.zzj
            com.google.android.gms.measurement.internal.zzeo r14 = r14.zzac()
            com.google.android.gms.measurement.internal.zzet r14 = r14.zzlk
            long r14 = r14.get()
            com.google.android.gms.measurement.internal.zzx r16 = r21.zzgy()
            r17 = r10
            long r9 = r16.zzcb()
            com.google.android.gms.measurement.internal.zzx r11 = r21.zzgy()
            r19 = r7
            long r6 = r11.zzcc()
            long r6 = java.lang.Math.max(r9, r6)
            int r8 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r8 != 0) goto L_0x010c
        L_0x0109:
            r8 = r3
            goto L_0x0182
        L_0x010c:
            long r6 = r6 - r1
            long r6 = java.lang.Math.abs(r6)
            long r6 = r1 - r6
            long r12 = r12 - r1
            long r8 = java.lang.Math.abs(r12)
            long r8 = r1 - r8
            long r14 = r14 - r1
            long r10 = java.lang.Math.abs(r14)
            long r1 = r1 - r10
            long r8 = java.lang.Math.max(r8, r1)
            long r10 = r6 + r19
            if (r5 == 0) goto L_0x0132
            int r5 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x0132
            long r10 = java.lang.Math.min(r6, r8)
            long r10 = r10 + r17
        L_0x0132:
            com.google.android.gms.measurement.internal.zzjo r5 = r21.zzgw()
            r12 = r17
            boolean r5 = r5.a((long) r8, (long) r12)
            if (r5 != 0) goto L_0x0140
            long r8 = r8 + r12
            goto L_0x0141
        L_0x0140:
            r8 = r10
        L_0x0141:
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x0182
            int r5 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r5 < 0) goto L_0x0182
            r5 = 0
        L_0x014a:
            r6 = 20
            com.google.android.gms.measurement.internal.zzdu<java.lang.Integer> r7 = com.google.android.gms.measurement.internal.zzak.zzhh
            r10 = 0
            java.lang.Object r7 = r7.get(r10)
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            r11 = 0
            int r7 = java.lang.Math.max(r11, r7)
            int r6 = java.lang.Math.min(r6, r7)
            if (r5 >= r6) goto L_0x0109
            r6 = 1
            long r6 = r6 << r5
            com.google.android.gms.measurement.internal.zzdu<java.lang.Long> r12 = com.google.android.gms.measurement.internal.zzak.zzhg
            java.lang.Object r12 = r12.get(r10)
            java.lang.Long r12 = (java.lang.Long) r12
            long r12 = r12.longValue()
            long r12 = java.lang.Math.max(r3, r12)
            long r12 = r12 * r6
            long r8 = r8 + r12
            int r6 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            if (r6 <= 0) goto L_0x017f
            goto L_0x0182
        L_0x017f:
            int r5 = r5 + 1
            goto L_0x014a
        L_0x0182:
            int r1 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x01a4
            com.google.android.gms.measurement.internal.zzfj r1 = r0.zzj
            com.google.android.gms.measurement.internal.zzef r1 = r1.zzab()
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzgs()
            java.lang.String r2 = "Next upload time is 0"
            r1.zzao(r2)
            com.google.android.gms.measurement.internal.zzem r1 = r21.zzjg()
            r1.unregister()
            com.google.android.gms.measurement.internal.zzjc r1 = r21.zzjh()
            r1.cancel()
            return
        L_0x01a4:
            com.google.android.gms.measurement.internal.zzej r1 = r21.zzjf()
            boolean r1 = r1.zzgv()
            if (r1 != 0) goto L_0x01cc
            com.google.android.gms.measurement.internal.zzfj r1 = r0.zzj
            com.google.android.gms.measurement.internal.zzef r1 = r1.zzab()
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzgs()
            java.lang.String r2 = "No network"
            r1.zzao(r2)
            com.google.android.gms.measurement.internal.zzem r1 = r21.zzjg()
            r1.zzha()
            com.google.android.gms.measurement.internal.zzjc r1 = r21.zzjh()
            r1.cancel()
            return
        L_0x01cc:
            com.google.android.gms.measurement.internal.zzfj r1 = r0.zzj
            com.google.android.gms.measurement.internal.zzeo r1 = r1.zzac()
            com.google.android.gms.measurement.internal.zzet r1 = r1.zzll
            long r1 = r1.get()
            com.google.android.gms.measurement.internal.zzdu<java.lang.Long> r5 = com.google.android.gms.measurement.internal.zzak.zzgw
            r6 = 0
            java.lang.Object r5 = r5.get(r6)
            java.lang.Long r5 = (java.lang.Long) r5
            long r5 = r5.longValue()
            long r5 = java.lang.Math.max(r3, r5)
            com.google.android.gms.measurement.internal.zzjo r7 = r21.zzgw()
            boolean r7 = r7.a((long) r1, (long) r5)
            if (r7 != 0) goto L_0x01f8
            long r1 = r1 + r5
            long r8 = java.lang.Math.max(r8, r1)
        L_0x01f8:
            com.google.android.gms.measurement.internal.zzem r1 = r21.zzjg()
            r1.unregister()
            com.google.android.gms.measurement.internal.zzfj r1 = r0.zzj
            com.google.android.gms.common.util.Clock r1 = r1.zzx()
            long r1 = r1.currentTimeMillis()
            long r8 = r8 - r1
            int r1 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r1 > 0) goto L_0x0234
            com.google.android.gms.measurement.internal.zzdu<java.lang.Long> r1 = com.google.android.gms.measurement.internal.zzak.zzhb
            r2 = 0
            java.lang.Object r1 = r1.get(r2)
            java.lang.Long r1 = (java.lang.Long) r1
            long r1 = r1.longValue()
            long r8 = java.lang.Math.max(r3, r1)
            com.google.android.gms.measurement.internal.zzfj r1 = r0.zzj
            com.google.android.gms.measurement.internal.zzeo r1 = r1.zzac()
            com.google.android.gms.measurement.internal.zzet r1 = r1.zzlj
            com.google.android.gms.measurement.internal.zzfj r2 = r0.zzj
            com.google.android.gms.common.util.Clock r2 = r2.zzx()
            long r2 = r2.currentTimeMillis()
            r1.set(r2)
        L_0x0234:
            com.google.android.gms.measurement.internal.zzfj r1 = r0.zzj
            com.google.android.gms.measurement.internal.zzef r1 = r1.zzab()
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzgs()
            java.lang.String r2 = "Upload scheduled in approximately ms"
            java.lang.Long r3 = java.lang.Long.valueOf(r8)
            r1.zza(r2, r3)
            com.google.android.gms.measurement.internal.zzjc r1 = r21.zzjh()
            r1.zzv(r8)
            return
        L_0x024f:
            com.google.android.gms.measurement.internal.zzfj r1 = r0.zzj
            com.google.android.gms.measurement.internal.zzef r1 = r1.zzab()
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzgs()
            java.lang.String r2 = "Nothing to upload or uploading impossible"
            r1.zzao(r2)
            com.google.android.gms.measurement.internal.zzem r1 = r21.zzjg()
            r1.unregister()
            com.google.android.gms.measurement.internal.zzjc r1 = r21.zzjh()
            r1.cancel()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzjg.zzjn():void");
    }

    @WorkerThread
    private final void zzjo() {
        zzo();
        if (this.zztc || this.zztd || this.zzte) {
            this.zzj.zzab().zzgs().zza("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zztc), Boolean.valueOf(this.zztd), Boolean.valueOf(this.zzte));
            return;
        }
        this.zzj.zzab().zzgs().zzao("Stopping uploading service(s)");
        List<Runnable> list = this.zzsz;
        if (list != null) {
            for (Runnable run : list) {
                run.run();
            }
            this.zzsz.clear();
        }
    }

    @WorkerThread
    @VisibleForTesting
    private final boolean zzjp() {
        String str;
        zzeh zzeh;
        FileLock fileLock;
        zzo();
        if (!this.zzj.zzad().zza(zzak.zzjh) || (fileLock = this.zztf) == null || !fileLock.isValid()) {
            try {
                this.zztg = new RandomAccessFile(new File(this.zzj.getContext().getFilesDir(), "google_app_measurement.db"), "rw").getChannel();
                this.zztf = this.zztg.tryLock();
                if (this.zztf != null) {
                    this.zzj.zzab().zzgs().zzao("Storage concurrent access okay");
                    return true;
                }
                this.zzj.zzab().zzgk().zzao("Storage concurrent data access panic");
                return false;
            } catch (FileNotFoundException e) {
                e = e;
                zzeh = this.zzj.zzab().zzgk();
                str = "Failed to acquire storage lock";
                zzeh.zza(str, e);
                return false;
            } catch (IOException e2) {
                e = e2;
                zzeh = this.zzj.zzab().zzgk();
                str = "Failed to access storage lock file";
                zzeh.zza(str, e);
                return false;
            } catch (OverlappingFileLockException e3) {
                e = e3;
                zzeh = this.zzj.zzab().zzgn();
                str = "Storage lock already acquired";
                zzeh.zza(str, e);
                return false;
            }
        } else {
            this.zzj.zzab().zzgs().zzao("Storage concurrent access okay");
            return true;
        }
    }

    @WorkerThread
    private final boolean zzjr() {
        zzo();
        b();
        return this.zzsw;
    }

    public static zzjg zzm(Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzsn == null) {
            synchronized (zzjg.class) {
                if (zzsn == null) {
                    zzsn = new zzjg(new zzjm(context));
                }
            }
        }
        return zzsn;
    }

    @WorkerThread
    private final void zzo() {
        this.zzj.zzaa().zzo();
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void a() {
        this.zzj.zzaa().zzo();
        zzgy().e();
        if (this.zzj.zzac().zzlj.get() == 0) {
            this.zzj.zzac().zzlj.set(this.zzj.zzx().currentTimeMillis());
        }
        zzjn();
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    @WorkerThread
    @VisibleForTesting
    public final void a(int i, Throwable th, byte[] bArr, String str) {
        zzx zzgy;
        zzo();
        b();
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (Throwable th2) {
                this.zztd = false;
                zzjo();
                throw th2;
            }
        }
        List<Long> list = this.zzth;
        this.zzth = null;
        boolean z = true;
        if ((i == 200 || i == 204) && th == null) {
            try {
                this.zzj.zzac().zzlj.set(this.zzj.zzx().currentTimeMillis());
                this.zzj.zzac().zzlk.set(0);
                zzjn();
                this.zzj.zzab().zzgs().zza("Successful upload. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                zzgy().beginTransaction();
                try {
                    for (Long next : list) {
                        try {
                            zzgy = zzgy();
                            long longValue = next.longValue();
                            zzgy.zzo();
                            zzgy.c();
                            if (zzgy.d().delete("queue", "rowid=?", new String[]{String.valueOf(longValue)}) != 1) {
                                throw new SQLiteException("Deleted fewer rows from queue than expected");
                            }
                        } catch (SQLiteException e) {
                            zzgy.zzab().zzgk().zza("Failed to delete a bundle in a queue table", e);
                            throw e;
                        } catch (SQLiteException e2) {
                            if (this.zzti == null || !this.zzti.contains(next)) {
                                throw e2;
                            }
                        }
                    }
                    zzgy().setTransactionSuccessful();
                    zzgy().endTransaction();
                    this.zzti = null;
                    if (!zzjf().zzgv() || !zzjm()) {
                        this.zztj = -1;
                        zzjn();
                    } else {
                        c();
                    }
                    this.zzsy = 0;
                } catch (Throwable th3) {
                    zzgy().endTransaction();
                    throw th3;
                }
            } catch (SQLiteException e3) {
                this.zzj.zzab().zzgk().zza("Database error while trying to delete uploaded bundles", e3);
                this.zzsy = this.zzj.zzx().elapsedRealtime();
                this.zzj.zzab().zzgs().zza("Disable upload, time", Long.valueOf(this.zzsy));
            }
        } else {
            this.zzj.zzab().zzgs().zza("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
            this.zzj.zzac().zzlk.set(this.zzj.zzx().currentTimeMillis());
            if (i != 503) {
                if (i != 429) {
                    z = false;
                }
            }
            if (z) {
                this.zzj.zzac().zzll.set(this.zzj.zzx().currentTimeMillis());
            }
            zzgy().a(list);
            zzjn();
        }
        this.zztd = false;
        zzjo();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void a(zzai zzai, zzn zzn) {
        List<zzq> list;
        List<zzq> list2;
        List<zzq> list3;
        zzeh zzgk;
        String str;
        Object a;
        String c;
        Object obj;
        zzai zzai2 = zzai;
        zzn zzn2 = zzn;
        Preconditions.checkNotNull(zzn);
        Preconditions.checkNotEmpty(zzn2.packageName);
        zzo();
        b();
        String str2 = zzn2.packageName;
        long j = zzai2.zzfu;
        if (zzgw().a(zzai2, zzn2)) {
            if (!zzn2.zzcq) {
                zzg(zzn2);
                return;
            }
            if (this.zzj.zzad().zze(str2, zzak.zzix) && zzn2.zzcw != null) {
                if (zzn2.zzcw.contains(zzai2.name)) {
                    Bundle zzcv = zzai2.zzfq.zzcv();
                    zzcv.putLong("ga_safelisted", 1);
                    zzai2 = new zzai(zzai2.name, new zzah(zzcv), zzai2.origin, zzai2.zzfu);
                } else {
                    this.zzj.zzab().zzgr().zza("Dropping non-safelisted event. appId, event name, origin", str2, zzai2.name, zzai2.origin);
                    return;
                }
            }
            zzgy().beginTransaction();
            try {
                zzx zzgy = zzgy();
                Preconditions.checkNotEmpty(str2);
                zzgy.zzo();
                zzgy.c();
                int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
                if (i < 0) {
                    zzgy.zzab().zzgn().zza("Invalid time querying timed out conditional properties", zzef.a(str2), Long.valueOf(j));
                    list = Collections.emptyList();
                } else {
                    list = zzgy.zzb("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{str2, String.valueOf(j)});
                }
                for (zzq next : list) {
                    if (next != null) {
                        this.zzj.zzab().zzgr().zza("User property timed out", next.packageName, this.zzj.zzy().c(next.zzdw.name), next.zzdw.getValue());
                        if (next.zzdx != null) {
                            zzd(new zzai(next.zzdx, j), zzn2);
                        }
                        zzgy().zzg(str2, next.zzdw.name);
                    }
                }
                zzx zzgy2 = zzgy();
                Preconditions.checkNotEmpty(str2);
                zzgy2.zzo();
                zzgy2.c();
                if (i < 0) {
                    zzgy2.zzab().zzgn().zza("Invalid time querying expired conditional properties", zzef.a(str2), Long.valueOf(j));
                    list2 = Collections.emptyList();
                } else {
                    list2 = zzgy2.zzb("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{str2, String.valueOf(j)});
                }
                ArrayList arrayList = new ArrayList(list2.size());
                for (zzq next2 : list2) {
                    if (next2 != null) {
                        this.zzj.zzab().zzgr().zza("User property expired", next2.packageName, this.zzj.zzy().c(next2.zzdw.name), next2.zzdw.getValue());
                        zzgy().zzd(str2, next2.zzdw.name);
                        if (next2.zzdz != null) {
                            arrayList.add(next2.zzdz);
                        }
                        zzgy().zzg(str2, next2.zzdw.name);
                    }
                }
                ArrayList arrayList2 = arrayList;
                int size = arrayList2.size();
                int i2 = 0;
                while (i2 < size) {
                    Object obj2 = arrayList2.get(i2);
                    i2++;
                    zzd(new zzai((zzai) obj2, j), zzn2);
                }
                zzx zzgy3 = zzgy();
                String str3 = zzai2.name;
                Preconditions.checkNotEmpty(str2);
                Preconditions.checkNotEmpty(str3);
                zzgy3.zzo();
                zzgy3.c();
                if (i < 0) {
                    zzgy3.zzab().zzgn().zza("Invalid time querying triggered conditional properties", zzef.a(str2), zzgy3.zzy().a(str3), Long.valueOf(j));
                    list3 = Collections.emptyList();
                } else {
                    list3 = zzgy3.zzb("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{str2, str3, String.valueOf(j)});
                }
                ArrayList arrayList3 = new ArrayList(list3.size());
                for (zzq next3 : list3) {
                    if (next3 != null) {
                        zzjn zzjn = next3.zzdw;
                        zzjp zzjp = r4;
                        zzjp zzjp2 = new zzjp(next3.packageName, next3.origin, zzjn.name, j, zzjn.getValue());
                        if (zzgy().zza(zzjp)) {
                            zzgk = this.zzj.zzab().zzgr();
                            str = "User property triggered";
                            a = next3.packageName;
                            c = this.zzj.zzy().c(zzjp.c);
                            obj = zzjp.e;
                        } else {
                            zzgk = this.zzj.zzab().zzgk();
                            str = "Too many active user properties, ignoring";
                            a = zzef.a(next3.packageName);
                            c = this.zzj.zzy().c(zzjp.c);
                            obj = zzjp.e;
                        }
                        zzgk.zza(str, a, c, obj);
                        if (next3.zzdy != null) {
                            arrayList3.add(next3.zzdy);
                        }
                        next3.zzdw = new zzjn(zzjp);
                        next3.active = true;
                        zzgy().zza(next3);
                    }
                }
                zzd(zzai2, zzn2);
                ArrayList arrayList4 = arrayList3;
                int size2 = arrayList4.size();
                int i3 = 0;
                while (i3 < size2) {
                    Object obj3 = arrayList4.get(i3);
                    i3++;
                    zzd(new zzai((zzai) obj3, j), zzn2);
                }
                zzgy().setTransactionSuccessful();
            } finally {
                zzgy().endTransaction();
            }
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void a(zzai zzai, String str) {
        zzai zzai2 = zzai;
        String str2 = str;
        zzf zzab = zzgy().zzab(str2);
        if (zzab == null || TextUtils.isEmpty(zzab.zzal())) {
            this.zzj.zzab().zzgr().zza("No app data available; dropping event", str2);
            return;
        }
        Boolean zzc = zzc(zzab);
        if (zzc == null) {
            if (!"_ui".equals(zzai2.name)) {
                this.zzj.zzab().zzgn().zza("Could not find package. appId", zzef.a(str));
            }
        } else if (!zzc.booleanValue()) {
            this.zzj.zzab().zzgk().zza("App version does not match; dropping event. appId", zzef.a(str));
            return;
        }
        zzn zzn = r2;
        zzf zzf = zzab;
        zzn zzn2 = new zzn(str, zzab.getGmpAppId(), zzab.zzal(), zzab.zzam(), zzab.zzan(), zzab.zzao(), zzab.zzap(), (String) null, zzab.isMeasurementEnabled(), false, zzf.getFirebaseInstanceId(), zzf.zzbd(), 0, 0, zzf.zzbe(), zzf.zzbf(), false, zzf.zzah(), zzf.zzbg(), zzf.zzaq(), zzf.zzbh());
        a(zzai2, zzn);
    }

    /* access modifiers changed from: package-private */
    public final void a(zzjh zzjh) {
        this.zzta++;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void a(zzjn zzjn, zzn zzn) {
        zzae zzc;
        zzo();
        b();
        if (TextUtils.isEmpty(zzn.zzcg) && TextUtils.isEmpty(zzn.zzcu)) {
            return;
        }
        if (!zzn.zzcq) {
            zzg(zzn);
            return;
        }
        int c = this.zzj.zzz().c(zzjn.name);
        if (c != 0) {
            this.zzj.zzz();
            this.zzj.zzz().a(zzn.packageName, c, "_ev", zzjs.zza(zzjn.name, 24, true), zzjn.name != null ? zzjn.name.length() : 0);
            return;
        }
        int b = this.zzj.zzz().b(zzjn.name, zzjn.getValue());
        if (b != 0) {
            this.zzj.zzz();
            String zza2 = zzjs.zza(zzjn.name, 24, true);
            Object value = zzjn.getValue();
            this.zzj.zzz().a(zzn.packageName, b, "_ev", zza2, (value == null || (!(value instanceof String) && !(value instanceof CharSequence))) ? 0 : String.valueOf(value).length());
            return;
        }
        Object c2 = this.zzj.zzz().c(zzjn.name, zzjn.getValue());
        if (c2 != null) {
            if ("_sid".equals(zzjn.name) && this.zzj.zzad().l(zzn.packageName)) {
                long j = zzjn.zztr;
                String str = zzjn.origin;
                long j2 = 0;
                zzjp zze = zzgy().zze(zzn.packageName, "_sno");
                if (zze == null || !(zze.e instanceof Long)) {
                    if (zze != null) {
                        this.zzj.zzab().zzgn().zza("Retrieved last session number from database does not contain a valid (long) value", zze.e);
                    }
                    if (this.zzj.zzad().zze(zzn.packageName, zzak.zzie) && (zzc = zzgy().zzc(zzn.packageName, "_s")) != null) {
                        j2 = zzc.c;
                        this.zzj.zzab().zzgs().zza("Backfill the session number. Last used session number", Long.valueOf(j2));
                    }
                } else {
                    j2 = ((Long) zze.e).longValue();
                }
                a(new zzjn("_sno", j, Long.valueOf(j2 + 1), str), zzn);
            }
            zzjp zzjp = new zzjp(zzn.packageName, zzjn.origin, zzjn.name, zzjn.zztr, c2);
            this.zzj.zzab().zzgr().zza("Setting user property", this.zzj.zzy().c(zzjp.c), c2);
            zzgy().beginTransaction();
            try {
                zzg(zzn);
                boolean zza3 = zzgy().zza(zzjp);
                zzgy().setTransactionSuccessful();
                if (zza3) {
                    this.zzj.zzab().zzgr().zza("User property set", this.zzj.zzy().c(zzjp.c), zzjp.e);
                } else {
                    this.zzj.zzab().zzgk().zza("Too many unique user properties are set. Ignoring user property", this.zzj.zzy().c(zzjp.c), zzjp.e);
                    this.zzj.zzz().a(zzn.packageName, 9, (String) null, (String) null, 0);
                }
            } finally {
                zzgy().endTransaction();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(zzn zzn) {
        zzo();
        b();
        Preconditions.checkNotEmpty(zzn.packageName);
        zzg(zzn);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void a(zzq zzq) {
        zzn zzbi = zzbi(zzq.packageName);
        if (zzbi != null) {
            a(zzq, zzbi);
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void a(zzq zzq, zzn zzn) {
        zzeh zzgk;
        String str;
        Object a;
        String c;
        Object value;
        zzeh zzgk2;
        String str2;
        Object a2;
        String c2;
        Object obj;
        Preconditions.checkNotNull(zzq);
        Preconditions.checkNotEmpty(zzq.packageName);
        Preconditions.checkNotNull(zzq.origin);
        Preconditions.checkNotNull(zzq.zzdw);
        Preconditions.checkNotEmpty(zzq.zzdw.name);
        zzo();
        b();
        if (TextUtils.isEmpty(zzn.zzcg) && TextUtils.isEmpty(zzn.zzcu)) {
            return;
        }
        if (!zzn.zzcq) {
            zzg(zzn);
            return;
        }
        zzq zzq2 = new zzq(zzq);
        boolean z = false;
        zzq2.active = false;
        zzgy().beginTransaction();
        try {
            zzq zzf = zzgy().zzf(zzq2.packageName, zzq2.zzdw.name);
            if (zzf != null && !zzf.origin.equals(zzq2.origin)) {
                this.zzj.zzab().zzgn().zza("Updating a conditional user property with different origin. name, origin, origin (from DB)", this.zzj.zzy().c(zzq2.zzdw.name), zzq2.origin, zzf.origin);
            }
            if (zzf != null && zzf.active) {
                zzq2.origin = zzf.origin;
                zzq2.creationTimestamp = zzf.creationTimestamp;
                zzq2.triggerTimeout = zzf.triggerTimeout;
                zzq2.triggerEventName = zzf.triggerEventName;
                zzq2.zzdy = zzf.zzdy;
                zzq2.active = zzf.active;
                zzq2.zzdw = new zzjn(zzq2.zzdw.name, zzf.zzdw.zztr, zzq2.zzdw.getValue(), zzf.zzdw.origin);
            } else if (TextUtils.isEmpty(zzq2.triggerEventName)) {
                zzq2.zzdw = new zzjn(zzq2.zzdw.name, zzq2.creationTimestamp, zzq2.zzdw.getValue(), zzq2.zzdw.origin);
                zzq2.active = true;
                z = true;
            }
            if (zzq2.active) {
                zzjn zzjn = zzq2.zzdw;
                zzjp zzjp = new zzjp(zzq2.packageName, zzq2.origin, zzjn.name, zzjn.zztr, zzjn.getValue());
                if (zzgy().zza(zzjp)) {
                    zzgk2 = this.zzj.zzab().zzgr();
                    str2 = "User property updated immediately";
                    a2 = zzq2.packageName;
                    c2 = this.zzj.zzy().c(zzjp.c);
                    obj = zzjp.e;
                } else {
                    zzgk2 = this.zzj.zzab().zzgk();
                    str2 = "(2)Too many active user properties, ignoring";
                    a2 = zzef.a(zzq2.packageName);
                    c2 = this.zzj.zzy().c(zzjp.c);
                    obj = zzjp.e;
                }
                zzgk2.zza(str2, a2, c2, obj);
                if (z && zzq2.zzdy != null) {
                    zzd(new zzai(zzq2.zzdy, zzq2.creationTimestamp), zzn);
                }
            }
            if (zzgy().zza(zzq2)) {
                zzgk = this.zzj.zzab().zzgr();
                str = "Conditional property added";
                a = zzq2.packageName;
                c = this.zzj.zzy().c(zzq2.zzdw.name);
                value = zzq2.zzdw.getValue();
            } else {
                zzgk = this.zzj.zzab().zzgk();
                str = "Too many conditional properties, ignoring";
                a = zzef.a(zzq2.packageName);
                c = this.zzj.zzy().c(zzq2.zzdw.name);
                value = zzq2.zzdw.getValue();
            }
            zzgk.zza(str, a, c, value);
            zzgy().setTransactionSuccessful();
        } finally {
            zzgy().endTransaction();
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void a(Runnable runnable) {
        zzo();
        if (this.zzsz == null) {
            this.zzsz = new ArrayList();
        }
        this.zzsz.add(runnable);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0132 A[Catch:{ all -> 0x0179, all -> 0x0182 }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0142 A[Catch:{ all -> 0x0179, all -> 0x0182 }] */
    @androidx.annotation.WorkerThread
    @com.google.android.gms.common.util.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.String r7, int r8, java.lang.Throwable r9, byte[] r10, java.util.Map<java.lang.String, java.util.List<java.lang.String>> r11) {
        /*
            r6 = this;
            r6.zzo()
            r6.b()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r7)
            r0 = 0
            if (r10 != 0) goto L_0x000e
            byte[] r10 = new byte[r0]     // Catch:{ all -> 0x0182 }
        L_0x000e:
            com.google.android.gms.measurement.internal.zzfj r1 = r6.zzj     // Catch:{ all -> 0x0182 }
            com.google.android.gms.measurement.internal.zzef r1 = r1.zzab()     // Catch:{ all -> 0x0182 }
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzgs()     // Catch:{ all -> 0x0182 }
            java.lang.String r2 = "onConfigFetched. Response size"
            int r3 = r10.length     // Catch:{ all -> 0x0182 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0182 }
            r1.zza(r2, r3)     // Catch:{ all -> 0x0182 }
            com.google.android.gms.measurement.internal.zzx r1 = r6.zzgy()     // Catch:{ all -> 0x0182 }
            r1.beginTransaction()     // Catch:{ all -> 0x0182 }
            com.google.android.gms.measurement.internal.zzx r1 = r6.zzgy()     // Catch:{ all -> 0x0179 }
            com.google.android.gms.measurement.internal.zzf r1 = r1.zzab(r7)     // Catch:{ all -> 0x0179 }
            r2 = 200(0xc8, float:2.8E-43)
            r3 = 304(0x130, float:4.26E-43)
            r4 = 1
            if (r8 == r2) goto L_0x003e
            r2 = 204(0xcc, float:2.86E-43)
            if (r8 == r2) goto L_0x003e
            if (r8 != r3) goto L_0x0042
        L_0x003e:
            if (r9 != 0) goto L_0x0042
            r2 = 1
            goto L_0x0043
        L_0x0042:
            r2 = 0
        L_0x0043:
            if (r1 != 0) goto L_0x005a
            com.google.android.gms.measurement.internal.zzfj r8 = r6.zzj     // Catch:{ all -> 0x0179 }
            com.google.android.gms.measurement.internal.zzef r8 = r8.zzab()     // Catch:{ all -> 0x0179 }
            com.google.android.gms.measurement.internal.zzeh r8 = r8.zzgn()     // Catch:{ all -> 0x0179 }
            java.lang.String r9 = "App does not exist in onConfigFetched. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r7)     // Catch:{ all -> 0x0179 }
            r8.zza(r9, r7)     // Catch:{ all -> 0x0179 }
            goto L_0x016d
        L_0x005a:
            r5 = 404(0x194, float:5.66E-43)
            if (r2 != 0) goto L_0x00ca
            if (r8 != r5) goto L_0x0061
            goto L_0x00ca
        L_0x0061:
            com.google.android.gms.measurement.internal.zzfj r10 = r6.zzj     // Catch:{ all -> 0x0179 }
            com.google.android.gms.common.util.Clock r10 = r10.zzx()     // Catch:{ all -> 0x0179 }
            long r10 = r10.currentTimeMillis()     // Catch:{ all -> 0x0179 }
            r1.zzm(r10)     // Catch:{ all -> 0x0179 }
            com.google.android.gms.measurement.internal.zzx r10 = r6.zzgy()     // Catch:{ all -> 0x0179 }
            r10.zza((com.google.android.gms.measurement.internal.zzf) r1)     // Catch:{ all -> 0x0179 }
            com.google.android.gms.measurement.internal.zzfj r10 = r6.zzj     // Catch:{ all -> 0x0179 }
            com.google.android.gms.measurement.internal.zzef r10 = r10.zzab()     // Catch:{ all -> 0x0179 }
            com.google.android.gms.measurement.internal.zzeh r10 = r10.zzgs()     // Catch:{ all -> 0x0179 }
            java.lang.String r11 = "Fetching config failed. code, error"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x0179 }
            r10.zza(r11, r1, r9)     // Catch:{ all -> 0x0179 }
            com.google.android.gms.measurement.internal.zzfd r9 = r6.zzgz()     // Catch:{ all -> 0x0179 }
            r9.c(r7)     // Catch:{ all -> 0x0179 }
            com.google.android.gms.measurement.internal.zzfj r7 = r6.zzj     // Catch:{ all -> 0x0179 }
            com.google.android.gms.measurement.internal.zzeo r7 = r7.zzac()     // Catch:{ all -> 0x0179 }
            com.google.android.gms.measurement.internal.zzet r7 = r7.zzlk     // Catch:{ all -> 0x0179 }
            com.google.android.gms.measurement.internal.zzfj r9 = r6.zzj     // Catch:{ all -> 0x0179 }
            com.google.android.gms.common.util.Clock r9 = r9.zzx()     // Catch:{ all -> 0x0179 }
            long r9 = r9.currentTimeMillis()     // Catch:{ all -> 0x0179 }
            r7.set(r9)     // Catch:{ all -> 0x0179 }
            r7 = 503(0x1f7, float:7.05E-43)
            if (r8 == r7) goto L_0x00ae
            r7 = 429(0x1ad, float:6.01E-43)
            if (r8 != r7) goto L_0x00ad
            goto L_0x00ae
        L_0x00ad:
            r4 = 0
        L_0x00ae:
            if (r4 == 0) goto L_0x00c5
            com.google.android.gms.measurement.internal.zzfj r7 = r6.zzj     // Catch:{ all -> 0x0179 }
            com.google.android.gms.measurement.internal.zzeo r7 = r7.zzac()     // Catch:{ all -> 0x0179 }
            com.google.android.gms.measurement.internal.zzet r7 = r7.zzll     // Catch:{ all -> 0x0179 }
            com.google.android.gms.measurement.internal.zzfj r8 = r6.zzj     // Catch:{ all -> 0x0179 }
            com.google.android.gms.common.util.Clock r8 = r8.zzx()     // Catch:{ all -> 0x0179 }
            long r8 = r8.currentTimeMillis()     // Catch:{ all -> 0x0179 }
            r7.set(r8)     // Catch:{ all -> 0x0179 }
        L_0x00c5:
            r6.zzjn()     // Catch:{ all -> 0x0179 }
            goto L_0x016d
        L_0x00ca:
            r9 = 0
            if (r11 == 0) goto L_0x00d6
            java.lang.String r2 = "Last-Modified"
            java.lang.Object r11 = r11.get(r2)     // Catch:{ all -> 0x0179 }
            java.util.List r11 = (java.util.List) r11     // Catch:{ all -> 0x0179 }
            goto L_0x00d7
        L_0x00d6:
            r11 = r9
        L_0x00d7:
            if (r11 == 0) goto L_0x00e6
            int r2 = r11.size()     // Catch:{ all -> 0x0179 }
            if (r2 <= 0) goto L_0x00e6
            java.lang.Object r11 = r11.get(r0)     // Catch:{ all -> 0x0179 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x0179 }
            goto L_0x00e7
        L_0x00e6:
            r11 = r9
        L_0x00e7:
            if (r8 == r5) goto L_0x0103
            if (r8 != r3) goto L_0x00ec
            goto L_0x0103
        L_0x00ec:
            com.google.android.gms.measurement.internal.zzfd r9 = r6.zzgz()     // Catch:{ all -> 0x0179 }
            boolean r9 = r9.a(r7, r10, r11)     // Catch:{ all -> 0x0179 }
            if (r9 != 0) goto L_0x011c
            com.google.android.gms.measurement.internal.zzx r7 = r6.zzgy()     // Catch:{ all -> 0x0182 }
        L_0x00fa:
            r7.endTransaction()     // Catch:{ all -> 0x0182 }
            r6.zztc = r0
            r6.zzjo()
            return
        L_0x0103:
            com.google.android.gms.measurement.internal.zzfd r11 = r6.zzgz()     // Catch:{ all -> 0x0179 }
            com.google.android.gms.internal.measurement.zzbw r11 = r11.a(r7)     // Catch:{ all -> 0x0179 }
            if (r11 != 0) goto L_0x011c
            com.google.android.gms.measurement.internal.zzfd r11 = r6.zzgz()     // Catch:{ all -> 0x0179 }
            boolean r9 = r11.a(r7, r9, r9)     // Catch:{ all -> 0x0179 }
            if (r9 != 0) goto L_0x011c
            com.google.android.gms.measurement.internal.zzx r7 = r6.zzgy()     // Catch:{ all -> 0x0182 }
            goto L_0x00fa
        L_0x011c:
            com.google.android.gms.measurement.internal.zzfj r9 = r6.zzj     // Catch:{ all -> 0x0179 }
            com.google.android.gms.common.util.Clock r9 = r9.zzx()     // Catch:{ all -> 0x0179 }
            long r2 = r9.currentTimeMillis()     // Catch:{ all -> 0x0179 }
            r1.zzl(r2)     // Catch:{ all -> 0x0179 }
            com.google.android.gms.measurement.internal.zzx r9 = r6.zzgy()     // Catch:{ all -> 0x0179 }
            r9.zza((com.google.android.gms.measurement.internal.zzf) r1)     // Catch:{ all -> 0x0179 }
            if (r8 != r5) goto L_0x0142
            com.google.android.gms.measurement.internal.zzfj r8 = r6.zzj     // Catch:{ all -> 0x0179 }
            com.google.android.gms.measurement.internal.zzef r8 = r8.zzab()     // Catch:{ all -> 0x0179 }
            com.google.android.gms.measurement.internal.zzeh r8 = r8.zzgp()     // Catch:{ all -> 0x0179 }
            java.lang.String r9 = "Config not found. Using empty config. appId"
            r8.zza(r9, r7)     // Catch:{ all -> 0x0179 }
            goto L_0x015a
        L_0x0142:
            com.google.android.gms.measurement.internal.zzfj r7 = r6.zzj     // Catch:{ all -> 0x0179 }
            com.google.android.gms.measurement.internal.zzef r7 = r7.zzab()     // Catch:{ all -> 0x0179 }
            com.google.android.gms.measurement.internal.zzeh r7 = r7.zzgs()     // Catch:{ all -> 0x0179 }
            java.lang.String r9 = "Successfully fetched config. Got network response. code, size"
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x0179 }
            int r10 = r10.length     // Catch:{ all -> 0x0179 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x0179 }
            r7.zza(r9, r8, r10)     // Catch:{ all -> 0x0179 }
        L_0x015a:
            com.google.android.gms.measurement.internal.zzej r7 = r6.zzjf()     // Catch:{ all -> 0x0179 }
            boolean r7 = r7.zzgv()     // Catch:{ all -> 0x0179 }
            if (r7 == 0) goto L_0x00c5
            boolean r7 = r6.zzjm()     // Catch:{ all -> 0x0179 }
            if (r7 == 0) goto L_0x00c5
            r6.c()     // Catch:{ all -> 0x0179 }
        L_0x016d:
            com.google.android.gms.measurement.internal.zzx r7 = r6.zzgy()     // Catch:{ all -> 0x0179 }
            r7.setTransactionSuccessful()     // Catch:{ all -> 0x0179 }
            com.google.android.gms.measurement.internal.zzx r7 = r6.zzgy()     // Catch:{ all -> 0x0182 }
            goto L_0x00fa
        L_0x0179:
            r7 = move-exception
            com.google.android.gms.measurement.internal.zzx r8 = r6.zzgy()     // Catch:{ all -> 0x0182 }
            r8.endTransaction()     // Catch:{ all -> 0x0182 }
            throw r7     // Catch:{ all -> 0x0182 }
        L_0x0182:
            r7 = move-exception
            r6.zztc = r0
            r6.zzjo()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzjg.a(java.lang.String, int, java.lang.Throwable, byte[], java.util.Map):void");
    }

    /* access modifiers changed from: package-private */
    public final void a(boolean z) {
        zzjn();
    }

    /* access modifiers changed from: package-private */
    public final void b() {
        if (!this.zzdh) {
            throw new IllegalStateException("UploadController is not initialized");
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void b(zzjn zzjn, zzn zzn) {
        zzo();
        b();
        if (TextUtils.isEmpty(zzn.zzcg) && TextUtils.isEmpty(zzn.zzcu)) {
            return;
        }
        if (!zzn.zzcq) {
            zzg(zzn);
        } else if (!this.zzj.zzad().zze(zzn.packageName, zzak.zzij)) {
            this.zzj.zzab().zzgr().zza("Removing user property", this.zzj.zzy().c(zzjn.name));
            zzgy().beginTransaction();
            try {
                zzg(zzn);
                zzgy().zzd(zzn.packageName, zzjn.name);
                zzgy().setTransactionSuccessful();
                this.zzj.zzab().zzgr().zza("User property removed", this.zzj.zzy().c(zzjn.name));
            } finally {
                zzgy().endTransaction();
            }
        } else if (!"_npa".equals(zzjn.name) || zzn.zzcv == null) {
            this.zzj.zzab().zzgr().zza("Removing user property", this.zzj.zzy().c(zzjn.name));
            zzgy().beginTransaction();
            try {
                zzg(zzn);
                zzgy().zzd(zzn.packageName, zzjn.name);
                zzgy().setTransactionSuccessful();
                this.zzj.zzab().zzgr().zza("User property removed", this.zzj.zzy().c(zzjn.name));
            } finally {
                zzgy().endTransaction();
            }
        } else {
            this.zzj.zzab().zzgr().zzao("Falling back to manifest metadata value for ad personalization");
            a(new zzjn("_npa", this.zzj.zzx().currentTimeMillis(), Long.valueOf(zzn.zzcv.booleanValue() ? 1 : 0), "auto"), zzn);
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    @VisibleForTesting
    public final void b(zzn zzn) {
        if (this.zzth != null) {
            this.zzti = new ArrayList();
            this.zzti.addAll(this.zzth);
        }
        zzx zzgy = zzgy();
        String str = zzn.packageName;
        Preconditions.checkNotEmpty(str);
        zzgy.zzo();
        zzgy.c();
        try {
            SQLiteDatabase d = zzgy.d();
            String[] strArr = {str};
            int delete = d.delete("apps", "app_id=?", strArr) + 0 + d.delete("events", "app_id=?", strArr) + d.delete("user_attributes", "app_id=?", strArr) + d.delete("conditional_properties", "app_id=?", strArr) + d.delete("raw_events", "app_id=?", strArr) + d.delete("raw_events_metadata", "app_id=?", strArr) + d.delete("queue", "app_id=?", strArr) + d.delete("audience_filter_values", "app_id=?", strArr) + d.delete("main_event_params", "app_id=?", strArr);
            if (delete > 0) {
                zzgy.zzab().zzgs().zza("Reset analytics data. app, records", str, Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzgy.zzab().zzgk().zza("Error resetting analytics data. appId, error", zzef.a(str), e);
        }
        zzn zza2 = zza(this.zzj.getContext(), zzn.packageName, zzn.zzcg, zzn.zzcq, zzn.zzcs, zzn.zzct, zzn.zzdr, zzn.zzcu);
        if (zzn.zzcq) {
            c(zza2);
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void b(zzq zzq) {
        zzn zzbi = zzbi(zzq.packageName);
        if (zzbi != null) {
            b(zzq, zzbi);
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void b(zzq zzq, zzn zzn) {
        Preconditions.checkNotNull(zzq);
        Preconditions.checkNotEmpty(zzq.packageName);
        Preconditions.checkNotNull(zzq.zzdw);
        Preconditions.checkNotEmpty(zzq.zzdw.name);
        zzo();
        b();
        if (TextUtils.isEmpty(zzn.zzcg) && TextUtils.isEmpty(zzn.zzcu)) {
            return;
        }
        if (!zzn.zzcq) {
            zzg(zzn);
            return;
        }
        zzgy().beginTransaction();
        try {
            zzg(zzn);
            zzq zzf = zzgy().zzf(zzq.packageName, zzq.zzdw.name);
            if (zzf != null) {
                this.zzj.zzab().zzgr().zza("Removing conditional user property", zzq.packageName, this.zzj.zzy().c(zzq.zzdw.name));
                zzgy().zzg(zzq.packageName, zzq.zzdw.name);
                if (zzf.active) {
                    zzgy().zzd(zzq.packageName, zzq.zzdw.name);
                }
                if (zzq.zzdz != null) {
                    Bundle bundle = null;
                    if (zzq.zzdz.zzfq != null) {
                        bundle = zzq.zzdz.zzfq.zzcv();
                    }
                    Bundle bundle2 = bundle;
                    zzd(this.zzj.zzz().a(zzq.packageName, zzq.zzdz.name, bundle2, zzf.origin, zzq.zzdz.zzfu, true, false), zzn);
                }
            } else {
                this.zzj.zzab().zzgn().zza("Conditional user property doesn't exist", zzef.a(zzq.packageName), this.zzj.zzy().c(zzq.zzdw.name));
            }
            zzgy().setTransactionSuccessful();
        } finally {
            zzgy().endTransaction();
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't wrap try/catch for region: R(2:84|85) */
    /* JADX WARNING: Code restructure failed: missing block: B:85:?, code lost:
        r1.zzj.zzab().zzgk().zza("Failed to parse upload URL. Not uploading. appId", com.google.android.gms.measurement.internal.zzef.a(r5), r9);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:84:0x02aa */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void c() {
        /*
            r17 = this;
            r1 = r17
            r17.zzo()
            r17.b()
            r0 = 1
            r1.zzte = r0
            r2 = 0
            com.google.android.gms.measurement.internal.zzfj r3 = r1.zzj     // Catch:{ all -> 0x02e3 }
            r3.zzae()     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzfj r3 = r1.zzj     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzhv r3 = r3.zzs()     // Catch:{ all -> 0x02e3 }
            java.lang.Boolean r3 = r3.g()     // Catch:{ all -> 0x02e3 }
            if (r3 != 0) goto L_0x0032
            com.google.android.gms.measurement.internal.zzfj r0 = r1.zzj     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzef r0 = r0.zzab()     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzgn()     // Catch:{ all -> 0x02e3 }
            java.lang.String r3 = "Upload data called on the client side before use of service was decided"
        L_0x0029:
            r0.zzao(r3)     // Catch:{ all -> 0x02e3 }
        L_0x002c:
            r1.zzte = r2
            r17.zzjo()
            return
        L_0x0032:
            boolean r3 = r3.booleanValue()     // Catch:{ all -> 0x02e3 }
            if (r3 == 0) goto L_0x0045
            com.google.android.gms.measurement.internal.zzfj r0 = r1.zzj     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzef r0 = r0.zzab()     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzgk()     // Catch:{ all -> 0x02e3 }
            java.lang.String r3 = "Upload called in the client side when service should be used"
            goto L_0x0029
        L_0x0045:
            long r3 = r1.zzsy     // Catch:{ all -> 0x02e3 }
            r5 = 0
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x0051
        L_0x004d:
            r17.zzjn()     // Catch:{ all -> 0x02e3 }
            goto L_0x002c
        L_0x0051:
            r17.zzo()     // Catch:{ all -> 0x02e3 }
            java.util.List<java.lang.Long> r3 = r1.zzth     // Catch:{ all -> 0x02e3 }
            if (r3 == 0) goto L_0x005a
            r3 = 1
            goto L_0x005b
        L_0x005a:
            r3 = 0
        L_0x005b:
            if (r3 == 0) goto L_0x006a
            com.google.android.gms.measurement.internal.zzfj r0 = r1.zzj     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzef r0 = r0.zzab()     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzgs()     // Catch:{ all -> 0x02e3 }
            java.lang.String r3 = "Uploading requested multiple times"
            goto L_0x0029
        L_0x006a:
            com.google.android.gms.measurement.internal.zzej r3 = r17.zzjf()     // Catch:{ all -> 0x02e3 }
            boolean r3 = r3.zzgv()     // Catch:{ all -> 0x02e3 }
            if (r3 != 0) goto L_0x0084
            com.google.android.gms.measurement.internal.zzfj r0 = r1.zzj     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzef r0 = r0.zzab()     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzgs()     // Catch:{ all -> 0x02e3 }
            java.lang.String r3 = "Network not connected, ignoring upload request"
            r0.zzao(r3)     // Catch:{ all -> 0x02e3 }
            goto L_0x004d
        L_0x0084:
            com.google.android.gms.measurement.internal.zzfj r3 = r1.zzj     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.common.util.Clock r3 = r3.zzx()     // Catch:{ all -> 0x02e3 }
            long r3 = r3.currentTimeMillis()     // Catch:{ all -> 0x02e3 }
            long r7 = com.google.android.gms.measurement.internal.zzs.zzbt()     // Catch:{ all -> 0x02e3 }
            long r7 = r3 - r7
            r9 = 0
            r1.zzd((java.lang.String) r9, (long) r7)     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzfj r7 = r1.zzj     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzeo r7 = r7.zzac()     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzet r7 = r7.zzlj     // Catch:{ all -> 0x02e3 }
            long r7 = r7.get()     // Catch:{ all -> 0x02e3 }
            int r10 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r10 == 0) goto L_0x00c1
            com.google.android.gms.measurement.internal.zzfj r5 = r1.zzj     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzef r5 = r5.zzab()     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzeh r5 = r5.zzgr()     // Catch:{ all -> 0x02e3 }
            java.lang.String r6 = "Uploading events. Elapsed time since last upload attempt (ms)"
            long r7 = r3 - r7
            long r7 = java.lang.Math.abs(r7)     // Catch:{ all -> 0x02e3 }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x02e3 }
            r5.zza(r6, r7)     // Catch:{ all -> 0x02e3 }
        L_0x00c1:
            com.google.android.gms.measurement.internal.zzx r5 = r17.zzgy()     // Catch:{ all -> 0x02e3 }
            java.lang.String r5 = r5.zzby()     // Catch:{ all -> 0x02e3 }
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x02e3 }
            r7 = -1
            if (r6 != 0) goto L_0x02bf
            long r10 = r1.zztj     // Catch:{ all -> 0x02e3 }
            int r6 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r6 != 0) goto L_0x00e1
            com.google.android.gms.measurement.internal.zzx r6 = r17.zzgy()     // Catch:{ all -> 0x02e3 }
            long r6 = r6.zzcf()     // Catch:{ all -> 0x02e3 }
            r1.zztj = r6     // Catch:{ all -> 0x02e3 }
        L_0x00e1:
            com.google.android.gms.measurement.internal.zzfj r6 = r1.zzj     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzs r6 = r6.zzad()     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzdu<java.lang.Integer> r7 = com.google.android.gms.measurement.internal.zzak.zzgl     // Catch:{ all -> 0x02e3 }
            int r6 = r6.zzb(r5, r7)     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzfj r7 = r1.zzj     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzs r7 = r7.zzad()     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzdu<java.lang.Integer> r8 = com.google.android.gms.measurement.internal.zzak.zzgm     // Catch:{ all -> 0x02e3 }
            int r7 = r7.zzb(r5, r8)     // Catch:{ all -> 0x02e3 }
            int r7 = java.lang.Math.max(r2, r7)     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzx r8 = r17.zzgy()     // Catch:{ all -> 0x02e3 }
            java.util.List r6 = r8.zza((java.lang.String) r5, (int) r6, (int) r7)     // Catch:{ all -> 0x02e3 }
            boolean r7 = r6.isEmpty()     // Catch:{ all -> 0x02e3 }
            if (r7 != 0) goto L_0x002c
            java.util.Iterator r7 = r6.iterator()     // Catch:{ all -> 0x02e3 }
        L_0x010f:
            boolean r8 = r7.hasNext()     // Catch:{ all -> 0x02e3 }
            if (r8 == 0) goto L_0x012e
            java.lang.Object r8 = r7.next()     // Catch:{ all -> 0x02e3 }
            android.util.Pair r8 = (android.util.Pair) r8     // Catch:{ all -> 0x02e3 }
            java.lang.Object r8 = r8.first     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.internal.measurement.zzbs$zzg r8 = (com.google.android.gms.internal.measurement.zzbs.zzg) r8     // Catch:{ all -> 0x02e3 }
            java.lang.String r10 = r8.zzot()     // Catch:{ all -> 0x02e3 }
            boolean r10 = android.text.TextUtils.isEmpty(r10)     // Catch:{ all -> 0x02e3 }
            if (r10 != 0) goto L_0x010f
            java.lang.String r7 = r8.zzot()     // Catch:{ all -> 0x02e3 }
            goto L_0x012f
        L_0x012e:
            r7 = r9
        L_0x012f:
            if (r7 == 0) goto L_0x015e
            r8 = 0
        L_0x0132:
            int r10 = r6.size()     // Catch:{ all -> 0x02e3 }
            if (r8 >= r10) goto L_0x015e
            java.lang.Object r10 = r6.get(r8)     // Catch:{ all -> 0x02e3 }
            android.util.Pair r10 = (android.util.Pair) r10     // Catch:{ all -> 0x02e3 }
            java.lang.Object r10 = r10.first     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.internal.measurement.zzbs$zzg r10 = (com.google.android.gms.internal.measurement.zzbs.zzg) r10     // Catch:{ all -> 0x02e3 }
            java.lang.String r11 = r10.zzot()     // Catch:{ all -> 0x02e3 }
            boolean r11 = android.text.TextUtils.isEmpty(r11)     // Catch:{ all -> 0x02e3 }
            if (r11 != 0) goto L_0x015b
            java.lang.String r10 = r10.zzot()     // Catch:{ all -> 0x02e3 }
            boolean r10 = r10.equals(r7)     // Catch:{ all -> 0x02e3 }
            if (r10 != 0) goto L_0x015b
            java.util.List r6 = r6.subList(r2, r8)     // Catch:{ all -> 0x02e3 }
            goto L_0x015e
        L_0x015b:
            int r8 = r8 + 1
            goto L_0x0132
        L_0x015e:
            com.google.android.gms.internal.measurement.zzbs$zzf$zza r7 = com.google.android.gms.internal.measurement.zzbs.zzf.zznj()     // Catch:{ all -> 0x02e3 }
            int r8 = r6.size()     // Catch:{ all -> 0x02e3 }
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ all -> 0x02e3 }
            int r11 = r6.size()     // Catch:{ all -> 0x02e3 }
            r10.<init>(r11)     // Catch:{ all -> 0x02e3 }
            boolean r11 = com.google.android.gms.measurement.internal.zzs.zzbv()     // Catch:{ all -> 0x02e3 }
            if (r11 == 0) goto L_0x0183
            com.google.android.gms.measurement.internal.zzfj r11 = r1.zzj     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzs r11 = r11.zzad()     // Catch:{ all -> 0x02e3 }
            boolean r11 = r11.zzl(r5)     // Catch:{ all -> 0x02e3 }
            if (r11 == 0) goto L_0x0183
            r11 = 1
            goto L_0x0184
        L_0x0183:
            r11 = 0
        L_0x0184:
            r12 = 0
        L_0x0185:
            if (r12 >= r8) goto L_0x01f0
            java.lang.Object r13 = r6.get(r12)     // Catch:{ all -> 0x02e3 }
            android.util.Pair r13 = (android.util.Pair) r13     // Catch:{ all -> 0x02e3 }
            java.lang.Object r13 = r13.first     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.internal.measurement.zzbs$zzg r13 = (com.google.android.gms.internal.measurement.zzbs.zzg) r13     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.internal.measurement.zzey$zza r13 = r13.zzuj()     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.internal.measurement.zzey$zza r13 = (com.google.android.gms.internal.measurement.zzey.zza) r13     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r13 = (com.google.android.gms.internal.measurement.zzbs.zzg.zza) r13     // Catch:{ all -> 0x02e3 }
            java.lang.Object r14 = r6.get(r12)     // Catch:{ all -> 0x02e3 }
            android.util.Pair r14 = (android.util.Pair) r14     // Catch:{ all -> 0x02e3 }
            java.lang.Object r14 = r14.second     // Catch:{ all -> 0x02e3 }
            java.lang.Long r14 = (java.lang.Long) r14     // Catch:{ all -> 0x02e3 }
            r10.add(r14)     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzfj r14 = r1.zzj     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzs r14 = r14.zzad()     // Catch:{ all -> 0x02e3 }
            long r14 = r14.zzao()     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r14 = r13.zzat(r14)     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r14 = r14.zzan(r3)     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzfj r15 = r1.zzj     // Catch:{ all -> 0x02e3 }
            r15.zzae()     // Catch:{ all -> 0x02e3 }
            r14.zzn(r2)     // Catch:{ all -> 0x02e3 }
            if (r11 != 0) goto L_0x01c5
            r13.zznw()     // Catch:{ all -> 0x02e3 }
        L_0x01c5:
            com.google.android.gms.measurement.internal.zzfj r14 = r1.zzj     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzs r14 = r14.zzad()     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r15 = com.google.android.gms.measurement.internal.zzak.zzis     // Catch:{ all -> 0x02e3 }
            boolean r14 = r14.zze(r5, r15)     // Catch:{ all -> 0x02e3 }
            if (r14 == 0) goto L_0x01ea
            com.google.android.gms.internal.measurement.zzgi r14 = r13.zzug()     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.internal.measurement.zzey r14 = (com.google.android.gms.internal.measurement.zzey) r14     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.internal.measurement.zzbs$zzg r14 = (com.google.android.gms.internal.measurement.zzbs.zzg) r14     // Catch:{ all -> 0x02e3 }
            byte[] r14 = r14.toByteArray()     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzjo r15 = r17.zzgw()     // Catch:{ all -> 0x02e3 }
            long r14 = r15.a((byte[]) r14)     // Catch:{ all -> 0x02e3 }
            r13.zzay(r14)     // Catch:{ all -> 0x02e3 }
        L_0x01ea:
            r7.zza(r13)     // Catch:{ all -> 0x02e3 }
            int r12 = r12 + 1
            goto L_0x0185
        L_0x01f0:
            com.google.android.gms.measurement.internal.zzfj r6 = r1.zzj     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzef r6 = r6.zzab()     // Catch:{ all -> 0x02e3 }
            r11 = 2
            boolean r6 = r6.a((int) r11)     // Catch:{ all -> 0x02e3 }
            if (r6 == 0) goto L_0x020e
            com.google.android.gms.measurement.internal.zzjo r6 = r17.zzgw()     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.internal.measurement.zzgi r11 = r7.zzug()     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.internal.measurement.zzey r11 = (com.google.android.gms.internal.measurement.zzey) r11     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.internal.measurement.zzbs$zzf r11 = (com.google.android.gms.internal.measurement.zzbs.zzf) r11     // Catch:{ all -> 0x02e3 }
            java.lang.String r6 = r6.a((com.google.android.gms.internal.measurement.zzbs.zzf) r11)     // Catch:{ all -> 0x02e3 }
            goto L_0x020f
        L_0x020e:
            r6 = r9
        L_0x020f:
            r17.zzgw()     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.internal.measurement.zzgi r11 = r7.zzug()     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.internal.measurement.zzey r11 = (com.google.android.gms.internal.measurement.zzey) r11     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.internal.measurement.zzbs$zzf r11 = (com.google.android.gms.internal.measurement.zzbs.zzf) r11     // Catch:{ all -> 0x02e3 }
            byte[] r14 = r11.toByteArray()     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzdu<java.lang.String> r11 = com.google.android.gms.measurement.internal.zzak.zzgv     // Catch:{ all -> 0x02e3 }
            java.lang.Object r9 = r11.get(r9)     // Catch:{ all -> 0x02e3 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ all -> 0x02e3 }
            java.net.URL r13 = new java.net.URL     // Catch:{ MalformedURLException -> 0x02aa }
            r13.<init>(r9)     // Catch:{ MalformedURLException -> 0x02aa }
            boolean r11 = r10.isEmpty()     // Catch:{ MalformedURLException -> 0x02aa }
            if (r11 != 0) goto L_0x0233
            r11 = 1
            goto L_0x0234
        L_0x0233:
            r11 = 0
        L_0x0234:
            com.google.android.gms.common.internal.Preconditions.checkArgument(r11)     // Catch:{ MalformedURLException -> 0x02aa }
            java.util.List<java.lang.Long> r11 = r1.zzth     // Catch:{ MalformedURLException -> 0x02aa }
            if (r11 == 0) goto L_0x024b
            com.google.android.gms.measurement.internal.zzfj r10 = r1.zzj     // Catch:{ MalformedURLException -> 0x02aa }
            com.google.android.gms.measurement.internal.zzef r10 = r10.zzab()     // Catch:{ MalformedURLException -> 0x02aa }
            com.google.android.gms.measurement.internal.zzeh r10 = r10.zzgk()     // Catch:{ MalformedURLException -> 0x02aa }
            java.lang.String r11 = "Set uploading progress before finishing the previous upload"
            r10.zzao(r11)     // Catch:{ MalformedURLException -> 0x02aa }
            goto L_0x0252
        L_0x024b:
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ MalformedURLException -> 0x02aa }
            r11.<init>(r10)     // Catch:{ MalformedURLException -> 0x02aa }
            r1.zzth = r11     // Catch:{ MalformedURLException -> 0x02aa }
        L_0x0252:
            com.google.android.gms.measurement.internal.zzfj r10 = r1.zzj     // Catch:{ MalformedURLException -> 0x02aa }
            com.google.android.gms.measurement.internal.zzeo r10 = r10.zzac()     // Catch:{ MalformedURLException -> 0x02aa }
            com.google.android.gms.measurement.internal.zzet r10 = r10.zzlk     // Catch:{ MalformedURLException -> 0x02aa }
            r10.set(r3)     // Catch:{ MalformedURLException -> 0x02aa }
            java.lang.String r3 = "?"
            if (r8 <= 0) goto L_0x0269
            com.google.android.gms.internal.measurement.zzbs$zzg r3 = r7.zzo(r2)     // Catch:{ MalformedURLException -> 0x02aa }
            java.lang.String r3 = r3.zzag()     // Catch:{ MalformedURLException -> 0x02aa }
        L_0x0269:
            com.google.android.gms.measurement.internal.zzfj r4 = r1.zzj     // Catch:{ MalformedURLException -> 0x02aa }
            com.google.android.gms.measurement.internal.zzef r4 = r4.zzab()     // Catch:{ MalformedURLException -> 0x02aa }
            com.google.android.gms.measurement.internal.zzeh r4 = r4.zzgs()     // Catch:{ MalformedURLException -> 0x02aa }
            java.lang.String r7 = "Uploading data. app, uncompressed size, data"
            int r8 = r14.length     // Catch:{ MalformedURLException -> 0x02aa }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ MalformedURLException -> 0x02aa }
            r4.zza(r7, r3, r8, r6)     // Catch:{ MalformedURLException -> 0x02aa }
            r1.zztd = r0     // Catch:{ MalformedURLException -> 0x02aa }
            com.google.android.gms.measurement.internal.zzej r11 = r17.zzjf()     // Catch:{ MalformedURLException -> 0x02aa }
            com.google.android.gms.measurement.internal.zzji r0 = new com.google.android.gms.measurement.internal.zzji     // Catch:{ MalformedURLException -> 0x02aa }
            r0.<init>(r1, r5)     // Catch:{ MalformedURLException -> 0x02aa }
            r11.zzo()     // Catch:{ MalformedURLException -> 0x02aa }
            r11.c()     // Catch:{ MalformedURLException -> 0x02aa }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r13)     // Catch:{ MalformedURLException -> 0x02aa }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r14)     // Catch:{ MalformedURLException -> 0x02aa }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)     // Catch:{ MalformedURLException -> 0x02aa }
            com.google.android.gms.measurement.internal.zzfc r3 = r11.zzaa()     // Catch:{ MalformedURLException -> 0x02aa }
            com.google.android.gms.measurement.internal.zzen r4 = new com.google.android.gms.measurement.internal.zzen     // Catch:{ MalformedURLException -> 0x02aa }
            r15 = 0
            r10 = r4
            r12 = r5
            r16 = r0
            r10.<init>(r11, r12, r13, r14, r15, r16)     // Catch:{ MalformedURLException -> 0x02aa }
            r3.zzb((java.lang.Runnable) r4)     // Catch:{ MalformedURLException -> 0x02aa }
            goto L_0x002c
        L_0x02aa:
            com.google.android.gms.measurement.internal.zzfj r0 = r1.zzj     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzef r0 = r0.zzab()     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzgk()     // Catch:{ all -> 0x02e3 }
            java.lang.String r3 = "Failed to parse upload URL. Not uploading. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r5)     // Catch:{ all -> 0x02e3 }
            r0.zza(r3, r4, r9)     // Catch:{ all -> 0x02e3 }
            goto L_0x002c
        L_0x02bf:
            r1.zztj = r7     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzx r0 = r17.zzgy()     // Catch:{ all -> 0x02e3 }
            long r5 = com.google.android.gms.measurement.internal.zzs.zzbt()     // Catch:{ all -> 0x02e3 }
            long r3 = r3 - r5
            java.lang.String r0 = r0.zzu(r3)     // Catch:{ all -> 0x02e3 }
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x02e3 }
            if (r3 != 0) goto L_0x002c
            com.google.android.gms.measurement.internal.zzx r3 = r17.zzgy()     // Catch:{ all -> 0x02e3 }
            com.google.android.gms.measurement.internal.zzf r0 = r3.zzab(r0)     // Catch:{ all -> 0x02e3 }
            if (r0 == 0) goto L_0x002c
            r1.zzb(r0)     // Catch:{ all -> 0x02e3 }
            goto L_0x002c
        L_0x02e3:
            r0 = move-exception
            r1.zzte = r2
            r17.zzjo()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzjg.c():void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x0489 A[Catch:{ SQLiteException -> 0x01db, all -> 0x04b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0278 A[Catch:{ SQLiteException -> 0x01db, all -> 0x04b2 }] */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void c(com.google.android.gms.measurement.internal.zzn r19) {
        /*
            r18 = this;
            r1 = r18
            r2 = r19
            r18.zzo()
            r18.b()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r19)
            java.lang.String r0 = r2.packageName
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r0)
            java.lang.String r0 = r2.zzcg
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x0023
            java.lang.String r0 = r2.zzcu
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x0023
            return
        L_0x0023:
            com.google.android.gms.measurement.internal.zzx r0 = r18.zzgy()
            java.lang.String r3 = r2.packageName
            com.google.android.gms.measurement.internal.zzf r0 = r0.zzab(r3)
            r3 = 0
            if (r0 == 0) goto L_0x0056
            java.lang.String r5 = r0.getGmpAppId()
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 == 0) goto L_0x0056
            java.lang.String r5 = r2.zzcg
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 != 0) goto L_0x0056
            r0.zzl(r3)
            com.google.android.gms.measurement.internal.zzx r5 = r18.zzgy()
            r5.zza((com.google.android.gms.measurement.internal.zzf) r0)
            com.google.android.gms.measurement.internal.zzfd r0 = r18.zzgz()
            java.lang.String r5 = r2.packageName
            r0.d(r5)
        L_0x0056:
            boolean r0 = r2.zzcq
            if (r0 != 0) goto L_0x005e
            r18.zzg(r19)
            return
        L_0x005e:
            long r5 = r2.zzdr
            int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r0 != 0) goto L_0x006e
            com.google.android.gms.measurement.internal.zzfj r0 = r1.zzj
            com.google.android.gms.common.util.Clock r0 = r0.zzx()
            long r5 = r0.currentTimeMillis()
        L_0x006e:
            com.google.android.gms.measurement.internal.zzfj r0 = r1.zzj
            com.google.android.gms.measurement.internal.zzs r0 = r0.zzad()
            java.lang.String r7 = r2.packageName
            com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzak.zzij
            boolean r0 = r0.zze(r7, r8)
            if (r0 == 0) goto L_0x0087
            com.google.android.gms.measurement.internal.zzfj r0 = r1.zzj
            com.google.android.gms.measurement.internal.zzac r0 = r0.zzw()
            r0.c()
        L_0x0087:
            int r0 = r2.zzds
            r13 = 0
            r14 = 1
            if (r0 == 0) goto L_0x00aa
            if (r0 == r14) goto L_0x00aa
            com.google.android.gms.measurement.internal.zzfj r7 = r1.zzj
            com.google.android.gms.measurement.internal.zzef r7 = r7.zzab()
            com.google.android.gms.measurement.internal.zzeh r7 = r7.zzgn()
            java.lang.String r8 = "Incorrect app type, assuming installed app. appId, appType"
            java.lang.String r9 = r2.packageName
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r9)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r7.zza(r8, r9, r0)
            r15 = 0
            goto L_0x00ab
        L_0x00aa:
            r15 = r0
        L_0x00ab:
            com.google.android.gms.measurement.internal.zzx r0 = r18.zzgy()
            r0.beginTransaction()
            com.google.android.gms.measurement.internal.zzfj r0 = r1.zzj     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.measurement.internal.zzs r0 = r0.zzad()     // Catch:{ all -> 0x04b2 }
            java.lang.String r7 = r2.packageName     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzak.zzij     // Catch:{ all -> 0x04b2 }
            boolean r0 = r0.zze(r7, r8)     // Catch:{ all -> 0x04b2 }
            r11 = 1
            if (r0 == 0) goto L_0x0122
            com.google.android.gms.measurement.internal.zzx r0 = r18.zzgy()     // Catch:{ all -> 0x04b2 }
            java.lang.String r7 = r2.packageName     // Catch:{ all -> 0x04b2 }
            java.lang.String r8 = "_npa"
            com.google.android.gms.measurement.internal.zzjp r0 = r0.zze(r7, r8)     // Catch:{ all -> 0x04b2 }
            if (r0 == 0) goto L_0x00dc
            java.lang.String r7 = "auto"
            java.lang.String r8 = r0.b     // Catch:{ all -> 0x04b2 }
            boolean r7 = r7.equals(r8)     // Catch:{ all -> 0x04b2 }
            if (r7 == 0) goto L_0x0122
        L_0x00dc:
            java.lang.Boolean r7 = r2.zzcv     // Catch:{ all -> 0x04b2 }
            if (r7 == 0) goto L_0x0111
            com.google.android.gms.measurement.internal.zzjn r9 = new com.google.android.gms.measurement.internal.zzjn     // Catch:{ all -> 0x04b2 }
            java.lang.String r8 = "_npa"
            java.lang.Boolean r7 = r2.zzcv     // Catch:{ all -> 0x04b2 }
            boolean r7 = r7.booleanValue()     // Catch:{ all -> 0x04b2 }
            if (r7 == 0) goto L_0x00ef
            r16 = r11
            goto L_0x00f1
        L_0x00ef:
            r16 = r3
        L_0x00f1:
            java.lang.Long r16 = java.lang.Long.valueOf(r16)     // Catch:{ all -> 0x04b2 }
            java.lang.String r17 = "auto"
            r7 = r9
            r3 = r9
            r9 = r5
            r11 = r16
            r12 = r17
            r7.<init>(r8, r9, r11, r12)     // Catch:{ all -> 0x04b2 }
            if (r0 == 0) goto L_0x010d
            java.lang.Object r0 = r0.e     // Catch:{ all -> 0x04b2 }
            java.lang.Long r4 = r3.zzts     // Catch:{ all -> 0x04b2 }
            boolean r0 = r0.equals(r4)     // Catch:{ all -> 0x04b2 }
            if (r0 != 0) goto L_0x0122
        L_0x010d:
            r1.a((com.google.android.gms.measurement.internal.zzjn) r3, (com.google.android.gms.measurement.internal.zzn) r2)     // Catch:{ all -> 0x04b2 }
            goto L_0x0122
        L_0x0111:
            if (r0 == 0) goto L_0x0122
            com.google.android.gms.measurement.internal.zzjn r0 = new com.google.android.gms.measurement.internal.zzjn     // Catch:{ all -> 0x04b2 }
            java.lang.String r8 = "_npa"
            r11 = 0
            java.lang.String r12 = "auto"
            r7 = r0
            r9 = r5
            r7.<init>(r8, r9, r11, r12)     // Catch:{ all -> 0x04b2 }
            r1.b((com.google.android.gms.measurement.internal.zzjn) r0, (com.google.android.gms.measurement.internal.zzn) r2)     // Catch:{ all -> 0x04b2 }
        L_0x0122:
            com.google.android.gms.measurement.internal.zzx r0 = r18.zzgy()     // Catch:{ all -> 0x04b2 }
            java.lang.String r3 = r2.packageName     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.measurement.internal.zzf r0 = r0.zzab(r3)     // Catch:{ all -> 0x04b2 }
            r3 = 0
            if (r0 == 0) goto L_0x01ee
            com.google.android.gms.measurement.internal.zzfj r4 = r1.zzj     // Catch:{ all -> 0x04b2 }
            r4.zzz()     // Catch:{ all -> 0x04b2 }
            java.lang.String r4 = r2.zzcg     // Catch:{ all -> 0x04b2 }
            java.lang.String r7 = r0.getGmpAppId()     // Catch:{ all -> 0x04b2 }
            java.lang.String r8 = r2.zzcu     // Catch:{ all -> 0x04b2 }
            java.lang.String r9 = r0.zzah()     // Catch:{ all -> 0x04b2 }
            boolean r4 = com.google.android.gms.measurement.internal.zzjs.a(r4, r7, r8, r9)     // Catch:{ all -> 0x04b2 }
            if (r4 == 0) goto L_0x01ee
            com.google.android.gms.measurement.internal.zzfj r4 = r1.zzj     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.measurement.internal.zzef r4 = r4.zzab()     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.measurement.internal.zzeh r4 = r4.zzgn()     // Catch:{ all -> 0x04b2 }
            java.lang.String r7 = "New GMP App Id passed in. Removing cached database data. appId"
            java.lang.String r8 = r0.zzag()     // Catch:{ all -> 0x04b2 }
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r8)     // Catch:{ all -> 0x04b2 }
            r4.zza(r7, r8)     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.measurement.internal.zzx r4 = r18.zzgy()     // Catch:{ all -> 0x04b2 }
            java.lang.String r7 = r0.zzag()     // Catch:{ all -> 0x04b2 }
            r4.c()     // Catch:{ all -> 0x04b2 }
            r4.zzo()     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r7)     // Catch:{ all -> 0x04b2 }
            android.database.sqlite.SQLiteDatabase r0 = r4.d()     // Catch:{ SQLiteException -> 0x01db }
            java.lang.String[] r8 = new java.lang.String[r14]     // Catch:{ SQLiteException -> 0x01db }
            r8[r13] = r7     // Catch:{ SQLiteException -> 0x01db }
            java.lang.String r9 = "events"
            java.lang.String r10 = "app_id=?"
            int r9 = r0.delete(r9, r10, r8)     // Catch:{ SQLiteException -> 0x01db }
            int r9 = r9 + r13
            java.lang.String r10 = "user_attributes"
            java.lang.String r11 = "app_id=?"
            int r10 = r0.delete(r10, r11, r8)     // Catch:{ SQLiteException -> 0x01db }
            int r9 = r9 + r10
            java.lang.String r10 = "conditional_properties"
            java.lang.String r11 = "app_id=?"
            int r10 = r0.delete(r10, r11, r8)     // Catch:{ SQLiteException -> 0x01db }
            int r9 = r9 + r10
            java.lang.String r10 = "apps"
            java.lang.String r11 = "app_id=?"
            int r10 = r0.delete(r10, r11, r8)     // Catch:{ SQLiteException -> 0x01db }
            int r9 = r9 + r10
            java.lang.String r10 = "raw_events"
            java.lang.String r11 = "app_id=?"
            int r10 = r0.delete(r10, r11, r8)     // Catch:{ SQLiteException -> 0x01db }
            int r9 = r9 + r10
            java.lang.String r10 = "raw_events_metadata"
            java.lang.String r11 = "app_id=?"
            int r10 = r0.delete(r10, r11, r8)     // Catch:{ SQLiteException -> 0x01db }
            int r9 = r9 + r10
            java.lang.String r10 = "event_filters"
            java.lang.String r11 = "app_id=?"
            int r10 = r0.delete(r10, r11, r8)     // Catch:{ SQLiteException -> 0x01db }
            int r9 = r9 + r10
            java.lang.String r10 = "property_filters"
            java.lang.String r11 = "app_id=?"
            int r10 = r0.delete(r10, r11, r8)     // Catch:{ SQLiteException -> 0x01db }
            int r9 = r9 + r10
            java.lang.String r10 = "audience_filter_values"
            java.lang.String r11 = "app_id=?"
            int r0 = r0.delete(r10, r11, r8)     // Catch:{ SQLiteException -> 0x01db }
            int r9 = r9 + r0
            if (r9 <= 0) goto L_0x01ed
            com.google.android.gms.measurement.internal.zzef r0 = r4.zzab()     // Catch:{ SQLiteException -> 0x01db }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzgs()     // Catch:{ SQLiteException -> 0x01db }
            java.lang.String r8 = "Deleted application data. app, records"
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ SQLiteException -> 0x01db }
            r0.zza(r8, r7, r9)     // Catch:{ SQLiteException -> 0x01db }
            goto L_0x01ed
        L_0x01db:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzef r4 = r4.zzab()     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.measurement.internal.zzeh r4 = r4.zzgk()     // Catch:{ all -> 0x04b2 }
            java.lang.String r8 = "Error deleting application data. appId, error"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r7)     // Catch:{ all -> 0x04b2 }
            r4.zza(r8, r7, r0)     // Catch:{ all -> 0x04b2 }
        L_0x01ed:
            r0 = r3
        L_0x01ee:
            if (r0 == 0) goto L_0x0258
            long r7 = r0.zzam()     // Catch:{ all -> 0x04b2 }
            r9 = -2147483648(0xffffffff80000000, double:NaN)
            int r4 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r4 == 0) goto L_0x0227
            long r7 = r0.zzam()     // Catch:{ all -> 0x04b2 }
            long r9 = r2.zzcn     // Catch:{ all -> 0x04b2 }
            int r4 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r4 == 0) goto L_0x0258
            android.os.Bundle r4 = new android.os.Bundle     // Catch:{ all -> 0x04b2 }
            r4.<init>()     // Catch:{ all -> 0x04b2 }
            java.lang.String r7 = "_pv"
            java.lang.String r0 = r0.zzal()     // Catch:{ all -> 0x04b2 }
            r4.putString(r7, r0)     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.measurement.internal.zzai r0 = new com.google.android.gms.measurement.internal.zzai     // Catch:{ all -> 0x04b2 }
            java.lang.String r8 = "_au"
            com.google.android.gms.measurement.internal.zzah r9 = new com.google.android.gms.measurement.internal.zzah     // Catch:{ all -> 0x04b2 }
            r9.<init>(r4)     // Catch:{ all -> 0x04b2 }
            java.lang.String r10 = "auto"
            r7 = r0
            r11 = r5
            r7.<init>(r8, r9, r10, r11)     // Catch:{ all -> 0x04b2 }
        L_0x0223:
            r1.a((com.google.android.gms.measurement.internal.zzai) r0, (com.google.android.gms.measurement.internal.zzn) r2)     // Catch:{ all -> 0x04b2 }
            goto L_0x0258
        L_0x0227:
            java.lang.String r4 = r0.zzal()     // Catch:{ all -> 0x04b2 }
            if (r4 == 0) goto L_0x0258
            java.lang.String r4 = r0.zzal()     // Catch:{ all -> 0x04b2 }
            java.lang.String r7 = r2.zzcm     // Catch:{ all -> 0x04b2 }
            boolean r4 = r4.equals(r7)     // Catch:{ all -> 0x04b2 }
            if (r4 != 0) goto L_0x0258
            android.os.Bundle r4 = new android.os.Bundle     // Catch:{ all -> 0x04b2 }
            r4.<init>()     // Catch:{ all -> 0x04b2 }
            java.lang.String r7 = "_pv"
            java.lang.String r0 = r0.zzal()     // Catch:{ all -> 0x04b2 }
            r4.putString(r7, r0)     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.measurement.internal.zzai r0 = new com.google.android.gms.measurement.internal.zzai     // Catch:{ all -> 0x04b2 }
            java.lang.String r8 = "_au"
            com.google.android.gms.measurement.internal.zzah r9 = new com.google.android.gms.measurement.internal.zzah     // Catch:{ all -> 0x04b2 }
            r9.<init>(r4)     // Catch:{ all -> 0x04b2 }
            java.lang.String r10 = "auto"
            r7 = r0
            r11 = r5
            r7.<init>(r8, r9, r10, r11)     // Catch:{ all -> 0x04b2 }
            goto L_0x0223
        L_0x0258:
            r18.zzg(r19)     // Catch:{ all -> 0x04b2 }
            if (r15 != 0) goto L_0x026a
            com.google.android.gms.measurement.internal.zzx r0 = r18.zzgy()     // Catch:{ all -> 0x04b2 }
            java.lang.String r4 = r2.packageName     // Catch:{ all -> 0x04b2 }
            java.lang.String r7 = "_f"
        L_0x0265:
            com.google.android.gms.measurement.internal.zzae r0 = r0.zzc(r4, r7)     // Catch:{ all -> 0x04b2 }
            goto L_0x0276
        L_0x026a:
            if (r15 != r14) goto L_0x0275
            com.google.android.gms.measurement.internal.zzx r0 = r18.zzgy()     // Catch:{ all -> 0x04b2 }
            java.lang.String r4 = r2.packageName     // Catch:{ all -> 0x04b2 }
            java.lang.String r7 = "_v"
            goto L_0x0265
        L_0x0275:
            r0 = r3
        L_0x0276:
            if (r0 != 0) goto L_0x0489
            r7 = 3600000(0x36ee80, double:1.7786363E-317)
            long r9 = r5 / r7
            r11 = 1
            long r9 = r9 + r11
            long r9 = r9 * r7
            if (r15 != 0) goto L_0x03ee
            com.google.android.gms.measurement.internal.zzjn r0 = new com.google.android.gms.measurement.internal.zzjn     // Catch:{ all -> 0x04b2 }
            java.lang.String r8 = "_fot"
            java.lang.Long r4 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x04b2 }
            java.lang.String r15 = "auto"
            r7 = r0
            r9 = r5
            r13 = r11
            r11 = r4
            r12 = r15
            r7.<init>(r8, r9, r11, r12)     // Catch:{ all -> 0x04b2 }
            r1.a((com.google.android.gms.measurement.internal.zzjn) r0, (com.google.android.gms.measurement.internal.zzn) r2)     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.measurement.internal.zzfj r0 = r1.zzj     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.measurement.internal.zzs r0 = r0.zzad()     // Catch:{ all -> 0x04b2 }
            java.lang.String r4 = r2.zzcg     // Catch:{ all -> 0x04b2 }
            boolean r0 = r0.i(r4)     // Catch:{ all -> 0x04b2 }
            if (r0 == 0) goto L_0x02b5
            r18.zzo()     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.measurement.internal.zzfj r0 = r1.zzj     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.measurement.internal.zzeu r0 = r0.zzht()     // Catch:{ all -> 0x04b2 }
            java.lang.String r4 = r2.packageName     // Catch:{ all -> 0x04b2 }
            r0.a(r4)     // Catch:{ all -> 0x04b2 }
        L_0x02b5:
            r18.zzo()     // Catch:{ all -> 0x04b2 }
            r18.b()     // Catch:{ all -> 0x04b2 }
            android.os.Bundle r4 = new android.os.Bundle     // Catch:{ all -> 0x04b2 }
            r4.<init>()     // Catch:{ all -> 0x04b2 }
            java.lang.String r0 = "_c"
            r4.putLong(r0, r13)     // Catch:{ all -> 0x04b2 }
            java.lang.String r0 = "_r"
            r4.putLong(r0, r13)     // Catch:{ all -> 0x04b2 }
            java.lang.String r0 = "_uwa"
            r7 = 0
            r4.putLong(r0, r7)     // Catch:{ all -> 0x04b2 }
            java.lang.String r0 = "_pfo"
            r4.putLong(r0, r7)     // Catch:{ all -> 0x04b2 }
            java.lang.String r0 = "_sys"
            r4.putLong(r0, r7)     // Catch:{ all -> 0x04b2 }
            java.lang.String r0 = "_sysu"
            r4.putLong(r0, r7)     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.measurement.internal.zzfj r0 = r1.zzj     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.measurement.internal.zzs r0 = r0.zzad()     // Catch:{ all -> 0x04b2 }
            java.lang.String r7 = r2.packageName     // Catch:{ all -> 0x04b2 }
            boolean r0 = r0.o(r7)     // Catch:{ all -> 0x04b2 }
            if (r0 == 0) goto L_0x02f3
            java.lang.String r0 = "_et"
            r4.putLong(r0, r13)     // Catch:{ all -> 0x04b2 }
        L_0x02f3:
            boolean r0 = r2.zzdt     // Catch:{ all -> 0x04b2 }
            if (r0 == 0) goto L_0x02fc
            java.lang.String r0 = "_dac"
            r4.putLong(r0, r13)     // Catch:{ all -> 0x04b2 }
        L_0x02fc:
            com.google.android.gms.measurement.internal.zzfj r0 = r1.zzj     // Catch:{ all -> 0x04b2 }
            android.content.Context r0 = r0.getContext()     // Catch:{ all -> 0x04b2 }
            android.content.pm.PackageManager r0 = r0.getPackageManager()     // Catch:{ all -> 0x04b2 }
            if (r0 != 0) goto L_0x031f
            com.google.android.gms.measurement.internal.zzfj r0 = r1.zzj     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.measurement.internal.zzef r0 = r0.zzab()     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzgk()     // Catch:{ all -> 0x04b2 }
            java.lang.String r3 = "PackageManager is null, first open report might be inaccurate. appId"
            java.lang.String r7 = r2.packageName     // Catch:{ all -> 0x04b2 }
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r7)     // Catch:{ all -> 0x04b2 }
            r0.zza(r3, r7)     // Catch:{ all -> 0x04b2 }
            goto L_0x03ba
        L_0x031f:
            com.google.android.gms.measurement.internal.zzfj r0 = r1.zzj     // Catch:{ NameNotFoundException -> 0x0331 }
            android.content.Context r0 = r0.getContext()     // Catch:{ NameNotFoundException -> 0x0331 }
            com.google.android.gms.common.wrappers.PackageManagerWrapper r0 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r0)     // Catch:{ NameNotFoundException -> 0x0331 }
            java.lang.String r7 = r2.packageName     // Catch:{ NameNotFoundException -> 0x0331 }
            r8 = 0
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r7, r8)     // Catch:{ NameNotFoundException -> 0x0331 }
            goto L_0x0348
        L_0x0331:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzfj r7 = r1.zzj     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.measurement.internal.zzef r7 = r7.zzab()     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.measurement.internal.zzeh r7 = r7.zzgk()     // Catch:{ all -> 0x04b2 }
            java.lang.String r8 = "Package info is null, first open report might be inaccurate. appId"
            java.lang.String r9 = r2.packageName     // Catch:{ all -> 0x04b2 }
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r9)     // Catch:{ all -> 0x04b2 }
            r7.zza(r8, r9, r0)     // Catch:{ all -> 0x04b2 }
            r0 = r3
        L_0x0348:
            if (r0 == 0) goto L_0x037a
            long r7 = r0.firstInstallTime     // Catch:{ all -> 0x04b2 }
            r9 = 0
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 == 0) goto L_0x037a
            long r7 = r0.firstInstallTime     // Catch:{ all -> 0x04b2 }
            long r9 = r0.lastUpdateTime     // Catch:{ all -> 0x04b2 }
            int r0 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r0 == 0) goto L_0x0361
            java.lang.String r0 = "_uwa"
            r4.putLong(r0, r13)     // Catch:{ all -> 0x04b2 }
            r0 = 0
            goto L_0x0362
        L_0x0361:
            r0 = 1
        L_0x0362:
            com.google.android.gms.measurement.internal.zzjn r15 = new com.google.android.gms.measurement.internal.zzjn     // Catch:{ all -> 0x04b2 }
            java.lang.String r8 = "_fi"
            if (r0 == 0) goto L_0x036a
            r9 = r13
            goto L_0x036c
        L_0x036a:
            r9 = 0
        L_0x036c:
            java.lang.Long r11 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x04b2 }
            java.lang.String r12 = "auto"
            r7 = r15
            r9 = r5
            r7.<init>(r8, r9, r11, r12)     // Catch:{ all -> 0x04b2 }
            r1.a((com.google.android.gms.measurement.internal.zzjn) r15, (com.google.android.gms.measurement.internal.zzn) r2)     // Catch:{ all -> 0x04b2 }
        L_0x037a:
            com.google.android.gms.measurement.internal.zzfj r0 = r1.zzj     // Catch:{ NameNotFoundException -> 0x038c }
            android.content.Context r0 = r0.getContext()     // Catch:{ NameNotFoundException -> 0x038c }
            com.google.android.gms.common.wrappers.PackageManagerWrapper r0 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r0)     // Catch:{ NameNotFoundException -> 0x038c }
            java.lang.String r7 = r2.packageName     // Catch:{ NameNotFoundException -> 0x038c }
            r8 = 0
            android.content.pm.ApplicationInfo r3 = r0.getApplicationInfo(r7, r8)     // Catch:{ NameNotFoundException -> 0x038c }
            goto L_0x03a2
        L_0x038c:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzfj r7 = r1.zzj     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.measurement.internal.zzef r7 = r7.zzab()     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.measurement.internal.zzeh r7 = r7.zzgk()     // Catch:{ all -> 0x04b2 }
            java.lang.String r8 = "Application info is null, first open report might be inaccurate. appId"
            java.lang.String r9 = r2.packageName     // Catch:{ all -> 0x04b2 }
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r9)     // Catch:{ all -> 0x04b2 }
            r7.zza(r8, r9, r0)     // Catch:{ all -> 0x04b2 }
        L_0x03a2:
            if (r3 == 0) goto L_0x03ba
            int r0 = r3.flags     // Catch:{ all -> 0x04b2 }
            r7 = 1
            r0 = r0 & r7
            if (r0 == 0) goto L_0x03af
            java.lang.String r0 = "_sys"
            r4.putLong(r0, r13)     // Catch:{ all -> 0x04b2 }
        L_0x03af:
            int r0 = r3.flags     // Catch:{ all -> 0x04b2 }
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x03ba
            java.lang.String r0 = "_sysu"
            r4.putLong(r0, r13)     // Catch:{ all -> 0x04b2 }
        L_0x03ba:
            com.google.android.gms.measurement.internal.zzx r0 = r18.zzgy()     // Catch:{ all -> 0x04b2 }
            java.lang.String r3 = r2.packageName     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3)     // Catch:{ all -> 0x04b2 }
            r0.zzo()     // Catch:{ all -> 0x04b2 }
            r0.c()     // Catch:{ all -> 0x04b2 }
            java.lang.String r7 = "first_open_count"
            long r7 = r0.c(r3, r7)     // Catch:{ all -> 0x04b2 }
            r9 = 0
            int r0 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r0 < 0) goto L_0x03da
            java.lang.String r0 = "_pfo"
            r4.putLong(r0, r7)     // Catch:{ all -> 0x04b2 }
        L_0x03da:
            com.google.android.gms.measurement.internal.zzai r0 = new com.google.android.gms.measurement.internal.zzai     // Catch:{ all -> 0x04b2 }
            java.lang.String r8 = "_f"
            com.google.android.gms.measurement.internal.zzah r9 = new com.google.android.gms.measurement.internal.zzah     // Catch:{ all -> 0x04b2 }
            r9.<init>(r4)     // Catch:{ all -> 0x04b2 }
            java.lang.String r10 = "auto"
            r7 = r0
            r11 = r5
            r7.<init>(r8, r9, r10, r11)     // Catch:{ all -> 0x04b2 }
            r1.a((com.google.android.gms.measurement.internal.zzai) r0, (com.google.android.gms.measurement.internal.zzn) r2)     // Catch:{ all -> 0x04b2 }
            goto L_0x0448
        L_0x03ee:
            r13 = r11
            r3 = 1
            if (r15 != r3) goto L_0x0448
            com.google.android.gms.measurement.internal.zzjn r0 = new com.google.android.gms.measurement.internal.zzjn     // Catch:{ all -> 0x04b2 }
            java.lang.String r8 = "_fvt"
            java.lang.Long r11 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x04b2 }
            java.lang.String r12 = "auto"
            r7 = r0
            r9 = r5
            r7.<init>(r8, r9, r11, r12)     // Catch:{ all -> 0x04b2 }
            r1.a((com.google.android.gms.measurement.internal.zzjn) r0, (com.google.android.gms.measurement.internal.zzn) r2)     // Catch:{ all -> 0x04b2 }
            r18.zzo()     // Catch:{ all -> 0x04b2 }
            r18.b()     // Catch:{ all -> 0x04b2 }
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x04b2 }
            r0.<init>()     // Catch:{ all -> 0x04b2 }
            java.lang.String r3 = "_c"
            r0.putLong(r3, r13)     // Catch:{ all -> 0x04b2 }
            java.lang.String r3 = "_r"
            r0.putLong(r3, r13)     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.measurement.internal.zzfj r3 = r1.zzj     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.measurement.internal.zzs r3 = r3.zzad()     // Catch:{ all -> 0x04b2 }
            java.lang.String r4 = r2.packageName     // Catch:{ all -> 0x04b2 }
            boolean r3 = r3.o(r4)     // Catch:{ all -> 0x04b2 }
            if (r3 == 0) goto L_0x042c
            java.lang.String r3 = "_et"
            r0.putLong(r3, r13)     // Catch:{ all -> 0x04b2 }
        L_0x042c:
            boolean r3 = r2.zzdt     // Catch:{ all -> 0x04b2 }
            if (r3 == 0) goto L_0x0435
            java.lang.String r3 = "_dac"
            r0.putLong(r3, r13)     // Catch:{ all -> 0x04b2 }
        L_0x0435:
            com.google.android.gms.measurement.internal.zzai r3 = new com.google.android.gms.measurement.internal.zzai     // Catch:{ all -> 0x04b2 }
            java.lang.String r8 = "_v"
            com.google.android.gms.measurement.internal.zzah r9 = new com.google.android.gms.measurement.internal.zzah     // Catch:{ all -> 0x04b2 }
            r9.<init>(r0)     // Catch:{ all -> 0x04b2 }
            java.lang.String r10 = "auto"
            r7 = r3
            r11 = r5
            r7.<init>(r8, r9, r10, r11)     // Catch:{ all -> 0x04b2 }
            r1.a((com.google.android.gms.measurement.internal.zzai) r3, (com.google.android.gms.measurement.internal.zzn) r2)     // Catch:{ all -> 0x04b2 }
        L_0x0448:
            com.google.android.gms.measurement.internal.zzfj r0 = r1.zzj     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.measurement.internal.zzs r0 = r0.zzad()     // Catch:{ all -> 0x04b2 }
            java.lang.String r3 = r2.packageName     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r4 = com.google.android.gms.measurement.internal.zzak.zzii     // Catch:{ all -> 0x04b2 }
            boolean r0 = r0.zze(r3, r4)     // Catch:{ all -> 0x04b2 }
            if (r0 != 0) goto L_0x04a3
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x04b2 }
            r0.<init>()     // Catch:{ all -> 0x04b2 }
            java.lang.String r3 = "_et"
            r0.putLong(r3, r13)     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.measurement.internal.zzfj r3 = r1.zzj     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.measurement.internal.zzs r3 = r3.zzad()     // Catch:{ all -> 0x04b2 }
            java.lang.String r4 = r2.packageName     // Catch:{ all -> 0x04b2 }
            boolean r3 = r3.o(r4)     // Catch:{ all -> 0x04b2 }
            if (r3 == 0) goto L_0x0475
            java.lang.String r3 = "_fr"
            r0.putLong(r3, r13)     // Catch:{ all -> 0x04b2 }
        L_0x0475:
            com.google.android.gms.measurement.internal.zzai r3 = new com.google.android.gms.measurement.internal.zzai     // Catch:{ all -> 0x04b2 }
            java.lang.String r8 = "_e"
            com.google.android.gms.measurement.internal.zzah r9 = new com.google.android.gms.measurement.internal.zzah     // Catch:{ all -> 0x04b2 }
            r9.<init>(r0)     // Catch:{ all -> 0x04b2 }
            java.lang.String r10 = "auto"
            r7 = r3
            r11 = r5
            r7.<init>(r8, r9, r10, r11)     // Catch:{ all -> 0x04b2 }
        L_0x0485:
            r1.a((com.google.android.gms.measurement.internal.zzai) r3, (com.google.android.gms.measurement.internal.zzn) r2)     // Catch:{ all -> 0x04b2 }
            goto L_0x04a3
        L_0x0489:
            boolean r0 = r2.zzdq     // Catch:{ all -> 0x04b2 }
            if (r0 == 0) goto L_0x04a3
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x04b2 }
            r0.<init>()     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.measurement.internal.zzai r3 = new com.google.android.gms.measurement.internal.zzai     // Catch:{ all -> 0x04b2 }
            java.lang.String r8 = "_cd"
            com.google.android.gms.measurement.internal.zzah r9 = new com.google.android.gms.measurement.internal.zzah     // Catch:{ all -> 0x04b2 }
            r9.<init>(r0)     // Catch:{ all -> 0x04b2 }
            java.lang.String r10 = "auto"
            r7 = r3
            r11 = r5
            r7.<init>(r8, r9, r10, r11)     // Catch:{ all -> 0x04b2 }
            goto L_0x0485
        L_0x04a3:
            com.google.android.gms.measurement.internal.zzx r0 = r18.zzgy()     // Catch:{ all -> 0x04b2 }
            r0.setTransactionSuccessful()     // Catch:{ all -> 0x04b2 }
            com.google.android.gms.measurement.internal.zzx r0 = r18.zzgy()
            r0.endTransaction()
            return
        L_0x04b2:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzx r2 = r18.zzgy()
            r2.endTransaction()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzjg.c(com.google.android.gms.measurement.internal.zzn):void");
    }

    /* access modifiers changed from: package-private */
    public final String d(zzn zzn) {
        try {
            return (String) this.zzj.zzaa().zza(new zzjk(this, zzn)).get(30000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            this.zzj.zzab().zzgk().zza("Failed to get app instance id. appId", zzef.a(zzn.packageName), e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void d() {
        String str;
        zzeh zzeh;
        zzo();
        b();
        if (!this.zzsx) {
            this.zzsx = true;
            zzo();
            b();
            if ((this.zzj.zzad().zza(zzak.zzim) || zzjr()) && zzjp()) {
                int zza2 = zza(this.zztg);
                int f = this.zzj.zzr().f();
                zzo();
                if (zza2 > f) {
                    zzeh = this.zzj.zzab().zzgk();
                    str = "Panic: can't downgrade version. Previous, current version";
                } else if (zza2 < f) {
                    if (zza(f, this.zztg)) {
                        zzeh = this.zzj.zzab().zzgs();
                        str = "Storage version upgraded. Previous, current version";
                    } else {
                        zzeh = this.zzj.zzab().zzgk();
                        str = "Storage version upgrade failed. Previous, current version";
                    }
                }
                zzeh.zza(str, Integer.valueOf(zza2), Integer.valueOf(f));
            }
        }
        if (!this.zzsw && !this.zzj.zzad().zza(zzak.zzim)) {
            this.zzj.zzab().zzgq().zzao("This instance being marked as an uploader");
            this.zzsw = true;
            zzjn();
        }
    }

    /* access modifiers changed from: package-private */
    public final void e() {
        this.zztb++;
    }

    /* access modifiers changed from: package-private */
    public final zzfj f() {
        return this.zzj;
    }

    public final Context getContext() {
        return this.zzj.getContext();
    }

    public final zzfc zzaa() {
        return this.zzj.zzaa();
    }

    public final zzef zzab() {
        return this.zzj.zzab();
    }

    public final zzs zzad() {
        return this.zzj.zzad();
    }

    public final zzr zzae() {
        return this.zzj.zzae();
    }

    public final zzjo zzgw() {
        zza((zzjh) this.zzsu);
        return this.zzsu;
    }

    public final zzp zzgx() {
        zza((zzjh) this.zzst);
        return this.zzst;
    }

    public final zzx zzgy() {
        zza((zzjh) this.zzsq);
        return this.zzsq;
    }

    public final zzfd zzgz() {
        zza((zzjh) this.zzso);
        return this.zzso;
    }

    public final zzej zzjf() {
        zza((zzjh) this.zzsp);
        return this.zzsp;
    }

    public final zzhp zzji() {
        zza((zzjh) this.zzsv);
        return this.zzsv;
    }

    public final Clock zzx() {
        return this.zzj.zzx();
    }

    public final zzed zzy() {
        return this.zzj.zzy();
    }

    public final zzjs zzz() {
        return this.zzj.zzz();
    }
}
