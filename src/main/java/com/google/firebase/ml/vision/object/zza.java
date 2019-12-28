package com.google.firebase.ml.vision.object;

import com.google.android.gms.common.internal.Objects;

public final class zza {
    private final int zzaww;
    private final boolean zzawx;
    private final boolean zzawy;

    private zza(int i, boolean z, boolean z2) {
        this.zzaww = i;
        this.zzawx = z;
        this.zzawy = z2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zza)) {
            return false;
        }
        zza zza = (zza) obj;
        return zza.zzaww == this.zzaww && zza.zzawy == this.zzawy && zza.zzawx == this.zzawx;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzaww), Boolean.valueOf(this.zzawy), Boolean.valueOf(this.zzawx));
    }
}
