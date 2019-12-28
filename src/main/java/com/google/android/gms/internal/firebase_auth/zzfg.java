package com.google.android.gms.internal.firebase_auth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzfd;

public final class zzfg implements zzfd<zzp.zzl> {
    @Nullable
    private String zzhy;
    private String zzib;
    private String zzif;
    private String zzig;
    private String zzjv;
    private String zzkc;
    private zzfk zzsh = new zzfk();
    private zzfk zzsi = new zzfk();
    private boolean zzsj = true;
    private String zzsk;

    @Nullable
    public final String getDisplayName() {
        return this.zzjv;
    }

    @Nullable
    public final String getEmail() {
        return this.zzif;
    }

    @Nullable
    public final String getPassword() {
        return this.zzig;
    }

    @Nullable
    public final String zzam() {
        return this.zzkc;
    }

    public final boolean zzcp(String str) {
        Preconditions.checkNotEmpty(str);
        return this.zzsi.zzez().contains(str);
    }

    @NonNull
    public final zzfg zzcq(String str) {
        this.zzib = Preconditions.checkNotEmpty(str);
        return this;
    }

    @NonNull
    public final zzfg zzcr(@Nullable String str) {
        if (str == null) {
            this.zzsi.zzez().add("EMAIL");
        } else {
            this.zzif = str;
        }
        return this;
    }

    @NonNull
    public final zzfg zzcs(@Nullable String str) {
        if (str == null) {
            this.zzsi.zzez().add("PASSWORD");
        } else {
            this.zzig = str;
        }
        return this;
    }

    @NonNull
    public final zzfg zzct(@Nullable String str) {
        if (str == null) {
            this.zzsi.zzez().add("DISPLAY_NAME");
        } else {
            this.zzjv = str;
        }
        return this;
    }

    @NonNull
    public final zzfg zzcu(@Nullable String str) {
        if (str == null) {
            this.zzsi.zzez().add("PHOTO_URL");
        } else {
            this.zzkc = str;
        }
        return this;
    }

    @NonNull
    public final zzfg zzcv(String str) {
        Preconditions.checkNotEmpty(str);
        this.zzsh.zzez().add(str);
        return this;
    }

    @NonNull
    public final zzfg zzcw(String str) {
        this.zzsk = Preconditions.checkNotEmpty(str);
        return this;
    }

