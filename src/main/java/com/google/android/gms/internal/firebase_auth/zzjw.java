package com.google.android.gms.internal.firebase_auth;

import java.util.Collections;
import java.util.List;
import java.util.Map;

final class zzjw extends zzjt<FieldDescriptorType, Object> {
    zzjw(int i) {
        super(i, (zzjw) null);
    }

    public final void zzfy() {
        if (!isImmutable()) {
            for (int i = 0; i < zzjy(); i++) {
                Map.Entry zzbf = zzbf(i);
                if (((zzhk) zzbf.getKey()).zzhz()) {
                    zzbf.setValue(Collections.unmodifiableList((List) zzbf.getValue()));
                }
            }
            for (Map.Entry entry : zzjz()) {
                if (((zzhk) entry.getKey()).zzhz()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzfy();
    }
}
