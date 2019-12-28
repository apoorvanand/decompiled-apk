package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class SMTPResultParser extends ResultParser {
    public EmailAddressParsedResult parse(Result result) {
        String str;
        String str2;
        String a = a(result);
        if (!a.startsWith("smtp:") && !a.startsWith("SMTP:")) {
            return null;
        }
        String substring = a.substring(5);
        int indexOf = substring.indexOf(58);
        if (indexOf >= 0) {
            String substring2 = substring.substring(indexOf + 1);
            substring = substring.substring(0, indexOf);
            int indexOf2 = substring2.indexOf(58);
            if (indexOf2 >= 0) {
                String substring3 = substring2.substring(indexOf2 + 1);
                str2 = substring2.substring(0, indexOf2);
                str = substring3;
            } else {
                str = null;
                str2 = substring2;
            }
        } else {
            str2 = null;
            str = null;
        }
        return new EmailAddressParsedResult(new String[]{substring}, (String[]) null, (String[]) null, str2, str);
    }
}
