package com.google.android.gms.internal.firebase_ml;

import java.nio.charset.Charset;

public abstract class zzfc implements zzfh {
    private zzfn zztq;
    private long zztr;

    private zzfc(zzfn zzfn) {
        this.zztr = -1;
        this.zztq = zzfn;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    protected zzfc(String str) {
        this(str == null ? null : new zzfn(str));
    }

    /* access modifiers changed from: protected */
    public final Charset a() {
        zzfn zzfn = this.zztq;
        return (zzfn == null || zzfn.zzei() == null) ? zzhi.UTF_8 : this.zztq.zzei();
    }

    public final long getLength() {
        if (this.zztr == -1) {
            this.zztr = zzht.zzb(this);
        }
        return this.zztr;
    }

    public final String getType() {
        zzfn zzfn = this.zztq;
        if (zzfn == null) {
            return null;
        }
        return zzfn.zzeg();
    }

    public final boolean zzef() {
        return true;
    }
}
