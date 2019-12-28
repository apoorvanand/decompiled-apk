package com.google.android.gms.internal.firebase_auth;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.api.internal.zzfd;

public final class zzeq implements zzfd<zzp.zzh> {
    @Nullable
    private String zzhy;
    private String zzib;
    private String zzif;
    private ActionCodeSettings zzkk;
    private String zzku;
    private String zzru;

    public zzeq(zzfw zzfw) {
        this.zzru = zzc(zzfw);
    }

    private zzeq(zzfw zzfw, ActionCodeSettings actionCodeSettings, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        this.zzru = zzc((zzfw) Preconditions.checkNotNull(zzfw));
        this.zzkk = (ActionCodeSettings) Preconditions.checkNotNull(actionCodeSettings);
        this.zzif = null;
        this.zzku = str2;
        this.zzib = str3;
        this.zzhy = null;
    }

    public static zzeq zza(ActionCodeSettings actionCodeSettings, String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(actionCodeSettings);
        return new zzeq(zzfw.VERIFY_AND_CHANGE_EMAIL, actionCodeSettings, (String) null, str2, str, (String) null);
    }

    private static String zzc(zzfw zzfw) {
        switch (zzet.a[zzfw.ordinal()]) {
            case 1:
                return "PASSWORD_RESET";
            case 2:
                return "VERIFY_EMAIL";
            case 3:
                return "EMAIL_SIGNIN";
            case 4:
                return "VERIFY_BEFORE_UPDATE_EMAIL";
            default:
                return "REQUEST_TYPE_UNSET_ENUM_VALUE";
        }
    }

    public final zzeq zza(ActionCodeSettings actionCodeSettings) {
        this.zzkk = (ActionCodeSettings) Preconditions.checkNotNull(actionCodeSettings);
        return this;
    }

    public final zzeq zzcj(String str) {
        this.zzif = Preconditions.checkNotEmpty(str);
        return this;
    }

    public final zzeq zzck(String str) {
        this.zzib = Preconditions.checkNotEmpty(str);
        return this;
    }

    public final zzeq zzcl(@Nullable String str) {
        this.zzhy = str;
        return this;
    }

