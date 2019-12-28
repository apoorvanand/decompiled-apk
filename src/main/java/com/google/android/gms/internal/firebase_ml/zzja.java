package com.google.android.gms.internal.firebase_ml;

import java.util.List;

public final class zzja extends zzgk {
    @zzhu
    private String blockType;
    @zzhu
    private zzjb boundingBox;
    @zzhu
    private Float confidence;
    @zzhu
    private List<zzjs> paragraphs;
    @zzhu
    private zzjz property;

    public final /* synthetic */ Object clone() {
        return (zzja) super.clone();
    }

    public final Float getConfidence() {
        return this.confidence;
    }

    public final List<zzjs> getParagraphs() {
        return this.paragraphs;
    }

    public final /* synthetic */ zzgk zza(String str, Object obj) {
        return (zzja) zzb(str, obj);
    }

    public final /* synthetic */ zzhs zzb(String str, Object obj) {
        return (zzja) super.zzb(str, obj);
    }

    public final /* synthetic */ zzgk zzdq() {
        return (zzja) clone();
    }

    public final /* synthetic */ zzhs zzdr() {
        return (zzja) clone();
    }

    public final zzjb zzha() {
        return this.boundingBox;
    }

    public final zzjz zzhb() {
        return this.property;
    }
}
