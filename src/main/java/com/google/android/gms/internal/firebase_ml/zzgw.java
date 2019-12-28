package com.google.android.gms.internal.firebase_ml;

import java.math.BigDecimal;
import java.math.BigInteger;

final class zzgw extends zzgm {
    private final zzpo zzxe;
    private final zzgu zzxf;

    zzgw(zzgu zzgu, zzpo zzpo) {
        this.zzxf = zzgu;
        this.zzxe = zzpo;
        zzpo.setLenient(true);
    }

    public final void flush() {
        this.zzxe.flush();
    }

    public final void writeBoolean(boolean z) {
        this.zzxe.zzaf(z);
    }

    public final void writeString(String str) {
        this.zzxe.zzcf(str);
    }

    public final void zza(double d) {
        this.zzxe.zzb(d);
    }

    public final void zza(BigDecimal bigDecimal) {
        this.zzxe.zza(bigDecimal);
    }

    public final void zza(BigInteger bigInteger) {
        this.zzxe.zza(bigInteger);
    }

    public final void zzab(int i) {
        this.zzxe.zzl((long) i);
    }

    public final void zzak(String str) {
        this.zzxe.zzce(str);
    }

    public final void zzd(long j) {
        this.zzxe.zzl(j);
    }

    public final void zzfi() {
        this.zzxe.zzml();
    }

    public final void zzfj() {
        this.zzxe.zzmm();
    }

    public final void zzfk() {
        this.zzxe.zzmn();
    }

    public final void zzfl() {
        this.zzxe.zzmo();
    }

    public final void zzfm() {
        this.zzxe.zzmq();
    }

    public final void zzfn() {
        this.zzxe.setIndent("  ");
    }

    public final void zzj(float f) {
        this.zzxe.zzb((double) f);
    }
}
