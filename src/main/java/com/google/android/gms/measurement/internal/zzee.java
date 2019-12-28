package com.google.android.gms.measurement.internal;

import androidx.exifinterface.media.ExifInterface;

final class zzee implements Runnable {
    private final /* synthetic */ int zzka;
    private final /* synthetic */ String zzkb;
    private final /* synthetic */ Object zzkc;
    private final /* synthetic */ Object zzkd;
    private final /* synthetic */ Object zzke;
    private final /* synthetic */ zzef zzkf;

    zzee(zzef zzef, int i, String str, Object obj, Object obj2, Object obj3) {
        this.zzkf = zzef;
        this.zzka = i;
        this.zzkb = str;
        this.zzkc = obj;
        this.zzkd = obj2;
        this.zzke = obj3;
    }

    public final void run() {
        char c;
        zzef zzef;
        zzeo zzac = this.zzkf.b.zzac();
        if (zzac.k()) {
            if (this.zzkf.zzkg == 0) {
                if (this.zzkf.zzad().zzbn()) {
                    zzef = this.zzkf;
                    zzef.zzae();
                    c = 'C';
                } else {
                    zzef = this.zzkf;
                    zzef.zzae();
                    c = 'c';
                }
                char unused = zzef.zzkg = c;
            }
            if (this.zzkf.zzr < 0) {
                zzef zzef2 = this.zzkf;
                long unused2 = zzef2.zzr = zzef2.zzad().zzao();
            }
            char charAt = "01VDIWEA?".charAt(this.zzka);
            char a = this.zzkf.zzkg;
            long b = this.zzkf.zzr;
            String a2 = zzef.a(true, this.zzkb, this.zzkc, this.zzkd, this.zzke);
            StringBuilder sb = new StringBuilder(String.valueOf(a2).length() + 24);
            sb.append(ExifInterface.GPS_MEASUREMENT_2D);
            sb.append(charAt);
            sb.append(a);
            sb.append(b);
            sb.append(":");
            sb.append(a2);
            String sb2 = sb.toString();
            if (sb2.length() > 1024) {
                sb2 = this.zzkb.substring(0, 1024);
            }
            zzac.zzli.zzc(sb2, 1);
            return;
        }
        this.zzkf.a(6, "Persisted config not initialized. Not logging error/warn");
    }
}
