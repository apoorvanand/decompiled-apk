package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import java.util.concurrent.atomic.AtomicReference;

public final class zzed extends zzge {
    private static final AtomicReference<String[]> zzjx = new AtomicReference<>();
    private static final AtomicReference<String[]> zzjy = new AtomicReference<>();
    private static final AtomicReference<String[]> zzjz = new AtomicReference<>();

    zzed(zzfj zzfj) {
        super(zzfj);
    }

    @Nullable
    private static String zza(String str, String[] strArr, String[] strArr2, AtomicReference<String[]> atomicReference) {
        String str2;
        Preconditions.checkNotNull(strArr);
        Preconditions.checkNotNull(strArr2);
        Preconditions.checkNotNull(atomicReference);
        Preconditions.checkArgument(strArr.length == strArr2.length);
        for (int i = 0; i < strArr.length; i++) {
            if (zzjs.d(str, strArr[i])) {
                synchronized (atomicReference) {
                    String[] strArr3 = atomicReference.get();
                    if (strArr3 == null) {
                        strArr3 = new String[strArr2.length];
                        atomicReference.set(strArr3);
                    }
                    if (strArr3[i] == null) {
                        strArr3[i] = strArr2[i] + "(" + strArr[i] + ")";
                    }
                    str2 = strArr3[i];
                }
                return str2;
            }
        }
        return str;
    }

    @Nullable
    private final String zzb(zzah zzah) {
        if (zzah == null) {
            return null;
        }
        return !zzgj() ? zzah.toString() : a(zzah.zzcv());
    }

    private final boolean zzgj() {
        zzae();
        return this.b.zzhw() && this.b.zzab().a(3);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String a(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        if (!zzgj()) {
            return bundle.toString();
        }
        StringBuilder sb = new StringBuilder();
        for (String str : bundle.keySet()) {
            sb.append(sb.length() != 0 ? ", " : "Bundle[{");
            sb.append(b(str));
            sb.append("=");
            sb.append(bundle.get(str));
        }
        sb.append("}]");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String a(zzaf zzaf) {
        if (zzaf == null) {
            return null;
        }
        if (!zzgj()) {
            return zzaf.toString();
        }
        return "Event{appId='" + zzaf.a + "', name='" + a(zzaf.b) + "', params=" + zzb(zzaf.e) + "}";
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String a(zzai zzai) {
        if (zzai == null) {
            return null;
        }
        if (!zzgj()) {
            return zzai.toString();
        }
        return "origin=" + zzai.origin + ",name=" + a(zzai.name) + ",params=" + zzb(zzai.zzfq);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String a(String str) {
        if (str == null) {
            return null;
        }
        return !zzgj() ? str : zza(str, zzgj.zzpo, zzgj.zzpn, zzjx);
    }

    /* access modifiers changed from: protected */
    public final boolean a() {
        return false;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String b(String str) {
        if (str == null) {
            return null;
        }
        return !zzgj() ? str : zza(str, zzgi.zzpm, zzgi.zzpl, zzjy);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String c(String str) {
        if (str == null) {
            return null;
        }
        if (!zzgj()) {
            return str;
        }
        if (!str.startsWith("_exp_")) {
            return zza(str, zzgl.zzpq, zzgl.zzpp, zzjz);
        }
        return "experiment_id" + "(" + str + ")";
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final /* bridge */ /* synthetic */ zzfc zzaa() {
        return super.zzaa();
    }

    public final /* bridge */ /* synthetic */ zzef zzab() {
        return super.zzab();
    }

    public final /* bridge */ /* synthetic */ zzeo zzac() {
        return super.zzac();
    }

    public final /* bridge */ /* synthetic */ zzs zzad() {
        return super.zzad();
    }

    public final /* bridge */ /* synthetic */ zzr zzae() {
        return super.zzae();
    }

    public final /* bridge */ /* synthetic */ void zzl() {
        super.zzl();
    }

    public final /* bridge */ /* synthetic */ void zzm() {
        super.zzm();
    }

    public final /* bridge */ /* synthetic */ void zzn() {
        super.zzn();
    }

    public final /* bridge */ /* synthetic */ void zzo() {
        super.zzo();
    }

    public final /* bridge */ /* synthetic */ zzac zzw() {
        return super.zzw();
    }

    public final /* bridge */ /* synthetic */ Clock zzx() {
        return super.zzx();
    }

    public final /* bridge */ /* synthetic */ zzed zzy() {
        return super.zzy();
    }

    public final /* bridge */ /* synthetic */ zzjs zzz() {
        return super.zzz();
    }
}
