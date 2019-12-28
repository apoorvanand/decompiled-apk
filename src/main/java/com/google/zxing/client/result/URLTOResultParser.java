package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class URLTOResultParser extends ResultParser {
    public URIParsedResult parse(Result result) {
        int indexOf;
        String a = a(result);
        String str = null;
        if ((!a.startsWith("urlto:") && !a.startsWith("URLTO:")) || (indexOf = a.indexOf(58, 6)) < 0) {
            return null;
        }
        if (indexOf > 6) {
            str = a.substring(6, indexOf);
        }
        return new URIParsedResult(a.substring(indexOf + 1), str);
    }
}
