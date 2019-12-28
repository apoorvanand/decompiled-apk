package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class WifiResultParser extends ResultParser {
    public WifiParsedResult parse(Result result) {
        String b;
        String a = a(result);
        if (!a.startsWith("WIFI:") || (b = b("S:", a, ';', false)) == null || b.isEmpty()) {
            return null;
        }
        String b2 = b("P:", a, ';', false);
        String b3 = b("T:", a, ';', false);
        if (b3 == null) {
            b3 = "nopass";
        }
        return new WifiParsedResult(b3, b, b2, Boolean.parseBoolean(b("H:", a, ';', false)));
    }
}
