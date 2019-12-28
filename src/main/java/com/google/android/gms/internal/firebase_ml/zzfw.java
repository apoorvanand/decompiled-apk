package com.google.android.gms.internal.firebase_ml;

public abstract class zzfw {
    private String zzvc;
    private String zzvd;
    private long zzvk = -1;
    private zzie zzvl;

    public abstract void addHeader(String str, String str2);

    public final String getContentEncoding() {
        return this.zzvc;
    }

    public final long getContentLength() {
        return this.zzvk;
    }

    public final String getContentType() {
        return this.zzvd;
    }

    public final void setContentEncoding(String str) {
        this.zzvc = str;
    }

    public final void setContentLength(long j) {
        this.zzvk = j;
    }

    public final void setContentType(String str) {
        this.zzvd = str;
    }

    public void zza(int i, int i2) {
    }

    public final void zza(zzie zzie) {
        this.zzvl = zzie;
    }

    public final zzie zzex() {
        return this.zzvl;
    }

    public abstract zzfx zzey();
}
