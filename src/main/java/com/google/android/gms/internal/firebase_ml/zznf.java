package com.google.android.gms.internal.firebase_ml;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzlo;
import com.google.firebase.FirebaseApp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public final class zznf {
    @GuardedBy("MlStatsLogger.class")
    private static final Map<String, zznf> zzaj = new HashMap();
    private static final GmsLogger zzaoe = new GmsLogger("MlStatsLogger", "");
    @Nullable
    private static List<String> zzaos;
    private final FirebaseApp zzaot;
    private final String zzaou;
    private final String zzaov;
    private final String zzaow;
    private final String zzaox;
    private final String zzaoy;
    private final ClearcutLogger zzaoz;
    @GuardedBy("this")
    private final Map<zzly, Long> zzapa = new HashMap();
    private final int zzapb;

    private zznf(FirebaseApp firebaseApp, int i) {
        this.zzaot = firebaseApp;
        this.zzapb = i;
        String projectId = firebaseApp.getOptions().getProjectId();
        this.zzaow = projectId == null ? "" : projectId;
        String gcmSenderId = firebaseApp.getOptions().getGcmSenderId();
        this.zzaox = gcmSenderId == null ? "" : gcmSenderId;
        String apiKey = firebaseApp.getOptions().getApiKey();
        this.zzaoy = apiKey == null ? "" : apiKey;
        Context applicationContext = firebaseApp.getApplicationContext();
        this.zzaoz = ClearcutLogger.anonymousLogger(applicationContext, "FIREBASE_ML_SDK");
        this.zzaou = applicationContext.getPackageName();
        this.zzaov = zzmv.zza(applicationContext);
    }

    public static synchronized zznf zza(@NonNull FirebaseApp firebaseApp, int i) {
        zznf zznf;
        synchronized (zznf.class) {
            Preconditions.checkNotNull(firebaseApp);
            String str = "";
            switch (i) {
                case 1:
                    str = "_vision";
                    break;
                case 2:
                    str = "_model";
                    break;
                case 3:
                    str = "_natural_language";
                    break;
                case 4:
                    str = "_model_download";
                    break;
            }
            String valueOf = String.valueOf(firebaseApp.getPersistenceKey());
            String valueOf2 = String.valueOf(str);
            String concat = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
            zznf = zzaj.get(concat);
            if (zznf == null) {
                zznf = new zznf(firebaseApp, i);
                zzaj.put(concat, zznf);
            }
        }
        return zznf;
    }

    private final boolean zzen() {
        switch (this.zzapb) {
            case 1:
                return zznl.zzc(this.zzaot);
            case 2:
                return zznl.zzd(this.zzaot);
            case 3:
            case 4:
                return true;
            default:
                return false;
        }
    }

    @NonNull
    private static synchronized List<String> zzkj() {
        synchronized (zznf.class) {
            if (zzaos != null) {
                List<String> list = zzaos;
                return list;
            }
            LocaleListCompat locales = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
            zzaos = new ArrayList(locales.size());
            for (int i = 0; i < locales.size(); i++) {
                zzaos.add(zzmv.a(locales.get(i)));
            }
            List<String> list2 = zzaos;
            return list2;
        }
    }

    public final synchronized void zza(@NonNull zzlo.zzq.zza zza, @NonNull zzly zzly) {
        if (!zzen()) {
            zzaoe.d("MlStatsLogger", "Logging is disabled.");
            return;
        }
        String zzkc = zza.zzit().zzkc();
        if ("NA".equals(zzkc) || "".equals(zzkc)) {
            zzkc = "NA";
        }
        zza.zzb(zzly).zzb(zzlo.zzac.zzkd().zzbn(this.zzaou).zzbo(this.zzaov).zzbp(this.zzaow).zzbs(this.zzaox).zzbt(this.zzaoy).zzbr(zzkc).zzn(zzkj()).zzbq(zzmw.zzkg().getVersion("firebase-ml-common")));
        zzlo.zzq zzq = (zzlo.zzq) ((zztc) zza.zzpx());
        GmsLogger gmsLogger = zzaoe;
        String valueOf = String.valueOf(zzq);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 30);
        sb.append("Logging FirebaseMlSdkLogEvent ");
        sb.append(valueOf);
        gmsLogger.d("MlStatsLogger", sb.toString());
        this.zzaoz.newEvent(zzq.toByteArray()).log();
    }

    public final synchronized void zza(@NonNull zzng zzng, @NonNull zzly zzly) {
        if (zzen()) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (this.zzapa.get(zzly) == null || elapsedRealtime - this.zzapa.get(zzly).longValue() > TimeUnit.SECONDS.toMillis(30)) {
                this.zzapa.put(zzly, Long.valueOf(elapsedRealtime));
                zza(zzng.zzkk(), zzly);
            }
        }
    }
}
