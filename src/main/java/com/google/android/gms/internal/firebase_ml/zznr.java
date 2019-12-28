package com.google.android.gms.internal.firebase_ml;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.util.IOUtils;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.common.modeldownload.FirebaseCloudModelSource;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.util.Locale;
import javax.net.ssl.HttpsURLConnection;

@WorkerThread
public final class zznr {
    private static final GmsLogger zzaoe = new GmsLogger("CloudModelInfoRetriever", "");

    @WorkerThread
    @Nullable
    static zznp a(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseCloudModelSource firebaseCloudModelSource, @NonNull zznn zznn) {
        return firebaseCloudModelSource.getModelName() != null ? zznx.a(firebaseApp, firebaseCloudModelSource, zznn) : zznm.a(firebaseCloudModelSource, zznn);
    }

    @Nullable
    static HttpsURLConnection a(@Nullable String str, @NonNull zznn zznn) {
        if (str == null) {
            return null;
        }
        try {
            zzny zzny = new zzny(str);
            GmsLogger gmsLogger = zzaoe;
            String valueOf = String.valueOf(str);
            gmsLogger.d("CloudModelInfoRetriever", valueOf.length() != 0 ? "Checking model URL: ".concat(valueOf) : new String("Checking model URL: "));
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) zzny.openConnection();
            httpsURLConnection.setConnectTimeout(CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE);
            httpsURLConnection.connect();
            if (httpsURLConnection.getResponseCode() == 200 || httpsURLConnection.getResponseCode() == 304) {
                return httpsURLConnection;
            }
            zznn.a(zzlv.MODEL_INFO_DOWNLOAD_UNSUCCESSFUL_HTTP_STATUS, false);
            InputStream errorStream = httpsURLConnection.getErrorStream();
            throw new FirebaseMLException(String.format(Locale.getDefault(), "Failed to connect to Firebase ML console server with HTTP status code: %d and error message: %s", new Object[]{Integer.valueOf(httpsURLConnection.getResponseCode()), errorStream == null ? "" : new String(IOUtils.readInputStreamFully(errorStream))}), 13);
        } catch (SocketTimeoutException e) {
            zznn.a(zzlv.TIME_OUT_FETCHING_MODEL_METADATA, false);
            throw new FirebaseMLException("Failed to get model URL due to time out", 13, e);
        } catch (IOException e2) {
            zznn.a(zzlv.MODEL_INFO_DOWNLOAD_CONNECTION_FAILED, false);
            throw new FirebaseMLException("Failed to get model URL", 13, e2);
        }
    }
}
