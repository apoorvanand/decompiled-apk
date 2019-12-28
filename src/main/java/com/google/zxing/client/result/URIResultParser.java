package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class URIResultParser extends ResultParser {
    private static final Pattern URL_WITHOUT_PROTOCOL_PATTERN = Pattern.compile("([a-zA-Z0-9\\-]+\\.){1,6}[a-zA-Z]{2,}(:\\d{1,5})?(/|\\?|$)");
    private static final Pattern URL_WITH_PROTOCOL_PATTERN = Pattern.compile("[a-zA-Z][a-zA-Z0-9+-.]+:");

    static boolean a(String str) {
        if (str.contains(" ")) {
            return false;
        }
        Matcher matcher = URL_WITH_PROTOCOL_PATTERN.matcher(str);
        if (matcher.find() && matcher.start() == 0) {
            return true;
        }
        Matcher matcher2 = URL_WITHOUT_PROTOCOL_PATTERN.matcher(str);
        return matcher2.find() && matcher2.start() == 0;
    }

    public URIParsedResult parse(Result result) {
        String a = a(result);
        if (a.startsWith("URL:") || a.startsWith("URI:")) {
            return new URIParsedResult(a.substring(4).trim(), (String) null);
        }
        String trim = a.trim();
        if (a(trim)) {
            return new URIParsedResult(trim, (String) null);
        }
        return null;
    }
}
