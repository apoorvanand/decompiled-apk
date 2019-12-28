package com.google.android.gms.internal.firebase_ml;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import java.io.File;
import java.io.IOException;

public final class zznu {
    private static final GmsLogger zzaoe = new GmsLogger("CloudModelUtils", "");
    private final zznw zzaqa;

    zznu(@NonNull zznw zznw) {
        Preconditions.checkNotNull(zznw);
        this.zzaqa = zznw;
    }

    static boolean a(@NonNull File file, @NonNull String str) {
        String str2 = "";
        try {
            str2 = zzb(file);
        } catch (IOException unused) {
            GmsLogger gmsLogger = zzaoe;
            String valueOf = String.valueOf(file.getAbsolutePath());
            gmsLogger.d("CloudModelUtils", valueOf.length() != 0 ? "Failed to close the tmp FileInputStream: ".concat(valueOf) : new String("Failed to close the tmp FileInputStream: "));
        }
        GmsLogger gmsLogger2 = zzaoe;
        String valueOf2 = String.valueOf(str2);
        gmsLogger2.d("CloudModelUtils", valueOf2.length() != 0 ? "Calculated hash value is: ".concat(valueOf2) : new String("Calculated hash value is: "));
        return str.equals(str2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0071, code lost:
        if (r2 != null) goto L_0x0073;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x004e */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0066 A[Catch:{ NoSuchAlgorithmException -> 0x0067, FileNotFoundException -> 0x005a, IOException -> 0x004d, all -> 0x004a, all -> 0x0077 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x007a  */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String zzb(java.io.File r7) {
        /*
            r0 = 0
            java.lang.String r1 = "SHA-256"
            java.security.MessageDigest r1 = java.security.MessageDigest.getInstance(r1)     // Catch:{ NoSuchAlgorithmException -> 0x0067, FileNotFoundException -> 0x005a, IOException -> 0x004d, all -> 0x004a }
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ NoSuchAlgorithmException -> 0x0067, FileNotFoundException -> 0x005a, IOException -> 0x004d, all -> 0x004a }
            r2.<init>(r7)     // Catch:{ NoSuchAlgorithmException -> 0x0067, FileNotFoundException -> 0x005a, IOException -> 0x004d, all -> 0x004a }
            r7 = 1024(0x400, float:1.435E-42)
            byte[] r7 = new byte[r7]     // Catch:{ NoSuchAlgorithmException -> 0x0068, FileNotFoundException -> 0x005b, IOException -> 0x004e }
        L_0x0010:
            int r3 = r2.read(r7)     // Catch:{ NoSuchAlgorithmException -> 0x0068, FileNotFoundException -> 0x005b, IOException -> 0x004e }
            r4 = -1
            r5 = 0
            if (r3 == r4) goto L_0x001c
            r1.update(r7, r5, r3)     // Catch:{ NoSuchAlgorithmException -> 0x0068, FileNotFoundException -> 0x005b, IOException -> 0x004e }
            goto L_0x0010
        L_0x001c:
            byte[] r7 = r1.digest()     // Catch:{ NoSuchAlgorithmException -> 0x0068, FileNotFoundException -> 0x005b, IOException -> 0x004e }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ NoSuchAlgorithmException -> 0x0068, FileNotFoundException -> 0x005b, IOException -> 0x004e }
            r1.<init>()     // Catch:{ NoSuchAlgorithmException -> 0x0068, FileNotFoundException -> 0x005b, IOException -> 0x004e }
        L_0x0025:
            int r3 = r7.length     // Catch:{ NoSuchAlgorithmException -> 0x0068, FileNotFoundException -> 0x005b, IOException -> 0x004e }
            if (r5 >= r3) goto L_0x0042
            byte r3 = r7[r5]     // Catch:{ NoSuchAlgorithmException -> 0x0068, FileNotFoundException -> 0x005b, IOException -> 0x004e }
            r3 = r3 & 255(0xff, float:3.57E-43)
            java.lang.String r3 = java.lang.Integer.toHexString(r3)     // Catch:{ NoSuchAlgorithmException -> 0x0068, FileNotFoundException -> 0x005b, IOException -> 0x004e }
            int r4 = r3.length()     // Catch:{ NoSuchAlgorithmException -> 0x0068, FileNotFoundException -> 0x005b, IOException -> 0x004e }
            r6 = 1
            if (r4 != r6) goto L_0x003c
            r4 = 48
            r1.append(r4)     // Catch:{ NoSuchAlgorithmException -> 0x0068, FileNotFoundException -> 0x005b, IOException -> 0x004e }
        L_0x003c:
            r1.append(r3)     // Catch:{ NoSuchAlgorithmException -> 0x0068, FileNotFoundException -> 0x005b, IOException -> 0x004e }
            int r5 = r5 + 1
            goto L_0x0025
        L_0x0042:
            java.lang.String r7 = r1.toString()     // Catch:{ NoSuchAlgorithmException -> 0x0068, FileNotFoundException -> 0x005b, IOException -> 0x004e }
            r2.close()
            return r7
        L_0x004a:
            r7 = move-exception
            r2 = r0
            goto L_0x0078
        L_0x004d:
            r2 = r0
        L_0x004e:
            com.google.android.gms.common.internal.GmsLogger r7 = zzaoe     // Catch:{ all -> 0x0077 }
            java.lang.String r1 = "CloudModelUtils"
            java.lang.String r3 = "Cannot read the temp file for SHA-256 check"
            r7.e(r1, r3)     // Catch:{ all -> 0x0077 }
            if (r2 == 0) goto L_0x0076
            goto L_0x0073
        L_0x005a:
            r2 = r0
        L_0x005b:
            com.google.android.gms.common.internal.GmsLogger r7 = zzaoe     // Catch:{ all -> 0x0077 }
            java.lang.String r1 = "CloudModelUtils"
            java.lang.String r3 = "Temp file is not found"
            r7.e(r1, r3)     // Catch:{ all -> 0x0077 }
            if (r2 == 0) goto L_0x0076
            goto L_0x0073
        L_0x0067:
            r2 = r0
        L_0x0068:
            com.google.android.gms.common.internal.GmsLogger r7 = zzaoe     // Catch:{ all -> 0x0077 }
            java.lang.String r1 = "CloudModelUtils"
            java.lang.String r3 = "Do not have SHA-256 algorithm"
            r7.e(r1, r3)     // Catch:{ all -> 0x0077 }
            if (r2 == 0) goto L_0x0076
        L_0x0073:
            r2.close()
        L_0x0076:
            return r0
        L_0x0077:
            r7 = move-exception
        L_0x0078:
            if (r2 == 0) goto L_0x007d
            r2.close()
        L_0x007d:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zznu.zzb(java.io.File):java.lang.String");
    }

    /* access modifiers changed from: package-private */
    public final boolean a(File file, zznn zznn) {
        return this.zzaqa.zzb(file, zznn);
    }
}
