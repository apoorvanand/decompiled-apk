package com.google.android.gms.internal.firebase_ml;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.common.net.HttpHeaders;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.common.modeldownload.FirebaseCloudModelSource;
import java.io.IOException;
import javax.net.ssl.HttpsURLConnection;

@WorkerThread
final class zznx {
    private static final GmsLogger zzaoe = new GmsLogger("ModelInfoRetriever", "");

    @Nullable
    static zznp a(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseCloudModelSource firebaseCloudModelSource, @NonNull zznn zznn) {
        HttpsURLConnection a = zznr.a(zza(firebaseApp, firebaseCloudModelSource.getModelName(), zznn), zznn);
        if (a == null) {
            return null;
        }
        String headerField = a.getHeaderField(HttpHeaders.CONTENT_LOCATION);
        String headerField2 = a.getHeaderField(HttpHeaders.ETAG);
        GmsLogger gmsLogger = zzaoe;
        String valueOf = String.valueOf(headerField);
        gmsLogger.d("ModelInfoRetriever", valueOf.length() != 0 ? "Received download URL: ".concat(valueOf) : new String("Received download URL: "));
        if (headerField == null) {
            return null;
        }
        if (headerField2 != null) {
            firebaseCloudModelSource.zzbz(headerField2);
            return new zznp(firebaseCloudModelSource.zzkz(), Uri.parse(headerField), headerField2);
        }
        zznn.a(zzlv.MODEL_INFO_DOWNLOAD_NO_HASH, false);
        throw new FirebaseMLException("No hash value for the custom model", 13);
    }

    @VisibleForTesting
    @Nullable
    private static String zza(FirebaseApp firebaseApp, String str, @NonNull zznn zznn) {
        GmsLogger gmsLogger;
        String str2;
        String str3;
        String gcmSenderId = firebaseApp.getOptions().getGcmSenderId();
        if (gcmSenderId != null) {
            FirebaseInstanceId instance = FirebaseInstanceId.getInstance(firebaseApp);
            if (instance == null) {
                gmsLogger = zzaoe;
                str2 = "ModelInfoRetriever";
                str3 = "Cannot get a valid instance of FirebaseInstanceId. Cannot retrieve model info.";
            } else {
                String id = instance.getId();
                if (id == null) {
                    gmsLogger = zzaoe;
                    str2 = "ModelInfoRetriever";
                    str3 = "Firebase instance id is null. Cannot retrieve model info.";
                } else {
                    try {
                        String token = instance.getToken(gcmSenderId, "*");
                        if (token == null) {
                            gmsLogger = zzaoe;
                            str2 = "ModelInfoRetriever";
                            str3 = "Firebase instance token is null. Cannot retrieve model info.";
                        } else {
                            return String.format("https://mlkit.googleapis.com/v1beta1/projects/%s/models/%s/versions/active?key=%s&app_instance_id=%s&app_instance_token=%s", new Object[]{firebaseApp.getOptions().getProjectId(), str, firebaseApp.getOptions().getApiKey(), id, token});
                        }
                    } catch (IOException e) {
                        zznn.a(zzlv.MODEL_INFO_DOWNLOAD_CONNECTION_FAILED, false);
                        throw new FirebaseMLException("Failed to get model URL", 13, e);
                    }
                }
            }
            gmsLogger.w(str2, str3);
            return null;
        }
        throw new FirebaseMLException("GCM sender id cannot be null in FirebaseOptions. Please configure FirebaseApp properly.", 9);
    }
}
