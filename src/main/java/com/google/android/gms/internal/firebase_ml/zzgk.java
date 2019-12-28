package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;

public class zzgk extends zzhs implements Cloneable {
    private zzgl zzwj;

    public String toString() {
        zzgl zzgl = this.zzwj;
        if (zzgl == null) {
            return super.toString();
        }
        try {
            return zzgl.toString(this);
        } catch (IOException e) {
            throw zzlh.zza(e);
        }
    }

    /* renamed from: zza */
    public zzgk zzb(String str, Object obj) {
        return (zzgk) super.zzb(str, obj);
    }

    public final void zza(zzgl zzgl) {
        this.zzwj = zzgl;
    }

    /* renamed from: zzdq */
    public zzgk clone() {
        return (zzgk) super.clone();
    }

    public /* synthetic */ zzhs zzdr() {
        return (zzgk) clone();
    }

    public final String zzfh() {
        zzgl zzgl = this.zzwj;
        return zzgl != null ? zzgl.zzc(this) : super.toString();
    }
}
