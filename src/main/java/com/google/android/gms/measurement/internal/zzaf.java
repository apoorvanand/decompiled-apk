package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;

public final class zzaf {
    final String a;
    final String b;
    final long c;
    final long d;
    final zzah e;
    private final String origin;

    zzaf(zzfj zzfj, String str, String str2, String str3, long j, long j2, Bundle bundle) {
        zzah zzah;
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        this.a = str2;
        this.b = str3;
        this.origin = TextUtils.isEmpty(str) ? null : str;
        this.c = j;
        this.d = j2;
        long j3 = this.d;
        if (j3 != 0 && j3 > this.c) {
            zzfj.zzab().zzgn().zza("Event created with reverse previous/current timestamps. appId", zzef.a(str2));
        }
        if (bundle == null || bundle.isEmpty()) {
            zzah = new zzah(new Bundle());
        } else {
            Bundle bundle2 = new Bundle(bundle);
            Iterator it = bundle2.keySet().iterator();
            while (it.hasNext()) {
                String str4 = (String) it.next();
                if (str4 == null) {
                    zzfj.zzab().zzgk().zzao("Param name can't be null");
                } else {
                    Object a2 = zzfj.zzz().a(str4, bundle2.get(str4));
                    if (a2 == null) {
                        zzfj.zzab().zzgn().zza("Param value can't be null", zzfj.zzy().b(str4));
                    } else {
                        zzfj.zzz().a(bundle2, str4, a2);
                    }
                }
                it.remove();
            }
            zzah = new zzah(bundle2);
        }
        this.e = zzah;
    }

    private zzaf(zzfj zzfj, String str, String str2, String str3, long j, long j2, zzah zzah) {
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(zzah);
        this.a = str2;
        this.b = str3;
        this.origin = TextUtils.isEmpty(str) ? null : str;
        this.c = j;
        this.d = j2;
        long j3 = this.d;
        if (j3 != 0 && j3 > this.c) {
            zzfj.zzab().zzgn().zza("Event created with reverse previous/current timestamps. appId, name", zzef.a(str2), zzef.a(str3));
        }
        this.e = zzah;
    }

    /* access modifiers changed from: package-private */
    public final zzaf a(zzfj zzfj, long j) {
        return new zzaf(zzfj, this.origin, this.a, this.b, this.c, j, this.e);
    }

    public final String toString() {
        String str = this.a;
        String str2 = this.b;
        String valueOf = String.valueOf(this.e);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 33 + String.valueOf(str2).length() + String.valueOf(valueOf).length());
        sb.append("Event{appId='");
        sb.append(str);
        sb.append("', name='");
        sb.append(str2);
        sb.append("', params=");
        sb.append(valueOf);
        sb.append('}');
        return sb.toString();
    }
}
