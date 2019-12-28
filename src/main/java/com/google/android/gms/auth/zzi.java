package com.google.android.gms.auth;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.internal.auth.zzay;
import com.google.android.gms.internal.auth.zzf;

final class zzi implements zzj<Boolean> {
    private final /* synthetic */ String zzt;

    zzi(String str) {
        this.zzt = str;
    }

    public final /* synthetic */ Object zzb(IBinder iBinder) {
        Bundle bundle = (Bundle) zzd.zza(zzf.zza(iBinder).zza(this.zzt));
        String string = bundle.getString("Error");
        Intent intent = (Intent) bundle.getParcelable("userRecoveryIntent");
        zzay zzc = zzay.zzc(string);
        if (zzay.SUCCESS.equals(zzc)) {
            return true;
        }
        if (zzay.zza(zzc)) {
            Logger a = zzd.zzn;
            String valueOf = String.valueOf(zzc);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 31);
            sb.append("isUserRecoverableError status: ");
            sb.append(valueOf);
            a.w("GoogleAuthUtil", sb.toString());
            throw new UserRecoverableAuthException(string, intent);
        }
        throw new GoogleAuthException(string);
    }
}
