package com.google.firebase.ml.common.modeldownload;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseApp;
import java.util.HashMap;
import java.util.Map;

@RequiresApi(16)
public class FirebaseModelManager {
    @GuardedBy("FirebaseModelManager.class")
    private static Map<String, FirebaseModelManager> zzaj = new HashMap();
    private static final GmsLogger zzaoe = new GmsLogger("FirebaseModelManager", "");
    @GuardedBy("this")
    private Map<String, FirebaseCloudModelSource> zzaqt = new HashMap();
    @GuardedBy("this")
    private Map<String, FirebaseCloudModelSource> zzaqu = new HashMap();
    @GuardedBy("this")
    private Map<String, FirebaseLocalModelSource> zzaqv = new HashMap();

    private FirebaseModelManager() {
    }

    public static synchronized FirebaseModelManager getInstance() {
        FirebaseModelManager zzf;
        synchronized (FirebaseModelManager.class) {
            zzf = zzf(FirebaseApp.getInstance());
        }
        return zzf;
    }

    public static synchronized FirebaseModelManager zzf(@NonNull FirebaseApp firebaseApp) {
        synchronized (FirebaseModelManager.class) {
            Preconditions.checkNotNull(firebaseApp, "Please provide a valid FirebaseApp");
            String persistenceKey = firebaseApp.getPersistenceKey();
            if (zzaj.containsKey(persistenceKey)) {
                FirebaseModelManager firebaseModelManager = zzaj.get(persistenceKey);
                return firebaseModelManager;
            }
            FirebaseModelManager firebaseModelManager2 = new FirebaseModelManager();
            zzaj.put(persistenceKey, firebaseModelManager2);
            return firebaseModelManager2;
        }
    }

    @Nullable
    public synchronized FirebaseCloudModelSource getCloudModelSource(@NonNull String str) {
        return this.zzaqt.get(str);
    }

    @Nullable
    public synchronized FirebaseLocalModelSource getLocalModelSource(@NonNull String str) {
        return this.zzaqv.get(str);
    }

    public synchronized boolean registerCloudModelSource(@NonNull FirebaseCloudModelSource firebaseCloudModelSource) {
        Map<String, FirebaseCloudModelSource> map;
        String zzkz;
        Preconditions.checkNotNull(firebaseCloudModelSource, "FirebaseCloudModelSource can not be null");
        if (firebaseCloudModelSource.getModelName() != null) {
            if (this.zzaqt.containsKey(firebaseCloudModelSource.zzkz())) {
                GmsLogger gmsLogger = zzaoe;
                String valueOf = String.valueOf(firebaseCloudModelSource.zzkz());
                gmsLogger.w("FirebaseModelManager", valueOf.length() != 0 ? "The cloud model name is already registered: ".concat(valueOf) : new String("The cloud model name is already registered: "));
                return false;
            }
            map = this.zzaqt;
            zzkz = firebaseCloudModelSource.zzkz();
        } else if (this.zzaqu.containsKey(firebaseCloudModelSource.zzkz())) {
            GmsLogger gmsLogger2 = zzaoe;
            String valueOf2 = String.valueOf(firebaseCloudModelSource.zzkz());
            gmsLogger2.w("FirebaseModelManager", valueOf2.length() != 0 ? "The base model is already registered: ".concat(valueOf2) : new String("The base model is already registered: "));
            return false;
        } else {
            map = this.zzaqu;
            zzkz = firebaseCloudModelSource.zzkz();
        }
        map.put(zzkz, firebaseCloudModelSource);
        return true;
    }

    public synchronized boolean registerLocalModelSource(@NonNull FirebaseLocalModelSource firebaseLocalModelSource) {
        boolean z;
        Preconditions.checkNotNull(firebaseLocalModelSource, "FirebaseLocalModelSource can not be null");
        if (this.zzaqv.containsKey(firebaseLocalModelSource.getModelName())) {
            GmsLogger gmsLogger = zzaoe;
            String valueOf = String.valueOf(firebaseLocalModelSource.getModelName());
            gmsLogger.w("FirebaseModelManager", valueOf.length() != 0 ? "The local model name is already registered: ".concat(valueOf) : new String("The local model name is already registered: "));
            z = false;
        } else {
            this.zzaqv.put(firebaseLocalModelSource.getModelName(), firebaseLocalModelSource);
            z = true;
        }
        return z;
    }
}