    @NonNull
    public final zzfg zzcx(@Nullable String str) {
        this.zzhy = str;
        return this;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0080  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ com.google.android.gms.internal.firebase_auth.zzjc zzeq() {
        /*
            r9 = this;
            com.google.android.gms.internal.firebase_auth.zzp$zzl$zza r0 = com.google.android.gms.internal.firebase_auth.zzp.zzl.zzaj()
            boolean r1 = r9.zzsj
            com.google.android.gms.internal.firebase_auth.zzp$zzl$zza r0 = r0.zzf(r1)
            com.google.android.gms.internal.firebase_auth.zzfk r1 = r9.zzsh
            java.util.List r1 = r1.zzez()
            com.google.android.gms.internal.firebase_auth.zzp$zzl$zza r0 = r0.zzd(r1)
            com.google.android.gms.internal.firebase_auth.zzfk r1 = r9.zzsi
            java.util.List r1 = r1.zzez()
            int r2 = r1.size()
            com.google.android.gms.internal.firebase_auth.zzv[] r2 = new com.google.android.gms.internal.firebase_auth.zzv[r2]
            r3 = 0
            r4 = 0
        L_0x0022:
            int r5 = r1.size()
            if (r4 >= r5) goto L_0x0087
            java.lang.Object r5 = r1.get(r4)
            java.lang.String r5 = (java.lang.String) r5
            r6 = -1
            int r7 = r5.hashCode()
            r8 = -333046776(0xffffffffec261c08, float:-8.0325624E26)
            if (r7 == r8) goto L_0x0066
            r8 = 66081660(0x3f0537c, float:1.4125099E-36)
            if (r7 == r8) goto L_0x005c
            r8 = 1939891618(0x73a065a2, float:2.541592E31)
            if (r7 == r8) goto L_0x0052
            r8 = 1999612571(0x772faa9b, float:3.5629384E33)
            if (r7 == r8) goto L_0x0048
            goto L_0x0070
        L_0x0048:
            java.lang.String r7 = "PASSWORD"
            boolean r5 = r5.equals(r7)
            if (r5 == 0) goto L_0x0070
            r5 = 2
            goto L_0x0071
        L_0x0052:
            java.lang.String r7 = "PHOTO_URL"
            boolean r5 = r5.equals(r7)
            if (r5 == 0) goto L_0x0070
            r5 = 3
            goto L_0x0071
        L_0x005c:
            java.lang.String r7 = "EMAIL"
            boolean r5 = r5.equals(r7)
            if (r5 == 0) goto L_0x0070
            r5 = 0
            goto L_0x0071
        L_0x0066:
            java.lang.String r7 = "DISPLAY_NAME"
            boolean r5 = r5.equals(r7)
            if (r5 == 0) goto L_0x0070
            r5 = 1
            goto L_0x0071
        L_0x0070:
            r5 = -1
        L_0x0071:
            switch(r5) {
                case 0: goto L_0x0080;
                case 1: goto L_0x007d;
                case 2: goto L_0x007a;
                case 3: goto L_0x0077;
                default: goto L_0x0074;
            }
        L_0x0074:
            com.google.android.gms.internal.firebase_auth.zzv r5 = com.google.android.gms.internal.firebase_auth.zzv.USER_ATTRIBUTE_NAME_UNSPECIFIED
            goto L_0x0082
        L_0x0077:
            com.google.android.gms.internal.firebase_auth.zzv r5 = com.google.android.gms.internal.firebase_auth.zzv.PHOTO_URL
            goto L_0x0082
        L_0x007a:
            com.google.android.gms.internal.firebase_auth.zzv r5 = com.google.android.gms.internal.firebase_auth.zzv.PASSWORD
            goto L_0x0082
        L_0x007d:
            com.google.android.gms.internal.firebase_auth.zzv r5 = com.google.android.gms.internal.firebase_auth.zzv.DISPLAY_NAME
            goto L_0x0082
        L_0x0080:
            com.google.android.gms.internal.firebase_auth.zzv r5 = com.google.android.gms.internal.firebase_auth.zzv.EMAIL
        L_0x0082:
            r2[r4] = r5
            int r4 = r4 + 1
            goto L_0x0022
        L_0x0087:
            java.util.List r1 = java.util.Arrays.asList(r2)
            com.google.android.gms.internal.firebase_auth.zzp$zzl$zza r0 = r0.zzc(r1)
            java.lang.String r1 = r9.zzib
            if (r1 == 0) goto L_0x0096
            r0.zzap(r1)
        L_0x0096:
            java.lang.String r1 = r9.zzif
            if (r1 == 0) goto L_0x009d
            r0.zzar(r1)
        L_0x009d:
            java.lang.String r1 = r9.zzig
            if (r1 == 0) goto L_0x00a4
            r0.zzas(r1)
        L_0x00a4:
            java.lang.String r1 = r9.zzjv
            if (r1 == 0) goto L_0x00ab
            r0.zzaq(r1)
        L_0x00ab:
            java.lang.String r1 = r9.zzkc
            if (r1 == 0) goto L_0x00b2
            r0.zzau(r1)
        L_0x00b2:
            java.lang.String r1 = r9.zzsk
            if (r1 == 0) goto L_0x00b9
            r0.zzat(r1)
        L_0x00b9:
            java.lang.String r1 = r9.zzhy
            if (r1 == 0) goto L_0x00c0
            r0.zzav(r1)
        L_0x00c0:
            com.google.android.gms.internal.firebase_auth.zzjc r0 = r0.zzih()
            com.google.android.gms.internal.firebase_auth.zzhs r0 = (com.google.android.gms.internal.firebase_auth.zzhs) r0
            com.google.android.gms.internal.firebase_auth.zzp$zzl r0 = (com.google.android.gms.internal.firebase_auth.zzp.zzl) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzfg.zzeq():com.google.android.gms.internal.firebase_auth.zzjc");
    }
}
