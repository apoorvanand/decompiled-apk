package com.google.android.gms.internal.firebase_ml;

import java.util.List;

public final class zzkd extends zzgk {
    @zzhu
    private zzjb boundingBox;
    @zzhu
    private Float confidence;
    @zzhu
    private zzjz property;
    @zzhu
    private List<zzjx> symbols;

    public final /* synthetic */ Object clone() {
        return (zzkd) super.clone();
    }

    public final Float getConfidence() {
        return this.confidence;
    }

    public final List<zzjx> getSymbols() {
        return this.symbols;
    }

    public final /* synthetic */ zzgk zza(String str, Object obj) {
        return (zzkd) zzb(str, obj);
    }

    public final /* synthetic */ zzhs zzb(String str, Object obj) {
        return (zzkd) super.zzb(str, obj);
    }

    public final /* synthetic */ zzgk zzdq() {
        return (zzkd) clone();
    }

    public final /* synthetic */ zzhs zzdr() {
        return (zzkd) clone();
    }

    public final zzjb zzha() {
        return this.boundingBox;
    }

    public final zzjz zzhb() {
        return this.property;
    }
}
