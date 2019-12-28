package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.regex.Pattern;

public final class EmailDoCoMoResultParser extends AbstractDoCoMoResultParser {
    private static final Pattern ATEXT_ALPHANUMERIC = Pattern.compile("[a-zA-Z0-9@.!#$%&'*+\\-/=?^_`{|}~]+");

    static boolean a(String str) {
        return str != null && ATEXT_ALPHANUMERIC.matcher(str).matches() && str.indexOf(64) >= 0;
    }

    public EmailAddressParsedResult parse(Result result) {
        String[] a;
        String a2 = a(result);
        if (!a2.startsWith("MATMSG:") || (a = a("TO:", a2, true)) == null) {
            return null;
        }
        for (String a3 : a) {
            if (!a(a3)) {
                return null;
            }
        }
        return new EmailAddressParsedResult(a, (String[]) null, (String[]) null, b("SUB:", a2, false), b("BODY:", a2, false));
    }
}
