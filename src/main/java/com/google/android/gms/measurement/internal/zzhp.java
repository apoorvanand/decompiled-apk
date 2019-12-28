package com.google.android.gms.measurement.internal;

final class zzhp extends zzjh {
    public zzhp(zzjg zzjg) {
        super(zzjg);
    }

    private static String zzo(String str, String str2) {
        throw new SecurityException("This implementation should not be used.");
    }

    /* access modifiers changed from: protected */
    public final boolean a() {
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:106:0x02ee A[Catch:{ SecurityException -> 0x0194, all -> 0x052b }, LOOP:2: B:104:0x02e8->B:106:0x02ee, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x035f A[Catch:{ SecurityException -> 0x0194, all -> 0x052b }] */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x0385 A[Catch:{ SecurityException -> 0x0194, all -> 0x052b }] */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x03b7 A[Catch:{ SecurityException -> 0x0194, all -> 0x052b }] */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x0407 A[Catch:{ SecurityException -> 0x0194, all -> 0x052b }, LOOP:3: B:115:0x0401->B:117:0x0407, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x0464 A[Catch:{ SecurityException -> 0x0194, all -> 0x052b }] */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x047b A[Catch:{ SecurityException -> 0x0194, all -> 0x052b }] */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x0486 A[Catch:{ SecurityException -> 0x0194, all -> 0x052b }] */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x048a A[Catch:{ SecurityException -> 0x0194, all -> 0x052b }] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x026c A[Catch:{ SecurityException -> 0x0194, all -> 0x052b }] */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] zzb(@androidx.annotation.NonNull com.google.android.gms.measurement.internal.zzai r32, @androidx.annotation.Size(min = 1) java.lang.String r33) {
        /*
            r31 = this;
            r1 = r31
            r0 = r32
            r15 = r33
            r31.zzo()
            com.google.android.gms.measurement.internal.zzfj r2 = r1.b
            r2.e()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r32)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r33)
            com.google.android.gms.measurement.internal.zzs r2 = r31.zzad()
            com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzak.zzio
            boolean r2 = r2.zze(r15, r3)
            r3 = 0
            if (r2 != 0) goto L_0x0031
            com.google.android.gms.measurement.internal.zzef r0 = r31.zzab()
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzgr()
            java.lang.String r2 = "Generating ScionPayload disabled. packageName"
            r0.zza(r2, r15)
            byte[] r0 = new byte[r3]
            return r0
        L_0x0031:
            java.lang.String r2 = "_iap"
            java.lang.String r4 = r0.name
            boolean r2 = r2.equals(r4)
            r14 = 0
            if (r2 != 0) goto L_0x0056
            java.lang.String r2 = "_iapx"
            java.lang.String r4 = r0.name
            boolean r2 = r2.equals(r4)
            if (r2 != 0) goto L_0x0056
            com.google.android.gms.measurement.internal.zzef r2 = r31.zzab()
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzgr()
            java.lang.String r3 = "Generating a payload for this event is not available. package_name, event_name"
            java.lang.String r0 = r0.name
            r2.zza(r3, r15, r0)
            return r14
        L_0x0056:
            com.google.android.gms.internal.measurement.zzbs$zzf$zza r13 = com.google.android.gms.internal.measurement.zzbs.zzf.zznj()
            com.google.android.gms.measurement.internal.zzx r2 = r31.zzgy()
            r2.beginTransaction()
            com.google.android.gms.measurement.internal.zzx r2 = r31.zzgy()     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzf r11 = r2.zzab(r15)     // Catch:{ all -> 0x052b }
            if (r11 != 0) goto L_0x0082
            com.google.android.gms.measurement.internal.zzef r0 = r31.zzab()     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzgr()     // Catch:{ all -> 0x052b }
            java.lang.String r2 = "Log and bundle not available. package_name"
            r0.zza(r2, r15)     // Catch:{ all -> 0x052b }
            byte[] r0 = new byte[r3]     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzx r2 = r31.zzgy()
            r2.endTransaction()
            return r0
        L_0x0082:
            boolean r2 = r11.isMeasurementEnabled()     // Catch:{ all -> 0x052b }
            if (r2 != 0) goto L_0x009f
            com.google.android.gms.measurement.internal.zzef r0 = r31.zzab()     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzgr()     // Catch:{ all -> 0x052b }
            java.lang.String r2 = "Log and bundle disabled. package_name"
            r0.zza(r2, r15)     // Catch:{ all -> 0x052b }
            byte[] r0 = new byte[r3]     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzx r2 = r31.zzgy()
            r2.endTransaction()
            return r0
        L_0x009f:
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r2 = com.google.android.gms.internal.measurement.zzbs.zzg.zzpr()     // Catch:{ all -> 0x052b }
            r4 = 1
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r2 = r2.zzp(r4)     // Catch:{ all -> 0x052b }
            java.lang.String r4 = "android"
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r12 = r2.zzcc(r4)     // Catch:{ all -> 0x052b }
            java.lang.String r2 = r11.zzag()     // Catch:{ all -> 0x052b }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x052b }
            if (r2 != 0) goto L_0x00bf
            java.lang.String r2 = r11.zzag()     // Catch:{ all -> 0x052b }
            r12.zzch(r2)     // Catch:{ all -> 0x052b }
        L_0x00bf:
            java.lang.String r2 = r11.zzan()     // Catch:{ all -> 0x052b }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x052b }
            if (r2 != 0) goto L_0x00d0
            java.lang.String r2 = r11.zzan()     // Catch:{ all -> 0x052b }
            r12.zzcg(r2)     // Catch:{ all -> 0x052b }
        L_0x00d0:
            java.lang.String r2 = r11.zzal()     // Catch:{ all -> 0x052b }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x052b }
            if (r2 != 0) goto L_0x00e1
            java.lang.String r2 = r11.zzal()     // Catch:{ all -> 0x052b }
            r12.zzci(r2)     // Catch:{ all -> 0x052b }
        L_0x00e1:
            long r4 = r11.zzam()     // Catch:{ all -> 0x052b }
            r6 = -2147483648(0xffffffff80000000, double:NaN)
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x00f4
            long r4 = r11.zzam()     // Catch:{ all -> 0x052b }
            int r2 = (int) r4     // Catch:{ all -> 0x052b }
            r12.zzv(r2)     // Catch:{ all -> 0x052b }
        L_0x00f4:
            long r4 = r11.zzao()     // Catch:{ all -> 0x052b }
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r2 = r12.zzas(r4)     // Catch:{ all -> 0x052b }
            long r4 = r11.zzaq()     // Catch:{ all -> 0x052b }
            r2.zzax(r4)     // Catch:{ all -> 0x052b }
            java.lang.String r2 = r11.getGmpAppId()     // Catch:{ all -> 0x052b }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x052b }
            if (r2 != 0) goto L_0x0115
            java.lang.String r2 = r11.getGmpAppId()     // Catch:{ all -> 0x052b }
            r12.zzcm(r2)     // Catch:{ all -> 0x052b }
            goto L_0x0126
        L_0x0115:
            java.lang.String r2 = r11.zzah()     // Catch:{ all -> 0x052b }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x052b }
            if (r2 != 0) goto L_0x0126
            java.lang.String r2 = r11.zzah()     // Catch:{ all -> 0x052b }
            r12.zzcq(r2)     // Catch:{ all -> 0x052b }
        L_0x0126:
            long r4 = r11.zzap()     // Catch:{ all -> 0x052b }
            r12.zzau(r4)     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzfj r2 = r1.b     // Catch:{ all -> 0x052b }
            boolean r2 = r2.isEnabled()     // Catch:{ all -> 0x052b }
            if (r2 == 0) goto L_0x0155
            boolean r2 = com.google.android.gms.measurement.internal.zzs.zzbv()     // Catch:{ all -> 0x052b }
            if (r2 == 0) goto L_0x0155
            com.google.android.gms.measurement.internal.zzs r2 = r31.zzad()     // Catch:{ all -> 0x052b }
            java.lang.String r4 = r12.zzag()     // Catch:{ all -> 0x052b }
            boolean r2 = r2.zzl(r4)     // Catch:{ all -> 0x052b }
            if (r2 == 0) goto L_0x0155
            r12.zzag()     // Catch:{ all -> 0x052b }
            boolean r2 = android.text.TextUtils.isEmpty(r14)     // Catch:{ all -> 0x052b }
            if (r2 != 0) goto L_0x0155
            r12.zzcp(r14)     // Catch:{ all -> 0x052b }
        L_0x0155:
            com.google.android.gms.measurement.internal.zzeo r2 = r31.zzac()     // Catch:{ all -> 0x052b }
            java.lang.String r4 = r11.zzag()     // Catch:{ all -> 0x052b }
            android.util.Pair r2 = r2.a((java.lang.String) r4)     // Catch:{ all -> 0x052b }
            boolean r4 = r11.zzbe()     // Catch:{ all -> 0x052b }
            if (r4 == 0) goto L_0x01b0
            if (r2 == 0) goto L_0x01b0
            java.lang.Object r4 = r2.first     // Catch:{ all -> 0x052b }
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4     // Catch:{ all -> 0x052b }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x052b }
            if (r4 != 0) goto L_0x01b0
            java.lang.Object r4 = r2.first     // Catch:{ SecurityException -> 0x0194 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ SecurityException -> 0x0194 }
            long r5 = r0.zzfu     // Catch:{ SecurityException -> 0x0194 }
            java.lang.String r5 = java.lang.Long.toString(r5)     // Catch:{ SecurityException -> 0x0194 }
            java.lang.String r4 = zzo(r4, r5)     // Catch:{ SecurityException -> 0x0194 }
            r12.zzcj(r4)     // Catch:{ SecurityException -> 0x0194 }
            java.lang.Object r4 = r2.second     // Catch:{ all -> 0x052b }
            if (r4 == 0) goto L_0x01b0
            java.lang.Object r2 = r2.second     // Catch:{ all -> 0x052b }
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ all -> 0x052b }
            boolean r2 = r2.booleanValue()     // Catch:{ all -> 0x052b }
            r12.zzm(r2)     // Catch:{ all -> 0x052b }
            goto L_0x01b0
        L_0x0194:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzef r2 = r31.zzab()     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzgr()     // Catch:{ all -> 0x052b }
            java.lang.String r4 = "Resettable device id encryption failed"
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x052b }
            r2.zza(r4, r0)     // Catch:{ all -> 0x052b }
            byte[] r0 = new byte[r3]     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzx r2 = r31.zzgy()
            r2.endTransaction()
            return r0
        L_0x01b0:
            com.google.android.gms.measurement.internal.zzac r2 = r31.zzw()     // Catch:{ all -> 0x052b }
            r2.l()     // Catch:{ all -> 0x052b }
            java.lang.String r2 = android.os.Build.MODEL     // Catch:{ all -> 0x052b }
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r2 = r12.zzce(r2)     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzac r4 = r31.zzw()     // Catch:{ all -> 0x052b }
            r4.l()     // Catch:{ all -> 0x052b }
            java.lang.String r4 = android.os.Build.VERSION.RELEASE     // Catch:{ all -> 0x052b }
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r2 = r2.zzcd(r4)     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzac r4 = r31.zzw()     // Catch:{ all -> 0x052b }
            long r4 = r4.zzcq()     // Catch:{ all -> 0x052b }
            int r5 = (int) r4     // Catch:{ all -> 0x052b }
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r2 = r2.zzt(r5)     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzac r4 = r31.zzw()     // Catch:{ all -> 0x052b }
            java.lang.String r4 = r4.zzcr()     // Catch:{ all -> 0x052b }
            r2.zzcf(r4)     // Catch:{ all -> 0x052b }
            java.lang.String r2 = r11.getAppInstanceId()     // Catch:{ SecurityException -> 0x050e }
            long r4 = r0.zzfu     // Catch:{ SecurityException -> 0x050e }
            java.lang.String r4 = java.lang.Long.toString(r4)     // Catch:{ SecurityException -> 0x050e }
            java.lang.String r2 = zzo(r2, r4)     // Catch:{ SecurityException -> 0x050e }
            r12.zzck(r2)     // Catch:{ SecurityException -> 0x050e }
            java.lang.String r2 = r11.getFirebaseInstanceId()     // Catch:{ all -> 0x052b }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x052b }
            if (r2 != 0) goto L_0x0204
            java.lang.String r2 = r11.getFirebaseInstanceId()     // Catch:{ all -> 0x052b }
            r12.zzcn(r2)     // Catch:{ all -> 0x052b }
        L_0x0204:
            java.lang.String r2 = r11.zzag()     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzx r4 = r31.zzgy()     // Catch:{ all -> 0x052b }
            java.util.List r10 = r4.zzaa(r2)     // Catch:{ all -> 0x052b }
            java.util.Iterator r4 = r10.iterator()     // Catch:{ all -> 0x052b }
        L_0x0214:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x052b }
            if (r5 == 0) goto L_0x022b
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzjp r5 = (com.google.android.gms.measurement.internal.zzjp) r5     // Catch:{ all -> 0x052b }
            java.lang.String r6 = "_lte"
            java.lang.String r7 = r5.c     // Catch:{ all -> 0x052b }
            boolean r6 = r6.equals(r7)     // Catch:{ all -> 0x052b }
            if (r6 == 0) goto L_0x0214
            goto L_0x022c
        L_0x022b:
            r5 = r14
        L_0x022c:
            r17 = 0
            if (r5 == 0) goto L_0x0237
            java.lang.Object r4 = r5.e     // Catch:{ all -> 0x052b }
            if (r4 != 0) goto L_0x0235
            goto L_0x0237
        L_0x0235:
            r3 = r10
            goto L_0x025e
        L_0x0237:
            com.google.android.gms.measurement.internal.zzjp r8 = new com.google.android.gms.measurement.internal.zzjp     // Catch:{ all -> 0x052b }
            java.lang.String r6 = "auto"
            java.lang.String r7 = "_lte"
            com.google.android.gms.common.util.Clock r4 = r31.zzx()     // Catch:{ all -> 0x052b }
            long r19 = r4.currentTimeMillis()     // Catch:{ all -> 0x052b }
            java.lang.Long r16 = java.lang.Long.valueOf(r17)     // Catch:{ all -> 0x052b }
            r4 = r8
            r5 = r2
            r14 = r8
            r8 = r19
            r3 = r10
            r10 = r16
            r4.<init>(r5, r6, r7, r8, r10)     // Catch:{ all -> 0x052b }
            r3.add(r14)     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzx r4 = r31.zzgy()     // Catch:{ all -> 0x052b }
            r4.zza((com.google.android.gms.measurement.internal.zzjp) r14)     // Catch:{ all -> 0x052b }
        L_0x025e:
            com.google.android.gms.measurement.internal.zzs r4 = r31.zzad()     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzak.zzij     // Catch:{ all -> 0x052b }
            boolean r2 = r4.zze(r2, r5)     // Catch:{ all -> 0x052b }
            r4 = 1
            if (r2 == 0) goto L_0x02e1
            com.google.android.gms.measurement.internal.zzjo r2 = r31.zzgw()     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzef r6 = r2.zzab()     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzeh r6 = r6.zzgs()     // Catch:{ all -> 0x052b }
            java.lang.String r7 = "Checking account type status for ad personalization signals"
            r6.zzao(r7)     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzac r6 = r2.zzw()     // Catch:{ all -> 0x052b }
            boolean r6 = r6.d()     // Catch:{ all -> 0x052b }
            if (r6 == 0) goto L_0x02e1
            java.lang.String r6 = r11.zzag()     // Catch:{ all -> 0x052b }
            boolean r7 = r11.zzbe()     // Catch:{ all -> 0x052b }
            if (r7 == 0) goto L_0x02e1
            com.google.android.gms.measurement.internal.zzfd r7 = r2.zzgz()     // Catch:{ all -> 0x052b }
            boolean r7 = r7.e(r6)     // Catch:{ all -> 0x052b }
            if (r7 == 0) goto L_0x02e1
            com.google.android.gms.measurement.internal.zzef r7 = r2.zzab()     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzeh r7 = r7.zzgr()     // Catch:{ all -> 0x052b }
            java.lang.String r8 = "Turning off ad personalization due to account type"
            r7.zzao(r8)     // Catch:{ all -> 0x052b }
            java.util.Iterator r7 = r3.iterator()     // Catch:{ all -> 0x052b }
        L_0x02ac:
            boolean r8 = r7.hasNext()     // Catch:{ all -> 0x052b }
            if (r8 == 0) goto L_0x02c5
            java.lang.Object r8 = r7.next()     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzjp r8 = (com.google.android.gms.measurement.internal.zzjp) r8     // Catch:{ all -> 0x052b }
            java.lang.String r9 = "_npa"
            java.lang.String r8 = r8.c     // Catch:{ all -> 0x052b }
            boolean r8 = r9.equals(r8)     // Catch:{ all -> 0x052b }
            if (r8 == 0) goto L_0x02ac
            r7.remove()     // Catch:{ all -> 0x052b }
        L_0x02c5:
            com.google.android.gms.measurement.internal.zzjp r7 = new com.google.android.gms.measurement.internal.zzjp     // Catch:{ all -> 0x052b }
            java.lang.String r24 = "auto"
            java.lang.String r25 = "_npa"
            com.google.android.gms.common.util.Clock r2 = r2.zzx()     // Catch:{ all -> 0x052b }
            long r26 = r2.currentTimeMillis()     // Catch:{ all -> 0x052b }
            java.lang.Long r28 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x052b }
            r22 = r7
            r23 = r6
            r22.<init>(r23, r24, r25, r26, r28)     // Catch:{ all -> 0x052b }
            r3.add(r7)     // Catch:{ all -> 0x052b }
        L_0x02e1:
            int r2 = r3.size()     // Catch:{ all -> 0x052b }
            com.google.android.gms.internal.measurement.zzbs$zzk[] r2 = new com.google.android.gms.internal.measurement.zzbs.zzk[r2]     // Catch:{ all -> 0x052b }
            r6 = 0
        L_0x02e8:
            int r7 = r3.size()     // Catch:{ all -> 0x052b }
            if (r6 >= r7) goto L_0x0326
            com.google.android.gms.internal.measurement.zzbs$zzk$zza r7 = com.google.android.gms.internal.measurement.zzbs.zzk.zzqu()     // Catch:{ all -> 0x052b }
            java.lang.Object r8 = r3.get(r6)     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzjp r8 = (com.google.android.gms.measurement.internal.zzjp) r8     // Catch:{ all -> 0x052b }
            java.lang.String r8 = r8.c     // Catch:{ all -> 0x052b }
            com.google.android.gms.internal.measurement.zzbs$zzk$zza r7 = r7.zzdb(r8)     // Catch:{ all -> 0x052b }
            java.lang.Object r8 = r3.get(r6)     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzjp r8 = (com.google.android.gms.measurement.internal.zzjp) r8     // Catch:{ all -> 0x052b }
            long r8 = r8.d     // Catch:{ all -> 0x052b }
            com.google.android.gms.internal.measurement.zzbs$zzk$zza r7 = r7.zzbk(r8)     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzjo r8 = r31.zzgw()     // Catch:{ all -> 0x052b }
            java.lang.Object r9 = r3.get(r6)     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzjp r9 = (com.google.android.gms.measurement.internal.zzjp) r9     // Catch:{ all -> 0x052b }
            java.lang.Object r9 = r9.e     // Catch:{ all -> 0x052b }
            r8.a((com.google.android.gms.internal.measurement.zzbs.zzk.zza) r7, (java.lang.Object) r9)     // Catch:{ all -> 0x052b }
            com.google.android.gms.internal.measurement.zzgi r7 = r7.zzug()     // Catch:{ all -> 0x052b }
            com.google.android.gms.internal.measurement.zzey r7 = (com.google.android.gms.internal.measurement.zzey) r7     // Catch:{ all -> 0x052b }
            com.google.android.gms.internal.measurement.zzbs$zzk r7 = (com.google.android.gms.internal.measurement.zzbs.zzk) r7     // Catch:{ all -> 0x052b }
            r2[r6] = r7     // Catch:{ all -> 0x052b }
            int r6 = r6 + 1
            goto L_0x02e8
        L_0x0326:
            java.util.List r2 = java.util.Arrays.asList(r2)     // Catch:{ all -> 0x052b }
            r12.zzb(r2)     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzah r2 = r0.zzfq     // Catch:{ all -> 0x052b }
            android.os.Bundle r14 = r2.zzcv()     // Catch:{ all -> 0x052b }
            java.lang.String r2 = "_c"
            r14.putLong(r2, r4)     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzef r2 = r31.zzab()     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzgr()     // Catch:{ all -> 0x052b }
            java.lang.String r3 = "Marking in-app purchase as real-time"
            r2.zzao(r3)     // Catch:{ all -> 0x052b }
            java.lang.String r2 = "_r"
            r14.putLong(r2, r4)     // Catch:{ all -> 0x052b }
            java.lang.String r2 = "_o"
            java.lang.String r3 = r0.origin     // Catch:{ all -> 0x052b }
            r14.putString(r2, r3)     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzjs r2 = r31.zzz()     // Catch:{ all -> 0x052b }
            java.lang.String r3 = r12.zzag()     // Catch:{ all -> 0x052b }
            boolean r2 = r2.f(r3)     // Catch:{ all -> 0x052b }
            if (r2 == 0) goto L_0x0379
            com.google.android.gms.measurement.internal.zzjs r2 = r31.zzz()     // Catch:{ all -> 0x052b }
            java.lang.String r3 = "_dbg"
            java.lang.Long r6 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x052b }
            r2.a((android.os.Bundle) r14, (java.lang.String) r3, (java.lang.Object) r6)     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzjs r2 = r31.zzz()     // Catch:{ all -> 0x052b }
            java.lang.String r3 = "_r"
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x052b }
            r2.a((android.os.Bundle) r14, (java.lang.String) r3, (java.lang.Object) r4)     // Catch:{ all -> 0x052b }
        L_0x0379:
            com.google.android.gms.measurement.internal.zzx r2 = r31.zzgy()     // Catch:{ all -> 0x052b }
            java.lang.String r3 = r0.name     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzae r2 = r2.zzc(r15, r3)     // Catch:{ all -> 0x052b }
            if (r2 != 0) goto L_0x03b7
            com.google.android.gms.measurement.internal.zzae r19 = new com.google.android.gms.measurement.internal.zzae     // Catch:{ all -> 0x052b }
            java.lang.String r4 = r0.name     // Catch:{ all -> 0x052b }
            r5 = 0
            r7 = 0
            long r9 = r0.zzfu     // Catch:{ all -> 0x052b }
            r22 = 0
            r16 = 0
            r20 = 0
            r24 = 0
            r25 = 0
            r2 = r19
            r3 = r33
            r26 = r11
            r29 = r12
            r11 = r22
            r30 = r13
            r13 = r16
            r21 = r14
            r22 = 0
            r14 = r20
            r15 = r24
            r16 = r25
            r2.<init>(r3, r4, r5, r7, r9, r11, r13, r14, r15, r16)     // Catch:{ all -> 0x052b }
            r9 = r17
            goto L_0x03ca
        L_0x03b7:
            r26 = r11
            r29 = r12
            r30 = r13
            r21 = r14
            r22 = 0
            long r3 = r2.f     // Catch:{ all -> 0x052b }
            long r5 = r0.zzfu     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzae r19 = r2.a(r5)     // Catch:{ all -> 0x052b }
            r9 = r3
        L_0x03ca:
            r12 = r19
            com.google.android.gms.measurement.internal.zzx r2 = r31.zzgy()     // Catch:{ all -> 0x052b }
            r2.zza((com.google.android.gms.measurement.internal.zzae) r12)     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzaf r13 = new com.google.android.gms.measurement.internal.zzaf     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzfj r3 = r1.b     // Catch:{ all -> 0x052b }
            java.lang.String r4 = r0.origin     // Catch:{ all -> 0x052b }
            java.lang.String r6 = r0.name     // Catch:{ all -> 0x052b }
            long r7 = r0.zzfu     // Catch:{ all -> 0x052b }
            r2 = r13
            r5 = r33
            r11 = r21
            r2.<init>((com.google.android.gms.measurement.internal.zzfj) r3, (java.lang.String) r4, (java.lang.String) r5, (java.lang.String) r6, (long) r7, (long) r9, (android.os.Bundle) r11)     // Catch:{ all -> 0x052b }
            com.google.android.gms.internal.measurement.zzbs$zzc$zza r2 = com.google.android.gms.internal.measurement.zzbs.zzc.zzmq()     // Catch:{ all -> 0x052b }
            long r3 = r13.c     // Catch:{ all -> 0x052b }
            com.google.android.gms.internal.measurement.zzbs$zzc$zza r2 = r2.zzag(r3)     // Catch:{ all -> 0x052b }
            java.lang.String r3 = r13.b     // Catch:{ all -> 0x052b }
            com.google.android.gms.internal.measurement.zzbs$zzc$zza r2 = r2.zzbx(r3)     // Catch:{ all -> 0x052b }
            long r3 = r13.d     // Catch:{ all -> 0x052b }
            com.google.android.gms.internal.measurement.zzbs$zzc$zza r2 = r2.zzah(r3)     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzah r3 = r13.e     // Catch:{ all -> 0x052b }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x052b }
        L_0x0401:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x052b }
            if (r4 == 0) goto L_0x0426
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x052b }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x052b }
            com.google.android.gms.internal.measurement.zzbs$zze$zza r5 = com.google.android.gms.internal.measurement.zzbs.zze.zzng()     // Catch:{ all -> 0x052b }
            com.google.android.gms.internal.measurement.zzbs$zze$zza r5 = r5.zzbz(r4)     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzah r6 = r13.e     // Catch:{ all -> 0x052b }
            java.lang.Object r4 = r6.a((java.lang.String) r4)     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzjo r6 = r31.zzgw()     // Catch:{ all -> 0x052b }
            r6.a((com.google.android.gms.internal.measurement.zzbs.zze.zza) r5, (java.lang.Object) r4)     // Catch:{ all -> 0x052b }
            r2.zza((com.google.android.gms.internal.measurement.zzbs.zze.zza) r5)     // Catch:{ all -> 0x052b }
            goto L_0x0401
        L_0x0426:
            r3 = r29
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r4 = r3.zza((com.google.android.gms.internal.measurement.zzbs.zzc.zza) r2)     // Catch:{ all -> 0x052b }
            com.google.android.gms.internal.measurement.zzbs$zzh$zza r5 = com.google.android.gms.internal.measurement.zzbs.zzh.zzpt()     // Catch:{ all -> 0x052b }
            com.google.android.gms.internal.measurement.zzbs$zzd$zza r6 = com.google.android.gms.internal.measurement.zzbs.zzd.zzms()     // Catch:{ all -> 0x052b }
            long r7 = r12.c     // Catch:{ all -> 0x052b }
            com.google.android.gms.internal.measurement.zzbs$zzd$zza r6 = r6.zzak(r7)     // Catch:{ all -> 0x052b }
            java.lang.String r0 = r0.name     // Catch:{ all -> 0x052b }
            com.google.android.gms.internal.measurement.zzbs$zzd$zza r0 = r6.zzby(r0)     // Catch:{ all -> 0x052b }
            com.google.android.gms.internal.measurement.zzbs$zzh$zza r0 = r5.zza(r0)     // Catch:{ all -> 0x052b }
            r4.zza((com.google.android.gms.internal.measurement.zzbs.zzh.zza) r0)     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzp r0 = r31.zzgx()     // Catch:{ all -> 0x052b }
            java.lang.String r4 = r26.zzag()     // Catch:{ all -> 0x052b }
            java.util.List r5 = java.util.Collections.emptyList()     // Catch:{ all -> 0x052b }
            java.util.List r6 = r3.zzno()     // Catch:{ all -> 0x052b }
            java.util.List r0 = r0.a(r4, r5, r6)     // Catch:{ all -> 0x052b }
            r3.zzc(r0)     // Catch:{ all -> 0x052b }
            boolean r0 = r2.zzml()     // Catch:{ all -> 0x052b }
            if (r0 == 0) goto L_0x0473
            long r4 = r2.getTimestampMillis()     // Catch:{ all -> 0x052b }
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r0 = r3.zzao(r4)     // Catch:{ all -> 0x052b }
            long r4 = r2.getTimestampMillis()     // Catch:{ all -> 0x052b }
            r0.zzap(r4)     // Catch:{ all -> 0x052b }
        L_0x0473:
            long r4 = r26.zzak()     // Catch:{ all -> 0x052b }
            int r0 = (r4 > r17 ? 1 : (r4 == r17 ? 0 : -1))
            if (r0 == 0) goto L_0x047e
            r3.zzar(r4)     // Catch:{ all -> 0x052b }
        L_0x047e:
            long r6 = r26.zzaj()     // Catch:{ all -> 0x052b }
            int r2 = (r6 > r17 ? 1 : (r6 == r17 ? 0 : -1))
            if (r2 == 0) goto L_0x048a
            r3.zzaq(r6)     // Catch:{ all -> 0x052b }
            goto L_0x048f
        L_0x048a:
            if (r0 == 0) goto L_0x048f
            r3.zzaq(r4)     // Catch:{ all -> 0x052b }
        L_0x048f:
            r26.zzau()     // Catch:{ all -> 0x052b }
            long r4 = r26.zzar()     // Catch:{ all -> 0x052b }
            int r0 = (int) r4     // Catch:{ all -> 0x052b }
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r0 = r3.zzu(r0)     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzs r2 = r31.zzad()     // Catch:{ all -> 0x052b }
            long r4 = r2.zzao()     // Catch:{ all -> 0x052b }
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r0 = r0.zzat(r4)     // Catch:{ all -> 0x052b }
            com.google.android.gms.common.util.Clock r2 = r31.zzx()     // Catch:{ all -> 0x052b }
            long r4 = r2.currentTimeMillis()     // Catch:{ all -> 0x052b }
            com.google.android.gms.internal.measurement.zzbs$zzg$zza r0 = r0.zzan(r4)     // Catch:{ all -> 0x052b }
            java.lang.Boolean r2 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x052b }
            boolean r2 = r2.booleanValue()     // Catch:{ all -> 0x052b }
            r0.zzn(r2)     // Catch:{ all -> 0x052b }
            r0 = r30
            r0.zza(r3)     // Catch:{ all -> 0x052b }
            long r4 = r3.zznq()     // Catch:{ all -> 0x052b }
            r2 = r26
            r2.zze((long) r4)     // Catch:{ all -> 0x052b }
            long r3 = r3.zznr()     // Catch:{ all -> 0x052b }
            r2.zzf((long) r3)     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzx r3 = r31.zzgy()     // Catch:{ all -> 0x052b }
            r3.zza((com.google.android.gms.measurement.internal.zzf) r2)     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzx r2 = r31.zzgy()     // Catch:{ all -> 0x052b }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzx r2 = r31.zzgy()
            r2.endTransaction()
            com.google.android.gms.measurement.internal.zzjo r2 = r31.zzgw()     // Catch:{ IOException -> 0x04fb }
            com.google.android.gms.internal.measurement.zzgi r0 = r0.zzug()     // Catch:{ IOException -> 0x04fb }
            com.google.android.gms.internal.measurement.zzey r0 = (com.google.android.gms.internal.measurement.zzey) r0     // Catch:{ IOException -> 0x04fb }
            com.google.android.gms.internal.measurement.zzbs$zzf r0 = (com.google.android.gms.internal.measurement.zzbs.zzf) r0     // Catch:{ IOException -> 0x04fb }
            byte[] r0 = r0.toByteArray()     // Catch:{ IOException -> 0x04fb }
            byte[] r0 = r2.c(r0)     // Catch:{ IOException -> 0x04fb }
            return r0
        L_0x04fb:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzef r2 = r31.zzab()
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzgk()
            java.lang.String r3 = "Data loss. Failed to bundle and serialize. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzef.a((java.lang.String) r33)
            r2.zza(r3, r4, r0)
            return r22
        L_0x050e:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzef r2 = r31.zzab()     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzeh r2 = r2.zzgr()     // Catch:{ all -> 0x052b }
            java.lang.String r3 = "app instance id encryption failed"
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x052b }
            r2.zza(r3, r0)     // Catch:{ all -> 0x052b }
            r2 = 0
            byte[] r0 = new byte[r2]     // Catch:{ all -> 0x052b }
            com.google.android.gms.measurement.internal.zzx r2 = r31.zzgy()
            r2.endTransaction()
            return r0
        L_0x052b:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzx r2 = r31.zzgy()
            r2.endTransaction()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzhp.zzb(com.google.android.gms.measurement.internal.zzai, java.lang.String):byte[]");
    }
}
