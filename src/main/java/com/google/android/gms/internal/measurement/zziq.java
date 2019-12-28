package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zziq;

public abstract class zziq<M extends zziq<M>> extends zziw {
    protected zzis a;

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
    public final boolean a(zzil zzil, int i) {
        int position = zzil.getPosition();
        if (!zzil.zzau(i)) {
            return false;
        }
        int i2 = i >>> 3;
        zziy zziy = new zziy(i, zzil.zzt(position, zzil.getPosition() - position));
        zzir zzir = null;
        zzis zzis = this.a;
        if (zzis == null) {
            this.a = new zzis();
        } else {
            zzir = zzis.a(i2);
        }
        if (zzir == null) {
            zzir = new zzir();
            this.a.a(i2, zzir);
        }
        zzir.a(zziy);
        return true;
    }

    public /* synthetic */ Object clone() {
        zziq zziq = (zziq) super.clone();
        zziu.zza(this, zziq);
        return zziq;
    }

    public void zza(zzio zzio) {
        if (this.a != null) {
            for (int i = 0; i < this.a.a(); i++) {
                this.a.b(i).a(zzio);
            }
        }
    }

    public final /* synthetic */ zziw zzxb() {
        return (zziq) clone();
    }
}
