package com.google.android.gms.internal.firebase_ml;

import java.util.List;

public final class zziw extends zzgk {
    @zzhu
    private List<zzji> features;
    @zzhu
    private zzjj image;
    @zzhu
    private zzjl imageContext;

    public final /* synthetic */ Object clone() {
        return (zziw) super.clone();
    }

    public final /* synthetic */ zzgk zza(String str, Object obj) {
        return (zziw) zzb(str, obj);
    }

    public final zziw zza(zzjj zzjj) {
        this.image = zzjj;
        return this;
    }

    public final zziw zza(zzjl zzjl) {
        this.imageContext = zzjl;
        return this;
    }

    public final /* synthetic */ zzhs zzb(String str, Object obj) {
        return (zziw) super.zzb(str, obj);
    }

    public final zziw zzb(List<zzji> list) {
        this.features = list;
        return this;
    }

    public final /* synthetic */ zzgk zzdq() {
        return (zziw) clone();
    }

    public final /* synthetic */ zzhs zzdr() {
        return (zziw) clone();
    }
}
