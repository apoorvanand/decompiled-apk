package com.google.android.gms.internal.firebase_ml;

import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.FirebaseApp;

public final class zznl {
    @Nullable
    public static synchronized Long zza(@NonNull FirebaseApp firebaseApp, @NonNull String str) {
        synchronized (zznl.class) {
            long j = firebaseApp.getApplicationContext().getSharedPreferences("com.google.firebase.ml.internal", 0).getLong(String.format("downloading_model_id_%s_%s", new Object[]{firebaseApp.getPersistenceKey(), str}), -1);
            if (j < 0) {
                return null;
            }
            Long valueOf = Long.valueOf(j);
            return valueOf;
        }
    }

    public static synchronized void zza(@NonNull FirebaseApp firebaseApp, @NonNull String str, long j) {
        synchronized (zznl.class) {
            firebaseApp.getApplicationContext().getSharedPreferences("com.google.firebase.ml.internal", 0).edit().putLong(String.format("model_first_use_time_%s_%s", new Object[]{firebaseApp.getPersistenceKey(), str}), j).apply();
        }
    }

    public static synchronized void zza(@NonNull FirebaseApp firebaseApp, @NonNull String str, @NonNull String str2) {
        synchronized (zznl.class) {
            firebaseApp.getApplicationContext().getSharedPreferences("com.google.firebase.ml.internal", 0).edit().putString(String.format("current_model_hash_%s_%s", new Object[]{firebaseApp.getPersistenceKey(), str}), str2).apply();
        }
    }

    public static synchronized void zza(@NonNull FirebaseApp firebaseApp, @NonNull String str, @NonNull String str2, long j) {
        synchronized (zznl.class) {
            firebaseApp.getApplicationContext().getSharedPreferences("com.google.firebase.ml.internal", 0).edit().putString(String.format("downloading_model_hash_%s_%s", new Object[]{firebaseApp.getPersistenceKey(), str}), str2).putLong(String.format("downloading_model_id_%s_%s", new Object[]{firebaseApp.getPersistenceKey(), str}), j).putLong(String.format("downloading_begin_time_%s_%s", new Object[]{firebaseApp.getPersistenceKey(), str}), SystemClock.elapsedRealtime()).apply();
        }
    }

    public static synchronized void zza(@NonNull FirebaseApp firebaseApp, @NonNull String str, @NonNull String str2, @NonNull String str3) {
        synchronized (zznl.class) {
            firebaseApp.getApplicationContext().getSharedPreferences("com.google.firebase.ml.internal", 0).edit().putString(String.format("bad_hash_%s_%s", new Object[]{firebaseApp.getPersistenceKey(), str}), str2).putString("app_version", str3).apply();
        }
    }

    public static synchronized void zza(@NonNull FirebaseApp firebaseApp, boolean z) {
        synchronized (zznl.class) {
            firebaseApp.getApplicationContext().getSharedPreferences("com.google.firebase.ml.internal", 0).edit().putBoolean(String.format("logging_%s_%s", new Object[]{"vision", firebaseApp.getPersistenceKey()}), z).apply();
        }
    }

    @Nullable
    public static synchronized String zzb(@NonNull FirebaseApp firebaseApp, @NonNull String str) {
        String string;
        synchronized (zznl.class) {
            string = firebaseApp.getApplicationContext().getSharedPreferences("com.google.firebase.ml.internal", 0).getString(String.format("downloading_model_hash_%s_%s", new Object[]{firebaseApp.getPersistenceKey(), str}), (String) null);
        }
        return string;
    }

    public static synchronized void zzb(@NonNull FirebaseApp firebaseApp, boolean z) {
        synchronized (zznl.class) {
            firebaseApp.getApplicationContext().getSharedPreferences("com.google.firebase.ml.internal", 0).edit().putBoolean(String.format("logging_%s_%s", new Object[]{"model", firebaseApp.getPersistenceKey()}), z).apply();
        }
    }

