package com.google.android.gms.internal.firebase_ml;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.auth.api.AuthProxy;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;
import com.google.android.gms.internal.firebase_ml.zzeq;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.common.FirebaseMLException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class zzop implements zzmx<zzix, zzom> {
    private static final Map<FirebaseApp, zzop> zzaj = new HashMap();
    private static final GmsLogger zzaoe = new GmsLogger("ImageAnnotatorTask", "");
    private static final zzfv zzauk = new zzgh();
    private static final zzgl zzaul = zzgu.zzgh();
    private final GoogleApiClient zzaui;
    /* access modifiers changed from: private */
    public String zzaum;
    private final boolean zzaun;
    private final zziv zzauo;
    private final String zzsu;

    private zzop(FirebaseApp firebaseApp, boolean z, GoogleApiClient googleApiClient) {
        zziv zziv;
        this.zzaun = z;
        if (z) {
            this.zzaui = googleApiClient;
            zziv = new zzoq(this);
        } else {
            this.zzaui = null;
            zziv = new zzor(this, zzh(firebaseApp), firebaseApp);
        }
        this.zzauo = zziv;
        this.zzsu = String.format("FirebaseML_%s", new Object[]{firebaseApp.getName()});
    }

    @WorkerThread
    private final String getSpatulaHeader() {
        if (!this.zzaui.isConnected()) {
            this.zzaui.blockingConnect(3, TimeUnit.SECONDS);
        }
        try {
            return AuthProxy.ProxyApi.getSpatulaHeader(this.zzaui).await(3, TimeUnit.SECONDS).getSpatulaHeader();
        } catch (SecurityException unused) {
            return null;
        }
    }

    public static synchronized zzop zza(@NonNull FirebaseApp firebaseApp, boolean z, @Nullable GoogleApiClient googleApiClient) {
        zzop zzop;
        synchronized (zzop.class) {
            zzop = zzaj.get(firebaseApp);
            if (zzop == null) {
                zzop = new zzop(firebaseApp, z, googleApiClient);
                zzaj.put(firebaseApp, zzop);
            }
        }
        return zzop;
    }

    /* access modifiers changed from: private */
    public static String zza(Context context, String str) {
        try {
            byte[] packageCertificateHashBytes = AndroidUtilsLight.getPackageCertificateHashBytes(context, str);
            if (packageCertificateHashBytes != null) {
                return Hex.bytesToStringUppercase(packageCertificateHashBytes, false);
            }
            GmsLogger gmsLogger = zzaoe;
            String valueOf = String.valueOf(str);
            gmsLogger.e("ImageAnnotatorTask", valueOf.length() != 0 ? "Could not get fingerprint hash: ".concat(valueOf) : new String("Could not get fingerprint hash: "));
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            GmsLogger gmsLogger2 = zzaoe;
            String valueOf2 = String.valueOf(str);
            gmsLogger2.e("ImageAnnotatorTask", valueOf2.length() != 0 ? "No such package: ".concat(valueOf2) : new String("No such package: "), e);
            return null;
        }
    }

    private static String zzh(FirebaseApp firebaseApp) {
        Bundle bundle;
        String apiKey = firebaseApp.getOptions().getApiKey();
        Context applicationContext = firebaseApp.getApplicationContext();
        try {
            ApplicationInfo applicationInfo = applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), 128);
            if (applicationInfo == null || (bundle = applicationInfo.metaData) == null) {
                return apiKey;
            }
            String string = bundle.getString("com.firebase.ml.cloud.ApiKeyForDebug");
            return !TextUtils.isEmpty(string) ? string : apiKey;
        } catch (PackageManager.NameNotFoundException e) {
            GmsLogger gmsLogger = zzaoe;
            String valueOf = String.valueOf(applicationContext.getPackageName());
            gmsLogger.e("ImageAnnotatorTask", valueOf.length() != 0 ? "No such package: ".concat(valueOf) : new String("No such package: "), e);
            return apiKey;
        }
    }

    @WorkerThread
    public final zzix zza(zzom zzom) {
        zziy zzc = new zziy().zzc(Collections.singletonList(new zziw().zzb(zzom.features).zza(new zzjj().zzf(zzom.zzaud)).zza(zzom.imageContext)));
        int i = 14;
        try {
            zziq zzgv = ((zzir) new zzir(zzauk, zzaul, new zzos(this)).zzk(this.zzsu)).zza(this.zzauo).zzgv();
            if (this.zzaun) {
                this.zzaum = getSpatulaHeader();
                if (TextUtils.isEmpty(this.zzaum)) {
                    zzaoe.e("ImageAnnotatorTask", "Failed to contact Google Play services for certificate fingerprint matching. Please ensure you have latest Google Play services installed");
                    throw new FirebaseMLException("Failed to contact Google Play services for certificate fingerprint matching. Please ensure you have latest Google Play services installed", 14);
                }
            }
            List<zzix> zzgz = ((zziz) new zzis(zzgv).zza(zzc).zzdz()).zzgz();
            if (zzgz != null && zzgz.size() > 0) {
                return zzgz.get(0);
            }
            throw new FirebaseMLException("Empty response from cloud vision api.", 13);
        } catch (zzer e) {
            GmsLogger gmsLogger = zzaoe;
            String valueOf = String.valueOf(e.zzds());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 44);
            sb.append("batchAnnotateImages call failed with error: ");
            sb.append(valueOf);
            gmsLogger.e("ImageAnnotatorTask", sb.toString());
            if (this.zzaun) {
                Log.d("ImageAnnotatorTask", "If you are developing / testing on a simulator, either register your development app on Firebase console or turn off enforceCertFingerprintMatch()");
            }
            String message = e.getMessage();
            if (e.getStatusCode() != 400) {
                if (e.zzds() != null && e.zzds().zzdp() != null) {
                    i = 13;
                    for (zzeq.zza reason : e.zzds().zzdp()) {
                        String reason2 = reason.getReason();
                        if (reason2 != null) {
                            if (reason2.equals("rateLimitExceeded") || reason2.equals("dailyLimitExceeded") || reason2.equals("userRateLimitExceeded")) {
                                i = 8;
                                continue;
                            } else {
                                if (!reason2.equals("accessNotConfigured")) {
                                    if (reason2.equals("forbidden") || reason2.equals("inactiveBillingState")) {
                                        message = String.format("If you haven't set up billing, please go to Firebase console to set up billing: %s. If you are specifying a debug Api Key override and turned on Api Key restrictions, make sure the restrictions are set up correctly", new Object[]{"https://firebase.corp.google.com/u/0/project/_/overview?purchaseBillingPlan=true"});
                                    }
                                }
                                i = 7;
                                continue;
                            }
                        }
                        if (i != 13) {
                            break;
                        }
                    }
                } else {
                    i = 13;
                }
            }
            throw new FirebaseMLException(message, i);
        } catch (IOException e2) {
            zzaoe.e("ImageAnnotatorTask", "batchAnnotateImages call failed with exception: ", e2);
            throw new FirebaseMLException("Cloud Vision batchAnnotateImages call failure", 13, e2);
        }
    }

    public final zznh zzkh() {
        return null;
    }
}
