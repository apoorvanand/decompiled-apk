package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzfu;

public class zzfu<M extends zzfu<M>> extends zzfz {
    protected zzfw a;

    /* access modifiers changed from: protected */
    public int a() {
        if (this.a != null) {
            for (int i = 0; i < this.a.a(); i++) {
                this.a.a(i).a();
            }
        }
        return 0;
    }

    public void zza(zzfs zzfs) {
        if (this.a != null) {
            for (int i = 0; i < this.a.a(); i++) {
                this.a.a(i).a(zzfs);
            }
        }
    }

    /* renamed from: zzeo */
    public M clone() {
        M m = (zzfu) super.clone();
        zzfy.zza(this, (zzfu) m);
        return m;
    }

    public /* synthetic */ zzfz zzep() {
        return (zzfu) clone();
    }
}
