package com.google.android.gms.internal.firebase_auth;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzlg;
import com.google.firebase.auth.api.internal.zzfd;

public final class zzei implements zzfd<zzlg.zza> {
    private String zzri;
    private String zzrj;
    @Nullable
    private final String zzrk;

    public zzei(String str) {
        this(str, (String) null);
    }

    private zzei(String str, @Nullable String str2) {
        this.zzri = zzej.REFRESH_TOKEN.toString();
        this.zzrj = Preconditions.checkNotEmpty(str);
        this.zzrk = null;
    }

    public final /* synthetic */ zzjc zzeq() {
        return (zzlg.zza) ((zzhs) zzlg.zza.zzkz().zzdn(this.zzri).zzdo(this.zzrj).zzih());
    }
}
