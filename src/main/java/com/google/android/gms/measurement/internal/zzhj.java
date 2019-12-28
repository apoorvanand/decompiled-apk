package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import androidx.annotation.MainThread;

@TargetApi(14)
@MainThread
final class zzhj implements Application.ActivityLifecycleCallbacks {
    private final /* synthetic */ zzgp zzpt;

    private zzhj(zzgp zzgp) {
        this.zzpt = zzgp;
    }

    /* synthetic */ zzhj(zzgp zzgp, zzgo zzgo) {
        this(zzgp);
    }

    /* JADX WARNING: Removed duplicated region for block: B:64:0x0151 A[Catch:{ Exception -> 0x01b9, all -> 0x01b7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0153 A[Catch:{ Exception -> 0x01b9, all -> 0x01b7 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onActivityCreated(android.app.Activity r11, android.os.Bundle r12) {
        /*
            r10 = this;
            com.google.android.gms.measurement.internal.zzgp r0 = r10.zzpt     // Catch:{ Exception -> 0x01b9 }
            com.google.android.gms.measurement.internal.zzef r0 = r0.zzab()     // Catch:{ Exception -> 0x01b9 }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzgs()     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r1 = "onActivityCreated"
            r0.zzao(r1)     // Catch:{ Exception -> 0x01b9 }
            android.content.Intent r0 = r11.getIntent()     // Catch:{ Exception -> 0x01b9 }
            if (r0 != 0) goto L_0x001f
        L_0x0015:
            com.google.android.gms.measurement.internal.zzgp r0 = r10.zzpt
            com.google.android.gms.measurement.internal.zzhq r0 = r0.zzt()
            r0.onActivityCreated(r11, r12)
            return
        L_0x001f:
            android.net.Uri r1 = r0.getData()     // Catch:{ Exception -> 0x01b9 }
            if (r1 == 0) goto L_0x0015
            boolean r2 = r1.isHierarchical()     // Catch:{ Exception -> 0x01b9 }
            if (r2 != 0) goto L_0x002c
            goto L_0x0015
        L_0x002c:
            com.google.android.gms.measurement.internal.zzgp r2 = r10.zzpt     // Catch:{ Exception -> 0x01b9 }
            r2.zzz()     // Catch:{ Exception -> 0x01b9 }
            boolean r0 = com.google.android.gms.measurement.internal.zzjs.a((android.content.Intent) r0)     // Catch:{ Exception -> 0x01b9 }
            if (r0 == 0) goto L_0x003a
            java.lang.String r0 = "gs"
            goto L_0x003c
        L_0x003a:
            java.lang.String r0 = "auto"
        L_0x003c:
            java.lang.String r2 = "referrer"
            java.lang.String r2 = r1.getQueryParameter(r2)     // Catch:{ Exception -> 0x01b9 }
            com.google.android.gms.measurement.internal.zzgp r3 = r10.zzpt     // Catch:{ Exception -> 0x01b9 }
            com.google.android.gms.measurement.internal.zzs r3 = r3.zzad()     // Catch:{ Exception -> 0x01b9 }
            com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r4 = com.google.android.gms.measurement.internal.zzak.zzje     // Catch:{ Exception -> 0x01b9 }
            boolean r3 = r3.zza(r4)     // Catch:{ Exception -> 0x01b9 }
            r4 = 0
            if (r3 != 0) goto L_0x0062
            com.google.android.gms.measurement.internal.zzgp r3 = r10.zzpt     // Catch:{ Exception -> 0x01b9 }
            com.google.android.gms.measurement.internal.zzs r3 = r3.zzad()     // Catch:{ Exception -> 0x01b9 }
            com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzak.zzjg     // Catch:{ Exception -> 0x01b9 }
            boolean r3 = r3.zza(r5)     // Catch:{ Exception -> 0x01b9 }
            if (r3 == 0) goto L_0x0060
            goto L_0x0062
        L_0x0060:
            r3 = r4
            goto L_0x00c7
        L_0x0062:
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Exception -> 0x01b9 }
            if (r3 == 0) goto L_0x0069
            goto L_0x0060
        L_0x0069:
            java.lang.String r3 = "gclid"
            boolean r3 = r2.contains(r3)     // Catch:{ Exception -> 0x01b9 }
            if (r3 != 0) goto L_0x0099
            java.lang.String r3 = "utm_campaign"
            boolean r3 = r2.contains(r3)     // Catch:{ Exception -> 0x01b9 }
            if (r3 != 0) goto L_0x0099
            java.lang.String r3 = "utm_source"
            boolean r3 = r2.contains(r3)     // Catch:{ Exception -> 0x01b9 }
            if (r3 != 0) goto L_0x0099
            java.lang.String r3 = "utm_medium"
            boolean r3 = r2.contains(r3)     // Catch:{ Exception -> 0x01b9 }
            if (r3 != 0) goto L_0x0099
            com.google.android.gms.measurement.internal.zzgp r3 = r10.zzpt     // Catch:{ Exception -> 0x01b9 }
            com.google.android.gms.measurement.internal.zzef r3 = r3.zzab()     // Catch:{ Exception -> 0x01b9 }
            com.google.android.gms.measurement.internal.zzeh r3 = r3.zzgr()     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r5 = "Activity created with data 'referrer' without required params"
            r3.zzao(r5)     // Catch:{ Exception -> 0x01b9 }
            goto L_0x0060
        L_0x0099:
            com.google.android.gms.measurement.internal.zzgp r3 = r10.zzpt     // Catch:{ Exception -> 0x01b9 }
            com.google.android.gms.measurement.internal.zzjs r3 = r3.zzz()     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r5 = "https://google.com/search?"
            java.lang.String r6 = java.lang.String.valueOf(r2)     // Catch:{ Exception -> 0x01b9 }
            int r7 = r6.length()     // Catch:{ Exception -> 0x01b9 }
            if (r7 == 0) goto L_0x00b0
            java.lang.String r5 = r5.concat(r6)     // Catch:{ Exception -> 0x01b9 }
            goto L_0x00b6
        L_0x00b0:
            java.lang.String r6 = new java.lang.String     // Catch:{ Exception -> 0x01b9 }
            r6.<init>(r5)     // Catch:{ Exception -> 0x01b9 }
            r5 = r6
        L_0x00b6:
            android.net.Uri r5 = android.net.Uri.parse(r5)     // Catch:{ Exception -> 0x01b9 }
            android.os.Bundle r3 = r3.a((android.net.Uri) r5)     // Catch:{ Exception -> 0x01b9 }
            if (r3 == 0) goto L_0x00c7
            java.lang.String r5 = "_cis"
            java.lang.String r6 = "referrer"
            r3.putString(r5, r6)     // Catch:{ Exception -> 0x01b9 }
        L_0x00c7:
            r5 = 0
            r6 = 1
            if (r12 != 0) goto L_0x011a
            com.google.android.gms.measurement.internal.zzgp r4 = r10.zzpt     // Catch:{ Exception -> 0x01b9 }
            com.google.android.gms.measurement.internal.zzjs r4 = r4.zzz()     // Catch:{ Exception -> 0x01b9 }
            android.os.Bundle r4 = r4.a((android.net.Uri) r1)     // Catch:{ Exception -> 0x01b9 }
            if (r4 == 0) goto L_0x011a
            java.lang.String r1 = "_cis"
            java.lang.String r7 = "intent"
            r4.putString(r1, r7)     // Catch:{ Exception -> 0x01b9 }
            com.google.android.gms.measurement.internal.zzgp r1 = r10.zzpt     // Catch:{ Exception -> 0x01b9 }
            com.google.android.gms.measurement.internal.zzs r1 = r1.zzad()     // Catch:{ Exception -> 0x01b9 }
            com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzak.zzje     // Catch:{ Exception -> 0x01b9 }
            boolean r1 = r1.zza(r7)     // Catch:{ Exception -> 0x01b9 }
            if (r1 == 0) goto L_0x0113
            java.lang.String r1 = "gclid"
            boolean r1 = r4.containsKey(r1)     // Catch:{ Exception -> 0x01b9 }
            if (r1 != 0) goto L_0x0113
            if (r3 == 0) goto L_0x0113
            java.lang.String r1 = "gclid"
            boolean r1 = r3.containsKey(r1)     // Catch:{ Exception -> 0x01b9 }
            if (r1 == 0) goto L_0x0113
            java.lang.String r1 = "_cer"
            java.lang.String r7 = "gclid=%s"
            java.lang.Object[] r8 = new java.lang.Object[r6]     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r9 = "gclid"
            java.lang.String r9 = r3.getString(r9)     // Catch:{ Exception -> 0x01b9 }
            r8[r5] = r9     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r7 = java.lang.String.format(r7, r8)     // Catch:{ Exception -> 0x01b9 }
            r4.putString(r1, r7)     // Catch:{ Exception -> 0x01b9 }
        L_0x0113:
            com.google.android.gms.measurement.internal.zzgp r1 = r10.zzpt     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r7 = "_cmp"
            r1.logEvent(r0, r7, r4)     // Catch:{ Exception -> 0x01b9 }
        L_0x011a:
            com.google.android.gms.measurement.internal.zzgp r0 = r10.zzpt     // Catch:{ Exception -> 0x01b9 }
            com.google.android.gms.measurement.internal.zzs r0 = r0.zzad()     // Catch:{ Exception -> 0x01b9 }
            com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r1 = com.google.android.gms.measurement.internal.zzak.zzjg     // Catch:{ Exception -> 0x01b9 }
            boolean r0 = r0.zza(r1)     // Catch:{ Exception -> 0x01b9 }
            if (r0 == 0) goto L_0x014b
            if (r3 == 0) goto L_0x014b
            java.lang.String r0 = "gclid"
            boolean r0 = r3.containsKey(r0)     // Catch:{ Exception -> 0x01b9 }
            if (r0 == 0) goto L_0x014b
            if (r4 == 0) goto L_0x013c
            java.lang.String r0 = "gclid"
            boolean r0 = r4.containsKey(r0)     // Catch:{ Exception -> 0x01b9 }
            if (r0 != 0) goto L_0x014b
        L_0x013c:
            com.google.android.gms.measurement.internal.zzgp r0 = r10.zzpt     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r1 = "auto"
            java.lang.String r4 = "_lgclid"
            java.lang.String r7 = "gclid"
            java.lang.String r3 = r3.getString(r7)     // Catch:{ Exception -> 0x01b9 }
            r0.zzb((java.lang.String) r1, (java.lang.String) r4, (java.lang.Object) r3, (boolean) r6)     // Catch:{ Exception -> 0x01b9 }
        L_0x014b:
            boolean r0 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Exception -> 0x01b9 }
            if (r0 == 0) goto L_0x0153
            goto L_0x0015
        L_0x0153:
            java.lang.String r0 = "gclid"
            boolean r0 = r2.contains(r0)     // Catch:{ Exception -> 0x01b9 }
            if (r0 == 0) goto L_0x0184
            java.lang.String r0 = "utm_campaign"
            boolean r0 = r2.contains(r0)     // Catch:{ Exception -> 0x01b9 }
            if (r0 != 0) goto L_0x0183
            java.lang.String r0 = "utm_source"
            boolean r0 = r2.contains(r0)     // Catch:{ Exception -> 0x01b9 }
            if (r0 != 0) goto L_0x0183
            java.lang.String r0 = "utm_medium"
            boolean r0 = r2.contains(r0)     // Catch:{ Exception -> 0x01b9 }
            if (r0 != 0) goto L_0x0183
            java.lang.String r0 = "utm_term"
            boolean r0 = r2.contains(r0)     // Catch:{ Exception -> 0x01b9 }
            if (r0 != 0) goto L_0x0183
            java.lang.String r0 = "utm_content"
            boolean r0 = r2.contains(r0)     // Catch:{ Exception -> 0x01b9 }
            if (r0 == 0) goto L_0x0184
        L_0x0183:
            r5 = 1
        L_0x0184:
            if (r5 != 0) goto L_0x0197
            com.google.android.gms.measurement.internal.zzgp r0 = r10.zzpt     // Catch:{ Exception -> 0x01b9 }
            com.google.android.gms.measurement.internal.zzef r0 = r0.zzab()     // Catch:{ Exception -> 0x01b9 }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzgr()     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r1 = "Activity created with data 'referrer' without required params"
            r0.zzao(r1)     // Catch:{ Exception -> 0x01b9 }
            goto L_0x0015
        L_0x0197:
            com.google.android.gms.measurement.internal.zzgp r0 = r10.zzpt     // Catch:{ Exception -> 0x01b9 }
            com.google.android.gms.measurement.internal.zzef r0 = r0.zzab()     // Catch:{ Exception -> 0x01b9 }
            com.google.android.gms.measurement.internal.zzeh r0 = r0.zzgr()     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r1 = "Activity created with referrer"
            r0.zza(r1, r2)     // Catch:{ Exception -> 0x01b9 }
            boolean r0 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Exception -> 0x01b9 }
            if (r0 != 0) goto L_0x0015
            com.google.android.gms.measurement.internal.zzgp r0 = r10.zzpt     // Catch:{ Exception -> 0x01b9 }
            java.lang.String r1 = "auto"
            java.lang.String r3 = "_ldl"
            r0.zzb((java.lang.String) r1, (java.lang.String) r3, (java.lang.Object) r2, (boolean) r6)     // Catch:{ Exception -> 0x01b9 }
            goto L_0x0015
        L_0x01b7:
            r0 = move-exception
            goto L_0x01cb
        L_0x01b9:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzgp r1 = r10.zzpt     // Catch:{ all -> 0x01b7 }
            com.google.android.gms.measurement.internal.zzef r1 = r1.zzab()     // Catch:{ all -> 0x01b7 }
            com.google.android.gms.measurement.internal.zzeh r1 = r1.zzgk()     // Catch:{ all -> 0x01b7 }
            java.lang.String r2 = "Throwable caught in onActivityCreated"
            r1.zza(r2, r0)     // Catch:{ all -> 0x01b7 }
            goto L_0x0015
        L_0x01cb:
            com.google.android.gms.measurement.internal.zzgp r1 = r10.zzpt
            com.google.android.gms.measurement.internal.zzhq r1 = r1.zzt()
            r1.onActivityCreated(r11, r12)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzhj.onActivityCreated(android.app.Activity, android.os.Bundle):void");
    }

    public final void onActivityDestroyed(Activity activity) {
        this.zzpt.zzt().onActivityDestroyed(activity);
    }

    @MainThread
    public final void onActivityPaused(Activity activity) {
        this.zzpt.zzt().onActivityPaused(activity);
        zziw zzv = this.zzpt.zzv();
        zzv.zzaa().zza((Runnable) new zzja(zzv, zzv.zzx().elapsedRealtime()));
    }

    @MainThread
    public final void onActivityResumed(Activity activity) {
        this.zzpt.zzt().onActivityResumed(activity);
        zziw zzv = this.zzpt.zzv();
        zzv.zzaa().zza((Runnable) new zzjb(zzv, zzv.zzx().elapsedRealtime()));
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.zzpt.zzt().onActivitySaveInstanceState(activity, bundle);
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }
}
