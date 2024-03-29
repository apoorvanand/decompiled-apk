package com.google.android.gms.internal.firebase_auth;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzdv;
import com.google.firebase.auth.zzf;
import java.util.ArrayList;
import java.util.List;

public final class zzfo implements zzdv<zzfo, zzp.zzq> {
    private String zzhy;
    private String zzia;
    private String zzib;
    private String zzie;
    private String zzif;
    private String zzjv;
    private String zzkc;
    private String zzkh;
    private String zzkx;
    private List<zzeu> zzky;
    private String zzrf;
    private boolean zzrg;
    private long zzrh;
    private String zzsd;
    private boolean zzsv;
    private boolean zzsw;
    private String zzsx;
    private String zzsy;
    private String zzsz;

    public final String getEmail() {
        return this.zzif;
    }

    public final String getErrorMessage() {
        return this.zzsz;
    }

    public final String getIdToken() {
        return this.zzib;
    }

    public final String getProviderId() {
        return this.zzia;
    }

    public final String getRawUserInfo() {
        return this.zzsd;
    }

    public final boolean isNewUser() {
        return this.zzrg;
    }

    public final /* synthetic */ zzdv zza(zzjc zzjc) {
        if (zzjc instanceof zzp.zzq) {
            zzp.zzq zzq = (zzp.zzq) zzjc;
            this.zzsv = zzq.zzav();
            this.zzsw = zzq.zzax();
            this.zzib = Strings.emptyToNull(zzq.getIdToken());
            this.zzkh = Strings.emptyToNull(zzq.zzs());
            this.zzrh = zzq.zzt();
            this.zzrf = Strings.emptyToNull(zzq.getLocalId());
            this.zzif = Strings.emptyToNull(zzq.getEmail());
            this.zzjv = Strings.emptyToNull(zzq.getDisplayName());
            this.zzkc = Strings.emptyToNull(zzq.zzam());
            this.zzia = Strings.emptyToNull(zzq.getProviderId());
            this.zzsd = Strings.emptyToNull(zzq.getRawUserInfo());
            this.zzrg = zzq.zzu();
            this.zzsx = zzq.zzaw();
            this.zzsy = zzq.zzay();
            this.zzsz = Strings.emptyToNull(zzq.getErrorMessage());
            this.zzie = Strings.emptyToNull(zzq.zzaz());
            this.zzhy = Strings.emptyToNull(zzq.zzba());
            this.zzky = new ArrayList();
            for (zzr zza : zzq.zzbc()) {
                this.zzky.add(zzeu.zza(zza));
            }
            this.zzkx = Strings.emptyToNull(zzq.zzbb());
            return this;
        }
        throw new IllegalArgumentException("The passed proto must be an instance of VerifyAssertionResponse.");
    }

    @Nullable
    public final String zzba() {
        return this.zzhy;
    }

    public final String zzbb() {
        return this.zzkx;
    }

    public final List<zzeu> zzbc() {
        return this.zzky;
    }

    @Nullable
    public final zzf zzdo() {
        if (TextUtils.isEmpty(this.zzsx) && TextUtils.isEmpty(this.zzsy)) {
            return null;
        }
        String str = this.zzie;
        return str != null ? zzf.zza(this.zzia, this.zzsy, this.zzsx, str) : zzf.zza(this.zzia, this.zzsy, this.zzsx);
    }

    public final zzjm<zzp.zzq> zzee() {
        return zzp.zzq.zzm();
    }

    public final boolean zzfb() {
        return this.zzsv;
    }

    public final boolean zzfc() {
        return this.zzsv || !TextUtils.isEmpty(this.zzsz);
    }

    public final boolean zzfd() {
        return !TextUtils.isEmpty(this.zzkx);
    }

    @Nullable
    public final String zzs() {
        return this.zzkh;
    }

    public final long zzt() {
        return this.zzrh;
    }
}
