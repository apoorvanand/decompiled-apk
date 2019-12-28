package com.google.android.gms.internal.measurement;

import java.util.Arrays;

final class zziy {
    final int a;
    final byte[] b;

    zziy(int i, byte[] bArr) {
        this.a = i;
        this.b = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zziy)) {
            return false;
        }
        zziy zziy = (zziy) obj;
        return this.a == zziy.a && Arrays.equals(this.b, zziy.b);
    }

    public final int hashCode() {
        return ((this.a + 527) * 31) + Arrays.hashCode(this.b);
    }
}
