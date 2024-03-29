package com.google.firebase.auth.api.internal;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.firebase_auth.zzek;
import com.google.android.gms.internal.firebase_auth.zzem;
import com.google.android.gms.internal.firebase_auth.zzes;
import com.google.android.gms.internal.firebase_auth.zzew;
import com.google.android.gms.internal.firebase_auth.zzey;
import com.google.firebase.auth.zzf;
import java.util.List;

final class zzj implements zzez<zzek> {
    private final /* synthetic */ zzdm zzla;
    private final /* synthetic */ zzew zzlj;
    private final /* synthetic */ String zzlk;
    private final /* synthetic */ String zzll;
    private final /* synthetic */ Boolean zzlm;
    private final /* synthetic */ zzf zzln;
    private final /* synthetic */ zzes zzlo;

    zzj(zzb zzb, zzew zzew, String str, String str2, Boolean bool, zzf zzf, zzdm zzdm, zzes zzes) {
        this.zzlj = zzew;
        this.zzlk = str;
        this.zzll = str2;
        this.zzlm = bool;
        this.zzln = zzf;
        this.zzla = zzdm;
        this.zzlo = zzes;
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        Object obj2;
        List<zzem> zzer = ((zzek) obj).zzer();
        if (zzer == null || zzer.isEmpty()) {
            this.zzlj.zzbv("No users.");
            return;
        }
        boolean z = false;
        zzem zzem = zzer.get(0);
        zzey zzet = zzem.zzet();
        List zzes = zzet != null ? zzet.zzes() : null;
        if (zzes != null && !zzes.isEmpty()) {
            if (!TextUtils.isEmpty(this.zzlk)) {
                int i = 0;
                while (true) {
                    if (i >= zzes.size()) {
                        break;
                    } else if (((zzew) zzes.get(i)).getProviderId().equals(this.zzlk)) {
                        obj2 = zzes.get(i);
                        break;
                    } else {
                        i++;
                    }
                }
            } else {
                obj2 = zzes.get(0);
            }
            ((zzew) obj2).zzco(this.zzll);
        }
        Boolean bool = this.zzlm;
        if (bool != null) {
            z = bool.booleanValue();
        } else if (zzem.getLastSignInTimestamp() - zzem.getCreationTimestamp() < 1000) {
            z = true;
        }
        zzem.zzo(z);
        zzem.zza(this.zzln);
        this.zzla.zza(this.zzlo, zzem);
    }

    public final void zzbv(@Nullable String str) {
        this.zzlj.zzbv(str);
    }
}
