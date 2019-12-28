package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.BitArray;
import java.util.Collection;
import java.util.Map;

public final class MultiFormatUPCEANReader extends OneDReader {
    private final UPCEANReader[] readers;

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MultiFormatUPCEANReader(java.util.Map<com.google.zxing.DecodeHintType, ?> r3) {
        /*
            r2 = this;
            r2.<init>()
            if (r3 != 0) goto L_0x0007
            r3 = 0
            goto L_0x000f
        L_0x0007:
            com.google.zxing.DecodeHintType r0 = com.google.zxing.DecodeHintType.POSSIBLE_FORMATS
            java.lang.Object r3 = r3.get(r0)
            java.util.Collection r3 = (java.util.Collection) r3
        L_0x000f:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            if (r3 == 0) goto L_0x0055
            com.google.zxing.BarcodeFormat r1 = com.google.zxing.BarcodeFormat.EAN_13
            boolean r1 = r3.contains(r1)
            if (r1 == 0) goto L_0x0027
            com.google.zxing.oned.EAN13Reader r1 = new com.google.zxing.oned.EAN13Reader
            r1.<init>()
        L_0x0023:
            r0.add(r1)
            goto L_0x0035
        L_0x0027:
            com.google.zxing.BarcodeFormat r1 = com.google.zxing.BarcodeFormat.UPC_A
            boolean r1 = r3.contains(r1)
            if (r1 == 0) goto L_0x0035
            com.google.zxing.oned.UPCAReader r1 = new com.google.zxing.oned.UPCAReader
            r1.<init>()
            goto L_0x0023
        L_0x0035:
            com.google.zxing.BarcodeFormat r1 = com.google.zxing.BarcodeFormat.EAN_8
            boolean r1 = r3.contains(r1)
            if (r1 == 0) goto L_0x0045
            com.google.zxing.oned.EAN8Reader r1 = new com.google.zxing.oned.EAN8Reader
            r1.<init>()
            r0.add(r1)
        L_0x0045:
            com.google.zxing.BarcodeFormat r1 = com.google.zxing.BarcodeFormat.UPC_E
            boolean r3 = r3.contains(r1)
            if (r3 == 0) goto L_0x0055
            com.google.zxing.oned.UPCEReader r3 = new com.google.zxing.oned.UPCEReader
            r3.<init>()
            r0.add(r3)
        L_0x0055:
            boolean r3 = r0.isEmpty()
            if (r3 == 0) goto L_0x0073
            com.google.zxing.oned.EAN13Reader r3 = new com.google.zxing.oned.EAN13Reader
            r3.<init>()
            r0.add(r3)
            com.google.zxing.oned.EAN8Reader r3 = new com.google.zxing.oned.EAN8Reader
            r3.<init>()
            r0.add(r3)
            com.google.zxing.oned.UPCEReader r3 = new com.google.zxing.oned.UPCEReader
            r3.<init>()
            r0.add(r3)
        L_0x0073:
            int r3 = r0.size()
            com.google.zxing.oned.UPCEANReader[] r3 = new com.google.zxing.oned.UPCEANReader[r3]
            java.lang.Object[] r3 = r0.toArray(r3)
            com.google.zxing.oned.UPCEANReader[] r3 = (com.google.zxing.oned.UPCEANReader[]) r3
            r2.readers = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.MultiFormatUPCEANReader.<init>(java.util.Map):void");
    }

    public Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) {
        int[] a = UPCEANReader.a(bitArray);
        UPCEANReader[] uPCEANReaderArr = this.readers;
        boolean z = false;
        int i2 = 0;
        while (i2 < uPCEANReaderArr.length) {
            try {
                Result decodeRow = uPCEANReaderArr[i2].decodeRow(i, bitArray, a, map);
                boolean z2 = decodeRow.getBarcodeFormat() == BarcodeFormat.EAN_13 && decodeRow.getText().charAt(0) == '0';
                Collection collection = map == null ? null : (Collection) map.get(DecodeHintType.POSSIBLE_FORMATS);
                if (collection == null || collection.contains(BarcodeFormat.UPC_A)) {
                    z = true;
                }
                if (!z2 || !z) {
                    return decodeRow;
                }
                Result result = new Result(decodeRow.getText().substring(1), decodeRow.getRawBytes(), decodeRow.getResultPoints(), BarcodeFormat.UPC_A);
                result.putAllMetadata(decodeRow.getResultMetadata());
                return result;
            } catch (ReaderException unused) {
                i2++;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public void reset() {
        for (UPCEANReader reset : this.readers) {
            reset.reset();
        }
    }
}
