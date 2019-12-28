package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class SMSMMSResultParser extends ResultParser {
    private static void addNumberVia(Collection<String> collection, Collection<String> collection2, String str) {
        int indexOf = str.indexOf(59);
        String str2 = null;
        if (indexOf < 0) {
            collection.add(str);
            collection2.add((Object) null);
            return;
        }
        collection.add(str.substring(0, indexOf));
        String substring = str.substring(indexOf + 1);
        if (substring.startsWith("via=")) {
            str2 = substring.substring(4);
        }
        collection2.add(str2);
    }

    public SMSParsedResult parse(Result result) {
        String str;
        String a = a(result);
        String str2 = null;
        if (!a.startsWith("sms:") && !a.startsWith("SMS:") && !a.startsWith("mms:") && !a.startsWith("MMS:")) {
            return null;
        }
        Map<String, String> d = d(a);
        boolean z = false;
        if (d == null || d.isEmpty()) {
            str = null;
        } else {
            str2 = d.get("subject");
            str = d.get("body");
            z = true;
        }
        int indexOf = a.indexOf(63, 4);
        String substring = (indexOf < 0 || !z) ? a.substring(4) : a.substring(4, indexOf);
        int i = -1;
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        while (true) {
            int i2 = i + 1;
            int indexOf2 = substring.indexOf(44, i2);
            if (indexOf2 > i) {
                addNumberVia(arrayList, arrayList2, substring.substring(i2, indexOf2));
                i = indexOf2;
            } else {
                addNumberVia(arrayList, arrayList2, substring.substring(i2));
                return new SMSParsedResult((String[]) arrayList.toArray(new String[arrayList.size()]), (String[]) arrayList2.toArray(new String[arrayList2.size()]), str2, str);
            }
        }
    }
}
