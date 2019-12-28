package com.google.android.gms.internal.firebase_ml;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

final class zzho implements Iterator<Map.Entry<String, Object>> {
    private final /* synthetic */ zzhm zzzj;
    private int zzzk = -1;
    private zzhr zzzl;
    private Object zzzm;
    private boolean zzzn;
    private boolean zzzo;
    private zzhr zzzp;

    zzho(zzhm zzhm) {
        this.zzzj = zzhm;
    }

    public final boolean hasNext() {
        if (!this.zzzo) {
            this.zzzo = true;
            Object obj = null;
            while (true) {
                this.zzzm = obj;
                if (this.zzzm != null) {
                    break;
                }
                int i = this.zzzk + 1;
                this.zzzk = i;
                if (i >= this.zzzj.b.a.size()) {
                    break;
                }
                this.zzzl = this.zzzj.b.zzal(this.zzzj.b.a.get(this.zzzk));
                obj = this.zzzl.zzh(this.zzzj.a);
            }
        }
        return this.zzzm != null;
    }

    public final /* synthetic */ Object next() {
        if (hasNext()) {
            this.zzzp = this.zzzl;
            Object obj = this.zzzm;
            this.zzzo = false;
            this.zzzn = false;
            this.zzzl = null;
            this.zzzm = null;
            return new zzhn(this.zzzj, this.zzzp, obj);
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        zzky.checkState(this.zzzp != null && !this.zzzn);
        this.zzzn = true;
        this.zzzp.zzb(this.zzzj.a, (Object) null);
    }
}
