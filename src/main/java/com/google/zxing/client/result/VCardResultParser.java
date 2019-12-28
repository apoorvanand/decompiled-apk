package com.google.zxing.client.result;

import com.facebook.share.internal.ShareConstants;
import com.google.zxing.Result;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class VCardResultParser extends ResultParser {
    private static final Pattern BEGIN_VCARD = Pattern.compile("BEGIN:VCARD", 2);
    private static final Pattern COMMA = Pattern.compile(",");
    private static final Pattern CR_LF_SPACE_TAB = Pattern.compile("\r\n[ \t]");
    private static final Pattern EQUALS = Pattern.compile("=");
    private static final Pattern NEWLINE_ESCAPE = Pattern.compile("\\\\[nN]");
    private static final Pattern SEMICOLON = Pattern.compile(";");
    private static final Pattern SEMICOLON_OR_COMMA = Pattern.compile("[;,]");
    private static final Pattern UNESCAPED_SEMICOLONS = Pattern.compile("(?<!\\\\);+");
    private static final Pattern VCARD_ESCAPES = Pattern.compile("\\\\([,;\\\\])");
    private static final Pattern VCARD_LIKE_DATE = Pattern.compile("\\d{4}-?\\d{2}-?\\d{2}");

    static List<List<String>> a(CharSequence charSequence, String str, boolean z, boolean z2) {
        String str2;
        boolean z3;
        ArrayList arrayList;
        int indexOf;
        String str3;
        String str4 = str;
        int length = str.length();
        int i = 0;
        int i2 = 0;
        ArrayList arrayList2 = null;
        while (i2 < length) {
            Matcher matcher = Pattern.compile("(?:^|\n)" + charSequence + "(?:;([^:]*))?:", 2).matcher(str4);
            if (i2 > 0) {
                i2--;
            }
            if (!matcher.find(i2)) {
                break;
            }
            int end = matcher.end(i);
            String group = matcher.group(1);
            if (group != null) {
                String[] split = SEMICOLON.split(group);
                int length2 = split.length;
                int i3 = 0;
                arrayList = null;
                z3 = false;
                str2 = null;
                while (i3 < length2) {
                    String str5 = split[i3];
                    if (arrayList == null) {
                        arrayList = new ArrayList(1);
                    }
                    arrayList.add(str5);
                    String[] split2 = EQUALS.split(str5, 2);
                    if (split2.length > 1) {
                        String str6 = split2[i];
                        String str7 = split2[1];
                        if ("ENCODING".equalsIgnoreCase(str6) && "QUOTED-PRINTABLE".equalsIgnoreCase(str7)) {
                            z3 = true;
                        } else if ("CHARSET".equalsIgnoreCase(str6)) {
                            str2 = str7;
                        }
                    }
                    i3++;
                    i = 0;
                }
            } else {
                arrayList = null;
                z3 = false;
                str2 = null;
            }
            int i4 = end;
            while (true) {
                indexOf = str4.indexOf(10, i4);
                if (indexOf >= 0) {
                    if (indexOf < str.length() - 1) {
                        int i5 = indexOf + 1;
                        if (str4.charAt(i5) == ' ' || str4.charAt(i5) == 9) {
                            i4 = indexOf + 2;
                        }
                    }
                    if (!z3 || ((indexOf <= 0 || str4.charAt(indexOf - 1) != '=') && (indexOf < 2 || str4.charAt(indexOf - 2) != '='))) {
                        break;
                    }
                    i4 = indexOf + 1;
                } else {
                    break;
                }
            }
            if (indexOf < 0) {
                i2 = length;
            } else {
                if (indexOf > end) {
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList(1);
                    }
                    if (indexOf > 0 && str4.charAt(indexOf - 1) == 13) {
                        indexOf--;
                    }
                    String substring = str4.substring(end, indexOf);
                    if (z) {
                        substring = substring.trim();
                    }
                    if (z3) {
                        str3 = decodeQuotedPrintable(substring, str2);
                        if (z2) {
                            str3 = UNESCAPED_SEMICOLONS.matcher(str3).replaceAll("\n").trim();
                        }
                    } else {
                        if (z2) {
                            substring = UNESCAPED_SEMICOLONS.matcher(substring).replaceAll("\n").trim();
                        }
                        str3 = VCARD_ESCAPES.matcher(NEWLINE_ESCAPE.matcher(CR_LF_SPACE_TAB.matcher(substring).replaceAll("")).replaceAll("\n")).replaceAll("$1");
                    }
                    if (arrayList == null) {
                        ArrayList arrayList3 = new ArrayList(1);
                        arrayList3.add(str3);
                        arrayList2.add(arrayList3);
                    } else {
                        arrayList.add(0, str3);
                        arrayList2.add(arrayList);
                        i2 = indexOf + 1;
                    }
                }
                i2 = indexOf + 1;
            }
            i = 0;
        }
        return arrayList2;
    }

    static List<String> b(CharSequence charSequence, String str, boolean z, boolean z2) {
        List<List<String>> a = a(charSequence, str, z, z2);
        if (a == null || a.isEmpty()) {
            return null;
        }
        return a.get(0);
    }

    private static String decodeQuotedPrintable(CharSequence charSequence, String str) {
        char charAt;
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (i < length) {
            char charAt2 = charSequence.charAt(i);
            if (!(charAt2 == 10 || charAt2 == 13)) {
                if (charAt2 != '=') {
                    maybeAppendFragment(byteArrayOutputStream, str, sb);
                    sb.append(charAt2);
                } else if (!(i >= length - 2 || (charAt = charSequence.charAt(i + 1)) == 13 || charAt == 10)) {
                    i += 2;
                    char charAt3 = charSequence.charAt(i);
                    int a = a(charAt);
                    int a2 = a(charAt3);
                    if (a >= 0 && a2 >= 0) {
                        byteArrayOutputStream.write((a << 4) + a2);
                    }
                }
            }
            i++;
        }
        maybeAppendFragment(byteArrayOutputStream, str, sb);
        return sb.toString();
    }

    private static void formatNames(Iterable<List<String>> iterable) {
        int indexOf;
        if (iterable != null) {
            for (List next : iterable) {
                String str = (String) next.get(0);
                String[] strArr = new String[5];
                int i = 0;
                int i2 = 0;
                while (i < 4 && (indexOf = str.indexOf(59, i2)) >= 0) {
                    strArr[i] = str.substring(i2, indexOf);
                    i++;
                    i2 = indexOf + 1;
                }
                strArr[i] = str.substring(i2);
                StringBuilder sb = new StringBuilder(100);
                maybeAppendComponent(strArr, 3, sb);
                maybeAppendComponent(strArr, 1, sb);
                maybeAppendComponent(strArr, 2, sb);
                maybeAppendComponent(strArr, 0, sb);
                maybeAppendComponent(strArr, 4, sb);
                next.set(0, sb.toString().trim());
            }
        }
    }

    private static boolean isLikeVCardDate(CharSequence charSequence) {
        return charSequence == null || VCARD_LIKE_DATE.matcher(charSequence).matches();
    }

    private static void maybeAppendComponent(String[] strArr, int i, StringBuilder sb) {
        if (strArr[i] != null && !strArr[i].isEmpty()) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            sb.append(strArr[i]);
        }
    }

    private static void maybeAppendFragment(ByteArrayOutputStream byteArrayOutputStream, String str, StringBuilder sb) {
        String str2;
        if (byteArrayOutputStream.size() > 0) {
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (str == null) {
                str2 = new String(byteArray, Charset.forName("UTF-8"));
            } else {
                try {
                    str2 = new String(byteArray, str);
                } catch (UnsupportedEncodingException unused) {
                    str2 = new String(byteArray, Charset.forName("UTF-8"));
                }
            }
            byteArrayOutputStream.reset();
            sb.append(str2);
        }
    }

    private static String toPrimaryValue(List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    private static String[] toPrimaryValues(Collection<List<String>> collection) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (List<String> list : collection) {
            String str = (String) list.get(0);
            if (str != null && !str.isEmpty()) {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[collection.size()]);
    }

    private static String[] toTypes(Collection<List<String>> collection) {
        String str;
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (List next : collection) {
            int i = 1;
            while (true) {
                if (i >= next.size()) {
                    str = null;
                    break;
                }
                String str2 = (String) next.get(i);
                int indexOf = str2.indexOf(61);
                if (indexOf < 0) {
                    str = str2;
                    break;
                } else if ("TYPE".equalsIgnoreCase(str2.substring(0, indexOf))) {
                    str = str2.substring(indexOf + 1);
                    break;
                } else {
                    i++;
                }
            }
            arrayList.add(str);
        }
        return (String[]) arrayList.toArray(new String[collection.size()]);
    }

    public AddressBookParsedResult parse(Result result) {
        String a = a(result);
        Matcher matcher = BEGIN_VCARD.matcher(a);
        if (!matcher.find() || matcher.start() != 0) {
            return null;
        }
        List<List<String>> a2 = a("FN", a, true, false);
        if (a2 == null) {
            a2 = a("N", a, true, false);
            formatNames(a2);
        }
        List<String> b = b("NICKNAME", a, true, false);
        String[] split = b == null ? null : COMMA.split(b.get(0));
        List<List<String>> a3 = a("TEL", a, true, false);
        List<List<String>> a4 = a("EMAIL", a, true, false);
        List<String> b2 = b("NOTE", a, false, false);
        List<List<String>> a5 = a("ADR", a, true, true);
        List<String> b3 = b("ORG", a, true, true);
        List<String> b4 = b("BDAY", a, true, false);
        List<String> list = (b4 == null || isLikeVCardDate(b4.get(0))) ? b4 : null;
        List<String> b5 = b(ShareConstants.TITLE, a, true, false);
        List<List<String>> a6 = a("URL", a, true, false);
        List<String> b6 = b("IMPP", a, true, false);
        List<String> b7 = b("GEO", a, true, false);
        String[] split2 = b7 == null ? null : SEMICOLON_OR_COMMA.split(b7.get(0));
        return new AddressBookParsedResult(toPrimaryValues(a2), split, (String) null, toPrimaryValues(a3), toTypes(a3), toPrimaryValues(a4), toTypes(a4), toPrimaryValue(b6), toPrimaryValue(b2), toPrimaryValues(a5), toTypes(a5), toPrimaryValue(b3), toPrimaryValue(list), toPrimaryValue(b5), toPrimaryValues(a6), (split2 == null || split2.length == 2) ? split2 : null);
    }
}