    public final ActionCodeSettings zzdj() {
        return this.zzkk;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00f1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ com.google.android.gms.internal.firebase_auth.zzjc zzeq() {
        /*
            r4 = this;
            com.google.android.gms.internal.firebase_auth.zzp$zzh$zza r0 = com.google.android.gms.internal.firebase_auth.zzp.zzh.zzaa()
            java.lang.String r1 = r4.zzru
            int r2 = r1.hashCode()
            r3 = -1452371317(0xffffffffa96e928b, float:-5.2973722E-14)
            if (r2 == r3) goto L_0x003d
            r3 = -1341836234(0xffffffffb0053436, float:-4.8459314E-10)
            if (r2 == r3) goto L_0x0033
            r3 = -1288726400(0xffffffffb32f9880, float:-4.088406E-8)
            if (r2 == r3) goto L_0x0029
            r3 = 870738373(0x33e669c5, float:1.0729449E-7)
            if (r2 == r3) goto L_0x001f
            goto L_0x0047
        L_0x001f:
            java.lang.String r2 = "EMAIL_SIGNIN"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0047
            r1 = 2
            goto L_0x0048
        L_0x0029:
            java.lang.String r2 = "VERIFY_BEFORE_UPDATE_EMAIL"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0047
            r1 = 3
            goto L_0x0048
        L_0x0033:
            java.lang.String r2 = "VERIFY_EMAIL"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0047
            r1 = 1
            goto L_0x0048
        L_0x003d:
            java.lang.String r2 = "PASSWORD_RESET"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0047
            r1 = 0
            goto L_0x0048
        L_0x0047:
            r1 = -1
        L_0x0048:
            switch(r1) {
                case 0: goto L_0x0057;
                case 1: goto L_0x0054;
                case 2: goto L_0x0051;
                case 3: goto L_0x004e;
                default: goto L_0x004b;
            }
        L_0x004b:
            com.google.android.gms.internal.firebase_auth.zzfw r1 = com.google.android.gms.internal.firebase_auth.zzfw.OOB_REQ_TYPE_UNSPECIFIED
            goto L_0x0059
        L_0x004e:
            com.google.android.gms.internal.firebase_auth.zzfw r1 = com.google.android.gms.internal.firebase_auth.zzfw.VERIFY_AND_CHANGE_EMAIL
            goto L_0x0059
        L_0x0051:
            com.google.android.gms.internal.firebase_auth.zzfw r1 = com.google.android.gms.internal.firebase_auth.zzfw.EMAIL_SIGNIN
            goto L_0x0059
        L_0x0054:
            com.google.android.gms.internal.firebase_auth.zzfw r1 = com.google.android.gms.internal.firebase_auth.zzfw.VERIFY_EMAIL
            goto L_0x0059
        L_0x0057:
            com.google.android.gms.internal.firebase_auth.zzfw r1 = com.google.android.gms.internal.firebase_auth.zzfw.PASSWORD_RESET
        L_0x0059:
            com.google.android.gms.internal.firebase_auth.zzp$zzh$zza r0 = r0.zza((com.google.android.gms.internal.firebase_auth.zzfw) r1)
            java.lang.String r1 = r4.zzif
            if (r1 == 0) goto L_0x0064
            r0.zzp(r1)
        L_0x0064:
            java.lang.String r1 = r4.zzku
            if (r1 == 0) goto L_0x006b
            r0.zzq(r1)
        L_0x006b:
            java.lang.String r1 = r4.zzib
            if (r1 == 0) goto L_0x0072
            r0.zzr(r1)
        L_0x0072:
            com.google.firebase.auth.ActionCodeSettings r1 = r4.zzkk
            if (r1 == 0) goto L_0x00ed
            boolean r1 = r1.getAndroidInstallApp()
            com.google.android.gms.internal.firebase_auth.zzp$zzh$zza r1 = r0.zza((boolean) r1)
            com.google.firebase.auth.ActionCodeSettings r2 = r4.zzkk
            boolean r2 = r2.canHandleCodeInApp()
            r1.zzb(r2)
            com.google.firebase.auth.ActionCodeSettings r1 = r4.zzkk
            java.lang.String r1 = r1.getUrl()
            if (r1 == 0) goto L_0x0098
            com.google.firebase.auth.ActionCodeSettings r1 = r4.zzkk
            java.lang.String r1 = r1.getUrl()
            r0.zzs(r1)
        L_0x0098:
            com.google.firebase.auth.ActionCodeSettings r1 = r4.zzkk
            java.lang.String r1 = r1.getIOSBundle()
            if (r1 == 0) goto L_0x00a9
            com.google.firebase.auth.ActionCodeSettings r1 = r4.zzkk
            java.lang.String r1 = r1.getIOSBundle()
            r0.zzt(r1)
        L_0x00a9:
            com.google.firebase.auth.ActionCodeSettings r1 = r4.zzkk
            java.lang.String r1 = r1.zzck()
            if (r1 == 0) goto L_0x00ba
            com.google.firebase.auth.ActionCodeSettings r1 = r4.zzkk
            java.lang.String r1 = r1.zzck()
            r0.zzu(r1)
        L_0x00ba:
            com.google.firebase.auth.ActionCodeSettings r1 = r4.zzkk
            java.lang.String r1 = r1.getAndroidPackageName()
            if (r1 == 0) goto L_0x00cb
            com.google.firebase.auth.ActionCodeSettings r1 = r4.zzkk
            java.lang.String r1 = r1.getAndroidPackageName()
            r0.zzv(r1)
        L_0x00cb:
            com.google.firebase.auth.ActionCodeSettings r1 = r4.zzkk
            java.lang.String r1 = r1.getAndroidMinimumVersion()
            if (r1 == 0) goto L_0x00dc
            com.google.firebase.auth.ActionCodeSettings r1 = r4.zzkk
            java.lang.String r1 = r1.getAndroidMinimumVersion()
            r0.zzw(r1)
        L_0x00dc:
            com.google.firebase.auth.ActionCodeSettings r1 = r4.zzkk
            java.lang.String r1 = r1.zzcm()
            if (r1 == 0) goto L_0x00ed
            com.google.firebase.auth.ActionCodeSettings r1 = r4.zzkk
            java.lang.String r1 = r1.zzcm()
            r0.zzy(r1)
        L_0x00ed:
            java.lang.String r1 = r4.zzhy
            if (r1 == 0) goto L_0x00f4
            r0.zzx(r1)
        L_0x00f4:
            com.google.android.gms.internal.firebase_auth.zzjc r0 = r0.zzih()
            com.google.android.gms.internal.firebase_auth.zzhs r0 = (com.google.android.gms.internal.firebase_auth.zzhs) r0
            com.google.android.gms.internal.firebase_auth.zzp$zzh r0 = (com.google.android.gms.internal.firebase_auth.zzp.zzh) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzeq.zzeq():com.google.android.gms.internal.firebase_auth.zzjc");
    }
}
