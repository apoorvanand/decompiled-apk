package com.google.android.gms.internal.firebase_ml;

import androidx.annotation.NonNull;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class zzmw {
    private static final GmsLogger zzaoe = new GmsLogger("LibraryVersion", "");
    private static zzmw zzaof = new zzmw();
    private ConcurrentHashMap<String, String> zzaog = new ConcurrentHashMap<>();

    private zzmw() {
    }

    public static zzmw zzkg() {
        return zzaof;
    }

    public final String getVersion(@NonNull String str) {
        Preconditions.checkNotEmpty(str, "Please provide a valid libraryName");
        if (this.zzaog.containsKey(str)) {
            return this.zzaog.get(str);
        }
        Properties properties = new Properties();
        String str2 = null;
        try {
            InputStream resourceAsStream = zzmw.class.getResourceAsStream(String.format("/%s.properties", new Object[]{str}));
            if (resourceAsStream != null) {
                properties.load(resourceAsStream);
                str2 = properties.getProperty(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, (String) null);
                GmsLogger gmsLogger = zzaoe;
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 12 + String.valueOf(str2).length());
                sb.append(str);
                sb.append(" version is ");
                sb.append(str2);
                gmsLogger.v("LibraryVersion", sb.toString());
            } else {
                GmsLogger gmsLogger2 = zzaoe;
                String valueOf = String.valueOf(str);
                gmsLogger2.e("LibraryVersion", valueOf.length() != 0 ? "Failed to get app version for libraryName: ".concat(valueOf) : new String("Failed to get app version for libraryName: "));
            }
        } catch (IOException e) {
            GmsLogger gmsLogger3 = zzaoe;
            String valueOf2 = String.valueOf(str);
            gmsLogger3.e("LibraryVersion", valueOf2.length() != 0 ? "Failed to get app version for libraryName: ".concat(valueOf2) : new String("Failed to get app version for libraryName: "), e);
        }
        if (str2 == null) {
            str2 = "UNKNOWN";
            zzaoe.d("LibraryVersion", ".properties file is dropped during release process. Failure to read app version isexpected druing Google internal testing where locally-built libraries are used");
        }
        this.zzaog.put(str, str2);
        return str2;
    }
}
