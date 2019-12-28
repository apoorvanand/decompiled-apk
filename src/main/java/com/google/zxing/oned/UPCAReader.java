package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.Result;
import com.google.zxing.common.BitArray;
import java.util.Map;

public final class UPCAReader extends UPCEANReader {
    private final UPCEANReader ean13Reader = new EAN13Reader();

    private static Result maybeReturnResult(Result result) {
        String text = result.getText();
        if (text.charAt(0) == '0') {
            return new Result(text.substring(1), (byte[]) null, result.getResultPoints(), BarcodeFormat.UPC_A);
        }
        throw FormatException.getFormatInstance();
    }

    /* access modifiers changed from: protected */
    public int a(BitArray bitArray, int[] iArr, StringBuilder sb) {
        return this.ean13Reader.a(bitArray, iArr, sb);
    }

    /* access modifiers changed from: package-private */
    public BarcodeFormat a() {
        return BarcodeFormat.UPC_A;
    }

    public Result decode(BinaryBitmap binaryBitmap) {
        return maybeReturnResult(this.ean13Reader.decode(binaryBitmap));
    }

    public Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) {
        return maybeReturnResult(this.ean13Reader.decode(binaryBitmap, map));
    }

    public Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) {
        return maybeReturnResult(this.ean13Reader.decodeRow(i, bitArray, map));
    }

    public Result decodeRow(int i, BitArray bitArray, int[] iArr, Map<DecodeHintType, ?> map) {
        return maybeReturnResult(this.ean13Reader.decodeRow(i, bitArray, iArr, map));
    }
}
