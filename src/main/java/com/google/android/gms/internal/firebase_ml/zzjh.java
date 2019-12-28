package com.google.android.gms.internal.firebase_ml;

import java.util.List;

public final class zzjh extends zzgk {
    @zzhu
    private zzjb boundingPoly;
    @zzhu
    private Float confidence;
    @zzhu
    private String description;
    @zzhu
    private String locale;
    @zzhu
    private List<zzjq> locations;
    @zzhu
    private String mid;
    @zzhu
    private List<Object> properties;
    @zzhu
    private Float score;
    @zzhu
    private Float topicality;

    public final /* synthetic */ Object clone() {
        return (zzjh) super.clone();
    }

    public final String getDescription() {
        return this.description;
    }

    public final List<zzjq> getLocations() {
        return this.locations;
    }

    public final String getMid() {
        return this.mid;
    }

    public final /* synthetic */ zzgk zza(String str, Object obj) {
        return (zzjh) zzb(str, obj);
    }

    public final /* synthetic */ zzhs zzb(String str, Object obj) {
        return (zzjh) super.zzb(str, obj);
    }

    public final /* synthetic */ zzgk zzdq() {
        return (zzjh) clone();
    }

    public final /* synthetic */ zzhs zzdr() {
        return (zzjh) clone();
    }

    public final zzjb zzhe() {
        return this.boundingPoly;
    }

    public final Float zzhf() {
        return this.score;
    }
}
