package com.google.firebase.iid;

import android.text.TextUtils;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Map;

final class zzba {
    @GuardedBy("itself")
    private final zzax zzar;
    @GuardedBy("this")
    private int zzdw = 0;
    @GuardedBy("this")
    private final Map<Integer, TaskCompletionSource<Void>> zzdx = new ArrayMap();

    zzba(zzax zzax) {
        this.zzar = zzax;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0042, code lost:
        android.util.Log.d(r6, r7);
     */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean zza(com.google.firebase.iid.FirebaseInstanceId r6, java.lang.String r7) {
        /*
            java.lang.String r0 = "!"
            java.lang.String[] r7 = r7.split(r0)
            int r0 = r7.length
            r1 = 1
            r2 = 2
            if (r0 != r2) goto L_0x0075
            r0 = 0
            r2 = r7[r0]
            r7 = r7[r1]
            r3 = -1
            int r4 = r2.hashCode()     // Catch:{ IOException -> 0x0054 }
            r5 = 83
            if (r4 == r5) goto L_0x0028
            r5 = 85
            if (r4 == r5) goto L_0x001e
            goto L_0x0031
        L_0x001e:
            java.lang.String r4 = "U"
            boolean r2 = r2.equals(r4)     // Catch:{ IOException -> 0x0054 }
            if (r2 == 0) goto L_0x0031
            r3 = 1
            goto L_0x0031
        L_0x0028:
            java.lang.String r4 = "S"
            boolean r2 = r2.equals(r4)     // Catch:{ IOException -> 0x0054 }
            if (r2 == 0) goto L_0x0031
            r3 = 0
        L_0x0031:
            switch(r3) {
                case 0: goto L_0x0046;
                case 1: goto L_0x0035;
                default: goto L_0x0034;
            }     // Catch:{ IOException -> 0x0054 }
        L_0x0034:
            goto L_0x0075
        L_0x0035:
            r6.zzc(r7)     // Catch:{ IOException -> 0x0054 }
            boolean r6 = com.google.firebase.iid.FirebaseInstanceId.zzm()     // Catch:{ IOException -> 0x0054 }
            if (r6 == 0) goto L_0x0075
            java.lang.String r6 = "FirebaseInstanceId"
            java.lang.String r7 = "unsubscribe operation succeeded"
        L_0x0042:
            android.util.Log.d(r6, r7)     // Catch:{ IOException -> 0x0054 }
            goto L_0x0075
        L_0x0046:
            r6.zzb((java.lang.String) r7)     // Catch:{ IOException -> 0x0054 }
            boolean r6 = com.google.firebase.iid.FirebaseInstanceId.zzm()     // Catch:{ IOException -> 0x0054 }
            if (r6 == 0) goto L_0x0075
            java.lang.String r6 = "FirebaseInstanceId"
            java.lang.String r7 = "subscribe operation succeeded"
            goto L_0x0042
        L_0x0054:
            r6 = move-exception
            java.lang.String r7 = "FirebaseInstanceId"
            java.lang.String r1 = "Topic sync failed: "
            java.lang.String r6 = r6.getMessage()
            java.lang.String r6 = java.lang.String.valueOf(r6)
            int r2 = r6.length()
            if (r2 == 0) goto L_0x006c
            java.lang.String r6 = r1.concat(r6)
            goto L_0x0071
        L_0x006c:
            java.lang.String r6 = new java.lang.String
            r6.<init>(r1)
        L_0x0071:
            android.util.Log.e(r7, r6)
            return r0
        L_0x0075:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzba.zza(com.google.firebase.iid.FirebaseInstanceId, java.lang.String):boolean");
    }

    @GuardedBy("this")
    @Nullable
    private final String zzar() {
        String zzak;
        synchronized (this.zzar) {
            zzak = this.zzar.zzak();
        }
        if (TextUtils.isEmpty(zzak)) {
            return null;
        }
        String[] split = zzak.split(",");
        if (split.length <= 1 || TextUtils.isEmpty(split[1])) {
            return null;
        }
        return split[1];
    }

    private final synchronized boolean zzn(String str) {
        synchronized (this.zzar) {
            String zzak = this.zzar.zzak();
            String valueOf = String.valueOf(",");
            String valueOf2 = String.valueOf(str);
            if (!zzak.startsWith(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf))) {
                return false;
            }
            String valueOf3 = String.valueOf(",");
            String valueOf4 = String.valueOf(str);
            this.zzar.zzh(zzak.substring((valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3)).length()));
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized Task<Void> zza(String str) {
        String zzak;
        TaskCompletionSource taskCompletionSource;
        synchronized (this.zzar) {
            zzak = this.zzar.zzak();
            zzax zzax = this.zzar;
            StringBuilder sb = new StringBuilder(String.valueOf(zzak).length() + 1 + String.valueOf(str).length());
            sb.append(zzak);
            sb.append(",");
            sb.append(str);
            zzax.zzh(sb.toString());
        }
        taskCompletionSource = new TaskCompletionSource();
        this.zzdx.put(Integer.valueOf(this.zzdw + (TextUtils.isEmpty(zzak) ? 0 : zzak.split(",").length - 1)), taskCompletionSource);
        return taskCompletionSource.getTask();
    }

    /* access modifiers changed from: package-private */
    public final synchronized boolean zzaq() {
        return zzar() != null;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (zza(r5, r0) != false) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r2 = r4.zzdx.remove(java.lang.Integer.valueOf(r4.zzdw));
        zzn(r0);
        r4.zzdw++;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0037, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0038, code lost:
        if (r2 == null) goto L_0x0000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003a, code lost:
        r2.setResult(null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0016, code lost:
        return true;
     */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzc(com.google.firebase.iid.FirebaseInstanceId r5) {
        /*
            r4 = this;
        L_0x0000:
            monitor-enter(r4)
            java.lang.String r0 = r4.zzar()     // Catch:{ all -> 0x0042 }
            r1 = 1
            if (r0 != 0) goto L_0x0017
            boolean r5 = com.google.firebase.iid.FirebaseInstanceId.zzm()     // Catch:{ all -> 0x0042 }
            if (r5 == 0) goto L_0x0015
            java.lang.String r5 = "FirebaseInstanceId"
            java.lang.String r0 = "topic sync succeeded"
            android.util.Log.d(r5, r0)     // Catch:{ all -> 0x0042 }
        L_0x0015:
            monitor-exit(r4)     // Catch:{ all -> 0x0042 }
            return r1
        L_0x0017:
            monitor-exit(r4)     // Catch:{ all -> 0x0042 }
            boolean r2 = zza(r5, r0)
            if (r2 != 0) goto L_0x0020
            r5 = 0
            return r5
        L_0x0020:
            monitor-enter(r4)
            java.util.Map<java.lang.Integer, com.google.android.gms.tasks.TaskCompletionSource<java.lang.Void>> r2 = r4.zzdx     // Catch:{ all -> 0x003f }
            int r3 = r4.zzdw     // Catch:{ all -> 0x003f }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x003f }
            java.lang.Object r2 = r2.remove(r3)     // Catch:{ all -> 0x003f }
            com.google.android.gms.tasks.TaskCompletionSource r2 = (com.google.android.gms.tasks.TaskCompletionSource) r2     // Catch:{ all -> 0x003f }
            r4.zzn(r0)     // Catch:{ all -> 0x003f }
            int r0 = r4.zzdw     // Catch:{ all -> 0x003f }
            int r0 = r0 + r1
            r4.zzdw = r0     // Catch:{ all -> 0x003f }
            monitor-exit(r4)     // Catch:{ all -> 0x003f }
            if (r2 == 0) goto L_0x0000
            r0 = 0
            r2.setResult(r0)
            goto L_0x0000
        L_0x003f:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x003f }
            throw r5
        L_0x0042:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0042 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzba.zzc(com.google.firebase.iid.FirebaseInstanceId):boolean");
    }
}
