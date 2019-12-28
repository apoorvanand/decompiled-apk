package com.google.android.gms.internal.firebase_ml;

import android.graphics.Rect;
import androidx.annotation.Nullable;

public final class zzol {
    public static float zza(@Nullable Float f) {
        if (f == null) {
            return 0.0f;
        }
        return f.floatValue();
    }

    @Nullable
    public static Rect zza(@Nullable zzjb zzjb, float f) {
        if (zzjb == null || zzjb.zzhc() == null || zzjb.zzhc().size() != 4) {
            return null;
        }
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MAX_VALUE;
        int i3 = 0;
        int i4 = 0;
        for (zzka next : zzjb.zzhc()) {
            i = Math.min(zzb(next.zzhl()), i);
            i2 = Math.min(zzb(next.zzhm()), i2);
            i3 = Math.max(zzb(next.zzhl()), i3);
            i4 = Math.max(zzb(next.zzhm()), i4);
        }
        return new Rect(Math.round(((float) i) * f), Math.round(((float) i2) * f), Math.round(((float) i3) * f), Math.round(((float) i4) * f));
    }

    private static int zzb(@Nullable Integer num) {
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    @Nullable
    public static String zzbc(int i) {
        switch (i) {
            case 1:
                return "builtin/stable";
            case 2:
                return "builtin/latest";
            default:
                return null;
        }
    }

    public static String zzcc(@Nullable String str) {
        return str == null ? "" : str;
    }
}
