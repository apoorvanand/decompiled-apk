package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.ArrayList;

public final class BizcardResultParser extends AbstractDoCoMoResultParser {
    private static String buildName(String str, String str2) {
        if (str == null) {
            return str2;
        }
        if (str2 == null) {
            return str;
        }
        return str + ' ' + str2;
    }

    private static String[] buildPhoneNumbers(String str, String str2, String str3) {
        ArrayList arrayList = new ArrayList(3);
        if (str != null) {
            arrayList.add(str);
        }
        if (str2 != null) {
            arrayList.add(str2);
        }
        if (str3 != null) {
            arrayList.add(str3);
        }
        int size = arrayList.size();
        if (size == 0) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[size]);
    }

    public AddressBookParsedResult parse(Result result) {
        String a = a(result);
        if (!a.startsWith("BIZCARD:")) {
            return null;
        }
        String buildName = buildName(b("N:", a, true), b("X:", a, true));
        String b = b("T:", a, true);
        String b2 = b("C:", a, true);
        return new AddressBookParsedResult(b(buildName), (String[]) null, (String) null, buildPhoneNumbers(b("B:", a, true), b("M:", a, true), b("F:", a, true)), (String[]) null, b(b("E:", a, true)), (String[]) null, (String) null, (String) null, a("A:", a, true), (String[]) null, b2, (String) null, b, (String[]) null, (String[]) null);
    }
}
