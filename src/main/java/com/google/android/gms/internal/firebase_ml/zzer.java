package com.google.android.gms.internal.firebase_ml;

public final class zzer extends zzfs {
    private final transient zzeq zzso;

    private zzer(zzft zzft, zzeq zzeq) {
        super(zzft);
        this.zzso = zzeq;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x007c A[SYNTHETIC, Splitter:B:30:0x007c] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0082 A[Catch:{ IOException -> 0x0080 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x009a A[SYNTHETIC, Splitter:B:47:0x009a] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x009e A[Catch:{ IOException -> 0x00b3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00a9 A[Catch:{ IOException -> 0x00b3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00af A[Catch:{ IOException -> 0x00b3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00cc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.firebase_ml.zzer zza(com.google.android.gms.internal.firebase_ml.zzgl r5, com.google.android.gms.internal.firebase_ml.zzfr r6) {
        /*
            com.google.android.gms.internal.firebase_ml.zzft r0 = new com.google.android.gms.internal.firebase_ml.zzft
            int r1 = r6.getStatusCode()
            java.lang.String r2 = r6.getStatusMessage()
            com.google.android.gms.internal.firebase_ml.zzfl r3 = r6.zzeo()
            r0.<init>(r1, r2, r3)
            com.google.android.gms.internal.firebase_ml.zzky.checkNotNull(r5)
            r1 = 0
            boolean r2 = r6.zzeu()     // Catch:{ IOException -> 0x00bd }
            if (r2 != 0) goto L_0x00b7
            java.lang.String r2 = "application/json; charset=UTF-8"
            java.lang.String r3 = r6.getContentType()     // Catch:{ IOException -> 0x00bd }
            boolean r2 = com.google.android.gms.internal.firebase_ml.zzfn.zzb(r2, r3)     // Catch:{ IOException -> 0x00bd }
            if (r2 == 0) goto L_0x00b7
            java.io.InputStream r2 = r6.getContent()     // Catch:{ IOException -> 0x00bd }
            if (r2 == 0) goto L_0x00b7
            java.io.InputStream r2 = r6.getContent()     // Catch:{ IOException -> 0x0092, all -> 0x008e }
            com.google.android.gms.internal.firebase_ml.zzgp r5 = r5.zza(r2)     // Catch:{ IOException -> 0x0092, all -> 0x008e }
            com.google.android.gms.internal.firebase_ml.zzgt r2 = r5.zzfs()     // Catch:{ IOException -> 0x008b, all -> 0x0088 }
            if (r2 != 0) goto L_0x003f
            com.google.android.gms.internal.firebase_ml.zzgt r2 = r5.zzfr()     // Catch:{ IOException -> 0x008b, all -> 0x0088 }
        L_0x003f:
            if (r2 == 0) goto L_0x0079
            java.lang.String r2 = "error"
            java.util.Set r2 = java.util.Collections.singleton(r2)     // Catch:{ IOException -> 0x008b, all -> 0x0088 }
            r5.zza(r2)     // Catch:{ IOException -> 0x008b, all -> 0x0088 }
            com.google.android.gms.internal.firebase_ml.zzgt r2 = r5.zzfs()     // Catch:{ IOException -> 0x008b, all -> 0x0088 }
            com.google.android.gms.internal.firebase_ml.zzgt r3 = com.google.android.gms.internal.firebase_ml.zzgt.VALUE_STRING     // Catch:{ IOException -> 0x008b, all -> 0x0088 }
            if (r2 != r3) goto L_0x0057
            java.lang.String r2 = r5.getText()     // Catch:{ IOException -> 0x008b, all -> 0x0088 }
            goto L_0x007a
        L_0x0057:
            com.google.android.gms.internal.firebase_ml.zzgt r2 = r5.zzfs()     // Catch:{ IOException -> 0x008b, all -> 0x0088 }
            com.google.android.gms.internal.firebase_ml.zzgt r3 = com.google.android.gms.internal.firebase_ml.zzgt.START_OBJECT     // Catch:{ IOException -> 0x008b, all -> 0x0088 }
            if (r2 != r3) goto L_0x0079
            java.lang.Class<com.google.android.gms.internal.firebase_ml.zzeq> r2 = com.google.android.gms.internal.firebase_ml.zzeq.class
            java.lang.Object r2 = r5.zza(r2, r1)     // Catch:{ IOException -> 0x008b, all -> 0x0088 }
            com.google.android.gms.internal.firebase_ml.zzeq r2 = (com.google.android.gms.internal.firebase_ml.zzeq) r2     // Catch:{ IOException -> 0x008b, all -> 0x0088 }
            java.lang.String r1 = r2.zzfh()     // Catch:{ IOException -> 0x0074, all -> 0x006f }
            r4 = r2
            r2 = r1
            r1 = r4
            goto L_0x007a
        L_0x006f:
            r3 = move-exception
            r4 = r3
            r3 = r2
            r2 = r4
            goto L_0x00a7
        L_0x0074:
            r3 = move-exception
            r4 = r3
            r3 = r2
            r2 = r4
            goto L_0x0095
        L_0x0079:
            r2 = r1
        L_0x007a:
            if (r5 != 0) goto L_0x0082
            r6.ignore()     // Catch:{ IOException -> 0x0080 }
            goto L_0x00c2
        L_0x0080:
            r5 = move-exception
            goto L_0x00bf
        L_0x0082:
            if (r1 != 0) goto L_0x00c2
            r5.close()     // Catch:{ IOException -> 0x0080 }
            goto L_0x00c2
        L_0x0088:
            r2 = move-exception
            r3 = r1
            goto L_0x00a7
        L_0x008b:
            r2 = move-exception
            r3 = r1
            goto L_0x0095
        L_0x008e:
            r2 = move-exception
            r5 = r1
            r3 = r5
            goto L_0x00a7
        L_0x0092:
            r2 = move-exception
            r5 = r1
            r3 = r5
        L_0x0095:
            com.google.android.gms.internal.firebase_ml.zzli.zzb(r2)     // Catch:{ all -> 0x00a6 }
            if (r5 != 0) goto L_0x009e
            r6.ignore()     // Catch:{ IOException -> 0x00b3 }
            goto L_0x00a3
        L_0x009e:
            if (r3 != 0) goto L_0x00a3
            r5.close()     // Catch:{ IOException -> 0x00b3 }
        L_0x00a3:
            r2 = r1
            r1 = r3
            goto L_0x00c2
        L_0x00a6:
            r2 = move-exception
        L_0x00a7:
            if (r5 == 0) goto L_0x00af
            if (r3 != 0) goto L_0x00b2
            r5.close()     // Catch:{ IOException -> 0x00b3 }
            goto L_0x00b2
        L_0x00af:
            r6.ignore()     // Catch:{ IOException -> 0x00b3 }
        L_0x00b2:
            throw r2     // Catch:{ IOException -> 0x00b3 }
        L_0x00b3:
            r5 = move-exception
            r2 = r1
            r1 = r3
            goto L_0x00bf
        L_0x00b7:
            java.lang.String r5 = r6.zzev()     // Catch:{ IOException -> 0x00bd }
            r2 = r5
            goto L_0x00c2
        L_0x00bd:
            r5 = move-exception
            r2 = r1
        L_0x00bf:
            com.google.android.gms.internal.firebase_ml.zzli.zzb(r5)
        L_0x00c2:
            java.lang.StringBuilder r5 = com.google.android.gms.internal.firebase_ml.zzfs.zzc(r6)
            boolean r6 = com.google.android.gms.internal.firebase_ml.zzlg.zzay(r2)
            if (r6 != 0) goto L_0x00d7
            java.lang.String r6 = com.google.android.gms.internal.firebase_ml.zzif.zzaai
            r5.append(r6)
            r5.append(r2)
            r0.zzaf(r2)
        L_0x00d7:
            java.lang.String r5 = r5.toString()
            r0.zzae(r5)
            com.google.android.gms.internal.firebase_ml.zzer r5 = new com.google.android.gms.internal.firebase_ml.zzer
            r5.<init>(r0, r1)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzer.zza(com.google.android.gms.internal.firebase_ml.zzgl, com.google.android.gms.internal.firebase_ml.zzfr):com.google.android.gms.internal.firebase_ml.zzer");
    }

    public final zzeq zzds() {
        return this.zzso;
    }
}
