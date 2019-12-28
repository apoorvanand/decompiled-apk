package com.google.android.gms.internal.firebase_ml;

public final class zzji extends zzgk {
    @zzhu
    private Integer maxResults;
    @zzhu
    private String model;
    @zzhu
    private String type;

    public final /* synthetic */ Object clone() {
        return (zzji) super.clone();
    }

    public final /* synthetic */ zzgk zza(String str, Object obj) {
        return (zzji) zzb(str, obj);
    }

    public final zzji zza(Integer num) {
        this.maxResults = num;
        return this;
    }

    public final zzji zzau(String str) {
        this.model = str;
        return this;
    }

    public final zzji zzav(String str) {
        this.type = str;
        return this;
    }

    public final /* synthetic */ zzhs zzb(String str, Object obj) {
        return (zzji) super.zzb(str, obj);
    }

    public final /* synthetic */ zzgk zzdq() {
        return (zzji) clone();
    }

    public final /* synthetic */ zzhs zzdr() {
        return (zzji) clone();
    }
}
