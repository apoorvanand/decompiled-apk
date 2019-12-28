package com.google.android.gms.internal.firebase_ml;

import java.util.Comparator;

final class zzrw implements Comparator<zzru> {
    zzrw() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzru zzru = (zzru) obj;
        zzru zzru2 = (zzru) obj2;
        zzsb zzsb = (zzsb) zzru.iterator();
        zzsb zzsb2 = (zzsb) zzru2.iterator();
        while (zzsb.hasNext() && zzsb2.hasNext()) {
            int compare = Integer.compare(zzru.zzb(zzsb.nextByte()), zzru.zzb(zzsb2.nextByte()));
            if (compare != 0) {
                return compare;
            }
        }
        return Integer.compare(zzru.size(), zzru2.size());
    }
}
