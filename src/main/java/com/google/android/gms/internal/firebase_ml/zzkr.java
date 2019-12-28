package com.google.android.gms.internal.firebase_ml;

import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public final class zzkr {
    private final String className;
    private final zzks zzabh;
    private zzks zzabi;
    private boolean zzabj;

    private zzkr(String str) {
        this.zzabh = new zzks();
        this.zzabi = this.zzabh;
        this.zzabj = false;
        this.className = (String) zzky.checkNotNull(str);
    }

    private final zzkr zzi(String str, @NullableDecl Object obj) {
        zzks zzks = new zzks();
        this.zzabi.c = zzks;
        this.zzabi = zzks;
        zzks.b = obj;
        zzks.a = (String) zzky.checkNotNull(str);
        return this;
    }

    public final String toString() {
        String str = "";
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.className);
        sb.append('{');
        zzks zzks = this.zzabh;
        while (true) {
            zzks = zzks.c;
            if (zzks != null) {
                Object obj = zzks.b;
                sb.append(str);
                str = ", ";
                if (zzks.a != null) {
                    sb.append(zzks.a);
                    sb.append('=');
                }
                if (obj == null || !obj.getClass().isArray()) {
                    sb.append(obj);
                } else {
                    String deepToString = Arrays.deepToString(new Object[]{obj});
                    sb.append(deepToString, 1, deepToString.length() - 1);
                }
            } else {
                sb.append('}');
                return sb.toString();
            }
        }
    }

    public final zzkr zza(String str, float f) {
        return zzi(str, String.valueOf(f));
    }

    public final zzkr zzb(String str, int i) {
        return zzi(str, String.valueOf(i));
    }

    public final zzkr zzb(String str, boolean z) {
        return zzi(str, String.valueOf(z));
    }

    public final zzkr zzh(String str, @NullableDecl Object obj) {
        return zzi(str, obj);
    }
}
