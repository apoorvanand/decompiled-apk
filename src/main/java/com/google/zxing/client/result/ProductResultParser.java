package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.oned.UPCEReader;

public final class ProductResultParser extends ResultParser {
    public ProductParsedResult parse(Result result) {
        BarcodeFormat barcodeFormat = result.getBarcodeFormat();
        if (barcodeFormat != BarcodeFormat.UPC_A && barcodeFormat != BarcodeFormat.UPC_E && barcodeFormat != BarcodeFormat.EAN_8 && barcodeFormat != BarcodeFormat.EAN_13) {
            return null;
        }
        String a = a(result);
        if (!a(a, a.length())) {
            return null;
        }
        return new ProductParsedResult(a, (barcodeFormat == BarcodeFormat.UPC_E && a.length() == 8) ? UPCEReader.convertUPCEtoUPCA(a) : a);
    }
}
