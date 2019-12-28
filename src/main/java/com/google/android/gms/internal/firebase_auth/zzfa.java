package com.google.android.gms.internal.firebase_auth;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzfd;

public final class zzfa implements zzfd<zzp.zzi> {
    private final String zzhu;
    @Nullable
    private final String zzhy;
    @Nullable
    private final String zzkd;

    public zzfa(String str, @Nullable String str2, @Nullable String str3) {
        this.zzhu = Preconditions.checkNotEmpty(str);
        this.zzkd = str2;
        this.zzhy = str3;
    }

    public final /* synthetic */ zzjc zzeq() {
        zzp.zzi.zza zzag = zzp.zzi.zzac().zzag(this.zzhu);
        String str = this.zzkd;
        if (str != null) {
            zzag.zzah(str);
        }
        String str2 = this.zzhy;
        if (str2 != null) {
            zzag.zzai(str2);
        }
        return (zzp.zzi) ((zzhs) zzag.zzih());
    }
}
