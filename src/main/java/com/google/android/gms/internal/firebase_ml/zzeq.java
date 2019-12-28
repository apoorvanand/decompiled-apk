package com.google.android.gms.internal.firebase_ml;

import java.util.List;

public class zzeq extends zzgk {
    @zzhu
    private int code;
    @zzhu
    private List<zza> errors;
    @zzhu
    private String message;

    public static class zza extends zzgk {
        @zzhu
        private String domain;
        @zzhu
        private String location;
        @zzhu
        private String locationType;
        @zzhu
        private String message;
        @zzhu
        private String reason;

        public /* synthetic */ Object clone() {
            return (zza) super.clone();
        }

        public final String getReason() {
            return this.reason;
        }

        public final /* synthetic */ zzgk zza(String str, Object obj) {
            return (zza) zzb(str, obj);
        }

        public final /* synthetic */ zzhs zzb(String str, Object obj) {
            return (zza) super.zzb(str, obj);
        }

        public final /* synthetic */ zzgk zzdq() {
            return (zza) clone();
        }

        public final /* synthetic */ zzhs zzdr() {
            return (zza) clone();
        }
    }

    static {
        zzhl.zze(zza.class);
    }

    public /* synthetic */ Object clone() {
        return (zzeq) super.clone();
    }

    public final /* synthetic */ zzgk zza(String str, Object obj) {
        return (zzeq) zzb(str, obj);
    }

    public final /* synthetic */ zzhs zzb(String str, Object obj) {
        return (zzeq) super.zzb(str, obj);
    }

    public final List<zza> zzdp() {
        return this.errors;
    }

    public final /* synthetic */ zzgk zzdq() {
        return (zzeq) clone();
    }

    public final /* synthetic */ zzhs zzdr() {
        return (zzeq) clone();
    }
}
