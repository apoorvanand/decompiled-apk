package com.google.android.gms.internal.firebase_ml;

import android.os.SystemClock;
import com.google.android.gms.internal.firebase_ml.zzlo;

final /* synthetic */ class zzpi implements zzng {
    private final long zzaxn;
    private final zzlv zzaxo;
    private final zzoy zzaxp;

    zzpi(long j, zzlv zzlv, zzoy zzoy) {
        this.zzaxn = j;
        this.zzaxo = zzlv;
        this.zzaxp = zzoy;
    }

    public final zzlo.zzq.zza zzkk() {
        long j = this.zzaxn;
        zzlv zzlv = this.zzaxo;
        zzoy zzoy = this.zzaxp;
        return zzlo.zzq.zziu().zzb(zzlo.zzab.zzjz().zzg(zzlo.zzs.zziy().zzk(SystemClock.elapsedRealtime() - j).zzc(zzlv).zzz(zzph.a).zzaa(true).zzab(true)).zze(zzou.zzc(zzoy)));
    }
}
