package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzjn;

public abstract class zzjn<M extends zzjn<M>> extends zzjt {
    protected zzjp a;

    /* access modifiers changed from: protected */
    public int a() {
        if (this.a == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.a.a(); i2++) {
            i += this.a.b(i2).a();
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public final boolean a(zzjk zzjk, int i) {
        int position = zzjk.getPosition();
        if (!zzjk.zzal(i)) {
            return false;
        }
        int i2 = i >>> 3;
        zzjv zzjv = new zzjv(i, zzjk.zzv(position, zzjk.getPosition() - position));
        zzjq zzjq = null;
        zzjp zzjp = this.a;
        if (zzjp == null) {
            this.a = new zzjp();
        } else {
            zzjq = zzjp.a(i2);
        }
        if (zzjq == null) {
            zzjq = new zzjq();
            this.a.a(i2, zzjq);
        }
        zzjq.a(zzjv);
        return true;
    }

    public /* synthetic */ Object clone() {
        zzjn zzjn = (zzjn) super.clone();
        zzjr.zza(this, zzjn);
        return zzjn;
    }

    public void zza(zzjl zzjl) {
        if (this.a != null) {
            for (int i = 0; i < this.a.a(); i++) {
                this.a.b(i).a(zzjl);
            }
        }
    }

    public final /* synthetic */ zzjt zzhr() {
        return (zzjn) clone();
    }
}
