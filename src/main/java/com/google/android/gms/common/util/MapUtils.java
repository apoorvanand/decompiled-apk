package com.google.android.gms.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.HashMap;

@KeepForSdk
public class MapUtils {
    @KeepForSdk
    public static void writeStringMapToJson(StringBuilder sb, HashMap<String, String> hashMap) {
        String str;
        sb.append("{");
        boolean z = true;
        for (String next : hashMap.keySet()) {
            if (!z) {
                sb.append(",");
            } else {
                z = false;
            }
            String str2 = hashMap.get(next);
            sb.append("\"");
            sb.append(next);
            sb.append("\":");
            if (str2 == null) {
                str = "null";
            } else {
                sb.append("\"");
                sb.append(str2);
                str = "\"";
            }
            sb.append(str);
        }
        sb.append("}");
    }
}
