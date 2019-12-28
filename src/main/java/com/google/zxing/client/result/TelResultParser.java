package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class TelResultParser extends ResultParser {
    public TelParsedResult parse(Result result) {
        String str;
        String a = a(result);
        if (!a.startsWith("tel:") && !a.startsWith("TEL:")) {
            return null;
        }
        if (a.startsWith("TEL:")) {
            str = "tel:" + a.substring(4);
        } else {
            str = a;
        }
        int indexOf = a.indexOf(63, 4);
        return new TelParsedResult(indexOf < 0 ? a.substring(4) : a.substring(4, indexOf), str, (String) null);
    }
}
