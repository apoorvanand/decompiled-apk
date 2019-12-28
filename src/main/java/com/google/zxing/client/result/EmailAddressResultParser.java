package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Map;
import java.util.regex.Pattern;

public final class EmailAddressResultParser extends ResultParser {
    private static final Pattern COMMA = Pattern.compile(",");

    public EmailAddressParsedResult parse(Result result) {
        String str;
        String str2;
        String[] strArr;
        String[] strArr2;
        String[] strArr3;
        String str3;
        String a = a(result);
        String[] strArr4 = null;
        if (a.startsWith("mailto:") || a.startsWith("MAILTO:")) {
            String substring = a.substring(7);
            int indexOf = substring.indexOf(63);
            if (indexOf >= 0) {
                substring = substring.substring(0, indexOf);
            }
            try {
                String e = e(substring);
                String[] split = !e.isEmpty() ? COMMA.split(e) : null;
                Map<String, String> d = d(a);
                if (d != null) {
                    if (split == null && (str3 = d.get("to")) != null) {
                        split = COMMA.split(str3);
                    }
                    String str4 = d.get("cc");
                    String[] split2 = str4 != null ? COMMA.split(str4) : null;
                    String str5 = d.get("bcc");
                    if (str5 != null) {
                        strArr4 = COMMA.split(str5);
                    }
                    str = d.get("body");
                    strArr3 = split;
                    strArr = strArr4;
                    strArr2 = split2;
                    str2 = d.get("subject");
                } else {
                    strArr3 = split;
                    strArr2 = null;
                    strArr = null;
                    str2 = null;
                    str = null;
                }
                return new EmailAddressParsedResult(strArr3, strArr2, strArr, str2, str);
            } catch (IllegalArgumentException unused) {
                return null;
            }
        } else if (!EmailDoCoMoResultParser.a(a)) {
            return null;
        } else {
            return new EmailAddressParsedResult(a);
        }
    }
}
