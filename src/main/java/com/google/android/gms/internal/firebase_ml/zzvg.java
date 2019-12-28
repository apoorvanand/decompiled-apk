package com.google.android.gms.internal.firebase_ml;

import java.util.Collections;
import java.util.List;
import java.util.Map;

final class zzvg extends zzvf<FieldDescriptorType, Object> {
    zzvg(int i) {
        super(i, (zzvg) null);
    }

    public final void zzoh() {
        if (!isImmutable()) {
            for (int i = 0; i < zzrl(); i++) {
                Map.Entry zzdg = zzdg(i);
                if (((zzsw) zzdg.getKey()).zzpi()) {
                    zzdg.setValue(Collections.unmodifiableList((List) zzdg.getValue()));
                }
            }
            for (Map.Entry entry : zzrm()) {
                if (((zzsw) entry.getKey()).zzpi()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzoh();
    }
}
