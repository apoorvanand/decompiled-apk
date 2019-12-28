package com.google.android.gms.internal.firebase_ml;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.common.net.HttpHeaders;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.common.modeldownload.FirebaseCloudModelSource;
import javax.net.ssl.HttpsURLConnection;

@WorkerThread
final class zznm {
    private static final GmsLogger zzaoe = new GmsLogger("BaseModelInfoRetriever", "");

    @Nullable
    static zznp a(@NonNull FirebaseCloudModelSource firebaseCloudModelSource, @NonNull zznn zznn) {
        HttpsURLConnection a = zznr.a(String.format("https://mlkit.googleapis.com/_i/v1/1p/m?n=%s", new Object[]{firebaseCloudModelSource.zzkz()}), zznn);
        if (a == null) {
            return null;
        }
        String headerField = a.getHeaderField(HttpHeaders.CONTENT_LOCATION);
        String headerField2 = a.getHeaderField(HttpHeaders.ETAG);
        GmsLogger gmsLogger = zzaoe;
        String valueOf = String.valueOf(headerField);
        gmsLogger.d("BaseModelInfoRetriever", valueOf.length() != 0 ? "Received download URL: ".concat(valueOf) : new String("Received download URL: "));
        if (headerField == null) {
            return null;
        }
        if (headerField2 == null) {
            zznn.a(zzlv.MODEL_INFO_DOWNLOAD_NO_HASH, false);
            throw new FirebaseMLException("No hash value for the base model", 13);
        } else if (firebaseCloudModelSource.zzby(headerField2)) {
            firebaseCloudModelSource.zzbz(headerField2);
            return new zznp(firebaseCloudModelSource.zzkz(), Uri.parse(headerField), headerField2);
        } else {
            throw new FirebaseMLException("Downloaded model hash doesn't match the expected. ", 13);
        }
    }
}
