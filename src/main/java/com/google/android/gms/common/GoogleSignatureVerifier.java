package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.wrappers.Wrappers;
import javax.annotation.CheckReturnValue;

@ShowFirstParty
@CheckReturnValue
@KeepForSdk
public class GoogleSignatureVerifier {
    private static GoogleSignatureVerifier zzam;
    private final Context mContext;
    private volatile String zzan;

    private GoogleSignatureVerifier(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @KeepForSdk
    public static GoogleSignatureVerifier getInstance(Context context) {
        Preconditions.checkNotNull(context);
        synchronized (GoogleSignatureVerifier.class) {
            if (zzam == null) {
                zzc.a(context);
                zzam = new GoogleSignatureVerifier(context);
            }
        }
        return zzam;
    }

    private static zze zza(PackageInfo packageInfo, zze... zzeArr) {
        if (packageInfo.signatures == null) {
            return null;
        }
        if (packageInfo.signatures.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return null;
        }
        zzf zzf = new zzf(packageInfo.signatures[0].toByteArray());
        for (int i = 0; i < zzeArr.length; i++) {
            if (zzeArr[i].equals(zzf)) {
                return zzeArr[i];
            }
        }
        return null;
    }

    private final zzm zza(String str, int i) {
        try {
            PackageInfo zza = Wrappers.packageManager(this.mContext).zza(str, 64, i);
            boolean honorsDebugCertificates = GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext);
            if (zza == null) {
                return zzm.a("null pkg");
            }
            if (zza.signatures.length != 1) {
                return zzm.a("single cert required");
            }
            zzf zzf = new zzf(zza.signatures[0].toByteArray());
            String str2 = zza.packageName;
            zzm a = zzc.a(str2, zzf, honorsDebugCertificates, false);
            return (!a.a || zza.applicationInfo == null || (zza.applicationInfo.flags & 2) == 0 || !zzc.a(str2, zzf, false, true).a) ? a : zzm.a("debuggable release cert app rejected");
        } catch (PackageManager.NameNotFoundException unused) {
            String valueOf = String.valueOf(str);
            return zzm.a(valueOf.length() != 0 ? "no pkg ".concat(valueOf) : new String("no pkg "));
        }
    }

    public static boolean zza(PackageInfo packageInfo, boolean z) {
        if (!(packageInfo == null || packageInfo.signatures == null)) {
            if (zza(packageInfo, z ? zzh.a : new zze[]{zzh.a[0]}) != null) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x006e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.common.zzm zzc(java.lang.String r8) {
        /*
            r7 = this;
            if (r8 != 0) goto L_0x0009
            java.lang.String r8 = "null pkg"
            com.google.android.gms.common.zzm r8 = com.google.android.gms.common.zzm.a((java.lang.String) r8)
            return r8
        L_0x0009:
            java.lang.String r0 = r7.zzan
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L_0x0016
            com.google.android.gms.common.zzm r8 = com.google.android.gms.common.zzm.a()
            return r8
        L_0x0016:
            android.content.Context r0 = r7.mContext     // Catch:{ NameNotFoundException -> 0x0071 }
            com.google.android.gms.common.wrappers.PackageManagerWrapper r0 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r0)     // Catch:{ NameNotFoundException -> 0x0071 }
            r1 = 64
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r8, r1)     // Catch:{ NameNotFoundException -> 0x0071 }
            android.content.Context r1 = r7.mContext
            boolean r1 = com.google.android.gms.common.GooglePlayServicesUtilLight.honorsDebugCertificates(r1)
            if (r0 != 0) goto L_0x0031
            java.lang.String r0 = "null pkg"
        L_0x002c:
            com.google.android.gms.common.zzm r0 = com.google.android.gms.common.zzm.a((java.lang.String) r0)
            goto L_0x006a
        L_0x0031:
            android.content.pm.Signature[] r2 = r0.signatures
            int r2 = r2.length
            r3 = 1
            if (r2 == r3) goto L_0x003a
            java.lang.String r0 = "single cert required"
            goto L_0x002c
        L_0x003a:
            com.google.android.gms.common.zzf r2 = new com.google.android.gms.common.zzf
            android.content.pm.Signature[] r4 = r0.signatures
            r5 = 0
            r4 = r4[r5]
            byte[] r4 = r4.toByteArray()
            r2.<init>(r4)
            java.lang.String r4 = r0.packageName
            com.google.android.gms.common.zzm r1 = com.google.android.gms.common.zzc.a(r4, r2, r1, r5)
            boolean r6 = r1.a
            if (r6 == 0) goto L_0x0069
            android.content.pm.ApplicationInfo r6 = r0.applicationInfo
            if (r6 == 0) goto L_0x0069
            android.content.pm.ApplicationInfo r0 = r0.applicationInfo
            int r0 = r0.flags
            r0 = r0 & 2
            if (r0 == 0) goto L_0x0069
            com.google.android.gms.common.zzm r0 = com.google.android.gms.common.zzc.a(r4, r2, r5, r3)
            boolean r0 = r0.a
            if (r0 == 0) goto L_0x0069
            java.lang.String r0 = "debuggable release cert app rejected"
            goto L_0x002c
        L_0x0069:
            r0 = r1
        L_0x006a:
            boolean r1 = r0.a
            if (r1 == 0) goto L_0x0070
            r7.zzan = r8
        L_0x0070:
            return r0
        L_0x0071:
            java.lang.String r0 = "no pkg "
            java.lang.String r8 = java.lang.String.valueOf(r8)
            int r1 = r8.length()
            if (r1 == 0) goto L_0x0083
            java.lang.String r8 = r0.concat(r8)
            goto L_0x0088
        L_0x0083:
            java.lang.String r8 = new java.lang.String
            r8.<init>(r0)
        L_0x0088:
            com.google.android.gms.common.zzm r8 = com.google.android.gms.common.zzm.a((java.lang.String) r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.GoogleSignatureVerifier.zzc(java.lang.String):com.google.android.gms.common.zzm");
    }

    @KeepForSdk
    public boolean isGooglePublicSignedPackage(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        if (zza(packageInfo, false)) {
            return true;
        }
        if (zza(packageInfo, true)) {
            if (GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext)) {
                return true;
            }
            Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
        }
        return false;
    }

    @ShowFirstParty
    @KeepForSdk
    public boolean isPackageGoogleSigned(String str) {
        zzm zzc = zzc(str);
        zzc.c();
        return zzc.a;
    }

    @ShowFirstParty
    @KeepForSdk
    public boolean isUidGoogleSigned(int i) {
        zzm zzm;
        String[] packagesForUid = Wrappers.packageManager(this.mContext).getPackagesForUid(i);
        if (packagesForUid != null && packagesForUid.length != 0) {
            zzm = null;
            for (String zza : packagesForUid) {
                zzm = zza(zza, i);
                if (zzm.a) {
                    break;
                }
            }
        } else {
            zzm = zzm.a("no pkgs");
        }
        zzm.c();
        return zzm.a;
    }
}
