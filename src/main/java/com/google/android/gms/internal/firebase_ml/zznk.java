package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.common.internal.Objects;
import java.util.concurrent.Callable;

final class zznk implements Callable<Void> {
    private final /* synthetic */ zzni zzapi;
    private final zznh zzapj;
    private final String zzapk;

    zznk(zzni zzni, zznh zznh, String str) {
        this.zzapi = zzni;
        this.zzapj = zznh;
        this.zzapk = str;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002a  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /* renamed from: zzko */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Void call() {
        /*
            r4 = this;
            java.lang.String r0 = r4.zzapk
            int r1 = r0.hashCode()
            r2 = 97847535(0x5d508ef, float:2.0033705E-35)
            if (r1 == r2) goto L_0x001b
            r2 = 710591710(0x2a5ac4de, float:1.9430592E-13)
            if (r1 == r2) goto L_0x0011
            goto L_0x0025
        L_0x0011:
            java.lang.String r1 = "OPERATION_LOAD"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0025
            r0 = 0
            goto L_0x0026
        L_0x001b:
            java.lang.String r1 = "OPERATION_RELEASE"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0025
            r0 = 1
            goto L_0x0026
        L_0x0025:
            r0 = -1
        L_0x0026:
            switch(r0) {
                case 0: goto L_0x0044;
                case 1: goto L_0x002a;
                default: goto L_0x0029;
            }
        L_0x0029:
            goto L_0x0058
        L_0x002a:
            com.google.android.gms.internal.firebase_ml.zznh r0 = r4.zzapj
            com.google.android.gms.common.internal.GmsLogger r1 = com.google.android.gms.internal.firebase_ml.zzni.zzaoj
            java.lang.String r2 = "ModelResourceManager"
            java.lang.String r3 = "Releasing modelResource"
            r1.v(r2, r3)
            r0.release()
            com.google.android.gms.internal.firebase_ml.zzni r1 = r4.zzapi
            java.util.Set r1 = r1.zzapf
            r1.remove(r0)
            goto L_0x0058
        L_0x0044:
            com.google.android.gms.internal.firebase_ml.zznh r0 = r4.zzapj
            com.google.android.gms.internal.firebase_ml.zzni r1 = r4.zzapi     // Catch:{ FirebaseMLException -> 0x004c }
            r1.a((com.google.android.gms.internal.firebase_ml.zznh) r0)     // Catch:{ FirebaseMLException -> 0x004c }
            goto L_0x0058
        L_0x004c:
            r0 = move-exception
            com.google.android.gms.common.internal.GmsLogger r1 = com.google.android.gms.internal.firebase_ml.zzni.zzaoj
            java.lang.String r2 = "ModelResourceManager"
            java.lang.String r3 = "Error preloading model resource"
            r1.e(r2, r3, r0)
        L_0x0058:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zznk.call():java.lang.Void");
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zznk)) {
            return false;
        }
        zznk zznk = (zznk) obj;
        return Objects.equal(this.zzapj, zznk.zzapj) && Objects.equal(this.zzapk, zznk.zzapk);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzapj, this.zzapk);
    }
}
