package com.google.android.gms.internal.firebase_ml;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

final class zzhp extends AbstractSet<Map.Entry<String, Object>> {
    private final /* synthetic */ zzhm zzzj;

    zzhp(zzhm zzhm) {
        this.zzzj = zzhm;
    }

    public final void clear() {
        for (String zzal : this.zzzj.b.a) {
            this.zzzj.b.zzal(zzal).zzb(this.zzzj.a, (Object) null);
        }
    }

    public final boolean isEmpty() {
        for (String zzal : this.zzzj.b.a) {
            if (this.zzzj.b.zzal(zzal).zzh(this.zzzj.a) != null) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Iterator iterator() {
        return new zzho(this.zzzj);
    }

    public final int size() {
        int i = 0;
        for (String zzal : this.zzzj.b.a) {
            if (this.zzzj.b.zzal(zzal).zzh(this.zzzj.a) != null) {
                i++;
            }
        }
        return i;
    }
}