    @Nullable
    public static synchronized String zzc(@NonNull FirebaseApp firebaseApp, @NonNull String str) {
        String string;
        synchronized (zznl.class) {
            string = firebaseApp.getApplicationContext().getSharedPreferences("com.google.firebase.ml.internal", 0).getString(String.format("current_model_hash_%s_%s", new Object[]{firebaseApp.getPersistenceKey(), str}), (String) null);
        }
        return string;
    }

    public static synchronized boolean zzc(@NonNull FirebaseApp firebaseApp) {
        boolean z;
        synchronized (zznl.class) {
            z = firebaseApp.getApplicationContext().getSharedPreferences("com.google.firebase.ml.internal", 0).getBoolean(String.format("logging_%s_%s", new Object[]{"vision", firebaseApp.getPersistenceKey()}), true);
        }
        return z;
    }

    @Nullable
    public static synchronized String zzd(@NonNull FirebaseApp firebaseApp, @NonNull String str) {
        String string;
        synchronized (zznl.class) {
            string = firebaseApp.getApplicationContext().getSharedPreferences("com.google.firebase.ml.internal", 0).getString(String.format("bad_hash_%s_%s", new Object[]{firebaseApp.getPersistenceKey(), str}), (String) null);
        }
        return string;
    }

    public static synchronized boolean zzd(@NonNull FirebaseApp firebaseApp) {
        boolean z;
        synchronized (zznl.class) {
            z = firebaseApp.getApplicationContext().getSharedPreferences("com.google.firebase.ml.internal", 0).getBoolean(String.format("logging_%s_%s", new Object[]{"model", firebaseApp.getPersistenceKey()}), true);
        }
        return z;
    }

    public static synchronized long zze(@NonNull FirebaseApp firebaseApp, @NonNull String str) {
        long j;
        synchronized (zznl.class) {
            j = firebaseApp.getApplicationContext().getSharedPreferences("com.google.firebase.ml.internal", 0).getLong(String.format("downloading_begin_time_%s_%s", new Object[]{firebaseApp.getPersistenceKey(), str}), 0);
        }
        return j;
    }

    @Nullable
    public static synchronized String zze(@NonNull FirebaseApp firebaseApp) {
        String string;
        synchronized (zznl.class) {
            string = firebaseApp.getApplicationContext().getSharedPreferences("com.google.firebase.ml.internal", 0).getString("app_version", (String) null);
        }
        return string;
    }

    public static synchronized long zzf(@NonNull FirebaseApp firebaseApp, @NonNull String str) {
        long j;
        synchronized (zznl.class) {
            j = firebaseApp.getApplicationContext().getSharedPreferences("com.google.firebase.ml.internal", 0).getLong(String.format("model_first_use_time_%s_%s", new Object[]{firebaseApp.getPersistenceKey(), str}), 0);
        }
        return j;
    }

    public static synchronized void zzg(@NonNull FirebaseApp firebaseApp, @NonNull String str) {
        synchronized (zznl.class) {
            firebaseApp.getApplicationContext().getSharedPreferences("com.google.firebase.ml.internal", 0).edit().remove(String.format("downloading_model_id_%s_%s", new Object[]{firebaseApp.getPersistenceKey(), str})).remove(String.format("downloading_model_hash_%s_%s", new Object[]{firebaseApp.getPersistenceKey(), str})).remove(String.format("downloading_begin_time_%s_%s", new Object[]{firebaseApp.getPersistenceKey(), str})).remove(String.format("model_first_use_time_%s_%s", new Object[]{firebaseApp.getPersistenceKey(), str})).apply();
        }
    }

    public static synchronized void zzh(@NonNull FirebaseApp firebaseApp, @NonNull String str) {
        synchronized (zznl.class) {
            firebaseApp.getApplicationContext().getSharedPreferences("com.google.firebase.ml.internal", 0).edit().remove(String.format("current_model_hash_%s_%s", new Object[]{firebaseApp.getPersistenceKey(), str})).apply();
        }
    }
}
