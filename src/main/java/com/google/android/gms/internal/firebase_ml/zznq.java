package com.google.android.gms.internal.firebase_ml;

import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.common.FirebaseMLException;
import java.io.File;

public final class zznq {
    private static final GmsLogger zzaoe = new GmsLogger("CloudModelFileManager", "");
    private final FirebaseApp zzaot;
    private final String zzapq;
    private final Runnable zzapt;
    private final zznu zzapu;

    zznq(@NonNull FirebaseApp firebaseApp, @NonNull String str, @NonNull Runnable runnable, @NonNull zznw zznw) {
        this.zzaot = firebaseApp;
        this.zzapq = str;
        this.zzapt = runnable;
        this.zzapu = new zznu(zznw);
    }

    private static int zza(@NonNull File file) {
        File[] listFiles = file.listFiles();
        if (listFiles.length == 0) {
            return -1;
        }
        int i = 0;
        for (File name : listFiles) {
            try {
                i = Math.max(i, Integer.parseInt(name.getName()));
            } catch (NumberFormatException unused) {
                zzaoe.d("CloudModelFileManager", "Contains non-integer file name.");
            }
        }
        return i;
    }

    private static File zzi(@NonNull FirebaseApp firebaseApp, @NonNull String str) {
        File file = new File(new File(Build.VERSION.SDK_INT >= 21 ? new File(firebaseApp.getApplicationContext().getNoBackupFilesDir(), "com.google.firebase.ml.custom.models") : firebaseApp.getApplicationContext().getDir("com.google.firebase.ml.custom.models", 0), firebaseApp.getPersistenceKey()), str);
        if (!file.exists()) {
            GmsLogger gmsLogger = zzaoe;
            String valueOf = String.valueOf(file.getAbsolutePath());
            gmsLogger.d("CloudModelFileManager", valueOf.length() != 0 ? "model folder does not exist, creating one: ".concat(valueOf) : new String("model folder does not exist, creating one: "));
            file.mkdirs();
        } else if (!file.isDirectory()) {
            String valueOf2 = String.valueOf(file);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf2).length() + 71);
            sb.append("Can not create model folder, since an existing file has the same name: ");
            sb.append(valueOf2);
            throw new FirebaseMLException(sb.toString(), 6);
        }
        return file;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    @Nullable
    public final synchronized String a() {
        File zzi = zzi(this.zzaot, this.zzapq);
        int zza = zza(zzi);
        if (zza < 0) {
            return null;
        }
        String absolutePath = zzi.getAbsolutePath();
        StringBuilder sb = new StringBuilder(String.valueOf(absolutePath).length() + 12);
        sb.append(absolutePath);
        sb.append("/");
        sb.append(zza);
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0117, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0165, code lost:
        return null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0209 A[SYNTHETIC, Splitter:B:102:0x0209] */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x0213 A[Catch:{ IOException -> 0x020f }] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01d0 A[SYNTHETIC, Splitter:B:88:0x01d0] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x01d8 A[Catch:{ IOException -> 0x01d4 }] */
    @androidx.annotation.WorkerThread
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized java.lang.String a(@androidx.annotation.NonNull android.os.ParcelFileDescriptor r12, @androidx.annotation.NonNull java.lang.String r13, com.google.android.gms.internal.firebase_ml.zznn r14) {
        /*
            r11 = this;
            monitor-enter(r11)
            r0 = 0
            com.google.firebase.FirebaseApp r1 = r11.zzaot     // Catch:{ IOException -> 0x01a5, all -> 0x01a1 }
            java.lang.String r2 = r11.zzapq     // Catch:{ IOException -> 0x01a5, all -> 0x01a1 }
            java.io.File r1 = zzi(r1, r2)     // Catch:{ IOException -> 0x01a5, all -> 0x01a1 }
            int r2 = zza(r1)     // Catch:{ IOException -> 0x01a5, all -> 0x01a1 }
            java.io.File r3 = new java.io.File     // Catch:{ IOException -> 0x01a5, all -> 0x01a1 }
            int r2 = r2 + 1
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ IOException -> 0x01a5, all -> 0x01a1 }
            r3.<init>(r1, r2)     // Catch:{ IOException -> 0x01a5, all -> 0x01a1 }
            java.io.File r1 = new java.io.File     // Catch:{ IOException -> 0x01a5, all -> 0x01a1 }
            java.lang.String r2 = r3.getAbsolutePath()     // Catch:{ IOException -> 0x01a5, all -> 0x01a1 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ IOException -> 0x01a5, all -> 0x01a1 }
            java.lang.String r4 = ".tmp"
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ IOException -> 0x01a5, all -> 0x01a1 }
            int r5 = r4.length()     // Catch:{ IOException -> 0x01a5, all -> 0x01a1 }
            if (r5 == 0) goto L_0x0034
            java.lang.String r2 = r2.concat(r4)     // Catch:{ IOException -> 0x01a5, all -> 0x01a1 }
            goto L_0x003a
        L_0x0034:
            java.lang.String r4 = new java.lang.String     // Catch:{ IOException -> 0x01a5, all -> 0x01a1 }
            r4.<init>(r2)     // Catch:{ IOException -> 0x01a5, all -> 0x01a1 }
            r2 = r4
        L_0x003a:
            r1.<init>(r2)     // Catch:{ IOException -> 0x01a5, all -> 0x01a1 }
            android.os.ParcelFileDescriptor$AutoCloseInputStream r2 = new android.os.ParcelFileDescriptor$AutoCloseInputStream     // Catch:{ IOException -> 0x01a5, all -> 0x01a1 }
            r2.<init>(r12)     // Catch:{ IOException -> 0x01a5, all -> 0x01a1 }
            java.io.FileOutputStream r12 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x019e, all -> 0x019a }
            r12.<init>(r1)     // Catch:{ IOException -> 0x019e, all -> 0x019a }
            r4 = 4096(0x1000, float:5.74E-42)
            byte[] r4 = new byte[r4]     // Catch:{ IOException -> 0x0195, all -> 0x018f }
        L_0x004b:
            int r5 = r2.read(r4)     // Catch:{ IOException -> 0x0195, all -> 0x018f }
            r6 = -1
            r7 = 0
            if (r5 == r6) goto L_0x0057
            r12.write(r4, r7, r5)     // Catch:{ IOException -> 0x0195, all -> 0x018f }
            goto L_0x004b
        L_0x0057:
            java.io.FileDescriptor r4 = r12.getFD()     // Catch:{ IOException -> 0x0195, all -> 0x018f }
            r4.sync()     // Catch:{ IOException -> 0x0195, all -> 0x018f }
            r2.close()     // Catch:{ IOException -> 0x0166 }
            r12.close()     // Catch:{ IOException -> 0x0166 }
            boolean r12 = com.google.android.gms.internal.firebase_ml.zznu.a((java.io.File) r1, (java.lang.String) r13)     // Catch:{ all -> 0x020d }
            if (r12 == 0) goto L_0x00bf
            com.google.android.gms.internal.firebase_ml.zznu r2 = r11.zzapu     // Catch:{ all -> 0x020d }
            boolean r14 = r2.a((java.io.File) r1, (com.google.android.gms.internal.firebase_ml.zznn) r14)     // Catch:{ all -> 0x020d }
            if (r14 != 0) goto L_0x00c0
            com.google.firebase.FirebaseApp r2 = r11.zzaot     // Catch:{ all -> 0x020d }
            android.content.Context r2 = r2.getApplicationContext()     // Catch:{ all -> 0x020d }
            java.lang.String r2 = com.google.android.gms.internal.firebase_ml.zzmv.zza(r2)     // Catch:{ all -> 0x020d }
            com.google.firebase.FirebaseApp r4 = r11.zzaot     // Catch:{ all -> 0x020d }
            java.lang.String r5 = r11.zzapq     // Catch:{ all -> 0x020d }
            com.google.android.gms.internal.firebase_ml.zznl.zza((com.google.firebase.FirebaseApp) r4, (java.lang.String) r5, (java.lang.String) r13, (java.lang.String) r2)     // Catch:{ all -> 0x020d }
            com.google.android.gms.common.internal.GmsLogger r4 = zzaoe     // Catch:{ all -> 0x020d }
            java.lang.String r5 = "CloudModelFileManager"
            java.lang.String r6 = "Model is not compatible. Model hash: "
            java.lang.String r8 = java.lang.String.valueOf(r13)     // Catch:{ all -> 0x020d }
            int r9 = r8.length()     // Catch:{ all -> 0x020d }
            if (r9 == 0) goto L_0x0098
            java.lang.String r6 = r6.concat(r8)     // Catch:{ all -> 0x020d }
            goto L_0x009e
        L_0x0098:
            java.lang.String r8 = new java.lang.String     // Catch:{ all -> 0x020d }
            r8.<init>(r6)     // Catch:{ all -> 0x020d }
            r6 = r8
        L_0x009e:
            r4.d(r5, r6)     // Catch:{ all -> 0x020d }
            com.google.android.gms.common.internal.GmsLogger r4 = zzaoe     // Catch:{ all -> 0x020d }
            java.lang.String r5 = "CloudModelFileManager"
            java.lang.String r6 = "The current app version is: "
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x020d }
            int r8 = r2.length()     // Catch:{ all -> 0x020d }
            if (r8 == 0) goto L_0x00b6
            java.lang.String r2 = r6.concat(r2)     // Catch:{ all -> 0x020d }
            goto L_0x00bb
        L_0x00b6:
            java.lang.String r2 = new java.lang.String     // Catch:{ all -> 0x020d }
            r2.<init>(r6)     // Catch:{ all -> 0x020d }
        L_0x00bb:
            r4.d(r5, r2)     // Catch:{ all -> 0x020d }
            goto L_0x00c0
        L_0x00bf:
            r14 = 0
        L_0x00c0:
            if (r12 == 0) goto L_0x0118
            if (r14 != 0) goto L_0x00c5
            goto L_0x0118
        L_0x00c5:
            boolean r12 = r1.renameTo(r3)     // Catch:{ all -> 0x020d }
            if (r12 == 0) goto L_0x00e5
            com.google.android.gms.common.internal.GmsLogger r12 = zzaoe     // Catch:{ all -> 0x020d }
            java.lang.String r13 = "CloudModelFileManager"
            java.lang.String r14 = "Rename to serving model successfully"
            r12.d(r13, r14)     // Catch:{ all -> 0x020d }
            r3.setExecutable(r7)     // Catch:{ all -> 0x020d }
            r3.setWritable(r7)     // Catch:{ all -> 0x020d }
            java.lang.Runnable r12 = r11.zzapt     // Catch:{ all -> 0x020d }
            r12.run()     // Catch:{ all -> 0x020d }
            java.lang.String r12 = r3.getAbsolutePath()     // Catch:{ all -> 0x020d }
            monitor-exit(r11)
            return r12
        L_0x00e5:
            com.google.android.gms.common.internal.GmsLogger r12 = zzaoe     // Catch:{ all -> 0x020d }
            java.lang.String r13 = "CloudModelFileManager"
            java.lang.String r14 = "Rename to serving model failed, remove the temp file."
            r12.d(r13, r14)     // Catch:{ all -> 0x020d }
            boolean r12 = r1.delete()     // Catch:{ all -> 0x020d }
            if (r12 != 0) goto L_0x0116
            com.google.android.gms.common.internal.GmsLogger r12 = zzaoe     // Catch:{ all -> 0x020d }
            java.lang.String r13 = "CloudModelFileManager"
            java.lang.String r14 = "Failed to delete the temp file: "
            java.lang.String r1 = r1.getAbsolutePath()     // Catch:{ all -> 0x020d }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x020d }
            int r2 = r1.length()     // Catch:{ all -> 0x020d }
            if (r2 == 0) goto L_0x010d
            java.lang.String r14 = r14.concat(r1)     // Catch:{ all -> 0x020d }
            goto L_0x0113
        L_0x010d:
            java.lang.String r1 = new java.lang.String     // Catch:{ all -> 0x020d }
            r1.<init>(r14)     // Catch:{ all -> 0x020d }
            r14 = r1
        L_0x0113:
            r12.d(r13, r14)     // Catch:{ all -> 0x020d }
        L_0x0116:
            monitor-exit(r11)
            return r0
        L_0x0118:
            if (r12 != 0) goto L_0x0137
            com.google.android.gms.common.internal.GmsLogger r12 = zzaoe     // Catch:{ all -> 0x020d }
            java.lang.String r14 = "CloudModelFileManager"
            java.lang.String r2 = "Hash does not match with expected: "
            java.lang.String r13 = java.lang.String.valueOf(r13)     // Catch:{ all -> 0x020d }
            int r3 = r13.length()     // Catch:{ all -> 0x020d }
            if (r3 == 0) goto L_0x012f
            java.lang.String r13 = r2.concat(r13)     // Catch:{ all -> 0x020d }
            goto L_0x0134
        L_0x012f:
            java.lang.String r13 = new java.lang.String     // Catch:{ all -> 0x020d }
            r13.<init>(r2)     // Catch:{ all -> 0x020d }
        L_0x0134:
            r12.d(r14, r13)     // Catch:{ all -> 0x020d }
        L_0x0137:
            java.lang.Runnable r12 = r11.zzapt     // Catch:{ all -> 0x020d }
            r12.run()     // Catch:{ all -> 0x020d }
            boolean r12 = r1.delete()     // Catch:{ all -> 0x020d }
            if (r12 != 0) goto L_0x0164
            com.google.android.gms.common.internal.GmsLogger r12 = zzaoe     // Catch:{ all -> 0x020d }
            java.lang.String r13 = "CloudModelFileManager"
            java.lang.String r14 = "Failed to delete the temp file: "
            java.lang.String r1 = r1.getAbsolutePath()     // Catch:{ all -> 0x020d }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x020d }
            int r2 = r1.length()     // Catch:{ all -> 0x020d }
            if (r2 == 0) goto L_0x015b
            java.lang.String r14 = r14.concat(r1)     // Catch:{ all -> 0x020d }
            goto L_0x0161
        L_0x015b:
            java.lang.String r1 = new java.lang.String     // Catch:{ all -> 0x020d }
            r1.<init>(r14)     // Catch:{ all -> 0x020d }
            r14 = r1
        L_0x0161:
            r12.d(r13, r14)     // Catch:{ all -> 0x020d }
        L_0x0164:
            monitor-exit(r11)
            return r0
        L_0x0166:
            r12 = move-exception
            com.google.android.gms.common.internal.GmsLogger r13 = zzaoe     // Catch:{ all -> 0x020d }
            java.lang.String r14 = "CloudModelFileManager"
            java.lang.String r12 = java.lang.String.valueOf(r12)     // Catch:{ all -> 0x020d }
            java.lang.String r1 = java.lang.String.valueOf(r12)     // Catch:{ all -> 0x020d }
            int r1 = r1.length()     // Catch:{ all -> 0x020d }
            int r1 = r1 + 13
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x020d }
            r2.<init>(r1)     // Catch:{ all -> 0x020d }
            java.lang.String r1 = "IOException: "
            r2.append(r1)     // Catch:{ all -> 0x020d }
            r2.append(r12)     // Catch:{ all -> 0x020d }
            java.lang.String r12 = r2.toString()     // Catch:{ all -> 0x020d }
            r13.d(r14, r12)     // Catch:{ all -> 0x020d }
            monitor-exit(r11)
            return r0
        L_0x018f:
            r13 = move-exception
            r10 = r13
            r13 = r12
            r12 = r10
            goto L_0x0207
        L_0x0195:
            r13 = move-exception
            r10 = r13
            r13 = r12
            r12 = r10
            goto L_0x01a8
        L_0x019a:
            r12 = move-exception
            r13 = r0
            goto L_0x0207
        L_0x019e:
            r12 = move-exception
            r13 = r0
            goto L_0x01a8
        L_0x01a1:
            r12 = move-exception
            r13 = r0
            r2 = r13
            goto L_0x0207
        L_0x01a5:
            r12 = move-exception
            r13 = r0
            r2 = r13
        L_0x01a8:
            com.google.android.gms.common.internal.GmsLogger r14 = zzaoe     // Catch:{ all -> 0x0206 }
            java.lang.String r1 = "CloudModelFileManager"
            java.lang.String r12 = java.lang.String.valueOf(r12)     // Catch:{ all -> 0x0206 }
            java.lang.String r3 = java.lang.String.valueOf(r12)     // Catch:{ all -> 0x0206 }
            int r3 = r3.length()     // Catch:{ all -> 0x0206 }
            int r3 = r3 + 56
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0206 }
            r4.<init>(r3)     // Catch:{ all -> 0x0206 }
            java.lang.String r3 = "Failed to copy downloaded model file to private folder: "
            r4.append(r3)     // Catch:{ all -> 0x0206 }
            r4.append(r12)     // Catch:{ all -> 0x0206 }
            java.lang.String r12 = r4.toString()     // Catch:{ all -> 0x0206 }
            r14.e(r1, r12)     // Catch:{ all -> 0x0206 }
            if (r2 == 0) goto L_0x01d6
            r2.close()     // Catch:{ IOException -> 0x01d4 }
            goto L_0x01d6
        L_0x01d4:
            r12 = move-exception
            goto L_0x01dc
        L_0x01d6:
            if (r13 == 0) goto L_0x0204
            r13.close()     // Catch:{ IOException -> 0x01d4 }
            goto L_0x0204
        L_0x01dc:
            com.google.android.gms.common.internal.GmsLogger r13 = zzaoe     // Catch:{ all -> 0x020d }
            java.lang.String r14 = "CloudModelFileManager"
            java.lang.String r12 = java.lang.String.valueOf(r12)     // Catch:{ all -> 0x020d }
            java.lang.String r1 = java.lang.String.valueOf(r12)     // Catch:{ all -> 0x020d }
            int r1 = r1.length()     // Catch:{ all -> 0x020d }
            int r1 = r1 + 13
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x020d }
            r2.<init>(r1)     // Catch:{ all -> 0x020d }
            java.lang.String r1 = "IOException: "
            r2.append(r1)     // Catch:{ all -> 0x020d }
            r2.append(r12)     // Catch:{ all -> 0x020d }
            java.lang.String r12 = r2.toString()     // Catch:{ all -> 0x020d }
            r13.d(r14, r12)     // Catch:{ all -> 0x020d }
            monitor-exit(r11)
            return r0
        L_0x0204:
            monitor-exit(r11)
            return r0
        L_0x0206:
            r12 = move-exception
        L_0x0207:
            if (r2 == 0) goto L_0x0211
            r2.close()     // Catch:{ IOException -> 0x020f }
            goto L_0x0211
        L_0x020d:
            r12 = move-exception
            goto L_0x0240
        L_0x020f:
            r12 = move-exception
            goto L_0x0217
        L_0x0211:
            if (r13 == 0) goto L_0x023f
            r13.close()     // Catch:{ IOException -> 0x020f }
            goto L_0x023f
        L_0x0217:
            com.google.android.gms.common.internal.GmsLogger r13 = zzaoe     // Catch:{ all -> 0x020d }
            java.lang.String r14 = "CloudModelFileManager"
            java.lang.String r12 = java.lang.String.valueOf(r12)     // Catch:{ all -> 0x020d }
            java.lang.String r1 = java.lang.String.valueOf(r12)     // Catch:{ all -> 0x020d }
            int r1 = r1.length()     // Catch:{ all -> 0x020d }
            int r1 = r1 + 13
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x020d }
            r2.<init>(r1)     // Catch:{ all -> 0x020d }
            java.lang.String r1 = "IOException: "
            r2.append(r1)     // Catch:{ all -> 0x020d }
            r2.append(r12)     // Catch:{ all -> 0x020d }
            java.lang.String r12 = r2.toString()     // Catch:{ all -> 0x020d }
            r13.d(r14, r12)     // Catch:{ all -> 0x020d }
            monitor-exit(r11)
            return r0
        L_0x023f:
            throw r12     // Catch:{ all -> 0x020d }
        L_0x0240:
            monitor-exit(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zznq.a(android.os.ParcelFileDescriptor, java.lang.String, com.google.android.gms.internal.firebase_ml.zznn):java.lang.String");
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final synchronized boolean a(@NonNull String str) {
        File zzi = zzi(this.zzaot, this.zzapq);
        if (!zzi.exists()) {
            return false;
        }
        boolean z = true;
        for (File file : zzi.listFiles()) {
            if (!file.getAbsolutePath().equals(str) && !file.delete()) {
                z = false;
            }
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final synchronized String b(@NonNull String str) {
        File file = new File(str);
        String concat = String.valueOf(zzi(this.zzaot, this.zzapq).getAbsolutePath()).concat("/0");
        File file2 = new File(concat);
        return file2.exists() ? str : file.renameTo(file2) ? concat : str;
    }
}
