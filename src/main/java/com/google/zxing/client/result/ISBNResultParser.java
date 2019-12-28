package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

public final class ISBNResultParser extends ResultParser {
    public ISBNParsedResult parse(Result result) {
        if (result.getBarcodeFormat() != BarcodeFormat.EAN_13) {
            return null;
        }
        String a = a(result);
        if (a.length() != 13) {
            return null;
        }
        if (a.startsWith("978") || a.startsWith("979")) {
            return new ISBNParsedResult(a);
        }
        return null;
    }
}
