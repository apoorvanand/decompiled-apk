package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class SMSTOMMSTOResultParser extends ResultParser {
    public SMSParsedResult parse(Result result) {
        String str;
        String a = a(result);
        if (!a.startsWith("smsto:") && !a.startsWith("SMSTO:") && !a.startsWith("mmsto:") && !a.startsWith("MMSTO:")) {
            return null;
        }
        String substring = a.substring(6);
        int indexOf = substring.indexOf(58);
        if (indexOf >= 0) {
            str = substring.substring(indexOf + 1);
            substring = substring.substring(0, indexOf);
        } else {
            str = null;
        }
        return new SMSParsedResult(substring, (String) null, (String) null, str);
    }
}
