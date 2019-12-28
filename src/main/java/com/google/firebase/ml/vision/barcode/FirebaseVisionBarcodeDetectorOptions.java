package com.google.firebase.ml.vision.barcode;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.firebase_ml.zzpq;
import com.google.android.gms.internal.firebase_ml.zzri;
import com.google.android.gms.internal.firebase_ml.zztc;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FirebaseVisionBarcodeDetectorOptions {
    private static final Map<Integer, zzri> zzatj;
    private final int zzatr;

    public static class Builder {
        private int zzats = 0;

        public FirebaseVisionBarcodeDetectorOptions build() {
            return new FirebaseVisionBarcodeDetectorOptions(this.zzats);
        }

        public Builder setBarcodeFormats(@FirebaseVisionBarcode.BarcodeFormat int i, @FirebaseVisionBarcode.BarcodeFormat int... iArr) {
            this.zzats = i;
            if (iArr != null) {
                for (int i2 : iArr) {
                    this.zzats = i2 | this.zzats;
                }
            }
            return this;
        }
    }

    static {
        HashMap hashMap = new HashMap();
        zzatj = hashMap;
        hashMap.put(1, zzri.CODE_128);
        zzatj.put(2, zzri.CODE_39);
        zzatj.put(4, zzri.CODE_93);
        zzatj.put(8, zzri.CODABAR);
        zzatj.put(16, zzri.DATA_MATRIX);
        zzatj.put(32, zzri.EAN_13);
        zzatj.put(64, zzri.EAN_8);
        zzatj.put(128, zzri.ITF);
        zzatj.put(256, zzri.QR_CODE);
        zzatj.put(512, zzri.UPC_A);
        zzatj.put(1024, zzri.UPC_E);
        zzatj.put(2048, zzri.PDF417);
        zzatj.put(4096, zzri.AZTEC);
    }

    private FirebaseVisionBarcodeDetectorOptions(int i) {
        this.zzatr = i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof FirebaseVisionBarcodeDetectorOptions) && this.zzatr == ((FirebaseVisionBarcodeDetectorOptions) obj).zzatr;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzatr));
    }

    public final int zzlt() {
        return this.zzatr;
    }

    public final zzpq.zza zzlu() {
        ArrayList arrayList = new ArrayList();
        if (this.zzatr == 0) {
            arrayList.addAll(zzatj.values());
        } else {
            for (Map.Entry next : zzatj.entrySet()) {
                if ((this.zzatr & ((Integer) next.getKey()).intValue()) != 0) {
                    arrayList.add((zzri) next.getValue());
                }
            }
        }
        return (zzpq.zza) ((zztc) zzpq.zza.zzmt().zzp(arrayList).zzpx());
    }
}
