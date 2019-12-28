package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;

public class zzet<T> extends zzhs {
    private final zzes zzta;
    private final String zztb;
    private final String zztc;
    private final zzfh zztd;
    private zzfl zzte = new zzfl();
    private zzfl zztf;
    private int zztg = -1;
    private String zzth;
    private Class<T> zzti;

    protected zzet(zzes zzes, String str, String str2, zzfh zzfh, Class<T> cls) {
        this.zzti = (Class) zzky.checkNotNull(cls);
        this.zzta = (zzes) zzky.checkNotNull(zzes);
        this.zztb = (String) zzky.checkNotNull(str);
        this.zztc = (String) zzky.checkNotNull(str2);
        this.zztd = zzfh;
        String zzdu = zzes.zzdu();
        if (zzdu != null) {
            zzfl zzfl = this.zzte;
            StringBuilder sb = new StringBuilder(String.valueOf(zzdu).length() + 23);
            sb.append(zzdu);
            sb.append(" Google-API-Java-Client");
            zzfl.zzab(sb.toString());
        } else {
            this.zzte.zzab("Google-API-Java-Client");
        }
        this.zzte.zzb("X-Goog-Api-Client", zzev.zzea().a(zzes.getClass().getSimpleName()));
    }

    /* access modifiers changed from: protected */
    public IOException a(zzfr zzfr) {
        return new zzfs(zzfr);
    }

    /* renamed from: zzc */
    public zzet<T> zzb(String str, Object obj) {
        return (zzet) super.zzb(str, obj);
    }

    public zzes zzdx() {
        return this.zzta;
    }

    public final zzfl zzdy() {
        return this.zzte;
    }

    public final T zzdz() {
        zzky.checkArgument(true);
        zzky.checkArgument(true);
        zzfo zza = zzdx().zzdv().zza(this.zztb, new zzfg(zzfy.zza(this.zzta.zzdt(), this.zztc, (Object) this, true)), this.zztd);
        new zzep().zzb(zza);
        zza.zza(zzdx().zzdw());
        if (this.zztd == null && (this.zztb.equals("POST") || this.zztb.equals("PUT") || this.zztb.equals("PATCH"))) {
            zza.zza((zzfh) new zzfd());
        }
        zza.zzeo().putAll(this.zzte);
        zza.zza((zzfi) new zzfe());
        zza.zza((zzfu) new zzeu(this, zza.zzeq(), zza));
        zzfr zzet = zza.zzet();
        this.zztf = zzet.zzeo();
        this.zztg = zzet.getStatusCode();
        this.zzth = zzet.getStatusMessage();
        return zzet.zzb(this.zzti);
    }
}
