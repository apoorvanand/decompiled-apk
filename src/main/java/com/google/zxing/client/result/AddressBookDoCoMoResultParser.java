package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class AddressBookDoCoMoResultParser extends AbstractDoCoMoResultParser {
    private static String parseName(String str) {
        int indexOf = str.indexOf(44);
        if (indexOf < 0) {
            return str;
        }
        return str.substring(indexOf + 1) + ' ' + str.substring(0, indexOf);
    }

    public AddressBookParsedResult parse(Result result) {
        String[] a;
        String a2 = a(result);
        if (!a2.startsWith("MECARD:") || (a = a("N:", a2, true)) == null) {
            return null;
        }
        String parseName = parseName(a[0]);
        String b = b("SOUND:", a2, true);
        String[] a3 = a("TEL:", a2, true);
        String[] a4 = a("EMAIL:", a2, true);
        String b2 = b("NOTE:", a2, false);
        String[] a5 = a("ADR:", a2, true);
        String b3 = b("BDAY:", a2, true);
        return new AddressBookParsedResult(b(parseName), (String[]) null, b, a3, (String[]) null, a4, (String[]) null, (String) null, b2, a5, (String[]) null, b("ORG:", a2, true), !a(b3, 8) ? null : b3, (String) null, a("URL:", a2, true), (String[]) null);
    }
}
