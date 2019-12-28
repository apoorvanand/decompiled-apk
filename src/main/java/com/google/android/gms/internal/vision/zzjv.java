package com.google.android.gms.internal.vision;

import java.util.Arrays;

final class zzjv {
    final int a;
    final byte[] b;

    zzjv(int i, byte[] bArr) {
        this.a = i;
        this.b = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzjv)) {
            return false;
        }
        zzjv zzjv = (zzjv) obj;
        return this.a == zzjv.a && Arrays.equals(this.b, zzjv.b);
    }

    public final int hashCode() {
        return ((this.a + 527) * 31) + Arrays.hashCode(this.b);
    }
}
