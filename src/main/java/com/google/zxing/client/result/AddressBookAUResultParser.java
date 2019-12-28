package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.ArrayList;

public final class AddressBookAUResultParser extends ResultParser {
    private static String[] matchMultipleValuePrefix(String str, int i, String str2, boolean z) {
        ArrayList arrayList = null;
        for (int i2 = 1; i2 <= i; i2++) {
            String b = b(str + i2 + ':', str2, 13, z);
            if (b == null) {
                break;
            }
            if (arrayList == null) {
                arrayList = new ArrayList(i);
            }
            arrayList.add(b);
        }
        if (arrayList == null) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public AddressBookParsedResult parse(Result result) {
        String a = a(result);
        String[] strArr = null;
        if (!a.contains("MEMORY") || !a.contains("\r\n")) {
            return null;
        }
        String b = b("NAME1:", a, 13, true);
        String b2 = b("NAME2:", a, 13, true);
        String[] matchMultipleValuePrefix = matchMultipleValuePrefix("TEL", 3, a, true);
        String[] matchMultipleValuePrefix2 = matchMultipleValuePrefix("MAIL", 3, a, true);
        String b3 = b("MEMORY:", a, 13, false);
        String b4 = b("ADD:", a, 13, true);
        if (b4 != null) {
            strArr = new String[]{b4};
        }
        return new AddressBookParsedResult(b(b), (String[]) null, b2, matchMultipleValuePrefix, (String[]) null, matchMultipleValuePrefix2, (String[]) null, (String) null, b3, strArr, (String[]) null, (String) null, (String) null, (String) null, (String[]) null, (String[]) null);
    }
}
