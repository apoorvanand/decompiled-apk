package com.google.android.gms.internal.firebase_auth;

final class zzaq extends zzap {
    private final /* synthetic */ zzan zzgs;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaq(zzan zzan, zzam zzam, CharSequence charSequence) {
        super(zzam, charSequence);
        this.zzgs = zzan;
    }

    public final int zze(int i) {
        int length = this.zzgs.a.length();
        int length2 = this.a.length() - length;
        while (i <= length2) {
            int i2 = 0;
            while (i2 < length) {
                if (this.a.charAt(i2 + i) == this.zzgs.a.charAt(i2)) {
                    i2++;
                } else {
                    i++;
                }
            }
            return i;
        }
        return -1;
    }

    public final int zzf(int i) {
        return i + this.zzgs.a.length();
    }
}
