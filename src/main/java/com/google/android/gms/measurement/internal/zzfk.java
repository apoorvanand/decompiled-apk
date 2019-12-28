package com.google.android.gms.measurement.internal;

import android.os.Binder;
import android.text.TextUtils;
import androidx.annotation.BinderThread;
import androidx.annotation.Nullable;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public final class zzfk extends zzdw {
    /* access modifiers changed from: private */
    public final zzjg zzkz;
    private Boolean zzpb;
    @Nullable
    private String zzpc;

    public zzfk(zzjg zzjg) {
        this(zzjg, (String) null);
    }

    private zzfk(zzjg zzjg, @Nullable String str) {
        Preconditions.checkNotNull(zzjg);
        this.zzkz = zzjg;
        this.zzpc = null;
    }

    @BinderThread
    private final void zza(String str, boolean z) {
        boolean z2;
        if (!TextUtils.isEmpty(str)) {
            if (z) {
                try {
                    if (this.zzpb == null) {
                        if (!"com.google.android.gms".equals(this.zzpc) && !UidVerifier.isGooglePlayServicesUid(this.zzkz.getContext(), Binder.getCallingUid())) {
                            if (!GoogleSignatureVerifier.getInstance(this.zzkz.getContext()).isUidGoogleSigned(Binder.getCallingUid())) {
                                z2 = false;
                                this.zzpb = Boolean.valueOf(z2);
                            }
                        }
                        z2 = true;
                        this.zzpb = Boolean.valueOf(z2);
                    }
                    if (this.zzpb.booleanValue()) {
                        return;
                    }
                } catch (SecurityException e) {
                    this.zzkz.zzab().zzgk().zza("Measurement Service called with invalid calling package. appId", zzef.a(str));
                    throw e;
                }
            }
            if (this.zzpc == null && GooglePlayServicesUtilLight.uidHasPackageName(this.zzkz.getContext(), Binder.getCallingUid(), str)) {
                this.zzpc = str;
            }
            if (!str.equals(this.zzpc)) {
                throw new SecurityException(String.format("Unknown calling package name '%s'.", new Object[]{str}));
            }
            return;
        }
        this.zzkz.zzab().zzgk().zzao("Measurement Service called without app package");
        throw new SecurityException("Measurement Service called without app package");
    }

    @BinderThread
    private final void zzb(zzn zzn, boolean z) {
        Preconditions.checkNotNull(zzn);
        zza(zzn.packageName, false);
        this.zzkz.zzz().c(zzn.zzcg, zzn.zzcu);
    }

    @VisibleForTesting
    private final void zzc(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        if (this.zzkz.zzaa().zzhp()) {
            runnable.run();
        } else {
            this.zzkz.zzaa().zza(runnable);
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final zzai a(zzai zzai, zzn zzn) {
        boolean z = false;
        if (!(!"_cmp".equals(zzai.name) || zzai.zzfq == null || zzai.zzfq.size() == 0)) {
            String d = zzai.zzfq.d("_cis");
            if (!TextUtils.isEmpty(d) && (("referrer broadcast".equals(d) || "referrer API".equals(d)) && this.zzkz.zzad().i(zzn.packageName))) {
                z = true;
            }
        }
        if (!z) {
            return zzai;
        }
        this.zzkz.zzab().zzgq().zza("Event has been filtered ", zzai.toString());
        return new zzai("_cmpx", zzai.zzfq, zzai.origin, zzai.zzfu);
    }

    @BinderThread
    public final List<zzjn> zza(zzn zzn, boolean z) {
        zzb(zzn, false);
        try {
            List<zzjp> list = (List) this.zzkz.zzaa().zza(new zzga(this, zzn)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzjp zzjp : list) {
                if (z || !zzjs.e(zzjp.c)) {
                    arrayList.add(new zzjn(zzjp));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zzkz.zzab().zzgk().zza("Failed to get user attributes. appId", zzef.a(zzn.packageName), e);
            return null;
        }
    }

    @BinderThread
    public final List<zzq> zza(String str, String str2, zzn zzn) {
        zzb(zzn, false);
        try {
            return (List) this.zzkz.zzaa().zza(new zzfs(this, zzn, str, str2)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zzkz.zzab().zzgk().zza("Failed to get conditional user properties", e);
            return Collections.emptyList();
        }
    }

    @BinderThread
    public final List<zzjn> zza(String str, String str2, String str3, boolean z) {
        zza(str, true);
        try {
            List<zzjp> list = (List) this.zzkz.zzaa().zza(new zzft(this, str, str2, str3)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzjp zzjp : list) {
                if (z || !zzjs.e(zzjp.c)) {
                    arrayList.add(new zzjn(zzjp));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zzkz.zzab().zzgk().zza("Failed to get user attributes. appId", zzef.a(str), e);
            return Collections.emptyList();
        }
    }

    @BinderThread
    public final List<zzjn> zza(String str, String str2, boolean z, zzn zzn) {
        zzb(zzn, false);
        try {
            List<zzjp> list = (List) this.zzkz.zzaa().zza(new zzfq(this, zzn, str, str2)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzjp zzjp : list) {
                if (z || !zzjs.e(zzjp.c)) {
                    arrayList.add(new zzjn(zzjp));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zzkz.zzab().zzgk().zza("Failed to get user attributes. appId", zzef.a(zzn.packageName), e);
            return Collections.emptyList();
        }
    }

    @BinderThread
    public final void zza(long j, String str, String str2, String str3) {
        zzc((Runnable) new zzgc(this, str2, str3, str, j));
    }

    @BinderThread
    public final void zza(zzai zzai, zzn zzn) {
        Preconditions.checkNotNull(zzai);
        zzb(zzn, false);
        zzc((Runnable) new zzfx(this, zzai, zzn));
    }

    @BinderThread
    public final void zza(zzai zzai, String str, String str2) {
        Preconditions.checkNotNull(zzai);
        Preconditions.checkNotEmpty(str);
        zza(str, true);
        zzc((Runnable) new zzfw(this, zzai, str));
    }

    @BinderThread
    public final void zza(zzjn zzjn, zzn zzn) {
        Preconditions.checkNotNull(zzjn);
        zzb(zzn, false);
        zzc(zzjn.getValue() == null ? new zzfy(this, zzjn, zzn) : new zzgb(this, zzjn, zzn));
    }

    @BinderThread
    public final void zza(zzn zzn) {
        zzb(zzn, false);
        zzc((Runnable) new zzgd(this, zzn));
    }

    @BinderThread
    public final void zza(zzq zzq, zzn zzn) {
        Preconditions.checkNotNull(zzq);
        Preconditions.checkNotNull(zzq.zzdw);
        zzb(zzn, false);
        zzq zzq2 = new zzq(zzq);
        zzq2.packageName = zzn.packageName;
        zzc(zzq.zzdw.getValue() == null ? new zzfm(this, zzq2, zzn) : new zzfp(this, zzq2, zzn));
    }

    @BinderThread
    public final byte[] zza(zzai zzai, String str) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzai);
        zza(str, true);
        this.zzkz.zzab().zzgr().zza("Log and bundle. event", this.zzkz.zzy().a(zzai.name));
        long nanoTime = this.zzkz.zzx().nanoTime() / 1000000;
        try {
            byte[] bArr = (byte[]) this.zzkz.zzaa().zzb(new zzfz(this, zzai, str)).get();
            if (bArr == null) {
                this.zzkz.zzab().zzgk().zza("Log and bundle returned null. appId", zzef.a(str));
                bArr = new byte[0];
            }
            this.zzkz.zzab().zzgr().zza("Log and bundle processed. event, size, time_ms", this.zzkz.zzy().a(zzai.name), Integer.valueOf(bArr.length), Long.valueOf((this.zzkz.zzx().nanoTime() / 1000000) - nanoTime));
            return bArr;
        } catch (InterruptedException | ExecutionException e) {
            this.zzkz.zzab().zzgk().zza("Failed to log and bundle. appId, event, error", zzef.a(str), this.zzkz.zzy().a(zzai.name), e);
            return null;
        }
    }

    @BinderThread
    public final void zzb(zzn zzn) {
        zzb(zzn, false);
        zzc((Runnable) new zzfn(this, zzn));
    }

    @BinderThread
    public final void zzb(zzq zzq) {
        Preconditions.checkNotNull(zzq);
        Preconditions.checkNotNull(zzq.zzdw);
        zza(zzq.packageName, true);
        zzq zzq2 = new zzq(zzq);
        zzc(zzq.zzdw.getValue() == null ? new zzfo(this, zzq2) : new zzfr(this, zzq2));
    }

    @BinderThread
    public final String zzc(zzn zzn) {
        zzb(zzn, false);
        return this.zzkz.d(zzn);
    }

    @BinderThread
    public final List<zzq> zzc(String str, String str2, String str3) {
        zza(str, true);
        try {
            return (List) this.zzkz.zzaa().zza(new zzfv(this, str, str2, str3)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zzkz.zzab().zzgk().zza("Failed to get conditional user properties", e);
            return Collections.emptyList();
        }
    }

    @BinderThread
    public final void zzd(zzn zzn) {
        zza(zzn.packageName, false);
        zzc((Runnable) new zzfu(this, zzn));
    }
}
