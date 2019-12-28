package com.google.android.gms.internal.firebase_ml;

import android.annotation.TargetApi;
import android.os.Build;
import com.google.android.gms.internal.firebase_ml.zzlo;

public final class zzou {
    public static int zzbd(int i) {
        switch (i) {
            case 0:
                return 0;
            case 1:
                return 90;
            case 2:
                return 180;
            case 3:
                return 270;
            default:
                StringBuilder sb = new StringBuilder(29);
                sb.append("Invalid rotation: ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    public static int zzbe(int i) {
        switch (i) {
            case 1:
                return 0;
            case 2:
                return 1;
            default:
                StringBuilder sb = new StringBuilder(34);
                sb.append("Invalid landmark type: ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    public static int zzbf(int i) {
        switch (i) {
            case 1:
                return 0;
            case 2:
                return 1;
            default:
                StringBuilder sb = new StringBuilder(30);
                sb.append("Invalid mode type: ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    public static int zzbg(int i) {
        switch (i) {
            case 1:
                return 0;
            case 2:
                return 1;
            default:
                StringBuilder sb = new StringBuilder(40);
                sb.append("Invalid classification type: ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    @TargetApi(19)
    public static zzlo.zzr zzc(zzoy zzoy) {
        int i;
        zzlo.zzr.zzb zzb;
        if (zzoy.zzaux.getBitmap() != null) {
            zzb = zzlo.zzr.zzb.BITMAP;
            i = Build.VERSION.SDK_INT >= 19 ? zzoy.zzaux.getBitmap().getAllocationByteCount() : zzoy.zzaux.getBitmap().getByteCount();
        } else {
            int format = zzoy.zzaux.getMetadata().getFormat();
            if (format != 842094169) {
                switch (format) {
                    case 16:
                        zzb = zzlo.zzr.zzb.NV16;
                        break;
                    case 17:
                        zzb = zzlo.zzr.zzb.NV21;
                        break;
                    default:
                        zzb = zzlo.zzr.zzb.UNKNOWN_FORMAT;
                        break;
                }
            } else {
                zzb = zzlo.zzr.zzb.YV12;
            }
            i = zzoy.zzaux.getGrayscaleImageData().capacity();
        }
        return (zzlo.zzr) ((zztc) zzlo.zzr.zziw().zzb(zzb).zzas(i).zzpx());
    }
}
