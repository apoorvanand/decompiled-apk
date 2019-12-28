package com.google.android.gms.internal.firebase_auth;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzfd;

public final class zzfi implements zzfd<zzp.zzn> {
    @Nullable
    private String zzhy;
    private String zzif;
    private String zzig;
    @Nullable
    private String zzjv;

    public zzfi(@Nullable String str) {
        this.zzhy = str;
    }

    public zzfi(String str, String str2, @Nullable String str3, @Nullable String str4) {
        this.zzif = Preconditions.checkNotEmpty(str);
        this.zzig = Preconditions.checkNotEmpty(str2);
        this.zzjv = null;
        this.zzhy = str4;
    }

    public final /* synthetic */ zzjc zzeq() {
        zzp.zzn.zza zzaq = zzp.zzn.zzaq();
        String str = this.zzif;
        if (str != null) {
            zzaq.zzaw(str);
        }
        String str2 = this.zzig;
        if (str2 != null) {
            zzaq.zzax(str2);
        }
        String str3 = this.zzhy;
        if (str3 != null) {
            zzaq.zzay(str3);
        }
        return (zzp.zzn) ((zzhs) zzaq.zzih());
    }
}
