package com.google.firebase.auth.api.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.firebase_auth.zzem;
import com.google.android.gms.internal.firebase_auth.zzes;
import com.google.android.gms.internal.firebase_auth.zzfg;
import com.google.android.gms.internal.firebase_auth.zzfj;

final class zzg implements zzez<zzfj> {
    private final /* synthetic */ zzdm zzla;
    private final /* synthetic */ zzb zzle;
    private final /* synthetic */ zzfg zzlg;
    private final /* synthetic */ zzem zzlh;
    private final /* synthetic */ zzes zzli;
    private final /* synthetic */ zzew zzlj;

    zzg(zzb zzb, zzfg zzfg, zzem zzem, zzdm zzdm, zzes zzes, zzew zzew) {
        this.zzle = zzb;
        this.zzlg = zzfg;
        this.zzlh = zzem;
        this.zzla = zzdm;
        this.zzli = zzes;
        this.zzlj = zzew;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x008c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ void onSuccess(java.lang.Object r4) {
        /*
            r3 = this;
            com.google.android.gms.internal.firebase_auth.zzfj r4 = (com.google.android.gms.internal.firebase_auth.zzfj) r4
            com.google.android.gms.internal.firebase_auth.zzfg r0 = r3.zzlg
            java.lang.String r1 = "EMAIL"
            boolean r0 = r0.zzcp(r1)
            r1 = 0
            if (r0 == 0) goto L_0x0013
            com.google.android.gms.internal.firebase_auth.zzem r0 = r3.zzlh
            r0.zzcf(r1)
            goto L_0x0026
        L_0x0013:
            com.google.android.gms.internal.firebase_auth.zzfg r0 = r3.zzlg
            java.lang.String r0 = r0.getEmail()
            if (r0 == 0) goto L_0x0026
            com.google.android.gms.internal.firebase_auth.zzem r0 = r3.zzlh
            com.google.android.gms.internal.firebase_auth.zzfg r2 = r3.zzlg
            java.lang.String r2 = r2.getEmail()
            r0.zzcf(r2)
        L_0x0026:
            com.google.android.gms.internal.firebase_auth.zzfg r0 = r3.zzlg
            java.lang.String r2 = "DISPLAY_NAME"
            boolean r0 = r0.zzcp(r2)
            if (r0 == 0) goto L_0x0036
            com.google.android.gms.internal.firebase_auth.zzem r0 = r3.zzlh
            r0.zzcg(r1)
            goto L_0x0049
        L_0x0036:
            com.google.android.gms.internal.firebase_auth.zzfg r0 = r3.zzlg
            java.lang.String r0 = r0.getDisplayName()
            if (r0 == 0) goto L_0x0049
            com.google.android.gms.internal.firebase_auth.zzem r0 = r3.zzlh
            com.google.android.gms.internal.firebase_auth.zzfg r2 = r3.zzlg
            java.lang.String r2 = r2.getDisplayName()
            r0.zzcg(r2)
        L_0x0049:
            com.google.android.gms.internal.firebase_auth.zzfg r0 = r3.zzlg
            java.lang.String r2 = "PHOTO_URL"
            boolean r0 = r0.zzcp(r2)
            if (r0 == 0) goto L_0x0059
            com.google.android.gms.internal.firebase_auth.zzem r0 = r3.zzlh
        L_0x0055:
            r0.zzch(r1)
            goto L_0x006a
        L_0x0059:
            com.google.android.gms.internal.firebase_auth.zzfg r0 = r3.zzlg
            java.lang.String r0 = r0.zzam()
            if (r0 == 0) goto L_0x006a
            com.google.android.gms.internal.firebase_auth.zzem r0 = r3.zzlh
            com.google.android.gms.internal.firebase_auth.zzfg r1 = r3.zzlg
            java.lang.String r1 = r1.zzam()
            goto L_0x0055
        L_0x006a:
            com.google.android.gms.internal.firebase_auth.zzfg r0 = r3.zzlg
            java.lang.String r0 = r0.getPassword()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x0085
            com.google.android.gms.internal.firebase_auth.zzem r0 = r3.zzlh
            java.lang.String r1 = "redacted"
            byte[] r1 = r1.getBytes()
            java.lang.String r1 = com.google.android.gms.common.util.Base64Utils.encode(r1)
            r0.zzci(r1)
        L_0x0085:
            java.util.List r0 = r4.zzes()
            if (r0 == 0) goto L_0x008c
            goto L_0x0091
        L_0x008c:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L_0x0091:
            com.google.android.gms.internal.firebase_auth.zzem r1 = r3.zzlh
            r1.zzc(r0)
            com.google.firebase.auth.api.internal.zzdm r0 = r3.zzla
            com.google.firebase.auth.api.internal.zzb r1 = r3.zzle
            com.google.android.gms.internal.firebase_auth.zzes r2 = r3.zzli
            com.google.android.gms.internal.firebase_auth.zzes r4 = com.google.firebase.auth.api.internal.zzb.zza((com.google.android.gms.internal.firebase_auth.zzes) r2, (com.google.android.gms.internal.firebase_auth.zzfj) r4)
            com.google.android.gms.internal.firebase_auth.zzem r1 = r3.zzlh
            r0.zza(r4, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.api.internal.zzg.onSuccess(java.lang.Object):void");
    }

    public final void zzbv(@Nullable String str) {
        this.zzlj.zzbv(str);
    }
}
