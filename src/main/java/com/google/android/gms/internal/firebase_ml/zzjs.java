package com.google.android.gms.internal.firebase_ml;

import java.util.List;

public final class zzjs extends zzgk {
    @zzhu
    private zzjb boundingBox;
    @zzhu
    private Float confidence;
    @zzhu
    private zzjz property;
    @zzhu
    private List<zzkd> words;

    public final /* synthetic */ Object clone() {
        return (zzjs) super.clone();
    }

    public final Float getConfidence() {
        return this.confidence;
    }

    public final List<zzkd> getWords() {
        return this.words;
    }

    public final /* synthetic */ zzgk zza(String str, Object obj) {
        return (zzjs) zzb(str, obj);
    }

    public final /* synthetic */ zzhs zzb(String str, Object obj) {
        return (zzjs) super.zzb(str, obj);
    }

    public final /* synthetic */ zzgk zzdq() {
        return (zzjs) clone();
    }

    public final /* synthetic */ zzhs zzdr() {
        return (zzjs) clone();
    }

    public final zzjb zzha() {
        return this.boundingBox;
    }

    public final zzjz zzhb() {
        return this.property;
    }
}
